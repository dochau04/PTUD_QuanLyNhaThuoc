package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangNhap extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNhThuc;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DangNhap frame = new DangNhap();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DangNhap() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Tạo contentPane
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Load ảnh nền và resize theo kích thước JPanel
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/hinh_anh/dangNhap.png"));
        Image img = originalIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);

        // Tạo JLabel chứa ảnh đã resize
        JLabel backgroundLabel = new JLabel(resizedIcon);
        backgroundLabel.setBounds(0, 0, 800, 500);
        contentPane.add(backgroundLabel); // Đặt ảnh nền vào contentPane

        // Tạo JPanel để hiển thị nội dung (đè lên ảnh nền)
        JPanel panel = new JPanel();
        panel.setBounds(128, 133, 541, 242);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        contentPane.add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 82, 40));
        panel_1.setBounds(15, 0, 249, 242);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Nhà thuốc K4");
        lblNewLabel.setBounds(21, 27, 206, 49);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setForeground(Color.WHITE);
        panel_1.add(lblNewLabel);
        
        ImageIcon originallogo = new ImageIcon(getClass().getResource("/hinh_anh/logo.png"));
        Image img_logo= originallogo.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_logo = new ImageIcon(img_logo);
        JLabel lblNewLabel_1 = new JLabel(resizedIcon_logo);
        panel_1.add(lblNewLabel_1);
        
        JTextField textField = new JTextField();
        textField.setBounds(340, 40, 165, 37);
        panel.add(textField);
        textField.setColumns(10);
        
        JPanel jpuser = new JPanel();
        jpuser.setLayout(null); 
        jpuser.setBounds(294, 40, 60,37);
        jpuser.setBackground(Color.LIGHT_GRAY);
        ImageIcon originalIconuer = new ImageIcon(getClass().getResource("/hinh_anh/user.png"));
        Image img_user = originalIconuer.getImage().getScaledInstance(45, 37, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_user = new ImageIcon(img_user);
        panel.add(jpuser);
        JLabel lblIconnguoi = new JLabel(resizedIcon_user);
        lblIconnguoi.setBounds(0,0, 49, 37);
        jpuser.add(lblIconnguoi);
 
        passwordField = new JPasswordField();
        passwordField.setBounds(340, 87, 165, 37);
        panel.add(passwordField);
        
        ImageIcon originalIeye = new ImageIcon(getClass().getResource("/hinh_anh/eye_off.png"));
        Image img_eye = originalIeye.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_eye = new ImageIcon(img_eye);
        JLabel lblIeye = new JLabel(resizedIcon_eye);
        lblIeye.setBounds(125,2, 30, 30);
        passwordField.add(lblIeye);
        
        JPanel jppw = new JPanel();
        jppw.setLayout(null); 
        jppw.setBounds(294, 87, 60,37);
        jppw.setBackground(Color.LIGHT_GRAY);
        ImageIcon originalIconpw = new ImageIcon(getClass().getResource("/hinh_anh/key2.png"));
        Image img_pw = originalIconpw.getImage().getScaledInstance(40, 37, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon_pw = new ImageIcon(img_pw);
        panel.add(jppw);
        JLabel lblIpw = new JLabel(resizedIcon_pw);
        lblIpw.setBounds(5,2,37, 36);
        jppw.add(lblIpw);
        
        JButton btnNewButton = new JButton("Quên mật khẩu");
        btnNewButton.setBackground(new Color(255, 255, 193));
        btnNewButton.setForeground(new Color(0, 82, 40));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBounds(378, 134, 127, 23);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Đăng nhập");
        btnNewButton_1.addActionListener(e -> {
		    new TrangChuQuanLy_GUI();  // Mở giao diện quản lý thuốc
		    this.dispose();         // Đóng giao diện bán hàng hiện tại
		});
		
        btnNewButton_1.setBackground(new Color(0, 82, 40));
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton_1.setBounds(324, 178, 156, 37);
        panel.add(btnNewButton_1);

        // Đặt backgroundLabel ra sau cùng để không che mất panel
        contentPane.setComponentZOrder(backgroundLabel, contentPane.getComponentCount() - 1);
    }
}
