package org.example.classes;

public class Feeder {
    private Animal animal;
    private boolean free;

    Feeder(){
        this.free = true;
    }

    public void setAnimal(Animal pet){
        animal = pet;
        free = false;
    }

    public void removeAnimal(){
        free = true;
        animal = null;
    }

    public boolean isFree() {
        return free;
    }

    public Animal getAnimal() {
        return animal;
    }
}
