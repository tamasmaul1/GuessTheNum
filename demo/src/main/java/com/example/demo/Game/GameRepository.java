package com.example.demo.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long>   {
    
    // @Query("SELECT g.id,g.finished,g.answer FROM Game g WHERE g.finished='true'")
    // List<Game> findFinished();

    // @Query("SELECT g.id,g.finished FROM Game g WHERE g.finished='false'")
    // List<Game> findUnfinished();

}