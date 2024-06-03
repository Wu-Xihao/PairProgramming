import MapLogic.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static Data.DataStructure.CellStatus.ALIVE;
import static Data.DataStructure.CellStatus.DEAD;
import static Data.DataStructure.COLS;
import static Data.DataStructure.ROWS;
import static Data.DataStructure.ALIVECOLOR;

public class GameForm implements SetForm.SetFormListener{

    private JFrame Frame;
    private JPanel OutPanel;
    private JPanel TopPanel;
    private JPanel CenterPanel;
    private JPanel BottomPanel;
    private JButton ReadyBtn;
    private JButton StartBtn;
    private JButton StopBtn;
    private JButton NextBtn;
    private JButton SetBtn;
    private JLabel GameLabel;
    private JButton[][] MapBtns;
    private Timer timer;

    private Map map;
    private Map lastMap;
    private int rows;
    private int cols;
    private int size;
    private Color DeadColor;
    private Color AliveColor;
    private SetForm set;


    public GameForm() {
        init();
        setWindow();
        mapBtnsListener();
        timer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        ReadyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ready();
            }
        });
        StartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        StopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        NextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });
        SetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set();
            }
        });


    }
    public void init(){
        size=20;
        rows=ROWS;
        cols=COLS;
        DeadColor=Color.white;
        AliveColor=Color.CYAN;
        map=new Map(rows,cols);
        lastMap=new Map(rows,cols);
    }
    public void setWindow(){
        Frame=new JFrame();
        CenterPanel.setLayout(new GridLayout(rows,cols));
        MapBtns=new JButton[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                MapBtns[i][j]=new JButton("");
                MapBtns[i][j].setPreferredSize(new Dimension(size, size));
                MapBtns[i][j].setBackground(DeadColor);
                CenterPanel.add(MapBtns[i][j]);
            }
        }
        Frame.setTitle("生命游戏");
        Frame.setContentPane(OutPanel);
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.pack();
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
    }

    public void ready(){
        timer.stop();
        if(!Objects.equals(StartBtn.getText(), "Start")){
            StartBtn.setText("Start");
        }
        if(!Objects.equals(StopBtn.getText(), "Stop")){
            StopBtn.setText("Stop");
        }
        map.randomMap();
        updateMap();
    }

    public void start(){
        if(Objects.equals(StartBtn.getText(), "Start")){
            timer.start();
            StartBtn.setText("End");
        }else{
            timer.stop();
            StartBtn.setText("Start");
        }
    }

    public void stop(){
        if(Objects.equals(StopBtn.getText(), "Stop")){
            timer.stop();
            StopBtn.setText("Continue");
        }else{
            timer.start();
            StopBtn.setText("Stop");
        }

    }

    public void next(){
        map.nextMap(lastMap);
        updateMap();

    }

    public void set(){
        showSetForm();
    }

    public void clickMapBtn(int i,int j){
        if(map.cells[i+1][j+1].getStatus()==DEAD){
            map.cells[i+1][j+1].setStatus(ALIVE);
            lastMap.cells[i+1][j+1].setStatus(ALIVE);
            MapBtns[i][j].setBackground(AliveColor);
        }
    }

    public void updateMap(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(map.cells[i+1][j+1].getStatus()==ALIVE){
                    MapBtns[i][j].setBackground(AliveColor);
                }else{
                    MapBtns[i][j].setBackground(DeadColor);
                }
            }
        }
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=cols;j++){
                lastMap.cells[i][j].setStatus(map.cells[i][j].getStatus());
            }
        }
    }
    private void showSetForm() {
        SetForm setForm = new SetForm(rows, cols, AliveColor);
        setForm.setSetFormListener(this); // 将父窗体自身注册为监听器
        setForm.Frame.setVisible(true);
    }
    public void mapBtnsListener(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                int finalI = i;
                int finalJ = j;
                MapBtns[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clickMapBtn(finalI, finalJ);
                    }
                });
            }
        }
    }

    @Override
    public void onSetFormSubmit(int rows, int cols, Color aliveColor) {
        // 处理接收到的 rows, cols, aliveColor 值，例如更新父窗体中的数据和界面
        this.rows = rows;
        this.cols = cols;
        this.AliveColor = aliveColor;
        // ... 更新界面等操作 ...
        // 移除所有按钮
        CenterPanel.removeAll();
        // 重新设置布局
        CenterPanel.setLayout(new GridLayout(rows, cols));
        map=new Map(rows,cols);
        lastMap=new Map(rows,cols);
        MapBtns=new JButton[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                MapBtns[i][j]=new JButton("");
                MapBtns[i][j].setPreferredSize(new Dimension(size, size));
                MapBtns[i][j].setBackground(DeadColor);
                CenterPanel.add(MapBtns[i][j]);
            }
        }
        mapBtnsListener();
        CenterPanel.repaint();
        updateMap();
        Frame.pack();
        Frame.setLocationRelativeTo(null);
    }

    public int getRows(){
        return rows;
    }
    public int getCols(){
        return cols;
    }
    public Color getAliveColor(){
        return AliveColor;
    }
    public Color getDeadColor(){
        return DeadColor;
    }
    public JButton[][] getMapBtns(){
        return MapBtns;
    }
    public Map getMap(){
        return map;
    }
    public Map getLastMap(){
        return lastMap;
    }
    public int getSize(){
        return size;
    }
    public JFrame getFrame(){
        return Frame;
    }
    public JButton getReadyBtn(){
        return ReadyBtn;
    }
    public JButton getStartBtn(){
        return StartBtn;
    }
    public Timer getTimer() {
        return timer;
    }
    public JButton getStopBtn() {
        return StopBtn;
    }
    public JButton getNextBtn(){
        return NextBtn;
    }

    public static void main(String[] args){
        GameForm gameForm=new GameForm();
    }
}
