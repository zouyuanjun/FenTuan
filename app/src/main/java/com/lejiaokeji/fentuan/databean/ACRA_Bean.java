package com.lejiaokeji.fentuan.databean;

public class ACRA_Bean {
    /**
     * APP版本名字
     */
    String APP_VERSION_NAME;
    /**
     * 包名
     */
    String PACKAGE_NAME;
    /**
     * 手机型号
     */
    String PHONE_MODEL;
    /**
     * Android版本
     */
    String ANDROID_VERSION;
    /**
     * 崩溃内容
     */
    String STACK_TRACE;
    /**
     * 崩溃时间
     */
    String USER_CRASH_DATE;

    String APP_VERSION_CODE;

    String USER_APP_START_DATE;
    String USER_COMMENT;
    public ACRA_Bean(){

    }
    public String getAPP_VERSION_NAME() {
        return APP_VERSION_NAME;
    }

    public void setAPP_VERSION_NAME(String APP_VERSION_NAME) {
        this.APP_VERSION_NAME = APP_VERSION_NAME;
    }

    public String getPACKAGE_NAME() {
        return PACKAGE_NAME;
    }

    public void setPACKAGE_NAME(String PACKAGE_NAME) {
        this.PACKAGE_NAME = PACKAGE_NAME;
    }

    public String getPHONE_MODEL() {
        return PHONE_MODEL;
    }

    public void setPHONE_MODEL(String PHONE_MODEL) {
        this.PHONE_MODEL = PHONE_MODEL;
    }

    public String getANDROID_VERSION() {
        return ANDROID_VERSION;
    }

    public void setANDROID_VERSION(String ANDROID_VERSION) {
        this.ANDROID_VERSION = ANDROID_VERSION;
    }

    public String getSTACK_TRACE() {
        return STACK_TRACE;
    }

    public void setSTACK_TRACE(String STACK_TRACE) {
        this.STACK_TRACE = STACK_TRACE;
    }

    public String getUSER_CRASH_DATE() {
        return USER_CRASH_DATE;
    }

    public void setUSER_CRASH_DATE(String USER_CRASH_DATE) {
        this.USER_CRASH_DATE = USER_CRASH_DATE;
    }

    public String getAPP_VERSION_CODE() {
        return APP_VERSION_CODE;
    }

    public void setAPP_VERSION_CODE(String APP_VERSION_CODE) {
        this.APP_VERSION_CODE = APP_VERSION_CODE;
    }

    public String getUSER_APP_START_DATE() {
        return USER_APP_START_DATE;
    }

    public void setUSER_APP_START_DATE(String USER_APP_START_DATE) {
        this.USER_APP_START_DATE = USER_APP_START_DATE;
    }

    public String getUSER_COMMENT() {
        return USER_COMMENT;
    }

    public void setUSER_COMMENT(String USER_COMMENT) {
        this.USER_COMMENT = USER_COMMENT;
    }
}
