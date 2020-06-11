package client.ui.userview;

import client.data.dao.ProductModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 선택된 상품을 구성하는 클래스
 *
 * @author 정유라
 */
public class SelectedItemPnl extends JPanel {

    public JButton btnMinus = new JButton("-"), btnPlus = new JButton("+"), btnX = new JButton("x");
    public int itemCount = 1;
    public ProductModel productModel;
    JPanel itemNameAndMoneyPnl = new JPanel();
    JLabel lblSelectedItemName = new JLabel();
    JLabel lblSelectedItemPrice = new JLabel();
    JPanel itemQuantityPnl = new JPanel();
    JLabel lblItemQuantity = new JLabel();

    public SelectedItemPnl(ProductModel productModel) {
        this.productModel = productModel;

        if (!this.productModel.IsSell) {
            btnPlus.setEnabled(false);
        }
        if (productModel.PrNumber < 0) {
            btnMinus.setEnabled(false);
        }
        lblItemQuantity.setText(String.valueOf(itemCount));
        itemNameAndMoneyPnl.setBounds(10, 10, 180, 50);
        itemNameAndMoneyPnl.setLayout(new BorderLayout());

        lblSelectedItemName.setText(productModel.PrName);
        lblSelectedItemName.setFont(new Font("맑은고딕", Font.PLAIN, 20));

        lblSelectedItemPrice.setFont(new Font("맑은고딕", Font.PLAIN, 20));

        itemNameAndMoneyPnl.add(lblSelectedItemName, BorderLayout.CENTER);
        itemNameAndMoneyPnl.add(lblSelectedItemPrice, BorderLayout.LINE_END);


        itemQuantityPnl.setBounds(190, 10, 100, 50);
        itemQuantityPnl.setBackground(Color.white);
        itemQuantityPnl.setLayout(new GridLayout(1, 4));

        itemQuantityPnl.add(btnMinus);
        itemQuantityPnl.add(lblItemQuantity);
        itemQuantityPnl.add(btnPlus);
        itemQuantityPnl.add(btnX);

        add(itemNameAndMoneyPnl);
        add(itemQuantityPnl);

    }

    public void addBtnClicked() {
        itemCount++;
        lblItemQuantity.setText(String.valueOf(itemCount));
    }

    public boolean minusBtnClicked() {
        if (itemCount > 1) {
            itemCount--;
            lblItemQuantity.setText(String.valueOf(itemCount));
            return true;
        } else return false;
    }

    public void updateItemCount() {
        itemCount = 1;
        lblItemQuantity.setText(String.valueOf(itemCount));
    }

    public void addListener(ActionListener listener) {
        if (btnMinus.getActionListeners().length == 0)
            btnMinus.addActionListener(listener);
        if (btnPlus.getActionListeners().length == 0)
            btnPlus.addActionListener(listener);
        if (btnX.getActionListeners().length == 0)
            btnX.addActionListener(listener);
    }

}