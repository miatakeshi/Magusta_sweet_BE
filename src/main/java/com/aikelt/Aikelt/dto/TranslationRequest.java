package com.aikelt.Aikelt.dto;

import java.util.List;

public class TranslationRequest {

    private String language;
    private String sentence;

    // Default constructor
    public TranslationRequest() {
    }

    // Parameterized constructor
    public TranslationRequest(String language, String sentence) {
        this.language = language;
        this.sentence = sentence;
    }

    // Getters and setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    // Optional: Override toString method for better logging/debugging
    @Override
    public String toString() {
        return "TranslationRequest{" +
                "language='" + language + '\'' +
                ", sentence='" + sentence + '\'' +
                '}';
    }
}