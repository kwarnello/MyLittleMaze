package com.warluscampsite.mylittlemaze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;

public class ItemsPanel extends JPanel {
	private static final long serialVersionUID = -7410159245315275604L;

	Data data;

	Image bgImage;

	boolean thisIsInitialize;

	int forRaritySorting = 0, forItemTypeSorting = 0;

	private JComboBox<String> nameComboBox;
	private JComboBox<String> rarityItemComboBox;
	private JComboBox<String> itemTypesComboBox;
	private JComboBox<String> autoSellComboBox;

	private JPanel jUnderItemsListPanel;

	/**
	 * Create the panel.
	 */
	public ItemsPanel(Data data) {
		this.data = data;

		try {
			bgImage = ImageIO.read(new File("res/GUI/itemsBg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		thisIsInitialize = true;

		setLayout(new BorderLayout(0, 0));

		JPanel mainItemPanel = new JPanel();
		mainItemPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainItemPanel.setOpaque(false);
		mainItemPanel.setBackground(Color.WHITE);
		add(mainItemPanel);
		GridBagLayout gbl_mainItemPanel = new GridBagLayout();
		gbl_mainItemPanel.columnWidths = new int[] { 0, 0 };
		gbl_mainItemPanel.rowHeights = new int[] { 5, 0, 0 };
		gbl_mainItemPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_mainItemPanel.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		mainItemPanel.setLayout(gbl_mainItemPanel);

		jUnderItemsListPanel = new JPanel();
		jUnderItemsListPanel.setOpaque(false);
		jUnderItemsListPanel.setBorder(null);
		jUnderItemsListPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		GridBagLayout gbl_jUnderItemsListPanel = new GridBagLayout();
		jUnderItemsListPanel.setLayout(gbl_jUnderItemsListPanel);
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		jUnderItemsListPanel.add(panel_1, gbc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		scrollPane.setAutoscrolls(true);
		scrollPane.setViewportView(jUnderItemsListPanel);
		mainItemPanel.add(scrollPane, gbc_scrollPane);

		JPanel panelUnderBoxes = new JPanel();
		panelUnderBoxes.setOpaque(false);
		panelUnderBoxes.setBorder(new MatteBorder(3, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelUnderBoxes.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelUnderBoxes = new GridBagConstraints();
		gbc_panelUnderBoxes.fill = GridBagConstraints.BOTH;
		gbc_panelUnderBoxes.gridx = 0;
		gbc_panelUnderBoxes.gridy = 1;
		mainItemPanel.add(panelUnderBoxes, gbc_panelUnderBoxes);
		GridBagLayout gbl_panelUnderBoxes = new GridBagLayout();
		gbl_panelUnderBoxes.columnWidths = new int[] { 3, 0, 0, 3, 0 };
		gbl_panelUnderBoxes.rowHeights = new int[] { 3, 0, 6, 5, 0, 0, 5, 0 };
		gbl_panelUnderBoxes.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelUnderBoxes.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelUnderBoxes.setLayout(gbl_panelUnderBoxes);

		JLabel label = new JLabel("Filtering options");
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("SansSerif", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panelUnderBoxes.add(label, gbc_label);

		nameComboBox = new JComboBox<String>();
		nameComboBox.setBackground(Color.WHITE);
		GridBagConstraints gbc_nameComboBox = new GridBagConstraints();
		gbc_nameComboBox.fill = GridBagConstraints.BOTH;
		gbc_nameComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_nameComboBox.gridx = 2;
		gbc_nameComboBox.gridy = 1;
		panelUnderBoxes.add(nameComboBox, gbc_nameComboBox);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setSize(new Dimension(0, 5));
		separator.setPreferredSize(new Dimension(0, 5));
		separator.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		panelUnderBoxes.add(separator, gbc_separator);

		JLabel label_1 = new JLabel("Show item with rarity above");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 3;
		panelUnderBoxes.add(label_1, gbc_label_1);

		rarityItemComboBox = new JComboBox<String>();
		rarityItemComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (rarityItemComboBox.getSelectedItem().toString()) {
				case "Broken":
					forRaritySorting = 0;
					break;
				case "Normal":
					forRaritySorting = 1;
					break;
				case "Magic":
					forRaritySorting = 2;
					break;
				case "Rare":
					forRaritySorting = 3;
					break;
				case "Epic":
					forRaritySorting = 4;
					break;
				case "Unique":
					forRaritySorting = 5;
					break;
				}
				if (!thisIsInitialize)
					addItemsList();
			}
		});
		rarityItemComboBox.addItem("Broken");
		rarityItemComboBox.addItem("Normal");
		rarityItemComboBox.addItem("Magic");
		rarityItemComboBox.addItem("Rare");
		rarityItemComboBox.addItem("Epic");
		rarityItemComboBox.addItem("Unique");
		rarityItemComboBox.setBackground(Color.WHITE);
		GridBagConstraints gbc_rarityItemComboBox = new GridBagConstraints();
		gbc_rarityItemComboBox.fill = GridBagConstraints.BOTH;
		gbc_rarityItemComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_rarityItemComboBox.gridx = 2;
		gbc_rarityItemComboBox.gridy = 3;
		panelUnderBoxes.add(rarityItemComboBox, gbc_rarityItemComboBox);

		JLabel label_2 = new JLabel("Show items type");
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 4;
		panelUnderBoxes.add(label_2, gbc_label_2);

		itemTypesComboBox = new JComboBox<String>();
		itemTypesComboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				switch (itemTypesComboBox.getSelectedItem().toString()) {
				case "All":
					forItemTypeSorting = 0;
					break;
				case "Helmet":
					forItemTypeSorting = 1;
					break;
				case "Shoulders":
					forItemTypeSorting = 2;
					break;
				case "Body armor":
					forItemTypeSorting = 3;
					break;
				case "Gloves":
					forItemTypeSorting = 4;
					break;
				case "Belt":
					forItemTypeSorting = 5;
					break;
				case "Legs":
					forItemTypeSorting = 6;
					break;
				case "Boots":
					forItemTypeSorting = 7;
					break;
				case "Amulet":
					forItemTypeSorting = 8;
					break;
				case "Ring":
					forItemTypeSorting = 9;
					break;
				case "Melee weapon":
					forItemTypeSorting = 10;
					break;
				case "Ranged weapon":
					forItemTypeSorting = 11;
					break;
				case "Magic weapon":
					forItemTypeSorting = 12;
					break;
				case "Shield":
					forItemTypeSorting = 13;
					break;
				}
				if (!thisIsInitialize)
					addItemsList();
			}
		});

		itemTypesComboBox.addItem("All");

		for (ItemTypes type : ItemTypes.values()) {
			if (!type.equals(ItemTypes.MISC) && !type.equals(ItemTypes.ONE_HANDED_MELEE)
					&& !type.equals(ItemTypes.ONE_HANDED_MAGIC) && !type.equals(ItemTypes.ONE_HANDED_RANGED)
					&& !type.equals(ItemTypes.TWO_HANDED_MAGIC) && !type.equals(ItemTypes.TWO_HANDED_MELEE)
					&& !type.equals(ItemTypes.TWO_HANDED_RANGED))
				itemTypesComboBox.addItem(type.toString());
			else if (type.equals(ItemTypes.ONE_HANDED_MELEE))
				itemTypesComboBox.addItem("Melee weapon");
			else if (type.equals(ItemTypes.ONE_HANDED_RANGED))
				itemTypesComboBox.addItem("Ranged weapon");
			else if (type.equals(ItemTypes.ONE_HANDED_MAGIC))
				itemTypesComboBox.addItem("Magic weapon");
		}

		itemTypesComboBox.setBackground(Color.WHITE);
		GridBagConstraints gbc_itemTypesComboBox = new GridBagConstraints();
		gbc_itemTypesComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemTypesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_itemTypesComboBox.gridx = 2;
		gbc_itemTypesComboBox.gridy = 4;
		panelUnderBoxes.add(itemTypesComboBox, gbc_itemTypesComboBox);

		JLabel label_3 = new JLabel("Autosell items with rarity below");
		label_3.setForeground(Color.LIGHT_GRAY);
		label_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 5;
		panelUnderBoxes.add(label_3, gbc_label_3);

		autoSellComboBox = new JComboBox<String>();
		autoSellComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (autoSellComboBox.getSelectedItem().toString()) {
				case "None":
					data.getPlayerParty().getItems().setAutoSellBelowRarity(-1);
					break;
				case "Normal":
					data.getPlayerParty().getItems().changeComboBoxStatus(1);
					break;
				case "Magic":
					data.getPlayerParty().getItems().changeComboBoxStatus(2);
					break;
				case "Rare":
					data.getPlayerParty().getItems().changeComboBoxStatus(3);
					break;
				case "Epic":
					data.getPlayerParty().getItems().changeComboBoxStatus(4);
					break;
				case "Unique":
					data.getPlayerParty().getItems().changeComboBoxStatus(5);
					break;
				default:
					break;
				}

			}
		});
		autoSellComboBox.setBackground(Color.WHITE);
		autoSellComboBox.addItem("None");
		autoSellComboBox.addItem("Normal");
		autoSellComboBox.addItem("Magic");
		autoSellComboBox.addItem("Rare");
		autoSellComboBox.addItem("Epic");
		autoSellComboBox.addItem("Unique");
		GridBagConstraints gbc_autoSellComboBox = new GridBagConstraints();
		gbc_autoSellComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_autoSellComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_autoSellComboBox.gridx = 2;
		gbc_autoSellComboBox.gridy = 5;
		panelUnderBoxes.add(autoSellComboBox, gbc_autoSellComboBox);

		thisIsInitialize = false;
	}

	public void addItemsList() {
		jUnderItemsListPanel.removeAll();

		for (SingleItemPanel itempanel : data.getPlayerParty().getItems().getItemHandler().values()) {
			if (itempanel.getItem().getItemRarity().getForSorting() >= forRaritySorting) {
				if (forItemTypeSorting == 0
						|| forItemTypeSorting == itempanel.getItem().getItemBase().getItemType().getJustForSorting()) {
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					gbc.weightx = 1;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					jUnderItemsListPanel.add(itempanel, gbc, 0);
				}
			}
		}

		jUnderItemsListPanel.validate();
		jUnderItemsListPanel.repaint();
	}

	public void addCharacter(String name) {
		nameComboBox.addItem(name);
	}

	public JComboBox<String> getNameComboBox() {
		return nameComboBox;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, null);
	}
}
