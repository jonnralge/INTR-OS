package Smco2;

import static test.TrainSystem.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Passenger implements Runnable{   
    private int id;
    private int startStation;
    private static int totalPassengerCount;  
    private final Station station;
    private String status;

    public Passenger(int startStation,Station s) {
        totalPassengerCount++;
        this.id = totalPassengerCount;
        this.startStation = startStation;
        station = s;
        this.status = "WAITING";
    }
    
    public static int getTotalPassengerCount() {
        return totalPassengerCount;
    }

    public static void setTotalPassengerCount(int totalPassengerCount) {
        Passenger.totalPassengerCount = totalPassengerCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStartStation() {
        return startStation;
    }

    public void setStartStation(int startStation) {
        this.startStation = startStation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  
    
    public void updateDestination(){
        this.startStation--;
    }
    
    @Override
    public void run(){
        station.waiting_for_train(this);
    }
    
}
