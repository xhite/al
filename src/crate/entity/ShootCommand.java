package crate.entity;

import crate.levels.CrateLevelOne;
import gameframework.base.MoveStrategy;
import gameframework.base.MoveStrategyStraightLine;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;

import java.awt.*;

public class ShootCommand implements Command {

    GameUniverse universe;
    Canvas canvas;
    MoveBlockerChecker moveBlockerChecker;
    Point position;

    public ShootCommand(GameUniverse u, Canvas c, MoveBlockerChecker m){
        universe = u;
        canvas = c;
        moveBlockerChecker = m;
    }

    public void setPosition(Point pos){
        position = pos;
    }

    public void execute(){
        Bullet bullet = new Bullet(canvas);
        bullet.setPosition(position);
        System.out.println(bullet.getPosition().y);
        GameMovableDriverDefaultImpl monsterDriv = new GameMovableDriverDefaultImpl();
        MoveStrategy strategy = new MoveStrategyStraightLine(position, new Point(CrateLevelOne.SPRITE_SIZE*CrateLevelOne.LEVEL_WIDTH, bullet.getPosition().y));
        monsterDriv.setStrategy(strategy);
        monsterDriv.setmoveBlockerChecker(moveBlockerChecker);
        bullet.setDriver(monsterDriv);
        bullet.setPosition(new Point(12 * CrateLevelOne.SPRITE_SIZE, 0));
        universe.addGameEntity(bullet);
    }
}
