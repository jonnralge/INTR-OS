package Sgui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import Smco2.Passenger;
import Smco2.Train;
import net.miginfocom.swing.MigLayout;
import test.TrainSystem;

public class SManagePassengerPanel extends JPanel implements ActionListener {
    
    JButton	button_add_passengers;
    
    Font labelFont = new Font("Trebuchet MS", Font.PLAIN + Font.BOLD, 14);
        
    public SManagePassengerPanel(){
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
    		TrainSystem.generatePassengers();
    	}
    }   
}