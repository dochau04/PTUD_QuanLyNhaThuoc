package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Ctrl.ConnectData;

public class HoaDon_DAO {
	// tự sinh mã mới
	public String phatSinhMaHoaDon() throws SQLException {
		String prefix = "HD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String sql = "SELECT COUNT(*) FROM HoaDon WHERE maHoaDon LIKE ?"; //đếm số lượng các hóa đơn trong bảng HoaDon mà maHoaDon có dạng khớp với một mẫu
		try (Connection conn = ConnectData.getConnection();
		        PreparedStatement ps = conn.prepareStatement(sql)) {
		        ps.setString(1, prefix + "%"); 
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            int count = rs.getInt(1) + 1; 
		            return prefix + String.format("%03d", count); 
		        }
		    } catch (SQLException e) {
		        System.out.println("Error generating maHoaDon: " + e.getMessage());
		        throw e;
		    }
		return prefix + "001";
		
	}
}
