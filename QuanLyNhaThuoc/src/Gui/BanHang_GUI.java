package Gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import Ctrl.ConnectData;
import Dao.BanHang_DAO;
import Dao.KhachHang_DAO;
import Dao.Thuoc_DAO;
import Entity.KhachHang;
import Entity.Thuoc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BanHang_GUI extends JFrame implements FocusListener, ActionListener{
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
	private JDateChooser dateFromTimKiem;
	private JPanel panel_Header_Right;
	private JButton btnTaoHoaDon;
	private JButton btnHoTroDoiTra;
	private JButton btnDanhSachHoaDon;
	private JTextField txtTimKiem;
	private JComboBox comboTimKiem;
	private JComboBox comboboxDanhSachTimKiem;
	private DefaultTableModel model;
	private JTable tableDanhSach;
	private JPanel panelCenterLeft_THD;
	private JPanel panelCenterRight_THD;
	private JButton btnXoaTatCa;
	private JButton btnThemThuoc;
	private JTextField txtNguoiLapHoaDon;
	private JTextField txtThuoc;
	private JSpinner txtSoLuong;
	private JTable tableBanThuoc;
	private JTextField txtMaHoaDon;
	private JTextField txtNgayTao;
	private JTextField txtTenKhachHang;
	private JTextField txtSDTKhach;
	private JTextField txtDiaChiKhach;
	private JTextField txtLoaiKH;
	private JTextField txtTongTienHang;
	private JTextField txtDiemTichLuy;
	private JTextField txtKhuyenMai;
	private JTextField txtTongTienThanhToan;
	private JTextField txtUuDai;
	private JTextField txtTongTienKhachTra;
	private JTextField txtTienTraLai;
	private JButton btnApDungTichLuy;
	private JButton btnThanhToan;
	private JPanel panel_Center_THD;
	private JLabel label_ThongTinThuoc;
	private JLabel label_Thuoc;
	private JLabel label_SoLuong;
	private JPanel panel_table;
	private JButton btnTienKhachTra1;
	private JButton btnTienKhachTra2;
	private JButton btnTienKhachTra3;
	private JButton btnTienKhachTra4;
	private JButton btnTienKhachTra5;
	private JButton btnTienKhachTra6;
	private JButton btnTienKhachTra7;
	private JButton btnTienKhachTra8;
	private JPanel panelCenterRight_THD_btn;
	private JButton btnHuyThanhToan;
	private int tocDoGian = 20; // mặc định
	private BanHang_DAO banHangDao;
    private JTextField txtHoTenMoi, txtDiaChiMoi, txtSDTMoi, txtLoaiKHMoi;
    private JButton btnThem, btnHuy;
	
	public BanHang_GUI() {
		super("Quản lý nhân viên quản lý");
		BanHang();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// full man hinh
        setLocationRelativeTo(null);
        
        ConnectData.getInstance().connect();
        banHangDao = new BanHang_DAO();
        
        
        setVisible(true);
        
	}
	public static void main(String[] args) {
		new BanHang_GUI();
	}

	public void BanHang() {
		
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
        
        panel_Header_Right = new JPanel();
        panel_Header_Right.setLayout(new BoxLayout(panel_Header_Right, BoxLayout.X_AXIS));
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
        btnMenu.add(label_Menu);
        
        //------------
        btnTaoHoaDon = new JButton("Tạo Hóa Đơn");
        btnHoTroDoiTra = new JButton("Hỗ trợ đổi trả");
        btnDanhSachHoaDon = new JButton("Danh sách hóa đơn");
        
        txtTimKiem = new JTextField("Tìm kiếm hóa đơn");
        txtTimKiem.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        txtTimKiem.setForeground(Color.GRAY);
        
        comboTimKiem= new JComboBox();
        comboTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tất cả","Mã hóa đơn","Mã nhân viên","SDT khách"}));
        comboTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        comboTimKiem.setLightWeightPopupEnabled(false);
        
        comboboxDanhSachTimKiem= new JComboBox();
        comboboxDanhSachTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tất cả","Thành công","Đã hủy","Hoàn lại"}));
        comboboxDanhSachTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        comboboxDanhSachTimKiem.setLightWeightPopupEnabled(false);

        dateFromTimKiem = new JDateChooser();
        
        JPanel panelTimKiem = new JPanel();
        panelTimKiem.setLayout(new BorderLayout());
        panelTimKiem.add(txtTimKiem);
        JPanel panelCoboboxTK = new JPanel();
        panelCoboboxTK.setLayout(new BorderLayout());
        panelCoboboxTK.add(comboTimKiem);
        JPanel panelCoboboxDS = new JPanel();
        panelCoboboxDS.setLayout(new BorderLayout());
        panelCoboboxDS.add(comboboxDanhSachTimKiem);
        JPanel panelLich = new JPanel();
        panelLich.setLayout(new BorderLayout());
        panelLich.add(dateFromTimKiem);
        JButton btnLoadLai = new JButton("⟳");
		btnLoadLai.setBackground(new Color(244, 242, 193));

        // kich thuoc
        txtTimKiem.setPreferredSize(new Dimension(250, 35)); // cố định kích thước ô tìm kiếm
        panelTimKiem.setMaximumSize(new Dimension(250, 35)); // bắt buộc panel không giãn
        panelTimKiem.setPreferredSize(new Dimension(250, 35));
        comboTimKiem.setPreferredSize(new Dimension(135, 35)); // cố định kích thước ô tìm kiếm
        panelCoboboxTK.setMaximumSize(new Dimension(135, 35)); // bắt buộc panel không giãn
        panelCoboboxTK.setPreferredSize(new Dimension(135, 35));
        comboboxDanhSachTimKiem.setPreferredSize(new Dimension(130, 35)); // cố định kích thước ô tìm kiếm
        panelCoboboxDS.setMaximumSize(new Dimension(130, 35)); // bắt buộc panel không giãn
        panelCoboboxDS.setPreferredSize(new Dimension(130, 35));
        dateFromTimKiem.setPreferredSize(new Dimension(130, 35)); // cố định kích thước ô tìm kiếm
        panelLich.setMaximumSize(new Dimension(130, 35)); // bắt buộc panel không giãn
        panelLich.setPreferredSize(new Dimension(130, 35));
        //add
        panel_Header_Right.add(btnTaoHoaDon);
        panel_Header_Right.add(Box.createHorizontalStrut(10)); // khoảng cách 20px bên phải
        panel_Header_Right.add(btnHoTroDoiTra);
        panel_Header_Right.add(Box.createHorizontalStrut(10)); // khoảng cách 20px bên phải
        panel_Header_Right.add(btnDanhSachHoaDon);
        panel_Header_Right.add(Box.createHorizontalStrut(5));
        panel_Header_Right.add(panelCoboboxDS);
        panel_Header_Right.add(Box.createHorizontalStrut(70));
        panel_Header_Right.add(panelLich);
        panel_Header_Right.add(Box.createHorizontalStrut(5)); 
        panel_Header_Right.add(panelCoboboxTK);
        panel_Header_Right.add(Box.createHorizontalStrut(5));
        panel_Header_Right.add(panelTimKiem);
        panel_Header_Right.add(Box.createHorizontalStrut(5));
		panel_Header_Right.add(btnLoadLai);
        
        //Cỡ chữ, màu
        btnTaoHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnHoTroDoiTra.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnDanhSachHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnTaoHoaDon.setBackground(new Color(244, 242, 193));
        btnHoTroDoiTra.setBackground(new Color(244, 242, 193));
        btnDanhSachHoaDon.setBackground(new Color(244, 242, 193));
        //tăt
        btnTaoHoaDon.setBorderPainted(false); // tắt viền
        btnTaoHoaDon.setFocusPainted(false);
        btnHoTroDoiTra.setBorderPainted(false); // tắt viền
        btnHoTroDoiTra.setFocusPainted(false);
        btnDanhSachHoaDon.setBorderPainted(false); // tắt viền
        btnDanhSachHoaDon.setFocusPainted(false);
        btnLoadLai.setBorderPainted(false);
        btnLoadLai.setFocusPainted(false);
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
		panel_Center.setBackground(new Color(255, 255, 255));
		panel_Full.add(panel_Center, BorderLayout.CENTER);
		
		
		JPanel panelCenter_DSHD_TieuDe = new JPanel();
		panelCenter_DSHD_TieuDe.setBackground(Color.white);
		JPanel panelCenter_DSHD_Bang = new JPanel(new BorderLayout());
		panelCenter_DSHD_Bang.setBackground(Color.BLUE);
		
		JLabel label_DSHD = new JLabel("DANH SÁCH HÓA ĐƠN");
		
		String[] columnBangDanhSach = {"STT", "Mã hóa đơn","Ngày tạo", "Thời gian tạo","Người lập hóa đơn","Tổng tiền"};
		Object[][] dataDanhSach = {};

	    // Tạo model không cho chỉnh sửa
        model = new DefaultTableModel(dataDanhSach, columnBangDanhSach) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa ô nào cả
            }
        };
        

        tableDanhSach = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableDanhSach);

		label_DSHD.setFont(new Font("Times New Roman", Font.BOLD, 25));
	     // Tùy chỉnh màu và font cho thanh tiêu đề (header)
        tableDanhSach.getTableHeader().setBackground(new Color(0, 60, 42)); // Màu nền thanh tiêu đề (ví dụ: xanh dương)
        tableDanhSach.getTableHeader().setForeground(Color.WHITE); // Màu chữ thanh tiêu đề
        tableDanhSach.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20)); // Đặt font chữ cho thanh tiêu đề
        tableDanhSach.setFont(new Font("Times New Roman", Font.BOLD, 16));
        tableDanhSach.setBackground(new Color(255, 255, 255)); 
		
		panel_Center.add(panelCenter_DSHD_TieuDe,BorderLayout.NORTH);
		panelCenter_DSHD_TieuDe.add(label_DSHD);
		panel_Center.add(panelCenter_DSHD_Bang,BorderLayout.CENTER);
		panelCenter_DSHD_Bang.add(scrollPane);
	    
		

        // Gọi hàm resize sau khi frame hiển thị
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePanel(); // tự động resize khi thay đổi kích thước
            }
        });
        
        
        // add sự kiện
        btnMenu.addActionListener(this);
        txtTimKiem.addFocusListener(this);
        btnTaoHoaDon.addActionListener(this);
        btnDanhSachHoaDon.addActionListener(this);
        
       
	}
	
	public void taoHoaDon() {
	    // Xóa và cập nhật panel chính
	    panel_Center.removeAll();
	    panel_Center.revalidate();
	    panel_Center.repaint();

	    panel_Center_THD = new JPanel();
	    panel_Center_THD.setLayout(null);
	    panel_Center.add(panel_Center_THD);
	    panel_Center_THD.setBackground(Color.white);
	    
	    //======== Tạo panel bên trái
	    panelCenterLeft_THD = new JPanel(null);
        int centerH = panel_Center.getHeight();
	    int centerWidth = panel_Center.getWidth();
	    int centerLeftWidth = (int)(centerWidth * 0.65);
	    panelCenterLeft_THD.setBackground(Color.white);
	    panelCenterLeft_THD.setBounds(0,0,centerLeftWidth,centerH);
	    panel_Center_THD.add(panelCenterLeft_THD);
	    
	    ///-------------bên trái
	    label_ThongTinThuoc = new JLabel("Thông tin thuốc");
	    label_Thuoc = new JLabel("Thuốc");
	    label_SoLuong = new JLabel("Số lượng");
	    txtThuoc = new JTextField();
	    btnXoaTatCa = new JButton("Xóa tất cả");
	    btnThemThuoc = new JButton("Thêm");
	    // (giá trị ban đầu, min, max, bước nhảy)
	    SpinnerNumberModel soLuong = new SpinnerNumberModel(1, 1, 150000, 1); 
	    txtSoLuong = new JSpinner(soLuong);
	    /////Bang
	    // 1. Khởi tạo bảng
	    DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{
	        "Mã", "Tên", "Số lượng", "Số lô", "Đơn vị", "Đơn giá", "Thành tiền", "Xóa"
	    });
	    tableBanThuoc = new JTable(model);
	    // 2. Gắn renderer cho nút
	    tableBanThuoc.getColumnModel().getColumn(7).setCellRenderer(new TableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            return new JButton("Xóa");
	        }
	    });
	    // 3. Gắn xóa cho nút
	    tableBanThuoc.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
	        private final JButton button = new JButton("Xóa");
	        private int currentRow = -1;
	        
	        {
	            button.addActionListener(e -> {
	                if (currentRow >= 0 && currentRow < model.getRowCount()) {
	                    model.removeRow(currentRow);
	                    fireEditingStopped(); // Dừng trạng thái chỉnh sửa
	                }
	            });
	        }

	        @Override
	        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	            currentRow = row;
	            return button;
	        }

	        @Override
	        public Object getCellEditorValue() {
	            return "Xóa";
	        }
	    });
	    // Thiết lập chiều cao dòng
		tableBanThuoc.setRowHeight(30); // Thay đổi chiều cao của các hàng
		tableBanThuoc.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
	        private final JButton button = new JButton("Xóa");
	        private int currentRow = -1;

	        {
	            button.addActionListener(e -> {
	                int rowToRemove = currentRow;
	                fireEditingStopped(); // Dừng chỉnh sửa trước

	                // Đợi kết thúc chỉnh sửa mới xóa dòng
	                SwingUtilities.invokeLater(() -> {
	                    if (rowToRemove >= 0 && rowToRemove < model.getRowCount()) {
	                        model.removeRow(rowToRemove);

	                        // Cập nhật lại STT sau khi xóa
	                        for (int i = 0; i < model.getRowCount(); i++) {
	                            model.setValueAt(i + 1, i, 0); // Cột 0 là STT
	                        }

	                        // Cập nhật tổng tiền
//	                        capNhatTongTien();
	                    }
	                });
	            });
	        }

	        @Override
	        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	            currentRow = row;
	            return button;
	        }

	        @Override
	        public Object getCellEditorValue() {
	            return "Xóa";
	        }
	    });
	    
		// Thêm bảng vào JScrollPane
        JScrollPane scrollPaneTTTB = new JScrollPane(tableBanThuoc);
        // Tùy chỉnh màu và font cho thanh tiêu đề (header)
        tableBanThuoc.getTableHeader().setBackground(new Color(0, 60, 42)); // Màu nền thanh tiêu đề (ví dụ: xanh dương)
        tableBanThuoc.getTableHeader().setForeground(Color.WHITE); // Màu chữ thanh tiêu đề
        tableBanThuoc.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20)); // Đặt font chữ cho thanh tiêu đề
        
        // Tạo màu nền cho bảng
        tableBanThuoc.setBackground(new Color(255, 255, 255)); // Màu nền của bảng (ví dụ: trắng)
        tableBanThuoc.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
	    // add 
	    panelCenterLeft_THD.add(label_ThongTinThuoc);
	    panelCenterLeft_THD.add(label_Thuoc);
	    panelCenterLeft_THD.add(txtThuoc);
	    panelCenterLeft_THD.add(label_SoLuong);
	    panelCenterLeft_THD.add(txtSoLuong);
	    panelCenterLeft_THD.add(btnThemThuoc);
	    panelCenterLeft_THD.add(btnXoaTatCa);
	    
	    // Vị trí ban đầu
	    int centerLeftW = panelCenterLeft_THD.getWidth();
	    label_ThongTinThuoc.setHorizontalAlignment(SwingConstants.CENTER);
	    label_ThongTinThuoc.setBounds(0, 0, centerLeftW, 50);
	    label_Thuoc.setBounds(20, 45, 100, 50);
	    txtThuoc.setBounds(150, 50, 450, 30);
	    label_SoLuong.setBounds(20, 90, 100, 50);
	    txtSoLuong.setBounds(150, 90, 100, 30);
	    btnThemThuoc.setBounds(300, 90, 100, 30);
	    btnXoaTatCa.setBounds(450, 90, 150, 30);
	    
	    //Panel table
	    panel_table = new JPanel(new BorderLayout());
	    panelCenterLeft_THD.add(panel_table);
	    panel_table.setBounds(10, 140, centerLeftWidth-20, 540);
	    panel_table.add(scrollPaneTTTB,BorderLayout.CENTER);
	    
	    //======= Tạo panel bên phải
	    panelCenterRight_THD = new JPanel(null);
	    panelCenterRight_THD.setBounds(centerLeftWidth,0,centerWidth-centerLeftWidth,centerH);
	    panelCenterRight_THD.setBackground(Color.white);
	    panel_Center_THD.add(panelCenterRight_THD);
	    
	    //==bên phải
	    JLabel label_ThongTinHoaDon = new JLabel("Thông tin hóa đơn");
        JLabel label_MHD = new JLabel("Mã hóa đơn:");
        JLabel label_NgayTao = new JLabel("Ngày tạo:");
        txtMaHoaDon = new JTextField();
        txtNgayTao = new JTextField();
        JLabel label_ThongTinKhachHang = new JLabel("Thông tin khách hàng:");
        JLabel label_SDTKhachhang = new JLabel("Số điện thoại:");
        JLabel label_TenKhachHang = new JLabel("Tên khách hàng:");
        JLabel label_DiaChi = new JLabel("Địa chỉ:");
        JLabel label_Loai = new JLabel("Loại:");
        txtSDTKhach = new JTextField();
        txtTenKhachHang = new JTextField();
        txtDiaChiKhach = new JTextField();
        txtLoaiKH = new JTextField();
        JLabel label_TongTienHang = new JLabel("Tổng tiền hàng:");
        JLabel label_DiemTichLuy = new JLabel("Điểm tích lũy:");
        JLabel label_KhuyenMai = new JLabel("Khuyến mãi:");
        JLabel label_TongTienThanhToan = new JLabel("Tổng tiền thanh toán:");
        JLabel label_TongTienKhachTra = new JLabel("Tổng tiền khách trả:");
        JLabel label_TienTraLai = new JLabel("Tiền trả lại:");
        txtTongTienHang = new JTextField();
        txtDiemTichLuy = new JTextField();
        txtKhuyenMai = new JTextField();
        txtUuDai = new JTextField();
        txtTongTienThanhToan = new JTextField();
        txtTongTienKhachTra = new JTextField();
        txtTienTraLai = new JTextField();
        btnApDungTichLuy = new JButton("Áp dụng");
        btnTienKhachTra1 = new JButton("2000000");
        btnTienKhachTra2 = new JButton("2000000");
        btnTienKhachTra3 = new JButton("2000000");
        btnTienKhachTra4 = new JButton("2000000");
        btnTienKhachTra5 = new JButton("2000000");
        btnTienKhachTra6 = new JButton("2000000");
        btnTienKhachTra7 = new JButton("2000000");
        btnTienKhachTra8 = new JButton("2000000");
        btnThanhToan = new JButton("Thanh toán");
        btnHuyThanhToan = new JButton("Hủy");
        
        panelCenterRight_THD_btn = new JPanel();
        panelCenterRight_THD.add(panelCenterRight_THD_btn);
        panelCenterRight_THD_btn.setBounds(0, 490, centerWidth-centerLeftW,90 );
        
	    //add
	    panelCenterRight_THD.add(label_ThongTinHoaDon);
	    panelCenterRight_THD.add(label_MHD);
	    panelCenterRight_THD.add(txtMaHoaDon);
	    panelCenterRight_THD.add(label_NgayTao);
	    panelCenterRight_THD.add(txtNgayTao);
	    panelCenterRight_THD.add(label_ThongTinKhachHang);
	    panelCenterRight_THD.add(label_SDTKhachhang);
	    panelCenterRight_THD.add(txtSDTKhach);
	    panelCenterRight_THD.add(label_TenKhachHang);
	    panelCenterRight_THD.add(txtTenKhachHang);
	    panelCenterRight_THD.add(label_DiaChi);
	    panelCenterRight_THD.add(txtDiaChiKhach);
	    panelCenterRight_THD.add(label_Loai);
	    panelCenterRight_THD.add(txtLoaiKH);
	    panelCenterRight_THD.add(label_TongTienHang);
	    panelCenterRight_THD.add(txtTongTienHang);
	    panelCenterRight_THD.add(label_DiemTichLuy);
	    panelCenterRight_THD.add(txtDiemTichLuy);
	    panelCenterRight_THD.add(label_KhuyenMai);
	    panelCenterRight_THD.add(txtKhuyenMai);
	    panelCenterRight_THD.add(txtUuDai);
	    panelCenterRight_THD.add(btnApDungTichLuy);
	    panelCenterRight_THD.add(label_TongTienThanhToan);
	    panelCenterRight_THD.add(txtTongTienThanhToan);
	    panelCenterRight_THD.add(label_TongTienKhachTra);
	    panelCenterRight_THD.add(txtTongTienKhachTra);
	    panelCenterRight_THD_btn.add(btnTienKhachTra1);
	    panelCenterRight_THD_btn.add(btnTienKhachTra2);
	    panelCenterRight_THD_btn.add(btnTienKhachTra3);
	    panelCenterRight_THD_btn.add(btnTienKhachTra4);
	    panelCenterRight_THD_btn.add(btnTienKhachTra5);
	    panelCenterRight_THD_btn.add(btnTienKhachTra6);
	    panelCenterRight_THD_btn.add(btnTienKhachTra7);
	    panelCenterRight_THD_btn.add(btnTienKhachTra8);
	    panelCenterRight_THD.add(label_TienTraLai);
	    panelCenterRight_THD.add(txtTienTraLai);
	    panelCenterRight_THD.add(btnThanhToan);
	    panelCenterRight_THD.add(btnHuyThanhToan);
	    
	    //vị trí
	    int centerRightW = (int) centerWidth-centerLeftW;
	    label_ThongTinHoaDon.setBounds(0, 0, 200, 50);
        label_MHD.setBounds(10, 35, 200, 50);
        txtMaHoaDon.setBounds(150, 40,centerRightW-190 , 30);
        label_NgayTao.setBounds(10, 70, 200, 50);
        txtNgayTao.setBounds(150, 73,centerRightW-190 , 30);
        label_ThongTinKhachHang.setBounds(0, 100, 300, 50);
        label_SDTKhachhang.setBounds(10, 135, 200, 50);
        txtSDTKhach.setBounds(160, 140, centerRightW-200, 30);
        label_TenKhachHang.setBounds(10, 165, 200, 50);
        txtTenKhachHang.setBounds(160, 173, centerRightW-200, 30);
        label_DiaChi.setBounds(10, 195, 200, 50);
        txtDiaChiKhach.setBounds(160, 206, centerRightW-200, 30);
        label_Loai.setBounds(10, 225, 200, 50);
        txtLoaiKH.setBounds(160, 239, centerRightW-200, 30);
        label_TongTienHang.setBounds(5, 275, 200, 50);
        txtTongTienHang.setBounds(160, 280, centerRightW-300, 30);
        label_DiemTichLuy.setBounds(5, 305, 200, 50);
        txtDiemTichLuy.setBounds(160, 313, centerRightW-300, 30);
        label_KhuyenMai.setBounds(5, 335, 200, 50);
        txtKhuyenMai.setBounds(160, 346, centerRightW-300, 30);
        txtUuDai.setBounds(160, 379, centerRightW-300, 25);
        btnApDungTichLuy.setBounds(313, 313, 95, 30);
        label_TongTienThanhToan.setBounds(5, 410, 200, 50);
        txtTongTienThanhToan.setBounds(200, 420, centerRightW-243, 25);
        label_TongTienKhachTra.setBounds(5, 445, 200, 50);
        txtTongTienKhachTra.setBounds(200, 450, centerRightW-243, 25);
        label_TienTraLai.setBounds(10, 575, 200, 50);
        txtTienTraLai.setBounds(160, 585, centerRightW-200, 25);
        btnThanhToan.setBounds(centerRightW-400, 630, 200, 30);
        btnHuyThanhToan.setBounds(centerRightW-150, 630, 100, 30);
        
	    // font chữ
	    Font fn_25 = new Font("Times New Roman", Font.BOLD, 25);
	    label_ThongTinThuoc.setFont(fn_25);
	    label_ThongTinHoaDon.setFont(fn_25);
	    label_ThongTinKhachHang.setFont(fn_25);
	    Font fn_20 = new Font("Times New Roman", Font.BOLD, 20);
	    Font fn_18 = new Font("Times New Roman", Font.BOLD, 18);
	    Font fn_16 = new Font("Times New Roman", Font.BOLD, 16);
	    label_Thuoc.setFont(fn_20);
	    label_SoLuong.setFont(fn_20);
	    txtThuoc.setFont(fn_20);
	    txtSoLuong.setFont(fn_20);
	    btnThemThuoc.setFont(fn_18);
	    btnXoaTatCa.setFont(fn_18);
	    label_MHD.setFont(fn_20);
	    txtMaHoaDon.setFont(fn_20);
	    label_NgayTao.setFont(fn_20);
	    txtNgayTao.setFont(fn_20);
	    label_SDTKhachhang.setFont(fn_20);
	    txtSDTKhach.setFont(fn_20);
	    label_DiaChi.setFont(fn_20);
	    txtDiaChiKhach.setFont(fn_20);
	    label_Loai.setFont(fn_20);
	    txtLoaiKH.setFont(fn_20);
	    label_TenKhachHang.setFont(fn_20);
	    txtTenKhachHang.setFont(fn_20);
	    label_TongTienHang.setFont(fn_20);
	    txtTongTienHang.setFont(fn_20);
	    label_DiemTichLuy.setFont(fn_20);
	    txtDiemTichLuy.setFont(fn_20);
	    label_KhuyenMai.setFont(fn_20);
	    txtKhuyenMai.setFont(fn_20);
	    txtUuDai.setFont(fn_18);
	    label_TongTienThanhToan.setFont(fn_20);
	    txtTongTienThanhToan.setFont(fn_20);
	    label_TongTienKhachTra.setFont(fn_20);
	    txtTongTienKhachTra.setFont(fn_20);
	    btnApDungTichLuy.setFont(fn_16);
	    btnTienKhachTra1.setFont(fn_16);
	    btnTienKhachTra2.setFont(fn_16);
	    btnTienKhachTra3.setFont(fn_16);
	    btnTienKhachTra4.setFont(fn_16);
	    btnTienKhachTra5.setFont(fn_16);
	    btnTienKhachTra6.setFont(fn_16);
	    btnTienKhachTra7.setFont(fn_16);
	    btnTienKhachTra8.setFont(fn_16);
	    label_TienTraLai.setFont(fn_20);
	    txtTienTraLai.setFont(fn_20);
	    btnThanhToan.setFont(fn_20);
	    btnHuyThanhToan.setFont(fn_20);
	    
	    voHieuHoaTextField(txtLoaiKH);
	    voHieuHoaTextField(txtMaHoaDon);
	    voHieuHoaTextField(txtNgayTao);
	    voHieuHoaTextField(txtTongTienHang);


	    //add sự kiệ
	    txtSDTKhach.addActionListener(this);
	    txtTenKhachHang.addActionListener(this);
	    txtDiaChiKhach.addActionListener(this);
	    btnThemThuoc.addActionListener(this);
	    //gọi hàm
        khoiTaoThongTinHoaDon();

	}
	////////=====================Mở đầu Chức năng xử lý=====================
	//---Khởi tạo thông tin hóa đơn
	private void khoiTaoThongTinHoaDon() {
		txtMaHoaDon.setText(phatSinhMaHoaDon());
		//Ngày tạo
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatNgayTao = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		txtNgayTao.setText(today.format(formatNgayTao));
		//Thông tin khách hàng
		txtSDTKhach.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        xuLySDTKhachHang();
		    }
		});
		//Thông tin thuốc
		txtThuoc.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        // Kiểm tra nếu người dùng nhấn Enter (KeyCode 10)
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            // Kiểm tra mã thuốc khi nhấn Enter
		            kiemTraThuoc();
		        }
		    }
		});
	}
	//---Ca làm
	public static int laySoCaHienTai() {
	    int gio = LocalDateTime.now().getHour();
	    if (gio >= 7 && gio < 15)
	        return 1; // Ca sáng
	    if  (gio >= 15 && gio < 22)
	        return 2;
	    return 0;
	    // Ca 1 : bắt đầu từ 7h - 15h.
	    // Ca 2 : bắt đầu 15h - 22h.
	}
	//---Tự phát sinh mã hóa đơn
	public static String phatSinhMaHoaDon() {
	    LocalDate today = LocalDate.now();// lấy ngày hiện tại
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");// định dạng theo mẫu
	    String ngay = today.format(formatter);//Định dạng ngày theo mẫu và lưu vào biến ngay

	    int soCa = laySoCaHienTai();                     // goi số ca hiện tại
	    int soThuTu = BanHang_DAO.laySoThuTuHoaDonTrongNgay();       // lấy số thứ tự hớ đơn trong ngày, trả về số thứu tự

	    String maCa = String.format("%02d", soCa);//Đảm bảo số ca (soCa) luôn có 2 chữ số
	    String thuTu = String.format("%03d", soThuTu);//Đảm bảo số thứ tự (soThuTu) luôn có 3 chữ số

	    return "HD" + ngay + maCa + thuTu;
	}
	//---Số điện thoại khách hàng
	private void xuLySDTKhachHang() {
		String sdtKH = txtSDTKhach.getText().trim();
		// nếu bỏ trống
	    if (sdtKH.isEmpty()) { 
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại khách hàng.");
	        txtSDTKhach.requestFocus();
	        return;
	    }
	    // kiểm tra định dạng 
        if (!sdtKH.matches("^0[3|5|7|8|9]\\d{8}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng 0 và có đúng 10 chữ số.");
            txtSDTKhach.requestFocus();
            return;
        }
        // kiểm tra khách hàng
        timVaDienThongTinKhachHang(sdtKH);
	}
	//---Tìm khách hàng dựa trên số điện thoại
	private void timVaDienThongTinKhachHang(String sdt) {
	    KhachHang khachHang = KhachHang_DAO.timKhachTheoSDT(sdt);
	    
	    if (khachHang != null) {
	        txtTenKhachHang.setText(khachHang.getHoTenKH());
	        txtDiaChiKhach.setText(khachHang.getDiaChi());
	        txtLoaiKH.setText(khachHang.getLoaiKH().getTenLoaiKH());
	    } else {
	        JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại! (Không là thành viên)");
	        // Hiển thị hộp thoại xác nhận
	        int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm khách hàng mới?", "Thêm khách hàng", JOptionPane.YES_NO_OPTION);
	        if (option == JOptionPane.YES_OPTION) {
	        	dialogThemKhachHang();
	        }
	        txtTenKhachHang.setText("");
	        txtDiaChiKhach.setText("");
	        txtLoaiKH.setText("");
	    }
	}
	// thuốc
	private boolean kiemTraThuoc() {
	    String mathuoc = txtThuoc.getText().trim();
	    
	    if (mathuoc.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Vui lòng nhập mã thuốc.");
	        txtThuoc.requestFocus();
	        return false;
	    }

	    if (!mathuoc.matches("^[A-Z]{4}[0-9]{4}$")) {
	        JOptionPane.showMessageDialog(null, "Mã thuốc không đúng định dạng! Định dạng: 4 chữ cái in hoa + 4 số (ví dụ: MTKS0001)");
	        txtThuoc.setText("");
	        txtThuoc.requestFocus();
	        return false;
	    }

	    if (!Thuoc_DAO.kiemTraThuocTonTai(mathuoc)) {
	        JOptionPane.showMessageDialog(null, "Mã thuốc không tồn tại!");
	        txtThuoc.setText("");
	        txtThuoc.requestFocus();
	        return false;
	    }
	    
	    // Nếu hợp lệ
	    txtSoLuong.requestFocus();
	    return true;
	}
	//Thêm thuốc vào bảng
	private void themThuocVaoBang() {
	    if (!kiemTraThuoc()) return; // Dừng nếu không hợp lệ

	    String maThuoc = txtThuoc.getText().trim();
	    
	    // Ép JSpinner commit nếu người dùng gõ tay
	    try {
	        txtSoLuong.commitEdit();
	    } catch (ParseException ex) {
	        JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
	        return;
	    }

	    int soLuong = (Integer) txtSoLuong.getValue();
	    if (soLuong <= 0) {
	        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!");
	        return;
	    }

	    int soLuongTon = Thuoc_DAO.laySoLuongTon(maThuoc);
	    if (soLuongTon < soLuong) {
	        JOptionPane.showMessageDialog(null, "Số lượng tồn chỉ còn " + soLuongTon + "!");
	        return;
	    }

	    Thuoc thuoc = Thuoc_DAO.timThuocTheoMa(maThuoc);
	    if (thuoc == null) {
	        JOptionPane.showMessageDialog(null, "Không thể lấy thông tin thuốc!");
	        return;
	    }

	    double thanhTien = thuoc.getGiaBan() * soLuong;

	    DefaultTableModel model = (DefaultTableModel) tableBanThuoc.getModel();
	    model.addRow(new Object[] {
	        model.getRowCount() + 1,
	        thuoc.getTenThuoc(),
	        soLuong,
	        thuoc.getSoLo(),
	        thuoc.getDonVi().getTenDonVi(),
	        thuoc.getGiaBan(),
	        thanhTien,
	        "Xóa"
	    });

	    txtThuoc.setText("");
	    txtSoLuong.setValue(1);
	}

	


	///////======================Kết thúc Chức năng xử lý===================
	
	//Hàm vô hiệu hóa txt
	private void voHieuHoaTextField(JTextField textField) {
	    textField.setEditable(false);                      // Không cho nhập
	    textField.setFocusable(false);                     // Không focus được bằng bàn phím hay chuột
	    textField.setBackground(new Color(220, 220, 220));  // Màu nền xám nhạt
	}
	
	//Thêm khách hàng
	private void dialogThemKhachHang() {
	    JDialog dialog = new JDialog(this, "Thêm khách hàng mới", true);
	    dialog.setSize(500, 500);
	    dialog.setLayout(null);
	    dialog.setLocationRelativeTo(this);
	    
	    JLabel lblThemKhachHang = new JLabel("THÊM KHÁCH HÀNG MỚI");
	    JLabel lblHoTen = new JLabel("Họ tên:");
	    JLabel lblDiaChi = new JLabel("Địa chỉ:");
	    JLabel lblSDT = new JLabel("SĐT:");
	    JLabel lblLoaiKH = new JLabel("Loại KH:");

	    txtHoTenMoi = new JTextField();
	    txtDiaChiMoi = new JTextField();
	    txtSDTMoi = new JTextField();
	    txtLoaiKHMoi = new JTextField();

	    JButton btnThem = new JButton("Thêm");
	    JButton btnHuy = new JButton("Hủy");
	    
	    lblThemKhachHang.setBounds(0, 5, 80, 25);
	    lblHoTen.setBounds(30, 20, 80, 25);
	    txtHoTenMoi.setBounds(120, 20, 230, 25);
	    lblDiaChi.setBounds(30, 60, 80, 25);
	    txtDiaChiMoi.setBounds(120, 60, 230, 25);
	    lblSDT.setBounds(30, 100, 80, 25);
	    txtSDTMoi.setBounds(120, 100, 230, 25);
	    lblLoaiKH.setBounds(30, 140, 80, 25);
	    txtLoaiKHMoi.setBounds(120, 140, 230, 25);

	    btnThem.setBounds(120, 200, 100, 30);
	    btnHuy.setBounds(250, 200, 100, 30);

	    Font fn_25 = new Font("Times New Roman", Font.BOLD, 25);
	    Font fn_20 = new Font("Times New Roman", Font.BOLD, 20);
	    lblThemKhachHang.setFont(fn_25);
	    
	    dialog.add(lblHoTen); dialog.add(txtHoTenMoi);
	    dialog.add(lblDiaChi); dialog.add(txtDiaChiMoi);
	    dialog.add(lblSDT); dialog.add(txtSDTMoi);
	    dialog.add(lblLoaiKH); dialog.add(txtLoaiKHMoi);
	    dialog.add(btnThem); dialog.add(btnHuy);

	    btnThem.addActionListener(e -> {
	        String hoTen = txtHoTenMoi.getText().trim();
	        String diaChi = txtDiaChiMoi.getText().trim();
	        String sdt = txtSDTMoi.getText().trim();
	        String loaiKH = txtLoaiKHMoi.getText().trim();

	        if (hoTen.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() || loaiKH.isEmpty()) {
	            JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!");
	            return;
	        }
	        if (!sdt.matches("^0\\d{9}$")) {
	            JOptionPane.showMessageDialog(dialog, "SĐT phải bắt đầu bằng 0 và có đúng 10 chữ số.");
	            return;
	        }

	        // TODO: Thêm khách hàng vào CSDL
	        // KhachHang kh = new KhachHang(sdt, hoTen, diaChi, new LoaiKhachHang(maLoai, loaiKH));
	        // khachHang_dao.themKhachHang(kh);

	        JOptionPane.showMessageDialog(dialog, "Thêm khách hàng thành công!");
	        dialog.dispose();
	    });

	    btnHuy.addActionListener(e -> dialog.dispose());

	    dialog.setVisible(true);
	}

	
	//Hàm thêm khách hàng
	
	
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
//        panel_Center.setPreferredSize(new Dimension( panel_Menu_Width, panel_Menu_Height));
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

	            // Cập nhật lại kích thước panel_Center_Left (nếu muốn giãn động theo %)
	            int centerLeftWidth = (int)(centerWidth * 0.65);
	            if (panelCenterLeft_THD != null) {
	                panelCenterLeft_THD.setBounds(0, 0, centerLeftWidth, panel_Center.getHeight());
	        	    label_ThongTinThuoc.setHorizontalAlignment(SwingConstants.CENTER);
	        	    label_ThongTinThuoc.setBounds(0, 0, panelCenterLeft_THD.getWidth(), 50);
	            }
	            if(panel_table!=null) {
	            	panel_table.setBounds(10, 140, centerLeftWidth-20, 540);
	            }
	            if(panelCenterRight_THD!=null) {
	            	int centerRightW = centerWidth-centerLeftWidth;
	            	panelCenterRight_THD.setBounds(centerLeftWidth,0,centerWidth-centerLeftWidth,panel_Center.getHeight());
		            txtMaHoaDon.setBounds(150, 40,centerRightW-200 , 30);
		            txtNgayTao.setBounds(150, 73,centerRightW-200 , 30);
		            txtSDTKhach.setBounds(160, 140, centerRightW-200, 30);
		            txtTenKhachHang.setBounds(160, 173, centerRightW-200, 30);
		            txtDiaChiKhach.setBounds(160, 206, centerRightW-200, 30);
		            txtLoaiKH.setBounds(160, 239, centerRightW-200, 30);
		            txtTongTienHang.setBounds(160, 280,centerRightW-300, 30);
		            txtDiemTichLuy.setBounds(160, 313, centerRightW-300, 30);
		            btnApDungTichLuy.setBounds(centerRightW-135, 313, 95, 30);
		            txtKhuyenMai.setBounds(160, 346, centerRightW-300, 30);
		            txtUuDai.setBounds(160, 379, centerRightW-300, 25);
		            txtTongTienThanhToan.setBounds(200, 420, centerRightW-243, 25);
		            txtTongTienKhachTra.setBounds(200, 450, centerRightW-243, 25);
		            panelCenterRight_THD_btn.setBounds(0, 490, centerRightW,90 );
		            txtTienTraLai.setBounds(160, 585, centerRightW-200, 25);
		            btnThanhToan.setBounds(centerRightW-400, 630, 200, 30);
		            btnHuyThanhToan.setBounds(centerRightW-150, 630, 100, 30);
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

	@Override
	public void focusGained(FocusEvent e) { //Khi component nhận focus (người dùng nhấn vào, hoặc dùng Tab chuyển đến)
		// TODO Auto-generated method stub
		if (txtTimKiem.getText().equals("Tìm kiếm hóa đơn")) {
            txtTimKiem.setText("");
            txtTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
            txtTimKiem.setForeground(Color.BLACK);
        }
	}
	@Override
	public void focusLost(FocusEvent e) { //Khi component mất focus (người dùng click ra ngoài hoặc chuyển sang ô khác)
		// TODO Auto-generated method stub
		if (txtTimKiem.getText().isEmpty()) {
            txtTimKiem.setText("Tìm kiếm hóa đơn");
            txtTimKiem.setFont(new Font("Times New Roman", Font.ITALIC, 18));
            txtTimKiem.setForeground(Color.GRAY);
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
	    if(o.equals(btnMenu)) {
	    	toggleMenu();
	    }
	    if (o.equals(btnTaoHoaDon)) {
	    	tocDoGian = 50; 
	        taoHoaDon();
	    }
	    if(o.equals(btnDanhSachHoaDon)) {
	    	tocDoGian = 10; // mở chậm hơn
	    }
	    if(o.equals(txtSDTKhach)) {
	    	txtTenKhachHang.requestFocus();
	    }
	    if(o.equals(txtTenKhachHang)) {
	    	txtDiaChiKhach.requestFocus();
	    }
	    if(o.equals(btnThemThuoc)) {
	    	themThuocVaoBang();
	    }
	}

}

