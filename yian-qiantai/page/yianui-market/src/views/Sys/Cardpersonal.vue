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
    <el-row style="right:170px">
      <el-col :span="27">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          background-color="#F4F4F6"
          text-color="#555555"
          active-text-color="#ffffff"
          
        >

          <!--下拉菜单一-->
<!--           
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>个人信息（必填）</span>
            </template> -->
            <el-menu-item-group>
                  <router-link to="/">
                    <img style="width:20px;height:20px;position:absolute; top:18px;left:40px;" src="@/assets/icon_back@2x.png" />
                   </router-link>
                <span style="font-family: PingFangSC-Regular;font-size: 18px;color: #000000;letter-spacing: 0;text-align: center;line-height: 24px;">个人信息（必填）</span>
                <br>  <br>  <br> 
              <el-form-item header-align="center" prop="name" label="姓名" label-width="190px" text-align="center">
              <el-input style="width:160px;right:40px;" type="text"  onkeyup="(this.v=function(){this.value=this.value.replace(/^[\u4e00-\u9fa5]{6}$/,'');}).call(this)" header-align="left" label-width="25px" placeholder="请输入用户姓名"  v-model="loginForm.name"   maxlength="5">
            </el-input>
            <div style="right:60px" class="reminder" v-show="nameShow">
            <span class="el-icon-warning"></span>{{nameText}}
      			</div>
              </el-form-item>
              <el-form-item header-align="center" prop="sex" label="性别" label-width="190px" text-align="center">
          <el-select v-model="loginForm.sex" placeholder="请选择"  style="width:160px;right:40px;">
		<el-option v-for="item in sex"	:key="item.value"	:label="item.label"	:value="item.value"	:disabled="item.disabled"></el-option></el-select>
              </el-form-item>
              <el-form-item header-align="center" prop="age" label="用户年龄（岁）" text-align="center" label-width="190px">
                <el-input
                  style="width:160px;right:40px;"
                  type="text"
                  header-align="left"
                  label-width="15px"
                  placeholder="请输入用户年龄"
                  v-model="loginForm.age"
                  onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
        		      oninput="if(value.length>3)value=value.slice(0,3)" 
                  maxlength="3"
                ></el-input>
              </el-form-item>
            		<el-form-item header-align="center" prop="telephone" label="电话" label-width="190px">
            <el-input v-model="loginForm.telephone" auto-complete="off" style="width:160px;right:40px;"   placeholder="请输入11位手机号码"
				 maxlength="11" @keyup.native="Mobilerule" type="text"></el-input>
				<!-- <div class="reminder" v-show="contactMobileShow">
            	<span class="el-icon-warning"></span>{{contactMobileText}}
        		</div> -->
              </el-form-item>
              <el-form-item header-align="center" prop="verificationcode" label="手机验证码" label-width="190px">
                <el-input   
                 style="width:80px;right:50px;" 
                  header-align="left"
                  label-width="15px"
                  v-model.number="loginForm.verificationcode"
                  auto-complete="off"
                  placeholder=""
                  maxlength="4"
                  type="text" 
                ></el-input>     
   
   <el-link v-show="!show1" @click="getCode" style="font-family: PingFangSC-Regular;
font-size: 16px;
color: #854FC1;
line-height: 18px;text-align: center;line-height: 24px;right:40px;">点击发送</el-link>
                <span v-show="!show" class="count"   style="font-family: PingFangSC-Regular;
font-size: 16px;
color: #854FC1;
line-height: 18px;text-align: center;line-height: 24px;margin-left:0px;">{{count}}(s)后重试</span>
                <!-- <el-link v-show="!show2" @click="getCode" style="font-family: PingFangSC-Regular;
  color: #854FC1;
