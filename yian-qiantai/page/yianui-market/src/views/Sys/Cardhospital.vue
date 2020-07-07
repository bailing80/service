<template>

  <el-form
    :model="loginForm"
    ref="loginForm"
    label-position="left"
    label-width="20px"
    :rules="rules"
  >
    <!--定义下拉菜单--> <!--@open="handleOpen"
          @close="handleClose" -->
    <el-row style="right:55px">
      <el-col :span="35">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          background-color="#F4F4F6"
          text-color="#555555"
          active-text-color="#ffffff"
        >
            <el-menu-item-group>
                  <router-link to="/">
                    <img style="width:20px;height:20px;position:absolute; top:18px;left:10px;" src="@/assets/icon_back@2x.png" />
                   </router-link>
                <span style="position:absolute; top:18px;left:100px;">基本信息</span>
                <br>  <br>  <br> 
              <el-form-item header-align="center" prop="name" label="医院名称" label-width="80px">
                <el-input
                style="width:150px;right:0px;" 
                  type="text"
                  header-align="left"
                  label-width="25px"
                  placeholder="请输入医院名称"
                  v-model="loginForm.name"
                  auto-complete="off"
                  
                    maxlength="10" 
                >
                <!-- <div class="reminder" v-show="nameShow">
            	<span class="el-icon-warning"></span>{{nameText}}
      			</div> --></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="address" label="地址" label-width="80px">
                <el-input
                  style="width:150px;right:0px;"
                  type="text"
                  header-align="left"
                  label-width="15px"
                  placeholder="请输入地址"
                  v-model="loginForm.address"
                  maxlength="30" 

                ></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="contacts" label="设备科联系人" label-width="110px">
                <el-input
                style="width:140px;right:15px;top:0px;" 
                  header-align="left"
                  label-width="15px"
                  v-model.number="loginForm.contacts"
                  auto-complete="off"
                  placeholder="请输入设备科联系人"
                  maxlength="10" 
                >
             </el-input>  </el-form-item>
				<el-form-item header-align="center" prop="telephone" label="电话" label-width="70px">
            <el-input v-model="loginForm.telephone" auto-complete="off" style="width:150px"  placeholder="请输入11位手机号码"
				 maxlength="11" @keyup.native="Mobilerule" type="text"></el-input>
				<!-- <div class="reminder" v-show="contactMobileShow">
            	<span class="el-icon-warning"></span>{{contactMobileText}}
        		</div> -->
              </el-form-item>
              <el-form-item header-align="center" prop="officephone" label="办公电话" label-width="100px">
               <el-input v-model="loginForm.officephone" placeholder="请输入办公电话" style="width:150px;right:20px;"
				onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"
        		oninput="if(value.length>11)value=value.slice(0,11)" ></el-input>
              </el-form-item>
               <el-form-item header-align="center" prop="verificationcode" label="手机验证码" label-width="100px">
                <el-input
                 style="width:150px;right:15px;" 
                  header-align="left"
                  label-width="15px"
                  v-model.number="loginForm.verificationcode"
                  auto-complete="off"
                  placeholder="请输入手机验证码"
               
                  maxlength="6"
                  type="text" 
                ></el-input>
              </el-form-item>
              <el-form-item header-align="center" label="装机科室" prop="office" label-width="100px">
			<el-input
                 style="width:150px;right:15px;top:0px;" 
                  type="text"
                  header-align="left"
                  label-width="15px"
                  placeholder="请输入装机科室"
                  v-model="loginForm.office"
                  auto-complete="off"
                  maxlength="10" 
                ></el-input>
			</el-form-item>
          <el-form-item header-align="center" label="科室主任" prop="officer" label-width="100px">
			<el-input
                 style="width:150px;right:15px;top:0px;" 
                  type="text"
                  header-align="left"
                  label-width="15px"
                  placeholder="请输入科室主任"
                  v-model="loginForm.officer"
                  auto-complete="off"
                  maxlength="10" 
                ></el-input>
			</el-form-item>
              <!-- <el-form-item header-align="center" prop="productmodel" label="产品型号" label-width="80px">
                <el-input
                 style="width:150px;right:5px;top:0px;" 
                  type="text"
                  header-align="left"
                  label-width="15px"
                font-size="10px"
                  placeholder="请输入产品型号"
                  v-model="loginForm.productmodel"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="serialnumber" label="序列号" label-width="80px">
                <el-input
                 style="width:150px;right:5px;" 
                  type="text"
                  header-align="left"
                  label-width="15px"
                  placeholder="请输入序列号"
                  v-model="loginForm.serialnumber"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
			   <el-form-item header-align="center" prop="sellername" label="销售商名称" label-width="100px">
                <el-input
                 style="width:150px;right:15px;" 
                  type="text"
                  header-align="left"
                  label-width="15px"
                  placeholder="请输入销售商名称"
                  v-model="loginForm.sellername"
                  auto-complete="off"
                ></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="purchasedate" label="购买日期" label-width="80px">
  <el-date-picker style="width:150px;right:5px;" v-model="loginForm.purchasedate" align="right" type="date" placeholder="选择日期" value-format="yyyy-MM-dd" ></el-date-picker>              </el-form-item> -->
            </el-menu-item-group>
                          <span>产品购买信息</span>

          <!--下拉菜单二-->
          <!-- <el-submenu index="2"> -->
            <template slot="title">
              <i class="el-icon-location"></i>
            </template>
                 <el-form-item header-align="center" prop="serialnumber" label="出厂编号（SN码）" label-width="80px">
                <el-input
                 style="width:170px;right:5px;top:25px;font-size:9.5px;" 
                  type="text"
                  header-align="left"
                  label-width="15px"
                font-size="10px"
                  placeholder="请按照案例中编号（SN码）录入"
                  v-model="loginForm.serialnumber"
                  auto-complete="off" @blur='ruleSerialnumber' @input='Serialnumberopt'  maxlength="14"
                ></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="productmodel" label="机型" label-width="80px">
                <el-input
                 style="width:150px;right:15px;font-size:9.8px;" 
                  type="text"
                  header-align="left"
                  label-width="15px"
                  placeholder="根据序列号前三位自动生成"
                  v-model="loginForm.productmodel"
                  auto-complete="off"
                  v-bind:disabled="true"
                ></el-input>
              </el-form-item>
                 <el-form-item header-align="center" prop="sellername" label="销售商品名称" label-width="70px">
                <el-input style="width:160px;left:5px;top:20px;"  type="text" header-align="left" label-width="25px" placeholder="请输入销售商品名称" v-model="loginForm.sellername" auto-complete="off"></el-input>
                </el-form-item>
               <el-form-item header-align="center" prop="purchasedate" label="购买日期" label-width="80px">
  <el-date-picker style="width:150px;right:15px;" v-model="loginForm.purchasedate" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions"  value-format="yyyy-MM-dd" ></el-date-picker>              </el-form-item>  
   <!-- </el-submenu> -->
          <!--下拉菜单三-->
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>产品安装信息</span>
            </template>
            <el-menu-item-group>
              <el-form-item header-align="center" prop="requirements" label="使用条件是否满足要求" label-width="160px">
              <el-input style="width:100px;right:10px" type="text" header-align="left" label-width="25px" placeholder="符合" v-model="loginForm.requirements" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="selftest" label="开机自检测试及工作情况" label-width="100px">
              <el-input style="width:100px;left:17px;top:25px"  type="text" header-align="left" label-width="25px" placeholder="正常" v-model="loginForm.selftest" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="usertraining" label="用户培训情况" label-width="150px">
              <el-input style="width:100px;right:10px"  type="text" header-align="left" label-width="25px" placeholder="操作培训" v-model="loginForm.usertraining" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="logoplacement" label="中文标识是否准确放置" label-width="160px">
              <el-input style="width:100px;right:15px" type="text" header-align="left" label-width="25px" placeholder="是"  maxlength="15"  v-model="loginForm.logoplacement" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="installconclusion" label="装机结论" label-width="120px;"></el-form-item>
              <el-input  style="width:240px;bottom:20px;"   type="textarea" :rows="2" placeholder="请输入描述:" v-model="loginForm.installconclusion"  maxlength="50" show-word-limit  auto-complete="off"></el-input>        
              <el-form-item header-align="center" prop="installer" label="安装人" label-width="130px">
              <el-input style="width:120px;right:10px" type="text" header-align="left" label-width="25px" placeholder="请输入安装人" maxlength="10"  v-model="loginForm.installer" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="installdate" label="安装日期" label-width="130px">
              <el-date-picker style="width:140px;right:20px;"  v-model="loginForm.installdate" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions" value-format="yyyy-MM-dd"></el-date-picker>              </el-form-item>
              <el-form-item header-align="center" prop="acceptor" label="验收人" label-width="130px">
              <el-input style="width:120px;right:10px" type="text" header-align="left" label-width="25px" placeholder="请输入验收人" maxlength="10"   v-model="loginForm.acceptor" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="acceptdate" label="验收日期" label-width="130px">
              <el-date-picker style="width:140px;right:20px;"  v-model="loginForm.acceptdate" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions" value-format="yyyy-MM-dd"></el-date-picker>              </el-form-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
        <el-form-item>
          <el-button
            type="info"
            style="width:80%;height:30px;position:absolute;top:15px;right:70px;"
            perms="sys:cardpersonal:add"  @click="submitForm">提交</el-button>
		  
          <div
            style="width:80%;height:30px;position:absolute;top:50px;right:65px;"
          >咨询热线：400-610-8333</div>
    
        </el-form-item>
        	
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
import KtButton from "@/views/Core/KtButton"
import { format } from "@/utils/datetime"
import XLSX from 'xlsx'
import { mapState } from "vuex"
import Cookies from "js-cookie"
import { provinceAndCityData,CodeToText, TextToCode} from "element-china-area-data"
    
