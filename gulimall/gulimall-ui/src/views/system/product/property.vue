<template>
  <el-row :gutter="20">
    <el-col :span="6"><category @node-click="nodeClick"></category></el-col>
    <el-col :span="18">
      <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="组名" prop="attrGroupName">
            <el-input v-model="queryParams.attrGroupName" placeholder="请输入组名" clearable
              @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input v-model="queryParams.sort" placeholder="请输入排序" clearable @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="描述" prop="descript">
            <el-input v-model="queryParams.descript" placeholder="请输入描述" clearable @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="组图标" prop="icon">
            <el-input v-model="queryParams.icon" placeholder="请输入组图标" clearable @keyup.enter.native="handleQuery" />
          </el-form-item>
          <!-- <el-form-item label="所属分类id" prop="catelogId">
            <el-input v-model="queryParams.catelogId" placeholder="请输入所属分类id" clearable
              @keyup.enter.native="handleQuery" />
          </el-form-item> -->
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
              v-hasPermi="['system:group:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
              v-hasPermi="['system:group:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
              v-hasPermi="['system:group:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
              v-hasPermi="['system:group:export']">导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="groupList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="分组id" align="center" prop="attrGroupId" />
          <el-table-column label="组名" align="center" prop="attrGroupName" />
          <el-table-column label="排序" align="center" prop="sort" />
          <el-table-column label="描述" align="center" prop="descript" />
          <el-table-column label="组图标" align="center" prop="icon" />
          <el-table-column label="所属分类id" align="center" prop="catelogId" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                v-hasPermi="['system:group:edit']">修改</el-button>
              <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                v-hasPermi="['system:group:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
          @pagination="getList" />

        <!-- 添加或修改属性分组对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
          <el-form ref="form" :model="form" :rules="rules" label-width="85px">
            <el-form-item label="组名" prop="attrGroupName">
              <el-input v-model="form.attrGroupName" placeholder="请输入组名" />
            </el-form-item>
            <el-form-item label="排序" prop="sort">
              <el-input v-model="form.sort" placeholder="请输入排序" />
            </el-form-item>
            <el-form-item label="描述" prop="descript">
              <el-input v-model="form.descript" placeholder="请输入描述" />
            </el-form-item>
            <el-form-item label="组图标" prop="icon">
              <el-input v-model="form.icon" placeholder="请输入组图标" />
            </el-form-item>
            <el-form-item label="所属分类id" prop="catelogId" label-width="85px">
              <el-cascader v-model="form.catelogPath" :options="categories" :props="catrgoryProps"></el-cascader>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { listGroup, getGroup, delGroup, addGroup, updateGroup } from "@/api/system/group";
import {getCategory} from "@/api/system/product"
import category from '../../../components/Product/category.vue'
export default {
  components: { category },
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
      // 属性分组表格数据
      groupList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        attrGroupName: null,
        sort: null,
        descript: null,
        icon: null,
        catelogId: null
      },
      // 表单参数
      form: {
        attrGroupId: null,
        sort: null,
        descript: null,
        icon: null,
        catelogPath: [],
        catelogId: 0
      },
      // 表单校验
      rules: {
        attrGroupName :[{ required: true, message: "组名不能为空", trigger: "blur" }],
        sort :[{ required: true, message: "排序不能为空", trigger: "blur" }],
        descript :[{ required: true, message: "描述不能为空", trigger: "blur" }],
        icon :[{ required: true, message: "图标不能为空", trigger: "blur" }],
        catelogId :[{ required: true, message: "所属分类id不能为空", trigger: "blur" }]
      },
      catrgoryProps : {
        value: 'catId',
        label: 'name',
        children: 'childrens'
      },
      categories: [],
      catelogId: 0
    };
  },
  created() {
    this.getList();
    this.getCategories();
  },
  methods: {
    getCategories() {
        this.loading = true;
        getCategory(this.queryParams).then(response => {
            this.categories = response.data;
        });
    },
    nodeClick(data, node, component) {
      // console.log("父data,node,component = ",data, node, component)
      if (node.level == 3) {
        this.queryParams.catelogId = data.catId
        this.handleQuery()
      }
    },

    /** 查询属性分组列表 */
    getList() {
      this.loading = true;
      listGroup(this.queryParams).then(response => {
        this.groupList = response.rows;
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
        attrGroupId: null,
        attrGroupName: null,
        sort: null,
        descript: null,
        icon: null,
        catelogId: null
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
      this.queryParams.catelogId = null;
      this.$refs.tree.setCurrentKey(null);
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.attrGroupId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加属性分组";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const attrGroupId = row.attrGroupId || this.ids
      getGroup(attrGroupId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改属性分组";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.attrGroupId != null) {
            this.form.catelogId = this.form.catelogPath[this.form.catelogPath.length - 1]
            updateGroup(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.catelogId = this.form.catelogPath[this.form.catelogPath.length - 1]
            addGroup(this.form).then(response => {
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
      const attrGroupIds = row.attrGroupId || this.ids;
      this.$modal.confirm('是否确认删除属性分组编号为"' + attrGroupIds + '"的数据项？').then(function () {
        return delGroup(attrGroupIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/group/export', {
        ...this.queryParams
      }, `group_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>

<style></style>