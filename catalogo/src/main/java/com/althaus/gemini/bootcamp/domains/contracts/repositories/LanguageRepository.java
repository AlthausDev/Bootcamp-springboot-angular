package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.althaus.gemini.bootcamp.domains.core.contracts.repositories.CoreRepository;
import com.althaus.gemini.bootcamp.domains.entities.Language;

@RepositoryRestResource(exported = false)
public interface LanguageRepository extends CoreRepository<Language, Integer>{

    List<Language> findAllByOrderByName();
	List<Language> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
    List<Language> findByLanguageIdGreaterThan(int languageId);
    List<Language> findByNameContaining(String name);
    List<Language> findByLastUpdate(Timestamp lastUpdate);
}
