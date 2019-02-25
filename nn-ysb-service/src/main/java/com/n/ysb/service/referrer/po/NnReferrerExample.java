package com.n.ysb.service.referrer.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NnReferrerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnReferrerExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("MOBILE =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("MOBILE <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("MOBILE >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("MOBILE <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("MOBILE <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("MOBILE like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("MOBILE not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("MOBILE in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("MOBILE not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("MOBILE between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("MOBILE not between", value1, value2, "mobile");
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

        public Criteria andRefCodeIsNull() {
            addCriterion("REF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRefCodeIsNotNull() {
            addCriterion("REF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRefCodeEqualTo(String value) {
            addCriterion("REF_CODE =", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotEqualTo(String value) {
            addCriterion("REF_CODE <>", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeGreaterThan(String value) {
            addCriterion("REF_CODE >", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REF_CODE >=", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeLessThan(String value) {
            addCriterion("REF_CODE <", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeLessThanOrEqualTo(String value) {
            addCriterion("REF_CODE <=", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeLike(String value) {
            addCriterion("REF_CODE like", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotLike(String value) {
            addCriterion("REF_CODE not like", value, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeIn(List<String> values) {
            addCriterion("REF_CODE in", values, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotIn(List<String> values) {
            addCriterion("REF_CODE not in", values, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeBetween(String value1, String value2) {
            addCriterion("REF_CODE between", value1, value2, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefCodeNotBetween(String value1, String value2) {
            addCriterion("REF_CODE not between", value1, value2, "refCode");
            return (Criteria) this;
        }

        public Criteria andRefLinkIsNull() {
            addCriterion("REF_LINK is null");
            return (Criteria) this;
        }

        public Criteria andRefLinkIsNotNull() {
            addCriterion("REF_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andRefLinkEqualTo(String value) {
            addCriterion("REF_LINK =", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkNotEqualTo(String value) {
            addCriterion("REF_LINK <>", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkGreaterThan(String value) {
            addCriterion("REF_LINK >", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkGreaterThanOrEqualTo(String value) {
            addCriterion("REF_LINK >=", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkLessThan(String value) {
            addCriterion("REF_LINK <", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkLessThanOrEqualTo(String value) {
            addCriterion("REF_LINK <=", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkLike(String value) {
            addCriterion("REF_LINK like", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkNotLike(String value) {
            addCriterion("REF_LINK not like", value, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkIn(List<String> values) {
            addCriterion("REF_LINK in", values, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkNotIn(List<String> values) {
            addCriterion("REF_LINK not in", values, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkBetween(String value1, String value2) {
            addCriterion("REF_LINK between", value1, value2, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefLinkNotBetween(String value1, String value2) {
            addCriterion("REF_LINK not between", value1, value2, "refLink");
            return (Criteria) this;
        }

        public Criteria andRefStatusIsNull() {
            addCriterion("REF_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRefStatusIsNotNull() {
            addCriterion("REF_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRefStatusEqualTo(String value) {
            addCriterion("REF_STATUS =", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusNotEqualTo(String value) {
            addCriterion("REF_STATUS <>", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusGreaterThan(String value) {
            addCriterion("REF_STATUS >", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusGreaterThanOrEqualTo(String value) {
            addCriterion("REF_STATUS >=", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusLessThan(String value) {
            addCriterion("REF_STATUS <", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusLessThanOrEqualTo(String value) {
            addCriterion("REF_STATUS <=", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusLike(String value) {
            addCriterion("REF_STATUS like", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusNotLike(String value) {
            addCriterion("REF_STATUS not like", value, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusIn(List<String> values) {
            addCriterion("REF_STATUS in", values, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusNotIn(List<String> values) {
            addCriterion("REF_STATUS not in", values, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusBetween(String value1, String value2) {
            addCriterion("REF_STATUS between", value1, value2, "refStatus");
            return (Criteria) this;
        }

        public Criteria andRefStatusNotBetween(String value1, String value2) {
            addCriterion("REF_STATUS not between", value1, value2, "refStatus");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNull() {
            addCriterion("COMMISSION_RATE is null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNotNull() {
            addCriterion("COMMISSION_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE =", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE <>", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThan(BigDecimal value) {
            addCriterion("COMMISSION_RATE >", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE >=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThan(BigDecimal value) {
            addCriterion("COMMISSION_RATE <", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION_RATE <=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIn(List<BigDecimal> values) {
            addCriterion("COMMISSION_RATE in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotIn(List<BigDecimal> values) {
            addCriterion("COMMISSION_RATE not in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION_RATE between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION_RATE not between", value1, value2, "commissionRate");
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

        public Criteria andIdCardIsNull() {
            addCriterion("ID_CARD is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("ID_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("ID_CARD =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("ID_CARD <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("ID_CARD >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("ID_CARD >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("ID_CARD <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("ID_CARD <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("ID_CARD like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("ID_CARD not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("ID_CARD in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("ID_CARD not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("ID_CARD between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("ID_CARD not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardIsNull() {
            addCriterion("CK_BANK_CARD is null");
            return (Criteria) this;
        }

        public Criteria andCkBankCardIsNotNull() {
            addCriterion("CK_BANK_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andCkBankCardEqualTo(String value) {
            addCriterion("CK_BANK_CARD =", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardNotEqualTo(String value) {
            addCriterion("CK_BANK_CARD <>", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardGreaterThan(String value) {
            addCriterion("CK_BANK_CARD >", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardGreaterThanOrEqualTo(String value) {
            addCriterion("CK_BANK_CARD >=", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardLessThan(String value) {
            addCriterion("CK_BANK_CARD <", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardLessThanOrEqualTo(String value) {
            addCriterion("CK_BANK_CARD <=", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardLike(String value) {
            addCriterion("CK_BANK_CARD like", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardNotLike(String value) {
            addCriterion("CK_BANK_CARD not like", value, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardIn(List<String> values) {
            addCriterion("CK_BANK_CARD in", values, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardNotIn(List<String> values) {
            addCriterion("CK_BANK_CARD not in", values, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardBetween(String value1, String value2) {
            addCriterion("CK_BANK_CARD between", value1, value2, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andCkBankCardNotBetween(String value1, String value2) {
            addCriterion("CK_BANK_CARD not between", value1, value2, "ckBankCard");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionIsNull() {
            addCriterion("BASIC_COMMISSION is null");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionIsNotNull() {
            addCriterion("BASIC_COMMISSION is not null");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionEqualTo(BigDecimal value) {
            addCriterion("BASIC_COMMISSION =", value, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionNotEqualTo(BigDecimal value) {
            addCriterion("BASIC_COMMISSION <>", value, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionGreaterThan(BigDecimal value) {
            addCriterion("BASIC_COMMISSION >", value, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BASIC_COMMISSION >=", value, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionLessThan(BigDecimal value) {
            addCriterion("BASIC_COMMISSION <", value, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BASIC_COMMISSION <=", value, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionIn(List<BigDecimal> values) {
            addCriterion("BASIC_COMMISSION in", values, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionNotIn(List<BigDecimal> values) {
            addCriterion("BASIC_COMMISSION not in", values, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BASIC_COMMISSION between", value1, value2, "basicCommission");
            return (Criteria) this;
        }

        public Criteria andBasicCommissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BASIC_COMMISSION not between", value1, value2, "basicCommission");
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