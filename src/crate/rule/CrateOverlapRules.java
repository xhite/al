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
		System.out.println("Overlap");
		this.life.setValue(0);
		this.score.setValue(500);
	
	}

	public void overlapRule(ArmedJohn j, Monster m) {
		System.out.println("Overlap monster");
		//universe.addGameEntity(new Fire(canvas, new Point(0,0)));
	}

	public void overlapRule(Flames f, Monster m) {
		System.out.println("Overlap monster flame");

	}
	
	public void overlapRule(ArmedJohn j, Crate c) {
		System.out.println("Overlap john crate");
		this.crateManager.shuffleCrate();
		this.score.setValue(this.score.getValue() + 1);
	}
	
}
