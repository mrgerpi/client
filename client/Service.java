package client;

public class Service {

	public void setMainFrame(User user){
		MainFrame frame = new MainFrame(user);
		
		frame.setVisible(true);
	}
}
