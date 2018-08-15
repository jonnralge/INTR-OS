package Smco2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.Random;

import Sgui.SPassengerWaitingPanel;
import Sgui.SStationVisualPanel;

public class Station {

    private int id;
    private Train currentTrain;
    private int curPassengers;
    private ArrayList<Passenger> passengers;
    private ArrayList<Thread> station_robots;
    private String status;
    private boolean station_hasTrain;
    
    private Semaphore mutex;
       
    public Station() {
        this.id = 0;   
        this.currentTrain = null;
        this.curPassengers = 0;
        this.station_hasTrain = false;
        this.passengers = new ArrayList();  
        this.mutex = new Semaphore(1);
        this.status = "IDLE";
        this.station_robots = new ArrayList();
    }

    public Station(int id) {
        this.id = id;   
        this.currentTrain = null;
        this.curPassengers = 0;
        this.station_hasTrain = false;
        this.passengers = new ArrayList();  
        this.mutex = new Semaphore(1);
        this.status = "IDLE";
        this.station_robots = new ArrayList();
    }
    
    public void acquire_mutex(){
        try{
            this.mutex.acquire();
        }catch(Exception e){
            
        }        
    }
    
    public void release_mutex(){
        this.mutex.release();
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }
    
    public void addPassenger(Passenger p){
        this.passengers.add(p);
    }
    
    public Passenger removeAndGetPassenger() {
            return passengers.remove(0);
    }    

    public int getId() {
        return id;
    }

