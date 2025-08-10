package Entity;

public class ChiTietPhieuNhap {
	private PhieuNhapKho phieuNhapKho;
	private Thuoc thuoc;
	private int soLuong;
	private String ghiChu;
	public PhieuNhapKho getPhieuNhapKho() {
		return phieuNhapKho;
	}
	public void setPhieuNhapKho(PhieuNhapKho phieuNhapKho) {
		this.phieuNhapKho = phieuNhapKho;
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
		return "ChiTietPhieuNhap [phieuNhapKho=" + phieuNhapKho + ", thuoc=" + thuoc + ", soLuong=" + soLuong
				+ ", ghiChu=" + ghiChu + "]";
	}
	public ChiTietPhieuNhap(PhieuNhapKho phieuNhapKho, Thuoc thuoc, int soLuong, String ghiChu) {
		super();
		this.phieuNhapKho = phieuNhapKho;
		this.thuoc = thuoc;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}
	
	
}
