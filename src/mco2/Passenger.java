package mco2;

public class Passenger {
	private final int id;
	private final int startStation;
	private final int destinationStation;
	public Passenger(int id, int startStation, int destinationStation) {
		this.id 				= id;
		this.startStation 		= startStation;
		this.destinationStation = destinationStation;
	}

//	public synchronized void waitForTrain(Station station) {
//		//while there is no train, wait
//	}
//	
//	public synchronized void boardTrain(Station station) {
//		//when passenger boards the train at a station, signal that the passenger has been seated
//		station.passengerBoardTrain(this);
//	}
	
	public int getStartStation() {
		return startStation;
	}

	public int getDestination() {
		return destinationStation;
	}
	
	public int getId() {
		return id;
	}
}
