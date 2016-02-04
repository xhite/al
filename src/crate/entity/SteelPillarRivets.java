package crate.entity;

import java.awt.Canvas;

import gameframework.base.DrawableImage;

public class SteelPillarRivets extends Wall {

	public SteelPillarRivets(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas);
		image = new DrawableImage("resources/wall_steelpillarrivets.png", defaultCanvas);
		this.defaultCanvas = defaultCanvas;
		x = xx;
		y = yy;
	}

}
