package crate.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

public class John extends GameMovable implements Drawable, GameEntity, Overlappable {
	protected final SpriteManager spriteManager;
	private static final int RENDERING_SIZE = 32;
	
	public John(Canvas canvas){
		this.spriteManager = new SpriteManagerDefaultImpl("resources/john.png",
				canvas, RENDERING_SIZE, 6);
		this.spriteManager.setTypes("idle");
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		spriteManager.setType("idle");
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// TODO Auto-generated method stub
		
	}

}
