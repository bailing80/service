<template>
  <div class="page-container">
	<!--工具栏-->
	<div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
		<el-form :inline="true" :model="filters" :size="size">
			<el-form-item>
				<el-input v-model="filters.name" placeholder="名称"></el-input>
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:mark:view" type="primary" @click="findPage(null)"/>
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-plus" :label="$t('action.add')" perms="sys:mark:add" type="primary" @click="handleAdd" />
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-search" :label="$t('action.leadout')" perms="sys:mark:leadout" type="primary" @click="leaddGout(null)"/>
			</el-form-item>
			<el-form-item>
				<kt-button type="primary" icon="fa fa-search" :label="$t('action.leadin')" perms="sys:mark:leadin" 					
					 @click="leaddGin(null)" />
				   <input type="file" ref="myfile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel">	 
			</el-form-item>
			
		</el-form>
	</div>
	<!--表格内容栏-->
	<kt-table :height="350" permsEdit="sys:mark:edit" permsDelete="sys:mark:delete"
		:data="pageResult" :columns="columns" @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete">
	</kt-table>
	<!--新增编辑界面-->
	<el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="editDialogVisible" :close-on-click-modal="false">
		<el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size">
			<el-form-item label="ID" prop="id"  v-if="false">
				<el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
			</el-form-item>
				<el-form-item label="客户名称" prop="name">
				<el-input v-model="dataForm.name" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="客户手机号" prop="tel" label-width="90px">
				<el-input v-model="dataForm.tel" label-width="60px" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="医院名称" prop="hospital">
				<el-input v-model="dataForm.hospital" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="科室"  prop="departmentoffices">
				<el-input v-model="dataForm.departmentoffices" ></el-input>
			</el-form-item>
			<el-form-item label="医院地址 " prop="address">
				<el-input v-model="dataForm.address" auto-complete="off"></el-input>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button :size="size" @click.native="editDialogVisible = false">{{$t('action.cancel')}}</el-button>
			<el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">{{$t('action.submit')}}</el-button>
		</div>
	</el-dialog>

	
  </div>
</template>

<script>
import KtTable from "@/views/Core/KtTable"
import KtButton from "@/views/Core/KtButton"
import { format } from "@/utils/datetime"
import XLSX from 'xlsx';

export default {
	components:{			
			KtTable,
			KtButton
	},
	data() {
		return {
			size: 'small',
			filters: {
				name: ''
			},
			columns: [
				{prop:"id", label:"ID", minWidth:50},
				{prop:"name", label:"客户名称", minWidth:100},
				{prop:"tel", label:"客户手机号", minWidth:150},
				{prop:"hospital", label:"医院名称", minWidth:80},
				{prop:"departmentoffices", label:"科室", minWidth:50},
				{prop:"address", label:"医院地址", minWidth:120},
				{prop:"sellname", label:"销售名称", minWidth:80},
				{prop:"createtime", label:"创建时间", minWidth:120}
			],
			template: "true",
			pageRequest: { pageNum: 1, pageSize: 10 },
			pageResult: {},

			operation: false, // true:新增, false:编辑
			editDialogVisible: false, // 新增编辑界面是否显示
			editLoading: false,
			dataFormRules: {
				name: [{ required: true, message: '请输入名称', trigger: 'blur' }]
			},
			// 新增编辑界面数据
			dataForm: {
				id: 0,
				name: '',
				tel: '',
				hospital: '',
				departmentoffices: '',
				address: '',
				createtime:''
			},
			//fileUploadBtnText: '导入数据',
		}
	},
	methods: {
		// 获取分页数据
		findPage: function (data) {
			if(data !== null) {
				this.pageRequest = data.pageRequest
			}
			this.pageRequest.columnFilters = {name: {name:'name', value:this.filters.name}}
			this.$api.mark.findPage(this.pageRequest).then((res) => {
				this.pageResult = res.data
			}).then(data!=null?data.callback:'')
		},
		// 批量删除
		handleDelete: function (data) {
			this.$api.mark.batchDelete(data.params).then(data!=null?data.callback:'')
		},
		// 显示新增界面
		handleAdd: function () {
			this.editDialogVisible = true
			this.operation = true
			this.dataForm = {
				id: 0,
				name: '',
				tel: '',
				hospital: '',
				departmentoffices: '',
				address: '',
				createtime:''
			}
		},
		// 显示编辑界面
		handleEdit: function (params) {
			this.editDialogVisible = true
			this.operation = false
			this.dataForm = Object.assign({}, params.row)
		},
		// 编辑
		submitForm: function () {
			this.$refs.dataForm.validate((valid) => {
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.editLoading = true						
						let params = Object.assign({}, this.dataForm)	
						this.$api.mark.save(params).then((res) => {
							if(res.code == 200) {
								this.$message({ message: '操作成功', type: 'success' })
							} else {
								this.$message({message: '操作失败, ' + res.msg, type: 'error'})
							}
							this.editLoading = false
							this.$refs['dataForm'].resetFields()
							this.editDialogVisible = false
							this.findPage(null)
						})
					})
				}
			})
		},

		//async exportExcel() {	
		leaddGout: function(data) {
			let le=[["序号","客户名称","手机号","医院名称","科室","详细地址"]];								
					this.$api.mark.leadgGout().then(res => {						
						//console.log("???:"+JSON.stringify(res.data))
						//let datalist = JSON.stringify(res.data);
						let datalist =res.data
			            datalist.map(dres=>{
							let arr =[];
							arr.push(dres.id)
							arr.push(dres.name)
							arr.push(dres.tel)
							arr.push(dres.hospital)
							arr.push(dres.departmentoffices)
							arr.push(dres.address)
							//arr.push(dres.createtime)
							le.push(arr)
						})											
					   	var wb=XLSX.utils.book_new(),
						ws=	XLSX.utils.aoa_to_sheet(le);
						XLSX.utils.book_append_sheet(wb,ws,"客户信息");
						XLSX.writeFile(wb,"客户信息.xlsx");			  	    				   
				});			
		},
    	leaddGin: function(data)
		{
	
				let myfile = this.$refs.myfile;
				let files = myfile.files;
				let file = files[0];
				var formData = new FormData();
				formData.append("file", file);
			    this.$api.mark.leadGin(formData).then(res => {	
				if(res.code == 200) {
					
					this.$message({ message: '操作成功', type: 'success' })
				} else {
					this.$message({message: '操作失败, ' + res.msg, type: 'error'})
				}
				this.findPage(null)
				this.editLoading = false
				this.editDialogVisible = false
			})	
		},
		// 时间格式化
      	dateFormat: function (row, column, cellValue, index)
		{
          	return format(row[column.property])
      	}
	},
	mounted() {
	}
}
</script>

<style scoped>

</style>