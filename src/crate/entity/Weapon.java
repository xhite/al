package crate.entity;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.*;

public class Weapon implements Drawable, GameEntity,
        Overlappable {

    protected static DrawableImage image = null;
    protected Point position;
    public static final int RENDERING_SIZE = 16;
    ShootCommand shootCommand;

    public Weapon(Canvas defaultCanvas, ShootCommand command) {
        image = new DrawableImage("resources/machine_gun.png", defaultCanvas);
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
        g.drawImage(image.getImage(), (int) getPosition().getX(),
                (int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
                null);
    }

    public Rectangle getBoundingBox() {
        return (new Rectangle((int) position.getX(), (int) position.getY(),
                RENDERING_SIZE, RENDERING_SIZE));
    }

}