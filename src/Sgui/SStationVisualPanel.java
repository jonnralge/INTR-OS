package Sgui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

public class SStationVisualPanel extends JPanel{
    public static JPanel Station1Panel, Station2Panel, Station3Panel, Station4Panel, Station5Panel, Station6Panel, Station7Panel, Station8Panel;
    
    public static JLabel label_1_train, label_2_train, label_3_train, label_4_train, label_5_train, label_6_train, label_7_train, label_8_train;
    
    Font stationFont = new Font("Georgia", Font.PLAIN, 20);
    
    public SStationVisualPanel(){
        this.setLayout(new MigLayout("", "[150,fill, grow]", "[150,fill]"));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Stations", TitledBorder.CENTER, TitledBorder.TOP));
        buildComponents();
        assembleComponents();
    }
    
    public void buildComponents(){
        
        label_1_train = new JLabel("");
        label_1_train.setFont(stationFont);
        
        label_2_train = new JLabel("");
        label_2_train.setFont(stationFont);
        
        label_3_train = new JLabel("");
        label_3_train.setFont(stationFont);
        
        label_4_train = new JLabel("");
        label_4_train.setFont(stationFont);
        
        label_5_train = new JLabel("");
        label_5_train.setFont(stationFont);
        
        label_6_train = new JLabel("");
        label_6_train.setFont(stationFont);
        
        label_7_train = new JLabel("");
        label_7_train.setFont(stationFont);
        
        label_8_train = new JLabel("");
        label_8_train.setFont(stationFont);
        
        Station1Panel = new JPanel(new MigLayout());
        Station1Panel.setBackground(Color.cyan);
        Station1Panel.add(new JLabel("Station1"), "pushx, center, wrap 10");
        Station1Panel.add(label_1_train, "pushx, center");
        
        Station2Panel = new JPanel(new MigLayout());
        Station2Panel.setBackground(Color.cyan);
        Station2Panel.add(new JLabel("Station2"), "pushx, center, wrap 10");
        Station2Panel.add(label_2_train, "pushx, center");
        
        Station3Panel = new JPanel(new MigLayout());
        Station3Panel.setBackground(Color.cyan);
        Station3Panel.add(new JLabel("Station3"), "pushx, center, wrap 10");
        Station3Panel.add(label_3_train, "pushx, center");
        
        Station4Panel = new JPanel(new MigLayout());
        Station4Panel.setBackground(Color.cyan);
        Station4Panel.add(new JLabel("Station4"), "pushx, center, wrap 10");
        Station4Panel.add(label_4_train, "pushx, center");
        
        Station5Panel = new JPanel(new MigLayout());
        Station5Panel.setBackground(Color.cyan);
        Station5Panel.add(new JLabel("Station5"), "pushx, center, wrap 10");
        Station5Panel.add(label_5_train, "pushx, center");
        
        Station6Panel = new JPanel(new MigLayout());
        Station6Panel.setBackground(Color.cyan);
        Station6Panel.add(new JLabel("Station6"), "pushx, center, wrap 10");
        Station6Panel.add(label_6_train, "pushx, center");
        
        Station7Panel = new JPanel(new MigLayout());
        Station7Panel.setBackground(Color.cyan);
        Station7Panel.add(new JLabel("Station7"), "pushx, center, wrap 10");
        Station7Panel.add(label_7_train, "pushx, center");
        
        Station8Panel = new JPanel(new MigLayout());
        Station8Panel.setBackground(Color.cyan);
        Station8Panel.add(new JLabel("Station8"), "pushx, center, wrap 10");
        Station8Panel.add(label_8_train, "pushx, center");
    }
    
    public void assembleComponents(){
        this.add(Station1Panel, "cell 0 0");
        this.add(Station2Panel, "cell 1 0");
        this.add(Station3Panel, "cell 2 0");
        this.add(Station4Panel, "cell 3 0");
        this.add(Station5Panel, "cell 4 0");
        this.add(Station6Panel, "cell 5 0");
        this.add(Station7Panel, "cell 6 0");
        this.add(Station8Panel, "cell 7 0");
    } 
}
