package client.ui.adminview.holder;

import javax.swing.*;

/**
 * AdminView - 재고구매 페이지를 구성하는 컴포넌트들
 */
public class BuyIngredientsHolder {
    public JPanel decreaseMoneyPnl = new JPanel(), buyIngredientsPnl = new JPanel();
    public JComboBox comboBox = new JComboBox();
    public String[] str = {"재고선택"};
    public JLabel lblIngredientsToAdd = new JLabel("추가할 재고"), lblQuantityToAdd = new JLabel("수량"), lblTitleDecreasedTotalMoney = new JLabel("총 액"), lblDecreasedTotalMoney = new JLabel("");
    public JButton btnInsert = new JButton("등록"), btnModify = new JButton("수정"), btnDelete = new JButton("삭제"), btnBackBuyIngredients = new JButton("뒤로가기");
    public JTextField tfQuantityToAdd = new JTextField();

    public String colBuyIngredient[] = {};
    public String rowBuyIngredient[][] = {};
    public JTable table;
}
