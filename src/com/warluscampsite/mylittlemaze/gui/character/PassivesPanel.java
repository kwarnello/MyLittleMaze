package com.warluscampsite.mylittlemaze.gui.character;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.warluscampsite.mylittlemaze.playerteam.Player;

public class PassivesPanel extends JPanel {
	private static final long serialVersionUID = -5895820683961957514L;

	Player player;

	final int FIRST_FULL = 30, SECOND_FULL = 169, THIRD_FULL = 369;

	final double FIRST_LEVEL_REQUIRMENTS = 10, SECOND_LEVEL_REQUIRMENTS = 30, THIRD_LEVEL_REQUIRMENTS = 50;
	private JButton jFirstPassiveButton;
	private JButton j2LPassiveButton;
	private JButton j2RPassiveButton;
	private JButton j3LLPassiveButton;
	private JButton j3RRPassiveButton;
	private JButton j3RLPassiveButton;
	private JButton j3LRPassiveButton;
	private JProgressBar progressBar;

	/**
	 * Create the panel.
	 */
	public PassivesPanel(Player player) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setOpaque(false);
		setBackground(Color.GRAY);
		this.player = player;

		Image imageIcon = null;
		ImageIcon imgToIcon = null;
		ImageIcon imgToIconBigger = null;
		try {
			imageIcon = ImageIO.read(new File("res/skills/passivesIcon.png"));
			imageIcon = imageIcon.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
			imgToIcon = new ImageIcon(imageIcon);

			imageIcon = ImageIO.read(new File("res/skills/passivesIcon.png"));
			imageIcon = imageIcon.getScaledInstance(47, 47, Image.SCALE_SMOOTH);
			imgToIconBigger = new ImageIcon(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 8, 180, 180, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 440, 5, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(null);
		panel.setBackground(new Color(204, 102, 51));
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 395, 40, 2);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 255, 40, 2);
		panel.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(10, 94, 40, 2);
		panel.add(separator_2);

		progressBar = new JProgressBar();
		progressBar.setMaximum(369);
		progressBar.setValue(0);
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(30, 55, 20, 369);
		panel.add(progressBar);

		JPanel panel_1 = new JPanel() {
			private static final long serialVersionUID = 2990656355286727696L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(3));
				g2.draw(new Line2D.Float(142, 322, 67, 182));
				g2.draw(new Line2D.Float(142, 322, 217, 182));

				g2.draw(new Line2D.Float(67, 182, 22, 22));
				g2.draw(new Line2D.Float(67, 182, 120, 22));

