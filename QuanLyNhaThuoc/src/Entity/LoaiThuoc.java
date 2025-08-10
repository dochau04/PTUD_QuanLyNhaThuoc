package Entity;

import java.util.Objects;

public class LoaiThuoc {
	private String maLoaiThuoc;
	private String tenLoaiThuoc;
	private String NhomThuoc;
	public String getMaLoaiThuoc() {
		return maLoaiThuoc;
	}
	public void setMaLoaiThuoc(String maLoaiThuoc) {
		this.maLoaiThuoc = maLoaiThuoc;
	}
	public String getTenLoaiThuoc() {
		return tenLoaiThuoc;
	}
	public void setTenLoaiThuoc(String tenLoaiThuoc) {
		this.tenLoaiThuoc = tenLoaiThuoc;
	}
	public String getNhomThuoc() {
		return NhomThuoc;
	}
	public void setNhomThuoc(String nhomThuoc) {
		NhomThuoc = nhomThuoc;
	}
	@Override
	public String toString() {
		return "LoaiThuoc [maLoaiThuoc=" + maLoaiThuoc + ", tenLoaiThuoc=" + tenLoaiThuoc + ", NhomThuoc=" + NhomThuoc
				+ "]";
	}
	public LoaiThuoc(String maLoaiThuoc, String tenLoaiThuoc, String nhomThuoc) {
		super();
		this.maLoaiThuoc = maLoaiThuoc;
		this.tenLoaiThuoc = tenLoaiThuoc;
		NhomThuoc = nhomThuoc;
	}
	public LoaiThuoc(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLoaiThuoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiThuoc other = (LoaiThuoc) obj;
		return Objects.equals(maLoaiThuoc, other.maLoaiThuoc);
	}
	
	
}
