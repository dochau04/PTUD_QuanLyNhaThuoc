package Gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import Dao.KhuyenMai_DAO;
import Entity.KhuyenMai;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMai_GUI extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(KhuyenMai_GUI.class.getName());
    private JPanel panel_Full, panel_Header, panel_Header_Noth, panel_Header_Left, panel_Header_Right, panel_Menu, panel_Center;
    private JLabel label_Logo, label_Icon_NV, label_NV_Header, label_Ma_NV_Header, label_Menu;
    private JButton btnTraCuu, btnBanHang, btnThuoc, btnKhachHang, btnCaLam, btnTTNV, btnTroGiup, btnGioiThieu, btnDangXuat;
    private JButton btnMenu, btnKhuyenMai, btnKho, btnBaoCaoThongKe, btnNhanVien;
    private boolean isMenuVisible = true;
    private Timer slideTimer;
    private int panel_Menu_Width;
    private JTable table;
    private DefaultTableModel tableModel;
    private KhuyenMai_DAO khuyenMaiDAO = new KhuyenMai_DAO();

    public KhuyenMai_GUI() {
        super("Quản lý khuyến mãi");
        initGUI();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new KhuyenMai_GUI();
    }

    private void initGUI() {
        panel_Full = new JPanel(new BorderLayout());
        setContentPane(panel_Full);

        // Panel Header
        panel_Header = new JPanel(new BorderLayout());
        panel_Full.add(panel_Header, BorderLayout.NORTH);

        panel_Header_Noth = new JPanel(null);
        panel_Header_Noth.setBackground(new Color(0, 60, 42));
        panel_Header.add(panel_Header_Noth, BorderLayout.NORTH);

        ImageIcon image_Icon_Logo = new ImageIcon(getClass().getResource("/hinh_anh/logo.png"));
        Image img_Logo = image_Icon_Logo.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        label_Logo = new JLabel(new ImageIcon(img_Logo));
        panel_Header_Noth.add(label_Logo);

        ImageIcon image_Icon_NV = new ImageIcon(getClass().getResource("/hinh_anh/iconNhanVien.png"));
        Image img_NV = image_Icon_NV.getImage().getScaledInstance(70, 50, Image.SCALE_SMOOTH);
        label_Icon_NV = new JLabel(new ImageIcon(img_NV));
        panel_Header_Noth.add(label_Icon_NV);

        label_NV_Header = new JLabel("Lâm Phát Đạt");
        label_NV_Header.setFont(new Font("Times New Roman", Font.BOLD, 22));
        label_NV_Header.setForeground(Color.WHITE);
        panel_Header_Noth.add(label_NV_Header);

        label_Ma_NV_Header = new JLabel("NV24003");
        label_Ma_NV_Header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label_Ma_NV_Header.setForeground(Color.WHITE);
        panel_Header_Noth.add(label_Ma_NV_Header);

        panel_Header_Left = new JPanel(null);
        panel_Header_Left.setBorder(new MatteBorder(2, 0, 0, 0, Color.WHITE));
        panel_Header_Left.setBackground(new Color(0, 60, 42));
        panel_Header.add(panel_Header_Left, BorderLayout.WEST);

        panel_Header_Right = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_Header_Right.setBorder(new MatteBorder(2, 0, 0, 0, Color.WHITE));
        panel_Header_Right.setBackground(new Color(0, 60, 42));
        panel_Header.add(panel_Header_Right, BorderLayout.CENTER);

        btnMenu = new JButton();
        btnMenu.setBackground(new Color(0, 60, 42));
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        panel_Header_Left.add(btnMenu);
        ImageIcon image_Icon_Menu = new ImageIcon(getClass().getResource("/hinh_anh/menu1.png"));
        Image img_menu = image_Icon_Menu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        label_Menu = new JLabel(new ImageIcon(img_menu));
        btnMenu.addActionListener(e -> toggleMenu());
        btnMenu.add(label_Menu);

        JButton btnThemKM = createToolbarButton("Thêm khuyến mãi", "➕");
        btnThemKM.addActionListener(e -> themKhuyenMai());
        panel_Header_Right.add(Box.createHorizontalStrut(50));
        panel_Header_Right.add(btnThemKM);
        panel_Header_Right.add(Box.createHorizontalStrut(30));

        JButton btnCapNhatKM = createToolbarButton("Cập nhật khuyến mãi", "🛠");
        btnCapNhatKM.addActionListener(e -> capNhatKhuyenMai());
        panel_Header_Right.add(btnCapNhatKM);
        panel_Header_Right.add(Box.createHorizontalStrut(480));

        String[] options = {"Tất cả", "Theo tên", "Theo mã"};
        JComboBox<String> comboSearch = new JComboBox<>(options);
        JTextField searchField = new JTextField("Tìm kiếm...");
        searchField.setPreferredSize(new Dimension(150, 30));
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Tìm kiếm...")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Tìm kiếm...");
                }
            }
        });

        JButton btnSearch = new JButton("🔍");
        btnSearch.addActionListener(e -> {
            String condition = (String) comboSearch.getSelectedItem();
            String keyword = searchField.getText().trim();
            if (!keyword.equals("Tìm kiếm...")) {
                loadKhuyenMaiData(khuyenMaiDAO.searchKhuyenMai(condition, keyword));
            }
        });

        JButton refreshButton = new JButton("⟳");
        refreshButton.addActionListener(e -> loadKhuyenMaiData());

        panel_Header_Right.add(comboSearch);
        panel_Header_Right.add(searchField);
        panel_Header_Right.add(btnSearch);
        panel_Header_Right.add(refreshButton);

        // Panel Menu
        panel_Menu = new JPanel(null);
        panel_Menu.setBackground(new Color(0, 80, 42));
        panel_Full.add(panel_Menu, BorderLayout.WEST);

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

        btnBaoCaoThongKe = createCustomButton("Báo cáo và thống kê", "/hinh_anh/iconBaoCaoThongKe.png", 40, 40, 0, 2, 50, 8);
        panel_Menu.add(btnBaoCaoThongKe);

        btnNhanVien = createCustomButton("Quản lý nhân viên", "/hinh_anh/iconQLNV.png", 55, 55, -10, -5, 50, 8);
        panel_Menu.add(btnNhanVien);

        btnTroGiup = createCustomButton("Trợ giúp", "/hinh_anh/iconTroGiup.png", 45, 45, 0, 5, 50, 8);
        panel_Menu.add(btnTroGiup);

        btnGioiThieu = createCustomButton("Giới thiệu", "/hinh_anh/iconGioiThieu.png", 34, 34, 4, 7, 50, 8);
        panel_Menu.add(btnGioiThieu);

        btnDangXuat = createCustomButton("Đăng xuất", "/hinh_anh/iconDangXuat.png", 36, 36, 5, 7, 50, 8);
        panel_Menu.add(btnDangXuat);

        // Panel Center
        panel_Center = new JPanel(new BorderLayout());
        panel_Center.setBackground(Color.WHITE);
        panel_Full.add(panel_Center, BorderLayout.CENTER);

        String[] columns = {"STT", "Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Trạng thái", "Giá trị"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0, 82, 40));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(220, 220, 220));

        JScrollPane scrollPane = new JScrollPane(table);
        panel_Center.add(scrollPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("DANH SÁCH KHUYẾN MÃI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(244, 242, 193));
        titleLabel.setPreferredSize(new Dimension(1000, 50));
        panel_Center.add(titleLabel, BorderLayout.NORTH);

        loadKhuyenMaiData();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePanel();
            }
        });
    }

    private void loadKhuyenMaiData() {
        loadKhuyenMaiData(khuyenMaiDAO.getAllKhuyenMai());
    }

    private void loadKhuyenMaiData(List<KhuyenMai> dsKhuyenMai) {
        tableModel.setRowCount(0);
        int stt = 1;
        for (KhuyenMai km : dsKhuyenMai) {
            tableModel.addRow(new Object[]{
                stt++,
                km.getMaKhuyenMai(),
                km.getTenKhuyenMai(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getNoiDungKhuyenMai(),
                km.isTrangThai() ? "Hoạt động" : "Ngừng",
                km.getGiaTriKhuyenMai() + "%"
            });
        }
    }

    private void themKhuyenMai() {
        JDialog dialog = new JDialog(this, "Thêm khuyến mãi", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtMaKM = new JTextField();
        JTextField txtTenKM = new JTextField();
        JDateChooser dateBatDau = new JDateChooser();
        JDateChooser dateKetThuc = new JDateChooser();
        JCheckBox chkTrangThai = new JCheckBox("Hoạt động");
        JTextField txtGiaTri = new JTextField();
        JTextField txtNoiDung = new JTextField();

        panel.add(new JLabel("Mã khuyến mãi*:"));
        panel.add(txtMaKM);
        panel.add(new JLabel("Tên khuyến mãi*:"));
        panel.add(txtTenKM);
        panel.add(new JLabel("Ngày bắt đầu*:"));
        panel.add(dateBatDau);
        panel.add(new JLabel("Ngày kết thúc*:"));
        panel.add(dateKetThuc);
        panel.add(new JLabel("Trạng thái:"));
        panel.add(chkTrangThai);
        panel.add(new JLabel("Giá trị (%)*:"));
        panel.add(txtGiaTri);
        panel.add(new JLabel("Nội dung*:"));
        panel.add(txtNoiDung);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(0, 82, 40));
        btnSave.setForeground(Color.WHITE);
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(204, 0, 0));
        btnCancel.setForeground(Color.WHITE);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        btnSave.addActionListener(e -> {
            try {
                String maKM = txtMaKM.getText().trim();
                if (maKM.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập mã khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMaKM.requestFocus();
                    return;
                }

                String tenKM = txtTenKM.getText().trim();
                if (tenKM.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtTenKM.requestFocus();
                    return;
                }

                LocalDate ngayBatDau = dateBatDau.getDate() != null ?
                        dateBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
                if (ngayBatDau == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateBatDau.requestFocus();
                    return;
                }

                LocalDate ngayKetThuc = dateKetThuc.getDate() != null ?
                        dateKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
                if (ngayKetThuc == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateKetThuc.requestFocus();
                    return;
                }

                if (ngayKetThuc.isBefore(ngayBatDau)) {
                    JOptionPane.showMessageDialog(dialog, "Ngày kết thúc phải sau ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateKetThuc.requestFocus();
                    return;
                }

                float giaTri;
                try {
                    giaTri = Float.parseFloat(txtGiaTri.getText().trim());
                    if (giaTri < 0 || giaTri > 100) {
                        JOptionPane.showMessageDialog(dialog, "Giá trị khuyến mãi phải từ 0 đến 100%!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtGiaTri.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Giá trị khuyến mãi phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtGiaTri.requestFocus();
                    return;
                }

                String noiDung = txtNoiDung.getText().trim();
                if (noiDung.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập nội dung khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtNoiDung.requestFocus();
                    return;
                }

                KhuyenMai km = new KhuyenMai(maKM, tenKM, ngayBatDau, ngayKetThuc, chkTrangThai.isSelected(), giaTri, noiDung);
                LOGGER.info("Dữ liệu gửi đi: " + km.toString());
                if (khuyenMaiDAO.addKhuyenMai(km)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm khuyến mãi thành công!");
                    loadKhuyenMaiData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Thêm khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Lỗi khi thêm khuyến mãi", ex);
                JOptionPane.showMessageDialog(dialog, "Lỗi khi thêm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void capNhatKhuyenMai() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maKM = (String) tableModel.getValueAt(selectedRow, 1);
        KhuyenMai km = khuyenMaiDAO.getKhuyenMaiByMa(maKM);

        if (km == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khuyến mãi", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Cập nhật khuyến mãi", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField txtMaKM = new JTextField(km.getMaKhuyenMai());
        txtMaKM.setEditable(false);
        JTextField txtTenKM = new JTextField(km.getTenKhuyenMai());
        JDateChooser dateBatDau = new JDateChooser();
        dateBatDau.setDate(km.getNgayBatDau() != null ?
                Date.from(km.getNgayBatDau().atStartOfDay(ZoneId.systemDefault()).toInstant()) : null);
        JDateChooser dateKetThuc = new JDateChooser();
        dateKetThuc.setDate(km.getNgayKetThuc() != null ?
                Date.from(km.getNgayKetThuc().atStartOfDay(ZoneId.systemDefault()).toInstant()) : null);
        JCheckBox chkTrangThai = new JCheckBox("Hoạt động", km.isTrangThai());
        JTextField txtGiaTri = new JTextField(String.valueOf(km.getGiaTriKhuyenMai()));
        JTextField txtNoiDung = new JTextField(km.getNoiDungKhuyenMai());

        panel.add(new JLabel("Mã khuyến mãi*:"));
        panel.add(txtMaKM);
        panel.add(new JLabel("Tên khuyến mãi*:"));
        panel.add(txtTenKM);
        panel.add(new JLabel("Ngày bắt đầu*:"));
        panel.add(dateBatDau);
        panel.add(new JLabel("Ngày kết thúc*:"));
        panel.add(dateKetThuc);
        panel.add(new JLabel("Trạng thái:"));
        panel.add(chkTrangThai);
        panel.add(new JLabel("Giá trị (%)*:"));
        panel.add(txtGiaTri);
        panel.add(new JLabel("Nội dung*:"));
        panel.add(txtNoiDung);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(0, 82, 40));
        btnSave.setForeground(Color.WHITE);
        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(204, 0, 0));
        btnCancel.setForeground(Color.WHITE);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        btnSave.addActionListener(e -> {
            try {
                String tenKM = txtTenKM.getText().trim();
                if (tenKM.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtTenKM.requestFocus();
                    return;
                }

                LocalDate ngayBatDau = dateBatDau.getDate() != null ?
                        dateBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
                if (ngayBatDau == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateBatDau.requestFocus();
                    return;
                }

                LocalDate ngayKetThuc = dateKetThuc.getDate() != null ?
                        dateKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
                if (ngayKetThuc == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateKetThuc.requestFocus();
                    return;
                }

                if (ngayKetThuc.isBefore(ngayBatDau)) {
                    JOptionPane.showMessageDialog(dialog, "Ngày kết thúc phải sau ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateKetThuc.requestFocus();
                    return;
                }

                float giaTri;
                try {
                    giaTri = Float.parseFloat(txtGiaTri.getText().trim());
                    if (giaTri < 0 || giaTri > 100) {
                        JOptionPane.showMessageDialog(dialog, "Giá trị khuyến mãi phải từ 0 đến 100%!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtGiaTri.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Giá trị khuyến mãi phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtGiaTri.requestFocus();
                    return;
                }

                String noiDung = txtNoiDung.getText().trim();
                if (noiDung.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập nội dung khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtNoiDung.requestFocus();
                    return;
                }

                km.setTenKhuyenMai(tenKM);
                km.setNgayBatDau(ngayBatDau);
                km.setNgayKetThuc(ngayKetThuc);
                km.setTrangThai(chkTrangThai.isSelected());
                km.setGiaTriKhuyenMai(giaTri);
                km.setNoiDungKhuyenMai(noiDung);

                LOGGER.info("Dữ liệu gửi đi: " + km.toString());
                if (khuyenMaiDAO.updateKhuyenMai(km)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật khuyến mãi thành công!");
                    loadKhuyenMaiData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật khuyến mãi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật khuyến mãi", ex);
                JOptionPane.showMessageDialog(dialog, "Lỗi khi cập nhật: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void resizePanel() {
        int panel_Full_Width = panel_Full.getWidth();
        int panel_Full_Height = panel_Full.getHeight();

        int panel_Header_Height = (int) (panel_Full_Height * 0.15);
        panel_Header.setPreferredSize(new Dimension(panel_Full_Width, panel_Header_Height));

        int panel_Header_Noth_H = (int) (panel_Full_Height * 0.07);
        panel_Header_Noth.setPreferredSize(new Dimension(panel_Full_Width, panel_Header_Noth_H));
        label_Logo.setBounds(10, 2, 70, 50);
        label_Icon_NV.setBounds(panel_Full_Width - 100, 1, 60, 64);
        label_NV_Header.setBounds(panel_Full_Width - 250, -2, 150, 50);
        label_Ma_NV_Header.setBounds(panel_Full_Width - 250, 20, 150, 50);

        btnMenu.setBounds(20, 20, 50, 30);
        label_Menu.setBounds(9, 0, 30, 30);
        int panel_Header_Left_Width = (int) (panel_Full_Width * 0.17);
        panel_Header_Left.setPreferredSize(new Dimension(panel_Header_Left_Width, panel_Header_Height - panel_Header_Noth_H));
        panel_Header_Right.setPreferredSize(new Dimension(panel_Full_Width - panel_Header_Left_Width, panel_Header_Height - panel_Header_Noth_H));

        panel_Menu_Width = (int) (panel_Full_Width * 0.17);
        int panel_Menu_Height = (int) (panel_Full_Height - panel_Header_Height);
        panel_Menu.setPreferredSize(new Dimension(panel_Menu_Width, panel_Menu_Height));

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

        if (text.equals("Quản lý khuyến mãi")) {
            button.setBackground(Color.WHITE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));
            Image img = originalIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            JLabel iconLabel = new JLabel(new ImageIcon(img));
            iconLabel.setBounds(iconX, iconY, iconWidth, iconHeight);
            button.add(iconLabel);

            JLabel textLabel = new JLabel(text);
            textLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
            textLabel.setForeground(new Color(0, 82, 40));
            textLabel.setBounds(textX, textY, 200, 30);
            button.add(textLabel);

            return button;
        }

        button.setBackground(new Color(244, 242, 193));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon originalIcon = new ImageIcon(getClass().getResource(iconPath));
        Image img = originalIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(img));
        iconLabel.setBounds(iconX, iconY, iconWidth, iconHeight);
        button.add(iconLabel);

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        textLabel.setForeground(new Color(0, 82, 40));
        textLabel.setBounds(textX, textY, 200, 30);
        button.add(textLabel);

        Color normal = new Color(244, 242, 193);
        Color hover = new Color(230, 220, 150);
        Color click = new Color(200, 190, 120);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hover);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(normal);
            }

            public void mousePressed(MouseEvent evt) {
                button.setBackground(click);
            }

            public void mouseReleased(MouseEvent evt) {
                if (button.getBounds().contains(evt.getPoint()))
                    button.setBackground(hover);
                else
                    button.setBackground(normal);
            }
        });

        return button;
    }

    private JButton createToolbarButton(String text, String iconText) {
        JButton button = new JButton(iconText + " " + text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(new Color(200, 220, 200));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(230, 245, 230));
                } else {
                    g2.setColor(Color.WHITE);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(0, 82, 40));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        button.setForeground(new Color(0, 82, 40));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setPreferredSize(new Dimension(170, 40));
        return button;
    }
}