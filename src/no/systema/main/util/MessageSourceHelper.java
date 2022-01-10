package no.systema.main.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import no.systema.main.context.JServicesAppContext;

/**
 * Help class accessing messages in message.properties
 * 
 * @author Fredrik Möller
 * @date Okt 6, 2016
 *
 */
public class MessageSourceHelper {
	private static final Logger logger = LoggerFactory.getLogger(MessageSourceHelper.class.getName());

	private ApplicationContext context = null;
	private Locale locale = null;

	public MessageSourceHelper(HttpServletRequest request) {
		context = JServicesAppContext.getApplicationContext();
		CookieLocaleResolver localeResolver = (CookieLocaleResolver) context.getBean("localeResolver");
		locale = localeResolver.resolveLocale(request);
	}
	
	
	/**
	 * Decode key/id into messages from message.properties.
	 * Using locale from CookieLocaleResolver
	 * 
	 * @param id, key in message.properties
	 * @param params
	 * @return decoded message
	 */
	public String getMessage(String id, Object[] params) {
		return context.getMessage(id, params, locale);
	}
	
}
