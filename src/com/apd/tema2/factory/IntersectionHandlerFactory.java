package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.*;
import com.apd.tema2.intersections.*;
//import tema2.utils.Constants;

import javax.swing.*;
import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * Clasa Factory ce returneaza implementari ale InterfaceHandler sub forma unor
 * clase anonime.
 */
public class IntersectionHandlerFactory {

    public static IntersectionHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of
        // them)
        // road in maintenance - 2 ways 1 lane each, X cars at a time
        // road in maintenance - 1 way, M out of N lanes are blocked, X cars at a time
        // railroad blockage for s seconds for all the cars
        // unmarked intersection
        // cars racing
        IntersectionHandler intersection;
        switch (handlerType) {
            case "simple_semaphore" : intersection = new IntersectionHandler() {
                Semaphore sem = new Semaphore(Main.carsNo);

                @Override
                public void handle(Car car) {
                    try{
                        System.out.println("Car " + car.getId() + " has reached the semaphore, now waiting...");
                        Thread.sleep(car.getWaitingTime());
                        sem.acquire();
                    } catch (InterruptedException exc) {
                        System.out.println(exc);
                    }
                    sem.release();
                    System.out.println("Car " + car.getId() + " has waited enough, now driving...");
                }
            }; break;
            case "simple_n_roundabout" : intersection = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    System.out.println("Car " + car.getId() + " has reached the roundabout, now waiting...");
                    try{
                        Main.roundabout.semaphore.acquire();
                        System.out.println("Car " + car.getId() + " has entered the roundabout");

                        sleep(Main.roundabout.getTime());

                    } catch (InterruptedException exc) {
                        System.out.println(exc);
                    }

                    System.out.println("Car " + car.getId() + " has exited the roundabout after " + Main.roundabout.getTime() / 1000+ " seconds");
                    Main.roundabout.semaphore.release();
                }
            }; break;
            case "simple_strict_1_car_roundabout" : intersection = new IntersectionHandler() {
                Semaphore semaphore;
                @Override
                public void handle(Car car) {
                    semaphore = Main.roundaboutOneCar.semaphores[car.getStartDirection()];
                    System.out.println("Car " + car.getId() + " has reached the roundabout");
                    try {
                        semaphore.acquire();
                        System.out.println("Car " + car.getId() + " has enetered the roundabout from lane " + car.getStartDirection());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car " + car.getId() + " has exited the roundabout after " + Main.roundaboutOneCar.getTime()/1000 + " seconds");
                    semaphore.release();

                }
            }; break;
            case "simple_strict_x_car_roundabout" : intersection = new IntersectionHandler() {
                Semaphore semaphore;
                AtomicInteger countCars;
                @Override
                public void handle(Car car) {
//                    semaphore = new Semaphore(Main.roundaboutXCar.getX());
//                    countCars = Main.roundaboutXCar.getCountCars();
//                    synchronized (car) {
//                        System.out.println("Car " + car.getId() + " has reached roundabout, now waiting...");
//                        countCars.incrementAndGet();
//                        if(countCars.intValue() != Main.carsNo) {
//                            try {
//                                wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            notifyAll();
//                        }
//                        // System.out.println(countCars + " " + Main.carsNo);
//
//                    }
//                    synchronized (car){
//                        if (countCars.intValue() == Main.carsNo) {
//                            System.out.println("cevaaa  " + Main.roundaboutXCar.getCountDrivingCars()[car.getStartDirection()]);
//                            if (Main.roundaboutXCar.getCountDrivingCars()[car.getStartDirection()] > Main.roundaboutXCar.getX()) {
//                                {
//                                    try {
//                                        sleep(Main.roundaboutXCar.getTime());
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                            try {
//                                semaphore.acquire();
//                                Main.roundaboutXCar.getCountDrivingCars()[car.getStartDirection()]++;
//                                System.out.println("Car " + car.getId() + " has enetered the roundabout from lane " + car.getStartDirection());
////                                sleep(Main.roundaboutOneCar.getTime());
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                                semaphore.release();
//                        Main.roundaboutXCar.getCountDrivingCars()[car.getStartDirection()]--;
//                           //     System.out.println("Car " + car.getId() + " has exited the roundabout after " + Main.roundaboutXCar.getTime()/1000 + " seconds");
//
//                        }

                }
            }; break;
            case "simple_max_x_car_roundabout" : intersection = new IntersectionHandler() {

                @Override
                public void handle(Car car) {
                    Semaphore s = Main.roundaboutMAXCar.getSemaphores()[car.getStartDirection()];
                    // Get your Intersection instance

                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

                    // Continuati de aici
                    System.out.println("Car " + car.getId() + " has reached roundabout");
                    try {
                        s.acquire();
                        System.out.println("Car " + car.getId() + " has entered the roundabout from lane " + car.getStartDirection());
                        sleep(Main.roundaboutMAXCar.getTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Car " + car.getId() + " has exited the roundabout in " + Main.roundaboutMAXCar.getTime()/1000 + " seconds");
                    s.release();
                }
            }; break;
            case "priority_intersection" : intersection = new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    // Get your Intersection instance

                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

                    // Continuati de aici
                }
            }; break;
            case "crosswalk" : intersection = new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                    while(!Main.pedestrians.isFinished()){
                        System.out.print("");
                        if (Main.pedestrians.isPass()) {
                            if (Main.crosswalk.memory.get(car.getId()) == "green" || Main.crosswalk.memory.get(car.getId()) == "null") {
                                System.out.println("Car " + car.getId() + " has now red light");
                                Main.crosswalk.memory.set(car.getId(), "red");
                            }
                        } else {
                            if (Main.crosswalk.memory.get(car.getId()) == "red" || Main.crosswalk.memory.get(car.getId()) == "null") {
                                System.out.println("Car " + car.getId() + " has now green light");
                                Main.crosswalk.memory.set(car.getId(),"green");
                            }
                        }
                    }
                    if (Main.crosswalk.memory.get(car.getId()) == "red" || Main.crosswalk.memory.get(car.getId()) == "null") {
                        System.out.println("Car " + car.getId() + " has now green light");
                        Main.crosswalk.memory.set(car.getId(), "green");
                    }
                }
            }; break;
            case "simple_maintenance" : intersection = new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                }
            };
            case "complex_maintenance" : intersection = new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                }
            };
            case "railroad" : intersection = new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                }
            };
            default : intersection = null;
        };
        return intersection;
    }
}
