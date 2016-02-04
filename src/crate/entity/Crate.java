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

public class Crate implements Drawable, GameEntity, Overlappable {
	protected Canvas defaultCanvas;
	protected DrawableImage image = null;
	int x, y;
	public static final int RENDERING_SIZE = 32;

	public Crate(Canvas defaultCanvas) {
		this.defaultCanvas = defaultCanvas;
		this.image = new DrawableImage("resources/crate.png", defaultCanvas);
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), x, y, RENDERING_SIZE, RENDERING_SIZE,
				null);
	}

	public Point getPos() {
		return new Point(this.x, this.y);
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(this.x, this.y, RENDERING_SIZE, RENDERING_SIZE));
	}

	@Override
	public Point getPosition() {
		return new Point(this.x, this.y);
	}
}