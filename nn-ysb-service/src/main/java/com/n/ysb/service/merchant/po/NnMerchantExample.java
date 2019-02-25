package com.n.ysb.service.merchant.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NnMerchantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NnMerchantExample() {
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

        public Criteria andMerchantCodeIsNull() {
            addCriterion("MERCHANT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIsNotNull() {
            addCriterion("MERCHANT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeEqualTo(String value) {
            addCriterion("MERCHANT_CODE =", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotEqualTo(String value) {
            addCriterion("MERCHANT_CODE <>", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThan(String value) {
            addCriterion("MERCHANT_CODE >", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_CODE >=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThan(String value) {
            addCriterion("MERCHANT_CODE <", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_CODE <=", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeLike(String value) {
            addCriterion("MERCHANT_CODE like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotLike(String value) {
            addCriterion("MERCHANT_CODE not like", value, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeIn(List<String> values) {
            addCriterion("MERCHANT_CODE in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotIn(List<String> values) {
            addCriterion("MERCHANT_CODE not in", values, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeBetween(String value1, String value2) {
            addCriterion("MERCHANT_CODE between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_CODE not between", value1, value2, "merchantCode");
            return (Criteria) this;
        }

        public Criteria andLoginMobileIsNull() {
            addCriterion("LOGIN_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andLoginMobileIsNotNull() {
            addCriterion("LOGIN_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andLoginMobileEqualTo(String value) {
            addCriterion("LOGIN_MOBILE =", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileNotEqualTo(String value) {
            addCriterion("LOGIN_MOBILE <>", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileGreaterThan(String value) {
            addCriterion("LOGIN_MOBILE >", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_MOBILE >=", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileLessThan(String value) {
            addCriterion("LOGIN_MOBILE <", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_MOBILE <=", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileLike(String value) {
            addCriterion("LOGIN_MOBILE like", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileNotLike(String value) {
            addCriterion("LOGIN_MOBILE not like", value, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileIn(List<String> values) {
            addCriterion("LOGIN_MOBILE in", values, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileNotIn(List<String> values) {
            addCriterion("LOGIN_MOBILE not in", values, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileBetween(String value1, String value2) {
            addCriterion("LOGIN_MOBILE between", value1, value2, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginMobileNotBetween(String value1, String value2) {
            addCriterion("LOGIN_MOBILE not between", value1, value2, "loginMobile");
            return (Criteria) this;
        }

        public Criteria andLoginPwdIsNull() {
            addCriterion("LOGIN_PWD is null");
            return (Criteria) this;
        }

        public Criteria andLoginPwdIsNotNull() {
            addCriterion("LOGIN_PWD is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPwdEqualTo(String value) {
            addCriterion("LOGIN_PWD =", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotEqualTo(String value) {
            addCriterion("LOGIN_PWD <>", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdGreaterThan(String value) {
            addCriterion("LOGIN_PWD >", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_PWD >=", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdLessThan(String value) {
            addCriterion("LOGIN_PWD <", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_PWD <=", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdLike(String value) {
            addCriterion("LOGIN_PWD like", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotLike(String value) {
            addCriterion("LOGIN_PWD not like", value, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdIn(List<String> values) {
            addCriterion("LOGIN_PWD in", values, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotIn(List<String> values) {
            addCriterion("LOGIN_PWD not in", values, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdBetween(String value1, String value2) {
            addCriterion("LOGIN_PWD between", value1, value2, "loginPwd");
            return (Criteria) this;
        }

        public Criteria andLoginPwdNotBetween(String value1, String value2) {
            addCriterion("LOGIN_PWD not between", value1, value2, "loginPwd");
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

        public Criteria andBindMobileIsNull() {
            addCriterion("BIND_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andBindMobileIsNotNull() {
            addCriterion("BIND_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andBindMobileEqualTo(String value) {
            addCriterion("BIND_MOBILE =", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileNotEqualTo(String value) {
            addCriterion("BIND_MOBILE <>", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileGreaterThan(String value) {
            addCriterion("BIND_MOBILE >", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileGreaterThanOrEqualTo(String value) {
            addCriterion("BIND_MOBILE >=", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileLessThan(String value) {
            addCriterion("BIND_MOBILE <", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileLessThanOrEqualTo(String value) {
            addCriterion("BIND_MOBILE <=", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileLike(String value) {
            addCriterion("BIND_MOBILE like", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileNotLike(String value) {
            addCriterion("BIND_MOBILE not like", value, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileIn(List<String> values) {
            addCriterion("BIND_MOBILE in", values, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileNotIn(List<String> values) {
            addCriterion("BIND_MOBILE not in", values, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileBetween(String value1, String value2) {
            addCriterion("BIND_MOBILE between", value1, value2, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBindMobileNotBetween(String value1, String value2) {
            addCriterion("BIND_MOBILE not between", value1, value2, "bindMobile");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("BANK_ACCOUNT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("BANK_ACCOUNT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT_NAME >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("BANK_ACCOUNT_NAME <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("BANK_ACCOUNT_NAME like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("BANK_ACCOUNT_NAME not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NAME in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NAME not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NAME between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NAME not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNull() {
            addCriterion("BANK_ACCOUNT_NO is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNotNull() {
            addCriterion("BANK_ACCOUNT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO =", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO <>", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT_NO >", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO >=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThan(String value) {
            addCriterion("BANK_ACCOUNT_NO <", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO <=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLike(String value) {
            addCriterion("BANK_ACCOUNT_NO like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotLike(String value) {
            addCriterion("BANK_ACCOUNT_NO not like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NO in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NO not in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NO between", value1, value2, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NO not between", value1, value2, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
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

        public Criteria andIdCardStartIsNull() {
            addCriterion("ID_CARD_START is null");
            return (Criteria) this;
        }

        public Criteria andIdCardStartIsNotNull() {
            addCriterion("ID_CARD_START is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardStartEqualTo(Date value) {
            addCriterion("ID_CARD_START =", value, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartNotEqualTo(Date value) {
            addCriterion("ID_CARD_START <>", value, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartGreaterThan(Date value) {
            addCriterion("ID_CARD_START >", value, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartGreaterThanOrEqualTo(Date value) {
            addCriterion("ID_CARD_START >=", value, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartLessThan(Date value) {
            addCriterion("ID_CARD_START <", value, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartLessThanOrEqualTo(Date value) {
            addCriterion("ID_CARD_START <=", value, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartIn(List<Date> values) {
            addCriterion("ID_CARD_START in", values, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartNotIn(List<Date> values) {
            addCriterion("ID_CARD_START not in", values, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartBetween(Date value1, Date value2) {
            addCriterion("ID_CARD_START between", value1, value2, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardStartNotBetween(Date value1, Date value2) {
            addCriterion("ID_CARD_START not between", value1, value2, "idCardStart");
            return (Criteria) this;
        }

        public Criteria andIdCardEndIsNull() {
            addCriterion("ID_CARD_END is null");
            return (Criteria) this;
        }

        public Criteria andIdCardEndIsNotNull() {
            addCriterion("ID_CARD_END is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEndEqualTo(Date value) {
            addCriterion("ID_CARD_END =", value, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndNotEqualTo(Date value) {
            addCriterion("ID_CARD_END <>", value, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndGreaterThan(Date value) {
            addCriterion("ID_CARD_END >", value, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndGreaterThanOrEqualTo(Date value) {
            addCriterion("ID_CARD_END >=", value, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndLessThan(Date value) {
            addCriterion("ID_CARD_END <", value, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndLessThanOrEqualTo(Date value) {
            addCriterion("ID_CARD_END <=", value, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndIn(List<Date> values) {
            addCriterion("ID_CARD_END in", values, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndNotIn(List<Date> values) {
            addCriterion("ID_CARD_END not in", values, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndBetween(Date value1, Date value2) {
            addCriterion("ID_CARD_END between", value1, value2, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andIdCardEndNotBetween(Date value1, Date value2) {
            addCriterion("ID_CARD_END not between", value1, value2, "idCardEnd");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagIsNull() {
            addCriterion("FEE_SET_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagIsNotNull() {
            addCriterion("FEE_SET_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagEqualTo(String value) {
            addCriterion("FEE_SET_FLAG =", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagNotEqualTo(String value) {
            addCriterion("FEE_SET_FLAG <>", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagGreaterThan(String value) {
            addCriterion("FEE_SET_FLAG >", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_SET_FLAG >=", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagLessThan(String value) {
            addCriterion("FEE_SET_FLAG <", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagLessThanOrEqualTo(String value) {
            addCriterion("FEE_SET_FLAG <=", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagLike(String value) {
            addCriterion("FEE_SET_FLAG like", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagNotLike(String value) {
            addCriterion("FEE_SET_FLAG not like", value, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagIn(List<String> values) {
            addCriterion("FEE_SET_FLAG in", values, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagNotIn(List<String> values) {
            addCriterion("FEE_SET_FLAG not in", values, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagBetween(String value1, String value2) {
            addCriterion("FEE_SET_FLAG between", value1, value2, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetFlagNotBetween(String value1, String value2) {
            addCriterion("FEE_SET_FLAG not between", value1, value2, "feeSetFlag");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeIsNull() {
            addCriterion("FEE_SET_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeIsNotNull() {
            addCriterion("FEE_SET_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeEqualTo(String value) {
            addCriterion("FEE_SET_TYPE =", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeNotEqualTo(String value) {
            addCriterion("FEE_SET_TYPE <>", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeGreaterThan(String value) {
            addCriterion("FEE_SET_TYPE >", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_SET_TYPE >=", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeLessThan(String value) {
            addCriterion("FEE_SET_TYPE <", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeLessThanOrEqualTo(String value) {
            addCriterion("FEE_SET_TYPE <=", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeLike(String value) {
            addCriterion("FEE_SET_TYPE like", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeNotLike(String value) {
            addCriterion("FEE_SET_TYPE not like", value, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeIn(List<String> values) {
            addCriterion("FEE_SET_TYPE in", values, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeNotIn(List<String> values) {
            addCriterion("FEE_SET_TYPE not in", values, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeBetween(String value1, String value2) {
            addCriterion("FEE_SET_TYPE between", value1, value2, "feeSetType");
            return (Criteria) this;
        }

        public Criteria andFeeSetTypeNotBetween(String value1, String value2) {
            addCriterion("FEE_SET_TYPE not between", value1, value2, "feeSetType");
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

        public Criteria andAuthDateIsNull() {
            addCriterion("AUTH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAuthDateIsNotNull() {
            addCriterion("AUTH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAuthDateEqualTo(Date value) {
            addCriterion("AUTH_DATE =", value, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateNotEqualTo(Date value) {
            addCriterion("AUTH_DATE <>", value, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateGreaterThan(Date value) {
            addCriterion("AUTH_DATE >", value, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateGreaterThanOrEqualTo(Date value) {
            addCriterion("AUTH_DATE >=", value, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateLessThan(Date value) {
            addCriterion("AUTH_DATE <", value, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateLessThanOrEqualTo(Date value) {
            addCriterion("AUTH_DATE <=", value, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateIn(List<Date> values) {
            addCriterion("AUTH_DATE in", values, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateNotIn(List<Date> values) {
            addCriterion("AUTH_DATE not in", values, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateBetween(Date value1, Date value2) {
            addCriterion("AUTH_DATE between", value1, value2, "authDate");
            return (Criteria) this;
        }

        public Criteria andAuthDateNotBetween(Date value1, Date value2) {
            addCriterion("AUTH_DATE not between", value1, value2, "authDate");
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