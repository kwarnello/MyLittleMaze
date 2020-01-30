package com.warluscampsite.mylittlemaze.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.data.Data;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;

public class AchievmentPanel extends JPanel {
	private Data data;

	private static final long serialVersionUID = -8289296418556212054L;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel jPanelUnderFiltrs;
	private JComboBox jComboBoxEnableFilter;
	private JComboBox jComboBoxTypeFilter;

	/**
	 * Create the panel.
	 * 
	 * @param data
	 */
	public AchievmentPanel(Data data) {
		this.data = data;

		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 9, 72, 7, 0 };
		gridBagLayout.rowHeights = new int[] { 12, 25, 0, 3, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		jPanelUnderFiltrs = new JPanel();
		jPanelUnderFiltrs.setBackground(Color.WHITE);
		GridBagConstraints gbc_jPanelUnderFiltrs = new GridBagConstraints();
		gbc_jPanelUnderFiltrs.insets = new Insets(0, 0, 5, 5);
		gbc_jPanelUnderFiltrs.fill = GridBagConstraints.BOTH;
		gbc_jPanelUnderFiltrs.gridx = 1;
		gbc_jPanelUnderFiltrs.gridy = 1;
		add(jPanelUnderFiltrs, gbc_jPanelUnderFiltrs);
		GridBagLayout gbl_jPanelUnderFiltrs = new GridBagLayout();
		gbl_jPanelUnderFiltrs.columnWidths = new int[] { 5, 0, 15, 0, 5, 0 };
		gbl_jPanelUnderFiltrs.rowHeights = new int[] { 0, 0 };
		gbl_jPanelUnderFiltrs.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_jPanelUnderFiltrs.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		jPanelUnderFiltrs.setLayout(gbl_jPanelUnderFiltrs);

		jComboBoxEnableFilter = new JComboBox();
		jComboBoxEnableFilter.setBackground(Color.WHITE);
		GridBagConstraints gbc_jComboBoxEnableFilter = new GridBagConstraints();
		gbc_jComboBoxEnableFilter.insets = new Insets(0, 0, 0, 5);
		gbc_jComboBoxEnableFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxEnableFilter.gridx = 1;
		gbc_jComboBoxEnableFilter.gridy = 0;
		jPanelUnderFiltrs.add(jComboBoxEnableFilter, gbc_jComboBoxEnableFilter);

		jComboBoxTypeFilter = new JComboBox();
		jComboBoxTypeFilter.setBackground(Color.WHITE);
		GridBagConstraints gbc_jComboBoxTypeFilter = new GridBagConstraints();
		gbc_jComboBoxTypeFilter.insets = new Insets(0, 0, 0, 5);
		gbc_jComboBoxTypeFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxTypeFilter.gridx = 3;
		gbc_jComboBoxTypeFilter.gridy = 0;
		jPanelUnderFiltrs.add(jComboBoxTypeFilter, gbc_jComboBoxTypeFilter);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 4, 1, 1));
		
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());
		panel.add(new SingleAchievmentPanel());


	}
}
