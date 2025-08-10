package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import Ctrl.ConnectData;
import Entity.ChiTietHoaDon;

public class ChiTietHoaDon_Dao {
    // Thêm danh sách chi tiết hóa đơn
    // Thêm chi tiết hóa đơn
    public boolean themChiTietHoaDon(ChiTietHoaDon chiTiet) {
        String sql = "INSERT INTO ChiTietHoaDon (maHoaDon, maThuoc, soLuong) VALUES (?, ?, ?)";

        try (Connection conn = ConnectData.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, chiTiet.getHoaDon().getMaHoaDon());
            ps.setString(2, chiTiet.getThuoc().getMaThuoc());
            ps.setInt(3, Integer.parseInt(chiTiet.getSoLuong()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
