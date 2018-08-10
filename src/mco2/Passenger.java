package mco2;

public class Passenger extends Thread{
	private final int id;
	private final Station startStation;
	private final Station destinationStation;
	private boolean onTrain = false;
	public Passenger(int id, Station startStation, Station destinationStation) {
		this.id 				= id;
		this.startStation 		= startStation;
		this.destinationStation = destinationStation;
	}
	
	public void run(){
		waitForTrain(startStation);
	}
	
	public synchronized void waitForDestination(Train train) {
		System.out.println("Passenger " + id + " on train " + train.getId());
		synchronized(train.doorLock) {
				while(train.getPosition() != destinationStation.getPosition()) {
					System.out.println("Waiting for destination");	
					try {
							train.doorLock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		}
		System.out.println("Passenger " + id + " has arrived at destination");
		train.unloadTrain(this);
	}
	public synchronized void waitForTrain(Station station) {
		//while there is no train, wait
		synchronized(station.boardingLock) {
			while(!onTrain) {
				if (!station.isOccupied()) {
					onTrain = false;
					System.out.println("Waiting");
						while(!station.isOccupied())
							try {
								station.boardingLock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				}
					if(station.currentTrain.loadTrain(this) && 
						station.currentTrain != null) {
						onTrain = true;
					}
			}
			if (station.currentTrain != null)
				waitForDestination(station.currentTrain);
		}
	}
	
	public Station getStartStation() {
		return startStation;
	}

	public int getDestination() {
		return destinationStation.getId();
	}
	
	public long getId() {
		return id;
	}
	
}
