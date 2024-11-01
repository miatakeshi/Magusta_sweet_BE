package com.aikelt.Aikelt.model;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "idString")
    private String idString;

    @Column(name = "english")
    private String english;

    @Column(name = "idParent")
    private String idParent;

    @Column(name = "childReason")
    private String childReason;

    @Column(name = "notes")
    private String notes;

    @Column(name = "features")
    private String features;  // This might be a String if you're storing JSON as a String, or you could use a different type.

    @Column(name = "type")
    private String type;

    @Column(name = "collocations")
    private String collocations;  // Similar to features, this might be a String if storing JSON.

    @Column(name = "lastApperance")
    private Timestamp lastApperance;

    @Column(name = "halo")
    private int halo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }


    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getChildReason() {
        return childReason;
    }

    public void setChildReason(String childReason) {
        this.childReason = childReason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollocations() {
        return collocations;
    }

    public void setCollocations(String collocations) {
        this.collocations = collocations;
    }

    public Timestamp getLastApperance() {
        return lastApperance;
    }

    public void setLastApperance(Timestamp lastApperance) {
        this.lastApperance = lastApperance;
    }



    public int getHalo() {
        return halo;
    }

    public void setHalo(int halo) {
        this.halo = halo;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", idString='" + idString + '\'' +
                ", english='" + english + '\'' +
                ", idParent='" + idParent + '\'' +
                ", childReason='" + childReason + '\'' +
                ", notes='" + notes + '\'' +
                ", features='" + features + '\'' +
                ", type='" + type + '\'' +
                ", collocations='" + collocations + '\'' +
                ", lastApperance=" + lastApperance +
                ", halo=" + halo +
                '}';
    }
}