package com.aikelt.Aikelt.model;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class PersonalWord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    // Many-to-one relationship, now storing just UUID for dictionaryWord
    @Column(name = "dictionaryWordId", nullable = false)
    private UUID dictionaryWordId;

    @Column(name = "lastAppearance", nullable = false)
    private LocalDateTime lastAppearance;

    @Column(name = "estonian", nullable = false)
    private String estonian;

    @Column(name = "halo", nullable = false)
    private Integer halo;

    @Column(name = "level_D", nullable = false)
    private Integer level;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, columnDefinition = "VARCHAR(255) CHECK (state IN ('MASTERED', 'RESERVED', 'LIVE'))")
    private State state;

    public PersonalWord() {

    }

    // Enum for allowed states
    public enum State {
        MASTERED, RESERVED, LIVE
    }

    // Constructor with all fields
    public PersonalWord( UUID userId, UUID dictionaryWordId, String estonian, Integer halo, Integer level, State state) {
        this.userId = userId;
        this.dictionaryWordId = dictionaryWordId;
        this.estonian = estonian;
        this.halo = halo;
        this.level = level;
        this.state = state;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getDictionaryWordId() {
        return dictionaryWordId;
    }

    public void setDictionaryWordId(UUID dictionaryWordId) {
        this.dictionaryWordId = dictionaryWordId;
    }

    public LocalDateTime getLastAppearance() {
        return lastAppearance;
    }

    public void setLastAppearance(LocalDateTime lastAppearance) {
        this.lastAppearance = lastAppearance;
    }

    public String getEstonian() {
        return estonian;
    }

    public void setEstonian(String estonian) {
        this.estonian = estonian;
    }

    public Integer getHalo() {
        return halo;
    }

    public void setHalo(Integer halo) {
        this.halo = halo;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}