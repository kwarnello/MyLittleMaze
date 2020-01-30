package com.warluscampsite.mylittlemaze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.warluscampsite.mylittlemaze.battle.BattleNumber;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.monsters.Monster;
import com.warluscampsite.mylittlemaze.playerteam.Player;

public class BattlePanel extends JPanel {

	private static final long serialVersionUID = 4139742684020826055L;

	Data data;
	List<BattleNumber> numbersController;

	Image image;

	HashMap<Integer, CharacterMiniature> monsterMiniatures;
	HashMap<Integer, CharacterMiniature> playerMiniatures;

	private JPanel jMonster1Panel;
	private JPanel jMonster4Panel;
	private JPanel jMonster5Panel;
	private JPanel jMonster6Panel;
	private JPanel jMonster2Panel;
	private JPanel jMonster3Panel;
	private JPanel jPlayer1Panel;
	private JPanel jPlayer2Panel;
	private JPanel jPlayer3Panel;
	private JPanel jPlayer4Panel;
	private JPanel jPlayer5Panel;
	private JPanel jPlayer6Panel;

	/**
	 * Create the panel.
	 * 
	 * @param data
	 */
	public BattlePanel(Data data) {
		setOpaque(false);
		this.data = data;
		this.numbersController = data.getBattle().getBattleAttackControllers().getBattleNumbers().getNumberList();

		image = null;
		try {
			File pathToFile = new File("res/GUI/bgForCharacterMiniatures.png");
			image = ImageIO.read(pathToFile);
			image = image.getScaledInstance(215, 105, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 30, 220, 22, 220, 22, 220, 5, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 110, 110, 20, 110, 110, 5, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		jMonster4Panel = new JPanel() {
			private static final long serialVersionUID = -3611919038648664763L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		jMonster4Panel.setBorder(null);
		jMonster4Panel.setBackground(Color.WHITE);
		jMonster4Panel.setLayout(new BoxLayout(jMonster4Panel, BoxLayout.X_AXIS));
		GridBagConstraints gbc_jMonster4Panel = new GridBagConstraints();
		gbc_jMonster4Panel.fill = GridBagConstraints.BOTH;
		gbc_jMonster4Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jMonster4Panel.gridx = 1;
		gbc_jMonster4Panel.gridy = 1;
		add(jMonster4Panel, gbc_jMonster4Panel);

		jMonster5Panel = new JPanel() {
			private static final long serialVersionUID = -1369012434302915266L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jMonster5Panel.setBorder(null);
		jMonster5Panel.setBackground(Color.WHITE);
		jMonster5Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jMonster5Panel = new GridBagConstraints();
		gbc_jMonster5Panel.fill = GridBagConstraints.BOTH;
		gbc_jMonster5Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jMonster5Panel.gridx = 3;
		gbc_jMonster5Panel.gridy = 1;
		add(jMonster5Panel, gbc_jMonster5Panel);

		jMonster6Panel = new JPanel() {
			private static final long serialVersionUID = 952217743501493822L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jMonster6Panel.setBorder(null);
		jMonster6Panel.setBackground(Color.WHITE);
		jMonster6Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jMonster6Panel = new GridBagConstraints();
		gbc_jMonster6Panel.fill = GridBagConstraints.BOTH;
		gbc_jMonster6Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jMonster6Panel.gridx = 5;
		gbc_jMonster6Panel.gridy = 1;
		add(jMonster6Panel, gbc_jMonster6Panel);

		jMonster1Panel = new JPanel() {
			private static final long serialVersionUID = 4241445981412879987L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jMonster1Panel.setBorder(null);
		jMonster1Panel.setBackground(Color.WHITE);
		jMonster1Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jMonster1Panel = new GridBagConstraints();
		gbc_jMonster1Panel.fill = GridBagConstraints.BOTH;
		gbc_jMonster1Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jMonster1Panel.gridx = 1;
		gbc_jMonster1Panel.gridy = 2;
		add(jMonster1Panel, gbc_jMonster1Panel);

		jMonster2Panel = new JPanel() {
			private static final long serialVersionUID = 2315778011393553518L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jMonster2Panel.setBorder(null);
		jMonster2Panel.setBackground(Color.WHITE);
		jMonster2Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jMonster2Panel = new GridBagConstraints();
		gbc_jMonster2Panel.fill = GridBagConstraints.BOTH;
		gbc_jMonster2Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jMonster2Panel.gridx = 3;
		gbc_jMonster2Panel.gridy = 2;
		add(jMonster2Panel, gbc_jMonster2Panel);

		jMonster3Panel = new JPanel() {
			private static final long serialVersionUID = 1151922126765595887L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jMonster3Panel.setBorder(null);
		jMonster3Panel.setBackground(Color.WHITE);
		jMonster3Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jMonster3Panel = new GridBagConstraints();
		gbc_jMonster3Panel.fill = GridBagConstraints.BOTH;
		gbc_jMonster3Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jMonster3Panel.gridx = 5;
		gbc_jMonster3Panel.gridy = 2;
		add(jMonster3Panel, gbc_jMonster3Panel);

		jPlayer1Panel = new JPanel() {
			private static final long serialVersionUID = 8340005912687765127L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jPlayer1Panel.setBorder(null);
		jPlayer1Panel.setBackground(Color.WHITE);
		jPlayer1Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jPlayer1Panel = new GridBagConstraints();
		gbc_jPlayer1Panel.fill = GridBagConstraints.BOTH;
		gbc_jPlayer1Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jPlayer1Panel.gridx = 1;
		gbc_jPlayer1Panel.gridy = 4;
		add(jPlayer1Panel, gbc_jPlayer1Panel);

		jPlayer2Panel = new JPanel() {
			private static final long serialVersionUID = 2893833664833822932L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jPlayer2Panel.setBorder(null);
		jPlayer2Panel.setBackground(Color.WHITE);
		jPlayer2Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jPlayer2Panel = new GridBagConstraints();
		gbc_jPlayer2Panel.fill = GridBagConstraints.BOTH;
		gbc_jPlayer2Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jPlayer2Panel.gridx = 3;
		gbc_jPlayer2Panel.gridy = 4;
		add(jPlayer2Panel, gbc_jPlayer2Panel);

		jPlayer3Panel = new JPanel() {
			private static final long serialVersionUID = -2154805721870615417L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jPlayer3Panel.setBorder(null);
		jPlayer3Panel.setBackground(Color.WHITE);
		jPlayer3Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jPlayer3Panel = new GridBagConstraints();
		gbc_jPlayer3Panel.fill = GridBagConstraints.BOTH;
		gbc_jPlayer3Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jPlayer3Panel.gridx = 5;
		gbc_jPlayer3Panel.gridy = 4;
		add(jPlayer3Panel, gbc_jPlayer3Panel);

		jPlayer4Panel = new JPanel() {
			private static final long serialVersionUID = -3019177318983139455L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jPlayer4Panel.setBorder(null);
		jPlayer4Panel.setBackground(Color.WHITE);
		jPlayer4Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jPlayer4Panel = new GridBagConstraints();
		gbc_jPlayer4Panel.fill = GridBagConstraints.BOTH;
		gbc_jPlayer4Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jPlayer4Panel.gridx = 1;
		gbc_jPlayer4Panel.gridy = 5;
		add(jPlayer4Panel, gbc_jPlayer4Panel);

		jPlayer5Panel = new JPanel() {
			private static final long serialVersionUID = -8362843427379175635L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jPlayer5Panel.setBorder(null);
		jPlayer5Panel.setBackground(Color.WHITE);
		jPlayer5Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jPlayer5Panel = new GridBagConstraints();
		gbc_jPlayer5Panel.fill = GridBagConstraints.BOTH;
		gbc_jPlayer5Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jPlayer5Panel.gridx = 3;
		gbc_jPlayer5Panel.gridy = 5;
		add(jPlayer5Panel, gbc_jPlayer5Panel);

		jPlayer6Panel = new JPanel() {
			private static final long serialVersionUID = -1787231149755652770L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		;
		jPlayer6Panel.setBorder(null);
		jPlayer6Panel.setBackground(Color.WHITE);
		jPlayer6Panel.setLayout(new BorderLayout(0, 0));
		GridBagConstraints gbc_jPlayer6Panel = new GridBagConstraints();
		gbc_jPlayer6Panel.insets = new Insets(0, 0, 5, 5);
		gbc_jPlayer6Panel.fill = GridBagConstraints.BOTH;
		gbc_jPlayer6Panel.gridx = 5;
		gbc_jPlayer6Panel.gridy = 5;
		add(jPlayer6Panel, gbc_jPlayer6Panel);

		initializeMaps();
	}

	private void initializeMaps() {
		monsterMiniatures = new HashMap<>();
		playerMiniatures = new HashMap<>();

	}

	private void addMonsters() {
		monsterMiniatures.clear();

		jMonster1Panel.removeAll();
		jMonster2Panel.removeAll();
		jMonster3Panel.removeAll();
		jMonster4Panel.removeAll();
		jMonster5Panel.removeAll();
		jMonster6Panel.removeAll();

		for (Entry<Integer, Monster> monster : data.getMaze().getMonstersParty().getPlaceMap().entrySet()) {
			monsterMiniatures.put(monster.getKey(), new CharacterMiniature(monster.getValue()));
		}

	}

	void initialize() {
		initializePlayerMiniatures();
		initializeMonsterMiniatures();
		revalidate();
		repaint();
	}

	private void addPlayerCharacter() {
		playerMiniatures.clear();

		for (Entry<Integer, Player> player : data.getPlayerParty().getCharacterMap().entrySet()) {
			if (player.getValue() != null)
				playerMiniatures.put(player.getKey(), new CharacterMiniature(player.getValue()));
		}
	}

	public void initializePlayerMiniatures() {
		addPlayerCharacter();

		for (Entry<Integer, CharacterMiniature> player : playerMiniatures.entrySet()) {
			if (player.getValue() != null) {
				switch (player.getKey()) {
				case 1:
					if (jPlayer1Panel.getComponentCount() == 1)
						jPlayer1Panel.removeAll();

					jPlayer1Panel.add(player.getValue());
					break;
				case 2:
					if (jPlayer2Panel.getComponentCount() == 1)
						jPlayer2Panel.removeAll();

					jPlayer2Panel.add(player.getValue());
					break;
				case 3:
					if (jPlayer3Panel.getComponentCount() == 1)
						jPlayer3Panel.removeAll();

					jPlayer3Panel.add(player.getValue());
					break;
				case 4:
					if (jPlayer4Panel.getComponentCount() == 1)
						jPlayer4Panel.removeAll();

					jPlayer4Panel.add(player.getValue());
					break;
				case 5:
					if (jPlayer5Panel.getComponentCount() == 1)
						jPlayer5Panel.removeAll();

					jPlayer5Panel.add(player.getValue());
					break;
				case 6:
					if (jPlayer6Panel.getComponentCount() == 1)
						jPlayer6Panel.removeAll();

					jPlayer6Panel.add(player.getValue());
					break;

				default:
					break;
				}
			}
		}
	}

	public void removeFromPlace(int place) {
		switch (place) {
		case 1:
			jPlayer1Panel.removeAll();
			break;
		case 2:
			jPlayer2Panel.removeAll();
			break;
		case 3:
			jPlayer3Panel.removeAll();
			break;
		case 4:
			jPlayer4Panel.removeAll();
			break;
		case 5:
			jPlayer5Panel.removeAll();
			break;
		case 6:
			jPlayer6Panel.removeAll();
			break;

		default:
			break;
		}
	}

	public void initializeMonsterMiniatures() {
		addMonsters();

		for (Entry<Integer, CharacterMiniature> monster : monsterMiniatures.entrySet()) {
			if (monster.getValue() != null) {
				switch (monster.getKey()) {
				case 1:
					jMonster1Panel.add(monster.getValue());
					break;
				case 2:
					jMonster2Panel.add(monster.getValue());
					break;
				case 3:
					jMonster3Panel.add(monster.getValue());
					break;
				case 4:
					jMonster4Panel.add(monster.getValue());
					break;
				case 5:
					jMonster5Panel.add(monster.getValue());
					break;
				case 6:
					jMonster6Panel.add(monster.getValue());
					break;
				default:
					break;
				}
			}
		}
	}

	public void changePlayerPlace(int oldPlace, int newPlace) {
		playerMiniatures.remove(oldPlace);
		removeFromPlace(oldPlace);

		playerMiniatures.remove(newPlace);
		removeFromPlace(newPlace);

		data.getPlayerParty().replecePlayers(oldPlace, newPlace);

		initializePlayerMiniatures();
		revalidate();
		repaint();
	}

	public void refresh() {
		monsterMiniatures.forEach((k, v) -> v.refresh());
		playerMiniatures.forEach((k, v) -> v.refresh());
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setFont(BattleNumber.getFont());

		for (BattleNumber battleNumber : numbersController) {
			g.setColor(battleNumber.getColor());
			g.drawString(battleNumber.getText(), (int) battleNumber.getX(), (int) battleNumber.getY());
		}
	}
}
