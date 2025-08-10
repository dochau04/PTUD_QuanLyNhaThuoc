package Entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String hoTenKH;
	private String diaChi;
	private LoaiKhachHang loaiKH;
	private boolean gioiTinh;
	private String sdtKH;
	private String ghiChu;
	private String lichSuMuaHang;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoTenKH() {
		return hoTenKH;
	}
	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public LoaiKhachHang getLoaiKH() {
		return loaiKH;
	}
	public void setLoaiKH(LoaiKhachHang loaiKH) {
		this.loaiKH = loaiKH;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSdtKH() {
		return sdtKH;
	}
	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getLichSuMuaHang() {
		return lichSuMuaHang;
	}
	public void setLichSuMuaHang(String lichSuMuaHang) {
		this.lichSuMuaHang = lichSuMuaHang;
	}

	public KhachHang(String maKH, String hoTenKH, String diaChi, LoaiKhachHang loaiKH, boolean gioiTinh, String sdtKH,
			String ghiChu, String lichSuMuaHang) {
		super();
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
		this.diaChi = diaChi;
		this.loaiKH = loaiKH;
		this.gioiTinh = gioiTinh;
		this.sdtKH = sdtKH;
		this.ghiChu = ghiChu;
		this.lichSuMuaHang = lichSuMuaHang;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTenKH=" + hoTenKH + ", diaChi=" + diaChi + ", loaiKH=" + loaiKH
				+ ", gioiTinh=" + gioiTinh + ", sdtKH=" + sdtKH + ", ghiChu=" + ghiChu + ", lichSuMuaHang="
				+ lichSuMuaHang + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	public KhachHang() {
		super();
	}
	
}
