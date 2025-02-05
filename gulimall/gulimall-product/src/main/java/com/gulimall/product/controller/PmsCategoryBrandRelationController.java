package com.gulimall.product.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.gulimall.product.domain.PmsBrand;
import com.gulimall.product.domain.PmsCategory;
import com.gulimall.product.service.IPmsBrandService;
import com.gulimall.product.service.IPmsCategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gulimall.common.annotation.Log;
import com.gulimall.common.core.controller.BaseController;
import com.gulimall.common.core.domain.AjaxResult;
import com.gulimall.common.enums.BusinessType;
import com.gulimall.product.domain.PmsCategoryBrandRelation;
import com.gulimall.product.service.IPmsCategoryBrandRelationService;
import com.gulimall.common.utils.poi.ExcelUtil;
import com.gulimall.common.core.page.TableDataInfo;

/**
 * 品牌分类关联Controller
 * 
 * @author ruoyi
 * @date 2024-11-23
 */
@RestController
@RequestMapping("/product/brandRelation")
public class PmsCategoryBrandRelationController extends BaseController
{
    @Autowired
    private IPmsCategoryBrandRelationService pmsCategoryBrandRelationService;
    @Autowired
    private IPmsBrandService pmsBrandService;
    @Autowired
    private IPmsCategoryService pmsCategoryService;

    /**
     * 查询品牌分类关联列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        System.out.println("brandId =" + pmsCategoryBrandRelation.getBrandId());
        startPage();
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        return getDataTable(list);
    }

    @GetMapping("/listById/{brandId}")
    public TableDataInfo listById(@PathVariable("brandId") Long brandId)
    {
        System.out.println("brandId =" + brandId);
        startPage();
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationById(brandId);
        return getDataTable(list);
    }

    /**
     * 导出品牌分类关联列表
     */
    @Log(title = "品牌分类关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        List<PmsCategoryBrandRelation> list = pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationList(pmsCategoryBrandRelation);
        ExcelUtil<PmsCategoryBrandRelation> util = new ExcelUtil<PmsCategoryBrandRelation>(PmsCategoryBrandRelation.class);
        util.exportExcel(response, list, "品牌分类关联数据");
    }

    /**
     * 获取品牌分类关联详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsCategoryBrandRelationService.selectPmsCategoryBrandRelationById(id));
    }

    /**
     * 新增品牌分类关联
     */
    @Log(title = "品牌分类关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        PmsCategory pmsCategory = pmsCategoryService.selectPmsCategoryByCatId(pmsCategoryBrandRelation.getCatelogId());
        PmsBrand pmsBrand = pmsBrandService.selectPmsBrandByBrandId(pmsCategoryBrandRelation.getBrandId());
        pmsCategoryBrandRelation.setBrandName(pmsBrand.getName());
        pmsCategoryBrandRelation.setCatelogName(pmsCategory.getName());
        return toAjax(pmsCategoryBrandRelationService.insertPmsCategoryBrandRelation(pmsCategoryBrandRelation));
    }

    /**
     * 修改品牌分类关联
     */
    @Log(title = "品牌分类关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCategoryBrandRelation pmsCategoryBrandRelation)
    {
        return toAjax(pmsCategoryBrandRelationService.updatePmsCategoryBrandRelation(pmsCategoryBrandRelation));
    }

    /**
     * 删除品牌分类关联
     */
    @Log(title = "品牌分类关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsCategoryBrandRelationService.deletePmsCategoryBrandRelationByIds(ids));
    }
}
