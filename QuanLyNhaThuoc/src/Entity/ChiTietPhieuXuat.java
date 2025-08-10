package Entity;

public class ChiTietPhieuXuat {
	private PhieuXuatKho phieuXuatKho;
	private Thuoc thuoc;
	private int soLuong;
	private String ghiChu;
	public PhieuXuatKho getPhieuXuatKho() {
		return phieuXuatKho;
	}
	public void setPhieuXuatKho(PhieuXuatKho phieuXuatKho) {
		this.phieuXuatKho = phieuXuatKho;
	}
	public Thuoc getThuoc() {
		return thuoc;
	}
	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public String toString() {
		return "ChiTietPhieuXuat [phieuXuatKho=" + phieuXuatKho + ", thuoc=" + thuoc + ", soLuong=" + soLuong
				+ ", ghiChu=" + ghiChu + "]";
	}
	public ChiTietPhieuXuat(PhieuXuatKho phieuXuatKho, Thuoc thuoc, int soLuong, String ghiChu) {
		super();
		this.phieuXuatKho = phieuXuatKho;
		this.thuoc = thuoc;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}
	
	
}
