package client.data.datasource.callback;

import client.data.dao.IngredientModel;
import client.data.dao.ProductModel;

import java.util.Vector;

/**
 * 서버와 통신시 필요한 Callbcak interface
 *
 * @author 조재영
 * @see ServerConnectionCallback 연결시 필요한 base 콜백
 * @see GetTableCallback 재료목록을 얻어올 때 필요한 콜백
 * @see SelectItemCallback 선택된 아이템을 얻어올 때 필요한 콜백
 * @see TotalMoneyCallback 총 구입 금액을 얻어올 때 필요한 콜백
 */
public interface ServerConnectionCallback {
    void accept(Vector<ProductModel> lists);

    void error(String error);

    interface GetTableCallback {
        void IgSuccess(Vector<IngredientModel> lists);
    }

    interface SelectItemCallback {
        void success(Vector<ProductModel> lists);

    }

    interface TotalMoneyCallback {
        void success(Vector<ProductModel> lists, int totalMoney);
    }
}