package crate.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class CrateManager {
	private Crate crateInstance;
	public ArrayList<Point> spawnPoints = new ArrayList<Point>();
	
	public CrateManager(Crate crate){
		this.crateInstance = crate;
	}
	
	public void addSpawnPoint(Point p){
		this.spawnPoints.add(p);
	}
	
	public void shuffleCrate(){
		// choosing random point
		int randInt = (int)(Math.random() * (this.spawnPoints.size()));
		Point choosen = spawnPoints.get(randInt);
		this.crateInstance.setPos((int)choosen.getX(), (int)choosen.getY());
	}
	
}
