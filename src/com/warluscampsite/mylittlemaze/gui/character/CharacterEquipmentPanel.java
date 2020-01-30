package com.warluscampsite.mylittlemaze.gui.character;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;

public class CharacterEquipmentPanel extends JPanel {
	private static final long serialVersionUID = 3706293883068796528L;

	CharacterPanel characterPanel;

	Map<ItemTypes, Item> equipment;

	private JLabel helmetLbl;
	private JLabel firstWeaponLbl;
	private JLabel amuletLbl;
	private JLabel shouldersLbl;
	private JLabel secondWeaponLbl;
	private JLabel firstRingLbl;
	private JLabel bodyArmorLbl;
	private JLabel secondRingLbl;
	private JLabel glovesLbl;
	private JLabel beltLbl;
	private JLabel legsLbl;
	private JLabel bootsLbl;

	JPanel jEquippedBootsPanel;

	final int width = 78;
	final int height = 78;

	/**
	 * Create the panel.
	 * 
	 * @param characterPanel
	 */
	public CharacterEquipmentPanel(CharacterPanel characterPanel) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setOpaque(false);
		this.characterPanel = characterPanel;

		new JPanel();
		this.setBackground(new Color(205, 133, 63));
		GridBagLayout gbl_jEquipmentPanel = new GridBagLayout();
		gbl_jEquipmentPanel.columnWidths = new int[] { 10, 82, 82, 82, 82, 82, 10, 0 };
		gbl_jEquipmentPanel.rowHeights = new int[] { 10, 82, 82, 82, 82, 82, 82, 10, 0 };
		gbl_jEquipmentPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_jEquipmentPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.setLayout(gbl_jEquipmentPanel);

		JPanel jEquippedHelmetPanel = new JPanel();
		jEquippedHelmetPanel.setOpaque(false);
		jEquippedHelmetPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedHelmetPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedHelmetPanel = new GridBagConstraints();
		gbc_jEquippedHelmetPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedHelmetPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedHelmetPanel.gridx = 3;
		gbc_jEquippedHelmetPanel.gridy = 1;
		this.add(jEquippedHelmetPanel, gbc_jEquippedHelmetPanel);
		jEquippedHelmetPanel.setLayout(new BorderLayout(0, 0));

