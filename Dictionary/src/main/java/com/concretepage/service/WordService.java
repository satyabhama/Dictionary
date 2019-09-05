package com.concretepage.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IWordDAO;
import com.concretepage.entity.Word;
@Service
public class WordService implements IWordService {
	@Autowired
	private IWordDAO wordDAO;
	@Override
	public boolean getWord(String word) {
		return wordDAO.wordExists(word);
		
	}	 
	@Override
	public synchronized boolean addWord(){
		boolean isAdded=false; 
		Set<String> input = getInputFileData();
		Word w=new Word();
		long k=0;
		for(String word:input)
		{
			k++;
			w.setWordId(k);
			w.setWord(word); 
	       if (!"".equals(word)&&!wordDAO.wordExists(word)) {
	    	   wordDAO.addWord(w); 
	    	   isAdded=true;
	       }
		}
		 return isAdded;
	}
	private Set<String> getInputFileData() {

		Set<String> set1 = new HashSet<String>();
		String fileName = "src//main//resources//test12.txt";
		String line = null;
		try { 
			FileReader fileReader = new FileReader(fileName);
 
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				line=line.replaceAll("[^a-zA-Z0-9\\ ]", "");
				String[] ss = line.split(" ");
				for (String s : ss) {
					set1.add(s);
				}
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		return set1;
	}


	
}
