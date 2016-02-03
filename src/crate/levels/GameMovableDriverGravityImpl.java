package crate.levels;


import java.awt.Point;

import gameframework.base.Movable;
import gameframework.base.MoveStrategy;
import gameframework.base.MoveStrategyDefaultImpl;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameMovableDriver;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;

public class GameMovableDriverGravityImpl implements GameMovableDriver {
	protected MoveBlockerChecker moveBlockerChecker;
	
	protected MoveStrategy moveStrategy;
	protected static final int jumpTickDuration = 16;
	protected int remainingJumpTicks = 0;

	public GameMovableDriverGravityImpl() {
		moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveStrategy = new MoveStrategyDefaultImpl();
	}

	public void setStrategy(MoveStrategy strat) {
		moveStrategy = strat;
	}

	public void setmoveBlockerChecker(MoveBlockerChecker obst) {
		moveBlockerChecker = obst;
	}

	public SpeedVector getSpeedVector(Movable m) {
		/*SpeedVector possibleSpeedVector;

		possibleSpeedVector = moveStrategy.getSpeedVector();
		System.out.println(pullSpeed);
		
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			System.out.println("flotte");
			

			return possibleSpeedVector;
		}

		// If the strategy did not provide a valid vector, try to keep the
		// current vector.
		possibleSpeedVector = m.getSpeedVector();
		if (moveBlockerChecker.moveValidation(m, possibleSpeedVector)) {
			System.out.println("Bonk");

			return possibleSpeedVector;
		}
		*/
		SpeedVector resultSpeedVector = new SpeedVectorDefaultImpl(new Point(0, 0));
		
		
		SpeedVector gravityVector;
		
		SpeedVector inputSpeedVector = moveStrategy.getSpeedVector();

		
		
		// if the user is currently jumping
		if(remainingJumpTicks > 0){
			gravityVector = new SpeedVectorDefaultImpl(new Point(0, -1));
			remainingJumpTicks--;
		} else {
			gravityVector = new SpeedVectorDefaultImpl(new Point(0, 1));
		}
		
		
		if(!moveBlockerChecker.moveValidation(m, new SpeedVectorDefaultImpl(new Point(0, 1)))){
			// if the user wants to jump
			if(inputSpeedVector.getDirection().getY() == -1){
				remainingJumpTicks = jumpTickDuration;
			}
		}
		
		if(!moveBlockerChecker.moveValidation(m, new SpeedVectorDefaultImpl(new Point(0, -1)))){
			// if the user wants to jump
			remainingJumpTicks = 0;
		}
		
		
		
		// checks if gravity is applicable
		if(moveBlockerChecker.moveValidation(m, gravityVector)){
			resultSpeedVector = new SpeedVectorDefaultImpl(new Point((int)gravityVector.getDirection().getX(), (int)gravityVector.getDirection().getY()));
		}
		
		// checks if the input vector is applicable, ignoring the Y component (used only to indicate jump)
		SpeedVector resultPlusInputSpeedVector = new SpeedVectorDefaultImpl(new Point((int)(resultSpeedVector.getDirection().getX() + inputSpeedVector.getDirection().getX()), (int)(resultSpeedVector.getDirection().getY())));
		if(moveBlockerChecker.moveValidation(m, resultPlusInputSpeedVector)){
			resultSpeedVector = resultPlusInputSpeedVector;
		}
		
		return resultSpeedVector;
	}
}
