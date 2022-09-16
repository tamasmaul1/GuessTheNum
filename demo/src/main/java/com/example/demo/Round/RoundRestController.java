package com.example.demo.Round;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoundRestController {
	
	@Autowired
	private RoundServiceImpl roundServiceImpl;

	@PostMapping("/guess")
	public Round guess(@RequestBody Round round){
		return roundServiceImpl.createRound(round);
	}

	@GetMapping("/rounds/{id}")
	public List<Round> findRounds(@PathVariable Long id){
		return roundServiceImpl.findRounds(id);
	}
}
