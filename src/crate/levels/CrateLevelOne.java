package crate.levels;

import java.awt.Canvas;
import java.awt.Point;

import crate.entity.John;
import crate.entity.WallRegular;
import crate.rule.CrateOverlapRules;
import gameframework.base.MoveStrategyKeyboard;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;

public class CrateLevelOne extends GameLevelCrateImpl{
	Canvas canvas;
	
	public static final int SPRITE_SIZE = 32;
	public static final int LEVEL_WIDTH = 24;
	public static final int LEVEL_HEIGHT = 16;
	
	/* -1: player start
	 * 0: empty
	 * 1: wall
	 * 2: 
	 *
	 */
	static int[][] levelMap = {
			{
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
			}, 
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
			},
			{
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
			},
	};
	
	public CrateLevelOne(Game g) {
		super(g);
		this.canvas = g.getCanvas();
	}

	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		
		CrateOverlapRules johnRules = new CrateOverlapRules();
		overlapProcessor.setOverlapRules(johnRules);
		
		this.universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		System.out.println(this.universe);
		
		this.gameBoard = new GameUniverseViewPortCrateImpl(canvas, universe);
		System.out.println(this.gameBoard);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);

		System.out.println(LEVEL_HEIGHT);
		// initializing the level
		for(int i = 0; i < LEVEL_HEIGHT; i++){
			for(int j = 0; j < LEVEL_WIDTH; j++){
				if(levelMap[i][j] == -1){
					John player = new John(canvas);
					GameMovableDriverGravityImpl playerDriver = new GameMovableDriverGravityImpl();
					MoveStrategyKeyboardCrate keyStr = new MoveStrategyKeyboardCrate();
					playerDriver.setStrategy(keyStr);
					playerDriver.setmoveBlockerChecker(moveBlockerChecker);
					canvas.addKeyListener(keyStr);
					player.setDriver(playerDriver);
					player.setPosition(new Point(j*SPRITE_SIZE, i*SPRITE_SIZE));
					universe.addGameEntity(player);
				}
				if(levelMap[i][j] == 1){
					universe.addGameEntity(new WallRegular(this.canvas, j*SPRITE_SIZE, i*SPRITE_SIZE));
				}
				
			}
		}
		
		
		
	}

	@Override
	public int getLevelHeight() {
		// TODO Auto-generated method stub
		return SPRITE_SIZE * LEVEL_HEIGHT;
	}

	@Override
	public int getLevelWidth() {
		// TODO Auto-generated method stub
		return SPRITE_SIZE * LEVEL_WIDTH;
	}
}
