package com.mixicoding.manageapp.giaodien;

import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.minhhuy.developeui.AttributeComponent;
import com.mixicoding.manageapp.database.DanhSachNhaTro;
import com.mixicoding.manageapp.database.Database;

import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;


/**
 * Description: Lớp giao diện
 * Write by:	Vũ Hoàng Minh Huy
 * Create date: 20/4/2018
 */

public class AppForm extends JFrame implements ActionListener {
	
	  private DanhSachNhaTro listPhongTro = new DanhSachNhaTro();

	  private static int width = 1400 + 200, height = 720 + 120;
	  
	  //WEST PANEL of BorderLayout
	  private TaskWestPanel pnWestForm;
	  
	  //CENTER PANEL of BorderLayout
	  private TroSinhVienPanel pnCenterForm; //Luu tru instance cua Panel chứ chưa khởi tạo panel
	  private SinhVienPanel pnSinhVien;		//tuong tu
	  private TroPanel pnTro;				//tuong tu
	  private JPanel pnWest, pnCenterTro, pnCenterSV; //Luu tru panel được khởi tạo
	  
	  public AppForm() {
		    try 
		    {
		      UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
		    } 
		    catch (Exception e) 
		    {
		      e.printStackTrace();
		    }
		  JPanel pnCenter = new JPanel();
		  pnCenter.setLayout(null);
		
		  //WEST
		  pnWestForm = new TaskWestPanel();
		  
		  add(pnWest = pnWestForm.createPanelWest(), BorderLayout.WEST);
		  
		  pnWestForm.getBtnPhongTro().addActionListener(this);
		  pnWestForm.getBtnSinhVien().addActionListener(this);
		  pnWestForm.getBtnThoat().addActionListener(this);
		  //END-WEST
		  
		  //CENTER
		  pnSinhVien = new SinhVienPanel(listPhongTro); 
		  pnTro = new TroPanel(listPhongTro, pnSinhVien);			
		  
		  
		  pnCenter.add(pnCenterTro = pnTro.createPnCenterTro(), BorderLayout.CENTER);
		  pnCenterTro.setBounds(0, 0, (int) (width - pnWest.getPreferredSize().getWidth() - 5), height-29);
		  setColorOnlyButton (pnWestForm.getBtnPhongTro());
		  
		  pnCenter.add(pnCenterSV = pnSinhVien.createPnCenterSV(), BorderLayout.CENTER);
		  pnCenterSV.setBounds(0, 0, (int) (width - pnWest.getPreferredSize().getWidth() - 5), height-29);
		  pnCenterSV.setVisible(false); //chỉ hiện panel tro
		  
		  //action listener
		  pnTro.getBtnXemSinhVien().addActionListener(this);
		  //END-CENTER

		  add(pnCenter, BorderLayout.CENTER);
		  
		  setTitle("Phần mềm quản lý phòng trọ sinh viên");
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  setSize(width, height);
		  setResizable(false);
		  setLocationRelativeTo(null);
		  setVisible(true);
		 
	  }
  

		@Override
		public void actionPerformed(ActionEvent e) {
			Object getSource = e.getSource();
			if (getSource == pnWestForm.getBtnPhongTro()) {		
				setColorOnlyButton (pnWestForm.getBtnPhongTro());
				showOnlyPanel(pnCenterTro);
			}
			else if (getSource == pnWestForm.getBtnSinhVien()) {
				setColorOnlyButton (pnWestForm.getBtnSinhVien());
				showOnlyPanel(pnCenterSV);
			}
			else if (getSource == pnTro.getBtnXemSinhVien()) {
				setColorOnlyButton (pnWestForm.getBtnSinhVien());
				showOnlyPanel(pnCenterSV);
			}
			else if (getSource == pnWestForm.getBtnThoat()) {
				
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình ?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
					Database.getInstance().disconnect();
				}		
			}		
		}
		
		/**
		 * Description: phương thức chỉ hiện thị duy nhất 1 panel tại 1 thời điểm
		 *
		 * Params:		panel: đối tượng JPanel
		 * Write by:	Vũ Hoàng Minh Huy
		 * Create date: 20/4/2018
		 */
		
		public void showOnlyPanel(JPanel panel) { //Chỉ hiển thị duy nhất 1 panel 1 thời điểm
			JPanel[] listPanelCenter = {
				pnCenterSV, pnCenterTro
			};
			for (JPanel iPanel : listPanelCenter) {
				if (iPanel == panel)
					iPanel.setVisible(true);	
				else
					iPanel.setVisible(false);
			}
			
					
		}
		
		/**
		 * Description: phương thức chỉ hiện thị duy nhất 1 Button tại 1 thời điểm
		 *
		 * Params:		button: đối tượng JButton
		 * Write by:	Vũ Hoàng Minh Huy
		 * Create date: 20/4/2018
		 */
		
		public void setColorOnlyButton (JButton button) {
			JButton[] btnTaskWest = {
				pnWestForm.getBtnPhongTro(), pnWestForm.getBtnSinhVien()
			};
			for (JButton iBtn : btnTaskWest) {
				if (iBtn == button)
					iBtn.setForeground(Color.BLACK);					
				else
					iBtn.setForeground(Color.WHITE);
			}
		}

	}
