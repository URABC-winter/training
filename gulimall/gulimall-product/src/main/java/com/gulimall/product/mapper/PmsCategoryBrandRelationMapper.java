package com.gulimall.product.mapper;

import java.util.List;
import com.gulimall.product.domain.PmsCategoryBrandRelation;

/**
 * 品牌分类关联Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-23
 */
public interface PmsCategoryBrandRelationMapper 
{
    /**
     * 查询品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    public List<PmsCategoryBrandRelation> selectPmsCategoryBrandRelationById(Long id);

    /**
     * 查询品牌分类关联列表
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 品牌分类关联集合
     */
    public List<PmsCategoryBrandRelation> selectPmsCategoryBrandRelationList(PmsCategoryBrandRelation pmsCategoryBrandRelation);

    /**
     * 新增品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public int insertPmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation);

    /**
     * 修改品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    public int updatePmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation);

    /**
     * 删除品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 结果
     */
    public int deletePmsCategoryBrandRelationById(Long id);

    /**
     * 批量删除品牌分类关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePmsCategoryBrandRelationByIds(Long[] ids);
}
