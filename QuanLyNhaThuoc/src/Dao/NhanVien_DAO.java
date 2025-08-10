package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import Ctrl.ConnectData;
import Entity.NhanVien;


public class NhanVien_DAO {

    private static Object ngayVaoLam;
	private static NhanVien nhanVien;

//	public static Connection getConnection() throws SQLException {
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyHieuThuoc;encrypt=true;trustServerCertificate=true;";
//        String user = "sa";
//        String password = "";
//        return DriverManager.getConnection(url, user, password);
//    }
//	{"STT", "Mã NV", "Tên NV", "SĐT", "Chức vụ", "Email", "Trạng thái", "Ngày vào làm", "Giới tính", "Ngày sinh",  "Trình độ"};
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();

        try (Connection conn = ConnectData.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM NhanVien")) {

            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getString("maNV"),
                        rs.getString("hoTenNV"),
                        rs.getString("sdtNV"),
                        rs.getString("chucVu"),
                        rs.getString("email"),
                        rs.getBoolean("trangThai"),
                        rs.getDate("ngayVaoLam"),
                        rs.getBoolean("gioiTinh"),
                        rs.getDate("ngaySinh"),
                        rs.getString("trinhDo")
                );
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
        
    }
    public List<NhanVien> searchNhanVien(String searchText) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE maNV LIKE ? OR hoTenNV LIKE ?";
        try (Connection conn = ConnectData.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + searchText + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien(
                            rs.getString("maNV"),
                            rs.getString("hoTenNV"),
                            rs.getString("sdtNV"),
                            rs.getString("chucVu"),
                            rs.getString("email"),
                            rs.getBoolean("trangThai"),
                            rs.getDate("ngayVaoLam"),
                            rs.getBoolean("gioiTinh"),
                            rs.getDate("ngaySinh"),
                            rs.getString("trinhDo")
                    );
                    list.add(nv);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhanVien searchNhanVienByMaNV(String maNV) {
        NhanVien nv = null;
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        try (Connection conn = ConnectData.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNV);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nv = new NhanVien(
                            rs.getString("maNV"),
                            rs.getString("hoTenNV"),
                            rs.getString("sdtNV"),
                            rs.getString("chucVu"),
                            rs.getString("email"),
                            rs.getBoolean("trangThai"),
                            rs.getDate("ngayVaoLam"),
                            rs.getBoolean("gioiTinh"),
                            rs.getDate("ngaySinh"),
                            rs.getString("trinhDo")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    };

    public boolean updateNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET hoTenNV = ?, sdtNV = ?, chucVu = ?, email = ?, trangThai = ?, ngayVaoLam = ?, gioiTinh = ?, ngaySinh = ?, trinhDo = ? WHERE maNV = ?";
        try (Connection conn = ConnectData.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nv.getHoTenNV());
            stmt.setString(2, nv.getSdtNV());
            stmt.setString(3, nv.getChucVu());
            stmt.setString(4, nv.getEmail());
            stmt.setBoolean(5, nv.isTrangThai());
//            stmt.setDate(6, new java.sql.Date(nv.getNgayVaoLam().getTime()));
//            rs.getDate("NSX").toLocalDate();
            stmt.setDate(6, Date.valueOf(nv.getNgayVaoLam()));
            stmt.setBoolean(7, nv.isGioiTinh());
            stmt.setDate(8, Date.valueOf(nv.getNgaySinh()));
            stmt.setString(9, nv.getTrinhDo());
            stmt.setString(10, nv.getMaNV());

            return stmt.executeUpdate() > 0; // thành công nếu có ít nhất 1 dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean themNhanVien(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (maNV, hoTenNV, sdtNV, chucVu, email, trangThai, ngayVaoLam, gioiTinh, ngaySinh, trinhDo) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectData.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getHoTenNV());
            stmt.setString(3, nv.getSdtNV());
            stmt.setString(4, nv.getChucVu());
            stmt.setString(5, nv.getEmail());
            stmt.setBoolean(6, nv.isTrangThai());
            stmt.setDate(7, Date.valueOf(nv.getNgayVaoLam()));
            stmt.setBoolean(8, nv.isGioiTinh());
            stmt.setDate(9, Date.valueOf(nv.getNgaySinh()));
            stmt.setString(10, nv.getTrinhDo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}

