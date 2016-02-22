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

import my.study.spstudy.domain.Course;
import my.study.spstudy.service.ICourseService;
import my.study.spstudy.util.JsonUtil;
import my.study.spstudy.vo.Page;

@Controller
public class CourseController extends BaseController{
	@Autowired
	ICourseService courseService;
		
	@RequestMapping(value="/course/create", method=RequestMethod.GET)
	public String createCoursePage(Model model) throws Exception{
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/course/create", Course.class, null, true);
		
		model.addAttribute("model", md);
		return "/pages/common/newentity";
	}
	
	
	@RequestMapping(value="/course/create", method=RequestMethod.POST)
	public String createCourse(Course course) throws Exception{	
		courseService.create(course);
		
		return "redirect:/course/" + course.getId();
	}
	
	@RequestMapping(value="/course/update/{id}", method=RequestMethod.GET)
	public String updateCoursePage(@PathVariable("id")Integer id, Model model) throws Exception{
		Course course = courseService.findById(id);		
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/course/update/" + id, Course.class, course, false);
		model.addAttribute("model", md);
		
		return "/pages/common/newentity";
	}
	
	@RequestMapping(value="/course/update/{id}", method=RequestMethod.POST)
	public String updateCourse(@PathVariable("id")Integer id, Course course) throws Exception{	
		courseService.update(course);
		
		return "redirect:/course/" + id;
	}
	
	@RequestMapping(value="/course/{id}", method=RequestMethod.GET)
	public String findCourseById(@PathVariable("id")Integer id, ModelMap model) throws Exception{	
		Course course = courseService.findById(id);		
		Map<String, Object> md = toEntityModel(request.getContextPath() + "/course/create", Course.class, course, false);
		model.addAttribute("model", md);
		return "/pages/common/entitydetail";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/course")	
	public String findCourseList(Page page, @ModelAttribute("listDetail") LinkedHashMap<String, Object> listDetail) throws Exception{
		List<Course> list = courseService.findList(page);	
		
		listDetail.put("name", Course.class.getSimpleName());
				
		String json = JsonUtil.toJson(new Course());		
		LinkedHashMap<String, Object> headers = JsonUtil.fromJson(json, LinkedHashMap.class);
		listDetail.put("headers", headers.keySet());
		
		listDetail.put("datas", list);
		
		
		return "/pages/common/entitylist";
		
	}

}
