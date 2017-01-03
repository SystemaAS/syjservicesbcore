package no.systema.jservices.jsonwriter;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.systema.jservices.model.dao.entities.CundcDao;
import no.systema.jservices.model.dao.entities.IDao;

public class TestJJsonResponseWriter {

	JsonResponseWriter jsonResponseWriter = null;
	List<IDao> cundcList = null;
	
	@Before
	public void setUp() throws Exception {
		jsonResponseWriter = new JsonResponseWriter();
		cundcList = getCundcDaoList();
	}

	@Test
	public final void testCommon_GetList() {
		String resp = jsonResponseWriter.setJsonResult_Common_GetList("UserInControl", cundcList);
		Assert.assertNotNull(resp);
	}
	
    @Test
    public final void testCommon_GetCompositeList() {
    	String resp = jsonResponseWriter.setJsonResult_Common_GetCompositeList("UserInControl", cundcList);
		Assert.assertNotNull(resp);   	
    }

    
    private List<IDao> getCundcDaoList() {
    	List<IDao> cundcList = new ArrayList<>();
    	CundcDao dao1 = new CundcDao();
    	dao1.setCcompn("1");
    	dao1.setCconta("Username");
    	dao1.setCfirma("SY");
    	dao1.setSonavn("Soknamn");
    	
    	cundcList.add(dao1);
    	
    	return cundcList;
    	
    }
}
