package com.csi.jcl.dao;

import com.csi.jcl.entity.DefectListEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DefectListRepository extends PagingAndSortingRepository<DefectListEntity, String> {

    /**
     * 依條件查詢defectlist+ad_jcl+code_list列表
     *
     * @param sprint sprint參數的值
     * @param ad ad參數的值
     * @param jcl jcl參數的值
     * @param issueStatusList issueStatusList 參數的值
     * @author si1255
     * @date 2021/10/25
     */

    @Query(nativeQuery = true, value = "SELECT DISTINCT DL.TEST_TYPE,A1.AD,A1.JCL,DL.ISSUE_KEY,DL.ISSUE_TYPE,DL.ISSUE_STATUS,CL.CODE_ID,CL.CODE_DESC,CL.CODE_TYPE_ID " +
            "from DEFECT_LIST DL,AD_JCL A1,CODE_LIST CL " +
            "WHERE DL.JID=A1.jid " +
            "and DL.ISSUE_STATUS=CL.CODE_ID " +
            "and ( :ad is null OR A1.AD = :ad) " +
            "and ( :jcl is null OR A1.JCL = :jcl) " +
            "and ( :sprint is null OR A1.SPRINT = :sprint ) " +
            "and  DL.ISSUE_STATUS in :issueStatusList ")
    public List<Map<String, Object>> listAllDefect(@Param("sprint") String sprint, @Param("ad") String ad, @Param("jcl") String jcl, @Param("issueStatusList") List<String> issueStatusList);


    /**
     * 依條件查詢defectlist+ad_jcl+code_list列表
     *
     * @param sprint sprint參數的值
     * @param ad ad參數的值
     * @param jcl jcl參數的值
     * @author si1255
     * @date 2021/10/25
     */



    @Query(nativeQuery = true, value = "SELECT DISTINCT DL.TEST_TYPE,A1.AD,A1.JCL,DL.ISSUE_KEY,DL.ISSUE_TYPE,DL.ISSUE_STATUS,CL.CODE_ID,CL.CODE_DESC,CL.CODE_TYPE_ID " +
            "from DEFECT_LIST DL,AD_JCL A1,CODE_LIST CL " +
            "WHERE DL.JID=A1.jid " +
            "and DL.ISSUE_STATUS=CL.CODE_ID " +
            "and ( :ad is null OR A1.AD = :ad) " +
            "and ( :jcl is null OR A1.JCL = :jcl) " +
            "and ( :sprint is null OR A1.SPRINT = :sprint ) ")
    public List<Map<String, Object>> listAllDefect123(@Param("sprint") String sprint, @Param("ad") String ad, @Param("jcl") String jcl);

}
