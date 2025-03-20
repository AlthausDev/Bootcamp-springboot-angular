package com.althaus.gemini.bootcamp.domains.entities.models;

import com.althaus.gemini.bootcamp.domains.entities.Language;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LanguageModel {
    
    @Schema(description = "Identificador del lenguaje", example = "1")
    private int languageId;

    @Schema(description = "Nombre del lenguaje", example = "English")
    private String name;
      
    public static LanguageModel from(Language source){
        return new LanguageModel(
            source.getLanguageId(),
            source.getName()
        );
    }
}
