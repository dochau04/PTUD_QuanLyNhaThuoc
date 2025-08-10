package Gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Dao.KhachHang_DAO;
import Entity.KhachHang;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

public class KhachHang_GUI extends JFrame implements FocusListener,ActionListener{
	private JPanel panel_Full, panel_Header, panel_Header_1,panel_Menu,panel_Center,panel_Center_Left, panel_Center_Right;
	private JLabel label_HoTenNV,label_MaNV,label_CaLam,label_BatDau,label_KetThuc,label_HoaDonCaTruoc,label_HoaDonChuaXuLy,label_HoaDonHienDai;
	private JLabel label_DoanhThuCaTruoc,label_icon_DoanhThu,label_TienCaTruoc,label_TienQuyDauCa,label_TienQuy,label_icon_TienQuy;
	private JLabel label_Logo,label_Icon_NV,label_NV_Header,label_Ma_NV_Header,label_Menu,label_AvataNV;
    private JButton btnTraCuu,btnBanHang,btnThuoc,btnKhachHang,btnCaLam,btnTTNV,btnTroGiup,btnGioiThieu,btnDangXuat,btnLeft,btnKhuyenMai,btnKho,btnBaoCaoThongKe,btnNhanVien;
	private JButton btnMenu,btnNhanCa;
	private boolean isMenuVisible = true; // Trạng thái menu: hiện hay ẩn
	private Timer slideTimer;             // Timer để thực hiện hiệu ứng
	private int panel_Menu_Width;         // Chiều rộng của menu (sidebar)
	private JPanel panel_Header_Noth;
	private JPanel panel_Header_Left;
	private JDateChooser dateFrom;
	private JPanel panel_Header_Right;
	private JTextField txtTimKiem;
	private JDateChooser dateFromTimKiem;
	private JComboBox comboTimKiem;
	private JTextField txtMaKH;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtGioiTinh;
	private JTextField txtLoaiKH;
	private JTextArea txtGhiChu;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnDSKH;
	private JButton btnLoadLai;
	private JLabel lblThemKhach;
	private JLabel lblMaKH;
	private JLabel lblDiaChi;
	private JLabel lblHoTen;
	private JLabel lblSDT;
	private JLabel lblLoaiKH;
	private JLabel lblGhiChu;
	private JLabel lblGioiTinh;
	private JComboBox<String> comboGioiTinh;
	private JScrollPane scrollGhiChu;
	private int tocDoGian = 20; // mặc định
	private JPanel panelCenterNho,p2;
	private JButton btnThemNV;
	private JButton btnHuy;
	private DefaultTableModel modelBangThem;
	private JTable tableDanhSachThem;
	private JPanel panelDSKH;
	private DefaultTableModel modelBang;
	private JTable tableDanhSach;
	private JPanel panelCenterCapNhat;
	private JTextField txtMaKHCapNhat;
	private JTextField txtHoTenCapNhat;
	private JTextField txtDiaChiCapNhat;
	private JTextField txtSDTCapNhat;
	private JTextField txtLoaiKHCapNhat;
	private JComboBox comboGioiTinhCapNhat;
	private JTextArea txtGhiChuCapNhat;
	private JScrollPane scrollGhiChuCapNhat;
	private JButton btnDSCapNhat;
	private JButton btnHuyCapNhat;
	private JPanel pCapNhat;
	private DefaultTableModel modelBangCapNhat;
	private JTable tableDanhSachCapNhat;
	private JLabel lblCapNhatKhachHang;

	
	public KhachHang_GUI() {
		super("Quản lý nhân viên quản lý");
        KhachHang();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// full man hinh
        setLocationRelativeTo(null);
        setVisible(true);
        
	}
	public static void main(String[] args) {
		new KhachHang_GUI();
	}

