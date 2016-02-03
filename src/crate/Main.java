package crate;

import java.util.ArrayList;

import crate.levels.CrateLevelOne;
import crate.levels.GameCrateImpl;
import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;

public class Main {

	public static void main(String[] args) {
		GameCrateImpl g = new GameCrateImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new CrateLevelOne(g));
		g.setLevels(levels);
		
		g.start();

	}

}
