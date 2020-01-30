package com.warluscampsite.mylittlemaze.camp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class CraftPanel extends JPanel {

	private static final long serialVersionUID = -7602986026847566112L;

	final int WIDTH_IMAGES = 50;
	final int HEIGHT_IMAGES = 50;
	final int WIDTH_ITEM = 80;
	final int HEIGHT_ITEM = 80;

	Crafting crafting;

	Image skull, eye, emerald, stormStone, dragonStone;
	ImageIcon skullIcon, eyeIcon, emeraldIcon, stormStoneIcon, dragonStoneIcon;

	Image item;
	ImageIcon itemIcon;

	Item selectedItem;
	Player selectedPlayer;

	Data data;
	private JComboBox<Player> playersComboBox;
	private JLabel lblForItemIcon;
	private JComboBox<Item> itemListComboBox;
	private JLabel skullLbl;
	private JLabel eyeLbl;
	private JLabel emeraldLbl;
	private JLabel stormStoneLbl;
	private JLabel dragonStoneLbl;
	private JLabel skullQuantityLbl;
	private JLabel eyeQuantityLbl;
	private JLabel emeraldQuantityLbl;
	private JLabel stormStoneQuantityLbl;
	private JLabel dragonStoneQuantityLbl;
	private JLabel chancesPrefixRerollLabel;
	private JButton rerollPrefixButton;
	private JLabel prefixSkullCostLbl;
	private JLabel prefixEyeCostLbl;
	private JLabel prefixEmeraldCostLbl;
	private JLabel suffixSkullCostLbl;
	private JLabel suffixEyeCostLbl;
	private JLabel suffixEmeraldCostLbl;
	private JLabel rarityUpgradeLabel;
	private JLabel sufixRerollLabel;
	private JButton rarityUpgradeButton;
	private JLabel raritySkullCostLbl;
	private JLabel rarityEyeCostLbl;
	private JLabel rarityDragonStoneCostLbl;
	private JButton rerollSufixButton;

	/**
	 * Create the panel.
	 * 
	 * @param data
	 */
	@SuppressWarnings("serial")
	public CraftPanel(Data data) {
		this.data = data;

		try {
			skull = ImageIO.read(new File("res/camp/skull.png")).getScaledInstance(WIDTH_IMAGES, HEIGHT_IMAGES,
					Image.SCALE_SMOOTH);
			eye = ImageIO.read(new File("res/camp/eye.png")).getScaledInstance(WIDTH_IMAGES, HEIGHT_IMAGES,
					Image.SCALE_SMOOTH);
			emerald = ImageIO.read(new File("res/camp/emerald.png")).getScaledInstance(WIDTH_IMAGES, HEIGHT_IMAGES,
					Image.SCALE_SMOOTH);
			stormStone = ImageIO.read(new File("res/camp/stormStone.png")).getScaledInstance(WIDTH_IMAGES,
					HEIGHT_IMAGES, Image.SCALE_SMOOTH);
			dragonStone = ImageIO.read(new File("res/camp/dragonStone.png")).getScaledInstance(WIDTH_IMAGES,
					HEIGHT_IMAGES, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		skullIcon = new ImageIcon(skull);
		eyeIcon = new ImageIcon(eye);
		emeraldIcon = new ImageIcon(emerald);
		stormStoneIcon = new ImageIcon(stormStone);
		dragonStoneIcon = new ImageIcon(dragonStone);

		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 8, 0, 5, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 15, 300, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panelUnderItem = new JPanel();
		panelUnderItem.setOpaque(false);
		panelUnderItem.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_panelUnderItem = new GridBagConstraints();
		gbc_panelUnderItem.insets = new Insets(0, 0, 0, 0);
		gbc_panelUnderItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelUnderItem.gridx = 1;
		gbc_panelUnderItem.gridy = 1;
		add(panelUnderItem, gbc_panelUnderItem);
		GridBagLayout gbl_panelUnderItem = new GridBagLayout();
		gbl_panelUnderItem.columnWidths = new int[] { 45, 89, 30, 138, 25, 0 };
		gbl_panelUnderItem.rowHeights = new int[] { 15, 45, 44, 15, 0 };
		gbl_panelUnderItem.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelUnderItem.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelUnderItem.setLayout(gbl_panelUnderItem);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panelUnderItem.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		lblForItemIcon = new JLabel("") {
			private static final long serialVersionUID = 1271153410366630149L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		panel_2.add(lblForItemIcon, BorderLayout.CENTER);

		itemListComboBox = new JComboBox<Item>();
		itemListComboBox.setRenderer(new DefaultListCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6241941777079866930L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof Item) {
					Item item = (Item) value;
					setForeground(item.getItemRarity().getColor());
					setText(item.getName());
				}
				return this;
			}
		});
		itemListComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doThingsWithSelectedItem();
			}
		});
		GridBagConstraints gbc_itemListComboBox = new GridBagConstraints();
		gbc_itemListComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_itemListComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemListComboBox.gridx = 3;
		gbc_itemListComboBox.gridy = 2;
		panelUnderItem.add(itemListComboBox, gbc_itemListComboBox);

		playersComboBox = new JComboBox<Player>();
		playersComboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof Player) {
					Player player = (Player) value;
					setText(player.getName());
				}
				return this;
			}
		});
		playersComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doThingsWithSelectedPlayer();
			}
		});
		GridBagConstraints gbc_playersComboBox = new GridBagConstraints();
		gbc_playersComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_playersComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_playersComboBox.gridx = 3;
		gbc_playersComboBox.gridy = 1;
		panelUnderItem.add(playersComboBox, gbc_playersComboBox);

		JPanel mainLowerPanel = new JPanel();
		mainLowerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_mainLowerPanel = new GridBagConstraints();
		gbc_mainLowerPanel.insets = new Insets(0, 0, 5, 5);
		gbc_mainLowerPanel.fill = GridBagConstraints.BOTH;
		gbc_mainLowerPanel.gridx = 1;
		gbc_mainLowerPanel.gridy = 3;
		add(mainLowerPanel, gbc_mainLowerPanel);
		GridBagLayout gbl_mainLowerPanel = new GridBagLayout();
		gbl_mainLowerPanel.columnWidths = new int[] { 10, 0, 10, 0 };
		gbl_mainLowerPanel.rowHeights = new int[] { 5, 0, 120, 120, 120, 5, 0 };
		gbl_mainLowerPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_mainLowerPanel.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		mainLowerPanel.setLayout(gbl_mainLowerPanel);

		JPanel panelUnderCraftingComponents = new JPanel();
		panelUnderCraftingComponents.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panelUnderCraftingComponents = new GridBagConstraints();
		gbc_panelUnderCraftingComponents.insets = new Insets(0, 0, 5, 5);
		gbc_panelUnderCraftingComponents.fill = GridBagConstraints.BOTH;
		gbc_panelUnderCraftingComponents.gridx = 1;
		gbc_panelUnderCraftingComponents.gridy = 1;
		mainLowerPanel.add(panelUnderCraftingComponents, gbc_panelUnderCraftingComponents);
		GridBagLayout gbl_panelUnderCraftingComponents = new GridBagLayout();
		gbl_panelUnderCraftingComponents.columnWidths = new int[] { 0, 60, 0, 60, 0, 60, 0, 60, 0, 60, 0, 0 };
		gbl_panelUnderCraftingComponents.rowHeights = new int[] { 2, 60, 25, 2, 0 };
		gbl_panelUnderCraftingComponents.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0,
				0.0, 1.0, Double.MIN_VALUE };
		gbl_panelUnderCraftingComponents.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelUnderCraftingComponents.setLayout(gbl_panelUnderCraftingComponents);

		skullLbl = new JLabel(skullIcon);
		GridBagConstraints gbc_skullLbl = new GridBagConstraints();
		gbc_skullLbl.fill = GridBagConstraints.BOTH;
		gbc_skullLbl.insets = new Insets(0, 0, 5, 5);
		gbc_skullLbl.gridx = 1;
		gbc_skullLbl.gridy = 1;
		panelUnderCraftingComponents.add(skullLbl, gbc_skullLbl);

		eyeLbl = new JLabel(eyeIcon);
		GridBagConstraints gbc_eyeLbl = new GridBagConstraints();
		gbc_eyeLbl.fill = GridBagConstraints.BOTH;
		gbc_eyeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_eyeLbl.gridx = 3;
		gbc_eyeLbl.gridy = 1;
		panelUnderCraftingComponents.add(eyeLbl, gbc_eyeLbl);

		emeraldLbl = new JLabel(emeraldIcon);
		GridBagConstraints gbc_emeraldLbl = new GridBagConstraints();
		gbc_emeraldLbl.fill = GridBagConstraints.BOTH;
		gbc_emeraldLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emeraldLbl.gridx = 5;
		gbc_emeraldLbl.gridy = 1;
		panelUnderCraftingComponents.add(emeraldLbl, gbc_emeraldLbl);

		stormStoneLbl = new JLabel(stormStoneIcon);
		GridBagConstraints gbc_stormStoneLbl = new GridBagConstraints();
		gbc_stormStoneLbl.fill = GridBagConstraints.BOTH;
		gbc_stormStoneLbl.insets = new Insets(0, 0, 5, 5);
		gbc_stormStoneLbl.gridx = 7;
		gbc_stormStoneLbl.gridy = 1;
		panelUnderCraftingComponents.add(stormStoneLbl, gbc_stormStoneLbl);

		dragonStoneLbl = new JLabel(dragonStoneIcon);
		GridBagConstraints gbc_dragonStoneLbl = new GridBagConstraints();
		gbc_dragonStoneLbl.fill = GridBagConstraints.BOTH;
		gbc_dragonStoneLbl.insets = new Insets(0, 0, 5, 5);
		gbc_dragonStoneLbl.gridx = 9;
		gbc_dragonStoneLbl.gridy = 1;
		panelUnderCraftingComponents.add(dragonStoneLbl, gbc_dragonStoneLbl);

		skullQuantityLbl = new JLabel("0");
		GridBagConstraints gbc_skullQuantityLbl = new GridBagConstraints();
		gbc_skullQuantityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_skullQuantityLbl.gridx = 1;
		gbc_skullQuantityLbl.gridy = 2;
		panelUnderCraftingComponents.add(skullQuantityLbl, gbc_skullQuantityLbl);

		eyeQuantityLbl = new JLabel("0");
		GridBagConstraints gbc_eyeQuantityLbl = new GridBagConstraints();
		gbc_eyeQuantityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_eyeQuantityLbl.gridx = 3;
		gbc_eyeQuantityLbl.gridy = 2;
		panelUnderCraftingComponents.add(eyeQuantityLbl, gbc_eyeQuantityLbl);

		emeraldQuantityLbl = new JLabel("0");
		GridBagConstraints gbc_emeraldQuantityLbl = new GridBagConstraints();
		gbc_emeraldQuantityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emeraldQuantityLbl.gridx = 5;
		gbc_emeraldQuantityLbl.gridy = 2;
		panelUnderCraftingComponents.add(emeraldQuantityLbl, gbc_emeraldQuantityLbl);

		stormStoneQuantityLbl = new JLabel("0");
		GridBagConstraints gbc_stormStoneQuantityLbl = new GridBagConstraints();
		gbc_stormStoneQuantityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_stormStoneQuantityLbl.gridx = 7;
		gbc_stormStoneQuantityLbl.gridy = 2;
		panelUnderCraftingComponents.add(stormStoneQuantityLbl, gbc_stormStoneQuantityLbl);

		dragonStoneQuantityLbl = new JLabel("0");
		GridBagConstraints gbc_dragonStoneQuantityLbl = new GridBagConstraints();
		gbc_dragonStoneQuantityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_dragonStoneQuantityLbl.gridx = 9;
		gbc_dragonStoneQuantityLbl.gridy = 2;
		panelUnderCraftingComponents.add(dragonStoneQuantityLbl, gbc_dragonStoneQuantityLbl);

		JPanel panelUnderPrefixUpgrade = new JPanel();
		panelUnderPrefixUpgrade.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panelUnderPrefixUpgrade = new GridBagConstraints();
		gbc_panelUnderPrefixUpgrade.insets = new Insets(0, 0, 5, 5);
		gbc_panelUnderPrefixUpgrade.fill = GridBagConstraints.BOTH;
		gbc_panelUnderPrefixUpgrade.gridx = 1;
		gbc_panelUnderPrefixUpgrade.gridy = 2;
		mainLowerPanel.add(panelUnderPrefixUpgrade, gbc_panelUnderPrefixUpgrade);
		GridBagLayout gbl_panelUnderPrefixUpgrade = new GridBagLayout();
		gbl_panelUnderPrefixUpgrade.columnWidths = new int[] { 8, 40, 10, 40, 10, 40, 10, 100, 5, 93, 5, 0 };
		gbl_panelUnderPrefixUpgrade.rowHeights = new int[] { 2, 0, 0, 0, 40, 0, 2, 0 };
		gbl_panelUnderPrefixUpgrade.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
		gbl_panelUnderPrefixUpgrade.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panelUnderPrefixUpgrade.setLayout(gbl_panelUnderPrefixUpgrade);

		JLabel prefixUpgradeLabel = new JLabel("Reroll prefix of the item");
		prefixUpgradeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_prefixUpgradeLabel = new GridBagConstraints();
		gbc_prefixUpgradeLabel.anchor = GridBagConstraints.WEST;
		gbc_prefixUpgradeLabel.gridwidth = 9;
		gbc_prefixUpgradeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_prefixUpgradeLabel.fill = GridBagConstraints.VERTICAL;
		gbc_prefixUpgradeLabel.gridx = 1;
		gbc_prefixUpgradeLabel.gridy = 2;
		panelUnderPrefixUpgrade.add(prefixUpgradeLabel, gbc_prefixUpgradeLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 7;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 3;
		panelUnderPrefixUpgrade.add(separator, gbc_separator);

		chancesPrefixRerollLabel = new JLabel("<html>\r\nReroll - 0% <br>\r\nNothing - 0%<br>\r\nBroken - 0%</html>");
		GridBagConstraints gbc_chancesPrefixRerollLabel = new GridBagConstraints();
		gbc_chancesPrefixRerollLabel.fill = GridBagConstraints.BOTH;
		gbc_chancesPrefixRerollLabel.gridheight = 2;
		gbc_chancesPrefixRerollLabel.insets = new Insets(0, 0, 5, 5);
		gbc_chancesPrefixRerollLabel.gridx = 7;
		gbc_chancesPrefixRerollLabel.gridy = 4;
		panelUnderPrefixUpgrade.add(chancesPrefixRerollLabel, gbc_chancesPrefixRerollLabel);

		JLabel prefixSkullLbl = new JLabel(skullIcon);
		GridBagConstraints gbc_prefixSkullLbl = new GridBagConstraints();
		gbc_prefixSkullLbl.insets = new Insets(0, 0, 5, 5);
		gbc_prefixSkullLbl.gridx = 1;
		gbc_prefixSkullLbl.gridy = 4;
		panelUnderPrefixUpgrade.add(prefixSkullLbl, gbc_prefixSkullLbl);

		JLabel prefixEyeLbl = new JLabel(eyeIcon);
		GridBagConstraints gbc_prefixEyeLbl = new GridBagConstraints();
		gbc_prefixEyeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_prefixEyeLbl.gridx = 3;
		gbc_prefixEyeLbl.gridy = 4;
		panelUnderPrefixUpgrade.add(prefixEyeLbl, gbc_prefixEyeLbl);

		JLabel prefixEmeraldLbl = new JLabel(emeraldIcon);
		GridBagConstraints gbc_prefixEmeraldLbl = new GridBagConstraints();
		gbc_prefixEmeraldLbl.insets = new Insets(0, 0, 5, 5);
		gbc_prefixEmeraldLbl.gridx = 5;
		gbc_prefixEmeraldLbl.gridy = 4;
		panelUnderPrefixUpgrade.add(prefixEmeraldLbl, gbc_prefixEmeraldLbl);

		rerollPrefixButton = new JButton("Reroll");
		rerollPrefixButton.setEnabled(false);
		rerollPrefixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedItem != null) {
					rerollPrefixButton.setEnabled(false);
					crafting.rerollPrefix(selectedPlayer, selectedItem);

					int selectedIndex = itemListComboBox.getSelectedIndex();
					doThingsWithSelectedPlayer();
					doThingsWithSelectedItem(selectedIndex);

					rerollPrefixButton.setEnabled(true);
				}
			}
		});
		GridBagConstraints gbc_rerollPrefixButton = new GridBagConstraints();
		gbc_rerollPrefixButton.fill = GridBagConstraints.BOTH;
		gbc_rerollPrefixButton.gridheight = 2;
		gbc_rerollPrefixButton.insets = new Insets(0, 0, 5, 5);
		gbc_rerollPrefixButton.gridx = 9;
		gbc_rerollPrefixButton.gridy = 4;
		panelUnderPrefixUpgrade.add(rerollPrefixButton, gbc_rerollPrefixButton);

		prefixSkullCostLbl = new JLabel("-");
		GridBagConstraints gbc_prefixSkullCostLbl = new GridBagConstraints();
		gbc_prefixSkullCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_prefixSkullCostLbl.gridx = 1;
		gbc_prefixSkullCostLbl.gridy = 5;
		panelUnderPrefixUpgrade.add(prefixSkullCostLbl, gbc_prefixSkullCostLbl);

		prefixEyeCostLbl = new JLabel("-");
		GridBagConstraints gbc_prefixEyeCostLbl = new GridBagConstraints();
		gbc_prefixEyeCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_prefixEyeCostLbl.gridx = 3;
		gbc_prefixEyeCostLbl.gridy = 5;
		panelUnderPrefixUpgrade.add(prefixEyeCostLbl, gbc_prefixEyeCostLbl);

		prefixEmeraldCostLbl = new JLabel("-");
		GridBagConstraints gbc_prefixEmeraldCostLbl = new GridBagConstraints();
		gbc_prefixEmeraldCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_prefixEmeraldCostLbl.gridx = 5;
		gbc_prefixEmeraldCostLbl.gridy = 5;
		panelUnderPrefixUpgrade.add(prefixEmeraldCostLbl, gbc_prefixEmeraldCostLbl);

		JPanel panelUnderSufixRerollUpgrade = new JPanel();
		panelUnderSufixRerollUpgrade.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panelUnderSufixRerollUpgrade = new GridBagConstraints();
		gbc_panelUnderSufixRerollUpgrade.insets = new Insets(0, 0, 5, 5);
		gbc_panelUnderSufixRerollUpgrade.fill = GridBagConstraints.BOTH;
		gbc_panelUnderSufixRerollUpgrade.gridx = 1;
		gbc_panelUnderSufixRerollUpgrade.gridy = 3;
		mainLowerPanel.add(panelUnderSufixRerollUpgrade, gbc_panelUnderSufixRerollUpgrade);
		GridBagLayout gbl_panelUnderSufixRerollUpgrade = new GridBagLayout();
		gbl_panelUnderSufixRerollUpgrade.columnWidths = new int[] { 8, 40, 10, 40, 10, 40, 10, 100, 5, 93, 5, 0 };
		gbl_panelUnderSufixRerollUpgrade.rowHeights = new int[] { 2, 0, 0, 0, 40, 0, 2, 0 };
		gbl_panelUnderSufixRerollUpgrade.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		gbl_panelUnderSufixRerollUpgrade.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
		panelUnderSufixRerollUpgrade.setLayout(gbl_panelUnderSufixRerollUpgrade);

		JLabel lblRerollSuffixOf = new JLabel("Reroll suffix of the item");
		lblRerollSuffixOf.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblRerollSuffixOf = new GridBagConstraints();
		gbc_lblRerollSuffixOf.fill = GridBagConstraints.BOTH;
		gbc_lblRerollSuffixOf.gridwidth = 9;
		gbc_lblRerollSuffixOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblRerollSuffixOf.gridx = 1;
		gbc_lblRerollSuffixOf.gridy = 2;
		panelUnderSufixRerollUpgrade.add(lblRerollSuffixOf, gbc_lblRerollSuffixOf);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridwidth = 7;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 3;
		panelUnderSufixRerollUpgrade.add(separator_1, gbc_separator_1);

		JLabel suffixSkullLbl = new JLabel(skullIcon);
		GridBagConstraints gbc_suffixSkullLbl = new GridBagConstraints();
		gbc_suffixSkullLbl.insets = new Insets(0, 0, 5, 5);
		gbc_suffixSkullLbl.gridx = 1;
		gbc_suffixSkullLbl.gridy = 4;
		panelUnderSufixRerollUpgrade.add(suffixSkullLbl, gbc_suffixSkullLbl);

		JLabel suffixEyeLbl = new JLabel(eyeIcon);
		GridBagConstraints gbc_suffixEyeLbl = new GridBagConstraints();
		gbc_suffixEyeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_suffixEyeLbl.gridx = 3;
		gbc_suffixEyeLbl.gridy = 4;
		panelUnderSufixRerollUpgrade.add(suffixEyeLbl, gbc_suffixEyeLbl);

		JLabel suffixStormLbl = new JLabel(stormStoneIcon);
		GridBagConstraints gbc_suffixEmeraldLbl = new GridBagConstraints();
		gbc_suffixEmeraldLbl.insets = new Insets(0, 0, 5, 5);
		gbc_suffixEmeraldLbl.gridx = 5;
		gbc_suffixEmeraldLbl.gridy = 4;
		panelUnderSufixRerollUpgrade.add(suffixStormLbl, gbc_suffixEmeraldLbl);

		sufixRerollLabel = new JLabel("<html>\r\nReroll - 0% <br>\r\nNothing - 0%<br>\r\nBroken - 0%</html>");
		GridBagConstraints gbc_sufixRerollLabel = new GridBagConstraints();
		gbc_sufixRerollLabel.fill = GridBagConstraints.VERTICAL;
		gbc_sufixRerollLabel.gridheight = 2;
		gbc_sufixRerollLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sufixRerollLabel.gridx = 7;
		gbc_sufixRerollLabel.gridy = 4;
		panelUnderSufixRerollUpgrade.add(sufixRerollLabel, gbc_sufixRerollLabel);

		rerollSufixButton = new JButton("Reroll");
		rerollSufixButton.setEnabled(false);
		rerollSufixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedItem != null) {
					rerollPrefixButton.setEnabled(false);
					crafting.rerollSuffix(selectedPlayer, selectedItem);

					int selectedIndex = itemListComboBox.getSelectedIndex();
					doThingsWithSelectedPlayer();
					doThingsWithSelectedItem(selectedIndex);

					rerollPrefixButton.setEnabled(true);
				}
			}
		});
		GridBagConstraints gbc_rerollSufixButton = new GridBagConstraints();
		gbc_rerollSufixButton.fill = GridBagConstraints.BOTH;
		gbc_rerollSufixButton.gridheight = 2;
		gbc_rerollSufixButton.insets = new Insets(0, 0, 5, 5);
		gbc_rerollSufixButton.gridx = 9;
		gbc_rerollSufixButton.gridy = 4;
		panelUnderSufixRerollUpgrade.add(rerollSufixButton, gbc_rerollSufixButton);

		suffixSkullCostLbl = new JLabel("-");
		GridBagConstraints gbc_suffixSkullCostLbl = new GridBagConstraints();
		gbc_suffixSkullCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_suffixSkullCostLbl.gridx = 1;
		gbc_suffixSkullCostLbl.gridy = 5;
		panelUnderSufixRerollUpgrade.add(suffixSkullCostLbl, gbc_suffixSkullCostLbl);

		suffixEyeCostLbl = new JLabel("-");
		GridBagConstraints gbc_suffixEyeCostLbl = new GridBagConstraints();
		gbc_suffixEyeCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_suffixEyeCostLbl.gridx = 3;
		gbc_suffixEyeCostLbl.gridy = 5;
		panelUnderSufixRerollUpgrade.add(suffixEyeCostLbl, gbc_suffixEyeCostLbl);

		suffixEmeraldCostLbl = new JLabel("-");
		GridBagConstraints gbc_suffixEmeraldCostLbl = new GridBagConstraints();
		gbc_suffixEmeraldCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_suffixEmeraldCostLbl.gridx = 5;
		gbc_suffixEmeraldCostLbl.gridy = 5;
		panelUnderSufixRerollUpgrade.add(suffixEmeraldCostLbl, gbc_suffixEmeraldCostLbl);

		JPanel panelUnderRarityUpgrade = new JPanel();
		panelUnderRarityUpgrade.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panelUnderRarityUpgrade = new GridBagConstraints();
		gbc_panelUnderRarityUpgrade.insets = new Insets(0, 0, 5, 5);
		gbc_panelUnderRarityUpgrade.fill = GridBagConstraints.BOTH;
		gbc_panelUnderRarityUpgrade.gridx = 1;
		gbc_panelUnderRarityUpgrade.gridy = 4;
		mainLowerPanel.add(panelUnderRarityUpgrade, gbc_panelUnderRarityUpgrade);
		GridBagLayout gbl_panelUnderRarityUpgrade = new GridBagLayout();
		gbl_panelUnderRarityUpgrade.columnWidths = new int[] { 8, 40, 10, 40, 10, 40, 5, 100, 5, 0, 5, 0 };
		gbl_panelUnderRarityUpgrade.rowHeights = new int[] { 2, 0, 0, 40, 0, 2, 0 };
		gbl_panelUnderRarityUpgrade.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
		gbl_panelUnderRarityUpgrade.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		panelUnderRarityUpgrade.setLayout(gbl_panelUnderRarityUpgrade);

		JLabel lblUpgradeRarityOf = new JLabel("Upgrade rarity of the item");
		lblUpgradeRarityOf.setHorizontalAlignment(SwingConstants.LEFT);
		lblUpgradeRarityOf.setMaximumSize(new Dimension(240, 15));
		lblUpgradeRarityOf.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblUpgradeRarityOf = new GridBagConstraints();
		gbc_lblUpgradeRarityOf.fill = GridBagConstraints.VERTICAL;
		gbc_lblUpgradeRarityOf.anchor = GridBagConstraints.WEST;
		gbc_lblUpgradeRarityOf.gridwidth = 9;
		gbc_lblUpgradeRarityOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpgradeRarityOf.gridx = 1;
		gbc_lblUpgradeRarityOf.gridy = 1;
		panelUnderRarityUpgrade.add(lblUpgradeRarityOf, gbc_lblUpgradeRarityOf);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.BOTH;
		gbc_separator_2.gridwidth = 7;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 2;
		panelUnderRarityUpgrade.add(separator_2, gbc_separator_2);

		JLabel raritySkullLbl = new JLabel(skullIcon);
		GridBagConstraints gbc_raritySkullLbl = new GridBagConstraints();
		gbc_raritySkullLbl.insets = new Insets(0, 0, 5, 5);
		gbc_raritySkullLbl.gridx = 1;
		gbc_raritySkullLbl.gridy = 3;
		panelUnderRarityUpgrade.add(raritySkullLbl, gbc_raritySkullLbl);

		JLabel rarityEyeLbl = new JLabel(eyeIcon);
		GridBagConstraints gbc_rarityEyeLbl = new GridBagConstraints();
		gbc_rarityEyeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_rarityEyeLbl.gridx = 3;
		gbc_rarityEyeLbl.gridy = 3;
		panelUnderRarityUpgrade.add(rarityEyeLbl, gbc_rarityEyeLbl);

		JLabel rarityDragonStoneLbl = new JLabel(dragonStoneIcon);
		GridBagConstraints gbc_rarityDragonStoneLbl = new GridBagConstraints();
		gbc_rarityDragonStoneLbl.insets = new Insets(0, 0, 5, 5);
		gbc_rarityDragonStoneLbl.gridx = 5;
		gbc_rarityDragonStoneLbl.gridy = 3;
		panelUnderRarityUpgrade.add(rarityDragonStoneLbl, gbc_rarityDragonStoneLbl);

		rarityUpgradeLabel = new JLabel("<html>\r\nUpgrade - 0% <br>\r\nNothing - 0%<br>\r\nFail - 0%</html>");
		rarityUpgradeLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_rarityUpgradeLabel = new GridBagConstraints();
		gbc_rarityUpgradeLabel.fill = GridBagConstraints.VERTICAL;
		gbc_rarityUpgradeLabel.gridheight = 2;
		gbc_rarityUpgradeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_rarityUpgradeLabel.gridx = 7;
		gbc_rarityUpgradeLabel.gridy = 3;
		panelUnderRarityUpgrade.add(rarityUpgradeLabel, gbc_rarityUpgradeLabel);

		rarityUpgradeButton = new JButton("Upgrade");
		rarityUpgradeButton.setEnabled(false);
		rarityUpgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedItem != null) {
					rerollPrefixButton.setEnabled(false);
					crafting.upgradeRarity(selectedPlayer, selectedItem);

					int selectedIndex = itemListComboBox.getSelectedIndex();
					doThingsWithSelectedPlayer();
					doThingsWithSelectedItem(selectedIndex);

					rerollPrefixButton.setEnabled(true);
				}
			}
		});
		GridBagConstraints gbc_rarityUpgradeButton = new GridBagConstraints();
		gbc_rarityUpgradeButton.fill = GridBagConstraints.BOTH;
		gbc_rarityUpgradeButton.gridheight = 2;
		gbc_rarityUpgradeButton.insets = new Insets(0, 0, 5, 5);
		gbc_rarityUpgradeButton.gridx = 9;
		gbc_rarityUpgradeButton.gridy = 3;
		panelUnderRarityUpgrade.add(rarityUpgradeButton, gbc_rarityUpgradeButton);

		raritySkullCostLbl = new JLabel("-");
		GridBagConstraints gbc_raritySkullCostLbl = new GridBagConstraints();
		gbc_raritySkullCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_raritySkullCostLbl.gridx = 1;
		gbc_raritySkullCostLbl.gridy = 4;
		panelUnderRarityUpgrade.add(raritySkullCostLbl, gbc_raritySkullCostLbl);

		rarityEyeCostLbl = new JLabel("-");
		GridBagConstraints gbc_rarityEyeCostLbl = new GridBagConstraints();
		gbc_rarityEyeCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_rarityEyeCostLbl.gridx = 3;
		gbc_rarityEyeCostLbl.gridy = 4;
		panelUnderRarityUpgrade.add(rarityEyeCostLbl, gbc_rarityEyeCostLbl);

		rarityDragonStoneCostLbl = new JLabel("-");
		rarityDragonStoneCostLbl.setToolTipText("");
		GridBagConstraints gbc_rarityDragonStoneCostLbl = new GridBagConstraints();
		gbc_rarityDragonStoneCostLbl.insets = new Insets(0, 0, 5, 5);
		gbc_rarityDragonStoneCostLbl.gridx = 5;
		gbc_rarityDragonStoneCostLbl.gridy = 4;
		panelUnderRarityUpgrade.add(rarityDragonStoneCostLbl, gbc_rarityDragonStoneCostLbl);

		initializeFromData();
	}

	private void initializeFromData() {
		crafting = new Crafting(data.getPlayerParty().getCamp(), data.getMaze().getLoot());
	}

	public void doThingsWithSelectedPlayer() {
		lblForItemIcon.setIcon(null);

		selectedPlayer = (Player) playersComboBox.getSelectedItem();
		itemListComboBox.removeAllItems();

		for (Item eq : selectedPlayer.getEq().getEquipmentMap().values()) {
			if (eq != null)
				itemListComboBox.addItem(eq);
		}
	}

	public void doThingsWithSelectedItem() {
		if (itemListComboBox.getItemCount() != 0) {
			selectedItem = (Item) itemListComboBox.getSelectedItem();

			try {
				item = ImageIO.read(new File(selectedItem.getItemBase().getPath())).getScaledInstance(WIDTH_ITEM,
						HEIGHT_ITEM, Image.SCALE_SMOOTH);
				itemIcon = new ImageIcon(item);
			} catch (IOException e) {
				e.printStackTrace();
			}
			lblForItemIcon.setIcon(itemIcon);
			lblForItemIcon.setToolTipText(MyTooltipFormatter.formatTooltipForItem(selectedItem));

			data.getPlayerParty().getCamp().calculateAllProbabilitiesForCrafting(selectedItem);
			chancesPrefixRerollLabel.setText(data.getPlayerParty().getCamp().getTextForPrefix());
			sufixRerollLabel.setText(data.getPlayerParty().getCamp().getTextForSuffix());
			rarityUpgradeLabel.setText(data.getPlayerParty().getCamp().getTextForRarityUpgrade());
		}
	}

	public void doThingsWithSelectedItem(int selectedComboBoxNumber) {
		if (itemListComboBox.getItemCount() > selectedComboBoxNumber)
			itemListComboBox.setSelectedIndex(selectedComboBoxNumber);
		doThingsWithSelectedItem();
	}

	public void initializeAfterNewPlayer(Player player) {
		playersComboBox.addItem(player);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (data.getPlayerParty().getCamp().getSmithLevel() > 0) {
			rerollPrefixButton.setEnabled(true);
			rerollSufixButton.setEnabled(true);
			rarityUpgradeButton.setEnabled(true);
		}
	}
}
