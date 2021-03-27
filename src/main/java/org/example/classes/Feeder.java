package org.example.classes;

public class Feeder {
    Animal animal;
    boolean free;

    Feeder(){
        free = true;
    }

    public void setAnimal(Animal pet){
        animal = pet;
        free = false;
    }

    public boolean isFree() {
        return free;
    }

    public Animal getAnimal() {
        return animal;
    }
}
