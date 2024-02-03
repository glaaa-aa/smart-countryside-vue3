package com.qfy.modules.city3d.po;

public class PeopleKnife {
    private String knifeId;

    private String ppId;

    private String knifeLenth;

    private String qrCode;

    private String fixedSituation;

    private String imgUrl;

    private String remark;

    private String knifeType;

    private PeopleBase peopleBase;

    public PeopleBase getPeopleBase() {
		return peopleBase;
	}

	public void setPeopleBase(PeopleBase peopleBase) {
		this.peopleBase = peopleBase;
	}

	public String getKnifeId() {
        return knifeId;
    }

    public void setKnifeId(String knifeId) {
        this.knifeId = knifeId == null ? null : knifeId.trim();
    }

    public String getPpId() {
        return ppId;
    }

    public void setPpId(String ppId) {
        this.ppId = ppId == null ? null : ppId.trim();
    }

    public String getKnifeLenth() {
        return knifeLenth;
    }

    public void setKnifeLenth(String knifeLenth) {
        this.knifeLenth = knifeLenth == null ? null : knifeLenth.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public String getFixedSituation() {
        return fixedSituation;
    }

    public void setFixedSituation(String fixedSituation) {
        this.fixedSituation = fixedSituation == null ? null : fixedSituation.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getKnifeType() {
        return knifeType;
    }

    public void setKnifeType(String knifeType) {
        this.knifeType = knifeType == null ? null : knifeType.trim();
    }
}
