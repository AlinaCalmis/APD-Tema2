package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class SimpleMaxXCar implements Intersection {
    private int n;
    private int time;
    private int x;
    private Semaphore[] semaphores;

    public SimpleMaxXCar(int n, int time, int x) {
        this.n = n;
        this.time = time;
        this.x = x;
        this.semaphores = new Semaphore[n];
        for (int i = 0; i < n; i++)
            semaphores[i] = new Semaphore(x);
    }

    public int getN() {
        return n;
    }

    public int getTime() {
        return time;
    }

    public int getX() {
        return x;
    }

    public Semaphore[] getSemaphores() {
        return semaphores;
    }
}
