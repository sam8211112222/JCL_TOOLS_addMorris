package com.csi.jcl.controller;

import com.csi.jcl.entity.AdJclEntity;
import com.csi.jcl.entity.UserInfoEntity;
import com.csi.jcl.model.AdJclModel;
import com.csi.jcl.service.JclService;
import com.csi.jcl.service.UserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 定義JclController類別
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */
@Controller
@RequestMapping("/jcl")
public class JclController {

    private static final Logger logger =
            LogManager.getLogger(JclController.class);

    /**
     * 注入JclService,UserInfoService
     *
     * @author si1206 Sam Chen
     * @date 2021/08/04
     */
    private final JclService jclService;
    private final UserInfoService userInfoService;

    @Autowired
    public JclController(JclService jclService, UserInfoService userInfoService) {
        this.jclService = jclService;
        this.userInfoService = userInfoService;
    }

    /**
     * JCL首頁
     * 從session取得userid來更新最後登入時間
     *
     * @author si1206 Sam Chen
     * @param httpSession HttpSession
     * @date 2021/08/04
     * @return jcl_home.html
     */
    @GetMapping("/home")
    public String hello(HttpSession httpSession) {

        // 取得Session內的key, value
//        Enumeration<String> names = httpSession.getAttributeNames();
//        while (names.hasMoreElements()){
//            String name = names.nextElement();
//            Object value = httpSession.getAttribute(name);
//            logger.info("name = " + name);
//            logger.info("value = " + value);
//        }
        // 取得SPRING_SECURITY_CONTEXT
        Object spring_security_context = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityContext securityContext = (SecurityContext) spring_security_context;
        // 取得認證訊息
        Authentication authentication = securityContext.getAuthentication();
        // 取得登入資訊
        Object principal = authentication.getPrincipal();

        //取得userid
        User user = (User) principal;
        String userid = user.getUsername();

        if (httpSession.getAttribute("login") == null){
            // 依userid尋找對應的userInfoEntity
            UserInfoEntity userInfoEntity =  userInfoService.findById(userid);
            // 更新userInfoEntity的狀態
            userInfoService.updateInfo(userInfoEntity);
            httpSession.setAttribute("login","login");
        }
        return "jcl/jcl_home";
    }

    /**
     * 依條件查詢JCL列表
     *
     * @author si1206 Sam Chen
     * @date 2021/08/04
     * @param model 傳入Thymeleaf的資料
     * @param sprint sprint參數的值
     * @param adName adName參數的值
     * @param pageable 單頁內容總數
     * @param page 頁面總數
     * @return jcl_list.html
     */
    @GetMapping("/search")
    public String searchAdJcl(Model model,
                              @RequestParam("sprint") String sprint,
                              @RequestParam("adName") String adName,
                              Pageable pageable,
                              @RequestParam("page") Integer page) {

        logger.info("jcl/search?sprint=" + sprint + "&adName=" + adName + "&page=" + page);

        // 從jclService的listAllJclByCondition取得資料
        List<AdJclModel> adJclModelList = jclService.listAllJclByCondition(adName, sprint);
        logger.info("Get adJclModelList");

        // 計算資料的起始與結束位置
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), adJclModelList.size());

        // 把adJclModelList轉換成Page型別
        Page<AdJclModel> allJclList = new PageImpl<AdJclModel>(adJclModelList.subList(start, end), pageable, adJclModelList.size());
        logger.info("Transfer List to Page");

        // 顯示頁面總數
        List<Integer> pageList = new ArrayList<Integer>();
        for (int i = 0; i < allJclList.getTotalPages(); i++) {
            pageList.add(i);
        }

        // 加入model
        model.addAttribute("allJclList", allJclList);
        model.addAttribute("sprint", sprint);
        model.addAttribute("adName", adName);
        model.addAttribute("page", page);
        model.addAttribute("pageList", pageList);
        logger.info("model " + model);
        // 導回jcl頁面
        return "jcl/jcl_list";
    }

    /**
     * 依adName查詢相關的JCL
     *
     * @author si1206 Sam Chen
     * @date 2021/08/04
     * @param model 傳入Thymeleaf的資料
     * @param adName adName參數的值
     * @return jcl_detail.html
     */
    @GetMapping("/searchJclDetail/{ad}")
    public String showJclDetail(Model model, @PathVariable("ad") String adName) {
        logger.info("jcl/searchJclDetail/" + adName);
        // 從jclService的listAllJclByCondition取得資料
        List<AdJclEntity> listJclByAd = jclService.findJclByAd(adName);

        // 作為頁面標題
        String ad = listJclByAd.get(0).getAd();
        String addesc = listJclByAd.get(0).getAddesc();
        model.addAttribute("ad", ad);
        model.addAttribute("addesc", addesc);
        model.addAttribute("listJclByAd", listJclByAd);
        logger.info("model " + model);

        // 導回jcl_detail頁面
        return "jcl/jcl_detail";
    }
}
