package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Ctrl.ConnectData;

public class BanHang_DAO {
	
	//---lấy số hóa đơn được tạo 
	public static int laySoThuTuHoaDonTrongNgay() {
	    int soThuTu = 1;
	    String sql = "SELECT COUNT(*) FROM HoaDon WHERE CAST(thoiGianTao AS DATE) = CAST(GETDATE() AS DATE)";
	    try (Connection conn = ConnectData.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        if (rs.next()) {
	            soThuTu = rs.getInt(1) + 1;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return soThuTu;
	}
	
	
}
