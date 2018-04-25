package no.systema.jservices.model.dao.services;

import java.util.List;

/**
 * Grandfather for all normal read only Dao services
 * @author oscardelatorre
 * @date Dec 21, 2017
 */
public interface IDaoMemberAliasServices {
	//Mandatory for Alias-mechanism
	public void setMember(String value);
	public void setAlias(String value);
	public void setMemberTable(String value);
	
}
