package client_system;

public class reservation_application {
	public static void main (String argv[]){
		ReservationControl rc = new ReservationControl();
		MainFrame mainFrame = new MainFrame(rc);
		mainFrame.setBounds(5,5,620,500);
		mainFrame.setVisible(true);
	}
}
