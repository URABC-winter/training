package com.gulimall.member.mapper;

import java.util.List;
import com.gulimall.member.domain.UmsGrowthChangeHistory;

/**
 * 成长值变化历史记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-23
 */
public interface UmsGrowthChangeHistoryMapper 
{
    /**
     * 查询成长值变化历史记录
     * 
     * @param id 成长值变化历史记录主键
     * @return 成长值变化历史记录
     */
    public UmsGrowthChangeHistory selectUmsGrowthChangeHistoryById(Long id);

    /**
     * 查询成长值变化历史记录列表
     * 
     * @param umsGrowthChangeHistory 成长值变化历史记录
     * @return 成长值变化历史记录集合
     */
    public List<UmsGrowthChangeHistory> selectUmsGrowthChangeHistoryList(UmsGrowthChangeHistory umsGrowthChangeHistory);

    /**
     * 新增成长值变化历史记录
     * 
     * @param umsGrowthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    public int insertUmsGrowthChangeHistory(UmsGrowthChangeHistory umsGrowthChangeHistory);

    /**
     * 修改成长值变化历史记录
     * 
     * @param umsGrowthChangeHistory 成长值变化历史记录
     * @return 结果
     */
    public int updateUmsGrowthChangeHistory(UmsGrowthChangeHistory umsGrowthChangeHistory);

    /**
     * 删除成长值变化历史记录
     * 
     * @param id 成长值变化历史记录主键
     * @return 结果
     */
    public int deleteUmsGrowthChangeHistoryById(Long id);

    /**
     * 批量删除成长值变化历史记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUmsGrowthChangeHistoryByIds(Long[] ids);
}
