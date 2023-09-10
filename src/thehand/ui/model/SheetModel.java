package thehand.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SheetModel {

    private final StringProperty sheetName;
    private final BooleanProperty enable;
    private final StringProperty sql;


    public SheetModel(String sheetName, Boolean enable, String sql) {
        this.sheetName = new SimpleStringProperty(sheetName);
        this.sql = new SimpleStringProperty(sql);
        this.enable = new SimpleBooleanProperty(enable);

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

    public boolean isEnable() {
        return enable.get();
    }

    public BooleanProperty enableProperty() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable.set(enable);
    }

    public String getSql() {
        return sql.get();
    }

    public StringProperty sqlProperty() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql.set(sql);
    }
}
