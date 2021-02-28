package com.java.fx.Panel.Modal;

import com.java.fx.Panel.IndexPanel;
import com.java.fx.Panel.Prompt.ErrorPromptPanel;
import com.java.fx.Util.DataUtil;
import com.java.fx.Util.Local;
import com.java.fx.Util.StringUtil;
import com.java.fx.model.Dictionary;
import com.java.fx.model.Plan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class PlanModalPanel extends SystemModal{
    private Plan plan;
    private DatePicker datePickerDate;
    private ChoiceBox dateWeekCB;
    private ChoiceBox dateMonthCB;

    public PlanModalPanel(Stage parent) {
        super(parent);
    }

    public PlanModalPanel setPlan(Plan plan) {
        this.plan = plan;
        return this;
    }

    @Override
    public void show() {
        AnchorPane ac = new AnchorPane();

        double startTop = 33.0;
        double index = 0.0;
        double line = 50.0;
        Label labelInterval = new Label("颗粒度:");
        AnchorPane.setLeftAnchor(labelInterval, 35.0);
        AnchorPane.setTopAnchor(labelInterval, startTop + (index++ * line));
        Label labelDate = new Label("日期:");
        AnchorPane.setLeftAnchor(labelDate, 35.0);
        AnchorPane.setTopAnchor(labelDate, startTop + (index++ * line));
        Label labelType = new Label("类型:");
        AnchorPane.setLeftAnchor(labelType, 35.0);
        AnchorPane.setTopAnchor(labelType, startTop + (index++ * line));
        Label labelClassify = new Label("分类:");
        AnchorPane.setLeftAnchor(labelClassify, 35.0);
        AnchorPane.setTopAnchor(labelClassify, startTop + (index++ * line));
        Label labelMoney = new Label("金额:");
        AnchorPane.setLeftAnchor(labelMoney, 35.0);
        AnchorPane.setTopAnchor(labelMoney, startTop + (index++ * line));
        Label labelNote = new Label("备注:");
        AnchorPane.setLeftAnchor(labelNote, 35.0);
        AnchorPane.setTopAnchor(labelNote, startTop + (index++ * line));

        startTop = 30.0;
        index = 0.0;
        line = 50.0;
        ObservableList intervalOL = FXCollections.observableArrayList(DataUtil.INTERVAL);
        ChoiceBox intervalCB = new ChoiceBox(intervalOL);
        AnchorPane.setLeftAnchor(intervalCB, 85.0);
        AnchorPane.setTopAnchor(intervalCB, startTop + (index++ * line));
        intervalCB.setValue(DataUtil.INTERVAL.get(0));

        datePickerDate = new DatePicker(LocalDate.now());
        datePickerDate.setEditable(false);
        datePickerDate.setConverter(StringUtil.dateStringConverter());
        AnchorPane.setLeftAnchor(datePickerDate, 85.0);
        AnchorPane.setTopAnchor(datePickerDate, startTop + (index * line));
        datePickerDate.setVisible(false);
        datePickerDate.setManaged(false);

        ObservableList dateWeekOL = FXCollections.observableArrayList(DataUtil.WEEK);
        dateWeekCB = new ChoiceBox(dateWeekOL);
        AnchorPane.setLeftAnchor(dateWeekCB, 85.0);
        AnchorPane.setTopAnchor(dateWeekCB, startTop + (index * line));
        dateWeekCB.setValue(DataUtil.WEEK.get(0));
        dateWeekCB.setVisible(false);
        dateWeekCB.setManaged(false);

        ObservableList dateMonthOL = FXCollections.observableArrayList(DataUtil.MONTH);
        dateMonthCB = new ChoiceBox(dateMonthOL);
        AnchorPane.setLeftAnchor(dateMonthCB, 85.0);
        AnchorPane.setTopAnchor(dateMonthCB, startTop + (index++ * line));
        dateMonthCB.setValue(DataUtil.MONTH.get(0));

        ToggleGroup tg = new ToggleGroup();
        RadioButton income  = new RadioButton(DataUtil.INCOME);
        RadioButton pay = new RadioButton(DataUtil.PAY);
        income.setToggleGroup(tg);
        pay.setToggleGroup(tg);
        tg.selectToggle(income);
        AnchorPane.setLeftAnchor(income, 85.0);
        AnchorPane.setTopAnchor(income, startTop + (index * line));
        AnchorPane.setLeftAnchor(pay, 145.0);
        AnchorPane.setTopAnchor(pay, startTop + (index++ * line));

        List<Dictionary> incomeClassify = Local.system.getIncomeClassify();
        List<Dictionary> payClassify = Local.system.getPayClassify();

        ObservableList listClassifyI = FXCollections.observableArrayList(incomeClassify.stream().map(Dictionary::getText).collect(Collectors.toList()));
        ObservableList listClassifyP = FXCollections.observableArrayList(payClassify.stream().map(Dictionary::getText).collect(Collectors.toList()));
        ChoiceBox cbClassify = new ChoiceBox(listClassifyI);
        AnchorPane.setLeftAnchor(cbClassify, 85.0);
        AnchorPane.setTopAnchor(cbClassify, startTop + (index++ * line));
        cbClassify.setValue(incomeClassify.get(0).getText());
        tg.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if ((DataUtil.INCOME).equals(((RadioButton) new_toggle).getText())) {
                cbClassify.setItems(listClassifyI);
                cbClassify.setValue(incomeClassify.get(0).getText());
            }
            if ((DataUtil.PAY).equals(((RadioButton) new_toggle).getText())) {
                cbClassify.setItems(listClassifyP);
                cbClassify.setValue(payClassify.get(0).getText());
            }
        });

        TextField testFiledMoney = new TextField();
        AnchorPane.setLeftAnchor(testFiledMoney, 85.0);
        AnchorPane.setTopAnchor(testFiledMoney, startTop + (index++ * line));

        TextField testFiledNote = new TextField();
        AnchorPane.setLeftAnchor(testFiledNote, 85.0);
        AnchorPane.setTopAnchor(testFiledNote, startTop + (index++ * line));

        if (plan != null) {
            intervalCB.setValue(plan.getInterval());
            refreshDateShow(plan.getInterval(), plan.getDate());
//            datePickerDate.setValue(StringUtil.stringToLocalDate(plan.getDate(), StringUtil.SIMPLE_DATE__M_D_FORMAT));
            if ((DataUtil.INCOME).equals(plan.getType())) {
                income.setSelected(true);
            }
            if ((DataUtil.PAY).equals(plan.getType())) {
                pay.setSelected(true);
            }
            cbClassify.setValue(plan.getClassify());
            testFiledMoney.setText(String.valueOf(plan.getMoney()));
            testFiledNote.setText(plan.getNote());
        }

        ac.getChildren().addAll(labelInterval, labelDate, labelType, labelClassify, labelMoney, labelNote,
                intervalCB, datePickerDate, dateMonthCB, dateWeekCB, income, pay, cbClassify, testFiledMoney, testFiledNote);
        ac.setPrefWidth(width);
        height = 350;
        ac.setPrefHeight(height);
        recountStageSize(width, height);
        bp.setCenter(ac);
        setTitle("计划");
        setBottomButton(CANCEL_F_CLOSE);
        setBottomButton(SURE_F_SUBMIT, e -> {
            if (!StringUtil.isMoney(testFiledMoney.getText())) {
                new ErrorPromptPanel(stage).show("金额不合法！");
                return false;
            }
            if (plan == null) {
                String dateString = "";
                switch (intervalCB.getValue().toString()) {
                    case DataUtil.INTERVAL_MONTH:
                        dateString = dateMonthCB.getSelectionModel().getSelectedItem().toString();
                        break;
                    case DataUtil.INTERVAL_WEEK:
                        dateString = dateWeekCB.getSelectionModel().getSelectedItem().toString();
                        break;
                    case DataUtil.INTERVAL_CUS:
                        dateString = StringUtil.localDateToString(datePickerDate.getValue(), StringUtil.SIMPLE_DATE_FORMAT);
                        break;
                }
                Plan newPlan = new Plan(
                        Local.accountId,
                        String.valueOf(intervalCB.getValue()),
                        dateString,
                        ((RadioButton) tg.getSelectedToggle()).getText(),
                        String.valueOf(cbClassify.getValue()),
                        new BigDecimal(testFiledMoney.getText()),
                        testFiledNote.getText()
                );
                Local.addPlan(newPlan);
                IndexPanel.planTV.getItems().add(newPlan);
            } else {
                String dateString = "";
                switch (intervalCB.getValue().toString()) {
                    case DataUtil.INTERVAL_MONTH:
                        dateString = dateMonthCB.getSelectionModel().getSelectedItem().toString();
                        break;
                    case DataUtil.INTERVAL_WEEK:
                        dateString = dateWeekCB.getSelectionModel().getSelectedItem().toString();
                        break;
                    case DataUtil.INTERVAL_CUS:
                        dateString = StringUtil.localDateToString(datePickerDate.getValue(), StringUtil.SIMPLE_DATE_FORMAT);
                        break;
                }
                plan.setInterval(String.valueOf(intervalCB.getValue()));
                plan.setDate(dateString);
                plan.setType(((RadioButton) tg.getSelectedToggle()).getText());
                plan.setClassify(String.valueOf(cbClassify.getValue()));
                plan.setMoney(new BigDecimal(testFiledMoney.getText()));
                plan.setNote(testFiledNote.getText());
                IndexPanel.planTV.refresh();
            }
            return true;
        });
        intervalCB.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                refreshDateShow(DataUtil.INTERVAL.get(newValue.intValue()), null);
            }
        });
       super.show();
    }

    private void refreshDateShow(String interval, String data) {
        if (DataUtil.INTERVAL_MONTH.equals(interval)) {
            datePickerDate.setVisible(false);
            datePickerDate.setManaged(false);
            dateMonthCB.setVisible(true);
            dateMonthCB.setManaged(true);
            dateWeekCB.setVisible(false);
            dateWeekCB.setManaged(false);
            dateMonthCB.setValue(data == null ? DataUtil.MONTH.get(0) : data);
        }

        if (DataUtil.INTERVAL_WEEK.equals(interval)) {
            datePickerDate.setVisible(false);
            datePickerDate.setManaged(false);
            dateMonthCB.setVisible(false);
            dateMonthCB.setManaged(false);
            dateWeekCB.setVisible(true);
            dateWeekCB.setManaged(true);
            dateWeekCB.setValue(data == null ? DataUtil.WEEK.get(0) : data);
        }

        if (DataUtil.INTERVAL_CUS.equals(interval)) {
            datePickerDate.setVisible(true);
            datePickerDate.setManaged(true);
            dateMonthCB.setVisible(false);
            dateMonthCB.setManaged(false);
            dateWeekCB.setVisible(false);
            dateWeekCB.setManaged(false);
            datePickerDate.setValue(data == null ? LocalDate.now(): StringUtil.stringToLocalDate(data, StringUtil.SIMPLE_DATE_FORMAT));
        }
    }
}
