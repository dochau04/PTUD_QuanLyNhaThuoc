package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhieuXuatKho {
	private String maPhieuXuat;
	private LocalDate ngayXuat;
	private NhanVien nhanVien;
	private Double tongTien;
	public String getMaPhieuXuat() {
		return maPhieuXuat;
	}
	public void setMaPhieuXuat(String maPhieuXuat) {
		this.maPhieuXuat = maPhieuXuat;
	}
	public LocalDate getNgayXuat() {
		return ngayXuat;
	}
	public void setNgayXuat(LocalDate ngayXuat) {
		this.ngayXuat = ngayXuat;
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
		return "PhieuXuatKHo [maPhieuXuat=" + maPhieuXuat + ", ngayXuat=" + ngayXuat + ", nhanVien=" + nhanVien
				+ ", tongTien=" + tongTien + "]";
	}
	public PhieuXuatKho(String maPhieuXuat, LocalDate ngayXuat, NhanVien nhanVien, Double tongTien) {
		super();
		this.maPhieuXuat = maPhieuXuat;
		this.ngayXuat = ngayXuat;
		this.nhanVien = nhanVien;
		this.tongTien = tongTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieuXuat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuXuatKho other = (PhieuXuatKho) obj;
		return Objects.equals(maPhieuXuat, other.maPhieuXuat);
	}
	
	
}
