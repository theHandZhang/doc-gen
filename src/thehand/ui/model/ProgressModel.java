package thehand.ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProgressModel {

    private final StringProperty sheetName;

    public ProgressModel(String sheetName) {
        this.sheetName = new SimpleStringProperty(sheetName);
    }

    public String getSheetName() {
        return sheetName.get();
    }

    public StringProperty sheetNameProperty() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName.set(sheetName);
    }
}
