package Entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private Thuoc thuoc;
	private String soLuong;
	private String ghiChu;
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public Thuoc getThuoc() {
		return thuoc;
	}
	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}
	public String getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(String soLuong) {
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
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", thuoc=" + thuoc + ", soLuong=" + soLuong + ", ghiChu=" + ghiChu
				+ "]";
	}
	public ChiTietHoaDon(HoaDon hoaDon, Thuoc thuoc, String soLuong, String ghiChu) {
		super();
		this.hoaDon = hoaDon;
		this.thuoc = thuoc;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}
	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
	}
	public ChiTietHoaDon(String maHD, String maThuoc, int soLuong2) {
		// TODO Auto-generated constructor stub
	}

	
}
