package configuration;

public interface AirQualityConfiguration {
	public double getMaximumAllowedEnergyInRoom();
	public void setMaximumAllowedEnergyInRoom(double maximumEnergy);
	public String getMode();
	public void setMode(String mode);
}