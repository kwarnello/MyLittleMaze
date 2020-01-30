package com.warluscampsite.mylittlemaze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import com.warluscampsite.mylittlemaze.status.Status;

public class StatusIcon extends JPanel {
	private static final long serialVersionUID = -7484575330542079373L;

	Status status;

	final int width = 24, height = 24;
	private JProgressBar jProgressBarToEnd;

	/**
	 * Create the panel.
	 */
	public StatusIcon(Status status) {
		setBorder(null);
		this.status = status;

		setPreferredSize(new Dimension(width, height));
		setSize(new Dimension(width, height));
		setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(null);

		jProgressBarToEnd = new JProgressBar();
		jProgressBarToEnd.setBorder(null);
		jProgressBarToEnd.setOpaque(true);
		jProgressBarToEnd.setBounds(-2, -2, width + 4, height + 4);
		jProgressBarToEnd.setValue(0);
		jProgressBarToEnd.setMaximum(status.getStatusTime());
		jProgressBarToEnd.setBackground(new Color(0, 0, 0));
		jProgressBarToEnd.setOrientation(SwingConstants.VERTICAL);
		layeredPane.add(jProgressBarToEnd);

		if (status.isItGood())
			jProgressBarToEnd.setForeground(new Color(0, 204, 0));
		else
			jProgressBarToEnd.setForeground(new Color(179, 0, 0));

		JLabel lblForIcon = new JLabel("");
		layeredPane.setLayer(lblForIcon, 1);
		lblForIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblForIcon.setBounds(0, 0, width, height);
		lblForIcon.setToolTipText(status.getToolTip());

		Image imageIcon = null;
		try {
			imageIcon = ImageIO.read(new File(status.getPathToImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		lblForIcon.setIcon(new ImageIcon(imageIcon));

		layeredPane.add(lblForIcon);
	}

	public void refresh() {
		jProgressBarToEnd.setValue(status.getStatusTime() - status.getActualTime());
		jProgressBarToEnd.setMaximum(status.getStatusTime());
	}

	public boolean checkIfShouldEnd() {
		return status.checkIfShouldEnd();
	}

	public Status getStatus() {
		return status;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
