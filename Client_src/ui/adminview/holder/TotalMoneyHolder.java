package client.ui.adminview.holder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * AdminView - 총 매출 페이지를 구성하는 컴포넌트들
 */
public class TotalMoneyHolder {
    private final static String[] columns = {"No", "상품명", "가격", "판매수량", "총액"};
    public JPanel totalMoneyPnl = new JPanel(), totalSalesPnl = new JPanel();
    public JLabel lblTitleTotalMoney = new JLabel("총 매출"), lblTotalSales = new JLabel(), lblTotalSalesMoney = new JLabel("합계");
    public JButton btnBackTotalMoney = new JButton("뒤로가기");
    public Vector<String> colTotalMoney = new Vector<>();
    public Vector<String> rowTotalMoney;
    public JTable table;
    public DefaultTableModel model;

    public TotalMoneyHolder() {
        for (String col : columns) {
            colTotalMoney.addElement(col);
        }
    }
}
