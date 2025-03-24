package com.althaus.gemini.bootcamp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.althaus.gemini.bootcamp.application.CountryClient;
import com.althaus.gemini.bootcamp.consumingwebservice.wsdl.GetCountryResponse;

@SpringBootApplication
public class GsConsumingWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsConsumingWebServiceApplication.class, args);
	}

	 @Bean
  CommandLineRunner lookup(CountryClient countryClient) {
    return args -> {
      String country = "Spain";

      if (args.length > 0) {
        country = args[0];
      }
      GetCountryResponse response = countryClient.getCountry(country);
      System.err.println(response.getCountry().getCurrency());
    };
  }

}
