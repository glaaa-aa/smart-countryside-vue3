package com.qfy.modules.city3d.po;

public class Pipeline {
    private String pipeId;

    private String plName;

    private String plLine;

    private String plOutDiameter;

    private String plInDiameter;

    private String plDeep;

    private String plMaterial;

    private String plType;

    private String plSlope;

    private String plRoughness;

    private String plDirection;

    private String plPos;

    public String getPipeId() {
        return pipeId;
    }

    public void setPipeId(String pipeId) {
        this.pipeId = pipeId == null ? null : pipeId.trim();
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName == null ? null : plName.trim();
    }

    public String getPlLine() {
        return plLine;
    }

    public void setPlLine(String plLine) {
        this.plLine = plLine == null ? null : plLine.trim();
    }

    public String getPlOutDiameter() {
        return plOutDiameter;
    }

    public void setPlOutDiameter(String plOutDiameter) {
        this.plOutDiameter = plOutDiameter == null ? null : plOutDiameter.trim();
    }

    public String getPlInDiameter() {
        return plInDiameter;
    }

    public void setPlInDiameter(String plInDiameter) {
        this.plInDiameter = plInDiameter == null ? null : plInDiameter.trim();
    }

    public String getPlDeep() {
        return plDeep;
    }

    public void setPlDeep(String plDeep) {
        this.plDeep = plDeep == null ? null : plDeep.trim();
    }

    public String getPlMaterial() {
        return plMaterial;
    }

    public void setPlMaterial(String plMaterial) {
        this.plMaterial = plMaterial == null ? null : plMaterial.trim();
    }

    public String getPlType() {
        return plType;
    }

    public void setPlType(String plType) {
        this.plType = plType == null ? null : plType.trim();
    }

    public String getPlSlope() {
        return plSlope;
    }

    public void setPlSlope(String plSlope) {
        this.plSlope = plSlope == null ? null : plSlope.trim();
    }

    public String getPlRoughness() {
        return plRoughness;
    }

    public void setPlRoughness(String plRoughness) {
        this.plRoughness = plRoughness == null ? null : plRoughness.trim();
    }

    public String getPlDirection() {
        return plDirection;
    }

    public void setPlDirection(String plDirection) {
        this.plDirection = plDirection == null ? null : plDirection.trim();
    }

    public String getPlPos() {
        return plPos;
    }

    public void setPlPos(String plPos) {
        this.plPos = plPos == null ? null : plPos.trim();
    }
}
