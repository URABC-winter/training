package com.gulimall.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gulimall.product.mapper.PmsCategoryBrandRelationMapper;
import com.gulimall.product.domain.PmsCategoryBrandRelation;
import com.gulimall.product.service.IPmsCategoryBrandRelationService;

/**
 * 品牌分类关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-23
 */
@Service
public class PmsCategoryBrandRelationServiceImpl implements IPmsCategoryBrandRelationService 
{
    @Autowired
    private PmsCategoryBrandRelationMapper pmsCategoryBrandRelationMapper;

    /**
     * 查询品牌分类关联
     * 
     * @param id 品牌分类关联主键
     * @return 品牌分类关联
     */
    @Override
    public List<PmsCategoryBrandRelation> selectPmsCategoryBrandRelationById(Long brandId)
    {
        return pmsCategoryBrandRelationMapper.selectPmsCategoryBrandRelationById(brandId);
    }

    /**
     * 查询品牌分类关联列表
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 品牌分类关联
     */
    @Override
    public List<PmsCategoryBrandRelation> selectPmsCategoryBrandRelationList(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return pmsCategoryBrandRelationMapper.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
    }

    /**
     * 新增品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    @Override
    public int insertPmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return pmsCategoryBrandRelationMapper.insertPmsCategoryBrandRelation(pmsCategoryBrandRelation);
    }

    /**
     * 修改品牌分类关联
     * 
     * @param pmsCategoryBrandRelation 品牌分类关联
     * @return 结果
     */
    @Override
    public int updatePmsCategoryBrandRelation(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return pmsCategoryBrandRelationMapper.updatePmsCategoryBrandRelation(pmsCategoryBrandRelation);
    }

    /**
     * 批量删除品牌分类关联
     * 
     * @param ids 需要删除的品牌分类关联主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryBrandRelationByIds(Long[] ids)
    {
        return pmsCategoryBrandRelationMapper.deletePmsCategoryBrandRelationByIds(ids);
    }

    /**
     * 删除品牌分类关联信息
     * 
     * @param id 品牌分类关联主键
     * @return 结果
     */
    @Override
    public int deletePmsCategoryBrandRelationById(Long id)
    {
        return pmsCategoryBrandRelationMapper.deletePmsCategoryBrandRelationById(id);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        PmsCategoryBrandRelation pmsCategoryBrandRelation = new PmsCategoryBrandRelation();
        pmsCategoryBrandRelation.setBrandId(brandId);
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationMapper.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        list.forEach(item -> {
            item.setBrandName(name);
            pmsCategoryBrandRelationMapper.updatePmsCategoryBrandRelation(item);
        });
    }

    @Override
    public void updateCategory(Long catId, String name) {
        PmsCategoryBrandRelation pmsCategoryBrandRelation = new PmsCategoryBrandRelation();
        pmsCategoryBrandRelation.setCatelogId(catId);
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationMapper.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        list.forEach(item -> {
            item.setCatelogName(name);
            pmsCategoryBrandRelationMapper.updatePmsCategoryBrandRelation(item);
        });
    }
}
