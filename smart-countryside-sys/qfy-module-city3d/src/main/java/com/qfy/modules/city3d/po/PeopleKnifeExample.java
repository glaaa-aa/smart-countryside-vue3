package com.qfy.modules.city3d.po;

import java.util.ArrayList;
import java.util.List;

public class PeopleKnifeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PeopleKnifeExample() {
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

        public Criteria andKnifeIdIsNull() {
            addCriterion("knife_id is null");
            return (Criteria) this;
        }

        public Criteria andKnifeIdIsNotNull() {
            addCriterion("knife_id is not null");
            return (Criteria) this;
        }

        public Criteria andKnifeIdEqualTo(String value) {
            addCriterion("knife_id =", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdNotEqualTo(String value) {
            addCriterion("knife_id <>", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdGreaterThan(String value) {
            addCriterion("knife_id >", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdGreaterThanOrEqualTo(String value) {
            addCriterion("knife_id >=", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdLessThan(String value) {
            addCriterion("knife_id <", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdLessThanOrEqualTo(String value) {
            addCriterion("knife_id <=", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdLike(String value) {
            addCriterion("knife_id like", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdNotLike(String value) {
            addCriterion("knife_id not like", value, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdIn(List<String> values) {
            addCriterion("knife_id in", values, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdNotIn(List<String> values) {
            addCriterion("knife_id not in", values, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdBetween(String value1, String value2) {
            addCriterion("knife_id between", value1, value2, "knifeId");
            return (Criteria) this;
        }

        public Criteria andKnifeIdNotBetween(String value1, String value2) {
            addCriterion("knife_id not between", value1, value2, "knifeId");
            return (Criteria) this;
        }

        public Criteria andPpIdIsNull() {
            addCriterion("pp_id is null");
            return (Criteria) this;
        }

        public Criteria andPpIdIsNotNull() {
            addCriterion("pp_id is not null");
            return (Criteria) this;
        }

        public Criteria andPpIdEqualTo(String value) {
            addCriterion("pp_id =", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdNotEqualTo(String value) {
            addCriterion("pp_id <>", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdGreaterThan(String value) {
            addCriterion("pp_id >", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdGreaterThanOrEqualTo(String value) {
            addCriterion("pp_id >=", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdLessThan(String value) {
            addCriterion("pp_id <", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdLessThanOrEqualTo(String value) {
            addCriterion("pp_id <=", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdLike(String value) {
            addCriterion("pp_id like", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdNotLike(String value) {
            addCriterion("pp_id not like", value, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdIn(List<String> values) {
            addCriterion("pp_id in", values, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdNotIn(List<String> values) {
            addCriterion("pp_id not in", values, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdBetween(String value1, String value2) {
            addCriterion("pp_id between", value1, value2, "ppId");
            return (Criteria) this;
        }

        public Criteria andPpIdNotBetween(String value1, String value2) {
            addCriterion("pp_id not between", value1, value2, "ppId");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthIsNull() {
            addCriterion("knife_lenth is null");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthIsNotNull() {
            addCriterion("knife_lenth is not null");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthEqualTo(String value) {
            addCriterion("knife_lenth =", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthNotEqualTo(String value) {
            addCriterion("knife_lenth <>", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthGreaterThan(String value) {
            addCriterion("knife_lenth >", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthGreaterThanOrEqualTo(String value) {
            addCriterion("knife_lenth >=", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthLessThan(String value) {
            addCriterion("knife_lenth <", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthLessThanOrEqualTo(String value) {
            addCriterion("knife_lenth <=", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthLike(String value) {
            addCriterion("knife_lenth like", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthNotLike(String value) {
            addCriterion("knife_lenth not like", value, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthIn(List<String> values) {
            addCriterion("knife_lenth in", values, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthNotIn(List<String> values) {
            addCriterion("knife_lenth not in", values, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthBetween(String value1, String value2) {
            addCriterion("knife_lenth between", value1, value2, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andKnifeLenthNotBetween(String value1, String value2) {
            addCriterion("knife_lenth not between", value1, value2, "knifeLenth");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNull() {
            addCriterion("qr_code is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNotNull() {
            addCriterion("qr_code is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeEqualTo(String value) {
            addCriterion("qr_code =", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotEqualTo(String value) {
            addCriterion("qr_code <>", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThan(String value) {
            addCriterion("qr_code >", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("qr_code >=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThan(String value) {
            addCriterion("qr_code <", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThanOrEqualTo(String value) {
            addCriterion("qr_code <=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLike(String value) {
            addCriterion("qr_code like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotLike(String value) {
            addCriterion("qr_code not like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeIn(List<String> values) {
            addCriterion("qr_code in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotIn(List<String> values) {
            addCriterion("qr_code not in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeBetween(String value1, String value2) {
            addCriterion("qr_code between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotBetween(String value1, String value2) {
            addCriterion("qr_code not between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andFixedSituationIsNull() {
            addCriterion("fixed_situation is null");
            return (Criteria) this;
        }

        public Criteria andFixedSituationIsNotNull() {
            addCriterion("fixed_situation is not null");
            return (Criteria) this;
        }

        public Criteria andFixedSituationEqualTo(String value) {
            addCriterion("fixed_situation =", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationNotEqualTo(String value) {
            addCriterion("fixed_situation <>", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationGreaterThan(String value) {
            addCriterion("fixed_situation >", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationGreaterThanOrEqualTo(String value) {
            addCriterion("fixed_situation >=", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationLessThan(String value) {
            addCriterion("fixed_situation <", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationLessThanOrEqualTo(String value) {
            addCriterion("fixed_situation <=", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationLike(String value) {
            addCriterion("fixed_situation like", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationNotLike(String value) {
            addCriterion("fixed_situation not like", value, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationIn(List<String> values) {
            addCriterion("fixed_situation in", values, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationNotIn(List<String> values) {
            addCriterion("fixed_situation not in", values, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationBetween(String value1, String value2) {
            addCriterion("fixed_situation between", value1, value2, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andFixedSituationNotBetween(String value1, String value2) {
            addCriterion("fixed_situation not between", value1, value2, "fixedSituation");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeIsNull() {
            addCriterion("knife_type is null");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeIsNotNull() {
            addCriterion("knife_type is not null");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeEqualTo(String value) {
            addCriterion("knife_type =", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeNotEqualTo(String value) {
            addCriterion("knife_type <>", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeGreaterThan(String value) {
            addCriterion("knife_type >", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("knife_type >=", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeLessThan(String value) {
            addCriterion("knife_type <", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeLessThanOrEqualTo(String value) {
            addCriterion("knife_type <=", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeLike(String value) {
            addCriterion("knife_type like", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeNotLike(String value) {
            addCriterion("knife_type not like", value, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeIn(List<String> values) {
            addCriterion("knife_type in", values, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeNotIn(List<String> values) {
            addCriterion("knife_type not in", values, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeBetween(String value1, String value2) {
            addCriterion("knife_type between", value1, value2, "knifeType");
            return (Criteria) this;
        }

        public Criteria andKnifeTypeNotBetween(String value1, String value2) {
            addCriterion("knife_type not between", value1, value2, "knifeType");
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
