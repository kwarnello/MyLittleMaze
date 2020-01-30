package com.warluscampsite.mylittlemaze.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.controllers.Options;

public class OptionsPanel extends JPanel {
	private static final long serialVersionUID = -8289296418556212054L;
	
	private JCheckBox chckbxShowDamage;
	private JCheckBox checkShowLevelUpInfo;
	private JCheckBox checkShowLootInfo;
	private JCheckBox checkShowDeathInfo;
	private JCheckBox checkShowGoldInfo;
	private JCheckBox checkExperienceInfo;
	private JCheckBox chckbxShowTime;
	private JPanel panel;
	private JLabel label;
	private JSeparator separator_1;
	private JCheckBox chckbxShowNumberDamages;
	private JCheckBox chckbxShowOnlyCritical;
	private JCheckBox checkShowHealing;

	/**
	 * Create the panel.
	 */
	public OptionsPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 5, 72, 3, 0 };
		gridBagLayout.rowHeights = new int[] { 5, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		JPanel jOptionsTextAreaPanel = new JPanel();
		jOptionsTextAreaPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		jOptionsTextAreaPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jOptionsTextAreaPanel = new GridBagConstraints();
		gbc_jOptionsTextAreaPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jOptionsTextAreaPanel.fill = GridBagConstraints.BOTH;
		gbc_jOptionsTextAreaPanel.gridx = 1;
		gbc_jOptionsTextAreaPanel.gridy = 1;
		add(jOptionsTextAreaPanel, gbc_jOptionsTextAreaPanel);
		GridBagLayout gbl_jOptionsTextAreaPanel = new GridBagLayout();
		gbl_jOptionsTextAreaPanel.columnWidths = new int[] { 5, 0, 0, 5, 0 };
		gbl_jOptionsTextAreaPanel.rowHeights = new int[] { 5, 0, 6, 0, 0, 0, 0, 5, 0 };
		gbl_jOptionsTextAreaPanel.columnWeights = new double[] { 0.0, 2.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_jOptionsTextAreaPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		jOptionsTextAreaPanel.setLayout(gbl_jOptionsTextAreaPanel);
		
				JLabel lblTextArea = new JLabel("Texts options");
				lblTextArea.setFont(new Font("SansSerif", Font.BOLD, 14));
				GridBagConstraints gbc_lblTextArea = new GridBagConstraints();
				gbc_lblTextArea.gridwidth = 2;
				gbc_lblTextArea.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblTextArea.insets = new Insets(0, 0, 5, 5);
				gbc_lblTextArea.gridx = 1;
				gbc_lblTextArea.gridy = 1;
				jOptionsTextAreaPanel.add(lblTextArea, gbc_lblTextArea);
				
						JSeparator separator = new JSeparator();
						separator.setPreferredSize(new Dimension(0, 5));
						separator.setSize(new Dimension(0, 5));
						separator.setBackground(Color.BLACK);
						GridBagConstraints gbc_separator = new GridBagConstraints();
						gbc_separator.gridwidth = 2;
						gbc_separator.fill = GridBagConstraints.BOTH;
						gbc_separator.insets = new Insets(0, 0, 5, 5);
						gbc_separator.gridx = 1;
						gbc_separator.gridy = 2;
						jOptionsTextAreaPanel.add(separator, gbc_separator);
						
								chckbxShowDamage = new JCheckBox("Damage dealt");
								chckbxShowDamage.addItemListener(new ItemListener() {

									public void itemStateChanged(ItemEvent arg0) {
										Options.setShowInformationAboutDamage(chckbxShowDamage.isSelected());
									}
								});
								chckbxShowDamage.setBackground(Color.WHITE);
								GridBagConstraints gbc_chckbxShowDamage = new GridBagConstraints();
								gbc_chckbxShowDamage.anchor = GridBagConstraints.WEST;
								gbc_chckbxShowDamage.insets = new Insets(0, 0, 5, 5);
								gbc_chckbxShowDamage.gridx = 1;
								gbc_chckbxShowDamage.gridy = 3;
								jOptionsTextAreaPanel.add(chckbxShowDamage, gbc_chckbxShowDamage);
								
										checkShowLevelUpInfo = new JCheckBox("Level up information");
										checkShowLevelUpInfo.addItemListener(new ItemListener() {
											public void itemStateChanged(ItemEvent e) {
												Options.setShowInformationAboutLeveling(checkShowLevelUpInfo.isSelected());
											}
										});
										checkShowLevelUpInfo.setSelected(true);
										checkShowLevelUpInfo.setBackground(Color.WHITE);
										GridBagConstraints gbc_checkShowLevelUpInfo = new GridBagConstraints();
										gbc_checkShowLevelUpInfo.anchor = GridBagConstraints.WEST;
										gbc_checkShowLevelUpInfo.insets = new Insets(0, 0, 5, 5);
										gbc_checkShowLevelUpInfo.gridx = 2;
										gbc_checkShowLevelUpInfo.gridy = 3;
										jOptionsTextAreaPanel.add(checkShowLevelUpInfo, gbc_checkShowLevelUpInfo);
										
												checkShowLootInfo = new JCheckBox("Loot");
												checkShowLootInfo.addItemListener(new ItemListener() {
													public void itemStateChanged(ItemEvent arg0) {
														Options.setShowInformationAboutLoot(checkShowLootInfo.isSelected());

													}
												});
												checkShowLootInfo.setSelected(true);
												checkShowLootInfo.setBackground(Color.WHITE);
												GridBagConstraints gbc_checkShowLootInfo = new GridBagConstraints();
												gbc_checkShowLootInfo.anchor = GridBagConstraints.WEST;
												gbc_checkShowLootInfo.insets = new Insets(0, 0, 5, 5);
												gbc_checkShowLootInfo.gridx = 1;
												gbc_checkShowLootInfo.gridy = 4;
												jOptionsTextAreaPanel.add(checkShowLootInfo, gbc_checkShowLootInfo);
												
														checkShowDeathInfo = new JCheckBox("Death Information");
														checkShowDeathInfo.addItemListener(new ItemListener() {
															public void itemStateChanged(ItemEvent e) {
																Options.setShowInformationAboutDeaths(checkShowDeathInfo.isSelected());
															}
														});
														checkShowDeathInfo.setSelected(true);
														checkShowDeathInfo.setBackground(Color.WHITE);
														GridBagConstraints gbc_checkShowDeathInfo = new GridBagConstraints();
														gbc_checkShowDeathInfo.anchor = GridBagConstraints.WEST;
														gbc_checkShowDeathInfo.insets = new Insets(0, 0, 5, 5);
														gbc_checkShowDeathInfo.gridx = 2;
														gbc_checkShowDeathInfo.gridy = 4;
														jOptionsTextAreaPanel.add(checkShowDeathInfo, gbc_checkShowDeathInfo);
														
																checkShowGoldInfo = new JCheckBox("Gold gained");
																checkShowGoldInfo.addItemListener(new ItemListener() {
																	public void itemStateChanged(ItemEvent e) {
																		Options.setShowInformationAboutGold(checkShowGoldInfo.isSelected());
																	}
																});
																checkShowGoldInfo.setSelected(true);
																checkShowGoldInfo.setBackground(Color.WHITE);
																GridBagConstraints gbc_checkShowGoldInfo = new GridBagConstraints();
																gbc_checkShowGoldInfo.anchor = GridBagConstraints.WEST;
																gbc_checkShowGoldInfo.insets = new Insets(0, 0, 5, 5);
																gbc_checkShowGoldInfo.gridx = 1;
																gbc_checkShowGoldInfo.gridy = 5;
																jOptionsTextAreaPanel.add(checkShowGoldInfo, gbc_checkShowGoldInfo);
																
																		checkExperienceInfo = new JCheckBox("Show experience gained");
																		checkExperienceInfo.addItemListener(new ItemListener() {
																			public void itemStateChanged(ItemEvent e) {
																				Options.setShowInformationAboutExperience(checkExperienceInfo.isSelected());
																			}
																		});
																		checkExperienceInfo.setSelected(true);
																		checkExperienceInfo.setBackground(Color.WHITE);
																		GridBagConstraints gbc_checkExperienceInfo = new GridBagConstraints();
																		gbc_checkExperienceInfo.anchor = GridBagConstraints.WEST;
																		gbc_checkExperienceInfo.insets = new Insets(0, 0, 5, 5);
																		gbc_checkExperienceInfo.gridx = 2;
																		gbc_checkExperienceInfo.gridy = 5;
																		jOptionsTextAreaPanel.add(checkExperienceInfo, gbc_checkExperienceInfo);
																		
																				chckbxShowTime = new JCheckBox("Show time");
																				chckbxShowTime.addItemListener(new ItemListener() {
																					public void itemStateChanged(ItemEvent e) {
																						Options.setShowTime(chckbxShowTime.isSelected());
																					}
																				});
																				chckbxShowTime.setBackground(Color.WHITE);
																				GridBagConstraints gbc_chckbxShowTime = new GridBagConstraints();
																				gbc_chckbxShowTime.anchor = GridBagConstraints.WEST;
																				gbc_chckbxShowTime.insets = new Insets(0, 0, 5, 5);
																				gbc_chckbxShowTime.gridx = 1;
																				gbc_chckbxShowTime.gridy = 6;
																				jOptionsTextAreaPanel.add(chckbxShowTime, gbc_chckbxShowTime);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 5, 0, 0, 5, 0 };
		gbl_panel.rowHeights = new int[] { 5, 0, 6, 0, 0, 5, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		label = new JLabel("Damage numbers");
		label.setFont(new Font("SansSerif", Font.BOLD, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);

		separator_1 = new JSeparator();
		separator_1.setSize(new Dimension(0, 5));
		separator_1.setPreferredSize(new Dimension(0, 5));
		separator_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 2;
		panel.add(separator_1, gbc_separator_1);

		chckbxShowNumberDamages = new JCheckBox("Show numbers with damage");
		chckbxShowNumberDamages.setSelected(true);
		chckbxShowNumberDamages.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxShowNumberDamages.setBackground(Color.WHITE);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.gridwidth = 2;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 1;
		gbc_checkBox.gridy = 3;
		panel.add(chckbxShowNumberDamages, gbc_checkBox);

		chckbxShowOnlyCritical = new JCheckBox("Show only critical hits");
		chckbxShowOnlyCritical.setBackground(Color.WHITE);
		GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.anchor = GridBagConstraints.WEST;
		gbc_checkBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_1.gridx = 1;
		gbc_checkBox_1.gridy = 4;
		panel.add(chckbxShowOnlyCritical, gbc_checkBox_1);

		checkShowHealing = new JCheckBox("Show healing");
		checkShowHealing.setSelected(true);
		checkShowHealing.setBackground(Color.WHITE);
		GridBagConstraints gbc_checkBox_2 = new GridBagConstraints();
		gbc_checkBox_2.anchor = GridBagConstraints.WEST;
		gbc_checkBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_2.gridx = 2;
		gbc_checkBox_2.gridy = 4;
		panel.add(checkShowHealing, gbc_checkBox_2);
	}

	void setOptionsStuff() {
		chckbxShowDamage.setSelected(Options.isShowInformationAboutDamage());
		checkShowLevelUpInfo.setSelected(Options.isShowInformationAboutLeveling());
		checkShowLootInfo.setSelected(Options.isShowInformationAboutLoot());
		checkShowDeathInfo.setSelected(Options.isShowInformationAboutDeaths());
		checkShowGoldInfo.setSelected(Options.isShowInformationAboutGold());
		checkExperienceInfo.setSelected(Options.isShowInformationAboutExperience());
		chckbxShowTime.setSelected(Options.isShowTime());

		chckbxShowNumberDamages.setSelected(Options.isShowNumbers());
		chckbxShowOnlyCritical.setSelected(Options.isShowOnlyCritical());
		checkShowHealing.setSelected(Options.isShowHealing());
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
