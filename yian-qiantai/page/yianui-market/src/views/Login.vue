<template @touchmove.prevent>
  <el-form
    label-position="left"
    class="demo-ruleForm login-container"
    :style="'height:'+fullHeight+'px;'"
    :rules="rules"
    :model="loginForm"
    ref="loginForm"
    @touchmove.prevent
  >
      <img
        style="width:131px;height:25px;position: absolute;top:95px;left:34px;"
        src="@/assets/logo.png"
      /> 
      <div style="position: absolute; width:80%;height:338px;margin-top:161px;margin-left:40px;background: #FFFFFF;border-radius:8px; webkit-border-radius: 5px;-moz-border-radius: 5px;box-shadow: 0 0 6px #cac6c6;">
</div>
<img
        src="@/assets/电话.png"
         style="position: absolute;top:220px;left:70px;"
      />
      <el-input
        style="width:210px; margin-top:200px;margin-left:40px;font-size:15px;"
        type="text"
        header-align="left"
        label-width="25px"
        placeholder="Please enter Phone"
        v-model="loginForm.account"
      ></el-input>
   
     
      <img
        src="@/assets/密码.png"
       style="position: absolute;top:270px;left:70px;"
      />
      <el-input
        style="width:210px;margin-left:40px;font-size:15px;margin-top:20px"
        type="password"
        header-align="left"
        label-width="25px"
        placeholder="Please enter Password"
        v-model="loginForm.password"
      ></el-input>
    <!-- </el-form-item> -->
    <!-- <p  style="margin-top:15px;margin-left:230px;font-size:13px;position: absolute;color:#808080;">Forgot password</p> -->
    <el-form-item>
      <el-button
        type="primary"
        style="width:60%;margin-top:60px;margin-left:5px;background: #7A68E5;font-size:17px;"
        @click.native.prevent="login"
        :loading="loading1"
      >Log in</el-button>
    </el-form-item>

    <el-button
      style="width:60%;position: absolute;left:87px;color:#7A68E5;font-size:18px;"
      @click.native.prevent="register"
      :loading="loading"
    >Create New Account</el-button>
  
    <p style="left:105px;position: absolute;margin-top:105px;font-size:13px;color:#808080;"> International customer service  1.1.0</p>
  
<el-link style="position: absolute;left:44%;margin-top:130px;font-size:13px;color:#7A68E5;"  type="primary"  target="_blank" @click.native.prevent="weidenglu">Entry not logged in >>
     
</el-link>   

  </el-form>
