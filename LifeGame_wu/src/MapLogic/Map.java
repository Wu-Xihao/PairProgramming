package MapLogic;

import Data.DataStructure;

import java.util.Random;

import static Data.DataStructure.CellStatus.ALIVE;
import static Data.DataStructure.CellStatus.DEAD;
import static Data.DataStructure.Cell;

public class Map {
    private int rows;
    private int cols;
    public Cell[][] cells;
    public Map(int rows,int cols){
        this.rows=rows;
        this.cols=cols;
        cells=new Cell[rows+2][cols+2];
        for(int i=0;i<rows+2;i++){
            for(int j=0;j<cols+2;j++){
                cells[i][j]=new DataStructure.Cell(i,j, DEAD);
            }
        }
    }

    //随机生成生命地图
    public void randomMap(){
        Random random=new Random();
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=cols;j++){
                int randomInt=random.nextInt()%3;
                if(randomInt==1){
                    cells[i][j].setStatus(ALIVE);
                }else{
                    cells[i][j].setStatus(DEAD);
                }
            }
        }
    }

    //更新下一代生命地图
    public void nextMap(Map lastMap){
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=cols;j++){
                int aliveNum=lastMap.detectCell(i,j);
                if(lastMap.cells[i][j].getStatus()==ALIVE){
                    //细胞为存活状态
                    //若周围存活细胞数小于2或大于3，该细胞下一代死亡
                    if(aliveNum<2||aliveNum>3){
                        cells[i][j].setStatus(DEAD);
                    }else{
                        cells[i][j].setStatus(ALIVE);
                    }
                }
                else if(lastMap.cells[i][j].getStatus()==DEAD){
                    //细胞为死亡状态
                    //若周围存活细胞数为3，该细胞下一代存活
                    if(aliveNum==3){
                        cells[i][j].setStatus(ALIVE);
                    }else{
                        cells[i][j].setStatus(DEAD);
                    }
                }
            }
        }
    }

    //检测周围细胞存活数量
    public int detectCell(int x,int y){
        int num=0;
        if(cells[x-1][y-1].getStatus()==ALIVE){
            num++;
        }
        if(cells[x][y-1].getStatus()==ALIVE){
            num++;
        }
        if(cells[x+1][y-1].getStatus()==ALIVE){
            num++;
        }
        if(cells[x][y-1].getStatus()==ALIVE){
            num++;
        }
        if(cells[x-1][y+1].getStatus()==ALIVE){
            num++;
        }
        if(cells[x+1][y-1].getStatus()==ALIVE){
            num++;
        }
        if(cells[x+1][y-1].getStatus()==ALIVE){
            num++;
        }
        if(cells[x+1][y+1].getStatus()==ALIVE){
            num++;
        }
        return num;
    }

    //清空生命地图
    public void clearMap(){
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=cols;j++){
                cells[i][j].setStatus(DEAD);
            }
        }
    }
}
