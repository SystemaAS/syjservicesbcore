package no.systema.jservices.model.dao.services;
import java.util.*;

import no.systema.jservices.model.dao.entities.EdiiDao;

/**
 * HEDUMMY with members
 * 
 * To be used for Faste data (dafualt values) in Oppdr.reg.- Create new
 * Must used alias of type
 * 
 * Create alias syspedf.ura_alias for syspedf.hedummy(ura)
 * 
 * ura = member for land import
 * urb = member for land export 
 * ...etc
 * 
 * @author oscardelatorre
 *
 */
public interface HedummyUraDaoServices extends IDaoServicesReadOnly, IDaoMemberAliasServices { 
	public List findById(Integer id, StringBuffer errorStackTrace);
	
}
