<template>
 
		
</template>

<script>
import CardTable from "@/views/Core/CardTable"
import KtButton from "@/views/Core/KtButton"
import { format } from "@/utils/datetime"
import XLSX from 'xlsx';
//import VDistpicker from 'v-distpicker'
import { provinceAndCityDataPlus,CodeToText, TextToCode} from 'element-china-area-data'

export default {
	components:{			
			CardTable,
			KtButton,
			//VDistpicker
	},
	data() {
		return {
			options: provinceAndCityDataPlus,
		
			contactMobileShow:false,
			contactMobileText:'',
			serialnumberShow:false,
			serialnumberText:'',
			nameShow:false,
			nameText:'',
			loading: false,
			sex: [{
				value: '男',
				label: '男'
				},{
				value: '女',
				label: '女'
				}],
			buychannel: [{
				value: '网购',
				label: '网购'
				},{
				value: '门店',
				label: '门店'
				}],
			size: 'small',
			filters: {
				customercode:'',
				name: '',
				telephone:'',
				sex:'',
				buychannel: '',
				provinceandcity: '',
				province:'',
				city: ''
			},
			columns: [
				//{prop:"id", label:"ID", minWidth:50},
				{prop:"customercode", label:"客户编码", minWidth:130},
				{prop:"name", label:"客户姓名", minWidth:100},
				{prop:"sex", label:"性别", minWidth:70},
				{prop:"age", label:"年龄", minWidth:70},
				//{prop:"height", label:"身高(cm)", minWidth:100},
				//{prop:"weight", label:"体重(kg)", minWidth:100},
				//{prop:"homephone", label:"家庭电话", minWidth:100},
				{prop:"telephone", label:"手机号", minWidth:100},
				{prop:"buychannel", label:"购买渠道", minWidth:100},				
				{prop:"province", label:"省份", minWidth:80},
				{prop:"city", label:"城市", minWidth:80},
				{prop:"serialnumber", label:"序列号", minWidth:120},
				{prop:"productmodel", label:"机型", minWidth:80},
				{prop:"purchasedate", label:"购买时间", minWidth:130},
				{prop:"createtime", label:"创建时间", minWidth:150}
			],
			pageRequest: { pageNum: 1, pageSize: 10 },
			pageResult: {},
			detailResult: {},
			valueDateRange:'',
			operation: 1, // 0:新增, 1:编辑, 2: 查看详情
			titleText:'',//对话框标题内容
			editDialogVisible: false, // 新增编辑界面是否显示
			editLoading: false,
			loginFormRules: {
				name:        	 [{ required: true, message: '请输入姓名', trigger: 'blur' }],
				sex:         	 [{ required: true, message: '请选择性别', trigger: 'blur' }],
				age:     	 	 [{ required: true, message: '请输入用户年龄', trigger: 'blur' }],
				telephone:    	 [{ required: true, message: '请输入11位手机号码', trigger: 'blur' }],
				buychannel: 	 [{ required: true, message: '请选择购买渠道', trigger: 'blur' }],
				//province:        [{ required: true, message: '请输入省份', trigger: 'blur' }],
				serialnumber:	 [{ required: true, message: '请输入序列号', trigger: 'blur' }],
				purchasedate: 	 [{ required: true, message: '请输入购买日期', trigger: 'blur' }]
				//height:       [{ required: true, message: '请输入身高', trigger: 'blur' }],
				//weight:       [{ required: true, message: '请输入体重', trigger: 'blur' }],
				//homephone:    [{ required: true, message: '请输入家庭电话', trigger: 'blur' }],
				//province:      [{ required: true, message: '请输入详细地址', trigger: 'blur' }],
				//breathetype:  [{ required: true, message: '请输入呼吸疾病类型', trigger: 'blur' }],
				//complication: [{ required: true, message: '请输入合并症', trigger: 'blur' }],
				//sellername:   [{ required: true, message: '请输入销售商名称', trigger: 'blur' }],
			},
			// 新增编辑界面数据
			loginForm: {
				id: 0,
				//customercode:'',
				name: '',
				sex:'',
				age:'',
				//height:'',
				//weight:'',
				telephone: '',
				buychannel: '',
				provinceandcity: '',
				province: '',
				city: '',
				serialnumber:'',
				productmodel:'',
				purchasedate:'',
				//homephone: '',
				//province: '',
				//createtime:''

				hospital:'',
				room:'',	
				breathetype:'',
				othersA:'',
				psgresult:'',
				saturation:'',
				complication:'',
				othersB:'',
				parameters:'',
	
				sellername:'',
				appearance:'',
				packing:'',
				accessories:'',
				remarks:'',

				requirements:'',
				selftest:'',
				usertraining:'',
				logoplacement:'',
				installconclusion:'',
				installer:'',
				installdate:'',
				acceptor:'',
				acceptdate:''
			},
		}
	},
	methods: {
		handleChange (value) {
			this.filters.province=CodeToText[value[0]]
			this.filters.city=CodeToText[value[1]]
			console.log(this.filters.province)
			console.log(this.filters.city)
		},
		handleChangeAdd (value) {
			if(this.operation != 2)//新增或者编辑时从控件值中分解出省份和城市
			{
				this.loginForm.province=CodeToText[value[0]]
				this.loginForm.city=CodeToText[value[1]]
			}
      	},
		// 手机号的输入规则
		Mobilerule () {
			this.loginForm.telephone = this.loginForm.telephone.replace( /[^\d]/g,  ''  )
		},
		// 验证手机号是否正确
		ruleTelephoneTrue () {
			var reg = new RegExp(/^1\d{10}$/)
			if (!this.loginForm.telephone) {
				this.contactMobileShow = true
				this.contactMobileText = '手机号不能为空'
			} else if (!reg.test(this.loginForm.telephone)) {
				this.contactMobileShow = true
				this.contactMobileText = '请输入11位手机号，以1开头'
			} else {
				this.contactMobileShow = false
			}
		},
		// 手机号通过输入规则，则隐藏提示
		Mobileadopt () {
			var reg = new RegExp(/^1\d{10}$/)
			if (this.loginForm.telephone && reg.test(this.loginForm.telephone)
			) {
				this.contactMobileShow = false
			}
		},
		//验证序列号是否正确，AS开头，后跟12位数字
		ruleSerialnumber () {
			var reg = new RegExp(/^AS\d{12}$/)
			if (!this.loginForm.serialnumber) {
				this.serialnumberShow = true
				this.serialnumberText = '序列号不能为空'
			} else if (!reg.test(this.loginForm.serialnumber)) {
				this.serialnumberShow = true
				this.serialnumberText = '序列号输入格式：AS开头，后跟12位数字'
			} else {
				this.serialnumberShow = false
				this.loginForm.productmodel = this.loginForm.serialnumber.slice(0,3)
			}
		},
		// 序列号通过输入规则，则隐藏提示
		Serialnumberopt () {
			var reg = new RegExp(/^AS\d{12}$/)
			if (this.loginForm.serialnumber && reg.test(this.loginForm.serialnumber))
			{
				this.serialnumberShow = false
				this.loginForm.productmodel = this.loginForm.serialnumber.slice(0,3)
			}
		},
		// 验证客户姓名是否正确
		ruleNameTrue () {
			var reg = new RegExp(/^[\u0391-\uFFE5]+$/)
			if (!this.loginForm.name) {
				this.nameShow = true
				this.nameText = '姓名不能为空'
			} else if (!reg.test(this.loginForm.name)) {
				this.nameShow = true
				this.nameText = '请输入汉字'
			} else {
				this.nameShow = false
			}
		},
		//客户姓名通过输入规则，则隐藏提示
		nameOpt () {
			var reg = new RegExp(/^[\u0391-\uFFE5]+$/)//只能输入汉字
			if (this.loginForm.name && reg.test(this.loginForm.name)
			) {
				this.nameShow = false
			}
		},
		handleReset: function() {
			this.filters.name = ''
			this.filters.telephone = ''
			this.filters.sex = ''
			this.filters.provinceandcity = ''
			this.filters.province = ''
			this.filters.city = ''
			this.filters.customercode = ''
			this.valueDateRange = ''
			this.filters.buychannel = ''
		},
	
		//查看详情
		handleDetails: function(params) {
			this.editDialogVisible = true
			this.operation = 2
			this.titleText = "个人客户--查看详情"
			this.loginForm = Object.assign({}, params.row)
			console.log(this.loginForm.province)
			console.log(this.loginForm.city)
				
			//console.log(this.loginForm.id)
			this.$api.cardpersonal.getDetails(this.loginForm.id).then((res) => {
				this.detailResult = res.data
				this.loginForm = Object.assign({}, this.detailResult)
				this.loginForm.provinceandcity = TextToCode[this.loginForm.province][this.loginForm.city].code
				if(!this.loginForm.hospital) this.loginForm.hospital = ' '
				if(!this.loginForm.room) this.loginForm.room = ' '
				if(!this.loginForm.breathetype) this.loginForm.breathetype = ' '
				if(!this.loginForm.othersA) this.loginForm.othersA = ' '
				if(!this.loginForm.psgresult) this.loginForm.psgresult = ' '
				if(!this.loginForm.saturation) this.loginForm.saturation = ' '
				if(!this.loginForm.complication) this.loginForm.complication = ' '
				if(!this.loginForm.othersB) this.loginForm.othersB = ' '
				if(!this.loginForm.parameters) this.loginForm.parameters = ' '
				if(!this.loginForm.sellername) this.loginForm.sellername = ' '
				if(!this.loginForm.appearance) this.loginForm.appearance = ' '
				if(!this.loginForm.packing) this.loginForm.packing = ' '
				if(!this.loginForm.accessories) this.loginForm.accessories = ' '
				if(!this.loginForm.remarks) this.loginForm.remarks = ' '
				if(!this.loginForm.requirements) this.loginForm.requirements = ' '
				if(!this.loginForm.selftest) this.loginForm.selftest = ' '
				if(!this.loginForm.usertraining) this.loginForm.usertraining = ' '
				if(!this.loginForm.logoplacement) this.loginForm.logoplacement = ' '
				if(!this.loginForm.installconclusion) this.loginForm.installconclusion = ' '
				if(!this.loginForm.installer) this.loginForm.installer = ' '
				if(!this.loginForm.installdate) this.loginForm.installdate = ''
				if(!this.loginForm.acceptor) this.loginForm.acceptor = ' '
				if(!this.loginForm.acceptdate) this.loginForm.acceptdate = ' '
			})
		},
		// 编辑
		submitForm: function () {
			this.$refs.loginForm.validate((valid) => {	
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.editLoading = true						
						let params = Object.assign({}, this.loginForm)	
						this.$api.cardpersonal.save(params).then((res) => {
							this.$message({ message: '提示： ' + res.msg })
							/*
							if(res.code == 200) {
								this.$message({ message: '操作成功', type: 'success' })
							} else {
								this.$message({message: '操作失败, ' + res.msg, type: 'error'})
							}*/
							this.editLoading = false
							this.$refs['loginForm'].resetFields()
							this.editDialogVisible = false
							this.findPage(null)
						})
					})
				}
			})
		},
		//批量导出
		handleExport: function(data) {
			let le=[["客户信息","患者病情记录","产品购买及配置信息","产品安装及培训"],["用户姓名","性别","出生日期","手机号码","购买渠道","省份","城市","序列号","机型","购买日期",
			"确诊医院","就诊科室","呼吸疾病类型","其他","PSG结果","血氧饱和度","合并症","其他","呼吸机参数设定",
			"销售商名称","产品运输途中产品外观","产品运输途中产品外包装","产品附件是否齐全","描述",
			"使用条件是否满足要求","开机自检测试机工作情况","用户培训情况","中文标识是否准确放置","装机结论","安装人","安装日期","验收人","验收日期"]];//"序号",
			this.$api.cardpersonal.batchExport(data.params).then((res) => {
				let datalist =res.data
				datalist.map(dres=>{
							let arr =[];
							//arr.push(dres.id)
							//arr.push(dres.customercode)
							arr.push(dres.name)
							arr.push(dres.sex)
							arr.push(dres.age)
							//arr.push(dres.height)
							//arr.push(dres.weight)
							arr.push(dres.telephone)
							arr.push(dres.buychannel)
							arr.push(dres.province)
							arr.push(dres.city)
							arr.push(dres.serialnumber)
							arr.push(dres.productmodel)
							arr.push(dres.purchasedate)
							//arr.push(dres.createtime)
							arr.push(dres.hospital)
							arr.push(dres.room)
							arr.push(dres.breathetype)
							arr.push(dres.othersA)
							arr.push(dres.psgresult)
							arr.push(dres.saturation)
							arr.push(dres.complication)
							arr.push(dres.othersB)
							arr.push(dres.parameters)

							arr.push(dres.sellername)
							arr.push(dres.appearance)
							arr.push(dres.packing)
							arr.push(dres.accessories)
							arr.push(dres.remarks)

							arr.push(dres.requirements)
							arr.push(dres.selftest)
							arr.push(dres.usertraining)
							arr.push(dres.logoplacement)
							arr.push(dres.installconclusion)
							arr.push(dres.installer)
							arr.push(dres.installdate)
							arr.push(dres.acceptor)
							arr.push(dres.acceptdate)
							le.push(arr)
						})											
						var wb=XLSX.utils.book_new(),
						ws=	XLSX.utils.aoa_to_sheet(le);
						XLSX.utils.book_append_sheet(wb,ws,"电子保修卡-个人客户");
						XLSX.writeFile(wb,"电子保修卡-个人客户.xlsx");		
			}).then(data!=null?data.callback:'')
		},
		//导出满足条件的数据
		exportExcel: function(data) {
			let leHead = [["客户信息","患者病情记录","产品购买及配置信息","产品安装及培训"]];
			let le=[["客户信息","患者病情记录","产品购买及配置信息","产品安装及培训"],["用户姓名","性别","出生日期","手机号码","购买渠道","省份","城市","序列号","机型","购买日期",
					 "确诊医院","就诊科室","呼吸疾病类型","其他","PSG结果","血氧饱和度","合并症","其他","呼吸机参数设定",
					 "销售商名称","产品运输途中产品外观","产品运输途中产品外包装","产品附件是否齐全","描述",
					 "使用条件是否满足要求","开机自监测试机工作情况","用户培训情况","中文标识是否准确放置","装机结论","安装人","安装日期","验收人","验收日期"]];//"序号","身高(cm)","体重(kg)","家庭电话","详细地址",

			if(data !== null) {
				this.pageRequest = data.pageRequest
			}
			this.pageRequest.columnFilters = {customercode: {name:'customercode', value:this.filters.customercode},
											  name: {name:'name', value:this.filters.name},
											  telephone: {name:'telephone', value:this.filters.telephone},
											  sex: {name:'sex', value:this.filters.sex},
											  province: {name:'province', value:this.filters.province},
											  city: {name:'city', value:this.filters.city},
											  buychannel: {name:'buychannel', value:this.filters.buychannel},
											  startTime: {name:'startTime', value:this.valueDateRange[0]},
											  endTime: {name:'endTime', value:this.valueDateRange[1]}}
			this.$api.cardpersonal.exportExcel(this.pageRequest).then((res) => {
				let datalist =res.data
				datalist.map(dres=>{
							let arr =[];
							//arr.push(dres.id)
							//arr.push(dres.customercode)
							arr.push(dres.name)
							arr.push(dres.sex)
							arr.push(dres.age)
							arr.push(dres.telephone)
							arr.push(dres.buychannel)
							arr.push(dres.province)
							arr.push(dres.city)
							arr.push(dres.serialnumber)
							arr.push(dres.productmodel)
							arr.push(dres.purchasedate)
							//arr.push(dres.createtime)
							arr.push(dres.hospital)
							arr.push(dres.room)
							arr.push(dres.breathetype)
							arr.push(dres.othersA)
							arr.push(dres.psgresult)
							arr.push(dres.saturation)
							arr.push(dres.complication)
							arr.push(dres.othersB)
							arr.push(dres.parameters)

							arr.push(dres.sellername)
							arr.push(dres.appearance)
							arr.push(dres.packing)
							arr.push(dres.accessories)
							arr.push(dres.remarks)

							arr.push(dres.requirements)
							arr.push(dres.selftest)
							arr.push(dres.usertraining)
							arr.push(dres.logoplacement)
							arr.push(dres.installconclusion)
							arr.push(dres.installer)
							arr.push(dres.installdate)
							arr.push(dres.acceptor)
							arr.push(dres.acceptdate)
							le.push(arr)
						})											
						var wb=XLSX.utils.book_new(),
						//ws=	XLSX.utils.aoa_to_sheet(leHead);
						ws=	XLSX.utils.aoa_to_sheet(le);
						XLSX.utils.book_append_sheet(wb,ws,"电子保修卡-个人客户");
						XLSX.writeFile(wb,"电子保修卡-个人客户.xlsx");		
			}).then(data!=null?data.callback:'')
		},
		
		importExcel: function(data)
		{
				let myfile = this.$refs.myfile;
				let files = myfile.files;
				let file = files[0];
				var formData = new FormData();
				formData.append("file", file);
			    this.$api.cardpersonal.importExcel(formData).then(res => {	
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