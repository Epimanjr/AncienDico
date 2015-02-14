/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphiquefx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 *
 * @author Maxime
 */
public class Add extends Parent {
    
    /**
     * Constructeur vide (et c'est le seul)
     */
    Add() {
        Button btn = new Button("Add");
        btn.setTranslateX(200);
        btn.setTranslateY(200);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("OK ADD");
            }
        });
        
        Button add = new Button("ADD PANEL 2");
        add.setTranslateX(10);
        add.setTranslateY(10);
        add.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Main.getMainFrame().changeState(0);
            }
        });
        
        Group group = new Group();
        group.getChildren().add(btn);
        group.getChildren().add(add);
        getChildren().add(group);
    }
}
