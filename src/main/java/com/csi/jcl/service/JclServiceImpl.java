package com.csi.jcl.service;

import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.dao.JclRepository;
import com.csi.jcl.entity.AdJclEntity;
import com.csi.jcl.model.AdJclModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 實作JclService功能
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */
@Service
public class JclServiceImpl implements JclService {

    private static final Logger logger =
            LogManager.getLogger(JclServiceImpl.class);

    /**
     * 注入JclRepository
     *
     * @author si1206 Sam Chen
     * @date 2021/08/04
     */
    private final JclRepository jclRepository;

    @Autowired
    public JclServiceImpl(JclRepository jclRepository) {
        this.jclRepository = jclRepository;
    }

    /**
     * 依adName查詢相關的JCL
     *
     * @param adName adName參數的值
     * @return jclRepository.findByAd(adName)
     * @author si1206 Sam Chen
     * @date 2021/08/04
     */
    @Override
    public List<AdJclEntity> findJclByAd(String adName) {
        logger.info("Call findJclByAd()");
        return jclRepository.findByAd(adName);
    }

    /**
     * 依條件查詢JCL列表
     *
     * @param sprint sprint參數的值
     * @param adName adName參數的值
     * @return listJclModel
     * @author si1206 Sam Chen
     * @date 2021/08/04
     */
    @Override
    public List<AdJclModel> listAllJclByCondition(String adName, String sprint) {
        logger.info("Call listAllJclByCondition()");

        //如果sprint=All, 將sprint的值轉換成空字串
        if ("All".equals(sprint)) {
            sprint = "";
            logger.info("adName = " + adName + " sprint = " + sprint);

            List<Map<String, Object>> listJcl = jclRepository.listAllJclByCondition(adName, sprint);
            logger.info("Get listJcl from DB");

            List<AdJclModel> listJclModel = JSONObject.parseArray(JSONObject.toJSONString(listJcl), AdJclModel.class);
            logger.info(listJclModel);
            return listJclModel;
        } else {

            logger.info("adName = " + adName + " sprint = " + sprint);

            List<Map<String, Object>> listJcl = jclRepository.listAllJclByCondition(adName, sprint);
            logger.info("Get listJcl from DB");

            List<AdJclModel> listJclModel = JSONObject.parseArray(JSONObject.toJSONString(listJcl), AdJclModel.class);
            logger.info(listJclModel);
            return listJclModel;
        }
    }

}

