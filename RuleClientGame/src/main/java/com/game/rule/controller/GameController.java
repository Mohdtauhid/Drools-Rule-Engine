package com.game.rule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.rule.model.GameEntity;
import com.game.rule.service.GameService;

@RestController
@RequestMapping(value = "/v1/rules")
public class GameController {

	@Autowired
	private GameService gameService;

	@PostMapping(value = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameEntity> createGameRule(@RequestBody final GameEntity gameEntity) {
		final var game = gameService.createGame(gameEntity);
		return new ResponseEntity<>(game, HttpStatus.CREATED);
	}

	@PostMapping(value = "/games/{ruleName}/apply", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameEntity> applyGameRule(@RequestBody final GameEntity gameEntity,
			@PathVariable("ruleName") final String ruleName) {
		final var gameResponse = this.gameService.applyGame(gameEntity, ruleName);
		return new ResponseEntity<>(gameResponse, HttpStatus.OK);
	}
}