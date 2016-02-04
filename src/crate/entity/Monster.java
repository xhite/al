package crate.entity;

import crate.levels.SpriteManagerCrateImpl;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.*;

public class Monster extends GameMovable implements Drawable, GameEntity,
		Overlappable, Cloneable {

	private final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 32;
	private static final int ANIMATION_DURATION = 8;
	private int animationCounter;

	public Monster(Canvas defaultCanvas) {
		this.spriteManager = new SpriteManagerCrateImpl("resources/green.png",
			defaultCanvas, RENDERING_SIZE, 11, 2);
		this.spriteManager.setTypes("idle");
		this.spriteManager.setType("idle");

	}

	public void draw(Graphics g) {
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if(animationCounter >= ANIMATION_DURATION){
			this.spriteManager.increment();
			animationCounter = 0;
		}
		animationCounter++;
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}

	public Object clone() {
		Object clone = null;

		try {
			clone = super.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return clone;
	}
}
