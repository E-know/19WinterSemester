package client;

import client.controller.Controller;
import client.data.RepositoryImpl;
import client.data.datasource.ServerConnection;
import client.ui.CardLayoutMain;
import client.ui.adminview.AdminViewImpl;
import client.ui.userview.UserViewImpl;


public class Main {
    /**
     * 앱 실행 Main
     * 컨트롤러를 생성하며 ViewHolder역할을 하는 CardLayoutMain과 Singleton Repository를 주입하며 실행된다.
     *
     * @author 조재영
     * @see (Controller)
     */
    public static void main(String[] args) {

        Controller controller = new Controller(new CardLayoutMain(new UserViewImpl(), new AdminViewImpl()), RepositoryImpl.getInstance(new ServerConnection()));
        controller.connectServer();

    }
}