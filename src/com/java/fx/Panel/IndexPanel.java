package com.java.fx.Panel;

import com.java.fx.Action.IndexAction;
import com.java.fx.Main;
import com.java.fx.Panel.Modal.PlanModalPanel;
import com.java.fx.Panel.Modal.PlanViewInfoPanel;
import com.java.fx.Panel.Modal.RecordModalPanel;
import com.java.fx.Panel.Prompt.ErrorPromptPanel;
import com.java.fx.Util.DataUtil;
import com.java.fx.Util.Local;
import com.java.fx.Util.StringUtil;
import com.java.fx.model.Dictionary;
import com.java.fx.model.Plan;
import com.java.fx.model.Record;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class IndexPanel extends MainPanel {
    private Button addPlan = new Button("新增计划");
    private Button editPlan = new Button("编辑计划");
    private Button deletePlan = new Button("删除计划");
    private Button viewPlan = new Button("计划预览");

    private Button addRecord = new Button("新增记录");
    private Button editRecord = new Button("编辑记录");
    private Button deleteRecord = new Button("删除记录");
    private static Label initMoneyLabel = new Label("初始金额: ");
    private TextField initMoney = new TextField();
    private static Label currentMoney = new Label();

    public static TableView<Plan> planTV;
    public static TableView<Record> recordTV;
    public static TableView<Record> planViewTV;

    private ChoiceBox accountListCb;
    private ObservableList<Plan> planListOL;
    private ObservableList<Record> recordListOL;

    AnchorPane left = new AnchorPane();
    private boolean isViewPlan = false;

    @Override
    public Scene init() {
        BorderPane bp = new BorderPane();
        AnchorPane center = new AnchorPane();
        Scene scene = new Scene(bp);
        List<Dictionary<BigDecimal>> accountList = Local.system.getAccount();

        // 账户下拉框
        accountListCb = new ChoiceBox();
        accountListCb.setConverter(new StringConverter<Dictionary>() {
            @Override
            public String toString(Dictionary object) {
                return object.getText();
            }
            @Override
            public Dictionary fromString(String string) {
                return Local.system.getAccount(string);
            }
        });
        accountListCb.getItems().addAll(accountList);
        accountListCb.setValue(Local.account);
        accountListCb.setPrefWidth(110);

        // 计划Table
        planListOL = FXCollections.observableArrayList();
        planListOL.setAll(Local.plan);

        TableColumn<Plan, String> interval = new TableColumn<>("颗粒度");
        interval.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getInterval()));
        interval.setPrefWidth(60);
        TableColumn<Plan, String> date = new TableColumn<>("日期");
        date.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDate()));
        date.setPrefWidth(80);
        TableColumn<Plan, String> type = new TableColumn<>("类型");
        type.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getType()));
        type.setPrefWidth(60);
        TableColumn<Plan, String> classify = new TableColumn<>("分类");
        classify.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getClassify()));
        classify.setPrefWidth(95);
        TableColumn<Plan, BigDecimal> money = new TableColumn<>("金额");
        money.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMoney()));
        money.setPrefWidth(90);
        TableColumn<Plan, String> note = new TableColumn<>("备注");
        note.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNote()));
        note.setPrefWidth(193);

        planTV = new TableView<>(planListOL);
        planTV.setPrefWidth(550);
        planTV.setPrefHeight(800);
        planTV.getColumns().addAll(interval, date, type, classify, money, note);

        // plan预览Table
        ObservableList<Record> planViewOL = FXCollections.observableArrayList();

        TableColumn<Record, String> planDate = new TableColumn<>("日期");
        planDate.setCellValueFactory(param -> new SimpleObjectProperty<>(StringUtil.dateToString(param.getValue().getDate())));
        planDate.setPrefWidth(80);
        TableColumn<Record, String> planType = new TableColumn<>("类型");
        planType.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getType()));
        planType.setPrefWidth(60);
        TableColumn<Record, String> planClassify = new TableColumn<>("分类");
        planClassify.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getClassify()));
        planClassify.setPrefWidth(95);
        TableColumn<Record, BigDecimal> planMoney = new TableColumn<>("金额");
        planMoney.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMoney()));
        planMoney.setPrefWidth(90);
        TableColumn<Record, String> planNote = new TableColumn<>("备注");
        planNote.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNote()));
        planNote.setPrefWidth(220);

        planViewTV = new TableView<>(planViewOL);
        planViewTV.setPrefWidth(550);
        planViewTV.setPrefHeight(800);
        planViewTV.getColumns().addAll(planDate, planType, planClassify, planMoney, planNote);

        // record Table
        recordListOL = FXCollections.observableArrayList();
        recordListOL.setAll(Local.record);

        TableColumn<Record, String> recordDate = new TableColumn<>("日期");
        recordDate.setCellValueFactory(param -> new SimpleObjectProperty<>(StringUtil.dateToString(param.getValue().getDate())));
        recordDate.setPrefWidth(80);
        TableColumn<Record, String> recordType = new TableColumn<>("类型");
        recordType.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getType()));
        recordType.setPrefWidth(60);
        TableColumn<Record, String> recordClassify = new TableColumn<>("分类");
        recordClassify.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getClassify()));
        recordClassify.setPrefWidth(95);
        TableColumn<Record, BigDecimal> recordMoney = new TableColumn<>("金额");
        recordMoney.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMoney()));
        recordMoney.setPrefWidth(90);
        TableColumn<Record, String> recordNote = new TableColumn<>("备注");
        recordNote.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNote()));
        recordNote.setPrefWidth(224);

        recordTV = new TableView<>(recordListOL);
        recordTV.setPrefWidth(550);
        recordTV.setPrefHeight(800);
        recordTV.getColumns().addAll(recordDate, recordType, recordClassify, recordMoney, recordNote);

        // 设置位置
        AnchorPane.setLeftAnchor(accountListCb, 10.0);
        AnchorPane.setTopAnchor(accountListCb, 10.0);
        AnchorPane.setLeftAnchor(addPlan, 130.0);
        AnchorPane.setTopAnchor(addPlan, 10.0);
        AnchorPane.setLeftAnchor(editPlan, 200.0);
        AnchorPane.setTopAnchor(editPlan, 10.0);
        AnchorPane.setLeftAnchor(deletePlan, 270.0);
        AnchorPane.setTopAnchor(deletePlan, 10.0);
        AnchorPane.setLeftAnchor(viewPlan, 340.0);
        AnchorPane.setTopAnchor(viewPlan, 10.0);
        AnchorPane.setLeftAnchor(planTV, 10.0);
        AnchorPane.setTopAnchor(planTV, 40.0);

        AnchorPane.setLeftAnchor(planViewTV, 10.0);
        AnchorPane.setTopAnchor(planViewTV, 40.0);

        AnchorPane.setLeftAnchor(addRecord, 10.0);
        AnchorPane.setTopAnchor(addRecord, 10.0);
        AnchorPane.setLeftAnchor(editRecord, 80.0);
        AnchorPane.setTopAnchor(editRecord, 10.0);
        AnchorPane.setLeftAnchor(deleteRecord, 150.0);
        AnchorPane.setTopAnchor(deleteRecord, 10.0);
        AnchorPane.setLeftAnchor(recordTV, 10.0);
        AnchorPane.setTopAnchor(recordTV, 40.0);

        AnchorPane.setTopAnchor(initMoneyLabel, 15.0);
        AnchorPane.setLeftAnchor(initMoneyLabel, 230.0);
        AnchorPane.setTopAnchor(initMoney, 10.0);
        AnchorPane.setLeftAnchor(initMoney, 285.0);
        AnchorPane.setTopAnchor(currentMoney, 15.0);
        AnchorPane.setLeftAnchor(currentMoney, 390.0);

        initMoneyLabel.setStyle("-fx-font-weight: bold");
        currentMoney.setStyle("-fx-font-weight: bold");
        initMoney.setAlignment(Pos.CENTER_RIGHT);
        initMoney.setPrefWidth(100);
        initMoney.setPromptText("初始金额");
        initMoney.setText(Local.account.getData().toString());
        refreshCurrentMoney();

        // 初始化左侧
        left.getChildren().addAll(accountListCb, addPlan, editPlan, deletePlan, viewPlan, planTV);
        // 初始化右侧
        center.getChildren().addAll(addRecord, editRecord, deleteRecord, recordTV, initMoneyLabel, initMoney, currentMoney);

        bp.setLeft(left);
        bp.setCenter(center);
        setWidth(1150);
        setHeight(900);
        setTitle("Index");
        setResizable(true);
        return scene;
    }

    @Override
    public void action() {
//        IndexAction indexAction = new IndexAction();
        accountListCb.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                Local.persistence();
                Local.init(Local.system.getAccount().get(newValue.intValue()));
                planListOL.setAll(Local.plan);
                recordListOL.setAll(Local.record);
                initMoney.setText(Local.account.getData().toString());
                refreshCurrentMoney();
            }
        });

        initMoney.textProperty().addListener((observable, oldValue, newValue) -> {
            Local.account.setData(new BigDecimal(newValue));
            refreshCurrentMoney();
        });

        addPlan.setOnAction(e -> new PlanModalPanel(Main.primaryStage).show());
        editPlan.setOnAction(e -> {
            if (planTV.getSelectionModel().getSelectedItem() == null) {
                new ErrorPromptPanel(Main.primaryStage).show("请选择要编辑的计划！");
            } else {
                new PlanModalPanel(Main.primaryStage).setPlan(planTV.getSelectionModel().getSelectedItem()).show();
            }
        });
        deletePlan.setOnAction(e -> {
            if (planTV.getSelectionModel().getSelectedItem() == null) {
                new ErrorPromptPanel(Main.primaryStage).show("请选择要删除的计划！");
            } else {
                Plan plan = planTV.getSelectionModel().getSelectedItem();
                planTV.getItems().remove(plan);
                Local.removePlan(plan);
            }
        });
        viewPlan.setOnAction(e -> {
            new PlanViewInfoPanel(Main.primaryStage).show();
            if (isViewPlan) {
                addPlan.setDisable(false);
                editPlan.setDisable(false);
                deletePlan.setDisable(false);
                viewPlan.setText("计划预览");
                left.getChildren().remove(planViewTV);
                left.getChildren().add(planTV);
                isViewPlan = false;
            } else {
                addPlan.setDisable(true);
                editPlan.setDisable(true);
                deletePlan.setDisable(true);
                viewPlan.setText("查看计划");
                left.getChildren().remove(planTV);
                left.getChildren().add(planViewTV);
                isViewPlan = true;
            }
        });


        addRecord.setOnAction(e -> new RecordModalPanel(Main.primaryStage).show());
        editRecord.setOnAction(e -> {
            if (recordTV.getSelectionModel().getSelectedItem() == null) {
                new ErrorPromptPanel(Main.primaryStage).show("请选择要编辑的记录！");
            } else {
                new RecordModalPanel(Main.primaryStage).setRecord(recordTV.getSelectionModel().getSelectedItem()).show();
            }
        });
        deleteRecord.setOnAction(e -> {
            if (recordTV.getSelectionModel().getSelectedItem() == null) {
                new ErrorPromptPanel(Main.primaryStage).show("请选择要删除的记录！");
            } else {
                Record record = recordTV.getSelectionModel().getSelectedItem();
                recordTV.getItems().remove(record);
                Local.removeRecord(record);
                refreshCurrentMoney();
            }
        });
    }

    public static void refreshCurrentMoney() {
        BigDecimal currentM = Local.account.getData();
        for (Record r : Local.record) {
            if (DataUtil.INCOME.equals(r.getType())) {
                currentM = currentM.add(r.getMoney());
            }
            if (DataUtil.PAY.equals(r.getType())) {
                currentM = currentM.subtract(r.getMoney());
            }
        }
        currentMoney.setText("当前金额: " + currentM.toString());
    }
}
