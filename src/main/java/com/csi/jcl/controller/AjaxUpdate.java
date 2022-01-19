package com.csi.jcl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.service.ThisService;


@RestController
//@RequestMapping("/ajax")
public class AjaxUpdate {

	@Autowired
	ThisService thisService;

	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model,  HttpServletRequest request,
			@RequestParam(value = "ss", required = false) String SS) throws IOException {
		//轉jsonarray
//		JSONObject jsonObject=JSONObject.parseObject(SS);
		JSONArray jsonArray = JSONArray.parseArray(SS);

		for (int i = 0; i < jsonArray.size(); i++) {
//			Integer seq = thisService.getseq();
			JSONObject key = (JSONObject) jsonArray.get(i);
			if (!key.get("STATUS").equals("1") && !key.get("TESTER_ID").equals("1")) {
				
				String status = key.get("STATUS").toString();
				String tid = key.get("TID").toString();
				String tester_id = key.get("TESTER_ID").toString();
				String rdatetime = key.get("RDATETIME").toString();
				String rid ="";
				
				
				thisService.saveresult(status, tid, tester_id, rdatetime,rid);
				thisService.updatetestcase(tid,status,tester_id);
			}
		}

//		PrintWriter out = response.getWriter();
		//ajax回寫json格式
		String jsonStr = "{\"success\":\"OK\"}";
//		out.write(jsonStr);
		return jsonStr;

	}
}
