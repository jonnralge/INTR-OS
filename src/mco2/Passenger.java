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
		startStation.addWaitingPassenger(this);
	}
	
	public void run(){
		Train t = waitForTrain(startStation);
		waitForDestination(t);
	}
	
	public void waitForDestination(Train train) {
		synchronized(train.doorLock) {
				while(train.getPosition() != destinationStation.getPosition()) {
					try {
							train.doorLock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		}
		train.unloadTrain(this);
	}
	public Train waitForTrain(Station station) {
		Train train = null;
		//while there is no train, wait
		synchronized(station.boardingLock) {
			while(!onTrain) {
				train = station.currentTrain;
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
					if(train.loadTrain(this)) {
						onTrain = true;
						station.removePassenger(this);
						return train;
					}
				}catch(Exception e){
					onTrain = false;
				}
			}
			return train;
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
