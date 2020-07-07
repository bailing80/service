<template>
  <div class="page-container">
	<!--工具栏-->
	<div class="toolbar" style="float:left;padding-top:5px;padding-left:15px;">
		<el-form :inline="true" :model="filters" :size="size">
			<el-form-item label="客户编码:" prop="customercode">
				<el-input v-model="filters.customercode" style="width:100px"></el-input>
			</el-form-item>
			<el-form-item label="客户姓名:" prop="name">
				<el-input v-model="filters.name" style="width:100px"></el-input>
			</el-form-item>
			<el-form-item label="手机号码:" prop="telephone">
				<el-input v-model="filters.telephone" style="width:100px"></el-input>
			</el-form-item>
			<el-form-item label="性别:" prop="sex">
				<el-select v-model="filters.sex" placeholder="请选择" style="width:100px">
					<el-option
						v-for="item in sex"
						:key="item.value"
						:label="item.label"
						:value="item.value"
						:disabled="item.disabled">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="购买渠道" prop="buychannel">
			 	<el-select v-model="filters.buychannel" placeholder="请选择" style="width:100px">
					<el-option
						v-for="item in buychannel"
						:key="item.value"
						:label="item.label"
						:value="item.value"
						:disabled="item.disabled">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="省市:" prop="provinceandcity">
			<el-cascader :options="options" v-model="filters.provinceandcity" style="width:150px" @change="handleChange"></el-cascader>
			</el-form-item>
			<el-form-item label="创建时间:" prop="createtime">
				<el-date-picker
					v-model="valueDateRange" unlink-panels type="daterange" value-format="yyyy-MM-dd HH:mm:ss" style="height:33px"
					range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
				</el-date-picker>
			</el-form-item>
			<!--
		</el-form>	
	</div>
	<div class="toolbar" style="float:left;padding-top:5px;padding-left:15px;">
		<el-form :inline="true" :size="size">
			-->
			<el-form-item>
				<kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:cardpersonal:view" type="primary" @click="findPage(null)"/>
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-trash" :label="$t('action.reset')" perms="sys:cardpersonal:view" type="primary" @click="handleReset" />
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-plus" :label="$t('action.add')" perms="sys:cardpersonal:add" type="primary" @click="handleAdd" />
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-search" :label="$t('action.exportExcel')" perms="sys:cardpersonal:exportExcel" type="primary" @click="exportExcel(null)"/>
			</el-form-item>
			<el-form-item>
				<kt-button type="primary" icon="fa fa-search" :label="$t('action.importExcel')" perms="sys:cardpersonal:importExcel"  @click="importExcel(null)" />
				<input type="file" ref="myfile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel">	 
			</el-form-item>
			
		</el-form>
	</div>
	<!--表格内容栏-->
	<card-table :height="350" permsExport="sys:cardpersonal:exportExcel" permsDetail="sys:cardpersonal:getDetails" permsEdit="sys:cardpersonal:edit" permsDelete="sys:cardpersonal:delete"
		:data="pageResult" :columns="columns" @findPage="findPage" @handleExport="handleExport" @handleDetails="handleDetails" @handleEdit="handleEdit" @handleDelete="handleDelete">
	</card-table>
	<!--新增编辑界面 "operation?'新增':'编辑'"-->
	<el-dialog :title="titleText" width="45%" :visible.sync="editDialogVisible" :close-on-click-modal="false">
		<el-form :model="dataForm" label-width="180px" :rules="dataFormRules" ref="dataForm" :size="size">
			<el-form-item label="个人信息" v-if="operation==0 || operation==2">
			</el-form-item>
			<el-form-item label="客户姓名" prop="name">
				<el-input v-model="dataForm.name" v-bind:disabled="operation==1 || operation==2"  placeholder= "请输入客户姓名" @blur='ruleNameTrue' @input="nameOpt" style="width:300px"></el-input>
				<div class="reminder" v-show="nameShow">,
            	<span class="el-icon-warning"></span>{{nameText}}
      			</div>
			</el-form-item>
			<el-form-item label="性别" prop="sex">
			 <el-select v-model="dataForm.sex" placeholder="请选择" v-bind:disabled="operation==2" style="width:300px">
					<el-option
						v-for="item in sex"
						:key="item.value"
						:label="item.label"
						:value="item.value"
						:disabled="item.disabled">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="年龄" prop="age">
			<el-input v-model="dataForm.age" placeholder="请输入用户年龄" v-bind:disabled="operation==2" style="width:300px"
				onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"
        		oninput="if(value.length>3)value=value.slice(0,3)"  ></el-input>
			</el-form-item>
			<!--
			<el-form-item label="出生日期" prop="age">
			<el-date-picker v-model="dataForm.age" v-bind:disabled="operation==2" placeholder= "请输入用户出生日期"  type="date" value-format="yyyy-MM-dd" style="width:300px">
			</el-date-picker>
			</el-form-item>
			<el-form-item label="身高(cm)" prop="height">
				<el-input v-model="dataForm.height" placeholder="请输入身高" v-bind:disabled="operation==2" style="width:300px" ></el-input>
			</el-form-item>
			<el-form-item label="体重(kg)" prop="weight">
				<el-input v-model="dataForm.weight" placeholder="请输入体重" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="家庭电话" prop="homephone">
				<el-input v-model="dataForm.homephone" placeholder="请输入家庭电话" style="width:300px" v-bind:disabled="operation==2"
				onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"
        		oninput="if(value.length>11)value=value.slice(0,11)" ></el-input>
			</el-form-item>
			-->
			<el-form-item label="手机号" prop="telephone">
				<el-input v-model="dataForm.telephone" auto-complete="off" style="width:300px" v-bind:disabled="operation==1 || operation==2" placeholder="请输入11位手机号码"
				 maxlength="11" @keyup.native="Mobilerule" @blur='ruleTelephoneTrue' @input="Mobileadopt" type="text"></el-input>
				<div class="reminder" v-show="contactMobileShow">
            	<span class="el-icon-warning"></span>{{contactMobileText}}
        		</div>
			</el-form-item>
			<el-form-item label="购买渠道" prop="buychannel">
			 <el-select v-model="dataForm.buychannel" placeholder="请选择" v-bind:disabled="operation==2" style="width:300px">
					<el-option
						v-for="item in buychannel"
						:key="item.value"
						:label="item.label"
						:value="item.value"
						:disabled="item.disabled">
					</el-option>
				</el-select>
			</el-form-item>
			
			<el-form-item label="省市" prop="provinceandcity">
				<!--<v-distpicker  v-bind:disabled="operation==2"></v-distpicker>-->
				<el-cascader v-model="dataForm.provinceandcity" :options="options" style="width:300px" @change="handleChangeAdd" v-bind:disabled="operation==2"></el-cascader>
			</el-form-item>
			
			<el-form-item label="序列号" prop="serialnumber">
				<el-input v-model="dataForm.serialnumber" placeholder="请输入序列号" v-bind:disabled="operation==2" style="width:300px"
				 maxlength="14" @blur='ruleSerialnumber' @input='Serialnumberopt'  type="text"></el-input>
				<div class="reminder" v-show="serialnumberShow">
            	<span class="el-icon-warning"></span>{{serialnumberText}}
      			</div>
			</el-form-item>
			<el-form-item label="机型" prop="productmodel">
				<el-input v-model="dataForm.productmodel" placeholder="根据序列号前三位自动生成" v-bind:disabled="true" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="购买日期" prop="purchasedate">
				<el-date-picker v-model="dataForm.purchasedate" placeholder= "请输入购买日期" v-bind:disabled="operation==2" type="date" value-format="yyyy-MM-dd" style="width:300px">
				</el-date-picker>
			</el-form-item>
			<!--
		</el-form>

		<el-form :model="dataForm" label-width="180px" :rules="dataFormRules" ref="dataForm" :size="size">
			-->
    		<el-divider v-if="operation==0 || operation==2"></el-divider>
    		<el-form-item label="患者病情记录" v-if="operation==0 || operation==2">
			</el-form-item>
			<el-form-item label="确诊医院" prop="hospital"  v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.hospital" placeholder="请输入确诊医院"  v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="就诊科室" prop="room"  v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.room" placeholder="请输入就诊科室" v-bind:disabled="operation==2" style="width:300px" clearable></el-input>
			</el-form-item>
			<el-form-item label="呼吸疾病类型" prop="breathetype"  v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.breathetype" placeholder="请输入呼吸疾病类型" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="其他(呼吸疾病类型)" prop="othersA"  v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.othersA" placeholder="请输入其他描述"  v-bind:disabled="operation==2" style="width:300px" ></el-input>
			</el-form-item>
			<el-form-item label="PSG结果" prop="psgresult"  v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.psgresult" placeholder="AHI n/h" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="血氧饱和度" prop="saturation" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.saturation" placeholder="请输入血氧饱和度" v-bind:disabled="operation==2" style="width:300px" ></el-input>
			</el-form-item>
			<el-form-item label="合并症" prop="complication" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.complication" style="width:300px" placeholder="高血压" v-bind:disabled="operation==2" type="text"></el-input>
			</el-form-item>
			<el-form-item label="其他(合并症)请描述 " prop="othersB" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.othersB"  placeholder="请输入描述" style="width:300px" v-bind:disabled="operation==2" type="textarea"></el-input>
			</el-form-item>
			<el-form-item label="呼吸机参数设定 " prop="parameters" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.parameters" placeholder="请输入描述" v-bind:disabled="operation==2" style="width:300px" type="textarea"></el-input>
			</el-form-item>
			<!--
		</el-form>

		<el-form :model="dataForm" label-width="180px" :rules="dataFormRules" ref="dataForm" :size="size">
			-->
			<el-divider v-if="operation==0 || operation==2"></el-divider>
    		<el-form-item label="产品购买及配置信息" v-if="operation==0 || operation==2">
			</el-form-item>
			<el-form-item label="销售商名称" prop="sellername" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.sellername" placeholder="请输入销售商名称" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="产品运输途中产品外观" prop="appearance" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.appearance" placeholder="完好" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="产品运输途中产品外包装" prop="packing" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.packing" placeholder="完好" v-bind:disabled="operation==2" style="width:300px" ></el-input>
			</el-form-item>
			<el-form-item label="产品附件是否齐全" prop="accessories" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.accessories" style="width:300px" v-bind:disabled="operation==2" placeholder="齐全" type="text"></el-input>
			</el-form-item>
			<el-form-item label="如破损请描述 " prop="remarks" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.remarks"  placeholder="请输入描述" v-bind:disabled="operation==2" style="width:300px" type="textarea"></el-input>
			</el-form-item>
			<!--
		</el-form>

		<el-form :model="dataForm" label-width="180px" :rules="dataFormRules" ref="dataForm" :size="size">
			-->
			<el-divider v-if="operation==0 || operation==2"></el-divider>
    		<el-form-item label="产品安装与培训" v-if="operation==0 || operation==2">
			</el-form-item>
			<el-form-item label="使用条件是否满足要求" prop="requirements"  v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.requirements" placeholder="满足"  v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="开机自检测试及开机情况" prop="selftest" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.selftest" placeholder="正常" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="用户培训情况" prop="usertraining" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.usertraining" placeholder="操作培训" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="中文标识是否准确放置" prop="logoplacement" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.logoplacement" placeholder="请输入中文标识是否准确放置" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="装机结论" prop="installconclusion" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.installconclusion" placeholder="请输入描述" v-bind:disabled="operation==2" type="textarea" style="width:300px"></el-input>
			</el-form-item>
			<el-form-item label="安装人" prop="installer" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.installer" placeholder="请输入安装人" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>			
			<el-form-item label="安装日期" prop="installdate" v-if="operation==0 || operation==2">
				<el-date-picker v-model="dataForm.installdate"  v-bind:disabled="operation==2" type="date" value-format="yyyy-MM-dd" style="width:300px">
				</el-date-picker>
			</el-form-item>
			<el-form-item label="验收人" prop="acceptor" v-if="operation==0 || operation==2">
				<el-input v-model="dataForm.acceptor" placeholder="请输入验收人" v-bind:disabled="operation==2" style="width:300px"></el-input>
			</el-form-item>			
			<el-form-item label="验收日期" prop="acceptdate" v-if="operation==0 || operation==2">
				<el-date-picker v-model="dataForm.acceptdate" v-bind:disabled="operation==2" type="date" value-format="yyyy-MM-dd" style="width:300px">
				</el-date-picker>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button :size="size" v-if="operation==0 || operation==1" @click.native="editDialogVisible = false">{{$t('action.cancel')}}</el-button>
			<el-button :size="size" v-if="operation==2" type="primary" style="width:80px;" @click.native="editDialogVisible = false">{{$t('action.ok')}}</el-button>
			<el-button :size="size" v-if="operation==0 || operation==1" type="primary" @click.native="submitForm" :loading="editLoading">{{$t('action.submit')}}</el-button>
		</div>
	</el-dialog>
  </div>
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
			dataFormRules: {
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
			dataForm: {
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
				this.dataForm.province=CodeToText[value[0]]
				this.dataForm.city=CodeToText[value[1]]
			}
      	},
		// 手机号的输入规则
		Mobilerule () {
			this.dataForm.telephone = this.dataForm.telephone.replace( /[^\d]/g,  ''  )
		},
		// 验证手机号是否正确
		ruleTelephoneTrue () {
			var reg = new RegExp(/^1\d{10}$/)
			if (!this.dataForm.telephone) {
				this.contactMobileShow = true
				this.contactMobileText = '手机号不能为空'
			} else if (!reg.test(this.dataForm.telephone)) {
				this.contactMobileShow = true
				this.contactMobileText = '请输入11位手机号，以1开头'
			} else {
				this.contactMobileShow = false
			}
		},
		// 手机号通过输入规则，则隐藏提示
		Mobileadopt () {
			var reg = new RegExp(/^1\d{10}$/)
			if (this.dataForm.telephone && reg.test(this.dataForm.telephone)
			) {
				this.contactMobileShow = false
			}
		},
		//验证序列号是否正确，AS开头，后跟12位数字
		ruleSerialnumber () {
			var reg = new RegExp(/^AS\d{12}$/)
			if (!this.dataForm.serialnumber) {
				this.serialnumberShow = true
				this.serialnumberText = '序列号不能为空'
			} else if (!reg.test(this.dataForm.serialnumber)) {
				this.serialnumberShow = true
				this.serialnumberText = '序列号输入格式：AS开头，后跟12位数字'
			} else {
				this.serialnumberShow = false
				this.dataForm.productmodel = this.dataForm.serialnumber.slice(0,3)
			}
		},
		// 序列号通过输入规则，则隐藏提示
		Serialnumberopt () {
			var reg = new RegExp(/^AS\d{12}$/)
			if (this.dataForm.serialnumber && reg.test(this.dataForm.serialnumber))
			{
				this.serialnumberShow = false
				this.dataForm.productmodel = this.dataForm.serialnumber.slice(0,3)
			}
		},
		// 验证客户姓名是否正确
		ruleNameTrue () {
			var reg = new RegExp(/^[\u0391-\uFFE5]+$/)
			if (!this.dataForm.name) {
				this.nameShow = true
				this.nameText = '姓名不能为空'
			} else if (!reg.test(this.dataForm.name)) {
				this.nameShow = true
				this.nameText = '请输入汉字'
			} else {
				this.nameShow = false
			}
		},
		//客户姓名通过输入规则，则隐藏提示
		nameOpt () {
			var reg = new RegExp(/^[\u0391-\uFFE5]+$/)//只能输入汉字
			if (this.dataForm.name && reg.test(this.dataForm.name)
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
		// 获取分页数据
		findPage: function (data) {
			if(data !== null) {
				this.pageRequest = data.pageRequest
			}
			this.pageRequest.columnFilters = {name: {name:'name', value:this.filters.name},
											  telephone: {name:'telephone', value:this.filters.telephone},
											  sex: {name:'sex', value:this.filters.sex},
											  province: {name:'province', value:this.filters.province},
											  city: {name:'city', value:this.filters.city},
											  buychannel: {name:'buychannel', value:this.filters.buychannel},
											  customercode: {name:'customercode', value:this.filters.customercode},
											  startTime: {name:'startTime', value:this.valueDateRange[0]},
											  endTime: {name:'endTime', value:this.valueDateRange[1]}}
			this.$api.cardpersonal.findPage(this.pageRequest).then((res) => {
				this.pageResult = res.data
			}).then(data!=null?data.callback:'')
		},
		// 批量删除
		handleDelete: function (data) {
			this.$api.cardpersonal.batchDelete(data.params).then(data!=null?data.callback:'')
		},
		// 显示新增界面
		handleAdd: function () {
			this.editDialogVisible = true
			this.operation = 0
			this.titleText = "个人客户--新增"
			this.dataForm = {
				id: 0,
				customercode:'',
				name: '',
				sex:'男',
				age:'',
				//height:'',
				//weight:'',
				telephone: '',
				buychannel: '',
				provinceandcity: '',
				serialnumber:'',
				productmodel:'',
				purchasedate:'',
				
				//homephone: '',
				//province: '北京市丰台区',
				//createtime:''
				hospital:'天坛医院',
				room:'呼吸科',	
				breathetype:'阻塞性睡眠呼吸暂停',
				othersA:'无',
				psgresult:'100',
				saturation:'正常',
				complication:'高血压',
				othersB:'无',
				parameters:'正常',
	
				sellername:'北京市代理商',
				appearance:'完好',
				packing:'完好',
				accessories:'齐全',
				remarks:'无',

				requirements:'满足',
				selftest:'正常',
				usertraining:'操作培训',
				logoplacement:'是',
				installconclusion:'OK',
				installer:'刘欢',
				installdate:'',
				acceptor:'汪洋',
				acceptdate:''
			}
		},
		// 显示编辑界面
		handleEdit: function (params) {
			this.editDialogVisible = true
			this.operation = 1
			this.titleText = "--编辑"
			this.dataForm = Object.assign({}, params.row)
			console.log(this.dataForm.province)
			console.log(this.dataForm.city)
			this.dataForm.provinceandcity = TextToCode[this.dataForm.province][this.dataForm.city].code
		},
		//查看详情
		handleDetails: function(params) {
			this.editDialogVisible = true
			this.operation = 2
			this.titleText = "个人客户--查看详情"
			this.dataForm = Object.assign({}, params.row)
			console.log(this.dataForm.province)
			console.log(this.dataForm.city)
				
			//console.log(this.dataForm.id)
			this.$api.cardpersonal.getDetails(this.dataForm.id).then((res) => {
				this.detailResult = res.data
				this.dataForm = Object.assign({}, this.detailResult)
				this.dataForm.provinceandcity = TextToCode[this.dataForm.province][this.dataForm.city].code
				if(!this.dataForm.hospital) this.dataForm.hospital = ' '
				if(!this.dataForm.room) this.dataForm.room = ' '
				if(!this.dataForm.breathetype) this.dataForm.breathetype = ' '
				if(!this.dataForm.othersA) this.dataForm.othersA = ' '
				if(!this.dataForm.psgresult) this.dataForm.psgresult = ' '
				if(!this.dataForm.saturation) this.dataForm.saturation = ' '
				if(!this.dataForm.complication) this.dataForm.complication = ' '
				if(!this.dataForm.othersB) this.dataForm.othersB = ' '
				if(!this.dataForm.parameters) this.dataForm.parameters = ' '
				if(!this.dataForm.sellername) this.dataForm.sellername = ' '
				if(!this.dataForm.appearance) this.dataForm.appearance = ' '
				if(!this.dataForm.packing) this.dataForm.packing = ' '
				if(!this.dataForm.accessories) this.dataForm.accessories = ' '
				if(!this.dataForm.remarks) this.dataForm.remarks = ' '
				if(!this.dataForm.requirements) this.dataForm.requirements = ' '
				if(!this.dataForm.selftest) this.dataForm.selftest = ' '
				if(!this.dataForm.usertraining) this.dataForm.usertraining = ' '
				if(!this.dataForm.logoplacement) this.dataForm.logoplacement = ' '
				if(!this.dataForm.installconclusion) this.dataForm.installconclusion = ' '
				if(!this.dataForm.installer) this.dataForm.installer = ' '
				if(!this.dataForm.installdate) this.dataForm.installdate = ''
				if(!this.dataForm.acceptor) this.dataForm.acceptor = ' '
				if(!this.dataForm.acceptdate) this.dataForm.acceptdate = ' '
			})
		},
		// 编辑
		submitForm: function () {
			this.$refs.dataForm.validate((valid) => {	
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.editLoading = true						
						let params = Object.assign({}, this.dataForm)	
						this.$api.cardpersonal.save(params).then((res) => {
							this.$message({ message: '提示： ' + res.msg })
							/*
							if(res.code == 200) {
								this.$message({ message: '操作成功', type: 'success' })
							} else {
								this.$message({message: '操作失败, ' + res.msg, type: 'error'})
							}*/
							this.editLoading = false
							this.$refs['dataForm'].resetFields()
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