package com.csi.jcl.service;

import com.csi.jcl.entity.UserInfoEntity;

/**
 * 定義UserInfoService介面
 *
 * @author si1206 Sam Chen
 * @date 2021/08/17
 * @version 1.8
 */
public interface UserInfoService {
    public UserInfoEntity findById(String userId);
    public void updateInfo(UserInfoEntity userInfoEntity);
}
