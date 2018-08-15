package Sgui;

import test.TrainSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import Smco2.Train;
import gui.TrainControlPanel;
import net.miginfocom.swing.MigLayout;

public class STrainControlPanel extends JPanel implements ActionListener {

	TrainSystem TrainSystem;
	
    JPanel addTrainPanel, headerPanel, sidePanel;
    	
    SStationVisualPanel stationPanel = new SStationVisualPanel();
    SPassengerWaitingPanel waitingPanel = new SPassengerWaitingPanel();
    STrainVisualPanel trainPanel = new STrainVisualPanel();
    
    JLabel label_header, label_train_num, label_train_count, label_train_seats;
    
    JTextField textfield_train_seats;
    
    JSeparator separator = new JSeparator();
    JButton button_add_train;

    Font headerFont = new Font("Dialog", Font.PLAIN, 20);
    Font trainNumFont = new Font("Dialog", Font.PLAIN, 18);
    Font labelFont = new Font("Dialog", Font.PLAIN, 14);
    Font fieldFont = new Font("Dialog", Font.PLAIN, 16);
    Font buttonFont = new Font("Dialog", Font.BOLD, 16);
    Font borderTitleFont = new Font("Dialog", Font.PLAIN + Font.BOLD, 16);
    
    public STrainControlPanel(){
        TrainSystem = new TrainSystem();
        
        this.setLayout(new MigLayout("inset 20"));

        buildComponents();
        assembleComponents();
    }
    
    public void buildComponents(){
        headerPanel = new JPanel();
        headerPanel.setLayout(new MigLayout(""));
        
        addTrainPanel = new JPanel();
        addTrainPanel.setLayout(new MigLayout(""));
        addTrainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Add a Train", TitledBorder.LEFT, TitledBorder.TOP, borderTitleFont));
        
        sidePanel = new JPanel(new MigLayout());
        
        label_header = new JLabel("Train Manager");
        label_header.setFont(headerFont);
        
        label_train_num = new JLabel("Active Trains");
        label_train_num.setFont(trainNumFont);
        
        label_train_count = new JLabel("0 out of 16");
        label_train_count.setFont(trainNumFont);
        label_train_count.setForeground(Color.red);
        
        label_train_seats = new JLabel("Num of Seats");
        label_train_seats.setFont(fieldFont);
        
        textfield_train_seats = new JTextField(10);
        textfield_train_seats.setFont(fieldFont);
        
        button_add_train = new JButton("Add Train");
        button_add_train.setFocusPainted(false);
        button_add_train.setFont(buttonFont);
        button_add_train.setForeground(Color.BLACK);
        button_add_train.addActionListener(this);  
    }
    
    public void assembleComponents(){
        headerPanel.add(label_header, "pushx, center, gaptop 20, wrap");
        headerPanel.add(separator, "growx, wrap 20, wrap");
        sidePanel.add(headerPanel, "pushx, growx, wrap");
        
        addTrainPanel.add(label_train_seats, "wrap");
        addTrainPanel.add(textfield_train_seats, "growx, pushx,wrap 10");
        addTrainPanel.add(button_add_train, "span, center");
        
        sidePanel.add(label_train_num, "pushx, center, wrap");
        sidePanel.add(label_train_count, "pushx, center, wrap 20");
        sidePanel.add(addTrainPanel, "pushx, growx");
        this.add(sidePanel, "dock west");
        
        this.add(trainPanel, "center, grow, push");
    }
    
    public boolean checkAddTrain(String seats){
        int trainSeats = 0;
        try{
            trainSeats = Integer.parseInt(seats);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(STrainControlPanel.this, "Invalid Train Number Seat", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(trainSeats <= 0){
            JOptionPane.showMessageDialog(STrainControlPanel.this, "Please Enter a Positive Seat Number", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button_add_train){
            String seats = textfield_train_seats.getText();
            
            if(checkAddTrain(seats)){
            	if (TrainSystem.numOfTrains == 16) {
					System.out.println("Max trains deployed");
            		JOptionPane.showMessageDialog(STrainControlPanel.this, "Maximum Train Limit", "Error",JOptionPane.ERROR_MESSAGE);
            	}
            	else {
            		int numSeats = Integer.parseInt(textfield_train_seats.getText());
            		
            		test.TrainSystem.dispatchTrain(Train.getId()+1, numSeats);

					JOptionPane.showMessageDialog(STrainControlPanel.this, "Created a New Train", "Success", JOptionPane.INFORMATION_MESSAGE);
		            textfield_train_seats.setText("");
		            label_train_count.setText(TrainSystem.numOfTrains + " out of 16");
				}
            }
        }
    }
}
