package Dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import Ctrl.ConnectData;
import Entity.KhachHang;
import Entity.LoaiKhachHang;
import Entity.NhanVien;

public class KhachHang_DAO {
	
	public static KhachHang timKhachTheoSDT(String sdt) {
		String sql = "{call layKhachHangTuSDT(?)}";
	    try (Connection conn = ConnectData.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, sdt);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                KhachHang kh = new KhachHang();
	                kh.setMaKH(rs.getString("maKH"));
	                kh.setHoTenKH(rs.getString("hoTen"));
	                kh.setDiaChi(rs.getString("diaChi"));
	                
	                // Lấy thông tin loại khách hàng
	                LoaiKhachHang loai = new LoaiKhachHang();
	                loai.setTenLoaiKH(rs.getString("tenLoai"));  // Đảm bảo rằng bạn đang lấy đúng trường từ bảng
	                kh.setLoaiKH(loai);
	                
	                return kh;  // Trả về đối tượng KhachHang
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static List<KhachHang> getAllDanhSachKhachHang() {
	    List<KhachHang> khachHang = new ArrayList<>();
	    String sql = "{call layDanhSachKhachHang}";
	    try (Connection conn = ConnectData.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            KhachHang kh = new KhachHang();
	            kh.setMaKH(rs.getString("maKH"));
	            kh.setHoTenKH(rs.getString("hoTen"));
	            kh.setSdtKH(rs.getString("sdtKH"));

	            LoaiKhachHang loai = new LoaiKhachHang();
	            loai.setTenLoaiKH(rs.getString("tenLoai"));
	            kh.setLoaiKH(loai);
	            
	            kh.setDiaChi(rs.getString("diaChi"));
	            kh.setGhiChu(rs.getString("ghiChu"));
	            kh.setGioiTinh(rs.getInt("gioiTinh")==1);
	            kh.setLichSuMuaHang(rs.getString("lichSuMuaHang"));

	            khachHang.add(kh);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khachHang;
	}

	
	
	
//	public static KhachHang timKhachTheoSDT(String sdt) {
//	    String sql =  "SELECT kh.maKH, kh.hoTen, kh.diaChi, kh.sdtKH, lkh.tenLoai " +
//                "FROM KhachHang kh " +
//                "JOIN LoaiKhachHang lkh ON kh.maLoaiKhachHang = lkh.maLoaiKhachHang " +
//                "WHERE kh.sdtKH = ?";
//	    try (Connection conn = ConnectData.getConnection();
//	         PreparedStatement stmt = conn.prepareStatement(sql)) {
//	        stmt.setString(1, sdt);
//	        try (ResultSet rs = stmt.executeQuery()) {
//	            if (rs.next()) {
//	                KhachHang kh = new KhachHang();
//	                kh.setMaKH(rs.getString("maKH"));
//	                kh.setHoTenKH(rs.getString("hoTen"));
//	                kh.setDiaChi(rs.getString("diaChi"));
//	                
//	                // Lấy thông tin loại khách hàng
//	                LoaiKhachHang loai = new LoaiKhachHang();
//	                loai.setTenLoaiKH(rs.getString("tenLoai"));  // Đảm bảo rằng bạn đang lấy đúng trường từ bảng
//	                kh.setLoaiKH(loai);
//	                
//	                return kh;  // Trả về đối tượng KhachHang
//	            }
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return null;  // Trả về null nếu không tìm thấy khách hàng
//	}
	
//	public static String timMaKhachTheoSDT(String sdt) {
//	    String sql = "SELECT maKH FROM KhachHang WHERE sdtKH = ?";
//	    try (Connection conn = ConnectData.getConnection();
//	         PreparedStatement ps = conn.prepareStatement(sql)) {
//
//	        ps.setString(1, sdt);
//	        ResultSet rs = ps.executeQuery();
//	        if (rs.next()) {
//	            return rs.getString("maKH"); // Trả về mã khách hàng
//	        } else {
//	            return null; // Nếu không tìm thấy khách hàng
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return null;
//	    }
//	}
//


}
