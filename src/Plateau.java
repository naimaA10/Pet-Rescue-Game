import java.awt.event.ActionEvent;

public class Plateau {
	
	protected Vue view;
	
	public Plateau() {
		view = new Vue();
		view.setVisible(true);
	}

	
	public void modifier(ActionEvent e, int l, int c) {
		view.modifier(e, l, c);
	}


}

