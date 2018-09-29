package com.mixicoding.manageapp.giaodien;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.minhhuy.developeui.AttributeComponent;
import com.minhhuy.developeui.MyUiComponent;
import com.mixicoding.manageapp.database.*;

import javafx.scene.input.KeyCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: Panel hiển thị giao diện dành cho SinhVien
 * Write by:	Vũ Hoàng Minh Huy
 * Create date: 20/4/2018
 */

public class SinhVienPanel extends TroSinhVienPanel implements ActionListener {
	
	private DanhSachNhaTro listPhongTro;
	
	private JLabel lblMssv,lblLop,lblTen,lblQue;
	private JTextField txtQue,txtLop,txtTen,txtMssv;
	private JButton btnThem, btnXoa, btnSua, btnLuu, btnXoaRong,
					btnMoiSinhVien, btnBoChon,
					btnNext, btnPrevious, btnEndNext, btnEndPrevious, btnChuyen;
	public JList<String> jLstTro;
	
	public JLabel lblChiSo;
	

	//Component cho Panel Tìm kiếm của TabbedPane
	private JRadioButton radMssv, radTen, radLop, radQue;
	
	public DefaultListModel dfList;
	//Component cho Panel liệt kê của TabbedPane
	private JComboBox<String> cbxNganh;
	private JLabel lblNganh;
	private JButton btnLietKe;
	
	private int keySaved = 0;	//Lưu trữ các Key
	private boolean allowUseCase = true; //cho phép sử dụng Key_Event 
	private boolean checkFocusTxtSearch = false;
	
	public SinhVienPanel(DanhSachNhaTro listPhongTro) {
		this.listPhongTro = listPhongTro;
	}

