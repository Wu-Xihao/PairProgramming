import Data.DataStructure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import static org.junit.jupiter.api.Assertions.*;

class GameFormTest {

    private GameForm gameForm;

    @BeforeEach
    void setUp() {
        // 在每个测试方法执行前创建新的 GameForm 实例
        gameForm = new GameForm();
    }

    @Test
    void testInit() {
        // 验证初始化后的变量值
        assertEquals(20, gameForm.getSize());
        assertEquals(DataStructure.ROWS, gameForm.getRows());
        assertEquals(DataStructure.COLS, gameForm.getCols());
        assertEquals(Color.white, gameForm.getDeadColor());
        assertEquals(Color.CYAN, gameForm.getAliveColor());
        assertNotNull(gameForm.getMap());
        assertNotNull(gameForm.getLastMap());
    }

    @Test
    void testSetWindow() {
        // 验证窗口标题是否正确
        assertEquals("生命游戏", gameForm.getFrame().getTitle());

        // 验证按钮数组是否初始化，且每个按钮的大小和背景颜色是否正确
        for (int i = 0; i < gameForm.getRows(); i++) {
            for (int j = 0; j < gameForm.getCols(); j++) {
                JButton button = gameForm.getMapBtns()[i][j];
                assertNotNull(button);
                assertEquals(20, button.getPreferredSize().width);
                assertEquals(20, button.getPreferredSize().height);
                assertEquals(Color.white, button.getBackground());
            }
        }
    }

    @Test
    void testReady() {
        // 模拟点击 "Ready" 按钮
        gameForm.getReadyBtn().doClick();

        // 验证地图是否随机生成，且按钮背景颜色已更新
        boolean hasAlive = false;
        boolean hasDead = false;
        for (int i = 0; i < gameForm.getRows(); i++) {
            for (int j = 0; j < gameForm.getCols(); j++) {
                Color color = gameForm.getMapBtns()[i][j].getBackground();
                if (color.equals(gameForm.getAliveColor())) {
                    hasAlive = true;
                } else if (color.equals(gameForm.getDeadColor())) {
                    hasDead = true;
                }
            }
        }
        assertTrue(hasAlive);
        assertTrue(hasDead);
    }

    @Test
    void testStartAndStop() {
        // 模拟点击 "Start" 按钮
        gameForm.getStartBtn().doClick();
        assertTrue(gameForm.getTimer().isRunning());
        assertEquals("End", gameForm.getStartBtn().getText());

        // 模拟点击 "Stop" 按钮
        gameForm.getStopBtn().doClick();
        assertFalse(gameForm.getTimer().isRunning());
        assertEquals("Continue", gameForm.getStopBtn().getText());

        // 再次点击 "Start" 按钮
        gameForm.getStartBtn().doClick();
        assertFalse(gameForm.getTimer().isRunning());
        assertEquals("Start", gameForm.getStartBtn().getText());

        // 再次点击 "Stop" 按钮
        gameForm.getStopBtn().doClick();
        assertTrue(gameForm.getTimer().isRunning());
        assertEquals("Stop", gameForm.getStopBtn().getText());
    }

    @Test
    void testNext() {
        // 手动设置初始状态
        gameForm.getMap().cells[1][1].setStatus(DataStructure.CellStatus.ALIVE);
        gameForm.getMap().cells[1][2].setStatus(DataStructure.CellStatus.ALIVE);
        gameForm.getMap().cells[2][1].setStatus(DataStructure.CellStatus.ALIVE);
        gameForm.updateMap();

        // 模拟点击 "Next" 按钮
        gameForm.getNextBtn().doClick();

        // 验证下一代地图状态是否正确
        assertEquals(gameForm.getAliveColor(), gameForm.getMapBtns()[0][0].getBackground());
        assertEquals(gameForm.getAliveColor(), gameForm.getMapBtns()[0][1].getBackground());
        assertEquals(gameForm.getAliveColor(), gameForm.getMapBtns()[1][0].getBackground());
        assertEquals(gameForm.getAliveColor(), gameForm.getMapBtns()[1][1].getBackground());
    }
    @Test
    void testClickMapBtn() {
        // 模拟点击地图按钮
        gameForm.clickMapBtn(0, 0);

        // 验证对应细胞状态和按钮背景颜色是否已更新
        assertEquals(DataStructure.CellStatus.ALIVE, gameForm.getMap().cells[1][1].getStatus());
        assertEquals(gameForm.getAliveColor(), gameForm.getMapBtns()[0][0].getBackground());
    }

    @Test
    void testUpdateMap() {
        // 手动设置细胞状态
        gameForm.getMap().cells[1][1].setStatus(DataStructure.CellStatus.ALIVE);
        gameForm.getMap().cells[1][2].setStatus(DataStructure.CellStatus.DEAD);

        // 更新地图显示
        gameForm.updateMap();

        // 验证按钮背景颜色是否与细胞状态一致
        assertEquals(gameForm.getAliveColor(), gameForm.getMapBtns()[0][0].getBackground());
        assertEquals(gameForm.getDeadColor(), gameForm.getMapBtns()[0][1].getBackground());
    }

    @Test
    void testOnSetFormSubmit() {
        // 模拟 SetForm 提交事件
        int newRows = 15;
        int newCols = 25;
        Color newAliveColor = Color.RED;
        gameForm.onSetFormSubmit(newRows, newCols, newAliveColor);

        // 验证 GameForm 的属性是否已更新
        assertEquals(newRows, gameForm.getRows());
        assertEquals(newCols, gameForm.getCols());
        assertEquals(newAliveColor, gameForm.getAliveColor());

        // 验证地图按钮数组大小和背景颜色是否已更新
        assertEquals(newRows, gameForm.getMapBtns().length);
        assertEquals(newCols, gameForm.getMapBtns()[0].length);
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                assertEquals(gameForm.getDeadColor(), gameForm.getMapBtns()[i][j].getBackground());
            }
        }
    }
}
