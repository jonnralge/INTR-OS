package test;
import mco2.Train;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import mco2.Passenger;
import mco2.Station;

public class threadTest {
	static int counter = 0;
	static Scanner sc = new Scanner(System.in);
	static class Runner extends Thread {
		@Override
		public synchronized void run(){
			for (int i = 0; i < 5; i++)
			{
				counter++;
				System.out.println("yes " + counter);
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String represent(Train[] railroad) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < railroad.length; i++){
			if (railroad[i] == null)
				sb.append("=");
			else
				sb.append(railroad[i].getId());
		}
		return sb.toString();
	}
	
//	Here's the logic for adding a train, it adds a train in the first free space it sees in the railroad, but yeah
//			it's hard to test without GUI, also needs to keep
			
			
	public static void main(String args[]) {
		
//		Runner runner = new Runner();
//		runner.start();
//		
//		Runner runner2 = new Runner();
//		runner2.start();
		Train[] railroad = new Train[33];
		ArrayList<Train> trains = new ArrayList<Train>();
		ArrayList<Station> stations = new ArrayList<Station>();
		int position = 0;
		for(int i = 0; i < 8; i++) {
			Station s = new Station(i, position);
			position += 4;
			stations.add(s);
		}


		int trainPosition = 1, capacity = 30;
//		for(int i = 0; i < 16; i++) {
//			Train t = new Train(i, trainPosition, capacity);
//			trainPosition += 2;
//			trains.add(t);
//		}
		
		
		Thread runnable = new Thread(){
			@Override
			public synchronized void run(){
				int trainId = 0;
				int numSeats = 0;
				int freePosition = 0;
				while(true){
					//Passenger p = new Passenger(idCounter, randStart, randDestination);
					//stations.get(randStart).addWaitingPassenger(p);
					for(Train train : trains) {			
						for(Station station : stations) {
							//System.out.println(station.getPosition() + " " + train.getPosition());
							if (train.getPosition() == station.getPosition())
							{
								station.addTrainIntoStation(train);
								train.unloadTrain(station);
								if (station.getPassengers().size() != 0)
									train.loadTrain(station);				
							}
						}
					}
					//notify();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		
		Thread runnable2 = new Thread(){
			@Override
			public synchronized void run(){
				int idCounter = 0;
				int randStart = 0;
				int randDestination = 0;
				int min = 0;
				int max = 7;			
				
				while(true){
					idCounter++;
					randStart = ThreadLocalRandom.current().nextInt(min, max + 1);
					randDestination = ThreadLocalRandom.current().nextInt(min, max + 1);
					while(randStart == randDestination) {
						randStart = ThreadLocalRandom.current().nextInt(min, max + 1);
						randDestination = ThreadLocalRandom.current().nextInt(min, max + 1);
					}
					
					Passenger p = new Passenger(idCounter, randStart, randDestination);
					stations.get(randStart).addWaitingPassenger(p);
					
					for(Train train : trains) {
						if (railroad[train.getPrevPosition()] != null && railroad[train.getPrevPosition()].getId() == train.getId())
							railroad[train.getPrevPosition()] = null;
						railroad[train.getPosition()] =  train;
					}
					System.out.println(represent(railroad));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					notify();
				}
			}
			//Here's the function for adding a train, it's hard to test properly without the GUI but yeah all the logic is here
			public synchronized void addTrain() {
				int trainId = 0;
				int numSeats = 0;
				int freePosition = 0;
				int choice = sc.nextInt();
				if(choice == 1) {
					System.out.print("How many seats for the new train? ");
					numSeats = sc.nextInt();
					while(numSeats < 0) {
						System.out.println("Invalid input.\nHow many seats for the new train? ");
						numSeats = sc.nextInt();
					}
					for(int i = 0; i < railroad.length; i++) {
						if(railroad[i] == null)
							freePosition = i;
					}
					Train t = new Train(trainId, freePosition, numSeats);
					trainId++;
					t.start();
					trains.add(t);
				}
				notifyAll();
			}
		};
		
//		Thread runnable2 = new Thread(){
//			@Override
//			public synchronized void run(){
//				while(true){
//					//wait();
//					if (railroad[train2.getPrevPosition()].getId() == train2.getId())
//						railroad[train2.getPrevPosition()] = null;
//					railroad[train2.getPosition()] =  train2;
//					System.out.println(represent(railroad));
//					notifyAll();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//			}
//		};
		for(Train t : trains) {
			t.start();
		}
		runnable.start();
		runnable2.start();
		//train2.start();
	}
}
