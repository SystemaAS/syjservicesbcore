package no.systema.jservices.bcore.z.maintenance.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import no.systema.jservices.common.dao.SvltfDao;
import no.systema.jservices.common.dao.services.SvltfDaoService;
import no.systema.jservices.common.json.JsonResponseWriter2;
import no.systema.jservices.common.util.StringUtils;
import no.systema.jservices.model.dao.services.BridfDaoServices;

@Controller
public class BcoreMaintResponseOutputterController_SVLTF {
	private static final Logger logger = Logger.getLogger(BcoreMaintResponseOutputterController_SVLTF.class.getName());

	@Autowired
	private SvltfDaoService svltfDaoService;
	
	/**
	 * @return
	 * @Example SELECT specific: http://localhost:8080/syjservicesbcore/syjsSVLTF.do?user=FREDRIK&svltf_igl=BJO
	 * @Example SELECT list: http://localhost:8080/syjservicesbcore/syjsSVLTF.do?user=FREDRIK
	 * 
	 */
	@RequestMapping(value="syjsSVLTF.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String getSvltf(HttpSession session, HttpServletRequest request) {
		JsonResponseWriter2<SvltfDao> jsonWriter = new JsonResponseWriter2<SvltfDao>();
		StringBuffer sb = new StringBuffer();
		List<SvltfDao> svltfDaoList = null;
		
		try {
			String user = request.getParameter("user");
			String svltf_igl = request.getParameter("svltf_igl");
			// Check ALWAYS user in BRIDF
			String userName = this.bridfDaoServices.findNameById(user); 
			String errMsg = "";
			String status = "ok";
			StringBuffer dbErrorStackTrace = new StringBuffer();

			if ((userName != null && !"".equals(userName))) {
				if (StringUtils.hasValue(svltf_igl)) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("svltf_igl", svltf_igl);
					logger.info("WTF");
					svltfDaoList = svltfDaoService.findAll(params);
				} else {
					svltfDaoList = svltfDaoService.findAll(null);
				}
				if (svltfDaoList != null) {
						sb.append(jsonWriter.setJsonResult_Common_GetList(userName, svltfDaoList));
				} else {
					errMsg = "ERROR on SELECT: Can not find SvltfDao list";
					status = "error";
					sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
				}

			} else {
				errMsg = "ERROR on SELECT";
				status = "error";
				dbErrorStackTrace.append("request input parameters are invalid: <user>");
				sb.append(jsonWriter.setJsonSimpleErrorResult(userName, errMsg, status, dbErrorStackTrace));
			}
		} catch (Exception e) {
			logger.info("Error :", e);
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return "ERROR [JsonResponseOutputterController]" + writer.toString();
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

}
