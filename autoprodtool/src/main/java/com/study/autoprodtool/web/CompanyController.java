package com.study.autoprodtool.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Urls;
import com.study.autoprodtool.entity.Company;
import com.study.autoprodtool.form.CompanyForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CompanyService;
import com.study.autoprodtool.service.CrudService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CompanyController extends AbstractEntityController<CompanyForm, Company>{	
//	private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;		
	
	@RequestMapping(value = Urls.COMPANY_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<CompanyForm> listCriteria, HttpServletRequest request) throws Exception {		
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.COMPANY_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(CompanyForm form) throws Exception{
		return super.create(form);
	}
	
	@RequestMapping(value = Urls.COMPANY_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(CompanyForm form) throws Exception{
		return super.update(form);
	}
	
	@RequestMapping(value = Urls.COMPANY_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<CompanyForm> initListCriteria(){		
		return getDefaultListCriteria();
	}
	
	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<Company> getCrudService() {		
		return companyService;
	}

}
