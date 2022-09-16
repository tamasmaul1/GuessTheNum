package com.example.demo.Game;

public interface GameService {

	Game createGame(Game game);
	
	Game findGame(Long id);

	Iterable<Game> listGames();
	
	Game finishGame(Long id);
}
