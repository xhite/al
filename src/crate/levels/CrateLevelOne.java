package crate.levels;

import java.awt.Canvas;
import java.awt.Point;

import crate.entity.*;
import crate.rule.CrateOverlapRules;
import crate.rule.MonsterMovableDriver;
import crate.rule.MonsterMoveStrategy;
import gameframework.base.MoveStrategy;
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
	
	/* -3: crate spawn point
	 * -2: monster spawn
	 * -1: player start
	 * 0: empty
	 * 1: brick
	 * 2: flames
	 * 3: dirt
	 * 4: steel platform
	 * 5: steel pillar
	 * 6: steel pillar with rivets
	 */
	static int[][] levelMap = {
			{
				6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, -2, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6
			}, 
			{
				5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, -3, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 0, 0, 5
			},
			{
				6, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 6
			},
			{
				5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, -3, 0, 0, -3, -1, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5
			},
			{
				5, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, 0, 0, 5
			},
			{
				6, 1, 1, 1, 1, 1, 0, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 6
			},
			{
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3
			},
	};
	
	public CrateLevelOne(Game g) {
		super(g);
		this.canvas = g.getCanvas();
	}

	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
 
		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		
		// CrateManager singleton
		Crate crate = new Crate(this.canvas);
		CrateManager cm = new CrateManager(crate);
		
		CrateOverlapRules johnRules = new CrateOverlapRules(canvas, this.score[0], this.life[0], cm);
		overlapProcessor.setOverlapRules(johnRules);
		
		this.universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		System.out.println(this.universe);
		
		this.gameBoard = new GameUniverseViewPortCrateImpl(canvas, universe);
		System.out.println(this.gameBoard);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		// Instances for prototype of recurring wall entities
		Wall brickWall = new BrickWall(this.canvas, 0, 0);
		Wall dirtWall = new DirtWall(this.canvas, 0, 0);
		Wall steelPlatform = new SteelPlatform(this.canvas, 0, 0);
		Wall steelPillar = new SteelPillar(this.canvas, 0, 0);
		Wall steelPillarRivets = new SteelPillarRivets(this.canvas, 0, 0);
		
		
		
		System.out.println(LEVEL_HEIGHT);
		// initializing the level
		for(int i = 0; i < LEVEL_HEIGHT; i++){
			for(int j = 0; j < LEVEL_WIDTH; j++){
				if(levelMap[i][j] == -1){
					Player player = new John(canvas);
                    GameMovableDriverGravityImpl playerDriver = new GameMovableDriverGravityImpl();
                    player.setPosition(new Point(j*SPRITE_SIZE, i*SPRITE_SIZE));
                    player.setDriver(playerDriver);
                    MoveStrategyKeyboardCrate keyStr = new MoveStrategyKeyboardCrate();
					playerDriver.setStrategy(keyStr);
					playerDriver.setmoveBlockerChecker(moveBlockerChecker);
					canvas.addKeyListener(keyStr);
                    player = new ArmedJohn(player, new Weapon(canvas));
                    universe.addGameEntity(player);
				}
				if(levelMap[i][j] == -2){
					MonsterSpawner ms = new MonsterSpawner(canvas, moveBlockerChecker, universe);
					ms.setPosition(new Point(j*SPRITE_SIZE, i*SPRITE_SIZE));
					universe.addGameEntity(ms);
				}
				if(levelMap[i][j] == -3){
					cm.addSpawnPoint(new Point(j*SPRITE_SIZE, i*SPRITE_SIZE));
				}
				if(levelMap[i][j] == 1){
					Wall bw = brickWall.clone();
					bw.setPos( j*SPRITE_SIZE, i*SPRITE_SIZE);
					universe.addGameEntity(bw);
				}
				if(levelMap[i][j] == 2){
					universe.addGameEntity(new Flames(this.canvas, j*SPRITE_SIZE, i*SPRITE_SIZE));
				}
				if(levelMap[i][j] == 3){
					Wall dw = dirtWall.clone();
					dw.setPos( j*SPRITE_SIZE, i*SPRITE_SIZE);
					universe.addGameEntity(dw);
				}
				if(levelMap[i][j] == 4){
					Wall dw = steelPlatform.clone();
					dw.setPos( j*SPRITE_SIZE, i*SPRITE_SIZE);
					universe.addGameEntity(dw);
				}
				if(levelMap[i][j] == 5){
					Wall dw = steelPillar.clone();
					dw.setPos( j*SPRITE_SIZE, i*SPRITE_SIZE);
					universe.addGameEntity(dw);
				}
				if(levelMap[i][j] == 6){
					Wall dw = steelPillarRivets.clone();
					dw.setPos( j*SPRITE_SIZE, i*SPRITE_SIZE);
					universe.addGameEntity(dw);
				}
			}
		}
		
		universe.addGameEntity(crate);
		cm.shuffleCrate();

	}

	@Override
	public int getLevelHeight() {
		return SPRITE_SIZE * LEVEL_HEIGHT;
	}

	@Override
	public int getLevelWidth() {
		return SPRITE_SIZE * LEVEL_WIDTH;
	}
}
