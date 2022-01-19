package com.csi.jcl.service;

import com.csi.jcl.model.DefectListAndAdJclModel;


import java.util.List;


/**
 * 定義ListAllDefectService介面
 *
 * @author si1255 Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */

public interface ListAllDefectService {

    public List<DefectListAndAdJclModel> listAllDefect(String sprint, String ad, String jcl, List<String> issueStatusList);

}
