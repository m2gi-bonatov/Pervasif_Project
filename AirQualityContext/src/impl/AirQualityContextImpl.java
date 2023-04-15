package impl;

import service.AirQualityContextService;
import fr.liglab.adele.icasa.device.gasSensor.CarbonMonoxydeSensor;
import fr.liglab.adele.icasa.device.gasSensor.CarbonDioxydeSensor;
import java.util.Map;

public class AirQualityContextImpl implements AirQualityContextService {

	/** Field for coSensors dependency */
	private CarbonMonoxydeSensor[] coSensors;
	/** Field for co2Sensors dependency */
	private CarbonDioxydeSensor[] co2Sensors;
	
	/**
	 * The name of the LOCATION property
	 */
	public static final String LOCATION_PROPERTY_NAME = "Location";

	/**
	 * The name of the location for unknown value
	 */
	public static final String LOCATION_UNKNOWN = "unknown";
	
	/**
	 * Renvoie la moyenne de la concentration en CO2 de la pièce.
	 * Si la valeur renvoyer est -1 alors problème, plus aucun détecteur ne fonctionne
	 */
	@Override
	public int getCO2Concentration(String location) {
		// TODO Auto-generated method stub
		double cmpt = 0;
		double sommeCO2 = 0;
		double moyenneCO2 = -1;
		for(CarbonDioxydeSensor co2Sensor: co2Sensors) {
			if(co2Sensor.getPropertyValue(LOCATION_PROPERTY_NAME).equals(location)) {
				cmpt++;
				sommeCO2 = sommeCO2 + co2Sensor.getCO2Concentration();
			}
		}
		if(cmpt != 0) {
			moyenneCO2 = sommeCO2 / cmpt;
		}
		System.out.println("Salut");
		return 50;
		//return moyenneCO2;
	}

	/**
	 * renvoie la moyenne de la concentration en CO de la pièce.
	 * Si la valeur renvoyer est -1 alors problème, plus aucun détecteur ne fonctionne
	 */
	@Override
	public int getCOConcentration(String location) {
		int cmpt = 0;
		double sommeCO = 0;
		double moyenneCO = -1;
		for(CarbonMonoxydeSensor coSensor: coSensors) {
			if(coSensor.getPropertyValue(LOCATION_PROPERTY_NAME).equals(location)) {
				cmpt++;
				sommeCO = sommeCO + coSensor.getCOConcentration();
			}
		}
		if(cmpt != 0) {
			moyenneCO = sommeCO / cmpt;
		}
		System.out.println("Salut");
		return 50;
		//return moyenneCO;
	}

	/** Bind Method for co2Sensors dependency */
	public void bindCO2Sensor(CarbonDioxydeSensor carbonDioxydeSensor, Map properties) {
		System.out.println("bind CO2Sensor " + carbonDioxydeSensor.getSerialNumber());
	}

	/** Unbind Method for co2Sensors dependency */
	public void unbindCO2Sensor(CarbonDioxydeSensor carbonDioxydeSensor, Map properties) {
		System.out.println("unbind CO2Sensor " + carbonDioxydeSensor.getSerialNumber());
	}

	/** Bind Method for coSensors dependency */
	public void bindCOSensor(CarbonMonoxydeSensor carbonMonoxydeSensor, Map properties) {
		System.out.println("bind COSensor " + carbonMonoxydeSensor.getSerialNumber());
	}

	/** Unbind Method for coSensors dependency */
	public void unbindCOSensor(CarbonMonoxydeSensor carbonMonoxydeSensor, Map properties) {
		System.out.println("unbind COSensor " + carbonMonoxydeSensor.getSerialNumber());
	}

	/** Component Lifecycle Method */
	public void stop() {
		System.out.println("Stop AirQualityContext ...");
	}

	/** Component Lifecycle Method */
	public void start() {
		System.out.println("Start AirQualityContext ...");
	}

}
