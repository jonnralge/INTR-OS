package mco2;

import java.util.ArrayList;

public class Train {
	private int position; //Where train currently is
	ArrayList<Passenger> passengers;
    private final int id;
    private final int passengerCapacity;
	    
	  public Train(int id, int position, int passengerCapacity) {
		  	this.id 			   = id;
	        this.position     	   = position;
	        this.passengers        = new ArrayList<Passenger>();
	        this.passengerCapacity = passengerCapacity;
	    }
	  
		public synchronized void loadTrain(Station station, int availableSeats) {
			//while there are still passengers waiting, and there are available seats, load train
		}
}
