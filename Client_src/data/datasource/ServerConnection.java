package client.data.datasource;

import client.data.dao.IngredientModel;
import client.data.dao.ProductModel;
import client.data.datasource.callback.ServerConnectionCallback;
import client.util.JsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

/**
 * 서버와 연결하기 위한 Datasource 클래스
 * 서버와 연결, 데이터 가공에만 관여한다.
 *
 * @author 조재영
 */
public class ServerConnection {
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    private JsonParser parser = new JsonParser();

    /**
     * Socket생성과 reader, writer 생성
     */
    public ServerConnection() {
        try {
            socket = new Socket("192.168.123.29", 5050);//LocalHost로 port 5050연결
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true); // Buffer에 사용할 수 있는 writer 클래스 생성 후 socket에 연결된 outputStream에 연결하는 과정
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 데이터 전송 이후 Receive Thread가 작동하므로 순서제어를 위하여 synchronized 부착
     *
     * @param text 전송할 String
     */
    private synchronized void send(String text) {
        writer.println(text);//전송
    }

    /**
     * 로그인 데이터를 전송하는 함수
     *
     * @param callback
     */
    public void login(ServerConnectionCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("login", "100");
        send(jsonObject.toString());
        new Thread(() -> {
            try {
                String data = reader.readLine();
                JsonArray array = (JsonArray) parser.parse(data);
                Vector<ProductModel> vector = new Vector<>();
                for (JsonElement object : array) {
                    ProductModel model = JsonUtil.INSTANCE.getProductModel(object);
                    vector.add(model);
                }
                callback.accept(vector);
            } catch (IOException e) {
                e.printStackTrace();
                callback.error(e.getMessage());
            }
        }).start();
    }

    /**
     * 선택된 아이템을 전송하는 함수
     *
     * @param item     선택된 아이템 정보
     * @param callback
     */
    public void selectItem(ProductModel item, ServerConnectionCallback.SelectItemCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("select", item.PrCode);
        send(jsonObject.toString());
        new Thread(() -> {
            try {
                String data = reader.readLine();
                JsonArray array = (JsonArray) parser.parse(data);
                Vector<ProductModel> vector = new Vector<>();
                for (JsonElement object : array) {
                    ProductModel model = JsonUtil.INSTANCE.getProductModel(object);
                    vector.add(model);
                }
                callback.success(vector);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    /**
     * 선택된 아이템의 갯수를 감소시키는 함수
     *
     * @param item     선택된 아이템 정보
     * @param callback
     */
    public void minusItem(ProductModel item, ServerConnectionCallback.SelectItemCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("minus", item.PrCode);
        send(jsonObject.toString());
        new Thread(() -> {
            try {
                String data = reader.readLine();
                JsonArray array = (JsonArray) parser.parse(data);
                Vector<ProductModel> vector = new Vector<>();
                for (JsonElement object : array) {
                    ProductModel model = JsonUtil.INSTANCE.getProductModel(object);
                    vector.add(model);
                }
                callback.success(vector);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ).start();
    }

    /**
     * 선택된 아이템의 수량을 전부 취소시키는 함수
     *
     * @param item      선택된 아이템의 정보
     * @param itemCount 선택된 아이템의 수량 정보
     * @param callback
     */
    public void exitItem(ProductModel item, int itemCount, ServerConnectionCallback.SelectItemCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("exit", item.PrCode);
        jsonObject.addProperty("count", itemCount);
        send(jsonObject.toString());
        new Thread(() -> {
            try {
                String data = reader.readLine();
                JsonArray array = (JsonArray) parser.parse(data);
                Vector<ProductModel> vector = new Vector<>();
                for (JsonElement object : array) {
                    ProductModel model = JsonUtil.INSTANCE.getProductModel(object);
                    vector.add(model);
                }
                callback.success(vector);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ).start();
    }

    /**
     * 아이템을 구매하는 함수
     *
     * @param total 총 구매 가격
     */
    public void buyItem(int total) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("buy", total);
        send(jsonObject.toString());
    }

    /**
     * 현재 총 판매량을 구하기 위한 함수
     *
     * @param callback
     */
    public void totalMoney(ServerConnectionCallback.TotalMoneyCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("total", 0);
        send(jsonObject.toString());
        new Thread(() -> {
            try {
                String data = reader.readLine();
                JsonObject object = (JsonObject) parser.parse(data);
                int total = object.get("total").getAsInt();
                String arr = object.get("array").getAsString();
                JsonArray array = (JsonArray) parser.parse(arr);
                Vector<ProductModel> vector = new Vector<>();
                for (JsonElement obj : array) {
                    ProductModel model = JsonUtil.INSTANCE.getProductModel(obj);
                    vector.add(model);
                }
                callback.success(vector, total);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    /**
     * 서버가 가지고있는 재료의 리스트를 구하기 위한 함수
     *
     * @param callback
     */
    public void currentIngredients(ServerConnectionCallback.GetTableCallback callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ingredient", 0);
        send(jsonObject.toString());
        new Thread(() -> {
            try {
                String data = reader.readLine();
                JsonArray array = (JsonArray) parser.parse(data);
                Vector<IngredientModel> vector = new Vector<>();
                for (JsonElement object : array) {
                    IngredientModel model = JsonUtil.INSTANCE.getIngredientModel(object);
                    vector.add(model);
                }
                callback.IgSuccess(vector);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ).start();
    }
}
