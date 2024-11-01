package com.aikelt.Aikelt.repository.mappers;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.aikelt.Aikelt.model.Word;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class WordRowMapper implements RowMapper<Word> {

    @Override
    public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
        Word word = new Word();

        // Map each column from the ResultSet to the corresponding field in the Word object
        word.setId(rs.getString("id"));  // UUID as a String
        word.setIdString(rs.getString("idString"));
        word.setEnglish(rs.getString("english"));
        word.setIdParent(rs.getString("idParent")); // Nullable field
        word.setChildReason(rs.getString("childReason"));
        word.setNotes(rs.getString("notes"));
        word.setFeatures(rs.getString("features"));
        word.setType(rs.getString("type"));
        word.setCollocations(rs.getString("collocations"));
        word.setLastApperance(rs.getTimestamp("lastApperance"));
        word.setHalo(rs.getInt("halo"));

        return word;
    }
}