package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleStrictXCar implements Intersection {
    private int time;
    private int n;
    private int x;
    private AtomicInteger countCars;
    private int[] countDrivingCars;

    public SimpleStrictXCar(int n, int time, int x) {
        this.time = time;
        this.n = n;
        this.x = x;
        this.countCars = new AtomicInteger();
        this.countDrivingCars = new int[n];
    }

    public int getTime() {
        return time;
    }

    public int getN() {
        return n;
    }

    public int getX() {
        return x;
    }

    public void setCountDrivingCars() {
        for (int i = 0; i < this.getN(); i++)
            this.countDrivingCars[i] = 0;
    }

    public int[] getCountDrivingCars() {
        return countDrivingCars;
    }

    public AtomicInteger getCountCars() {
        return countCars;
    }

    public void setCountCars(AtomicInteger countCars) {
        this.countCars = countCars;
    }
}
