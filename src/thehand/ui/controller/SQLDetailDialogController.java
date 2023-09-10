package thehand.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import thehand.ui.model.SheetModel;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class SQLDetailDialogController {


    @FXML
    private TextArea sqlText;


    private Stage dialogStage;
    private SheetModel sheetModel;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    public void setSheetModel(SheetModel sheetModel) {
        this.sheetModel = sheetModel;
        sqlText.setText(sheetModel.getSql());
    }

}