package windowImpl;

import fr.liglab.adele.icasa.device.util.AbstractDevice;
import fr.liglab.adele.icasa.simulator.SimulatedDevice;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.ServiceProperty;
import org.apache.felix.ipojo.annotations.StaticServiceProperty;

import window.Window;


@Component(name = "iCasa.WindowSensor")
@Provides(properties = { @StaticServiceProperty(type = "java.lang.Boolean", name = "Window") })
public class WindowSensorImpl extends AbstractDevice implements Window, SimulatedDevice {
	
	@ServiceProperty(name = Window.DEVICE_SERIAL_NUMBER, mandatory = true)
    private String m_serialNumber;

    public WindowSensorImpl() {
    	super();
    	
    	super.setPropertyValue(SimulatedDevice.LOCATION_PROPERTY_NAME, SimulatedDevice.LOCATION_UNKNOWN);
    	setPropertyValue(Window.WINDOW_STATE, false);
    }
	
	@Override
	public String getSerialNumber() {
		return m_serialNumber;
	}

	@Override
	public void openWindow() {
		setPropertyValue(Window.WINDOW_STATE, true);
		System.out.println("Fenêtre " + getSerialNumber() + " ouverte dans la pièce " +super.getPropertyValue(LOCATION_PROPERTY_NAME));
	}

	@Override
	public void closeWindow() {
		setPropertyValue(Window.WINDOW_STATE, false);
		System.out.println("Fenêtre " + getSerialNumber() + " fermée dans la pièce " +super.getPropertyValue(LOCATION_PROPERTY_NAME));
	}

	@Override
	public boolean getStateWindow() {
		// TODO Auto-generated method stub
		return (boolean) getPropertyValue(Window.WINDOW_STATE);
	}
}
