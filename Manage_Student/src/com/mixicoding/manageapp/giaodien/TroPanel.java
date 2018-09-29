package com.mixicoding.manageapp.giaodien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.minhhuy.developeui.AttributeComponent;
import com.minhhuy.developeui.MyUiComponent;
import com.mixicoding.manageapp.database.DanhSachNhaTro;
import com.mixicoding.manageapp.database.NhaTro;
import com.mixicoding.manageapp.database.SinhVien;

/**
 * Description: phương thức chứa các giao diện dành cho sinh viên
 * Write by:	Vũ Hoàng Minh Huy
 * Create date: 20/4/2018
 */

public class TroPanel extends TroSinhVienPanel implements ActionListener{
	private DanhSachNhaTro listPhongTro;
	private SinhVienPanel pnSinhVien;
	
	private JLabel lblDiachi, lblChuNha, lblSoDT;
	private static JTextField txtDiaChi, txtChuNha, txtSoDT;
	private JTable tblInfo;
	private static JButton btnThem, btnXoa, btnSua, btnLuu, btnXoaRong, btnMoiNhaTro, btnXemSinhVien,
					btnNext, btnPrevious, btnEndNext, btnEndPrevious;
	

	public JLabel lblChiSo;				
	
	private JRadioButton radDiaChi, radChuNha, radSdt;
	private static boolean  kiemTra = true;
	
	private static int keySaved = 0;
	private boolean allowUseCase = true; //cho phép sử dụng Key_Event 

	private boolean checkFocusTxtSearch = false;
	
	public TroPanel(DanhSachNhaTro listPhongTro, SinhVienPanel pnSinhVien) {
		this.listPhongTro = listPhongTro;
		this.pnSinhVien = pnSinhVien;
	}

