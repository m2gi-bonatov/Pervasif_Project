package communication;

public interface AirQualityCommunication {
	/**
	 * Permet � l'application d'appeler la police si l'alarme sonne
	 */
	public void appelPolice();
	
	/**
	 * Permet � l'application d'appeler les pompiers si l'alarme sonne
	 */
	public void appelPompier();
	
	/**
	 * Permet � l'application d'appeler le Samu si l'alarme sonne
	 */
	public void appelSamu();
	
	/**
	 * Permet � l'application d'appeler le r�parateur si aucun capteur de carbone ne fonctionne.
	 */
	public void appelReparateur(String salle);
}
