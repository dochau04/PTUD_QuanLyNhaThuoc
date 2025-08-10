package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private LocalDate ngayLapHoaDon;
	private KhuyenMai khuyenMai;
	private LocalDateTime thoiGianTao;
	private Double tongTien;
	public String getMaHoaDon() {
		return maHoaDon;
	}

	public HoaDon() {
		super();
		this.maHoaDon = maHoaDon;
		this.nhanVien = nhanVien;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.khuyenMai = khuyenMai;
		this.thoiGianTao = thoiGianTao;
		this.tongTien = tongTien;
		this.khachHang = khachHang;
		
	}

	public HoaDon(String maHoaDon, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapHoaDon, KhuyenMai khuyenMai,
			LocalDateTime thoiGianTao, Double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.khuyenMai = khuyenMai;
		this.thoiGianTao = thoiGianTao;
		this.tongTien = tongTien;
	}



	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", nhanVien=" + nhanVien + ", ngayLapHoaDon=" + ngayLapHoaDon
				+ ", KhuyenMai=" + khuyenMai + ", thoiGianTao=" + thoiGianTao + ", tongTien=" + tongTien + "]";
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien maNhanVien) {
		this.nhanVien = maNhanVien;
	}

	public LocalDate getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}


	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public LocalDateTime getThoiGianTao() {
		return thoiGianTao;
	}

	public void setThoiGianTao(LocalDateTime thoiGianTao) {
		this.thoiGianTao = thoiGianTao;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	} 
	
	
}
