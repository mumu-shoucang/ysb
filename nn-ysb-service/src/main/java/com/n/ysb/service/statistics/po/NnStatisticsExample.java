package com.n.ysb.service.statistics.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NnStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnStatisticsExample() {
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

        public Criteria andStatisticsTypeIsNull() {
            addCriterion("STATISTICS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeIsNotNull() {
            addCriterion("STATISTICS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeEqualTo(String value) {
            addCriterion("STATISTICS_TYPE =", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotEqualTo(String value) {
            addCriterion("STATISTICS_TYPE <>", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeGreaterThan(String value) {
            addCriterion("STATISTICS_TYPE >", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STATISTICS_TYPE >=", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeLessThan(String value) {
            addCriterion("STATISTICS_TYPE <", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeLessThanOrEqualTo(String value) {
            addCriterion("STATISTICS_TYPE <=", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeLike(String value) {
            addCriterion("STATISTICS_TYPE like", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotLike(String value) {
            addCriterion("STATISTICS_TYPE not like", value, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeIn(List<String> values) {
            addCriterion("STATISTICS_TYPE in", values, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotIn(List<String> values) {
            addCriterion("STATISTICS_TYPE not in", values, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeBetween(String value1, String value2) {
            addCriterion("STATISTICS_TYPE between", value1, value2, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsTypeNotBetween(String value1, String value2) {
            addCriterion("STATISTICS_TYPE not between", value1, value2, "statisticsType");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateIsNull() {
            addCriterion("STATISTICS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateIsNotNull() {
            addCriterion("STATISTICS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateEqualTo(Date value) {
            addCriterion("STATISTICS_DATE =", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateNotEqualTo(Date value) {
            addCriterion("STATISTICS_DATE <>", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateGreaterThan(Date value) {
            addCriterion("STATISTICS_DATE >", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateGreaterThanOrEqualTo(Date value) {
            addCriterion("STATISTICS_DATE >=", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateLessThan(Date value) {
            addCriterion("STATISTICS_DATE <", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateLessThanOrEqualTo(Date value) {
            addCriterion("STATISTICS_DATE <=", value, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateIn(List<Date> values) {
            addCriterion("STATISTICS_DATE in", values, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateNotIn(List<Date> values) {
            addCriterion("STATISTICS_DATE not in", values, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateBetween(Date value1, Date value2) {
            addCriterion("STATISTICS_DATE between", value1, value2, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsDateNotBetween(Date value1, Date value2) {
            addCriterion("STATISTICS_DATE not between", value1, value2, "statisticsDate");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtIsNull() {
            addCriterion("STATISTICS_AMT is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtIsNotNull() {
            addCriterion("STATISTICS_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtEqualTo(BigDecimal value) {
            addCriterion("STATISTICS_AMT =", value, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtNotEqualTo(BigDecimal value) {
            addCriterion("STATISTICS_AMT <>", value, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtGreaterThan(BigDecimal value) {
            addCriterion("STATISTICS_AMT >", value, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STATISTICS_AMT >=", value, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtLessThan(BigDecimal value) {
            addCriterion("STATISTICS_AMT <", value, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STATISTICS_AMT <=", value, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtIn(List<BigDecimal> values) {
            addCriterion("STATISTICS_AMT in", values, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtNotIn(List<BigDecimal> values) {
            addCriterion("STATISTICS_AMT not in", values, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATISTICS_AMT between", value1, value2, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andStatisticsAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATISTICS_AMT not between", value1, value2, "statisticsAmt");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountIsNull() {
            addCriterion("TRADE_SUC_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountIsNotNull() {
            addCriterion("TRADE_SUC_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountEqualTo(Long value) {
            addCriterion("TRADE_SUC_COUNT =", value, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountNotEqualTo(Long value) {
            addCriterion("TRADE_SUC_COUNT <>", value, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountGreaterThan(Long value) {
            addCriterion("TRADE_SUC_COUNT >", value, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TRADE_SUC_COUNT >=", value, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountLessThan(Long value) {
            addCriterion("TRADE_SUC_COUNT <", value, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountLessThanOrEqualTo(Long value) {
            addCriterion("TRADE_SUC_COUNT <=", value, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountIn(List<Long> values) {
            addCriterion("TRADE_SUC_COUNT in", values, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountNotIn(List<Long> values) {
            addCriterion("TRADE_SUC_COUNT not in", values, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountBetween(Long value1, Long value2) {
            addCriterion("TRADE_SUC_COUNT between", value1, value2, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andTradeSucCountNotBetween(Long value1, Long value2) {
            addCriterion("TRADE_SUC_COUNT not between", value1, value2, "tradeSucCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountIsNull() {
            addCriterion("REG_USER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andRegUserCountIsNotNull() {
            addCriterion("REG_USER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRegUserCountEqualTo(Long value) {
            addCriterion("REG_USER_COUNT =", value, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountNotEqualTo(Long value) {
            addCriterion("REG_USER_COUNT <>", value, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountGreaterThan(Long value) {
            addCriterion("REG_USER_COUNT >", value, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountGreaterThanOrEqualTo(Long value) {
            addCriterion("REG_USER_COUNT >=", value, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountLessThan(Long value) {
            addCriterion("REG_USER_COUNT <", value, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountLessThanOrEqualTo(Long value) {
            addCriterion("REG_USER_COUNT <=", value, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountIn(List<Long> values) {
            addCriterion("REG_USER_COUNT in", values, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountNotIn(List<Long> values) {
            addCriterion("REG_USER_COUNT not in", values, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountBetween(Long value1, Long value2) {
            addCriterion("REG_USER_COUNT between", value1, value2, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andRegUserCountNotBetween(Long value1, Long value2) {
            addCriterion("REG_USER_COUNT not between", value1, value2, "regUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountIsNull() {
            addCriterion("ANTU_USER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountIsNotNull() {
            addCriterion("ANTU_USER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountEqualTo(Long value) {
            addCriterion("ANTU_USER_COUNT =", value, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountNotEqualTo(Long value) {
            addCriterion("ANTU_USER_COUNT <>", value, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountGreaterThan(Long value) {
            addCriterion("ANTU_USER_COUNT >", value, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountGreaterThanOrEqualTo(Long value) {
            addCriterion("ANTU_USER_COUNT >=", value, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountLessThan(Long value) {
            addCriterion("ANTU_USER_COUNT <", value, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountLessThanOrEqualTo(Long value) {
            addCriterion("ANTU_USER_COUNT <=", value, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountIn(List<Long> values) {
            addCriterion("ANTU_USER_COUNT in", values, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountNotIn(List<Long> values) {
            addCriterion("ANTU_USER_COUNT not in", values, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountBetween(Long value1, Long value2) {
            addCriterion("ANTU_USER_COUNT between", value1, value2, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andAntuUserCountNotBetween(Long value1, Long value2) {
            addCriterion("ANTU_USER_COUNT not between", value1, value2, "antuUserCount");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateIsNull() {
            addCriterion("TRADE_COMMISSTION_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateIsNotNull() {
            addCriterion("TRADE_COMMISSTION_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION_RATE =", value, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateNotEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION_RATE <>", value, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateGreaterThan(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION_RATE >", value, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION_RATE >=", value, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateLessThan(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION_RATE <", value, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRADE_COMMISSTION_RATE <=", value, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateIn(List<BigDecimal> values) {
            addCriterion("TRADE_COMMISSTION_RATE in", values, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateNotIn(List<BigDecimal> values) {
            addCriterion("TRADE_COMMISSTION_RATE not in", values, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRADE_COMMISSTION_RATE between", value1, value2, "tradeCommisstionRate");
            return (Criteria) this;
        }

        public Criteria andTradeCommisstionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRADE_COMMISSTION_RATE not between", value1, value2, "tradeCommisstionRate");
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

        public Criteria andCommissionTransferStatIsNull() {
            addCriterion("COMMISSION_TRANSFER_STAT is null");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatIsNotNull() {
            addCriterion("COMMISSION_TRANSFER_STAT is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatEqualTo(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT =", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatNotEqualTo(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT <>", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatGreaterThan(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT >", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatGreaterThanOrEqualTo(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT >=", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatLessThan(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT <", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatLessThanOrEqualTo(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT <=", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatLike(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT like", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatNotLike(String value) {
            addCriterion("COMMISSION_TRANSFER_STAT not like", value, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatIn(List<String> values) {
            addCriterion("COMMISSION_TRANSFER_STAT in", values, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatNotIn(List<String> values) {
            addCriterion("COMMISSION_TRANSFER_STAT not in", values, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatBetween(String value1, String value2) {
            addCriterion("COMMISSION_TRANSFER_STAT between", value1, value2, "commissionTransferStat");
            return (Criteria) this;
        }

        public Criteria andCommissionTransferStatNotBetween(String value1, String value2) {
            addCriterion("COMMISSION_TRANSFER_STAT not between", value1, value2, "commissionTransferStat");
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