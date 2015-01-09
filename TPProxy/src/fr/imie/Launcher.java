package fr.imie;

public class Launcher {
	public static void main(String[] args) {
		BoiteDeNuitInterface boiteDeNuit = new BoiteDeNuitProxy(new BoiteDeNuit());
		Client client = new Client();
		boiteDeNuit.entrer(client);
	}

}