</template>
<script>
import { mapState } from "vuex";
import Cookies from "js-cookie";
import ThemePicker from "@/components/ThemePicker";
import LangSelector from "@/components/LangSelector";
import VueCookies from "vue-cookies";
export default {
  //  name: 'HelloWorld',
  data() {
    return {
      loading: false,
      loading1: false,
      msg: "",
      fullHeight: document.documentElement.clientHeight,
      //checked: false,
      //加
      loginForm: {
        account: "",
        password: ""
      },
      rules: {
        account: [
          {
            required: true,
            message: "Please enter your account ",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "Please enter your password ",
            trigger: "blur"
          }
        ]
      }
      //加
    };
  },

  watch: {
			fullHeight (val) {//监控浏览器高度变化
				if(!this.timer) {
					this.fullHeight = val
					this.timer = true
					let that = this
					setTimeout(function (){
						that.timer = false
					},500)
				}
				
			}
		},
		mounted () {
			this.get_bodyHeight()
		},
  methods: {
    //加
  get_bodyHeight () {//动态获取浏览器高度
				const that = this
				window.onresize = () => {
					return (() => {
						window.fullHeight = document.documentElement.clientHeight
						that.fullHeight = window.fullHeight
					})()
				}
			
		},
    saoma(){
       //console.log("进来了")
       this.$router.push({
                path: "/beforescanning",
              });
    },
     weidenglu(){
       //console.log("进来了")
       this.$router.push({
                path: "/notlogin",
              });
    },
    register() {
      this.loading = true;

      //  let userInfo = Object.assign({}, this.loginForm )
      let userInfo = { account: "admin", password: "admin" };

      let bailing;
      this.$api.login
        .login(userInfo)
        .then(res => {
          if (res.msg != null) {
            this.$message({
              message: res.msg,
              type: "error"
            });
          } else {
            Cookies.set("token", res.data.token), // 放置token到Cookie
              (bailing = Cookies.get().token);

            this.$router.push({
              path: "/register",
              params: { token: bailing }
            });
            sessionStorage.setItem("user", userInfo.account); // 保存用户到本地会话
          }
          this.loading = false;
        })
        .catch(res => {
          this.$message({
            message: res.message,
            type: "error"
          });
        });
    },
    login() {
      this.loading1 = true;
      if (!this.loginForm.account) {
        this.$message({
          message: "Please enter your account ！",
          type: "error"
        });
           this.loading1 = false;
        return false;
      } else if (!this.loginForm.password) {
        this.$message({
          message: "Please enter your password ！",
          type: "error"
        });
         this.loading1 = false;
        return false;
        
      }
    
      //  let userInfo = Object.assign({}, this.loginForm )
      let userInfo = {
        account: this.loginForm.account,
        password: this.loginForm.password
      };
      this.$api.login
        .login(userInfo)
        .then(res => {
          if (res.msg != null) {
            this.$message({
              message: res.msg,
              type: "error"
            });
          } else {
            Cookies.set("token", res.data.token); // 放置token到Cookie
            sessionStorage.setItem("user", userInfo.account); // 保存用户到本地会话
            //   localStorage.setItem('token',res.data.token)
            this.$router.push({
              path: "/mainpage"
            });
          }
          this.loading1 = false;
        })
        .catch(res => {
          this.$message({
            message: res.message,
            type: "error"
          });
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.div-inline {
  float: left;
}
.login-container {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  position: relative;
  width: 100%;
  background: #F0F0F9;
  border: 2px solid #eaeaea;
  box-shadow: 0 0 0px #cac6c6;
}
.bgd{
  width:330px;
  height:338px;
  margin-top:161px;
  margin-left:30px;
  background: #FFFFFF;
  border-radius:8px; 
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
 
 // border: 2px solid #eaeaea;
  box-shadow: 0 0 6px #cac6c6;
}
.cw-1 {
  position: relative;
  margin-left: 20px;
  margin-top: 1%;
  font-family: PingFangSC-Regular;
  font-size: 30px;
  color: #333333;
  line-height: 30px;
}
.cw-2 {
  position: relative;
  left: 24px;
  font-family: PingFangSC-Regular;
  font-size: 16px;
  color: #333333;
  line-height: 20px;
}
.cw-3 {
  position: relative;
  margin-left: 47px;
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #854fc1;
  line-height: 20px;
}

.cw-5 {
  position: relative;
  background: #fee9c7;
  border-radius: 8px;
  width: 156px;
  height: 220px;
  margin-left: 35px;
  float: left;
}
.cw-6 {
  background: #e3e8fe;
  border-radius: 8px;
  width: 156px;
  height: 220px;
  margin-left: 35px;
  float: left;
}
.cw-7 {
  font-family: PingFangSC-Medium;
  font-size: 20px;
  color: #643232;
  line-height: 20px;
  position: relative;
  margin-left: 38px;
  margin-top: 16px;
}
.cw-8 {
  font-family: PingFangSC-Regular;
  font-size: 12px;
  color: #643232;
  line-height: 12px;
  position: relative;
  margin-left: 20px;
  margin-top: 5px;
}
.cw-9 {
  opacity: 0.9;
  background: #ffffff;
  border-radius: 16px;
  width: 124px;
  height: 32;
  margin-bottom: 16px;
  margin-left: 16px;
}
.cw-10 {
  font-family: PingFangSC-Medium;
  font-size: 20px;
  color: #3849a2;
  line-height: 20px;
  position: relative;
  margin-left: 38px;
  margin-top: 16px;
}
.cw-11 {
  font-family: PingFangSC-Regular;
  font-size: 12px;
  color: #3849a2;
  line-height: 12px;
  position: relative;
  margin-left: 20px;
  margin-top: 5px;
}
.cw-12 {
  opacity: 0.9;
  background: #ffffff;
  border-radius: 16px;
  width: 124px;
  height: 32;
  margin-bottom: 16px;
  margin-left: 16px;
}
.aaa {
  float: left;
}
.bbb {
  margin-left: 80px;
  margin-top: 30px;

  float: left;
}
.ccc {
  float: left;
}
</style>
