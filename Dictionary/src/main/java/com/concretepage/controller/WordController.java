package com.concretepage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.service.IWordService;

@Controller
@RequestMapping("dictionary")
public class WordController {
	@Autowired
	private IWordService wordService;

	@GetMapping("word/{word}")
	public ResponseEntity<String> getWordById(@PathVariable("word") String word) {
		boolean isPresent = wordService.getWord(word);
		if (isPresent) {
			return new ResponseEntity<String>("Exist", HttpStatus.OK);
		} else {

			return new ResponseEntity<String>("Not Exist", HttpStatus.OK);
		}
	}

	@PostMapping("add/word")
	public ResponseEntity<String> addWord(UriComponentsBuilder builder) {
		boolean flag = wordService.addWord();
		if(flag)
		{
		return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Already added", HttpStatus.OK);
		}
			
	}

}