package fr.imie;


public class Jeux {

	Integer J1 = 0;
	Integer J2 = 0;
	
	ISerialiser serialiser;
	
	

	/**
	 * @param serialiser
	 */
	public Jeux(ISerialiser serialiser) {
		super();
		this.serialiser = serialiser;
	}

	public String getScore() {
		// TODO Auto-generated method stub
		String retour = null;
		if (J1 < 4) {
			String scoreJ1 = stringifyScore(J1);
			String scoreJ2 = stringifyScore(J2);
			retour = scoreJ1.concat("-").concat(scoreJ2);
		}else{
			retour= "Victoire J1";
		}
		
		
		return retour;
	}
	
	public void recharger(){
		Jeux savedGame = serialiser.read();
		setJ1(savedGame.getJ1());
		setJ2(savedGame.getJ2());
	}

	/**
	 * @param scoreJ1
	 * @return
	 */
	private String stringifyScore(Integer score) {
		String scoreStingified=null;
		switch (score) {
		case 0:
			scoreStingified = "00";
			break;
		case 1:
			scoreStingified = "15";
			break;
		case 2:
			scoreStingified = "30";
			break;
		case 3:
			scoreStingified = "40";
			break;

		default:
			break;
		}
		return scoreStingified;
	}

	public void marquerJoueur1() {
		J1++;
	}

	public void marquerJoueur2() {
		J2++;
		
	}

	public Integer getJ1() {
		return J1;
	}

	public void setJ1(Integer j1) {
		J1 = j1;
	}

	public Integer getJ2() {
		return J2;
	}

	public void setJ2(Integer j2) {
		J2 = j2;
	}

}
