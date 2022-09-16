package com.example.demo.Game;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	private GameRepository gameRepository;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	Random rng = new Random();
	
	@Override
	public Game createGame(Game game) {

		game.setFinished(false);

		game.setAnswer(this.generateAnswer());

		return gameRepository.save(game);
	}

	@Override
	public Game findGame(Long id){
		if(gameRepository.findById(id).isPresent()){
			Game game = gameRepository.findById(id).get();
			if(!game.isFinished()){
				game.setAnswer(null);
			}
			return game;
		}
		return null;
	}

	@Override
    public Iterable<Game> listGames() {
		Iterable<Game> games = gameRepository.findAll();
		for(Game game : games){
			System.out.println(game);
			if(!game.isFinished()){
				game.setAnswer(null);
			}
		}
		return games;
    }

	public int generateAnswer(){
		ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4 , 5, 6, 7, 8, 9));
		String answer = "";
		for(int i = 0; i < 4; i++){
			int index;
			if(i==0){
				index = rng.nextInt(1,nums.size());
			}
			else{
				index = rng.nextInt(nums.size());
			}
			int num = nums.get(index);
			nums.remove(index);
			answer += num;
		}
		return Integer.parseInt(answer);
	}

	public Game finishGame(Long id) {
		Game game = this.findGame(id);
		game.setFinished(true);
		return gameRepository.save(game);
	}
}
