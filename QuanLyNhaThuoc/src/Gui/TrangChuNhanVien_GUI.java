package Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrangChuNhanVien_GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panel_menu, panel_2, panel, panel_menu_button,panel_line;
    private JButton btnTraCuu, toggleButton,btnBanHang,btnThuoc,btnKhachHang,btnCaLam,btnTTNV,btnTroGiup,btnGioiThieu,btnDangXuat;
    private boolean isMenuVisible = true;
    private Timer slideTimer;
    private int sidebarWidth;
    private final int sidebarYOffset = 40;
    private JTabbedPane tabbedPane; // Bảng nội dung chính
    private JLabel lblINV ,lbNhanVien,lbMaNhanVien;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TrangChuNhanVien_GUI frame = new TrangChuNhanVien_GUI();
                frame.setVisible(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TrangChuNhanVien_GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 82, 40));
        panel.setBounds(0, 0, 771, 60);
        contentPane.add(panel);
        ImageIcon originalIlogo = new ImageIcon(getClass().getResource("/hinh_anh/logo.png"));
        Image img_logo = originalIlogo.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_logo = new ImageIcon(img_logo);
        JLabel lblIlogo = new JLabel(resizedIcon_logo);
        lblIlogo.setBounds(10, 10, 70, 50);
        panel.add(lblIlogo);
        //iconNhanVien
        ImageIcon originalINV = new ImageIcon(getClass().getResource("/hinh_anh/iconNhanVien.png"));
        Image img_NV = originalINV.getImage().getScaledInstance(73, 77, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_NV= new ImageIcon(img_NV);
        lblINV = new JLabel(resizedIcon_NV);
//        lblINV.setBounds(100, 10, 70, 50);
        panel.add(lblINV);
        //lb Nhan viên
        lbNhanVien = new JLabel("Lâm Phát Đạt");
        lbNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lbNhanVien.setForeground(new Color(255, 255, 255));
        panel.add(lbNhanVien);
        //lb mã nhân viên
        lbMaNhanVien = new JLabel("NV24003");
        lbMaNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbMaNhanVien.setForeground(new Color(255, 255, 255));
        panel.add(lbMaNhanVien);
        
        panel_menu = new JPanel();
        panel_menu.setLayout(null);
        panel_menu.setBackground(new Color(0, 82, 40));
        contentPane.add(panel_menu);
        
        panel_menu_button = new JPanel();
        panel_menu_button.setLayout(null);
        panel_menu_button.setBackground(new Color(0, 80, 42));
        contentPane.add(panel_menu_button);
        
        panel_line = new JPanel();
        panel_line.setBackground(new Color(244, 242, 193));
//        panel_line.setBounds(0, 0, 107, 7);
        panel_menu_button.add(panel_line);
        // menu
        toggleButton = new JButton();
        toggleButton.setBackground(new Color(255, 255, 255));
        toggleButton.setForeground(Color.BLACK);
        toggleButton.setBounds(10, 11, 50, 30);
        toggleButton.setBorderPainted(false); // tắt viền
        toggleButton.setFocusPainted(false);
        toggleButton.addActionListener(e -> toggleMenu());
        panel_menu_button.add(toggleButton);
        ImageIcon originalImenu = new ImageIcon(getClass().getResource("/hinh_anh/menu.png"));
        Image img_menu = originalImenu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_menu = new ImageIcon(img_menu);
        JLabel lblImenu = new JLabel(resizedIcon_menu);
        lblImenu.setBounds(5, 5, 30, 30);
        toggleButton.add(lblImenu);
        
        btnTraCuu = createCustomButton("Tra cứu", "/hinh_anh/iconTimKiem_resized.png", 
                40, 40, 0, 5, 50, 10);
		panel_menu.add(btnTraCuu);
		
		btnBanHang = createCustomButton("Quản lý bán hàng", "/hinh_anh/iconGioHang.png", 
		                35, 35, 5, 7, 50, 10);
		panel_menu.add(btnBanHang);
		
		btnThuoc = createCustomButton("Quản lý thuốc", "/hinh_anh/iconThuoc.png", 
		                60, 60, -10, -5, 50, 10);
		panel_menu.add(btnThuoc);
		
		btnKhachHang = createCustomButton("Quản lý khách hàng", "/hinh_anh/iconKhachHang.png", 
		                60, 60, -10, -5, 50, 15);
		panel_menu.add(btnKhachHang);
		
		btnCaLam = createCustomButton("Quản lý ca làm", "/hinh_anh/iconCaLam.png", 
		                40, 40, 0, 7, 50, 10);
		panel_menu.add(btnCaLam);
		
		btnTTNV = createCustomButton("Thông tin nhân viên", "/hinh_anh/iconThongTin.png", 
		                50, 50, -5, 1, 50, 10);
		panel_menu.add(btnTTNV);
		
		btnTroGiup = createCustomButton("Trợ giúp", "/hinh_anh/iconTroGiup.png", 
		                45, 45, 0, 5, 50, 10);
		panel_menu.add(btnTroGiup);
		
		btnGioiThieu = createCustomButton("Giới thiệu", "/hinh_anh/iconGioiThieu.png", 
                		36, 36, 5, 7, 50, 10);
		panel_menu.add(btnGioiThieu);
		
		btnDangXuat = createCustomButton("Đăng xuất", "/hinh_anh/iconDangXuat.png", 
        				36, 36, 5, 7, 50, 10);
		panel_menu.add(btnDangXuat);
        
        // Tạo bảng nội dung chính
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);
        

        contentPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePanel();
            }
        });

        resizePanel();
    }

    private void resizePanel() {
        int width = contentPane.getWidth();
        int height = contentPane.getHeight();
        // Header
        int panelWidth = width;
        int panelHeight = (int) (height * 0.08);
        panel.setBounds(0, 0, panelWidth, panelHeight);
        //icon nhan vien
        lblINV.setBounds(width-100, 2, 60, 64);
        //lb nhan vien
        lbNhanVien.setBounds(width-250, 2, 150, 50);
        //lb ma nhan vien
        lbMaNhanVien.setBounds(width-210, 25, 150, 50);
        // Sidebar Menu
        sidebarWidth = (int) (width * 0.15);
        int sidebarHeight = (int) (height * 0.9);
        panel_menu.setBounds(0, panelHeight + sidebarYOffset, sidebarWidth, sidebarHeight);
        //line
        panel_line.setBounds(0, 0, sidebarWidth, 7);
        // Panel menu nhỏ chứa nút ☰
        panel_menu_button.setBounds(0, panelHeight - 5, sidebarWidth, 45);
        //btn tra cuu
        btnTraCuu.setBounds(0, 0,sidebarWidth-15 , 50);
        //btn ban hang
        btnBanHang.setBounds(0, 60,sidebarWidth-15 , 50);
        //btn Thuoc
        btnThuoc.setBounds(0, 120,sidebarWidth-15 , 50);
        //btn Thuoc
        btnKhachHang.setBounds(0, 180,sidebarWidth-15 , 50);
        //btn ca làm
        btnCaLam.setBounds(0, 240,sidebarWidth-15 , 50);
        //btn thông tin nhân viên
        btnTTNV.setBounds(0, 300,sidebarWidth-15 , 50);
        //btn trợ giúp
        btnTroGiup.setBounds(0, 360,sidebarWidth-15 , 50);
        //btn giới thiệu
        btnGioiThieu.setBounds(0, 420,sidebarWidth-15 , 50);
        //btn đăng xuất
        btnDangXuat.setBounds(0, sidebarHeight-75,sidebarWidth-15 , 50);
        
        // Bảng nội dung chính (JTabbedPane)
        int contentX = isMenuVisible ? sidebarWidth : 0;  // Điều chỉnh theo trạng thái menu
        int contentWidth = isMenuVisible ? (width - sidebarWidth) : width;
        int contentHeight = height - (panelHeight + sidebarYOffset);
        tabbedPane.setBounds(contentX, panelHeight + sidebarYOffset, contentWidth, contentHeight);
    }

    private void toggleMenu() {
        int start = isMenuVisible ? 0 : -sidebarWidth;
        int end = isMenuVisible ? -sidebarWidth : 0;
        int step = isMenuVisible ? -10 : 10;

        slideTimer = new Timer(10, new ActionListener() {
            int x = start;
            @Override
            public void actionPerformed(ActionEvent e) {
                x += step;
                if ((isMenuVisible && x <= end) || (!isMenuVisible && x >= end)) {
                    ((Timer) e.getSource()).stop();
                    isMenuVisible = !isMenuVisible;
                    resizePanel();  // Cập nhật kích thước bảng khi menu thay đổi
                }
                panel_menu.setBounds(x, panel.getHeight() + sidebarYOffset, sidebarWidth, contentPane.getHeight() - panel.getHeight());
                contentPane.repaint();
            }
        });
        slideTimer.start();
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
