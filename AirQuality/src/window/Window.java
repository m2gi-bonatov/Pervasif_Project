package window;

import fr.liglab.adele.icasa.device.GenericDevice;

public interface Window extends GenericDevice{
	void openWindow();
	void closeWindow();
	boolean getStateWindow();
	static String WINDOW_STATE = "false";
}
