package com.aikelt.Aikelt.repository.mappers;

import com.aikelt.Aikelt.model.DictionaryWord;
import com.aikelt.Aikelt.model.PersonalWord;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PersonalWordRowMapper implements RowMapper<PersonalWord> {

    @Override
    public PersonalWord mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonalWord personalWord = new PersonalWord();

        // Mapping the columns to the PersonalWord fields
        personalWord.setId(UUID.fromString(rs.getString("id")));  // UUID from string
        personalWord.setUserId(UUID.fromString(rs.getString("userId")));  // UUID from string

        // Set dictionaryWordId directly as UUID without creating a DictionaryWord object
        personalWord.setDictionaryWordId(UUID.fromString(rs.getString("dictionaryWordId")));  // UUID for dictionaryWordId

        // Mapping the other fields
        personalWord.setLastAppearance(rs.getTimestamp("lastAppearance").toLocalDateTime());  // Convert SQL Timestamp to LocalDateTime
        personalWord.setEstonian(rs.getString("estonian"));  // Estonian word
        personalWord.setHalo(rs.getInt("halo"));  // Halo integer value
        personalWord.setLevel(rs.getInt("level_D"));  // Level integer value

        // Map the state enum from string to enum type
        personalWord.setState(PersonalWord.State.valueOf(rs.getString("state")));  // State enum value

        return personalWord;
    }

}
