package org.example;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;
import org.example.classes.Animal;
import org.example.classes.Farm;


public class Controller {

    Farm farm;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textField;

    @FXML
    private Circle lightFeed1;

    @FXML
    private Circle lightFeed2;

    @FXML
    private Circle lightFeed3;

    @FXML
    void addCat(ActionEvent event) {
        Animal cat = new Animal("Кот");
        farm.addPet(cat);
    }

    @FXML
    void addChiken(ActionEvent event) {
        Animal chiken = new Animal("Курица");
        farm.addPet(chiken);
    }

    @FXML
    void addCow(ActionEvent event) {
        Animal cow = new Animal("Корова");
        farm.addPet(cow);
    }

    @FXML
    void addDog(ActionEvent event) {
        Animal dog = new Animal("Собака");
        farm.addPet(dog);
    }

    @FXML
    void clearFeed1(ActionEvent event) {
        farm.deletePet(0);
    }

    @FXML
    void clearFeed2(ActionEvent event) {
        farm.deletePet(1);
    }

    @FXML
    void clearFeed3(ActionEvent event) {
        farm.deletePet(2);
    }

    @FXML
    void clearTextField(ActionEvent event) {
        textField.clear();
    }

    @FXML
    void initialize() {
        farm = new Farm(textField, lightFeed1, lightFeed2, lightFeed3);
    }
}