	public void KhachHang() {
		
        panel_Full = new JPanel();
        setContentPane(panel_Full);
        panel_Full.setLayout(new BorderLayout());
        
        ///////////panel Header
        panel_Header = new JPanel();
        panel_Header.setLayout(new BorderLayout());
        panel_Full.add(panel_Header, BorderLayout.NORTH);
        
        panel_Header_Noth = new JPanel();
        panel_Header_Noth.setLayout(null);
        panel_Header_Noth.setBackground(new Color(0, 60, 42));
        panel_Header.add(panel_Header_Noth, BorderLayout.NORTH);
        
        ImageIcon image_Icon_Logo = new ImageIcon(getClass().getResource("/hinh_anh/logo.png"));
        Image img_Logo = image_Icon_Logo.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_logo = new ImageIcon(img_Logo);
        label_Logo = new JLabel(resizedIcon_logo);
        panel_Header_Noth.add(label_Logo);
        // icon Nhan Vien
        ImageIcon image_Icon_NV = new ImageIcon(getClass().getResource("/hinh_anh/iconNhanVien.png"));
        Image img_NV = image_Icon_NV.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_NV = new ImageIcon(img_NV);
        label_Icon_NV = new JLabel(resizedIcon_NV);
        panel_Header_Noth.add(label_Icon_NV);
        // label Nhan Vien
        label_NV_Header = new JLabel("Lâm Phát Đạt");
        label_NV_Header.setFont(new Font("Times New Roman", Font.BOLD, 22));
        label_NV_Header.setForeground(new Color(255, 255, 255));
        panel_Header_Noth.add(label_NV_Header);
        // label Ma Nhan Vien
        label_Ma_NV_Header = new JLabel("NV24003");
        label_Ma_NV_Header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label_Ma_NV_Header.setForeground(new Color(255, 255, 255));
        panel_Header_Noth.add(label_Ma_NV_Header);
        
        
        panel_Header_Left = new JPanel();
        panel_Header_Left.setLayout(null);
        panel_Header_Left.setBorder(new MatteBorder(2, 0, 0, 0, Color.WHITE));
        panel_Header_Left.setBackground(new Color(0, 60, 42));
        panel_Header.add(panel_Header_Left,BorderLayout.WEST);
        
        panel_Header_Right = new JPanel(null);
        panel_Header_Right.setBorder(new MatteBorder(2, 0, 0, 0, Color.WHITE));
        panel_Header_Right.setBackground(new Color(0, 60, 42));
        panel_Header.add(panel_Header_Right,BorderLayout.CENTER);
        
        // nut button menu
        btnMenu = new JButton();
        btnMenu.setBackground(new Color(0, 80, 42));
        btnMenu.setBorderPainted(false); // tắt viền
        btnMenu.setFocusPainted(false);
        panel_Header_Left.add(btnMenu);
        ImageIcon image_Icon_Menu = new ImageIcon(getClass().getResource("/hinh_anh/menu1.png"));
        Image img_menu = image_Icon_Menu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_menu = new ImageIcon(img_menu);
        label_Menu = new JLabel(resizedIcon_menu);
        btnMenu.addActionListener(e -> toggleMenu());
        btnMenu.add(label_Menu);
        
        //-----
        Font fn_18 = new Font("Times New Roman", Font.BOLD, 18);
        btnThem = new JButton("Thêm");
        btnCapNhat = new JButton("Cập nhật");
        btnDSKH = new JButton("Danh sách");
        btnLoadLai = new JButton("⟳");
        txtTimKiem = new JTextField("Tìm kiếm khách hàng");
        dateFromTimKiem = new JDateChooser();
        comboTimKiem= new JComboBox();
        comboTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tất cả","Bạc","Vàng","Kim cương","Vãng lai"}));
        comboTimKiem.setLightWeightPopupEnabled(false);
        
        panel_Header_Right.add(btnThem);
        panel_Header_Right.add(btnCapNhat);
        panel_Header_Right.add(btnDSKH);
        panel_Header_Right.add(btnLoadLai);
        panel_Header_Right.add(txtTimKiem);
        panel_Header_Right.add(comboTimKiem);
        panel_Header_Right.add(dateFromTimKiem);
        btnThem.setBounds(0, 15, 150, 35);
        btnCapNhat.setBounds(180, 15, 150, 35);
        btnDSKH.setBounds(360, 15, 150, 35);
        dateFromTimKiem.setBounds(690, 17, 150, 30);
        comboTimKiem.setBounds(850, 15, 120, 33);
        txtTimKiem.setBounds(980, 15, 220, 33);
        btnLoadLai.setBounds(1210, 17, 50, 30);

        txtTimKiem.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        btnThem.setFont(fn_18);
        btnCapNhat.setFont(fn_18);
        btnDSKH.setFont(fn_18);
        comboTimKiem.setFont(fn_18);
        dateFromTimKiem.setFont(fn_18);
        btn(btnThem);
        btn(btnCapNhat);
        btn(btnDSKH);
        btn(btnLoadLai);
        ///////// panel_Menu
        panel_Menu = new JPanel();

        panel_Menu.setBackground(new Color(0, 80, 42));
        panel_Full.add(panel_Menu, BorderLayout.WEST);
        panel_Menu.setLayout(null);
        
        btnTraCuu = createCustomButton("Tra cứu", "/hinh_anh/iconTimKiem_resized.png", 40, 40, 0, 3, 50, 8);
		panel_Menu.add(btnTraCuu);
		
		btnBanHang = createCustomButton("Quản lý bán hàng", "/hinh_anh/iconGioHang.png",  35, 35, 5, 7, 50, 8);
		panel_Menu.add(btnBanHang);
		
		btnThuoc = createCustomButton("Quản lý thuốc", "/hinh_anh/iconThuoc.png", 60, 60, -10, -7, 50, 8);
		panel_Menu.add(btnThuoc);
		
		btnKhachHang = createCustomButton("Quản lý khách hàng", "/hinh_anh/iconKhachHang.png",  60, 60, -10, -5, 50,8);
		panel_Menu.add(btnKhachHang);
		
		btnCaLam = createCustomButton("Quản lý ca làm", "/hinh_anh/iconCaLam.png",  40, 40, 0, 4, 50, 8);
		panel_Menu.add(btnCaLam);
		
		btnKhuyenMai = createCustomButton("Quản lý khuyến mãi", "/hinh_anh/iconKhuyenMai.png", 35, 35, 2, 5, 50, 8);
		panel_Menu.add(btnKhuyenMai);
		
		btnTTNV = createCustomButton("Thông tin nhân viên", "/hinh_anh/iconThongTin.png", 50, 50, -5, 0, 50, 8);
		panel_Menu.add(btnTTNV);
		
		btnKho = createCustomButton("Quản lý kho", "/hinh_anh/iconKho.png",  40, 40, 0, 2, 50, 8);
		panel_Menu.add(btnKho);
		
		btnBaoCaoThongKe = createCustomButton("Báo cáo và thống kê", "/hinh_anh/iconBaoCaoThongKe.png",  40, 40, 0, 2, 50, 8);
		panel_Menu.add(btnBaoCaoThongKe);
		
		btnNhanVien = createCustomButton("Quản lý nhân viên", "/hinh_anh/iconQLNV.png",  55, 55, -10, -5, 50, 8);
		panel_Menu.add(btnNhanVien);
		
		btnTroGiup = createCustomButton("Trợ giúp", "/hinh_anh/iconTroGiup.png",  45, 45, 0, 5, 50, 8);
		panel_Menu.add(btnTroGiup);
		
		btnGioiThieu = createCustomButton("Giới thiệu", "/hinh_anh/iconGioiThieu.png",34, 34, 4, 7, 50, 8);
		panel_Menu.add(btnGioiThieu);
		
		btnDangXuat = createCustomButton("Đăng xuất", "/hinh_anh/iconDangXuat.png", 36, 36, 5, 7, 50, 8);
		panel_Menu.add(btnDangXuat);

        
		//////////////panel_Center
		panel_Center = new JPanel();
		panel_Center.setLayout(new BorderLayout());
		panel_Center.setBackground(Color.white);
		panel_Full.add(panel_Center, BorderLayout.CENTER);
		
		panelDSKH = new JPanel(new BorderLayout());
		JLabel lblDSKH = new JLabel("DANH SÁCH KHÁCH HÀNG");
		lblDSKH.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		lblDSKH.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		String[] columnBangDanhSach = {"STT", "Mã khách hàng","Tên khách hàng", "Số điện thoại","Loại khách hàng","Địa chỉ","Ghi chú","Giới tính","Lịch sử mua hàng"};
		Object[][] dataDanhSach = {};

	    // Tạo model không cho chỉnh sửa
        modelBang = new DefaultTableModel(dataDanhSach, columnBangDanhSach) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa ô nào cả
            }
        };
        tableDanhSach = new JTable(modelBang);
        JScrollPane scrollPaneBang = new JScrollPane(tableDanhSach);
	     // Tùy chỉnh màu và font cho thanh tiêu đề (header)
        tableDanhSach.getTableHeader().setBackground(new Color(0, 60, 42)); // Màu nền thanh tiêu đề (ví dụ: xanh dương)
        tableDanhSach.getTableHeader().setForeground(Color.WHITE); // Màu chữ thanh tiêu đề
        tableDanhSach.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20)); // Đặt font chữ cho thanh tiêu đề
        tableDanhSach.setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableDanhSach.setBackground(new Color(255, 255, 255)); 
        
        panelDSKH.add(scrollPaneBang);
		panel_Center.add(lblDSKH,BorderLayout.NORTH);
		panel_Center.add(panelDSKH,BorderLayout.CENTER);
		
		
		btnBanHang.setBackground(Color.white);
	    // add sự kiện
	    txtTimKiem.addFocusListener(this);
	    btnThem.addActionListener(this);
	    btnDSKH.addActionListener(this);
	    btnCapNhat.addActionListener(this);
        // Gọi hàm resize sau khi frame hiển thị
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePanel(); // tự động resize khi thay đổi kích thước
            }
        });
	}
	////=============Giao diện Thêm khách hàng mới
	public void GDThemKhachHang() {
	    // Xóa và cập nhật panel chính
	    panel_Center.removeAll();
	    panel_Center.revalidate();
	    panel_Center.repaint();

		
		Font fn_22 = new Font("Times New Roman", Font.BOLD, 22);
		Font fn_25 = new Font("Times New Roman", Font.BOLD, 25);
		panelCenterNho = new JPanel(null);
		panelCenterNho.setBackground(Color.white);
		panel_Center.add(panelCenterNho);
		
		JPanel p1 = new JPanel(new BorderLayout());
		panelCenterNho.add(p1);
		
		//----------
		lblThemKhach = new JLabel("THÊM KHÁCH HÀNG MỚI");
		lblMaKH = new JLabel("Mã khách hàng");
		lblHoTen = new JLabel("Họ và tên");
		lblDiaChi = new JLabel("Địa chỉ");
		lblSDT = new JLabel("Số điện thoại");
		lblLoaiKH = new JLabel("Loại khách hàng");
		lblGioiTinh = new JLabel("Giới tính");
		lblGhiChu = new JLabel("Ghi chú");
		txtMaKH = new JTextField();
		txtHoTen = new JTextField();
		txtDiaChi = new JTextField();
		txtSDT = new JTextField();
		txtLoaiKH = new JTextField();
		comboGioiTinh = new JComboBox<>(new String[] { "Nam", "Nữ", "Khác" });
		txtGhiChu = new JTextArea();
		scrollGhiChu = new JScrollPane(txtGhiChu);
		btnThemNV = new JButton("Thêm");
		btnHuy = new JButton("Hủy");
		p2 = new JPanel(new BorderLayout());
		panelCenterNho.add(p2);
		String[] columnBangDanhSachThem = {"STT", "Mã khách hàng","Tên khách hàng", "Số điện thoại","Loại khách hàng","Địa chỉ"};
		Object[][] dataDanhSachThem = {};

	    // Tạo model không cho chỉnh sửa
        modelBangThem = new DefaultTableModel(dataDanhSachThem, columnBangDanhSachThem) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa ô nào cả
            }
        };
        tableDanhSachThem = new JTable(modelBangThem);
        JScrollPane scrollPaneBangThem = new JScrollPane(tableDanhSachThem);
	     // Tùy chỉnh màu và font cho thanh tiêu đề (header)
        tableDanhSachThem.getTableHeader().setBackground(new Color(0, 60, 42)); // Màu nền thanh tiêu đề (ví dụ: xanh dương)
        tableDanhSachThem.getTableHeader().setForeground(Color.WHITE); // Màu chữ thanh tiêu đề
        tableDanhSachThem.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20)); // Đặt font chữ cho thanh tiêu đề
        tableDanhSachThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableDanhSachThem.setBackground(new Color(255, 255, 255)); 
        
	    int centertW = panel_Center.getWidth();
	    lblThemKhach.setHorizontalAlignment(SwingConstants.CENTER);
	    lblThemKhach.setBounds(0, 0, centertW, 50);
	    int x = (int)(centertW*0.3);
	    int x1 = (int)(centertW*0.5);
	    int x2 = (int)(centertW*0.65);
	    int x3 = (int)(centertW*0.15);
