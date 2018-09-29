package com.mixicoding.manageapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DanhSachNhaTro {
	private List<NhaTro> dsNhaTro;

	public DanhSachNhaTro() {
		dsNhaTro = new ArrayList<NhaTro>();
		loadNhaTroFromDatabase();
	}

	/**
	 * Description: phương thức load danh sách nhà trọ từ Database
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	
	
	public List<NhaTro> loadNhaTroFromDatabase() {

		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from NhaTro";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String diaChi = rs.getString(1);
				String chuNha = rs.getString(2);
				String soDT = rs.getString(3);

				NhaTro pt = new NhaTro(diaChi, chuNha, soDT);
				dsNhaTro.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNhaTro;
	}

	public List<NhaTro> getDsNhaTro() {
		return dsNhaTro;
	}
	
	/**
	 * Description: phương thức thêm 1 nhà trọ
	 *
	 * Params:		diaChi: địa chỉ của nhà trọ
	 * 				chuNha: tên chủ nhà
	 * 				soDT  : số điện thoại của chủ nhà
	 * 
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	
	public boolean them(String diaChi, String chuNha, String soDT) {
		int n = 0;
		NhaTro nhaTro = new NhaTro(diaChi, chuNha, soDT);
		if (!dsNhaTro.contains(nhaTro)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement st = null;
			try {
				st = con.prepareStatement("insert into NhaTro values(?, ?, ?)");
				st.setString(1, diaChi);
				st.setString(2, chuNha);
				st.setString(3, soDT);
				n = st.executeUpdate();
				
				dsNhaTro.add(nhaTro);
				System.out.println(n);
				System.out.println("Insert success");
			} catch (SQLException e) {
				e.printStackTrace();				
				System.out.println("Insert error \n");
			}
		}
		return n > 0;
	}

	/**
	 * Description: phương thức xoá 1 nhà trọ
	 *
	 * Params:		diaChi: địa chỉ của nhà trọ
	 * 
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	public boolean delete(String diaChi) {
		int n = 0;
		for (NhaTro nhaTro : dsNhaTro) {
			if (nhaTro.getDiaChi().equalsIgnoreCase(diaChi)) {
				Connection con = Database.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = con.prepareStatement("delete from NhaTro where diaChi = N'" + diaChi + "'");
					n = st.executeUpdate();
					dsNhaTro.remove(nhaTro);

					System.out.println("delete success");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("delete error \n");
				}
				break;
			}
		}
		return n > 0;
	}

	/**
	 * Description: phương thức cập nhật 1 nhà trọ
	 *
	 * Params:		diaChi:		địa chỉ cũ ủa nhà trọ
	 * 				newDiaChi:	địa chỉ mới của nhà trọ
	 * 				chuNha:		tên mới của chủ nhà trọ
	 * 				soDT:		số điện thoại mới của chủ nhà trọ
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 17/4/2018
	 */
	
	public boolean update(String diaChi, String newDiaChi, String chuNha, String soDT) {
		int n = 0;
		for (NhaTro nhaTro : dsNhaTro) {
			if (nhaTro.getDiaChi().equalsIgnoreCase(diaChi)) {

				Connection con = Database.getInstance().getConnection();
				PreparedStatement st = null;
				try {

					addAndDropConstraint(con, false);
					
					//update table nha tro
					st = con.prepareStatement("update NhaTro set diaChi = ?, chuNha = ?, soDT = ? where diaChi = ?");
					st.setString(1, newDiaChi);
					st.setString(2, chuNha);
					st.setString(3, soDT);
					st.setString(4, diaChi);
					n = st.executeUpdate();

					//update table sinh vien
					st = con.prepareStatement("update SinhVien set diaChi = ? where diaChi = ?"); 
					st.setString(1, newDiaChi);
					st.setString(2, diaChi);
					st.executeUpdate();
					
					nhaTro.setDiaChi(newDiaChi);
					nhaTro.setChuNha(chuNha);
					nhaTro.setSoDT(soDT);
					
					System.out.println("Update success");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Update error");
				} finally {
					addAndDropConstraint(con, true);
				}
				break;
			}
		}
		return n > 0;
	}

	/**
	 * Description: phương thức thêm, bỏ ràng buộc khoá ngoại
	 *
	 * Params:		con:	đối tượng connection của datatbase
	 * 				flag:	nếu flag = true thì thêm ràng buộc khoá ngoại,
	 * 						nếu flag = false thì xoá ràng buộc khoá ngoại
	 * Write by:	Vũ Hoàng Minh Huy
	 * Create date: 17/4/2018
	 */
	
	public void addAndDropConstraint(Connection con, boolean flag) { // false = drop, true = add
		try {
			if (!flag) {
				(con.createStatement()).executeUpdate("ALTER TABLE [dbo].[SinhVien] DROP CONSTRAINT FK_dcTro"); // xoá ràng buộc khoá ngoại
			} else {
				(con.createStatement())
						.executeUpdate("ALTER TABLE [dbo].[SinhVien] ADD CONSTRAINT FK_dcTro foreign key(diaChi)" // đặt ràng buộc khoá ngoại																			// ngoại
								+ "references NhaTro(diaChi)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Description: phương thức tìm nhà trọ
	 *
	 * Params:		diaChi: địa chỉ của nhà trọ
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	
	public NhaTro timNhaTro(String diaChi) {
		for (NhaTro nhaTro : dsNhaTro) {
			if (nhaTro.getDiaChi().equalsIgnoreCase(diaChi)) {
				return nhaTro;
			}
		}
		return null;
	}

	/**
	 * Description: phương thức xuất toàn bộ thông tin nhà trọ
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	
	public void xuatNhaTro() {
		for (NhaTro pt : dsNhaTro) {
			System.out.println(pt);
		}
	}
	public void chuyenSinhVienVaoTro(SinhVien sv, NhaTro ntCu, NhaTro ntMoi) {
		if(dsNhaTro.contains(ntCu)) {
			ntCu.chuyenSinhVien(sv, ntMoi);
			for(NhaTro nt : dsNhaTro) {
				if(nt.getDiaChi().equals(ntMoi.getDiaChi())) {
					nt.getListSinhVien().add(sv);
					break;
				}
			}
		}
	}
}
