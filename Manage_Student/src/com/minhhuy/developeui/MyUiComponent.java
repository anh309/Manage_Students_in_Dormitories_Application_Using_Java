package com.minhhuy.developeui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
//Author: Rob Camick 
public class MyUiComponent {

	public static JButton myButtonUI (Dimension dim, int horialign, String name, int font, String hexColor, String hexColorHover, String iconPath) {

		JButton btn = new JButton(name);
		btn.setOpaque(true);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		

		btn.setHorizontalAlignment(horialign);
		btn.setVerticalAlignment(JLabel.CENTER);
		
		btn.setFont(new Font("Roboto", Font.BOLD, font));
		if (dim != null)
		btn.setPreferredSize(dim);

		btn.setBackground(Color.decode(hexColor));
		btn.setForeground(Color.WHITE);
		if (!iconPath.equals(""))
			btn.setIcon(new ImageIcon(iconPath));
		
		
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(Color.decode(hexColorHover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(Color.decode(hexColor));
			}
		});
		return btn;
	}
}
