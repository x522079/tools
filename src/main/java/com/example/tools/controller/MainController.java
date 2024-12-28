package com.example.tools.controller;

import com.example.tools.service.JsonService;
import com.example.tools.service.Base64Service;
import com.example.tools.service.UrlService;
import com.example.tools.service.TimeService;
import com.example.tools.model.TimeCalculation;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.time.Duration;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.TableColumn;

public class MainController {
    @FXML
    private TextArea jsonInput;
    @FXML
    private TextArea jsonOutput;
    @FXML
    private TextArea base64Input;
    @FXML
    private TextArea base64Output;
    @FXML
    private TextArea urlInput;
    @FXML
    private TextArea urlOutput;
    @FXML
    private TextField startTimeInput;
    @FXML
    private TextField endTimeInput;
    @FXML
    private TableView<TimeCalculation> timeResultTable;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private TableColumn<TimeCalculation, Boolean> selectColumn;
    @FXML
    private TableColumn<TimeCalculation, String> startTimeColumn;
    @FXML
    private TableColumn<TimeCalculation, String> endTimeColumn;
    @FXML
    private TableColumn<TimeCalculation, String> resultColumn;

    private final JsonService jsonService = new JsonService();
    private final Base64Service base64Service = new Base64Service();
    private final UrlService urlService = new UrlService();
    private final TimeService timeService = new TimeService();
    private final ObservableList<TimeCalculation> timeCalculations = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        timeResultTable.setItems(timeCalculations);
        
        // 设置选择列的 cellFactory
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
        selectColumn.setEditable(true);
        
        // 设置其他列的 cellValueFactory
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
        
        // 设置表格可编辑
        timeResultTable.setEditable(true);
        
        // 监听选择变化，更新总时间
        timeCalculations.addListener((ListChangeListener<TimeCalculation>) c -> updateTotalTime());
        
        // 添加对每个项目选中状态变化的监听
        timeResultTable.getItems().forEach(item -> 
            item.selectedProperty().addListener((obs, oldVal, newVal) -> updateTotalTime())
        );
    }

    @FXML
    protected void formatJson() {
        try {
            String input = jsonInput.getText();
            String formatted = jsonService.format(input);
            jsonOutput.setText(formatted);
        } catch (Exception e) {
            showError("JSON格式化错误", e.getMessage());
        }
    }

    @FXML
    protected void compressJson() {
        try {
            String input = jsonInput.getText();
            String compressed = jsonService.compress(input);
            jsonOutput.setText(compressed);
        } catch (Exception e) {
            showError("JSON压缩错误", e.getMessage());
        }
    }

    @FXML
    protected void encodeBase64() {
        try {
            String input = base64Input.getText();
            String encoded = base64Service.encode(input);
            base64Output.setText(encoded);
        } catch (Exception e) {
            showError("Base64编码错误", e.getMessage());
        }
    }

    @FXML
    protected void decodeBase64() {
        try {
            String input = base64Input.getText();
            String decoded = base64Service.decode(input);
            base64Output.setText(decoded);
        } catch (Exception e) {
            showError("Base64解码错误", e.getMessage());
        }
    }

    @FXML
    protected void encodeUrl() {
        try {
            String input = urlInput.getText();
            String encoded = urlService.encode(input);
            urlOutput.setText(encoded);
        } catch (Exception e) {
            showError("URL编码错误", e.getMessage());
        }
    }

    @FXML
    protected void decodeUrl() {
        try {
            String input = urlInput.getText();
            String decoded = urlService.decode(input);
            urlOutput.setText(decoded);
        } catch (Exception e) {
            showError("URL解码错误", e.getMessage());
        }
    }

    @FXML
    protected void calculateTime() {
        try {
            String startTime = startTimeInput.getText();
            String endTime = endTimeInput.getText();
            Duration duration = timeService.calculateDifference(startTime, endTime);
            String result = timeService.formatDuration(duration);
            
            TimeCalculation calculation = new TimeCalculation(startTime, endTime, result, duration);
            // 添加对新项目选中状态变化的监听
            calculation.selectedProperty().addListener((obs, oldVal, newVal) -> updateTotalTime());
            timeCalculations.add(calculation);
            
            // 清空输入框
            startTimeInput.clear();
            endTimeInput.clear();
        } catch (Exception e) {
            showError("时间计算错误", e.getMessage());
        }
    }

    @FXML
    protected void clearTimeCalculations() {
        timeCalculations.clear();
        updateTotalTime();
    }

    private void updateTotalTime() {
        Duration total = timeCalculations.stream()
            .filter(TimeCalculation::isSelected)
            .map(TimeCalculation::getDuration)
            .reduce(Duration.ZERO, Duration::plus);
        
        totalTimeLabel.setText(timeService.formatDuration(total));
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 