package crate.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;

import crate.levels.SpriteManagerCrateImpl;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;

public class John extends GameMovable implements Player {
	protected final SpriteManager spriteManager;
	private static final int RENDERING_SIZE = 32;
	private static final int ANIMATION_DURATION = 8;
	private int animationCounter;

	public John(Canvas canvas){
		this.spriteManager = new SpriteManagerCrateImpl("resources/john.png",
				canvas, RENDERING_SIZE, 5, 1);
		this.spriteManager.setTypes("idle");
		this.spriteManager.setType("idle");
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}
	
	@Override
	public void draw(Graphics g) {
		this.spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if(animationCounter >= ANIMATION_DURATION){
			this.spriteManager.increment();
			animationCounter = 0;
		}
		animationCounter++;
		
	}

}
