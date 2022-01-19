package com.csi.jcl.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csi.jcl.entity.TestCase;
import com.csi.jcl.service.ThisService;

@Controller

public class Update {

	@Autowired
	ThisService thisService;

	@RequestMapping("/su")
	public String sus() {
		return "update/su";
	}

//	@ResponseBody
	@GetMapping(value = "/show")
	public String find(Model model, HttpServletRequest request, Pageable pageable, @RequestParam("page") Integer page) {
		String ad = request.getParameter("AD");
		String sprint = request.getParameter("sprint");
		String jcl = request.getParameter("JCL");

		if (ad != "" || !ad.equals(null)) {

//		List<Map<ThisService, Object>> findad = thisService.findbyadsprint(ad, sprint,jcl);
			// 找使用者給下拉選單
			List<Map<ThisService, Object>> findtester = thisService.findbycodetypeid("TESTER");
			// 找status給下拉選單
			List<Map<ThisService, Object>> findtestresult = thisService.findbyresult("TEST_RESULTS");
//		List<Map<ThisService, Object>> findinner = thisService.findinner();

			// leftjoin查詢後的資料
			List<Map<ThisService, Object>> findleftinner = thisService.findleftinner(ad, sprint, jcl);

			List<Map<String, String>> breakpoint = thisService.findbreakpoint(ad);

			List<Map<String, String>> checkpoint = thisService.findcheckpoint(ad);
//			List<Map<String, String>> afterRemove = new ArrayList<Map<String, String>>();
//			for (Map<String, String> all : checkpoint) {
//				Map<String, String> d = new HashMap<String, String>(all);
//				d.remove("COUNT(PASSFORM)");
//				d.remove("COUNT(IOCHECKLIST)");
//				d.remove("COUNT(ALLJCL)");
//				afterRemove.add(d);
//			}

//			System.out.println(afterRemove);
//			for(Map<String, String> a:afterRemove) {
//				for (Entry<String, String> allmap : a.entrySet()) {
//					System.out.print(allmap.getKey() + "    ");
//					System.out.println(allmap.getValue());
//				}
//			}

			// 計算資料的起始與結束位置
			int start = (int) pageable.getOffset();
			int end = Math.min((start + pageable.getPageSize()), findleftinner.size());

			// 把adJclModelList轉換成Page型別
			Page<TestCase> allJclList = new PageImpl(findleftinner.subList(start, end), pageable, findleftinner.size());

			// 顯示頁面總數
			List<Integer> pageList = new ArrayList<Integer>();
			for (int i = 0; i < allJclList.getTotalPages(); i++) {
				pageList.add(i);
			}
			model.addAttribute("checkpoint", checkpoint);
			model.addAttribute("breakpoint", breakpoint);
			model.addAttribute("result", findtestresult);
			model.addAttribute("tester", findtester);
			model.addAttribute("allJclList", allJclList);
			model.addAttribute("sprint", sprint);
			model.addAttribute("adName", ad);
			model.addAttribute("page", page);
			model.addAttribute("pageList", pageList);
			model.addAttribute("jcl", jcl);
//		model.addAttribute("havetime", findleftinner);

		}

		return "update/show";
	}

}
