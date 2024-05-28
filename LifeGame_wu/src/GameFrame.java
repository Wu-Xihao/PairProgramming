import Data.DataStructure;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameFrame {
    private JFrame Frame;
    private JPanel OutPanel;
    private JPanel LabelPanel;
    private JPanel GamePanel;
    private JPanel ControlPanel;
    private JButton StartBtn;
    private JButton StopBtn;
    private JButton NextBtn;
    private JButton ReadyBtn;
    private JButton SetBtn;
    private JLabel TitleLabel;
    private GamePanel MapPanel;

    public GameFrame(){
        init();
    }
    public void init(){
        //GamePanel.setLayout(new BorderLayout());
        GamePanel.setSize(60*20,30*20);
        GamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        MapPanel=new GamePanel();
        GamePanel.add(MapPanel);
        Frame=new JFrame();
        Frame.setContentPane(OutPanel);
        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Frame.pack();
        Frame.setSize(60*20+20,30*20+50);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);

    }


    public class GamePanel extends JPanel{
        private int rows;
        private int cols;
        private int size;
        private int width;
        private int height;
        private int beginX;
        private int beginY;
        private int offSetX;
        private int offSetY;
        private int bufferImageSize;
        private Point originPoint;
        private Point lastPoint;
        private BufferedImage bufferImage;
        private HashMap<DataStructure.Cell,DataStructure.CellStatus> cells;
        public GamePanel(){
            rows=30;
            cols=60;
            size=20;
            width=cols*size;
            height=rows*size;
            beginX=0;
            beginY=0;
            originPoint=new Point(0,0);
            bufferImageSize=10000;
            offSetX = (bufferImageSize - width) / 2;
            offSetY = (bufferImageSize - height) / 2;
            bufferImage=new BufferedImage(bufferImageSize,bufferImageSize,TYPE_INT_ARGB);
//            this.setSize(width,height);

            Graphics bg=bufferImage.getGraphics();
            bg.setColor(Color.RED);
            int lineNum=bufferImageSize/size;
            bg.clearRect(0, 0, bufferImageSize, bufferImageSize);
            for(int i=0;i<=lineNum;i++){
                bg.drawLine(i * size, 0, i * size, bufferImageSize);
            }
            for (int j = 0; j <= lineNum; j++) {
                bg.drawLine(0, j * size, bufferImageSize, j * size);
            }
            repaint();

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    lastPoint = e.getPoint();
                }
            });
            this.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (lastPoint != null) {
                        int dx = e.getX() - lastPoint.x;
                        int dy = e.getY() - lastPoint.y;
                        originPoint.x=dx;
                        originPoint.y=dy;
                        repaint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    lastPoint = null;
                }
            });
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            //g.drawImage(bufferImage, originPoint.x+offSetX,originPoint.y+offSetY,width,height,null);
            System.out.println("originPoint.x: " + originPoint.x + ", originPoint.y: " + originPoint.y);
            System.out.println("offSetX: " + offSetX + ", offSetY: " + offSetY);
            g.drawImage(bufferImage, offSetX - originPoint.x, offSetY - originPoint.y, width, height, null);
        }

    }


    public static void main(String[] args){
        GameFrame frame=new GameFrame();

    }
}
