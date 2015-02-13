package com.study.autoprodtool.web;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Urls;
import com.study.autoprodtool.entity.CategoryBig;
import com.study.autoprodtool.form.CategoryBigForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CrudService;
import com.study.autoprodtool.service.CategoryBigService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CategoryBigController extends AbstractEntityController<CategoryBigForm, CategoryBig>{	
//	private static final Logger log = LoggerFactory.getLogger(CategoryBigController.class);

	@Autowired
	private CategoryBigService categoryBigService;		
	
	@RequestMapping(value = Urls.CATEGORYBIG_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<CategoryBigForm> listCriteria, HttpServletRequest request) throws Exception {		
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.CATEGORYBIG_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(CategoryBigForm form) throws Exception{
		return super.create(form);
	}
	
	@RequestMapping(value = Urls.CATEGORYBIG_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(CategoryBigForm form) throws Exception{
		return super.update(form);
	}
	
	@RequestMapping(value = Urls.CATEGORYBIG_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<CategoryBigForm> initListCriteria(HttpServletRequest request){
		ListCriteria<CategoryBigForm> listCriteria = new ListCriteria<CategoryBigForm>(){

			@Override
			protected void addCriterions(Criteria criteria) {


			}

		};		
		return listCriteria;
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<CategoryBig> getCrudService() {		
		return categoryBigService;
	}	
}
