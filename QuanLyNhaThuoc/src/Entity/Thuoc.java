package Entity;

import java.time.LocalDate;
import java.util.Objects;

public class Thuoc {
	private String maThuoc;
	private String tenThuoc;
	private LoaiThuoc loaiThuoc;
	private DonVi donVi;
	private LocalDate hanSuDung;
	private Double giaBan;
	private int soLuongTon;
	private String soLo;
	private LocalDate ngaySanXuat;
	private String nhaSanXuat;

	public Thuoc() {
		super();
	}

	public Thuoc(String maThuoc, String tenThuoc, LoaiThuoc loaiThuoc, DonVi donVi, LocalDate hanSuDung,
				 Double giaBan, int soLuongTon, String soLo, LocalDate ngaySanXuat, String nhaSanXuat) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiThuoc = loaiThuoc;
		this.donVi = donVi;
		this.hanSuDung = hanSuDung;
		this.giaBan = giaBan;
		this.soLuongTon = soLuongTon;
		this.soLo = soLo;
		this.ngaySanXuat = ngaySanXuat;
		this.nhaSanXuat = nhaSanXuat;
	}

	public String getMaThuoc() {
		return maThuoc;
	}

	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public LoaiThuoc getLoaiThuoc() {
		return loaiThuoc;
	}

	public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}

	public DonVi getDonVi() {
		return donVi;
	}

	public void setDonVi(DonVi donVi) {
		this.donVi = donVi;
	}

	public LocalDate getHanSuDung() {
		return hanSuDung;
	}

	public void setHanSuDung(LocalDate hanSuDung) {
		this.hanSuDung = hanSuDung;
	}

	public Double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public String getSoLo() {
		return soLo;
	}

	public void setSoLo(String soLo) {
		this.soLo = soLo;
	}

	public LocalDate getNgaySanXuat() {
		return ngaySanXuat;
	}

	public void setNgaySanXuat(LocalDate ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}

	public String getNhaSanXuat() {
		return nhaSanXuat;
	}

	public void setNhaSanXuat(String nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}

	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", loaiThuoc=" + loaiThuoc
				+ ", donVi=" + donVi + ", hanSuDung=" + hanSuDung + ", giaBan=" + giaBan
				+ ", soLuongTon=" + soLuongTon + ", soLo=" + soLo + ", ngaySanXuat=" + ngaySanXuat
				+ ", nhaSanXuat=" + nhaSanXuat + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maThuoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Thuoc other = (Thuoc) obj;
		return Objects.equals(maThuoc, other.maThuoc);
	}
}