//	    int x4 = (int)(centertW*0.97);
	    lblMaKH.setBounds(30, 50, 200, 50);
	    txtMaKH.setBounds(x3, 60, x, 30);
		lblSDT.setBounds(30, 90, 200, 50);
		txtSDT.setBounds(x3, 100, x, 30);
	    lblHoTen.setBounds(30, 130, 200, 50);
	    txtHoTen.setBounds(x3, 140, x, 30);
	    lblDiaChi.setBounds(30, 170, 200, 50);
	    txtDiaChi.setBounds(x3, 180, x, 30);
	    lblLoaiKH.setBounds(x1, 50, 200, 50);
	    txtLoaiKH.setBounds(x2, 60, x, 30);
	    lblGioiTinh.setBounds(x1, 90, 200, 50);
	    comboGioiTinh.setBounds(x2, 100, x, 30);
	    lblGhiChu.setBounds(x1, 130, 200, 50);
		scrollGhiChu.setBounds(x2, 140, x, 70);
		btnThemNV.setBounds(x, 230, x3, 35);
	    btnHuy.setBounds(x1, 230, x3, 35);
	    p2.setBounds(0, 280, centertW, 350);
	    
		lblThemKhach.setFont(fn_25);
		lblMaKH.setFont(fn_22);
		lblHoTen.setFont(fn_22);
		lblDiaChi.setFont(fn_22);
		lblSDT.setFont(fn_22);
		lblLoaiKH.setFont(fn_22);
		lblGioiTinh.setFont(fn_22);
		lblGhiChu.setFont(fn_22);
		txtMaKH.setFont(fn_22);
		txtHoTen.setFont(fn_22);
		txtSDT.setFont(fn_22);
		txtDiaChi.setFont(fn_22);
		txtLoaiKH.setFont(fn_22);
		comboGioiTinh.setFont(fn_22);
		txtGhiChu.setFont(fn_22);
		btnThemNV.setFont(fn_22);
		btnHuy.setFont(fn_22);
		btn(btnThemNV);
		btn(btnHuy);
		btnThemNV.setBackground(new Color(0, 82, 40));
		btnThemNV.setForeground(Color.white);
		voHieuHoaTextField(txtMaKH);
		voHieuHoaTextField(txtLoaiKH);
		
		panelCenterNho.add(lblThemKhach);
		panelCenterNho.add(lblMaKH);
		panelCenterNho.add(lblHoTen);
		panelCenterNho.add(lblSDT);
		panelCenterNho.add(lblDiaChi);
		panelCenterNho.add(lblLoaiKH);
		panelCenterNho.add(lblGioiTinh);
		panelCenterNho.add(lblGhiChu);
		panelCenterNho.add(txtMaKH);
		panelCenterNho.add(txtHoTen);
		panelCenterNho.add(txtDiaChi);
		panelCenterNho.add(txtSDT);
		panelCenterNho.add(txtLoaiKH);
		panelCenterNho.add(comboGioiTinh);
		panelCenterNho.add(scrollGhiChu);
		panelCenterNho.add(btnThemNV);
		panelCenterNho.add(btnHuy);
		p2.add(scrollPaneBangThem);
	}
	
	public void GDDanhSach() {
	    panel_Center.removeAll();
	    panel_Center.revalidate();
	    panel_Center.repaint();

		panelDSKH = new JPanel(new BorderLayout());
		JLabel lblDSKH = new JLabel("DANH SÁCH KHÁCH HÀNG");
		lblDSKH.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
		lblDSKH.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		String[] columnBangDanhSach = {"STT", "Mã khách hàng","Tên khách hàng", "Số điện thoại","Loại khách hàng","Địa chỉ","Ghi chú","Giới tính","Lịch sử mua hàng"};
		Object[][] dataDanhSach = {};

	    // Tạo model không cho chỉnh sửa
        modelBang = new DefaultTableModel(dataDanhSach, columnBangDanhSach) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa ô nào cả
            }
        };
        tableDanhSach = new JTable(modelBang);
        JScrollPane scrollPaneBang = new JScrollPane(tableDanhSach);
	     // Tùy chỉnh màu và font cho thanh tiêu đề (header)
        tableDanhSach.getTableHeader().setBackground(new Color(0, 60, 42)); // Màu nền thanh tiêu đề (ví dụ: xanh dương)
        tableDanhSach.getTableHeader().setForeground(Color.WHITE); // Màu chữ thanh tiêu đề
        tableDanhSach.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20)); // Đặt font chữ cho thanh tiêu đề
        tableDanhSach.setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableDanhSach.setBackground(new Color(255, 255, 255)); 
        
        panelDSKH.add(scrollPaneBang);
		panel_Center.add(lblDSKH,BorderLayout.NORTH);
		panel_Center.add(panelDSKH,BorderLayout.CENTER);
		
		loadDanhSachKhachHang();
	}
	
	public void GDCapNhat() {
	    panel_Center.removeAll();
	    panel_Center.revalidate();
	    panel_Center.repaint();
	    
		Font fn_22 = new Font("Times New Roman", Font.BOLD, 22);
		Font fn_25 = new Font("Times New Roman", Font.BOLD, 25);
	    panelCenterCapNhat = new JPanel(null);
	    panelCenterCapNhat.setBackground(Color.white);
	    panel_Center.add(panelCenterCapNhat);
	    
	    lblCapNhatKhachHang = new JLabel("CẬP NHẬT KHÁCH HÀNG");
		lblMaKH = new JLabel("Mã khách hàng");
		lblHoTen = new JLabel("Họ và tên");
		lblDiaChi = new JLabel("Địa chỉ");
		lblSDT = new JLabel("Số điện thoại");
		lblLoaiKH = new JLabel("Loại khách hàng");
		lblGioiTinh = new JLabel("Giới tính");
		lblGhiChu = new JLabel("Ghi chú");
		txtMaKHCapNhat = new JTextField();
		txtHoTenCapNhat = new JTextField();
		txtDiaChiCapNhat = new JTextField();
		txtSDTCapNhat = new JTextField();
		txtLoaiKHCapNhat = new JTextField();
		comboGioiTinhCapNhat = new JComboBox<>(new String[] { "Nam", "Nữ", "Khác" });
		txtGhiChuCapNhat = new JTextArea();
		scrollGhiChuCapNhat = new JScrollPane(txtGhiChu);
		btnDSCapNhat = new JButton("Cập nhật");
		btnHuyCapNhat = new JButton("Hủy");
		pCapNhat = new JPanel(new BorderLayout());
		panelCenterCapNhat.add(pCapNhat);
		String[] columnBangDanhSachCapNhat = {"STT", "Mã khách hàng","Tên khách hàng", "Số điện thoại","Loại khách hàng","Địa chỉ"};
		Object[][] dataDanhSachCapNhat = {};

	    // Tạo model không cho chỉnh sửa
        modelBangCapNhat = new DefaultTableModel(dataDanhSachCapNhat, columnBangDanhSachCapNhat) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa ô nào cả
            }
        };
        tableDanhSachCapNhat = new JTable(modelBangCapNhat);
        JScrollPane scrollPaneBangCapNhat = new JScrollPane(tableDanhSachCapNhat);
	     // Tùy chỉnh màu và font cho thanh tiêu đề (header)
        tableDanhSachCapNhat.getTableHeader().setBackground(new Color(0, 60, 42)); // Màu nền thanh tiêu đề (ví dụ: xanh dương)
        tableDanhSachCapNhat.getTableHeader().setForeground(Color.WHITE); // Màu chữ thanh tiêu đề
        tableDanhSachCapNhat.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20)); // Đặt font chữ cho thanh tiêu đề
        tableDanhSachCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableDanhSachCapNhat.setBackground(new Color(255, 255, 255)); 
        
	    int centertW = panel_Center.getWidth();
	    lblCapNhatKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
	    lblCapNhatKhachHang.setBounds(0, 0, centertW, 50);
	    int x = (int)(centertW*0.3);
	    int x1 = (int)(centertW*0.5);
	    int x2 = (int)(centertW*0.65);
	    int x3 = (int)(centertW*0.15);
	    lblMaKH.setBounds(30, 50, 200, 50);
	    txtMaKHCapNhat.setBounds(x3, 60, x, 30);
		lblSDT.setBounds(30, 90, 200, 50);
		txtSDTCapNhat.setBounds(x3, 100, x, 30);
	    lblHoTen.setBounds(30, 130, 200, 50);
	    txtHoTenCapNhat.setBounds(x3, 140, x, 30);
	    lblDiaChi.setBounds(30, 170, 200, 50);
	    txtDiaChiCapNhat.setBounds(x3, 180, x, 30);
	    lblLoaiKH.setBounds(x1, 50, 200, 50);
	    txtLoaiKHCapNhat.setBounds(x2, 60, x, 30);
	    lblGioiTinh.setBounds(x1, 90, 200, 50);
	    comboGioiTinhCapNhat.setBounds(x2, 100, x, 30);
	    lblGhiChu.setBounds(x1, 130, 200, 50);
		scrollGhiChuCapNhat.setBounds(x2, 140, x, 70);
		btnDSCapNhat.setBounds(x, 230, x3, 35);
	    btnHuyCapNhat.setBounds(x1, 230, x3, 35);
	    pCapNhat.setBounds(0, 280, centertW, 350);
	    
		lblCapNhatKhachHang.setFont(fn_25);
		lblMaKH.setFont(fn_22);
		lblHoTen.setFont(fn_22);
		lblDiaChi.setFont(fn_22);
		lblSDT.setFont(fn_22);
		lblLoaiKH.setFont(fn_22);
		lblGioiTinh.setFont(fn_22);
		lblGhiChu.setFont(fn_22);
		txtMaKHCapNhat.setFont(fn_22);
		txtHoTenCapNhat.setFont(fn_22);
		txtSDTCapNhat.setFont(fn_22);
		txtDiaChiCapNhat.setFont(fn_22);
		txtLoaiKHCapNhat.setFont(fn_22);
		comboGioiTinhCapNhat.setFont(fn_22);
		txtGhiChuCapNhat.setFont(fn_22);
		btnDSCapNhat.setFont(fn_22);
		btnHuyCapNhat.setFont(fn_22);
		btn(btnDSCapNhat);
		btn(btnHuyCapNhat);
		btnDSCapNhat.setBackground(new Color(0, 82, 40));
		btnDSCapNhat.setForeground(Color.white);
		voHieuHoaTextField(txtMaKHCapNhat);
		voHieuHoaTextField(txtLoaiKHCapNhat);
		
		panelCenterCapNhat.add(lblCapNhatKhachHang);
		panelCenterCapNhat.add(lblMaKH);
		panelCenterCapNhat.add(lblHoTen);
		panelCenterCapNhat.add(lblSDT);
		panelCenterCapNhat.add(lblDiaChi);
		panelCenterCapNhat.add(lblLoaiKH);
		panelCenterCapNhat.add(lblGioiTinh);
		panelCenterCapNhat.add(lblGhiChu);
		panelCenterCapNhat.add(txtMaKHCapNhat);
		panelCenterCapNhat.add(txtHoTenCapNhat);
		panelCenterCapNhat.add(txtDiaChiCapNhat);
		panelCenterCapNhat.add(txtSDTCapNhat);
		panelCenterCapNhat.add(txtLoaiKHCapNhat);
		panelCenterCapNhat.add(comboGioiTinhCapNhat);
		panelCenterCapNhat.add(scrollGhiChuCapNhat);
		panelCenterCapNhat.add(btnDSCapNhat);
		panelCenterCapNhat.add(btnHuyCapNhat);
		pCapNhat.add(scrollPaneBangCapNhat);
	    
		///Gọi hàm

	}
	
	////=============================
	////================Đầu phần xử lý===========
