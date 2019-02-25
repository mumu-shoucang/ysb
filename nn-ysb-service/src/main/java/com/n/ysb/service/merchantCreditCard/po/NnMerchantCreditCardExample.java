package com.n.ysb.service.merchantCreditCard.po;

import java.util.ArrayList;
import java.util.List;

public class NnMerchantCreditCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnMerchantCreditCardExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileIsNull() {
            addCriterion("MERCHANT_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileIsNotNull() {
            addCriterion("MERCHANT_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileEqualTo(String value) {
            addCriterion("MERCHANT_MOBILE =", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileNotEqualTo(String value) {
            addCriterion("MERCHANT_MOBILE <>", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileGreaterThan(String value) {
            addCriterion("MERCHANT_MOBILE >", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_MOBILE >=", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileLessThan(String value) {
            addCriterion("MERCHANT_MOBILE <", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_MOBILE <=", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileLike(String value) {
            addCriterion("MERCHANT_MOBILE like", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileNotLike(String value) {
            addCriterion("MERCHANT_MOBILE not like", value, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileIn(List<String> values) {
            addCriterion("MERCHANT_MOBILE in", values, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileNotIn(List<String> values) {
            addCriterion("MERCHANT_MOBILE not in", values, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileBetween(String value1, String value2) {
            addCriterion("MERCHANT_MOBILE between", value1, value2, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantMobileNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_MOBILE not between", value1, value2, "merchantMobile");
            return (Criteria) this;
        }

        public Criteria andCardCodeIsNull() {
            addCriterion("CARD_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCardCodeIsNotNull() {
            addCriterion("CARD_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCardCodeEqualTo(String value) {
            addCriterion("CARD_CODE =", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotEqualTo(String value) {
            addCriterion("CARD_CODE <>", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeGreaterThan(String value) {
            addCriterion("CARD_CODE >", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_CODE >=", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeLessThan(String value) {
            addCriterion("CARD_CODE <", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeLessThanOrEqualTo(String value) {
            addCriterion("CARD_CODE <=", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeLike(String value) {
            addCriterion("CARD_CODE like", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotLike(String value) {
            addCriterion("CARD_CODE not like", value, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeIn(List<String> values) {
            addCriterion("CARD_CODE in", values, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotIn(List<String> values) {
            addCriterion("CARD_CODE not in", values, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeBetween(String value1, String value2) {
            addCriterion("CARD_CODE between", value1, value2, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardCodeNotBetween(String value1, String value2) {
            addCriterion("CARD_CODE not between", value1, value2, "cardCode");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("CARD_NO not between", value1, value2, "cardNo");
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