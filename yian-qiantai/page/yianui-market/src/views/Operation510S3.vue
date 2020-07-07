<template>
  <el-form :model="loginForm" ref="loginForm" label-position="left" label-width="372px" >
    <el-row>
      <el-col :span="27">
        <el-menu  default-active="2"  class="el-menu-vertical-demo"  background-color="#F4F4F6"  text-color="#555555"  active-text-color="#ffffff">
             <img src="@/assets/顶部.png" style="position:absolute;width:418px;height:70px;left:0px;"/> 
        <router-link to="/mainpage">
         <img style="width:20px;height:20px;position:absolute;margin-top:25px;left:15px;color:#ffffff" src="@/assets/icon_back@2x.png" />
        </router-link>
         
               <p style="position:absolute; top:10px;left:50px;color:#ffffff">510S(Bedside)-инструкция по эксплуатации</p>
                  <img style="width:390px;height:650px;margin-top:80px;margin-left:14px;" src="@/assets/cc1.png"/>
               <br>
               <img style="width:390px;height:700px;margin-top:5px;margin-left:14px;" src="@/assets/cc2.png"/>
                 <br>
               <img style="width:390px;height:700px;margin-top:5px;margin-left:14px;" src="@/assets/cc3.png"/>
                 <br>
               <img style="width:390px;height:700px;margin-top:5px;margin-left:14px;" src="@/assets/cc4.png"/>
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

     data () {
     return {
        activeName: 'second',
        msg: '',
     
         loginForm: {
             textarea:'',
      }, 
      //加
    }
  },
  methods: {
          handleClick(tab, event)
           {
             console.log(tab, event);
          }
        }
      }
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