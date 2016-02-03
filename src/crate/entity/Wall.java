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

public abstract class Wall implements Drawable, GameEntity, MoveBlocker, Cloneable {
	protected Canvas defaultCanvas;
	protected DrawableImage image = null;
	int x, y;
	public static final int RENDERING_SIZE = 32;

	public Wall(Canvas defaultCanvas) {
		this.defaultCanvas = defaultCanvas;
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
	
	public Wall clone(){
		Object o = null;
		try{
			o = super.clone();
			Wall w = (Wall)o;
			//w.image = this.image;
			//w.defaultCanvas = null;
		} catch(CloneNotSupportedException cnse){
			// whatever
		}
		return (Wall) o;
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
}