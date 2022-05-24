package com.game.rule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.rule.model.GameEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GameService {

	@Autowired
	private RuleEngineService ruleEngineService;

	public GameEntity createGame(final GameEntity gameEntity) {
		log.info("Creating new Game");
		String ruleId = ruleEngineService.createRulesForGames(gameEntity);
		log.info("ruleId {}", ruleId);
		return gameEntity;
	}

	public GameEntity applyGame(GameEntity gameEntity, String ruleName) {
		log.info("Apply Game {}", gameEntity);
		var validateRule = ruleEngineService.validateRule(gameEntity, ruleName);
		return validateRule.get();
	}

}