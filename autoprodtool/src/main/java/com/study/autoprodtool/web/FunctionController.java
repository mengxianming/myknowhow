package com.study.autoprodtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Urls;
import com.study.autoprodtool.entity.Function;
import com.study.autoprodtool.form.FilterListCriteria;
import com.study.autoprodtool.form.FunctionForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.CrudService;
import com.study.autoprodtool.service.FunctionService;

/**
 * 
 * Descriptions
 *
 * @version 2015-2-15
 * @author SUNTEC
 * @since JDK1.6
 *
 */
@Controller
public class FunctionController extends AbstractEntityController<FunctionForm, Function>{	
//	private static final Logger log = LoggerFactory.getLogger(FunctionController.class);
	
	@Autowired
	private FunctionService functionService;
	
	
	@RequestMapping(value = Urls.FUNCTION_LIST, method = RequestMethod.GET)
	public String listPage() {
		
		return "/function/list";
	}
	
	@RequestMapping(value = Urls.FUNCTION_CREATE, method = RequestMethod.GET)
	public String createPage() {
		
		return "/function/create";
	}
	
	@RequestMapping(value = Urls.FUNCTION_UPDATE, method = RequestMethod.GET)
	public String updatePage(@RequestParam Long id, Model model) throws Exception {
		Function function = functionService.selectOne(id);
		model.addAttribute("function", new FunctionForm().initFromEntity(function));
		
		return "/function/update";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = Urls.FUNCTION_DETAIL , method = RequestMethod.GET)
	public String detailPage(@RequestParam Long id, Model model) throws Exception {
		Function function = functionService.selectOne(id);
		model.addAttribute("function", new FunctionForm().initFromEntity(function));

		return "/function/detail";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = Urls.FUNCTION_FILTER_LIST, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult filterList(@ModelAttribute("filterListCriteria") FilterListCriteria<FunctionForm> listCriteria) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, List<Object>> resultOptions = getCrudService().selectFilterOptions(listCriteria, true);		
		result.put("selections", listCriteria.getSelections());
		result.put("resultOptions", resultOptions);
		return JsonResult.success(resultOptions);
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = Urls.FUNCTION_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute("listCriteria") ListCriteria<FunctionForm> listCriteria, HttpServletRequest request) throws Exception {
		return super.list(listCriteria, request);
	}

	@RequestMapping(value = Urls.FUNCTION_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(FunctionForm functionForm) throws Exception{
		return super.create(functionForm);
	}
	
	@RequestMapping(value = Urls.FUNCTION_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(FunctionForm functionForm) throws Exception{
		return super.update(functionForm);
	}
	
	@RequestMapping(value = Urls.FUNCTION_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@RequestParam String ids) throws Exception{
		return super.delete(ids);
	}

	@ModelAttribute
	public ListCriteria<FunctionForm> initListCriteria(){		
		return getDefaultListCriteria();
	}
	
	@ModelAttribute("filterListCriteria")
	public ListCriteria<FunctionForm> initFilterListCriteria(HttpServletRequest request){
		ListCriteria<FunctionForm> listCriteria = new FilterListCriteria<FunctionForm>(getEntityFormClass());
		
		return listCriteria;
	}

	/* (non-Javadoc)
	 * @see com.study.autoprodtool.web.AbstractEntityController#getCrudService()
	 */
	@Override
	CrudService<Function> getCrudService() {		
		return functionService;
	}

}
