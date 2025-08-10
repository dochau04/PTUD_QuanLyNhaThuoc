
package Dao;

import Entity.KhuyenMai;
import Ctrl.ConnectData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class KhuyenMai_DAO {
    private static final Logger LOGGER = Logger.getLogger(KhuyenMai_DAO.class.getName());

    // Lấy tất cả khuyến mãi
    public List<KhuyenMai> getAllKhuyenMai() {
        List<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai";

        try (Connection con = ConnectData.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dsKhuyenMai.add(mapResultSetToKhuyenMai(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách khuyến mãi", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách khuyến mãi: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsKhuyenMai;
    }

    // Thêm khuyến mãi
    public boolean addKhuyenMai(KhuyenMai km) {
        LOGGER.info("Bắt đầu thêm khuyến mãi: " + km.getMaKhuyenMai());
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectData.getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT INTO KhuyenMai (maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, trangThai, giaTriKhuyenMai, noiDungKhuyenMai) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, km.getMaKhuyenMai());
            stmt.setString(2, km.getTenKhuyenMai());
            stmt.setDate(3, km.getNgayBatDau() != null ? Date.valueOf(km.getNgayBatDau()) : null);
            stmt.setDate(4, km.getNgayKetThuc() != null ? Date.valueOf(km.getNgayKetThuc()) : null);
            stmt.setBoolean(5, km.isTrangThai());
            stmt.setFloat(6, km.getGiaTriKhuyenMai());
            stmt.setString(7, km.getNoiDungKhuyenMai());

            int rowsAffected = stmt.executeUpdate();
            con.commit();
            LOGGER.info("Thêm khuyến mãi thành công: " + km.getMaKhuyenMai());
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    LOGGER.info("Rollback transaction cho khuyến mãi: " + km.getMaKhuyenMai());
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Lỗi khi rollback", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi thêm khuyến mãi", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm khuyến mãi: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Lỗi khi đóng kết nối", e);
            }
        }
    }

    // Cập nhật khuyến mãi
    public boolean updateKhuyenMai(KhuyenMai km) {
        LOGGER.info("Bắt đầu cập nhật khuyến mãi: " + km.getMaKhuyenMai());
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectData.getConnection();
            con.setAutoCommit(false);

            KhuyenMai kmCu = getKhuyenMaiByMaWithConnection(con, km.getMaKhuyenMai());
            if (kmCu == null) {
                LOGGER.warning("Không tìm thấy khuyến mãi: " + km.getMaKhuyenMai());
                JOptionPane.showMessageDialog(null, "Không tìm thấy khuyến mãi cần cập nhật",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String sql = "UPDATE KhuyenMai SET tenKhuyenMai = ?, ngayBatDau = ?, ngayKetThuc = ?, " +
                         "trangThai = ?, giaTriKhuyenMai = ?, noiDungKhuyenMai = ? WHERE maKhuyenMai = ?";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, km.getTenKhuyenMai());
            stmt.setDate(2, km.getNgayBatDau() != null ? Date.valueOf(km.getNgayBatDau()) : null);
            stmt.setDate(3, km.getNgayKetThuc() != null ? Date.valueOf(km.getNgayKetThuc()) : null);
            stmt.setBoolean(4, km.isTrangThai());
            stmt.setFloat(5, km.getGiaTriKhuyenMai());
            stmt.setString(6, km.getNoiDungKhuyenMai());
            stmt.setString(7, km.getMaKhuyenMai());

            int rowsAffected = stmt.executeUpdate();
            con.commit();
            LOGGER.info("Cập nhật khuyến mãi thành công: " + km.getMaKhuyenMai());
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    LOGGER.info("Rollback transaction cho khuyến mãi: " + km.getMaKhuyenMai());
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Lỗi khi rollback", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật khuyến mãi, SQL State: " + e.getSQLState() +
                       ", Error Code: " + e.getErrorCode(), e);
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật khuyến mãi: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Lỗi khi đóng kết nối", e);
            }
        }
    }

    // Xóa khuyến mãi
    public boolean deleteKhuyenMai(String maKhuyenMai) {
        LOGGER.info("Bắt đầu xóa khuyến mãi: " + maKhuyenMai);
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectData.getConnection();
            con.setAutoCommit(false);

            String sql = "DELETE FROM KhuyenMai WHERE maKhuyenMai = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maKhuyenMai);

            int rowsAffected = stmt.executeUpdate();
            con.commit();
            LOGGER.info("Xóa khuyến mãi thành công: " + maKhuyenMai);
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    LOGGER.info("Rollback transaction cho xóa khuyến mãi: " + maKhuyenMai);
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Lỗi khi rollback", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa khuyến mãi: " + maKhuyenMai, e);
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa khuyến mãi: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;

        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Lỗi khi đóng kết nối", e);
            }
        }
    }

    // Tìm kiếm khuyến mãi
    public List<KhuyenMai> searchKhuyenMai(String condition, String keyword) {
        List<KhuyenMai> dsKhuyenMai = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM KhuyenMai ");

        switch (condition) {
            case "Theo mã":
                sql.append("WHERE maKhuyenMai LIKE ?");
                break;
            case "Theo tên":
                sql.append("WHERE tenKhuyenMai LIKE ?");
                break;
            default:
                sql.append("WHERE maKhuyenMai LIKE ? OR tenKhuyenMai LIKE ?");
                break;
        }

        try (Connection con = ConnectData.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {

            if (condition.equals("Tất cả")) {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
            } else {
                stmt.setString(1, "%" + keyword + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dsKhuyenMai.add(mapResultSetToKhuyenMai(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm khuyến mãi", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm khuyến mãi: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsKhuyenMai;
    }

    // Lấy khuyến mãi theo mã
    public KhuyenMai getKhuyenMaiByMa(String maKhuyenMai) {
        String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";

        try (Connection con = ConnectData.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maKhuyenMai);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToKhuyenMai(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy khuyến mãi theo mã: " + maKhuyenMai, e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy khuyến mãi: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    // Lấy khuyến mãi theo mã với connection có sẵn
    private KhuyenMai getKhuyenMaiByMaWithConnection(Connection con, String maKhuyenMai) throws SQLException {
        String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maKhuyenMai);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToKhuyenMai(rs);
                }
            }
        }
        return null;
    }

    // Ánh xạ ResultSet thành đối tượng KhuyenMai
    private KhuyenMai mapResultSetToKhuyenMai(ResultSet rs) throws SQLException {
        String maKhuyenMai = rs.getString("maKhuyenMai");
        String tenKhuyenMai = rs.getString("tenKhuyenMai");
        LocalDate ngayBatDau = rs.getDate("ngayBatDau") != null ? rs.getDate("ngayBatDau").toLocalDate() : null;
        LocalDate ngayKetThuc = rs.getDate("ngayKetThuc") != null ? rs.getDate("ngayKetThuc").toLocalDate() : null;
        boolean trangThai = rs.getBoolean("trangThai");
        float giaTriKhuyenMai = rs.getFloat("giaTriKhuyenMai");
        String noiDungKhuyenMai = rs.getString("noiDungKhuyenMai");

        return new KhuyenMai(maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, trangThai,
                             giaTriKhuyenMai, noiDungKhuyenMai);
    }
}
