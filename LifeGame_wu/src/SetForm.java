import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Data.DataStructure.COLS;
import static Data.DataStructure.ROWS;
import static Data.DataStructure.ALIVECOLOR;

public class SetForm {
    public JFrame Frame;
    private JLabel RowLabel;
    private JLabel ColLabel;
    private JLabel ColorLabel;
    private JTextField RowText;
    private JTextField ColText;
    private JPanel OutPanel;
    private JButton OkButton;
    private JButton CancelButton;
    private JButton SelectBtn;
    private JColorChooser ColorChooser;
    public int Cols;
    public int Rows;
    public Color AliveColor;
    private boolean flag;

    private SetFormListener listener;

    public void setSetFormListener(SetFormListener listener) {
        this.listener = listener;
    }

    public SetForm(int rows,int cols,Color c){
        flag=true;
        Rows=rows;
        Cols=cols;
        AliveColor=c;
        ColorChooser=new JColorChooser();
        ColorChooser.setColor(c);
        RowText.setText(String.valueOf(rows));
        ColText.setText(String.valueOf(cols));
        Frame=new JFrame();
        Frame.setContentPane(OutPanel);
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.pack();
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
        RowText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRows();
            }
        });
        ColText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCols();
            }
        });
        SelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AliveColor = JColorChooser.showDialog(null, "选择颜色", Color.CYAN);
                if(AliveColor==null){
                    AliveColor=Color.CYAN;
                }
            }
        });
        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok();
                if (flag && listener != null) {
                    listener.onSetFormSubmit(Rows, Cols, AliveColor);
                }
            }
        });
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }
    public int changeRows(){
        String s=RowText.getText();
        int rows=30;
        try{
            rows=Integer.parseInt(s);
            if(rows<10||rows>40){
                rows=30;
                RowText.setText(String.valueOf(Rows));
                throw new Exception("超出限制");
            }
        }catch(Exception e){
            flag=false;
            JOptionPane.showMessageDialog(null, "行数输入错误！", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        return rows;
    }
    public int changeCols(){
        String s=ColText.getText();
        int cols=60;
        try{
            cols=Integer.parseInt(s);
            if(cols<20||cols>80){
                cols=60;
                ColText.setText(String.valueOf(Cols));
                throw new Exception("超出限制");
            }
        }catch(Exception e){
            flag=false;
            JOptionPane.showMessageDialog(null, "列数输入错误", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        return cols;
    }
    public Color getAliveColor(){
        return AliveColor;
    }
    public void ok(){
        flag=true;
        Rows=changeRows();
        Cols=changeCols();
        AliveColor=getAliveColor();
        if(flag) {
            ROWS=Rows;
            COLS=Cols;
            ALIVECOLOR=AliveColor;
            Frame.setVisible(false);
        }
    }
    public void cancel(){
        Frame.setVisible(false);
    }

    public interface SetFormListener {
        void onSetFormSubmit(int rows, int cols, Color aliveColor);
    }

    public JTextField getRowText(){
        return RowText;
    }
    public JTextField getColText(){
        return ColText;
    }
    public JButton getOkButton(){
        return OkButton;
    }
    public JButton getCancelButton(){
        return CancelButton;
    }
    public boolean getFlag(){
        return flag;
    }
    public void setFlag(boolean flag){
        this.flag=flag;
    }
    public int getRows(){
        return Rows;
    }
    public int getCols(){
        return Cols;
    }

    public static void main(String[] args){
        SetForm set=new SetForm(60,30,Color.CYAN);
    }
}

