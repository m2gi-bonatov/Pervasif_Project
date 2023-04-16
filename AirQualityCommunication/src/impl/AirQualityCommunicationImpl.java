package impl;

import communication.AirQualityCommunication;

public class AirQualityCommunicationImpl implements AirQualityCommunication {

	@Override
	public void appelReparateur(String salle) {
		System.out.println("Envoie d'un message aux réparateur pour la salle " + salle +" ...");

	}

	@Override
	public void appelPolice() {
		System.out.println("Appel de la Police en cours ...");
	}

	@Override
	public void appelPompier() {
		System.out.println("Appel des Pompiers en cours ...");
	}

	@Override
	public void appelSamu() {
		System.out.println("Appel du Samu en cours ...");
	}

	/** Component Lifecycle Method */
	public void stop() {
		System.out.println("Le service de communication est arrêté ...");
	}

	/** Component Lifecycle Method */
	public void start() {
		System.out.println("Le service de communication est démarré ...");
	}

}