    public Train getCurrentTrain() {
        return currentTrain;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentTrain(Train currentTrain) {
        this.currentTrain = currentTrain;
    }

    public int getCurPassengers() {
        return curPassengers;
    }

    public void setCurPassengers(int curPassengers) {
        this.curPassengers = curPassengers;
    }
	
    public static int uniqueDest(int given){
        int n;
        do{
            n = randNum(8-1,0);
        }while(given == n);
        return n;
    }
    
    public static int randNum(int max, int min){
        Random rand = new Random();
        int  n = rand.nextInt(max) + min;
        return n;
    }  
    
    /**
     * load train into station
     * @param train_freeSeats integer value of free seats in train currently at station
     */
    public synchronized void load_train(int train_freeSeats){
        this.acquire_mutex();
        try{
            System.out.println("Train#" + Train.getId() + " arrived at Station " + this.getId());
            trainArrived();
            Thread.sleep(1500);
         }catch(InterruptedException e){
             System.out.println(e);
         }
        
        //System.out.println("Train#"+currentTrain.getId()+" is now boarding passengers");
        if(curPassengers == 0){
            //System.out.println("No passengers in station " + this.getId());
        }else if(train_freeSeats == 0){
            System.out.println("Train is full! Train is leaving station");
        }else{
            //System.out.println("Passengers boarding");
            this.notifyAll();
        }
        this.release_mutex();
        System.out.println("Train#" + Train.getId() + " left Station " + this.getId());
        trainLeft();
    }
    
    /**
     * station waits for train
     * @param p passenger returns to waiting on station if there is no train yet
     */
    public synchronized void waiting_for_train(Passenger p){
        this.acquire_mutex();
        this.release_mutex();
        try{
            wait();
        }catch(Exception e){
            
        }
        this.passengers_boarded(p);
    }
    
    /**
     * generates passengers for station
     * @param numPassengers integer value how many passengers to be generated
     * @param destStation integer id of station passenger to be added to
     */
    public void generatePassengers(int numPassengers, int destStation){
        this.curPassengers += numPassengers;
        Passenger p = null;
        for(int i=0;i<numPassengers;i++){
            int dropoffStation = uniqueDest(destStation);
            p = new Passenger(destStation,dropoffStation,this);
            passengers.add(p);
            station_robots.add(new Thread(p));
            station_robots.get(station_robots.size()-1).start();
        }
        System.out.println("Passenger " + p.getId() + " is going to Station "+ destStation);
    }
    
    /**
     * checks if all passengers are on board
     * @param p an incoming single passenger
     */
    public void passengers_boarded(Passenger p){        
        this.acquire_mutex();
        try{
            if(currentTrain.getAvailableSeats() == 0){
                this.release_mutex();
                waiting_for_train(p);
            }else{
                int passengers_onboard = this.currentTrain.getOccupied_seats();
                try{
                    currentTrain.get_Semaphore().acquire();
                }catch(Exception e){
                    System.out.println(e);
                }    
                this.curPassengers--;
                int currentPassengersCount = currentTrain.getOccupied_seats()+1;
                currentTrain.setOccupied_seats(passengers_onboard + 1);
                currentTrain.addPassenger(p);
                station_robots.remove(p);
                
                //System.out.println("train[" + currentTrain.getId()+ "]:");
                System.out.println("Passenger " + p.getId()+ " has boarded the Train#"+ currentTrain.getId());
                //System.out.println("Available seats left: " + currentTrain.getAvailableSeats());
            }
            //System.out.println("Passengers onboard: " + currentTrain.getOccupied_seats());
            setWaiting(id, curPassengers);
            Train.update();
            
        }catch(Exception e){
            System.out.println(e);
        }
        this.release_mutex();
    }
    
    public void trainArrived() {
		switch(id){
        case 1:        			SStationVisualPanel.Station1Panel.setBackground(Color.decode("#ffc107"));
        						SStationVisualPanel.label_1_train.setText("Train#"+currentTrain.getId());
        						break;
                                
        case 2:      			SStationVisualPanel.Station2Panel.setBackground(Color.decode("#ffc107"));
								SStationVisualPanel.label_2_train.setText("Train#"+currentTrain.getId());
								break;
								
		case 3:        			SStationVisualPanel.Station3Panel.setBackground(Color.decode("#ffc107"));
								SStationVisualPanel.label_3_train.setText("Train#"+currentTrain.getId());
								break;
								
		case 4:        			SStationVisualPanel.Station4Panel.setBackground(Color.decode("#ffc107"));
								SStationVisualPanel.label_4_train.setText("Train#"+currentTrain.getId());
								break; 
								
		case 5:       			SStationVisualPanel.Station5Panel.setBackground(Color.decode("#ffc107"));
								SStationVisualPanel.label_5_train.setText("Train#"+currentTrain.getId());
								break;
								
		case 6:        			SStationVisualPanel.Station6Panel.setBackground(Color.decode("#ffc107"));
								SStationVisualPanel.label_6_train.setText("Train#"+currentTrain.getId());
								break; 
								
		case 7:        			SStationVisualPanel.Station7Panel.setBackground(Color.decode("#ffc107"));
								SStationVisualPanel.label_7_train.setText("Train#"+currentTrain.getId());
								break; 
								
		case 8:        			SStationVisualPanel.Station8Panel.setBackground(Color.decode("#ffc107"));
								SStationVisualPanel.label_8_train.setText("Train#"+currentTrain.getId());
								break;
		}
	}
	public void trainLeft() {
		switch(id){
        case 1:        			SStationVisualPanel.Station1Panel.setBackground(Color.cyan);
        						SStationVisualPanel.label_1_train.setText("");
                                break;
                                
        case 2:      			SStationVisualPanel.Station2Panel.setBackground(Color.cyan);
								SStationVisualPanel.label_2_train.setText("");
								break;
								
        case 3:        			SStationVisualPanel.Station3Panel.setBackground(Color.cyan);
								SStationVisualPanel.label_3_train.setText("");
								break;
								
        case 4:        			SStationVisualPanel.Station4Panel.setBackground(Color.cyan);
								SStationVisualPanel.label_4_train.setText("");
								break; 
								
        case 5:       			SStationVisualPanel.Station5Panel.setBackground(Color.cyan);
								SStationVisualPanel.label_5_train.setText("");
								break;
								
        case 6:        			SStationVisualPanel.Station6Panel.setBackground(Color.cyan);
								SStationVisualPanel.label_6_train.setText("");
								break;  
								
        case 7:        			SStationVisualPanel.Station7Panel.setBackground(Color.cyan);
								SStationVisualPanel.label_7_train.setText("");
								break;   
								
        case 8:        			SStationVisualPanel.Station8Panel.setBackground(Color.cyan);
								SStationVisualPanel.label_8_train.setText("");
								break;                  
    	}
	}
	
	public static void setWaiting(int stationName, int waiting){
        switch(stationName){
            case 1:        	SPassengerWaitingPanel.label_1_waiting.setText(waiting + "");
                            break;
            case 2:      	SPassengerWaitingPanel.label_2_waiting.setText(waiting + "");
                            break;
            case 3:        	SPassengerWaitingPanel.label_3_waiting.setText(waiting + "");
                            break;
            case 4:       	SPassengerWaitingPanel.label_4_waiting.setText(waiting + "");
                            break;               
            case 5:      	SPassengerWaitingPanel.label_5_waiting.setText(waiting + "");
                            break;
            case 6:        	SPassengerWaitingPanel.label_6_waiting.setText(waiting + "");
                            break;                
            case 7:       	SPassengerWaitingPanel.label_7_waiting.setText(waiting + "");
                            break;
            case 8:        	SPassengerWaitingPanel.label_8_waiting.setText(waiting + "");
                            break;           
        }
    }
}
