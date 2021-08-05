package com.csi.jcl.model;

/**
 * 用NativeSql複合查詢時所需要的類別
 * 變數名稱需要對應sql上的變數名稱
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */

public class AdJclModel {

    private String sprint;

    private String ad;

    private String addesc;

    private String systemtype;

    private String systemdesc;

    private String cht;

    private Integer jclcout;

    private String jid;

    private String cht_ap;

    private String cht_dc;

    public AdJclModel() {
    }

    public AdJclModel(String sprint, String ad, String addesc, String systemtype, String systemdesc, String cht, Integer jclcout, String jid, String cht_ap, String cht_dc) {
        this.sprint = sprint;
        this.ad = ad;
        this.addesc = addesc;
        this.systemtype = systemtype;
        this.systemdesc = systemdesc;
        this.cht = cht;
        this.jclcout = jclcout;
        this.jid = jid;
        this.cht_ap = cht_ap;
        this.cht_dc = cht_dc;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAddesc() {
        return addesc;
    }

    public void setAddesc(String addesc) {
        this.addesc = addesc;
    }

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype;
    }

    public String getSystemdesc() {
        return systemdesc;
    }

    public void setSystemdesc(String systemdesc) {
        this.systemdesc = systemdesc;
    }

    public String getCht() {
        return cht;
    }

    public void setCht(String cht) {
        this.cht = cht;
    }

    public Integer getJclcout() {
        return jclcout;
    }

    public void setJclcout(Integer jclcout) {
        this.jclcout = jclcout;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getCht_ap() {
        return cht_ap;
    }

    public void setCht_ap(String cht_ap) {
        this.cht_ap = cht_ap;
    }

    public String getCht_dc() {
        return cht_dc;
    }

    public void setCht_dc(String cht_dc) {
        this.cht_dc = cht_dc;
    }

    @Override
    public String toString() {
        return "AdJclModel{" +
                "sprint='" + sprint + '\'' +
                ", ad='" + ad + '\'' +
                ", addesc='" + addesc + '\'' +
                ", systemtype='" + systemtype + '\'' +
                ", systemdesc='" + systemdesc + '\'' +
                ", cht='" + cht + '\'' +
                ", jclcout=" + jclcout +
                ", jid='" + jid + '\'' +
                ", cht_ap='" + cht_ap + '\'' +
                ", cht_dc='" + cht_dc + '\'' +
                '}';
    }
}
