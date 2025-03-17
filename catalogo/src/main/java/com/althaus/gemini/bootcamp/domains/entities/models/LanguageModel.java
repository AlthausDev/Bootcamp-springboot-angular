package com.althaus.gemini.bootcamp.domains.entities.models;

import com.althaus.gemini.bootcamp.domains.entities.Language;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LanguageModel {
    
    private int languageId;
    private String name;
  
    public static LanguageModel from(Language source){
        return new LanguageModel(
            source.getLanguageId(),
            source.getName()
        );
    }
}
