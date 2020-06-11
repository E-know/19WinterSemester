package client.ui.userview;

import client.data.dao.ProductModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * UserView의 상품(버튼, 이름, 가격)을 구성하는 클래스
 *
 * @author 정유라
 */

public class ItemInfoPnl extends JPanel {
    public ProductModel productModel;
    public JButton btnItem = new JButton();
    public int itemIndex;
    JLabel lblItemPrice = new JLabel();
    JLabel lblItemName = new JLabel();


    public ItemInfoPnl(ProductModel productModel, int index) {
        setPreferredSize(new Dimension(100, 70));

        setBackground(new Color(237, 145, 55));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.productModel = productModel;
        btnItem.setText(Integer.toString(index));

        btnItem.setBackground(new Color(0x87C24C));
        btnItem.setPreferredSize(new Dimension(90, 30));

        lblItemPrice.setText(String.valueOf(productModel.PrPrice));
        lblItemName.setText(productModel.PrName);
        lblItemName.setFont(new Font("맑은고딕", Font.PLAIN, 12));
        lblItemPrice.setFont(new Font("맑은고딕", Font.PLAIN, 12));
        itemIndex = index;


        add(btnItem);
        add(lblItemName);
        add(lblItemPrice);
    }

    public void addListener(ActionListener listener) {
        btnItem.addActionListener(listener);
    }

    ;

}