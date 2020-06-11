package client.ui.adminview;

import client.data.dao.IngredientModel;
import client.data.dao.ProductModel;
import client.ui.adminview.holder.AddItemHolder;
import client.ui.adminview.holder.BuyIngredientsHolder;
import client.ui.adminview.holder.CurrentIngrdntHolder;
import client.ui.adminview.holder.TotalMoneyHolder;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Vector;
/**
 *  AdminView 초기화면을 구성하고 서버로부터 데이터를 받기위해 Controller와 연결될 함수들 정의
 */
public interface AdminView {
    TotalMoneyHolder TOTAL_MONEY_HOLDER = new TotalMoneyHolder();
    BuyIngredientsHolder BUY_INGREDIENTS_HOLDER = new BuyIngredientsHolder();
    CurrentIngrdntHolder CURRENT_INGRDNT_HOLDER = new CurrentIngrdntHolder();
    AddItemHolder ADD_ITEM_HOLDER = new AddItemHolder();
    JPanel adminStartPanel = new JPanel(), btnAdminWhatToDo = new JPanel();


    JPanel AdminClientButtonPnl = new JPanel();
    JButton btnTotalMoney = new JButton("총 매출"), btnCurrentIngredients = new JButton("재고 현황"),
            btnBuyIngredients = new JButton("재고 구매"), btnAddItem = new JButton("제품 추가"), btnAdminClient = new JButton("사용자");

    void updateCurrentTable(Vector<IngredientModel> ingredientModels);

    void updateBuyIngredientTable(Vector<IngredientModel> ingredientModels);

    void totalMoneyTable(Vector<ProductModel> productModels, int totalMoney);

    void addAdminListener(ActionListener listener);
}