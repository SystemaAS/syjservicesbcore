package no.systema.jservices.bcore.z.maintenance.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestJBcoreMaintResponseOutputterController_FORTOLLING_DATA {

	BcoreMaintResponseOutputterController_FORTOLLING_DATA controller = null;
	
	@Before
	public void setUp() throws Exception {
		controller = new BcoreMaintResponseOutputterController_FORTOLLING_DATA();
	}

	@Test
	public void testInitData() {
		String errMsg = null;
				
		errMsg =controller.initData("http://10.13.3.22", "CB", "20171000");
		assertNull(errMsg);	
		
		errMsg =controller.initData("http://10.13.3.22", "KALLE", "20171000");
		assertNotNull(errMsg);	

	}

}
