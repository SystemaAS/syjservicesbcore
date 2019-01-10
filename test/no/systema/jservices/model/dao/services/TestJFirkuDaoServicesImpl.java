package no.systema.jservices.model.dao.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import no.systema.jservices.bcore.z.maintenance.model.dao.services.FirkuDaoServices;

public class TestJFirkuDaoServicesImpl {

	FirkuDaoServices firkuDaoServices = null;
	StringBuffer errorStackTrace = new StringBuffer();
	ApplicationContext context = null;
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:syjservicesbcore-data-service-mod.xml");
		firkuDaoServices = (FirkuDaoServices) context.getBean("firkuDaoServices");
	}
	
	@Test
	public final void testGetFikune() {
		String fikune = firkuDaoServices.getFikune(errorStackTrace);
		
		System.out.println("fikune="+fikune);
		
	}

	@Test
	public final void testGetFikufn() {
		String fikufn = firkuDaoServices.getFikufn(errorStackTrace);
		
		System.out.println("fikufn="+fikufn);
		
	}	
	
	
	@Test
	public final void testInvoiceCustomerEnabled() {
		boolean enabled = firkuDaoServices.invoiceCustomerEnabled(errorStackTrace);

		Assert.assertTrue(enabled);
		
	}

}
