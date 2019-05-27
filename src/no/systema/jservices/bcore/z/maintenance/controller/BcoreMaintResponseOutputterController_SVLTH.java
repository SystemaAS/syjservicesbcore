package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.jservices.bcore.z.maintenance.controller.rules.SVLTH_U;
import no.systema.jservices.common.dao.SvlthDao;
import no.systema.jservices.common.dao.SvltuDao;
import no.systema.jservices.common.dao.services.SvltfDaoService;
import no.systema.jservices.common.dao.services.SvlthDaoService;
import no.systema.jservices.common.dao.services.SvltuDaoService;
import no.systema.jservices.common.dao.services.Svtx03fDaoService;
import no.systema.jservices.common.dto.SvlthDto;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.common.values.EventTypeEnum;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SVLTH {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SVLTH.class.getName());

	
	/**
	 * Search in SVLTH
	 * 
	 * Note: Returning dto
	 * 
	 * Example :
	 * http://localhost:8080/syjservicesbcore/syjsSVLTH?user=SYSTEMA&svlth_igl=BJO&svlth_ign=BJO19-004
	 */
	@RequestMapping(path = "/syjsSVLTH", method = RequestMethod.GET)
	@ResponseBody
	public String syjsSVLTH(HttpSession session, 
			@RequestParam(value = "user", required = true) String user,
			@RequestParam(value = "svlth_h", required = false) String svlth_h,
			@RequestParam(value = "svlth_igl", required = false) String svlth_igl,
			@RequestParam(value = "svlth_ign", required = false) String svlth_ign,
			@RequestParam(value = "svlth_pos", required = false) String svlth_pos,
			@RequestParam(value = "svlth_irn", required = false) String svlth_irn,
			@RequestParam(value = "svlth_id2F", required = false) Integer svlth_id2F,
			@RequestParam(value = "svlth_id2T", required = false) Integer svlth_id2T,
			@RequestParam(value = "svlth_id1", required = false) Integer svlth_id1,
			@RequestParam(value = "svlth_im1", required = false) Integer svlth_im1,
			@RequestParam(value = "svlth_rty", required = false) String svlth_rty,
			@RequestParam(value = "DO_NOT_LOAD", required = false) String DO_NOT_LOAD) {

		logger.info("INSIDE /syjsSVLTH...");
		
		logger.info("svlth_h="+svlth_h);
		logger.info("svlth_igl="+svlth_igl);
		logger.info("svlth_ign="+svlth_ign);
		logger.info("svlth_pos="+svlth_pos);
		logger.info("svlth_irn="+svlth_irn);
		logger.info("svlth_id2F="+svlth_id2F);
		logger.info("svlth_id2T="+svlth_id2T);
		logger.info("svlth_id1="+svlth_id1);
		logger.info("svlth_im1="+svlth_im1);
		logger.info("svlth_rty="+svlth_rty);
		
		
		JsonResponseWriter2<SvlthDto> jsonWriter = new JsonResponseWriter2<SvlthDto>();		
		String errMsg = "";
		String status = "ok";
		StringBuffer dbErrorStackTrace = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		List<SvlthDto> svlthDtoList = new ArrayList<SvlthDto>();	
		try {
			String userName = bridfDaoServices.findNameById(user);
			if (StringUtils.hasValue(userName)) {

				if (DO_NOT_LOAD != null) {  //datatables trick, due to autoload
					//do nothing
				} else {
					svlthDtoList = svlthDaoService.getAll(svlth_h, svlth_igl, svlth_ign,svlth_pos, svlth_irn, svlth_id2F,svlth_id2T, svlth_id1, svlth_im1, svlth_rty );
					if (StringUtils.hasValue(svlth_h) && svlth_h.equals(EventTypeEnum.UTTAG.getValue())) {
						addUtgHandlingar(svlthDtoList);
					}
				}

				sb.append(jsonWriter.setJsonResult_Common_GetList(userName, svlthDtoList));
				
			} else {
				errMsg = "ERROR on SELECT ";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user> ");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			
			}
		} catch (Throwable e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
		}

		session.invalidate();
		return sb.toString();

	}
	

	@RequestMapping(value = "syjsSVLTH_U.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String syjsSVLTH_U(HttpSession session, HttpServletRequest request) {	
		logger.info("INSIDE syjsSVLTH_U.do");
		JsonResponseWriter2<SvlthDao> jsonWriter = new JsonResponseWriter2<SvlthDao>();		
		StringBuffer sb = new StringBuffer();
		String userName = null;
		String errMsg;
		String status;
		StringBuffer dbErrorStackTrace  = new StringBuffer();
		String user = request.getParameter("user");
		String mode = request.getParameter("mode");
	
		logger.info("user="+user);
		logger.info("mode="+mode);
		
		
		try {
			// Check ALWAYS user in BRIDF
			userName = this.bridfDaoServices.findNameById(user);
			errMsg = "";
			status = "ok";
			SvlthDao dao = new SvlthDao();
			SvlthDao resultDao = new SvlthDao();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(dao);
			binder.bind(request);

	        SVLTH_U rulerLord = new SVLTH_U(request, svlthDaoService, svtx03fDaoService ,sb, dbErrorStackTrace); 
	        
			if (rulerLord.isValidInput(dao, userName)) {
				if (StringUtils.hasValue(userName)) {
						resultDao = svlthDaoService.create(dao);
						logger.info("SVLTH created, dao="+ReflectionToStringBuilder.toString(resultDao));
					if (resultDao == null) {
						errMsg = "Could not add dao=" + ReflectionToStringBuilder.toString(dao);
						status = "error ";
						dbErrorStackTrace.append("Could not add dao=" + ReflectionToStringBuilder.toString(dao));
						sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
					} else {
						// OK UPDATE
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, Arrays.asList(resultDao)));
					}
				} else {
					logger.error("user: "+user+ " not found in bridf.");
					// write JSON error output
					errMsg = "ERROR on DML for SVLTH";
					status = "error";
					dbErrorStackTrace.append("request input parameters are invalid: <user>");
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}
			} else {
				// write JSON error output
				errMsg = "ERROR on ADD for SVLTH: invalid rulerLord, error="+sb.toString();
				status = "error";
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				logger.error(sb);
			}
			

		} catch (Throwable e) {
			logger.error("::ERROR::", e);
			errMsg = "ERROR on add/update SVLTH: "+e.getMessage();
			status = "error ";
			dbErrorStackTrace.append(e.getMessage());
			sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status,dbErrorStackTrace));
		}
		session.invalidate();
		return sb.toString();
		
	}

	
	private void addUtgHandlingar(List<SvlthDto> svlthDtoList) {
		logger.info("::addUtgHandlingar::");
		List<SvlthDto> newList = new ArrayList<SvlthDto>();

		svlthDtoList.forEach(dto -> {
			dto.setSvlth_uex(getUtgaendeHandlingar(dto.getSvlth_uex(), dto.getSvlth_igl(), dto.getSvlth_ign(), dto.getSvlth_pos()));
			newList.add(dto);
		});
		
		svlthDtoList.clear();
		svlthDtoList.addAll(newList);
		
	}

	private String getUtgaendeHandlingar(String uex, String svlth_igl, String svlth_ign, String svlth_pos) {
		StringBuffer buffer = new StringBuffer(uex);
		List<SvltuDao> list = svltuDaoService.findAll(svlth_igl, svlth_ign, svlth_pos);
		list.forEach(dao -> {
			buffer.append(System.lineSeparator()).append("#").append(dao.getSvltu_uha());			
		});

		return buffer.toString();
	}


	/**
	 * 
	 * Generate concat godsnummer with value from SVLTF.
	 * 
	 * Example :
	 * http://localhost:8080/syjservicesbcore/generateGodsnummer.do?user=SYSTEMA&svlth_igl=BJO
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "generateGodsnummer.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String generateGodsnummer(@RequestParam(value = "svlth_igl", required = true) String svlth_igl,
										HttpSession session, HttpServletRequest request) {	
		logger.info("INSIDE generateGodsnummer");
		
		return svltfDaoService.getGenerateGodsnummer(svlth_igl);
		
	}
	
	@Qualifier ("bridfDaoServices")
	private BridfDaoServices bridfDaoServices;
	@Autowired
	@Required
	public void setBridfDaoServices (BridfDaoServices value){ this.bridfDaoServices = value; }
	public BridfDaoServices getBridfDaoServices(){ return this.bridfDaoServices; }	

	@Qualifier ("svlthDaoService")
	private SvlthDaoService svlthDaoService;
	@Autowired
	@Required
	public void setSvlthDaoService(SvlthDaoService value){ this.svlthDaoService = value; }
	public SvlthDaoService getSvlthDaoService(){ return this.svlthDaoService; }		

	@Qualifier ("svltfDaoService")
	private SvltfDaoService svltfDaoService;
	@Autowired
	@Required
	public void setSvltfDaoService(SvltfDaoService value){ this.svltfDaoService = value; }
	public SvltfDaoService getSvltfDaoService(){ return this.svltfDaoService; }		
	
	
	@Qualifier ("svltuDaoService")
	private SvltuDaoService svltuDaoService;
	@Autowired
	@Required
	public void setSvltuDaoService(SvltuDaoService value){ this.svltuDaoService = value; }
	public SvltuDaoService getSvltuDaoService(){ return this.svltuDaoService; }		

	
	@Qualifier ("svtx03fDaoService")
	private Svtx03fDaoService svtx03fDaoService;
	@Autowired
	@Required
	public void setSvtx03fDaoService(Svtx03fDaoService value){ this.svtx03fDaoService = value; }
	public Svtx03fDaoService getSvtx03fDaoService(){ return this.svtx03fDaoService; }			
}
