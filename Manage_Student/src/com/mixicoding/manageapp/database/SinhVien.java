package com.mixicoding.manageapp.database;

/**
 * Description: Lớp SinhVien
 * Write by:	Võ Phi Khanh
 * Create date: 15/4/2018
 */

public class SinhVien {
	private String maSV;
	private String hoTen;
	private String lop;
	private String queQuan;
	private String nganh;

	
	public SinhVien(String maSV, String hoTen, String lop, String queQuan, String nganh) {
		super();
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.lop = lop;
		this.queQuan = queQuan;
		this.nganh = nganh;
	}

	public SinhVien(String maSV) {
		this.maSV = maSV;
		this.hoTen = "";
		this.lop = "";
		this.queQuan = "";
		this.nganh = "";
	}

	public SinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public String getNganh() {
		return nganh;
	}

	public void setNganh(String nganh) {
		this.nganh = nganh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSV == null) ? 0 : maSV.hashCode());
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
		SinhVien other = (SinhVien) obj;
		if (maSV == null) {
			if (other.maSV != null)
				return false;
		} else if (!maSV.equals(other.maSV))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", hoTen=" + hoTen + ", lop=" + lop + ", queQuan=" + queQuan + "]";
	}

}
