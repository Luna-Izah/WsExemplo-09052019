/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsconsumer;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author drink
 */
public class WsConsumer extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Consumer.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("consumer.css").toExternalForm());
        primaryStage.setTitle("Web Services");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
