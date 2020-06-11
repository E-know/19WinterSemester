package client.data;

import client.data.dao.ProductModel;
import client.data.datasource.ServerConnection;
import client.data.datasource.callback.ServerConnectionCallback;

import java.util.Vector;

/**
 * 데이터 분기를 위해 필요한 클래스
 * 하지만, 여기서는 DataSource가 하나만 존재하기 떄문에 같은 작업을 반복하는 역할을 한다.
 * 추후 기능확장을 위해 남겨두었음.
 *
 * @author 조재영
 */
public class RepositoryImpl implements Repository {
    private static Repository INSTANCE = null;
    private ServerConnection serverConnection;

    private RepositoryImpl(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public static Repository getInstance(ServerConnection serverConnection) {
        if (INSTANCE == null) INSTANCE = new RepositoryImpl(serverConnection);
        return INSTANCE;
    }

    @Override
    public void connectServer(ServerConnectionCallback callback) {
        serverConnection.login(new ServerConnectionCallback() {
            @Override
            public void accept(Vector<ProductModel> lists) {
                callback.accept(lists);
            }

            @Override
            public void error(String error) {
                callback.error(error);
            }
        });

    }

    @Override
    public void selectItem(ProductModel item, ServerConnectionCallback.SelectItemCallback callback) {
        serverConnection.selectItem(item, callback);
    }

    @Override
    public void minusItem(ProductModel item, ServerConnectionCallback.SelectItemCallback callback) {
        serverConnection.minusItem(item, callback);
    }

    @Override
    public void exitItem(ProductModel item, int itemCount, ServerConnectionCallback.SelectItemCallback callback) {
        serverConnection.exitItem(item, itemCount, callback);
    }

    @Override
    public void buyItem(int total) {
        serverConnection.buyItem(total);
    }


    @Override
    public void totalMoney(ServerConnectionCallback.TotalMoneyCallback callback) {
        serverConnection.totalMoney(callback);
    }


    @Override
    public void currentIngredients(ServerConnectionCallback.GetTableCallback callback) {
        serverConnection.currentIngredients(callback);
    }

    @Override
    public void buyIngredient(ServerConnectionCallback.GetTableCallback callback) {
    }


}
