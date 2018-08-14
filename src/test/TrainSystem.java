package test;

import java.util.Random;
import java.util.concurrent.Semaphore;

import Sgui.STrainVisualPanel;
import Smco2.Station;
import Smco2.Train;
import gui.TrainVisualPanel;

/**
 * class for train system functionality
 * @author author
 */
public class TrainSystem {
    private static int maxTrains = 16;
    private static int numStations = 8;
    static Thread[] train_threads = new Thread[maxTrains];
    public static Station[] trainStations = new Station[8];
    
    public static int numOfTrains = 0;
    static Semaphore availableSeats;

    public TrainSystem() {
        for(int i = 0; i< numStations; i++){
            Station station = new Station(i+1);
            trainStations[i] = station;
        }
    }
    
    /**
     * dispatches a new train into the train system
     * creates new train object, adds it to array of trains running 
     * in system train_threads
     * @param id integer value train id
     * @param capacity integer value of passenger capacity
     */
    public static void dispatchTrain(int id, int capacity){
        if(numOfTrains<16){
            availableSeats = new Semaphore(capacity, true);
        
            Train t = new Train(id, capacity,trainStations, availableSeats);
            System.out.println(capacity);
            STrainVisualPanel.trainName.get(numOfTrains).setText("Train#"+Long.toString(t.getId()));
            STrainVisualPanel.trainSeats.get(numOfTrains).setText(t.getOccupied_seats() + "/" + capacity);
            STrainVisualPanel.trainStatusHead.get(numOfTrains).setText("<html><u>Status:</u></html>");
            STrainVisualPanel.trainStatus.get(numOfTrains).setText("");
            
            train_threads[numOfTrains] = new Thread(t);
            train_threads[numOfTrains].start();
            numOfTrains++;
        }        
    }
    
    public static void generatePassengers(){
    	Random hey = new Random();
    	int i = hey.nextInt(8) + 0;
        int passengers_to_generate = 1;        
        //for(int i=0;i<numStations;i++){
            int destStation = uniqueDest(i);
            trainStations[i].generatePassengers(passengers_to_generate,destStation);
            Station.setWaiting(i+1, trainStations[i].getCurPassengers());
        //}
    }
    
    public static int uniqueDest(int given){
        int n;
        do{
            n = randNum(1,numStations);
        }while(given == n);
        return n;
    }
    
    public static int randNum(int max, int min){
        Random rand = new Random();
        int  n = rand.nextInt(max) + min;
        return n;
    }    
    
}
