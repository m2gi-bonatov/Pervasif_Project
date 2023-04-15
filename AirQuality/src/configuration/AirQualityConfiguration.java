package configuration;

public interface AirQualityConfiguration {
	public double getMaximumAllowedEnergyInRoom();
	public void setMaximumAllowedEnergyInRoom(double maximumEnergy);
	public int getMode();
	public void setMode(int mode);
}