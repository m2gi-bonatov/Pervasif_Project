package impl;

import java.util.concurrent.TimeUnit;
import configuration.AirQualityConfiguration;
import fr.liglab.adele.icasa.service.scheduler.PeriodicRunnable;
import fr.liglab.adele.icasa.device.security.Siren;
import window.Window;
import java.util.Map;
import service.AirQualityContextService;
import communication.AirQualityCommunication;

public class AirQualityImpl implements PeriodicRunnable, AirQualityConfiguration {

	/** Field for sirens dependency */
	private Siren[] sirens;
	/** Field for windows dependency */
	private Window[] windows;
	/** Field for airQualityContextService dependency */
	private AirQualityContextService airQualityContextService;
	/** Field for communication dependency */
	private AirQualityCommunication communication;

	private String modeActuel = "PRESENT";

	private int maxDetectorByEnergy = 1;
	/**
	* The maximum energy consumption allowed in a room in Watt:
	**/
	private double maximumEnergyConsumptionAllowedInARoom = 100.0d;

	private String[] salles = { "Chambre", "Cuisine", "Salle de bain", "Salon" };
	
	/**
	 * The name of the LOCATION property
	 */
	public static final String LOCATION_PROPERTY_NAME = "Location";

	/**
	 * The name of the location for unknown value
	 */
	public static final String LOCATION_UNKNOWN = "unknown";
	
	
	
