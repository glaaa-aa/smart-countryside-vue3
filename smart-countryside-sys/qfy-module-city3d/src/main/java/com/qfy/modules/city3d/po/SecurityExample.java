package com.qfy.modules.city3d.po;

import java.util.ArrayList;
import java.util.List;

public class SecurityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SecurityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSecurityIdIsNull() {
            addCriterion("security_id is null");
            return (Criteria) this;
        }

        public Criteria andSecurityIdIsNotNull() {
            addCriterion("security_id is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityIdEqualTo(String value) {
            addCriterion("security_id =", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdNotEqualTo(String value) {
            addCriterion("security_id <>", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdGreaterThan(String value) {
            addCriterion("security_id >", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdGreaterThanOrEqualTo(String value) {
            addCriterion("security_id >=", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdLessThan(String value) {
            addCriterion("security_id <", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdLessThanOrEqualTo(String value) {
            addCriterion("security_id <=", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdLike(String value) {
            addCriterion("security_id like", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdNotLike(String value) {
            addCriterion("security_id not like", value, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdIn(List<String> values) {
            addCriterion("security_id in", values, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdNotIn(List<String> values) {
            addCriterion("security_id not in", values, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdBetween(String value1, String value2) {
            addCriterion("security_id between", value1, value2, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityIdNotBetween(String value1, String value2) {
            addCriterion("security_id not between", value1, value2, "securityId");
            return (Criteria) this;
        }

        public Criteria andSecurityNameIsNull() {
            addCriterion("security_name is null");
            return (Criteria) this;
        }

        public Criteria andSecurityNameIsNotNull() {
            addCriterion("security_name is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityNameEqualTo(String value) {
            addCriterion("security_name =", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotEqualTo(String value) {
            addCriterion("security_name <>", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameGreaterThan(String value) {
            addCriterion("security_name >", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameGreaterThanOrEqualTo(String value) {
            addCriterion("security_name >=", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameLessThan(String value) {
            addCriterion("security_name <", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameLessThanOrEqualTo(String value) {
            addCriterion("security_name <=", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameLike(String value) {
            addCriterion("security_name like", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotLike(String value) {
            addCriterion("security_name not like", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameIn(List<String> values) {
            addCriterion("security_name in", values, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotIn(List<String> values) {
            addCriterion("security_name not in", values, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameBetween(String value1, String value2) {
            addCriterion("security_name between", value1, value2, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotBetween(String value1, String value2) {
            addCriterion("security_name not between", value1, value2, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeIsNull() {
            addCriterion("security_type is null");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeIsNotNull() {
            addCriterion("security_type is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeEqualTo(String value) {
            addCriterion("security_type =", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeNotEqualTo(String value) {
            addCriterion("security_type <>", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeGreaterThan(String value) {
            addCriterion("security_type >", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeGreaterThanOrEqualTo(String value) {
            addCriterion("security_type >=", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeLessThan(String value) {
            addCriterion("security_type <", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeLessThanOrEqualTo(String value) {
            addCriterion("security_type <=", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeLike(String value) {
            addCriterion("security_type like", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeNotLike(String value) {
            addCriterion("security_type not like", value, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeIn(List<String> values) {
            addCriterion("security_type in", values, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeNotIn(List<String> values) {
            addCriterion("security_type not in", values, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeBetween(String value1, String value2) {
            addCriterion("security_type between", value1, value2, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityTypeNotBetween(String value1, String value2) {
            addCriterion("security_type not between", value1, value2, "securityType");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdIsNull() {
            addCriterion("security_police_id is null");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdIsNotNull() {
            addCriterion("security_police_id is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdEqualTo(String value) {
            addCriterion("security_police_id =", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdNotEqualTo(String value) {
            addCriterion("security_police_id <>", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdGreaterThan(String value) {
            addCriterion("security_police_id >", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdGreaterThanOrEqualTo(String value) {
            addCriterion("security_police_id >=", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdLessThan(String value) {
            addCriterion("security_police_id <", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdLessThanOrEqualTo(String value) {
            addCriterion("security_police_id <=", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdLike(String value) {
            addCriterion("security_police_id like", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdNotLike(String value) {
            addCriterion("security_police_id not like", value, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdIn(List<String> values) {
            addCriterion("security_police_id in", values, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdNotIn(List<String> values) {
            addCriterion("security_police_id not in", values, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdBetween(String value1, String value2) {
            addCriterion("security_police_id between", value1, value2, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityPoliceIdNotBetween(String value1, String value2) {
            addCriterion("security_police_id not between", value1, value2, "securityPoliceId");
            return (Criteria) this;
        }

        public Criteria andSecurityTelIsNull() {
            addCriterion("security_tel is null");
            return (Criteria) this;
        }

        public Criteria andSecurityTelIsNotNull() {
            addCriterion("security_tel is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityTelEqualTo(String value) {
            addCriterion("security_tel =", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelNotEqualTo(String value) {
            addCriterion("security_tel <>", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelGreaterThan(String value) {
            addCriterion("security_tel >", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelGreaterThanOrEqualTo(String value) {
            addCriterion("security_tel >=", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelLessThan(String value) {
            addCriterion("security_tel <", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelLessThanOrEqualTo(String value) {
            addCriterion("security_tel <=", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelLike(String value) {
            addCriterion("security_tel like", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelNotLike(String value) {
            addCriterion("security_tel not like", value, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelIn(List<String> values) {
            addCriterion("security_tel in", values, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelNotIn(List<String> values) {
            addCriterion("security_tel not in", values, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelBetween(String value1, String value2) {
            addCriterion("security_tel between", value1, value2, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityTelNotBetween(String value1, String value2) {
            addCriterion("security_tel not between", value1, value2, "securityTel");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeIsNull() {
            addCriterion("security_describe is null");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeIsNotNull() {
            addCriterion("security_describe is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeEqualTo(String value) {
            addCriterion("security_describe =", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeNotEqualTo(String value) {
            addCriterion("security_describe <>", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeGreaterThan(String value) {
            addCriterion("security_describe >", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("security_describe >=", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeLessThan(String value) {
            addCriterion("security_describe <", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeLessThanOrEqualTo(String value) {
            addCriterion("security_describe <=", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeLike(String value) {
            addCriterion("security_describe like", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeNotLike(String value) {
            addCriterion("security_describe not like", value, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeIn(List<String> values) {
            addCriterion("security_describe in", values, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeNotIn(List<String> values) {
            addCriterion("security_describe not in", values, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeBetween(String value1, String value2) {
            addCriterion("security_describe between", value1, value2, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityDescribeNotBetween(String value1, String value2) {
            addCriterion("security_describe not between", value1, value2, "securityDescribe");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiIsNull() {
            addCriterion("security_poi is null");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiIsNotNull() {
            addCriterion("security_poi is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiEqualTo(String value) {
            addCriterion("security_poi =", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiNotEqualTo(String value) {
            addCriterion("security_poi <>", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiGreaterThan(String value) {
            addCriterion("security_poi >", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiGreaterThanOrEqualTo(String value) {
            addCriterion("security_poi >=", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiLessThan(String value) {
            addCriterion("security_poi <", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiLessThanOrEqualTo(String value) {
            addCriterion("security_poi <=", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiLike(String value) {
            addCriterion("security_poi like", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiNotLike(String value) {
            addCriterion("security_poi not like", value, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiIn(List<String> values) {
            addCriterion("security_poi in", values, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiNotIn(List<String> values) {
            addCriterion("security_poi not in", values, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiBetween(String value1, String value2) {
            addCriterion("security_poi between", value1, value2, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityPoiNotBetween(String value1, String value2) {
            addCriterion("security_poi not between", value1, value2, "securityPoi");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterIsNull() {
            addCriterion("security_center is null");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterIsNotNull() {
            addCriterion("security_center is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterEqualTo(String value) {
            addCriterion("security_center =", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterNotEqualTo(String value) {
            addCriterion("security_center <>", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterGreaterThan(String value) {
            addCriterion("security_center >", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterGreaterThanOrEqualTo(String value) {
            addCriterion("security_center >=", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterLessThan(String value) {
            addCriterion("security_center <", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterLessThanOrEqualTo(String value) {
            addCriterion("security_center <=", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterLike(String value) {
            addCriterion("security_center like", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterNotLike(String value) {
            addCriterion("security_center not like", value, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterIn(List<String> values) {
            addCriterion("security_center in", values, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterNotIn(List<String> values) {
            addCriterion("security_center not in", values, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterBetween(String value1, String value2) {
            addCriterion("security_center between", value1, value2, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityCenterNotBetween(String value1, String value2) {
            addCriterion("security_center not between", value1, value2, "securityCenter");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdIsNull() {
            addCriterion("security_video_id is null");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdIsNotNull() {
            addCriterion("security_video_id is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdEqualTo(String value) {
            addCriterion("security_video_id =", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdNotEqualTo(String value) {
            addCriterion("security_video_id <>", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdGreaterThan(String value) {
            addCriterion("security_video_id >", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdGreaterThanOrEqualTo(String value) {
            addCriterion("security_video_id >=", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdLessThan(String value) {
            addCriterion("security_video_id <", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdLessThanOrEqualTo(String value) {
            addCriterion("security_video_id <=", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdLike(String value) {
            addCriterion("security_video_id like", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdNotLike(String value) {
            addCriterion("security_video_id not like", value, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdIn(List<String> values) {
            addCriterion("security_video_id in", values, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdNotIn(List<String> values) {
            addCriterion("security_video_id not in", values, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdBetween(String value1, String value2) {
            addCriterion("security_video_id between", value1, value2, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andSecurityVideoIdNotBetween(String value1, String value2) {
            addCriterion("security_video_id not between", value1, value2, "securityVideoId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNull() {
            addCriterion("equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNotNull() {
            addCriterion("equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdEqualTo(String value) {
            addCriterion("equipment_id =", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotEqualTo(String value) {
            addCriterion("equipment_id <>", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThan(String value) {
            addCriterion("equipment_id >", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_id >=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThan(String value) {
            addCriterion("equipment_id <", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThanOrEqualTo(String value) {
            addCriterion("equipment_id <=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLike(String value) {
            addCriterion("equipment_id like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotLike(String value) {
            addCriterion("equipment_id not like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIn(List<String> values) {
            addCriterion("equipment_id in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotIn(List<String> values) {
            addCriterion("equipment_id not in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdBetween(String value1, String value2) {
            addCriterion("equipment_id between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotBetween(String value1, String value2) {
            addCriterion("equipment_id not between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityIsNull() {
            addCriterion("security_community is null");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityIsNotNull() {
            addCriterion("security_community is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityEqualTo(String value) {
            addCriterion("security_community =", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityNotEqualTo(String value) {
            addCriterion("security_community <>", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityGreaterThan(String value) {
            addCriterion("security_community >", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityGreaterThanOrEqualTo(String value) {
            addCriterion("security_community >=", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityLessThan(String value) {
            addCriterion("security_community <", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityLessThanOrEqualTo(String value) {
            addCriterion("security_community <=", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityLike(String value) {
            addCriterion("security_community like", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityNotLike(String value) {
            addCriterion("security_community not like", value, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityIn(List<String> values) {
            addCriterion("security_community in", values, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityNotIn(List<String> values) {
            addCriterion("security_community not in", values, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityBetween(String value1, String value2) {
            addCriterion("security_community between", value1, value2, "securityCommunity");
            return (Criteria) this;
        }

        public Criteria andSecurityCommunityNotBetween(String value1, String value2) {
            addCriterion("security_community not between", value1, value2, "securityCommunity");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
