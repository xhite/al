package crate.rule;


import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import crate.entity.Command;
import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

/**
 * {@link MoveStrategy} which listens to the keyboard and answers new
 * {@link SpeedVector speed vectors} based on what the user typed.
 */
public class MoveStrategyKeyboardCrate extends KeyAdapter implements MoveStrategy {
	protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0, 0));

	protected Command command;

	public SpeedVector getSpeedVector() {
		return speedVector;
	}

	public void setCommand(Command c){
		command = c;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_RIGHT:
			speedVector.setDirection(new Point(1, (int) speedVector.getDirection().getY()));
			//speedVector.setDirection(new Point(1, 0));
			break;
		case KeyEvent.VK_LEFT:
			speedVector.setDirection(new Point(-1, (int) speedVector.getDirection().getY()));
			break;
		case KeyEvent.VK_UP:
			speedVector.setDirection(new Point((int) speedVector.getDirection().getX(), -1));
			break;
		case KeyEvent.VK_SPACE:
            if (command != null)
                command.execute();
            break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent event){
		int keycode = event.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_RIGHT:
			speedVector.setDirection(new Point(0, (int) speedVector.getDirection().getY()));
			break;
		case KeyEvent.VK_LEFT:
			speedVector.setDirection(new Point(0, (int) speedVector.getDirection().getY()));
			break;
		case KeyEvent.VK_UP:
			speedVector.setDirection(new Point((int) speedVector.getDirection().getX(), 0));
			break;
		case KeyEvent.VK_SPACE:
			// stop shooting
			break;
		}
	}
}
