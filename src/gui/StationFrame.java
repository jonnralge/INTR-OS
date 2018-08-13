package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class StationFrame extends JFrame{
    public static JPanel stationPanel = new StationVisualPanel();
    public static JPanel passengerPanel = new PassengerWaitingPanel();
    
    public StationFrame(){
        this.setTitle("CalTrain Station Manager");
        this.setSize(1200,600);
        this.setMinimumSize(new Dimension(1200, 600));
        this.setLocation(10,10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new MigLayout());
        
        this.add(passengerPanel, "pushx, growx, wrap");
        this.add(stationPanel, "pushx, growx, wrap");

        this.add(new ManagePassengerPanel(), "pushx, center");
    }
}
