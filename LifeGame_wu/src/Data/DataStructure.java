package Data;

import java.awt.*;
import java.util.Random;

public class DataStructure {

    public static int ROWS=30;
    public static int COLS=60;
    public static Color ALIVECOLOR=Color.CYAN;
    //public static Cell Cells[][];

    //枚举类型：细胞状态（存活和死亡）
    public enum CellStatus{
        ALIVE,DEAD
    }

    //细胞类
    public static class Cell{
        private int row;
        private int col;
        private CellStatus status;
        public Cell(int row,int col,CellStatus status){
            this.row=row;
            this.col=col;
            this.status=status;
        }
        public Cell(){
            status=CellStatus.DEAD;
        }
        public int getRow(){
            return row;
        }
        public int getCol(){
            return col;
        }
        public CellStatus getStatus(){
            return status;
        }
        public void setStatus(CellStatus status){
            this.status=status;
        }
    }

}
