package crate.levels;

import gameframework.base.BackgroundImage;
import gameframework.base.Drawable;
import gameframework.game.GameEntity;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseViewPort;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

public class GameUniverseViewPortCrateImpl implements GameUniverseViewPort {
	private Image buffer;
	private Graphics bufferGraphics;
	protected Canvas canvas;
	protected BackgroundImage background;
	protected GameUniverse universe;

	public GameUniverseViewPortCrateImpl(Canvas canvas, GameUniverse universe) {
		this.canvas = canvas;
		buffer = canvas.createImage(canvas.getWidth(), canvas.getHeight());
		bufferGraphics = buffer.getGraphics();
		background = new BackgroundImage(backgroundImage(), canvas);
		this.universe = universe;
	}

	protected String backgroundImage() {
		return "resources/background_default.png";
	}

	public void setBackground(String filename) {
		background = new BackgroundImage(filename, canvas);
	}

	public void paint() {
		background.draw(bufferGraphics);
		Iterator<GameEntity> gt = universe.gameEntities();
		for (; gt.hasNext();) {
			GameEntity tmp = gt.next();
			if (tmp instanceof Drawable) {
				((Drawable) tmp).draw(bufferGraphics);
			}
		}
		refresh();
	}

	public void refresh() {
		canvas.getGraphics().drawImage(buffer, 0, 0, canvas.getWidth(),
				canvas.getHeight(), canvas);
	}
}
