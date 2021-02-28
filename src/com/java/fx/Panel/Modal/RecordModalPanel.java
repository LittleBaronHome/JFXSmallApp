package com.java.fx.Panel.Modal;

import com.java.fx.Panel.IndexPanel;
import com.java.fx.Panel.Prompt.ErrorPromptPanel;
import com.java.fx.Util.DataUtil;
import com.java.fx.Util.Local;
import com.java.fx.Util.StringUtil;
import com.java.fx.model.Dictionary;
import com.java.fx.model.Plan;
import com.java.fx.model.Record;
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
public class RecordModalPanel extends SystemModal{
    private Record record;

    public RecordModalPanel(Stage parent) {
        super(parent);
    }

    public RecordModalPanel setRecord(Record record) {
        this.record = record;
        return this;
    }

    @Override
    public void show() {
        AnchorPane ac = new AnchorPane();

        double startTop = 33.0;
        double index = 0.0;
        double line = 50.0;
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
        DatePicker datePickerDate = new DatePicker(LocalDate.now());
        datePickerDate.setEditable(false);
        datePickerDate.setConverter(StringUtil.dateStringConverter());
        AnchorPane.setLeftAnchor(datePickerDate, 85.0);
        AnchorPane.setTopAnchor(datePickerDate, startTop + (index++ * line));

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

        if (record != null) {
            datePickerDate.setValue(StringUtil.dateToLocalDate(record.getDate()));
            if ((DataUtil.INCOME).equals(record.getType())) {
                income.setSelected(true);
            }
            if ((DataUtil.PAY).equals(record.getType())) {
                pay.setSelected(true);
            }
            cbClassify.setValue(record.getClassify());
            testFiledMoney.setText(String.valueOf(record.getMoney()));
            testFiledNote.setText(record.getNote());
        }

        ac.getChildren().addAll(labelDate, labelType, labelClassify, labelMoney, labelNote,
                datePickerDate, income, pay, cbClassify, testFiledMoney, testFiledNote);
        ac.setPrefWidth(width);
        height = 350;
        ac.setPrefHeight(height);
        recountStageSize(width, height);
        bp.setCenter(ac);
        setTitle("记录");
        setBottomButton(CANCEL_F_CLOSE);
        setBottomButton(SURE_F_SUBMIT, e -> {
            if (!StringUtil.isMoney(testFiledMoney.getText())) {
                new ErrorPromptPanel(stage).show("金额不合法！");
                return false;
            }
            if (record == null) {
                Record newRecord = new Record(
                        Local.accountId,
                        StringUtil.stringToDate(datePickerDate.getValue()),
                        ((RadioButton) tg.getSelectedToggle()).getText(),
                        String.valueOf(cbClassify.getValue()),
                        new BigDecimal(testFiledMoney.getText()),
                        testFiledNote.getText()
                );
                Local.addRecord(newRecord);
                IndexPanel.recordTV.getItems().add(newRecord);
            } else {
                record.setDate(StringUtil.stringToDate(datePickerDate.getValue()));
                record.setType(((RadioButton) tg.getSelectedToggle()).getText());
                record.setClassify(String.valueOf(cbClassify.getValue()));
                record.setMoney(new BigDecimal(testFiledMoney.getText()));
                record.setNote(testFiledNote.getText());
                IndexPanel.recordTV.refresh();
            }
            IndexPanel.refreshCurrentMoney();
            return true;
        });
       super.show();
    }
}
