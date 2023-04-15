package impl;

import java.util.concurrent.TimeUnit;
import configuration.AirQualityConfiguration;
import fr.liglab.adele.icasa.service.scheduler.PeriodicRunnable;
import fr.liglab.adele.icasa.device.security.Siren;
import window.Window;
import java.util.Map;
import service.AirQualityContextService;

public class AirQualityImpl implements PeriodicRunnable, AirQualityConfiguration {

	/** Field for sirens dependency */
	private Siren[] sirens;
	/** Field for windows dependency */
	private Window[] windows;
	/** Field for airQualityContextService dependency */
	private AirQualityContextService airQualityContextService;

	@Override
	public void run() {
		System.out.println("Récupération du contexte, et traitement des données");
		System.out.println("Taux de CO2 dans l'air : " + airQualityContextService.getCO2Concentration("test"));
	}

	@Override
	public long getPeriod() {
		return 5;
	}

	@Override
	public TimeUnit getUnit() {
		// TODO Auto-generated method stub
		return TimeUnit.MINUTES;
	}

	/** Bind Method for windows dependency */
	public void bindWindow(Window window, Map properties) {
		System.out.println("bind window " + window.getSerialNumber());
	}

	/** Unbind Method for windows dependency */
	public void unbindWindow(Window window, Map properties) {
		System.out.println("unbind window " + window.getSerialNumber());
	}

	/** Bind Method for sirens dependency */
	public void bindSiren(Siren siren, Map properties) {
		System.out.println("bind siren " + siren.getSerialNumber());
	}

	/** Unbind Method for sirens dependency */
	public void unbindSiren(Siren siren, Map properties) {
		System.out.println("unbind siren " + siren.getSerialNumber());
	}

	/** Component Lifecycle Method */
	public void stop() {
		System.out.println("Stop component AirQuality ...");
	}

	/** Component Lifecycle Method */
	public void start() {
		System.out.println("Start component AirQuality ...");
	}

	@Override
	public double getMaximumAllowedEnergyInRoom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximumAllowedEnergyInRoom(double maximumEnergy) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMode(int mode) {
		// TODO Auto-generated method stub

	}

}
