<template>
  <div class="page-container">
	<!--工具栏-->
	<div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
		<el-form :inline="true" :model="filters" :size="size">
			<el-form-item>
				<el-input v-model="filters.name" placeholder="名称"></el-input>
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:exdel:view" type="primary" @click="findPage(null)"/>
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-plus" :label="$t('action.add')" perms="sys:exdel:add" type="primary" @click="handleAdd" />
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-search" :label="$t('action.leadout')" perms="sys:exdel:leadout" type="primary" @click="leadout(null)"/>
			</el-form-item>
			<el-form-item>
				<kt-button type="primary" icon="fa fa-search" :label="$t('action.leadin')" perms="sys:exdel:leadin" @click="leadin(null)" />
				<input type="file" ref="myfile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel">
			</el-form-item>					
		</el-form>
	</div>
	<!--表格内容栏 permsQuery="sys:exdel:queryExpInfo" @handleQuery="handleQuery" -->
	<kt-table :height="350"  permsEdit="sys:exdel:edit" permsDelete="sys:exdel:delete"
		:data="pageResult" :columns="columns" @findPage="findPage"  @handleEdit="handleEdit" @handleDelete="handleDelete">
	</kt-table>

	<!--新增界面-->
	<el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="editDialogVisible" :close-on-click-modal="false">
		<el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size">
			<el-form-item label="ID" prop="id"  v-if="false">
				<el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
			</el-form-item>
				<el-form-item label="客户名称" prop="name">
				<el-input v-model="dataForm.name" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="联系方式" prop="telephone">
				<el-input v-model="dataForm.telephone" auto-complete="off"></el-input>
			</el-form-item>	
			<el-form-item label="目的地" prop="toLocation">
				<el-input v-model="dataForm.toLocation" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="发送地" prop="fromLocation">
				<el-input v-model="dataForm.fromLocation" ></el-input>
			</el-form-item>
			<el-form-item label="快递名称" prop="expressName">
				<el-input v-model="dataForm.expressName" ></el-input>
			</el-form-item>
			<el-form-item label="快递单号" prop="expressNumber">
				<el-input v-model="dataForm.expressNumber" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="礼物是否发送" prop="giftSend">
				<el-input v-model="dataForm.giftSend" auto-complete="off"></el-input>
			</el-form-item>
			<!--
			<el-form-item label="返回数据格式" prop="sparam">
				<el-input v-model="dataForm.sparam" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="授权码" prop="sign">
				<el-input v-model="dataForm.sign" auto-complete="off"></el-input>
			</el-form-item>
-->
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
				{prop:"id", label:"ID", minWidth:60},
				{prop:"name", label:"客户名称", minWidth:100},
				{prop:"telephone", label:"联系方式", minWidth:100},
				{prop:"toLocation", label:"目的地", minWidth:100},
				{prop:"fromLocation", label:"发送地", minWidth:100},				
				{prop:"expressName", label:"快递名称", minWidth:100},
				{prop:"expressNumber", label:"快递单号", minWidth:130},
				{prop:"giftSend", label:"礼物是否发送", minWidth:120},
				//{prop:"sparam", label:"返回数据格式", minWidth:120},				
				//{prop:"sign", label:"授权码", minWidth:100},
				{prop:"createtime", label:"创建时间", minWidth:150}
			],
			pageRequest: { pageNum: 1, pageSize: 10 },
			pageResult: {},

			operation: true, // true:新增, false:编辑
			editDialogVisible: false, // 新增编辑界面是否显示
			editLoading: false,
			dataFormRules: {
				name: [
					   { required: true, message: '请输入名称', trigger: 'blur' }
				]
			},
			// 新增编辑界面数据
			dataForm: {
				id: 0,
				name: '',
				telephone: '',
				toLocation: '',
				fromLocation: '',
				expressName: '',
				expressNumber:'',
				giftSend:'',
				sparam:'',
				sign:'',
				createtime:''
			}
		}
	},
	methods: {
		// 获取分页数据
		findPage: function (data) {
			if(data !== null) {
				this.pageRequest = data.pageRequest
			}
			this.pageRequest.columnFilters = {name: {name:'name', value:this.filters.name}}
			this.$api.exdel.findPage(this.pageRequest).then((res) => {
				this.pageResult = res.data
			}).then(data!=null?data.callback:'')
		},
		// 显示新增界面
		handleAdd: function () {
			this.editDialogVisible = true
			this.operation = true
			this.dataForm = {
				id: 0,
				name: '',
				telephone: '',
				toLocation: '',
				fromLocation: '',
				expressName: '',
				expressNumber:'',
				giftSend:'',
				sparam:'',
				sign:'',
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
						this.$api.exdel.save(params).then((res) => {
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
		//查询快递状态
		handleQuery: function (data) {
			let params = Object.assign({}, this.dataForm)
			this.$api.exdel.queryExpInfo(data.params).then(data!=null?data.callback:'')
		},
		handleDelete: function (data) {
			this.$api.exdel.batchDelete(data.params).then(data!=null?data.callback:'')
			//let params = Object.assign({}, this.dataForm)
			//this.$api.exdel.queryExpInfo(params).then(data!=null?data.callback:'')
		},

		leadin: function(data)
		{
				let myfile = this.$refs.myfile;
				let files = myfile.files;
				let file = files[0];
				var formData = new FormData();
				formData.append("file", file);
			    this.$api.exdel.leadin(formData).then(res => {	
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
		leadout: function(data) {
			let le=[["序号","客户名称","手机号","详细地址","快递名称","快递单号"]];								
					this.$api.exdel.leadout().then(res => {						
						//console.log("???:"+JSON.stringify(res.data))
						let datalist =res.data
			            datalist.map(dres=>{
							let arr =[];
							arr.push(dres.id)
							arr.push(dres.name)
							arr.push(dres.telephone)
							arr.push(dres.toLocation)
							arr.push(dres.expressName)
							arr.push(dres.expressNumber)
							le.push(arr)
						})											
					   	var wb=XLSX.utils.book_new(),
						ws=	XLSX.utils.aoa_to_sheet(le);
						XLSX.utils.book_append_sheet(wb,ws,"客户_快递_信息");
						XLSX.writeFile(wb,"客户_快递_信息.xlsx");	
						this.editLoading = true
						this.findPage(null)		  	    				   
				});	
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