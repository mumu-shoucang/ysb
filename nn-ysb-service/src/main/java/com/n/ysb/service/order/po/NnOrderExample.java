package com.n.ysb.service.order.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NnOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnOrderExample() {
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

        public Criteria andOrderStatusIsNull() {
            addCriterion("ORDER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("ORDER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(String value) {
            addCriterion("ORDER_STATUS =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(String value) {
            addCriterion("ORDER_STATUS <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(String value) {
            addCriterion("ORDER_STATUS >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(String value) {
            addCriterion("ORDER_STATUS <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLike(String value) {
            addCriterion("ORDER_STATUS like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotLike(String value) {
            addCriterion("ORDER_STATUS not like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<String> values) {
            addCriterion("ORDER_STATUS in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<String> values) {
            addCriterion("ORDER_STATUS not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoIsNull() {
            addCriterion("DEBIT_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoIsNotNull() {
            addCriterion("DEBIT_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoEqualTo(String value) {
            addCriterion("DEBIT_CARD_NO =", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoNotEqualTo(String value) {
            addCriterion("DEBIT_CARD_NO <>", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoGreaterThan(String value) {
            addCriterion("DEBIT_CARD_NO >", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("DEBIT_CARD_NO >=", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoLessThan(String value) {
            addCriterion("DEBIT_CARD_NO <", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoLessThanOrEqualTo(String value) {
            addCriterion("DEBIT_CARD_NO <=", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoLike(String value) {
            addCriterion("DEBIT_CARD_NO like", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoNotLike(String value) {
            addCriterion("DEBIT_CARD_NO not like", value, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoIn(List<String> values) {
            addCriterion("DEBIT_CARD_NO in", values, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoNotIn(List<String> values) {
            addCriterion("DEBIT_CARD_NO not in", values, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoBetween(String value1, String value2) {
            addCriterion("DEBIT_CARD_NO between", value1, value2, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andDebitCardNoNotBetween(String value1, String value2) {
            addCriterion("DEBIT_CARD_NO not between", value1, value2, "debitCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoIsNull() {
            addCriterion("CREDIT_CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoIsNotNull() {
            addCriterion("CREDIT_CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoEqualTo(String value) {
            addCriterion("CREDIT_CARD_NO =", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoNotEqualTo(String value) {
            addCriterion("CREDIT_CARD_NO <>", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoGreaterThan(String value) {
            addCriterion("CREDIT_CARD_NO >", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CREDIT_CARD_NO >=", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoLessThan(String value) {
            addCriterion("CREDIT_CARD_NO <", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoLessThanOrEqualTo(String value) {
            addCriterion("CREDIT_CARD_NO <=", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoLike(String value) {
            addCriterion("CREDIT_CARD_NO like", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoNotLike(String value) {
            addCriterion("CREDIT_CARD_NO not like", value, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoIn(List<String> values) {
            addCriterion("CREDIT_CARD_NO in", values, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoNotIn(List<String> values) {
            addCriterion("CREDIT_CARD_NO not in", values, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoBetween(String value1, String value2) {
            addCriterion("CREDIT_CARD_NO between", value1, value2, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andCreditCardNoNotBetween(String value1, String value2) {
            addCriterion("CREDIT_CARD_NO not between", value1, value2, "creditCardNo");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNull() {
            addCriterion("ORDER_AMT is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNotNull() {
            addCriterion("ORDER_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtEqualTo(BigDecimal value) {
            addCriterion("ORDER_AMT =", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotEqualTo(BigDecimal value) {
            addCriterion("ORDER_AMT <>", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThan(BigDecimal value) {
            addCriterion("ORDER_AMT >", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDER_AMT >=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThan(BigDecimal value) {
            addCriterion("ORDER_AMT <", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDER_AMT <=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIn(List<BigDecimal> values) {
            addCriterion("ORDER_AMT in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotIn(List<BigDecimal> values) {
            addCriterion("ORDER_AMT not in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDER_AMT between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDER_AMT not between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andTradeFeeIsNull() {
            addCriterion("TRADE_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTradeFeeIsNotNull() {
            addCriterion("TRADE_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTradeFeeEqualTo(BigDecimal value) {
            addCriterion("TRADE_FEE =", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeNotEqualTo(BigDecimal value) {
            addCriterion("TRADE_FEE <>", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeGreaterThan(BigDecimal value) {
            addCriterion("TRADE_FEE >", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRADE_FEE >=", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeLessThan(BigDecimal value) {
            addCriterion("TRADE_FEE <", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRADE_FEE <=", value, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeIn(List<BigDecimal> values) {
            addCriterion("TRADE_FEE in", values, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeNotIn(List<BigDecimal> values) {
            addCriterion("TRADE_FEE not in", values, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRADE_FEE between", value1, value2, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andTradeFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRADE_FEE not between", value1, value2, "tradeFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeIsNull() {
            addCriterion("T0_WITHDRAW_FEE is null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeIsNotNull() {
            addCriterion("T0_WITHDRAW_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_FEE =", value, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeNotEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_FEE <>", value, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeGreaterThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_FEE >", value, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_FEE >=", value, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeLessThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_FEE <", value, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_FEE <=", value, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_FEE in", values, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeNotIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_FEE not in", values, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_FEE between", value1, value2, "t0WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_FEE not between", value1, value2, "t0WithdrawFee");
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

        public Criteria andYeePayStatusIsNull() {
            addCriterion("YEE_PAY_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusIsNotNull() {
            addCriterion("YEE_PAY_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusEqualTo(String value) {
            addCriterion("YEE_PAY_STATUS =", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusNotEqualTo(String value) {
            addCriterion("YEE_PAY_STATUS <>", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusGreaterThan(String value) {
            addCriterion("YEE_PAY_STATUS >", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("YEE_PAY_STATUS >=", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusLessThan(String value) {
            addCriterion("YEE_PAY_STATUS <", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusLessThanOrEqualTo(String value) {
            addCriterion("YEE_PAY_STATUS <=", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusLike(String value) {
            addCriterion("YEE_PAY_STATUS like", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusNotLike(String value) {
            addCriterion("YEE_PAY_STATUS not like", value, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusIn(List<String> values) {
            addCriterion("YEE_PAY_STATUS in", values, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusNotIn(List<String> values) {
            addCriterion("YEE_PAY_STATUS not in", values, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusBetween(String value1, String value2) {
            addCriterion("YEE_PAY_STATUS between", value1, value2, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayStatusNotBetween(String value1, String value2) {
            addCriterion("YEE_PAY_STATUS not between", value1, value2, "yeePayStatus");
            return (Criteria) this;
        }

        public Criteria andYeePayDateIsNull() {
            addCriterion("YEE_PAY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andYeePayDateIsNotNull() {
            addCriterion("YEE_PAY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andYeePayDateEqualTo(Date value) {
            addCriterion("YEE_PAY_DATE =", value, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateNotEqualTo(Date value) {
            addCriterion("YEE_PAY_DATE <>", value, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateGreaterThan(Date value) {
            addCriterion("YEE_PAY_DATE >", value, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateGreaterThanOrEqualTo(Date value) {
            addCriterion("YEE_PAY_DATE >=", value, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateLessThan(Date value) {
            addCriterion("YEE_PAY_DATE <", value, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateLessThanOrEqualTo(Date value) {
            addCriterion("YEE_PAY_DATE <=", value, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateIn(List<Date> values) {
            addCriterion("YEE_PAY_DATE in", values, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateNotIn(List<Date> values) {
            addCriterion("YEE_PAY_DATE not in", values, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateBetween(Date value1, Date value2) {
            addCriterion("YEE_PAY_DATE between", value1, value2, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeePayDateNotBetween(Date value1, Date value2) {
            addCriterion("YEE_PAY_DATE not between", value1, value2, "yeePayDate");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeIsNull() {
            addCriterion("YEE_TRADE_FEE is null");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeIsNotNull() {
            addCriterion("YEE_TRADE_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeEqualTo(BigDecimal value) {
            addCriterion("YEE_TRADE_FEE =", value, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeNotEqualTo(BigDecimal value) {
            addCriterion("YEE_TRADE_FEE <>", value, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeGreaterThan(BigDecimal value) {
            addCriterion("YEE_TRADE_FEE >", value, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_TRADE_FEE >=", value, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeLessThan(BigDecimal value) {
            addCriterion("YEE_TRADE_FEE <", value, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YEE_TRADE_FEE <=", value, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeIn(List<BigDecimal> values) {
            addCriterion("YEE_TRADE_FEE in", values, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeNotIn(List<BigDecimal> values) {
            addCriterion("YEE_TRADE_FEE not in", values, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_TRADE_FEE between", value1, value2, "yeeTradeFee");
            return (Criteria) this;
        }

        public Criteria andYeeTradeFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YEE_TRADE_FEE not between", value1, value2, "yeeTradeFee");
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

        public Criteria andMccIsNull() {
            addCriterion("MCC is null");
            return (Criteria) this;
        }

        public Criteria andMccIsNotNull() {
            addCriterion("MCC is not null");
            return (Criteria) this;
        }

        public Criteria andMccEqualTo(String value) {
            addCriterion("MCC =", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotEqualTo(String value) {
            addCriterion("MCC <>", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccGreaterThan(String value) {
            addCriterion("MCC >", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccGreaterThanOrEqualTo(String value) {
            addCriterion("MCC >=", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccLessThan(String value) {
            addCriterion("MCC <", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccLessThanOrEqualTo(String value) {
            addCriterion("MCC <=", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccLike(String value) {
            addCriterion("MCC like", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotLike(String value) {
            addCriterion("MCC not like", value, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccIn(List<String> values) {
            addCriterion("MCC in", values, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotIn(List<String> values) {
            addCriterion("MCC not in", values, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccBetween(String value1, String value2) {
            addCriterion("MCC between", value1, value2, "mcc");
            return (Criteria) this;
        }

        public Criteria andMccNotBetween(String value1, String value2) {
            addCriterion("MCC not between", value1, value2, "mcc");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeIsNull() {
            addCriterion("T0_WITHDRAW_EXFEE is null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeIsNotNull() {
            addCriterion("T0_WITHDRAW_EXFEE is not null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_EXFEE =", value, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeNotEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_EXFEE <>", value, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeGreaterThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_EXFEE >", value, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_EXFEE >=", value, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeLessThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_EXFEE <", value, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_EXFEE <=", value, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_EXFEE in", values, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeNotIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_EXFEE not in", values, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_EXFEE between", value1, value2, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawExfeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_EXFEE not between", value1, value2, "t0WithdrawExfee");
            return (Criteria) this;
        }

        public Criteria andCommisstionIsNull() {
            addCriterion("COMMISSTION is null");
            return (Criteria) this;
        }

        public Criteria andCommisstionIsNotNull() {
            addCriterion("COMMISSTION is not null");
            return (Criteria) this;
        }

        public Criteria andCommisstionEqualTo(BigDecimal value) {
            addCriterion("COMMISSTION =", value, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionNotEqualTo(BigDecimal value) {
            addCriterion("COMMISSTION <>", value, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionGreaterThan(BigDecimal value) {
            addCriterion("COMMISSTION >", value, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSTION >=", value, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionLessThan(BigDecimal value) {
            addCriterion("COMMISSTION <", value, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSTION <=", value, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionIn(List<BigDecimal> values) {
            addCriterion("COMMISSTION in", values, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionNotIn(List<BigDecimal> values) {
            addCriterion("COMMISSTION not in", values, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSTION between", value1, value2, "commisstion");
            return (Criteria) this;
        }

        public Criteria andCommisstionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSTION not between", value1, value2, "commisstion");
            return (Criteria) this;
        }
        
        public Criteria andTradeCommisstionIsNull() {
            addCriterion("TRADE_COMMISSTION is null");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionIsNotNull() {
            addCriterion("TRADE_COMMISSTION is not null");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION =", value, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionNotEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION <>", value, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionGreaterThan(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION >", value, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION >=", value, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionLessThan(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION <", value, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION <=", value, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionIn(List<BigDecimal> values) {
            addCriterion("TRADE_COMMISSTION in", values, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionNotIn(List<BigDecimal> values) {
            addCriterion("TRADE_COMMISSTION not in", values, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRADE_COMMISSTION between", value1, value2, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRADE_COMMISSTION not between", value1, value2, "tradeCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionIsNull() {
            addCriterion("WITHDRAW_COMMISSTION is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionIsNotNull() {
            addCriterion("WITHDRAW_COMMISSTION is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_COMMISSTION =", value, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionNotEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_COMMISSTION <>", value, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionGreaterThan(BigDecimal value) {
            addCriterion("WITHDRAW_COMMISSTION >", value, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_COMMISSTION >=", value, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionLessThan(BigDecimal value) {
            addCriterion("WITHDRAW_COMMISSTION <", value, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WITHDRAW_COMMISSTION <=", value, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionIn(List<BigDecimal> values) {
            addCriterion("WITHDRAW_COMMISSTION in", values, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionNotIn(List<BigDecimal> values) {
            addCriterion("WITHDRAW_COMMISSTION not in", values, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WITHDRAW_COMMISSTION between", value1, value2, "withdrawCommisstion");
            return (Criteria) this;
        }

        public Criteria andWithdrawCommisstionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WITHDRAW_COMMISSTION not between", value1, value2, "withdrawCommisstion");
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