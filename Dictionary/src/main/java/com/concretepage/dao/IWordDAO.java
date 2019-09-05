package com.concretepage.dao;
import java.util.List;

import com.concretepage.entity.Word;
public interface IWordDAO {
    List<Word> getAllWords();
    Word getWordById(int articleId);
    void addWord(Word word); 
    boolean wordExists(String title);
}
 