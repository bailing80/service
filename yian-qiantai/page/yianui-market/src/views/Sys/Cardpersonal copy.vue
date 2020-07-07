<template>
  <el-form :model="loginForm" ref="loginForm" label-position="left" label-width="359px" :rules="rules" >
    <el-row style="right:144px">
      <el-col :span="27">
        <el-menu  default-active="2"  class="el-menu-vertical-demo"  background-color="#F4F4F6"  text-color="#555555"  active-text-color="#ffffff">
         <el-tabs v-model="activeName" @tab-click="handleClick" >
             <el-tab-pane label="ColorPage" name="first" style="left:90px;">
                 <el-form-item header-align="center" prop="account"  label-width="30px" text-align="center"  style="margin-top:90px;">
                 <el-link type="info" href="https://youtu.be/iuru0I_PuH4" >VG70 English Brochure</el-link>
                 </el-form-item>
                 <el-form-item header-align="center" prop="account"  label-width="30px" text-align="center"  style="margin-top:90px;">
                 <el-link type="info" href="https://youtu.be/s8Kx3GZp1sQ" >Shangrila510S English Brochure</el-link>    
                 </el-form-item>   
                 <el-form-item header-align="center" prop="account"  label-width="30px" text-align="center"  style="margin-top:90px;">        
                 <el-link type="info" href="https://youtu.be/iuru0I_PuH4" >Shangrila590P Brochure</el-link>
                 </el-form-item> 
                 <el-form-item header-align="center" prop="account"  label-width="60px" text-align="center"  style="width:385px;margin-top:160px;">        
                 </el-form-item> 
             </el-tab-pane>
             <el-tab-pane label="Operation Video" name="second">
                 <el-form-item header-align="center" prop="account"  label-width="30px" text-align="center"  style="margin-top:90px;">
                 <el-link type="info" href="https://youtu.be/iuru0I_PuH4" >VG70 Installation Guidance</el-link>
                 </el-form-item>
                 <el-form-item header-align="center" prop="account"  label-width="30px" text-align="center"  style="margin-top:90px;">
                 <el-link type="info" href="https://youtu.be/s8Kx3GZp1sQ" >Shangrila510S Installation Guidance</el-link>    
                 </el-form-item>   
                 <el-form-item header-align="center" prop="account"  label-width="30px" text-align="center"  style="margin-top:90px;">        
                 <el-link type="info" href="https://youtu.be/iuru0I_PuH4" >Shangrila510S Installation Guidance</el-link>
                 </el-form-item> 
                 <el-form-item header-align="center" prop="account"  label-width="60px" text-align="center"  style="width:385px;margin-top:160px;">          
                 </el-form-item> 
             </el-tab-pane>
             <el-tab-pane label="Leaving messages" name="third">
                 <el-input type="textarea" :rows="10" placeholder="Please enter your question" v-model="textarea" style="margin-top:30px;"></el-input>
                 <el-button type="info" style="width:60%;height:38px;right:50px;margin-top:220px;background: #854FC1;border-radius: 19px;" href="#/Login" perms="sys:cardpersonal:add"  @click.native.prevent="submitForm" :loading="loading">提交</el-button>
             </el-tab-pane>
          </el-tabs>     
         </el-menu>
        <el-form-item>
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
//import Time from 'time_check_jiangji'
import { provinceAndCityData,CodeToText, TextToCode} from "element-china-area-data"
    
