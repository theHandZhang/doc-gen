package thehand.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import thehand.ui.model.ConfigModel;
import thehand.ui.model.ParamModel;
import thehand.ui.model.SheetModel;

public class MainLine {

    public static void run(ConfigModel configModel,
                           ObservableList<SheetModel> sheetList,
                           ObservableList<ParamModel> paramList,
                           Label progressText,
                           ProgressBar progressBar,
                           Runnable afterRun
    ) throws InterruptedException {
        float allTask = 0;
        float completeTask = 0;
        long taskStartTime = System.currentTimeMillis();
        for (SheetModel sheetModel : sheetList) {

            if(sheetModel.isEnable()) {
                allTask += 1;
            }
        }
        for (SheetModel sheetModel : sheetList) {
            if(sheetModel.isEnable()) {
                Thread.sleep(1000);
                completeTask += 1;
                float progress = completeTask / allTask;
                Platform.runLater(() -> {
                    progressBar.setProgress(progress);
                    progressText.setText("complete " + sheetModel.getSheetName());
                });
            }
        }
        long taskEndTime = System.currentTimeMillis();
        long elapsedTimeInSeconds = (taskEndTime - taskStartTime) / 1000;
        Platform.runLater(() -> {
            progressText.setText("all complete, use " + elapsedTimeInSeconds + " seconds");
        });
        Platform.runLater(afterRun);
    }
}
