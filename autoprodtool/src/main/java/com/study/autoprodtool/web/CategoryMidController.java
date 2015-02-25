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
import com.study.autoprodtool.entity.CategoryMid;
import com.study.autoprodtool.form.CategoryMidForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CategoryMidService;
import com.study.autoprodtool.service.CrudService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CategoryMidController extends AbstractEntityController<CategoryMidForm, CategoryMid>{	
//	private static final Logger log = LoggerFactory.getLogger(CategoryMidController.class);

	@Autowired
	private CategoryMidService categoryMidService;		
	
	@RequestMapping(value = Urls.CATEGORYMID_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<CategoryMidForm> listCriteria, HttpServletRequest request) throws Exception {		
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.CATEGORYMID_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(CategoryMidForm form) throws Exception{
		return super.create(form);
	}
	
	@RequestMapping(value = Urls.CATEGORYMID_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(CategoryMidForm form) throws Exception{
		return super.update(form);
	}
	
	@RequestMapping(value = Urls.CATEGORYMID_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<CategoryMidForm> initListCriteria() throws Exception{		
		return getDefaultListCriteria();
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<CategoryMid> getCrudService() {		
		return categoryMidService;
	}	
}
