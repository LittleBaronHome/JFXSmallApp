package com.java.fx.Panel;

import com.java.fx.Action.IndexAction;
import com.java.fx.Main;
import com.java.fx.Panel.Modal.AddPlanModalPanel;
import com.java.fx.Panel.Prompt.ErrorPromptPanel;
import com.java.fx.Util.Local;
import com.java.fx.Util.PanelUtil;
import com.java.fx.Util.StringUtil;
import com.java.fx.model.Dictionary;
import com.java.fx.model.Plan;
import com.java.fx.model.Record;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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

    public static TableView<Plan> tableView;
    public static TableView<Record> recordTableView;
    public static TableView<Record> planTableView;

    AnchorPane left = new AnchorPane();
    private boolean isViewPlan = false;

    @Override
    public Scene init() {
        BorderPane bp = new BorderPane();
        AnchorPane center = new AnchorPane();
        Scene scene = new Scene(bp);
        List<Dictionary> accountList = Local.system.getAccount();

        ObservableList cbList = FXCollections.observableArrayList(accountList.stream().map(Dictionary::getText).collect(Collectors.toList()));
        ChoiceBox cb = new ChoiceBox(cbList);
        cb.setValue(accountList.get(0).getText());
        cb.setPrefWidth(110);

        ObservableList<Plan> list = FXCollections.observableArrayList();
        list.addAll(Local.plan);

        TableColumn<Plan, String> interval = new TableColumn<>("颗粒度");
        interval.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getInterval()));
        interval.setPrefWidth(60);
        TableColumn<Plan, String> date = new TableColumn<>("日期");
        date.setCellValueFactory(param -> new SimpleObjectProperty<>(StringUtil.dateToString(param.getValue().getDate(), StringUtil.SIMPLE_DATE__M_D_FORMAT)));
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

        tableView = new TableView<>(list);
        tableView.getColumns().addAll(interval, date, type, classify, money, note);

        AnchorPane.setLeftAnchor(cb, 10.0);
        AnchorPane.setTopAnchor(cb, 10.0);
        AnchorPane.setLeftAnchor(addPlan, 130.0);
        AnchorPane.setTopAnchor(addPlan, 10.0);
        AnchorPane.setLeftAnchor(editPlan, 200.0);
        AnchorPane.setTopAnchor(editPlan, 10.0);
        AnchorPane.setLeftAnchor(deletePlan, 270.0);
        AnchorPane.setTopAnchor(deletePlan, 10.0);
        AnchorPane.setLeftAnchor(viewPlan, 340.0);
        AnchorPane.setTopAnchor(viewPlan, 10.0);
        AnchorPane.setLeftAnchor(tableView, 10.0);
        AnchorPane.setTopAnchor(tableView, 40.0);

        ObservableList<Record> planList2 = FXCollections.observableArrayList();
        planList2.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(1000), null));
        planList2.add(new Record(1L, new Date(), "支出", "房租", BigDecimal.valueOf(2000), null));
        planList2.add(new Record(1L, new Date(), "支出", "生活费", BigDecimal.valueOf(3000), null));
        planList2.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(4000), null));
        planList2.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(5000), null));
        planList2.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(6000), null));
        planList2.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(7000), null));


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

        planTableView = new TableView<>(planList2);
        planTableView.getColumns().addAll(planDate, planType, planClassify, planMoney, planNote);

        AnchorPane.setLeftAnchor(planTableView, 10.0);
        AnchorPane.setTopAnchor(planTableView, 40.0);

//        leftPlan.getChildren().addAll(planTableView);
        planTableView.setPrefWidth(550);
        planTableView.setPrefHeight(800);

        left.getChildren().addAll(cb, addPlan, editPlan, deletePlan, viewPlan, tableView);
        tableView.setPrefWidth(550);
        tableView.setPrefHeight(800);


        ObservableList<Record> recordList = FXCollections.observableArrayList();
        recordList.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(1000), null));
        recordList.add(new Record(1L, new Date(), "支出", "房租", BigDecimal.valueOf(2000), null));
        recordList.add(new Record(1L, new Date(), "支出", "生活费", BigDecimal.valueOf(3000), null));
        recordList.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(4000), null));
        recordList.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(5000), null));
        recordList.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(6000), null));
        recordList.add(new Record(1L, new Date(), "收入", "工资", BigDecimal.valueOf(7000), null));


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
        recordNote.setPrefWidth(220);

        recordTableView = new TableView<>(recordList);
        recordTableView.getColumns().addAll(recordDate, recordType, recordClassify, recordMoney, recordNote);

        AnchorPane.setLeftAnchor(addRecord, 10.0);
        AnchorPane.setTopAnchor(addRecord, 10.0);
        AnchorPane.setLeftAnchor(editRecord, 80.0);
        AnchorPane.setTopAnchor(editRecord, 10.0);
        AnchorPane.setLeftAnchor(deleteRecord, 150.0);
        AnchorPane.setTopAnchor(deleteRecord, 10.0);
        AnchorPane.setLeftAnchor(recordTableView, 10.0);
        AnchorPane.setTopAnchor(recordTableView, 40.0);

        center.getChildren().addAll(addRecord, editRecord, deleteRecord, recordTableView);
        recordTableView.setPrefWidth(550);
        recordTableView.setPrefHeight(800);

        bp.setLeft(left);
        bp.setCenter(center);
        setWidth(1200);
        setHeight(900);
        setTitle("Index");
        setResizable(true);
        return scene;
    }

    @Override
    public void action() {
        IndexAction indexAction = new IndexAction();
        addPlan.setOnAction(e -> new AddPlanModalPanel(Main.primaryStage).show());
        editPlan.setOnAction(e -> {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                new ErrorPromptPanel(Main.primaryStage).show("请选择要编辑的计划！");
            } else {
                new AddPlanModalPanel(Main.primaryStage).setPlan(tableView.getSelectionModel().getSelectedItem()).show();
            }
        });
        deletePlan.setOnAction(e -> {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                new ErrorPromptPanel(Main.primaryStage).show("请选择要删除的计划！");
            } else {
                Plan plan = tableView.getSelectionModel().getSelectedItem();
                tableView.getItems().remove(plan);
                Local.removePlan(plan);
            }
        });
        viewPlan.setOnAction(e -> {
            if (isViewPlan) {
                addPlan.setDisable(false);
                editPlan.setDisable(false);
                deletePlan.setDisable(false);
                viewPlan.setText("计划预览");
                left.getChildren().remove(planTableView);
                left.getChildren().add(tableView);
                isViewPlan = false;
            } else {
                addPlan.setDisable(true);
                editPlan.setDisable(true);
                deletePlan.setDisable(true);
                viewPlan.setText("查看计划");
                left.getChildren().remove(tableView);
                left.getChildren().add(planTableView);
                isViewPlan = true;
            }
        });
        addRecord.setOnAction(e -> PanelUtil.show(MainPanel.LOGIN_PANEL));

    }
}
