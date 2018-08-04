package mco2;

import java.util.ArrayList;

public class Train extends Thread {
	private int position; //Where train currently is
	ArrayList<Passenger> passengers;
    private final int id;
    private final int passengerCapacity;
    private int previousPos;
	    
	  public Train(int id, int position, int passengerCapacity) {
		  	this.id 			   = id;
	        this.position     	   = position;
	        this.passengers        = new ArrayList<Passenger>();
	        this.passengerCapacity = passengerCapacity;
	    }
	  
		public synchronized void loadTrain(Station station) {
			//while there are still passengers waiting, and there are available seats, load train
			//synchronized(station) {
				System.out.println("Loading train with passengers");
				int passengersToGet = Math.min(
						passengerCapacity - passengers.size(),
						station.getPassengers().size());
				for (int i=0 ; i < passengersToGet ; i++) {
					passengers.add(station.removeAndGetPassenger());
				}
	    		try {
	        		Thread.sleep(10); // simulates small time wait    			
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
			//} // synchronized on the station
	    		notify();
		}
		
		public synchronized void run() {
			while(true)
			{
		    	//System.out.println("Train started: "+ toString() );
				setName("Train. " + id);  // i.e. Train.0
				previousPos = position;
		    	position++;
		    	if (position == 18)
		    		position = 0;
		    	if (previousPos == 18)
		    		previousPos = 0;
		    	notifyAll();
		    	//System.out.println(position);
		    	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	// here we simulate the train status
			// a Train always starts in a station
	    	
	    }
		
		public long getId() {
			return this.id;
		}
		public int getPosition(){
			return this.position;
		}
		
		public int getPrevPosition(){
			return this.previousPos;
		}
		
		public void setPosition(int pos){
			this.position = pos;
		}
}