export default {
  data() {
      return {
      contactMobileText:'',
      contactMobileShow:false,
      loadingCity: false,
      province: "",
      city: "",
      options: provinceAndCityData,
      selectedOptions: [],
      salesnamelist: [],
      hospitalNameList: [],
      list: [],
      isloading: false,
      allHospitalInfo: [],
      loading: false,sex: [{
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
        city: '',
        purchasedata:"",
			},
      loginForm: {
				//customercode:'',
				name: '',
				address:'',
				contacts:'',
				telephone: '',
				officephone: '',
				office: '',
				officer:'',
				//createtime:''
        verificationcode:'',
				productmodel:'',
				serialnumber:'',	
				sellername:'',
				purchasedate:'',
 	      hospital:'',
				room:'',	
				breathetype:'',
				othersA:'',
				psgresult:'',
				saturation:'',
				complication:'',
				othersB:'',
				parameters:'',
      	compllication:'',
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
				acceptdate:'',
      },
      pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, 
          {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },  
        value1: '',
        value2: '',
        value3: '', 
      rules:  { 
				        name:             [{ required: true, message: '请输入医院名称', trigger: 'blur' }],
                address:          [{ required: true, message: '请输入详细地址', trigger: 'blur' }],
                verificationcode: [{ required: true, message: '请输入6位验证码', trigger: 'blur' }],
		 	        	contacts:         [{ required: true, message: '请输入设备科联系人', trigger: 'blur' }],
		        		officephone:      [{ required: true, message: '请输入办公电话', trigger: 'blur' }],
			        	telephone:        [{ required: true, message: '请输入11位手机号码', trigger: 'blur' }],
			        	office:  	        [{ required: true, message: '请输入装机科室', trigger: 'blur' }],
                officer:   	      [{ required: true, message: '请输入科室主任', trigger: 'blur' }],
                serialnumber:     [{ required: true, message: '请输入序列号', trigger: 'blur' }],
                sellername:       [{ required: true, message: '请输入销售商名称', trigger: 'blur' }],
                purchasedate:     [{ required: true, message: '请选择购买日期', trigger: 'blur' }],
                productmodel:     [{ required: true, message: '请输入产品型号', trigger: 'blur' }],
	      	//	  productmodel:     [{ required: true, message: '请输入产品型号', trigger: 'blur' }],
	      	//		serialnumber:     [{ required: true, message: '请输入序列号', trigger: 'blur' }],
      		//    sellername:       [{ required: true, message: '请输入销售商名称', trigger: 'blur' }],
      		//		purchasedate:     [{ required: true, message: '请输入购买日期', trigger: 'blur' }]
			},
    };
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
      	},	// 手机号的输入规则
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
		// // 验证客户姓名是否正确
		// ruleNameTrue () {
		// 	var reg = new RegExp(/^[\u0391-\uFFE5]+$/)
		// 	if (!this.loginForm.name) {
		// 		this.nameShow = true
		// 		this.nameText = '姓名不能为空'
		// 	} else if (!reg.test(this.loginForm.name)) {
		// 		this.nameShow = true
		// 		this.nameText = '请输入汉字'
		// 	} else {
		// 		this.nameShow = false
		// 	}
		// },
		//客户姓名通过输入规则，则隐藏提示
		// nameOpt () {
		// 	var reg = new RegExp(/^[\u0391-\uFFE5]+$/)//只能输入汉字
		// 	if (this.loginForm.name && reg.test(this.loginForm.name)
		// 	) {
		// 		this.nameShow = false
		// 	}
		// },
    // save() {
    //     if (!this.loginForm.name) {
    //       this.$message({ message: "姓名不能为空 ！", type: "error" });
    //       return false;
    //     } else if (!this.loginForm.age) {
    //       this.$message({ message: "年龄不能为空 ！", type: "error" });
    //       return false;
    //     }else if (!this.loginForm.telephone) {
    //       this.$message({ message: "联系人手机号不能为空 ！", type: "error" });
    //       return false;
    //     }  else if (!this.loginForm.verificationcode) {
    //       this.$message({ message: "手机验证码不能为空 ！", type: "error" });
    //       return false;
    //     } else if (!this.loginForm.serialnumber) {
    //       this.$message({ message: "出厂编号不能为空 ！", type: "error" });
    //       return false;
    //     } else if (!this.loginForm.productmodel) {
    //       this.$message({ message: "机型不能为空 ！", type: "error" });
    //       return false;
    //     }
    // },
          submitForm: function () {
        let lentelephone=this.loginForm.telephone.length
        if(lentelephone == 11){
        if(this.contactMobileShow = true){
			  this.$refs.loginForm.validate((valid) => {	
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.editLoading = true						
            let params = Object.assign({}, this.loginForm)
                console.log("000000000000000000")	
            this.$api.cardpersonal.save(params).then((res) => 
            {

               console.log("2222222222222222")
              this.$message({ message: '提示： ' + res.msg })
              this.$refs['loginForm'].resetFields()

							this.editLoading = false
							this.editDialogVisible = false
						//	this.findPage(null) 
						})
					})
				}
      })}

        }else{ 
           this.$confirm('请输入正确的手机号码', '提示', {}).then(() => {})
}  
    },
    refreshCaptcha: function() {
      this.$api.login.srcmarket().then(res => {
        if (res.code == 200) {
          this.loginForm.qrcodeId = res.data.id;
          this.loginForm.activityname = res.data.name;
          //console.log("res.data :"+res.data.name)
          this.loginForm.src = res.data.pathqrcode;
        } else {
          this.$message({ message: "加载失败... " });
        }
      });
    },
  handleReset: function() {
			this.filters.name = ''
			this.filters.address = ''
			this.filters.contacts = ''
			this.filters.officephone = ''
      this.filters.office = ''
      this.filters.officer = ''
			this.filters.telephone = ''
      this.filters.productmodel = ''
      this.filters.serialnumber = ''
      this.filters.purchasedate = ''
			this.valueDateRange = ''
      this.filters.buychannel = ''
       this.filters.verificationcode = ''
       
		},
  },

  computed: {
    ...mapState({
      themeColor: state => st+ate.app.themeColor
    })
  },
  value1: '',
  value2: '',
  value3:'',
};
</script>

<style lang="scss" scoped>
.el-menu-vertical-demo {
right:35px;
}
.login-container {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  width: 185px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 0px 0px 0px 0px;
  position: absolute;
  padding: 35px 35px 120px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  .title {
    margin: 30px auto 30px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }
}
</style>