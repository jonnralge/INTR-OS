package mco2;

import java.util.ArrayList;

public class Station {
	private final int id;
	private final int position;
	private Train currentTrain;
	private ArrayList<Passenger> passengers;
	
	public Station(int id, int position) {
		this.id 	  = id;
		this.position = position;
		passengers    = new ArrayList<Passenger>();
	}
	
	public synchronized void loadTrain(Train train) {
		currentTrain = train;
		System.out.println("train " + train.getName() + " loaded in station");
		for(Passenger p : passengers) {
			p.boardTrain(this);
			System.out.println("Passenger " + p.getId() + " has boarded train " + train.getName());
		}
		notify();
	}
	
	public synchronized void removeTrain() {
		System.out.println("train " + currentTrain.getName() + "left the station");
		currentTrain = null;
		notify();
	}
	
	public synchronized void addWaitingPassenger(Passenger p) {
		passengers.add(p);
	}
	
	public synchronized void passengerBoardTrain(Passenger p) {
		passengers.remove(p);
	}
	
	public int getPosition(){
		return this.position;
	}
}
