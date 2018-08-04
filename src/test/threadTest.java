package test;
import mco2.Train;

import java.util.ArrayList;

import mco2.Passenger;
import mco2.Station;

public class threadTest {
	static int counter = 0;
	
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
	public static void main(String args[]) {
		
//		Runner runner = new Runner();
//		runner.start();
//		
//		Runner runner2 = new Runner();
//		runner2.start();
		Train[] railroad = new Train[18];
		ArrayList<Train> trains = new ArrayList<Train>();
		Station[] stations = new Station[2];
 		Station station1 = new Station(0, 2);
		Station station2 = new Station(1, 4);
		
		stations[0] = station1;
		stations[1] = station2;
		Passenger passenger1 = new Passenger(0, 1, 2);
		
		Train train1 = new Train(0, 0, 20);
		Train train2 = new Train(1, 1, 20);
		Train train3 = new Train(2, 2, 20);
		
		trains.add(train1);
		trains.add(train2);
		trains.add(train3);
		railroad[0] = train1;
		railroad[1] = train2;
		railroad[2] = train3;
		
		Thread runnable = new Thread(){
			@Override
			public synchronized void run(){
				station1.addWaitingPassenger(passenger1);
				while(true){
					for(Train train : trains) {
						if (railroad[train.getPrevPosition()].getId() == train.getId())
							railroad[train.getPrevPosition()] = null;
						railroad[train.getPosition()] =  train;
						
//						for(Station station : stations) {
//							//System.out.println(station.getPosition() + " " + train.getPosition());
//							if (train.getPosition() == station.getPosition())
//							{
//								station.loadTrain(train);
//								try {
//									System.out.println("Waiting for passengers to finish boarding");
//									wait();
//								} catch (InterruptedException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								station.removeTrain();
//							}
//						}
					}
					
					System.out.println(represent(railroad));
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
				while(true){
					//wait();
					if (railroad[train2.getPrevPosition()].getId() == train2.getId())
						railroad[train2.getPrevPosition()] = null;
					railroad[train2.getPosition()] =  train2;
					System.out.println(represent(railroad));
					notifyAll();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		train1.start();
		train2.start();
		train3.start();
		runnable.start();
		
		//train2.start();
	}
}
