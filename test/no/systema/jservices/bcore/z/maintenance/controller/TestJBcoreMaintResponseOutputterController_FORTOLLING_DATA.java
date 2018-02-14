package no.systema.jservices.bcore.z.maintenance.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import no.systema.jservices.common.dto.FortollingDto;

public class TestJBcoreMaintResponseOutputterController_FORTOLLING_DATA {

	BcoreMaintResponseOutputterController_FORTOLLING_DATA controller = null;
	FortollingDto qDto = null;
	
	@Before
	public void setUp() throws Exception {
		controller = new BcoreMaintResponseOutputterController_FORTOLLING_DATA();
		qDto = new FortollingDto();
	}

	@Test
	public void testInitData() {
		String errMsg = null;
		qDto.setRegistreringsdato("20171000");	
		qDto.setMottaker(1);
		qDto.setAvsender(1);
		errMsg =controller.initData("http://10.13.3.22", "CB", qDto);
		assertNull(errMsg);	
		
		errMsg =controller.initData("http://10.13.3.22", "KALLE", qDto);
		assertNotNull(errMsg);	

	}

}
