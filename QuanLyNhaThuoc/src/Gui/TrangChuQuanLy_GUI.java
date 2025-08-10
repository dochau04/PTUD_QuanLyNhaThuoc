package Gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TrangChuQuanLy_GUI extends JFrame{
	private JPanel panel_Full, panel_Header, panel_Header_1,panel_Menu,panel_Center,panel_Center_Left, panel_Center_Right;
	private JLabel label_HoTenNV,label_MaNV,label_CaLam,label_BatDau,label_KetThuc,label_HoaDonCaTruoc,label_HoaDonChuaXuLy,label_HoaDonHienDai;
	private JLabel label_DoanhThuCaTruoc,label_icon_DoanhThu,label_TienQuyDauCa,label_icon_TienQuy;
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
	private boolean menuVisible = true;
	private JTextField txt_TienQuy;
	private JTextField txt_TienCaTruoc;


	
	public TrangChuQuanLy_GUI() {
		super("Quản lý nhân viên quản lý");
		TrangChuQuanLy_GUI();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// full man hinh
        setLocationRelativeTo(null);
        setVisible(true);
        
	}
	public static void main(String[] args) {
		new TrangChuQuanLy_GUI();
	}

	public void TrangChuQuanLy_GUI () {
		
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
		btnBanHang.addActionListener(e -> {
//		    new BanHang_GUI12();  // Mở giao diện quản lý thuốc
		    this.dispose();         // Đóng giao diện bán hàng hiện tại
		});
		
		btnThuoc = createCustomButton("Quản lý thuốc", "/hinh_anh/iconThuoc.png", 60, 60, -10, -7, 50, 8);
		panel_Menu.add(btnThuoc);
		btnThuoc.addActionListener(e -> {
//		    new Thuoc_GUI();  // Mở giao diện quản lý thuốc
		    this.dispose();         // Đóng giao diện bán hàng hiện tại
		});
		
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
		panel_Center.setBackground(new Color(255, 255, 255));
		panel_Full.add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(new BorderLayout());
		
		//// panel_Center_Left
		panel_Center_Left = new JPanel();
		panel_Center_Left.setLayout(new BorderLayout());
		panel_Center_Left.setBackground(new Color(238, 238, 238));
		panel_Center.add(panel_Center_Left, BorderLayout.WEST);
		//// panel_Centrer_Right
		panel_Center_Right = new JPanel();
		panel_Center_Right.setLayout(new BorderLayout());
		panel_Center_Right.setBackground(Color.BLACK);
		panel_Center.add(panel_Center_Right, BorderLayout.CENTER);
	
		// ===== panel_Center_Left sử dụng BorderLayout =====
		// ===== Panel chứa avatar căn giữa =====
		ImageIcon image_AvataNV = new ImageIcon(getClass().getResource("/hinh_anh/Dat_cri.png"));
		Image img_Avata = image_AvataNV.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon_Avata = new ImageIcon(img_Avata);
		label_AvataNV = new JLabel(resizedIcon_Avata);
		panel_Center_Left.add(label_AvataNV);
		JPanel p1 = new JPanel(new BorderLayout());
//		p1.setBackground(Color.LIGHT_GRAY);
		panel_Center_Left.add(p1,BorderLayout.NORTH);
		p1.setPreferredSize(new Dimension(panel_Center_Left.getWidth(),300));
		p1.add(label_AvataNV);
		// ===== Panel chứa ho ten mã căn giữa =====
		// label ho ten nhan vien
		label_HoTenNV = new JLabel("Lâm Phát Đạt");
        label_HoTenNV.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel_Center_Left.add(label_HoTenNV);
        // label ma nhan vien
        label_MaNV = new JLabel("NV24003");
        label_MaNV.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel_Center_Left.add(label_MaNV);
		JPanel p12 = new JPanel();
		p12.setLayout(new BoxLayout(p12, BoxLayout.Y_AXIS));
//		p12.setBackground(Color.LIGHT_GRAY);
		p1.add(p12,BorderLayout.SOUTH);
		p12.setPreferredSize(new Dimension(panel_Center_Left.getWidth(),60));
		label_HoTenNV.setAlignmentX(Component.CENTER_ALIGNMENT); // căn giữa
		label_MaNV.setAlignmentX(Component.CENTER_ALIGNMENT); // căn giữam
		p12.add(label_HoTenNV );
		p12.add(label_MaNV);
		
		JPanel rong = new JPanel();
		panel_Center_Left.add(rong,BorderLayout.WEST);
//		rong.setBackground(Color.LIGHT_GRAY);
		rong.setPreferredSize(new Dimension(70,panel_Center_Left.getHeight()-p1.getHeight()));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		panel_Center_Left.add(p2,BorderLayout.CENTER);
//		p2.setBackground(Color.LIGHT_GRAY);
		p2.setPreferredSize(new Dimension(panel_Center_Left.getWidth()-70,panel_Center_Left.getHeight()-p1.getHeight()));
		p2.add(Box.createVerticalStrut(20)); 
		// label ca lam
        label_CaLam = new JLabel("Ca làm: SC02");
        label_CaLam.setFont(new Font("Times New Roman", Font.BOLD, 20));
        p2.add(label_CaLam);
        p2.add(Box.createVerticalStrut(10)); 
        
        label_BatDau = new JLabel("Bắt đầu: 15:00");
        label_BatDau.setFont(new Font("Times New Roman", Font.BOLD, 20));
        p2.add(label_BatDau);
        p2.add(Box.createVerticalStrut(10)); 
        
        label_KetThuc = new JLabel("Kết thúc: 22:00");
        label_KetThuc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        p2.add(label_KetThuc);
        p2.add(Box.createVerticalStrut(10)); 
		
        label_HoaDonCaTruoc = new JLabel("Hóa đơn ca trước: 30");
        label_HoaDonCaTruoc.setFont(new Font("Times New Roman", Font.BOLD, 20));
        p2.add(label_HoaDonCaTruoc);
        p2.add(Box.createVerticalStrut(10)); 
        
        label_HoaDonChuaXuLy = new JLabel("Hóa đơn chưa xử lý: 0");
        label_HoaDonChuaXuLy.setFont(new Font("Times New Roman", Font.BOLD, 20));
        p2.add(label_HoaDonChuaXuLy);
        p2.add(Box.createVerticalStrut(10)); 
        
        label_HoaDonHienDai = new JLabel("Hóa đơn hiện tại: 0");
        label_HoaDonHienDai.setFont(new Font("Times New Roman", Font.BOLD, 20));
        p2.add(label_HoaDonHienDai);
        p2.add(Box.createVerticalStrut(10)); 
		// nhan ca
        JPanel btnnc = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15)); // căn giữa, cách trên dưới 15px
        panel_Center_Left.add(btnnc, BorderLayout.SOUTH);
