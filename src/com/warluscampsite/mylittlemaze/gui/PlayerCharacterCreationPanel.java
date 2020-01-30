package com.warluscampsite.mylittlemaze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.playerteam.classes.Archer;
import com.warluscampsite.mylittlemaze.playerteam.classes.Elementalist;
import com.warluscampsite.mylittlemaze.playerteam.classes.Warrior;

public class PlayerCharacterCreationPanel extends JDialog {
	private static final long serialVersionUID = 8078677877221240909L;

	Data data;

	boolean firstTime;

	int profession = 1;

	final int SIZE_OF_ICON = 166;
	final int HALF_BIGGER_SIZE = 2;
	final int FRAME_WIDTH = 1220;
	final int FRAME_HEIGHT = 590;
	final int BUTTON_WIDTH = 80;
	final int BUTTON_HEIGHT = 30;

	Image imageForText = null;
	Image imageForBg = null;

	Image imageIcon = null;
	ImageIcon imgToIcon = null;
	ImageIcon imgToIconBigger = null;
	private JLabel jTextLbl;

	public PlayerCharacterCreationPanel(Data data) {
		this.data = data;

		firstTime = true;

		data.getGUI().setEnabled(false);

		setBounds(50, 50, FRAME_WIDTH, FRAME_HEIGHT);
		setLocation(90, 110);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		try {
			imageForBg = ImageIO.read(new File("res/GUI/bgForCharacterMiniatures.png"));
			imageForBg = imageForBg.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JPanel jPanelForBg = new JPanel() {
			private static final long serialVersionUID = 6482725990776181078L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imageForBg, 0, 0, null);
			}
		};
		jPanelForBg.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		getContentPane().add(jPanelForBg);
		GridBagConstraints gbc_jPanelForText_1 = new GridBagConstraints();
		gbc_jPanelForText_1.anchor = GridBagConstraints.WEST;
		gbc_jPanelForText_1.insets = new Insets(0, 0, 0, 5);
		gbc_jPanelForText_1.fill = GridBagConstraints.BOTH;
		gbc_jPanelForText_1.gridx = 1;
		gbc_jPanelForText_1.gridy = 0;
		GridBagConstraints gbc_jPanelUnderClassIcons_1 = new GridBagConstraints();
		gbc_jPanelUnderClassIcons_1.anchor = GridBagConstraints.WEST;
		gbc_jPanelUnderClassIcons_1.insets = new Insets(0, 0, 0, 5);
		gbc_jPanelUnderClassIcons_1.fill = GridBagConstraints.BOTH;
		gbc_jPanelUnderClassIcons_1.gridx = 2;
		gbc_jPanelUnderClassIcons_1.gridy = 0;
		GridBagLayout gbl_jPanelForBg = new GridBagLayout();
		gbl_jPanelForBg.columnWidths = new int[] { 40, 850, 140, 140, 50 };
		gbl_jPanelForBg.rowHeights = new int[] { 25, 494, 35, 20, 5 };
		gbl_jPanelForBg.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_jPanelForBg.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jPanelForBg.setLayout(gbl_jPanelForBg);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 0;

		try {
			imageForText = ImageIO.read(new File("res/tooltipBg2.png"));
			imageForText = imageForText.getScaledInstance(280, 490, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JPanel jPanelForText = new JPanel() {
			private static final long serialVersionUID = 4986965231883491360L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imageForText, 0, 0, null);
			}
		};
		jPanelForText.setOpaque(false);
		GridBagConstraints gbc_jPanelForText = new GridBagConstraints();
		gbc_jPanelForText.gridwidth = 2;
		gbc_jPanelForText.fill = GridBagConstraints.BOTH;
		gbc_jPanelForText.insets = new Insets(0, 0, 5, 5);
		gbc_jPanelForText.gridx = 2;
		gbc_jPanelForText.gridy = 1;
		jPanelForBg.add(jPanelForText, gbc_jPanelForText);
		jPanelForText.setLayout(new BorderLayout(0, 0));

