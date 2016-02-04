package crate.entity;

import gameframework.base.Drawable;
import gameframework.base.Movable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovableDriver;

import java.awt.*;

public interface Player extends Drawable, GameEntity, Overlappable, Movable {
    public void setPosition(Point position);
    public void setDriver(GameMovableDriver driver);
    public void oneStepMoveAddedBehavior();
    public GameMovableDriver getDriver();

}
