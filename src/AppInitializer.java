import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane load = FXMLLoader.load(this.getClass().getResource("./view/TextEditorMainForm.fxml"));
        Scene mainScene= new Scene(load);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Advanced Text Editor");
        primaryStage.show();
    }
}
