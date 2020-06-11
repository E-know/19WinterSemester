package client.ui.adminview.holder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * AdminView - 재고 현황 페이지를 구성하는 컴포넌트들
 */
public class CurrentIngrdntHolder {
    private final static String[] columns = {"No", "재고", "재고수량"};
    public JPanel currentIngredientsPnl = new JPanel();
    public JLabel lblTitleCurrentIngredients = new JLabel("재료 재고 현황");
    public JButton btnBackCurrentIngredients = new JButton("뒤로가기");
    public Vector<String> colCurrentIngredient = new Vector<>();
    public Vector<String> rowCurrentIngredient;
    public JTable table;
    public DefaultTableModel model;

    public CurrentIngrdntHolder() {
        for (String col : columns) {
            colCurrentIngredient.addElement(col);
        }
    }
}
