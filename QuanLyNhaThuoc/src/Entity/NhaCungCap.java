package Entity;

import java.util.Objects;

public class NhaCungCap {
	private String maNhaCC;
	private String tenNhaCC;
	private String diaChi;
	private String email;
	private String sdtNCC;
	public String getMaNhaCC() {
		return maNhaCC;
	}
	public void setMaNhaCC(String maNhaCC) {
		this.maNhaCC = maNhaCC;
	}
	public String getTenNhaCC() {
		return tenNhaCC;
	}
	public void setTenNhaCC(String tenNhaCC) {
		this.tenNhaCC = tenNhaCC;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdtNCC() {
		return sdtNCC;
	}
	public void setSdtNCC(String sdtNCC) {
		this.sdtNCC = sdtNCC;
	}
	@Override
	public String toString() {
		return "NhaCungCap [maNhaCC=" + maNhaCC + ", tenNhaCC=" + tenNhaCC + ", diaChi=" + diaChi + ", email=" + email
				+ ", sdtNCC=" + sdtNCC + "]";
	}
	public NhaCungCap(String maNhaCC, String tenNhaCC, String diaChi, String email, String sdtNCC) {
		super();
		this.maNhaCC = maNhaCC;
		this.tenNhaCC = tenNhaCC;
		this.diaChi = diaChi;
		this.email = email;
		this.sdtNCC = sdtNCC;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhaCC);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(maNhaCC, other.maNhaCC);
	}
	
	
}
