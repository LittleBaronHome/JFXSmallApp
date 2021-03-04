package com.java.fx.Panel.Dialog;

import com.java.fx.Panel.Modal.SystemModal;
import com.java.fx.Util.DataUtil;
import com.java.fx.Util.StringUtil;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @author qiaojiyuan
 * @date 2021/1/28
 */
public class PlanViewInfoPanel extends SystemModal {

    public PlanViewInfoPanel(Stage parent) {
        super(parent);
    }

    @Override
    public void show() {
        AnchorPane ac = new AnchorPane();

        double startTop = 33.0;
        double index = 0.0;
        double line = 50.0;
        Label labelInitMoney = new Label("初始金额:");
        AnchorPane.setLeftAnchor(labelInitMoney, 35.0);
        AnchorPane.setTopAnchor(labelInitMoney, startTop + (index++ * line));
        Label labelViewDate = new Label("预览时间段:");
        AnchorPane.setLeftAnchor(labelViewDate, 35.0);
        AnchorPane.setTopAnchor(labelViewDate, startTop + (index++ * line));
        Label labelViewDateRange = new Label("自定义时间段:");
        AnchorPane.setLeftAnchor(labelViewDateRange, 35.0);
        AnchorPane.setTopAnchor(labelViewDateRange, startTop + (index * line));
        Label labelTo = new Label("至:");
        AnchorPane.setLeftAnchor(labelTo, 280.0);
        AnchorPane.setTopAnchor(labelTo, startTop + (index * line));

        startTop = 30.0;
        index = 0.0;
        line = 50.0;
        TextField initMoney = new TextField();
        AnchorPane.setLeftAnchor(initMoney, 105.0);
        AnchorPane.setTopAnchor(initMoney, startTop + (index++ * line));
        initMoney.setText("0");

        ToggleGroup tg = new ToggleGroup();
        RadioButton viewYear  = new RadioButton(DataUtil.VIEW_CURRENT_YEAR);
        RadioButton viewMonth = new RadioButton(DataUtil.VIEW_CURRENT_MONTH);
        RadioButton viewCustomer = new RadioButton(DataUtil.VIEW_CUSTOMER);
        viewYear.setToggleGroup(tg);
        viewMonth.setToggleGroup(tg);
        viewCustomer.setToggleGroup(tg);
        tg.selectToggle(viewYear);
        AnchorPane.setLeftAnchor(viewYear, 105.0);
        AnchorPane.setTopAnchor(viewYear, startTop + (index * line));
        AnchorPane.setLeftAnchor(viewMonth, 165.0);
        AnchorPane.setTopAnchor(viewMonth, startTop + (index * line));
        AnchorPane.setLeftAnchor(viewCustomer, 225.0);
        AnchorPane.setTopAnchor(viewCustomer, startTop + (index++ * line));

        DatePicker datePickerDateStart = new DatePicker(LocalDate.now());
        datePickerDateStart.setPrefWidth(150);
        datePickerDateStart.setEditable(false);
        datePickerDateStart.setConverter(StringUtil.dateStringConverter());
        AnchorPane.setLeftAnchor(datePickerDateStart, 125.0);
        AnchorPane.setTopAnchor(datePickerDateStart, startTop + (index * line));
        datePickerDateStart.setDisable(true);

        DatePicker datePickerDateEnd = new DatePicker(LocalDate.now());
        datePickerDateEnd.setPrefWidth(150);
        datePickerDateEnd.setEditable(false);
        datePickerDateEnd.setConverter(StringUtil.dateStringConverter());
        AnchorPane.setLeftAnchor(datePickerDateEnd, 305.0);
        AnchorPane.setTopAnchor(datePickerDateEnd, startTop + (index * line));
        datePickerDateEnd.setDisable(true);

//        tg.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
//            switch (((RadioButton) new_toggle).getText()) {
//                case DataUtil.VIEW_CURRENT_YEAR: case DataUtil.VIEW_CURRENT_MONTH:
//                    datePickerDateStart.setDisable(true);
//                    datePickerDateEnd.setDisable(true);
//                    break;
//                case DataUtil.VIEW_CUSTOMER:
//                    datePickerDateStart.setDisable(false);
//                    datePickerDateEnd.setDisable(false);
//                    break;
//            }
//        });

        ac.getChildren().addAll(labelInitMoney, labelViewDate, labelViewDateRange,
                initMoney, viewYear, viewMonth, viewCustomer, datePickerDateStart, labelTo, datePickerDateEnd);
        width = 500;
        ac.setPrefWidth(width);
        height = 250;
        ac.setPrefHeight(height);
        recountStageSize(width, height);
        bp.setCenter(ac);
        setTitle("计划预览");
        setBottomButton(CANCEL_F_CLOSE);
        setBottomButton(SURE_F_SUBMIT);
       super.show();
    }

}
