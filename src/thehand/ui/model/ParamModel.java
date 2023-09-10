package thehand.ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParamModel {

    private final StringProperty key;
    private final StringProperty value;

    public ParamModel(String key) {
        this.key = new SimpleStringProperty(key);
        this.value = new SimpleStringProperty("");
    }

    public String getKey() {
        return key.get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    @Override
    public String toString() {
        return "ParamModel{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
