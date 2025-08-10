package Gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class demo1 extends JFrame{
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

	
	public demo1() {
		super("Quản lý nhân viên quản lý");
        demo();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// full man hinh
        setLocationRelativeTo(null);
        setVisible(true);
        
	}
	public static void main(String[] args) {
		new demo1();
	}

	public void demo() {
		
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
		
		// Dữ liệu mẫu cho bảng
		String[] columnNames = {"Mã thuốc", "Tên thuốc", "Đơn vị", "Giá"};
		Object[][] data = {
		    {"T001", "Paracetamol", "Viên", "5,000đ"},
		    {"T002", "Amoxicillin", "Viên", "7,000đ"},
		    {"T003", "Vitamin C", "Viên", "3,000đ"}
		};

		// Tạo JTable và JScrollPane
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		

		
		// Label + JDateChooser từ ngày
	    JLabel lbFrom = new JLabel("Từ ngày:");
	    
	    

	    dateFrom = new JDateChooser();
	    dateFrom.setBounds(80, 10, 120, 25);
	    panel_Center.add(dateFrom, BorderLayout.NORTH);
		
	    panel_Center.add(scrollPane, BorderLayout.CENTER);

        // Gọi hàm resize sau khi frame hiển thị
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePanel(); // tự động resize khi thay đổi kích thước
            }
        });
	}
	
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
	    Timer timer = new Timer(10, null);
	    final int[] width = {panel_Menu.getPreferredSize().width};
	    int endWidth = isMenuVisible ? 0 : panel_Menu_Width;
	    int step = isMenuVisible ? -10 : 10;

	    timer.addActionListener(e -> {
	        width[0] += step;
	        if ((step < 0 && width[0] <= endWidth) || (step > 0 && width[0] >= endWidth)) {
	            width[0] = endWidth;
	            timer.stop();
	            isMenuVisible = !isMenuVisible;
	        }
	        panel_Menu.setPreferredSize(new Dimension(width[0], panel_Menu.getHeight()));
	        panel_Menu.revalidate();
	        panel_Full.revalidate();
	        panel_Full.repaint();
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


}
