package com.warluscampsite.mylittlemaze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.data.Data;

public class MazePanel extends JPanel {
	private static final long serialVersionUID = 1109804073986636189L;
	
	private Data data;
	private MazePainting mazePainting;

	Image bgImage;

	final int PANEL_UNDER_MAZE_SIZE = 380;

	private JCheckBox chckbxAutoDescend;
	private JPanel jPanelForMazePainting;
	private JButton jDescendButton;
	private JCheckBox chckbxKeyFoundQuest;
	private JCheckBox chckbxBossKilledQuest;
	private JCheckBox chckbxExitFound;
	private JLabel jMazeNameLabel;
	private JLabel jMazeLevelLabel;

	/**
	 * Create the panel.
	 * 
	 * @param data
	 */
	public MazePanel(Data data) {
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.data = data;
		mazePainting = new MazePainting(data);
		try {
			bgImage = ImageIO.read(new File("res/GUI/mazeBg.png")).getScaledInstance(464, 675, Image.SCALE_SMOOTH);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, PANEL_UNDER_MAZE_SIZE, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, PANEL_UNDER_MAZE_SIZE, 5, 0, 10, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		jMazeNameLabel = new JLabel("Cementary");
		jMazeNameLabel.setForeground(Color.WHITE);
		jMazeNameLabel.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_jMazeNameLabel = new GridBagConstraints();
		gbc_jMazeNameLabel.anchor = GridBagConstraints.WEST;
		gbc_jMazeNameLabel.fill = GridBagConstraints.VERTICAL;
		gbc_jMazeNameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_jMazeNameLabel.gridx = 0;
		gbc_jMazeNameLabel.gridy = 0;
		panel.add(jMazeNameLabel, gbc_jMazeNameLabel);

		jMazeLevelLabel = new JLabel("Level: ");
		jMazeLevelLabel.setForeground(Color.WHITE);
		jMazeLevelLabel.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 17));
		GridBagConstraints gbc_jMazeLevelLabel = new GridBagConstraints();
		gbc_jMazeLevelLabel.anchor = GridBagConstraints.EAST;
		gbc_jMazeLevelLabel.fill = GridBagConstraints.VERTICAL;
		gbc_jMazeLevelLabel.gridx = 1;
		gbc_jMazeLevelLabel.gridy = 0;
		panel.add(jMazeLevelLabel, gbc_jMazeLevelLabel);

		jPanelForMazePainting = new JPanel();
		jPanelForMazePainting.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		GridBagConstraints gbc_jPanelForMazePainting = new GridBagConstraints();
		gbc_jPanelForMazePainting.insets = new Insets(0, 0, 5, 5);
		gbc_jPanelForMazePainting.fill = GridBagConstraints.BOTH;
		gbc_jPanelForMazePainting.gridx = 1;
		gbc_jPanelForMazePainting.gridy = 2;
		add(jPanelForMazePainting, gbc_jPanelForMazePainting);
		jPanelForMazePainting.setLayout(new BorderLayout(0, 0));

		JPanel jMazeLowerPanel = new JPanel();
		jMazeLowerPanel.setOpaque(false);
		GridBagConstraints gbc_jMazeLowerPanel = new GridBagConstraints();
		gbc_jMazeLowerPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jMazeLowerPanel.fill = GridBagConstraints.BOTH;
		gbc_jMazeLowerPanel.gridx = 1;
		gbc_jMazeLowerPanel.gridy = 4;
		add(jMazeLowerPanel, gbc_jMazeLowerPanel);
		GridBagLayout gbl_jMazeLowerPanel = new GridBagLayout();
		gbl_jMazeLowerPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_jMazeLowerPanel.rowHeights = new int[] { 0, 0 };
		gbl_jMazeLowerPanel.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_jMazeLowerPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		jMazeLowerPanel.setLayout(gbl_jMazeLowerPanel);

		JPanel jMazeQuetsPanel = new JPanel();
		jMazeQuetsPanel.setOpaque(false);
		GridBagConstraints gbc_jMazeQuetsPanel = new GridBagConstraints();
		gbc_jMazeQuetsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_jMazeQuetsPanel.fill = GridBagConstraints.BOTH;
		gbc_jMazeQuetsPanel.gridx = 0;
		gbc_jMazeQuetsPanel.gridy = 0;
		jMazeLowerPanel.add(jMazeQuetsPanel, gbc_jMazeQuetsPanel);
		GridBagLayout gbl_jMazeQuetsPanel = new GridBagLayout();
		gbl_jMazeQuetsPanel.columnWidths = new int[] { 0, 0 };
		gbl_jMazeQuetsPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_jMazeQuetsPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jMazeQuetsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jMazeQuetsPanel.setLayout(gbl_jMazeQuetsPanel);

