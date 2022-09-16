package com.example.demo.Round;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Game.Game;
import com.example.demo.Game.GameServiceImpl;

@Service
public class RoundServiceImpl implements RoundService {

	@Autowired
	private RoundRepository roundRepository;
	@Autowired
	private GameServiceImpl gameServiceImpl;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	@Override
	public Round createRound(Round round) {
		Game game = gameServiceImpl.findGame(round.getGameId());
		int answer = game.getAnswer();

		String result = this.calcResult(round.getGuess(), answer);

		round.setResult(result);
		round.setCreateDate(dtf.format(LocalDateTime.now()));

		String wincondition = "e:4:p:0";
		if(result.equals(wincondition)){
			System.out.println("Game won");
			gameServiceImpl.finishGame(game.getId());
		}

		return roundRepository.save(round);
	}

	public String calcResult(int guess, int answer){
		String strguess = String.valueOf(guess);
		String stranswer = String.valueOf(answer);

		int exact = 0;
		int partial = 0;
		for(int i = 0; i < strguess.length(); i++){
			if(stranswer.charAt(i) == strguess.charAt(i))
			{
				exact++;
			}
			else if(stranswer.contains(""+strguess.charAt(i))){
				partial++;
			}
		}
		String result = "e:" + exact + ":p:" + partial;

		return result;
	}
	@Override
	public List<Round> findRounds(Long id){
		List<Round> result = new ArrayList<>();
		for(Round round : roundRepository.findAll()){
			if(round.getGameId() == id){
				result.add(round);
			}
		}
		return result;
	}
}