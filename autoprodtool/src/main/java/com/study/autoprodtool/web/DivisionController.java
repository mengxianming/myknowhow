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
import com.study.autoprodtool.entity.Division;
import com.study.autoprodtool.form.DivisionForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CrudService;
import com.study.autoprodtool.service.DivisionService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DivisionController extends AbstractEntityController<DivisionForm, Division>{	
//	private static final Logger log = LoggerFactory.getLogger(DivisionController.class);

	@Autowired
	private DivisionService divisionService;		
	
	@RequestMapping(value = Urls.DIVISION_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<DivisionForm> listCriteria, HttpServletRequest request) throws Exception {		
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.DIVISION_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(DivisionForm form) throws Exception{
		return super.create(form);
	}
	
	@RequestMapping(value = Urls.DIVISION_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(DivisionForm form) throws Exception{
		return super.update(form);
	}
	
	@RequestMapping(value = Urls.DIVISION_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<DivisionForm> initListCriteria() throws Exception{
		return getDefaultListCriteria();
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<Division> getCrudService() {		
		return divisionService;
	}

}
