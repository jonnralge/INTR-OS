package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

public class PassengerWaitingPanel extends JPanel{

    public static JPanel Station1, Station2, Station3, Station4, Station5, Station6, Station7, Station8;
    
    public static JLabel label_1_waiting, label_2_waiting, label_3_waiting, label_4_waiting, label_5_waiting, label_6_waiting, label_7_waiting, label_8_waiting;

    Font stationFont = new Font("Georgia", Font.PLAIN, 20);
    
    public PassengerWaitingPanel(){
        this.setLayout(new MigLayout("", "[150,fill, grow]", "[150,fill]"));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Waiting", TitledBorder.CENTER, TitledBorder.TOP));
        
        buildComponents();
        assembleComponents();
    }
    
    public void buildComponents(){
        label_1_waiting = new JLabel("0");
        label_1_waiting.setFont(stationFont);
        
        label_2_waiting = new JLabel("0");
        label_2_waiting.setFont(stationFont);
        
        label_3_waiting = new JLabel("0");
        label_3_waiting.setFont(stationFont);
        
        label_4_waiting = new JLabel("0");
        label_4_waiting.setFont(stationFont);
        
        label_5_waiting = new JLabel("0");
        label_5_waiting.setFont(stationFont);
        
        label_6_waiting = new JLabel("0");
        label_6_waiting.setFont(stationFont);
        
        label_7_waiting = new JLabel("0");
        label_7_waiting.setFont(stationFont);
        
        label_8_waiting = new JLabel("0");
        label_8_waiting.setFont(stationFont);
        
        Station1 = new JPanel(new MigLayout());
        Station1.setBackground(Color.white);
        Station1.add(label_1_waiting, "pushx, center, wrap 10, gaptop 10");
        Station1.add(new JLabel("Waiting Passengers"), "pushx, center");
        
        Station2 = new JPanel(new MigLayout());
        Station2.setBackground(Color.white);
        Station2.add(label_2_waiting, "pushx, center, wrap 10, gaptop 10");
        Station2.add(new JLabel("Waiting Passengers"), "pushx, center");
        
        Station3 = new JPanel(new MigLayout());
        Station3.setBackground(Color.white);
        Station3.add(label_3_waiting, "pushx, center, wrap 10, gaptop 10");
        Station3.add(new JLabel("Waiting Passengers"), "pushx, center");
        
        
        Station4 = new JPanel(new MigLayout());
        Station4.setBackground(Color.white);
        Station4.add(label_4_waiting, "pushx, center, wrap 10, gaptop 10");
        Station4.add(new JLabel("Waiting Passengers"), "pushx, center");
        
        Station5 = new JPanel(new MigLayout());
        Station5.setBackground(Color.white);
        Station5.add(label_5_waiting, "pushx, center, wrap 10, gaptop 10");
        Station5.add(new JLabel("Waiting Passengers"), "pushx, center");
        
        Station6 = new JPanel(new MigLayout());
        Station6.setBackground(Color.white);
        Station6.add(label_6_waiting, "pushx, center, wrap 10, gaptop 10");
        Station6.add(new JLabel("Waiting Passengers"), "pushx, center");
        
        Station7 = new JPanel(new MigLayout());
        Station7.setBackground(Color.white);
        Station7.add(label_7_waiting, "pushx, center, wrap 10, gaptop 10");
        Station7.add(new JLabel("Waiting Passengers"), "pushx, center");
        
        Station8 = new JPanel(new MigLayout());
        Station8.setBackground(Color.white);
        Station8.add(label_8_waiting, "pushx, center, wrap 10, gaptop 10");
        Station8.add(new JLabel("Waiting Passengers"), "pushx, center");
    }
    
    public void assembleComponents(){
        this.add(Station1, "cell 0 0");
        this.add(Station2, "cell 1 0");
        this.add(Station3, "cell 2 0");
        this.add(Station4, "cell 3 0");
        this.add(Station5, "cell 4 0");
        this.add(Station6, "cell 5 0");
        this.add(Station7, "cell 6 0");
        this.add(Station8, "cell 7 0");
    }
}
