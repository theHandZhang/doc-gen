package thehand.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import thehand.ui.MainApp;

import java.io.File;
import java.io.IOException;

public class RootLayoutController {

    private MainApp mainApp;

    private static final String userDir = System.getProperty("user.dir") + "\\";


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(userDir + mainApp.getConfigModel().getConfigXmlPath()));

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {

        }
    }

    @FXML
    private void handleOutput() throws IOException {


        Runtime.getRuntime().exec("explorer.exe \"" + userDir + mainApp.getConfigModel().getOutputPath().replace("/", "\\") + "\"");
    }

    @FXML
    private void handleConfig() throws IOException {
        Runtime.getRuntime().exec("explorer.exe \"" + userDir + mainApp.getConfigModel().getConfigXmlPath().replace("/", "\\") + "\"");

    }

    @FXML
    private void handleExcel() throws IOException {
        Runtime.getRuntime().exec("explorer.exe \"" + userDir + mainApp.getConfigModel().getExcelTemplatePath().replace("/", "\\") + "\"");

    }
}