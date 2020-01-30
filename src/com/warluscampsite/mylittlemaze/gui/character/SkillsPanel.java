package com.warluscampsite.mylittlemaze.gui.character;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.gui.CompoundIcon;
import com.warluscampsite.mylittlemaze.gui.CompoundIcon.Axis;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;

public class SkillsPanel extends JPanel {
	private static final long serialVersionUID = 2925116132433252626L;

	Player player;
	Map<Integer, Skill> skillsList;

	final int FIRST_FULL = 64, SECOND_FULL = 174, THIRD_FULL = 325;

	private JButton firstSkillButton;
	private JButton firstSkill1R;
	private JButton firstSkill1L;
	private JProgressBar firstSkillProgressBar;
	private JButton firstSkill2R;
	private JButton firstSkill3R;
	private JButton firstSkill3L;
	private JButton firstSkill2L;
	private JButton secondSkill2R;
	private JButton secondSkill3L;
	private JButton secondSkill3R;
	private JButton secondSkill2L;
	private JButton secondSkillButton;
	private JButton secondSkill1R;
	private JButton secondSkill1L;
	private JButton thirdSkillButton;
	private JButton thirdSkill1L;
	private JButton thirdSkill1R;
	private JButton thirdSkill3R;
	private JButton thirdSkill3L;
	private JButton thirdSkill2R;
	private JButton thirdSkill2L;
	private JProgressBar secondSkillProgressBar;
	private JProgressBar thirdSkillProgressBar;

