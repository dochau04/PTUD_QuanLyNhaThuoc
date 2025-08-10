package Gui;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class BieuDoDemo extends JFrame {
    private JPanel panel_Center;

    public BieuDoDemo() {
        setTitle("Biểu đồ Doanh thu - Chi phí - Lợi nhuận");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel_Center = new JPanel();
        panel_Center.setLayout(null);
        panel_Center.setBackground(Color.white);
        add(panel_Center);

        // Gọi hàm vẽ biểu đồ
        veBieuDoDoanhThu();
    }

    public void veBieuDoDoanhThu() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Dữ liệu mẫu
        dataset.addValue(500, "Doanh thu", "Tháng 1");
        dataset.addValue(700, "Doanh thu", "Tháng 2");
        dataset.addValue(850, "Doanh thu", "Tháng 3");
        dataset.addValue(900, "Doanh thu", "Tháng 4");

        dataset.addValue(200, "Chi phí", "Tháng 1");
        dataset.addValue(300, "Chi phí", "Tháng 2");
        dataset.addValue(400, "Chi phí", "Tháng 3");
        dataset.addValue(450, "Chi phí", "Tháng 4");

        dataset.addValue(300, "Lợi nhuận", "Tháng 1");
        dataset.addValue(400, "Lợi nhuận", "Tháng 2");
        dataset.addValue(450, "Lợi nhuận", "Tháng 3");
        dataset.addValue(500, "Lợi nhuận", "Tháng 4");

        JFreeChart lineChart = ChartFactory.createLineChart(
            "Biểu đồ Doanh thu - Chi phí - Lợi nhuận",
            "Thời gian",
            "VNĐ (nghìn)",
            dataset
        );

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setBounds(10, 10, 760, 500);
        chartPanel.setOpaque(false);

        panel_Center.add(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BieuDoDemo().setVisible(true);
        });
    }
}
