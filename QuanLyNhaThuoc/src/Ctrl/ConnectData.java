package Ctrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectData {
	   public static Connection conn = null;
	    private static ConnectData instance = new ConnectData();

	    private ConnectData() {
	        connect(); // Tự động kết nối khi khởi tạo
	    }

	    public static ConnectData getInstance() {
	        return instance;
	    }

	    public void connect() {
	        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyHieuThuoc;encrypt=true;trustServerCertificate=true";
	        String user = "sa";
	        String password = "123";
//	        try {
//	            conn = DriverManager.getConnection(url, user, password);
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
	        try {
	            // Đảm bảo rằng driver đã được đăng ký
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection(url, user, password);
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void disconnect() {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static Connection getConnection() {
	        try {
	            if (conn == null || conn.isClosed()) {
	                instance.connect(); // Tạo lại kết nối nếu chưa có hoặc đã bị đóng
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }
}
