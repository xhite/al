package crate.rule;

import crate.entity.Flames;
import crate.entity.John;
import gameframework.base.MoveStrategyRandom;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

public class CrateOverlapRules extends OverlapRulesApplierDefaultImpl{
	protected GameUniverse universe;
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}
	
	public void overlapRule(Flames f, John j) {
		System.out.println("Overlap");
	
	}
}