	public JPanel createPnCenterSV() {
		setLblTitle("Sinh viên");
	//TOP
		JPanel pnWrapTop = new JPanel(); //panel chứa panel nhập liệu và panel chức năng
		pnWrapTop.setOpaque(false);
		
		//Vị trí Center của pnWrapTop
		JPanel pnInput = new JPanel(); //panel chứa các label và textfield nhập liệu
		pnInput.setBorder(new TitledBorder(null, "Nhập thông tin sinh viên", 0, 0, new Font("", Font.BOLD, 20), Color.WHITE));
		pnInput.setOpaque(false);
		pnInput.setLayout(new BoxLayout(pnInput, BoxLayout.Y_AXIS));
		
			JPanel pnMssv = new JPanel(new FlowLayout(0));
			pnMssv.setOpaque(false);
			pnMssv.add(lblMssv = new JLabel("MSSV:"));
			pnMssv.add(txtMssv = new JTextField());
			
			JPanel pnTen = new JPanel(new FlowLayout(0));
			pnTen.setOpaque(false);
			pnTen.add(lblTen = new JLabel("Tên sinh viên:"));
			pnTen.add(txtTen = new JTextField());
			
			JPanel pnLop = new JPanel(new FlowLayout(0));
			pnLop.setOpaque(false);
			pnLop.add(lblLop = new JLabel("Lớp:"));
			pnLop.add(txtLop = new JTextField());
			
			JPanel pnQue = new JPanel(new FlowLayout(0));
			pnQue.setOpaque(false);
			pnQue.add(lblQue = new JLabel("Quê quán:"));
			pnQue.add(txtQue = new JTextField());
		
			pnInput.add(pnMssv);
			pnInput.add(pnTen);
			pnInput.add(pnLop);
			pnInput.add(pnQue);
			
		pnWrapTop.add(pnInput);
		
		//Vị trí East của pnWrapTop
		JTabbedPane tbbChucNang = new JTabbedPane();
		tbbChucNang.setPreferredSize(new Dimension(400, 200));
		tbbChucNang.setFont(new Font("", Font.BOLD, 15));
		//Panel tim kiếm cho tabbedpane
		JPanel pnLoaiTimKiem = new JPanel();										
		pnLoaiTimKiem.setLayout(new BoxLayout(pnLoaiTimKiem, BoxLayout.Y_AXIS));
		
		//Panel Search
		JPanel pnWrapSearch = new JPanel();
		pnWrapSearch.setPreferredSize(new Dimension(0, 30));
		pnWrapSearch.setOpaque(false);
		pnWrapSearch.add(Box.createHorizontalStrut(5));
		pnWrapSearch.add(pnSearch.getPnSearch());
		pnLoaiTimKiem.add(pnWrapSearch);	
		//end
		
		pnLoaiTimKiem.add(radMssv = new JRadioButton("tìm kiếm theo MSSV"));
		radMssv.setSelected(true);
		pnLoaiTimKiem.add(radTen = new JRadioButton("tìm kiếm theo Tên"));
		pnLoaiTimKiem.add(radLop = new JRadioButton("tìm kiếm theo Lớp"));
		pnLoaiTimKiem.add(radQue = new JRadioButton("tìm kiếm theo Quê quán"));
		
		ButtonGroup grpRadTimKiem = new ButtonGroup();
		grpRadTimKiem.add(radMssv);
		grpRadTimKiem.add(radTen);
		grpRadTimKiem.add(radLop);
		grpRadTimKiem.add(radQue);
		
		//Panel liệt kê cho tabbedpane
		JPanel pnLietKe = new JPanel(new BorderLayout());										

		//	pnNganh
		JPanel pnNganh = new JPanel();// chứa label "ngành" và combobox các loại ngành
		
		pnNganh.add(lblNganh = new JLabel("Ngành:"));
		lblNganh.setFont(new Font("", 0, 17));
		
		pnNganh.add(cbxNganh = new JComboBox<>());
		cbxNganh.setFont(new Font("", 0, 17));
		cbxNganh.setMaximumRowCount(4);
		cbxNganh.setPreferredSize(new Dimension(250, 30));
		cbxNganh.setEditable(true);
		
		//add pnNganh vào panel pnLietKe của tabbedPane
		pnLietKe.add(pnNganh, BorderLayout.CENTER);
		
		pnLietKe.add(btnLietKe = MyUiComponent.myButtonUI(null,JButton.CENTER, "Thực hiện", 20, "#039BE5", "#29B6F6", ""), BorderLayout.SOUTH);
		
		//Add panel Tìm kiếm và LietKe vào tabbedPane
		tbbChucNang.add("Loại tìm kiếm", pnLoaiTimKiem);
		tbbChucNang.add("Liệt kê theo chuyên ngành", pnLietKe);
		
		pnWrapTop.add(tbbChucNang);
			
		//set font-size, color cho label, và size cho textfield
		Component[] compInput = pnInput.getComponents();
		for (Component component : compInput) {
			AttributeComponent.set(component, new Font("", 0, 25), AttributeComponent.LABEL);
			AttributeComponent.set(component, new Dimension(300, 30), AttributeComponent.TEXT_FIELD);
			AttributeComponent.set(component, new Font("", 0, 14), AttributeComponent.TEXT_FIELD);
			AttributeComponent.set(component, Color.white, AttributeComponent.LABEL);
		}
			
		for (Component component : compInput)
			AttributeComponent.set(component, lblTen.getPreferredSize(), AttributeComponent.LABEL);
		
		Component[] radComponent = pnLoaiTimKiem.getComponents();
		for (Component rad: radComponent) {
			rad.setFont(new Font("", Font.PLAIN, 18));
		}
		
		//Hoàn tất, thêm wrapTop vào pnCenter
		pnCenter.add(pnWrapTop);
	//END-TOP
		
	//task
		JPanel pnTask = new JPanel(new FlowLayout(1, 0, 0));
		pnTask.setBackground(Color.decode("#039BE5"));
		
		//icon next and previous
		pnTask.add(btnEndPrevious = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\first.png"));
		pnTask.add(btnPrevious = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\back.png"));
		pnTask.add(Box.createHorizontalStrut(10));
		pnTask.add(lblChiSo = new JLabel());
		pnTask.add(Box.createHorizontalStrut(10));
		pnTask.add(btnNext = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\next.png"));
		pnTask.add(btnEndNext = MyUiComponent.myButtonUI(new Dimension(55, 55),JButton.CENTER, "", 12, "#039BE5", "#29B6F6", "asset\\last.png"));
		
		pnTask.add(Box.createHorizontalStrut(20)); //khoảng cách
		
		//tác vụ
		pnTask.add(btnThem = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Thêm", 18, "#039BE5", "#29B6F6", "asset\\SVadd.png"));
		pnTask.add(btnXoa = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Xoá", 18, "#039BE5", "#29B6F6", "asset\\SVdel.png"));
		pnTask.add(btnSua = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Sửa", 18, "#039BE5", "#29B6F6", "asset\\SVedit.png"));
		pnTask.add(btnChuyen = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Chuyển", 18, "#039BE5", "#29B6F6", ""));
		pnTask.add(btnLuu = MyUiComponent.myButtonUI(new Dimension(120, 55),JButton.CENTER, "Lưu", 18, "#039BE5", "#29B6F6", "asset\\database.png"));
		pnTask.add(btnXoaRong = MyUiComponent.myButtonUI(new Dimension(150, 55),JButton.CENTER, "Xoá rỗng", 18, "#039BE5", "#29B6F6", "asset\\eraser.png"));
		
		pnCenter.add(pnTask); //add to pnCenter

	//end-task
	//List Trọ và Table
		//Panel List trọ
		JPanel pnListTbl = new JPanel(new BorderLayout());
		pnListTbl.setOpaque(false);

		JPanel pnWestListTbl = new JPanel(); //panel chứa panelList và panel tác vụ: Chontatca
		pnWestListTbl.setOpaque(false);
		pnWestListTbl.setLayout(new BoxLayout(pnWestListTbl, BoxLayout.Y_AXIS));

		dfList = new  DefaultListModel<>();
		jLstTro = new JList<>(dfList);
		jLstTro.setFont(new Font("", 0, 15));
		
		jLstTro.setVisibleRowCount(17);

		JScrollPane pnList = new JScrollPane(jLstTro, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //panelList
		
		pnList.setBorder(new TitledBorder(null, "Địa chỉ trọ", 0, 0, new Font("", Font.BOLD, 20), Color.WHITE));
		pnWestListTbl.add(pnList);
		
//Panel tác vụ:  Chontatca
		JPanel pnTaskList = new JPanel(new FlowLayout(0,0,0));
		pnTaskList.setOpaque(false);
		pnTaskList.add(btnMoiSinhVien = MyUiComponent.myButtonUI(new Dimension((int)pnList.getPreferredSize().getWidth() + 20, 50), JButton.CENTER, "Mọi sinh viên", 18, "#039BE5", "#29B6F6", ""));
		
		pnWestListTbl.add(pnTaskList);
		pnListTbl.add(pnWestListTbl, BorderLayout.WEST);
		
		//Panel table
		String col[]= {
				"MSSV"	,"Tên sinh viên","Lớp", "Chuyên ngành", "Quê quán", "Trọ"
		};

		dfTable = new DefaultTableModel(col, 0);
		
		tblInfo = new JTable(dfTable){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblInfo.getTableHeader().setFont(new Font("", Font.BOLD, 15));
		tblInfo.setFont(new Font("", 0, 15));
		
		modelSelect = tblInfo.getSelectionModel();
		modelSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		//set pre-size = 0 -> kích thước chỉ vừa đủ cho 1 cột
		
		tblInfo.getColumnModel().getColumn(0).setMinWidth(100);
		tblInfo.getColumnModel().getColumn(0).setMaxWidth(100);
		tblInfo.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblInfo.getColumnModel().getColumn(2).setPreferredWidth(0);
		tblInfo.getColumnModel().getColumn(3).setPreferredWidth(0);
		tblInfo.getColumnModel().getColumn(4).setPreferredWidth(0);
		tblInfo.getColumnModel().getColumn(5).setPreferredWidth(100);
//	    tblInfo.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 18));
	     
		JScrollPane pnTable = new JScrollPane(tblInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTable.setOpaque(false);
		pnListTbl.add(pnTable, BorderLayout.CENTER);

		//Hoàn tất add vào pnCenter
		pnCenter.add(pnListTbl); //add to pnCenter

		addEventToComponent();
		loadAllSinhVien();
		loadListDiaChiTro();
		loadNganhToCbx();
		inputField(false);
		btnXoaRong.setEnabled(false);
		showIndex();
	//End - Task and Table
		return pnCenter;		
	}
	
	@Override
//truyền event
	public void addEventToComponent() {
		btnNext.addActionListener(this);
		btnEndNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnEndPrevious.addActionListener(this);
		pnSearch.getBtnSearch().addActionListener(this);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnChuyen.addActionListener(this);

		
		modelSelect.addListSelectionListener(new ListSelectionListener() { //add Event to JTable by Model Select
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblInfo.getSelectedRow();
				if (row >= 0) {
					setTextToField(row);
				}
				showIndex();
			}
		});
		
		jLstTro.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String diaChi = (String)jLstTro.getSelectedValue();
				if(diaChi != null) {
					loadSinhVienFromDiaChi(diaChi);
					showIndex();
					emptyField();
				}

			}
		});
		
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
		btnMoiSinhVien.addActionListener(this);
		btnLietKe.addActionListener(this);

	    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){
	        public boolean dispatchKeyEvent(KeyEvent e){
	        	
	          if(pnCenter.isVisible() && allowUseCase && e.getID() == KeyEvent.KEY_PRESSED)
	          {
	        	  int key = e.getKeyCode();
	        	  
	        	  switch (key) {
	        	  	case KeyEvent.VK_F5:
	        	  		pnSearch.getTxtSearch().requestFocus();
	        	  		keySaved = 1;
	        	  		lblHelpSearch.setText("Nhấn ESC để tắt trỏ tìm kiếm nhanh ");
	        	  		break;
	        	  	case KeyEvent.VK_F1:
	        	  		if(keySaved == 1) radMssv.setSelected(true);
	        	  		break;
	        	  	case KeyEvent.VK_F2:
	        	  		if(keySaved == 1) radTen.setSelected(true);
	        	  		break;
	        	  	case KeyEvent.VK_F3:
	        	  		if(keySaved == 1) radLop.setSelected(true);
	        	  		break;
	        	  	case KeyEvent.VK_F4:
	        	  		if(keySaved == 1) radQue.setSelected(true);
	        	  		break;
	        	  	case KeyEvent.VK_ENTER:
	        	  		if((keySaved == 1 && checkFocusTxtSearch)||checkFocusTxtSearch)
	        	  			searchHandling(pnSearch.getTxtSearch().getText());
	        	  		break;
	        	  	case KeyEvent.VK_ESCAPE:
	        	  		if (keySaved == 1) {
		        	  		pnCenter.getRootPane().requestFocusInWindow();
		        	  		keySaved = 0;
		        	  		lblHelpSearch.setText("Nhấn F5 để tắt trỏ tìm kiếm nhanh ");
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
			
//thêm
		}else if (getObject == btnThem && btnThem.getText().equalsIgnoreCase("Thêm")){	
			if (jLstTro.isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "Chưa chọn phòng trọ để thêm");
			}else {
				
				inputField(true);
				emptyField();
				txtMssv.requestFocus();
				tblInfo.clearSelection();
				txtMssv.setEnabled(true);
				btnThem.setText("Huỷ");
				btnXoaRong.setEnabled(true);
				btnXoa.setEnabled(false);
				btnSua.setEnabled(false);
				btnLuu.setEnabled(true);
				loadNganhToCbx();
				showIndex();
			}
		}else if(getObject == btnThem && btnThem.getText().equalsIgnoreCase("Huỷ")){
			
			inputField(false);			
			btnThem.setText("Thêm");		
			btnXoaRong.setEnabled(false);
			btnXoa.setEnabled(true);
			btnSua.setEnabled(true);
			btnLuu.setEnabled(false);
			loadNganhToCbx();
			
			showIndex();
			int row = tblInfo.getSelectedRow();
			setTextToField(row);
//xoá
		}else if (getObject == btnXoa) {
			int row = tblInfo.getSelectedRow();
			if (row >= 0) {
				int option = JOptionPane.showConfirmDialog(null, "Xoá Sinh Viên này?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
	
						String maSV = (String)tblInfo.getValueAt(row, 0);
						for (NhaTro phongTro : listPhongTro.getDsNhaTro()) {
							if (phongTro.deleteSinhVien(maSV)) {
								dfTable.removeRow(row);
								showIndex();
							}			
						}
					}
	
				}
			else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn sinh viên cần xoá", titleDialog, JOptionPane.INFORMATION_MESSAGE);
			}
			loadNganhToCbx();
//xoá rỗng			
		}else if (getObject == btnXoaRong) {			
			emptyField();	
			if (btnSua.getText().equalsIgnoreCase("Huỷ")) {
				int row = tblInfo.getSelectedRow();
				if (row >= 0) {
					txtMssv.setText((String) tblInfo.getValueAt(row, 0));
					txtTen.requestFocus();
				}
				
			}
				
//sửa
		}else if (getObject == btnSua && btnSua.getText().equalsIgnoreCase("Sửa")) {
			if(tblInfo.getSelectedRow()!= -1) {
				inputField(true);
				btnXoaRong.setEnabled(true);
				txtMssv.setEnabled(false);
				btnSua.setText("Huỷ");
				btnXoa.setEnabled(false);
				btnThem.setEnabled(false);
				btnLuu.setEnabled(true);
				txtTen.requestFocus();
				loadNganhToCbx();
				showIndex();
			}else {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng cần sửa");
			}

		}else if (getObject == btnSua && btnSua.getText().equalsIgnoreCase("Huỷ")) {
			inputField(false);
			btnSua.setText("Sửa");
			btnXoaRong.setEnabled(false);
			btnXoa.setEnabled(true);
			btnThem.setEnabled(true);
			btnLuu.setEnabled(false);
			setTextToField(tblInfo.getSelectedRow());
			loadNganhToCbx();
			showIndex();
//Lưu thêm
		}else if (getObject == btnLuu && btnThem.isEnabled() && btnThem.getText().equalsIgnoreCase("Huỷ")) {
			
				if(checkField()) {
					String diaChi = jLstTro.getSelectedValue().toString();
					String maSV = txtMssv.getText();
					String hoTen = txtTen.getText();
					String lop = txtLop.getText();
					String queQuan = txtQue.getText();
					String nganh = xacDinhNganh(lop);
					
					
					if(nganh==null) {
						JOptionPane.showMessageDialog(null, "Lớp không phù hợp!");
						return;
					}
					if(!checkData(hoTen, maSV))
						return;

					NhaTro phongTro = listPhongTro.timNhaTro(diaChi);
					if (phongTro != null) {
						SinhVien sv = new SinhVien(maSV, hoTen, lop, queQuan, nganh);
						String rowData []= {
								maSV,
								hoTen,
								lop,
								nganh,
								queQuan
						};
						if(phongTro.addSinhVien(sv)) {
							loadSinhVienFromDiaChi(diaChi);
						
							inputField(false);
							btnThem.setText("Thêm");
							btnXoaRong.setEnabled(false);
							btnXoa.setEnabled(true);
							btnSua.setEnabled(true);
							btnLuu.setEnabled(false);
							tblInfo.changeSelection(tblInfo.getRowCount()-1, -1, false, false);
							loadNganhToCbx();
							showIndex();
						}else 
							JOptionPane.showMessageDialog(null, "Sinh Viên này đã tồn tại");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Chưa nhập "+ checkTxt()+"!");
				}
//Lưu sửa
		}else if (getObject == btnLuu && btnSua.isEnabled() && btnSua.getText().equalsIgnoreCase("Huỷ")) {

			if(checkField()) {
				String maSV = txtMssv.getText();
				String hoTen = txtTen.getText();
				String lop = txtLop.getText();
				String queQuan = txtQue.getText();
				String nganh = xacDinhNganh(lop);

				if(nganh==null) {
					JOptionPane.showMessageDialog(null, "Lớp không phù hợp!");
					return;
				}
				
				if(!checkData(hoTen, maSV))
					return;
				
				SinhVien sv = new SinhVien(maSV, hoTen, lop, queQuan, nganh);
				String rowData []= {
						maSV,
						hoTen,
						lop,
						nganh,
						queQuan
				};
				for (NhaTro phongTro : listPhongTro.getDsNhaTro()) {
					if(phongTro.updateSinhVien(sv)) {
						int row = tblInfo.getSelectedRow();
						dfTable.removeRow(row);
						dfTable.insertRow(row, rowData);
						tblInfo.changeSelection(row, -1, false, false);
						inputField(false);
						btnSua.setText("Sửa");
						btnXoa.setEnabled(true);
						btnThem.setEnabled(true);
						btnLuu.setEnabled(false);
						btnXoaRong.setEnabled(false);
						loadNganhToCbx();						
						showIndex();
					}
					
				}

			}else {
				JOptionPane.showMessageDialog(null, "Chưa nhập "+ checkTxt()+"!");
			}
//mọi sinh viên
		}else if (getObject == btnMoiSinhVien) {		
			jLstTro.getSelectionModel().clearSelection();			
			loadListDiaChiTro();
			tblInfo.clearSelection();
			dfTable.setRowCount(0);
			
			emptyField();
			loadAllSinhVien();
			showIndex();
			
//liệt kê
		}else if (getObject == btnLietKe) {
			
			String nganhCbx = (String)cbxNganh.getSelectedItem();
			boolean flag = true;
			
			String diaChi = jLstTro.getSelectedValue();
			loadSinhVienFromDiaChi(diaChi);
			if (diaChi != null) {
				NhaTro nhaTro = listPhongTro.timNhaTro(diaChi);
				for (SinhVien sinhVien : nhaTro.getListSinhVien()) {			
					String nganh = sinhVien.getNganh();
					if (nganhCbx.equalsIgnoreCase(nganh)) {
						if (flag) {
							dfTable.setRowCount(0);
							flag = false;
						}
						String [] rowData = {
								sinhVien.getMaSV(),
								sinhVien.getHoTen(),
								sinhVien.getLop(),
								sinhVien.getNganh(),
								sinhVien.getQueQuan(),
								nhaTro.getDiaChi()
						};
						dfTable.addRow(rowData);
					}
				}
			}else {
				for (NhaTro nhaTro : listPhongTro.getDsNhaTro()) {
					for (SinhVien sinhVien : nhaTro.getListSinhVien()) {			
						String nganh = sinhVien.getNganh();
						if (nganhCbx.equalsIgnoreCase(nganh)) {
							if (flag) {
								dfTable.setRowCount(0);
								flag = false;
							}
							String [] rowData = {
									sinhVien.getMaSV(),
									sinhVien.getHoTen(),
									sinhVien.getLop(),
									sinhVien.getNganh(),
									sinhVien.getQueQuan(),
									nhaTro.getDiaChi()
							};
							dfTable.addRow(rowData);
						}
					}
				}
			}
			showIndex();
			if(flag)
				JOptionPane.showMessageDialog(null, "Không thế liệt kê theo ngành này !", titleDialog, JOptionPane.INFORMATION_MESSAGE);
//Chuyển
		}else if(getObject == btnChuyen) {
			int row = tblInfo.getSelectedRow();
			if (row!=-1) {
				int rowCount = jLstTro.getModel().getSize();
				
				Object rowData[] = new Object[rowCount];
				for(int i =0 ; i < rowCount; i++) {
					rowData[i] = dfList.getElementAt(i);
				}
				
				Object selection = JOptionPane.showInputDialog(null, "Chọn Trọ để chuyển","Chọn Trọ", JOptionPane.QUESTION_MESSAGE, null, rowData, rowData[0]);
		
				if (selection != null) {
					SinhVien sv = new SinhVien(txtMssv.getText(),txtTen.getText(),txtLop.getText(),txtQue.getText(),xacDinhNganh(txtLop.getText()));
					for(NhaTro ntCu : listPhongTro.getDsNhaTro()) {
						if(ntCu.getListSinhVien().contains(sv)) {
							for(NhaTro ntMoi : listPhongTro.getDsNhaTro()) 
								if(ntMoi.getDiaChi().equalsIgnoreCase(selection.toString())) {
									listPhongTro.chuyenSinhVienVaoTro(sv, ntCu, ntMoi);
									break;
								}
							loadSinhVienFromDiaChi(ntCu.getDiaChi());
							break;
						}
					}
				}

				
			}
			else 
				JOptionPane.showMessageDialog(null, "Chưa chọn sinh viên để chuyển");
		}
	
	}
	
	/**
	 * Description: phương thức tìm kiếm mssv, họ tên, lớp, quê quán
	 *
	 * Params:		input - nội dung tìm kiếm bao gồm mssv, họ tên, lớp, quê quán
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	
	public void searchHandling(String input) {
		allowUseCase = false;
		if(radMssv.isSelected()) {
			if(!timKiemTheoLoai(input, 1))
				JOptionPane.showMessageDialog(null, "Không thể tìm thấy MSSV !", titleDialog, 1);
		}else if (radTen.isSelected()) {
			if(!timKiemTheoLoai(input, 2))
				JOptionPane.showMessageDialog(null, "Không thể tìm thấy Họ tên !", titleDialog, 1);
		}else if (radLop.isSelected()) {
			if(!timKiemTheoLoai(input, 3))
				JOptionPane.showMessageDialog(null, "Không thể tìm thấy Lớp !", titleDialog, 1);
		}else if (radQue.isSelected()) {
			if(!timKiemTheoLoai(input, 4))
				JOptionPane.showMessageDialog(null, "Không thể tìm thấy Quê quán !", titleDialog, 1);
		}
		allowUseCase = true;
	}
	
	/**
	 * Description: phương thức tìm kiếm theo từng loại mssv, họ tên, lớp, quê quán
	 *
	 * Params:		input - nội dung tìm kiếm theo từng loại
	 * 				loại - gồm mssv, họ tên, lớp, quê quán | loai = 1: mssv, 2:ten, 3:lop, 4:que
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 18/4/2018
	 */
	
	private boolean timKiemTheoLoai(String input, int loai) { //loai = 1: mssv, 2:ten, 3:lop, 4:que
		boolean flag = false;
		loadSinhVienFromDiaChi(jLstTro.getSelectedValue()); //Load lại data sinh viên
		
		int maxRow = tblInfo.getRowCount();
		int count = 0;
		String[][] rowSaved = new String[maxRow][6];
			for (int row=0; row<maxRow; row++) {
				if(loai == 1) {
					String mssv = (String)tblInfo.getValueAt(row, 0);
					if (mssv.equalsIgnoreCase(input))
						flag = true;						
				}else if (loai == 2) {
					String ten = (String)tblInfo.getValueAt(row, 1);
					if (ten.equalsIgnoreCase(input))
						flag = true;									
				}else if (loai == 3) {
					String lop = (String)tblInfo.getValueAt(row, 2);
					if (lop.equalsIgnoreCase(input))
						flag = true;									
				}else if (loai == 4) {
					String que = (String)tblInfo.getValueAt(row, 4);
					if (que.equalsIgnoreCase(input))
						flag = true;									
				}
				if(flag) {
					rowSaved[count][0] = (String)tblInfo.getValueAt(row, 0);
					rowSaved[count][1] = (String)tblInfo.getValueAt(row, 1);
					rowSaved[count][2] = (String)tblInfo.getValueAt(row, 2);
					rowSaved[count][3] = (String)tblInfo.getValueAt(row, 3);
					rowSaved[count][4] = (String)tblInfo.getValueAt(row, 4);
					rowSaved[count][5] = (String)tblInfo.getValueAt(row, 5); 
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
			showIndex();
			return count > 0;
	}

	/**
	 * Description: phương thức load danh sách sinh viên từ 1 nhà trọ lên table
	 *
	 * Params:		diaChi: địa chỉ nhà trọ
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
//Lấy địa chỉ
	public void loadSinhVienFromDiaChi(String diaChi) {
		if (diaChi == null) {
			dfTable.setRowCount(0);
			loadAllSinhVien();
			return;
		}
			
		NhaTro phongTro = listPhongTro.timNhaTro(diaChi);
		if (phongTro != null) {
			tblInfo.clearSelection();
			dfTable.setRowCount(0);
			ArrayList<SinhVien> listSinhVien = phongTro.getListSinhVien();
			for (SinhVien sinhVien : listSinhVien) {
				String [] rowData = {
						sinhVien.getMaSV(),
						sinhVien.getHoTen(),
						sinhVien.getLop(),
						sinhVien.getNganh(),
						sinhVien.getQueQuan(),
						phongTro.getDiaChi()
				};
				dfTable.addRow(rowData);
			}
		}
		showIndex();	
		
	}
	
	/**
	 * Description: phương thức load tất cả danh sách sinh viên của tất cả nhà trọ lên table
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
	private void loadAllSinhVien() {		//Xanh Update
		for (NhaTro phongTro : listPhongTro.getDsNhaTro()) {
			for (SinhVien sinhVien : phongTro.getListSinhVien()) {
				String [] rowData =  {sinhVien.getMaSV(),sinhVien.getHoTen(),sinhVien.getLop(),sinhVien.getNganh(),sinhVien.getQueQuan(),phongTro.getDiaChi()};
				dfTable.addRow(rowData);
			}
		}
	}
	
	/**
	 * Description: phương thức load tất cả địa chỉ nhà trọ lên List
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
	public void loadListDiaChiTro() {		//Xanh Update
		dfList.clear();
		for (NhaTro phongTro : listPhongTro.getDsNhaTro()) {
			dfList.addElement(phongTro.getDiaChi());
		}
	}
	
	/**
	 * Description: phương thức load tất cả ngành của sinh viên lên Combobox
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
	private void loadNganhToCbx() {		//Xanh Update
		Set<String> listNganh = new HashSet<>();
		for (NhaTro phongTro : listPhongTro.getDsNhaTro()) {
			for (SinhVien sinhVien : phongTro.getListSinhVien()) {
				listNganh.add(sinhVien.getNganh());
			}
		}
		for (String nganh : listNganh)
			cbxNganh.addItem(nganh);
	}
		
	/**
	 * Description: phương thức làm trống tất cả ô nhập liệu
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
//trống textfield
	public void emptyField() {
		txtMssv.setText("");
		txtTen.setText("");
		txtLop.setText("");
		txtQue.setText("");
	}
		
	/**
	 * Description: phương thức set Enable/Disable các nút con trỏ
	 *
	 * Params:		b - nếu true: hiện ô nhập liệu. false: ẩn ô nhập liệu
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
//trạng thái con trỏ
	public void pointer(boolean b) {
		btnEndNext.setEnabled(b);
		btnEndPrevious.setEnabled(b);
		btnNext.setEnabled(b);
		btnPrevious.setEnabled(b);
	}
	
	/**
	 * Description: phương thức set Enable/Disable các ô nhập liệu
	 *
	 * Params:		b - nếu true: enable ô nhập liệu. false: ẩn ô nhập liệu
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
//trạng thái txt
	public void inputField(boolean b) {
		txtMssv.setEnabled(b);
		txtTen.setEnabled(b);
		txtLop.setEnabled(b);
		txtQue.setEnabled(b);
	}
	
	/**
	 * Description: phương thức kiểm tra các textFiled có rỗng hay không
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
//kiểm tra txt
	public boolean checkField() {
		if(txtMssv.getText().equals("")||txtTen.getText().equals("")||txtLop.getText().equals("")||txtQue.getText().equals(""))
			return false;
		return true;
	}
	
	/**
	 * Description: phương thức kiểm tra lớp có trong những ngành hiện tại của CSDL hay không
	 *
	 * Params:		lop - tên lớp
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
//nhận ngành từ lớp
	public String xacDinhNganh(String lop) {

		if (lop.matches("^DHCNTT.+")) return "Công nghệ thông tin";
		if (lop.matches("^DHKTPM.+")) return "Kỹ thuật phần mềm";
		if (lop.matches("^DHHTTT.+")) return "Hệ thống thông tin";
		if (lop.matches("^DHKHMT.+")) return "Khoa học máy tính";
		if (lop.matches("^DHQT.+")) return "Quản trị kinh doanh";
		if (lop.matches("^DHMK.+")) return "Marketing";
		if (lop.matches("^DHTN.+")) return "Tài chính ngân hàng";
		if (lop.matches("^DHTD.+")) return "Tài chính doanh nghiệp";
		return null;
	}
//đưa data vào txt
	public void setTextToField(int row) {
		if (row >= 0) {
			txtMssv.setText((String) tblInfo.getValueAt(row, 0));
			txtTen.setText((String) tblInfo.getValueAt(row, 1));
			txtLop.setText((String) tblInfo.getValueAt(row, 2));
			txtQue.setText((String) tblInfo.getValueAt(row, 4));
		}
	}
	
	/**
	 * Description: phương thức kiểm tra độ rỗng của từng textFiled
	 * Write by:	Phạm Xuân Anh
	 * Create date: 20/4/2018
	 */
	
//Kiểm tra txt 
	public String checkTxt() {
		boolean ma = false,ten=false,lop=false;
		String s1="",s2="",s3="",s4="";
		if(txtMssv.getText() == null || txtMssv.getText().equals("")) {
			ma = true;
			s1 = "Mã sinh viên";
		}
			
		if(txtTen.getText() == null || txtTen.getText().equals("")) {
			ten = true;
			s2 = "Tên";
			if(ma)
				s2 = ", Tên";
		}
			
		if(txtLop.getText() == null || txtLop.getText().equals("")) {
			lop = true;
			s3 = "Lớp";
			if(ten || ma)
				s3 =", Lớp";
		}
			
		if(txtQue.getText() == null || txtQue.getText().equals("")) {
			s4 = "Quê quán";
			if(ten || ma || lop)
				s4 =", Quê quán";
		}
			
		return s1+s2+s3+s4;		
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
	
	public boolean checkData(String hoTen, String maSV) {
		String patternTen = "(\\p{Lu}\\p{Ll}+\\s?)+";
		String patternMa = "[0-9]{8}";
		
		if (!hoTen.matches(patternTen)) {
			JOptionPane.showMessageDialog(null, "Tên Sinh Viên nhập không hợp lệ\n"
					+ "Tên Sinh Viên phải được viết hoa mỗi từ và mỗi từ phải có khoảng cách", titleDialog, JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!maSV.matches(patternMa)) {
			JOptionPane.showMessageDialog(null, "Mã Sinh Viên nhập không hợp lệ\n"
					+ "Mã Sinh Viên là dãy 8 kí tự số", titleDialog, JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

}
