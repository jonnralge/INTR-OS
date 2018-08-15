package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import mco2.Passenger;
import mco2.Train;
import net.miginfocom.swing.MigLayout;
import test.threadTest;

public class ManagePassengerPanel extends JPanel implements ActionListener {
    
    JButton	button_add_passengers;
        
    public ManagePassengerPanel(){
        this.setLayout(new MigLayout("", "[]20[]", ""));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "", TitledBorder.CENTER, TitledBorder.TOP));
  
        buildComponents();
        assembleComponents();
    }
    
    public void buildComponents(){
        button_add_passengers = new JButton("Add Passengers");
        button_add_passengers.addActionListener(this);
    }
    
    public void assembleComponents(){
    	this.add(button_add_passengers, "pushx, growx, span");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button_add_passengers){
        	threadTest.idCounter++;
        	threadTest.randStart = ThreadLocalRandom.current().nextInt(threadTest.min, threadTest.max + 1);
        	threadTest.randDestination = ThreadLocalRandom.current().nextInt(threadTest.min, threadTest.max + 1);
			
			while(threadTest.randStart == threadTest.randDestination) {
				threadTest.randStart = ThreadLocalRandom.current().nextInt(threadTest.min, threadTest.max + 1);
				threadTest.randDestination = ThreadLocalRandom.current().nextInt(threadTest.min, threadTest.max + 1);
			}
			//System.out.println(threadTest.randStart + " " + threadTest.randDestination);
			Passenger p = new Passenger(threadTest.idCounter, Train.stations.get(threadTest.randStart - 1), Train.stations.get(threadTest.randDestination - 1));

			p.start();
        }  
    }   
}
