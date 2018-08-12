package mco2;

import java.util.ArrayList;

public class Train extends Thread {
	private int position; //Where train currently is
	private int passengers;
    private final long id;
    private final int passengerCapacity;
    private int previousPos;
    protected Object doorLock = new Object();
    public static ArrayList<Station> stations = new ArrayList<Station>();
	    
	  public Train(int id, int position, int passengerCapacity) {
		  	this.id 			   = id;
	        this.position     	   = position;
	        this.passengers        = 0;
	        this.passengerCapacity = passengerCapacity;
	        this.previousPos = position;
	    }
	  
	  public static void initializeStations() {
		  int position = 0;
			for(int i = 0; i < 8; i++) {
				Station s = new Station(i, position);
				position += 2;
				stations.add(s);
				System.out.println(i + " " + s.getPosition());
			}
	  }
	  
	  public Station getStationByLocation(int position) {
		  System.out.println("Getting station");
		  return stations.get(position);
	  }
		
	  	@Override
		public void run() {
	  		setName("Train. " + id);  // i.e. Train.0
			while(true)
			{
				
				if (position == 16)
		    		position = 0;
		    	if (previousPos == 16)
		    		previousPos = 0;
		    	
		    	System.out.println("Train at: "+ position + " " + passengers + "/" +passengerCapacity);
				
		    	
				if (position % 2 == 0) {
					while (stations.get(position/2).isOccupied()) {
						synchronized(stations.get(position/2).trainLock) {
							System.out.println("Train " + this.id + " is waiting for Station " + stations.get(position/2).getId() + " to open.");
							try{
								stations.get(position/2).trainLock.wait();
							}catch(Exception e){
								e.printStackTrace();
							}		
						}
					}
					stations.get(position/2).addTrainIntoStation(this);
					synchronized(doorLock) {
						doorLock.notifyAll(); //inform passengers the doors are open, and can depart
					}
					
					if (getPassengerCount() != passengerCapacity) { //have seats
						synchronized(stations.get(position/2).boardingLock) {
							stations.get(position/2).boardingLock.notifyAll();
						}
					}
					int waitTime = stations.get(position/2).getPassengers().size() * 500;
					try {
						if (waitTime == 0)
							Thread.sleep(500);
						else
							Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stations.get(position/2).removeTrain();
				}
		    	position++;
		    	//System.out.println(position);
			}
	    }
		
	  	public synchronized boolean loadTrain(Passenger p) {
			//while there are still passengers waiting, and there are available seats, load train
//				System.out.println("Loading train " + this.id + " with passengers");
//				int passengersToGet = Math.min(
//						passengerCapacity - passengers.size(),
//						station.getPassengers().size());
//				if (passengersToGet == 0)
//					System.out.println("Train " + this.id +" is full");
//				else
//					System.out.println("Adding " + passengersToGet + " passengers");
//				for (int i = 0; i < passengersToGet ; i++) {
//					Passenger p = station.removeAndGetPassenger();
//					passengers.add(p);
//					System.out.println("Passenger added " + p.getId());
//				}
//	    		try {
//	        		Thread.sleep(10); // simulates small time wait    			
//	    		} catch (Exception e) {
//	    			e.printStackTrace();
//	    		}
//	    		notifyAll();
				if (passengers != passengerCapacity) {
					passengers++;
					System.out.println("Passenger " + p.getId() + " has boarded train " + this.id);
					return true;
				}
				return false;
				
		}
	  	
	  	public synchronized void unloadTrain(Passenger p) {
	  		passengers--;
	  		System.out.println("Passenger " + p.getId() + " has arrived at destination");
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
			
		public int getPassengerCount(){
			return passengers;
		}
		
		public boolean isFull(){
			return passengers == passengerCapacity;
		}
}
