package manager;

public interface AirQualityAdministration {

	public void setAirQualityMode(Mode mode);
	public Mode getAirQualityMode();
	public void setAirQualityEnergy(EnergyGoal energyGoal);
	public EnergyGoal getAirQualityEnergy();
}
