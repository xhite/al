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

	public CrateOverlapRules(Canvas c){ canvas = c; }

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}
	
	public void overlapRule(Flames f, John j) {
		System.out.println("Overlap");

	}

	public void overlapRule(John j, Monster m) {
		System.out.println("Overlap monster");
		//universe.addGameEntity(new Fire(canvas, new Point(0,0)));
	}

	public void overlapRule(Flames f, Monster m) {}
}
