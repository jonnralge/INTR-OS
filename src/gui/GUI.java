package gui;

import javax.swing.JFrame;
import gui.StartFrame;


public class GUI {

	public static JFrame startFrame;
    public static JFrame trainFrame;
<<<<<<< HEAD
    public static JFrame StrainFrame;
    
=======

>>>>>>> branch 'Gui' of https://github.com/jonnralge/INTR-OS.git
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
              startFrame = new StartFrame();    
        }
    });
    }
}
