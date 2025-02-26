package com.gulimall.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.gulimall.common.annotation.Excel;
import com.gulimall.common.core.domain.BaseEntity;

/**
 * 支付信息对象 oms_payment_info
 * 
 * @author ruoyi
 * @date 2024-11-23
 */
public class OmsPaymentInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 订单号（对外业务号） */
    @Excel(name = "订单号", readConverterExp = "对=外业务号")
    private String orderSn;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 支付宝交易流水号 */
    @Excel(name = "支付宝交易流水号")
    private String alipayTradeNo;

    /** 支付总金额 */
    @Excel(name = "支付总金额")
    private BigDecimal totalAmount;

    /** 交易内容 */
    @Excel(name = "交易内容")
    private String subject;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String paymentStatus;

    /** 确认时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "确认时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date confirmTime;

    /** 回调内容 */
    @Excel(name = "回调内容")
    private String callbackContent;

    /** 回调时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回调时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date callbackTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderSn(String orderSn) 
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn() 
    {
        return orderSn;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setAlipayTradeNo(String alipayTradeNo) 
    {
        this.alipayTradeNo = alipayTradeNo;
    }

    public String getAlipayTradeNo() 
    {
        return alipayTradeNo;
    }
    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }
    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }
    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() 
    {
        return paymentStatus;
    }
    public void setConfirmTime(Date confirmTime) 
    {
        this.confirmTime = confirmTime;
    }

    public Date getConfirmTime() 
    {
        return confirmTime;
    }
    public void setCallbackContent(String callbackContent) 
    {
        this.callbackContent = callbackContent;
    }

    public String getCallbackContent() 
    {
        return callbackContent;
    }
    public void setCallbackTime(Date callbackTime) 
    {
        this.callbackTime = callbackTime;
    }

    public Date getCallbackTime() 
    {
        return callbackTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderSn", getOrderSn())
            .append("orderId", getOrderId())
            .append("alipayTradeNo", getAlipayTradeNo())
            .append("totalAmount", getTotalAmount())
            .append("subject", getSubject())
            .append("paymentStatus", getPaymentStatus())
            .append("createTime", getCreateTime())
            .append("confirmTime", getConfirmTime())
            .append("callbackContent", getCallbackContent())
            .append("callbackTime", getCallbackTime())
            .toString();
    }
}
