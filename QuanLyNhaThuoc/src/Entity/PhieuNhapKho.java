package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhieuNhapKho {
	private String maPhieuNhap;
	private LocalDate ngayNhap;
	private NhaCungCap nhaCungCap;
	private NhanVien nhanVien;
	private Double tongTien;
	public String getMaPhieuNhap() {
		return maPhieuNhap;
	}
	public void setMaPhieuNhap(String maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}
	public LocalDate getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(LocalDate ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public Double getTongTien() {
		return tongTien;
	}
	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public String toString() {
		return "PhieuNhapKho [maPhieuNhap=" + maPhieuNhap + ", ngayNhap=" + ngayNhap + ", nhaCungCap=" + nhaCungCap
				+ ", nhanVien=" + nhanVien + ", tongTien=" + tongTien + "]";
	}
	public PhieuNhapKho(String maPhieuNhap, LocalDate ngayNhap, NhaCungCap nhaCungCap, NhanVien nhanVien,
			Double tongTien) {
		super();
		this.maPhieuNhap = maPhieuNhap;
		this.ngayNhap = ngayNhap;
		this.nhaCungCap = nhaCungCap;
		this.nhanVien = nhanVien;
		this.tongTien = tongTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieuNhap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuNhapKho other = (PhieuNhapKho) obj;
		return Objects.equals(maPhieuNhap, other.maPhieuNhap);
	}
	
	
}
