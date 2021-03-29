package org.example.classes;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;

import java.util.*;

public class Farm{

    private ArrayList<Feeder> feeders;
    private ArrayList<Circle> lights;
    private ArrayList<Label> labels;
    private TextArea output;
    private Thread thLight;
    private petsStatus petStat;
    private boolean work;


    public Farm(TextArea text,
                Circle light1, Circle light2, Circle light3,
                Label  label1, Label  label2, Label  label3){
        this.work = true;

        this.feeders = new ArrayList<Feeder>();
        this.lights = new ArrayList<Circle>();
        this.labels = new ArrayList<Label>();
        for (int i = 0; i < 3; i++)
            this.feeders.add(new Feeder());
        this.lights.add(light1);
        this.lights.add(light2);
        this.lights.add(light3);
        this.labels.add(label1);
        this.labels.add(label2);
        this.labels.add(label3);
        this.output = text;

        this.petStat = new petsStatus();          // Start thread printing in text field
        this.petStat.start();

        updateLight upLight = new updateLight();
        this.thLight = new Thread (upLight);
        this.thLight.start();
    }

    class petsStatus extends Thread {
        public void run() {
            while (work) {
                for (int i = 0; i < 3; i++) {
                    try {
                        sleep(300);            // 1s = 1000 millis
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!work) return;
                    if (!feeders.get(i).isFree())
                        output.setText(output.getText() + "> " + feeders.get(i).getAnimal().getName() + " ест.\n");
                }
            }
        }
    }
    class updateLight implements Runnable{
        public void run() {
            while (work) {
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
            labels.get(0).setText(pet.getName());
            return;
        }
        if (feeders.get(1).isFree()){
            feeders.get(1).setAnimal(pet);
            labels.get(1).setText(pet.getName());
            return;
        }
        if (feeders.get(2).isFree()){
            feeders.get(2).setAnimal(pet);
            labels.get(2).setText(pet.getName());
            return;
        }
    }

    public void deletePet(int number){
        if (!feeders.get(number).isFree()){
            feeders.get(number).removeAnimal();
            labels.get(number).setText(" ");
        }
    }

    public void closeFarm() throws InterruptedException {
        work = false;
        Thread.sleep(300);          // We wait before all thread stop
        thLight.interrupt();
        petStat.interrupt();
    }
}
