package com.aikelt.Aikelt.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

        // Fields that will not be stored in the database
        private Long id;
        private com.aikelt.Aikelt.model.Game.GameType type;
        private Integer wordLength;
        private String seeds;
        private String translation;
        private String human_response;

        // Changed to use DictionaryWord instead of EstonianWord
        private List<DictionaryWord> estonianWords = new ArrayList<>();

        private String initialSentence;
        private Timestamp openTime;
        private Timestamp closeTime;
        private int tokens = 0;
        private int totalPoints = 0;
        private int maxPoints = 0;
        private String correction;
        private int step = 1;

        // New field: userID (UUID)
        private UUID userID;

        // Enum for game types
        public enum GameType {
            ESTONIAN, ENGLISH
        }



    // Constructors
    public Game() {
    }

    public Game(GameType type, String initialSentence, Integer initialTokens, Integer length, String seeds, String translation, UUID userID) {
        this.type = type;
        this.initialSentence = initialSentence;
        this.openTime = Timestamp.from(Instant.now());  // Sets the current timestamp as the open time
        this.tokens = initialTokens;
        this.totalPoints = 0;
        this.maxPoints = 0;
        this.wordLength = length;
        this.seeds = seeds;
        this.translation = translation;
        this.userID = userID;
        this.step = 1;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
    }

    public String getInitialSentence() {
        return initialSentence;
    }

    public void setInitialSentence(String initialSentence) {
        this.initialSentence = initialSentence;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public String getSeeds() {
        return seeds;
    }

    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    // Updated to use List<DictionaryWord>
    public List<DictionaryWord> getEstonianWords() {
        return estonianWords;
    }

    // Updated parameter to List<DictionaryWord>
    public void setEstonianWords(List<DictionaryWord> estonianWords) {
        this.estonianWords = new ArrayList<>();
        this.estonianWords.addAll(estonianWords);  // Adds all words to the list
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public void setWordLength(Integer wordLength) {
        this.wordLength = wordLength;
    }

    public String getHumanResponse() {
        return human_response;
    }

    public void setHumanResponse(String human_response) {
        this.human_response = human_response;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void addTokens(Integer newTokens) {
        if (newTokens != null && newTokens > 0) {
            this.tokens += newTokens;
        } else {
            throw new IllegalArgumentException("New tokens must be a positive value.");
        }
    }

    public static String oppositeType(GameType type) {
        if (type == GameType.ESTONIAN) {
            return GameType.ENGLISH.name();
        } else if (type == GameType.ENGLISH) {
            return GameType.ESTONIAN.name();
        } else {
            throw new IllegalArgumentException("Unknown GameType: " + type);
        }
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", type=" + type +
                ", initialSentence='" + initialSentence + '\'' +
                ", openTime=" + openTime +
                ", wordLength=" + wordLength +
                ", closeTime=" + closeTime +
                ", tokens=" + tokens +
                ", totalPoints=" + totalPoints +
                ", maxPoints=" + maxPoints +
                ", correction='" + correction + '\'' +
                ", seeds='" + seeds + '\'' +
                ", translation='" + translation + '\'' +
                ", human_response='" + human_response + '\'' +
                ", estonianWords=" + estonianWords +
                ", userID=" + userID +
                ", step=" + step +
                '}';
    }
}
