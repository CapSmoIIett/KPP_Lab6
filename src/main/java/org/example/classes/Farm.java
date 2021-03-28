package org.example.classes;

import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;

import java.util.*;

public class Farm{

    ArrayList<Feeder> feeders;
    ArrayList<Circle> lights;
    TextArea output;
    Thread thLight;
    petsStatus petStat;


    public Farm(TextArea text, Circle light1, Circle light2, Circle light3){
        feeders = new ArrayList<Feeder>(3);
        lights = new ArrayList<Circle>(3);
        for (int i = 0; i < 3; i++)
            feeders.add(i, new Feeder());
        lights.add(0, light1);
        lights.add(1, light2);
        lights.add(2, light3);
        output = text;

        petStat = new petsStatus();          // Start thread printing in text field
        petStat.start();

        updateLight upLight = new updateLight();
        thLight = new Thread (upLight);
        thLight.start();
    }

    class petsStatus extends Thread {
        public void run() {
            while (true) {
                for (int i = 0; i < 3; i++) {
                    try {
                        sleep(300);            // 1s = 1000 millis
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!feeders.get(i).isFree())
                        output.setText(output.getText() + "> " + feeders.get(i).getAnimal().getName() + " ест.\n");
                }
            }
        }
    }
    class updateLight implements Runnable{
        public void run() {
            while (true) {
                for (int i = 0; i < 3; i++) {
                    if (!feeders.get(i).isFree()) lights.get(i).setFill(javafx.scene.paint.Color.RED);
                    else lights.get(i).setFill(javafx.scene.paint.Color.BLUE);
                }
            }
        }
    }

    public void addPet(Animal pet){
        if (feeders.get(0).isFree()){
            feeders.get(0).setAnimal(pet);
            return;
        }
        if (feeders.get(1).isFree()){
            feeders.get(1).setAnimal(pet);
            return;
        }
        if (feeders.get(2).isFree()){
            feeders.get(2).setAnimal(pet);
            return;
        }
    }

    public void deletePet(int number){
        if (!feeders.get(number).isFree()){
            feeders.get(number).removeAnimal();
        }
    }
}
