package fr.imie;

import static org.mockito.Mockito.mock;

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
//		ISerialiser serialiser = new ISerialiser() {
//
//			@Override
//			public Jeux read() {
//				Jeux retour = new Jeux(this);
//				retour.setJ1(1);
//				retour.setJ2(1);
//				return retour;
//			}
//
//			@Override
//			public void persist(Jeux jeux) {
//				// TODO Auto-generated method stub
//
//			}
//		};
		ISerialiser serialiser = mock(ISerialiser.class);
		jeux = new Jeux(serialiser);
		jeux.recharger();
		Assert.assertEquals("30-30", jeux.getScore());
	}

	@Test
	public void testRechargerPartie2() {
		ISerialiser serialiser = new ISerialiser() {

			@Override
			public Jeux read() {
				Jeux retour = new Jeux(this);
				retour.setJ1(2);
				retour.setJ2(2);
				return retour;
			}

			@Override
			public void persist(Jeux jeux) {
				// TODO Auto-generated method stub

			}
		};
		jeux = new Jeux(serialiser);
		jeux.recharger();
		Assert.assertEquals("30-30", jeux.getScore());
	}

}
