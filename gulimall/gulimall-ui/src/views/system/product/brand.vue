<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="品牌名" prop="name">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入品牌名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="检索首字母" prop="firstLetter" label-width="85px">
          <el-input
            v-model="queryParams.firstLetter"
            placeholder="请输入检索首字母"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input
            v-model="queryParams.sort"
            placeholder="请输入排序"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:brand:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:brand:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:brand:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:brand:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="brandList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="品牌id" width="80px" align="center" prop="brandId" />
        <el-table-column label="品牌名" width="80px" align="center" prop="name" />
        <el-table-column label="品牌logo地址" align="center" prop="logo">
          <template slot-scope="scope">
            <img :src="scope.row.logo" style="width: 100px; height: 80px">
          </template>
        </el-table-column>
        <el-table-column label="介绍" align="center" prop="descript" />
        <el-table-column label="显示状态" align="center" prop="showStatus">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.showStatus" active-color="#13ce66" inactive-color="#ff4949"
            :active-value="1" :inactive-value="0" @change="updateBrandStatus(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="检索首字母" align="center" prop="firstLetter" />
        <el-table-column label="排序" align="center" prop="sort" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-view" @click="handleRelation(scope.row.brandId)">关联分类</el-button>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:brand:edit']">修改</el-button>
            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:brand:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改品牌对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-form-item label="品牌名" prop="name">
            <el-input v-model="form.name" placeholder="请输入品牌名" />
          </el-form-item>
          <el-form-item label="品牌logo地址" prop="logo">
            <!-- <el-input v-model="form.logo" type="textarea" placeholder="请输入内容" /> -->
            <single-upload v-model="form.logo"></single-upload>
          </el-form-item>
          <el-form-item label="介绍" prop="descript">
            <el-input v-model="form.descript" type="textarea" placeholder="请输入内容" />
          </el-form-item>
          <el-form-item label="显示状态" prop="showStatus">
            <el-switch v-model="form.showStatus" active-color="#13ce66" inactive-color="#ff4949"
            :active-value="1" :inactive-value="0"></el-switch>
          </el-form-item>
          <el-form-item label="检索首字母" prop="firstLetter">
            <el-input v-model="form.firstLetter" placeholder="请输入检索首字母" />
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input v-model.number="form.sort" placeholder="请输入排序" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog title="关联分类" :visible.sync="catelogDialogVisible" width="30%">
        <el-popover placement="right-end" v-model="popCatelogSelectVisible">
          <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
          <div style="text-align: right; margin: 0">
            <el-button size="mini" type="text" @click="popCatelogSelectVisible = false">取消</el-button>
            <el-button type="primary" size="mini" @click="addCatelogSelect">确定</el-button>
          </div>
          <el-button slot="reference">新增关联</el-button>
        </el-popover>
        <el-table :data="cateRelationTableData" style="width: 100%">
          <el-table-column prop="id" label="#"></el-table-column>
          <el-table-column prop="brandName" label="品牌名"></el-table-column>
          <el-table-column prop="catelogName" label="分类名"></el-table-column>
          <el-table-column fixed="right" header-align="center" align="center" label="操作">
            <template slot-scope="scope">
              <el-button
                type="text"
                size="small"
                @click="deleteCateRelationHandle(scope.row.id, scope.row.brandId)"
              >移除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cateRelationDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="cateRelationDialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listBrand, getBrand, delBrand, addBrand, updateBrand } from "@/api/system/brand";
  import {listBrandRelation, addBrandRelation, deleteBrandRelation } from "@/api/system/brandRelation";
  import singleUpload from '../../../components/FileUpload/singleUpload.vue';
  import CategoryCascader from '../../../components/Product/category-cascader.vue';
  export default {
  components: { singleUpload, CategoryCascader },
    name: "Brand",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 品牌表格数据
        brandList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          logo: null,
          descript: null,
          showStatus: null,
          firstLetter: null,
          sort: null
        },
        // 表单参数
        form: {
          brandId: 0,
          name: "",
          logo: "",
          descript: "",
          showStatus: 1,
          firstLetter: "",
          sort: 0
        },
        brandRelationForm: {
          brandId: 0,
          brandName: "",
          catelogId: 0,
          catelogName: ""
        },
        // 表单校验
        rules: {
          name : [{ required: true, message: "品牌名不能为空", trigger: "blur" }],
          logo : [{ required: true, message: "品牌logo地址不能为空", trigger: "blur" }],
          descript : [{required: true, message: "介绍不能为空", trigger: "blur" }],
          showStatus: [{ required: true, message: "显示状态[0-不显示；1-显示]不能为空", trigger: "blur"}],
          firstLetter: [{
            validator: (rule, value, callback) => {
              if (value === "" || value == null) {
                callback(new Error("首字母必须填写"));
              } else if (!/^[a-zA-Z]$/.test(value)) {
                callback(new Error("首字母必须a-z或者A-Z之间"));
              } else {
                callback();
              }
            },
            trigger: "blur"
          }],
          sort: [{
              validator: (rule, value, callback) => {
                if (value === "" || value == null) {
                  callback(new Error("排序字段必须填写"));
                } else if (!Number.isInteger(value) || value<0) {
                  callback(new Error("排序必须是一个大于等于0的整数"));
                } else {
                  callback();
                }
              },
              trigger: "blur"
          }]
        },
        brandId: null,
        catelogPath: [],
        cateRelationTableData: [],
        catelogDialogVisible: false,
        popCatelogSelectVisible: false
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询品牌列表 */
      getList() {
        this.loading = true;
        listBrand(this.queryParams).then(response => {
          this.brandList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          brandId: null,
          name: null,
          logo: null,
          descript: null,
          showStatus: null,
          firstLetter: null,
          sort: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.brandId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      // 更新显示状态
      updateBrandStatus(data){
        // let {brandId,status} = data
        updateBrand(data).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
        });
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加品牌";
      },
      /** 关联分类操作 */
      handleRelation(brandId) {
        console.log("brandId =", brandId);
        this.catelogDialogVisible = true;
        this.brandId = brandId;
        this.getCateRelationList();
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const brandId = row.brandId || this.ids
        getBrand(brandId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改品牌";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.brandId != null) {
              updateBrand(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addBrand(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const brandIds = row.brandId || this.ids;
        this.$modal.confirm('是否确认删除品牌编号为"' + brandIds + '"的数据项？').then(function() {
          return delBrand(brandIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/brand/export', {
          ...this.queryParams
        }, `brand_${new Date().getTime()}.xlsx`)
      },
      getCateRelationList() {
        listBrandRelation(this.brandId).then(response => {
          console.log("cateRelationTableData =", response);
          this.cateRelationTableData = response.rows;
        });
      },

      addCatelogSelect() {
        this.popCatelogSelectVisible = false;
        this.brandRelationForm.brandId = this.brandId;
        this.brandRelationForm.catelogId = this.catelogPath[this.catelogPath.length-1];
        addBrandRelation(this.brandRelationForm).then(response => {
          this.getCateRelationList();
        });
      },

      deleteCateRelationHandle(id, brandId) {
        deleteBrandRelation(id).then(response => {
          this.getCateRelationList();
        });
      }

    }
  };
  </script>

  <style>
  input[aria-hidden=true] {
    display: none !important;
  }
  </style>
  