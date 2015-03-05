package fr.imie;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTennis {

	private Jeux jeux = null;

	@Before
	public void setUp() throws Exception {
		ISerialiser serialiser = new MockedSerialiser();
		jeux = new Jeux(serialiser);
	}

	@Test
	public void testAfficherScoreDebutPartie() {
		Assert.assertEquals("00-00", jeux.getScore());
	}

	@Test
	public void testAfficherScore_15_00() {
		jeux.marquerJoueur1();
		Assert.assertEquals("15-00", jeux.getScore());
	}

	@Test
	public void testAfficherScore_30_00() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		Assert.assertEquals("30-00", jeux.getScore());
	}

	@Test
	public void testAfficherScore_40_00() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		Assert.assertEquals("40-00", jeux.getScore());
	}

	@Test
	public void testAfficherScore_VictoireBlancheJ1() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		Assert.assertEquals("Victoire J1", jeux.getScore());
	}

	@Test
	public void testAfficherScore_15_15() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		Assert.assertEquals("15-15", jeux.getScore());
	}

	@Test
	public void testRechargerPartie() {

		//ARRANGE
		//mock de l'environnement
		ISerialiser serialiser = mock(ISerialiser.class);
		// construcition du retour de l'environnement
		Jeux retour = new Jeux(serialiser);
		retour.setJ1(1);
		retour.setJ2(1);
		//paramétrage du mock (contrat de collaboration)
		when(serialiser.read()).thenReturn(retour);
		jeux = new Jeux(serialiser);
		//ACT
		jeux.recharger();
		//ASSERT
		Assert.assertEquals("15-15", jeux.getScore());
	}

	@Test
	public void testRechargerPartie2() {
		//ARRANGE
		//mock de l'environnement
		ISerialiser serialiser = mock(ISerialiser.class);
		// construcition du retour de l'environnement
		Jeux retour = new Jeux(serialiser);
		retour.setJ1(2);
		retour.setJ2(2);
		//paramétrage du mock (contrat de collaboration)
		when(serialiser.read()).thenReturn(retour);
		jeux = new Jeux(serialiser);
		//ACT
		jeux.recharger();
		//ASSERT
		Assert.assertEquals("30-30", jeux.getScore());
	}

}
