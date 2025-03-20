package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.althaus.gemini.bootcamp.domains.core.contracts.repositories.CoreRepository;
import com.althaus.gemini.bootcamp.domains.entities.Language;

public interface LanguageRepository extends CoreRepository<Language, Integer>{

    List<Language> findAllByOrderByName();
	List<Language> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
    List<Language> findByLanguageIdGreaterThan(int languageId);
    List<Language> findByNameContaining(String name);
    List<Language> findByLastUpdate(Timestamp lastUpdate);
}
