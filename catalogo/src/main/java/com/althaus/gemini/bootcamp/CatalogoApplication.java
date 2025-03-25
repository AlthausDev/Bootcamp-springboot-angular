package com.althaus.gemini.bootcamp;

import java.util.TreeMap;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
	}

	@Bean
	public OpenApiCustomizer sortSchemasAlphabetically() {
	    return openApi -> {
	        var schemas = openApi.getComponents().getSchemas();
	        openApi.getComponents().setSchemas(new TreeMap<>(schemas));
	    };
	}
	
	@Bean
	@Primary
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplateLB(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@LoadBalanced
	@Bean
	RestClient.Builder restClientBuilderLB() {
		return RestClient.builder();
	}

	@Primary
	@Bean
	RestClient.Builder restClientBuilder() {
		return RestClient.builder();
	}
}
