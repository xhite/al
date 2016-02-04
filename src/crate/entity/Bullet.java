package crate.entity;

import crate.levels.SpriteManagerCrateImpl;
import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;

import java.awt.*;

public class Bullet extends GameMovable implements Drawable, GameEntity,
        Overlappable {

    private final SpriteManager spriteManager;
    public static final int RENDERING_SIZE = 16;

    public Bullet(Canvas defaultCanvas) {
        this.spriteManager = new SpriteManagerCrateImpl("resources/bullet.png",
                defaultCanvas, RENDERING_SIZE, 1, 1);
        this.spriteManager.setTypes("idle");
        this.spriteManager.setType("idle");
    }

    @Override
    public void draw(Graphics g) {
        spriteManager.draw(g, getPosition());
    }

    public Point getPosition(){
        return super.getPosition();
    }

    public void setPosition(Point p){
        super.setPosition(p);
    }

    @Override
    public void oneStepMoveAddedBehavior() {}

    @Override
    public Rectangle getBoundingBox() {
        return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
    }
}
