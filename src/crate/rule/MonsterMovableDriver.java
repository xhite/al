package crate.rule;

import gameframework.base.Movable;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriverDefaultImpl;

public class MonsterMovableDriver extends GameMovableDriverDefaultImpl {

	// A modified random strategy that makes ghosts mostly follow the alleys in
	// one direction.
	// Random speed vectors are (1,0) (0,1) (-1,0) (0,-1), but sometimes speed
    // vectors are reinitialized to (0,0) by GameMovableDriver.

    String nextMove = "right";

    public void setNextMove(String move){
        nextMove = move;
    }

    @Override
	public SpeedVector getSpeedVector(Movable m) {
		SpeedVector currentSpeedVector, possibleSpeedVector;

		currentSpeedVector = m.getSpeedVector();
		possibleSpeedVector = moveStrategy.getSpeedVector();

		MonsterMoveStrategy msm = (MonsterMoveStrategy) moveStrategy;

		msm.setDirection("down");

		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			return possibleSpeedVector;
		}

        if (currentSpeedVector.getDirection().getX() < 0) {
            msm.setDirection("left");
            if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
                return possibleSpeedVector;
            } else {
                nextMove = "right";
            }
        }

        if (currentSpeedVector.getDirection().getX() > 0) {
            msm.setDirection("right");
            if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
                return possibleSpeedVector;
            } else {
                nextMove = "left";
            }
        }

        msm.setDirection(nextMove);
        if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
            return possibleSpeedVector;
        }

        msm.setDirection("right");
        if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
            return possibleSpeedVector;
        } else {
            nextMove = "left";
        }


        possibleSpeedVector = super.getSpeedVector(m);
		return (possibleSpeedVector);
	}

}