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
import gameframework.game.MoveBlockerChecker;

import java.awt.*;
import java.util.Vector;

/**
 * Created by xhitedev on 2/3/16.
 */
public class MonsterSpawner extends GameMovable implements Drawable, GameEntity, Cloneable, Overlappable {

    private static final int SPAWN_INTERVAL = 100;

    protected Vector<Monster> vMonsters = new Vector<Monster>();
    private Canvas canvas;
    MoveBlockerChecker moveBlockerChecker;

    private int spawnCounter;

    public MonsterSpawner(Canvas canvas, MoveBlockerChecker mbc){
        this.canvas = canvas;
        this.moveBlockerChecker = mbc;
        createMonster();
    }

    private void createMonster(){
        MonsterMovableDriver ghostDriv = new MonsterMovableDriver();
        MoveStrategy strategy = new MonsterMoveStrategy();
        ghostDriv.setStrategy(strategy);
        ghostDriv.setmoveBlockerChecker(moveBlockerChecker);
        Monster monster = new Monster(canvas);
        monster.setDriver(ghostDriv);
        monster.setPosition(new Point(12 * CrateLevelOne.SPRITE_SIZE, 0));
        vMonsters.addElement(monster);
    }

    @Override
    public void oneStepMoveAddedBehavior() {
        for (Monster monster : vMonsters) {
            monster.oneStepMove();
        }
        if(spawnCounter >= SPAWN_INTERVAL){
            createMonster();
            spawnCounter = 0;
        }
        spawnCounter++;
    }

    @Override
    public void draw(Graphics g) {
        for (Monster monster : vMonsters) {
            monster.draw(g);
        }
    }

    @Override
    public Rectangle getBoundingBox() {
        return vMonsters.get(0).getBoundingBox();
    }
}
