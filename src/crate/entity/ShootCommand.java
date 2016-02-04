package crate.entity;

import crate.levels.CrateLevelOne;
import crate.rule.MoveStrategyCrateStraightLine;
import gameframework.base.MoveStrategy;
import gameframework.base.MoveStrategyStraightLine;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;

import java.awt.*;

public class ShootCommand implements Command {

    GameUniverse universe;
    Canvas canvas;
    MoveBlockerChecker moveBlockerChecker;
    Point position;
    SpeedVector playerSpeedVector;

    public ShootCommand(GameUniverse u, Canvas c, MoveBlockerChecker m){
        universe = u;
        canvas = c;
        moveBlockerChecker = m;
    }

    public void setPosition(Point pos){
        position = pos;
    }
    
    public void setPlayerSpeedVector(SpeedVector sv){
    	this.playerSpeedVector = sv;
    }

    public void execute(){
        Bullet bullet = new Bullet(canvas);
        bullet.setPosition(position);

        GameMovableDriverDefaultImpl monsterDriv = new GameMovableDriverDefaultImpl();
        MoveStrategy strategy = new MoveStrategyCrateStraightLine(position, new Point(CrateLevelOne.SPRITE_SIZE*CrateLevelOne.LEVEL_WIDTH, bullet.getPosition().y));
        monsterDriv.setStrategy(strategy);
        monsterDriv.setmoveBlockerChecker(moveBlockerChecker);
        bullet.setDriver(monsterDriv);

        universe.addGameEntity(bullet);
    }
}
