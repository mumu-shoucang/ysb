package com.n.ysb.service.withdraw.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NnYeecustomerWithdrawExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnYeecustomerWithdrawExample() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("ORDER_NO =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("ORDER_NO <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("ORDER_NO >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_NO >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("ORDER_NO <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ORDER_NO <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("ORDER_NO like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("ORDER_NO not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("ORDER_NO in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("ORDER_NO not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("ORDER_NO between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("ORDER_NO not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtIsNull() {
            addCriterion("WITHDRAW_AMT is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtIsNotNull() {
            addCriterion("WITHDRAW_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_AMT =", value, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtNotEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_AMT <>", value, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtGreaterThan(BigDecimal value) {
            addCriterion("WITHDRAW_AMT >", value, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_AMT >=", value, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtLessThan(BigDecimal value) {
            addCriterion("WITHDRAW_AMT <", value, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_AMT <=", value, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtIn(List<BigDecimal> values) {
            addCriterion("WITHDRAW_AMT in", values, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtNotIn(List<BigDecimal> values) {
            addCriterion("WITHDRAW_AMT not in", values, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WITHDRAW_AMT between", value1, value2, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WITHDRAW_AMT not between", value1, value2, "withdrawAmt");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusIsNull() {
            addCriterion("WITHDRAW_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusIsNotNull() {
            addCriterion("WITHDRAW_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusEqualTo(String value) {
            addCriterion("WITHDRAW_STATUS =", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusNotEqualTo(String value) {
            addCriterion("WITHDRAW_STATUS <>", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusGreaterThan(String value) {
            addCriterion("WITHDRAW_STATUS >", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusGreaterThanOrEqualTo(String value) {
            addCriterion("WITHDRAW_STATUS >=", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusLessThan(String value) {
            addCriterion("WITHDRAW_STATUS <", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusLessThanOrEqualTo(String value) {
            addCriterion("WITHDRAW_STATUS <=", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusLike(String value) {
            addCriterion("WITHDRAW_STATUS like", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusNotLike(String value) {
            addCriterion("WITHDRAW_STATUS not like", value, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusIn(List<String> values) {
            addCriterion("WITHDRAW_STATUS in", values, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusNotIn(List<String> values) {
            addCriterion("WITHDRAW_STATUS not in", values, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusBetween(String value1, String value2) {
            addCriterion("WITHDRAW_STATUS between", value1, value2, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andWithdrawStatusNotBetween(String value1, String value2) {
            addCriterion("WITHDRAW_STATUS not between", value1, value2, "withdrawStatus");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateIsNull() {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateIsNotNull() {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateEqualTo(Date value) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE =", value, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateNotEqualTo(Date value) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE <>", value, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateGreaterThan(Date value) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE >", value, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateGreaterThanOrEqualTo(Date value) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE >=", value, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateLessThan(Date value) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE <", value, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateLessThanOrEqualTo(Date value) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE <=", value, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateIn(List<Date> values) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE in", values, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateNotIn(List<Date> values) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE not in", values, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateBetween(Date value1, Date value2) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE between", value1, value2, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawHandleDateNotBetween(Date value1, Date value2) {
            addCriterion("YEE_WITHDRAW_HANDLE_DATE not between", value1, value2, "yeeWithdrawHandleDate");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverIsNull() {
            addCriterion("YEE_RECEIVER is null");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverIsNotNull() {
            addCriterion("YEE_RECEIVER is not null");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverEqualTo(String value) {
            addCriterion("YEE_RECEIVER =", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverNotEqualTo(String value) {
            addCriterion("YEE_RECEIVER <>", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverGreaterThan(String value) {
            addCriterion("YEE_RECEIVER >", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("YEE_RECEIVER >=", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverLessThan(String value) {
            addCriterion("YEE_RECEIVER <", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverLessThanOrEqualTo(String value) {
            addCriterion("YEE_RECEIVER <=", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverLike(String value) {
            addCriterion("YEE_RECEIVER like", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverNotLike(String value) {
            addCriterion("YEE_RECEIVER not like", value, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverIn(List<String> values) {
            addCriterion("YEE_RECEIVER in", values, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverNotIn(List<String> values) {
            addCriterion("YEE_RECEIVER not in", values, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBetween(String value1, String value2) {
            addCriterion("YEE_RECEIVER between", value1, value2, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverNotBetween(String value1, String value2) {
            addCriterion("YEE_RECEIVER not between", value1, value2, "yeeReceiver");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoIsNull() {
            addCriterion("YEE_RECEIVER_BANKCARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoIsNotNull() {
            addCriterion("YEE_RECEIVER_BANKCARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoEqualTo(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO =", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoNotEqualTo(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO <>", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoGreaterThan(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO >", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoGreaterThanOrEqualTo(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO >=", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoLessThan(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO <", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoLessThanOrEqualTo(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO <=", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoLike(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO like", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoNotLike(String value) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO not like", value, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoIn(List<String> values) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO in", values, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoNotIn(List<String> values) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO not in", values, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoBetween(String value1, String value2) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO between", value1, value2, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeReceiverBankcardNoNotBetween(String value1, String value2) {
            addCriterion("YEE_RECEIVER_BANKCARD_NO not between", value1, value2, "yeeReceiverBankcardNo");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtIsNull() {
            addCriterion("YEE_WITHDRAW_AMT is null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtIsNotNull() {
            addCriterion("YEE_WITHDRAW_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_AMT =", value, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtNotEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_AMT <>", value, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtGreaterThan(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_AMT >", value, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_AMT >=", value, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtLessThan(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_AMT <", value, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_AMT <=", value, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtIn(List<BigDecimal> values) {
            addCriterion("YEE_WITHDRAW_AMT in", values, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtNotIn(List<BigDecimal> values) {
            addCriterion("YEE_WITHDRAW_AMT not in", values, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_WITHDRAW_AMT between", value1, value2, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_WITHDRAW_AMT not between", value1, value2, "yeeWithdrawAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtIsNull() {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT is null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtIsNotNull() {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT =", value, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtNotEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT <>", value, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtGreaterThan(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT >", value, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT >=", value, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtLessThan(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT <", value, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT <=", value, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtIn(List<BigDecimal> values) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT in", values, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtNotIn(List<BigDecimal> values) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT not in", values, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT between", value1, value2, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawActualAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_WITHDRAW_ACTUAL_AMT not between", value1, value2, "yeeWithdrawActualAmt");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeIsNull() {
            addCriterion("YEE_T0_WITHDRAW_FEE is null");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeIsNotNull() {
            addCriterion("YEE_T0_WITHDRAW_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_FEE =", value, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeNotEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_FEE <>", value, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeGreaterThan(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_FEE >", value, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_FEE >=", value, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeLessThan(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_FEE <", value, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_FEE <=", value, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeIn(List<BigDecimal> values) {
            addCriterion("YEE_T0_WITHDRAW_FEE in", values, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeNotIn(List<BigDecimal> values) {
            addCriterion("YEE_T0_WITHDRAW_FEE not in", values, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_T0_WITHDRAW_FEE between", value1, value2, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_T0_WITHDRAW_FEE not between", value1, value2, "yeeT0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeIsNull() {
            addCriterion("YEE_T0_WITHDRAW_EXFEE is null");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeIsNotNull() {
            addCriterion("YEE_T0_WITHDRAW_EXFEE is not null");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE =", value, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeNotEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE <>", value, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeGreaterThan(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE >", value, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE >=", value, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeLessThan(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE <", value, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE <=", value, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeIn(List<BigDecimal> values) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE in", values, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeNotIn(List<BigDecimal> values) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE not in", values, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE between", value1, value2, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeT0WithdrawExfeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_T0_WITHDRAW_EXFEE not between", value1, value2, "yeeT0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdIsNull() {
            addCriterion("YEE_EXTERNAL_LD is null");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdIsNotNull() {
            addCriterion("YEE_EXTERNAL_LD is not null");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdEqualTo(String value) {
            addCriterion("YEE_EXTERNAL_LD =", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdNotEqualTo(String value) {
            addCriterion("YEE_EXTERNAL_LD <>", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdGreaterThan(String value) {
            addCriterion("YEE_EXTERNAL_LD >", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdGreaterThanOrEqualTo(String value) {
            addCriterion("YEE_EXTERNAL_LD >=", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdLessThan(String value) {
            addCriterion("YEE_EXTERNAL_LD <", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdLessThanOrEqualTo(String value) {
            addCriterion("YEE_EXTERNAL_LD <=", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdLike(String value) {
            addCriterion("YEE_EXTERNAL_LD like", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdNotLike(String value) {
            addCriterion("YEE_EXTERNAL_LD not like", value, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdIn(List<String> values) {
            addCriterion("YEE_EXTERNAL_LD in", values, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdNotIn(List<String> values) {
            addCriterion("YEE_EXTERNAL_LD not in", values, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdBetween(String value1, String value2) {
            addCriterion("YEE_EXTERNAL_LD between", value1, value2, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeExternalLdNotBetween(String value1, String value2) {
            addCriterion("YEE_EXTERNAL_LD not between", value1, value2, "yeeExternalLd");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusIsNull() {
            addCriterion("YEE_WITHDRAW_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusIsNotNull() {
            addCriterion("YEE_WITHDRAW_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusEqualTo(String value) {
            addCriterion("YEE_WITHDRAW_STATUS =", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusNotEqualTo(String value) {
            addCriterion("YEE_WITHDRAW_STATUS <>", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusGreaterThan(String value) {
            addCriterion("YEE_WITHDRAW_STATUS >", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusGreaterThanOrEqualTo(String value) {
            addCriterion("YEE_WITHDRAW_STATUS >=", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusLessThan(String value) {
            addCriterion("YEE_WITHDRAW_STATUS <", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusLessThanOrEqualTo(String value) {
            addCriterion("YEE_WITHDRAW_STATUS <=", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusLike(String value) {
            addCriterion("YEE_WITHDRAW_STATUS like", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusNotLike(String value) {
            addCriterion("YEE_WITHDRAW_STATUS not like", value, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusIn(List<String> values) {
            addCriterion("YEE_WITHDRAW_STATUS in", values, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusNotIn(List<String> values) {
            addCriterion("YEE_WITHDRAW_STATUS not in", values, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusBetween(String value1, String value2) {
            addCriterion("YEE_WITHDRAW_STATUS between", value1, value2, "yeeWithdrawStatus");
            return (Criteria) this;
        }

        public Criteria andYeeWithdrawStatusNotBetween(String value1, String value2) {
            addCriterion("YEE_WITHDRAW_STATUS not between", value1, value2, "yeeWithdrawStatus");
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