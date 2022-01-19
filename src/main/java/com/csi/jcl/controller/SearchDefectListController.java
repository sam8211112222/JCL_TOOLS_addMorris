package com.csi.jcl.controller;

import com.csi.jcl.model.DefectListAndAdJclModel;
import com.csi.jcl.service.CodeListService;
import com.csi.jcl.service.ListAllDefectService;
import com.csi.jcl.service.UserInfoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SearchDefectListController {

    private static final Logger logger =
            LogManager.getLogger(SearchDefectListController.class);

    private final ListAllDefectService listAllDefectService;

    private final CodeListService codeListService;

    public SearchDefectListController(ListAllDefectService listAllDefectService, CodeListService codeListService) {
        this.listAllDefectService = listAllDefectService;
        this.codeListService = codeListService;
    }


    @GetMapping("/defect")
    public String home() {
        logger.info("It's defectSearchHomePage");
        return "defectSearch/newHome";
    }


    @RequestMapping(value = "/defectSearch")
    public String searchDefectList(Model model,
                                   @RequestParam("sprint") String sprint,
                                   @RequestParam("ad") String ad,
                                   @RequestParam("jcl") String jcl,
                                   @RequestParam("issueStatus") String issueStatus,
                                   Pageable pageable,
                                   @RequestParam("page") Integer page) {

        logger.info("It's defectSearchPage");
        String[] ary = issueStatus.split(",");
        ArrayList issueStatusList = new ArrayList<String>();
        for (int i = 0; i < ary.length; i++) {
            issueStatusList.add(ary[i]);
        }


//        model.addAttribute("allCodeList",codeListService.getAllCodeList());
        // 從jclService的listAllJclByCondition取得資料
        List<DefectListAndAdJclModel> defectListAndAdJclModelList = listAllDefectService.listAllDefect(sprint, ad, jcl, issueStatusList);

        // 計算資料的起始與結束位置
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), defectListAndAdJclModelList.size());

        // 把adJclModelList轉換成Page型別
        Page<DefectListAndAdJclModel> allDefectList =
                new PageImpl<DefectListAndAdJclModel>(defectListAndAdJclModelList.subList(start, end), pageable, defectListAndAdJclModelList.size());

        // 顯示頁面總數
        List<Integer> pageList = new ArrayList<Integer>();
        for (int i = 0; i < allDefectList.getTotalPages(); i++) {
            pageList.add(i);
        }

        // 加入model
        model.addAttribute("allDefectList", allDefectList);
        model.addAttribute("sprint", sprint);
        model.addAttribute("ad", ad);
        model.addAttribute("jcl", jcl);
        model.addAttribute("page", page);
        model.addAttribute("issueStatus", issueStatus);
        model.addAttribute("pageList", pageList);

        // 導回defect_list頁面
        return "defectSearch/defect_list";
    }

}


