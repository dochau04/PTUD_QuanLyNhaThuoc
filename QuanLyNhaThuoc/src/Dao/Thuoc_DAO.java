package Dao;

import Entity.Thuoc;
import Entity.LoaiThuoc;
import Entity.DonVi;
import Ctrl.ConnectData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Thuoc_DAO {
    private static final Logger LOGGER = Logger.getLogger(Thuoc_DAO.class.getName());

    private Thuoc mapResultSetToThuoc(ResultSet rs) throws SQLException {
        String maThuoc = rs.getString("maThuoc");
        String tenThuoc = rs.getString("tenThuoc");
        
        String maLoaiThuoc = rs.getString("maLoaiThuoc");
        String tenLoai = rs.getString("tenLoai");
        LoaiThuoc loaiThuoc = new LoaiThuoc(maLoaiThuoc, tenLoai, null);
        
        String maDonVi = rs.getString("maDonVi");
        String tenDonVi = rs.getString("tenDonVi");
        DonVi donVi = new DonVi(maDonVi, tenDonVi);
        
        LocalDate hsd = rs.getDate("HSD") != null ? rs.getDate("HSD").toLocalDate() : null;
        double giaBan = rs.getDouble("giaBan");
        int soLuongTon = rs.getInt("soLuongTon");
        String soLo = rs.getString("soLo");
        LocalDate nsx = rs.getDate("ngaySanXuat") != null ? rs.getDate("ngaySanXuat").toLocalDate() : null;
        String nhaSanXuat = rs.getString("nhaSanXuat");
        
        return new Thuoc(maThuoc, tenThuoc, loaiThuoc, donVi, hsd, 
                         giaBan, soLuongTon, soLo, nsx, nhaSanXuat);
    }

    public List<Thuoc> getAllThuoc() {
        List<Thuoc> dsThuoc = new ArrayList<>();
        String sql = "SELECT t.*, lt.tenLoai, dv.tenDonVi " +
                     "FROM Thuoc t " +
                     "JOIN LoaiThuoc lt ON t.maLoaiThuoc = lt.maLoaiThuoc " +
                     "JOIN DonVi dv ON t.maDonVi = dv.maDonVi";
        
        try (Connection con = ConnectData.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                dsThuoc.add(mapResultSetToThuoc(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách thuốc", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách thuốc: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsThuoc;
    }

    public boolean addThuoc(Thuoc thuoc) {
        LOGGER.info("Bắt đầu thêm thuốc: " + thuoc.getMaThuoc() + ", soLo: " + thuoc.getSoLo());
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectData.getConnection();
            con.setAutoCommit(false);

            if (!isLoaiThuocExists(con, thuoc.getLoaiThuoc().getMaLoaiThuoc())) {
                LOGGER.warning("Mã loại thuốc không tồn tại: " + thuoc.getLoaiThuoc().getMaLoaiThuoc());
                JOptionPane.showMessageDialog(null, "Mã loại thuốc không tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!isDonViExists(con, thuoc.getDonVi().getMaDonVi())) {
                LOGGER.warning("Mã đơn vị không tồn tại: " + thuoc.getDonVi().getMaDonVi());
                JOptionPane.showMessageDialog(null, "Mã đơn vị không tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String sql = "INSERT INTO Thuoc (maThuoc, tenThuoc, maLoaiThuoc, maDonVi, " +
                         "HSD, giaBan, soLuongTon, soLo, ngaySanXuat, nhaSanXuat) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, thuoc.getMaThuoc());
            stmt.setString(2, thuoc.getTenThuoc());
            stmt.setString(3, thuoc.getLoaiThuoc().getMaLoaiThuoc());
            stmt.setString(4, thuoc.getDonVi().getMaDonVi());
            stmt.setDate(5, thuoc.getHanSuDung() != null ? Date.valueOf(thuoc.getHanSuDung()) : null);
            stmt.setDouble(6, thuoc.getGiaBan());
            stmt.setInt(7, thuoc.getSoLuongTon());
            stmt.setString(8, thuoc.getSoLo());
            stmt.setDate(9, thuoc.getNgaySanXuat() != null ? Date.valueOf(thuoc.getNgaySanXuat()) : null);
            stmt.setString(10, thuoc.getNhaSanXuat());
            
            int rowsAffected = stmt.executeUpdate();
            con.commit();
            LOGGER.info("Thêm thuốc thành công: " + thuoc.getMaThuoc() + ", soLo: " + thuoc.getSoLo());
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    LOGGER.info("Rollback transaction cho thuốc: " + thuoc.getMaThuoc() + ", soLo: " + thuoc.getSoLo());
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Lỗi khi rollback", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi thêm thuốc, SQL State: " + e.getSQLState() + 
                       ", Error Code: " + e.getErrorCode(), e);
            if (e.getMessage().contains("PRIMARY KEY")) {
                JOptionPane.showMessageDialog(null, 
                    "Lỗi: Cặp mã thuốc " + thuoc.getMaThuoc() + " và số lô " + thuoc.getSoLo() + 
                    " đã tồn tại.", 
                    "Lỗi trùng khóa", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Lỗi khi thêm thuốc: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
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

    public boolean updateThuoc(Thuoc thuoc) {
        LOGGER.info("Bắt đầu cập nhật thuốc: " + thuoc.getMaThuoc() + ", soLo: " + thuoc.getSoLo());
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectData.getConnection();
            con.setAutoCommit(false);

            if (!isLoaiThuocExists(con, thuoc.getLoaiThuoc().getMaLoaiThuoc())) {
                LOGGER.warning("Mã loại thuốc không tồn tại: " + thuoc.getLoaiThuoc().getMaLoaiThuoc());
                JOptionPane.showMessageDialog(null, "Mã loại thuốc không tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!isDonViExists(con, thuoc.getDonVi().getMaDonVi())) {
                LOGGER.warning("Mã đơn vị không tồn tại: " + thuoc.getDonVi().getMaDonVi());
                JOptionPane.showMessageDialog(null, "Mã đơn vị không tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            Thuoc thuocCu = getThuocByMaAndSoLoWithConnection(con, thuoc.getMaThuoc(), thuoc.getSoLo());
            if (thuocCu == null) {
                LOGGER.warning("Không tìm thấy thuốc: " + thuoc.getMaThuoc() + ", soLo: " + thuoc.getSoLo());
                JOptionPane.showMessageDialog(null, "Không tìm thấy thuốc cần cập nhật với mã " + 
                    thuoc.getMaThuoc() + " và số lô " + thuoc.getSoLo(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String sql = "UPDATE Thuoc SET tenThuoc = ?, maLoaiThuoc = ?, maDonVi = ?, " +
                         "HSD = ?, giaBan = ?, soLuongTon = ?, ngaySanXuat = ?, nhaSanXuat = ? " +
                         "WHERE maThuoc = ? AND soLo = ?";
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, thuoc.getTenThuoc());
            stmt.setString(2, thuoc.getLoaiThuoc().getMaLoaiThuoc());
            stmt.setString(3, thuoc.getDonVi().getMaDonVi());
            stmt.setDate(4, thuoc.getHanSuDung() != null ? Date.valueOf(thuoc.getHanSuDung()) : null);
            stmt.setDouble(5, thuoc.getGiaBan());
            stmt.setInt(6, thuoc.getSoLuongTon());
            stmt.setDate(7, thuoc.getNgaySanXuat() != null ? Date.valueOf(thuoc.getNgaySanXuat()) : null);
            stmt.setString(8, thuoc.getNhaSanXuat());
            stmt.setString(9, thuoc.getMaThuoc());
            stmt.setString(10, thuoc.getSoLo());

            int rowsAffected = stmt.executeUpdate();
            con.commit();
            LOGGER.info("Cập nhật thuốc thành công: " + thuoc.getMaThuoc() + ", soLo: " + thuoc.getSoLo());
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    LOGGER.info("Rollback transaction cho thuốc: " + thuoc.getMaThuoc() + ", soLo: " + thuoc.getSoLo());
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Lỗi khi rollback", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật thuốc, SQL State: " + e.getSQLState() + 
                       ", Error Code: " + e.getErrorCode(), e);
            JOptionPane.showMessageDialog(null, 
                "Lỗi khi cập nhật thuốc: " + e.getMessage(), 
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

    public boolean deleteThuoc(String maThuoc, String soLo) {
        LOGGER.info("Bắt đầu xóa thuốc: " + maThuoc + ", soLo: " + soLo);
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectData.getConnection();
            con.setAutoCommit(false);

            String sql = "DELETE FROM Thuoc WHERE maThuoc = ? AND soLo = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maThuoc);
            stmt.setString(2, soLo);

            int rowsAffected = stmt.executeUpdate();
            con.commit();
            LOGGER.info("Xóa thuốc thành công: " + maThuoc + ", soLo: " + soLo);
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    LOGGER.info("Rollback transaction cho xóa thuốc: " + maThuoc + ", soLo: " + soLo);
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Lỗi khi rollback", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa thuốc: " + maThuoc + ", soLo: " + soLo, e);
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa thuốc: " + e.getMessage(), 
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

    public List<Thuoc> searchThuoc(String condition, String keyword) {
        List<Thuoc> dsThuoc = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT t.*, lt.tenLoai, dv.tenDonVi " +
            "FROM Thuoc t " +
            "JOIN LoaiThuoc lt ON t.maLoaiThuoc = lt.maLoaiThuoc " +
            "JOIN DonVi dv ON t.maDonVi = dv.maDonVi "
        );
        
        switch (condition) {
            case "Theo mã":
                sql.append("WHERE t.maThuoc LIKE ?");
                break;
            case "Theo tên":
                sql.append("WHERE t.tenThuoc LIKE ?");
                break;
            default:
                sql.append("WHERE t.maThuoc LIKE ? OR t.tenThuoc LIKE ?");
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
                    dsThuoc.add(mapResultSetToThuoc(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm thuốc", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm thuốc: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsThuoc;
    }

    public List<Thuoc> filterThuoc(String maLoaiThuoc, String maDonVi, LocalDate minHSD) {
        List<Thuoc> dsThuoc = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT t.*, lt.tenLoai, dv.tenDonVi " +
            "FROM Thuoc t " +
            "LEFT JOIN LoaiThuoc lt ON t.maLoaiThuoc = lt.maLoaiThuoc " +
            "LEFT JOIN DonVi dv ON t.maDonVi = dv.maDonVi WHERE 1=1"
        );
        
        if (maLoaiThuoc != null && !maLoaiThuoc.isEmpty()) {
            sql.append(" AND t.maLoaiThuoc = ?");
        }
        if (maDonVi != null && !maDonVi.isEmpty()) {
            sql.append(" AND t.maDonVi = ?");
        }
        if (minHSD != null) {
            sql.append(" AND t.HSD >= ?");
        }
        
        try (Connection con = ConnectData.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {
            
            int paramIndex = 1;
            if (maLoaiThuoc != null && !maLoaiThuoc.isEmpty()) {
                stmt.setString(paramIndex++, maLoaiThuoc);
            }
            if (maDonVi != null && !maDonVi.isEmpty()) {
                stmt.setString(paramIndex++, maDonVi);
            }
            if (minHSD != null) {
                stmt.setDate(paramIndex++, Date.valueOf(minHSD));
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dsThuoc.add(mapResultSetToThuoc(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lọc thuốc", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lọc thuốc: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsThuoc;
    }

    public List<LoaiThuoc> getAllLoaiThuoc() {
        List<LoaiThuoc> dsLoaiThuoc = new ArrayList<>();
        String sql = "SELECT * FROM LoaiThuoc";
        
        try (Connection con = ConnectData.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                dsLoaiThuoc.add(new LoaiThuoc(
                    rs.getString("maLoaiThuoc"), 
                    rs.getString("tenLoai"), 
                    null
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách loại thuốc", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách loại thuốc: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsLoaiThuoc;
    }

    public List<DonVi> getAllDonVi() {
        List<DonVi> dsDonVi = new ArrayList<>();
        String sql = "SELECT * FROM DonVi";
        
        try (Connection con = ConnectData.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                dsDonVi.add(new DonVi(
                    rs.getString("maDonVi"), 
                    rs.getString("tenDonVi")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách đơn vị", e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách đơn vị: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return dsDonVi;
    }

    public Thuoc getThuocByMaAndSoLo(String maThuoc, String soLo) {
        String sql = "SELECT t.*, lt.tenLoai, dv.tenDonVi " +
                     "FROM Thuoc t " +
                     "JOIN LoaiThuoc lt ON t.maLoaiThuoc = lt.maLoaiThuoc " +
                     "JOIN DonVi dv ON t.maDonVi = dv.maDonVi " +
                     "WHERE t.maThuoc = ? AND t.soLo = ?";
        
        try (Connection con = ConnectData.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, maThuoc);
            stmt.setString(2, soLo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToThuoc(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy thuốc theo mã: " + maThuoc + ", soLo: " + soLo, e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy thuốc: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public String getLastMaThuocByPrefix(String prefix) {
        String sql = "SELECT TOP 1 maThuoc FROM Thuoc WHERE maThuoc LIKE ? ORDER BY maThuoc DESC";
        
        try (Connection con = ConnectData.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, prefix + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("maThuoc");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy mã thuốc cuối cùng với prefix: " + prefix, e);
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy mã thuốc: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private boolean isLoaiThuocExists(Connection con, String maLoaiThuoc) throws SQLException {
        String sql = "SELECT 1 FROM LoaiThuoc WHERE maLoaiThuoc = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maLoaiThuoc);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private boolean isDonViExists(Connection con, String maDonVi) throws SQLException {
        String sql = "SELECT 1 FROM DonVi WHERE maDonVi = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maDonVi);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private Thuoc getThuocByMaAndSoLoWithConnection(Connection con, String maThuoc, String soLo) throws SQLException {
        String sql = "SELECT t.*, lt.tenLoai, dv.tenDonVi " +
                     "FROM Thuoc t " +
                     "JOIN LoaiThuoc lt ON t.maLoaiThuoc = lt.maLoaiThuoc " +
                     "JOIN DonVi dv ON t.maDonVi = dv.maDonVi " +
                     "WHERE t.maThuoc = ? AND t.soLo = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maThuoc);
            stmt.setString(2, soLo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToThuoc(rs);
                }
            }
        }
        return null;
    }
}