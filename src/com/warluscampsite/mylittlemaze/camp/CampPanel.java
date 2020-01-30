package com.warluscampsite.mylittlemaze.camp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.data.Data;

public class CampPanel extends JPanel {

	private static final long serialVersionUID = 4107570001108494900L;

	Data data;

	final int newHeroCost[] = { 0, 500, 4000, 25000, 100000, 500000, 1000000 };

	final int IMAGE_HEIGHT = 189;
	final int IMAGE_WIDTH = 135;

	final int GOLD_HEIGHT = 18;
	final int GOLD_WIDTH = 18;

	final int BUTTON_HEIGHT = 50;
	final int BUTTON_WIDTH = 131;

	Image imageGold = null;
	ImageIcon iconGold = null;
	final Font font = new Font("Tahoma", Font.BOLD, 14);

	/**
	 * Create the panel image ratio = 0,71505
	 */
	public CampPanel(Data data) {
		this.data = data;

		Image imageIcon = null;
		ImageIcon icon = null;

		ImageIcon iconButton = null;
		ImageIcon iconButtonRollover = null;

		try {
			imageIcon = ImageIO.read(new File("res/camp/upgradeButton.png")).getScaledInstance(BUTTON_WIDTH,
					BUTTON_HEIGHT, Image.SCALE_SMOOTH);
			iconButton = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/camp/upgradeButtonRollover.png")).getScaledInstance(BUTTON_WIDTH,
					BUTTON_HEIGHT, Image.SCALE_SMOOTH);
			iconButtonRollover = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(Color.LIGHT_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 137, 135, 135, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 235, 235, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		try {
			imageGold = ImageIO.read(new File("res/camp/gold.png")).getScaledInstance(GOLD_WIDTH, GOLD_HEIGHT,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageGold);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblGold = new JLabel("G") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8111464740021525030L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				this.setText("GOLD: " + data.getPlayerParty().getItems().getGoldCoins());
			}
		};
		lblGold.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				data.getPlayerParty().getItems().setGoldCoins(1000000);
			}
		});
		lblGold.setHorizontalTextPosition(SwingConstants.LEFT);
		lblGold.setHorizontalAlignment(SwingConstants.CENTER);
		lblGold.setIcon(iconGold);
		lblGold.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblGold, gbc_lblNewLabel);

		JPanel jNewHeroPanel = new JPanel();
		jNewHeroPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		jNewHeroPanel.setOpaque(false);
		GridBagConstraints gbc_jNewHeroPanel = new GridBagConstraints();
		gbc_jNewHeroPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jNewHeroPanel.fill = GridBagConstraints.BOTH;
		gbc_jNewHeroPanel.gridx = 1;
		gbc_jNewHeroPanel.gridy = 2;
		add(jNewHeroPanel, gbc_jNewHeroPanel);
		GridBagLayout gbl_jNewHeroPanel = new GridBagLayout();
		gbl_jNewHeroPanel.columnWidths = new int[] { 0, 0 };
		gbl_jNewHeroPanel.rowHeights = new int[] { 2, 0, 0, 0, 5, 0 };
		gbl_jNewHeroPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jNewHeroPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jNewHeroPanel.setLayout(gbl_jNewHeroPanel);

		try {
			imageIcon = ImageIO.read(new File("res/camp/newHero.png")).getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel jNewHeroLabel = new JLabel("NEW HERO");
		jNewHeroLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_jNewHeroLabel = new GridBagConstraints();
		gbc_jNewHeroLabel.insets = new Insets(0, 0, 5, 0);
		gbc_jNewHeroLabel.gridx = 0;
		gbc_jNewHeroLabel.gridy = 1;
		jNewHeroPanel.add(jNewHeroLabel, gbc_jNewHeroLabel);

		JLabel jNewHeroIcon = new JLabel("");
		GridBagConstraints gbc_jNewHeroIcon = new GridBagConstraints();
		gbc_jNewHeroIcon.insets = new Insets(0, 0, 5, 0);
		gbc_jNewHeroIcon.gridx = 0;
		gbc_jNewHeroIcon.gridy = 2;
		jNewHeroIcon.setIcon(icon);
		jNewHeroPanel.add(jNewHeroIcon, gbc_jNewHeroIcon);

		JButton jNewHeroButton = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 2384125113548967775L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				g.setFont(font);
				g.setColor(Color.BLACK);

				String text = "Buy new hero!";
				FontMetrics metrics = g.getFontMetrics(font);
				// Determine the X coordinate for the text
				int x = (BUTTON_WIDTH - metrics.stringWidth(text) + 10) / 2;
				// Determine the Y coordinate for the text (note we add the
				// ascent, as in java
				// 2d 0 is top of the screen)
				int y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y - g.getFont().getSize() * 0.2));

				int numberOfCharacter = data.getPlayerParty().getCharacterMap().size();

				text = Integer.toString(newHeroCost[numberOfCharacter]);
				x = (BUTTON_WIDTH - metrics.stringWidth(text)) / 2;
				y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y + g.getFont().getSize() * 0.9));

				x = (BUTTON_WIDTH / 2) + (metrics.stringWidth(text) / 2) + (GOLD_WIDTH / 2);
				g.drawImage(imageGold, x, y - 2, null);
			}
		};
		jNewHeroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int costOfNewHero = newHeroCost[data.getPlayerParty().getCharacterMap().size()];
				if (data.getPlayerParty().getItems().getGoldCoins() >= costOfNewHero) {
					data.getBattle().getBattleScheduler().setPause(true);

					data.getCreationPanel().showAgain();
				}
			}
		});
		jNewHeroButton.setBorderPainted(false);
		jNewHeroButton.setMargin(new Insets(0, 0, 0, 0));
		jNewHeroButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		jNewHeroButton.setFocusPainted(false);
		jNewHeroButton.setIcon(iconButton);
		jNewHeroButton.setRolloverIcon(iconButtonRollover);
		jNewHeroButton.setContentAreaFilled(false);
		GridBagConstraints gbc_jNewHeroButton = new GridBagConstraints();
		gbc_jNewHeroButton.insets = new Insets(0, 0, 5, 0);
		gbc_jNewHeroButton.gridx = 0;
		gbc_jNewHeroButton.gridy = 3;
		jNewHeroPanel.add(jNewHeroButton, gbc_jNewHeroButton);

		JPanel jSmithPanel = new JPanel();
		jSmithPanel.setOpaque(false);
		jSmithPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_jSmithPanel = new GridBagConstraints();
		gbc_jSmithPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jSmithPanel.fill = GridBagConstraints.BOTH;
		gbc_jSmithPanel.gridx = 2;
		gbc_jSmithPanel.gridy = 2;
		add(jSmithPanel, gbc_jSmithPanel);
		GridBagLayout gbl_jSmithPanel = new GridBagLayout();
		gbl_jSmithPanel.columnWidths = new int[] { 0, 0 };
		gbl_jSmithPanel.rowHeights = new int[] { 2, 0, 0, 0, 5, 0 };
		gbl_jSmithPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jSmithPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jSmithPanel.setLayout(gbl_jSmithPanel);

		try {
			imageIcon = ImageIO.read(new File("res/camp/smith.png")).getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblSmith = new JLabel("SMITH");
		lblSmith.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblSmith = new GridBagConstraints();
		gbc_lblSmith.insets = new Insets(0, 0, 5, 0);
		gbc_lblSmith.gridx = 0;
		gbc_lblSmith.gridy = 1;
		jSmithPanel.add(lblSmith, gbc_lblSmith);

		JLabel jSmithIcon = new JLabel("");
		jSmithIcon.setIcon(icon);
		GridBagConstraints gbc_jSmithIcon = new GridBagConstraints();
		gbc_jSmithIcon.insets = new Insets(0, 0, 5, 0);
		gbc_jSmithIcon.gridx = 0;
		gbc_jSmithIcon.gridy = 2;
		jSmithPanel.add(jSmithIcon, gbc_jSmithIcon);

		JButton jSmithButton = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4626558320734111296L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				g.setFont(font);
				g.setColor(Color.BLACK);

				String text = "Upgrade!";
				FontMetrics metrics = g.getFontMetrics(font);
				// Determine the X coordinate for the text
				int x = (BUTTON_WIDTH - metrics.stringWidth(text) + 10) / 2;
				// Determine the Y coordinate for the text (note we add the
				// ascent, as in java
				// 2d 0 is top of the screen)
				int y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y - g.getFont().getSize() * 0.2));

				text = "100";
				x = (BUTTON_WIDTH - metrics.stringWidth(text)) / 2;
				y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y + g.getFont().getSize() * 0.9));

				x = (BUTTON_WIDTH / 2) + (metrics.stringWidth(text) / 2) + (GOLD_WIDTH / 2);
				g.drawImage(imageGold, x, y - 2, null);
			}
		};
		jSmithButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.getPlayerParty().getCamp().addSmithLevel();
			}
		});
		jSmithButton.setBorderPainted(false);
		jSmithButton.setMargin(new Insets(0, 0, 0, 0));
		jSmithButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		jSmithButton.setIcon(iconButton);
		jSmithButton.setRolloverIcon(iconButtonRollover);
		jSmithButton.setContentAreaFilled(false);
		GridBagConstraints gbc_jSmithButton = new GridBagConstraints();
		gbc_jSmithButton.insets = new Insets(0, 0, 5, 0);
		gbc_jSmithButton.gridx = 0;
		gbc_jSmithButton.gridy = 3;
		jSmithPanel.add(jSmithButton, gbc_jSmithButton);

		JPanel jConjurorPanel = new JPanel();
		jConjurorPanel.setOpaque(false);
		jConjurorPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_jConjurorPanel = new GridBagConstraints();
		gbc_jConjurorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jConjurorPanel.fill = GridBagConstraints.BOTH;
		gbc_jConjurorPanel.gridx = 3;
		gbc_jConjurorPanel.gridy = 2;
		add(jConjurorPanel, gbc_jConjurorPanel);
		GridBagLayout gbl_jConjurorPanel = new GridBagLayout();
		gbl_jConjurorPanel.columnWidths = new int[] { 0, 0 };
		gbl_jConjurorPanel.rowHeights = new int[] { 2, 0, 0, 0, 5, 0 };
		gbl_jConjurorPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jConjurorPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jConjurorPanel.setLayout(gbl_jConjurorPanel);

		try {
			imageIcon = ImageIO.read(new File("res/camp/conjuror.png")).getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblConjuror = new JLabel("CONJUROR");
		lblConjuror.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblConjuror = new GridBagConstraints();
		gbc_lblConjuror.insets = new Insets(0, 0, 5, 0);
		gbc_lblConjuror.gridx = 0;
		gbc_lblConjuror.gridy = 1;
		jConjurorPanel.add(lblConjuror, gbc_lblConjuror);

		JLabel jConjurorIcon = new JLabel("");
		jConjurorIcon.setIcon(icon);
		GridBagConstraints gbc_jConjurorIcon = new GridBagConstraints();
		gbc_jConjurorIcon.insets = new Insets(0, 0, 5, 0);
		gbc_jConjurorIcon.gridx = 0;
		gbc_jConjurorIcon.gridy = 2;
		jConjurorPanel.add(jConjurorIcon, gbc_jConjurorIcon);

		JButton jConjurorButton = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1069424489312062242L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				g.setFont(font);
				g.setColor(Color.BLACK);

				String text = "Upgrade!";
				FontMetrics metrics = g.getFontMetrics(font);
				// Determine the X coordinate for the text
				int x = (BUTTON_WIDTH - metrics.stringWidth(text) + 10) / 2;
				// Determine the Y coordinate for the text (note we add the
				// ascent, as in java
				// 2d 0 is top of the screen)
				int y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y - g.getFont().getSize() * 0.2));

				text = "100";
				x = (BUTTON_WIDTH - metrics.stringWidth(text)) / 2;
				y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y + g.getFont().getSize() * 0.9));

				x = (BUTTON_WIDTH / 2) + (metrics.stringWidth(text) / 2) + (GOLD_WIDTH / 2);
				g.drawImage(imageGold, x, y - 2, null);
			}
		};
		jConjurorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.getPlayerParty().getCamp().addConjurorLevel();
			}
		});
		jConjurorButton.setBorderPainted(false);
		jConjurorButton.setMargin(new Insets(0, 0, 0, 0));
		jConjurorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		jConjurorButton.setFocusPainted(false);
		jConjurorButton.setIcon(iconButton);
		jConjurorButton.setRolloverIcon(iconButtonRollover);
		jConjurorButton.setContentAreaFilled(false);
		GridBagConstraints gbc_jConjurorButton = new GridBagConstraints();
		gbc_jConjurorButton.insets = new Insets(0, 0, 5, 0);
		gbc_jConjurorButton.gridx = 0;
		gbc_jConjurorButton.gridy = 3;
		jConjurorPanel.add(jConjurorButton, gbc_jConjurorButton);

		JPanel jChestPanel = new JPanel();
		jChestPanel.setOpaque(false);
		jChestPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_jChestPanel = new GridBagConstraints();
		gbc_jChestPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jChestPanel.fill = GridBagConstraints.BOTH;
		gbc_jChestPanel.gridx = 1;
		gbc_jChestPanel.gridy = 3;
		add(jChestPanel, gbc_jChestPanel);
		GridBagLayout gbl_jChestPanel = new GridBagLayout();
		gbl_jChestPanel.columnWidths = new int[] { 0, 0 };
		gbl_jChestPanel.rowHeights = new int[] { 2, 0, 0, 0, 5, 0 };
		gbl_jChestPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jChestPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jChestPanel.setLayout(gbl_jChestPanel);

		try {
			imageIcon = ImageIO.read(new File("res/camp/chest.png")).getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblChest = new JLabel("CHEST");
		lblChest.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblChest = new GridBagConstraints();
		gbc_lblChest.insets = new Insets(0, 0, 5, 0);
		gbc_lblChest.gridx = 0;
		gbc_lblChest.gridy = 1;
		jChestPanel.add(lblChest, gbc_lblChest);

		JLabel jChestIcon = new JLabel("");
		jChestIcon.setIcon(icon);
		GridBagConstraints gbc_jChestIcon = new GridBagConstraints();
		gbc_jChestIcon.insets = new Insets(0, 0, 5, 0);
		gbc_jChestIcon.gridx = 0;
		gbc_jChestIcon.gridy = 2;
		jChestPanel.add(jChestIcon, gbc_jChestIcon);

		JButton jChestUpgradeButton = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 458209717722859722L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				g.setFont(font);
				g.setColor(Color.BLACK);

				String text = "Upgrade!";
				FontMetrics metrics = g.getFontMetrics(font);
				// Determine the X coordinate for the text
				int x = (BUTTON_WIDTH - metrics.stringWidth(text) + 10) / 2;
				// Determine the Y coordinate for the text (note we add the
				// ascent, as in java
				// 2d 0 is top of the screen)
				int y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y - g.getFont().getSize() * 0.2));

				text = "100";
				x = (BUTTON_WIDTH - metrics.stringWidth(text)) / 2;
				y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y + g.getFont().getSize() * 0.9));

				x = (BUTTON_WIDTH / 2) + (metrics.stringWidth(text) / 2) + (GOLD_WIDTH / 2);
				g.drawImage(imageGold, x, y - 2, null);
			}
		};
		jChestUpgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.getPlayerParty().getCamp().addChestLevel();
			}
		});
		jChestUpgradeButton.setBorderPainted(false);
		jChestUpgradeButton.setMargin(new Insets(0, 0, 0, 0));
		jChestUpgradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		jChestUpgradeButton.setFocusPainted(false);
		jChestUpgradeButton.setIcon(iconButton);
		jChestUpgradeButton.setRolloverIcon(iconButtonRollover);
		jChestUpgradeButton.setContentAreaFilled(false);
		GridBagConstraints gbc_jChestUpgradeButton = new GridBagConstraints();
		gbc_jChestUpgradeButton.insets = new Insets(0, 0, 5, 0);
		gbc_jChestUpgradeButton.gridx = 0;
		gbc_jChestUpgradeButton.gridy = 3;
		jChestPanel.add(jChestUpgradeButton, gbc_jChestUpgradeButton);

		JPanel jFamiliarPanel = new JPanel();
		jFamiliarPanel.setOpaque(false);
		jFamiliarPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_jFamiliarPanel = new GridBagConstraints();
		gbc_jFamiliarPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jFamiliarPanel.fill = GridBagConstraints.BOTH;
		gbc_jFamiliarPanel.gridx = 2;
		gbc_jFamiliarPanel.gridy = 3;
		add(jFamiliarPanel, gbc_jFamiliarPanel);
		GridBagLayout gbl_jFamiliarPanel = new GridBagLayout();
		gbl_jFamiliarPanel.columnWidths = new int[] { 0, 0 };
		gbl_jFamiliarPanel.rowHeights = new int[] { 2, 0, 0, 0, 5, 0 };
		gbl_jFamiliarPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jFamiliarPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jFamiliarPanel.setLayout(gbl_jFamiliarPanel);

		try {
			imageIcon = ImageIO.read(new File("res/camp/familiar.png")).getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblFamiliar = new JLabel("FAMILIAR");
		lblFamiliar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblFamiliar = new GridBagConstraints();
		gbc_lblFamiliar.insets = new Insets(0, 0, 5, 0);
		gbc_lblFamiliar.gridx = 0;
		gbc_lblFamiliar.gridy = 1;
		jFamiliarPanel.add(lblFamiliar, gbc_lblFamiliar);

		JLabel jFamiliarIcon = new JLabel("");
		jFamiliarIcon.setIcon(icon);
		GridBagConstraints gbc_jFamiliarIcon = new GridBagConstraints();
		gbc_jFamiliarIcon.insets = new Insets(0, 0, 5, 0);
		gbc_jFamiliarIcon.gridx = 0;
		gbc_jFamiliarIcon.gridy = 2;
		jFamiliarPanel.add(jFamiliarIcon, gbc_jFamiliarIcon);

		JButton jFamiliarButton = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6286522123518004933L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				g.setFont(font);
				g.setColor(Color.BLACK);

				String text = "Upgrade!";
				FontMetrics metrics = g.getFontMetrics(font);
				// Determine the X coordinate for the text
				int x = (BUTTON_WIDTH - metrics.stringWidth(text) + 10) / 2;
				// Determine the Y coordinate for the text (note we add the
				// ascent, as in java
				// 2d 0 is top of the screen)
				int y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y - g.getFont().getSize() * 0.2));

				text = "100";
				x = (BUTTON_WIDTH - metrics.stringWidth(text)) / 2;
				y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y + g.getFont().getSize() * 0.9));

				x = (BUTTON_WIDTH / 2) + (metrics.stringWidth(text) / 2) + (GOLD_WIDTH / 2);
				g.drawImage(imageGold, x, y - 2, null);
			}
		};
		jFamiliarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.getPlayerParty().getCamp().addFamiliarLevel();
			}
		});
		jFamiliarButton.setBorderPainted(false);
		jFamiliarButton.setMargin(new Insets(0, 0, 0, 0));
		jFamiliarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		jFamiliarButton.setFocusPainted(false);
		jFamiliarButton.setIcon(iconButton);
		jFamiliarButton.setRolloverIcon(iconButtonRollover);
		jFamiliarButton.setContentAreaFilled(false);
		GridBagConstraints gbc_jFamiliarButton = new GridBagConstraints();
		gbc_jFamiliarButton.insets = new Insets(0, 0, 5, 0);
		gbc_jFamiliarButton.gridx = 0;
		gbc_jFamiliarButton.gridy = 3;
		jFamiliarPanel.add(jFamiliarButton, gbc_jFamiliarButton);

		JPanel jAscensionPanel = new JPanel();
		jAscensionPanel.setOpaque(false);
		jAscensionPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_jAscensionPanel = new GridBagConstraints();
		gbc_jAscensionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jAscensionPanel.fill = GridBagConstraints.BOTH;
		gbc_jAscensionPanel.gridx = 3;
		gbc_jAscensionPanel.gridy = 3;
		add(jAscensionPanel, gbc_jAscensionPanel);
		GridBagLayout gbl_jAscensionPanel = new GridBagLayout();
		gbl_jAscensionPanel.columnWidths = new int[] { 0, 0 };
		gbl_jAscensionPanel.rowHeights = new int[] { 2, 0, 0, 0, 5, 0 };
		gbl_jAscensionPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_jAscensionPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		jAscensionPanel.setLayout(gbl_jAscensionPanel);

		try {
			imageIcon = ImageIO.read(new File("res/camp/ascension.png")).getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT,
					Image.SCALE_SMOOTH);
			icon = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel lblAscension = new JLabel("ASCENSION");
		lblAscension.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblAscension = new GridBagConstraints();
		gbc_lblAscension.insets = new Insets(0, 0, 5, 0);
		gbc_lblAscension.gridx = 0;
		gbc_lblAscension.gridy = 1;
		jAscensionPanel.add(lblAscension, gbc_lblAscension);

		JLabel jAscensionIcon = new JLabel("");
		jAscensionIcon.setIcon(icon);
		GridBagConstraints gbc_jAscensionIcon = new GridBagConstraints();
		gbc_jAscensionIcon.insets = new Insets(0, 0, 5, 0);
		gbc_jAscensionIcon.gridx = 0;
		gbc_jAscensionIcon.gridy = 2;
		jAscensionPanel.add(jAscensionIcon, gbc_jAscensionIcon);

		JButton jAscensionButton = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6607675345217906715L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);

				g.setFont(font);
				g.setColor(Color.BLACK);

				String text = "Upgrade!";
				FontMetrics metrics = g.getFontMetrics(font);
				// Determine the X coordinate for the text
				int x = (BUTTON_WIDTH - metrics.stringWidth(text) + 10) / 2;
				// Determine the Y coordinate for the text (note we add the
				// ascent, as in java
				// 2d 0 is top of the screen)
				int y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y - g.getFont().getSize() * 0.2));

				text = "100000000";
				x = (BUTTON_WIDTH - metrics.stringWidth(text)) / 2;
				y = ((BUTTON_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
				g.drawString(text, x, (int) (y + g.getFont().getSize() * 0.9));

				x = (BUTTON_WIDTH / 2) + (metrics.stringWidth(text) / 2) + (GOLD_WIDTH / 2);
				g.drawImage(imageGold, x, y - 2, null);
			}
		};
		jAscensionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.getPlayerParty().getCamp().ascension();
			}
		});
		jAscensionButton.setBorderPainted(false);
		jAscensionButton.setMargin(new Insets(0, 0, 0, 0));
		jAscensionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		jAscensionButton.setFocusPainted(false);
		jAscensionButton.setIcon(iconButton);
		jAscensionButton.setRolloverIcon(iconButtonRollover);
		jAscensionButton.setContentAreaFilled(false);
		GridBagConstraints gbc_jAscensionButton = new GridBagConstraints();
		gbc_jAscensionButton.insets = new Insets(0, 0, 5, 0);
		gbc_jAscensionButton.gridx = 0;
		gbc_jAscensionButton.gridy = 3;
		jAscensionPanel.add(jAscensionButton, gbc_jAscensionButton);

	}

}
