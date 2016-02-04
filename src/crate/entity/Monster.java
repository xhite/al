package crate.entity;

import crate.levels.SpriteManagerCrateImpl;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.*;

public class Monster extends GameMovable implements Drawable, GameEntity,
		Overlappable, Cloneable {

	private final SpriteManagerDefaultImpl spriteManager;
	public static final int RENDERING_SIZE = 32;

	public Monster(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/ghost.gif",
				defaultCanvas, RENDERING_SIZE, 6);
		this.spriteManager.setTypes("idle");
		this.spriteManager.setType("idle");

	}

	public void draw(Graphics g) {
		spriteManager.draw(g, getPosition());
	}

	@Override
	public void oneStepMoveAddedBehavior() {}

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
