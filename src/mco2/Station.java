package mco2;

import java.awt.Color;
import java.util.ArrayList;
import gui.PassengerWaitingPanel;
import gui.StationVisualPanel;

public class Station {
	private final int id;
	private final int position;
	public Train currentTrain;
	private ArrayList<Passenger> passengers;
	protected Object trainLock = new Object();
	protected Object boardingLock = new Object();
	private int passenger = 0;
	
	public Station(int id, int position) {
		this.id 	  = id + 1; 
		this.position = position;
		passengers    = new ArrayList<Passenger>();
	}
	
	public boolean isOccupied() {
		//System.out.println(currentTrain == null);
		return currentTrain != null;
	}
	
	public void trainArrived() {
		switch(id){
        case 1:        			StationVisualPanel.Station1Panel.setBackground(Color.decode("#ffc107"));
        						StationVisualPanel.label_1_train.setText("Train#"+currentTrain.getId());
        						break;
                                
        case 2:      			StationVisualPanel.Station2Panel.setBackground(Color.decode("#ffc107"));
								StationVisualPanel.label_2_train.setText("Train#"+currentTrain.getId());
								break;
								
		case 3:        			StationVisualPanel.Station3Panel.setBackground(Color.decode("#ffc107"));
								StationVisualPanel.label_3_train.setText("Train#"+currentTrain.getId());
								break;
								
		case 4:        			StationVisualPanel.Station4Panel.setBackground(Color.decode("#ffc107"));
								StationVisualPanel.label_4_train.setText("Train#"+currentTrain.getId());
								break; 
								
		case 5:       			StationVisualPanel.Station5Panel.setBackground(Color.decode("#ffc107"));
								StationVisualPanel.label_5_train.setText("Train#"+currentTrain.getId());
								break;
								
		case 6:        			StationVisualPanel.Station6Panel.setBackground(Color.decode("#ffc107"));
								StationVisualPanel.label_6_train.setText("Train#"+currentTrain.getId());
								break; 
								
		case 7:        			StationVisualPanel.Station7Panel.setBackground(Color.decode("#ffc107"));
								StationVisualPanel.label_7_train.setText("Train#"+currentTrain.getId());
								break; 
								
		case 8:        			StationVisualPanel.Station8Panel.setBackground(Color.decode("#ffc107"));
								StationVisualPanel.label_8_train.setText("Train#"+currentTrain.getId());
								break;
		}
	}
	public void trainLeft() {
		switch(id){
        case 1:        			StationVisualPanel.Station1Panel.setBackground(Color.cyan);
        						StationVisualPanel.label_1_train.setText("");
                                break;
                                
        case 2:      			StationVisualPanel.Station2Panel.setBackground(Color.cyan);
								StationVisualPanel.label_2_train.setText("");
								break;
								
        case 3:        			StationVisualPanel.Station3Panel.setBackground(Color.cyan);
								StationVisualPanel.label_3_train.setText("");
								break;
								
        case 4:        			StationVisualPanel.Station4Panel.setBackground(Color.cyan);
								StationVisualPanel.label_4_train.setText("");
								break; 
								
        case 5:       			StationVisualPanel.Station5Panel.setBackground(Color.cyan);
								StationVisualPanel.label_5_train.setText("");
								break;
								
        case 6:        			StationVisualPanel.Station6Panel.setBackground(Color.cyan);
								StationVisualPanel.label_6_train.setText("");
								break;  
								
        case 7:        			StationVisualPanel.Station7Panel.setBackground(Color.cyan);
								StationVisualPanel.label_7_train.setText("");
								break;   
								
        case 8:        			StationVisualPanel.Station8Panel.setBackground(Color.cyan);
								StationVisualPanel.label_8_train.setText("");
								break;                  
    	}
	}
	public void addTrainIntoStation(Train train) {
		synchronized(boardingLock) {
			this.currentTrain = train;
			System.out.println("Train " + train.getName() + " loaded in station " + this.id);
			boardingLock.notifyAll();
		}
	}
	
	public void removeTrain() {
		synchronized(trainLock) {
			System.out.println("Train " + currentTrain.getName() + " left the station " + this.id);
			this.currentTrain = null;
			trainLock.notifyAll();
		}
	}
	
	public synchronized void addWaitingPassenger(Passenger p) {
		passengers.add(p);
		passenger++;
		setWaiting(id, passenger);
		System.out.println("Passenger " + p.getId() + " added to Station " + id + " destination is Station " + p.getDestination());
	}
	
	
	public int getPosition(){
		return this.position;
	}

	public ArrayList<Passenger> getPassengers() {
		// TODO Auto-generated method stub
		return passengers;
	}

	public synchronized void removePassenger(Passenger p) {
		// TODO Auto-generated method stub
		int indexOfPassenger = passengers.indexOf(p);
		passengers.remove(indexOfPassenger);
		passenger--;
		setWaiting(id,passenger);
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public static void setWaiting(int stationName, int waiting){
        switch(stationName){
            case 1:        	PassengerWaitingPanel.label_1_waiting.setText(waiting + "");
                            break;
            case 2:      	PassengerWaitingPanel.label_2_waiting.setText(waiting + "");
                            break;
            case 3:        	PassengerWaitingPanel.label_3_waiting.setText(waiting + "");
                            break;
            case 4:       	PassengerWaitingPanel.label_4_waiting.setText(waiting + "");
                            break;               
            case 5:      	PassengerWaitingPanel.label_5_waiting.setText(waiting + "");
                            break;
            case 6:        	PassengerWaitingPanel.label_6_waiting.setText(waiting + "");
                            break;                
            case 7:       	PassengerWaitingPanel.label_7_waiting.setText(waiting + "");
                            break;
            case 8:        	PassengerWaitingPanel.label_8_waiting.setText(waiting + "");
                            break;           
        }
    }
}
