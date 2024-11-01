package com.aikelt.Aikelt.model;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable  // This marks the class as embeddable in another entity (Game).
public class EstonianWord {

    private String word;
    private String basicForm;
    private int achieved;

    // Constructors
    public EstonianWord() {
    }

    public EstonianWord(String word, String basicForm, int achieved) {
        this.word = word;
        this.basicForm = basicForm;
        this.achieved = achieved;
    }

    // Getters and Setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getBasicForm() {
        return basicForm;
    }

    public void setBasicForm(String basicForm) {
        this.basicForm = basicForm;
    }

    public int getAchieved() {
        return achieved;
    }

    public void setAchieved(int achieved) {
        this.achieved = achieved;
    }

    @Override
    public String toString() {
        return "EstonianWord{" +
                "word='" + word + '\'' +
                ", basicForm='" + basicForm + '\'' +
                ", achieved=" + achieved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstonianWord that = (EstonianWord) o;
        return achieved == that.achieved &&
                Objects.equals(word, that.word) &&
                Objects.equals(basicForm, that.basicForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, basicForm, achieved);
    }
}
