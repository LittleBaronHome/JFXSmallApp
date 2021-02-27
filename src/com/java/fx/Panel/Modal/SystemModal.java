package com.java.fx.Panel.Modal;

import com.java.fx.Util.StringUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;

/**
 * @author qiaojiyuan
 * @date 2021/2/2
 */
public class SystemModal extends ModalPanel {
    protected double width = 300;
    protected int line = 2;
    protected int lineHeight = 30;
    protected double height = 300;
    protected Label label;

    public SystemModal(Stage parent) {
        super(parent);
    }

    protected FlowPane init(String text) {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);

        label = new Label(text);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(18));
        int size = 0;
        String[] labelArray = label.getText().split("\n");
        for (String str : labelArray) {
            int countSize = StringUtil.countSize(str);
            size = size > countSize ? size : countSize;
        }
        width = size > width ? size : width;
        height = ((labelArray.length > line ? labelArray.length : line) + 1) * lineHeight;
        flowPane.setPrefWidth(width);
        flowPane.setPrefHeight(height);
        flowPane.getChildren().add(label);
        recountStageSize(width, height);
        return flowPane;
    }

    @Override
    public void show() {
        super.show();
    }
}
