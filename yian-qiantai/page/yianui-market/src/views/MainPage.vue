<template>
  <el-form :model="loginForm" ref="loginForm" label-position="left" >
    <el-row >
      <el-col :span="27">
        <el-menu
          default-active="2"
          background-color="#F4F4F6"
          text-color="#555555"
          active-text-color="#ffffff"
          >

      <img
        src="@/assets/上方条形.png" style="width:100%;position:absolute;right:0px;"
      /> 
<el-tabs type="border-card" v-model="activeName" @tab-click="handleClick" style="margin-top:25px; ">
  <el-tab-pane style="width:391px;margin-top:20px;">
    <span slot="label" style="margin-left:20px;"> Brochure</span> 
  <router-link to="vg70caiye">
               
                  <el-button style="width:380px;height:120px;margin-top:20px;right:170px;background: #F0F0F9; border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:90px;height:130px;position:absolute;top:30px;left:40px;"
                      src="@/assets/VG70.png"
                    />   
                    <p
                      style="margin-left:90px;font-size:15px;"
                    >VG70 English Brochure</p>
                    <img
                      style="margin-top:20px;margin-left:230px;"
                      src="@/assets/继续阅读.png"
                    />
                  </el-button>
               
              </router-link>
              <router-link to="caiye510S">
                <div>
                  <el-button style="width:380px;height:120px;margin-top:60px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:90px;height:130px;position:absolute;top:210px;left:40px;"
                      src="@/assets/510S.png"
                    />
                    <p style="margin-left:95px;font-size:15px;"
                    >Shangrila510S English Brochure</p>
                 <img
                      style="margin-top:20px;margin-left:230px;"
                      src="@/assets/继续阅读.png"
                    />
                  </el-button>
                </div>
              </router-link>
              <router-link to="caiye590P">
                <div>
                  <el-button style="width:380px;height:120px;margin-top:60px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:80px;height:130px;position:absolute;top:400px;left:40px;"
                      src="@/assets/590P.png"
                    />
                    <p style="margin-left:90px;font-size:15px;">Shangrila590P Brochure</p>
               <img
                      style="margin-top:20px;margin-left:230px;"
                      src="@/assets/继续阅读.png"
                    />
                  </el-button>
                </div>
              </router-link>
<div style="width:100%;height:100px;" ></div>
           </el-tab-pane>
  <el-tab-pane label="Operation" name="second" style="width:391px;">
<!-- 加-->
<!-- 1-->
  <router-link to="operation510S1">
                <div>
                  <el-button style="width:380px;height:150px;margin-top:40px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:100px;height:120px;position:absolute;top:80px;left:40px;"
                      src="@/assets/aa1.png"
                    />
                    <p
                      style="margin-top:25px;margin-left:100px;font-size:15px;"
                    >510S(Bedside)-Operation Card</p>
                    <!-- <p style="margin-top:10px;margin-left:80px;font-size:11px;">Brochure</p> -->
                  </el-button>
                </div>

              </router-link>

<!-- 2-->
                <router-link to="operation510S2">
                <div>
                  <el-button style="width:380px;height:150px;margin-top:40px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:100px;height:120px;position:absolute;top:270px;left:40px;"
                      src="@/assets/bb1.png"
                    />
                    <p
                      style="margin-top:25px;margin-left:100px;font-size:15px;"
                    >510S(Bedside)-Tarjetadeperaci</p>
                    <!-- <p style="margin-top:10px;margin-left:80px;font-size:11px;">Brochure</p> -->
                  </el-button>
                </div>
              </router-link>
              
<!-- 3-->
        <router-link to="operation510S3">
                <div>
                  <el-button style="width:380px;height:150px;margin-top:40px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:100px;height:120px;position:absolute;top:460px;left:40px;"
                      src="@/assets/cc1.png"
                    />
                    <p
                      style="margin-top:25px;margin-left:100px;font-size:15px;"
                    >510S(Bedside)-инструкция<br> по эксплуатации</p>
                    <!-- <p style="margin-top:10px;margin-left:80px;font-size:11px;">Brochure</p> -->
                  </el-button>
                </div>

              </router-link>
              
<!-- 4-->
                <router-link to="vg70operation1">
                <div>
                  <el-button style="width:380px;height:150px;margin-top:40px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:100px;height:120px;position:absolute;top:650px;left:40px;"
                      src="@/assets/dd1.png"
                    />
                    <p
                      style="margin-top:25px;margin-left:100px;font-size:15px;"
                    >VG70-Operation Card</p>
                    <!-- <p style="margin-top:10px;margin-left:80px;font-size:11px;">Brochure</p> -->
                  </el-button>
                </div>

              </router-link>
              
