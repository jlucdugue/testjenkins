package fr.imie;

public class BoiteDeNuitProxy implements BoiteDeNuitInterface {

	public BoiteDeNuitInterface real;

	/**
	 * @param real
	 */
	public BoiteDeNuitProxy(BoiteDeNuitInterface real) {
		super();
		this.real = real;
	}

	@Override
	public void entrer(Client client) {
		if (client.getBienHabille()) {
			real.entrer(client);
		} else {
			
			System.out.println("vous auriez pu faire un effort");
			client.setBienHabille(true);
			real.entrer(client);
		}

	}

}
