package com.warluscampsite.mylittlemaze.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.loot.Item;

public class SingleItemPanel extends JPanel {
	private static final long serialVersionUID = -8864667922921159852L;
	
	private Item item;
	Data data;

	/**
	 * Create the panel.
	 * 
	 * @param mainGUI
	 */
	public SingleItemPanel(Item item, Data data) {
		setOpaque(false);
		setMaximumSize(new Dimension(32767, 60));
		this.item = item;
		this.data = data;

		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 5, 0 };
		gridBagLayout.rowHeights = new int[] { 3, 0, 3, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblItemName = new JLabel(item.getName()) {
			private static final long serialVersionUID = 5137267254828191691L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		lblItemName.setForeground(item.getItemRarity().getColor());
		lblItemName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemName.setBackground(Color.WHITE);

		lblItemName.setToolTipText(MyTooltipFormatter.formatTooltipForItem(item));

		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.fill = GridBagConstraints.BOTH;
		gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName.gridx = 1;
		gbc_lblItemName.gridy = 1;
		add(lblItemName, gbc_lblItemName);

		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.getPlayerParty().getItems().sellItemFromList(item);
				data.getGUI().getItemsPanel().addItemsList();
			}
		});
		GridBagConstraints gbc_btnSell = new GridBagConstraints();
		gbc_btnSell.insets = new Insets(0, 0, 5, 5);
		gbc_btnSell.gridx = 2;
		gbc_btnSell.gridy = 1;
		add(btnSell, gbc_btnSell);

		JButton btnEquip = new JButton("Equip");
		btnEquip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.getPlayerParty()
						.getPlayerByName(data.getGUI().getItemsPanel().getNameComboBox().getSelectedItem().toString())
						.getEq().equipItem(item);
				data.getGUI().getItemsPanel().addItemsList();
			}
		});
		GridBagConstraints gbc_btnEquip = new GridBagConstraints();
		gbc_btnEquip.insets = new Insets(0, 0, 5, 5);
		gbc_btnEquip.gridx = 3;
		gbc_btnEquip.gridy = 1;
		add(btnEquip, gbc_btnEquip);

	}

	public Item getItem() {
		return item;
	}

}
