package Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class QuanLyThongKe_GUI extends JFrame {
	private JPanel panel_Full, panel_Header, panel_Header_1, panel_Menu, panel_Center;
	private JLabel label_Logo, label_Icon_NV, label_NV_Header, label_Ma_NV_Header, label_Menu;
	private JButton btnTraCuu, btnBanHang, btnThuoc, btnKhachHang, btnCaLam, btnTTNV, btnTroGiup, btnGioiThieu,
			btnDangXuat, btnNhanCa, btnKhuyenMai, btnKho, btnBaoCaoThongKe, btnNhanVien;
	private JButton btnMenu;
	private boolean isMenuVisible = true; // Trạng thái menu: hiện hay ẩn
	private Timer slideTimer; // Timer để thực hiện hiệu ứng
	private int panel_Menu_Width; // Chiều rộng của menu (sidebar)
	private JPanel panel_Header_1_Left;
	private JPanel panel_Header_1_Right;
	private JLabel lbTKDT, lbHoadon, lbKhachhang,lbTieuDeTK,lbTieuDeHD,lbTieuDeKH;
	private JTextField txtHoaDon, txtKhachHang, txtDoanhThu;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnBCTK, btnBCDT, btnBDMH;

	public QuanLyThongKe_GUI() {
		super("Quản lý nhân viên");
		TrangChuQuanLy_GUI();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);// full man hinh
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		new QuanLyThongKe_GUI();
	}

	public void TrangChuQuanLy_GUI() {

		panel_Full = new JPanel();
		setContentPane(panel_Full);
		panel_Full.setLayout(null);

		/////////// panel Header
		panel_Header = new JPanel();
		panel_Header.setLayout(null);
		panel_Header.setBackground(new Color(0, 80, 42));
		panel_Full.add(panel_Header);

		ImageIcon image_Icon_Logo = new ImageIcon(getClass().getResource("/hinh_anh/logo.png"));
		Image img_Logo = image_Icon_Logo.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon_logo = new ImageIcon(img_Logo);
		label_Logo = new JLabel(resizedIcon_logo);
		panel_Header.add(label_Logo);

		// icon Nhan Vien
		ImageIcon image_Icon_NV = new ImageIcon(getClass().getResource("/hinh_anh/iconNhanVien.png"));
		Image img_NV = image_Icon_NV.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon_NV = new ImageIcon(img_NV);
		label_Icon_NV = new JLabel(resizedIcon_NV);
		panel_Header.add(label_Icon_NV);

		// label Nhan Vien
		label_NV_Header = new JLabel("Lâm Phát Đạt");
		label_NV_Header.setFont(new Font("Times New Roman", Font.BOLD, 22));
		label_NV_Header.setForeground(new Color(255, 255, 255));
		panel_Header.add(label_NV_Header);

		// label Ma Nhan Vien
		label_Ma_NV_Header = new JLabel("NV24003");
		label_Ma_NV_Header.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_Ma_NV_Header.setForeground(new Color(255, 255, 255));
		panel_Header.add(label_Ma_NV_Header);

		// panel header 1
		panel_Header_1 = new JPanel();
		panel_Header_1.setLayout(null);
		panel_Header_1.setBackground(new Color(0, 65, 42));
		panel_Full.add(panel_Header_1);

		//// panel_Header_1_Left && panel_Header_1_Right
		//// panel_Header_1_Left
		panel_Header_1_Left = new JPanel();
		panel_Header_1_Left.setLayout(null);
		panel_Header_1_Left.setBackground(new Color(0, 65, 42));

		//// panel_Header_1_Right
		panel_Header_1_Right = new JPanel();
		panel_Header_1_Right.setLayout(null);
		panel_Header_1_Right.setBackground(new Color(0, 65, 42));

		//// add panel_Header_1_Left && panel_Header_1_Right vào panel_Header_1
		panel_Header_1.add(panel_Header_1_Left);
		panel_Header_1.add(panel_Header_1_Right);

		//// panel_Header_1_Right
		btnBCTK = new JButton("Báo Cáo Thống Kê");
		btnBCDT = new JButton("Báo Cáo Doanh Thu");
		btnBDMH = new JButton("Biểu Đồ Minh Họa");

		//// set màu va hieu ung hover cho button
		addHoverButton(btnBCTK, new Color(255, 253, 208), new Color(230, 220, 150));
		addHoverButton(btnBCDT, new Color(255, 253, 208), new Color(230, 220, 150));
		addHoverButton(btnBDMH, new Color(255, 253, 208), new Color(230, 220, 150));

		// Thêm 3 button vào panel_Header_1_Right
		panel_Header_1_Right.add(btnBCTK);
		panel_Header_1_Right.add(btnBCDT);
		panel_Header_1_Right.add(btnBDMH);
		
		

		// nut button menu
		btnMenu = new JButton();
		btnMenu.setBackground(new Color(0, 80, 42));
		btnMenu.setBorderPainted(false); // tắt viền
		btnMenu.setFocusPainted(false);
		panel_Header_1_Left.add(btnMenu);
		ImageIcon image_Icon_Menu = new ImageIcon(getClass().getResource("/hinh_anh/menu1.png"));
		Image img_menu = image_Icon_Menu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon_menu = new ImageIcon(img_menu);
		label_Menu = new JLabel(resizedIcon_menu);
		btnMenu.addActionListener(e -> tongleMenu());
		btnMenu.add(label_Menu);

		///////// panel_Menu
		panel_Menu = new JPanel();
		panel_Menu.setBackground(new Color(0, 80, 42));
		panel_Full.add(panel_Menu);

		btnTraCuu = createCustomButton("Tra cứu", "/hinh_anh/iconTimKiem_resized.png", 40, 40, 0, 3, 50, 8);
		panel_Menu.add(btnTraCuu);

		btnBanHang = createCustomButton("Quản lý bán hàng", "/hinh_anh/iconGioHang.png", 35, 35, 5, 7, 50, 8);
		panel_Menu.add(btnBanHang);

		btnThuoc = createCustomButton("Quản lý thuốc", "/hinh_anh/iconThuoc.png", 60, 60, -10, -7, 50, 8);
		panel_Menu.add(btnThuoc);

		btnKhachHang = createCustomButton("Quản lý khách hàng", "/hinh_anh/iconKhachHang.png", 60, 60, -10, -5, 50, 8);
		panel_Menu.add(btnKhachHang);

		btnCaLam = createCustomButton("Quản lý ca làm", "/hinh_anh/iconCaLam.png", 40, 40, 0, 4, 50, 8);
		panel_Menu.add(btnCaLam);

		btnKhuyenMai = createCustomButton("Quản lý khuyến mãi", "/hinh_anh/iconKhuyenMai.png", 35, 35, 2, 5, 50, 8);
		panel_Menu.add(btnKhuyenMai);

		btnTTNV = createCustomButton("Thông tin nhân viên", "/hinh_anh/iconThongTin.png", 50, 50, -5, 0, 50, 8);
		panel_Menu.add(btnTTNV);

		btnKho = createCustomButton("Quản lý kho", "/hinh_anh/iconKho.png", 40, 40, 0, 2, 50, 8);
		panel_Menu.add(btnKho);

		btnBaoCaoThongKe = createCustomButton("Báo cáo và thống kê", "/hinh_anh/iconBaoCaoThongKe.png", 40, 40, 0, 2,
				50, 8);
		panel_Menu.add(btnBaoCaoThongKe);

		btnNhanVien = createCustomButton("Quản lý nhân viên", "/hinh_anh/iconQLNV.png", 55, 55, -10, -5, 50, 8);
		panel_Menu.add(btnNhanVien);

		btnTroGiup = createCustomButton("Trợ giúp", "/hinh_anh/iconTroGiup.png", 45, 45, 0, 5, 50, 8);
		panel_Menu.add(btnTroGiup);

		btnGioiThieu = createCustomButton("Giới thiệu", "/hinh_anh/iconGioiThieu.png", 34, 34, 4, 7, 50, 8);
		panel_Menu.add(btnGioiThieu);

		btnDangXuat = createCustomButton("Đăng xuất", "/hinh_anh/iconDangXuat.png", 36, 36, 5, 7, 50, 8);
		panel_Menu.add(btnDangXuat);

		////////////// panel_Center
		panel_Center = new JPanel();
		panel_Center.setLayout(null);
		panel_Center.setBackground(new Color(255, 255, 255));
		panel_Full.add(panel_Center);
		


		// Gọi hàm resize sau khi frame hiển thị
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizePanel(); // tự động resize khi thay đổi kích thước
			}
		});

	}

	
	
	//
	private void initComponents() 
	{
		Font fontChu = new Font("Arial", Font.BOLD, 18);
		Color textColor = new Color(0,0,0);
		lbTieuDeHD = new JLabel("Số hóa đơn hôm nay");
		lbTieuDeHD.setFont(fontChu);
		lbTieuDeHD.setForeground(textColor);
		panel_Center.add(lbTieuDeHD);
		
	}
	
	
	// hàm resize icon
	public JLabel resizeIcon(int iconX, int iconY, int iconWidth, int iconHeight, String filePath) {
		ImageIcon originalIcon = new ImageIcon(getClass().getResource(filePath));
		Image img = originalIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(img);
		JLabel iconLabel = new JLabel(resizedIcon);
		iconLabel.setBounds(iconX, iconY, iconWidth, iconHeight);

		return iconLabel;
	}
	
	
	// Hàm gắn hiệu ứng hover cho một button
	private void addHoverButton(JButton button, Color defaultColor, Color hoverColor) {
	    button.setBackground(defaultColor);
	    button.setBorderPainted(false); // không viền
	    button.setFocusPainted(false);  // không viền khi focus

	    button.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) {
	            button.setBackground(hoverColor);
	        }

	        public void mouseExited(java.awt.event.MouseEvent evt) {
	            button.setBackground(defaultColor);
	        }
	    });
	}
	
	
	// ham reisze Jframe
	private void resizePanel() {
		int panel_Full_Width = panel_Full.getWidth();
		int panel_Full_Height = panel_Full.getHeight();
		// panel_header
		int panel_Header_Width = panel_Full_Width;
		int panel_Header_Height = (int) (panel_Full_Height * 0.08); // 8% cua panel_Full
		panel_Header.setBounds(0, 0, panel_Header_Width, panel_Header_Height);

		label_Logo.setBounds(10, 5, 70, 50);
		label_Icon_NV.setBounds(panel_Full_Width - 100, 1, 60, 64);
		label_NV_Header.setBounds(panel_Header_Width - 250, -2, 150, 50);
		label_Ma_NV_Header.setBounds(panel_Header_Width - 250, 20, 150, 50);

		// panel_header_1
		int panel_Header_1_Height = (int) (panel_Full_Height * 0.06); // 6% cua panel_Full
		panel_Header_1.setBounds(0, panel_Header_Height, panel_Full_Width, panel_Header_1_Height);

		int leftPanelWidth = (int) (panel_Full_Width * 0.17);
		int rightPanelWidth = panel_Full_Width - leftPanelWidth;

		panel_Header_1_Left.setBounds(0, 0, leftPanelWidth, panel_Header_1_Height);
		panel_Header_1_Right.setBounds(leftPanelWidth, 0, rightPanelWidth, panel_Header_1_Height);

		btnMenu.setBounds(10, 10, 50, 30);
		label_Menu.setBounds(9, 0, 30, 30);

		//// panel_Header_1_Right

		//// set kich thuoc va x,y cho button
		int panelWidth = panel_Header_1_Right.getWidth();
		int buttonWidth = 200;
		int buttonHeight = 40;
		int numberOfButtons = 3;

		int spacing = (panelWidth - (numberOfButtons * buttonWidth)) / (numberOfButtons + 1);
		int y = (panel_Header_1_Height - buttonHeight) / 2;

		// Đặt vị trí từng nút
		btnBCTK.setBounds(spacing, y, buttonWidth, buttonHeight);
		btnBCDT.setBounds(spacing * 2 + buttonWidth, y, buttonWidth, buttonHeight);
		btnBDMH.setBounds(spacing * 3 + buttonWidth * 2, y, buttonWidth, buttonHeight);


		// panel_Menu
		panel_Menu_Width = (int) (panel_Full_Width * 0.17);// 0.17% cua panel_Full
		int panel_Menu_Height = (int) (panel_Full_Height - panel_Header_Height - panel_Header_1_Height);
		panel_Menu.setBounds(0, panel_Header_1_Height + panel_Header_Height, panel_Menu_Width, panel_Menu_Height);

		// Các nút trong panel_menu
		btnTraCuu.setBounds(0, 2, panel_Menu_Width - 15, 45);
		btnBanHang.setBounds(0, 50, panel_Menu_Width - 15, 45);
		btnThuoc.setBounds(0, 98, panel_Menu_Width - 15, 45);
		btnKhachHang.setBounds(0, 146, panel_Menu_Width - 15, 45);
		btnCaLam.setBounds(0, 194, panel_Menu_Width - 15, 45);
		btnTTNV.setBounds(0, 242, panel_Menu_Width - 15, 45);
		btnKhuyenMai.setBounds(0, 290, panel_Menu_Width - 15, 45);
		btnKho.setBounds(0, 338, panel_Menu_Width - 15, 45);
		btnBaoCaoThongKe.setBounds(0, 386, panel_Menu_Width - 15, 45);
		btnNhanVien.setBounds(0, 434, panel_Menu_Width - 15, 45);
		btnTroGiup.setBounds(0, 482, panel_Menu_Width - 15, 45);
		btnGioiThieu.setBounds(0, 530, panel_Menu_Width - 15, 45);
		btnDangXuat.setBounds(0, panel_Menu_Height - 50, panel_Menu_Width - 15, 45);

		// panel_Center
		int panel_Center_X = isMenuVisible ? panel_Menu_Width + 10 : 10;
		int panel_Center_Width = isMenuVisible ? (panel_Full_Width - panel_Menu_Width - 20) : (panel_Full_Width - 20);
		int panel_Center_Height = panel_Menu_Height - 10;
		panel_Center.setBounds(panel_Center_X, panel_Header_Height + panel_Header_1_Height, panel_Center_Width,
				panel_Center_Height);

		//// tao một cac label với icon hình ảnh...

		int iconWidth = 270;
		int iconHeight = 300;

		int xTK = (int) (panel_Center_Width * 0.20);
		int yTK = (int) (panel_Center_Height * 0.05);

		int xHD = (int) (panel_Center_Width * 0.65); // Bên phải
		int yHD = yTK;

		int xKH = (int) (panel_Center_Width * 0.42); // Chính giữa (xấp xỉ)
		int yKH = (int) (panel_Center_Height * 0.15); // Thấp hơn

		//// Font và màu cho text
		Font fontChu = new Font("Arial", Font.BOLD, 18);
		Color textColor = new Color(0,0,0);

		int textWidth = 200;
		int textHeight = 35;

		//// x va y txt cua thống kê
		int xTextTK = xTK + (iconWidth - textWidth) / 2;
		int yTextTK = yTK + iconHeight + 10;

		//// x va y txt cua hoa don
		int xTextHD = xHD + (iconWidth - textWidth) / 2;
		int yTextHD = yHD + iconHeight + 10;

		//// x va y txt cua khach hang
		int xTextKH = xKH + (iconWidth - textWidth) / 2;
		int yTextKH = yKH + iconHeight + 10;
		
		
		
		
		// Tiêu đề: Doanh thu hôm nay => icon1
		if (lbTieuDeTK == null) {
			lbTieuDeTK = new JLabel("Doanh thu hôm nay", SwingConstants.CENTER);
			lbTieuDeTK.setFont(fontChu);
			lbTieuDeTK.setForeground(textColor);
			lbTieuDeTK.setBounds(xTextTK, yTK - 40, textWidth, textHeight);
			panel_Center.add(lbTieuDeTK);
			
		} else {
			lbTieuDeTK.setBounds(xTextTK, yTK - 40, textWidth, textHeight);
		}

		//// Icon 1: Thống kê (trái)
		if (lbTKDT == null) {
			lbTKDT = resizeIcon(xTK, yTK, iconWidth, iconHeight, "/hinh_anh/ThongKe.png");
			panel_Center.add(lbTKDT);
		} else {
			lbTKDT.setBounds(xTK, yTK, iconWidth, iconHeight);
		}

		//// JTextField doanh thu (icon1)
		if (txtDoanhThu == null) {
			txtDoanhThu = new JTextField("100,000,000 VNĐ");
			txtDoanhThu.setFont(fontChu);
			txtDoanhThu.setForeground(textColor);
			txtDoanhThu.setHorizontalAlignment(JTextField.CENTER);
			txtDoanhThu.setEditable(false);
			txtDoanhThu.setBorder(null);
			txtDoanhThu.setBounds(xTextTK, yTextTK, textWidth, textHeight);
			panel_Center.add(txtDoanhThu);
		} else {
			txtDoanhThu.setBounds(xTextTK, yTextTK, textWidth, textHeight);
		}

		
		
		
		//// Tạo tiêu đề: "Số hóa đơn hôm nay" => icon2
		if (lbTieuDeHD == null) {
			lbTieuDeHD = new JLabel("Hóa đơn hôm nay", SwingConstants.CENTER);
			lbTieuDeHD.setFont(fontChu);
			lbTieuDeHD.setForeground(textColor);
			lbTieuDeHD.setBounds(xTextHD, yHD - 40, textWidth, textHeight);
			panel_Center.add(lbTieuDeHD);
		} else {
			lbTieuDeHD.setBounds(xTextHD, yHD - 40, textWidth, textHeight);
		}
		
		//// Icon 2: Hóa đơn (phải)
		if (lbHoadon == null) {
			lbHoadon = resizeIcon(xHD, yHD, iconWidth + 50, iconHeight + 50, "/hinh_anh/HoaDon.png");
			panel_Center.add(lbHoadon);
		} else {
			lbHoadon.setBounds(xHD, yHD, iconWidth, iconHeight);
		}

		//// JTextField hóa đơn
		if (txtHoaDon == null) {
			txtHoaDon = new JTextField("240 Hóa đơn");
			txtHoaDon.setFont(fontChu);
			txtHoaDon.setForeground(textColor);
			txtHoaDon.setHorizontalAlignment(JTextField.CENTER);
			txtHoaDon.setEditable(false);
			txtHoaDon.setBorder(null);
			txtHoaDon.setBounds(xTextHD, yTextHD, textWidth, textHeight);
			panel_Center.add(txtHoaDon);
		} else {
			txtHoaDon.setBounds(xTextHD, yTextHD, textWidth, textHeight);
		}

		
		
		
		// Tiêu đề: Số khách hàng hôm nay => icon3
		if (lbTieuDeKH == null) {
			lbTieuDeKH = new JLabel("Khách hàng hôm nay", SwingConstants.CENTER);
			lbTieuDeKH.setFont(fontChu);
			lbTieuDeKH.setForeground(textColor);
			lbTieuDeKH.setBounds(xTextKH, yKH - 20, textWidth, textHeight);
			panel_Center.add(lbTieuDeKH);
		} else {
			lbTieuDeKH.setBounds(xTextKH, yKH - 20, textWidth, textHeight);
		}
		
		//// Icon 3: Khách hàng (giữa, thấp hơn)
		if (lbKhachhang == null) {
			lbKhachhang = resizeIcon(xKH, yKH, iconWidth, iconHeight, "/hinh_anh/NhieuKhachHang.png");
			panel_Center.add(lbKhachhang);
		} else {
			lbKhachhang.setBounds(xKH, yKH, iconWidth, iconHeight);
		}

		//// JTextField khách hàng icon3
		if (txtKhachHang == null) {
			txtKhachHang = new JTextField("500 Khách hàng");
			txtKhachHang.setFont(fontChu);
			txtKhachHang.setForeground(textColor);
			txtKhachHang.setHorizontalAlignment(JTextField.CENTER);
			txtKhachHang.setEditable(false);
			txtKhachHang.setBorder(null);
			txtKhachHang.setBounds(xTextKH, yTextKH - 30, textWidth, textHeight);
			panel_Center.add(txtKhachHang);
		} else {
			txtKhachHang.setBounds(xTextKH, yTextKH - 30, textWidth, textHeight);
		}

		
		
		
		//// ====== BẢNG HÓA ĐƠN PHÍA DƯỚI ======
		if (scrollPane == null) {
			// Dữ liệu mẫu
			String[] columnNames = { "Mã HĐ", "Ngày lập", "Tên khách hàng", "Tổng tiền" };
//            Object[][] data = {
//                {"HD001", "2025-04-10", "Nguyễn Văn A", "1,200,000"},
//                {"HD002", "2025-04-10", "Trần Thị B", "950,000"},
//                {"HD003", "2025-04-10", "Phạm Văn C", "2,300,000"},
//            };

			DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			JTable table = new JTable(model);
			table.setFont(new Font("Arial", Font.PLAIN, 14));
			table.setRowHeight(28);
			scrollPane = new JScrollPane(table);
			panel_Center.add(scrollPane);

			////// Lưu lại table vào biến toàn cục
			this.table = table;
		}

		//// Cập nhật vị trí và kích thước khi resize
		int width_Tbale = (int) (panel_Center.getWidth());
		int x_Table = 0;
		int y_Table = (int) (panel_Center.getHeight() * 0.55);
		int height = 500;

		scrollPane.setBounds(x_Table, y_Table, width_Tbale, height);

		//// Chia đều lại cột
		if (table != null) {
			int columnCount = table.getColumnCount();
			int columnWidth = width_Tbale / columnCount; // do rong cua cot

			for (int i = 0; i < columnCount; i++) {
				TableColumn column = table.getColumnModel().getColumn(i); // lay ra cot thu i
				column.setPreferredWidth(columnWidth); // set lai kich thuoc cot theo columnWidth
			}

		}

		//// Header table
		JTableHeader header_table = table.getTableHeader();
		header_table.setBackground(new Color(0, 80, 42)); // nền header xanh
		header_table.setForeground(Color.WHITE); // chữ header trắng
		header_table.setFont(new Font("Arial", Font.BOLD, 15));

	}

	// ham di chuyen menu
	private void tongleMenu() {
		int start = isMenuVisible ? 0 : -panel_Menu_Width; // Nếu đang hiện => vị trí bắt đầu là 0, ẩn => -width
		int end = isMenuVisible ? -panel_Menu_Width : -5; // Nếu đang hiện => trượt ra ngoài (-width), ẩn => trượt về -5
		int step = isMenuVisible ? -10 : 10; // Bước nhảy trái hay phải

		slideTimer = new Timer(10, new ActionListener() {
			int x = start;

			@Override
			public void actionPerformed(ActionEvent e) {
				x += step;
				if ((isMenuVisible && x <= end) || (!isMenuVisible && x >= end)) {
					((Timer) e.getSource()).stop();
					isMenuVisible = !isMenuVisible;
					resizePanel(); // Cập nhật kích thước bảng khi menu thay đổi
				}
				panel_Menu.setBounds(x, panel_Header.getHeight() + panel_Header_1.getHeight(), panel_Menu_Width,
						panel_Full.getHeight() - panel_Header.getHeight());
				panel_Full.repaint();
			}
		});
		slideTimer.start();
	}

	// ham Custom các button
	private JButton createCustomButton(String text, String iconPath, int iconWidth, int iconHeight, int iconX,
			int iconY, int textX, int textY) {
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
