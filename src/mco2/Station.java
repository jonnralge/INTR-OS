package mco2;

import java.util.ArrayList;

public class Station {
	private final int id;
	private Train currentTrain;
	private ArrayList<Passenger> passengers;
	
	public Station(int id) {
		this.id 	= id;
		passengers  = new ArrayList<Passenger>();
	}
	
	public synchronized void loadTrain(Train train) {
		currentTrain = train;
	}
	
}