//        btnnc.setBackground(Color.LIGHT_GRAY);
        btnnc.setPreferredSize(new Dimension(panel_Center_Left.getWidth(), 100));
        btnNhanCa = new JButton("Nhận ca");
        btnNhanCa.setBackground(new Color(239, 99, 29));
        btnNhanCa.setForeground(Color.WHITE);
        btnNhanCa.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnNhanCa.setBorderPainted(false);
        btnNhanCa.setFocusPainted(false);
        btnNhanCa.setPreferredSize(new Dimension(120, 50));
        btnnc.add(btnNhanCa);

		// ===== panel_Center_Right sử dụng BorderLayout =====
        
        label_DoanhThuCaTruoc = new JLabel("Doanh thu ca trước");
        label_DoanhThuCaTruoc.setFont(new Font("Times New Roman", Font.BOLD, 25));
        
        ImageIcon image_DoanhThu = new ImageIcon(getClass().getResource("/hinh_anh/ThongKe.png"));
        Image img_DoanhThu = image_DoanhThu.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_DoanhThu = new ImageIcon(img_DoanhThu);
        label_icon_DoanhThu = new JLabel(resizedIcon_DoanhThu);
        
        txt_TienCaTruoc = new JTextField("256,700,000 Đ");
        txt_TienCaTruoc.setFont(new Font("Times New Roman", Font.BOLD, 25));
        txt_TienCaTruoc.setEditable(false);// ko nhập
        txt_TienCaTruoc.setFocusable(false);//tắt tương tác vs chuột
        
        label_TienQuyDauCa = new JLabel("Tiền quỹ đầu ca");
        label_TienQuyDauCa.setFont(new Font("Times New Roman", Font.BOLD, 25));
        
        ImageIcon image_TienQuy = new ImageIcon(getClass().getResource("/hinh_anh/TienDauCa.png"));
        Image img_TienQuy = image_TienQuy.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_TienQuy = new ImageIcon(img_TienQuy);
        label_icon_TienQuy = new JLabel(resizedIcon_TienQuy);
        
        txt_TienQuy = new JTextField("1,000,000 Đ");
        txt_TienQuy.setFont(new Font("Times New Roman", Font.BOLD, 25));
        txt_TienQuy.setEditable(false);// ko nhập
        txt_TienQuy.setFocusable(false);//tắt tương tác vs chuột
        
        // doanh thu
        JPanel pR1 = new JPanel(new BorderLayout());
        pR1.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),350));
        pR1.setBackground(Color.red);
        panel_Center_Right.add(pR1,BorderLayout.NORTH);
        
        JPanel pR12 = new JPanel(new BorderLayout());
        pR12.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),50));
        pR12.add(label_DoanhThuCaTruoc);
        pR1.add(pR12,BorderLayout.NORTH);
        
        JPanel pR13 = new JPanel(new BorderLayout());
        pR13.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),200));
        pR13.add(label_icon_DoanhThu);
        pR1.add(pR13,BorderLayout.CENTER);
        
        JPanel pR14 = new JPanel(new BorderLayout());
        pR14.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),50));
        pR14.add(txt_TienCaTruoc);
        txt_TienCaTruoc.setHorizontalAlignment(SwingConstants.CENTER);// căn giữa
        pR1.add(pR14,BorderLayout.SOUTH);
        
        JPanel pR2 = new JPanel(new BorderLayout());
        pR2.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),panel_Center.getHeight()-pR1.getHeight()));
        panel_Center_Right.add(pR2, BorderLayout.CENTER);
        
        JPanel p21 = new JPanel(new BorderLayout());
        p21.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),50));
        pR2.add(p21,BorderLayout.NORTH);
        p21.add(label_TienQuyDauCa,BorderLayout.WEST);
        
        JPanel p22 = new JPanel(new BorderLayout());
        p22.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),200));
        pR2.add(p22,BorderLayout.CENTER);
        p22.add(label_icon_TienQuy,BorderLayout.CENTER);

        JPanel p23 = new JPanel(new BorderLayout());
        p23.setPreferredSize(new Dimension(panel_Center.getWidth()-panel_Center_Left.getWidth(),50));
        pR2.add(p23,BorderLayout.SOUTH);
        p23.add(txt_TienQuy);
        txt_TienQuy.setHorizontalAlignment(SwingConstants.CENTER);// căn giữa
        

        
        
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
        
