package app.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.domain.Employee;
import app.service.DatabaseService;
import app.util.Constant;
import app.util.Constraint;
import app.util.PagingInfo;
import app.util.SearchResult;
import app.web.filter.FlashMap.MessageType;
import app.web.validator.EmployeeValidator;

@Controller
@RequestMapping("/master/employee/*")
public class EmployeeController extends BaseController {

	@Autowired private DatabaseService databaseService;
	
	@InitBinder
    public void initBinder(DataBinder dataBinder) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");

        dataBinder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
        dataBinder.registerCustomEditor(Date.class, "workTime", new CustomDateEditor(timeFormat, true));
    }
	
	@RequestMapping(value = "/")
    public String search(@ModelAttribute Employee employee, @ModelAttribute PagingInfo pagingInfo, ModelMap modelMap) {
		
		Constraint constraint = resolveSearch(employee);
		
		SearchResult searchResult = databaseService.searchEmployee(constraint, null, 
        		true, pagingInfo.getOffset(), pagingInfo.getPageSize());

        modelMap.put("employees", searchResult.getRecs());
        modelMap.put("pagingInfo", searchResult.getPagingInfo());
        
		return "master/employee/search";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String open(@ModelAttribute Employee employee, ModelMap modelMap) {
		
		modelMap.put("mode", Constant.MODE_CREATE);
		
		if (StringUtils.isNotBlank(employee.getId())) {
        	employee = databaseService.findEmployeeById(employee.getId());
        	
        	if(employee != null) {
        		modelMap.put("mode", Constant.MODE_UPDATE);
            	modelMap.put("employee", employee);
        	}
		}
		
		return "master/employee/detail";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
    public String save(@RequestParam int mode, @ModelAttribute Employee employee, 
    		BindingResult result, ModelMap modelMap) {
		
		modelMap.put("mode", mode);
		
		new EmployeeValidator().validate(employee, result);
    	if(result.hasErrors()) {
    		return "master/employee/detail";
    	}
    	
		if(mode == Constant.MODE_CREATE) {
			Employee existing = databaseService.findEmployeeById(employee.getId());
    		if(existing !=null) {
				addMessage(modelMap, "Karyawan dengan ID: "+ employee.getId() +" sudah ada.", MessageType.error, false);
				
				return "master/employee/detail";
			}
			
			employee = databaseService.saveEmployee(employee);
			
			addMessage(modelMap, "Data Karyawan berhasil ditambahkan.", MessageType.success, true);
		} else {
			employee = databaseService.saveEmployee(employee);
			
			addMessage(modelMap, "Data Karyawan berhasil diupdate.", MessageType.success, true);
		}
		
		return "redirect:detail?id=" + employee.getId();
	}
	
}
