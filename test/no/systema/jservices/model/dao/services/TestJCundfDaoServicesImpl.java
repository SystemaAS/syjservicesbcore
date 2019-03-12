package no.systema.jservices.model.dao.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import no.systema.jservices.model.dao.entities.CundcDto;
import no.systema.jservices.model.dao.entities.CundfDao;

public class TestJCundfDaoServicesImpl {

	CundfDaoServices cundfDaoServices = null;
	CundcDaoServices cundcDaoServices = null;

	StringBuffer errorStackTrace = new StringBuffer();
	ApplicationContext context = null;
	CundfDao cundfDao = null;
	CundcDto cundcDto = null;
	
	String kundnr = null;
	String firma = null;

	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:syjservicesbcore-data-service-mod.xml");
		cundfDaoServices = (CundfDaoServices) context.getBean("cundfDaoServices");
		cundcDaoServices = (CundcDaoServices) context.getBean("cundcDaoServices");
		kundnr = "222";
		firma = "SY";
	}
	
	@Test
	public final void testFindByName() {
		String qKnavn = "FISK";
		
		List list = cundfDaoServices.findByName(qKnavn,errorStackTrace);
		
	}
	
	@Test
	public final void testFetchFirstRowsOnly() {
		List list = cundfDaoServices.findFetchFirstRowsOnly(2, errorStackTrace);

		list.forEach(dao -> {
			System.out.println("dao="+ReflectionToStringBuilder.toString(dao));
		});
		
		
	}	
	
	
	
	//@Test
	public final void testCreate() {
		createCundfWithCundc();
		assertTrue(cundfDaoServices.exists(kundnr, errorStackTrace));
	}
	
	
	//@Test
	public final void testDeleteWithRollback() {
		createCundfWithCundc();
		
		//Handpalaggning neeeded. Fix cundfDaoServices.delete in code.
		//Rememeber to manually delete kundnr : 222
		
		assertTrue(cundfDaoServices.exists(cundfDao.getKundnr(), errorStackTrace));
		assertTrue(cundcDaoServices.exists(firma, kundnr, "kontakt", "funksjon", errorStackTrace));

		int retval = cundfDaoServices.cascadeDelete(cundfDao, errorStackTrace);
		assertTrue("retval should be -1", retval == -1);
		
		assertTrue(cundfDaoServices.exists(kundnr, errorStackTrace));
		assertTrue(cundcDaoServices.exists(firma, kundnr, "kontakt", "funksjon", errorStackTrace));
		
	}	
	
	
	@Test
	public final void testOrgnrExist() {
		String orgnr = "979642121";
		String kundnr = "12";
		assertTrue(cundfDaoServices.orgNrExist(orgnr, kundnr,errorStackTrace));
		
		kundnr = "";
		assertTrue(cundfDaoServices.orgNrExist(orgnr, kundnr,errorStackTrace));
	
		
		orgnr = "XYZ";
		assertFalse(cundfDaoServices.orgNrExist(orgnr, kundnr,errorStackTrace));
		
	}
	
	
	@Test
	public final void testOrgnrCount() {
		String orgnr = "936809219";  //systema

		System.out.println("cundfDaoServices.orgNrCount(orgnr, errorStackTrace)="+cundfDaoServices.orgNrCount(orgnr, errorStackTrace));
		
		assertTrue(cundfDaoServices.orgNrCount(orgnr, errorStackTrace) > 1);
		
		orgnr = "XYZ";
		assertTrue(cundfDaoServices.orgNrCount(orgnr, errorStackTrace) == 0);
		
	}	
	

	@Test
	public final void testFindAll() {
		CundfDao qDao = new CundfDao();
		qDao.setKundnr("2");
		qDao.setKnavn("cargo");
		qDao.setSyrg("93"); //936809219
		qDao.setSonavn("cargo");
		qDao.setSyland("NO");
		qDao.setPostnr("16");
		qDao.setFirma("SY");
		
		
		List<CundfDao>  resultList = cundfDaoServices.findAll(qDao, errorStackTrace);

		assertTrue(resultList.size() > 0);

		resultList.forEach(c -> {
			System.out.println("c="+ReflectionToStringBuilder.toString(c));
			
		});
		
		
	}	
	
	

	//@Test
	public final void testCreateAndDelete() {
		createCundfWithCundc();
		
		assertTrue(cundfDaoServices.exists(cundfDao.getKundnr(), errorStackTrace));
		assertTrue(cundcDaoServices.exists(firma, kundnr, "kontakt", "funksjon", errorStackTrace));

		cundfDaoServices.cascadeDelete(cundfDao, errorStackTrace);

		assertFalse(cundfDaoServices.exists(kundnr, errorStackTrace));
		assertFalse(cundcDaoServices.exists(firma, kundnr, "kontakt", "funksjon", errorStackTrace));
	}		
	
	private void createCundfWithCundc() {
		
		assertFalse("Kundnr "+kundnr+" should not exist.",cundfDaoServices.exists(kundnr, errorStackTrace));
		cundfDao = new CundfDao();
		cundcDto = new CundcDto();
		
		cundfDao.setKundnr(kundnr);
		cundfDao.setFirma(firma);
		cundfDao.setKnavn("firmanamn");
		cundfDao.setSonavn("firmanamn");
		cundfDao.setPostnr("40");
		cundfDao.setFmot("0");
		cundfDao.setKgrens("0");
		cundfDao.setSyregn("0");
		cundfDao.setSykont("0");
		cundfDao.setSyminu("0");
		cundfDao.setSyutlp("0");
		cundfDao.setSyutlp("0");
		cundfDao.setSysalu("0");
		cundfDao.setXxbre("0");
		cundfDao.setXxlen("0");
		cundfDao.setXxinm3("0");
		cundfDao.setXxinlm("0");
		cundfDao.setBetbet("0");
		cundfDao.setSyiat1("0");
		cundfDao.setSyiat2("0");
		cundfDao.setAknrku("0");
		
		cundfDaoServices.insert(cundfDao, errorStackTrace);
		assertTrue(cundfDaoServices.exists(kundnr, errorStackTrace));

		assertFalse(cundcDaoServices.exists(firma, kundnr, "kontakt", "funksjon",errorStackTrace));
		cundcDto.setCcompn(kundnr);
		cundcDto.setCfirma(firma);
		cundcDto.setCconta("kontakt");
		cundcDto.setCtype("funksjon");

		cundcDaoServices.insert(cundcDto, errorStackTrace);
		assertTrue(cundcDaoServices.exists(firma, kundnr, "kontakt", "funksjon",errorStackTrace));

	}

}