//        panel_Center.setPreferredSize(new Dimension( panel_Full_Width-panel_Menu_Width, panel_Menu_Height));
        int panel_Center_Width = panel_Center.getWidth();
        int panel_Center_Height = panel_Center.getHeight();
        // panel_Center_Left
        int panel_Center_Left_Width = (int) (panel_Center_Width * 0.4);
        int panel_Center_Left_Height = (int) (panel_Center_Height* 0.97);
        panel_Center_Left.setPreferredSize(new Dimension( panel_Center_Left_Width, panel_Center_Left_Height));
        
        
        
	}
//	private void toggleMenu() {
//	    Timer timer = new Timer(10, null);
//	    final int[] width = {panel_Menu.getPreferredSize().width};
//	    int endWidth = isMenuVisible ? 0 : panel_Menu_Width;
//	    int step = isMenuVisible ? -10 : 10;
//
//	    timer.addActionListener(e -> {
//	        width[0] += step;
//	        if ((step < 0 && width[0] <= endWidth) || (step > 0 && width[0] >= endWidth)) {
//	            width[0] = endWidth;
//	            timer.stop();
//	            isMenuVisible = !isMenuVisible;
//	        }
//	        panel_Menu.setPreferredSize(new Dimension(width[0], panel_Menu.getHeight()));
//	        panel_Menu.revalidate();
//	        panel_Full.revalidate();
//	        panel_Full.repaint();
//	    });
//
//	    timer.start();
//	}

	private void toggleMenu() {
	    Timer timer = new Timer(10, null);

	    final int startWidth = panel_Menu.getPreferredSize().width;
	    final int endWidth = isMenuVisible ? 0 : panel_Menu_Width;
	    final int step = isMenuVisible ? -10 : 10;

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
	            int centerLeftWidth = (int)(centerWidth * 0.45);
	            int centerLeftHeight = (int)(centerHeight * 0.97);
	            panel_Center_Left.setPreferredSize(new Dimension(centerLeftWidth, centerLeftHeight));
	            int centerRightW =(int)(centerWidth-centerLeftWidth);
	            panel_Center_Right.setPreferredSize(new Dimension(centerRightW, centerLeftHeight));
	            //
	            

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


}
