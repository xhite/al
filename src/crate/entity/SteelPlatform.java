package crate.entity;

import java.awt.Canvas;

import gameframework.base.DrawableImage;

public class SteelPlatform extends Wall {

	public SteelPlatform(Canvas defaultCanvas, int xx, int yy) {
		super(defaultCanvas);
		image = new DrawableImage("resources/wall_steelplatform.png", defaultCanvas);
		this.defaultCanvas = defaultCanvas;
		x = xx;
		y = yy;
	}

}
