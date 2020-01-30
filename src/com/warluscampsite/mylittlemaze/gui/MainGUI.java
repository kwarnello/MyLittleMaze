package com.warluscampsite.mylittlemaze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ToolTipManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import com.warluscampsite.mylittlemaze.camp.CampPanel;
import com.warluscampsite.mylittlemaze.camp.CraftPanel;
import com.warluscampsite.mylittlemaze.controllers.TextAreaController;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.gui.character.CharacterPanel;
import com.warluscampsite.mylittlemaze.playerteam.Player;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 4475681753630701593L;

	private Data data;

	private Image bgImageCharacter;
	private Image bgImage;
	private Image image;
	private ImageIcon icon;

	private HashMap<Player, CharacterPanel> characterPanels;

	private BattlePanel battlePanel;
	private MazePanel mazePanel;
	private ItemsPanel itemsPanel;
	private OptionsPanel optionsPanel;
	private CraftPanel craftPanel;
	private AchievmentPanel achievmentPanel;

	private JPanel contentPane;
	private JPanel jBattlePanel;
	private JTabbedPane tabbedPane;
	private JTabbedPane jCharacterTabbed;
	private JPanel panel;
	private JTextArea jTextArea;
	private JScrollPane scrollPane;
	private JPanel jAchievmentPanel;
	private JPanel jOptionsPanel;
	private JPanel jMazePanel;
	private JPanel jItemsPanel;
	private JPanel jCampPanel;
	private JPanel jCraftPanel;

	/**
	 * Create the frame.width=464,height=675]
	 * 
	 * @param data
	 */
	public MainGUI(Data data) {
		try {
			bgImage = ImageIO.read(new File("res/GUI/mainBackground.png"));
			bgImageCharacter = ImageIO.read(new File("res/GUI/characterBg.png")).getScaledInstance(464, 675,
					Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setUndecorated(true);
		setResizable(false);
		this.data = data;

		characterPanels = new HashMap<>();

		ToolTipManager.sharedInstance().setInitialDelay(50);
		setBounds(50, 50, 1300, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel() {
			private static final long serialVersionUID = -112497963227372272L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bgImage, 0, 0, null);
			}
		};
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 10, 400, 0, 10, 0 };
		gbl_contentPane.rowHeights = new int[] { 10, 300, 150, 10, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		jBattlePanel = new JPanel();
		jBattlePanel.setOpaque(false);
		GridBagConstraints gbc_jBattlePanel = new GridBagConstraints();
		gbc_jBattlePanel.insets = new Insets(0, 0, 5, 5);
		gbc_jBattlePanel.fill = GridBagConstraints.BOTH;
		gbc_jBattlePanel.gridx = 1;
		gbc_jBattlePanel.gridy = 1;
		contentPane.add(jBattlePanel, gbc_jBattlePanel);
		jBattlePanel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 3) {
					craftPanel.doThingsWithSelectedPlayer();
					craftPanel.doThingsWithSelectedItem();
				}
			}
		});
		tabbedPane.setOpaque(false);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridheight = 2;
		gbc_tabbedPane.gridx = 2;
		gbc_tabbedPane.gridy = 1;
		contentPane.add(tabbedPane, gbc_tabbedPane);

		try {
			image = ImageIO.read(new File("res/GUI/tabbedPane/char.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JPanel panelJustForCharacterTabbed = new JPanel() {
			private static final long serialVersionUID = -5289770820861187118L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bgImageCharacter, 0, 0, null);
			}
		};
		panelJustForCharacterTabbed.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tabbedPane.addTab("", icon, panelJustForCharacterTabbed, null);

		jCharacterTabbed = new JTabbedPane(JTabbedPane.TOP);
		jCharacterTabbed.setBorder(null);
		jCharacterTabbed.setBackground(Color.WHITE);
		jCharacterTabbed.setOpaque(false);
		panelJustForCharacterTabbed.add(jCharacterTabbed);

		try {
			image = ImageIO.read(new File("res/GUI/tabbedPane/camp.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jCampPanel = new CampPanel(data);
		tabbedPane.addTab("", icon, jCampPanel, null);

		try {
			image = ImageIO.read(new File("res/GUI/tabbedPane/items.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jItemsPanel = new JPanel();
		tabbedPane.addTab("", icon, jItemsPanel, null);
		jItemsPanel.setLayout(new BorderLayout(0, 0));

		try {
			image = ImageIO.read(new File("res/GUI/tabbedPane/craft.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jCraftPanel = new JPanel();
		tabbedPane.addTab("", icon, jCraftPanel, null);
		jCraftPanel.setLayout(new BorderLayout(0, 0));

		try {
			image = ImageIO.read(new File("res/GUI/tabbedPane/maze.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jMazePanel = new JPanel();
		jMazePanel.setBackground(Color.GREEN);
		tabbedPane.addTab("", icon, jMazePanel, null);
		jMazePanel.setLayout(new BorderLayout(0, 0));

		try {
			image = ImageIO.read(new File("res/GUI/tabbedPane/achiv.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jAchievmentPanel = new JPanel();
		tabbedPane.addTab("", icon, jAchievmentPanel, null);
		jAchievmentPanel.setLayout(new BorderLayout(0, 0));

		try {
			image = ImageIO.read(new File("res/GUI/tabbedPane/options.png"));
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jOptionsPanel = new JPanel();
		jOptionsPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("", icon, jOptionsPanel, null);
		BorderLayout gbl_jOptionsPanel = new BorderLayout();
		jOptionsPanel.setLayout(gbl_jOptionsPanel);

		optionsPanel = new OptionsPanel();
		jOptionsPanel.add(optionsPanel);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(5, 5));

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setAutoscrolls(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);

		jTextArea = new JTextArea();
		jTextArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
		jTextArea.setBackground(Color.WHITE);
		jTextArea.setWrapStyleWord(true);
		jTextArea.setEditable(false);
		scrollPane.setViewportView(jTextArea);

		DefaultCaret caret = (DefaultCaret) jTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		getContentPane().setVisible(false);
		this.setVisible(true);

		initializeBeforeGameDataLoaded();

	}

	private void initializeBeforeGameDataLoaded() {
		mazePanel = new MazePanel(data);
		itemsPanel = new ItemsPanel(data);
		battlePanel = new BattlePanel(data);
		craftPanel = new CraftPanel(data);
		achievmentPanel = new AchievmentPanel(data);

		jMazePanel.add(mazePanel);
		jItemsPanel.add(itemsPanel);
		jBattlePanel.add(battlePanel);
		jCraftPanel.add(craftPanel);
		jAchievmentPanel.add(achievmentPanel);

		optionsPanel.setOptionsStuff();

		TextAreaController.setTextAreaController(getTextArea());
	}

	public void addNewCharacterElements(Player player) {
		characterPanels.put(player, new CharacterPanel(player));
		jCharacterTabbed.addTab(player.getName(), getCharacterPanel(player));
		itemsPanel.addCharacter(player.getName());
		craftPanel.initializeAfterNewPlayer(player);

		battlePanel.initialize();
	}

	public void refresh() {
		characterPanels.forEach((v, k) -> k.refresh());
		battlePanel.refresh();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bgImage, 0, 0, null);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public JTextArea getTextArea() {
		return jTextArea;
	}

	public BattlePanel getBattlePanel() {
		return battlePanel;
	}

	public CharacterPanel getCharacterPanel(Player character) {
		return characterPanels.get(character);
	}

	public MazePanel getMazePanel() {
		return mazePanel;
	}

	public ItemsPanel getItemsPanel() {
		return itemsPanel;
	}

}