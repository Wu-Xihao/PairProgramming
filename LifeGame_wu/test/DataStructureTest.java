import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Data.DataStructure;

class DataStructureTest {
    @Test
    void testCellConstructorAndGetters() {
        // 测试默认构造函数和获取状态
        DataStructure.Cell cell1 = new DataStructure.Cell();
        assertEquals(DataStructure.CellStatus.DEAD, cell1.getStatus());

        // 测试带参构造函数和获取行列状态
        int testRow = 5;
        int testCol = 10;
        DataStructure.Cell cell2 = new DataStructure.Cell(testRow, testCol, DataStructure.CellStatus.ALIVE);
        assertEquals(testRow, cell2.getRow());
        assertEquals(testCol, cell2.getCol());
        assertEquals(DataStructure.CellStatus.ALIVE, cell2.getStatus());
    }

    @Test
    void testSetStatus() {
        DataStructure.Cell cell = new DataStructure.Cell();
        // 初始状态应该是 DEAD
        assertEquals(DataStructure.CellStatus.DEAD, cell.getStatus());

        // 设置为 ALIVE 并验证
        cell.setStatus(DataStructure.CellStatus.ALIVE);
        assertEquals(DataStructure.CellStatus.ALIVE, cell.getStatus());

        // 再次设置为 DEAD 并验证
        cell.setStatus(DataStructure.CellStatus.DEAD);
        assertEquals(DataStructure.CellStatus.DEAD, cell.getStatus());
    }

    @Test
    void testDataStructureConstants() {
        // 验证常量值是否符合预期
        assertEquals(30, DataStructure.ROWS);
        assertEquals(60, DataStructure.COLS);
        assertEquals(java.awt.Color.CYAN, DataStructure.ALIVECOLOR);
    }

}