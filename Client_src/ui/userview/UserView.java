package client.ui.userview;

import client.data.dao.ProductModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserView 초기화면을 구성하고 서버로부터 데이터를 받기위해 Controller와 연결될 함수들 정의
 */
public interface UserView {
    JPanel startPnl = new JPanel(), itemListPnl = new JPanel(), selectedListPnl = new JPanel();
    JButton btnAdminClient = new JButton(), btnPay = new JButton("구매");
    JLabel lblTotalMoney = new JLabel("총 금액 ");
    List<ItemInfoPnl> itemLists = new LinkedList<>();
    List<SelectedItemPnl> selectedItemLists = new LinkedList<>();
    AtomicInteger totalMoney = new AtomicInteger();

    void updateItemLists(Vector<ProductModel> lists);

    void updateMoney();

    void updateSelectedLists(ProductModel productModel);

    boolean plusItemCount(SelectedItemPnl item);

    boolean minusItemCount(SelectedItemPnl item);

    void removeItem(SelectedItemPnl item);

    void clearItem();

    void addListener(ActionListener listener);

    void addItemListListener(ActionListener listener);

    void addSelectedItemListener(ActionListener listener);

}