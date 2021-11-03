package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.CundfDao;
import no.systema.jservices.model.dao.entities.SvivRflnDao;
import no.systema.jservices.model.dao.entities.Sviv_aggDao;
import no.systema.jservices.model.dao.entities.Sviva_aggDao;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2021
 * 
 */
public interface Sviv_aggDaoServices extends IDaoServices { 
	
	public int insertSviv_agg(List<Sviv_aggDao> items, List<Sviva_aggDao> avgifter, StringBuffer errorStackTrace);
	public int updateVanoSviv(List<SvivRflnDao> itemListSviv, StringBuffer errorStackTrace);
	public int updateRflnSviv(List<SvivRflnDao> itemListRfln, StringBuffer errorStackTrace);
	public int blankRfln(String avd, String opd, StringBuffer errorStackTrace);
}
