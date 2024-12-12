package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;

	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
	}


	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}

	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);

		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

	}
    @Test
    public void testVolumeNeg() {
        try {
            untel.ajouteEnseignement(uml, -1, 1, 1);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void testEnSousService() {
        assertTrue(untel.enSousService());
        untel.ajouteEnseignement(uml, 100, 92, 0);
        assertFalse(untel.enSousService());
    }

    @Test
    void testServiceFromUe() {
        assertNull(untel.getServicePrevuFromUE(uml));
        untel.ajouteEnseignement(uml, 10, 10, 10);
        assertEquals(untel.getServicePrevus().iterator().next(), untel.getServicePrevuFromUE(uml));
        assertNull(untel.getServicePrevuFromUE(java));
    }
    @Test
    void testAjoutInterNeedUEToExist() {
        try {
            untel.ajouteIntervention(new Intervention(new Date(), 1, null, TypeIntervention.CM, untel, uml));
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    void testAjoutInter(){
        untel.ajouteEnseignement(uml, 10, 10, 10);
        Intervention inter =  new Intervention(new Date(), 1, null, TypeIntervention.CM, untel, uml);
        untel.ajouteIntervention(inter);
        assertEquals(inter, untel.getIntervensions().iterator().next(), "intervension not added proprely");
    }

    @Test
    void testAPlanifier(){
        untel.ajouteEnseignement(uml, 10, 10, 10);
        assertEquals(10, untel.resteAPlanifier(uml, TypeIntervention.CM));
        untel.ajouteIntervention(new Intervention(new Date(), 1, null, TypeIntervention.CM, untel, uml));
        assertEquals(9, untel.resteAPlanifier(uml, TypeIntervention.CM));
        untel.ajouteIntervention(new Intervention(new Date(), 5, null, TypeIntervention.TP, untel, uml));

        assertEquals(10, untel.resteAPlanifier(uml, TypeIntervention.TD));

    }
}
