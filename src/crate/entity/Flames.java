package crate.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import crate.levels.SpriteManagerCrateImpl;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.MoveBlocker;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

public class Flames  implements Drawable, GameEntity, Overlappable {
	protected final SpriteManager spriteManager;
	private static final int RENDERING_SIZE = 32;
	private static final int ANIMATION_DURATION = 8;
	private int animationCounter;
	
	int x, y;

	public Flames(Canvas canvas, int xx, int yy) {
		this.spriteManager = new SpriteManagerCrateImpl("resources/flame.png",
				canvas, RENDERING_SIZE, 4, 1);
		this.spriteManager.setTypes("idle");
		this.spriteManager.setType("idle");
		x = xx;
		y = yy;
	}

	public void draw(Graphics g) {
		if(animationCounter >= ANIMATION_DURATION){
			this.spriteManager.increment();
			animationCounter = 0;
		}
		animationCounter++;
		this.spriteManager.draw(g, getPosition());
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
	}

	@Override
	public Point getPosition() {
		return (new Point(x, y));
	}
}