	public JPanel createPnCenterTro() {
	
		//CONTENT
			//TOP
				setLblTitle("Nhà trọ");
				JPanel pnWrapTop = new JPanel(); //panel chứa panel nhập liệu và panel chức năng tim kiem
				pnWrapTop.setOpaque(false);
				
				//Vị trí Center của pnWrapTop
				
				JPanel pnInput = new JPanel(); //panel chứa các label và textfield nhập liệu
				pnInput.setBorder(new TitledBorder(null, "Nhập thông tin nhà trọ", 0, 0, new Font("", Font.BOLD, 20), Color.WHITE));
				pnInput.setOpaque(false);
				pnInput.setLayout(new BoxLayout(pnInput, BoxLayout.Y_AXIS));
				
				JPanel pnDiaChi = new JPanel(new FlowLayout(0));
				pnDiaChi.setOpaque(false);
				pnDiaChi.add(lblDiachi = new JLabel("Địa chỉ:"));
				pnDiaChi.add(txtDiaChi = new JTextField());
				
				JPanel pnChuNha = new JPanel(new FlowLayout(0));
				pnChuNha.setOpaque(false);
				pnChuNha.add(lblChuNha = new JLabel("Chủ nhà:"));
				pnChuNha.add(txtChuNha = new JTextField());
				
				JPanel pnSoDT = new JPanel(new FlowLayout(0));
				pnSoDT.setOpaque(false);
				pnSoDT.add(lblSoDT = new JLabel("Số điện thoại:"));
				pnSoDT.add(txtSoDT = new JTextField());
				
				pnInput.add(pnDiaChi);
				pnInput.add(pnChuNha);
				pnInput.add(pnSoDT);
				
				
				//Vị trí East của pnWrapTop
				JPanel pnLoaiTimKiem = new JPanel();	
	
				pnLoaiTimKiem.setPreferredSize(new Dimension(400, 173));
				
				pnLoaiTimKiem.setBorder(new TitledBorder(null, "Loại tìm kiếm", 0, 0, new Font("", Font.BOLD, 20), Color.WHITE));
				pnLoaiTimKiem.setOpaque(false);
		
				pnLoaiTimKiem.setLayout(new BoxLayout(pnLoaiTimKiem, BoxLayout.Y_AXIS));
				
				//Panel Search
				JPanel pnWrapSearch = new JPanel();
				pnWrapSearch.setPreferredSize(new Dimension(0, 30));
				pnWrapSearch.setOpaque(false);
				pnWrapSearch.add(Box.createHorizontalStrut(5));
				pnWrapSearch.add(pnSearch.getPnSearch());
				pnLoaiTimKiem.add(pnWrapSearch);	
				//end
				pnLoaiTimKiem.add(radDiaChi = new JRadioButton("tìm kiếm theo địa chỉ"));
				radDiaChi.setSelected(true);
				pnLoaiTimKiem.add(radChuNha = new JRadioButton("tìm kiếm chủ nhà"));
				pnLoaiTimKiem.add(radSdt = new JRadioButton("tìm kiếm số điện thoại"));							

				ButtonGroup grpRadTimKiem = new ButtonGroup();
				grpRadTimKiem.add(radDiaChi);
				grpRadTimKiem.add(radChuNha);
				grpRadTimKiem.add(radSdt);
				
				pnWrapTop.add(pnInput);
				pnWrapTop.add(pnLoaiTimKiem);
				
				//set font-size, color cho label, và size cho textfield

				Component[] compInput = pnInput.getComponents();
				for (Component component : compInput) {
					AttributeComponent.set(component, new Font("", 0, 25), AttributeComponent.LABEL);
					AttributeComponent.set(component, new Dimension(300, 30), AttributeComponent.TEXT_FIELD);
					AttributeComponent.set(component, new Font("", 0, 14), AttributeComponent.TEXT_FIELD);
					AttributeComponent.set(component, Color.WHITE, AttributeComponent.LABEL);
				}
					
				for (Component component : compInput)
					AttributeComponent.set(component, lblSoDT.getPreferredSize(), AttributeComponent.LABEL);
								
				Component[] radComponent = pnLoaiTimKiem.getComponents();
				for (Component rad: radComponent) {
					rad.setFont(new Font("", Font.PLAIN, 18));
					rad.setForeground(Color.WHITE);
				}
				
				//Hoàn tất, thêm wrapTop vào pnCenter
				pnCenter.add(pnWrapTop);
				
			//END-TOP
				
			//task
				JPanel pnTask = new JPanel(new FlowLayout(1, 0, 0));
				pnTask.setBackground(Color.decode("#039BE5"));
				
				pnTask.add(btnEndPrevious = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\first.png"));
				pnTask.add(btnPrevious = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\back.png"));
				pnTask.add(Box.createHorizontalStrut(10));
				pnTask.add(lblChiSo = new JLabel());
				pnTask.add(Box.createHorizontalStrut(10));
				pnTask.add(btnNext = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\next.png"));
				pnTask.add(btnEndNext = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\last.png"));
				
				pnTask.add(Box.createHorizontalStrut(50)); //khoảng cách
				
				pnTask.add(btnThem = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Thêm", 18, "#039BE5", "#29B6F6", "asset\\plus.png"));
				pnTask.add(btnXoa = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Xoá", 18, "#039BE5", "#29B6F6", "asset\\minus.png"));
				pnTask.add(btnSua = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Sửa", 18, "#039BE5", "#29B6F6", "asset\\edit.png"));
				pnTask.add(btnLuu = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Lưu", 18, "#039BE5", "#29B6F6", "asset\\database.png"));
				pnTask.add(btnXoaRong = MyUiComponent.myButtonUI(new Dimension(150, 55),JButton.CENTER, "Xoá rỗng", 18, "#039BE5", "#29B6F6", "asset\\eraser.png"));
				
				pnCenter.add(pnTask); //add to pnCenter


			//end-task
			//table
				String col[]= {
						"Địa chỉ", "Chủ nhà", "Số điện thoại"
				};
				dfTable = new DefaultTableModel(col, 0);
				
				tblInfo = new JTable(dfTable) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				tblInfo.getTableHeader().setFont(new Font("", Font.BOLD, 15));
				tblInfo.setFont(new Font("", 0, 15));

				modelSelect = tblInfo.getSelectionModel();
			    modelSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			    
				JScrollPane pnTable = new JScrollPane(tblInfo);
				
				pnCenter.add(pnTable); //add to pnCenter
	
			//end-table
			//Panel xem sinh vien
				JPanel pnBot = new JPanel(new FlowLayout(2, 0, 0));
				pnBot.setOpaque(false);
				
				pnBot.add(btnMoiNhaTro = MyUiComponent.myButtonUI(new Dimension(200, 55),JButton.CENTER, "Mọi nhà trọ", 18, "#039BE5", "#29B6F6", "asset\\house.png"));
				pnBot.add(btnXemSinhVien = MyUiComponent.myButtonUI(new Dimension(200, 55),JButton.CENTER, "Xem sinh viên", 18, "#039BE5", "#29B6F6", "asset\\tasksman.png"));
				
				pnCenter.add(pnBot); //add to pnCenter
			//end-panel xem sinh vien
	
		//END-CONTENT
		addEventToComponent();
		loadDataTro();
		
		showHideControlThem(false);// Các trạng thái của control mặc định

		return pnCenter;
	}
	public JButton getBtnXemSinhVien() {
		return btnXemSinhVien;
	}
	
	@Override
	public void addEventToComponent() {
		btnNext.addActionListener(this);
		btnEndNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnEndPrevious.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		pnSearch.getBtnSearch().addActionListener(this);
		modelSelect.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblInfo.getSelectedRow();
				if (row >= 0) {
					setTextToField(row);
				}
					
				showIndex();
			}
		});
		btnMoiNhaTro.addActionListener(this);
		btnXemSinhVien.addActionListener(this);
		
		pnSearch.getTxtSearch().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				checkFocusTxtSearch = false;
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				checkFocusTxtSearch = true;
				
			}
		});
		
	    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){
	        public boolean dispatchKeyEvent(KeyEvent e){
	          if(pnCenter.isVisible() && allowUseCase && e.getID() == KeyEvent.KEY_PRESSED)
	          {
	        	  int key = e.getKeyCode();
	        	  
	        	  switch (key) {
	        	  	case KeyEvent.VK_F5:
	        	  		pnSearch.getTxtSearch().requestFocus();
	        	  		keySaved = 1; // Đã nhấn F5
	        	  		lblHelpSearch.setText("Nhấn ESC để tắt trỏ tìm kiếm nhanh ");
	        	  		break;
	        	  	case KeyEvent.VK_F1:
	        	  		if(keySaved == 1) radDiaChi.setSelected(true);
	        	  		break;
	        	  	case KeyEvent.VK_F2:
	        	  		if(keySaved == 1) radChuNha.setSelected(true);
	        	  		break;
	        	  	case KeyEvent.VK_F3:
	        	  		if(keySaved == 1) radSdt.setSelected(true);
	        	  		break;
	        	  	case KeyEvent.VK_ENTER:
	        	  		if((keySaved == 1 && checkFocusTxtSearch) || checkFocusTxtSearch) 
	        	  			searchHandling(pnSearch.getTxtSearch().getText());
	        	  		break;
	        	  	case KeyEvent.VK_ESCAPE:
	        	  		if(keySaved == 1) {
	        	  			pnCenter.getRootPane().requestFocusInWindow();
	        	  			keySaved = 0;
	        	  			lblHelpSearch.setText("Nhấn F5 để trỏ tìm kiếm nhanh ");
	        	  		}    	  		
	        	  		break;
	        	  }
	          }
	          return false;
	        }
	    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object getObject = e.getSource();
		if (getObject == btnNext) {
			int row = tblInfo.getSelectedRow();
			if (row >= 0 && row <tblInfo.getRowCount()-1) {
				tblInfo.changeSelection(row + 1, -1, false, false);
				 setTextToField(row + 1);
			}else if (row == -1) {							//trường hợp chưa chọn row nào
				tblInfo.changeSelection(0, -1, false, false);
				 setTextToField(0);			
			}	
			showIndex();
		}else if (getObject == btnEndNext) {
			int row = tblInfo.getRowCount();
			tblInfo.changeSelection(row - 1, -1, false, false);
			setTextToField(row -1);
			showIndex();
		}else if (getObject == btnPrevious) {
			int row = tblInfo.getSelectedRow();
			if (row >= 1) {
				tblInfo.changeSelection(row - 1, -1, false, false);
				setTextToField(row - 1);
			}else if (row == -1) {							//trường hợp chưa chọn row nào
				tblInfo.changeSelection(0, -1, false, false);
				 setTextToField(0);			
			}
			showIndex();
		}else if (getObject == btnEndPrevious) {
			tblInfo.changeSelection(0, -1, false, false);
			setTextToField(0);
			showIndex();
		}else if (getObject == pnSearch.getBtnSearch()) {

			String inputSearch = pnSearch.getTxtSearch().getText();
			if (!inputSearch.trim().equals(""))
				searchHandling(inputSearch);
			showIndex();
		}
		else if (getObject == btnThem) {
			kiemTra = true;
			xoaRongTexfields();
			tblInfo.clearSelection();
			txtDiaChi.requestFocus(true);
			if (btnThem.getText().equals("Thêm"))
				showHideControlThem(true);
			else {
				showHideControlThem(false);
				setTextToField(tblInfo.getSelectedRow());
			}	
			showIndex();
		}else if (getObject == btnSua) {
			kiemTra = false;

			if (btnSua.getText().equals("Sửa")) {
				if(tblInfo.getSelectedRow() >= 0)
					showHideControlSua(true);
				else
					JOptionPane.showMessageDialog(null, "Chưa chọn dòng để sửa", titleDialog, JOptionPane.INFORMATION_MESSAGE);
			} else {
				showHideControlSua(false);
				setTextToField(tblInfo.getSelectedRow());
			}
			txtDiaChi.requestFocus();
			showIndex();
		}else if (getObject == btnLuu) {
			//System.out.println(kiemTra);
			String diaChi = txtDiaChi.getText();
			String chuNha = txtChuNha.getText();
			String soDT = txtSoDT.getText();
			String patternSdt = "09[0-9]{8}|01[0-9]{9}";
			String patternTen = "(\\p{Lu}\\p{Ll}+\\s?)+";
			String patternDiaChi = "([0-9]+\\s)(\\p{Lu}\\p{Ll}+\\s?)+(, (P|Q)(?:[0-9]+|(\\.(\\p{Lu}\\p{Ll}+\\s?)+)))+";

			if (diaChi.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ", titleDialog, JOptionPane.INFORMATION_MESSAGE);
				return;
			}			
			
			if(!diaChi.matches(patternDiaChi)) {
				JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ và phải thoả mãn điều kiện sau\n"
						+ "[số nhà], [Tên Đường], P[số phường], Q[số quận]\n"
						+ "[số nhà], [Tên Đường], P.[Tên Phường], Q.[Tên Quận]", titleDialog, JOptionPane.INFORMATION_MESSAGE);
				return;			
			}
			if (chuNha.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Chưa nhập chủ nhà", titleDialog, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if (!chuNha.matches(patternTen)) {
				JOptionPane.showMessageDialog(null, "Tên chủ nhà nhập không hợp lệ\n"
						+ "Tên chủ nhà phải được viết hoa mỗi từ và mỗi từ phải có khoảng cách", titleDialog, JOptionPane.INFORMATION_MESSAGE);

				return;
			}
			if (soDT.trim().equals("")){
				JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại", titleDialog, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if(!soDT.matches(patternSdt)) {
				JOptionPane.showMessageDialog(null, "Số điện thoại phải nếu bắt đầu từ 09 thì gồm 10 số \n"
						+ "Số điện thoại phải nếu bắt đầu từ 01 thì gồm 11 số", titleDialog, JOptionPane.INFORMATION_MESSAGE);
				return;			
			}
				
			if (kiemTra){ //Luu (trường hợp bấm thêm)
				if (listPhongTro.them(diaChi, chuNha, soDT)) {
					loadDataTro();
					tblInfo.changeSelection(tblInfo.getRowCount()-1, -1, false, false);
					showHideControlThem(false);
				}else {
					JOptionPane.showMessageDialog(null, "Lỗi không thể thêm", titleDialog, JOptionPane.ERROR_MESSAGE);
				}
				
			}else{	 //Luu (trường hợp bấm sửa)
				int row = tblInfo.getSelectedRow();
				if (row >= 0) {
					String oldDiaChi = (String)tblInfo.getValueAt(row, 0);
					if (listPhongTro.update(oldDiaChi, diaChi, chuNha, soDT)){
						System.out.println("update to table success");
						tblInfo.setValueAt(diaChi, row, 0);
						tblInfo.setValueAt(chuNha, row, 1);
						tblInfo.setValueAt(soDT, row, 2);
						showHideControlSua(false);

					}else {
						JOptionPane.showMessageDialog(null, "Lỗi không thể sửa", titleDialog, JOptionPane.ERROR_MESSAGE);

					}
				}
			}
			pnSinhVien.loadListDiaChiTro(); // load Thông tin Trọ ở panel sinh viên		
			showIndex();
		}else if (getObject == btnXoa) {
			
			int row = tblInfo.getSelectedRow();
			if (row >= 0) {
				int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá không ?", titleDialog, JOptionPane.YES_NO_OPTION);
				if (chon == JOptionPane.YES_OPTION) {
					String diaChi = (String) tblInfo.getValueAt(row, 0);
					
					NhaTro phongTro;
					int soLuongSinhVien = 0;
					if((phongTro=listPhongTro.timNhaTro(diaChi)) != null)
						soLuongSinhVien = phongTro.getListSinhVien().size(); //lấy số lượng sinh viên trong phòng trọ
					
					if (soLuongSinhVien <= 0) {
						if(listPhongTro.delete(diaChi)) {
							dfTable.removeRow(row);
							pnSinhVien.loadListDiaChiTro(); // load Thông tin Trọ ở panel sinh viên
							xoaRongTexfields();
							JOptionPane.showMessageDialog(null, "Xoá thành công", titleDialog, JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "Lỗi, không thể xoá", titleDialog, JOptionPane.ERROR_MESSAGE);
							
					}else{
						JOptionPane.showMessageDialog(null, "Không thể xoá vì tồn tại sinh viên trong Trọ này", titleDialog, JOptionPane.ERROR_MESSAGE);
					}

				}
			}else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn dữ liệu cần xoá", titleDialog, JOptionPane.OK_OPTION);
			}
			showIndex();
		}else if(getObject == btnXoaRong) {
			txtDiaChi.setText("");
			txtChuNha.setText("");
			txtSoDT.setText("");
		}else if (getObject == btnMoiNhaTro) {
			loadDataTro();
			showIndex();
		}else if (getObject == btnXemSinhVien) {
			int row = tblInfo.getSelectedRow();
			pnSinhVien.jLstTro.getSelectionModel().clearSelection();
			if (row >= 0) {
				String diaChi = (String)tblInfo.getValueAt(row, 0);			
				for (int index=0; index< pnSinhVien.dfList.getSize(); index++) {
					if (pnSinhVien.dfList.getElementAt(index).equals(diaChi))
						pnSinhVien.jLstTro.setSelectedIndex(index);	 
				}	
			}
			showIndex();
		}
	}

	/**
	 * Description: phương thức tìm kiếm  địa chỉ, chủ nhà, quê quán
	 *
	 * Params:		input - nội dung tìm kiếm bao gồm địa chỉ, chủ nhà, quê quán
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	
	 @Override
	public void searchHandling(String input) {
		allowUseCase = false;
		if(radDiaChi.isSelected()) {
			 if(!timKiemTheoLoai(input, 1))
				JOptionPane.showMessageDialog(null, "Không thể tìm thấy Địa chỉ !", titleDialog, 1);
		}else if (radChuNha.isSelected()) {
			if(!timKiemTheoLoai(input, 2))
				JOptionPane.showMessageDialog(null, "Không thể tìm thấy Chủ nhà !", titleDialog, 1);
		}else if (radSdt.isSelected()) {
			if(!timKiemTheoLoai(input, 3))
				JOptionPane.showMessageDialog(null, "Không thể tìm thấy Số điện thoại !", titleDialog, 1);
		}
		allowUseCase = true;
	}
	 
	/**
	 * Description: phương thức tìm kiếm theo từng loại địa chỉ, chủ nhà, quê quán
	 *
	 * Params:		input - nội dung tìm kiếm theo từng loại
	 * 				loại - gồm địa chỉ, chủ nhà, quê quán | loai = 1: dia chi, 2:chu nha, 3:so dt
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	 
	private boolean timKiemTheoLoai(String input, int loai) { //loai = 1: dia chi, 2:chu nha, 3:so dt
		boolean flag = false;
		loadDataTro();
		int maxRow = tblInfo.getRowCount();
		int count = 0;
		String[][] rowSaved = new String[maxRow][3];
			for (int row=0; row<maxRow; row++) {
				if(loai == 1) {
					String diaChi = (String)tblInfo.getValueAt(row, 0);
					if (diaChi.trim().equalsIgnoreCase(input))
						flag = true;						
					
				}else if (loai == 2) {
					String chuNha = (String)tblInfo.getValueAt(row, 1);
					if (chuNha.trim().equalsIgnoreCase(input))
						flag = true;									
				}else if (loai == 3) {
					String soDt = (String)tblInfo.getValueAt(row, 2);
					if (soDt.trim().equalsIgnoreCase(input))
						flag = true;									
				}
				if(flag) {
					rowSaved[count][0] = (String)tblInfo.getValueAt(row, 0);
					rowSaved[count][1] = (String)tblInfo.getValueAt(row, 1);
					rowSaved[count][2] = (String)tblInfo.getValueAt(row, 2);
					count++;
					flag = false;
				}
			}
			if (count > 0)
				dfTable.setRowCount(0);
			for (int i=0; i<maxRow; i++) {
				if (rowSaved[i][0] != null)			
					dfTable.addRow(rowSaved[i]);
			}
		return count > 0;
	}

	/**
	 * Description: phương thức xoá rỗng các ô nhập liệu
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	 
	private void xoaRongTexfields() {
		txtDiaChi.setText("");
		txtChuNha.setText("");
		txtSoDT.setText("");
		txtDiaChi.requestFocus();
	}
	
	/**
	 * Description: phương thức set text từ row table lên các ô nhập liệu
	 *
	 * Params:		row - row của table
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	 
	
	public void setTextToField(int row) {
		if (row >= 0) {
			txtDiaChi.setText((String) tblInfo.getValueAt(row, 0));
			txtChuNha.setText((String) tblInfo.getValueAt(row, 1));
			txtSoDT.setText((String) tblInfo.getValueAt(row, 2));

		}
	}
	
	/**
	 * Description: phương thức load dữ liệu nhà trọ từ danh sách lên table
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	 
	
	private void loadDataTro() {	//Xanh update		
		
		dfTable.setRowCount(0);
		for (NhaTro phongTro : listPhongTro.getDsNhaTro()) {
			int sizeSinhVien = 0;
			String [] rowData = {phongTro.getDiaChi(),phongTro.getChuNha(),phongTro.getSoDT()};
			dfTable.addRow(rowData);
		}
		showIndex();
	}
	
	
	/**
	 * Description: phương thức Enable/Disable các state khi nhấn thêm
	 *
	 * Params:		flag - flag = true: hiển thị các state khi nhấn thêm và 
	 * 						ngược lại khi nhấn huỷ flag = false
	 * 		
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	 
	
	public void showHideControlThem(boolean flag) { 
		if (flag) {
			btnThem.setText("Huỷ");
			btnXoaRong.setEnabled(true);
			btnThem.setEnabled(true);
			btnLuu.setEnabled(true);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
			txtDiaChi.setEnabled(true);
			txtChuNha.setEnabled(true);
			txtSoDT.setEnabled(true);
		}
		else {
			btnThem.setText("Thêm");
			btnXoaRong.setEnabled(false);
			btnThem.setEnabled(true);
			btnLuu.setEnabled(false);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
			txtDiaChi.setEnabled(false);
			txtChuNha.setEnabled(false);
			txtSoDT.setEnabled(false);
		}
	}
	
	/**
	 * Description: phương thức Enable/Disable các state khi nhấn sửa
	 *
	 * Params:		flag - flag = true: hiển thị các state khi sửa thêm và 
	 * 						ngược lại khi nhấn huỷ flag = false
	 * 		
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	
	public void showHideControlSua(boolean flag) { 
		
		if (flag) {
			btnSua.setText("Hủy");
			btnXoaRong.setEnabled(true);
			btnThem.setEnabled(false);
			btnLuu.setEnabled(true);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(false);
			txtDiaChi.setEnabled(true);
			txtChuNha.setEnabled(true);
			txtSoDT.setEnabled(true);
		}
		else {
			btnSua.setText("Sửa");
			btnXoaRong.setEnabled(false);
			btnThem.setEnabled(true);
			btnLuu.setEnabled(false);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
			txtDiaChi.setEnabled(false);
			txtChuNha.setEnabled(false);
			txtSoDT.setEnabled(false);
		}
	}
	
	/**
	 * Description: phương thức hiển thị lên số row hiện tại và row được chọn của table
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
	//xem dòng và tổng dòng được chọn
		public void showIndex() {
			int index = tblInfo.getSelectedRow()+1,sumOfIndex = tblInfo.getRowCount();
			lblChiSo.setText(index +"/"+ sumOfIndex);
			Font font = new Font("", Font.BOLD, 17);
			lblChiSo.setFont(font);
		} 
}