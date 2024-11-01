package com.aikelt.Aikelt.dto;

import com.aikelt.Aikelt.model.EstonianWord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class GameSolvedResponse {

    private int tokens;
    private String translation;
    private List<EstonianWord> estonianWords;

    @Override
    public String toString() {
        return "{" +
                "tokens=" + tokens +
                ", translation='" + translation + '\'' +
                ", estonianWords=" + estonianWords +
                '}';
    }

    public void setAlmostAllData(String translation, List<EstonianWord> estonianWords) {
        this.translation = translation;
        this.estonianWords = estonianWords;
    }

    // Getters and Setters
    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public List<EstonianWord> getEstonianWords() {
        return this.estonianWords;
    }

    public void setEstonianWords(List<EstonianWord> estonianWords) {
        this.estonianWords = estonianWords;
    }

}