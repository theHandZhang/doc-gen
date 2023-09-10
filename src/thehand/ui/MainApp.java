package thehand.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import thehand.ui.model.ConfigModel;
import thehand.ui.model.ParamModel;
import thehand.ui.model.SheetModel;
import thehand.ui.controller.RootLayoutController;
import thehand.ui.controller.SQLDetailDialogController;
import thehand.ui.controller.SheetOverviewController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.prefs.Preferences;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public static ExecutorService executorService;
    private static final String viewLocation;
    private static final String SQLDetailLocation;
    private static final String SheetOverviewLocation;
    private static final String RootLayoutLocation;
    private static final String LogDetailDialogLocation;


    static {
        executorService = Executors.newCachedThreadPool();

        viewLocation = System.getProperty("user.dir") + "/src/thehand/ui/view/";
        SQLDetailLocation = viewLocation + "SQLDetailDialog.fxml";
        SheetOverviewLocation = viewLocation + "SheetOverview.fxml";
        RootLayoutLocation = viewLocation + "RootLayout.fxml";
        LogDetailDialogLocation = viewLocation + "LogDetailDialog.fxml";
    }

    private ObservableList<SheetModel> sheetList = FXCollections.observableArrayList();

    private ConfigModel configModel;

    private ObservableList<ParamModel> paramList = FXCollections.observableArrayList();

    public MainApp() {
        sheetList.add(new SheetModel("CHDR", true, "select * from table"));
        sheetList.add(new SheetModel("COVR", true, "select * from table"));
        sheetList.add(new SheetModel("PTRN", false, "select * from table"));

        configModel = new ConfigModel(
                "default",
                "default",
                "PROD-TSSQRY",
                true,
                "resources/config",
                "resources/excel",
                "resources/output",
                "default",
                "default");

        paramList.add(new ParamModel("chdrnum"));
        paramList.add(new ParamModel("chdrnum"));
    }

    public ObservableList<SheetModel> getSheetList() {
        return sheetList;
    }

    public ConfigModel getConfigModel() {
        return configModel;
    }

    public ObservableList<ParamModel> getParamList() {
        return paramList;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DOC-GEN");
        
        this.primaryStage.getIcons().add(new Image("file:resources/images/calendar.png"));

        initRootLayout();

        showSheetOverview();
    }

    public void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File(RootLayoutLocation).toURI().toURL());
        rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();

    }

    public void showSheetOverview() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File(SheetOverviewLocation).toURI().toURL());
        AnchorPane sheetOverview = loader.load();

        rootLayout.setCenter(sheetOverview);

        SheetOverviewController controller = loader.getController();
        controller.setMainApp(this);
    }

    public void showSQLDetailDialog(SheetModel sheetModel) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File(SQLDetailLocation).toURI().toURL());
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("SQL DETAIL");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        SQLDetailDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setSheetModel(sheetModel);

        dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    public void process(ProgressBar progressBar, Label progressText, Runnable afterRun) {
        executorService.execute(() -> {
            try {
                MainLine.run(configModel, sheetList, paramList, progressText, progressBar, afterRun);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}