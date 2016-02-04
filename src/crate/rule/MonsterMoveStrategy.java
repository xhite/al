package crate.rule;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.*;

public class MonsterMoveStrategy implements MoveStrategy {

    SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0));

    public void setDirection(String dir){
        switch (dir) {
            case "right":
                currentMove.setDirection(new Point(1, 0));
                break;
            case "left":
                currentMove.setDirection(new Point(-1, 0));
                break;
            case "up":
                currentMove.setDirection(new Point(0, -1));
                break;
            case "down":
                currentMove.setDirection(new Point(0, 1));
                break;
        }
    }

    public SpeedVector getSpeedVector() {
    	this.currentMove.setSpeed(2);
        return currentMove;
    }
}
