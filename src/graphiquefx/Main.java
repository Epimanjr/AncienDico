/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphiquefx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import newsupermdico.donnees.Donnees;

/**
 *
 * @author Maxime
 */
public class Main extends Application {
    
    // Dimension
    public static int width = 700;
    public static int height = 500;

    private static MainFrame mainFrame;

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Donnees.init();
        
        Group root = new Group();
        mainFrame = new MainFrame(root);

        // Caractéristiques du stage
        primaryStage.setTitle("YiruMax Dico");
        primaryStage.setResizable(true);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        // Scene
        Scene scene = new Scene(root);
        scene.setFill(Color.web("#eca10e"));

        // Lancement
        primaryStage.setScene(scene);
        mainFrame.changeState(MainFrame.SEARCH);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public class MainFrame {

        // Instance of scene root node

        private Group root;

        // Instance of search (if exists)
        private Search search;

        private Add add;

        private MainFrame(Group root) {
            this.root = root;
        }

        // Current state of the game. The next values are available
        // 0 - Splash
        public static final int SEARCH = 0;
        public static final int ADD = 1;
        // 1..Level.LEVEL_COUNT - Level
        private int state = SEARCH;

        public void changeState(int newState) {
            this.state = newState;

            if (state == 0) {
                root.getChildren().remove(add);
                add = null;
                search = new Search();
                root.getChildren().add(search);
            } else {
                root.getChildren().remove(search);
                search = null;
                add = new Add();
                root.getChildren().add(add);
            }
        }
    }
}