		helmetLbl = new JLabel("") {
			private static final long serialVersionUID = -4050388720639660416L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedHelmetPanel.add(helmetLbl);

		JPanel jEquippedAmuletPanel = new JPanel();
		jEquippedAmuletPanel.setOpaque(false);
		jEquippedAmuletPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedAmuletPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedAmuletPanel = new GridBagConstraints();
		gbc_jEquippedAmuletPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedAmuletPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedAmuletPanel.gridx = 3;
		gbc_jEquippedAmuletPanel.gridy = 2;
		this.add(jEquippedAmuletPanel, gbc_jEquippedAmuletPanel);
		jEquippedAmuletPanel.setLayout(new BorderLayout(0, 0));

		amuletLbl = new JLabel("") {
			private static final long serialVersionUID = -7284721996466322509L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedAmuletPanel.add(amuletLbl);

		JPanel jEquippedShouldersPanel = new JPanel();
		jEquippedShouldersPanel.setOpaque(false);
		jEquippedShouldersPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedShouldersPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedShouldersPanel = new GridBagConstraints();
		gbc_jEquippedShouldersPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedShouldersPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedShouldersPanel.gridx = 4;
		gbc_jEquippedShouldersPanel.gridy = 2;
		this.add(jEquippedShouldersPanel, gbc_jEquippedShouldersPanel);
		jEquippedShouldersPanel.setLayout(new BorderLayout(0, 0));

		shouldersLbl = new JLabel("") {
			private static final long serialVersionUID = -9097982097714722462L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedShouldersPanel.add(shouldersLbl, BorderLayout.NORTH);

		JPanel jEquippedFirstWeaponPanel = new JPanel();
		jEquippedFirstWeaponPanel.setOpaque(false);
		jEquippedFirstWeaponPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedFirstWeaponPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedFirstWeaponPanel = new GridBagConstraints();
		gbc_jEquippedFirstWeaponPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedFirstWeaponPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedFirstWeaponPanel.gridx = 1;
		gbc_jEquippedFirstWeaponPanel.gridy = 3;
		this.add(jEquippedFirstWeaponPanel, gbc_jEquippedFirstWeaponPanel);
		jEquippedFirstWeaponPanel.setLayout(new BorderLayout(0, 0));

		firstWeaponLbl = new JLabel("") {
			private static final long serialVersionUID = 6907711866029274412L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedFirstWeaponPanel.add(firstWeaponLbl, BorderLayout.CENTER);

		JPanel jEquippedBodyArmorPanel = new JPanel();
		jEquippedBodyArmorPanel.setOpaque(false);
		jEquippedBodyArmorPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedBodyArmorPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedBodyArmorPanel = new GridBagConstraints();
		gbc_jEquippedBodyArmorPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedBodyArmorPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedBodyArmorPanel.gridx = 3;
		gbc_jEquippedBodyArmorPanel.gridy = 3;
		this.add(jEquippedBodyArmorPanel, gbc_jEquippedBodyArmorPanel);
		jEquippedBodyArmorPanel.setLayout(new BorderLayout(0, 0));

		bodyArmorLbl = new JLabel("") {
			private static final long serialVersionUID = 7967861470981944050L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedBodyArmorPanel.add(bodyArmorLbl, BorderLayout.CENTER);

		JPanel jEquippedSecondWeaponPanel = new JPanel();
		jEquippedSecondWeaponPanel.setOpaque(false);
		jEquippedSecondWeaponPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedSecondWeaponPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedSecondWeaponPanel = new GridBagConstraints();
		gbc_jEquippedSecondWeaponPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedSecondWeaponPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedSecondWeaponPanel.gridx = 5;
		gbc_jEquippedSecondWeaponPanel.gridy = 3;
		this.add(jEquippedSecondWeaponPanel, gbc_jEquippedSecondWeaponPanel);
		jEquippedSecondWeaponPanel.setLayout(new BorderLayout(0, 0));

		secondWeaponLbl = new JLabel("") {
			private static final long serialVersionUID = 437460337359641698L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedSecondWeaponPanel.add(secondWeaponLbl, BorderLayout.CENTER);

		JPanel jEquippedFirstRingPanel = new JPanel();
		jEquippedFirstRingPanel.setOpaque(false);
		jEquippedFirstRingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedFirstRingPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedFirstRingPanel = new GridBagConstraints();
		gbc_jEquippedFirstRingPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedFirstRingPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedFirstRingPanel.gridx = 2;
		gbc_jEquippedFirstRingPanel.gridy = 4;
		this.add(jEquippedFirstRingPanel, gbc_jEquippedFirstRingPanel);
		jEquippedFirstRingPanel.setLayout(new BorderLayout(0, 0));

		firstRingLbl = new JLabel("") {
			private static final long serialVersionUID = -2350269823261812769L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedFirstRingPanel.add(firstRingLbl, BorderLayout.CENTER);

		JPanel jEquippedBeltPanel = new JPanel();
		jEquippedBeltPanel.setOpaque(false);
		jEquippedBeltPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedBeltPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedBeltPanel = new GridBagConstraints();
		gbc_jEquippedBeltPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedBeltPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedBeltPanel.gridx = 3;
		gbc_jEquippedBeltPanel.gridy = 4;
		this.add(jEquippedBeltPanel, gbc_jEquippedBeltPanel);
		jEquippedBeltPanel.setLayout(new BorderLayout(0, 0));

		beltLbl = new JLabel("") {
			private static final long serialVersionUID = 6810106883135245755L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedBeltPanel.add(beltLbl, BorderLayout.CENTER);

		JPanel jEquippedSecondRingPanel = new JPanel();
		jEquippedSecondRingPanel.setOpaque(false);
		jEquippedSecondRingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedSecondRingPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedSecondRingPanel = new GridBagConstraints();
		gbc_jEquippedSecondRingPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedSecondRingPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedSecondRingPanel.gridx = 4;
		gbc_jEquippedSecondRingPanel.gridy = 4;
		this.add(jEquippedSecondRingPanel, gbc_jEquippedSecondRingPanel);
		jEquippedSecondRingPanel.setLayout(new BorderLayout(0, 0));

		secondRingLbl = new JLabel("") {
			private static final long serialVersionUID = -6247519349792322943L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedSecondRingPanel.add(secondRingLbl, BorderLayout.CENTER);

		JPanel jEquippedGlovesPanel = new JPanel();
		jEquippedGlovesPanel.setOpaque(false);
		jEquippedGlovesPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedGlovesPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedGlovesPanel = new GridBagConstraints();
		gbc_jEquippedGlovesPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedGlovesPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedGlovesPanel.gridx = 1;
		gbc_jEquippedGlovesPanel.gridy = 5;
		this.add(jEquippedGlovesPanel, gbc_jEquippedGlovesPanel);
		jEquippedGlovesPanel.setLayout(new BorderLayout(0, 0));

		glovesLbl = new JLabel("") {
			private static final long serialVersionUID = 8214322013219079443L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedGlovesPanel.add(glovesLbl, BorderLayout.CENTER);

		JPanel jEquippedLegsPanel = new JPanel();
		jEquippedLegsPanel.setOpaque(false);
		jEquippedLegsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedLegsPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedLegsPanel = new GridBagConstraints();
		gbc_jEquippedLegsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedLegsPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedLegsPanel.gridx = 3;
		gbc_jEquippedLegsPanel.gridy = 5;
		this.add(jEquippedLegsPanel, gbc_jEquippedLegsPanel);
		jEquippedLegsPanel.setLayout(new BorderLayout(0, 0));

		legsLbl = new JLabel("") {
			private static final long serialVersionUID = 2290088918920040123L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedLegsPanel.add(legsLbl, BorderLayout.CENTER);

		jEquippedBootsPanel = new JPanel();
		jEquippedBootsPanel.setOpaque(false);
		jEquippedBootsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jEquippedBootsPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_jEquippedBootsPanel = new GridBagConstraints();
		gbc_jEquippedBootsPanel.insets = new Insets(0, 0, 5, 5);
		gbc_jEquippedBootsPanel.fill = GridBagConstraints.BOTH;
		gbc_jEquippedBootsPanel.gridx = 3;
		gbc_jEquippedBootsPanel.gridy = 6;
		this.add(jEquippedBootsPanel, gbc_jEquippedBootsPanel);
		jEquippedBootsPanel.setLayout(new BorderLayout(0, 0));

		bootsLbl = new JLabel("") {
			private static final long serialVersionUID = -5793732985592441587L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		;
		jEquippedBootsPanel.add(bootsLbl, BorderLayout.CENTER);

	}

	public void addEquipedIcon(EquipmentSlots slot) {
		Image imageIcon = null;
		Item item = characterPanel.getCharacter().getEq().getEquipmentElement(slot);

		if (item != null) {
			try {
				imageIcon = ImageIO.read(new File(item.getItemBase().getPath()));
				imageIcon = imageIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			} catch (IOException e) {
				e.printStackTrace();
			}
			ImageIcon imgToIcon = new ImageIcon(imageIcon);
			String tooltip = MyTooltipFormatter.formatTooltipForItem(item);
			switch (slot) {
			case AMULET:
				amuletLbl.setToolTipText(tooltip);
				amuletLbl.setIcon(imgToIcon);
				break;

			case BELT:
				beltLbl.setToolTipText(tooltip);
				beltLbl.setIcon(imgToIcon);
				break;

			case BODY_ARMOR:
				bodyArmorLbl.setToolTipText(tooltip);
				bodyArmorLbl.setIcon(imgToIcon);
				break;

			case BOOTS:
				bootsLbl.setToolTipText(tooltip);
				bootsLbl.setIcon(imgToIcon);
				break;

			case FIRST_HAND:
				firstWeaponLbl.setToolTipText(tooltip);
				firstWeaponLbl.setIcon(imgToIcon);

				if (item.getItemBase().getItemType().equals(ItemTypes.TWO_HANDED_MAGIC)
						|| item.getItemBase().getItemType().equals(ItemTypes.TWO_HANDED_MELEE)
						|| item.getItemBase().getItemType().equals(ItemTypes.TWO_HANDED_RANGED)) {
					secondWeaponLbl.setToolTipText(tooltip);
					secondWeaponLbl.setIcon(imgToIcon);
				} else if (characterPanel.getCharacter().getEq()
						.getEquipmentElement(EquipmentSlots.SECOND_HAND) == null) {
					secondWeaponLbl.setToolTipText(null);
					secondWeaponLbl.setIcon(null);
				}
				break;

			case SECOND_HAND:
				secondWeaponLbl.setToolTipText(tooltip);
				secondWeaponLbl.setIcon(imgToIcon);

				item = characterPanel.getCharacter().getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
				if (item == null) {
					firstWeaponLbl.setIcon(null);
					firstWeaponLbl.setToolTipText(null);
				}
				break;

			case GLOVES:
				glovesLbl.setToolTipText(tooltip);
				glovesLbl.setIcon(imgToIcon);
				break;

			case HELMET:
				helmetLbl.setToolTipText(tooltip);
				helmetLbl.setIcon(imgToIcon);
				break;
			case LEGS:
				legsLbl.setToolTipText(tooltip);
				legsLbl.setIcon(imgToIcon);
				break;

			case RING:
				firstRingLbl.setToolTipText(tooltip);
				firstRingLbl.setIcon(imgToIcon);
				break;

			case SHOULDERS:
				shouldersLbl.setToolTipText(tooltip);
				shouldersLbl.setIcon(imgToIcon);
				break;

			default:
				break;

			}
		} else {
			switch (slot) {
			case AMULET:
				amuletLbl.setToolTipText(null);
				amuletLbl.setIcon(null);
				break;

			case BELT:
				beltLbl.setToolTipText(null);
				beltLbl.setIcon(null);
				break;

			case BODY_ARMOR:
				bodyArmorLbl.setToolTipText(null);
				bodyArmorLbl.setIcon(null);
				break;

			case BOOTS:
				bootsLbl.setToolTipText(null);
				bootsLbl.setIcon(null);
				break;

			case FIRST_HAND:
				firstWeaponLbl.setToolTipText(null);
				firstWeaponLbl.setIcon(null);

				if (characterPanel.getCharacter().getEq().getEquipmentElement(EquipmentSlots.SECOND_HAND) == null) {
					secondWeaponLbl.setToolTipText(null);
					secondWeaponLbl.setIcon(null);
				}
				break;

			case SECOND_HAND:
				secondWeaponLbl.setToolTipText(null);
				secondWeaponLbl.setIcon(null);

				item = characterPanel.getCharacter().getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
				if (item == null) {
					firstWeaponLbl.setIcon(null);
					firstWeaponLbl.setToolTipText(null);
				}
				break;

			case GLOVES:
				glovesLbl.setToolTipText(null);
				glovesLbl.setIcon(null);
				break;

			case HELMET:
				helmetLbl.setToolTipText(null);
				helmetLbl.setIcon(null);
				break;
			case LEGS:
				legsLbl.setToolTipText(null);
				legsLbl.setIcon(null);
				break;

			case RING:
				firstRingLbl.setToolTipText(null);
				firstRingLbl.setIcon(null);
				break;

			case SHOULDERS:
				shouldersLbl.setToolTipText(null);
				shouldersLbl.setIcon(null);
				break;

			default:
				break;

			}
		}

	}

}
