package com.csi.jcl.service;

import com.csi.jcl.dao.CodeListRepository;
import com.csi.jcl.entity.CodeListEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 實作CodeListService功能
 *
 * @author si1255   Morris Mao
 * @version 1.8
 * @date 2021/10/25
 */

@Service
public class CodeListServiceImpl implements CodeListService {

    private static final Logger logger =
            LogManager.getLogger(CodeListServiceImpl.class);

    @Autowired
    private CodeListRepository codeListRepository;


    @Override
    public List<CodeListEntity> getAllCodeList() {
        logger.info(codeListRepository.findAll());
        return codeListRepository.findAll();
    }

    @Override
    public List<CodeListEntity> findByCodeTypeIdEqualsSystemType() {
//        logger.info(codeListRepository.findByCodeTypeIdEqualsSystemType());
        return  codeListRepository.findByCodeTypeIdEqualsSystemType();
    }

}