<!-- 5-->
                <router-link to="vg70operation2">
                <div>
                  <el-button style="width:380px;height:150px;margin-top:40px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:100px;height:120px;position:absolute;top:840px;left:40px;"
                      src="@/assets/ff1.png"
                    />
                    <p
                      style="margin-top:25px;margin-left:100px;font-size:15px;"
                    >VG70-Quick Installation <br>  and Operation Guide</p>
                    <!-- <p style="margin-top:10px;margin-left:80px;font-size:11px;">Brochure</p> -->
                  </el-button>
                </div>

              </router-link>
              
<!-- 6-->
                <router-link to="vg70operation3">
                <div>
                  <el-button style="width:380px;height:150px;margin-top:40px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:100px;height:120px;position:absolute;top:1030px;left:40px;"
                      src="@/assets/ff1.png"
                    />
                    <p
                      style="margin-top:25px;margin-left:100px;font-size:15px;"
                    >VG70-Tarjetadeperaci</p>
                    <!-- <p style="margin-top:10px;margin-left:80px;font-size:11px;">Brochure</p> -->
                  </el-button>
                </div>

              </router-link>
              
<!-- 7-->
                <router-link to="vg70operation4">
                <div>
                  <el-button style="width:380px;height:150px;margin-top:40px;right:170px;background: #F0F0F9;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;">
                    <img
                      style="width:100px;height:120px;position:absolute;top:1220px;left:40px;"
                      src="@/assets/gg1.png"
                    />
                    <p
                      style="margin-top:25px;margin-left:100px;font-size:15px;"
                    >VG70-инструкция <br>  по эксплуатации</p>
                    <!-- <p style="margin-top:10px;margin-left:80px;font-size:11px;">Brochure</p> -->
                  </el-button>
                </div>
              </router-link>

<!-- 加-->


              <div style="width:80px;height:200px;"></div>
  </el-tab-pane>

  <el-tab-pane label="Video" name="third" style="width:391px;">  
          <div style="background: #ffffff;width:260px;height:150px;position:absolute;top:20px;left:50px;">
                  <img
                  style="width:320px;height:200px;margin-top:20px;left:10px;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;"
                  src="@/assets/aa.png"
                    onclick="location.href='https://youtu.be/szBnYPemuQ4';"
                />
                   <img
                  style="position:absolute;top:165px;left:10px;"
                  src="@/assets/播放按钮.png"
                    onclick="location.href='https://youtu.be/szBnYPemuQ4';"
                />
                   <img
                      style="width:320px;height:200px;margin-top:20px;left:10px;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;"
                      src="@/assets/bb.png"
                       onclick="location.href='https://youtu.be/jlklXNBOwrc';"
                    />
                    <img
                  style="position:absolute;top:385px;left:10px;"
                  src="@/assets/播放按钮.png"
                    onclick="location.href='https://youtu.be/jlklXNBOwrc';"
                />
                     <img
                      style="width:320px;height:200px;margin-top:20px;left:10px;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;"
                      src="@/assets/ff.png"
                      onclick="location.href='https://youtu.be/0x3rkGOrpGw';"
                    />
             <img
                  style="position:absolute;top:610px;left:10px;"
                  src="@/assets/播放按钮.png"
                    onclick="location.href='https://youtu.be/0x3rkGOrpGw';"
                />
                  <img
                      style="width:320px;height:200px;margin-top:20px;left:10px;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;"
                      src="@/assets/cc.png"
                      onclick="location.href='https://youtu.be/Zdb1D9gTPKM';"
                    />
             <img
                  style="position:absolute;top:835px;left:10px;"
                  src="@/assets/播放按钮.png"
                    onclick="location.href='https://youtu.be/Zdb1D9gTPKM';"
                />
                    <img
                      style="width:320px;height:200px;margin-top:20px;left:10px;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;"
                      src="@/assets/dd.png"
                      onclick="location.href='https://youtu.be/yr40ImY0Pu0';"
                    />
             <img
                  style="position:absolute;top:1058px;left:10px;"
                  src="@/assets/播放按钮.png"
                    onclick="location.href='https://youtu.be/yr40ImY0Pu0';"
                />
                    <img
                      style="width:320px;height:200px;margin-top:20px;left:10px;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;"
                      src="@/assets/ee.png"
                      onclick="location.href='https://youtu.be/eEEyygP-o_A';"
                    />
             <img
                  style="position:absolute;top:1285px;left:10px;"
                  src="@/assets/播放按钮.png"
                    onclick="location.href='https://youtu.be/eEEyygP-o_A';"
                />
                <!-- </el-form-item> -->
              </div>
              <div
                style="background: #ffffff;width:260px;height:800px; margin-top:170px;left:10px;"
              >

            
              </div>
              <!-- <img
                style="width:200px;height:150px;position:absolute;top:470px;right:200px;"
                src="@/assets/590P.png"
              />-->
              <div style="background: #ffffff;width:240px;height:150px;margin-top:20px;left:10px;">
  
              </div>
              <div style="width:100%;height:200px;" ></div>
            </el-tab-pane>
           
  <el-tab-pane label="Message" style="width:391px;"> 
        <img
        src="@/assets/顶部.png" style="position:absolute;width:500px;height:200px;right:5px;bottom:520px;"
      /> 
              <el-input
                type="textarea"
                :rows="12"
                placeholder="Leave your question 、your country and your phone here:"
                v-model="loginForm.remarks"
                style="margin-top:30px;width:250px;height:263px;top:10px;left:0px;border-radius: 5px;  -moz-box-shadow:0 0 10px #7A68E5; -webkit-box-shadow:0 0 10px #7A68E5;box-shadow:0 0 10px #7A68E5;"
              ></el-input>
                <el-button
                type="info"
                style="width:60%;height:40px;position:absolute;right:82px;margin-top:350px;background:#7A68E5;border-radius: 10px;font-size:18px"
                @click.native.prevent="submitForm"
                :loading="loading"
              >submit</el-button>
                <div style="width:100%;height:330px;" ></div>
              </el-tab-pane>
