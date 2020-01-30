package com.warluscampsite.mylittlemaze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.controllers.DragListener;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.status.Status;

public class CharacterMiniature extends JPanel {
	private static final long serialVersionUID = 5865089809366299133L;

	Characterr character;

	final int imageWidth = 70, imageHeight = 70;
	private JProgressBar jHealthBar;
	private JLabel lblForName;
	private JLabel lblForInformation;
	private JProgressBar jManaBar;

	List<StatusIcon> statusIcons;
	private JPanel panelForStatuses;

	/**
	 * Create the panel.
	 * 
	 * @param character
	 */
	public CharacterMiniature(Characterr character) {
		DragListener drag = new DragListener();

		setOpaque(false);
		setSize(new Dimension(213, 103));
		this.character = character;

		setBackground(Color.GRAY);
		setLayout(null);

		JPanel jPanelUnderImageAndBars = new JPanel();
		jPanelUnderImageAndBars.setOpaque(false);
		jPanelUnderImageAndBars.setBounds(138, 0, 68, 103);
		add(jPanelUnderImageAndBars);
		jPanelUnderImageAndBars.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 3, 68, 68);
		jPanelUnderImageAndBars.add(panel_1);
		panel_1.setLayout(null);

		Image imageIcon = null;
		try {
			imageIcon = ImageIO.read(new File("res/monsters/zombie.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageIcon = imageIcon.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
		ImageIcon imgToIcon = new ImageIcon(imageIcon);
		JLabel lblForImage = new JLabel("");
		lblForImage.setBounds(0, 0, 68, 68);
		panel_1.add(lblForImage);
		lblForImage.setIcon(imgToIcon);

		jHealthBar = new JProgressBar();
		jHealthBar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jHealthBar.setStringPainted(true);
		jHealthBar.setForeground(new Color(128, 0, 0));
		jHealthBar.setValue(50);
		jHealthBar.setBounds(0, 71, 68, 13);
		jPanelUnderImageAndBars.add(jHealthBar);

		jManaBar = new JProgressBar();
		jManaBar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jManaBar.setStringPainted(true);
		jManaBar.setForeground(new Color(0, 0, 128));
		jManaBar.setValue(50);
		jManaBar.setBounds(0, 85, 68, 13);
		jPanelUnderImageAndBars.add(jManaBar);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(9, 11, 27, 27);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		lblForInformation = new JLabel("i");
		lblForInformation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblForInformation.addMouseListener(drag);
		lblForInformation.addMouseMotionListener(drag);
		panel.add(lblForInformation);
		lblForInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblForInformation.setHorizontalAlignment(SwingConstants.CENTER);

		lblForName = new JLabel("");
		lblForName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblForName.setBounds(39, 11, 90, 27);
		add(lblForName);

		panelForStatuses = new JPanel();
		panelForStatuses.setOpaque(false);
		panelForStatuses.setBounds(9, 43, 124, 53);
		add(panelForStatuses);
		panelForStatuses.setLayout(null);

		initialize();
	}

	private void initialize() {
		lblForName.setText(character.getName());
		lblForInformation.setToolTipText(character.getName() + " PlaceHolder");

		statusIcons = new LinkedList<>();
		refresh();
	}

	public void refresh() {
		jHealthBar.setMaximum(character.getHealth().getMaxValue());
		jHealthBar.setValue((int) character.getHealth().getCurrentValue());
		jHealthBar.setString((int) character.getHealth().getCurrentValue() + "/" + character.getHealth().getMaxValue());

		jManaBar.setMaximum(character.getMana().getMaxValue());
		jManaBar.setValue((int) character.getMana().getCurrentValue());
		jManaBar.setString((int) character.getMana().getCurrentValue() + "/" + character.getMana().getMaxValue());

		if (character.getStatus().isStatusChanged()) {
			character.getStatus().setStatusChanged(false);
			repaintStatusesIcons();
		} else
			statusIcons.forEach(n -> n.refresh());
	}

	private void repaintStatusesIcons() {
		int goodCounter = 0, badCounter = 0;
		int widthIcon = 24;
		int heightIcon = 24;

		statusIcons.clear();
		panelForStatuses.removeAll();

		for (Status status : character.getStatus().getStatusesMap().values())
			statusIcons.add(new StatusIcon(status));

		for (StatusIcon statusIcon : statusIcons) {
			if (statusIcon.getStatus().isItGood()) {
				statusIcon.setBounds((widthIcon + 1) * goodCounter, 0, widthIcon, heightIcon);
				panelForStatuses.add(statusIcon);

				goodCounter++;
			} else {
				statusIcon.setBounds((widthIcon + 1) * badCounter, heightIcon + 2, widthIcon, heightIcon);
				panelForStatuses.add(statusIcon);

				badCounter++;
			}
		}

		revalidate();
		repaint();
	}

	public Characterr getCharacter() {
		return character;
	}
	
	
}
