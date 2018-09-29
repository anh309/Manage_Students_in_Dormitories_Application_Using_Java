package com.minhhuy.developeui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSearch {
	private JButton btnSearch;
	private JTextField txtSearch;
	private JPanel pnSearch;
	public PanelSearch(Dimension dim, String iconPath) {
		String hexColorSearch = "#424242";
		String hexColorSearchHover = "#616161";
		String placeHolder ="Search...";
		
		
		pnSearch = new JPanel(new FlowLayout(0, 0, 0));
		pnSearch.setOpaque(false); //trong suá»‘t
		
		//Text to Search
		txtSearch = new JTextField(); //text search
		txtSearch.setBorder(BorderFactory.createLineBorder(Color.red));
		txtSearch.setFont(new Font("", 0, 20));
		
		TextPrompt prompt = new TextPrompt(placeHolder, txtSearch); //táº¡o place holder
		prompt.changeAlpha(0.5f);
		prompt.changeStyle(Font.ITALIC);
		//Icon Search
		ImageIcon iconSeach = new ImageIcon(iconPath);
		Image image = iconSeach.getImage(); // Ä�Æ°a vá»� Image Ä‘á»ƒ resize
		Image newImg = image.getScaledInstance((int)dim.getHeight(), (int)dim.getHeight(),  Image.SCALE_SMOOTH); // scale it the smooth way  
		iconSeach = new ImageIcon(newImg);  // GÃ¡n trá»Ÿ láº¡i iconSearch
		
		btnSearch = new JButton(iconSeach); //icon search
		
		btnSearch.setPreferredSize(new Dimension((int)dim.getHeight(), (int)dim.getHeight()));
		btnSearch.setBorderPainted(false);
		btnSearch.setOpaque(true);
		if (!hexColorSearch.equals(""))
			btnSearch.setBackground(Color.decode(hexColorSearch));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSearch.setBackground(Color.decode(hexColorSearchHover));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSearch.setBackground(Color.decode(hexColorSearch));
			}
		});
		pnSearch.setPreferredSize(dim);
		txtSearch.setPreferredSize(new Dimension((int)dim.getWidth() - (int)btnSearch.getPreferredSize().getWidth()
				, (int)dim.getHeight())); // khoang trong width cho btnSeach
		
		pnSearch.add(txtSearch);
		pnSearch.add(btnSearch);
		
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
	public JTextField getTxtSearch() {
		return txtSearch;
	}
	public JPanel getPnSearch() {
		return pnSearch;
	}
	
	
}
