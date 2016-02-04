package crate.entity;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.SpriteManager;

import java.awt.*;

import crate.levels.SpriteManagerCrateImpl;

public class Weapon implements Drawable, GameEntity,
        Overlappable {

    protected Point position;
    private final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 24;
    ShootCommand shootCommand;

    public Weapon(Canvas defaultCanvas, ShootCommand command) {
        this.spriteManager = new SpriteManagerCrateImpl("resources/machine_gun.png",
    			defaultCanvas, RENDERING_SIZE, 1, 2);
    		this.spriteManager.setTypes("idle");
    		this.spriteManager.setType("idle");
        shootCommand = command;
    }

    public void setPosition(Point pos) {
        position = pos;
        shootCommand.setPosition(position);
    }

    public Point getPosition() {
        return position;
    }

    public void draw(Graphics g) {
        spriteManager.draw(g, getPosition());
    }

    public Rectangle getBoundingBox() {
        return (new Rectangle((int) position.getX(), (int) position.getY(),
                RENDERING_SIZE, RENDERING_SIZE));
    }

}