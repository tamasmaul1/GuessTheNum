package com.example.demo.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameRestController {

	@Autowired
	private GameServiceImpl gameServiceImpl;
	

	@PostMapping("/begin")
	public Game beginGame(@RequestBody Game game){
		return gameServiceImpl.createGame(game);
	}

	@GetMapping("/game/{id}")
	public Game findGame(@PathVariable Long id){
		return gameServiceImpl.findGame(id);
	}

	@GetMapping("/game")
	public Iterable<Game> listGames(){
		return gameServiceImpl.listGames();
	}
}
