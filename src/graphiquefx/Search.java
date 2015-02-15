/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphiquefx;

import base.activerecord.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

/**
 *
 * @author Maxime
 */
public class Search extends Parent {

    // Tous les éléments de la page
    final Button btn;
    final Button wordreference;
    final WebView webview;
    final TextField saisie = new TextField();

    /**
     * Constructeur vide (et c'est le seul)
     */
    Search() {

        btn = new Button("Search");
        btn.setTranslateX(Main.width / 2);
        btn.setTranslateY(5);
        btn.setOnAction((ActionEvent event) -> {
            chercher();
        });

        webview = new WebView();
        webview.setMaxSize(Main.width - 25, Main.height - 90);
        webview.getEngine().loadContent("<html><body style=\"background-color: #cce6ff;\">En attente ...</body></html>");
        webview.setTranslateX(5);
        webview.setTranslateY(45);

        wordreference = new Button("Wordreference");
        wordreference.setTranslateX(Main.width - 150);
        wordreference.setTranslateY(5);
        wordreference.setOnAction((ActionEvent event) -> {
            String url = "http://www.wordreference.com/fren/" + saisie.getText();
            System.out.println("Chargement de " + url);
            webview.getEngine().load(url);
        });

        saisie.setTranslateX(Main.width / 2 - 160);
        saisie.setTranslateY(5);
        saisie.setOnAction((ActionEvent event) -> {
            chercher();
        });

        Group group = new Group();
        group.getChildren().add(btn);
        group.getChildren().add(wordreference);
        group.getChildren().add(saisie);
        group.getChildren().add(webview);
        getChildren().add(group);
    }

    /**
     * Action de chercher un mot
     */
    private void chercher() {
        Word m = new Word(saisie.getText());
        String html = m.chercherHtml();
        webview.getEngine().loadContent(html);
    }
}
