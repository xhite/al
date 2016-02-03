package crate.entity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.MoveBlocker;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

public class WallRegular  implements Drawable, GameEntity, MoveBlocker {
	protected static DrawableImage image = null;
	int x, y;
	public static final int RENDERING_SIZE = 32;

	public WallRegular(Canvas defaultCanvas, int xx, int yy) {
		image = new DrawableImage("resources/wall_regular.png", defaultCanvas);
		x = xx;
		y = yy;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
				null);
	}

	public Point getPos() {
		return (new Point(x, y));
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(x, y, RENDERING_SIZE, RENDERING_SIZE));
	}
}