//	private void loadDanhSachKhachHang() {
//	        List <KhachHang> dsKH = KhachHang_DAO.getAllDanhSachKhachHang();
//	        int stt = 1; // Bắt đầu từ 1, có thể thay đổi tùy vào nhu cầu
//	        for (KhachHang kh : dsKH) {
//	        	String gioiTinhText = kh.isGioiTinh() ? "Nam" : "Nữ";
//	            modelBang.addRow(new Object[] {
//	                stt++, // Tăng giá trị STT trong mỗi lần lặp
//	                kh.getMaKH(),
//	                kh.getHoTenKH(),
//	                kh.getSdtKH(),
//	                kh.getLoaiKH().getTenLoaiKH(),
//	                kh.getDiaChi(),
//	                kh.getGhiChu(),
//	                gioiTinhText,
//	                kh.getLichSuMuaHang()
//	            });
//	        }
//	}
	private void loadDanhSachKhachHang() {
	    try {
	        List<KhachHang> dsKH = KhachHang_DAO.getAllDanhSachKhachHang();
	        modelBang.setRowCount(0); // Xóa dữ liệu cũ trước khi load mới
	        
	        System.out.println("Bắt đầu load " + dsKH.size() + " khách hàng vào bảng"); // Log kiểm tra
	        
	        int stt = 1;
	        for (KhachHang kh : dsKH) {
	            String gioiTinhText = kh.isGioiTinh() ? "Nam" : "Nữ";
	            modelBang.addRow(new Object[] {
	                stt++,
	                kh.getMaKH(),
	                kh.getHoTenKH(),
	                kh.getSdtKH(),
	                kh.getLoaiKH().getTenLoaiKH(),
	                kh.getDiaChi(),
	                kh.getGhiChu(),
	                gioiTinhText,
	                kh.getLichSuMuaHang()
	            });
	        }
	        System.out.println("Đã load xong dữ liệu vào bảng"); // Log kiểm tra
	    } catch (Exception e) {
	        System.err.println("Lỗi khi load danh sách khách hàng:");
	        e.printStackTrace();
	    }
	}
	////================Kết thúc xử lý===========
	private void resizePanel() {
		int panel_Full_Width = panel_Full.getWidth();
		int panel_Full_Height = panel_Full.getHeight();
		// panel_header
		int panel_Header_Width = panel_Full_Width;
		int panel_Header_Height = (int)(panel_Full_Height*0.15); //15% của height full
		panel_Header.setPreferredSize(new Dimension(panel_Header_Width, panel_Header_Height));
		int panel_Header_Noth_H = (int)(panel_Full_Height*0.07);//7% của height header
		panel_Header_Noth.setPreferredSize(new Dimension(panel_Header_Width,panel_Header_Noth_H));
		label_Logo.setBounds(10, 2,70,50);
		label_Icon_NV.setBounds(panel_Full_Width-100, 1, 60, 64);
		label_NV_Header.setBounds(panel_Header_Width-250, -2, 150, 50);
		label_Ma_NV_Header.setBounds(panel_Header_Width-250, 20, 150, 50);
		// panel_header_Left
		btnMenu.setBounds(20,20, 50, 30);
        label_Menu.setBounds(9, 0, 30, 30);
        int panel_Header_Left_Width = (int) (panel_Full_Width*0.17);
        panel_Header_Left.setPreferredSize(new Dimension(panel_Header_Left_Width, panel_Header_Width-panel_Header_Noth_H));
        panel_Header_Right.setPreferredSize(new Dimension(panel_Header_Width-panel_Header_Left_Width, panel_Header_Width-panel_Header_Noth_H));
        // panel_Menu
        panel_Menu_Width = (int)(panel_Full_Width*0.17);// 0.17% cua panel_Full
        int panel_Menu_Height = (int)(panel_Full_Height-panel_Header_Height);
        panel_Menu.setPreferredSize(new Dimension( panel_Menu_Width, panel_Menu_Height));
        // Các nút trong panel_menu
        btnTraCuu.setBounds(0, 2, panel_Menu_Width - 15, 45);
        btnBanHang.setBounds(0, 50, panel_Menu_Width - 15, 45);
        btnThuoc.setBounds(0, 98, panel_Menu_Width - 15, 45);
        btnKhachHang.setBounds(0, 146, panel_Menu_Width - 15, 45);
        btnCaLam.setBounds(0, 194, panel_Menu_Width - 15, 45);
        btnTTNV.setBounds(0, 242, panel_Menu_Width - 15, 45);
        btnKhuyenMai.setBounds(0, 290, panel_Menu_Width - 15, 45);
        btnKho.setBounds(0, 338, panel_Menu_Width - 15, 45);
        btnBaoCaoThongKe.setBounds(0, 386, panel_Menu_Width-15, 45);
        btnNhanVien.setBounds(0, 434, panel_Menu_Width-15, 45);
        btnTroGiup.setBounds(0, 482, panel_Menu_Width-15, 45);
        btnGioiThieu.setBounds(0, 530, panel_Menu_Width-15, 45);
        btnDangXuat.setBounds(0, panel_Menu_Height - 50, panel_Menu_Width - 15, 45);
        
        // panel_Center
        panel_Center.setPreferredSize(new Dimension( panel_Menu_Width, panel_Menu_Height));
        
	}
	private void toggleMenu() {
	    Timer timer = new Timer(5, null);

	    final int startWidth = panel_Menu.getPreferredSize().width;
	    final int endWidth = isMenuVisible ? 0 : panel_Menu_Width;
	    final int step = isMenuVisible ? -tocDoGian : tocDoGian;


	    timer.addActionListener(new ActionListener() {
	        int currentWidth = startWidth;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            currentWidth += step;

	            if ((step < 0 && currentWidth <= endWidth) || (step > 0 && currentWidth >= endWidth)) {
	                currentWidth = endWidth;
	                timer.stop();
	                isMenuVisible = !isMenuVisible;
	            }

	            // Cập nhật panel_Menu
	            panel_Menu.setPreferredSize(new Dimension(currentWidth, panel_Menu.getHeight()));

	            //  Tính lại kích thước panel_Center theo chiều rộng mới
	            int fullWidth = panel_Full.getWidth();
	            int fullHeight = panel_Full.getHeight();
	            int headerHeight = (int) (fullHeight * 0.15);
	            int centerWidth = fullWidth - currentWidth;
	            int centerHeight = fullHeight - headerHeight;
	            panel_Center.setPreferredSize(new Dimension(centerWidth, centerHeight));
	            if(panelCenterNho!=null) {
		    	    int centertW = panel_Center.getWidth();
		    	    lblThemKhach.setHorizontalAlignment(SwingConstants.CENTER);
		    	    lblThemKhach.setBounds(0, 0, centertW, 50);
		    	    int x = (int)(centertW*0.3);
		    	    int x1 = (int)(centertW*0.5);
		    	    int x2 = (int)(centertW*0.65);
		    	    int x3 = (int)(centertW*0.15);
		    	    
		    	    lblMaKH.setBounds(30, 50, 200, 50);
		    	    txtMaKH.setBounds(x3, 60, x, 30);
		    		lblSDT.setBounds(30, 90, 200, 50);
		    		txtSDT.setBounds(x3, 100, x, 30);
		    	    lblHoTen.setBounds(30, 130, 200, 50);
		    	    txtHoTen.setBounds(x3, 140, x, 30);
		    	    lblDiaChi.setBounds(30, 170, 200, 50);
		    	    txtDiaChi.setBounds(x3, 180, x, 30);
		    	    lblLoaiKH.setBounds(x1, 50, 200, 50);
		    	    txtLoaiKH.setBounds(x2, 60, x, 30);
		    	    lblGioiTinh.setBounds(x1, 90, 200, 50);
		    	    comboGioiTinh.setBounds(x2, 100, x, 30);
		    	    lblGhiChu.setBounds(x1, 130, 200, 50);
		    		scrollGhiChu.setBounds(x2, 140, x, 70);
		    		btnThemNV.setBounds(x, 230, x3, 35);
		    	    btnHuy.setBounds(x1, 230, x3, 35);
		    	    p2.setBounds(5, 280, centertW, 350);
	            }
	            if(panelCenterCapNhat!=null) {
	        	    int centertW = panel_Center.getWidth();
	        	    lblCapNhatKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
	        	    lblCapNhatKhachHang.setBounds(0, 0, centertW, 50);
	        	    int x = (int)(centertW*0.3);
	        	    int x1 = (int)(centertW*0.5);
	        	    int x2 = (int)(centertW*0.65);
	        	    int x3 = (int)(centertW*0.15);
	        	    lblMaKH.setBounds(30, 50, 200, 50);
	        	    txtMaKHCapNhat.setBounds(x3, 60, x, 30);
	        		lblSDT.setBounds(30, 90, 200, 50);
	        		txtSDTCapNhat.setBounds(x3, 100, x, 30);
	        	    lblHoTen.setBounds(30, 130, 200, 50);
	        	    txtHoTenCapNhat.setBounds(x3, 140, x, 30);
	        	    lblDiaChi.setBounds(30, 170, 200, 50);
	        	    txtDiaChiCapNhat.setBounds(x3, 180, x, 30);
	        	    lblLoaiKH.setBounds(x1, 50, 200, 50);
	        	    txtLoaiKHCapNhat.setBounds(x2, 60, x, 30);
	        	    lblGioiTinh.setBounds(x1, 90, 200, 50);
	        	    comboGioiTinhCapNhat.setBounds(x2, 100, x, 30);
	        	    lblGhiChu.setBounds(x1, 130, 200, 50);
	        		scrollGhiChuCapNhat.setBounds(x2, 140, x, 70);
	        		btnDSCapNhat.setBounds(x, 230, x3, 35);
	        	    btnHuyCapNhat.setBounds(x1, 230, x3, 35);
	        	    pCapNhat.setBounds(5, 280, centertW, 350);
	            }
	            
	            
	            // Cập nhật giao diện
	            panel_Full.revalidate();
	            panel_Full.repaint();
	        }
	    });

	    timer.start();
	}


	   private JButton createCustomButton(String text, String iconPath, int iconWidth, int iconHeight, 
	            int iconX, int iconY, int textX, int textY) {
	        JButton button = new JButton();
	        button.setLayout(null);
	        button.setFont(new Font("Times New Roman", Font.BOLD, 18));
	        button.setForeground(new Color(0, 82, 40));
	        button.setBackground(new Color(244, 242, 193));
	        button.setBorderPainted(false);
	        button.setFocusPainted(false);
	        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

	        // Load và resize icon
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));
	        Image img = originalIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(img);

	        // Icon
	        JLabel iconLabel = new JLabel(resizedIcon);
	        iconLabel.setBounds(iconX, iconY, iconWidth, iconHeight);
	        button.add(iconLabel);

	        // Text
	        JLabel textLabel = new JLabel(text);
	        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
	        textLabel.setForeground(new Color(0, 82, 40));
	        textLabel.setBounds(textX, textY, 200, 30);
	        button.add(textLabel);

	        // Hiệu ứng hover
	        Color normal = new Color(244, 242, 193);
	        Color hover = new Color(230, 220, 150);
	        Color click = new Color(200, 190, 120);

	        button.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                button.setBackground(hover);
	            }

	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                button.setBackground(normal);
	            }

	            public void mousePressed(java.awt.event.MouseEvent evt) {
	                button.setBackground(click);
	            }

	            public void mouseReleased(java.awt.event.MouseEvent evt) {
	                if (button.getBounds().contains(evt.getPoint()))
	                    button.setBackground(hover);
	                else
	                    button.setBackground(normal);
	            }
	        });

	        return button;
	    }
		//Hàm btn
		private void btn(JButton btn) {
			btn.setBorderPainted(false);
			btn.setFocusPainted(false);                    // Không focus được bằng bàn phím hay chuột
		    btn.setBackground(new Color(244, 242, 193));  // Màu nền xám nhạt
		}
		//Hàm vô hiệu hóa txt
		private void voHieuHoaTextField(JTextField textField) {
		    textField.setEditable(false);                      // Không cho nhập
		    textField.setFocusable(false);                     // Không focus được bằng bàn phím hay chuột
		    textField.setBackground(new Color(220, 220, 220));  // Màu nền xám nhạt
		}
		
		@Override
		public void focusGained(FocusEvent e) { //Khi component nhận focus (người dùng nhấn vào, hoặc dùng Tab chuyển đến)
			// TODO Auto-generated method stub
			if (txtTimKiem.getText().equals("Tìm kiếm khách hàng")) {
	            txtTimKiem.setText("");
	            txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
	            txtTimKiem.setForeground(Color.BLACK);
	        }
		}
		@Override
		public void focusLost(FocusEvent e) { //Khi component mất focus (người dùng click ra ngoài hoặc chuyển sang ô khác)
			// TODO Auto-generated method stub
			if (txtTimKiem.getText().isEmpty()) {
	            txtTimKiem.setText("Tìm kiếm khách hàng");
	            txtTimKiem.setFont(new Font("Times New Roman", Font.ITALIC, 18));
	            txtTimKiem.setForeground(Color.GRAY);
	        }
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			if (o.equals(btnThem)) {
				tocDoGian = 35;
			    GDThemKhachHang();
				btnThem.setBackground(Color.white);
				btnDSKH.setBackground(new Color(244, 242, 193));
				btnCapNhat.setBackground(new Color(244, 242, 193));
			}
			if(o.equals(btnDSKH)) {
				GDDanhSach();
				btnDSKH.setBackground(Color.white);
				btnThem.setBackground(new Color(244, 242, 193));
				btnCapNhat.setBackground(new Color(244, 242, 193));
			}
			if(o.equals(btnCapNhat)) {
				GDCapNhat();
				btnCapNhat.setBackground(Color.white);
				btnThem.setBackground(new Color(244, 242, 193));
				btnDSKH.setBackground(new Color(244, 242, 193));
			}
		}
}
