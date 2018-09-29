package com.mixicoding.manageapp.giaodien;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.minhhuy.developeui.AttributeComponent;
import com.minhhuy.developeui.MyUiComponent;
import com.minhhuy.developeui.PanelSearch;

/**
 * Description: Panel chứa các tác vụ chung của Panel của phòng trọ và của sinh viên
 * Write by:	Vũ Hoàng Minh Huy
 * Create date: 18/4/2018
 */
public class TroSinhVienPanel{
	public  TroSinhVienPanel centerForm;
	
	protected JPanel pnCenter;
	protected JLabel lblTitle;
	protected PanelSearch pnSearch;
	protected JTable tblInfo;
	protected DefaultTableModel dfTable;
	protected ListSelectionModel modelSelect;
	protected String titleDialog ="Thông báo";
	protected JLabel lblHelpSearch;
	
	public TroSinhVienPanel() {
		pnCenter = new JPanel();

		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		
		pnCenter.setBackground(Color.decode("#009688"));
		
		JPanel pnTop = new JPanel(new BorderLayout());
		pnTop.setOpaque(false);
		//title
			JPanel pnTitle = new JPanel(new FlowLayout(0));
			pnTitle.setOpaque(false);
			pnTitle.add(lblTitle = new JLabel("Title"));
			lblTitle.setFont(new Font("", 0, 44));

			lblTitle.setForeground(Color.white);
			pnTop.add(pnTitle, BorderLayout.WEST);
			pnTop.add(lblHelpSearch = new JLabel("Nhấn F5 để trỏ tìm kiếm nhanh "), BorderLayout.EAST);
			lblHelpSearch.setFont(new Font("", Font.ITALIC, 22));
		//end-title
		
		//search panel

			pnSearch = new PanelSearch(new Dimension(330, 33), "asset\\search.png");
		//end - search panel
		
			pnCenter.add(pnTop);
	}	

	public JLabel getLblTitle() {
		return lblTitle;
	}


	public void setLblTitle(String titleName) {
		this.lblTitle.setText(titleName);
	}


	public JPanel getPnCenter() {
		return pnCenter;
	}


	public PanelSearch getPnSearch() {
		return pnSearch;
	}

	public void searchHandling(String input) {
		// TODO Auto-generated method stub
		
	}
	
	public void addEventToComponent() {
		// TODO Auto-generated method stub
	}
	
	
}
