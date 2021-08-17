package com.csi.jcl.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CheckCodeEntity implements Serializable {

    // 驗證碼字元
    private String code;

    // 過期時間
    private LocalDateTime expireTime;

    /**
     * @param code 驗證碼字元
     * @param expireTime 過期時間，單位秒
     */
    public CheckCodeEntity(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public CheckCodeEntity(String code) {
        // 默認驗證碼 60 秒後過期
        this(code, 60);
    }

    // 是否過期
    public boolean isExpried() {
        return this.expireTime.isBefore(LocalDateTime.now());
    }

    public String getCode() {
        return this.code;
    }
}
