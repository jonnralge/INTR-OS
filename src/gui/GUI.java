package gui;

import javax.swing.JFrame;
import gui.StartFrame;


public class GUI {

	public static JFrame startFrame;
    public static JFrame trainFrame;
    public static JFrame StrainFrame;
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
              startFrame = new StartFrame();    
        }
    });
    }
}
