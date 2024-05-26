import javax.print.attribute.standard.JobName;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

import static Data.DataStructure.Cols;
import static Data.DataStructure.Rows;

public class LifeGameDlg extends JDialog {
    private JPanel ContentPanel;
    private JButton StartBtn;
    private JButton StopBtn;
    private JButton NextBtn;
    private JButton SetBtn;
    private JButton ReadyBtn;
    private JPanel ControlPanel;
    private JPanel BtnPanel;
    private JPanel MapPanel;
    private JTable MapTable;
    //private DefaultTableModel TableModel;
    private JButton MapBtn;
    private int rows;
    private int cols;
    private int size;
    private int sizeX;
    private int sizeY;
    private int MaxHeight;
    private int MinHeight;
    private int MaxWidth;
    private int MinWidth;
    private Color DeadColor;
    private Color AliveColor;


    public LifeGameDlg() {
        init();
        setWindow();
        // call onCancel() when cross is clicked
        //点击关闭按钮响应
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        //点击Esc按键响应
        ContentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //开始按钮监听事件
        StartBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        //暂停按钮监听事件
        StopBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        //初始化按钮监听事件
        ReadyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ready();
            }
        });

        //下一代按钮监听事件
        NextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        //设置按钮监听事件
        SetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set();
            }
        });
    }

    public void init(){
        MaxHeight=1200;
        MinHeight=600;
        MaxWidth=1600;
        MinWidth=800;
        size=15;
        rows=Rows;
        cols=Cols;
        sizeX=Math.max(MinWidth,size*cols);
        sizeX=Math.min(MaxWidth,sizeX);
        sizeY=Math.max(MinHeight,size*rows);
        sizeY=Math.min(MaxHeight,sizeY);
        DeadColor=Color.white;
        AliveColor=Color.CYAN;
    }
    public void setWindow(){
        this.setTitle("Conways's Game of Life");
        //将自定义的内容面板设置为对话框的内容面板
        //ContentPanel=new JPanel(new BorderLayout());
        //ContentPanel.add(MapPanel,"Center");
        //ContentPanel.add(ControlPanel,"South");
        this.setContentPane(ContentPanel);
        //设置对话框模块，对话框打开时，用户无法与其它窗口进行交互
        this.setModal(true);
        //设置Map大小
//        TableModel = (DefaultTableModel) MapTable.getModel();
//        TableModel.setRowCount(0);
//        for(int i=0;i<cols;i++){
//            TableModel.addColumn(null);
//        }
//        for(int i=0;i<rows;i++){
//            Object[] rowData=new Object[cols];
//            TableModel.addRow(rowData);
//        }
//        MapTable.setRowHeight(size);
//        for(int i=0;i<cols;i++){
//            TableColumn column=MapTable.getColumnModel().getColumn(i);
//            column.setPreferredWidth(size);
//        }

        //自适应窗口大小
        this.pack();
        //设置居中显示
        this.setLocationRelativeTo(null);
        //设置窗体可见
        this.setVisible(true);
    }
    public void ready(){

    }

    public void start(){

    }

    public void stop(){

    }

    public void next(){

    }

    public void set(){

    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        LifeGameDlg dialog = new LifeGameDlg();

        System.exit(0);
    }
}
