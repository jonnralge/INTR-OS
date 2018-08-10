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
		Train t = waitForTrain(startStation);
		waitForDestination(t);
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
	public synchronized Train waitForTrain(Station station) {
		//while there is no train, wait
		synchronized(station.boardingLock) {
			while(!onTrain) {
				if (!station.isOccupied()) {
					onTrain = false;
						while(!station.isOccupied())
							try {
								station.boardingLock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				}
				try{
					if(station.currentTrain != null) {
						if (station.currentTrain.loadTrain(this)) {
						onTrain = true;
						station.removePassenger(this);
						}
					}
				}catch(Exception e){
					onTrain = false;
					e.printStackTrace();
				}
			}
			if (station.currentTrain != null)
				return station.currentTrain;
			else
				return null;
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
