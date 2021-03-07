package com.apd.tema2;
import com.apd.tema2.entities.Intersection;
import com.apd.tema2.entities.Pedestrians;
import com.apd.tema2.intersections.*;
import com.apd.tema2.io.Reader;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static Pedestrians pedestrians = null;
    public static Intersection intersection;
    public static SimpleNRoundabout roundabout;
    public static SimpleStrictOneCar roundaboutOneCar;
    public static SimpleStrictXCar roundaboutXCar;
    public static SimpleMaxXCar roundaboutMAXCar;
    public static Crosswalk crosswalk;
    public static int carsNo;
    ArrayList<Integer> arr = new ArrayList<>();


    public static void main(String[] args) {
        Reader fileReader = Reader.getInstance(args[0]);
        Set<Thread> cars = fileReader.getCarsFromInput();

        for(Thread car : cars) {
            car.start();
            arr.
        }

        if(pedestrians != null) {
            try {
                Thread p = new Thread(pedestrians);
                p.start();
                p.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(Thread car : cars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
