package com.althaus.gemini.bootcamp.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.althaus.gemini.bootcamp.domains.entities.models.FilmModel;

@FeignClient(name = "CATALOGO-SERVICE", url = "http://localhost:8010")
public interface CatalogoProxy {
	@GetMapping(path = "/")
	String getCatalogo();
	
	@GetMapping(path = "/actuator/info")
	String getInfo();
	
	@GetMapping(path = "/peliculas/v1?mode=short")
	List<FilmModel> getPelis();
	@GetMapping(path = "/peliculas/v1?mode=details")
	List<FilmModel> getPelisConDetalle();
	
	@GetMapping(path = "/peliculas/v1/{id}?mode=short")
	FilmModel getPeli(@PathVariable int id);
	
	@PostMapping(path = "/peliculas/v1/{id}/like")
	String meGusta(@PathVariable int id);
	@PostMapping(path = "/peliculas/v1/{id}/like")
	String meGusta(@PathVariable int id, @RequestHeader(value = "Authorization", required = true) String authorization);
}
