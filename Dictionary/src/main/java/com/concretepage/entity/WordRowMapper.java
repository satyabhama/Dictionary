package com.concretepage.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WordRowMapper implements RowMapper<Word> {

	@Override
	public Word mapRow(ResultSet row, int rowNum) throws SQLException {
		Word article = new Word();
		article.setWordId(row.getInt("wordId"));
		article.setWord(row.getString("word")); 
		return article;
	}

}
