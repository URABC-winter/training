package com.gulimall.coupou.service;

import java.util.List;
import com.gulimall.coupou.domain.SmsSkuFullReduction;

/**
 * 商品满减信息Service接口
 * 
 * @author ruoyi
 * @date 2024-11-23
 */
public interface ISmsSkuFullReductionService 
{
    /**
     * 查询商品满减信息
     * 
     * @param id 商品满减信息主键
     * @return 商品满减信息
     */
    public SmsSkuFullReduction selectSmsSkuFullReductionById(Long id);

    /**
     * 查询商品满减信息列表
     * 
     * @param smsSkuFullReduction 商品满减信息
     * @return 商品满减信息集合
     */
    public List<SmsSkuFullReduction> selectSmsSkuFullReductionList(SmsSkuFullReduction smsSkuFullReduction);

    /**
     * 新增商品满减信息
     * 
     * @param smsSkuFullReduction 商品满减信息
     * @return 结果
     */
    public int insertSmsSkuFullReduction(SmsSkuFullReduction smsSkuFullReduction);

    /**
     * 修改商品满减信息
     * 
     * @param smsSkuFullReduction 商品满减信息
     * @return 结果
     */
    public int updateSmsSkuFullReduction(SmsSkuFullReduction smsSkuFullReduction);

    /**
     * 批量删除商品满减信息
     * 
     * @param ids 需要删除的商品满减信息主键集合
     * @return 结果
     */
    public int deleteSmsSkuFullReductionByIds(Long[] ids);

    /**
     * 删除商品满减信息信息
     * 
     * @param id 商品满减信息主键
     * @return 结果
     */
    public int deleteSmsSkuFullReductionById(Long id);
}
