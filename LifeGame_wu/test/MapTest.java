import MapLogic.Map;
import org.junit.jupiter.api.Test;

import static Data.DataStructure.CellStatus.ALIVE;
import static Data.DataStructure.CellStatus.DEAD;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;



class MapTest {

    @Test
    void testConstructor() {
        int testRows = 10;
        int testCols = 20;
        Map map = new Map(testRows, testCols);

        // 验证行列数是否正确
        assertEquals(testRows, map.getRows());
        assertEquals(testCols, map.getCols());

        // 验证 cells 数组是否初始化，且每个元素都是 DEAD 状态
        assertNotNull(map.cells);
        for (int i = 0; i < testRows + 2; i++) {
            for (int j = 0; j < testCols + 2; j++) {
                assertNotNull(map.cells[i][j]);
                assertEquals(DEAD, map.cells[i][j].getStatus());
            }
        }
    }

    @Test
    void testRandomMap() {
        int testRows = 5;
        int testCols = 5;
        Map map = new Map(testRows, testCols);
        map.randomMap();

        // 验证是否有活细胞和死细胞
        boolean hasAlive = false;
        boolean hasDead = false;
        for (int i = 1; i <= testRows; i++) {
            for (int j = 1; j <= testCols; j++) {
                if (map.cells[i][j].getStatus() == ALIVE) {
                    hasAlive = true;
                } else if (map.cells[i][j].getStatus() == DEAD) {
                    hasDead = true;
                }
            }
        }
        assertTrue(hasAlive);
        assertTrue(hasDead);
    }

    @Test
    void testNextMap() {
        // 创建一个测试地图，并手动设置初始状态
        Map lastMap = new Map(3, 3);
        lastMap.cells[1][1].setStatus(ALIVE);
        lastMap.cells[1][2].setStatus(ALIVE);
        lastMap.cells[2][1].setStatus(ALIVE);

        // 创建一个新的地图，用于存储下一代状态
        Map newMap = new Map(3, 3);
        newMap.nextMap(lastMap);

        // 验证下一代地图的状态是否符合预期
        assertEquals(ALIVE, newMap.cells[1][1].getStatus());
        assertEquals(ALIVE, newMap.cells[1][2].getStatus());
        assertEquals(ALIVE, newMap.cells[2][1].getStatus());
        assertEquals(ALIVE, newMap.cells[2][2].getStatus());
    }

    @Test
    void testDetectCell() {
        Map map = new Map(3, 3);
        // 设置周围细胞状态
        map.cells[0][1].setStatus(ALIVE);
        map.cells[1][0].setStatus(ALIVE);
        map.cells[2][1].setStatus(ALIVE);
        // 测试中间细胞周围存活细胞数量
        int aliveNum = map.detectCell(1, 1);
        assertEquals(3, aliveNum);
    }

    @Test
    void testClearMap() {
        Map map = new Map(5, 5);
        map.randomMap(); // 随机生成地图，确保有活细胞

        map.clearMap();

        // 验证所有细胞状态是否都为 DEAD
        for (int i = 1; i <= map.getRows(); i++) {
            for (int j = 1; j <= map.getCols(); j++) {
                assertEquals(DEAD, map.cells[i][j].getStatus());
            }
        }
    }
}
