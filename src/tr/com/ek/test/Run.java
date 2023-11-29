package tr.com.ek.test;

import javax.swing.SwingUtilities;

import tr.com.ek.fe.AnaPencereFe;

public class Run {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new AnaPencereFe();
			}
		});
	}
}
