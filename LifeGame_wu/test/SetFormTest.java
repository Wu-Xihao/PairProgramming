import Data.DataStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class SetFormTest {

    private SetForm setForm;

    @BeforeEach
    void setUp() {
        // 在每个测试方法执行前创建新的 SetForm 实例
        setForm = new SetForm(30, 60, Color.CYAN);
    }

    @Test
    void testChangeRows() {
        // 测试正常输入
        setForm.getRowText().setText("20");
        assertEquals(20, setForm.changeRows());

        // 测试超出上限
        setForm.getRowText().setText("50");
        assertEquals(30, setForm.changeRows()); // 应该返回初始值
        assertFalse(setForm.getFlag()); // flag 应该设置为 false

        // 测试超出下限
        setForm.getRowText().setText("5");
        assertEquals(30, setForm.changeRows()); // 应该返回初始值
        assertFalse(setForm.getFlag());

        // 测试非数字输入
        setForm.getRowText().setText("abc");
        assertEquals(30, setForm.changeRows()); // 应该返回初始值
        assertFalse(setForm.getFlag());
    }

    @Test
    void testChangeCols() {
        // 测试正常输入
        setForm.getColText().setText("40");
        assertEquals(40, setForm.changeCols());

        // 测试超出上限
        setForm.getColText().setText("90");
        assertEquals(60, setForm.changeCols()); // 应该返回初始值
        assertFalse(setForm.getFlag());

        // 测试超出下限
        setForm.getColText().setText("10");
        assertEquals(60, setForm.changeCols()); // 应该返回初始值
        assertFalse(setForm.getFlag());

        // 测试非数字输入
        setForm.getColText().setText("xyz");
        assertEquals(60, setForm.changeCols()); // 应该返回初始值
        assertFalse(setForm.getFlag());
    }

    @Test
    void testGetAliveColor() {
        // 默认颜色应该是 CYAN
        assertEquals(Color.CYAN, setForm.getAliveColor());

        // 模拟选择其他颜色
        setForm.AliveColor = Color.RED;
        assertEquals(Color.RED, setForm.getAliveColor());
    }

    @Test
    void testOk() {
        // 设置正确的行数和列数
        setForm.getRowText().setText("25");
        setForm.getColText().setText("70");

        // 模拟点击 "OK" 按钮
        setForm.getOkButton().doClick();

        // 验证数据是否正确更新
        assertTrue(setForm.getFlag());
        assertEquals(25, setForm.Rows);
        assertEquals(70, setForm.Cols);
        assertEquals(Color.CYAN, setForm.AliveColor);
        assertEquals(25, DataStructure.ROWS);
        assertEquals(70, DataStructure.COLS);
        assertEquals(Color.CYAN, DataStructure.ALIVECOLOR);

        // 验证窗口是否关闭
        assertFalse(setForm.Frame.isVisible());
    }

    @Test
    void testCancel() {
        // 模拟点击 "Cancel" 按钮
        setForm.getCancelButton().doClick();

        // 验证窗口是否关闭
        assertFalse(setForm.Frame.isVisible());
    }

    @Test
    void testSetFormListener() {
        // 创建一个 Mock Listener
        SetForm.SetFormListener mockListener = (rows, cols, aliveColor) -> {
            // 验证传递给 listener 的参数是否正确
            assertEquals(35, rows);
            assertEquals(75, cols);
            assertEquals(Color.BLUE, aliveColor);
        };

        // 设置 listener
        setForm.setSetFormListener(mockListener);

        // 设置行数、列数和颜色
        setForm.Rows = 35;
        setForm.getRowText().setText("35");
        setForm.Cols = 75;
        setForm.getColText().setText("75");
        setForm.AliveColor = Color.BLUE;
        setForm.setFlag(true);

        // 模拟点击 "OK" 按钮
        setForm.getOkButton().doClick();
    }
}