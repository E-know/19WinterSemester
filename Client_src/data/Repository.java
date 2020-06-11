package client.data;

import client.data.dao.ProductModel;
import client.data.datasource.callback.ServerConnectionCallback;

/**
 * 서버로 요청할 함수들에 대한 인터페이스
 *
 * @author 조재영
 */
public interface Repository {
    void connectServer(ServerConnectionCallback callback);

    void selectItem(ProductModel item, ServerConnectionCallback.SelectItemCallback callback);

    void minusItem(ProductModel item, ServerConnectionCallback.SelectItemCallback callback);

    void exitItem(ProductModel item, int itemCount, ServerConnectionCallback.SelectItemCallback callback);

    void buyItem(int total);

    void currentIngredients(ServerConnectionCallback.GetTableCallback callback);

    void buyIngredient(ServerConnectionCallback.GetTableCallback callback);

    void totalMoney(ServerConnectionCallback.TotalMoneyCallback callback);
}
