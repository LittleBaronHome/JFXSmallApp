package com.java.fx.Panel;

import com.java.fx.Main;
import com.java.fx.Panel.Dialog.PlanModalPanel;
import com.java.fx.Panel.Dialog.RecordModalPanel;
import com.java.fx.Panel.Prompt.ErrorPromptPanel;
import com.java.fx.Util.DataUtil;
import com.java.fx.Util.Local;
import com.java.fx.Util.StringUtil;
import com.java.fx.model.Dictionary;
import com.java.fx.model.Plan;
import com.java.fx.model.PlanView;
import com.java.fx.model.Record;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
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
    private Label initMoneyLabel = new Label("初始金额: ");
    private TextField initMoney = new TextField();
    private static Label currentMoney = new Label();
    private Label planViewCurrentMoney = new Label();
    private Label planViewInitLabelMoney = new Label("初始金额:");
    private TextField planViewInitMoney = new TextField();
    private DatePicker datePickerDateStart = new DatePicker(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
    private DatePicker datePickerDateEnd = new DatePicker(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
    private Label labelTo = new Label("至:");
    private ToggleGroup tg = new ToggleGroup();
    RadioButton viewYear  = new RadioButton(DataUtil.VIEW_CURRENT_YEAR);
    RadioButton viewMonth = new RadioButton(DataUtil.VIEW_CURRENT_MONTH);
    RadioButton viewCustomer = new RadioButton(DataUtil.VIEW_CUSTOMER);
    private List<PlanView> planViews = new ArrayList<>();

    public static TableView<Plan> planTV;
    public static TableView<Record> recordTV;
    public static TableView<PlanView> planViewTV;

    private ChoiceBox accountListCb;
    private ObservableList<Plan> planListOL;
    private ObservableList<Record> recordListOL;
    private ObservableList<PlanView> planViewOL;

    private AnchorPane left = new AnchorPane();
    private AnchorPane right = new AnchorPane();
    private boolean isViewPlan = false;

    @Override
    public Scene init() {
        BorderPane bp = new BorderPane();
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
        recordNote.setPrefWidth(220);
        recordTV = new TableView<>(recordListOL);
        recordTV.setPrefWidth(550);
        recordTV.setPrefHeight(770);
        recordTV.getColumns().addAll(recordDate, recordType, recordClassify, recordMoney, recordNote);

        // 计划Table
        planListOL = FXCollections.observableArrayList();
        planListOL.setAll(Local.plan);
        TableColumn<Plan, String> interval = new TableColumn<>("颗粒度");
        interval.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getInterval()));
        interval.setPrefWidth(60);
        TableColumn<Plan, String> date = new TableColumn<>("日期");
        date.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getDate()));
        date.setPrefWidth(85);
        TableColumn<Plan, String> type = new TableColumn<>("类型");
        type.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getType()));
        type.setPrefWidth(60);
        TableColumn<Plan, String> classify = new TableColumn<>("分类");
        classify.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getClassify()));
        classify.setPrefWidth(85);
        TableColumn<Plan, BigDecimal> money = new TableColumn<>("金额");
        money.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMoney()));
        money.setPrefWidth(90);
        TableColumn<Plan, String> note = new TableColumn<>("备注");
        note.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNote()));
        note.setPrefWidth(168);
        planTV = new TableView<>(planListOL);
        planTV.setPrefWidth(550);
        planTV.setPrefHeight(770);
        planTV.getColumns().addAll(interval, date, type, classify, money, note);

        // plan预览Table
        planViewOL = FXCollections.observableArrayList();
        TableColumn<PlanView, String> planDate = new TableColumn<>("日期");
        planDate.setCellValueFactory(param -> new SimpleObjectProperty<>(StringUtil.dateToString(param.getValue().getDate())));
        planDate.setPrefWidth(80);
        TableColumn<PlanView, String> planType = new TableColumn<>("类型");
        planType.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getType()));
        planType.setPrefWidth(60);
        TableColumn<PlanView, String> planClassify = new TableColumn<>("分类");
        planClassify.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getClassify()));
        planClassify.setPrefWidth(95);
        TableColumn<PlanView, BigDecimal> planMoney = new TableColumn<>("金额");
        planMoney.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getMoney()));
        planMoney.setPrefWidth(90);
        TableColumn<PlanView, String> planNote = new TableColumn<>("备注");
        planNote.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNote()));
        planNote.setPrefWidth(225);
        planViewTV = new TableView<>(planViewOL);
        planViewTV.setPrefWidth(550);
        planViewTV.setPrefHeight(770);
        planViewTV.getColumns().addAll(planDate, planType, planClassify, planMoney, planNote);

        // 初始化金额 - 当前金额
        initMoneyLabel.setStyle("-fx-font-weight: bold");
        currentMoney.setStyle("-fx-font-weight: bold");
        initMoney.setAlignment(Pos.CENTER_RIGHT);
        initMoney.setPromptText("初始金额");
        initMoney.setText(Local.account.getData().toString());
        refreshCurrentMoney();

        // 计划预览初始金额输入框
        planViewInitLabelMoney.setStyle("-fx-font-weight: bold");
        planViewCurrentMoney.setStyle("-fx-font-weight: bold");
        planViewInitMoney.setText(Local.account.getData().toString());
        planViewInitMoney.setPrefWidth(100);
        planViewInitMoney.setDisable(true);
        refreshPlanViewCurrentMoney();

        // 计划预览开始日期
        datePickerDateStart.setPrefWidth(120);
        datePickerDateStart.setEditable(false);
        datePickerDateStart.setConverter(StringUtil.dateStringConverter());
        datePickerDateStart.setDisable(true);
        // 计划预览结束日期
        datePickerDateEnd.setPrefWidth(120);
        datePickerDateEnd.setEditable(false);
        datePickerDateEnd.setConverter(StringUtil.dateStringConverter());
        datePickerDateEnd.setDisable(true);

        // 计划预览类型
        viewYear.setToggleGroup(tg);
        viewMonth.setToggleGroup(tg);
        viewCustomer.setToggleGroup(tg);
        tg.selectToggle(viewMonth);
        viewYear.setDisable(true);
        viewMonth.setDisable(true);
        viewCustomer.setDisable(true);

        // 设置位置
        // 左第一排
        AnchorPane.setLeftAnchor(accountListCb, 10.0);
        AnchorPane.setTopAnchor(accountListCb, 10.0);
        AnchorPane.setLeftAnchor(addRecord, 130.0);
        AnchorPane.setTopAnchor(addRecord, 10.0);
        AnchorPane.setLeftAnchor(editRecord, 200.0);
        AnchorPane.setTopAnchor(editRecord, 10.0);
        AnchorPane.setLeftAnchor(deleteRecord, 270.0);
        AnchorPane.setTopAnchor(deleteRecord, 10.0);
        // 左第二排
        AnchorPane.setTopAnchor(initMoneyLabel, 48.0);
        AnchorPane.setLeftAnchor(initMoneyLabel, 10.0);
        AnchorPane.setTopAnchor(initMoney, 45.0);
        AnchorPane.setLeftAnchor(initMoney, 65.0);
        AnchorPane.setTopAnchor(currentMoney, 48.0);
        AnchorPane.setLeftAnchor(currentMoney, 240.0);
        // 记录列表
        AnchorPane.setLeftAnchor(recordTV, 10.0);
        AnchorPane.setTopAnchor(recordTV, 80.0);
        left.getChildren().addAll(accountListCb, addRecord, editRecord, deleteRecord,
                initMoneyLabel, initMoney, currentMoney,
                recordTV);

        // 右第一排
        AnchorPane.setLeftAnchor(addPlan, 10.0);
        AnchorPane.setTopAnchor(addPlan, 10.0);
        AnchorPane.setLeftAnchor(editPlan, 80.0);
        AnchorPane.setTopAnchor(editPlan, 10.0);
        AnchorPane.setLeftAnchor(deletePlan, 150.0);
        AnchorPane.setTopAnchor(deletePlan, 10.0);
        AnchorPane.setLeftAnchor(viewPlan, 220.0);
        AnchorPane.setTopAnchor(viewPlan, 10.0);
        AnchorPane.setLeftAnchor(planViewInitLabelMoney, 290.0);
        AnchorPane.setTopAnchor(planViewInitLabelMoney, 15.0);
        AnchorPane.setLeftAnchor(planViewInitMoney, 345.0);
        AnchorPane.setTopAnchor(planViewInitMoney, 10.0);
        AnchorPane.setLeftAnchor(planViewCurrentMoney, 450.0);
        AnchorPane.setTopAnchor(planViewCurrentMoney, 15.0);
        // 右第二排
        AnchorPane.setLeftAnchor(viewYear, 10.0);
        AnchorPane.setTopAnchor(viewYear, 48.0);
        AnchorPane.setLeftAnchor(viewMonth, 65.0);
        AnchorPane.setTopAnchor(viewMonth, 48.0);
        AnchorPane.setLeftAnchor(viewCustomer, 120.0);
        AnchorPane.setTopAnchor(viewCustomer, 48.0);
        AnchorPane.setLeftAnchor(datePickerDateStart, 190.0);
        AnchorPane.setTopAnchor(datePickerDateStart, 43.0);
        AnchorPane.setLeftAnchor(labelTo, 320.0);
        AnchorPane.setTopAnchor(labelTo, 48.0);
        AnchorPane.setLeftAnchor(datePickerDateEnd, 340.0);
        AnchorPane.setTopAnchor(datePickerDateEnd, 43.0);
        // 计划列表
        AnchorPane.setLeftAnchor(planTV, 10.0);
        AnchorPane.setTopAnchor(planTV, 80.0);
        // 计划预览列表
        AnchorPane.setLeftAnchor(planViewTV, 10.0);
        AnchorPane.setTopAnchor(planViewTV, 80.0);
        right.getChildren().addAll(addPlan, editPlan, deletePlan, viewPlan, planViewInitLabelMoney, planViewInitMoney, planViewCurrentMoney,
                viewYear, viewMonth, viewCustomer, datePickerDateStart, labelTo, datePickerDateEnd,
                planTV);

        bp.setLeft(left);
        bp.setCenter(right);
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
        tg.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            switch (((RadioButton) new_toggle).getText()) {
                case DataUtil.VIEW_CURRENT_YEAR:
                case DataUtil.VIEW_CURRENT_MONTH:
                    datePickerDateStart.setDisable(true);
                    datePickerDateEnd.setDisable(true);
                    break;
                case DataUtil.VIEW_CUSTOMER:
                    datePickerDateStart.setDisable(false);
                    datePickerDateEnd.setDisable(false);
                    break;
            }
            viewPlan();
        });
        datePickerDateStart.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue.compareTo(newValue) != 0) {
                viewPlan();
            }
        });
        datePickerDateEnd.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue.compareTo(newValue) != 0) {
                viewPlan();
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
            if (isViewPlan) {
                addPlan.setDisable(false);
                editPlan.setDisable(false);
                deletePlan.setDisable(false);
                viewPlan.setText("计划预览");
                right.getChildren().remove(planViewTV);
                right.getChildren().add(planTV);

                planViewInitMoney.setDisable(true);
                viewYear.setDisable(true);
                viewMonth.setDisable(true);
                viewCustomer.setDisable(true);
                datePickerDateStart.setDisable(true);
                datePickerDateEnd.setDisable(true);

                isViewPlan = false;
            } else {
                addPlan.setDisable(true);
                editPlan.setDisable(true);
                deletePlan.setDisable(true);
                viewPlan.setText("查看计划");
                right.getChildren().remove(planTV);
                right.getChildren().add(planViewTV);

                planViewInitMoney.setDisable(false);
                viewYear.setDisable(false);
                viewMonth.setDisable(false);
                viewCustomer.setDisable(false);
                if (DataUtil.VIEW_CUSTOMER.equals(((RadioButton) tg.getSelectedToggle()).getText())) {
                    datePickerDateStart.setDisable(false);
                    datePickerDateEnd.setDisable(false);
                }
                viewPlan();
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

    private void viewPlan() {
        LocalDate start = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate end = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        if (DataUtil.VIEW_CURRENT_YEAR.equals(((RadioButton) tg.getSelectedToggle()).getText())) {
            start = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
            end = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
        }
        if (DataUtil.VIEW_CUSTOMER.equals(((RadioButton) tg.getSelectedToggle()).getText())) {
            start = datePickerDateStart.getValue();
            end = datePickerDateEnd.getValue();
        }

        planViewOL.setAll(generatePlanViewList(start, end, Local.plan));
    }

    private List<PlanView> generatePlanViewList(LocalDate startDate, LocalDate endDate, List<Plan> planList) {
        if(startDate.compareTo(endDate) >= 0) {
            return new ArrayList<>();
        }
        List<Plan> monthPlanList = new ArrayList<>();
        List<Plan> weekPlanList = new ArrayList<>();
        List<Plan> cusPlanList = new ArrayList<>();
        List<PlanView> result = new ArrayList<>();
        for (Plan plan : planList) {
            if (DataUtil.INTERVAL_MONTH.equals(plan.getInterval())) {
                monthPlanList.add(plan);
                continue;
            }
            if (DataUtil.INTERVAL_WEEK.equals(plan.getInterval())) {
                weekPlanList.add(plan);
                continue;
            }
            if (DataUtil.INTERVAL_CUS.equals(plan.getInterval())) {
                cusPlanList.add(plan);
            }
        }

        if (monthPlanList.size() > 0) {
            LocalDate startFinal = startDate.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate start = startDate.with(TemporalAdjusters.firstDayOfMonth());
            while(start.compareTo(endDate) <= 0) {
                for (Plan plan : monthPlanList) {
                    LocalDate temp;
                    if (DataUtil.MONTH_LAST_DAY.equals(plan.getDate())) {
                        temp = start.with(TemporalAdjusters.lastDayOfMonth());
                    } else {
                        temp = start.withDayOfMonth(Integer.valueOf(plan.getDate()));
                    }

                    if (temp.compareTo(startFinal) >= 0 && temp.compareTo(endDate) <= 0) {
                        result.add(new PlanView(plan, temp));
                    }
                }
                start = start.plusMonths(1L);
            }
        }

        if (weekPlanList.size() > 0) {
            LocalDate startFinal = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate start = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            while(start.compareTo(endDate) <= 0) {
                for (Plan plan : weekPlanList) {
                    LocalDate temp = start.plusDays(DataUtil.WEEK_MAP.get(plan.getDate()));
                    if (temp.compareTo(startFinal) >= 0 && temp.compareTo(endDate) <= 0) {
                        result.add(new PlanView(plan, temp));
                    }
                }
                start = start.plusDays(7L);
            }
        }

        if (cusPlanList.size() > 0) {
            for (Plan plan : cusPlanList) {
                LocalDate temp = LocalDate.parse(plan.getDate());
                if (temp.compareTo(startDate) >= 0 && temp.compareTo(endDate) <= 0) {
                    result.add(new PlanView(plan, temp));
                }
            }
        }
        result.sort(Comparator.comparing(PlanView::getDate));
        planViews = result;
        refreshPlanViewCurrentMoney();
        return result;
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

    public void refreshPlanViewCurrentMoney() {
        BigDecimal currentM = new BigDecimal(planViewInitMoney.getText());
        for (PlanView r : planViews) {
            if (DataUtil.INCOME.equals(r.getType())) {
                currentM = currentM.add(r.getMoney());
            }
            if (DataUtil.PAY.equals(r.getType())) {
                currentM = currentM.subtract(r.getMoney());
            }
        }
        planViewCurrentMoney.setText("预计金额: " + currentM.toString());
    }
}
