package client.controller;

import client.data.Repository;
import client.data.dao.ProductModel;
import client.data.datasource.callback.ServerConnectionCallback;
import client.ui.CardLayoutMain;
import kotlin.jvm.Volatile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * View에게 알려주고
 * Model에서 데이터를 받아오는 중간자 역할을 하는 클래스
 *
 * @author 조재영
 */

public class Controller implements ActionListener {

    private Repository repository;
    private CardLayoutMain cardLayoutMain;
    @Volatile
    private boolean changeDialogFlag = true;

    /**
     * userView와 AdminView를 관리하는 Controller Class
     *
     * @param cardLayoutMain view 에관한 정보를 담고 있는 클래스
     * @param repository     Repository 에 관한 정보를 담고 있는 클래스
     */
    public Controller(CardLayoutMain cardLayoutMain, Repository repository) {
        this.cardLayoutMain = cardLayoutMain;
        this.repository = repository;
    }

    /**
     * 서버와 연결하는 클래스
     * 성공시 리스트를 받아와 음식 목록을 화면에 뿌려준다.
     *
     * @see ServerConnectionCallback 해당 콜백을 이용하여 결과값을 리턴.
     */
    public void connectServer() {
        repository.connectServer(new ServerConnectionCallback() {
            @Override
            public void accept(Vector<ProductModel> lists) {
                cardLayoutMain.userView.updateItemLists(lists);
                cardLayoutMain.userView.addItemListListener(Controller.this::actionPerformed);
                cardLayoutMain.userView.addListener(Controller.this::actionPerformed);
                cardLayoutMain.adminView.addAdminListener(Controller.this::actionPerformed);

            }

            @Override
            public void error(String error) {
                System.out.println("에러 발생 : " + error);
            }


        });

    }

    /**
     * Controller의 주 역할을 하는 함수
     *
     * @param actionEvent 선택된 액션
     */
    @Override
    public synchronized void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        if (cardLayoutMain.userView.btnPay.equals(obj)) {//구매 결정이 눌렸을때 호출

            repository.buyItem(Integer.parseInt(cardLayoutMain.userView.totalMoney.toString()));
            cardLayoutMain.userView.clearItem();
            cardLayoutMain.userView.updateMoney();

        } else if (cardLayoutMain.userView.btnAdminClient.equals(obj) || cardLayoutMain.adminView.btnAdminClient.equals(obj)) { //사용자, 관리자 버튼

            if (changeDialogFlag) {
                cardLayoutMain.changeDialog("Admin");
            } else {
                cardLayoutMain.changeDialog("Start");
            }

            changeDialogFlag = !changeDialogFlag;

        } else if (cardLayoutMain.adminView.btnCurrentIngredients.equals(obj)) { // 재고 현황

            cardLayoutMain.changeDialog("CurrentIngredients");
            repository.currentIngredients(lists -> cardLayoutMain.adminView.updateCurrentTable(lists));

        } else if (cardLayoutMain.adminView.btnBuyIngredients.equals(obj)) { // 재고 구매

            cardLayoutMain.changeDialog("BuyIngredients");
            repository.buyIngredient(lists -> cardLayoutMain.adminView.updateBuyIngredientTable(lists));

        } else if (cardLayoutMain.adminView.btnTotalMoney.equals(obj)) { // 총 매출

            cardLayoutMain.changeDialog("TotalMoney");

            repository.totalMoney(new ServerConnectionCallback.TotalMoneyCallback() {
                @Override
                public void success(Vector<ProductModel> lists, int totalMoney) {
                    cardLayoutMain.adminView.totalMoneyTable(lists, totalMoney);
                }
            });

        } else if (cardLayoutMain.adminView.btnAddItem.equals(obj)) { //재고 추가 버튼

            cardLayoutMain.changeDialog("AddItem");

        } else if (cardLayoutMain.adminView.TOTAL_MONEY_HOLDER.btnBackTotalMoney.equals(obj) || cardLayoutMain.adminView.BUY_INGREDIENTS_HOLDER.btnBackBuyIngredients.equals(obj)// 모든 뒤로가기 버튼에 대한 동작
                || cardLayoutMain.adminView.CURRENT_INGRDNT_HOLDER.btnBackCurrentIngredients.equals(obj) || cardLayoutMain.adminView.ADD_ITEM_HOLDER.btnBackAddItem.equals(obj)) {

            cardLayoutMain.changeDialog("Admin");

        } else { // 위 버튼들의 동작이 아닌 리스트 형태의 동작
            //뷰를 재사용하지 않고 계속 새로 만들기 때문에 이러한 Overhead 가 발생한다.

            cardLayoutMain.userView.itemLists.forEach(item ->
            {

                if (obj.equals(item.btnItem)) {
                    repository.selectItem(item.productModel, (productModelVector) -> {
                        cardLayoutMain.userView.updateSelectedLists(item.productModel);
                        updateItemView(productModelVector);
                    });
                }

            });

            cardLayoutMain.userView.selectedItemLists.forEach(item -> {

                if (obj.equals(item.btnMinus)) {

                    if (cardLayoutMain.userView.minusItemCount(item)) {

                        repository.minusItem(item.productModel, (productModelVector) -> {
                            cardLayoutMain.userView.updateSelectedLists(item.productModel);
                            updateItemView(productModelVector);
                        });

                    }

                } else if (obj.equals(item.btnPlus)) {

                    if (cardLayoutMain.userView.plusItemCount(item)) {

                        repository.selectItem(item.productModel, (productModelVector) -> {
                            cardLayoutMain.userView.updateSelectedLists(item.productModel);
                            updateItemView(productModelVector);
                        });

                    }
                } else if (obj.equals(item.btnX)) {

                    repository.exitItem(item.productModel, item.itemCount, (productModelVector) -> {
                        cardLayoutMain.userView.removeItem(item);
                        updateItemView(productModelVector);
                    });

                }

            });

        }

    }


    /**
     * 1.아이템 리스트를 업데이트
     * 2.각 아이템에 리스너 셋팅
     * 3.선택된 아이템들에 리스너 셋팅
     * 4.총 구입 금액 최신화
     *
     * @param productModelVector Server에게서 받은 음식 목록
     */
    private void updateItemView(Vector<ProductModel> productModelVector) {
        cardLayoutMain.userView.updateItemLists(productModelVector);
        cardLayoutMain.userView.addItemListListener(Controller.this::actionPerformed);
        cardLayoutMain.userView.addSelectedItemListener(this::actionPerformed);
        cardLayoutMain.userView.updateMoney();
    }


}