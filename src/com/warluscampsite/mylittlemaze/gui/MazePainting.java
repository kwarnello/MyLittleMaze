package com.warluscampsite.mylittlemaze.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.warluscampsite.mylittlemaze.data.Data;

public class MazePainting extends JPanel {
	private static final long serialVersionUID = -8267906135324339968L;
	
	private Data data;

	/**
	 * Create the panel.
	 * 
	 * @param data
	 */
	public MazePainting(Data data) {
		setOpaque(false);
		this.data = data;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int screenWidth = (int) this.getParent().getSize().getWidth();
		int screenHeight = (int) this.getParent().getSize().getHeight();

		int oneCellInXSize = (int) (screenWidth / data.getMaze().getMazeGrid().getMazeMaxX());
		int oneCellInYSize = (int) (screenHeight / data.getMaze().getMazeGrid().getMazeMaxY());
		int sizeFromLeft = (int) (screenWidth % (oneCellInXSize * data.getMaze().getMazeGrid().getMazeMaxX()));
		int sizeFromDown = (int) (screenHeight % (oneCellInYSize * data.getMaze().getMazeGrid().getMazeMaxY()));

		// drawWalls
		for (int x = 1; x <= data.getMaze().getMazeGrid().getMazeMaxX(); x++) {
			for (int y = 1; y <= data.getMaze().getMazeGrid().getMazeMaxY(); y++) {
				if (data.getMaze().getMazeGrid().getMazeCells().get(x).get(y).isWasVisited())
					g.setColor(Color.WHITE);
				else if (data.getMaze().getMazeGrid().getMazeCells().get(x).get(y).isVisible())
					g.setColor(Color.GRAY);
				else
					g.setColor(Color.BLACK);

				g.fillRect(-sizeFromLeft + (x - 1) * oneCellInXSize, -sizeFromDown + (y - 1) * oneCellInYSize,
						oneCellInXSize, oneCellInYSize);
			}
		}

		int widthOfWalls = oneCellInXSize / 10;
		if (widthOfWalls < 2)
			widthOfWalls = 2;

		g.setColor(Color.BLACK);
		for (int x = 1; x <= data.getMaze().getMazeGrid().getMazeMaxX(); x++) {
			for (int y = 1; y <= data.getMaze().getMazeGrid().getMazeMaxY(); y++) {
				if (data.getMaze().getMazeGrid().getMazeCells().get(x).get(y).isHasSouthWall()
						&& y != data.getMaze().getMazeGrid().getMazeMaxY()) {
					g.fillRect(-sizeFromLeft + (x - 1) * oneCellInXSize - widthOfWalls / 2,
							-sizeFromDown + y * oneCellInYSize - widthOfWalls / 2, oneCellInXSize + widthOfWalls,
							widthOfWalls);
				}
				if (data.getMaze().getMazeGrid().getMazeCells().get(x).get(y).isHasWestWall() && x > 1) {
					g.fillRect(-sizeFromLeft + (x - 1) * oneCellInXSize - widthOfWalls / 2,
							-sizeFromDown + (y - 1) * oneCellInYSize - widthOfWalls / 2, widthOfWalls,
							oneCellInYSize + widthOfWalls);
				}
			}
		}
	}
}
