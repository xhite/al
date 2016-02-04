package crate.rule;

import crate.entity.*;
import gameframework.base.MoveStrategyRandom;
import gameframework.base.ObservableValue;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.awt.*;

public class CrateOverlapRules extends OverlapRulesApplierDefaultImpl{
	protected GameUniverse universe;
	private Canvas canvas;

	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;

	private final CrateManager crateManager;

	public CrateOverlapRules(Canvas c, ObservableValue<Integer> score, ObservableValue<Integer> life, CrateManager cm){
		this.canvas = c;
		this.score = score;
		this.life = life;
		this.crateManager = cm;
		System.out.println("heyo");
	}

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}
	
	public void overlapRule(Flames f, ArmedJohn j) {
		this.life.setValue(0);
	}

	public void overlapRule(ArmedJohn j, Monster m) {
		this.life.setValue(0);
	}

	public void overlapRule(Flames f, Monster m) {
		//universe.removeGameEntity(m);
	}

	public void overlapRule(ArmedJohn j, Crate c) {
		this.crateManager.shuffleCrate();
		this.score.setValue(this.score.getValue() + 1);
	}

}
