package com.aikelt.Aikelt.dto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InitialSentenceResponse {
    private String content;
    private int totalTokens;
    private String seeds;

    // Updated constructor to include seeds
    public InitialSentenceResponse(String content, int totalTokens) {
        this.content = content;
        this.totalTokens = totalTokens;
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    public String getSeeds() {
        return seeds;
    }

    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    // Updated toString() method to include seeds
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}
