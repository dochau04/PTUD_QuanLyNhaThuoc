package Entity;

import java.util.Objects;

public class NhomThuoc {
	private String maNhomThuoc;
	private String tenNhomThuoc;
	public String getMaNhomThuoc() {
		return maNhomThuoc;
	}
	public void setMaNhomThuoc(String maNhomThuoc) {
		this.maNhomThuoc = maNhomThuoc;
	}
	public String getTenNhomThuoc() {
		return tenNhomThuoc;
	}
	public void setTenNhomThuoc(String tenNhomThuoc) {
		this.tenNhomThuoc = tenNhomThuoc;
	}
	@Override
	public String toString() {
		return "NhomThuoc [maNhomThuoc=" + maNhomThuoc + ", tenNhomThuoc=" + tenNhomThuoc + "]";
	}
	public NhomThuoc(String maNhomThuoc, String tenNhomThuoc) {
		super();
		this.maNhomThuoc = maNhomThuoc;
		this.tenNhomThuoc = tenNhomThuoc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maNhomThuoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhomThuoc other = (NhomThuoc) obj;
		return Objects.equals(maNhomThuoc, other.maNhomThuoc);
	}
	
	
}
