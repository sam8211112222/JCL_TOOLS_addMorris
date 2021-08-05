package com.csi.jcl.service;

import com.csi.jcl.entity.AdJclEntity;
import com.csi.jcl.model.AdJclModel;

import java.util.List;

/**
 * 定義JclService介面
 *
 * @author si1206 Sam Chen
 * @date 2021/08/04
 * @version 1.8
 */
public interface JclService {

    public List<AdJclEntity> findJclByAd(String adName);

    public List<AdJclModel> listAllJclByCondition(String adName, String sprint);

}
