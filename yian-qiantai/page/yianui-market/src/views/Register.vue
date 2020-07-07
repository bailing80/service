<template>
  <el-form
    label-position="left"
    class="demo-ruleForm login-container"
    :rules="rules"
    :model="loginForm"
    ref="loginForm"
    :data="pageResult"
    :columns="columns">
      <img
        src="@/assets/上方条形.png" style="width:100%;position:absolute;right:0px;"
      /> 
     <p style="position:absolute; top:8px;left:170px;color:#ffffff;font-size:22px;">Sign up</p>
    <img style="margin-top:30px;margin-right:20px;margin-top:120px;" src="@/assets/国家.png" />
    <el-select v-model="loginForm.diccode" placeholder="Select the country code" :value="value" @input="change($event)" style="width:220px;left:10px;">
    <el-option v-for="item in options" :key="item.value" :label="`${item.en}`" :value="item.en+item.tel"></el-option>
  </el-select>
      <br>
       <img style="margin-top:50px;margin-left:35px;" src="@/assets/电话.png" />
     
   <el-input v-model="loginForm.quhao"  auto-complete="off"  style="width:80px;margin-left:30px; "  placeholder="+86" v-bind:disabled="true" type="text" >
      </el-input>
      <el-input
        v-model="loginForm.phone"
        auto-complete="off"
        style="width:160px;right:20px;"
        placeholder="Please enter phone"
        type="text"
        @blur="phone_blur"
     
      >
  
      </el-input>
    
    <br>
   <img style="margin-top:50px;margin-left:15px;" src="@/assets/密码.png" />
      <el-input
        style="width:220px;margin-left:30px;"
        type="password"
        header-align="left"
        label-width="15px"
        placeholder="Please enter Password"
        v-model="loginForm.password"
    
      ></el-input>
   
    <br>
   <img style="margin-top:50px;margin-left:15px;" src="@/assets/密码.png" />
      <el-input
        style="width:220px;margin-left:30px;"
        v-model="loginForm.passwordagain"
        placeholder="Please Re-enter password"
        type="password"
        @blur="password_blur"
      ></el-input>
   
     <br>
   <img style="margin-top:50px;margin-left:15px;" src="@/assets/邮箱.png" />
      <el-input
        style="width:220px;margin-left:30px;"
        type="text"
        header-align="left"
        label-width="25px"
        placeholder="Please enter Email"
        v-model="loginForm.email"
      ></el-input>

     <br>
   <img style="margin-top:50px;margin-left:15px;" src="@/assets/姓名.png" />
           <el-input style="width:220px;margin-left:30px;" type="text"  header-align="left" label-width="25px" placeholder="Please enter your name"  v-model="loginForm.name" ></el-input>
     
  <!-- <el-form-item header-align="center" prop="diccode" label="Your country" label-width="130px" style="margin-top:50px;margin-left:5px;">
         <el-input  style="width:200px;right:10px;"   placeholder="Please enter your country"  type="text"  v-model="loginForm.diccode"  ></el-input>
      </el-form-item> -->
      <br>
   <img style="margin-top:50px;margin-left:15px;" src="@/assets/序列号.png" />
         <el-input style="width:220px;margin-left:30px;"   placeholder="Please enter serial number"  type="text"  v-model="loginForm.identificationnumber"  ></el-input>


      <!-- <el-form-item>
        <el-button type="info" style="width:60%;height:38px;margin-top:70px;margin-left:5px;background: #854FC1;border-radius: 19px;"  @click.native.prevent="submit" :loading="loading">Submit</el-button>
      </el-form-item> -->

    <div>
      <!-- <div class="bbb">
        <el-checkbox  v-model="checked">I agree</el-checkbox>
      </div>-->
      <div>
        <p
          style="color:#808080;margin-right:10px;margin-top:40px;font-size:13px;"
        >By clicking Agree & Sign up,you agree to the :</p>
        <!-- <el-link
          style="margin-right:15px;margin-top:10px;font-size:12px;"
          type="primary"
          target="_blank"
           @click.native.prevent="chakan"
        >Conditions of Use and Privacy Notice.</el-link> -->
         <el-link  style="margin-right:15px;margin-top:10px;font-size:12px;" type="primary" target="_blank"   @click="open">Conditions of Use and Privacy Notice.</el-link>
      </div>
    </div>
  
    <el-form-item>
      <el-button
        type="info"
        style="width:60%;height:38px;margin-top:35px;margin-right:10px;background: #7A68E5;"
        @click.native.prevent="zhuce"
        :loading="loading"
      >Agree & Sign up</el-button>
    </el-form-item>
    <div style="width:300px;height:45px;"></div>
  </el-form>
