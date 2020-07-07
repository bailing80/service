<template>

  <el-form
   
    label-position="left"
    class="demo-ruleForm login-container"
  
  >
    <!-- <el-form-item>
          <img style="width:24px;height:24px;position:relative;margin-right:327px;top:54px;"  src="@/assets/icon_back@2x.png" />   
    </el-form-item> -->
   <el-form-item>
          <img style="width:131px;height:25px;position:relative;margin-right:220px;margin-top:58px;"  src="@/assets/logo.png" />   
          <el-col :span="20" class="cw-1">
          注册电子保修卡成功
            </el-col>
               <el-col :span="20" class="cw-11">
          <br>
          自注册成功之日起，
            <br>
          您可享受2年产品质保服务。
          <br>
          主机，湿化器保修2年，水罐保修1年。
          管路面罩保修30天。
           </el-col>
    </el-form-item>

     <el-form-item>
     <div>


      <div>
      <el-col :span="30" class="cw-3" style="position:relative;margin-left:5px;top:10px;">
     
      </el-col>
      </div>
      </div>
      </el-form-item>
    

     <el-form-item>
            <img style="width:375px;height:265px; position:relative;margin-top:10px"  src="@/assets/369775d1b7de92c2456000c6f443949.png" />   
    </el-form-item>
    <template>
 
</template>

  </el-form>
</template>

 
<script>
import { mapState } from 'vuex'
import Cookies from "js-cookie"
import ThemePicker from "@/components/ThemePicker"
import LangSelector from "@/components/LangSelector"

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '',
      checked: false,
      //加
       loginForm: {
        account: 'cardAdmin',
        password: 'cardAdmin',
      
      }, 
      //加
    }
  },
  methods:{
  	go(){
  		this.$router.push('/cardpersonal')
    },
    //加
      login() { if (!this.checked) {
        alert('请同意《电子保修卡隐私政策》');
        return false;
    }
      this.loading = true
      let userInfo = {account:this.loginForm.account, password:this.loginForm.password}
      this.$api.login.login(userInfo).then((res) => {
          if(res.msg != null) {
            this.$message({
              message: res.msg,
              type: 'error'
            })
          } else {
            Cookies.set('token', res.data.token) // 放置token到Cookie
            sessionStorage.setItem('user', userInfo.account) // 保存用户到本地会话
            this.$store.commit('menuRouteLoaded', false) // 要求重新加载导航菜单
            this.$router.push('/cardpersonal')  // 登录成功，跳转到主页
          }
          this.loading = false
        }).catch((res) => {
          this.$message({
          message: res.message,
          type: 'error'
          })
        });
    },
    //加
     //加
      login1() {if (!this.checked) {
        alert('请同意《电子保修卡隐私政策》');
        return false;
    }
      this.loading = true
      let userInfo = {account:this.loginForm.account, password:this.loginForm.password}
      this.$api.login.login(userInfo).then((res) => {
          if(res.msg != null) {
            this.$message({
              message: res.msg,
              type: 'error'
            })
          } else {
            Cookies.set('token', res.data.token) // 放置token到Cookie
            sessionStorage.setItem('user', userInfo.account) // 保存用户到本地会话
            this.$store.commit('menuRouteLoaded', false) // 要求重新加载导航菜单
            this.$router.push('/cardhospital')  // 登录成功，跳转到主页
          }
          this.loading = false
        }).catch((res) => {
          this.$message({
          message: res.message,
          type: 'error'
          })
        });
    },
    //加
  }
}
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
right:170px;
 //margin: 0px auto;
  width: 420px;
  height:600px;
  padding: 0px 0px 0px 0px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 0px #cac6c6;
}

.cw-1{
position:relative;
margin-left:30px;
margin-top:50px;
font-family: PingFangSC-Regular;
font-size: 30px;
color: #333333;
line-height: 30px;
}

.cw-11{
position:relative;
margin-left:35px;
margin-top:3px;
font-family: PingFangSC-Regular;
font-size: 20px;
color: #333333;
line-height: 30px;
}
.cw-2{
position:relative;
left:24px;
font-family: PingFangSC-Regular;
font-size: 16px;
color: #333333;
line-height: 20px;
}
.cw-3{
position:relative;
margin-left:47px;
font-family: PingFangSC-Regular;
font-size: 14px;
color: #854FC1;
line-height: 20px;
}

.cw-5{
position:relative;
background: #FEE9C7;
border-radius: 8px;
width:156px;
height:220px;
margin-left:40px;
float:left;
}
.cw-6{
background: #E3E8FE;
border-radius: 8px;
width:156px;
height:220px;
margin-left:35px;
float:left;
}
.cw-7{ 
font-family: PingFangSC-Medium;
font-size: 20px;
color: #643232;
line-height: 20px;
position:relative;
margin-left:38px;
margin-top:16px;
}
.cw-8{
font-family: PingFangSC-Regular;
font-size: 12px;
color: #643232;
line-height: 12px;
position:relative;
margin-left:20px;
margin-top:5px;
}
.cw-9{

opacity: 0.9;
background: #FFFFFF;
border-radius: 16px;
width:124px;
height:32;
margin-bottom:16px;
margin-left:16px;
}
.cw-10{ 
font-family: PingFangSC-Medium;
font-size: 20px;
color: #3849A2;
line-height: 20px;
position:relative;
margin-left:38px;
margin-top:16px;
}

.cw-12{
opacity: 0.9;
background: #FFFFFF;
border-radius: 16px;
width:124px;
height:32;
margin-bottom:16px;
margin-left:16px;
}
.aaa{
float:left;
}
.bbb{
margin-left:50px;
float:left;
}
.ccc{
float:left;
}
</style>