line-height: 18px;text-align: center;line-height: 24px;left:60px;">重新获取</el-link> -->
              </el-form-item>
              <el-form-item header-align="center" label="购买渠道" prop="buychannel" label-width="190px">
			 <el-select v-model="loginForm.buychannel" placeholder="请选择" style="width:160px;right:40px;">
					<el-option
						v-for="item in buychannel"
						:key="item.value"
						:label="item.label"
						:value="item.value"
						:disabled="item.disabled">
					</el-option>
				</el-select>
			</el-form-item>
              <el-form-item header-align="center" prop="provinceandcity" label="所在城市" label-width="190px">
                <el-cascader
               style="width:160px;right:40px;"
                  size="medium"
                  :options="options"
                 v-model="loginForm.provinceandcity"
                  @change="handleChangeAdd"
                ></el-cascader>
              </el-form-item>
              <el-form-item header-align="center" prop="serialnumber" label="出厂编号(SN码)" label-width="190px">
                <el-input style="width:170px;right:40px;font-size:14px;"  type="text" header-align="left"   label-width="15px" font-size="10px"
                  placeholder="请按照案例SN码录入"  v-model="loginForm.serialnumber"  auto-complete="off" @blur='ruleSerialnumber' @input='Serialnumberopt'  maxlength="14"
                ></el-input>	
                <!-- <div class="reminder" v-show="serialnumberShow">
                                     	<span class="el-icon-warning"></span>{{serialnumberText}}
      		   	</div> -->
              </el-form-item>
              <el-form-item header-align="center" prop="productmodel" label="机型" label-width="190px">
                <el-input  style="width:170px;right:40px;font-size:10.5px;"   type="text"  header-align="left"  label-width="15px"  placeholder="根据序列号自动生成"
                  v-model="loginForm.productmodel"  auto-complete="off"   v-bind:disabled="true"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="anli" label="填写案例" label-width="190px"> </el-form-item>
                <img  style="width:220px;height:170px;position:absolute;right:50px;top:670px;"  src="@/assets/tianxieanli.png"  />  
                <br>
                <br>        
                <br>   
                <br>  
                <br>
                <br>  <br>
                <br>  <br>
              
              <el-form-item header-align="center" prop="purchasedate" label="购买日期" label-width="245px">
             <el-date-picker :editable="false" style="width:170px;right:65px;" v-model="loginForm.purchasedate" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions1"  value-format="yyyy-MM-dd" ></el-date-picker>              </el-form-item>  
            </el-menu-item-group>
          <!--下拉菜单二-->
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span style="font-family: PingFangSC-Regular;
font-size: 18px;
color: #000000;
letter-spacing: 0;
text-align: center;
line-height: 24px;">患者病情记录（选填）</span>
            </template>
                <el-form-item header-align="center" prop="hospital" label=" 确诊医院" label-width="170px">
                <el-input style="width:170px;right:20px;" type="text" header-align="left" label-width="25px" placeholder="请输入确诊医院" v-model="loginForm.hospital"  maxlength="25" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item header-align="center" prop="room" label="就诊科室" label-width="170px">
                <el-input style="width:170px;right:20px;"  type="text" header-align="left" label-width="25px" placeholder="请输入就诊科室" v-model="loginForm.room" maxlength="10"  auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item header-align="center" prop="breathetype" label="呼吸疾病类型" label-width="180px">
                <el-input style="width:170px;right:25px;"  type="text" header-align="left" label-width="25px" placeholder="阻塞性睡眠呼吸暂停" v-model="loginForm.breathetype" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item header-align="center" prop="othersA" label="其他（呼吸疾病类型）" label-width="230px">
                <el-input style="width:170px;bottom:80px;right:50px;bottom:5px;"  type="textarea" header-align="left" label-width="25px" placeholder="请输入其他描述" v-model="loginForm.othersA" maxlength="50" show-word-limit  auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item header-align="center" prop="psgresult" label="PSG结果" label-width="170px">
                <el-input style="width:170px;right:20px;"  type="text" header-align="left" label-width="25px" placeholder="AHI(次/h)" v-model="loginForm.psgresult" auto-complete="off"></el-input>
                </el-form-item>
                 <el-form-item header-align="center" prop="saturation" label="最低血氧饱和度(%)" label-width="200px">
                <el-input style="width:170px;right:40px;font-size:13px"  type="text" header-align="left" label-width="25px" placeholder="请输入最低血氧饱和度" v-model="loginForm.saturation" maxlength="3"   auto-complete="off" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"
        		      oninput="if(value.length>3)value=value.slice(0,3)"  ></el-input>
                </el-form-item>
                 <el-form-item header-align="center" prop="complication" label="合并症" label-width="170px">
                <el-input style="width:170px;right:25px;"  type="text" header-align="left" label-width="25px" placeholder="如：高血压" v-model="loginForm.complication" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item header-align="center" prop="othersB" label="其他（合并症）请描述：" label-width="230px">
                                  <el-input style="width:170px;bottom:20px;right:50px;top:0px;" type="textarea" :rows="2" placeholder="请输入描述:" v-model="loginForm.othersB" maxlength="50" show-word-limit  auto-complete="off"></el-input>        

               </el-form-item>
              
                 <el-form-item header-align="left" prop="parameters" label="呼吸机参数设定:" label-width="200px">
                <el-input style="width:170px;top:0px;right:35px" type="textarea" :rows="2" placeholder="请输入描述:" v-model="loginForm.parameters" maxlength="50" show-word-limit auto-complete="off"></el-input>        
                
                 </el-form-item>
                    <p style="font-size:13px;bottom:20px;">(如运行模式、吸气压、呼气压、吸气灵敏度、呼气灵敏度)</p>
              
          </el-submenu>
          <!--下拉菜单三-->
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span style="font-family: PingFangSC-Regular;
font-size: 18px;
color: #000000;
letter-spacing: 0;
text-align: center;
line-height: 24px;">产品购买及配置信息（选填）</span>
            </template>
            <el-menu-item-group>
               <el-form-item header-align="center" prop="sellername" label="销售商品名称" label-width="200px">
                <el-input style="width:160px;right:35px;"  type="text" header-align="left" label-width="25px" placeholder="请输入销售商品名称" v-model="loginForm.sellername" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item header-align="center" prop="appearance" label="产品运输中产品外观" label-width="200px">
                <el-input style="width:160px;right:35px;"  type="text" header-align="left" label-width="25px" placeholder="完好" v-model="loginForm.appearance" auto-complete="off"></el-input>
                </el-form-item>
                  <el-form-item header-align="center" prop="packing" label="产品运输中产品外包装" label-width="200px">
                <el-input  style="width:160px;right:35px;"   type="text" header-align="left" label-width="25px" placeholder="完好" v-model="loginForm.packing" auto-complete="off"></el-input>
                </el-form-item>
                  <el-form-item header-align="center" prop="accessories" label="产品附件是否齐全" label-width="200px">
                <el-input  style="width:160px;right:35px;"   type="text" header-align="left" label-width="25px" placeholder="齐全" v-model="loginForm.accessories" auto-complete="off"></el-input>
                </el-form-item>
               <el-form-item header-align="center" prop="remarks" label="如有破损请描述：" label-width="200px">  
                                 <el-input  style="width:160px;right:35px;top:0px;"   type="textarea" :rows="2" placeholder="请输入描述:" v-model="loginForm.remarks"   maxlength="50" show-word-limit   auto-complete="off"></el-input>        

                   </el-form-item>
            </el-menu-item-group>
          </el-submenu>
          <!--下拉菜单四-->
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span style="font-family: PingFangSC-Regular;font-size: 18px;color: #000000;letter-spacing: 0;text-align: center;line-height: 24px;">产品安装及培训（选填）</span>
            </template>
            <el-menu-item-group>
              <el-form-item header-align="center" prop="requirements" label="使用条件是否满足要求" label-width="200px">
              <el-input style="width:160px;right:30px" type="text" header-align="left" label-width="25px" placeholder="符合" v-model="loginForm.requirements" auto-complete="off"></el-input>
              </el-form-item>
              <!-- <el-form-item header-align="center" prop="selftest" label="开机自检测试及工作情况" label-width="100px">
              <el-input style="width:100px;left:17px;top:25px"  type="text" header-align="left" label-width="25px" placeholder="正常" v-model="loginForm.selftest" auto-complete="off"></el-input>
              </el-form-item> -->
              <el-form-item header-align="center" prop="usertraining" label="用户培训情况" label-width="200px">
              <el-input style="width:160px;right:30px"  type="text" header-align="left" label-width="25px" placeholder="操作培训" v-model="loginForm.usertraining" auto-complete="off"></el-input>
              </el-form-item>
              <!-- <el-form-item header-align="center" prop="logoplacement" label="中文标识是否准确放置" label-width="160px">
              <el-input style="width:100px;right:15px" type="text" header-align="left" label-width="25px" placeholder="是"  maxlength="15"  v-model="loginForm.logoplacement" auto-complete="off"></el-input>
              </el-form-item> -->
              <el-form-item header-align="center" prop="installconclusion" label="装机结论" label-width="200px">
              <el-input  style="width:160px;right:30px"   type="textarea" :rows="2" placeholder="请输入描述:" v-model="loginForm.installconclusion"  maxlength="50" show-word-limit  auto-complete="off"></el-input>        
              </el-form-item>
              <el-form-item header-align="center" prop="installer" label="安装人" label-width="200px">
              <el-input style="width:160px;right:30px" type="text" header-align="left" label-width="25px" placeholder="请输入安装人" maxlength="10"  v-model="loginForm.installer" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="installdate" label="安装日期" label-width="200px">
              <el-date-picker :editable="false" style="width:160px;right:30px;"  v-model="loginForm.installdate" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions2" value-format="yyyy-MM-dd"></el-date-picker>              </el-form-item>
              <el-form-item header-align="center" prop="acceptor" label="验收人" label-width="200px">
              <el-input style="width:160px;right:30px" type="text" header-align="left" label-width="25px" placeholder="请输入验收人" maxlength="10"   v-model="loginForm.acceptor" auto-complete="off"></el-input>
              </el-form-item>
              <el-form-item header-align="center" prop="acceptdate" label="验收日期" label-width="200px">
              <el-date-picker :editable="false" style="width:160px;right:30px;"  v-model="loginForm.acceptdate" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions3" value-format="yyyy-MM-dd"></el-date-picker>              </el-form-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
        <el-form-item>
 
          <el-button
            type="info"
            style="width:80%;height:38px;position:absolute;top:15px;right:50px;background: #854FC1;border-radius: 19px;"
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