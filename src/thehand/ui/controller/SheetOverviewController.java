package thehand.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import thehand.ui.MainApp;
import thehand.ui.MainLine;
import thehand.ui.model.ConfigModel;
import thehand.ui.model.ParamModel;
import thehand.ui.model.SheetModel;

import java.io.File;
import java.io.IOException;

import static thehand.ui.MainApp.executorService;

public class SheetOverviewController {
    @FXML
    private TableView<SheetModel> excelTable;
    @FXML
    private TableColumn<SheetModel, String> sheetColumn;
    @FXML
    private TableColumn<SheetModel, Boolean> enableColumn;

    @FXML
    private TableView<ParamModel> paramTable;

    @FXML
    private TableColumn<ParamModel, String> keyColumn;

    @FXML
    private TableColumn<ParamModel, String> valueColumn;

    @FXML
    private Label sheetNameLabel;
    @FXML
    private Label excelTemplateLabel;
    @FXML
    private Label configXmlLabel;
    @FXML
    private Label connectionLabel;
    @FXML
    private Label progressText;
    @FXML
    private Label DBMsg;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button sqlDetailButton;
    @FXML
    private Button processButton;

    private MainApp mainApp;

    private ConfigModel configModel;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public SheetOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        sheetColumn.setCellValueFactory(cellData -> cellData.getValue().sheetNameProperty());
        keyColumn.setCellValueFactory(cellData -> cellData.getValue().keyProperty());
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
        enableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(enableColumn));
        enableColumn.setCellValueFactory(cellData -> cellData.getValue().enableProperty());

        excelTable.setEditable(true);
        paramTable.setEditable(true);
        sheetColumn.setEditable(false);
        enableColumn.setEditable(true);
        keyColumn.setEditable(false);
        valueColumn.setEditable(true);

        excelTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSheetDetails(newValue));


    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        excelTable.setItems(mainApp.getSheetList());
        paramTable.setItems(mainApp.getParamList());
        configModel = mainApp.getConfigModel();

        showSheetDetails(null);
        showExcelDetails(mainApp.getConfigModel());
    }

    public void showSheetDetails(SheetModel sheetModel) {

        if(sheetModel != null) {
            this.sheetNameLabel.setText(sheetModel.getSheetName());
            this.sqlDetailButton.setDisable(false);
        }else {
            this.sheetNameLabel.setText("");
            this.sqlDetailButton.setDisable(true);
        }
    }

    public void showExcelDetails(ConfigModel configModel) {
        this.excelTemplateLabel.setText(configModel.getExcelTemplate());
        this.configXmlLabel.setText(configModel.getConfigXml());
        this.connectionLabel.setText(configModel.getConnection());
    }


    @FXML
    private void handleSqlDetail() throws IOException {
        SheetModel selectedSheetModel = excelTable.getSelectionModel().getSelectedItem();
        if (selectedSheetModel != null) {
            mainApp.showSQLDetailDialog(selectedSheetModel);

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No sheet Selected");
            alert.setContentText("Please select a sheet in the table.");
            
            alert.showAndWait();
        }
    }

    @FXML
    private void handleProcess() {
        if(!checkParamTableEditing()) {
            return;
        }
        if(!checkParamTableEmpty()) {
            return;
        }
        processButton.setDisable(true);
        mainApp.process(progressBar, progressText, () -> {
            processButton.setDisable(false);
        });
    }


    private boolean checkParamTableEditing() {
        if(paramTable.editingCellProperty().get() != null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Value Editing");
            alert.setHeaderText("please commit value editing");
            alert.setContentText("use ENTER to commit editing");

            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean checkParamTableEmpty() {
        for (ParamModel paramModel : mainApp.getParamList()) {
            String value = paramModel.getValue();
            boolean flag = true;
            if(value != null) {
                for (char c : value.toCharArray()) {
                    if(!Character.isWhitespace(c)) {
                        flag = false;
                    }
                }
            }
            if(flag) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Value Error");
                alert.setHeaderText("value is empty or only white space in key {" + paramModel.getKey() + "}");
                alert.setContentText("use ENTER to commit editing");

                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
}