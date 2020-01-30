package com.warluscampsite.mylittlemaze.gui.character;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;

public class CharacterPanel extends JPanel {
	private static final long serialVersionUID = -1031069936743118082L;

	Player character;

	Image bgImage;

	private JLabel jCharNameLabel;
	private JLabel jProfessionLabel;
	private JLabel jLevelLabel;
	private JLabel jStrengthValueLabel;
	private JLabel jDexterityValueLabel;
	private JLabel jIntelligenceValueLabel;
	private JLabel jSpeedValueLabel;
	private JLabel jVitalityValueLabel;
	private JLabel jWisdomValueLabel;
	private JLabel jLuckValueLabel;
	private JButton jWisdomButton;
	private JButton jVitalityButton;
	private JButton jSpeedButton;
	private JButton jIntelligenceButton;
	private JButton jLuckButton;
	private JButton jDexterityButton;
	private JButton jStrengthButton;
	private JLabel jAttributePoints;
	private JTabbedPane jCharacterTabbedPanel;
	private JLabel jAttackLabel;
	private JLabel jDefenceLabel;
	private JLabel jDodgeLabel;
	private JLabel jArmorLabel;
	private JLabel lblMana;
	private JLabel jHealthLabel;
	private JLabel jHealthRegenerationLabel;
	private JLabel jManaLabel;
	private JLabel jManaRegenerationLabel;
	private JProgressBar jExperienceBar;

	CharacterEquipmentPanel jEquipmentPanel;
	private JLabel lblForPhysicalResistSymbol;
	private JLabel lblForPhysicalResistValue;
	private JPanel jFireResistPanel;
	private JLabel lblForFireResistSymbol;
	private JLabel lblForFireResistValue;
	private JLabel lblForIceResistValue;
	private JLabel lblForIceResistSymbol;
	private JLabel lblForEarthResistSymbol;
	private JLabel lblForEarthResistValue;
	private JLabel lblForLightningResistSymbol;
	private JLabel lblForLightningResistValue;
	private JLabel lblForDarkResistSymbol;
	private JLabel lblForDarkResistValue;
	private JLabel lblForRadianceResistSymbol;
	private JLabel lblForRadianceResistValue;
	private JLabel lblForPoisonResistSymbol;
	private JLabel lblForPoisonResistValue;
	private JLabel lblWeaponAttack;
	private JSeparator separator;
	private JLabel lblDamageInfo;
	private JLabel lblAPS;
	private JLabel lblHitPerAttack;
	private JLabel lblCritChanceValue;
	private JLabel label;
	private JLabel lblAttackType;

	SkillsPanel skillsPanel;
	PassivesPanel jPassivesPanel;
	private JLabel jBlockChancelabel;
	private JLabel blockSpellsLabel;
	private JLabel jCanBlockSpells;
	private JLabel lblDPS;
	private JLabel lblDPSValue;

	/**
	 * Create the panel.
	 * 
	 * @param character
	 */
	public CharacterPanel(Player character) {
		this.character = character;
		try {
			bgImage = ImageIO.read(new File("res/GUI/characterBg.png")).getScaledInstance(808, 675, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setOpaque(false);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 8, 120, 60, 5, 5, 5, 5, 5 };
		gridBagLayout.rowHeights = new int[] { 5, 5, 5, 5, 5, 0, 5 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblLevel = new JLabel("LEVEL");
		lblLevel.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.gridwidth = 2;
		gbc_lblLevel.anchor = GridBagConstraints.WEST;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 4;
		gbc_lblLevel.gridy = 1;
		add(lblLevel, gbc_lblLevel);

		jCharNameLabel = new JLabel("Billy McBill");
		jCharNameLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				character.getExperience().addExperience(10000);
			}
		});
		jCharNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		jCharNameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		jCharNameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_jCharNameLabel = new GridBagConstraints();
		gbc_jCharNameLabel.anchor = GridBagConstraints.WEST;
		gbc_jCharNameLabel.gridwidth = 2;
		gbc_jCharNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jCharNameLabel.gridx = 1;
		gbc_jCharNameLabel.gridy = 1;
		add(jCharNameLabel, gbc_jCharNameLabel);

		jProfessionLabel = new JLabel("Paladin");
		jProfessionLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		jProfessionLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_jProfessionLabel = new GridBagConstraints();
		gbc_jProfessionLabel.anchor = GridBagConstraints.WEST;
		gbc_jProfessionLabel.gridwidth = 2;
		gbc_jProfessionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jProfessionLabel.gridx = 1;
		gbc_jProfessionLabel.gridy = 2;
		add(jProfessionLabel, gbc_jProfessionLabel);

		jLevelLabel = new JLabel("10000");
		jLevelLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_jLevelLabel = new GridBagConstraints();
		gbc_jLevelLabel.gridwidth = 2;
		gbc_jLevelLabel.anchor = GridBagConstraints.WEST;
		gbc_jLevelLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jLevelLabel.gridx = 4;
		gbc_jLevelLabel.gridy = 2;
		add(jLevelLabel, gbc_jLevelLabel);