export default {
  data() {
      return {
           pickerOptions3: {
                disabledDate: (time) => {
                  if (this.loginForm.installdate != "") {
                        return time.getTime() <  new Date(this.loginForm.installdate).getTime()- 1*24*60*60*1000|| time.getTime() > Date.now();
                }
                }},

                pickerOptions2: {
                disabledDate: (time) => {
                    if (this.loginForm.purchasedate != "") {
                        return time.getTime() <  new Date(this.loginForm.purchasedate).getTime()- 1*24*60*60*1000|| time.getTime() > Date.now();
                }
                 }
            },
            
            pickerOptions1: {
               disabledDate(time) {
                return time.getTime() > Date.now();
                }
                },
 //导航栏
      activeName: 'second',
      
      contactMobileShow:false,
      contactMobileText:'',
      showChinese: false,
      resetTime: [],
      type: null,
      loadingCity: false,
      province: "",
      city: "",
      show: true,
      show1:false,
      show2: true,
      count: '',
      yanzhengma:"",
      timer: null,
      serialnumberText:'',
      serialnumberShow:false,
      options: provinceAndCityData,
      selectedOptions: [],
      salesnamelist: [],
      hospitalNameList: [],
      list: [],
      isloading: false,
      allHospitalInfo: [],
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
        city: '',
        purchasedata:"",
			},
      loginForm: {
        name:"",
        sex:"",
        age:"",
        telephone:"",
        buychannel:"",
        city:"",
        serialnumber:"",
        productmodel:"",
        purchasedate:"",
        provinceandcity:"",
        src: "",
        qrcodeId: "",
        activityname: "",
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
        sta: false
      },
           
        
        value1: '',
        value2: '',
        value3: '', 
      rules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        sex: [{ required: true, message: "请选择性别", trigger: "blur" }],
        age: [{ required: true, message: "请输入年龄", trigger: "blur" }],
        telephone: [{ required: true, message: "请输入联系人手机号码", trigger: "blur" }],
        verificationcode: [{ required: true, message: "请输入手机验证码", trigger: "blur" }],
        serialnumber: [{ required: true, message: "请选择出厂编号", trigger: "blur" }],
        productmodel: [{ required: true, message: "请输入机型", trigger: "blur" }],
        buychannel: [{ required: true, message: "请选择购买方式", trigger: "blur" }],			
        purchasedate: [{ required: true, message: "请选择购买日期", trigger: "blur" }],
        provinceandcity: [{ required: true, message: "请选择省市", trigger: "blur" }]
      }
    };
  },

  methods: {
    
    getCode: function (){
      if(!this.loginForm.telephone){
          this.$message({ message: "请输入手机号 ！", type: "error" });
      }else{
      
           let params = Object.assign({}, this.loginForm)
           this.$api.cardpersonal.sendMessage(params).then((res) =>       
            {
              if(res.msg != null){
              this.$message({ message: '提示： ' + "验证码发送成功" })}
              this.yanzhengma=res.msg
							this.editLoading = false
							this.editDialogVisible = false
        const TIME_COUNT = 60;
       this.count = TIME_COUNT;
      
       
       this.timer = setInterval(() => {    
          this.show = false;
       if (this.count > 0 && this.count <= TIME_COUNT) {
        this.count--;
        this.show1=true;
        } 
        else {
         this.show = true;
         clearInterval(this.timer);
         this.timer = null;
         this.show1= false;  
        }
       }, 1000)
      })

   } } ,
  	handleChange (value) {
			this.filters.province=CodeToText[value[0]]
			this.filters.city=CodeToText[value[1]]
		},
		handleChangeAdd (value) {
			if(this.operation != 2)//新增或者编辑时从控件值中分解出省份和城市
			{
				this.loginForm.province=CodeToText[value[0]]
				this.loginForm.city=CodeToText[value[1]]
			}
      	},	// 手机号的输入规则
			// 手机号的输入规则
		Mobilerule () {
			this.loginForm.telephone = this.loginForm.telephone.replace( /[^\d]/g,  ''  )
		},
		// 验证手机号是否正确
		ruleTelephoneTrue () {
      //var reg = new RegExp(/^1\d{10}$/)
      var reg = new RegExp(/^1\d{10}$/)
			if (!this.loginForm.telephone) {
				//this.contactMobileShow = true
				//this.contactMobileText = '手机号不能为空'
			} else if (!reg.test(this.loginForm.telephone)) {
				this.contactMobileShow = true
        this.contactMobileText = '请输入11位手机号，以1开头'
			} else {
      //  this.contactMobileShow = false
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
			// if (!this.loginForm.serialnumber) {
			// 	this.serialnumberShow = true
	    //   this.serialnumberText = '序列号不能为空'
      // } else 
      if (!reg.test(this.loginForm.serialnumber)) {
				this.serialnumberShow = true
				this.serialnumberText = '序列号输入格式：AS开头，后跟12位数字'
			} else {
				this.serialnumberShow = false
				this.loginForm.productmodel = this.loginForm.serialnumber.slice(0,3)
			}
    },
      handleClick(tab, event) {
        console.log(tab, event);
        },
    	ruleSerialnumber  () {
			var reg = new RegExp(/^AS\d{12}$/)
			// if (!this.loginForm.serialnumber) {
			// 	this.serialnumberShow = true
			// 	this.serialnumberText = '序列号不能为空'
      // } else 
      if (!reg.test(this.loginForm.serialnumber)) {
        this.serialnumberShow = true
          this.$message({ message: "序列号输入格式：AS开头，后跟12位数字 ！", type: "error" });
          return false;
				this.serialnumberText = '序列号输入格式：AS开头，后跟12位数字'
			} else {
				this.serialnumberShow = false
        //this.loginForm.productmodel = this.loginForm.serialnumber.slice(0,3)
        
			}
		},
	// 序列号通过输入规则，则隐藏提示
		Serialnumberopt : function (params)  {

			var reg = new RegExp(/^AS\d{12}$/)
			if (this.loginForm.serialnumber && reg.test(this.loginForm.serialnumber))
			{   let bailing;
				this.serialnumberShow = false
				bailing = this.loginForm.serialnumber.slice(0,3);
			
				this.$api.cardpersonal.findXuliehao(bailing).then((res) => {
				this.loginForm.productmodel = res.msg
			})


			}
		},
		// // 验证客户姓名是否正确
		// ruleNameTrue () {
		// 	var reg = new RegExp(/^[\u0391-\uFFE5]+$/)
		// 	 if (!reg.test(this.loginForm.name)) {
		// 		this.nameShow = true
		// 		this.nameText = '请输入汉字'
		// 	} else {
		// 		this.nameShow = false
    //   }
      
		// },
		// //客户姓名通过输入规则，则隐藏提示
		// nameOpt () {
    //   var reg = new RegExp(/^[\u0391-\uFFE5]+$/)//只能输入汉字
		// 	if (this.loginForm.name && reg.test(this.loginForm.name)
		// 	) {
		// 		this.nameShow = false
		// 	}
		// },
    save() {
        if (!this.loginForm.name) {
          this.$message({ message: "姓名不能为空 ！", type: "error" });
          return false;
        } else if (!this.loginForm.age) {
          this.$message({ message: "年龄不能为空 ！", type: "error" });
          return false;
        }else if (!this.loginForm.telephone) {
          this.$message({ message: "联系人手机号不能为空 ！", type: "error" });
          return false;
        }  else if (!this.loginForm.verificationcode) {
          this.$message({ message: "手机验证码不能为空 ！", type: "error" });
          return false;
        } else if (!this.loginForm.provinceandcity) {
          this.$message({ message: "请选择省市 ！", type: "error" });
          return false;
        } 
        else if (!this.loginForm.serialnumber) {
          this.$message({ message: "出厂编号不能为空 ！", type: "error" });
          return false;
        } else if (!this.loginForm.productmodel) {
          this.$message({ message: "机型不能为空 ！", type: "error" });
          return false;
        }
    },
      submitForm: function () {
      
        let lentelephone=this.loginForm.telephone.length
         console.log("22222222"+this.yanzhengma)
          if(this.loginForm.verificationcode != this.yanzhengma  ){
             this.$message({ message: "验证码输入错误 ！", type: "error" });
            //this.$confirm('验证码输入错误', '提示',{}).then(() => {})
          return false;
        }
        if(this.loginForm.age>150){
          this.$message({message: "请输入正确年龄 ！", type: "error" });
          //this.$confirm('请输入正确年龄', '提示', {}).then(() => {})
          return false;
        }
        if(lentelephone == 11){
             if (!this.loginForm.name) {
          this.$message({ message: "姓名不能为空 ！", type: "error" });
          return false;
        } else if (!this.loginForm.age) {
          this.$message({ message: "年龄不能为空 ！", type: "error" });
          return false;
        }else if (!this.loginForm.telephone) {
          this.$message({ message: "电话号不能为空 ！", type: "error" });
          return false;
        }  else if (!this.loginForm.verificationcode) {
          this.$message({ message: "手机验证码不能为空 ！", type: "error" });
          return false;
        } else if (!this.loginForm.buychannel) {
          this.$message({ message: "请选择购买渠道 ！", type: "error" });
          return false;
        } else if (!this.loginForm.provinceandcity) {
          this.$message({ message: "请选择所在城市 ！", type: "error" });
          return false;
        } else if (!this.loginForm.serialnumber) {
          this.$message({ message: "请输入出厂编号（SN码） ！", type: "error" });
          return false;
        } else if (!this.loginForm.purchasedate) {
          this.$message({ message: "请选择购买日期 ！", type: "error" });
          return false;
        } 
        if(this.contactMobileShow = true){
			  this.$refs.loginForm.validate((valid) => {	
				if (valid) {
				//	this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.editLoading = true						
            let params = Object.assign({}, this.loginForm)
                console.log("000000000000000000")	
            this.$api.cardpersonal.save(params).then((res) => 
            {
              console.log("2222222222222222")
              this.$message({ message: '提示： ' + res.msg })
              this.$refs['loginForm'].resetFields()
          this.$router.push({
    // 你要跳转的地址
    path: '/endpage'
    })
							this.editLoading = false
							this.editDialogVisible = false
						//	this.findPage(null) 
						})
			
				}
      })}
        }else{ 
          this.$message({message: "请输入正确的手机号码 ！", type: "error" });
          //  this.$confirm('请输入正确的手机号码', '提示', {}).then(() => {})
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
			this.filters.telephone = ''
			this.filters.sex = ''
			this.filters.provinceandcity = ''
			this.filters.province = ''
			this.filters.city = ''
			this.filters.customercode = ''
			this.valueDateRange = ''
			this.filters.buychannel = ''
		},
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

.login-container {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  width: 1850px;
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
.bailing{
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #666666;
  line-height: 14px;
}
</style>