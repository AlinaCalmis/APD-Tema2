package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class SimpleStrictOneCar implements Intersection {
    public int time;
    public int n;
    public int[] count_cars ;
    public Semaphore[] semaphores;

    public SimpleStrictOneCar() {
        this.time = 0;
        this.n = 0;
        this.count_cars = new int[n];
        this.semaphores = new Semaphore[n];
    }

    public SimpleStrictOneCar(int n, int time) {
        this.time = time;
        this.n = n;
        this.count_cars = new int[n];
        for (int i = 0; i < n; i++)
            this.count_cars[i] = 0;
        this.semaphores = new Semaphore[n];
        for(int i = 0; i < n; i++)
            this.semaphores[i] = new Semaphore(1);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getN() {
        return n;
    }

    public void setN(int n){
        System.out.println("in set");
        this.n = n;
    }

//    public Semaphore[] getSemaphores() {
//        return semaphores;
//    }
//
//    public void setSemaphores() {
//        for (int i = 0; i < this.getN(); i++){ //semaphores.add(new Semaphore(1));
//            this.semaphores[i] = new Semaphore(1);
//        }
//    }

    public int[] getCount_cars() {
        return count_cars;
    }

    public void setCount_cars() {
        this.count_cars = new int [this.getN()];
        for (int i =0; i < this.getN(); i++)
            this.count_cars[i] = 0;
    }
}
