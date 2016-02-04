package crate.levels;

import java.util.Date;

import gameframework.base.ObservableValue;
import gameframework.game.Game;
import gameframework.game.GameLevel;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseViewPort;

public abstract class GameLevelCrateImpl extends Thread implements GameLevel{
	private static final int TARGET_FPS = 40;
	private static final long NS = 1000000000;
	
	protected final Game game;
	
	protected GameUniverse universe;
	protected GameUniverseViewPort gameBoard;
	protected boolean isGameRunning;

	protected ObservableValue<Integer> life[];
	protected ObservableValue<Integer> score[];

	protected abstract void init();
	public abstract int getLevelHeight();
	public abstract int getLevelWidth();
	
	public GameLevelCrateImpl(Game game){
		this.game = game;
		this.isGameRunning = true;
		this.score = game.score();
		this.life = game.life();
	}
	
	@Override
	public void run() {
		long targetDelay = NS / TARGET_FPS;
		long lastFrameTime = System.nanoTime();
		long lastFpsTime = 0;
		int numberOfFramesThisSecond = 0;
		
		while(this.isGameRunning){
			long now = System.nanoTime();
			
			if(now - lastFpsTime >= NS){
				System.out.println("FPS: " + numberOfFramesThisSecond);
				numberOfFramesThisSecond = 0;
				lastFpsTime = now; 
			}
			
			if(now - lastFrameTime > targetDelay){
				this.universe.allOneStepMoves();
				this.universe.processAllOverlaps();
				this.gameBoard.paint();
				numberOfFramesThisSecond++;
				lastFrameTime = now;
			}
		}
	}

	@Override
	public void start() {
		this.isGameRunning = true;
		init();
		super.start();
		
		try {
			super.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void end() {
		this.isGameRunning = false;
	}

	protected void overlap_handler() {
	}
}
