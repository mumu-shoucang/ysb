package com.n.ysb.service.globalCfg.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NnGlobalCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnGlobalCfgExample() {
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

        public Criteria andSingleLimitIsNull() {
            addCriterion("SINGLE_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIsNotNull() {
            addCriterion("SINGLE_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andSingleLimitEqualTo(Long value) {
            addCriterion("SINGLE_LIMIT =", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotEqualTo(Long value) {
            addCriterion("SINGLE_LIMIT <>", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitGreaterThan(Long value) {
            addCriterion("SINGLE_LIMIT >", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("SINGLE_LIMIT >=", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLessThan(Long value) {
            addCriterion("SINGLE_LIMIT <", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLessThanOrEqualTo(Long value) {
            addCriterion("SINGLE_LIMIT <=", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIn(List<Long> values) {
            addCriterion("SINGLE_LIMIT in", values, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotIn(List<Long> values) {
            addCriterion("SINGLE_LIMIT not in", values, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitBetween(Long value1, Long value2) {
            addCriterion("SINGLE_LIMIT between", value1, value2, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotBetween(Long value1, Long value2) {
            addCriterion("SINGLE_LIMIT not between", value1, value2, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitIsNull() {
            addCriterion("DAY_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andDayLimitIsNotNull() {
            addCriterion("DAY_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andDayLimitEqualTo(Long value) {
            addCriterion("DAY_LIMIT =", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotEqualTo(Long value) {
            addCriterion("DAY_LIMIT <>", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitGreaterThan(Long value) {
            addCriterion("DAY_LIMIT >", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("DAY_LIMIT >=", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLessThan(Long value) {
            addCriterion("DAY_LIMIT <", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLessThanOrEqualTo(Long value) {
            addCriterion("DAY_LIMIT <=", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitIn(List<Long> values) {
            addCriterion("DAY_LIMIT in", values, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotIn(List<Long> values) {
            addCriterion("DAY_LIMIT not in", values, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitBetween(Long value1, Long value2) {
            addCriterion("DAY_LIMIT between", value1, value2, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotBetween(Long value1, Long value2) {
            addCriterion("DAY_LIMIT not between", value1, value2, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitIsNull() {
            addCriterion("MONTH_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andMonthLimitIsNotNull() {
            addCriterion("MONTH_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andMonthLimitEqualTo(Long value) {
            addCriterion("MONTH_LIMIT =", value, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitNotEqualTo(Long value) {
            addCriterion("MONTH_LIMIT <>", value, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitGreaterThan(Long value) {
            addCriterion("MONTH_LIMIT >", value, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("MONTH_LIMIT >=", value, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitLessThan(Long value) {
            addCriterion("MONTH_LIMIT <", value, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitLessThanOrEqualTo(Long value) {
            addCriterion("MONTH_LIMIT <=", value, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitIn(List<Long> values) {
            addCriterion("MONTH_LIMIT in", values, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitNotIn(List<Long> values) {
            addCriterion("MONTH_LIMIT not in", values, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitBetween(Long value1, Long value2) {
            addCriterion("MONTH_LIMIT between", value1, value2, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andMonthLimitNotBetween(Long value1, Long value2) {
            addCriterion("MONTH_LIMIT not between", value1, value2, "monthLimit");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNull() {
            addCriterion("DAY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNotNull() {
            addCriterion("DAY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDayCountEqualTo(Long value) {
            addCriterion("DAY_COUNT =", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotEqualTo(Long value) {
            addCriterion("DAY_COUNT <>", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThan(Long value) {
            addCriterion("DAY_COUNT >", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThanOrEqualTo(Long value) {
            addCriterion("DAY_COUNT >=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThan(Long value) {
            addCriterion("DAY_COUNT <", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThanOrEqualTo(Long value) {
            addCriterion("DAY_COUNT <=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountIn(List<Long> values) {
            addCriterion("DAY_COUNT in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotIn(List<Long> values) {
            addCriterion("DAY_COUNT not in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountBetween(Long value1, Long value2) {
            addCriterion("DAY_COUNT between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotBetween(Long value1, Long value2) {
            addCriterion("DAY_COUNT not between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountIsNull() {
            addCriterion("MONTH_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andMonthCountIsNotNull() {
            addCriterion("MONTH_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andMonthCountEqualTo(Long value) {
            addCriterion("MONTH_COUNT =", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountNotEqualTo(Long value) {
            addCriterion("MONTH_COUNT <>", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountGreaterThan(Long value) {
            addCriterion("MONTH_COUNT >", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountGreaterThanOrEqualTo(Long value) {
            addCriterion("MONTH_COUNT >=", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountLessThan(Long value) {
            addCriterion("MONTH_COUNT <", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountLessThanOrEqualTo(Long value) {
            addCriterion("MONTH_COUNT <=", value, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountIn(List<Long> values) {
            addCriterion("MONTH_COUNT in", values, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountNotIn(List<Long> values) {
            addCriterion("MONTH_COUNT not in", values, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountBetween(Long value1, Long value2) {
            addCriterion("MONTH_COUNT between", value1, value2, "monthCount");
            return (Criteria) this;
        }

        public Criteria andMonthCountNotBetween(Long value1, Long value2) {
            addCriterion("MONTH_COUNT not between", value1, value2, "monthCount");
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

        public Criteria andT1WithdrawFeeIsNull() {
            addCriterion("T1_WITHDRAW_FEE is null");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeIsNotNull() {
            addCriterion("T1_WITHDRAW_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeEqualTo(BigDecimal value) {
            addCriterion("T1_WITHDRAW_FEE =", value, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeNotEqualTo(BigDecimal value) {
            addCriterion("T1_WITHDRAW_FEE <>", value, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeGreaterThan(BigDecimal value) {
            addCriterion("T1_WITHDRAW_FEE >", value, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("T1_WITHDRAW_FEE >=", value, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeLessThan(BigDecimal value) {
            addCriterion("T1_WITHDRAW_FEE <", value, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("T1_WITHDRAW_FEE <=", value, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeIn(List<BigDecimal> values) {
            addCriterion("T1_WITHDRAW_FEE in", values, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeNotIn(List<BigDecimal> values) {
            addCriterion("T1_WITHDRAW_FEE not in", values, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T1_WITHDRAW_FEE between", value1, value2, "t1WithdrawFee");
            return (Criteria) this;
        }

        public Criteria andT1WithdrawFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T1_WITHDRAW_FEE not between", value1, value2, "t1WithdrawFee");
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

        public Criteria andT0WithdrawWorkdayFeeIsNull() {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE is null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeIsNotNull() {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE =", value, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeNotEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE <>", value, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeGreaterThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE >", value, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE >=", value, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeLessThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE <", value, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE <=", value, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE in", values, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeNotIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE not in", values, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE between", value1, value2, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawWorkdayFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_WORKDAY_FEE not between", value1, value2, "t0WithdrawWorkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeIsNull() {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE is null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeIsNotNull() {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE =", value, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeNotEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE <>", value, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeGreaterThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE >", value, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE >=", value, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeLessThan(BigDecimal value) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE <", value, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE <=", value, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE in", values, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeNotIn(List<BigDecimal> values) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE not in", values, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE between", value1, value2, "t0WithdrawNonworkdayFee");
            return (Criteria) this;
        }

        public Criteria andT0WithdrawNonworkdayFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("T0_WITHDRAW_NONWORKDAY_FEE not between", value1, value2, "t0WithdrawNonworkdayFee");
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