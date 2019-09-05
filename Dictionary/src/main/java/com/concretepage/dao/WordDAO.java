package com.concretepage.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Word;
import com.concretepage.entity.WordRowMapper;
@Transactional
@Repository
public class WordDAO implements IWordDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public Word getWordById(int articleId) {
		String sql = "SELECT articleId, title, category FROM Word WHERE articleId = ?";
		RowMapper<Word> rowMapper = new BeanPropertyRowMapper<Word>(Word.class);
		Word article = jdbcTemplate.queryForObject(sql, rowMapper, articleId);
		return article;
	}
	@Override
	public List<Word> getAllWords() {
		String sql = "SELECT articleId, title, category FROM Word";
        //RowMapper<Word> rowMapper = new BeanPropertyRowMapper<Word>(Word.class);
		RowMapper<Word> rowMapper = new WordRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}	
	@Override
	public void addWord(Word word) { 
		String sql = "INSERT INTO Word (wordId, word) values (?, ?)";
		jdbcTemplate.update(sql, word.getWordId(), word.getWord());
		
		sql = "SELECT wordId FROM Word WHERE word = ?";
		int wordId = jdbcTemplate.queryForObject(sql, Integer.class, word.getWord());
		word.setWordId(wordId);
	}
	
	@Override
	public boolean wordExists(String name) {
		System.out.println(name);
		String sql = "SELECT count(*) FROM Word WHERE word = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, name);
		System.out.println(count);
		if(count == 0) {
    		return false;
		} else {
			return true;
		}
	}
}
