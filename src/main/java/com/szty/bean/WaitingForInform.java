package com.szty.bean;

import com.szty.enums.InformType;
import java.util.Date;

public class WaitingForInform {
    private Integer informFlowNo;

    private String informUser;

    private InformType type;

    private String no;

    private Date createDate;

    private String memo;

    private String sno;

    public Integer getInformFlowNo() {
        return informFlowNo;
    }

    public void setInformFlowNo(Integer informFlowNo) {
        this.informFlowNo = informFlowNo;
    }

    public String getInformUser() {
        return informUser;
    }

    public void setInformUser(String informUser) {
        this.informUser = informUser == null ? null : informUser.trim();
    }

    public InformType getType() {
        return type;
    }

    public void setType(InformType type) {
        this.type = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }
}