package com.althaus.gemini.bootcamp.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.althaus.gemini.bootcamp.models.Persona;
import com.althaus.gemini.bootcamp.models.PersonaModel;

@Component
public class PersonaItemProcessor implements ItemProcessor<PersonaModel, Persona> {

	private static final Logger log = LoggerFactory.getLogger(PersonaItemProcessor.class);

	@Override
	public Persona process(PersonaModel item) throws Exception {
		
		if (item.getId() % 2 == 0 && "Male".equals(item.getSexo()))
			return null;

		Persona persona = new Persona(item.getId(), 
				item.getApellidos() + ", " + 
				item.getNombre(), 
				item.getCorreo(),
				item.getIp());
		
		log.info("Procesando: " + item);
		return persona;
	}
}
