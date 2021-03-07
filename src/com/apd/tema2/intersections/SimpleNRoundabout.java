package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleNRoundabout implements Intersection {
    public static int permitedCars;
    public static int time ;
    public Semaphore semaphore;


    public SimpleNRoundabout(int permitedCars, int time){
        this.permitedCars = permitedCars;
        this.time = time;
        semaphore = new Semaphore(permitedCars);
    }

    public static int getPermitedCars() {
        return permitedCars;
    }

    public static int getTime() {
        return time;
    }
}
