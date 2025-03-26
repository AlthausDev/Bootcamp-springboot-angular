package com.althaus.gemini.bootcamp.domains.entities.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.althaus.gemini.bootcamp.domains.entities.Language;


class LanguageModelTest {

    @Test
    void testFrom() {
        // Arrange
        Language language = new Language();
        language.setLanguageId(1);
        language.setName("English");

        // Act
        LanguageModel languageModel = LanguageModel.from(language);

        // Assert
        assertEquals(1, languageModel.getLanguageId());
        assertEquals("English", languageModel.getName());
    }
}