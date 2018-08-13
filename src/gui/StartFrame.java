package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import gui.GUI;
//import gui.StationFrame;
import gui.TrainFrame;
import mco2.Train;
import net.miginfocom.swing.MigLayout;
import test.threadTest;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class StartFrame extends JFrame implements ActionListener{

	public JRadioButton semaphore, monitor;
    public JButton button;
    
    public StartFrame(){
        this.setTitle("Choose an Implementation");
        this.setSize(200,200);
        this.setMinimumSize(new Dimension(200, 200));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new MigLayout(""));
        
        semaphore = new JRadioButton("Semaphore");
        monitor = new JRadioButton("Monitors");
        button = new JButton("Start Simulation");
        ButtonGroup group = new ButtonGroup();
        group.add(semaphore);
        group.add(monitor);
        
        this.add(semaphore, "split 2, gapbottom 40");
        this.add(monitor, "wrap");
        this.add(button, "push, grow");
        
        button.addActionListener(this);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(semaphore.isSelected()){
            System.out.print("yo");
        }
        else if(monitor.isSelected()){
        	this.setVisible(false);
            //Caltrain_GUI.textFrame = new TextFrame();
            GUI.trainFrame = new TrainFrame();
            //GUI.startFrame = new StationFrame();

            new threadTest();
    		Train.initializeStations();
    		
    		/*while (true) {
    				
    			System.out.println(threadTest.represent());
    			try {
    				Thread.sleep(500);
    			} catch (InterruptedException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		}*/
        }
        
    }
}