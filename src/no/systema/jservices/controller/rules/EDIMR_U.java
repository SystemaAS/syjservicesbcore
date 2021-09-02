package no.systema.jservices.controller.rules;


import org.apache.commons.lang3.StringUtils;
import no.systema.jservices.model.dao.entities.EdimDao;

/**
 * 
 * @author oscardelatorre
 * @date Nov 2020
 */
public class EDIMR_U {

	/**
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(EdimDao dao, String user, String mode){
		boolean retval = true;
		if( (StringUtils.isNotEmpty(user)) && StringUtils.isNotEmpty(mode) ){
			//check dao
			/*TODO
			if( (dao.getKoaavd()!=null && !"".equals(dao.getKoaavd())) &&
				(dao.getKoanvn()!=null && !"".equals(dao.getKoanvn())) && 
				(dao.getKoaknr()!=null && !"".equals(dao.getKoaknr())) &&
				(dao.getKoafir()!=null && !"".equals(dao.getKoafir())) ){
					
				
			}else{
				retval = false;
			}
			*/
		}else{
			retval = false;
		}
		return retval;
	}
	
}
