package crate.entity;

import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriver;

import java.awt.*;

public class ArmedJohn implements Player {

    protected Weapon weapon;
    protected Player john;

    public ArmedJohn(Player john, Weapon weapon){
        this.weapon = weapon;
        this.john = john;
    }

    @Override
    public Point getPosition() {
        return john.getPosition();
    }

    @Override
    public SpeedVector getSpeedVector() {
        return john.getSpeedVector();
    }

    @Override
    public void setSpeedVector(SpeedVector m) {
        john.setSpeedVector(m);
    }

    @Override
    public void oneStepMove() {
        john.oneStepMove();
    }

    @Override
    public void oneStepMoveAddedBehavior() {
        john.oneStepMoveAddedBehavior();
    }

    @Override
    public void draw(Graphics g) {
        john.draw(g);
        weapon.setPosition(new Point(getPosition().x+30, getPosition().y));
        weapon.draw(g);
    }


    @Override
    public Rectangle getBoundingBox() {
        return john.getBoundingBox();
    }

    @Override
    public void setPosition(Point position) {
        john.setPosition(position);
    }

    @Override
    public void setDriver(GameMovableDriver driver) {
        john.setDriver(driver);
    }

    @Override
    public GameMovableDriver getDriver() {
        return john.getDriver();
    }
}
