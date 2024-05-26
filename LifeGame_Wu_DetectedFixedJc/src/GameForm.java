import MapLogic.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static Data.DataStructure.CellStatus.ALIVE;
import static Data.DataStructure.Cols;
import static Data.DataStructure.Rows;

public class GameForm{

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
    private int sizeX;
    private int sizeY;
    private Color DeadColor;
    private Color AliveColor;


    public GameForm() {
        init();
        setWindow();
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
        rows=Rows;
        cols=Cols;
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
        Frame.setContentPane(OutPanel);
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.pack();
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
    }

    public void ready(){
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
        timer.stop();

    }

    public void next(){
        map.nextMap(lastMap);
        updateMap();

    }

    public void set(){

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


    public static void main(String[] args){
        GameForm gameForm=new GameForm();

    }
}