		jExperienceBar = new JProgressBar();
		jExperienceBar.setStringPainted(true);
		jExperienceBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		jExperienceBar.setForeground(new Color(184, 134, 11));
		jExperienceBar
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 0), new Color(153, 102, 51)));
		jExperienceBar.setValue(60);
		GridBagConstraints gbc_jExperienceBar = new GridBagConstraints();
		gbc_jExperienceBar.fill = GridBagConstraints.BOTH;
		gbc_jExperienceBar.gridwidth = 5;
		gbc_jExperienceBar.insets = new Insets(0, 0, 5, 5);
		gbc_jExperienceBar.gridx = 1;
		gbc_jExperienceBar.gridy = 3;
		add(jExperienceBar, gbc_jExperienceBar);

		jCharacterTabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		jCharacterTabbedPanel.setBorder(null);
		jCharacterTabbedPanel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		GridBagConstraints gbc_jCharacterTabbedPanel = new GridBagConstraints();
		gbc_jCharacterTabbedPanel.gridwidth = 5;
		gbc_jCharacterTabbedPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jCharacterTabbedPanel.fill = GridBagConstraints.BOTH;
		gbc_jCharacterTabbedPanel.gridx = 1;
		gbc_jCharacterTabbedPanel.gridy = 4;
		add(jCharacterTabbedPanel, gbc_jCharacterTabbedPanel);

		JPanel jAttributesPanel = new JPanel();
		jAttributesPanel.setOpaque(false);
		jAttributesPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		jAttributesPanel.setBackground(Color.WHITE);
		jCharacterTabbedPanel.addTab("Attributes", null, jAttributesPanel, null);
		GridBagLayout gbl_jAttributesPanel = new GridBagLayout();
		gbl_jAttributesPanel.columnWidths = new int[] { 15, 0, 60, 0, 0, 47, 15, 0 };
		gbl_jAttributesPanel.rowHeights = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 20, 20, 20, 20, 20, 0, 0 };
		gbl_jAttributesPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_jAttributesPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 2.0, Double.MIN_VALUE };
		jAttributesPanel.setLayout(gbl_jAttributesPanel);

		JLabel lblStrength = new JLabel("Strength");
		GridBagConstraints gbc_lblStrength = new GridBagConstraints();
		gbc_lblStrength.insets = new Insets(0, 0, 5, 5);
		gbc_lblStrength.gridx = 1;
		gbc_lblStrength.gridy = 1;
		jAttributesPanel.add(lblStrength, gbc_lblStrength);

		jStrengthValueLabel = new JLabel("1000");
		GridBagConstraints gbc_jStrengthValueLabel = new GridBagConstraints();
		gbc_jStrengthValueLabel.gridwidth = 2;
		gbc_jStrengthValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jStrengthValueLabel.gridx = 2;
		gbc_jStrengthValueLabel.gridy = 1;
		jAttributesPanel.add(jStrengthValueLabel, gbc_jStrengthValueLabel);

		jStrengthButton = new JButton("<html><center>Add strength<br>\r\nPoints needed</center></html>");
		jStrengthButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		jStrengthButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked(AttributesEnum.STRENGTH);
			}
		});
		GridBagConstraints gbc_jStrengthButton = new GridBagConstraints();
		gbc_jStrengthButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_jStrengthButton.gridwidth = 2;
		gbc_jStrengthButton.insets = new Insets(0, 0, 5, 5);
		gbc_jStrengthButton.gridx = 4;
		gbc_jStrengthButton.gridy = 1;
		jAttributesPanel.add(jStrengthButton, gbc_jStrengthButton);

		JLabel lblDexterity = new JLabel("Dexterity");
		GridBagConstraints gbc_lblDexterity = new GridBagConstraints();
		gbc_lblDexterity.insets = new Insets(0, 0, 5, 5);
		gbc_lblDexterity.gridx = 1;
		gbc_lblDexterity.gridy = 2;
		jAttributesPanel.add(lblDexterity, gbc_lblDexterity);

		jDexterityValueLabel = new JLabel("1000");
		GridBagConstraints gbc_jDexterityValueLabel = new GridBagConstraints();
		gbc_jDexterityValueLabel.gridwidth = 2;
		gbc_jDexterityValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jDexterityValueLabel.gridx = 2;
		gbc_jDexterityValueLabel.gridy = 2;
		jAttributesPanel.add(jDexterityValueLabel, gbc_jDexterityValueLabel);

		jDexterityButton = new JButton("New button");
		jDexterityButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		jDexterityButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked(AttributesEnum.DEXTERITY);
			}
		});
		GridBagConstraints gbc_jDexterityButton = new GridBagConstraints();
		gbc_jDexterityButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_jDexterityButton.gridwidth = 2;
		gbc_jDexterityButton.insets = new Insets(0, 0, 5, 5);
		gbc_jDexterityButton.gridx = 4;
		gbc_jDexterityButton.gridy = 2;
		jAttributesPanel.add(jDexterityButton, gbc_jDexterityButton);

		JLabel lblIntelligence = new JLabel("Intelligence");
		GridBagConstraints gbc_lblIntelligence = new GridBagConstraints();
		gbc_lblIntelligence.insets = new Insets(0, 0, 5, 5);
		gbc_lblIntelligence.gridx = 1;
		gbc_lblIntelligence.gridy = 3;
		jAttributesPanel.add(lblIntelligence, gbc_lblIntelligence);

		jIntelligenceValueLabel = new JLabel("1000");
		GridBagConstraints gbc_jIntelligenceValueLabel = new GridBagConstraints();
		gbc_jIntelligenceValueLabel.gridwidth = 2;
		gbc_jIntelligenceValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jIntelligenceValueLabel.gridx = 2;
		gbc_jIntelligenceValueLabel.gridy = 3;
		jAttributesPanel.add(jIntelligenceValueLabel, gbc_jIntelligenceValueLabel);

		jIntelligenceButton = new JButton("New button");
		jIntelligenceButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		jIntelligenceButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked(AttributesEnum.INTELLIGENCE);
			}
		});
		GridBagConstraints gbc_jIntelligenceButton = new GridBagConstraints();
		gbc_jIntelligenceButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_jIntelligenceButton.gridwidth = 2;
		gbc_jIntelligenceButton.insets = new Insets(0, 0, 5, 5);
		gbc_jIntelligenceButton.gridx = 4;
		gbc_jIntelligenceButton.gridy = 3;
		jAttributesPanel.add(jIntelligenceButton, gbc_jIntelligenceButton);

		JLabel lblSpeed = new JLabel("Speed");
		GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
		gbc_lblSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeed.gridx = 1;
		gbc_lblSpeed.gridy = 4;
		jAttributesPanel.add(lblSpeed, gbc_lblSpeed);

		jSpeedValueLabel = new JLabel("1000");
		GridBagConstraints gbc_jSpeedValueLabel = new GridBagConstraints();
		gbc_jSpeedValueLabel.gridwidth = 2;
		gbc_jSpeedValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jSpeedValueLabel.gridx = 2;
		gbc_jSpeedValueLabel.gridy = 4;
		jAttributesPanel.add(jSpeedValueLabel, gbc_jSpeedValueLabel);

		jSpeedButton = new JButton("New button");
		jSpeedButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		jSpeedButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked(AttributesEnum.SPEED);
			}
		});
		GridBagConstraints gbc_jSpeedButton = new GridBagConstraints();
		gbc_jSpeedButton.gridwidth = 2;
		gbc_jSpeedButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_jSpeedButton.insets = new Insets(0, 0, 5, 5);
		gbc_jSpeedButton.gridx = 4;
		gbc_jSpeedButton.gridy = 4;
		jAttributesPanel.add(jSpeedButton, gbc_jSpeedButton);

		JLabel lblVitality = new JLabel("Vitality");
		GridBagConstraints gbc_lblVitality = new GridBagConstraints();
		gbc_lblVitality.insets = new Insets(0, 0, 5, 5);
		gbc_lblVitality.gridx = 1;
		gbc_lblVitality.gridy = 5;
		jAttributesPanel.add(lblVitality, gbc_lblVitality);

		jVitalityValueLabel = new JLabel("1000");
		GridBagConstraints gbc_jVitalityValueLabel = new GridBagConstraints();
		gbc_jVitalityValueLabel.gridwidth = 2;
		gbc_jVitalityValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jVitalityValueLabel.gridx = 2;
		gbc_jVitalityValueLabel.gridy = 5;
		jAttributesPanel.add(jVitalityValueLabel, gbc_jVitalityValueLabel);

		jVitalityButton = new JButton("New button");
		jVitalityButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		jVitalityButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked(AttributesEnum.VITALITY);
			}
		});
		GridBagConstraints gbc_jVitalityButton = new GridBagConstraints();
		gbc_jVitalityButton.gridwidth = 2;
		gbc_jVitalityButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_jVitalityButton.insets = new Insets(0, 0, 5, 5);
		gbc_jVitalityButton.gridx = 4;
		gbc_jVitalityButton.gridy = 5;
		jAttributesPanel.add(jVitalityButton, gbc_jVitalityButton);

		JLabel lblWisdom = new JLabel("Wisdom");
		GridBagConstraints gbc_lblWisdom = new GridBagConstraints();
		gbc_lblWisdom.insets = new Insets(0, 0, 5, 5);
		gbc_lblWisdom.gridx = 1;
		gbc_lblWisdom.gridy = 6;
		jAttributesPanel.add(lblWisdom, gbc_lblWisdom);

		jWisdomValueLabel = new JLabel("1000");
		GridBagConstraints gbc_jWisdomValueLabel = new GridBagConstraints();
		gbc_jWisdomValueLabel.gridwidth = 2;
		gbc_jWisdomValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jWisdomValueLabel.gridx = 2;
		gbc_jWisdomValueLabel.gridy = 6;
		jAttributesPanel.add(jWisdomValueLabel, gbc_jWisdomValueLabel);

		jWisdomButton = new JButton("New button");
		jWisdomButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		jWisdomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked(AttributesEnum.WISDOM);

			}
		});
		GridBagConstraints gbc_jWisdomButton = new GridBagConstraints();
		gbc_jWisdomButton.gridwidth = 2;
		gbc_jWisdomButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_jWisdomButton.insets = new Insets(0, 0, 5, 5);
		gbc_jWisdomButton.gridx = 4;
		gbc_jWisdomButton.gridy = 6;
		jAttributesPanel.add(jWisdomButton, gbc_jWisdomButton);

		JLabel lblLuck = new JLabel("Luck");
		GridBagConstraints gbc_lblLuck = new GridBagConstraints();
		gbc_lblLuck.insets = new Insets(0, 0, 5, 5);
		gbc_lblLuck.gridx = 1;
		gbc_lblLuck.gridy = 7;
		jAttributesPanel.add(lblLuck, gbc_lblLuck);

		jLuckValueLabel = new JLabel("1000");
		GridBagConstraints gbc_jLuckValueLabel = new GridBagConstraints();
		gbc_jLuckValueLabel.gridwidth = 2;
		gbc_jLuckValueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jLuckValueLabel.gridx = 2;
		gbc_jLuckValueLabel.gridy = 7;
		jAttributesPanel.add(jLuckValueLabel, gbc_jLuckValueLabel);

		jLuckButton = new JButton("New button");
		jLuckButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		jLuckButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonClicked(AttributesEnum.LUCK);
			}
		});
		GridBagConstraints gbc_jLuckButton = new GridBagConstraints();
		gbc_jLuckButton.gridwidth = 2;
		gbc_jLuckButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLuckButton.insets = new Insets(0, 0, 5, 5);
		gbc_jLuckButton.gridx = 4;
		gbc_jLuckButton.gridy = 7;
		jAttributesPanel.add(jLuckButton, gbc_jLuckButton);

		JSeparator jUpperSeparator = new JSeparator();
		jUpperSeparator.setMinimumSize(new Dimension(0, 5));
		jUpperSeparator.setSize(new Dimension(0, 5));
		jUpperSeparator.setPreferredSize(new Dimension(0, 5));
		jUpperSeparator.setBackground(Color.BLACK);
		GridBagConstraints gbc_jUpperSeparator = new GridBagConstraints();
		gbc_jUpperSeparator.fill = GridBagConstraints.BOTH;
		gbc_jUpperSeparator.gridwidth = 5;
		gbc_jUpperSeparator.insets = new Insets(0, 0, 5, 5);
		gbc_jUpperSeparator.gridx = 1;
		gbc_jUpperSeparator.gridy = 8;
		jAttributesPanel.add(jUpperSeparator, gbc_jUpperSeparator);

		JLabel lblAttributePointsTo = new JLabel("Attribute points to spend");
		GridBagConstraints gbc_lblAttributePointsTo = new GridBagConstraints();
		gbc_lblAttributePointsTo.fill = GridBagConstraints.VERTICAL;
		gbc_lblAttributePointsTo.gridwidth = 3;
		gbc_lblAttributePointsTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttributePointsTo.gridx = 1;
		gbc_lblAttributePointsTo.gridy = 9;
		jAttributesPanel.add(lblAttributePointsTo, gbc_lblAttributePointsTo);

		jAttributePoints = new JLabel("0");
		GridBagConstraints gbc_jAttributePoints = new GridBagConstraints();
		gbc_jAttributePoints.anchor = GridBagConstraints.WEST;
		gbc_jAttributePoints.fill = GridBagConstraints.VERTICAL;
		gbc_jAttributePoints.gridwidth = 2;
		gbc_jAttributePoints.insets = new Insets(0, 0, 5, 5);
		gbc_jAttributePoints.gridx = 4;
		gbc_jAttributePoints.gridy = 9;
		jAttributesPanel.add(jAttributePoints, gbc_jAttributePoints);

		JSeparator jLowerSeparator = new JSeparator();
		jLowerSeparator.setMinimumSize(new Dimension(0, 5));
		jLowerSeparator.setSize(new Dimension(0, 5));
		jLowerSeparator.setPreferredSize(new Dimension(0, 5));
		jLowerSeparator.setBackground(Color.BLACK);
		GridBagConstraints gbc_jLowerSeparator = new GridBagConstraints();
		gbc_jLowerSeparator.fill = GridBagConstraints.BOTH;
		gbc_jLowerSeparator.gridwidth = 5;
		gbc_jLowerSeparator.insets = new Insets(0, 0, 5, 5);
		gbc_jLowerSeparator.gridx = 1;
		gbc_jLowerSeparator.gridy = 10;
		jAttributesPanel.add(jLowerSeparator, gbc_jLowerSeparator);

		JLabel lblAttack = new JLabel("Attack");
		GridBagConstraints gbc_lblAttack = new GridBagConstraints();
		gbc_lblAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttack.gridx = 1;
		gbc_lblAttack.gridy = 11;
		jAttributesPanel.add(lblAttack, gbc_lblAttack);

		jAttackLabel = new JLabel("1000");
		GridBagConstraints gbc_jAttackLabel = new GridBagConstraints();
		gbc_jAttackLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jAttackLabel.gridx = 2;
		gbc_jAttackLabel.gridy = 11;
		jAttributesPanel.add(jAttackLabel, gbc_jAttackLabel);

		JLabel lblHealth = new JLabel("Health");
		GridBagConstraints gbc_lblHealth = new GridBagConstraints();
		gbc_lblHealth.gridwidth = 2;
		gbc_lblHealth.fill = GridBagConstraints.VERTICAL;
		gbc_lblHealth.insets = new Insets(0, 0, 5, 5);
		gbc_lblHealth.gridx = 3;
		gbc_lblHealth.gridy = 11;
		jAttributesPanel.add(lblHealth, gbc_lblHealth);

		jHealthLabel = new JLabel("1000");
		GridBagConstraints gbc_jHealthLabel = new GridBagConstraints();
		gbc_jHealthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jHealthLabel.gridx = 5;
		gbc_jHealthLabel.gridy = 11;
		jAttributesPanel.add(jHealthLabel, gbc_jHealthLabel);

		JLabel lblDefence = new JLabel("Defence");
		GridBagConstraints gbc_lblDefence = new GridBagConstraints();
		gbc_lblDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefence.gridx = 1;
		gbc_lblDefence.gridy = 12;
		jAttributesPanel.add(lblDefence, gbc_lblDefence);

		jDefenceLabel = new JLabel("1000");
		GridBagConstraints gbc_jDefenceLabel = new GridBagConstraints();
		gbc_jDefenceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jDefenceLabel.gridx = 2;
		gbc_jDefenceLabel.gridy = 12;
		jAttributesPanel.add(jDefenceLabel, gbc_jDefenceLabel);

		JLabel lblHealthRegeneration = new JLabel("<html><center>Health regeneration</center></html>");
		GridBagConstraints gbc_lblHealthRegeneration = new GridBagConstraints();
		gbc_lblHealthRegeneration.gridwidth = 2;
		gbc_lblHealthRegeneration.fill = GridBagConstraints.VERTICAL;
		gbc_lblHealthRegeneration.insets = new Insets(0, 0, 5, 5);
		gbc_lblHealthRegeneration.gridx = 3;
		gbc_lblHealthRegeneration.gridy = 12;
		jAttributesPanel.add(lblHealthRegeneration, gbc_lblHealthRegeneration);

		jHealthRegenerationLabel = new JLabel("1000");
		GridBagConstraints gbc_jHealthRegenerationLabel = new GridBagConstraints();
		gbc_jHealthRegenerationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jHealthRegenerationLabel.gridx = 5;
		gbc_jHealthRegenerationLabel.gridy = 12;
		jAttributesPanel.add(jHealthRegenerationLabel, gbc_jHealthRegenerationLabel);

		JLabel lblDodge = new JLabel("Dodge");
		GridBagConstraints gbc_lblDodge = new GridBagConstraints();
		gbc_lblDodge.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodge.gridx = 1;
		gbc_lblDodge.gridy = 13;
		jAttributesPanel.add(lblDodge, gbc_lblDodge);

		jDodgeLabel = new JLabel("1000");
		GridBagConstraints gbc_jDodgeLabel = new GridBagConstraints();
		gbc_jDodgeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jDodgeLabel.gridx = 2;
		gbc_jDodgeLabel.gridy = 13;
		jAttributesPanel.add(jDodgeLabel, gbc_jDodgeLabel);

		lblMana = new JLabel("Mana");
		GridBagConstraints gbc_lblMana = new GridBagConstraints();
		gbc_lblMana.gridwidth = 2;
		gbc_lblMana.fill = GridBagConstraints.VERTICAL;
		gbc_lblMana.insets = new Insets(0, 0, 5, 5);
		gbc_lblMana.gridx = 3;
		gbc_lblMana.gridy = 13;
		jAttributesPanel.add(lblMana, gbc_lblMana);

		jManaLabel = new JLabel("1000");
		GridBagConstraints gbc_jManaLabel = new GridBagConstraints();
		gbc_jManaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jManaLabel.gridx = 5;
		gbc_jManaLabel.gridy = 13;
		jAttributesPanel.add(jManaLabel, gbc_jManaLabel);

		JLabel lblArmor = new JLabel("Armor");
		GridBagConstraints gbc_lblArmor = new GridBagConstraints();
		gbc_lblArmor.insets = new Insets(0, 0, 5, 5);
		gbc_lblArmor.gridx = 1;
		gbc_lblArmor.gridy = 14;
		jAttributesPanel.add(lblArmor, gbc_lblArmor);

		jArmorLabel = new JLabel("1000");
		GridBagConstraints gbc_jArmorLabel = new GridBagConstraints();
		gbc_jArmorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jArmorLabel.gridx = 2;
		gbc_jArmorLabel.gridy = 14;
		jAttributesPanel.add(jArmorLabel, gbc_jArmorLabel);

		JLabel lblManaRegeneration = new JLabel("<html><center>Mana regeneration</center></html>");
		GridBagConstraints gbc_lblManaRegeneration = new GridBagConstraints();
		gbc_lblManaRegeneration.gridwidth = 2;
		gbc_lblManaRegeneration.fill = GridBagConstraints.VERTICAL;
		gbc_lblManaRegeneration.insets = new Insets(0, 0, 5, 5);
		gbc_lblManaRegeneration.gridx = 3;
		gbc_lblManaRegeneration.gridy = 14;
		jAttributesPanel.add(lblManaRegeneration, gbc_lblManaRegeneration);

		jManaRegenerationLabel = new JLabel("1000");
		GridBagConstraints gbc_jManaRegenerationLabel = new GridBagConstraints();
		gbc_jManaRegenerationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jManaRegenerationLabel.gridx = 5;
		gbc_jManaRegenerationLabel.gridy = 14;
		jAttributesPanel.add(jManaRegenerationLabel, gbc_jManaRegenerationLabel);

		JLabel lblBlockChance = new JLabel("Block chance");
		GridBagConstraints gbc_lblBlockChance = new GridBagConstraints();
		gbc_lblBlockChance.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlockChance.gridx = 1;
		gbc_lblBlockChance.gridy = 15;
		jAttributesPanel.add(lblBlockChance, gbc_lblBlockChance);

		jBlockChancelabel = new JLabel("NaN%");
		GridBagConstraints gbc_jBlockChancelabel = new GridBagConstraints();
		gbc_jBlockChancelabel.insets = new Insets(0, 0, 5, 5);
		gbc_jBlockChancelabel.gridx = 2;
		gbc_jBlockChancelabel.gridy = 15;
		jAttributesPanel.add(jBlockChancelabel, gbc_jBlockChancelabel);

		blockSpellsLabel = new JLabel("Can block spells?");
		GridBagConstraints gbc_blockSpellsLabel = new GridBagConstraints();
		gbc_blockSpellsLabel.gridwidth = 2;
		gbc_blockSpellsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_blockSpellsLabel.gridx = 3;
		gbc_blockSpellsLabel.gridy = 15;
		jAttributesPanel.add(blockSpellsLabel, gbc_blockSpellsLabel);

		jCanBlockSpells = new JLabel("no");
		GridBagConstraints gbc_jCanBlockSpells = new GridBagConstraints();
		gbc_jCanBlockSpells.insets = new Insets(0, 0, 5, 5);
		gbc_jCanBlockSpells.gridx = 5;
		gbc_jCanBlockSpells.gridy = 15;
		jAttributesPanel.add(jCanBlockSpells, gbc_jCanBlockSpells);

		JPanel jAttackResistPanel = new JPanel();
		jAttackResistPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		jAttackResistPanel.setOpaque(false);
		jAttackResistPanel.setBackground(Color.GRAY);
		jCharacterTabbedPanel.addTab("Attack and resists", null, jAttackResistPanel, null);
		GridBagLayout gbl_jAttackResistPanel = new GridBagLayout();
		gbl_jAttackResistPanel.columnWidths = new int[] { 0, 50, 50, 0, 0, 0, 0 };
		gbl_jAttackResistPanel.rowHeights = new int[] { 0, 0, 0, 50, 0, 0, 0 };
		gbl_jAttackResistPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_jAttackResistPanel.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jAttackResistPanel.setLayout(gbl_jAttackResistPanel);

		JPanel jPanelUnderWeaponInfo = new JPanel();
		jPanelUnderWeaponInfo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		jPanelUnderWeaponInfo.setBackground(Color.WHITE);
		GridBagConstraints gbc_jPanelUnderWeaponInfo = new GridBagConstraints();
		gbc_jPanelUnderWeaponInfo.gridwidth = 4;
		gbc_jPanelUnderWeaponInfo.insets = new Insets(0, 0, 5, 5);
		gbc_jPanelUnderWeaponInfo.fill = GridBagConstraints.BOTH;
		gbc_jPanelUnderWeaponInfo.gridx = 1;
		gbc_jPanelUnderWeaponInfo.gridy = 1;
		jAttackResistPanel.add(jPanelUnderWeaponInfo, gbc_jPanelUnderWeaponInfo);
		GridBagLayout gbl_jPanelUnderWeaponInfo = new GridBagLayout();
		gbl_jPanelUnderWeaponInfo.columnWidths = new int[] { 15, 0, 0, 15, 0 };
		gbl_jPanelUnderWeaponInfo.rowHeights = new int[] { 10, 0, 6, 0, 0, 0, 0, 5, 0, 10, 0 };
		gbl_jPanelUnderWeaponInfo.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_jPanelUnderWeaponInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		jPanelUnderWeaponInfo.setLayout(gbl_jPanelUnderWeaponInfo);

		lblWeaponAttack = new JLabel("Weapon attack");
		lblWeaponAttack.setFont(new Font("SansSerif", Font.BOLD, 14));
		GridBagConstraints gbc_lblWeaponAttack = new GridBagConstraints();
		gbc_lblWeaponAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWeaponAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeaponAttack.gridx = 1;
		gbc_lblWeaponAttack.gridy = 1;
		jPanelUnderWeaponInfo.add(lblWeaponAttack, gbc_lblWeaponAttack);

		lblAttackType = new JLabel("New label");
		lblAttackType.setFont(new Font("SansSerif", Font.BOLD, 14));
		GridBagConstraints gbc_lblAttackType = new GridBagConstraints();
		gbc_lblAttackType.anchor = GridBagConstraints.EAST;
		gbc_lblAttackType.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttackType.gridx = 2;
		gbc_lblAttackType.gridy = 1;
		jPanelUnderWeaponInfo.add(lblAttackType, gbc_lblAttackType);

		separator = new JSeparator();
		separator.setSize(new Dimension(0, 5));
		separator.setPreferredSize(new Dimension(0, 5));
		separator.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		jPanelUnderWeaponInfo.add(separator, gbc_separator);

		JLabel lblDamage = new JLabel("Damage:");
		GridBagConstraints gbc_lblDamage = new GridBagConstraints();
		gbc_lblDamage.insets = new Insets(0, 0, 5, 5);
		gbc_lblDamage.gridx = 1;
		gbc_lblDamage.gridy = 3;
		jPanelUnderWeaponInfo.add(lblDamage, gbc_lblDamage);

		lblDamageInfo = new JLabel("1-2");
		lblDamageInfo.setToolTipText("TEMP");
		GridBagConstraints gbc_lblDamageInfo = new GridBagConstraints();
		gbc_lblDamageInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblDamageInfo.gridx = 2;
		gbc_lblDamageInfo.gridy = 3;
		jPanelUnderWeaponInfo.add(lblDamageInfo, gbc_lblDamageInfo);

		JLabel lblAttacksPerSecond = new JLabel("Attacks per second:");
		GridBagConstraints gbc_lblAttacksPerSecond = new GridBagConstraints();
		gbc_lblAttacksPerSecond.insets = new Insets(0, 0, 5, 5);
		gbc_lblAttacksPerSecond.gridx = 1;
		gbc_lblAttacksPerSecond.gridy = 4;
		jPanelUnderWeaponInfo.add(lblAttacksPerSecond, gbc_lblAttacksPerSecond);

		lblAPS = new JLabel("1.00");
		GridBagConstraints gbc_lblAPS = new GridBagConstraints();
		gbc_lblAPS.insets = new Insets(0, 0, 5, 5);
		gbc_lblAPS.gridx = 2;
		gbc_lblAPS.gridy = 4;
		jPanelUnderWeaponInfo.add(lblAPS, gbc_lblAPS);

		JLabel lblHitsPerAttack = new JLabel("Hits per attack:");
		GridBagConstraints gbc_lblHitsPerAttack = new GridBagConstraints();
		gbc_lblHitsPerAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblHitsPerAttack.gridx = 1;
		gbc_lblHitsPerAttack.gridy = 5;
		jPanelUnderWeaponInfo.add(lblHitsPerAttack, gbc_lblHitsPerAttack);

		lblHitPerAttack = new JLabel("2");
		GridBagConstraints gbc_lblHitPerAttack = new GridBagConstraints();
		gbc_lblHitPerAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblHitPerAttack.gridx = 2;
		gbc_lblHitPerAttack.gridy = 5;
		jPanelUnderWeaponInfo.add(lblHitPerAttack, gbc_lblHitPerAttack);

		JLabel lblCritChance = new JLabel("Critical chance");
		GridBagConstraints gbc_lblCritChance = new GridBagConstraints();
		gbc_lblCritChance.insets = new Insets(0, 0, 5, 5);
		gbc_lblCritChance.gridx = 1;
		gbc_lblCritChance.gridy = 6;
		jPanelUnderWeaponInfo.add(lblCritChance, gbc_lblCritChance);

		lblCritChanceValue = new JLabel("3.00%");
		GridBagConstraints gbc_lblCritChanceValue = new GridBagConstraints();
		gbc_lblCritChanceValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCritChanceValue.gridx = 2;
		gbc_lblCritChanceValue.gridy = 6;
		jPanelUnderWeaponInfo.add(lblCritChanceValue, gbc_lblCritChanceValue);

		JLabel lblCritMultiplier = new JLabel("Critical multiplier");
		GridBagConstraints gbc_lblCritMultiplier = new GridBagConstraints();
		gbc_lblCritMultiplier.insets = new Insets(0, 0, 5, 5);
		gbc_lblCritMultiplier.gridx = 1;
		gbc_lblCritMultiplier.gridy = 7;
		jPanelUnderWeaponInfo.add(lblCritMultiplier, gbc_lblCritMultiplier);

		label = new JLabel("150%");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 7;
		jPanelUnderWeaponInfo.add(label, gbc_label);

		lblDPS = new JLabel("DPS");
		GridBagConstraints gbc_lblDPS = new GridBagConstraints();
		gbc_lblDPS.insets = new Insets(0, 0, 5, 5);
		gbc_lblDPS.gridx = 1;
		gbc_lblDPS.gridy = 8;
		jPanelUnderWeaponInfo.add(lblDPS, gbc_lblDPS);

		lblDPSValue = new JLabel("0");
		GridBagConstraints gbc_lblDPSValue = new GridBagConstraints();
		gbc_lblDPSValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblDPSValue.gridx = 2;
		gbc_lblDPSValue.gridy = 8;
		jPanelUnderWeaponInfo.add(lblDPSValue, gbc_lblDPSValue);

		jFireResistPanel = new JPanel();
		jFireResistPanel.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		jFireResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jFireResistPanel = new GridBagConstraints();
		gbc_jFireResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jFireResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jFireResistPanel.gridx = 1;
		gbc_jFireResistPanel.gridy = 3;
		jAttackResistPanel.add(jFireResistPanel, gbc_jFireResistPanel);
		GridBagLayout gbl_jFireResistPanel = new GridBagLayout();
		gbl_jFireResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jFireResistPanel.rowHeights = new int[] { 90, 0, 5, 0 };
		gbl_jFireResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jFireResistPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jFireResistPanel.setLayout(gbl_jFireResistPanel);

		lblForFireResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForFireResistSymbol = new GridBagConstraints();
		gbc_lblForFireResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForFireResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForFireResistSymbol.gridx = 0;
		gbc_lblForFireResistSymbol.gridy = 0;
		jFireResistPanel.add(lblForFireResistSymbol, gbc_lblForFireResistSymbol);

		lblForFireResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForFireResistValue = new GridBagConstraints();
		gbc_lblForFireResistValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblForFireResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForFireResistValue.gridx = 0;
		gbc_lblForFireResistValue.gridy = 1;
		jFireResistPanel.add(lblForFireResistValue, gbc_lblForFireResistValue);

		JPanel jIceResistPanel = new JPanel();
		jIceResistPanel.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		jIceResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jIceResistPanel = new GridBagConstraints();
		gbc_jIceResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jIceResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jIceResistPanel.gridx = 2;
		gbc_jIceResistPanel.gridy = 3;
		jAttackResistPanel.add(jIceResistPanel, gbc_jIceResistPanel);
		GridBagLayout gbl_jIceResistPanel = new GridBagLayout();
		gbl_jIceResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jIceResistPanel.rowHeights = new int[] { 90, 0, 0 };
		gbl_jIceResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jIceResistPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jIceResistPanel.setLayout(gbl_jIceResistPanel);

		lblForIceResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForIceResistSymbol = new GridBagConstraints();
		gbc_lblForIceResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForIceResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForIceResistSymbol.gridx = 0;
		gbc_lblForIceResistSymbol.gridy = 0;
		jIceResistPanel.add(lblForIceResistSymbol, gbc_lblForIceResistSymbol);

		lblForIceResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForIceResistValue = new GridBagConstraints();
		gbc_lblForIceResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForIceResistValue.gridx = 0;
		gbc_lblForIceResistValue.gridy = 1;
		jIceResistPanel.add(lblForIceResistValue, gbc_lblForIceResistValue);

		JPanel jEarthResistPanel = new JPanel();
		jEarthResistPanel.setBorder(new LineBorder(new Color(102, 51, 0), 1, true));
		jEarthResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEarthResistPanel = new GridBagConstraints();
		gbc_jEarthResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEarthResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jEarthResistPanel.gridx = 3;
		gbc_jEarthResistPanel.gridy = 3;
		jAttackResistPanel.add(jEarthResistPanel, gbc_jEarthResistPanel);
		GridBagLayout gbl_jEarthResistPanel = new GridBagLayout();
		gbl_jEarthResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jEarthResistPanel.rowHeights = new int[] { 90, 0, 0 };
		gbl_jEarthResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jEarthResistPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jEarthResistPanel.setLayout(gbl_jEarthResistPanel);

		lblForEarthResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForEarthResistSymbol = new GridBagConstraints();
		gbc_lblForEarthResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForEarthResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForEarthResistSymbol.gridx = 0;
		gbc_lblForEarthResistSymbol.gridy = 0;
		jEarthResistPanel.add(lblForEarthResistSymbol, gbc_lblForEarthResistSymbol);

		lblForEarthResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForEarthResistValue = new GridBagConstraints();
		gbc_lblForEarthResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForEarthResistValue.gridx = 0;
		gbc_lblForEarthResistValue.gridy = 1;
		jEarthResistPanel.add(lblForEarthResistValue, gbc_lblForEarthResistValue);

		JPanel jLightningResistPanel = new JPanel();
		jLightningResistPanel.setBorder(new LineBorder(new Color(255, 165, 0), 1, true));
		jLightningResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jLightningResistPanel = new GridBagConstraints();
		gbc_jLightningResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jLightningResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jLightningResistPanel.gridx = 4;
		gbc_jLightningResistPanel.gridy = 3;
		jAttackResistPanel.add(jLightningResistPanel, gbc_jLightningResistPanel);
		GridBagLayout gbl_jLightningResistPanel = new GridBagLayout();
		gbl_jLightningResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jLightningResistPanel.rowHeights = new int[] { 90, 0, 0 };
		gbl_jLightningResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jLightningResistPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jLightningResistPanel.setLayout(gbl_jLightningResistPanel);

		lblForLightningResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForLightningResistSymbol = new GridBagConstraints();
		gbc_lblForLightningResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForLightningResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForLightningResistSymbol.gridx = 0;
		gbc_lblForLightningResistSymbol.gridy = 0;
		jLightningResistPanel.add(lblForLightningResistSymbol, gbc_lblForLightningResistSymbol);

		lblForLightningResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForLightningResistValue = new GridBagConstraints();
		gbc_lblForLightningResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForLightningResistValue.gridx = 0;
		gbc_lblForLightningResistValue.gridy = 1;
		jLightningResistPanel.add(lblForLightningResistValue, gbc_lblForLightningResistValue);

		JPanel jPhysicalResistPanel = new JPanel();
		jPhysicalResistPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		jPhysicalResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jPhysicalResistPanel = new GridBagConstraints();
		gbc_jPhysicalResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jPhysicalResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jPhysicalResistPanel.gridx = 1;
		gbc_jPhysicalResistPanel.gridy = 4;
		jAttackResistPanel.add(jPhysicalResistPanel, gbc_jPhysicalResistPanel);
		GridBagLayout gbl_jPhysicalResistPanel = new GridBagLayout();
		gbl_jPhysicalResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jPhysicalResistPanel.rowHeights = new int[] { 90, 0, 5, 0 };
		gbl_jPhysicalResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jPhysicalResistPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		jPhysicalResistPanel.setLayout(gbl_jPhysicalResistPanel);

		lblForPhysicalResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForPhysicalResistSymbol = new GridBagConstraints();
		gbc_lblForPhysicalResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForPhysicalResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForPhysicalResistSymbol.gridx = 0;
		gbc_lblForPhysicalResistSymbol.gridy = 0;
		jPhysicalResistPanel.add(lblForPhysicalResistSymbol, gbc_lblForPhysicalResistSymbol);

		lblForPhysicalResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForPhysicalResistValue = new GridBagConstraints();
		gbc_lblForPhysicalResistValue.insets = new Insets(0, 0, 5, 0);
		gbc_lblForPhysicalResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForPhysicalResistValue.gridx = 0;
		gbc_lblForPhysicalResistValue.gridy = 1;
		jPhysicalResistPanel.add(lblForPhysicalResistValue, gbc_lblForPhysicalResistValue);

		JPanel jDarkResistPanel = new JPanel();
		jDarkResistPanel.setBorder(new LineBorder(new Color(75, 0, 130), 1, true));
		jDarkResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jDarkResistPanel = new GridBagConstraints();
		gbc_jDarkResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jDarkResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jDarkResistPanel.gridx = 2;
		gbc_jDarkResistPanel.gridy = 4;
		jAttackResistPanel.add(jDarkResistPanel, gbc_jDarkResistPanel);
		GridBagLayout gbl_jDarkResistPanel = new GridBagLayout();
		gbl_jDarkResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jDarkResistPanel.rowHeights = new int[] { 90, 0, 0 };
		gbl_jDarkResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jDarkResistPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jDarkResistPanel.setLayout(gbl_jDarkResistPanel);

		lblForDarkResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForDarkResistSymbol = new GridBagConstraints();
		gbc_lblForDarkResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForDarkResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForDarkResistSymbol.gridx = 0;
		gbc_lblForDarkResistSymbol.gridy = 0;
		jDarkResistPanel.add(lblForDarkResistSymbol, gbc_lblForDarkResistSymbol);

		lblForDarkResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForDarkResistValue = new GridBagConstraints();
		gbc_lblForDarkResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForDarkResistValue.gridx = 0;
		gbc_lblForDarkResistValue.gridy = 1;
		jDarkResistPanel.add(lblForDarkResistValue, gbc_lblForDarkResistValue);

		JPanel jRadianceResistPanel = new JPanel();
		jRadianceResistPanel.setBorder(new LineBorder(new Color(255, 255, 102), 1, true));
		jRadianceResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jRadianceResistPanel = new GridBagConstraints();
		gbc_jRadianceResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jRadianceResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jRadianceResistPanel.gridx = 3;
		gbc_jRadianceResistPanel.gridy = 4;
		jAttackResistPanel.add(jRadianceResistPanel, gbc_jRadianceResistPanel);
		GridBagLayout gbl_jRadianceResistPanel = new GridBagLayout();
		gbl_jRadianceResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jRadianceResistPanel.rowHeights = new int[] { 90, 0, 0 };
		gbl_jRadianceResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jRadianceResistPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jRadianceResistPanel.setLayout(gbl_jRadianceResistPanel);

		lblForRadianceResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForRadianceResistSymbol = new GridBagConstraints();
		gbc_lblForRadianceResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForRadianceResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForRadianceResistSymbol.gridx = 0;
		gbc_lblForRadianceResistSymbol.gridy = 0;
		jRadianceResistPanel.add(lblForRadianceResistSymbol, gbc_lblForRadianceResistSymbol);

		lblForRadianceResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForRadianceResistValue = new GridBagConstraints();
		gbc_lblForRadianceResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForRadianceResistValue.gridx = 0;
		gbc_lblForRadianceResistValue.gridy = 1;
		jRadianceResistPanel.add(lblForRadianceResistValue, gbc_lblForRadianceResistValue);

		JPanel jPoisonResistPanel = new JPanel();
		jPoisonResistPanel.setBorder(new LineBorder(new Color(0, 153, 0), 1, true));
		jPoisonResistPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jPoisonResistPanel = new GridBagConstraints();
		gbc_jPoisonResistPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jPoisonResistPanel.fill = GridBagConstraints.BOTH;
		gbc_jPoisonResistPanel.gridx = 4;
		gbc_jPoisonResistPanel.gridy = 4;
		jAttackResistPanel.add(jPoisonResistPanel, gbc_jPoisonResistPanel);
		GridBagLayout gbl_jPoisonResistPanel = new GridBagLayout();
		gbl_jPoisonResistPanel.columnWidths = new int[] { 90, 0 };
		gbl_jPoisonResistPanel.rowHeights = new int[] { 90, 0, 0 };
		gbl_jPoisonResistPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_jPoisonResistPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		jPoisonResistPanel.setLayout(gbl_jPoisonResistPanel);

		lblForPoisonResistSymbol = new JLabel("");
		GridBagConstraints gbc_lblForPoisonResistSymbol = new GridBagConstraints();
		gbc_lblForPoisonResistSymbol.fill = GridBagConstraints.BOTH;
		gbc_lblForPoisonResistSymbol.insets = new Insets(0, 0, 5, 0);
		gbc_lblForPoisonResistSymbol.gridx = 0;
		gbc_lblForPoisonResistSymbol.gridy = 0;
		jPoisonResistPanel.add(lblForPoisonResistSymbol, gbc_lblForPoisonResistSymbol);

		lblForPoisonResistValue = new JLabel("0%");
		GridBagConstraints gbc_lblForPoisonResistValue = new GridBagConstraints();
		gbc_lblForPoisonResistValue.fill = GridBagConstraints.VERTICAL;
		gbc_lblForPoisonResistValue.gridx = 0;
		gbc_lblForPoisonResistValue.gridy = 1;
		jPoisonResistPanel.add(lblForPoisonResistValue, gbc_lblForPoisonResistValue);

		jEquipmentPanel = new CharacterEquipmentPanel(this);
		jCharacterTabbedPanel.addTab("Equipment", null, jEquipmentPanel, null);

		JPanel jSkillsPanel = new JPanel();
		jSkillsPanel.setOpaque(false);
		jSkillsPanel.setBackground(Color.WHITE);
		jCharacterTabbedPanel.addTab("Skills", null, jSkillsPanel, null);
		jSkillsPanel.setLayout(new BorderLayout(0, 0));

		skillsPanel = new SkillsPanel(character);
		jSkillsPanel.add(skillsPanel);

		jPassivesPanel = new PassivesPanel(character);
		jCharacterTabbedPanel.addTab("Passives", null, jPassivesPanel, null);

		initializeData();
	}

	protected void buttonClicked(AttributesEnum attribute) {
		character.getAttributes().levelUpAttribute(attribute);
		character.refreshCharacter();
	}

	private void initializeData() {
		jCharNameLabel.setText(character.getName());
		jProfessionLabel.setText(character.getProffesion().getName());

		refreshExperienceThings();
		refreshAttributesThings();
		refreshSecondaryStatsThings();
		addIconsForResistsPanels();
	}

	private void addIconsForResistsPanels() {
		try {
			int height = 90;
			int width = 90;

			Image imageIcon = ImageIO.read(new File("res/resistsIcons/physical.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon imgToIcon = new ImageIcon(imageIcon);
			lblForPhysicalResistSymbol.setIcon(imgToIcon);

			imageIcon = ImageIO.read(new File("res/resistsIcons/fire.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);
			lblForFireResistSymbol.setIcon(imgToIcon);

			imageIcon = ImageIO.read(new File("res/resistsIcons/ice.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);
			lblForIceResistSymbol.setIcon(imgToIcon);

			imageIcon = ImageIO.read(new File("res/resistsIcons/earth.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);
			lblForEarthResistSymbol.setIcon(imgToIcon);

			imageIcon = ImageIO.read(new File("res/resistsIcons/lightning.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);
			lblForLightningResistSymbol.setIcon(imgToIcon);

			imageIcon = ImageIO.read(new File("res/resistsIcons/darkness.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);
			lblForDarkResistSymbol.setIcon(imgToIcon);

			imageIcon = ImageIO.read(new File("res/resistsIcons/radiance.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);
			lblForRadianceResistSymbol.setIcon(imgToIcon);

			imageIcon = ImageIO.read(new File("res/resistsIcons/poison.png"));
			imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);
			lblForPoisonResistSymbol.setIcon(imgToIcon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refresh() {
		refreshAttributesThings();
		refreshExperienceThings();
		refreshSecondaryStatsThings();
	}

	public void refreshExperienceThings() {
		jLevelLabel.setText(Integer.toString(character.getExperience().getLevel()));
		jExperienceBar.setValue((int) character.getExperience().getExperience());
		jExperienceBar.setMaximum(character.getExperience().checkExpToNextLevel());
		jExperienceBar.setString("Experience: " + character.getExperience().getExperience() + "/"
				+ character.getExperience().checkExpToNextLevel());
	}

	public void refreshAttributesThings() {
		jStrengthValueLabel
				.setText(Integer.toString(character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum()));
		jDexterityValueLabel
				.setText(Integer.toString(character.getAttributes().getAttribute(AttributesEnum.DEXTERITY).getSum()));
		jIntelligenceValueLabel.setText(
				Integer.toString(character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum()));
		jSpeedValueLabel
				.setText(Integer.toString(character.getAttributes().getAttribute(AttributesEnum.SPEED).getSum()));
		jVitalityValueLabel
				.setText(Integer.toString(character.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum()));
		jWisdomValueLabel
				.setText(Integer.toString(character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum()));
		jLuckValueLabel.setText(Integer.toString(character.getAttributes().getAttribute(AttributesEnum.LUCK).getSum()));

		jAttackLabel.setText(MyStringFormatter.formatDouble(character.getStatistics().getAttack().getTotal(), 0));
		jDefenceLabel.setText(MyStringFormatter.formatDouble(character.getStatistics().getDefence().getTotal(), 0));
		jDodgeLabel.setText(MyStringFormatter.formatDoubleAsPercentage(character.getStatistics().getDodge().getTotal()
				/ Math.sqrt(character.getPlayerParty().getData().getMaze().getLevel()), 2));
		jArmorLabel.setText(MyStringFormatter.formatDouble(character.getStatistics().getArmor().getTotal(), 0));

		jHealthLabel.setText(Integer.toString(character.getHealth().getMaxValue()));
		jHealthRegenerationLabel
				.setText(MyStringFormatter.formatDouble(character.getHealth().getHealthRegenPerSecond(), 2) + "/s");
		jManaLabel.setText(Integer.toString(character.getMana().getMaxValue()));
		jManaRegenerationLabel
				.setText(MyStringFormatter.formatDouble(character.getMana().getManaRegenPerSecond(), 2) + "/s");

		jAttributePoints.setText(Integer.toString(character.getAttributes().getAttributePoints()));
		jBlockChancelabel.setText(MyStringFormatter.formatDoubleAsPercentage(character.getBlock().getBlockChance(), 0));

		jStrengthButton.setText("<html><center>Add strength<br>\r\nPoints needed "
				+ character.getAttributes().checkPointNeededForLevelAttribute(AttributesEnum.STRENGTH)
				+ "</center></html>");
		jDexterityButton.setText("<html><center>Add dexterity<br>\r\nPoints needed "
				+ character.getAttributes().checkPointNeededForLevelAttribute(AttributesEnum.DEXTERITY)
				+ "</center></html>");
		jIntelligenceButton.setText("<html><center>Add intelligence<br>\r\nPoints needed "
				+ character.getAttributes().checkPointNeededForLevelAttribute(AttributesEnum.INTELLIGENCE)
				+ "</center></html>");
		jSpeedButton.setText("<html><center>Add speed<br>\r\nPoints needed "
				+ character.getAttributes().checkPointNeededForLevelAttribute(AttributesEnum.SPEED)
				+ "</center></html>");
		jVitalityButton.setText("<html><center>Add vitality<br>\r\nPoints needed "
				+ character.getAttributes().checkPointNeededForLevelAttribute(AttributesEnum.VITALITY)
				+ "</center></html>");
		jWisdomButton.setText("<html><center>Add wisdom<br>\r\nPoints needed "
				+ character.getAttributes().checkPointNeededForLevelAttribute(AttributesEnum.WISDOM)
				+ "</center></html>");
		jLuckButton.setText("<html><center>Add luck<br>\r\nPoints needed "
				+ character.getAttributes().checkPointNeededForLevelAttribute(AttributesEnum.LUCK)
				+ "</center></html>");
	}

	private void refreshSecondaryStatsThings() {
		lblForPhysicalResistValue.setText(character.getResist(DamageTypes.PHYSICAL).toString());
		lblForFireResistValue.setText(character.getResist(DamageTypes.FIRE).toString());
		lblForIceResistValue.setText(character.getResist(DamageTypes.ICE).toString());
		lblForEarthResistValue.setText(character.getResist(DamageTypes.EARTH).toString());
		lblForLightningResistValue.setText(character.getResist(DamageTypes.LIGHTNING).toString());
		lblForDarkResistValue.setText(character.getResist(DamageTypes.DARKNESS).toString());
		lblForRadianceResistValue.setText(character.getResist(DamageTypes.RADIANCE).toString());
		lblForPoisonResistValue.setText(character.getResist(DamageTypes.POISON).toString());

		Skill skill = character.getSkills().getMapOfAttacks().get("weapon");
		lblAttackType.setText(skill.getSkillType() + " " + skill.getDamageTypes());
		lblDamageInfo.setText(skill.getMinDamage() + "-" + skill.getMaxDamage());
		lblAPS.setText(MyStringFormatter.formatAsAPS(skill.getCooldownActually(), 3));
		lblHitPerAttack.setText(Integer.toString(skill.getNumberOfAttacks()));
		lblCritChanceValue.setText(MyStringFormatter.formatDoubleAsPercentage(skill.getCritChance(), 1));
		label.setText(MyStringFormatter.formatDoubleAsPercentage(skill.getCritMultiplier(), 0));
	}

	public CharacterEquipmentPanel getjEquipmentPanel() {
		return jEquipmentPanel;
	}

	public Player getCharacter() {
		return character;
	}

	public SkillsPanel getSkillsPanel() {
		return skillsPanel;
	}

}
