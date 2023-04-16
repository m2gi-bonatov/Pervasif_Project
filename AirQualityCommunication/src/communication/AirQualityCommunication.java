package communication;

public interface AirQualityCommunication {
	/**
	 * Permet à l'application d'appeler la police si l'alarme sonne
	 */
	public void appelPolice();
	
	/**
	 * Permet à l'application d'appeler les pompiers si l'alarme sonne
	 */
	public void appelPompier();
	
	/**
	 * Permet à l'application d'appeler le Samu si l'alarme sonne
	 */
	public void appelSamu();
	
	/**
	 * Permet à l'application d'appeler le réparateur si aucun capteur de carbone ne fonctionne.
	 */
	public void appelReparateur(String salle);
}
