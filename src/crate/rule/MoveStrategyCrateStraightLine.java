package crate.rule;

import java.awt.Point;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class MoveStrategyCrateStraightLine implements MoveStrategy {

	Point goal, currentPosition;

	public MoveStrategyCrateStraightLine(Point pos, Point goal) {
		this.goal = goal;
		this.currentPosition = pos;
	}

	public SpeedVector getSpeedVector() {
		double dist = currentPosition.distance(goal);
		int xDirection = (int) Math.rint((goal.getX() - currentPosition.getX())
				/ dist);
		int yDirection = (int) Math.rint((goal.getY() - currentPosition.getY())
				/ dist);
		SpeedVector move = new SpeedVectorDefaultImpl(new Point(xDirection,
				yDirection));
		move.setSpeed(10);
		return move;
	}
}
