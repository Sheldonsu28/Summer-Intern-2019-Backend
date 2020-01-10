package com.mmm.weixin.vo;

public class ContestTeamMember {
    private Integer ctmId;

    private Integer memberId;

    private String memberName;

    private Integer ctId;

    public Integer getCtmId() {
        return ctmId;
    }

    public void setCtmId(Integer ctmId) {
        this.ctmId = ctmId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }
}