package Gui;

import Dao.Thuoc_DAO;
import Entity.Thuoc;
import Entity.LoaiThuoc;
import Entity.DonVi;
import java.util.List;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Thuoc_GUI extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(Thuoc_GUI.class.getName());
    
    private JPanel panel_Full, panel_Header, panel_Header_Noth, panel_Menu, panel_Center;
    private JPanel panel_Header_Left, panel_Header_Right;
    private JLabel label_Logo, label_Icon_NV, label_NV_Header, label_Ma_NV_Header, label_Menu;
    private JButton btnMenu, btnTraCuu, btnBanHang, btnThuoc, btnKhachHang, btnCaLam, btnTTNV;
    private JButton btnKhuyenMai, btnKho, btnBaoCaoThongKe, btnNhanVien, btnTroGiup, btnGioiThieu, btnDangXuat;
    private boolean isMenuVisible = true;
    private int panel_Menu_Width;
    
    private Thuoc_DAO thuocDAO;
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> comboSearch;
    private JTextField searchField;
    private boolean isSearchPlaceholder = true;

    public Thuoc_GUI() {
        super("Quản lý thuốc");
        thuocDAO = new Thuoc_DAO();
        initGUI();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
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

        JButton btnThem = createToolbarButton("THÊM", "➕");
        JButton btnSua = createToolbarButton("SỬA", "🛠");
        JButton btnXoa = createToolbarButton("XÓA", "🗑");
        JButton btnExcel = createToolbarButton("XUẤT EXCEL", "📊");
        panel_Header_Right.add(Box.createHorizontalStrut(50));
        panel_Header_Right.add(btnThem);
        panel_Header_Right.add(Box.createHorizontalStrut(5));
        panel_Header_Right.add(btnSua);
        panel_Header_Right.add(Box.createHorizontalStrut(5));
        panel_Header_Right.add(btnXoa);
        panel_Header_Right.add(Box.createHorizontalStrut(5));
        panel_Header_Right.add(btnExcel);
        panel_Header_Right.add(Box.createHorizontalStrut(270));

        String[] options = {"Tất cả", "Theo tên", "Theo mã"};
        comboSearch = new JComboBox<>(options);
        comboSearch.setFont(new Font("Times New Roman", Font.BOLD, 15));
        comboSearch.setLightWeightPopupEnabled(false);
        searchField = new JTextField("Tìm kiếm...");
        searchField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(150, 30));
        setupSearchField();

        panel_Header_Right.add(comboSearch);
        panel_Header_Right.add(searchField);
        JButton searchButton = new JButton("Tìm");
        searchButton.setBackground(new Color(244, 242, 193));
        searchButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        panel_Header_Right.add(searchButton);
        JButton refreshButton = new JButton("⟳");
        refreshButton.setBackground(new Color(244, 242, 193));
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

        String[] columns = {"STT", "Mã thuốc", "Tên thuốc", "Loại thuốc", "Đơn vị", 
                "HSD", "Giá bán", "Số lượng tồn", "Số lô"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(new Color(248, 248, 248));
                }
                if (isRowSelected(row)) {
                    c.setBackground(new Color(220, 240, 220));
                }
                return c;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i == 0 || i >= 6) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        loadThuocData();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(28);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(0, 82, 40));
        table.getTableHeader().setForeground(Color.WHITE);

        JPanel panelTableWrapper = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("DANH SÁCH THÔNG TIN THUỐC", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(244, 242, 193));
        titleLabel.setPreferredSize(new Dimension(1000, 50));
        panelTableWrapper.add(titleLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        panelTableWrapper.add(scrollPane, BorderLayout.CENTER);
        panel_Center.add(panelTableWrapper, BorderLayout.CENTER);

        // Sidebar trái
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(0, 82, 40));
        leftPanel.setForeground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(180, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        leftPanel.add(Box.createVerticalStrut(20));

        JLabel lblDanhMuc = new JLabel("Danh mục thuốc");
        lblDanhMuc.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblDanhMuc.setForeground(Color.WHITE);
        lblDanhMuc.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblDanhMuc);

        List<LoaiThuoc> dsLoaiThuoc = thuocDAO.getAllLoaiThuoc();
        DefaultComboBoxModel<LoaiThuoc> modelLoaiThuoc = new DefaultComboBoxModel<>();
        modelLoaiThuoc.addElement(new LoaiThuoc("", "Tất cả"));
        for (LoaiThuoc lt : dsLoaiThuoc) {
            modelLoaiThuoc.addElement(lt);
        }

        JComboBox<LoaiThuoc> comboDanhMuc = new JComboBox<>(modelLoaiThuoc);
        comboDanhMuc.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof LoaiThuoc) {
                    setText(((LoaiThuoc) value).getTenLoaiThuoc());
                }
                return this;
            }
        });
        comboDanhMuc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        comboDanhMuc.setFont(new Font("Times New Roman", Font.BOLD, 13));
        comboDanhMuc.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(comboDanhMuc);
        leftPanel.add(Box.createVerticalStrut(10));

        JLabel lblDonVi = new JLabel("Đơn vị tính");
        lblDonVi.setForeground(Color.WHITE);
        lblDonVi.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblDonVi.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblDonVi);

        List<DonVi> dsDonVi = thuocDAO.getAllDonVi();
        DefaultComboBoxModel<DonVi> modelDonVi = new DefaultComboBoxModel<>();
        modelDonVi.addElement(new DonVi("", "Tất cả"));
        for (DonVi dv : dsDonVi) {
            modelDonVi.addElement(dv);
        }

        JComboBox<DonVi> comboDonVi = new JComboBox<>(modelDonVi);
        comboDonVi.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DonVi) {
                    setText(((DonVi) value).getTenDonVi());
                }
                return this;
            }
        });
        comboDonVi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        comboDonVi.setFont(new Font("Times New Roman", Font.BOLD, 13));
        comboDonVi.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(comboDonVi);
        leftPanel.add(Box.createVerticalStrut(10));

        JLabel lblHSD = new JLabel("Hạn sử dụng còn");
        lblHSD.setForeground(Color.WHITE);
        lblHSD.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblHSD.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblHSD);

        JTextField txtHSD = new JTextField("Nhập số ngày...");
        txtHSD.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        txtHSD.setFont(new Font("Times New Roman", Font.BOLD, 13));
        txtHSD.setAlignmentX(Component.CENTER_ALIGNMENT);
        setupHSDField(txtHSD);
        leftPanel.add(txtHSD);

        JButton btnApplyFilter = new JButton("Áp dụng");
        btnApplyFilter.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnApplyFilter.setMaximumSize(new Dimension(100, 25));
        btnApplyFilter.setBackground(new Color(244, 242, 193));
        btnApplyFilter.setForeground(new Color(0, 82, 40));
        btnApplyFilter.setFont(new Font("Times New Roman", Font.BOLD, 14));
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(btnApplyFilter);

        JButton btnResetFilter = new JButton("Reset");
        btnResetFilter.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnResetFilter.setMaximumSize(new Dimension(100, 25));
        btnResetFilter.setBackground(new Color(244, 242, 193));
        btnResetFilter.setForeground(new Color(0, 82, 40));
        btnResetFilter.setFont(new Font("Times New Roman", Font.BOLD, 14));
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(btnResetFilter);

        panel_Center.add(leftPanel, BorderLayout.WEST);

        // Sự kiện
        btnResetFilter.addActionListener(e -> {
            comboDanhMuc.setSelectedIndex(0);
            comboDonVi.setSelectedIndex(0);
            txtHSD.setText("Nhập số ngày...");
            txtHSD.setForeground(Color.GRAY);
            loadThuocData();
        });

        searchButton.addActionListener(e -> {
            String condition = (String) comboSearch.getSelectedItem();
            String keyword = isSearchPlaceholder ? "" : searchField.getText();
            searchThuoc(condition, keyword);
        });

        refreshButton.addActionListener(e -> {
            loadThuocData();
            searchField.setText("Tìm kiếm...");
            searchField.setForeground(Color.GRAY);
            isSearchPlaceholder = true;
            comboSearch.setSelectedIndex(0);
        });

        btnThem.addActionListener(e -> themThuoc());
        btnSua.addActionListener(e -> {
            try {
                suaThuoc();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Lỗi khi sửa thuốc", ex);
                JOptionPane.showMessageDialog(this, "Lỗi khi sửa thuốc: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnXoa.addActionListener(e -> xoaThuoc());
        btnExcel.addActionListener(e -> xuatExcel());

        btnApplyFilter.addActionListener(e -> {
            LoaiThuoc selectedLoai = (LoaiThuoc) comboDanhMuc.getSelectedItem();
            DonVi selectedDonVi = (DonVi) comboDonVi.getSelectedItem();
            String hsdText = txtHSD.getText().equals("Nhập số ngày...") ? "" : txtHSD.getText();
            filterThuoc(selectedLoai, selectedDonVi, hsdText);
        });

        comboDanhMuc.addActionListener(e -> {
            if (comboDanhMuc.getSelectedIndex() > 0) {
                LoaiThuoc selectedLoai = (LoaiThuoc) comboDanhMuc.getSelectedItem();
                DonVi selectedDonVi = (DonVi) comboDonVi.getSelectedItem();
                String hsdText = txtHSD.getText().equals("Nhập số ngày...") ? "" : txtHSD.getText();
                filterThuoc(selectedLoai, selectedDonVi, hsdText);
            }
        });

        comboDonVi.addActionListener(e -> {
            if (comboDonVi.getSelectedIndex() > 0) {
                LoaiThuoc selectedLoai = (LoaiThuoc) comboDanhMuc.getSelectedItem();
                DonVi selectedDonVi = (DonVi) comboDonVi.getSelectedItem();
                String hsdText = txtHSD.getText().equals("Nhập số ngày...") ? "" : txtHSD.getText();
                filterThuoc(selectedLoai, selectedDonVi, hsdText);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePanel();
            }
        });
    }

    private void setupSearchField() {
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Tìm kiếm...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                    isSearchPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("Tìm kiếm...");
                    isSearchPlaceholder = true;
                }
            }
        });

        searchField.addActionListener(e -> {
            String condition = (String) comboSearch.getSelectedItem();
            String keyword = isSearchPlaceholder ? "" : searchField.getText();
            searchThuoc(condition, keyword);
        });
    }

    private void setupHSDField(JTextField field) {
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals("Nhập số ngày...")) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText("Nhập số ngày...");
                }
            }
        });
    }

    private void addRealTimeValidation(JTextField field, String fieldName, boolean isInteger) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            private void validate() {
                String text = field.getText().trim();
                if (!text.isEmpty()) {
                    try {
                        if (isInteger) {
                            int value = Integer.parseInt(text);
                            if (value < 0) {
                                field.setBackground(Color.PINK);
                                field.setToolTipText(fieldName + " không được âm");
                            } else {
                                field.setBackground(Color.WHITE);
                                field.setToolTipText(null);
                            }
                        } else {
                            double value = Double.parseDouble(text);
                            if (value <= 0) {
                                field.setBackground(Color.PINK);
                                field.setToolTipText(fieldName + " phải lớn hơn 0");
                            } else {
                                field.setBackground(Color.WHITE);
                                field.setToolTipText(null);
                            }
                        }
                    } catch (NumberFormatException ex) {
                        field.setBackground(Color.PINK);
                        field.setToolTipText(fieldName + " phải là số hợp lệ");
                    }
                } else {
                    field.setBackground(Color.PINK);
                    field.setToolTipText(fieldName + " không được để trống");
                }
            }

            public void insertUpdate(DocumentEvent e) { validate(); }
            public void removeUpdate(DocumentEvent e) { validate(); }
            public void changedUpdate(DocumentEvent e) { validate(); }
        });
    }

    private void loadThuocData() {
        try {
            tableModel.setRowCount(0);
            List<Thuoc> dsThuoc = thuocDAO.getAllThuoc();
            int stt = 1;
            for (Thuoc thuoc : dsThuoc) {
                Object[] rowData = {
                    stt++,
                    thuoc.getMaThuoc(),
                    thuoc.getTenThuoc(),
                    thuoc.getLoaiThuoc().getTenLoaiThuoc(),
                    thuoc.getDonVi().getTenDonVi(),
                    thuoc.getHanSuDung() != null ? thuoc.getHanSuDung().toString() : "",
                    String.format("%,.0f", thuoc.getGiaBan()),
                    thuoc.getSoLuongTon(),
                    thuoc.getSoLo() != null ? thuoc.getSoLo() : "" // Kiểm tra null
                };
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tải dữ liệu thuốc", e);
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchThuoc(String condition, String keyword) {
        if (keyword.equals("Tìm kiếm...")) {
            keyword = "";
        }
        tableModel.setRowCount(0);
        List<Thuoc> dsThuoc = thuocDAO.searchThuoc(condition, keyword);
        int stt = 1;
        for (Thuoc thuoc : dsThuoc) {
            Object[] rowData = {
                stt++,
                thuoc.getMaThuoc(),
                thuoc.getTenThuoc(),
                thuoc.getLoaiThuoc().getTenLoaiThuoc(),
                thuoc.getDonVi().getTenDonVi(),
                thuoc.getHanSuDung() != null ? thuoc.getHanSuDung().toString() : "",
                String.format("%,.0f", thuoc.getGiaBan()),
                thuoc.getSoLuongTon(),
                thuoc.getSoLo()
            };
            tableModel.addRow(rowData);
        }
    }

    private void filterThuoc(LoaiThuoc loaiThuoc, DonVi donVi, String soNgayHSD) {
        tableModel.setRowCount(0);
        LocalDate minHSD = null;
        try {
            if (soNgayHSD != null && !soNgayHSD.isEmpty() && !soNgayHSD.equals("Nhập số ngày...")) {
                int days = Integer.parseInt(soNgayHSD);
                if (days < 0) {
                    JOptionPane.showMessageDialog(this, "Số ngày HSD không được âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                minHSD = LocalDate.now().plusDays(days);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số ngày HSD không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Thuoc> dsThuoc = thuocDAO.filterThuoc(
            loaiThuoc.getMaLoaiThuoc().isEmpty() ? null : loaiThuoc.getMaLoaiThuoc(),
            donVi.getMaDonVi().isEmpty() ? null : donVi.getMaDonVi(),
            minHSD
        );

        int stt = 1;
        for (Thuoc thuoc : dsThuoc) {
            Object[] rowData = {
                stt++,
                thuoc.getMaThuoc(),
                thuoc.getTenThuoc(),
                thuoc.getLoaiThuoc().getTenLoaiThuoc(),
                thuoc.getDonVi().getTenDonVi(),
                thuoc.getHanSuDung() != null ? thuoc.getHanSuDung().toString() : "",
                String.format("%,.0f", thuoc.getGiaBan()),
                thuoc.getSoLuongTon(),
                thuoc.getSoLo()
            };
            tableModel.addRow(rowData);
        }
    }

    private void themThuoc() {
        JDialog dialog = new JDialog(this, "Thêm thuốc mới", true);
        dialog.setSize(500, 550); // Giảm chiều cao do bỏ trường giá nhập
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(11, 2, 10, 10)); // Giảm số hàng
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<LoaiThuoc> dsLoaiThuoc = thuocDAO.getAllLoaiThuoc();
        JComboBox<LoaiThuoc> cboLoaiThuoc = new JComboBox<>(dsLoaiThuoc.toArray(new LoaiThuoc[0]));
        cboLoaiThuoc.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof LoaiThuoc) {
                    setText(((LoaiThuoc) value).getTenLoaiThuoc());
                }
                return this;
            }
        });

        String maThuoc = generateNewMaThuoc(cboLoaiThuoc.getItemAt(0).getTenLoaiThuoc());
        JTextField txtMaThuoc = new JTextField(maThuoc);
        txtMaThuoc.setEditable(false);

        cboLoaiThuoc.addActionListener(e -> {
            LoaiThuoc selected = (LoaiThuoc) cboLoaiThuoc.getSelectedItem();
            String newMaThuoc = generateNewMaThuoc(selected.getTenLoaiThuoc());
            txtMaThuoc.setText(newMaThuoc);
        });

        JTextField txtTenThuoc = new JTextField();
        List<DonVi> dsDonVi = thuocDAO.getAllDonVi();
        JComboBox<DonVi> cboDonVi = new JComboBox<>(dsDonVi.toArray(new DonVi[0]));
        cboDonVi.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DonVi) {
                    setText(((DonVi) value).getTenDonVi());
                }
                return this;
            }
        });

        JTextField txtGiaBan = new JTextField();
        JTextField txtSoLuong = new JTextField();
        JTextField txtSoLo = new JTextField();
        JTextField txtNhaSanXuat = new JTextField();
        JDateChooser dateNSX = new JDateChooser();
        JDateChooser dateHSD = new JDateChooser();

        panel.add(new JLabel("Mã thuốc:"));
        panel.add(txtMaThuoc);
        panel.add(new JLabel("Tên thuốc:"));
        panel.add(txtTenThuoc);
        panel.add(new JLabel("Loại thuốc:"));
        panel.add(cboLoaiThuoc);
        panel.add(new JLabel("Đơn vị:"));
        panel.add(cboDonVi);
        panel.add(new JLabel("Giá bán:"));
        panel.add(txtGiaBan);
        panel.add(new JLabel("Số lượng tồn:"));
        panel.add(txtSoLuong);
        panel.add(new JLabel("Số lô:"));
        panel.add(txtSoLo);
        panel.add(new JLabel("Nhà sản xuất:"));
        panel.add(txtNhaSanXuat);
        panel.add(new JLabel("Ngày sản xuất:"));
        panel.add(dateNSX);
        panel.add(new JLabel("Hạn sử dụng:"));
        panel.add(dateHSD);

        addRealTimeValidation(txtGiaBan, "Giá bán", false);
        addRealTimeValidation(txtSoLuong, "Số lượng tồn", true);

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(e -> {
            try {
                String tenThuoc = txtTenThuoc.getText().trim();
                LoaiThuoc loaiThuoc = (LoaiThuoc) cboLoaiThuoc.getSelectedItem();
                DonVi donVi = (DonVi) cboDonVi.getSelectedItem();
                String giaBanStr = txtGiaBan.getText().trim();
                String soLuongStr = txtSoLuong.getText().trim();
                String soLo = txtSoLo.getText().trim();
                String nhaSanXuat = txtNhaSanXuat.getText().trim();
                LocalDate nsx = dateNSX.getDate() != null ?
                        dateNSX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
                LocalDate hsd = dateHSD.getDate() != null ?
                        dateHSD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

                if (tenThuoc.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên thuốc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtTenThuoc.requestFocus();
                    return;
                }
                if (nhaSanXuat.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập nhà sản xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtNhaSanXuat.requestFocus();
                    return;
                }
                if (giaBanStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập giá bán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtGiaBan.requestFocus();
                    return;
                }
                double giaBan;
                try {
                    giaBan = Double.parseDouble(giaBanStr);
                    if (giaBan <= 0) {
                        JOptionPane.showMessageDialog(dialog, "Giá bán phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtGiaBan.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Giá bán phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtGiaBan.requestFocus();
                    return;
                }
                if (soLuongStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtSoLuong.requestFocus();
                    return;
                }
                int soLuong;
                try {
                    soLuong = Integer.parseInt(soLuongStr);
                    if (soLuong < 0) {
                        JOptionPane.showMessageDialog(dialog, "Số lượng không được âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtSoLuong.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Số lượng phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtSoLuong.requestFocus();
                    return;
                }
                if (soLo.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số lô!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtSoLo.requestFocus();
                    return;
                }
                if (nsx == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày sản xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateNSX.requestFocus();
                    return;
                }
                if (hsd == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn hạn sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateHSD.requestFocus();
                    return;
                }
                if (hsd.isBefore(nsx)) {
                    JOptionPane.showMessageDialog(dialog, "Hạn sử dụng phải sau ngày sản xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateHSD.requestFocus();
                    return;
                }

                Thuoc thuoc = new Thuoc(txtMaThuoc.getText(), tenThuoc, loaiThuoc, donVi, hsd,
                        giaBan, soLuong, soLo, nsx, nhaSanXuat);

                if (thuocDAO.addThuoc(thuoc)) {
                    JOptionPane.showMessageDialog(dialog, "Thêm thuốc thành công!");
                    loadThuocData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Thêm thuốc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Lỗi khi thêm thuốc", ex);
                JOptionPane.showMessageDialog(dialog, "Lỗi xử lý dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(btnSave, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void suaThuoc() throws SQLException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maThuoc = (String) tableModel.getValueAt(selectedRow, 1);
        String soLoObj = (String) tableModel.getValueAt(selectedRow, 8); // Lấy số lô từ cột 8
        String soLo = soLoObj != null ? soLoObj.toString() : "";
        if (soLo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lô không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Thuoc thuoc = thuocDAO.getThuocByMaAndSoLo(maThuoc, soLo);

        if (thuoc == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thuốc", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Sửa thông tin thuốc", true);
        dialog.setSize(500, 550); // Giảm chiều cao do bỏ giá nhập
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(11, 2, 10, 10)); // Giảm số hàng
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<LoaiThuoc> dsLoaiThuoc = thuocDAO.getAllLoaiThuoc();
        List<DonVi> dsDonVi = thuocDAO.getAllDonVi();

        JTextField txtMaThuoc = new JTextField(thuoc.getMaThuoc());
        txtMaThuoc.setEditable(false);
        JTextField txtTenThuoc = new JTextField(thuoc.getTenThuoc());
        JTextField txtNhaSanXuat = new JTextField(thuoc.getNhaSanXuat());

        JComboBox<LoaiThuoc> cboLoaiThuoc = new JComboBox<>();
        for (LoaiThuoc lt : dsLoaiThuoc) {
            cboLoaiThuoc.addItem(lt);
        }
        cboLoaiThuoc.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof LoaiThuoc) {
                    setText(((LoaiThuoc) value).getTenLoaiThuoc());
                }
                return this;
            }
        });
        cboLoaiThuoc.setSelectedItem(thuoc.getLoaiThuoc());

        JComboBox<DonVi> cboDonVi = new JComboBox<>();
        for (DonVi dv : dsDonVi) {
            cboDonVi.addItem(dv);
        }
        cboDonVi.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DonVi) {
                    setText(((DonVi) value).getTenDonVi());
                }
                return this;
            }
        });
        cboDonVi.setSelectedItem(thuoc.getDonVi());

        JTextField txtGiaBan = new JTextField(String.format("%.0f", thuoc.getGiaBan()));
        JTextField txtSoLuong = new JTextField(String.valueOf(thuoc.getSoLuongTon()));
        JTextField txtSoLo = new JTextField(thuoc.getSoLo());

        JDateChooser dateNSX = new JDateChooser();
        dateNSX.setDate(Date.from(thuoc.getNgaySanXuat().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        JDateChooser dateHSD = new JDateChooser();
        dateHSD.setDate(Date.from(thuoc.getHanSuDung().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        panel.add(new JLabel("Mã thuốc:"));
        panel.add(txtMaThuoc);
        panel.add(new JLabel("Tên thuốc*:"));
        panel.add(txtTenThuoc);
        panel.add(new JLabel("Loại thuốc:"));
        panel.add(cboLoaiThuoc);
        panel.add(new JLabel("Đơn vị:"));
        panel.add(cboDonVi);
        panel.add(new JLabel("Giá bán*:"));
        panel.add(txtGiaBan);
        panel.add(new JLabel("Số lượng tồn*:"));
        panel.add(txtSoLuong);
        panel.add(new JLabel("Số lô:"));
        panel.add(txtSoLo);
        panel.add(new JLabel("Nhà sản xuất*:"));
        panel.add(txtNhaSanXuat);
        panel.add(new JLabel("Ngày sản xuất:"));
        panel.add(dateNSX);
        panel.add(new JLabel("Hạn sử dụng:"));
        panel.add(dateHSD);

        addRealTimeValidation(txtGiaBan, "Giá bán", false);
        addRealTimeValidation(txtSoLuong, "Số lượng tồn", true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(0, 82, 40));
        btnSave.setForeground(Color.WHITE);
        btnSave.setPreferredSize(new Dimension(100, 30));

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(204, 0, 0));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        btnSave.addActionListener(e -> {
            try {
                // Kiểm tra mã thuốc không bị thay đổi
                if (!txtMaThuoc.getText().equals(thuoc.getMaThuoc())) {
                    JOptionPane.showMessageDialog(dialog, "Mã thuốc không được thay đổi!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMaThuoc.requestFocus();
                    return;
                }

                String tenThuoc = txtTenThuoc.getText().trim();
                if (tenThuoc.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên thuốc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtTenThuoc.requestFocus();
                    return;
                }

                String nhaSanXuat = txtNhaSanXuat.getText().trim();
                if (nhaSanXuat.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập nhà sản xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtNhaSanXuat.requestFocus();
                    return;
                }

                double giaBan;
                try {
                    giaBan = Double.parseDouble(txtGiaBan.getText().trim());
                    if (giaBan <= 0) {
                        JOptionPane.showMessageDialog(dialog, "Giá bán phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtGiaBan.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Giá bán phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtGiaBan.requestFocus();
                    return;
                }

                int soLuong;
                try {
                    soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                    if (soLuong < 0) {
                        JOptionPane.showMessageDialog(dialog, "Số lượng tồn không được âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        txtSoLuong.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Số lượng tồn phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtSoLuong.requestFocus();
                    return;
                }

                String soLo1 = txtSoLo.getText().trim();
                if (soLo.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số lô!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtSoLo.requestFocus();
                    return;
                }

                LocalDate nsx = dateNSX.getDate() != null ?
                        dateNSX.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
                if (nsx == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn ngày sản xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateNSX.requestFocus();
                    return;
                }

                LocalDate hsd = dateHSD.getDate() != null ?
                        dateHSD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
                if (hsd == null) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn hạn sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateHSD.requestFocus();
                    return;
                }

                if (hsd.isBefore(nsx)) {
                    JOptionPane.showMessageDialog(dialog, "Hạn sử dụng phải sau ngày sản xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateHSD.requestFocus();
                    return;
                }

                thuoc.setTenThuoc(tenThuoc);
                thuoc.setLoaiThuoc((LoaiThuoc) cboLoaiThuoc.getSelectedItem());
                thuoc.setDonVi((DonVi) cboDonVi.getSelectedItem());
                thuoc.setGiaBan(giaBan);
                thuoc.setSoLuongTon(soLuong);
                thuoc.setSoLo(soLo1);
                thuoc.setNhaSanXuat(nhaSanXuat);
                thuoc.setNgaySanXuat(nsx);
                thuoc.setHanSuDung(hsd);

                LOGGER.info("Dữ liệu gửi đi: " + thuoc.toString());
                if (thuocDAO.updateThuoc(thuoc)) {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thuốc thành công!");
                    loadThuocData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Cập nhật thuốc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật thuốc", ex);
                JOptionPane.showMessageDialog(dialog, "Lỗi khi cập nhật: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancel.addActionListener(e -> dialog.dispose());
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void xoaThuoc() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maThuoc = (String) tableModel.getValueAt(selectedRow, 1);
        String soLo = (String) tableModel.getValueAt(selectedRow, 8); // Lấy số lô từ cột 8
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa thuốc này?", "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (thuocDAO.deleteThuoc(maThuoc, soLo)) {
                JOptionPane.showMessageDialog(this, "Xóa thuốc thành công!");
                loadThuocData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thuốc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xuatExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
        fileChooser.setSelectedFile(new File("DanhSachThuoc.xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Danh sách thuốc");

                CellStyle headerStyle = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setColor(IndexedColors.WHITE.getIndex());
                headerStyle.setFont(headerFont);
                headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tableModel.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        Object value = tableModel.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        if (value != null) {
                            if (value instanceof Number) {
                                cell.setCellValue(((Number) value).doubleValue());
                            } else {
                                cell.setCellValue(value.toString());
                            }
                        }
                    }
                }

                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
                    workbook.write(outputStream);
                    JOptionPane.showMessageDialog(this, 
                        "Xuất Excel thành công!\nFile được lưu tại: " + fileToSave.getAbsolutePath(), 
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Lỗi khi xuất Excel", ex);
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi xuất Excel: " + ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String generateNewMaThuoc(String tenLoaiThuoc) {
        String prefix = "MT";
        if (tenLoaiThuoc != null && !tenLoaiThuoc.trim().isEmpty()) {
            String[] words = tenLoaiThuoc.trim().split(" ");
            if (words.length >= 1 && !words[0].isEmpty()) {
                String firstWord = words[0];
                prefix += firstWord.length() >= 2 ? firstWord.substring(0, 2).toUpperCase() : firstWord.toUpperCase();
            } else {
                prefix += "TH";
            }
        } else {
            prefix += "TH";
        }

        String lastMaThuoc = thuocDAO.getLastMaThuocByPrefix(prefix);
        if (lastMaThuoc == null) {
            return prefix + "0001";
        }

        try {
            String numberStr = lastMaThuoc.substring(prefix.length());
            int number = Integer.parseInt(numberStr);
            number++;
            return String.format("%s%04d", prefix, number);
        } catch (NumberFormatException e) {
            return prefix + "0001";
        }
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
        Timer timer = new Timer(5, null);
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

        if (text.equals("Quản lý thuốc")) {
            button.setBackground(Color.WHITE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            button.setBackground(new Color(244, 242, 193));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
        }

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

    private JButton createToolbarButton(String text, String iconText) {
        JButton button = new JButton(iconText + " " + text);
        button.setForeground(new Color(0, 82, 40));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 40));
        return button;
    }

    public static void main(String[] args) {
        new Thuoc_GUI();
    }
}