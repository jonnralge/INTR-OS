package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import gui.TrainControlPanel;

public class TrainFrame extends JFrame{

	JScrollPane jsp = new JScrollPane(new TrainControlPanel());
    public TrainFrame(){
        this.setTitle("CalTrain Train Manager");
        this.setSize(1050,750);
        this.setMinimumSize(new Dimension(1050, 750));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(jsp);
    }
}