		JLabel lblMazeQuests = new JLabel("Maze quests");
		lblMazeQuests.setForeground(Color.WHITE);
		lblMazeQuests.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblMazeQuests = new GridBagConstraints();
		gbc_lblMazeQuests.insets = new Insets(0, 0, 5, 0);
		gbc_lblMazeQuests.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMazeQuests.gridx = 0;
		gbc_lblMazeQuests.gridy = 0;
		jMazeQuetsPanel.add(lblMazeQuests, gbc_lblMazeQuests);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		jMazeQuetsPanel.add(separator, gbc_separator);

		chckbxKeyFoundQuest = new JCheckBox("Key found");
		chckbxKeyFoundQuest.setForeground(Color.WHITE);
		chckbxKeyFoundQuest.setOpaque(false);
		chckbxKeyFoundQuest.setEnabled(false);
		chckbxKeyFoundQuest.setBackground(Color.WHITE);
		GridBagConstraints gbc_chckbxKeyFoundQuest = new GridBagConstraints();
		gbc_chckbxKeyFoundQuest.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxKeyFoundQuest.gridx = 0;
		gbc_chckbxKeyFoundQuest.gridy = 2;
		jMazeQuetsPanel.add(chckbxKeyFoundQuest, gbc_chckbxKeyFoundQuest);

		chckbxBossKilledQuest = new JCheckBox("Boss killed");
		chckbxBossKilledQuest.setForeground(Color.WHITE);
		chckbxBossKilledQuest.setOpaque(false);
		chckbxBossKilledQuest.setEnabled(false);
		chckbxBossKilledQuest.setBackground(Color.WHITE);
		GridBagConstraints gbc_chckbxBossKilledQuest = new GridBagConstraints();
		gbc_chckbxBossKilledQuest.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxBossKilledQuest.gridx = 0;
		gbc_chckbxBossKilledQuest.gridy = 3;
		jMazeQuetsPanel.add(chckbxBossKilledQuest, gbc_chckbxBossKilledQuest);

		chckbxExitFound = new JCheckBox("Exit found");
		chckbxExitFound.setForeground(Color.WHITE);
		chckbxExitFound.setOpaque(false);
		chckbxExitFound.setEnabled(false);
		chckbxExitFound.setBackground(Color.WHITE);
		GridBagConstraints gbc_chckbxExitFound = new GridBagConstraints();
		gbc_chckbxExitFound.gridx = 0;
		gbc_chckbxExitFound.gridy = 4;
		jMazeQuetsPanel.add(chckbxExitFound, gbc_chckbxExitFound);

		JPanel jUnderButtonQuest = new JPanel();
		jUnderButtonQuest.setOpaque(false);
		GridBagConstraints gbc_jUnderButtonQuest = new GridBagConstraints();
		gbc_jUnderButtonQuest.fill = GridBagConstraints.BOTH;
		gbc_jUnderButtonQuest.gridx = 1;
		gbc_jUnderButtonQuest.gridy = 0;
		jMazeLowerPanel.add(jUnderButtonQuest, gbc_jUnderButtonQuest);
		GridBagLayout gbl_jUnderButtonQuest = new GridBagLayout();
		gbl_jUnderButtonQuest.columnWidths = new int[] { 0, 0 };
		gbl_jUnderButtonQuest.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_jUnderButtonQuest.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jUnderButtonQuest.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jUnderButtonQuest.setLayout(gbl_jUnderButtonQuest);

		jDescendButton = new JButton("Descend!");
		jDescendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.getMaze().newMaze();
				data.getMaze().getMonstersParty().reconstructMonsterTeam();
				data.getGUI().getBattlePanel().initializeMonsterMiniatures();

				refresh();
				repaint();
			}
		});
		jDescendButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_jDescendButton = new GridBagConstraints();
		gbc_jDescendButton.insets = new Insets(0, 0, 5, 0);
		gbc_jDescendButton.fill = GridBagConstraints.VERTICAL;
		gbc_jDescendButton.gridx = 0;
		gbc_jDescendButton.gridy = 1;
		jUnderButtonQuest.add(jDescendButton, gbc_jDescendButton);

		chckbxAutoDescend = new JCheckBox("Auto descent");
		chckbxAutoDescend.setContentAreaFilled(false);
		chckbxAutoDescend.setOpaque(false);
		chckbxAutoDescend.setForeground(Color.WHITE);
		chckbxAutoDescend.setBackground(Color.WHITE);
		GridBagConstraints gbc_chckbxAutoDescend = new GridBagConstraints();
		gbc_chckbxAutoDescend.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAutoDescend.gridx = 0;
		gbc_chckbxAutoDescend.gridy = 2;
		jUnderButtonQuest.add(chckbxAutoDescend, gbc_chckbxAutoDescend);

		initializeMazePainting();
	}

	private void initializeMazePainting() {
		jPanelForMazePainting.add(mazePainting);
	}

	public void refresh() {
		jMazeNameLabel.setText(data.getMaze().getMazeTypes().toString());
		jMazeLevelLabel.setText("Level: " + data.getMaze().getLevel());
	}

	public JCheckBox getChckbxAutoDescend() {
		return chckbxAutoDescend;
	}

	public JButton getjDescendButton() {
		return jDescendButton;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, null);
	}
}
