package test;
import mco2.Train;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gui.TrainVisualPanel;
import mco2.Passenger;
import mco2.Station;

public class threadTest extends JFrame {
	
	static Scanner sc = new Scanner(System.in);
	public static Train[] railroad = new Train[16];
	public static ArrayList<Train> trains = new ArrayList<Train>();
	public static ArrayList<Station> stations = new ArrayList<Station>();
	public static ArrayList<Integer> trainCapacities = new ArrayList<Integer>();
	public static int location = 0;
	public static int availableSeats;
	public static int numTrains = 0;
	public static int idCounter = 0;
	public static int randStart = 0;
	public static int randDestination = 0;
	public static int min = 1;
	public static int max = 8;

	public threadTest() {
	}
	
	public static String represent() {
		Arrays.fill(railroad, null);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < trains.size(); i++){
			if (trains.get(i) == null)
				railroad[trains.get(i).getPosition()] = trains.get(i);
		}
		
		for (int i = 0; i < railroad.length; i ++) {
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
		
		new threadTest();
		Train.initializeStations();
			
		while (true) {
				
			System.out.println(represent());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		for(int i = 0; i < 16; i++) {
////			System.out.println("Input capacity for train " + (i + 1));
////			trainCapacities.add(sc.nextInt());
////			sc.nextLine();
//			trainCapacities.add(20);
//		}
		
		
//		Thread runnable = new Thread(){
//			@Override
//			public synchronized void run(){
//				while(true){
//						//Passenger p = new Passenger(idCounter, randStart, randDestination);
//						//stations.get(randStart).addWaitingPassenger(p);
//						for(Train train : trains) {			
//							for(Station station : stations) {
//								//System.out.println(station.getPosition() + " " + train.getPosition());
//								if (train.getPosition() == station.getPosition())
//								{
//									station.addTrainIntoStation(train);
//									train.unloadTrain(station);
//									if (station.getPassengers().size() != 0)
//										train.loadTrain(station);				
//								}
//							}
//						}
//						//notify();
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					//}
//				}
//			}
//		};
//		
//		Thread runnable2 = new Thread(){
//			@Override
//			public synchronized void run(){
//				int idCounter = 0;
//				int randStart = 0;
//				int randDestination = 0;
//				int min = 0;
//				int max = 7;			
//				
//				while(true){
//					idCounter++;
//					randStart = ThreadLocalRandom.current().nextInt(min, max + 1);
//					randDestination = ThreadLocalRandom.current().nextInt(min, max + 1);
//					
//					while(randStart == randDestination) {
//						randStart = ThreadLocalRandom.current().nextInt(min, max + 1);
//						randDestination = ThreadLocalRandom.current().nextInt(min, max + 1);
//						System.out.println(randStart);
//					}
//					
//					Passenger p = new Passenger(idCounter, randStart, randDestination);
//					stations.get(randStart).addWaitingPassenger(p);
//					
//					for(Train train : trains) {
//						if (railroad[train.getPrevPosition()] != null && railroad[train.getPrevPosition()].getId() == train.getId())
//							railroad[train.getPrevPosition()] = null;
//						railroad[train.getPosition()] =  train;
//					}
//					System.out.println(represent(railroad));
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					notify();
//				}
//			}
//		};
//		
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
//		for(Train t : trains) {
//			t.start();
//		}
		
//		runnable.start();
//		runnable2.start();
		//train2.start();
	}
	public static void dispatchTrain(Train newTrain){
        availableSeats = newTrain.getCapacity() - newTrain.getPassengerCount();
        
        TrainVisualPanel.trainName.get(numTrains).setText("Train#"+Long.toString(newTrain.getId()));
        TrainVisualPanel.trainSeats.get(numTrains).setText(newTrain.getPassengerCount() + "/" + newTrain.getCapacity());
    }
}
