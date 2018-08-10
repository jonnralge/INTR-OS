package mco2;

import java.util.ArrayList;

public class Station {
	private final int id;
	private final int position;
	public Train currentTrain;
	private ArrayList<Passenger> passengers;
	protected Object trainLock = new Object();
	protected Object boardingLock = new Object();
	
	public Station(int id, int position) {
		this.id 	  = id + 1; 
		this.position = position;
		passengers    = new ArrayList<Passenger>();
	}
	
	public boolean isOccupied() {
		//System.out.println(currentTrain == null);
		return currentTrain != null;
	}
	public void addTrainIntoStation(Train train) {
		synchronized(boardingLock) {
			currentTrain = train;
			System.out.println("Train " + train.getName() + " loaded in station " + this.id);
			boardingLock.notifyAll();
		}
	}
	
	public void removeTrain() {
		synchronized(trainLock) {
			System.out.println("Train " + currentTrain.getName() + " left the station " + this.id);
			currentTrain = null;
			trainLock.notifyAll();
		}
	}
	
	public synchronized void addWaitingPassenger(Passenger p) {
		passengers.add(p);
		System.out.println("Passenger " + p.getId() + " added to Station " + id + " destination is Station " + p.getDestination());
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