</template>

 
<script>
import { mapState } from "vuex";
import Cookies from "js-cookie";
import ThemePicker from "@/components/ThemePicker";
import LangSelector from "@/components/LangSelector";
import VueCookies from "vue-cookies";
export default {
  name: 'select-country',
  props: {
    value: {
      type: [String, Number, Array],
      default: ''
    }},
  //  name: 'HelloWorld',
  data() {
    return {
     options: [{
        'short': 'AD',
        'name': '安道尔共和国',
        'en': 'Andorra',
        'tel': '376'
      }, {
        'short': 'AE',
        'name': '阿拉伯联合酋长国',
        'en': 'UnitedArabEmirates',
        'tel': '971'
      }, {
        'short': 'AF',
        'name': '阿富汗',
        'en': 'Afghanistan',
        'tel': '93'
      }, {
        'short': 'AG',
        'name': '安提瓜和巴布达',
        'en': 'AntiguaandBarbuda',
        'tel': '1268'
      }, {
        'short': 'AI',
        'name': '安圭拉岛',
        'en': 'Anguilla',
        'tel': '1264'
      }, {
        'short': 'AL',
        'name': '阿尔巴尼亚',
        'en': 'Albania',
        'tel': '355'
      }, {
        'short': 'AM',
        'name': '亚美尼亚',
        'en': 'Armenia',
        'tel': '374'
      }, {
        'short': '',
        'name': '阿森松',
        'en': 'Ascension',
        'tel': '247'
      }, {
        'short': 'AO',
        'name': '安哥拉',
        'en': 'Angola',
        'tel': '244'
      }, {
        'short': 'AR',
        'name': '阿根廷',
        'en': 'Argentina',
        'tel': '54'
      }, {
        'short': 'AT',
        'name': '奥地利',
        'en': 'Austria',
        'tel': '43'
      }, {
        'short': 'AU',
        'name': '澳大利亚',
        'en': 'Australia',
        'tel': '61'
      }, {
        'short': 'AZ',
        'name': '阿塞拜疆',
        'en': 'Azerbaijan',
        'tel': '994'
      }, {
        'short': 'BB',
        'name': '巴巴多斯',
        'en': 'Barbados',
        'tel': '1246'
      }, {
        'short': 'BD',
        'name': '孟加拉国',
        'en': 'Bangladesh',
        'tel': '880'
      }, {
        'short': 'BE',
        'name': '比利时',
        'en': 'Belgium',
        'tel': '32'
      }, {
        'short': 'BF',
        'name': '布基纳法索',
        'en': 'Burkina-faso',
        'tel': '226'
      }, {
        'short': 'BG',
        'name': '保加利亚',
        'en': 'Bulgaria',
        'tel': '359'
      }, {
        'short': 'BH',
        'name': '巴林',
        'en': 'Bahrain',
        'tel': '973'
      }, {
        'short': 'BI',
        'name': '布隆迪',
        'en': 'Burundi',
        'tel': '257'
      }, {
        'short': 'BJ',
        'name': '贝宁',
        'en': 'Benin',
        'tel': '229'
      }, {
        'short': 'BL',
        'name': '巴勒斯坦',
        'en': 'Palestine',
        'tel': '970'
      }, {
        'short': 'BM',
        'name': '百慕大群岛',
        'en': 'BermudaIs.',
        'tel': '1441'
      }, {
        'short': 'BN',
        'name': '文莱',
        'en': 'Brunei',
        'tel': '673'
      }, {
        'short': 'BO',
        'name': '玻利维亚',
        'en': 'Bolivia',
        'tel': '591'
      }, {
        'short': 'BR',
        'name': '巴西',
        'en': 'Brazil',
        'tel': '55'
      }, {
        'short': 'BS',
        'name': '巴哈马',
        'en': 'Bahamas',
        'tel': '1242'
      }, {
        'short': 'BW',
        'name': '博茨瓦纳',
        'en': 'Botswana',
        'tel': '267'
      }, {
        'short': 'BY',
        'name': '白俄罗斯',
        'en': 'Belarus',
        'tel': '375'
      }, {
        'short': 'BZ',
        'name': '伯利兹',
        'en': 'Belize',
        'tel': '501'
      }, {
        'short': 'CA',
        'name': '加拿大',
        'en': 'Canada',
        'tel': '1'
      }, {
        'short': '',
        'name': '开曼群岛',
        'en': 'CaymanIs.',
        'tel': '1345'
      }, {
        'short': 'CF',
        'name': '中非共和国',
        'en': 'CentralAfricanRepublic',
        'tel': '236'
      }, {
        'short': 'CG',
        'name': '刚果',
        'en': 'Congo',
        'tel': '242'
      }, {
        'short': 'CH',
        'name': '瑞士',
        'en': 'Switzerland',
        'tel': '41'
      }, {
        'short': 'CK',
        'name': '库克群岛',
        'en': 'CookIs.',
        'tel': '682'
      }, {
        'short': 'CL',
        'name': '智利',
        'en': 'Chile',
        'tel': '56'
      }, {
        'short': 'CM',
        'name': '喀麦隆',
        'en': 'Cameroon',
        'tel': '237'
      }, {
        'short': 'CN',
        'name': '中国',
        'en': 'China',
        'tel': '86'
      }, {
        'short': 'CO',
        'name': '哥伦比亚',
        'en': 'Colombia',
        'tel': '57'
      }, {
        'short': 'CR',
        'name': '哥斯达黎加',
        'en': 'CostaRica',
        'tel': '506'
      }, {
        'short': 'CS',
        'name': '捷克',
        'en': 'Czech',
        'tel': '420'
      }, {
        'short': 'CU',
        'name': '古巴',
        'en': 'Cuba',
        'tel': '53'
      }, {
        'short': 'CY',
        'name': '塞浦路斯',
        'en': 'Cyprus',
        'tel': '357'
      }, {
        'short': 'CZ',
        'name': '捷克',
        'en': 'CzechRepublic',
        'tel': '420'
      }, {
        'short': 'DE',
        'name': '德国',
        'en': 'Germany',
        'tel': '49'
      }, {
        'short': 'DJ',
        'name': '吉布提',
        'en': 'Djibouti',
        'tel': '253'
      }, {
        'short': 'DK',
        'name': '丹麦',
        'en': 'Denmark',
        'tel': '45'
      }, {
        'short': 'DO',
        'name': '多米尼加共和国',
        'en': 'DominicaRep.',
        'tel': '1890'
      }, {
        'short': 'DZ',
        'name': '阿尔及利亚',
        'en': 'Algeria',
        'tel': '213'
      }, {
        'short': 'EC',
        'name': '厄瓜多尔',
        'en': 'Ecuador',
        'tel': '593'
      }, {
        'short': 'EE',
        'name': '爱沙尼亚',
        'en': 'Estonia',
        'tel': '372'
      }, {
        'short': 'EG',
        'name': '埃及',
        'en': 'Egypt',
        'tel': '20'
      }, {
        'short': 'ES',
        'name': '西班牙',
        'en': 'Spain',
        'tel': '34'
      }, {
        'short': 'ET',
        'name': '埃塞俄比亚',
        'en': 'Ethiopia',
        'tel': '251'
      }, {
        'short': 'FI',
        'name': '芬兰',
        'en': 'Finland',
        'tel': '358'
      }, {
        'short': 'FJ',
        'name': '斐济',
        'en': 'Fiji',
        'tel': '679'
      }, {
        'short': 'FR',
        'name': '法国',
        'en': 'France',
        'tel': '33'
      }, {
        'short': 'GA',
        'name': '加蓬',
        'en': 'Gabon',
        'tel': '241'
      }, {
        'short': 'GB',
        'name': '英国',
        'en': 'UnitedKiongdom',
        'tel': '44'
      }, {
        'short': 'GD',
        'name': '格林纳达',
        'en': 'Grenada',
        'tel': '1809'
      }, {
        'short': 'GE',
        'name': '格鲁吉亚',
        'en': 'Georgia',
        'tel': '995'
      }, {
        'short': 'GF',
        'name': '法属圭亚那',
        'en': 'FrenchGuiana',
        'tel': '594'
      }, {
        'short': 'GH',
        'name': '加纳',
        'en': 'Ghana',
        'tel': '233'
      }, {
        'short': 'GI',
        'name': '直布罗陀',
        'en': 'Gibraltar',
        'tel': '350'
      }, {
        'short': 'GM',
        'name': '冈比亚',
        'en': 'Gambia',
        'tel': '220'
      }, {
        'short': 'GN',
        'name': '几内亚',
        'en': 'Guinea',
        'tel': '224'
      }, {
        'short': 'GR',
        'name': '希腊',
        'en': 'Greece',
        'tel': '30'
      }, {
        'short': 'GT',
        'name': '危地马拉',
        'en': 'Guatemala',
        'tel': '502'
      }, {
        'short': 'GU',
        'name': '关岛',
        'en': 'Guam',
        'tel': '1671'
      }, {
        'short': 'GY',
        'name': '圭亚那',
        'en': 'Guyana',
        'tel': '592'
      }, {
        'short': 'HK',
        'name': '香港特别行政区',
        'en': 'Hongkong',
        'tel': '852'
      }, {
        'short': 'HN',
        'name': '洪都拉斯',
        'en': 'Honduras',
        'tel': '504'
      }, {
        'short': 'HT',
        'name': '海地',
        'en': 'Haiti',
        'tel': '509'
      }, {
        'short': 'HU',
        'name': '匈牙利',
        'en': 'Hungary',
        'tel': '36'
      }, {
        'short': 'ID',
        'name': '印度尼西亚',
        'en': 'Indonesia',
        'tel': '62'
      }, {
        'short': 'IE',
        'name': '爱尔兰',
        'en': 'Ireland',
        'tel': '353'
      }, {
        'short': 'IL',
        'name': '以色列',
        'en': 'Israel',
        'tel': '972'
      }, {
        'short': 'IN',
        'name': '印度',
        'en': 'India',
        'tel': '91'
      }, {
        'short': 'IQ',
        'name': '伊拉克',
        'en': 'Iraq',
        'tel': '964'
      }, {
        'short': 'IR',
        'name': '伊朗',
        'en': 'Iran',
        'tel': '98'
      }, {
        'short': 'IS',
        'name': '冰岛',
        'en': 'Iceland',
        'tel': '354'
      }, {
        'short': 'IT',
        'name': '意大利',
        'en': 'Italy',
        'tel': '39'
      }, {
        'short': '',
        'name': '科特迪瓦',
        'en': 'IvoryCoast',
        'tel': '225'
      }, {
        'short': 'JM',
        'name': '牙买加',
        'en': 'Jamaica',
        'tel': '1876'
      }, {
        'short': 'JO',
        'name': '约旦',
        'en': 'Jordan',
        'tel': '962'
      }, {
        'short': 'JP',
        'name': '日本',
        'en': 'Japan',
        'tel': '81'
      }, {
        'short': 'KE',
        'name': '肯尼亚',
        'en': 'Kenya',
        'tel': '254'
      }, {
        'short': 'KG',
        'name': '吉尔吉斯坦',
        'en': 'Kyrgyzstan',
        'tel': '331'
      }, {
        'short': 'KH',
        'name': '柬埔寨',
        'en': 'Kampuchea(Cambodia)',
        'tel': '855'
      }, {
        'short': 'KP',
        'name': '朝鲜',
        'en': 'NorthKorea',
        'tel': '850'
      }, {
        'short': 'KR',
        'name': '韩国',
        'en': 'Korea',
        'tel': '82'
      }, {
        'short': 'KT',
        'name': '科特迪瓦共和国',
        'en': 'RepublicofIvoryCoast',
        'tel': '225'
      }, {
        'short': 'KW',
        'name': '科威特',
        'en': 'Kuwait',
        'tel': '965'
      }, {
        'short': 'KZ',
        'name': '哈萨克斯坦',
        'en': 'Kazakstan',
        'tel': '327'
      }, {
        'short': 'LA',
        'name': '老挝',
        'en': 'Laos',
        'tel': '856'
      }, {
        'short': 'LB',
        'name': '黎巴嫩',
        'en': 'Lebanon',
        'tel': '961'
      }, {
        'short': 'LC',
        'name': '圣卢西亚',
        'en': 'St.Lucia',
        'tel': '1758'
      }, {
        'short': 'LI',
        'name': '列支敦士登',
        'en': 'Liechtenstein',
        'tel': '423'
      }, {
        'short': 'LK',
        'name': '斯里兰卡',
        'en': 'SriLanka',
        'tel': '94'
      }, {
        'short': 'LR',
        'name': '利比里亚',
        'en': 'Liberia',
        'tel': '231'
      }, {
        'short': 'LS',
        'name': '莱索托',
        'en': 'Lesotho',
        'tel': '266'
      }, {
        'short': 'LT',
        'name': '立陶宛',
        'en': 'Lithuania',
        'tel': '370'
      }, {
        'short': 'LU',
        'name': '卢森堡',
        'en': 'Luxembourg',
        'tel': '352'
      }, {
        'short': 'LV',
        'name': '拉脱维亚',
        'en': 'Latvia',
        'tel': '371'
      }, {
        'short': 'LY',
        'name': '利比亚',
        'en': 'Libya',
        'tel': '218'
      }, {
        'short': 'MA',
        'name': '摩洛哥',
        'en': 'Morocco',
        'tel': '212'
      }, {
        'short': 'MC',
        'name': '摩纳哥',
        'en': 'Monaco',
        'tel': '377'
      }, {
        'short': 'MD',
        'name': '摩尔多瓦',
        'en': 'Moldova,Republicof',
        'tel': '373'
      }, {
        'short': 'MG',
        'name': '马达加斯加',
        'en': 'Madagascar',
        'tel': '261'
      }, {
        'short': 'ML',
        'name': '马里',
        'en': 'Mali',
        'tel': '223'
      }, {
        'short': 'MM',
        'name': '缅甸',
        'en': 'Burma',
        'tel': '95'
      }, {
        'short': 'MN',
        'name': '蒙古',
        'en': 'Mongolia',
        'tel': '976'
      }, {
        'short': 'MO',
        'name': '澳门',
        'en': 'Macao',
        'tel': '853'
      }, {
        'short': 'MS',
        'name': '蒙特塞拉特岛',
        'en': 'MontserratIs',
        'tel': '1664'
      }, {
        'short': 'MT',
        'name': '马耳他',
        'en': 'Malta',
        'tel': '356'
      }, {
        'short': '',
        'name': '马里亚那群岛',
        'en': 'MarianaIs',
        'tel': '1670'
      }, {
        'short': '',
        'name': '马提尼克',
        'en': 'Martinique',
        'tel': '596'
      }, {
        'short': 'MU',
        'name': '毛里求斯',
        'en': 'Mauritius',
        'tel': '230'
      }, {
        'short': 'MV',
        'name': '马尔代夫',
        'en': 'Maldives',
        'tel': '960'
      }, {
        'short': 'MW',
        'name': '马拉维',
        'en': 'Malawi',
        'tel': '265'
      }, {
        'short': 'MX',
        'name': '墨西哥',
        'en': 'Mexico',
        'tel': '52'
      }, {
        'short': 'MY',
        'name': '马来西亚',
        'en': 'Malaysia',
        'tel': '60'
      }, {
        'short': 'MZ',
        'name': '莫桑比克',
        'en': 'Mozambique',
        'tel': '258'
      }, {
        'short': 'NA',
        'name': '纳米比亚',
        'en': 'Namibia',
        'tel': '264'
      }, {
        'short': 'NE',
        'name': '尼日尔',
        'en': 'Niger',
        'tel': '977'
      }, {
        'short': 'NG',
        'name': '尼日利亚',
        'en': 'Nigeria',
        'tel': '234'
      }, {
        'short': 'NI',
        'name': '尼加拉瓜',
        'en': 'Nicaragua',
        'tel': '505'
      }, {
        'short': 'NL',
        'name': '荷兰',
        'en': 'Netherlands',
        'tel': '31'
      }, {
        'short': 'NO',
        'name': '挪威',
        'en': 'Norway',
        'tel': '47'
      }, {
        'short': 'NP',
        'name': '尼泊尔',
        'en': 'Nepal',
        'tel': '977'
      }, {
        'short': '',
        'name': '荷属安的列斯',
        'en': 'NetheriandsAntilles',
        'tel': '599'
      }, {
        'short': 'NR',
        'name': '瑙鲁',
        'en': 'Nauru',
        'tel': '674'
      }, {
        'short': 'NZ',
        'name': '新西兰',
        'en': 'NewZealand',
        'tel': '64'
      }, {
        'short': 'OM',
        'name': '阿曼',
        'en': 'Oman',
        'tel': '968'
      }, {
        'short': 'PA',
        'name': '巴拿马',
        'en': 'Panama',
        'tel': '507'
      }, {
        'short': 'PE',
        'name': '秘鲁',
        'en': 'Peru',
        'tel': '51'
      }, {
        'short': 'PF',
        'name': '法属玻利尼西亚',
        'en': 'FrenchPolynesia',
        'tel': '689'
      }, {
        'short': 'PG',
        'name': '巴布亚新几内亚',
        'en': 'PapuaNewCuinea',
        'tel': '675'
      }, {
        'short': 'PH',
        'name': '菲律宾',
        'en': 'Philippines',
        'tel': '63'
      }, {
        'short': 'PK',
        'name': '巴基斯坦',
        'en': 'Pakistan',
        'tel': '92'
      }, {
        'short': 'PL',
        'name': '波兰',
        'en': 'Poland',
        'tel': '48'
      }, {
        'short': 'PR',
        'name': '波多黎各',
        'en': 'PuertoRico',
        'tel': '1787'
      }, {
        'short': 'PT',
        'name': '葡萄牙',
        'en': 'Portugal',
        'tel': '351'
      }, {
        'short': 'PY',
        'name': '巴拉圭',
        'en': 'Paraguay',
        'tel': '595'
      }, {
        'short': 'QA',
        'name': '卡塔尔',
        'en': 'Qatar',
        'tel': '974'
      }, {
        'short': '',
        'name': '留尼旺',
        'en': 'Reunion',
        'tel': '262'
      }, {
        'short': 'RO',
        'name': '罗马尼亚',
        'en': 'Romania',
        'tel': '40'
      }, {
        'short': 'RU',
        'name': '俄罗斯',
        'en': 'Russia',
        'tel': '7'
      }, {
        'short': 'SA',
        'name': '沙特阿拉伯',
        'en': 'SaudiArabia',
        'tel': '966'
      }, {
        'short': 'SB',
        'name': '所罗门群岛',
        'en': 'SolomonIs',
        'tel': '677'
      }, {
        'short': 'SC',
        'name': '塞舌尔',
        'en': 'Seychelles',
        'tel': '248'
      }, {
        'short': 'SD',
        'name': '苏丹',
        'en': 'Sudan',
        'tel': '249'
      }, {
        'short': 'SE',
        'name': '瑞典',
        'en': 'Sweden',
        'tel': '46'
      }, {
        'short': 'SG',
        'name': '新加坡',
        'en': 'Singapore',
        'tel': '65'
      }, {
        'short': 'SI',
        'name': '斯洛文尼亚',
        'en': 'Slovenia',
        'tel': '386'
      }, {
        'short': 'SK',
        'name': '斯洛伐克',
        'en': 'Slovakia',
        'tel': '421'
      }, {
        'short': 'SL',
        'name': '塞拉利昂',
        'en': 'SierraLeone',
        'tel': '232'
      }, {
        'short': 'SM',
        'name': '圣马力诺',
        'en': 'SanMarino',
        'tel': '378'
      }, {
        'short': '',
        'name': '东萨摩亚(美)',
        'en': 'SamoaEastern',
        'tel': '684'
      }, {
        'short': '',
        'name': '西萨摩亚',
        'en': 'SanMarino',
        'tel': '685'
      }, {
        'short': 'SN',
        'name': '塞内加尔',
        'en': 'Senegal',
        'tel': '221'
      }, {
        'short': 'SO',
        'name': '索马里',
        'en': 'Somali',
        'tel': '252'
      }, {
        'short': 'SR',
        'name': '苏里南',
        'en': 'Suriname',
        'tel': '597'
      }, {
        'short': 'ST',
        'name': '圣多美和普林西比',
        'en': 'SaoTomeandPrincipe',
        'tel': '239'
      }, {
        'short': 'SV',
        'name': '萨尔瓦多',
        'en': 'EISalvador',
        'tel': '503'
      }, {
        'short': 'SY',
        'name': '叙利亚',
        'en': 'Syria',
        'tel': '963'
      }, {
        'short': 'SZ',
        'name': '斯威士兰',
        'en': 'Swaziland',
        'tel': '268'
      }, {
        'short': 'TD',
        'name': '乍得',
        'en': 'Chad',
        'tel': '235'
      }, {
        'short': 'TG',
        'name': '多哥',
        'en': 'Togo',
        'tel': '228'
      }, {
        'short': 'TH',
        'name': '泰国',
        'en': 'Thailand',
        'tel': '66'
      }, {
        'short': 'TJ',
        'name': '塔吉克斯坦',
        'en': 'Tajikstan',
        'tel': '992'
      }, {
        'short': 'TM',
        'name': '土库曼斯坦',
        'en': 'Turkmenistan',
        'tel': '993'
      }, {
        'short': 'TN',
        'name': '突尼斯',
        'en': 'Tunisia',
        'tel': '216'
      }, {
        'short': 'TO',
        'name': '汤加',
        'en': 'Tonga',
        'tel': '676'
      }, {
        'short': 'TR',
        'name': '土耳其',
        'en': 'Turkey',
        'tel': '90'
      }, {
        'short': 'TT',
        'name': '特立尼达和多巴哥',
        'en': 'TrinidadandTobago',
        'tel': '1809'
      }, {
        'short': 'TW',
        'name': '台湾省',
        'en': 'Taiwan',
        'tel': '886'
      }, {
        'short': 'TZ',
        'name': '坦桑尼亚',
        'en': 'Tanzania',
        'tel': '255'
      }, {
        'short': 'UA',
        'name': '乌克兰',
        'en': 'Ukraine',
        'tel': '380'
      }, {
        'short': 'UG',
        'name': '乌干达',
        'en': 'Uganda',
        'tel': '256'
      }, {
        'short': 'US',
        'name': '美国',
        'en': 'UnitedStatesofAmerica',
        'tel': '1'
      }, {
        'short': 'UY',
        'name': '乌拉圭',
        'en': 'Uruguay',
        'tel': '598'
      }, {
        'short': 'UZ',
        'name': '乌兹别克斯坦',
        'en': 'Uzbekistan',
        'tel': '233'
      }, {
        'short': 'VC',
        'name': '圣文森特岛',
        'en': 'SaintVincent',
        'tel': '1784'
      }, {
        'short': 'VE',
        'name': '委内瑞拉',
        'en': 'Venezuela',
        'tel': '58'
      }, {
        'short': 'VN',
        'name': '越南',
        'en': 'Vietnam',
        'tel': '84'
      }, {
        'short': 'YE',
        'name': '也门',
        'en': 'Yemen',
        'tel': '967'
      }, {
        'short': 'YU',
        'name': '南斯拉夫',
        'en': 'Yugoslavia',
        'tel': '381'
      }, {
        'short': 'ZA',
        'name': '南非',
        'en': 'SouthAfrica',
        'tel': '27'
      }, {
        'short': 'ZM',
        'name': '赞比亚',
        'en': 'Zambia',
        'tel': '260'
      }, {
        'short': 'ZR',
        'name': '扎伊尔',
        'en': 'Zaire',
        'tel': '243'
      }, {
        'short': 'ZW',
        'name': '津巴布韦',
        'en': 'Zimbabwe',
        'tel': '263'
      }],
      loading: false,
      msg: "",
      checked: false,
      email: null,
      pageResult: {},

      columns: [
				{prop:"email", label:"email", minWidth:50},
				{prop:"password", label:"password", minWidth:100},
				{prop:"passwordagain", label:"passwordagain", minWidth:150},
				{prop:"phone", label:"phone", minWidth:80},
				
      ],
      en:"",
      //加
      loginForm: {
        account: '',
        //password: ""
       // email:'',
       // password:'',
         tel:"",

         name: "",
         hospital: '',
         diccode:"",
         qehao:"",
        identificationnumber: "",
        emialparams:'',
        phoneparams:''
      },
      rules: {
        email: [
          {
            required: true,
            message: "Please enter your email ",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "Please enter your password ",
            trigger: "blur"
          }
        ],
        passwordagain: [
          {
            required: true,
            message: "Please enter your passwordagain ",
            trigger: "blur"
          }
        ],
        phone: [
          {
            required: true,
            message: "Please enter your phone ",
            trigger: "blur"
          }
        ],
           name: [{ required: true, message: "Please enter your name ", trigger: "blur"}],
           diccode: [{ required: true, message: "Please enter your diccode ", trigger: "blur" }],
           identificationnumber: [{ required: true, message: "Please enter your identificationnumber ", trigger: "blur" }],
      }
      //加
    };
  },
  // computed: {
  //   fullName: function() {
  //     if ((bailing = null)) {
  //       this.$router.push({
  //         path: "/"
  //       });
  //     }
  //   }
  // },
  // created() {
  //   this.getParams();
  // },
created()
  {
    
    this.getParams();
  
  },
 mounted() {
 // window.addEventListener('scroll', this.windowScroll)
},
  methods: {
      open() {
      // let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
     
    this.$alert('<div style=" overflow-y:auto; overflow-x:auto; width:340px; height:600px;”>  <p>Last updated: March 21, 2020 <br> We built this App as a Free App. This SERVICE is provided at no cost and is intended for use as is. This page is used to inform visitors regarding our policies with the collection, use, and disclosure of Personal Information if anyone decided to use our Service.  If you choose to use our Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that we collect is used for providing and improving the Service. We will not use or share your information with anyone except as described in this Privacy Policy. The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at this App unless otherwise defined in this Privacy Policy.</P> <p style=" margin-top:18px;font-size:19px;margin-right:60px;">  <p>Last updated: March 21, 2020 <br> We built this App as a Free App. This SERVICE is provided at no cost and is intended for use as is. This page is used to inform visitors regarding our policies with the collection, use, and disclosure of Personal Information if anyone decided to use our Service.  If you choose to use our Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that we collect is used for providing and improving the Service. We will not use or share your information with anyone except as described in this Privacy Policy. The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at this App unless otherwise defined in this Privacy Policy.</P><p style="margin-top:18px;font-size:19px;"> Information Collection and Use  </p>  <p>    For a better experience, while using our Service, We may require you to provide us with certain personally identifiable information. The information that we request will be retained on your device and is not collected by us in any way. </p> <p align="left" style="margin-top:18px;font-size:19px;">   Log Data  </p>  <p> We want to inform you that whenever you use our Service, in a case of an error in the app we collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing our Service, the time and date of your use of the Service, and other statistics.  </p>  <p align="left" style="margin-top:18px;font-size:19px;">  Cookies </p>  <p> Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device’s internal memory. This Service does not use these “cookies” explicitly. However, the app may use third party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service. </p> <p align="left" style="margin-top:18px;font-size:19px;"> Security </p> <p>&nbsp; &nbsp; &nbsp; &nbsp;We value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee its absolute security. </p>  <p align="left" style="margin-top:18px;font-size:19px;">  Links to Other Sites  </p> <p>&nbsp; &nbsp; &nbsp; &nbsp;This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by us. Therefore, we strongly advise you to review the Privacy Policy of these websites. We have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.  </p> <p align="left" style="margin-top:18px;font-size:19px;">   Children’s Privacy  </p> <p>&nbsp; &nbsp; &nbsp; &nbsp;These Services do not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. In the case, we discover that a child under 13 has provided us with personal information, we immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact us so that we will be able to do necessary actions. </p>  <p align="left" style="margin-top:18px;font-size:19px;">   Changes to This Privacy Policy  </p> <p>We may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted on this page. </p> <p align="left" style="margin-top:18px;font-size:19px;"> Contact Us  </p> <p>   &nbsp; &nbsp; &nbsp; &nbsp;If you have any questions or suggestions about our Privacy Policy, do not hesitate to contact us.  service@aeonmed.com  marketing@aeonmed.com  </p></div>', 'Privacy Policy', 
         {
          customClass:"msgBox",
          
          dangerouslyUseHTMLString: true,
         
        });
        
        //this.$alert('Last updated: March 21, 2020 We built this App as a Free App. This SERVICE is provided at no cost and is intended for use as is. This page is used to inform visitors regarding our policies with the collection, use, and disclosure of Personal Information if anyone decided to use our Service. If you choose to use our Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that we collect is used for providing and improving the Service. We will not use or share your information with anyone except as described in this Privacy Policy. The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at this App unless otherwise defined in this Privacy Policy. Information Collection and Use For a better experience, while using our Service, We may require you to provide us with certain personally identifiable information. The information that we request will be retained on your device and is not collected by us in any way. Log Data We want to inform you that whenever you use our Service, in a case of an error in the app we collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing our Service, the time and date of your use of the Service, and other statistics.Cookies Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device s internal memory.This Service does not use these “cookies” explicitly. However, the app may use third party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.Security We value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee its absolute security.Links to Other Sites This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by us. Therefore, we strongly advise you to review the Privacy Policy of these websites. We have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.Children s Privacy These Services do not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. In the case, we discover that a child under 13 has provided us with personal information, we immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact us so that we will be able to do necessary actions.Changes to This Privacy Policy We may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted on this page.Contact Us If you have any questions or suggestions about our Privacy Policy, do not hesitate to contact us. service@aeonmed.commarketing@aeonmed.com', 'Privacy Policy',
        // {
          // confirmButtonText: '确定',
          // callback: action => {
          //   this.$message({
          //     type: 'info',
          //     message: `action: ${ action }`
          //   });
          // }
       // }
        //);
        //  const h = this.$createElement;
        // this.$msgbox({
        //   title: 'Privacy Policy',
        //   message: h('p', null, [
        //            h('span', null, 'Last updated: March 21, 2020 '),
        //             h('span', null, 'Last updated: March 21, 2020 '),
        //           // h('i', { style: 'color: teal' }, 'VNode')
        //   ]),
         // showCancelButton: true,
         // confirmButtonText: 'Agree',
         // cancelButtonText: 'cancel',
          // beforeClose: (action, instance, done) => {
          //   if (action === 'confirm') {
          //     instance.confirmButtonLoading = true;
          //     instance.confirmButtonText = '执行中...';
          //     setTimeout(() => {
          //       done();
          //       setTimeout(() => {
          //         instance.confirmButtonLoading = false;
          //       }, 300);
          //     }, 3000);
          //   } else {
          //     done();
          //   }
          // }
       // })
        // .then(action => {
        //   this.$message({
        //     type: 'info',
        //     message: 'action: ' + action
        //   });
        // });
      
      },
getParams:function ()
      { 
          let emailparams3=this.$route.query.letemail4;
          let passwordparams3=this.$route.query.letpassword4;
          let passwordagainparams3=this.$route.query.letpasswordagain4;
          let phoneparams3=this.$route.query.letphone4;
         // document.body.scrollTop = document.documentElement.scrollTop = 0
          
      // this.$router.push('/ypWebsite')
      // document.body.scrollTop = document.documentElement.scrollTop = 0
      if(emailparams3&&passwordparams3&&passwordagainparams3&&phoneparams3){
         
            this.loginForm.phone=phoneparams3;
            this.loginForm.password=passwordparams3;
            this.loginForm.passwordagain=passwordagainparams3;
            this.loginForm.email=emailparams3;


      }

      },
    chakan(){
       
       //console.log("进来了")
      //  let letemail = this.loginForm.email;
      //  let letpassword = this.loginForm.password;
      //  let letpasswordagain = this.loginForm.passwordagain;
      //  let letphone = this.loginForm.phone;
       this.$router.push({
                path: "/privacyExplain",
                query: {
                  letemail: this.loginForm.email,
                  letpassword: this.loginForm.password,
                  letpasswordagain: this.loginForm.passwordagain,
                  letphone: this.loginForm.phone,
                
                }
              });
    },
  change (val) {
       
      this.$emit('input', val)
      var num= val.replace(/[^0-9]/ig,"");
      // let bailing =this.$emit('input', val);
      // var  str = JSON.stringify(data.tel)
    //  console.log(num+" 0151551515212")
      // let ltel=this.$emit('input', val)
      // //  var str = JSON.parse(ltel);
      // console.log(str+" 0151551515212")
	    this.loginForm.quhao ="+" + num
    }
,
 change1 (num) {
       
      // this.$emit('input', val)
 //console.log(num+"bailing");
    }
,
    // getParams: function() {
    //       let bailing1;
    //       bailing1 = Cookies.get().token;
    //       let emailparams1=this.$route.query.letemail1;
    //       let passwordparams1=this.$route.query.letpassword1;
    //       let passwordagainparams1=this.$route.query.letpasswordagain1;
    //       let phoneparams1=this.$route.query.letphone1;
    //       let userInfo1 = {emailparams1:this.$route.query.letemail1, passwordparams1:this.$route.query.letpassword1,passwordagainparams1:this.$route.query.letpasswordagain1,phoneparams1:this.$route.query.letphone1}
    //       this.pageResult = userInfo1
    //       console.log(emailparams1+passwordparams1+passwordagainparams1+phoneparams1)
    // },
    password_blur(){
      
    if (this.loginForm.password != this.loginForm.passwordagain) {
        this.$message({
          message: "Two password entries are inconsistent！",
          type: "error"
        });
        return false;
      }
    },
    phone_blur() {
      // 规则对象(flag)
      var flag = new RegExp(
        "[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]"
      );
      // 判断 even 是否包含特殊字符
      if (flag.test(this.loginForm.phone)) {
       this.$message({
          message: "Phone format error",
          type: "error"
        });
      }
        return false;
    },
    // email_blur() {
    //   var verify = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;

    //   if (!verify.test(this.loginForm.email)) {
    //     this.$message({
    //       message: "Email format error！",
    //       type: "error"
    //     });
    //   }
    //     return false;
    // },
    //加
    zhuce() {

        if (!this.loginForm.diccode) {
          this.$message({
           message: "The country is empty",
          type: "error"
        });
         return false;
      }
       if(!this.loginForm.phone){
          this.$message({
          message: "The phone is empty ！",
          type: "error"
        });
        return false;
       } 
       if(!this.loginForm.password){
          this.$message({
          message: "The password is empty ！",
          type: "error"
        });
        return false;
       }
        if(!this.loginForm.passwordagain){
          this.$message({
          message: "The passwordagain is empty ！",
          type: "error"
        });
        return false;
       }
        if(!this.loginForm.email){
          this.$message({
          message: "The email is empty ！",
          type: "error"
        });
        return false;
       }
      if (!this.loginForm.name) {
          this.$message({
           message: "The name is empty",
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
       var verify = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
       if(!verify.test(this.loginForm.email)){
          this.$message({
          message: "Email format error！",
          type: "error"
        });
     // }
        return false;
      } 
        //if (!verify.test(this.loginForm.email)) {
      if (this.loginForm.password != this.loginForm.passwordagain) {
        this.$message({
          message: "Two password entries are inconsistent！",
          type: "error"
        });
        return false;
      }
      else {
       // console.log(this.loginForm.diccode.value.tel+"22200000004555555")    
        this.loading = true;
        
        // console.log(this.item.tel+"333333333333")
     //   let letemil = this.loginForm.email;
      //  let letdiccode = this.loginForm.diccode.replace("[^\\d.]","")
      //  console.log("111111114444444"+letdiccode)
        let userInfo = {
          email: this.loginForm.email,
          password: this.loginForm.password,
          code:this.loginForm.quhao,
          phone:this.loginForm.phone,
          name:this.loginForm.name,
          diccode:this.loginForm.diccode.replace(/[^a-zA-Z]/g,''),
          identificationnumber:this.loginForm.identificationnumber
        };
      
        this.$api.englishuser.save(userInfo).then(res => {
            if (res.msg != null) {
              this.$message({
                message: res.msg,
                type: "error"
              });
            } else {
              let bailing = Cookies.get().token;
              // Cookies.set("token", tokenparams); // 放置token到Cookie
              sessionStorage.setItem("user", userInfo.account); // 保存用户到本地会话
              localStorage.setItem("token", bailing);
              
              this.$router.push({
                path: "/mainpage",
                // query: {
                //   letemail: this.loginForm.email,
                //   letphone: this.loginForm.phone,
                //   lettoken: this.$route.params.lettoken
                // }
              });
            }

            this.loading = false;
          })
          .catch(res => {
            this.$message({
              message: res.message,
              type: "error"
            });
          });
      }
    }
    //加
  }
};
</script>

<style lang="scss">
.div-inline {
  float: left;
}
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
}
.msgBox{
  width: 95%;
  height: 700px;
}
.aaa {
  float: left;
}
.bbb {
  margin-left: 50px;
  margin-top: 28px;

  float: left;
}
.ccc {
  float: left;
}
</style>
