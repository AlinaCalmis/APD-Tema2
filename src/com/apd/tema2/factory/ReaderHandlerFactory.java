package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Pedestrians;
import com.apd.tema2.entities.ReaderHandler;
import com.apd.tema2.intersections.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Returneaza sub forma unor clase anonime implementari pentru metoda de citire din fisier.
 */
public class ReaderHandlerFactory {

    public static ReaderHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of them)
        // road in maintenance - 1 lane 2 ways, X cars at a time
        // road in maintenance - N lanes 2 ways, X cars at a time
        // railroad blockage for T seconds for all the cars
        // unmarked intersection
        // cars racing
        ReaderHandler reader ;
        switch (handlerType) {
            case "simple_semaphore" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) {
                    // Exemplu de utilizare:
                     Semaphore sem = new Semaphore(Main.carsNo);
                     Main.intersection = IntersectionFactory.getIntersection("simple_semaphore");
                  //  Semaphore sem = new Semaphore(Main.carsNo);
                    IntersectionHandlerFactory handler = new IntersectionHandlerFactory();
                    handler.getHandler("simple_semaphore");
                }
            }; break;
            case "simple_n_roundabout" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    // To parse input line use:
                    String[] line = br.readLine().split(" ");
                  //  System.out.println(line[1]);
                    Main.intersection = IntersectionFactory.getIntersection("simple_n_roundabout");
                    Main.roundabout = new SimpleNRoundabout(Integer.parseInt(line[0]),Integer.parseInt(line[1]));
                    IntersectionHandlerFactory handler = new IntersectionHandlerFactory();
                    handler.getHandler("simple_n_roundabout");
                }
            }; break;
            case "simple_strict_1_car_roundabout" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("simple_strict_1_car_roundabout");
                   // SimpleStrictOneCar roundabout = (SimpleStrictOneCar) Main.intersection;
                    Main.roundaboutOneCar = new SimpleStrictOneCar(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
//                    roundabout.setCount_cars();
//                    roundabout.setN(Integer.parseInt(line[0]));
//                    roundabout.setTime(Integer.parseInt(line[1]));

                }
            }; break;
            case "simple_strict_x_car_roundabout" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("simple_strict_x_car_roundabout");
                    Main.roundaboutXCar = new SimpleStrictXCar(Integer.parseInt(line[0]), Integer.parseInt(line[1]),
                            Integer.parseInt(line[2]));
                    Main.roundaboutXCar.setCountDrivingCars();

                }
            }; break;
            case "simple_max_x_car_roundabout" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("simple_max_x_car_roundabout");
                    Main.roundaboutMAXCar = new SimpleMaxXCar(Integer.parseInt(line[0]), Integer.parseInt(line[1]),
                            Integer.parseInt(line[2]));
                }
            }; break;
            case "priority_intersection" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {

                }
            };break;
            case "crosswalk" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.intersection = IntersectionFactory.getIntersection("crosswalk");
                    Main.pedestrians = new Pedestrians(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                    Main.crosswalk = new Crosswalk();
                }
            }; break;
            case "simple_maintenance" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {

                }
            };
            case "complex_maintenance" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {

                }
            };
            case "railroad" : reader = new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {

                }
            };
            default : reader = null;
        };
        return reader;
    }

}
