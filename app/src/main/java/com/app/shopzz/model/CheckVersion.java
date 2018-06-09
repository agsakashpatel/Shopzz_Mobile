package com.app.shopzz.model;

/**
 * Created by AGS on 04-06-2018.
 */

public class CheckVersion {
    private String appUrl;
    private String appUpdateMsg;
    private String appMaintenanceMsg;
    private int isUpdateType;

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppUpdateMsg() {
        return appUpdateMsg;
    }

    public void setAppUpdateMsg(String appUpdateMsg) {
        this.appUpdateMsg = appUpdateMsg;
    }

    public String getAppMaintenanceMsg() {
        return appMaintenanceMsg;
    }

    public void setAppMaintenanceMsg(String appMaintenanceMsg) {
        this.appMaintenanceMsg = appMaintenanceMsg;
    }

    public int getIsUpdateType() {
        return isUpdateType;
    }

    public void setIsUpdateType(int isUpdateType) {
        this.isUpdateType = isUpdateType;
    }
}