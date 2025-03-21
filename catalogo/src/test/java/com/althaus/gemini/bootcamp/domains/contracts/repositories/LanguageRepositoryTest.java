package com.althaus.gemini.bootcamp.domains.contracts.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Timestamp;
import java.util.List;
import com.althaus.gemini.bootcamp.domains.entities.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



@DataJpaTest
public class LanguageRepositoryTest {

    @Autowired
    private LanguageRepository languageRepository;

    private Language language1;
    private Language language2;

    @BeforeEach
    public void setUp() {
        language1 = new Language();
        language1.setName("English");
        language1.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        language2 = new Language();
        language2.setName("Spanish");
        language2.setLastUpdate(new Timestamp(System.currentTimeMillis() - 10000));

        languageRepository.save(language1);
        languageRepository.save(language2);
    }

    @Test
    public void testFindAllByOrderByName() {
        List<Language> languages = languageRepository.findAllByOrderByName();
        assertEquals(2, languages.size());
        assertEquals("English", languages.get(0).getName());
        assertEquals("Spanish", languages.get(1).getName());
    }

    @Test
    public void testFindByLastUpdateGreaterThanEqualOrderByLastUpdate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 5000);
        List<Language> languages = languageRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(timestamp);
        assertEquals(1, languages.size());
        assertEquals("English", languages.get(0).getName());
    }

    @Test
    public void testFindByLanguageIdGreaterThan() {
        List<Language> languages = languageRepository.findByLanguageIdGreaterThan(0);
        assertEquals(2, languages.size());
    }

    @Test
    public void testFindByNameContaining() {
        List<Language> languages = languageRepository.findByNameContaining("Eng");
        assertEquals(1, languages.size());
        assertEquals("English", languages.get(0).getName());
    }

    @Test
    public void testFindByLastUpdate() {
        List<Language> languages = languageRepository.findByLastUpdate(language1.getLastUpdate());
        assertEquals(1, languages.size());
        assertEquals("English", languages.get(0).getName());
    }
}