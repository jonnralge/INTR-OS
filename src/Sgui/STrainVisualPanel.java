package Sgui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

public class STrainVisualPanel extends JPanel{

    public static ArrayList<JPanel> trainPanels = new ArrayList(16);
    public static ArrayList<JLabel> trainName = new ArrayList(16);
    public static ArrayList<JLabel> trainSeats = new ArrayList(16);
    
    Font trainNumFont = new Font("Dialog", Font.BOLD, 18);
    Font numFont = new Font("Dialog", Font.BOLD, 14);
    
    public STrainVisualPanel(){
        this.setLayout(new MigLayout("", "[150, fill, grow]", "[150, fill,  grow]"));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Train List", TitledBorder.CENTER, TitledBorder.TOP));
        
        buildComponents();
        assembleComponents();
    }
    
    public void buildComponents(){
        for(int i = 0; i < 16; i++){
            trainPanels.add(new JPanel(new MigLayout("")));
            trainPanels.get(i).setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Train # " + (i + 1), TitledBorder.CENTER, TitledBorder.TOP));
            
            trainName.add(new JLabel(""));
            trainName.get(i).setFont(trainNumFont);
            trainSeats.add(new JLabel(""));
            trainSeats.get(i).setFont(numFont);
        }
    }
    
    public void assembleComponents(){
        for(int i = 0 ; i < 16; i++){
            trainPanels.get(i).add(trainName.get(i), "pushx, center, wrap 5");
            trainPanels.get(i).add(trainSeats.get(i), "pushx, center, wrap");
            
            this.add(trainPanels.get(i), "cell " + i%4 + " " + i/4);
        }  
    }
}
