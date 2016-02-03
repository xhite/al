package crate.entity;

import java.awt.Canvas;

import gameframework.base.DrawableImage;

public class SteelPillar extends Wall {

	public SteelPillar(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas);
		image = new DrawableImage("resources/wall_steelpillar.png", defaultCanvas);
		this.defaultCanvas = defaultCanvas;
		x = xx;
		y = yy;
		// TODO Auto-generated constructor stub
	}

}
