package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.common.dao.VispnrDao;
import no.systema.jservices.common.dao.services.VispnrDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.services.BridfDaoServices;

/**
 * 
 * Hosting valid postnr/poststed. Data from provided by Visma.net
 * 
 * @author fredrikmoller
 * *date 2019-03-11
 */
@Controller
public class BcoreMaintResponseOutputterController_VISPNR {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_VISPNR.class.getName());

	
	/**
	 * 
	 * Get Vispnr for postnr and landkode
	 * 
	 * @Example SELECT http://localhost:8080/syjservicesbcore/syjsVISPNR.do?user=OSCAR&postnr=6001&landkode=NO
	 * 
	 */
	@RequestMapping(value="syjsVISPNR.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getVispnr(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<VispnrDao> jsonWriter = new JsonResponseWriter2<VispnrDao>();
		StringBuffer sb = new StringBuffer();
		VispnrDao returnDao;
		
		String user = request.getParameter("user");
		String postnr = request.getParameter("postnr");
		String landkode = request.getParameter("landkode");
		String userName = null;
		String errMsg = "";
		String status = "ok";
		StringBuffer dbErrorStackTrace = new StringBuffer();
		
		try {
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user); 
			if (StringUtils.hasValue(userName) && StringUtils.hasValue(postnr) && StringUtils.hasValue(landkode)) {
				VispnrDao qDao = new VispnrDao();
				qDao.setViponr(postnr);
				qDao.setVilk(landkode);
				returnDao = vispnrDaoService.find(qDao);
				if (returnDao != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetComposite(userName, returnDao));
				} else {
					errMsg = "Can not find Vispnr on postnr "+postnr+ " and landkode "+landkode;
					status = "error";
					logger.error("vispnrDaoService.find(qDao) :" + " " + status + errMsg);
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}

			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>, <postnr>, <landkode>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			logger.info(sb);
			logger.error(":::ERROR:::",e);
			errMsg = "ERROR SELECT:  error="+e.getMessage();
			status = "error";
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
		}

		session.invalidate();
		return sb.toString();

	}

	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("vadrDaoService")
	private VispnrDaoService vispnrDaoService;
	@Autowired
	@Required
	public void setVispnrDaoService(VispnrDaoService value){ this.vispnrDaoService = value; }
	public VispnrDaoService getVispnrDaoService(){ return this.vispnrDaoService; }		
	
	
}
