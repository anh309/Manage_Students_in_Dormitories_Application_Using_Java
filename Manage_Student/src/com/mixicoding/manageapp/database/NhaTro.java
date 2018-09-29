package com.mixicoding.manageapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class NhaTro {
	private String diaChi;
	private String chuNha;
	private String soDT;
	private List<SinhVien> listSinhVien;

	public NhaTro(String diaChi, String chuNha, String soDT) {
		super();
		this.diaChi = diaChi;
		this.chuNha = chuNha;
		this.soDT = soDT;
		this.listSinhVien = new ArrayList<>();
		loadSinhVienFromDatabase();
	}

	public ArrayList<SinhVien> getListSinhVien() {
		return (ArrayList<SinhVien>) listSinhVien;
	}

	public NhaTro() {
		this.listSinhVien = new ArrayList<>();
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getChuNha() {
		return chuNha;
	}

	public void setChuNha(String chuNha) {
		this.chuNha = chuNha;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	/**
	 * Description: phương thức load dữ liệu SinhVien trong Database
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	public List<SinhVien> loadSinhVienFromDatabase() {
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from SinhVien where diaChi = N'" + diaChi + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSV = rs.getString(1);
				String hoTen = rs.getString(2);
				String lop = rs.getString(3);
				String queQuan = rs.getString(4);
				String nganh = rs.getString(5);
				SinhVien sv = new SinhVien(maSV, hoTen, lop, queQuan, nganh);
				listSinhVien.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSinhVien;
	}

	/**
	 * Description: phương thức thêm 1 sinh viên
	 *
	 * Params:		sv: đối tượng của SinhVien
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	
	public boolean addSinhVien(SinhVien sv) {
		int n = 0;
		if (listSinhVien.contains(sv)) {
			return false;
		} else {
			String maSV = sv.getMaSV();
			String hoTen = sv.getHoTen();
			String lop = sv.getLop();
			String queQuan = sv.getQueQuan();
			String nganh = sv.getNganh();
			Connection con = Database.getInstance().getConnection();
			PreparedStatement st = null;

			try {
				st = con.prepareStatement("insert into SinhVien values('" + maSV + "', N'" + hoTen + "', '" + lop
						+ "',  N'" + queQuan + "',  N'" + nganh + "',  N'" + diaChi + "')");
				n = st.executeUpdate();

				listSinhVien.add(sv);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	/**
	 * Description: phương thức xoá 1 sinhVien
	 *
	 * Params:		maSV: mã số sinh viên
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	
	public boolean deleteSinhVien(String maSV) {

		int n = 0;
		SinhVien sv = new SinhVien(maSV);
		if (listSinhVien.contains(sv)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement st = null;
			try {
				st = con.prepareStatement("delete from SinhVien where maSV =?");
				st.setString(1, maSV);
				n = st.executeUpdate();
				listSinhVien.remove(sv);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	/**
	 * Description: phương thức cập nhật thông tin sinh viên
	 *
	 * Params:		sv: đối tượng của SinhVien
	 * Write by:	Phạm Xuân Anh
	 * Create date: 18/4/2018
	 */
	
	public boolean updateSinhVien(SinhVien sv) {

		int n = 0;
		if (listSinhVien.contains(sv)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement st = null;
			try {
				st = con.prepareStatement("update SinhVien set hoTen =N'" + sv.getHoTen() + "', lop='" + sv.getLop()
						+ "', queQuan=N'" + sv.getQueQuan() + "', nganh=N'" + sv.getNganh() + "' where maSV ='"
						+ sv.getMaSV() + "'");
				n = st.executeUpdate();

				int index = listSinhVien.indexOf(sv);
				if (listSinhVien.contains(sv)) {
					listSinhVien.remove(index);
					listSinhVien.add(index, sv);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;

	}

	/**
	 * Description: phương thức tìm kiếm sinh viên
	 *
	 * Params:		maSV: mã số sinh viên
	 * Write by:	Võ Phi Khanh
	 * Create date: 15/4/2018
	 */
	
	public SinhVien timSinhVien(String maSV) {
		for (SinhVien sinhVien : listSinhVien) {
			if (sinhVien.getMaSV().equalsIgnoreCase(maSV)) {
				return sinhVien;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaChi == null) ? 0 : diaChi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaTro other = (NhaTro) obj;
		if (diaChi == null) {
			if (other.diaChi != null)
				return false;
		} else if (!diaChi.equals(other.diaChi))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PhongTro [diaChi=" + diaChi + ", chuNha=" + chuNha + ", soDT=" + soDT + ", sinhvien=" + "]";
	}

	public boolean chuyenSinhVien(SinhVien sv, NhaTro ntMoi) {

		int n = 0;
		if (listSinhVien.contains(sv)) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement st = null;
			try {
				st = con.prepareStatement("update SinhVien set diaChi =N'" + ntMoi.getDiaChi() + "' where maSV ='"+ sv.getMaSV() + "'");
				n = st.executeUpdate();

				int index = listSinhVien.indexOf(sv);
				listSinhVien.remove(index);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
}
