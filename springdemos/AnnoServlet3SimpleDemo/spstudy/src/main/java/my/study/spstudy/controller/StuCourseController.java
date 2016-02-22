package my.study.spstudy.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.study.spstudy.domain.StuCourse;
import my.study.spstudy.service.IStuCourseService;
import my.study.spstudy.util.JsonUtil;
import my.study.spstudy.vo.Page;

@Controller
public class StuCourseController extends BaseController{
	@Autowired
	IStuCourseService stucourseService;
		
	@RequestMapping(value="/stucourse/create", method=RequestMethod.GET)
	public String createStuCoursePage(Model model) throws Exception{
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/stucourse/create", StuCourse.class, null, true);
		
		model.addAttribute("model", md);
		return "/pages/common/newentity";
	}
	
	
	@RequestMapping(value="/stucourse/create", method=RequestMethod.POST)
	public String createStuCourse(StuCourse stucourse) throws Exception{	
		stucourseService.create(stucourse);
		
		return "redirect:/stucourse/" + stucourse.getId();
	}
	
	@RequestMapping(value="/stucourse/update/{id}", method=RequestMethod.GET)
	public String updateStuCoursePage(@PathVariable("id")Integer id, Model model) throws Exception{
		StuCourse stucourse = stucourseService.findById(id);		
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/stucourse/update/" + id, StuCourse.class, stucourse, false);
		model.addAttribute("model", md);
		
		return "/pages/common/newentity";
	}
	
	@RequestMapping(value="/stucourse/update/{id}", method=RequestMethod.POST)
	public String updateStuCourse(@PathVariable("id")Integer id, StuCourse stucourse) throws Exception{	
		stucourseService.update(stucourse);
		
		return "redirect:/stucourse/" + id;
	}
	
	@RequestMapping(value="/stucourse/{id}", method=RequestMethod.GET)
	public String findStuCourseById(@PathVariable("id")Integer id, ModelMap model) throws Exception{	
		StuCourse stucourse = stucourseService.findById(id);		
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/stucourse/create", StuCourse.class, stucourse, false);
		model.addAttribute("model", md);
		return "/pages/common/entitydetail";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/stucourse")	
	public String findStuCourseList(Page page, @ModelAttribute("listDetail") LinkedHashMap<String, Object> listDetail) throws Exception{
		List<StuCourse> list = stucourseService.findList(page);	
		
		listDetail.put("name", StuCourse.class.getSimpleName());
				
		String json = JsonUtil.toJson(new StuCourse());		
		LinkedHashMap<String, Object> headers = JsonUtil.fromJson(json, LinkedHashMap.class);
		listDetail.put("headers", headers.keySet());
		
		listDetail.put("datas", list);
		
		
		return "/pages/common/entitylist";
		
	}

}
