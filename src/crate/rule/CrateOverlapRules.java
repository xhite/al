package crate.rule;

import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

public class CrateOverlapRules extends OverlapRulesApplierDefaultImpl{
	protected GameUniverse universe;
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

}
