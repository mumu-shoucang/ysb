package com.n.ysb.service.transfer.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NnTransferLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnTransferLogExample() {
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

        public Criteria andOpDateIsNull() {
            addCriterion("OP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOpDateIsNotNull() {
            addCriterion("OP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOpDateEqualTo(Date value) {
            addCriterion("OP_DATE =", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateNotEqualTo(Date value) {
            addCriterion("OP_DATE <>", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateGreaterThan(Date value) {
            addCriterion("OP_DATE >", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("OP_DATE >=", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateLessThan(Date value) {
            addCriterion("OP_DATE <", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateLessThanOrEqualTo(Date value) {
            addCriterion("OP_DATE <=", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateIn(List<Date> values) {
            addCriterion("OP_DATE in", values, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateNotIn(List<Date> values) {
            addCriterion("OP_DATE not in", values, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateBetween(Date value1, Date value2) {
            addCriterion("OP_DATE between", value1, value2, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateNotBetween(Date value1, Date value2) {
            addCriterion("OP_DATE not between", value1, value2, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpUserIsNull() {
            addCriterion("OP_USER is null");
            return (Criteria) this;
        }

        public Criteria andOpUserIsNotNull() {
            addCriterion("OP_USER is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserEqualTo(String value) {
            addCriterion("OP_USER =", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotEqualTo(String value) {
            addCriterion("OP_USER <>", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserGreaterThan(String value) {
            addCriterion("OP_USER >", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserGreaterThanOrEqualTo(String value) {
            addCriterion("OP_USER >=", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLessThan(String value) {
            addCriterion("OP_USER <", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLessThanOrEqualTo(String value) {
            addCriterion("OP_USER <=", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLike(String value) {
            addCriterion("OP_USER like", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotLike(String value) {
            addCriterion("OP_USER not like", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserIn(List<String> values) {
            addCriterion("OP_USER in", values, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotIn(List<String> values) {
            addCriterion("OP_USER not in", values, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserBetween(String value1, String value2) {
            addCriterion("OP_USER between", value1, value2, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotBetween(String value1, String value2) {
            addCriterion("OP_USER not between", value1, value2, "opUser");
            return (Criteria) this;
        }

        public Criteria andTransferDateIsNull() {
            addCriterion("TRANSFER_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTransferDateIsNotNull() {
            addCriterion("TRANSFER_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTransferDateEqualTo(Date value) {
            addCriterion("TRANSFER_DATE =", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotEqualTo(Date value) {
            addCriterion("TRANSFER_DATE <>", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateGreaterThan(Date value) {
            addCriterion("TRANSFER_DATE >", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateGreaterThanOrEqualTo(Date value) {
            addCriterion("TRANSFER_DATE >=", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateLessThan(Date value) {
            addCriterion("TRANSFER_DATE <", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateLessThanOrEqualTo(Date value) {
            addCriterion("TRANSFER_DATE <=", value, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateIn(List<Date> values) {
            addCriterion("TRANSFER_DATE in", values, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotIn(List<Date> values) {
            addCriterion("TRANSFER_DATE not in", values, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateBetween(Date value1, Date value2) {
            addCriterion("TRANSFER_DATE between", value1, value2, "transferDate");
            return (Criteria) this;
        }

        public Criteria andTransferDateNotBetween(Date value1, Date value2) {
            addCriterion("TRANSFER_DATE not between", value1, value2, "transferDate");
            return (Criteria) this;
        }

        public Criteria andRefSignIsNull() {
            addCriterion("REF_SIGN is null");
            return (Criteria) this;
        }

        public Criteria andRefSignIsNotNull() {
            addCriterion("REF_SIGN is not null");
            return (Criteria) this;
        }

        public Criteria andRefSignEqualTo(String value) {
            addCriterion("REF_SIGN =", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignNotEqualTo(String value) {
            addCriterion("REF_SIGN <>", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignGreaterThan(String value) {
            addCriterion("REF_SIGN >", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignGreaterThanOrEqualTo(String value) {
            addCriterion("REF_SIGN >=", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignLessThan(String value) {
            addCriterion("REF_SIGN <", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignLessThanOrEqualTo(String value) {
            addCriterion("REF_SIGN <=", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignLike(String value) {
            addCriterion("REF_SIGN like", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignNotLike(String value) {
            addCriterion("REF_SIGN not like", value, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignIn(List<String> values) {
            addCriterion("REF_SIGN in", values, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignNotIn(List<String> values) {
            addCriterion("REF_SIGN not in", values, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignBetween(String value1, String value2) {
            addCriterion("REF_SIGN between", value1, value2, "refSign");
            return (Criteria) this;
        }

        public Criteria andRefSignNotBetween(String value1, String value2) {
            addCriterion("REF_SIGN not between", value1, value2, "refSign");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberIsNull() {
            addCriterion("YEE_CUSTOMER_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberIsNotNull() {
            addCriterion("YEE_CUSTOMER_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberEqualTo(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER =", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberNotEqualTo(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER <>", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberGreaterThan(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER >", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberGreaterThanOrEqualTo(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER >=", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberLessThan(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER <", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberLessThanOrEqualTo(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER <=", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberLike(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER like", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberNotLike(String value) {
            addCriterion("YEE_CUSTOMER_NUMBER not like", value, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberIn(List<String> values) {
            addCriterion("YEE_CUSTOMER_NUMBER in", values, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberNotIn(List<String> values) {
            addCriterion("YEE_CUSTOMER_NUMBER not in", values, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberBetween(String value1, String value2) {
            addCriterion("YEE_CUSTOMER_NUMBER between", value1, value2, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andYeeCustomerNumberNotBetween(String value1, String value2) {
            addCriterion("YEE_CUSTOMER_NUMBER not between", value1, value2, "yeeCustomerNumber");
            return (Criteria) this;
        }

        public Criteria andTransferAmtIsNull() {
            addCriterion("TRANSFER_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTransferAmtIsNotNull() {
            addCriterion("TRANSFER_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTransferAmtEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_AMT =", value, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtNotEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_AMT <>", value, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtGreaterThan(BigDecimal value) {
            addCriterion("TRANSFER_AMT >", value, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_AMT >=", value, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtLessThan(BigDecimal value) {
            addCriterion("TRANSFER_AMT <", value, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_AMT <=", value, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtIn(List<BigDecimal> values) {
            addCriterion("TRANSFER_AMT in", values, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtNotIn(List<BigDecimal> values) {
            addCriterion("TRANSFER_AMT not in", values, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSFER_AMT between", value1, value2, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSFER_AMT not between", value1, value2, "transferAmt");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNull() {
            addCriterion("TRANSFER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNotNull() {
            addCriterion("TRANSFER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusEqualTo(String value) {
            addCriterion("TRANSFER_STATUS =", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotEqualTo(String value) {
            addCriterion("TRANSFER_STATUS <>", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThan(String value) {
            addCriterion("TRANSFER_STATUS >", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSFER_STATUS >=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThan(String value) {
            addCriterion("TRANSFER_STATUS <", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThanOrEqualTo(String value) {
            addCriterion("TRANSFER_STATUS <=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLike(String value) {
            addCriterion("TRANSFER_STATUS like", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotLike(String value) {
            addCriterion("TRANSFER_STATUS not like", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIn(List<String> values) {
            addCriterion("TRANSFER_STATUS in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotIn(List<String> values) {
            addCriterion("TRANSFER_STATUS not in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusBetween(String value1, String value2) {
            addCriterion("TRANSFER_STATUS between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotBetween(String value1, String value2) {
            addCriterion("TRANSFER_STATUS not between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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