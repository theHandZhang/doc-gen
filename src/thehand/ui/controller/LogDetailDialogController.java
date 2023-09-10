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
public class LogDetailDialogController {


    @FXML
    private TextArea sqlText;


    private Stage dialogStage;
    private SheetModel sheetModel;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    /**
     * Sets the person to be edited in the dialog.
     */
    public void setPerson(SheetModel sheetModel) {
        this.sheetModel = sheetModel;

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
}