				g2.draw(new Line2D.Float(217, 182, 178, 22));
				g2.draw(new Line2D.Float(217, 182, 262, 22));

			}
		};
		panel_1.setOpaque(false);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(60, 55, 285, 369);
		panel.add(panel_1);
		panel_1.setLayout(null);

		jFirstPassiveButton = new JButton("") {
			private static final long serialVersionUID = 6450683432817183034L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		jFirstPassiveButton.setContentAreaFilled(false);
		jFirstPassiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (player.getExperience().getLevel() >= FIRST_LEVEL_REQUIRMENTS)
					player.getProffesion().getPassives(1).setActivated(true);
				player.refreshCharacter();
			}
		});
		jFirstPassiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				jFirstPassiveButton.setBounds(119, 299, 45, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jFirstPassiveButton.setBounds(120, 300, 45, 45);
			}
		});
		jFirstPassiveButton.setIcon(imgToIcon);
		jFirstPassiveButton.setRolloverIcon(imgToIconBigger);
		jFirstPassiveButton.setMargin(new Insets(0, 0, 0, 0));
		jFirstPassiveButton.setBorder(null);
		jFirstPassiveButton.setBounds(120, 300, 45, 45);
		jFirstPassiveButton.setToolTipText(player.getProffesion().getPassives(1).getDescription());
		panel_1.add(jFirstPassiveButton);

		j2LPassiveButton = new JButton("") {
			private static final long serialVersionUID = 3737931028866431437L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		j2LPassiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getExperience().getLevel() >= SECOND_LEVEL_REQUIRMENTS) {
					player.getProffesion().getPassives(2).setActivated(true);
					j2RPassiveButton.setEnabled(false);
					j3RRPassiveButton.setEnabled(false);
					j3RLPassiveButton.setEnabled(false);
					player.refreshCharacter();
				}
			}
		});
		j2LPassiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				j2LPassiveButton.setBounds(44, 159, 45, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				j2LPassiveButton.setBounds(45, 160, 45, 45);
			}
		});
		j2LPassiveButton.setIcon(imgToIcon);
		j2LPassiveButton.setRolloverIcon(imgToIconBigger);
		j2LPassiveButton.setMargin(new Insets(0, 0, 0, 0));
		j2LPassiveButton.setContentAreaFilled(false);
		j2LPassiveButton.setBorder(null);
		j2LPassiveButton.setToolTipText(player.getProffesion().getPassives(2).getDescription());
		j2LPassiveButton.setBounds(45, 160, 45, 45);
		panel_1.add(j2LPassiveButton);

		j2RPassiveButton = new JButton("") {
			private static final long serialVersionUID = 737900089461250561L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		j2RPassiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getExperience().getLevel() >= SECOND_LEVEL_REQUIRMENTS) {
					player.getProffesion().getPassives(3).setActivated(true);
					j2LPassiveButton.setEnabled(false);
					j3LLPassiveButton.setEnabled(false);
					j3LRPassiveButton.setEnabled(false);
					player.refreshCharacter();
				}
			}
		});
		j2RPassiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				j2RPassiveButton.setBounds(194, 159, 45, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				j2RPassiveButton.setBounds(195, 160, 45, 45);
			}
		});
		j2RPassiveButton.setIcon(imgToIcon);
		j2RPassiveButton.setRolloverIcon(imgToIconBigger);
		j2RPassiveButton.setMargin(new Insets(0, 0, 0, 0));
		j2RPassiveButton.setContentAreaFilled(false);
		j2RPassiveButton.setBorder(null);
		j2RPassiveButton.setToolTipText(player.getProffesion().getPassives(3).getDescription());
		j2RPassiveButton.setBounds(195, 160, 45, 45);
		panel_1.add(j2RPassiveButton);

		j3LLPassiveButton = new JButton("") {
			private static final long serialVersionUID = 6626903337392904953L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		j3LLPassiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getExperience().getLevel() >= THIRD_LEVEL_REQUIRMENTS) {
					player.getProffesion().getPassives(4).setActivated(true);
					j3LRPassiveButton.setEnabled(false);
					player.refreshCharacter();
				}
			}
		});
		j3LLPassiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				j3LLPassiveButton.setBounds(-1, -1, 45, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				j3LLPassiveButton.setBounds(0, 0, 45, 45);
			}
		});
		j3LLPassiveButton.setIcon(imgToIcon);
		j3LLPassiveButton.setRolloverIcon(imgToIconBigger);
		j3LLPassiveButton.setMargin(new Insets(0, 0, 0, 0));
		j3LLPassiveButton.setContentAreaFilled(false);
		j3LLPassiveButton.setBorder(null);
		j3LLPassiveButton.setToolTipText(player.getProffesion().getPassives(4).getDescription());
		j3LLPassiveButton.setBounds(0, 0, 45, 45);
		panel_1.add(j3LLPassiveButton);

		j3RRPassiveButton = new JButton("") {
			private static final long serialVersionUID = -1182946478503676987L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		j3RRPassiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getExperience().getLevel() >= THIRD_LEVEL_REQUIRMENTS) {
					player.getProffesion().getPassives(7).setActivated(true);
					j3RLPassiveButton.setEnabled(false);
					player.refreshCharacter();
				}
			}
		});
		j3RRPassiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				j3RRPassiveButton.setBounds(239, -1, 45, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				j3RRPassiveButton.setBounds(240, 0, 45, 45);
			}
		});
		j3RRPassiveButton.setIcon(imgToIcon);
		j3RRPassiveButton.setRolloverIcon(imgToIconBigger);
		j3RRPassiveButton.setMargin(new Insets(0, 0, 0, 0));
		j3RRPassiveButton.setContentAreaFilled(false);
		j3RRPassiveButton.setBorder(null);
		j3RRPassiveButton.setToolTipText(player.getProffesion().getPassives(7).getDescription());
		j3RRPassiveButton.setBounds(240, 0, 45, 45);
		panel_1.add(j3RRPassiveButton);

		j3RLPassiveButton = new JButton("") {
			private static final long serialVersionUID = 3723072373534315681L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		j3RLPassiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getExperience().getLevel() >= THIRD_LEVEL_REQUIRMENTS) {
					player.getProffesion().getPassives(6).setActivated(true);
					j3RRPassiveButton.setEnabled(false);
					player.refreshCharacter();
				}
			}
		});
		j3RLPassiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				j3RLPassiveButton.setBounds(155, -1, 45, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				j3RLPassiveButton.setBounds(156, 0, 45, 45);
			}
		});
		j3RLPassiveButton.setIcon(imgToIcon);
		j3RLPassiveButton.setRolloverIcon(imgToIconBigger);
		j3RLPassiveButton.setMargin(new Insets(0, 0, 0, 0));
		j3RLPassiveButton.setContentAreaFilled(false);
		j3RLPassiveButton.setToolTipText(player.getProffesion().getPassives(6).getDescription());
		j3RLPassiveButton.setBorder(null);
		j3RLPassiveButton.setBounds(156, 0, 45, 45);
		panel_1.add(j3RLPassiveButton);

		j3LRPassiveButton = new JButton("") {
			private static final long serialVersionUID = 4756593666838579842L;

			public JToolTip createToolTip() {
				JToolTip tip = super.createToolTip();
				tip.setOpaque(false);
				tip.setBorder(null);
				tip.setForeground(Color.BLACK);
				return tip;
			}
		};
		j3LRPassiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getExperience().getLevel() >= THIRD_LEVEL_REQUIRMENTS) {
					player.getProffesion().getPassives(5).setActivated(true);
					j3LLPassiveButton.setEnabled(false);
					player.refreshCharacter();
				}
			}
		});
		j3LRPassiveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				j3LRPassiveButton.setBounds(97, -1, 45, 45);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				j3LRPassiveButton.setBounds(98, 0, 45, 45);
			}
		});
		j3LRPassiveButton.setIcon(imgToIcon);
		j3LRPassiveButton.setRolloverIcon(imgToIconBigger);
		j3LRPassiveButton.setMargin(new Insets(0, 0, 0, 0));
		j3LRPassiveButton.setContentAreaFilled(false);
		j3LRPassiveButton.setBorder(null);
		j3LRPassiveButton.setToolTipText(player.getProffesion().getPassives(5).getDescription());
		j3LRPassiveButton.setBounds(98, 0, 45, 45);
		panel_1.add(j3LRPassiveButton);

		JLabel label = new JLabel("10 ");
		label.setBounds(10, 380, 21, 14);
		panel.add(label);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel label_1 = new JLabel("30 ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(0, 241, 31, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("50 ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(0, 80, 31, 14);
		panel.add(label_2);

		JLabel lblNewLabel = new JLabel(player.getProffesion().getFirstSubclassName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(60, 30, 143, 14);
		panel.add(lblNewLabel);

		JLabel lblMarauder = new JLabel(player.getProffesion().getSecondSubclassName());
		lblMarauder.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarauder.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMarauder.setBounds(202, 30, 143, 14);
		panel.add(lblMarauder);
	}

	public void changeBarValue(int level) {
		double value = 0;
		if (level >= 50)
			value = THIRD_FULL;
		else if (level <= FIRST_LEVEL_REQUIRMENTS)
			value = (level / FIRST_LEVEL_REQUIRMENTS) * FIRST_FULL;
		else if (level > FIRST_LEVEL_REQUIRMENTS && level <= SECOND_LEVEL_REQUIRMENTS)
			value = (level / SECOND_LEVEL_REQUIRMENTS) * SECOND_FULL;
		else if (level > SECOND_LEVEL_REQUIRMENTS)
			value = (SECOND_FULL)
					+ ((level - SECOND_LEVEL_REQUIRMENTS) / THIRD_LEVEL_REQUIRMENTS * (THIRD_FULL - SECOND_FULL));

		progressBar.setValue((int) value);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		changeBarValue(player.getExperience().getLevel());
	}
}
