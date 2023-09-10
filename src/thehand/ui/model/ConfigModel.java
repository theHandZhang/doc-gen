package thehand.ui.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfigModel {

    private final StringProperty configXml;
    private final StringProperty excelTemplate;
    private final StringProperty connection;
    private final BooleanProperty showDBMSG;
    private final StringProperty configXmlPath;
    private final StringProperty excelTemplatePath;
    private final StringProperty outputPath;
    private final StringProperty defaultConfigXml;
    private final StringProperty defaultExcelTemplate;

    public ConfigModel(String configXml,
                       String excelTemplate,
                       String connection,
                       Boolean showDBMSG,
                       String configXmlPath,
                       String excelTemplatePath,
                       String outputPath,
                       String defaultConfigXml,
                       String defaultExcelTemplate) {
        this.configXml = new SimpleStringProperty(configXml);
        this.excelTemplate = new SimpleStringProperty(excelTemplate);
        this.connection = new SimpleStringProperty(connection);
        this.showDBMSG = new SimpleBooleanProperty(showDBMSG);
        this.configXmlPath = new SimpleStringProperty(configXmlPath);
        this.excelTemplatePath = new SimpleStringProperty(excelTemplatePath);
        this.outputPath = new SimpleStringProperty(outputPath);
        this.defaultConfigXml = new SimpleStringProperty(defaultConfigXml);
        this.defaultExcelTemplate = new SimpleStringProperty(defaultExcelTemplate);
    }

    public String getConfigXmlPath() {
        return configXmlPath.get();
    }

    public StringProperty configXmlPathProperty() {
        return configXmlPath;
    }

    public void setConfigXmlPath(String configXmlPath) {
        this.configXmlPath.set(configXmlPath);
    }

    public String getExcelTemplatePath() {
        return excelTemplatePath.get();
    }

    public StringProperty excelTemplatePathProperty() {
        return excelTemplatePath;
    }

    public void setExcelTemplatePath(String excelTemplatePath) {
        this.excelTemplatePath.set(excelTemplatePath);
    }

    public String getOutputPath() {
        return outputPath.get();
    }

    public StringProperty outputPathProperty() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath.set(outputPath);
    }

    public String getDefaultConfigXml() {
        return defaultConfigXml.get();
    }

    public StringProperty defaultConfigXmlProperty() {
        return defaultConfigXml;
    }

    public void setDefaultConfigXml(String defaultConfigXml) {
        this.defaultConfigXml.set(defaultConfigXml);
    }

    public String getDefaultExcelTemplate() {
        return defaultExcelTemplate.get();
    }

    public StringProperty defaultExcelTemplateProperty() {
        return defaultExcelTemplate;
    }

    public void setDefaultExcelTemplate(String defaultExcelTemplate) {
        this.defaultExcelTemplate.set(defaultExcelTemplate);
    }

    public boolean isShowDBMSG() {
        return showDBMSG.get();
    }

    public BooleanProperty showDBMSGProperty() {
        return showDBMSG;
    }

    public void setShowDBMSG(boolean showDBMSG) {
        this.showDBMSG.set(showDBMSG);
    }

    public String getConnection() {
        return connection.get();
    }

    public StringProperty connectionProperty() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection.set(connection);
    }

    public String getConfigXml() {
        return configXml.get();
    }

    public StringProperty configXmlProperty() {
        return configXml;
    }

    public void setConfigXml(String configXml) {
        this.configXml.set(configXml);
    }

    public String getExcelTemplate() {
        return excelTemplate.get();
    }

    public StringProperty excelTemplateProperty() {
        return excelTemplate;
    }

    public void setExcelTemplate(String excelTemplate) {
        this.excelTemplate.set(excelTemplate);
    }
}
