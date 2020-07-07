<template>
    <el-form label-position="left" class="demo-ruleForm login-container" :rules="rules"  :model="loginForm"  ref="loginForm">
       <el-form-item>
          <img style="width:131px;height:25px;margin-top:50px;margin-left:5px;"  src="@/assets/logo.png" />   
       </el-form-item>
         <p style="font-size:20px;margin-left:10px;margin-top:20px;">Personal information</p>
       <el-form-item header-align="center" prop="name" label="Your name" label-width="130px" text-align="center"  style="margin-top:50px;margin-left:40px;">
           <el-input style="width:200px;right:35px;" type="text"  header-align="left" label-width="25px" placeholder="Please enter your name"  v-model="loginForm.name" ></el-input>
       </el-form-item>
  <el-form-item header-align="center" prop="diccode" label="Your diccode" label-width="130px" style="margin-top:50px;margin-left:30px;">
         <el-input  style="width:200px;right:25px;"   placeholder="Please enter your diccode"  type="text"  v-model="loginForm.diccode"  ></el-input>
      </el-form-item>
      <el-form-item header-align="center" prop="identificationnumber" label="Serial number" label-width="130px" style="margin-top:50px;margin-left:25px;">
         <el-input style="width:200px;right:25px;"   placeholder="Please enter serial number"  type="text"  v-model="loginForm.identificationnumber"  ></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="info" style="width:60%;height:38px;margin-top:70px;margin-left:5px;background: #7A68E5;border-radius: 19px;"  @click.native.prevent="submit" :loading="loading">Submit</el-button>
      </el-form-item>
               <div style="width:300px;height:53px;" ></div>
        <el-form-item>
      </el-form-item>
  </el-form>
</template>
 
<script>
import { mapState } from 'vuex'
import Cookies from "js-cookie"
import ThemePicker from "@/components/ThemePicker"
import LangSelector from "@/components/LangSelector"

export default {
  //name: 'HelloWorld',
  data () {
    return {
      loading: false,
      msg: '',
      checked: false,
      //加
       loginForm: {
         
         name: "",
         hospital: '',
        diccode:"",
        identificationnumber: "",
        emialparams:'',
        phoneparams:''

      }, 
       rules: {
        name: [{ required: true, message: "Please enter your name ", trigger: "blur"}],
        diccode: [{ required: true, message: "Please enter your diccode ", trigger: "blur" }],
        identificationnumber: [{ required: true, message: "Please enter your identificationnumber ", trigger: "blur" }],
      }
      //加
    }
  },
  
  created()
  {
    
    this.getParams();
  
  },
  
  methods:{
      getParams:function ()
      {
          let emialparams=this.$route.query.letemail;
          let phoneparams=this.$route.query.letphone;
          //emialparams=emialparams
          console.log("))))))))))))))))))))))):"+emialparams)
          phoneparams=phoneparams
      },
    //    name_blur() {
    //   //  var flag = new RegExp(
    //   //   "[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]"
    //   // );
    //   // 判断 even 是否包含特殊字符
    //   if (!this.loginForm.name) {
    //    this.$message({
    //       message: "The name is empty",
    //       type: "error"
    //     });
    //   }
     
    // },

    //  diccode_blur() {
      
    //   if (!this.loginForm.diccode) {
    //    this.$message({
    //       message: "The diccode is empty",
    //       type: "error"
    //     });
    //   }
    
    // },
    //  identificationnumber_blur() {
      
    //   if (!this.loginForm.identificationnumber) {
    //    this.$message({
    //       message: "The identificationnumber is empty",
    //       type: "error"
    //     });
    //   }
     
    // },


      submit() { 
      if(this.loginForm.name&&this.loginForm.diccode&&this.loginForm.identificationnumber){
        this.submit1();
       }
     
         if (!this.loginForm.name) {
          this.$message({
           message: "The name is empty",
          type: "error"
        });
         return false;
      }
         if (!this.loginForm.diccode) {
          this.$message({
           message: "The diccode is empty",
          type: "error"
        });
         return false;
      }
     
      if (!this.loginForm.identificationnumber) {
       this.$message({
           message: "The identification number is empty",
          type: "error"
        });
         return false;
      }
      },
      
      submit1(){
        
         // this.loading = true
          let  bailing=Cookies.get().token;
          let userInfo = {name:this.loginForm.name, diccode:this.loginForm.diccode,identificationnumber:this.loginForm.identificationnumber,phone:phoneparams,email:emialparams}
          this.$api.englishuser.updatesave(userInfo).then((res) => {
          if(res.msg != null) {
            this.$message({
              message: res.msg,
              type: 'error'
            })
          }
          
            if (!this.loginForm.name) {
          this.name_blur();
          return false;
           }
         if (!this.loginForm.diccode) {
         this.diccode_blur();
        return false;
      }
     
      if (!this.loginForm.identificationnumber) {
      this.identificationnumber_blur();
       return false;
      }
          else {
             let  bailing=Cookies.get().token;
             // Cookies.set('token', tokenparams) // 放置token到Cookie
             sessionStorage.setItem('user', userInfo.account) // 保存用户到本地会话
             //  this.$store.commit('menuRouteLoaded', false) // 要求重新加载导航菜单
            this.$router.push('/mainpage')  // 提交成功，跳转到主页
          }
         // this.loading = false
        }).catch((res) => {
          this.$message({
          message: res.message,
          type: 'error'
          })
          })
      }}}
</script>

<style lang="scss" scoped>
.div-inline{ 
  float:left;
  } 
.login-container {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  position: relative; 
  right:168px;
  width: 372px;
  padding: 0px 0px 0px 0px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 0px #cac6c6;
}


</style>
