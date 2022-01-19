package com.csi.jcl.service;

import com.alibaba.fastjson.JSONObject;
import com.csi.jcl.dao.AdJclRepository;
import com.csi.jcl.dao.UserInfoRepository;
import com.csi.jcl.entity.AdJclEntity;
import com.csi.jcl.entity.UserInfoEntity;
import com.csi.jcl.model.AdJclModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
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
    private final AdJclRepository adJclRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public JclServiceImpl(AdJclRepository adJclRepository, UserInfoRepository userInfoRepository) {
        this.adJclRepository = adJclRepository;
        this.userInfoRepository = userInfoRepository;
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
        return adJclRepository.findByAd(adName);
    }

    /**
     * 依條件查詢JCL列表
     *
     * @param testType testType參數的值
     * @param systemOp systemOp參數的值
     * @param adName adName參數的值
     * @return listJclModel
     * @author si1206 Sam Chen
     * @date 2021/08/04
     */
    @Override
    public List<AdJclModel> listAllJclByCondition(String testType,String systemOp,String adName) {
        logger.info("Call listAllJclByCondition()");

        //如果testType=All, 將testType的值轉換成空字串
        if ("All".equals(testType)) {
            testType = "";
            if ("All".equals(systemOp)){
                systemOp = "";
                logger.info("testType = " + testType + " systemOp = " + systemOp + "adName = " + adName);
                List<Map<String, Object>> listJcl = adJclRepository.listAllJclByCondition(testType, systemOp, adName);
                logger.info("Get listJcl from DB");
                List<AdJclModel> listJclModel = JSONObject.parseArray(JSONObject.toJSONString(listJcl), AdJclModel.class);
                logger.debug(listJclModel);
                return listJclModel;
            }else {
                logger.info("testType = " + testType + " systemOp = " + systemOp + "adName = " + adName);
                List<Map<String, Object>> listJcl = adJclRepository.listAllJclByCondition(testType, systemOp, adName);
                logger.info("Get listJcl from DB");
                List<AdJclModel> listJclModel = JSONObject.parseArray(JSONObject.toJSONString(listJcl), AdJclModel.class);
                logger.debug(listJclModel);
                return listJclModel;
            }
        } else {
            if ("All".equals(systemOp)){
                systemOp = "";
                logger.info("testType = " + testType + " systemOp = " + systemOp + "adName = " + adName);
                List<Map<String, Object>> listJcl = adJclRepository.listAllJclByCondition(testType, systemOp, adName);
                logger.info("Get listJcl from DB");
                List<AdJclModel> listJclModel = JSONObject.parseArray(JSONObject.toJSONString(listJcl), AdJclModel.class);
                logger.debug(listJclModel);
                return listJclModel;
            }else {
                logger.info("testType = " + testType + " systemOp = " + systemOp + "adName = " + adName);
                List<Map<String, Object>> listJcl = adJclRepository.listAllJclByCondition(testType, systemOp, adName);
                logger.info("Get listJcl from DB");
                List<AdJclModel> listJclModel = JSONObject.parseArray(JSONObject.toJSONString(listJcl), AdJclModel.class);
                logger.debug(listJclModel);
                return listJclModel;
            }
        }
    }

    @Override
    public List<UserInfoEntity> findAll() {

        List<UserInfoEntity> list = userInfoRepository.findAll();

        return list;
    }

    /**
     * 依條件產生Excel檔
     * @param listAllJclByCondition
     * @return
     */
    @Override
    public ByteArrayInputStream generateExcel(List<AdJclModel> listAllJclByCondition) {
        // 輸出流
        FileOutputStream out = null;

        // 建立Excel需要的東西
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 目前的Sheet
        XSSFSheet sheet = workbook.createSheet("查詢可執行之AD");

        // 表頭的資料
        XSSFRow firstRow = sheet.createRow(0);

        // 目前的RowIndex
        int rowIndex = 1;

        XSSFRow currentRow;

        // 用Map存放第一列的欄位名稱
        Map<String, Integer> headerMap = new HashMap<String, Integer>();
        headerMap.put("SPRINT",0);
        headerMap.put("AD",1);
        headerMap.put("AD Description",2);
        headerMap.put("CHT AP",3);
        headerMap.put("CHT DC",4);
        headerMap.put("子系統",5);
        headerMap.put("JCL數量",6);

        firstRow.createCell(0).setCellValue("SPRINT");
        firstRow.createCell(1).setCellValue("AD");
        firstRow.createCell(2).setCellValue("AD Description");
        firstRow.createCell(3).setCellValue("CHT AP");
        firstRow.createCell(4).setCellValue("CHT DC");
        firstRow.createCell(5).setCellValue("子系統");
        firstRow.createCell(6).setCellValue("JCL數量");



        for (int i = 0; i < listAllJclByCondition.size(); i++){
            XSSFRow xssfRow = sheet.createRow(rowIndex+i);
            currentRow = sheet.getRow(rowIndex+i);
            currentRow.createCell(headerMap.get("SPRINT")).setCellValue(listAllJclByCondition.get(i).getSprint());
            currentRow.createCell(headerMap.get("AD")).setCellValue(listAllJclByCondition.get(i).getAd());
            currentRow.createCell(headerMap.get("AD Description")).setCellValue(listAllJclByCondition.get(i).getAddesc());
            currentRow.createCell(headerMap.get("CHT AP")).setCellValue(listAllJclByCondition.get(i).getCht_ap());
            currentRow.createCell(headerMap.get("CHT DC")).setCellValue(listAllJclByCondition.get(i).getCht_dc());
            currentRow.createCell(headerMap.get("子系統")).setCellValue(listAllJclByCondition.get(i).getSystemtype());
            currentRow.createCell(headerMap.get("JCL數量")).setCellValue(listAllJclByCondition.get(i).getJclcout());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(outputStream.toByteArray());

    }

}

