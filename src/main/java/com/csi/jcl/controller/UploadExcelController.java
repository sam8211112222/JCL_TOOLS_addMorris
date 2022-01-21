package com.csi.jcl.controller;

import com.csi.jcl.service.UploadExcelService;
import com.csi.jcl.service.UserInfoServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadExcelController {

    private static final Logger logger =
            LogManager.getLogger(UploadExcelController.class);

    private UploadExcelService uploadExcelService;

    public UploadExcelController(UploadExcelService uploadExcelService) {
        this.uploadExcelService = uploadExcelService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, Model model) throws Exception {

        logger.info("It's afterUploadPage");

        //顯示上傳EXCEL狀態
        logger.info("Call uploadExcelService.getExcel(file)");
        String uploadStatus = uploadExcelService.getExcel(file);

        if (uploadStatus.equals("上傳成功")) {

            model.addAttribute("Message", "上傳成功");

        } else if (uploadStatus.equals("請選擇要上傳的檔案")) {

            model.addAttribute("Message", "請選擇要上傳的檔案");

        } else if(uploadStatus.contains("請確認")){
            model.addAttribute("Message", uploadStatus);
        }else {
            model.addAttribute("Message", "檔案格式或內容錯誤！");
        }

        return "defectSearch/afterUpload";

    }


}
