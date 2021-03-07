package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;

public class Crosswalk implements Intersection {

    public ArrayList<String> memory;

    public Crosswalk() {
        this.memory = new ArrayList<>();
        for(int i = 0; i < Main.carsNo; i++){
            this.memory.add("null");
        }
    }
}
