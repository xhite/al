package crate.entity;

import crate.levels.SpriteManagerCrateImpl;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.*;

public class Weapon implements Drawable, GameEntity,
        Overlappable {

    protected static DrawableImage image = null;
    protected Point position;
    public static final int RENDERING_SIZE = 16;

    public Weapon(Canvas defaultCanvas) {
        image = new DrawableImage("resources/machine_gun.png", defaultCanvas);
    }

    public void setPosition(Point pos) {
        position = pos;
    }

    public Point getPosition() {
        return position;
    }

    public void draw(Graphics g) {
        g.drawImage(image.getImage(), (int) getPosition().getX(),
                (int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
                null);

    }

    public Rectangle getBoundingBox() {
        return (new Rectangle((int) position.getX(), (int) position.getY(),
                RENDERING_SIZE, RENDERING_SIZE));
    }

}