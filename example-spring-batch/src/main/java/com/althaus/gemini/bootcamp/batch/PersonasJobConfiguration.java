package com.althaus.gemini.bootcamp.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import com.althaus.gemini.bootcamp.models.Persona;
import com.althaus.gemini.bootcamp.models.PersonaModel;


@Configuration
public class PersonasJobConfiguration {

	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	PlatformTransactionManager transactionManager;	
	
	@Autowired
	public PersonaItemProcessor personaItemProcessor;
	
	
	public FlatFileItemReader<PersonaModel> personaCSVItemReader(String fname){
		return new FlatFileItemReaderBuilder<PersonaModel>()
				.name("personaCSVItemReader")
				.resource(new FileSystemResource(fname))
				.linesToSkip(1)
				.delimited()
				.names(new String[] {"id", "nombre", "apellidos", "correo", "sexo", "ip"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<PersonaModel>() {{
					setTargetType(PersonaModel.class);
				}})
				.build();
	}

	
	@Bean
	@DependsOnDatabaseInitialization
	public JdbcBatchItemWriter<Persona> personaDBItemWriter(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Persona>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO personas VALUES (:id,:nombre,:correo,:ip)")
				.dataSource(dataSource)
				.build();
	}
	
	Step importCSV2DBStep(int index, String file, JdbcBatchItemWriter<Persona> toDB) {
		return new StepBuilder("importCSV2DBStep" + index, jobRepository)
				.<PersonaModel, Persona> chunk(10, transactionManager)
				.reader(personaCSVItemReader(file))
				.processor(personaItemProcessor)
				.writer(toDB)
				.build();
	}
	
	@Bean
	public Job personasJob(PersonasJobListener listener, JdbcBatchItemWriter<Persona> personaDBItemWriter) {
		return new JobBuilder("personasJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(importCSV2DBStep(1, "input/personas-1.csv", personaDBItemWriter))
				.build();
	}
	
	@Bean
	public Job personasJob(Step importXML2DBStep1, Step 
	exportDB2XMLStep, Step exportDB2CSVStep) {
		return new JobBuilder("personasJob", jobRepository)
			.incrementer(new RunIdIncrementer())
			.start(importXML2DBStep1)
			.next(exportDB2XMLStep)
			.next(exportDB2CSVStep)
			.build();
	}

}
