package locationsweb;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import locationsweb.controller.Webconfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	//backend-hez tartozó konfiguráció
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	//frontend-hez tartotó konfiguráció
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {Webconfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
