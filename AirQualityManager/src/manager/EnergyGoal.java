package manager;

public enum EnergyGoal {
	LOW(100d), MEDIUM(200d), HIGH(300d);
	
	private double maximumEnergyInRoom;
	 
    /**
     * get the maximum energy consumption in each room
     * 
     * @return the energy in watt
     */
    public double getMaximumEnergyInRoom() {
        return maximumEnergyInRoom;
    }
 
    private EnergyGoal(double powerInWatt) {
        maximumEnergyInRoom = powerInWatt;
    }
}
