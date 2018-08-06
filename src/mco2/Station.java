package mco2;

import java.util.ArrayList;

public class Station {
	private final int id;
	private final int position;
	private Train currentTrain;
	private ArrayList<Passenger> passengers;
	
	public Station(int id, int position) {
		this.id 	  = id + 1; 
		this.position = position;
		passengers    = new ArrayList<Passenger>();
	}
	
	public synchronized void addTrainIntoStation(Train train) {
		currentTrain = train;
		System.out.println("Train " + train.getName() + " loaded in station " + this.id);
		notify();
	}
	
	public synchronized void loadTrainWithPassengers() {
		currentTrain.loadTrain(this);
	}
	
	public synchronized void removeTrain() {
		System.out.println("Train " + currentTrain.getName() + " left the station " + this.id);
		currentTrain = null;
		notify();
	}
	
	public synchronized void addWaitingPassenger(Passenger p) {
		passengers.add(p);
		System.out.println("Passenger " + p.getId() + " added");
	}
	
	
	public int getPosition(){
		return this.position;
	}

	public ArrayList<Passenger> getPassengers() {
		// TODO Auto-generated method stub
		return passengers;
	}

	public synchronized Passenger removeAndGetPassenger() {
		// TODO Auto-generated method stub
		return passengers.remove(0);
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
