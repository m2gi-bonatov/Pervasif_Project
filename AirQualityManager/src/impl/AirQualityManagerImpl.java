package impl;

import manager.AirQualityAdministration;
import manager.EnergyGoal;
import manager.Mode;
import configuration.AirQualityConfiguration;

public class AirQualityManagerImpl implements AirQualityAdministration {

	/** Field for airQualityConfiguration dependency */
	private AirQualityConfiguration airQualityConfiguration;

	@Override
	public void setAirQualityMode(Mode mode) {
		airQualityConfiguration.setMode(mode.toString());
	}

	@Override
	public Mode getAirQualityMode() {
		// TODO Auto-generated method stub
		String mode = airQualityConfiguration.getMode();

		Mode airMode;
		if (mode == "PRESENT") {
			airMode = Mode.PRESENT;
		} else if (mode == "ABSENT") {
			airMode = Mode.ABSENT;
		} else if (mode == "NUIT") {
			airMode = Mode.NUIT;
		} else if (mode == "SILENCIEUX") {
			airMode = Mode.SILENCIEUX;
		} else if (mode == "HIVER") {
			airMode = Mode.HIVER;
		} else if (mode == "ETEINT") {
			airMode = Mode.ETEINT;
		} else {
			throw new IllegalArgumentException("Mauvais Mode");
		}

		return airMode;
	}

	@Override
	public void setAirQualityEnergy(EnergyGoal energyGoal) {
		double enGoal = energyGoal.getMaximumEnergyInRoom();
		airQualityConfiguration.setMaximumAllowedEnergyInRoom(enGoal);
	}

	@Override
	public EnergyGoal getAirQualityEnergy() {
		double enGoal = airQualityConfiguration.getMaximumAllowedEnergyInRoom();

		EnergyGoal energyGoal;

		if (enGoal == 100.0d) {
			energyGoal = EnergyGoal.LOW;
		} else if (enGoal == 200.0d) {
			energyGoal = EnergyGoal.MEDIUM;
		} else if (enGoal == 300.0d) {
			energyGoal = EnergyGoal.HIGH;
		} else {
			throw new IllegalArgumentException("Mauvais Energy Goal");
		}

		return energyGoal;
	}

	/** Component Lifecycle Method */
	public void stop() {
		System.out.println("Arrêt du AirQualityManager ...");
	}

	/** Component Lifecycle Method */
	public void start() {
		System.out.println("Démarrage du AirQualityManager ...");
	}

}