	@Override
	public void run() {
		System.out.println("____________________________________________________");
		int cmptAlarmeCO = 0;
		int cmptAlarmeCO2 = 0;
		if(this.modeActuel == "ETEINT") {
			System.out.println("Le système est éteint");
		}else {
			System.out.println("Mode : " + getMode() + " Energy : " + maxDetectorByEnergy);
			double CODehors = airQualityContextService.getCOConcentrationDehors(maxDetectorByEnergy);
			double CO2Dehors = airQualityContextService.getCO2ConcentrationDehors(maxDetectorByEnergy);
			boolean urgence = false;
			
			for (String salle : salles) {
				System.out.println("***************************************************************************");
				double CO2 = airQualityContextService.getCO2Concentration(salle, maxDetectorByEnergy);
				double CO = airQualityContextService.getCOConcentration(salle, maxDetectorByEnergy);
				boolean fenetre = false;
				System.out.println(salle.toUpperCase() + ": Taux CO2 - (" + CO2 + ") et Taux CO - (" + CO + ")");
				
				
				//Traitement du CO
				if(CO == -1.0) {
					//Appel des réparateurs
					communication.appelReparateur(salle);
				}else if(CO < 1000.0) {
					cmptAlarmeCO++;
					//Fermeture des fenêtres de la pièce
					fenetre = fenetre || false;
				}else if(CO >= 1000.0 && CO < 2000.0) {
					cmptAlarmeCO++;
					if(this.modeActuel == "PRESENT") {
						//Ouverture des fenêtres
						if(CODehors < CO) {
							fenetre = fenetre || true;
						}else if(CODehors >= CO) {
							System.out.println("Le Taux de CO est pire dehors (" + CODehors +") les fenêtres restent fermées");
						}
					}else if(this.modeActuel == "SILENCIEUX") {
						//Ouverture des fenêtres
						if(CODehors < CO) {
							fenetre = fenetre || true;
						}else if(CODehors >= CO) {
							System.out.println("Le Taux de CO est pire dehors (" + CODehors +") les fenêtres restent fermées");
						}
					}
				}else if(CO >= 2000.0) {
					//Appel Pompier, Police et Samu
					urgence = urgence || true;
					if(this.modeActuel == "PRESENT" || this.modeActuel == "NUIT" || this.modeActuel == "HIVER") {
						//Action des sirènes
						for(Siren siren: sirens) {
							siren.turnOn();
						}
						//Ouverture des fenêtres
						if(CODehors < CO) {
							fenetre = fenetre || true;
						}else if(CODehors >= CO) {
							System.out.println("Le Taux de CO est pire dehors (" + CODehors +") les fenêtres restent fermées");
						}
					}else if(this.modeActuel == "SILENCIEUX") {
						//Ouverture des fenêtres
						if(CODehors < CO) {
							fenetre = fenetre || true;
						}else if(CODehors >= CO) {
							System.out.println("Le Taux de CO est pire dehors (" + CODehors +") les fenêtres restent fermées");
						}
					}
				}
				
				//Traitement du CO2
				if(CO2 == -1.0) {
					//Appel des réparateurs
					communication.appelReparateur(salle);
				}else if(CO2 < 2500.0) {
					cmptAlarmeCO2++;
					//Fermeture des fenêtres de la pièce
					fenetre = fenetre || false;
				}else if(CO2 >= 2500.0 && CO2 < 5000) {
					cmptAlarmeCO2++;
					if(this.modeActuel == "PRESENT") {
						//Ouverture des fenêtres
						if(CO2Dehors < CO2) {
							fenetre = fenetre || true;
						}else if(CO2Dehors >= CO2) {
							System.out.println("Le Taux de CO2 est pire dehors (" + CO2Dehors +") les fenêtres restent fermées");
						}
					}else if(this.modeActuel == "SILENCIEUX") {
						//Ouverture des fenêtres
						if(CO2Dehors < CO2) {
							fenetre = fenetre || true;
						}else if(CO2Dehors >= CO2) {
							System.out.println("Le Taux de CO2 est pire dehors (" + CO2Dehors +") les fenêtres restent fermées");
						}
					}
				}else if(CO2 >= 5000.0) {
					//Appel Pompier, Police et Samu
					urgence = urgence || true;
					if(this.modeActuel == "PRESENT" || this.modeActuel == "NUIT" || this.modeActuel == "HIVER") {
						//Action des sirènes
						for(Siren siren: sirens) {
							siren.turnOn();
						}
						//Ouverture des fenêtres
						if(CO2Dehors < CO2) {
							fenetre = fenetre || true;
						}else if(CO2Dehors >= CO2) {
							System.out.println("Le Taux de CO2 est pire dehors (" + CO2Dehors +") les fenêtres restent fermées");
						}
					}else if(this.modeActuel == "SILENCIEUX") {
						//Ouverture des fenêtres
						if(CO2Dehors < CO2) {
							fenetre = fenetre || true;
						}else if(CO2Dehors >= CO2) {
							System.out.println("Le Taux de CO2 est pire dehors (" + CO2Dehors +") les fenêtres restent fermées");
						}
					}
				}
				
				if(fenetre) {
					for(Window window: windows) {
						if(window.getPropertyValue(LOCATION_PROPERTY_NAME).equals(salle)) {
							window.openWindow();
						}
					}
				}else {
					for(Window window: windows) {
						if(window.getPropertyValue(LOCATION_PROPERTY_NAME).equals(salle)) {
							window.closeWindow();
						}
					}
				}
			}
			
			if(cmptAlarmeCO == salles.length && cmptAlarmeCO2 == salles.length) {
				for(Siren siren: sirens) {
					siren.turnOff();
				}
			}
			
			if(urgence) {
				communication.appelPolice();
				communication.appelPompier();
				communication.appelSamu();
			}
		}
		
		
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
		return maximumEnergyConsumptionAllowedInARoom;
	}

	@Override
	public void setMaximumAllowedEnergyInRoom(double maximumEnergy) {
		maximumEnergyConsumptionAllowedInARoom = maximumEnergy;
		maxDetectorByEnergy = ((int) maximumEnergyConsumptionAllowedInARoom) / 100;
	}

	@Override
	public String getMode() {
		// TODO Auto-generated method stub
		return this.modeActuel;
	}

	@Override
	public void setMode(String mode) {
		this.modeActuel = mode;
	}
}
