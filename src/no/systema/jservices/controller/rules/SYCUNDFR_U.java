package no.systema.jservices.controller.rules;


import no.systema.jservices.model.dao.entities.CundfDao;
/**
 * 
 * @author oscardelatorre
 * @date Aug 4, 2016
 */
public class SYCUNDFR_U {

	/**
	 * Validate null values and exist controls i db.
	 * 
	 * @param dao
	 * @param user
	 * @param mode
	 * @return
	 */
	public boolean isValidInput(CundfDao dao, String user, String mode) {
		boolean retval = true;
		if ((user != null && !"".equals(user)) && (mode != null && !"".equals(mode))) {
			if ((dao.getFirma() != null && !"".equals(dao.getFirma())) && (dao.getKundnr() != null && !"".equals(dao.getKundnr()))
					&& (dao.getPostnr() != null && !"".equals(dao.getPostnr())) && (dao.getSonavn() != null && !"".equals(dao.getSonavn()))
					&& (dao.getBetbet() != null && !"".equals(dao.getBetbet())) && (dao.getAdr3() != null && !"".equals(dao.getAdr3()))) {
				// Check språk
/*				if (existInTrkodf(user, dao.getSpraak())) {
					return false;
				}
*/			
			} else{ 
				  retval = false; 
			 }
			 
		} else {
			retval = false;
		}
		return retval;
	}

	public boolean isValidInputForDelete(CundfDao dao, String user, String mode){
		boolean retval = true;
		if( (user!=null && !"".equals(user)) && (mode!=null && !"".equals(mode)) ){
			//check dao
			/*
			if( dao.getKoaavd()!=null && !"".equals(dao.getKoaavd()) ){
				//OK
			}else{
				retval = false;
			}*/			  
		}else{
			retval = false;
		}
		return retval;
	}

	/**
	 * Conversion of data to fit in DB2-file
	 * 
	 * <li>Setting num to 0</li>
	 * <li>Setting dec separator from , to . </li>
	 * 
	 * 
	 * @param dao
	 */
	public void updateNumericFieldsIfNull(CundfDao dao){
		String ZERO = "0";
		if(dao.getFmot()==null || "".equals(dao.getFmot())){
			dao.setFmot(ZERO);
		}
		if(dao.getBetbet()==null || "".equals(dao.getBetbet())){
			dao.setBetbet(ZERO);
		}
		if(dao.getPostnr()==null || "".equals(dao.getPostnr())){
			dao.setPostnr(ZERO);
		}
		if(dao.getXxinm3()==null || "".equals(dao.getXxinm3())){
			dao.setXxinm3(ZERO);
		}
		if(dao.getXxinlm()==null || "".equals(dao.getXxinlm())){
			dao.setXxinlm(ZERO);
		}
		if(dao.getAknrku()==null || "".equals(dao.getAknrku())){
			dao.setAknrku(ZERO);
		}
		if(dao.getSyregn()==null || "".equals(dao.getSyregn())){
			dao.setSyregn(ZERO);
		}
		if(dao.getSyiat1()==null || "".equals(dao.getSyiat1())){
			dao.setSyiat1(ZERO);
		}
		if(dao.getSyiat2()==null || "".equals(dao.getSyiat2())){
			dao.setSyiat2(ZERO);
		}
		if(dao.getKgrens()==null || "".equals(dao.getKgrens())){ //Pakket
			dao.setKgrens(ZERO);
		}
		if(dao.getSykont()==null || "".equals(dao.getSykont())){ //Pakket
			dao.setSykont(ZERO);
		}
		if(dao.getSysalu()==null || "".equals(dao.getSysalu())){ //Pakket
			dao.setSysalu(ZERO);
		}
		if(dao.getSyminu()!=null && !"".equals(dao.getSyminu())){ //Tegn, decimal
			String tmp = dao.getSyminu().replace(",", ".");
			dao.setSyminu(tmp);
		}else{
			dao.setSyminu(ZERO);
		}		
		if(dao.getSyutlp()!=null && !"".equals(dao.getSyutlp())){ //Tegn, decimal
			String tmp = dao.getSyutlp().replace(",", ".");
			dao.setSyutlp(tmp);
		}else{
			dao.setSyutlp(ZERO);
		}		
		if(dao.getXxbre()!=null && !"".equals(dao.getXxbre())){ //Tegn, decimal
			String tmp = dao.getXxbre().replace(",", ".");
			dao.setXxbre(tmp);
		}else{
			dao.setXxbre(ZERO);
		}		
		if(dao.getXxlen()!=null && !"".equals(dao.getXxlen())){ //Tegn, decimal
			String tmp = dao.getXxlen().replace(",", ".");
			dao.setXxlen(tmp);
		}else{
			dao.setXxlen(ZERO);
		}	
	}

	
//	Oklart vad språk hanteras, mail ligger hos Christer
/*	private boolean existInTrkodf(String userName,  String tkKode) {
		boolean exists = this.trkodfDaoServices.exists(TransitKoder..., tkKode);
		if (!exists) {
			return false;
		} else {
			errors.append(jsonWriter.setJsonSimpleErrorResult(userName,
					messageSourceHelper.getMessage("systema.tvinn.sad.ncts.export.error.tkkode", new Object[] { tkKode, tkUnik.getTransitKode() }), "error", dbErrors));
			return true;
			
		}
	}	
	*/
	
	
	
	
}