</el-tabs>

        </el-menu>
        <el-form-item></el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
import KtButton from "@/views/Core/KtButton";
import { format } from "@/utils/datetime";
import XLSX from "xlsx";
import { mapState } from "vuex";
import Cookies from "js-cookie";
// import 'video.js/dist/video-js.css'
// import 'vue-video-player/src/custom-theme.css'
// import 'videojs-contrib-hls.js/src/videojs.hlsjs'
//import Time from 'time_check_jiangji'
 import {
  provinceAndCityData,
  CodeToText,
  TextToCode
} from "element-china-area-data";

export default {
  data() {
    return {
      loading: false,
      activeName: "third",
      msg: "",
      tabPosition: 'left',
      loginForm: {
        account: "admin",
        password: "admin",
        remarks: "",
        playerOptions: {
          playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
          autoplay: false, //如果true,浏览器准备好时开始回放。
          controls: true, //控制条
          preload: "auto", //视频预加载
          muted: false, //默认情况下将会消除任何音频。
          loop: false, //导致视频一结束就重新开始。
          language: "zh-CN",
          aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
          fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
          sources: [
            {
              type: "video/mp4",
              src: "https://v.youku.com/v_show/id_XNDU4NTA5NTM0OA==.html" //你所放置的视频的地址，最好是放在服务器上
            }
          ],
          poster: "http://39.106.117.192:8080/static/indexImg.png", //你的封面地址（覆盖在视频上面的图片）
          width: document.documentElement.clientWidth,
          notSupportedMessage: "此视频暂无法播放，请稍后再试" //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        }
      }
      //加
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    submitForm() {
      this.loading = true;
      if (this.loginForm.remarks) {
        let userInfo = {
          account: this.loginForm.account,
          password: this.loginForm.password
        };
        this.$api.login.login(userInfo).then(res => {
          if (res.msg != null) {
            this.$message({
              message: res.msg,
              type: "error"
            });
          } else {
            Cookies.set("token", res.data.token); // 放置token到Cookie
          }
          let userInfo = { remarks: this.loginForm.remarks };
          this.$api.englishuser.remarksave(userInfo).then(res => {
            if (res.msg != null) {
              this.$message({
                message: res.msg,
                type: "error"
              });
            }
            this.loading = false;
            this.$message({ message: "Tip: successfully added" });
            //this.$refs['loginForm'].resetFields()
            this.loginForm.remarks = "";
          });
        });
      } else {
         this.loading = false;
        this.$message({ message: "Content cannot be empty！", type: "error" });
        return false;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.login-container {
   -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  position: relative;
  right: 0px;
  width: 100%;
  padding: 0rem 0rem 0rem 0rem;
  background: #F0F0F9;
  border: 2px solid #eaeaea;
  box-shadow: 0 0 0px #cac6c6;
  .title {
    margin: 30px auto 30px auto;
    text-align: center;
    color: #505458;
  }
  .remember {
    margin: 0px 0px 35px 0px;
  }
}
.bailing {
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #666666;
  line-height: 14px;
}
</style>