package Map;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GameMap extends JFrame{
    private JPanel OutPanel;
    private JPanel ButtonPanel;
    private JPanel MapPAnel;
    private JButton StartBtn;
    private JButton SettingBtn;
    private JButton StopBtn;
    private JButton NextBtn;

    public GameMap() {
        super("GameMap");
        //addTableToPanel(this.MapPAnel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(700,400);
        this.add(OutPanel);
        this.pack();
    }


    private int rows;
    private int cols;


    //测试GameMap的main函数
    public static void main(String[] args){
        JFrame Map = new GameMap();
        Map.setVisible(true);
    }

    /*private static void addTableToPanel(JPanel panel) {
        // 定义行数和列数
        int rowCount = 10;
        int columnCount = 5;

        // 创建一个没有数据的 DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(rowCount, columnCount);

        // 创建 JTable
        JTable table = new JTable(model);

        // 自定义渲染器
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.YELLOW);
                return c;
            }
        };

        // 设置渲染器到每一列
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // 设置单元格的大小相等
        table.setRowHeight(30); // 设置行高
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(30); // 设置列宽
        }

        // 禁用表头
        table.setTableHeader(null);

        // 创建 JScrollPane 并将 JTable 添加到其中
        JScrollPane scrollPane = new JScrollPane(table);

        // 将 JScrollPane 添加到指定的 JPanel
        panel.add(scrollPane, BorderLayout.CENTER);
    }*/
}
