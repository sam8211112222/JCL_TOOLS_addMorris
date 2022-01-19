package com.csi.jcl.service;

import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.dao.DefectListRepository;
import com.csi.jcl.model.DefectListAndAdJclModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 實作ListAllDefectService功能
 *
 * @author si1255 Morris Mao
 * @date 2021/10/25
 */

@Service
public class ListAllDefectServiceImpl implements ListAllDefectService {

    private static final Logger logger =
            LogManager.getLogger(ListAllDefectServiceImpl.class);

    private final DefectListRepository defectListRepository;

    public ListAllDefectServiceImpl(DefectListRepository defectListRepository) {
        this.defectListRepository = defectListRepository;
    }

    @Override
    public List<DefectListAndAdJclModel> listAllDefect(String sprint, String ad, String jcl, List<String> issueStatusList) {


        //如果SPRINT跟DEFECT狀態都選擇ALL
        if (issueStatusList.contains("ALL") & "All".equals(sprint)  ) {
            sprint = "";
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect123(sprint,ad,jcl);
            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("SPRINT and DEFECT selected All");
            return DefectListAndAdJclModel;
        //只有SPRINT選擇ALL
        }else if ("All".equals(sprint)) {
            sprint = "";

            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect(sprint, ad, jcl, issueStatusList);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("SPRINT selected All");
            return DefectListAndAdJclModel;
        //DEFECT選擇ALL
        } else if(issueStatusList.contains("ALL")){

            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect123(sprint, ad, jcl);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info(" DEFECT selected All");
            return DefectListAndAdJclModel;
        }else{
            List<Map<String, Object>> listDefect = defectListRepository.listAllDefect(sprint, ad, jcl, issueStatusList);

            List<DefectListAndAdJclModel> DefectListAndAdJclModel = JSONObject.parseArray(JSONObject.toJSONString(listDefect), DefectListAndAdJclModel.class);

            logger.info("All didn't be selected");
            return DefectListAndAdJclModel;
        }

    }
}
