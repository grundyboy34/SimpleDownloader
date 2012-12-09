import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Loader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	        	try {
	        		UIManager.setLookAndFeel(info.getClassName());
	        	} catch (Exception e) {
	        		
	        	}
	            break;
	        }
	    }
		new Loader();
	}

	public Loader() {
		String current = "C:/";
		try {
			current = new java.io.File(".").getCanonicalPath();
			GUI frame = new GUI(current);
			frame.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
