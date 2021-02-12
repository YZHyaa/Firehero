package com.competition.firemon.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatewayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GatewayExample() {
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

        public Criteria andGatewayIdIsNull() {
            addCriterion("gateway_id is null");
            return (Criteria) this;
        }

        public Criteria andGatewayIdIsNotNull() {
            addCriterion("gateway_id is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayIdEqualTo(Integer value) {
            addCriterion("gateway_id =", value, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdNotEqualTo(Integer value) {
            addCriterion("gateway_id <>", value, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdGreaterThan(Integer value) {
            addCriterion("gateway_id >", value, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gateway_id >=", value, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdLessThan(Integer value) {
            addCriterion("gateway_id <", value, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdLessThanOrEqualTo(Integer value) {
            addCriterion("gateway_id <=", value, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdIn(List<Integer> values) {
            addCriterion("gateway_id in", values, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdNotIn(List<Integer> values) {
            addCriterion("gateway_id not in", values, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdBetween(Integer value1, Integer value2) {
            addCriterion("gateway_id between", value1, value2, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gateway_id not between", value1, value2, "gatewayId");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeIsNull() {
            addCriterion("gateway_createtime is null");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeIsNotNull() {
            addCriterion("gateway_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeEqualTo(Date value) {
            addCriterion("gateway_createtime =", value, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeNotEqualTo(Date value) {
            addCriterion("gateway_createtime <>", value, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeGreaterThan(Date value) {
            addCriterion("gateway_createtime >", value, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gateway_createtime >=", value, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeLessThan(Date value) {
            addCriterion("gateway_createtime <", value, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("gateway_createtime <=", value, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeIn(List<Date> values) {
            addCriterion("gateway_createtime in", values, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeNotIn(List<Date> values) {
            addCriterion("gateway_createtime not in", values, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeBetween(Date value1, Date value2) {
            addCriterion("gateway_createtime between", value1, value2, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("gateway_createtime not between", value1, value2, "gatewayCreatetime");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzIsNull() {
            addCriterion("gateway_ghz is null");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzIsNotNull() {
            addCriterion("gateway_ghz is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzEqualTo(String value) {
            addCriterion("gateway_ghz =", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzNotEqualTo(String value) {
            addCriterion("gateway_ghz <>", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzGreaterThan(String value) {
            addCriterion("gateway_ghz >", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_ghz >=", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzLessThan(String value) {
            addCriterion("gateway_ghz <", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzLessThanOrEqualTo(String value) {
            addCriterion("gateway_ghz <=", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzLike(String value) {
            addCriterion("gateway_ghz like", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzNotLike(String value) {
            addCriterion("gateway_ghz not like", value, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzIn(List<String> values) {
            addCriterion("gateway_ghz in", values, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzNotIn(List<String> values) {
            addCriterion("gateway_ghz not in", values, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzBetween(String value1, String value2) {
            addCriterion("gateway_ghz between", value1, value2, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayGhzNotBetween(String value1, String value2) {
            addCriterion("gateway_ghz not between", value1, value2, "gatewayGhz");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateIsNull() {
            addCriterion("gateway_transfer_rate is null");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateIsNotNull() {
            addCriterion("gateway_transfer_rate is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateEqualTo(String value) {
            addCriterion("gateway_transfer_rate =", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateNotEqualTo(String value) {
            addCriterion("gateway_transfer_rate <>", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateGreaterThan(String value) {
            addCriterion("gateway_transfer_rate >", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_transfer_rate >=", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateLessThan(String value) {
            addCriterion("gateway_transfer_rate <", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateLessThanOrEqualTo(String value) {
            addCriterion("gateway_transfer_rate <=", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateLike(String value) {
            addCriterion("gateway_transfer_rate like", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateNotLike(String value) {
            addCriterion("gateway_transfer_rate not like", value, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateIn(List<String> values) {
            addCriterion("gateway_transfer_rate in", values, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateNotIn(List<String> values) {
            addCriterion("gateway_transfer_rate not in", values, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateBetween(String value1, String value2) {
            addCriterion("gateway_transfer_rate between", value1, value2, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayTransferRateNotBetween(String value1, String value2) {
            addCriterion("gateway_transfer_rate not between", value1, value2, "gatewayTransferRate");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryIsNull() {
            addCriterion("gateway_flash_memory is null");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryIsNotNull() {
            addCriterion("gateway_flash_memory is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryEqualTo(String value) {
            addCriterion("gateway_flash_memory =", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryNotEqualTo(String value) {
            addCriterion("gateway_flash_memory <>", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryGreaterThan(String value) {
            addCriterion("gateway_flash_memory >", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_flash_memory >=", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryLessThan(String value) {
            addCriterion("gateway_flash_memory <", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryLessThanOrEqualTo(String value) {
            addCriterion("gateway_flash_memory <=", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryLike(String value) {
            addCriterion("gateway_flash_memory like", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryNotLike(String value) {
            addCriterion("gateway_flash_memory not like", value, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryIn(List<String> values) {
            addCriterion("gateway_flash_memory in", values, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryNotIn(List<String> values) {
            addCriterion("gateway_flash_memory not in", values, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryBetween(String value1, String value2) {
            addCriterion("gateway_flash_memory between", value1, value2, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayFlashMemoryNotBetween(String value1, String value2) {
            addCriterion("gateway_flash_memory not between", value1, value2, "gatewayFlashMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryIsNull() {
            addCriterion("gateway_memory is null");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryIsNotNull() {
            addCriterion("gateway_memory is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryEqualTo(String value) {
            addCriterion("gateway_memory =", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryNotEqualTo(String value) {
            addCriterion("gateway_memory <>", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryGreaterThan(String value) {
            addCriterion("gateway_memory >", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_memory >=", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryLessThan(String value) {
            addCriterion("gateway_memory <", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryLessThanOrEqualTo(String value) {
            addCriterion("gateway_memory <=", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryLike(String value) {
            addCriterion("gateway_memory like", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryNotLike(String value) {
            addCriterion("gateway_memory not like", value, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryIn(List<String> values) {
            addCriterion("gateway_memory in", values, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryNotIn(List<String> values) {
            addCriterion("gateway_memory not in", values, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryBetween(String value1, String value2) {
            addCriterion("gateway_memory between", value1, value2, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andGatewayMemoryNotBetween(String value1, String value2) {
            addCriterion("gateway_memory not between", value1, value2, "gatewayMemory");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionIsNull() {
            addCriterion("firmware_version is null");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionIsNotNull() {
            addCriterion("firmware_version is not null");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionEqualTo(String value) {
            addCriterion("firmware_version =", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionNotEqualTo(String value) {
            addCriterion("firmware_version <>", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionGreaterThan(String value) {
            addCriterion("firmware_version >", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionGreaterThanOrEqualTo(String value) {
            addCriterion("firmware_version >=", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionLessThan(String value) {
            addCriterion("firmware_version <", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionLessThanOrEqualTo(String value) {
            addCriterion("firmware_version <=", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionLike(String value) {
            addCriterion("firmware_version like", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionNotLike(String value) {
            addCriterion("firmware_version not like", value, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionIn(List<String> values) {
            addCriterion("firmware_version in", values, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionNotIn(List<String> values) {
            addCriterion("firmware_version not in", values, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionBetween(String value1, String value2) {
            addCriterion("firmware_version between", value1, value2, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andFirmwareVersionNotBetween(String value1, String value2) {
            addCriterion("firmware_version not between", value1, value2, "firmwareVersion");
            return (Criteria) this;
        }

        public Criteria andGatewayMacIsNull() {
            addCriterion("gateway_mac is null");
            return (Criteria) this;
        }

        public Criteria andGatewayMacIsNotNull() {
            addCriterion("gateway_mac is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayMacEqualTo(String value) {
            addCriterion("gateway_mac =", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacNotEqualTo(String value) {
            addCriterion("gateway_mac <>", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacGreaterThan(String value) {
            addCriterion("gateway_mac >", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_mac >=", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacLessThan(String value) {
            addCriterion("gateway_mac <", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacLessThanOrEqualTo(String value) {
            addCriterion("gateway_mac <=", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacLike(String value) {
            addCriterion("gateway_mac like", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacNotLike(String value) {
            addCriterion("gateway_mac not like", value, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacIn(List<String> values) {
            addCriterion("gateway_mac in", values, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacNotIn(List<String> values) {
            addCriterion("gateway_mac not in", values, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacBetween(String value1, String value2) {
            addCriterion("gateway_mac between", value1, value2, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andGatewayMacNotBetween(String value1, String value2) {
            addCriterion("gateway_mac not between", value1, value2, "gatewayMac");
            return (Criteria) this;
        }

        public Criteria andFirstDnsIsNull() {
            addCriterion("first_dns is null");
            return (Criteria) this;
        }

        public Criteria andFirstDnsIsNotNull() {
            addCriterion("first_dns is not null");
            return (Criteria) this;
        }

        public Criteria andFirstDnsEqualTo(String value) {
            addCriterion("first_dns =", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsNotEqualTo(String value) {
            addCriterion("first_dns <>", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsGreaterThan(String value) {
            addCriterion("first_dns >", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsGreaterThanOrEqualTo(String value) {
            addCriterion("first_dns >=", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsLessThan(String value) {
            addCriterion("first_dns <", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsLessThanOrEqualTo(String value) {
            addCriterion("first_dns <=", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsLike(String value) {
            addCriterion("first_dns like", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsNotLike(String value) {
            addCriterion("first_dns not like", value, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsIn(List<String> values) {
            addCriterion("first_dns in", values, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsNotIn(List<String> values) {
            addCriterion("first_dns not in", values, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsBetween(String value1, String value2) {
            addCriterion("first_dns between", value1, value2, "firstDns");
            return (Criteria) this;
        }

        public Criteria andFirstDnsNotBetween(String value1, String value2) {
            addCriterion("first_dns not between", value1, value2, "firstDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsIsNull() {
            addCriterion("next_dns is null");
            return (Criteria) this;
        }

        public Criteria andNextDnsIsNotNull() {
            addCriterion("next_dns is not null");
            return (Criteria) this;
        }

        public Criteria andNextDnsEqualTo(String value) {
            addCriterion("next_dns =", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsNotEqualTo(String value) {
            addCriterion("next_dns <>", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsGreaterThan(String value) {
            addCriterion("next_dns >", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsGreaterThanOrEqualTo(String value) {
            addCriterion("next_dns >=", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsLessThan(String value) {
            addCriterion("next_dns <", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsLessThanOrEqualTo(String value) {
            addCriterion("next_dns <=", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsLike(String value) {
            addCriterion("next_dns like", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsNotLike(String value) {
            addCriterion("next_dns not like", value, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsIn(List<String> values) {
            addCriterion("next_dns in", values, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsNotIn(List<String> values) {
            addCriterion("next_dns not in", values, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsBetween(String value1, String value2) {
            addCriterion("next_dns between", value1, value2, "nextDns");
            return (Criteria) this;
        }

        public Criteria andNextDnsNotBetween(String value1, String value2) {
            addCriterion("next_dns not between", value1, value2, "nextDns");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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