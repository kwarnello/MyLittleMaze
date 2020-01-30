package com.warluscampsite.mylittlemaze.gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;

public class SingleAchievmentPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SingleAchievmentPanel() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 2, 0, 2, 0 };
		gridBagLayout.rowHeights = new int[] { 2, 0, 2, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), Color.DARK_GRAY,
				Color.DARK_GRAY));
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 3, 64, 1, 0 };
		gbl_panel.rowHeights = new int[] { 1, 0, 0, 64, 4, 0, 1, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 2.0, 0.0, 1.0, 0.0, 1.0, 0.0, 2.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel jNameLabel = new JLabel("New label");
		jNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jNameLabel.setPreferredSize(new Dimension(10, 15));
		jNameLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
		GridBagConstraints gbc_jNameLabel = new GridBagConstraints();
		gbc_jNameLabel.fill = GridBagConstraints.BOTH;
		gbc_jNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jNameLabel.gridx = 1;
		gbc_jNameLabel.gridy = 1;
		panel.add(jNameLabel, gbc_jNameLabel);

		JPanel jPanelForIcon = new JPanel();
		jPanelForIcon.setBackground(Color.WHITE);
		GridBagConstraints gbc_jPanelForIcon = new GridBagConstraints();
		gbc_jPanelForIcon.insets = new Insets(0, 0, 5, 5);
		gbc_jPanelForIcon.fill = GridBagConstraints.BOTH;
		gbc_jPanelForIcon.gridx = 1;
		gbc_jPanelForIcon.gridy = 3;
		panel.add(jPanelForIcon, gbc_jPanelForIcon);

		JProgressBar jProgressBar = new JProgressBar();
		jProgressBar.setPreferredSize(new Dimension(10, 14));
		jProgressBar.setValue(50);
		jProgressBar.setForeground(new Color(0, 100, 0));
		GridBagConstraints gbc_jProgressBar = new GridBagConstraints();
		gbc_jProgressBar.fill = GridBagConstraints.BOTH;
		gbc_jProgressBar.insets = new Insets(0, 0, 5, 5);
		gbc_jProgressBar.gridx = 1;
		gbc_jProgressBar.gridy = 5;
		panel.add(jProgressBar, gbc_jProgressBar);
	}

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
		System.out.println(this.getSize());

	}

}
