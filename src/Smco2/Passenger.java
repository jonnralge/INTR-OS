package Smco2;

public class Passenger implements Runnable{   
    private int id;
    private int startStation; //0-7 station id
    private int destStation;
    private static int totalPassengerCount; //track total number of spawned passengers    
    private final Station station;
    private String status; //WAITING, ONBOARD

    public Passenger(int startStation,int destStation,Station s) {
        totalPassengerCount++;
        this.id = totalPassengerCount;
        this.startStation = startStation;
        this.destStation = destStation;
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
    
    public int getDestStation() {
        return destStation;
    }    
    
    @Override
    public void run(){
        station.waiting_for_train(this);
    } 
}
