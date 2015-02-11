package com.study.autoprodtool.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.autoprodtool.common.CheckUtil;
import com.study.autoprodtool.common.ComUtils;
import com.study.autoprodtool.common.JsonResult;
import com.study.autoprodtool.common.ListJsonResult;
import com.study.autoprodtool.common.Urls;
import com.study.autoprodtool.entity.Division;
import com.study.autoprodtool.form.DivisionForm;
import com.study.autoprodtool.form.ListCriteria;
import com.study.autoprodtool.service.DivisionService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DivisionController {	
	private static final Logger log = LoggerFactory.getLogger(DivisionController.class);

	@Autowired
	private DivisionService divisionService;
	@Value("${pageLimit}")
	private Integer pageLimit;
	
	
	@RequestMapping(value = Urls.DIVISION_LIST, method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResult list(@ModelAttribute ListCriteria<DivisionForm> listCriteria) throws Exception {
		List<Division> list = divisionService.selectList(listCriteria);			
		List<DivisionForm> ret = new ArrayList<DivisionForm>();
		for(Division ent : list){
			ret.add(new DivisionForm().initFromEntity(ent));
		}

		return ListJsonResult.success(ret, listCriteria.getPager());
	}

	@RequestMapping(value = Urls.DIVISION_CREATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult create(DivisionForm form) throws Exception{
		Division ent = form.toEntity(Division.class);
		divisionService.insert(ent);
		return JsonResult.success(ent.getId());
	}
	
	@RequestMapping(value = Urls.DIVISION_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(DivisionForm form) throws Exception{
		Division ent = form.toEntity(Division.class);
		divisionService.update(ent);
		return JsonResult.success(null);
	}
	
	@RequestMapping(value = Urls.DIVISION_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(String ids) throws Exception{
		if(CheckUtil.isNull(ids)){
			throw new Exception("empty parameter: ids");
		}
		List<Long> idList = ComUtils.toNumbers(ids.split(","), Long.class);
		
		
		Map<Long, Division> entities = divisionService.selectListByIds(idList.toArray(new Long[0]));
		//check parameter
		for(Long id : idList){
			if(!entities.containsKey(id)){
				throw new Exception(String.format("invalid parameter: %s... the entity for id %s does not exist.", ids, id));
			}
		}
		
		//do delete
		for(Long id : idList){
			divisionService.delete(entities.get(id));
		}
		
		return JsonResult.success(null);
	}

	@ModelAttribute
	public ListCriteria<DivisionForm> initListCriteria(HttpServletRequest request){
		ListCriteria<DivisionForm> listCriteria = new ListCriteria<DivisionForm>(){

			@Override
			protected void addCriterions(Criteria criteria) {


			}

		};
		ComUtils.populateJqGridPagerSorterInfo(request, pageLimit, listCriteria);
		return listCriteria;
	}

}
