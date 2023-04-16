package command;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Requires;

import fr.liglab.adele.icasa.command.handler.Command;
import fr.liglab.adele.icasa.command.handler.CommandProvider;
import manager.AirQualityAdministration;
import manager.EnergyGoal;
import manager.Mode;

@Component
@Instantiate(name = "command")
@CommandProvider(namespace = "airqualitycommand")
public class AirQualityCommandImpl {
	
	@Requires
	private AirQualityAdministration m_administrationService;

	@Command
	public void setAirQualityMode(String mode) {
		Mode airQualityMode;
		
		switch(mode) {
		case "PRESENT":
			airQualityMode = Mode.PRESENT;
			break;
		case "ABSENT":
			airQualityMode = Mode.ABSENT;
			break;
		case "HIVER":
			airQualityMode = Mode.HIVER;
			break;
		case "SILENCIEUX":
			airQualityMode = Mode.SILENCIEUX;
			break;
		case "ETEINT":
			airQualityMode = Mode.ETEINT;
			break;
		case "NUIT":
			airQualityMode = Mode.NUIT;
		default: 
			throw new IllegalArgumentException("Invalid mode ! " + mode);
		}
		
		m_administrationService.setAirQualityMode(airQualityMode);
	}
	
	@Command
	public void getAirQualityMode() {
		System.out.println("The mode is " + m_administrationService.getAirQualityMode());
	}
	
	@Command
	public void setAirQualityEnergy(String goal) {
		EnergyGoal energyGoal;
        
        switch (goal) {
		case "LOW":
			energyGoal = EnergyGoal.LOW;
			break;
		case "MEDIUM":
			energyGoal = EnergyGoal.MEDIUM;
			break;
		case "HIGH":
			energyGoal = EnergyGoal.HIGH;
			break;
		default:
			throw new IllegalArgumentException("Invalid energy goal !" + goal);

		}
 
        //call the administration service to configure it :
        m_administrationService.setAirQualityEnergy(energyGoal);
	}
	
	@Command
	public void getAirQualityEnergy() {
		System.out.println("The EnergyMode is " + m_administrationService.getAirQualityEnergy());
	}
}