		jTextLbl = new JLabel();
		jPanelForText.add(jTextLbl, BorderLayout.NORTH);
		jTextLbl.setVerticalAlignment(SwingConstants.TOP);
		jTextLbl.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel jPanelUnderClassIcons = new JPanel();
		jPanelUnderClassIcons.setOpaque(false);
		jPanelUnderClassIcons.setBackground(Color.WHITE);
		jPanelUnderClassIcons.setLayout(null);
		GridBagConstraints gbc_jPanelUnderClassIcons = new GridBagConstraints();
		gbc_jPanelUnderClassIcons.fill = GridBagConstraints.BOTH;
		gbc_jPanelUnderClassIcons.gridheight = 2;
		gbc_jPanelUnderClassIcons.insets = new Insets(0, 0, 5, 5);
		gbc_jPanelUnderClassIcons.gridx = 1;
		gbc_jPanelUnderClassIcons.gridy = 1;
		jPanelForBg.add(jPanelUnderClassIcons, gbc_jPanelUnderClassIcons);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/warrior.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/warrior.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton jWarriorButton = new JButton("");
		jWarriorButton.setContentAreaFilled(false);
		jWarriorButton.setOpaque(false);
		jWarriorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profession = 1;
				String text = "<h2><center>&nbsp Warrior</center></h2><br>"
						+ "<p>The Warrior is front class who is doing well with two-handed sword or with a shield. The Warrior can hit multiple enemies and weaken them. His anger can buff his and allies armor.</p>";
				setLabelText(text);
			}
		});
		jWarriorButton.setBounds(2, 2, 166, 166);
		jWarriorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		jWarriorButton.setFocusPainted(false);
		jWarriorButton.setBorder(null);
		jWarriorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				jWarriorButton.setBounds(-1, -1, SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2,
						SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jWarriorButton.setBounds(2, 2, SIZE_OF_ICON + 2, SIZE_OF_ICON + 2);
			}
		});

		jWarriorButton.setIcon(imgToIcon);
		jWarriorButton.setRolloverIcon(imgToIconBigger);
		jPanelUnderClassIcons.add(jWarriorButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/archer.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/archer.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton jArcherButton = new JButton("");
		jArcherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profession = 2;
				String text = "<h2><center>&nbsp Archer</center></h2><br>"
						+ "<p>The Archer is perfect damage dealer for single or multiple enemies. His subclasses allow him to perfectly use critical strikes or kill enemies with powerful statuses like bleed or poisons.</p>";
				setLabelText(text);
			}
		});
		jArcherButton.setContentAreaFilled(false);
		jArcherButton.setEnabled(true);
		jArcherButton.setOpaque(false);
		jArcherButton.setFocusPainted(false);
		jArcherButton.setBorder(null);
		jArcherButton.setAlignmentX(0.5f);
		jArcherButton.setBounds(2, 172, 166, 166);
		jArcherButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				jArcherButton.setBounds(-1, 169, SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2,
						SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jArcherButton.setBounds(2, 172, SIZE_OF_ICON + 2, SIZE_OF_ICON + 2);
			}
		});
		jArcherButton.setIcon(imgToIcon);
		jArcherButton.setRolloverIcon(imgToIconBigger);
		jPanelUnderClassIcons.add(jArcherButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/priest.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/priest.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton jPriestButton = new JButton("");
		jPriestButton.setContentAreaFilled(false);
		jPriestButton.setEnabled(false);
		jPriestButton.setOpaque(false);
		jPriestButton.setFocusPainted(false);
		jPriestButton.setBorder(null);
		jPriestButton.setAlignmentX(0.5f);
		jPriestButton.setBounds(2, 342, 166, 166);
		jPriestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				jPriestButton.setBounds(-1, 339, SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2,
						SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPriestButton.setBounds(2, 342, SIZE_OF_ICON + 2, SIZE_OF_ICON + 2);
			}
		});
		jPriestButton.setIcon(imgToIcon);
		jPriestButton.setRolloverIcon(imgToIconBigger);
		jPanelUnderClassIcons.add(jPriestButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/paladin.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/paladin.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jPaladinButton = new JButton("");
		jPaladinButton.setContentAreaFilled(false);
		jPaladinButton.setEnabled(false);
		jPaladinButton.setOpaque(false);
		jPaladinButton.setFocusPainted(false);
		jPaladinButton.setBorder(null);
		jPaladinButton.setAlignmentX(0.5f);
		jPaladinButton.setBounds(172, 2, 166, 166);
		jPaladinButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jPaladinButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/barbarian.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/barbarian.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jBarbarianButton = new JButton("");
		jBarbarianButton.setContentAreaFilled(false);
		jBarbarianButton.setEnabled(false);
		jBarbarianButton.setOpaque(false);
		jBarbarianButton.setFocusPainted(false);
		jBarbarianButton.setBorder(null);
		jBarbarianButton.setAlignmentX(0.5f);
		jBarbarianButton.setBounds(342, 2, 166, 166);
		jBarbarianButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jBarbarianButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/monk.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/monk.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jMonkButton = new JButton("");
		jMonkButton.setContentAreaFilled(false);
		jMonkButton.setEnabled(false);
		jMonkButton.setOpaque(false);
		jMonkButton.setFocusPainted(false);
		jMonkButton.setBorder(null);
		jMonkButton.setAlignmentX(0.5f);
		jMonkButton.setBounds(512, 2, 166, 166);
		jMonkButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jMonkButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/darkKnight.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/darkKnight.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jDarkKnight = new JButton("");
		jDarkKnight.setContentAreaFilled(false);
		jDarkKnight.setEnabled(false);
		jDarkKnight.setOpaque(false);
		jDarkKnight.setFocusPainted(false);
		jDarkKnight.setBorder(null);
		jDarkKnight.setAlignmentX(0.5f);
		jDarkKnight.setBounds(682, 2, 166, 166);
		jDarkKnight.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jDarkKnight);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/elementalist.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/elementalist.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jElementalistButton = new JButton("");
		jElementalistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profession = 5;
				String text = "<h2><center>&nbsp Elementalist</center></h2><br>"
						+ "<p>Elementalist is master of the elements, which uses destructively or to buff his teammate's weapons. When his power is increasing he can implicit powerful ailments to his enemies or fight in the first row with a shield and use his physical qualities to enhance his magic.</p>";
				setLabelText(text);
			}
		});
		jElementalistButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				jElementalistButton.setBounds(169, 169, SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2,
						SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE + 2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jElementalistButton.setBounds(172, 172, SIZE_OF_ICON + 2, SIZE_OF_ICON + 2);
			}
		});
		jElementalistButton.setContentAreaFilled(false);
		jElementalistButton.setOpaque(false);
		jElementalistButton.setFocusPainted(false);
		jElementalistButton.setBorder(null);
		jElementalistButton.setAlignmentX(0.5f);
		jElementalistButton.setBounds(172, 172, 166, 166);
		jElementalistButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jElementalistButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/necromancer.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/necromancer.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jNecromancerButton = new JButton("");
		jNecromancerButton.setContentAreaFilled(false);
		jNecromancerButton.setEnabled(false);
		jNecromancerButton.setOpaque(false);
		jNecromancerButton.setFocusPainted(false);
		jNecromancerButton.setBorder(null);
		jNecromancerButton.setAlignmentX(0.5f);
		jNecromancerButton.setBounds(342, 172, 166, 166);
		jNecromancerButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jNecromancerButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/pyromancer.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/pyromancer.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jPyromancerButton = new JButton("");
		jPyromancerButton.setContentAreaFilled(false);
		jPyromancerButton.setEnabled(false);
		jPyromancerButton.setOpaque(false);
		jPyromancerButton.setFocusPainted(false);
		jPyromancerButton.setBorder(null);
		jPyromancerButton.setAlignmentX(0.5f);
		jPyromancerButton.setBounds(512, 172, 166, 166);
		jPyromancerButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jPyromancerButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/thief.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/thief.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jThiefButton = new JButton("");
		jThiefButton.setContentAreaFilled(false);
		jThiefButton.setEnabled(false);
		jThiefButton.setOpaque(false);
		jThiefButton.setFocusPainted(false);
		jThiefButton.setBorder(null);
		jThiefButton.setAlignmentX(0.5f);
		jThiefButton.setBounds(682, 172, 166, 166);
		jThiefButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jThiefButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/druid.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/druid.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jDruidButton = new JButton("");
		jDruidButton.setContentAreaFilled(false);
		jDruidButton.setEnabled(false);
		jDruidButton.setOpaque(false);
		jDruidButton.setFocusPainted(false);
		jDruidButton.setBorder(null);
		jDruidButton.setAlignmentX(0.5f);
		jDruidButton.setBounds(172, 342, 166, 166);
		jDruidButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jDruidButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/shaman.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/shaman.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jShamanButton = new JButton("");
		jShamanButton.setContentAreaFilled(false);
		jShamanButton.setEnabled(false);
		jShamanButton.setOpaque(false);
		jShamanButton.setFocusPainted(false);
		jShamanButton.setBorder(null);
		jShamanButton.setAlignmentX(0.5f);
		jShamanButton.setBounds(342, 342, 166, 166);
		jShamanButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jShamanButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/witch.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/witch.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jWitchButton = new JButton("");
		jWitchButton.setContentAreaFilled(false);
		jWitchButton.setEnabled(false);
		jWitchButton.setOpaque(false);
		jWitchButton.setFocusPainted(false);
		jWitchButton.setBorder(null);
		jWitchButton.setAlignmentX(0.5f);
		jWitchButton.setBounds(512, 342, 166, 166);
		jWitchButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jWitchButton);

		try {
			imageIcon = ImageIO.read(new File("res/classBadges/bard.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON, SIZE_OF_ICON, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/classBadges/bard.png"));
			imageIcon = imageIcon.getScaledInstance(SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE,
					SIZE_OF_ICON + 2 * HALF_BIGGER_SIZE, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JButton jBardButton = new JButton("");
		jBardButton.setContentAreaFilled(false);
		jBardButton.setEnabled(false);
		jBardButton.setOpaque(false);
		jBardButton.setFocusPainted(false);
		jBardButton.setBorder(null);
		jBardButton.setAlignmentX(0.5f);
		jBardButton.setBounds(682, 342, 166, 166);
		jBardButton.setIcon(imgToIcon);
		jPanelUnderClassIcons.add(jBardButton);

		try {
			imageIcon = ImageIO.read(new File("res/GUI/button.png"));
			imageIcon = imageIcon.getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon) {
				private static final long serialVersionUID = 4087747971163730219L;

				public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
					super.paintIcon(c, g, x, y);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setFont(new Font("Rockwell", Font.BOLD, 14));

					g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

					g2d.setPaint(Color.BLACK);
					g2d.drawString("Return", this.getIconWidth() / 2 - 8, this.getIconHeight() / 2 + 8);
				};
			};
		} catch (IOException e) {
			e.printStackTrace();
		}

		JButton jReturnButton = new JButton("");
		jReturnButton.setContentAreaFilled(false);
		jReturnButton.setOpaque(false);
		jReturnButton.setBorderPainted(false);
		jReturnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jReturnButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		jReturnButton.setFocusPainted(false);
		jReturnButton.setIcon(imgToIcon);
		jReturnButton.setVisible(false);
		jReturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		GridBagConstraints gbc_jReturnButton = new GridBagConstraints();
		gbc_jReturnButton.insets = new Insets(0, 0, 5, 5);
		gbc_jReturnButton.gridx = 2;
		gbc_jReturnButton.gridy = 2;
		jPanelForBg.add(jReturnButton, gbc_jReturnButton);

		imgToIcon = new ImageIcon(imageIcon) {
			private static final long serialVersionUID = -6144591747476560493L;

			public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
				super.paintIcon(c, g, x, y);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2d.setFont(new Font("Rockwell", Font.BOLD, 14));

				g2d.setPaint(Color.BLACK);
				g2d.drawString("Next", this.getIconWidth() / 2, this.getIconHeight() / 2 + 8);
			};
		};
		JButton jCreateButton = new JButton("");
		jCreateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jCreateButton.setContentAreaFilled(false);
		jCreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int place = data.getPlayerParty().getRandomFreePlace();

				switch (profession) {
				case 1:
					data.getPlayerParty().newPlayer(place, "John", new Warrior());
					break;
				case 2:
					data.getPlayerParty().newPlayer(place, "Billy", new Archer());
					break;
				case 5:
					data.getPlayerParty().newPlayer(place, "Bob", new Elementalist());
					break;
				default:
					break;
				}

				setVisible(false);
				jReturnButton.setVisible(true);

				data.getGUI().setEnabled(true);
				data.getGUI().getContentPane().setVisible(true);
				data.getBattle().getBattleScheduler().setPause(false);

				firstTime = false;
			}
		});
		jCreateButton.setFocusPainted(false);
		jCreateButton.setOpaque(false);
		jCreateButton.setBorderPainted(false);
		jCreateButton.setIcon(imgToIcon);
		GridBagConstraints gbc_jCreateButton = new GridBagConstraints();
		gbc_jCreateButton.insets = new Insets(0, 0, 5, 5);
		gbc_jCreateButton.gridx = 3;
		gbc_jCreateButton.gridy = 2;
		jPanelForBg.add(jCreateButton, gbc_jCreateButton);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLabelText("<h2><center>&nbsp Warrior</center></h2><br>"
				+ "<p>The Warrior is front class who is doing well with two-handed sword or with a shield. The Warrior can hit multiple enemies and weaken them. His anger can buff his and allies armor.</p>");
		setVisible(true);
	}

	private void setLabelText(String string) {
		StringBuilder text = new StringBuilder();

		text.append(
				"<html><style type=\"text/css\">p{padding: 10px 10px 0px 20px; align:jusitfy; text-justift:inter-character}");

		text.append("<br>" + string);

		text.append("</html>");
		jTextLbl.setText(text.toString());
	}

	public void showAgain() {
		data.getGUI().setEnabled(false);
		data.getGUI().getContentPane().setVisible(false);

		data.getBattle().getBattleScheduler().setPause(true);

		setVisible(true);
	}
}
