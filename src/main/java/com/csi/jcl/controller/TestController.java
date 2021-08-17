package com.csi.jcl.controller;

import com.csi.jcl.entity.UserInfoEntity;
import com.csi.jcl.service.JclService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 這個純粹測試用
 */
@RestController
public class TestController {

    private final JclService jclService;

    public TestController(JclService jclService) {
        this.jclService = jclService;
    }

    @GetMapping("/info")
    public List<UserInfoEntity> listAll(){
        return jclService.findAll();
    }

//    @GetMapping("/login/form")
//    @ResponseBody
//    public String updateUesrInfo(@RequestParam("userId") String userId){
//        TestuserinfoEntity user = userInfoService.findById(userId);
//        user.setLastlogindatetime(new java.sql.Timestamp(System.currentTimeMillis()));
//        logger.info("有進來");
//        return "jcl/jcl_home";
//    }
}
