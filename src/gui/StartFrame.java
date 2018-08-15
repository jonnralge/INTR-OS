package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import gui.GUI;
import gui.TrainFrame;
import Sgui.SStationFrame;
import Sgui.STrainFrame;
import mco2.Train;
import net.miginfocom.swing.MigLayout;
import test.TrainSystem;
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
    		this.setVisible(false);
        	
            GUI.StrainFrame = new STrainFrame();
            GUI.startFrame = new SStationFrame();

    		TrainSystem t = new TrainSystem();
        }
        else if(monitor.isSelected()){
        	this.setVisible(false);
        	
            GUI.trainFrame = new TrainFrame();
            GUI.startFrame = new StationFrame();

    		Train.initializeStations();
        }  
    }
}