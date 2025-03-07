package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.ioc.Configuracion;
import com.example.ioc.Repositorio;
import com.example.ioc.Servicio;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Value("$mi.valor:mi valor por defecto")
	String valor;
	
	@Autowired
	private Servicio service;
	
	@Autowired
	private Repositorio respositorio;
	
	@Autowired
	private Configuracion configuracion;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
//		Servicio srv = new Servicio(new Repositorio(new Configuracion()));
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		service.guardar();
	}
	
//	@Bean
//	CommandLineRunner demo() {
//		return(args)->{
//			System.err.println("Aplicacion arrancada 2");
//		};
//	}

}
