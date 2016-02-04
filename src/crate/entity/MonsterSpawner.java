package crate.entity;

import crate.entity.Monster;
import crate.levels.CrateLevelOne;
import crate.rule.MonsterMovableDriver;
import crate.rule.MonsterMoveStrategy;
import gameframework.base.Drawable;
import gameframework.base.MoveStrategy;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;

import java.awt.*;
import java.util.Vector;

/**
 * Created by xhitedev on 2/3/16.
 */
public class MonsterSpawner extends GameMovable implements  GameEntity {

    private static final int SPAWN_INTERVAL = 150;

    protected Vector<Monster> vMonsters = new Vector<Monster>();
    private Canvas canvas;
    MoveBlockerChecker moveBlockerChecker;
    private boolean side;

    private int spawnCounter;
    private int interval;
    private GameUniverse universe;

    public MonsterSpawner(Canvas canvas, MoveBlockerChecker mbc, GameUniverse universe){
        this.canvas = canvas;
        this.moveBlockerChecker = mbc;
        this.universe = universe;
        this.side = true;
        createMonster();
    }

    private void createMonster(){
        MonsterMovableDriver monsterDriv = new MonsterMovableDriver();
        MoveStrategy strategy = new MonsterMoveStrategy();
        monsterDriv.setStrategy(strategy);
        monsterDriv.setmoveBlockerChecker(moveBlockerChecker);
        Monster monster = new Monster(canvas);
        monster.setDriver(monsterDriv);
        monster.setPosition(new Point(12 * CrateLevelOne.SPRITE_SIZE, 0));
        vMonsters.addElement(monster);
        universe.addGameEntity(monster);
        if (side == true)
            monsterDriv.setNextMove("left");
        else
            monsterDriv.setNextMove("right");
        side = !side;
    }

    @Override
    public void oneStepMoveAddedBehavior() {
        for (Monster monster : vMonsters) {
            monster.oneStepMove();
        }
        if (interval >= SPAWN_INTERVAL){
            if (spawnCounter < 25) {
                if (spawnCounter%5 == 0) createMonster();
                spawnCounter++;
            } else {
                interval = 0;
                spawnCounter = 0;
            }
        }
        interval++;
    }

    @Override
    public Rectangle getBoundingBox() {
        return vMonsters.get(0).getBoundingBox();
    }
}
