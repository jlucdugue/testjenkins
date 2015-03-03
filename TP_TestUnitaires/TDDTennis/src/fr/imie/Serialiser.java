package fr.imie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialiser implements ISerialiser {
	/* (non-Javadoc)
	 * @see org.imie.testTDDTennis.ISerialiser#persist(org.imie.testTDDTennis.Jeux)
	 */
	@Override
	public void persist(Jeux jeux)  {
		ObjectOutputStream oos = null;
		FileOutputStream fichier;
		try {
			fichier = new FileOutputStream("jeux.ser");
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(jeux);
			oos.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/* (non-Javadoc)
	 * @see org.imie.testTDDTennis.ISerialiser#read()
	 */
	@Override
	public Jeux read()  {
		ObjectInputStream ois = null;
		FileInputStream fichier;
		Jeux retour;
		try {
			fichier = new FileInputStream("jeux.ser");
			ois = new ObjectInputStream(fichier);
			retour = (Jeux) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return retour;
	}
}
