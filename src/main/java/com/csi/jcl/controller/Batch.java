package com.csi.jcl.controller;

import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csi.jcl.service.ThisService;

//@ResponseBody
@Controller
public class Batch {
	@Autowired
	ThisService ThisService;
	
	
	@GetMapping(value = "/batch")
	public String find(Model model, HttpServletRequest request) {
		String ad = request.getParameter("AD");
		String sprint = request.getParameter("sprint");
		
//		String find1=ThisService.findbyonead(ad);
		List<Map<ThisService,Object>> find=ThisService.findbatchad(ad);
		List<Map<ThisService, Object>> findtester = ThisService.findbycodetypeid("TESTER");
		List<Map<ThisService, Object>> findtestresult = ThisService.findbyresult("TEST_RESULTS");
		if(find.size()!=0) {
		for(Map<ThisService, Object> all:find) 
//			 for (Entry<ThisService, Object> allmap : all.entrySet()) {
//	                System.out.print(allmap.getKey() + "    ");
//	                System.out.println(allmap.getValue());
//			 }
//		}
//		if(find1!=null) {
		model.addAttribute("batch", all);
		model.addAttribute("findtester",findtester);
		model.addAttribute("findtestresult",findtestresult);
		}else {
			return "update/su2";
		}
		
		return "batchupdate/showb";

	}
}
