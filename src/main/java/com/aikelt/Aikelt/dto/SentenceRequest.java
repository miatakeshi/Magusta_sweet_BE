package com.aikelt.Aikelt.dto;

import java.util.List;

public class SentenceRequest {
    private String language;
    private int length;
    private List<String> meaningsToUse;

    // Getters and Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<String> getMeaningsToUse() {
        return meaningsToUse;
    }

    public void setMeaningsToUse(List<String> meaningsToUse) {
        this.meaningsToUse = meaningsToUse;
    }
}
