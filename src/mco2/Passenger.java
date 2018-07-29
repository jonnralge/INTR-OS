package mco2;

public class Passenger {

	public synchronized void waitForTrain(Station station) {
		//while there is no train, wait
	}
	
	public synchronized void boardTrain(Station station) {
		//when passenger boards the train at a station, signal that the passenger has been seated
	}
}
