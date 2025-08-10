package Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTenNV;
	private String sdtNV;
	private String email;
	private boolean trangThai;
	private LocalDate ngayVaoLam;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String trinhDo;
	private String chucVu;
	private String sdt;
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoTenNV() {
		return hoTenNV;
	}
	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}
	public String getSdtNV() {
		return sdtNV;
	}
	public void setSdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getTrinhDo() {
		return trinhDo;
	}
	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", sdtNV=" + sdtNV + ", email=" + email
				+ ", trangThai=" + trangThai + ", ngayVaoLam=" + ngayVaoLam + ", gioiTinh=" + gioiTinh + ", ngaySinh="
				+ ngaySinh + ", trinhDo=" + trinhDo + ", chucVu=" + chucVu + "]";
	}
	public NhanVien() {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.sdtNV = sdt;
		this.email = email;
		this.trangThai = trangThai;
		this.ngayVaoLam = ngayVaoLam;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.trinhDo = trinhDo;
		this.chucVu = chucVu;
	}
	public NhanVien(String maNV, String tenNV, String sdt, String chucVu, String email2, boolean trangThai2,
			Date sqlNgayVaoLam, boolean gioiTinh, Date sqlNgaySinh, String trinhDo) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	public void setMaNhanVien(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
}
