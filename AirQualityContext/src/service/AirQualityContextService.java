package service;

public interface AirQualityContextService {
	public double getCO2Concentration(String location, int maxDetectorByEnergy);
	public double getCOConcentration(String location, int maxDetectorByEnergy);
	public double getCOConcentrationDehors(int maxDetectorByEnergy);
	public double getCO2ConcentrationDehors(int maxDetectorByEnergy);
}
