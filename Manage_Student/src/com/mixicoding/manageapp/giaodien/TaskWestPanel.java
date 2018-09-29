package com.mixicoding.manageapp.giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.minhhuy.developeui.MyUiComponent;

/**
 * Description: Panel chứa các tác vụ: hiển thị panel phòng trọ, sinh viên. và tác vụ thoát
 * Write by:	Vũ Hoàng Minh Huy
 * Create date: 18/4/2018
 */

public class TaskWestPanel {
	private JPanel pnWest;
	private JButton btnPhongTro, btnSinhVien, btnHelp, btnTacGia, btnThoat;
	private JLabel imgLogo;
	public TaskWestPanel(){
		pnWest = new JPanel();
	}
	public JPanel createPanelWest() {
		
		btnPhongTro = MyUiComponent.myButtonUI (new Dimension(290, 70),JButton.LEFT, "Nhà trọ", 25, "#2d89ef", "#03A9F4", "asset\\lodge.png");
		btnSinhVien = MyUiComponent.myButtonUI (btnPhongTro.getPreferredSize(),JButton.LEFT, "Sinh viên", 25, "#2d89ef", "#03A9F4", "asset\\learning.png");
		btnThoat = MyUiComponent.myButtonUI (btnPhongTro.getPreferredSize(),JButton.LEFT, "Thoát", 25, "#2d89ef", "#03A9F4", "asset\\exit.png");
		imgLogo = new JLabel(new ImageIcon("asset\\mixilogo.png"));
		
		pnWest.setBackground(Color.decode("#2d89ef"));
		pnWest.setLayout(new FlowLayout(0, 0, 0));
		pnWest.setPreferredSize(btnPhongTro.getPreferredSize());
		
		
		pnWest.add(btnPhongTro);
		pnWest.add(btnSinhVien);
		pnWest.add(btnThoat);
		JPanel pnDistance = new JPanel();
		pnDistance.setOpaque(false);
		pnDistance.setPreferredSize(new Dimension(290, 200));
		
		JPanel pnLogo = new JPanel(new FlowLayout(1));
		pnLogo.setOpaque(false);
		pnLogo.setPreferredSize(new Dimension(290, 200));
		pnLogo.add(imgLogo);
		
		pnWest.add(pnDistance);
		pnWest.add(pnLogo);
		
		return pnWest;
	}
	
	public JPanel getPnWest() {
		return pnWest;
	}
	public JButton getBtnPhongTro() {
		return btnPhongTro;
	}
	public JButton getBtnSinhVien() {
		return btnSinhVien;
	}
	public JButton getBtnHelp() {
		return btnHelp;
	}
	public JButton getBtnTacGia() {
		return btnTacGia;
	}
	public JButton getBtnThoat() {
		return btnThoat;
	}
	
}
