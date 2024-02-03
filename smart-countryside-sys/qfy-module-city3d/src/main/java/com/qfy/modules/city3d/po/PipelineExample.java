package com.qfy.modules.city3d.po;

import java.util.ArrayList;
import java.util.List;

public class PipelineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PipelineExample() {
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

        public Criteria andPipeIdIsNull() {
            addCriterion("pipe_id is null");
            return (Criteria) this;
        }

        public Criteria andPipeIdIsNotNull() {
            addCriterion("pipe_id is not null");
            return (Criteria) this;
        }

        public Criteria andPipeIdEqualTo(String value) {
            addCriterion("pipe_id =", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdNotEqualTo(String value) {
            addCriterion("pipe_id <>", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdGreaterThan(String value) {
            addCriterion("pipe_id >", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdGreaterThanOrEqualTo(String value) {
            addCriterion("pipe_id >=", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdLessThan(String value) {
            addCriterion("pipe_id <", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdLessThanOrEqualTo(String value) {
            addCriterion("pipe_id <=", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdLike(String value) {
            addCriterion("pipe_id like", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdNotLike(String value) {
            addCriterion("pipe_id not like", value, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdIn(List<String> values) {
            addCriterion("pipe_id in", values, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdNotIn(List<String> values) {
            addCriterion("pipe_id not in", values, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdBetween(String value1, String value2) {
            addCriterion("pipe_id between", value1, value2, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPipeIdNotBetween(String value1, String value2) {
            addCriterion("pipe_id not between", value1, value2, "pipeId");
            return (Criteria) this;
        }

        public Criteria andPlNameIsNull() {
            addCriterion("pl_name is null");
            return (Criteria) this;
        }

        public Criteria andPlNameIsNotNull() {
            addCriterion("pl_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlNameEqualTo(String value) {
            addCriterion("pl_name =", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameNotEqualTo(String value) {
            addCriterion("pl_name <>", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameGreaterThan(String value) {
            addCriterion("pl_name >", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameGreaterThanOrEqualTo(String value) {
            addCriterion("pl_name >=", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameLessThan(String value) {
            addCriterion("pl_name <", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameLessThanOrEqualTo(String value) {
            addCriterion("pl_name <=", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameLike(String value) {
            addCriterion("pl_name like", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameNotLike(String value) {
            addCriterion("pl_name not like", value, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameIn(List<String> values) {
            addCriterion("pl_name in", values, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameNotIn(List<String> values) {
            addCriterion("pl_name not in", values, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameBetween(String value1, String value2) {
            addCriterion("pl_name between", value1, value2, "plName");
            return (Criteria) this;
        }

        public Criteria andPlNameNotBetween(String value1, String value2) {
            addCriterion("pl_name not between", value1, value2, "plName");
            return (Criteria) this;
        }

        public Criteria andPlLineIsNull() {
            addCriterion("pl_line is null");
            return (Criteria) this;
        }

        public Criteria andPlLineIsNotNull() {
            addCriterion("pl_line is not null");
            return (Criteria) this;
        }

        public Criteria andPlLineEqualTo(String value) {
            addCriterion("pl_line =", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineNotEqualTo(String value) {
            addCriterion("pl_line <>", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineGreaterThan(String value) {
            addCriterion("pl_line >", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineGreaterThanOrEqualTo(String value) {
            addCriterion("pl_line >=", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineLessThan(String value) {
            addCriterion("pl_line <", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineLessThanOrEqualTo(String value) {
            addCriterion("pl_line <=", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineLike(String value) {
            addCriterion("pl_line like", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineNotLike(String value) {
            addCriterion("pl_line not like", value, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineIn(List<String> values) {
            addCriterion("pl_line in", values, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineNotIn(List<String> values) {
            addCriterion("pl_line not in", values, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineBetween(String value1, String value2) {
            addCriterion("pl_line between", value1, value2, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlLineNotBetween(String value1, String value2) {
            addCriterion("pl_line not between", value1, value2, "plLine");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterIsNull() {
            addCriterion("pl_out_diameter is null");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterIsNotNull() {
            addCriterion("pl_out_diameter is not null");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterEqualTo(String value) {
            addCriterion("pl_out_diameter =", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterNotEqualTo(String value) {
            addCriterion("pl_out_diameter <>", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterGreaterThan(String value) {
            addCriterion("pl_out_diameter >", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterGreaterThanOrEqualTo(String value) {
            addCriterion("pl_out_diameter >=", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterLessThan(String value) {
            addCriterion("pl_out_diameter <", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterLessThanOrEqualTo(String value) {
            addCriterion("pl_out_diameter <=", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterLike(String value) {
            addCriterion("pl_out_diameter like", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterNotLike(String value) {
            addCriterion("pl_out_diameter not like", value, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterIn(List<String> values) {
            addCriterion("pl_out_diameter in", values, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterNotIn(List<String> values) {
            addCriterion("pl_out_diameter not in", values, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterBetween(String value1, String value2) {
            addCriterion("pl_out_diameter between", value1, value2, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlOutDiameterNotBetween(String value1, String value2) {
            addCriterion("pl_out_diameter not between", value1, value2, "plOutDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterIsNull() {
            addCriterion("pl_in_diameter is null");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterIsNotNull() {
            addCriterion("pl_in_diameter is not null");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterEqualTo(String value) {
            addCriterion("pl_in_diameter =", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterNotEqualTo(String value) {
            addCriterion("pl_in_diameter <>", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterGreaterThan(String value) {
            addCriterion("pl_in_diameter >", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterGreaterThanOrEqualTo(String value) {
            addCriterion("pl_in_diameter >=", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterLessThan(String value) {
            addCriterion("pl_in_diameter <", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterLessThanOrEqualTo(String value) {
            addCriterion("pl_in_diameter <=", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterLike(String value) {
            addCriterion("pl_in_diameter like", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterNotLike(String value) {
            addCriterion("pl_in_diameter not like", value, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterIn(List<String> values) {
            addCriterion("pl_in_diameter in", values, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterNotIn(List<String> values) {
            addCriterion("pl_in_diameter not in", values, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterBetween(String value1, String value2) {
            addCriterion("pl_in_diameter between", value1, value2, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlInDiameterNotBetween(String value1, String value2) {
            addCriterion("pl_in_diameter not between", value1, value2, "plInDiameter");
            return (Criteria) this;
        }

        public Criteria andPlDeepIsNull() {
            addCriterion("pl_deep is null");
            return (Criteria) this;
        }

        public Criteria andPlDeepIsNotNull() {
            addCriterion("pl_deep is not null");
            return (Criteria) this;
        }

        public Criteria andPlDeepEqualTo(String value) {
            addCriterion("pl_deep =", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepNotEqualTo(String value) {
            addCriterion("pl_deep <>", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepGreaterThan(String value) {
            addCriterion("pl_deep >", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepGreaterThanOrEqualTo(String value) {
            addCriterion("pl_deep >=", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepLessThan(String value) {
            addCriterion("pl_deep <", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepLessThanOrEqualTo(String value) {
            addCriterion("pl_deep <=", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepLike(String value) {
            addCriterion("pl_deep like", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepNotLike(String value) {
            addCriterion("pl_deep not like", value, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepIn(List<String> values) {
            addCriterion("pl_deep in", values, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepNotIn(List<String> values) {
            addCriterion("pl_deep not in", values, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepBetween(String value1, String value2) {
            addCriterion("pl_deep between", value1, value2, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlDeepNotBetween(String value1, String value2) {
            addCriterion("pl_deep not between", value1, value2, "plDeep");
            return (Criteria) this;
        }

        public Criteria andPlMaterialIsNull() {
            addCriterion("pl_material is null");
            return (Criteria) this;
        }

        public Criteria andPlMaterialIsNotNull() {
            addCriterion("pl_material is not null");
            return (Criteria) this;
        }

        public Criteria andPlMaterialEqualTo(String value) {
            addCriterion("pl_material =", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialNotEqualTo(String value) {
            addCriterion("pl_material <>", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialGreaterThan(String value) {
            addCriterion("pl_material >", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialGreaterThanOrEqualTo(String value) {
            addCriterion("pl_material >=", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialLessThan(String value) {
            addCriterion("pl_material <", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialLessThanOrEqualTo(String value) {
            addCriterion("pl_material <=", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialLike(String value) {
            addCriterion("pl_material like", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialNotLike(String value) {
            addCriterion("pl_material not like", value, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialIn(List<String> values) {
            addCriterion("pl_material in", values, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialNotIn(List<String> values) {
            addCriterion("pl_material not in", values, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialBetween(String value1, String value2) {
            addCriterion("pl_material between", value1, value2, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlMaterialNotBetween(String value1, String value2) {
            addCriterion("pl_material not between", value1, value2, "plMaterial");
            return (Criteria) this;
        }

        public Criteria andPlTypeIsNull() {
            addCriterion("pl_type is null");
            return (Criteria) this;
        }

        public Criteria andPlTypeIsNotNull() {
            addCriterion("pl_type is not null");
            return (Criteria) this;
        }

        public Criteria andPlTypeEqualTo(String value) {
            addCriterion("pl_type =", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeNotEqualTo(String value) {
            addCriterion("pl_type <>", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeGreaterThan(String value) {
            addCriterion("pl_type >", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pl_type >=", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeLessThan(String value) {
            addCriterion("pl_type <", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeLessThanOrEqualTo(String value) {
            addCriterion("pl_type <=", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeLike(String value) {
            addCriterion("pl_type like", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeNotLike(String value) {
            addCriterion("pl_type not like", value, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeIn(List<String> values) {
            addCriterion("pl_type in", values, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeNotIn(List<String> values) {
            addCriterion("pl_type not in", values, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeBetween(String value1, String value2) {
            addCriterion("pl_type between", value1, value2, "plType");
            return (Criteria) this;
        }

        public Criteria andPlTypeNotBetween(String value1, String value2) {
            addCriterion("pl_type not between", value1, value2, "plType");
            return (Criteria) this;
        }

        public Criteria andPlSlopeIsNull() {
            addCriterion("pl_slope is null");
            return (Criteria) this;
        }

        public Criteria andPlSlopeIsNotNull() {
            addCriterion("pl_slope is not null");
            return (Criteria) this;
        }

        public Criteria andPlSlopeEqualTo(String value) {
            addCriterion("pl_slope =", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeNotEqualTo(String value) {
            addCriterion("pl_slope <>", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeGreaterThan(String value) {
            addCriterion("pl_slope >", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeGreaterThanOrEqualTo(String value) {
            addCriterion("pl_slope >=", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeLessThan(String value) {
            addCriterion("pl_slope <", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeLessThanOrEqualTo(String value) {
            addCriterion("pl_slope <=", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeLike(String value) {
            addCriterion("pl_slope like", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeNotLike(String value) {
            addCriterion("pl_slope not like", value, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeIn(List<String> values) {
            addCriterion("pl_slope in", values, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeNotIn(List<String> values) {
            addCriterion("pl_slope not in", values, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeBetween(String value1, String value2) {
            addCriterion("pl_slope between", value1, value2, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlSlopeNotBetween(String value1, String value2) {
            addCriterion("pl_slope not between", value1, value2, "plSlope");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessIsNull() {
            addCriterion("pl_roughness is null");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessIsNotNull() {
            addCriterion("pl_roughness is not null");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessEqualTo(String value) {
            addCriterion("pl_roughness =", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessNotEqualTo(String value) {
            addCriterion("pl_roughness <>", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessGreaterThan(String value) {
            addCriterion("pl_roughness >", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessGreaterThanOrEqualTo(String value) {
            addCriterion("pl_roughness >=", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessLessThan(String value) {
            addCriterion("pl_roughness <", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessLessThanOrEqualTo(String value) {
            addCriterion("pl_roughness <=", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessLike(String value) {
            addCriterion("pl_roughness like", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessNotLike(String value) {
            addCriterion("pl_roughness not like", value, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessIn(List<String> values) {
            addCriterion("pl_roughness in", values, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessNotIn(List<String> values) {
            addCriterion("pl_roughness not in", values, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessBetween(String value1, String value2) {
            addCriterion("pl_roughness between", value1, value2, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlRoughnessNotBetween(String value1, String value2) {
            addCriterion("pl_roughness not between", value1, value2, "plRoughness");
            return (Criteria) this;
        }

        public Criteria andPlDirectionIsNull() {
            addCriterion("pl_direction is null");
            return (Criteria) this;
        }

        public Criteria andPlDirectionIsNotNull() {
            addCriterion("pl_direction is not null");
            return (Criteria) this;
        }

        public Criteria andPlDirectionEqualTo(String value) {
            addCriterion("pl_direction =", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionNotEqualTo(String value) {
            addCriterion("pl_direction <>", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionGreaterThan(String value) {
            addCriterion("pl_direction >", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("pl_direction >=", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionLessThan(String value) {
            addCriterion("pl_direction <", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionLessThanOrEqualTo(String value) {
            addCriterion("pl_direction <=", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionLike(String value) {
            addCriterion("pl_direction like", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionNotLike(String value) {
            addCriterion("pl_direction not like", value, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionIn(List<String> values) {
            addCriterion("pl_direction in", values, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionNotIn(List<String> values) {
            addCriterion("pl_direction not in", values, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionBetween(String value1, String value2) {
            addCriterion("pl_direction between", value1, value2, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlDirectionNotBetween(String value1, String value2) {
            addCriterion("pl_direction not between", value1, value2, "plDirection");
            return (Criteria) this;
        }

        public Criteria andPlPosIsNull() {
            addCriterion("pl_pos is null");
            return (Criteria) this;
        }

        public Criteria andPlPosIsNotNull() {
            addCriterion("pl_pos is not null");
            return (Criteria) this;
        }

        public Criteria andPlPosEqualTo(String value) {
            addCriterion("pl_pos =", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosNotEqualTo(String value) {
            addCriterion("pl_pos <>", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosGreaterThan(String value) {
            addCriterion("pl_pos >", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosGreaterThanOrEqualTo(String value) {
            addCriterion("pl_pos >=", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosLessThan(String value) {
            addCriterion("pl_pos <", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosLessThanOrEqualTo(String value) {
            addCriterion("pl_pos <=", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosLike(String value) {
            addCriterion("pl_pos like", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosNotLike(String value) {
            addCriterion("pl_pos not like", value, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosIn(List<String> values) {
            addCriterion("pl_pos in", values, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosNotIn(List<String> values) {
            addCriterion("pl_pos not in", values, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosBetween(String value1, String value2) {
            addCriterion("pl_pos between", value1, value2, "plPos");
            return (Criteria) this;
        }

        public Criteria andPlPosNotBetween(String value1, String value2) {
            addCriterion("pl_pos not between", value1, value2, "plPos");
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
