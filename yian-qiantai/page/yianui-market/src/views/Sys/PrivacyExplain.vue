<template>
  <el-form label-position="left" class="demo-ruleForm login-container" style>
 
      <img
        style="width:20px;height:20px;position:absolute; top:18px;left:30px;"
        src="@/assets/icon_back@2x.png" @click="fanhui"/>
    
    <p style="position:absolute;margin-top:18px;left:70px;font-size:20px;">Aeonmed APP Privacy Policy</p>
    <div style="width:320px;position:absolute;margin-top:50px;left:38px;">
      <p style="position:absolute;margin-top:18px;font-size:19px;">Privacy Policy</p>
      <br />
      <br />
      <br />
     <P> Last updated: March 21, 2020
      We built this App as a Free App. This SERVICE is provided at no cost and is intended for use as is.
      This page is used to inform visitors regarding our policies with the collection, use, and disclosure of Personal Information if anyone decided to use our Service.
      If you choose to use our Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that we collect is used for providing and improving the Service. We will not use or share your information with anyone except as described in this Privacy Policy.
      The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at this App unless otherwise defined in this Privacy Policy.</P>
      <p  style=" margin-top:18px;font-size:19px;margin-right:60px;" >
         Information Collection and Use
      </p>
      <p style=" margin-top:18px;font-size:19px;">
      </p>
       <p>
       For a better experience, while using our Service, We may require you to provide us with certain personally identifiable information. The information that we request will be retained on your device and is not collected by us in any way.
      </p>
     
      <p align="left" style="margin-top:18px;font-size:19px;">
        Log Data
      </p>
      <p>
      We want to inform you that whenever you use our Service, in a case of an error in the app we collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing our Service, the time and date of your use of the Service, and other statistics.
      </p>
      <p align="left" style="margin-top:18px;font-size:19px;">
        Cookies
      </p>
      <p>
      Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.
      This Service does not use these “cookies” explicitly. However, the app may use third party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.
      </p>
      <p align="left" style="margin-top:18px;font-size:19px;">
        Security
      </p>
      <p>  
        &nbsp; &nbsp; &nbsp; &nbsp;We value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee its absolute security.
      </p>
      <p align="left" style="margin-top:18px;font-size:19px;">
        Links to Other Sites
          </p>
      <p>
      &nbsp; &nbsp; &nbsp; &nbsp;This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by us. Therefore, we strongly advise you to review the Privacy Policy of these websites. We have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.
      </p>

      <p align="left" style="margin-top:18px;font-size:19px;">
        Children's Privacy
          </p>
      <p>&nbsp; &nbsp; &nbsp; &nbsp;These Services do not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. In the case, we discover that a child under 13 has provided us with personal information, we immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact us so that we will be able to do necessary actions.</p>
      <p align="left" style="margin-top:18px;font-size:19px;">
        Changes to This Privacy Policy
          </p>
      <p>
        We may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted on this page.
      </p>
      <p align="left" style="margin-top:18px;font-size:19px;">
        Contact Us
          </p>
      <p>
        &nbsp; &nbsp; &nbsp; &nbsp;If you have any questions or suggestions about our Privacy Policy, do not hesitate to contact us.
        service@aeonmed.com
        marketing@aeonmed.com
      </p>
    </div>
  </el-form>
</template>

<script>
import { mapState } from "vuex";
import Cookies from "js-cookie";
import ThemePicker from "@/components/ThemePicker";
import LangSelector from "@/components/LangSelector";

export default {
  name: "HelloWorld",
  data() {
    return {
      msg: "",
      checked: false,
      //加
      loginForm: {
        account: "cardAdmin",
        password: "cardAdmin"
      }
      //加
    };
  },

created()
  {
    
    this.getParams();
  
  },

  methods: {
    getParams:function ()
      { 
         
          let emailparams1=this.$route.query.letemail;
          let passwordparams1=this.$route.query.letpassword;
          let passwordagainparams1=this.$route.query.letpasswordagain;
          let phoneparams1=this.$route.query.letphone;
          document.body.scrollTop = document.documentElement.scrollTop = 0
          console.log(emailparams1)
          console.log(passwordparams1)
          console.log(passwordagainparams1)
          console.log(phoneparams1)
      // this.$router.push('/ypWebsite')
      // document.body.scrollTop = document.documentElement.scrollTop = 0
      },
   fanhui(){

          let letemail2=this.$route.query.letemail;
          let letpassword2=this.$route.query.letpassword;
          let letpasswordagain2=this.$route.query.letpasswordagain;
          let letphone2=this.$route.query.letphone;
          console.log("进入了返回")
          console.log(letemail2)
          console.log(letpassword2)
          console.log(letpasswordagain2)
          console.log(letphone2)
          this.$router.push({
                path: "/register",
                query: {
                    letemail4: this.$route.query.letemail,
                    letpassword4:this.$route.query.letpassword,
                    letpasswordagain4: this.$route.query.letpasswordagain,
                    letphone4:this.$route.query.letphone,
                
                }
              });
   }
  }}
</script>

<style lang="scss" scoped>
.div-inline {
  float: left;
}
.login-container {
  -webkit-border-radius: 0px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  position: relative;
  right: 162px;
  width: 385px;
  height: 2300px;
  padding: 0px 0px 0px 0px;
  background: #ffffff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 0px #cac6c6;
}

.aaa {
  float: left;
}
.bbb {
  margin-left: 10px;
  float: left;
}
.ccc {
  float: left;
}
</style>
