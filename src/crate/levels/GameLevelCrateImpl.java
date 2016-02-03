package crate.levels;

import gameframework.game.Game;
import gameframework.game.GameLevel;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseViewPort;

public abstract class GameLevelCrateImpl extends Thread implements GameLevel{
	protected final Game game;
	
	protected GameUniverse universe;
	protected GameUniverseViewPort gameBoard;
		
	protected abstract void init();
	
	public GameLevelCrateImpl(Game game){
		this.game = game;
		game.score();
		game.life();
	}
	
	@Override
	public void run() {
		gameBoard.paint();
	}

	@Override
	public void start() {
		init();
		super.start();
		try {
			super.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void end() {
		
	}

	protected void overlap_handler() {
	}
}