	/**
	 * Create the panel.
	 */
	public SkillsPanel(Player player) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setOpaque(false);
		this.player = player;
		skillsList = player.getSkills().getMapOfSkillsFromProfession();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 5, 134, 134, 134, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 3, 475, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new CardLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(102, 51, 0), 2, true));
		panel.add(layeredPane, "name_222410417796788");

		firstSkillButton = new JButton("") {
			private static final long serialVersionUID = 4852988694711032415L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				return tip;
			}
		};
		firstSkillButton.setOpaque(false);
		firstSkillButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				if (player.getSkills().getSkillPoints() > 0) {
					skillsList.get(i).addLevel();

					int level = skillsList.get(i).getLevel();
					firstSkillProgressBar.setValue(calcValue(level));
					firstSkillProgressBar.repaint();

					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_FIRST_UPGRADE()) {
						firstSkill1L.setEnabled(true);
						firstSkill1R.setEnabled(true);
					}
					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_SECOND_UPGRADE()) {
						firstSkill2L.setEnabled(true);
						firstSkill2R.setEnabled(true);
					}
					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_THIRD_UPGRADE()) {
						firstSkill3L.setEnabled(true);
						firstSkill3R.setEnabled(true);
					}
				}
			}
		});
		firstSkillButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				int i = 1;
				firstSkillButton.setToolTipText(skillsList.get(i).getSkillTooltip());
			}

		});
		firstSkillButton.setBounds(10, 10, 110, 110);
		layeredPane.add(firstSkillButton);

		firstSkillProgressBar = new JProgressBar();
		firstSkillProgressBar.setForeground(new Color(0, 0, 139));
		firstSkillProgressBar.setBackground(new Color(0, 191, 255));
		firstSkillProgressBar.setMaximum(325);
		firstSkillProgressBar.setValue(firstSkillProgressBar.getMaximum());
		firstSkillProgressBar.setOrientation(SwingConstants.VERTICAL);
		firstSkillProgressBar.setBounds(55, 120, 20, 325);
		layeredPane.add(firstSkillProgressBar);

		firstSkill1L = new JButton("") {
			private static final long serialVersionUID = -8756588081101226778L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		firstSkill1L.setEnabled(false);
		firstSkill1L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = 1;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_FIRST_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated1L(true);
					firstSkill1R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		firstSkill1L.setBounds(10, 140, 44, 44);
		firstSkill1L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(1).getSkillName1L()));
		layeredPane.add(firstSkill1L);

		firstSkill1R = new JButton("") {
			private static final long serialVersionUID = -7461884499244964113L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		firstSkill1R.setEnabled(false);
		firstSkill1R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = 1;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_FIRST_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated1R(true);
					firstSkill1L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		firstSkill1R.setBounds(76, 140, 44, 44);
		firstSkill1R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(1).getSkillName1R()));
		layeredPane.add(firstSkill1R);

		firstSkill2L = new JButton("") {
			private static final long serialVersionUID = -6642902083623694689L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		firstSkill2L.setEnabled(false);
		firstSkill2L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_SECOND_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated2L(true);
					firstSkill2R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		firstSkill2L.setBounds(10, 250, 44, 44);
		firstSkill2L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(1).getSkillName2L()));
		layeredPane.add(firstSkill2L);

		firstSkill2R = new JButton("") {
			private static final long serialVersionUID = 3063365322810380132L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		firstSkill2R.setEnabled(false);
		firstSkill2R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_SECOND_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated2R(true);
					firstSkill2L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		firstSkill2R.setBounds(76, 250, 44, 44);
		firstSkill2R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(1).getSkillName2R()));
		layeredPane.add(firstSkill2R);

		firstSkill3L = new JButton("") {
			private static final long serialVersionUID = 6858015400181155328L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		firstSkill3L.setEnabled(false);
		firstSkill3L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_THIRD_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated3L(true);
					firstSkill3R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		firstSkill3L.setBounds(10, 401, 44, 44);
		firstSkill3L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(1).getSkillName3L()));
		layeredPane.add(firstSkill3L);

		firstSkill3R = new JButton("") {
			private static final long serialVersionUID = 8157340847640973868L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		firstSkill3R.setEnabled(false);
		firstSkill3R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 1;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_THIRD_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated3R(true);
					firstSkill3L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		firstSkill3R.setBounds(76, 401, 44, 44);
		firstSkill3R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(1).getSkillName3R()));
		layeredPane.add(firstSkill3R);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new CardLayout(0, 0));

		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new LineBorder(new Color(102, 51, 0), 2, true));
		panel_1.add(layeredPane_1, "name_222865866070963");

		secondSkillButton = new JButton("") {
			private static final long serialVersionUID = -2755694587580791681L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		secondSkillButton.setOpaque(false);
		secondSkillButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				int i = 2;
				secondSkillButton.setToolTipText(skillsList.get(i).getSkillTooltip());
			}
		});
		secondSkillButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				if (player.getSkills().getSkillPoints() > 0) {
					skillsList.get(i).addLevel();

					int level = skillsList.get(i).getLevel();
					secondSkillProgressBar.setValue(calcValue(level));
					secondSkillProgressBar.repaint();

					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_FIRST_UPGRADE()) {
						secondSkill1L.setEnabled(true);
						secondSkill1R.setEnabled(true);
					}
					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_SECOND_UPGRADE()) {
						secondSkill2L.setEnabled(true);
						secondSkill2R.setEnabled(true);
					}
					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_THIRD_UPGRADE()) {
						secondSkill3L.setEnabled(true);
						secondSkill3R.setEnabled(true);
					}
				}
			}
		});
		secondSkillButton.setBounds(10, 10, 110, 110);
		layeredPane_1.add(secondSkillButton);

		secondSkillProgressBar = new JProgressBar();
		secondSkillProgressBar.setForeground(new Color(0, 0, 139));
		secondSkillProgressBar.setBackground(new Color(0, 191, 255));
		secondSkillProgressBar.setMaximum(325);
		secondSkillProgressBar.setValue(325);
		secondSkillProgressBar.setOrientation(SwingConstants.VERTICAL);
		secondSkillProgressBar.setBounds(55, 120, 20, 325);
		layeredPane_1.add(secondSkillProgressBar);

		secondSkill1L = new JButton("") {
			private static final long serialVersionUID = 4874595425901930645L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		secondSkill1L.setEnabled(false);
		secondSkill1L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_FIRST_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated1L(true);
					secondSkill1R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		secondSkill1L.setBounds(10, 140, 44, 44);
		secondSkill1L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(2).getSkillName1L()));
		layeredPane_1.add(secondSkill1L);

		secondSkill1R = new JButton("") {
			private static final long serialVersionUID = -9142657368460604953L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		secondSkill1R.setEnabled(false);
		secondSkill1R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_SECOND_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated1R(true);
					secondSkill1L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		secondSkill1R.setBounds(76, 140, 44, 44);
		secondSkill1R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(2).getSkillName1R()));
		layeredPane_1.add(secondSkill1R);

		secondSkill2L = new JButton("") {
			private static final long serialVersionUID = 4311102145660634925L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		secondSkill2L.setEnabled(false);
		secondSkill2L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_SECOND_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated2L(true);
					secondSkill2R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		secondSkill2L.setBounds(10, 250, 44, 44);
		secondSkill2L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(2).getSkillName2L()));
		layeredPane_1.add(secondSkill2L);

		secondSkill2R = new JButton("") {
			private static final long serialVersionUID = -8200395679669696671L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		secondSkill2R.setEnabled(false);
		secondSkill2R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_THIRD_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated2R(true);
					secondSkill2L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		secondSkill2R.setBounds(76, 250, 44, 44);
		secondSkill2R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(2).getSkillName2R()));
		layeredPane_1.add(secondSkill2R);

		secondSkill3L = new JButton("") {
			private static final long serialVersionUID = -4490738157200332066L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		secondSkill3L.setEnabled(false);
		secondSkill3L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_THIRD_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated3L(true);
					secondSkill3R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		secondSkill3L.setBounds(10, 401, 44, 44);
		secondSkill3L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(2).getSkillName3L()));
		layeredPane_1.add(secondSkill3L);

		secondSkill3R = new JButton("") {
			private static final long serialVersionUID = 9097608322935139657L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		secondSkill3R.setEnabled(false);
		secondSkill3R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 2;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_THIRD_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated3R(true);
					secondSkill3L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		secondSkill3R.setBounds(76, 401, 44, 44);
		secondSkill3R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(2).getSkillName3R()));
		layeredPane_1.add(secondSkill3R);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		panel_2.setLayout(new CardLayout(0, 0));

		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setBorder(new LineBorder(new Color(102, 51, 0), 2, true));
		panel_2.add(layeredPane_2, "name_222867124396728");

		thirdSkillButton = new JButton("") {
			private static final long serialVersionUID = 7866737203336656668L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		thirdSkillButton.setOpaque(false);
		thirdSkillButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				int i = 3;
				thirdSkillButton.setToolTipText(skillsList.get(i).getSkillTooltip());
			}
		});
		thirdSkillButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				if (player.getSkills().getSkillPoints() > 0) {
					skillsList.get(i).addLevel();
					
					int level = skillsList.get(i).getLevel();
					thirdSkillProgressBar.setValue(calcValue(level));
					thirdSkillProgressBar.repaint();

					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_FIRST_UPGRADE()) {
						thirdSkill1L.setEnabled(true);
						thirdSkill1R.setEnabled(true);
					}
					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_SECOND_UPGRADE()) {
						thirdSkill2L.setEnabled(true);
						thirdSkill2R.setEnabled(true);
					}
					if (level == skillsList.get(i).getLEVEL_NEEDS_FOR_THIRD_UPGRADE()) {
						thirdSkill3L.setEnabled(true);
						thirdSkill3R.setEnabled(true);
					}
				}
			}

		});
		thirdSkillButton.setBounds(10, 10, 110, 110);
		layeredPane_2.add(thirdSkillButton);

		thirdSkillProgressBar = new JProgressBar();
		thirdSkillProgressBar.setForeground(new Color(0, 0, 139));
		thirdSkillProgressBar.setBackground(new Color(0, 191, 255));
		thirdSkillProgressBar.setMaximum(325);
		thirdSkillProgressBar.setValue(325);
		thirdSkillProgressBar.setOrientation(SwingConstants.VERTICAL);
		thirdSkillProgressBar.setBounds(55, 120, 20, 325);
		layeredPane_2.add(thirdSkillProgressBar);

		thirdSkill1L = new JButton("") {
			private static final long serialVersionUID = -5615398293864591930L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		thirdSkill1L.setEnabled(false);
		thirdSkill1L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_FIRST_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated1L(true);
					thirdSkill1R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		thirdSkill1L.setBounds(10, 140, 44, 44);
		thirdSkill1L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(3).getSkillName1L()));
		layeredPane_2.add(thirdSkill1L);

		thirdSkill1R = new JButton("") {
			private static final long serialVersionUID = -443226392280262811L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		thirdSkill1R.setEnabled(false);
		thirdSkill1R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_FIRST_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated1R(true);
					thirdSkill1L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		thirdSkill1R.setBounds(76, 140, 44, 44);
		thirdSkill1R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(3).getSkillName1R()));
		layeredPane_2.add(thirdSkill1R);

		thirdSkill2L = new JButton("") {
			private static final long serialVersionUID = 50697191620536319L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		thirdSkill2L.setEnabled(false);
		thirdSkill2L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_SECOND_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated2L(true);
					thirdSkill2R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		thirdSkill2L.setBounds(10, 250, 44, 44);
		thirdSkill2L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(3).getSkillName2L()));
		layeredPane_2.add(thirdSkill2L);

		thirdSkill2R = new JButton("") {
			private static final long serialVersionUID = -809113217178711579L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		thirdSkill2R.setEnabled(false);
		thirdSkill2R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_SECOND_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated2R(true);
					thirdSkill2L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		thirdSkill2R.setBounds(76, 250, 44, 44);
		thirdSkill2R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(3).getSkillName2R()));
		layeredPane_2.add(thirdSkill2R);

		thirdSkill3L = new JButton("") {
			private static final long serialVersionUID = 3427778062209678515L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		thirdSkill3L.setEnabled(false);
		thirdSkill3L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_THIRD_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated3L(true);
					thirdSkill3R.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		thirdSkill3L.setBounds(10, 401, 44, 44);
		thirdSkill3L.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(3).getSkillName3L()));
		layeredPane_2.add(thirdSkill3L);

		thirdSkill3R = new JButton("") {
			private static final long serialVersionUID = -4788487601847931177L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		thirdSkill3R.setEnabled(false);
		thirdSkill3R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 3;
				Skill skill = skillsList.get(i);
				if (skill.getLEVEL_NEEDS_FOR_THIRD_UPGRADE() <= skill.getLevel()) {
					skillsList.get(i).setSkillActivated3R(true);
					thirdSkill3L.setEnabled(false);
					skillsList.get(i).refresh();
				}
			}
		});
		thirdSkill3R.setBounds(76, 401, 44, 44);
		thirdSkill3R.setToolTipText(MyTooltipFormatter.formatTooltipForUpgrade(skillsList.get(3).getSkillName3R()));
		layeredPane_2.add(thirdSkill3R);

		JLabel lblNewLabel = new JLabel("Points to spend: 0");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		addIconsBasedOnClass();
	}

	protected int calcValue(int level) {
		if (level >= 30)
			return 0;

		double value = THIRD_FULL;
		
		if (level <= 5)
			value -= level / 5. * FIRST_FULL;
		if (level > 5 && level <= 12)
			value -= level / 12. * SECOND_FULL;
		if (level > 12)
			value -= (SECOND_FULL) + ((level - 12) / 18. * (THIRD_FULL - SECOND_FULL));

		return (int) value;
	}

	private void addIconsBasedOnClass() {
		Image imageIcon = null;

		int bigButtonWidth = firstSkillButton.getWidth();
		int bigButtonHeight = firstSkillButton.getHeight();
		int smallButtonWidth = firstSkill1L.getHeight();
		int smallButtonHeight = firstSkill1L.getHeight();

		try {
			Image borderBigImage = ImageIO.read(new File("res/skills/skillBorder.png"))
					.getScaledInstance(bigButtonWidth, bigButtonHeight, Image.SCALE_SMOOTH);
			ImageIcon borderBigIcon = new ImageIcon(borderBigImage);

			CompoundIcon compoundIcon = null;
			// FIRST SKILL
			for (Entry<Integer, Skill> skill : skillsList.entrySet()) {
				if (skill.getKey() == 1) {
					imageIcon = ImageIO.read(new File(skill.getValue().getPathSkillToImage()));
					imageIcon = imageIcon.getScaledInstance(bigButtonWidth, bigButtonHeight, Image.SCALE_SMOOTH);
					compoundIcon = new CompoundIcon(Axis.Z_AXIS, new ImageIcon(imageIcon), borderBigIcon);
					firstSkillButton.setIcon(compoundIcon);

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill1L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					firstSkill1L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill1R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					firstSkill1R.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill2L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					firstSkill2L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill2R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					firstSkill2R.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill3L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					firstSkill3L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill3R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					firstSkill3R.setIcon(new ImageIcon(imageIcon));
				}
				if (skill.getKey() == 2) {
					imageIcon = ImageIO.read(new File(skill.getValue().getPathSkillToImage()));
					imageIcon = imageIcon.getScaledInstance(bigButtonWidth, bigButtonHeight, Image.SCALE_SMOOTH);
					compoundIcon = new CompoundIcon(Axis.Z_AXIS, new ImageIcon(imageIcon), borderBigIcon);
					secondSkillButton.setIcon(compoundIcon);

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill1L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					secondSkill1L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill1R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					secondSkill1R.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill2L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					secondSkill2L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill2R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					secondSkill2R.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill3L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					secondSkill3L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill3R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					secondSkill3R.setIcon(new ImageIcon(imageIcon));
				}
				if (skill.getKey() == 3) {
					imageIcon = ImageIO.read(new File(skill.getValue().getPathSkillToImage()));
					imageIcon = imageIcon.getScaledInstance(bigButtonWidth, bigButtonHeight, Image.SCALE_SMOOTH);
					compoundIcon = new CompoundIcon(Axis.Z_AXIS, new ImageIcon(imageIcon), borderBigIcon);
					thirdSkillButton.setIcon(compoundIcon);

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill1L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					thirdSkill1L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill1R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					thirdSkill1R.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill2L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					thirdSkill2L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill2R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					thirdSkill2R.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill3L()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					thirdSkill3L.setIcon(new ImageIcon(imageIcon));

					imageIcon = ImageIO.read(new File(skill.getValue().getSkill3R()));
					imageIcon = imageIcon.getScaledInstance(smallButtonWidth, smallButtonHeight, Image.SCALE_SMOOTH);
					thirdSkill3R.setIcon(new ImageIcon(imageIcon));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
