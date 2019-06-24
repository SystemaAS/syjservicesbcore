package no.systema.jservices.bcore.z.maintenance.controller.rules;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestJSVLTH_U {

	
	@Test
	final public void testValidGodsnummer() {
		String svlth_igl = "BJO";
		String svlth_ign = "BJO19-0511";
		
		assertTrue("Should be true",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));

		svlth_igl = "AAA";
		assertFalse("Should be false",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));
		
		svlth_igl = "BJO";
		svlth_ign = "BJO19-511";
		assertFalse("Should be false",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));

		svlth_ign = "BJO99-0511";
		assertFalse("Should be false",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));

		svlth_ign = "BJO17-0511";
		assertFalse("Should be false",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));		

		svlth_ign = "BJO18-0511";
		assertTrue("Should be true",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));		

		svlth_ign = "BJO19-0511";
		assertTrue("Should be true",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));		
		
		svlth_ign = "BJO20-0511";
		assertTrue("Should be true",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));		

		svlth_ign = "BJO21-0511";
		assertFalse("Should be false",SVLTH_U.validateGodsnummer(svlth_igl, svlth_ign));		
		
		
	}
	
}
