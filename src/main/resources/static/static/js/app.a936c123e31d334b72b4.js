webpackJsonp([57],{"+MaT":function(module,__webpack_exports__,__webpack_require__){"use strict";function getOSType(){var e=navigator.userAgent,n=e.indexOf("Android")>-1||e.indexOf("Adr")>-1,t=!!e.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);return n?1:t?2:3}function getQueryString(e){var n=new RegExp("(^|&)"+e+"=([^&]*)(&|$)","i"),t=window.location.search.substr(1).match(n);return null!=t?unescape(t[2]):null}function getParam(id){var url=window.location.href;url+="";var regstr="/(\\?|\\&)"+id+"=([^\\&]+)/",reg=eval(regstr),result=url.match(reg);if(result&&result[2])return result[2]}__webpack_exports__.a=getOSType,__webpack_exports__.b=getParam},"/Hv2":function(e,n){},"5OHe":function(e,n){},CE3C:function(e,n){},GYqG:function(e,n,t){"use strict";n.a=function(e){return Object(o.a)({url:"api/login/bind",method:"post",data:e})},n.b=function(e){return Object(o.a)({url:"api/login/getCode",method:"get",params:{telNum:e}})},n.f=function(){return Object(o.a)({url:"api/login/login",method:"get"})},n.g=function(){return Object(o.a)({url:"api/login/unbind",method:"get"})},n.e=function(){return Object(o.a)({url:"api/myInfo/list",method:"get"})},n.d=function(e){return Object(o.a)({url:"api/register/getInstroduce",method:"get",params:{name:e}})},n.c=function(e,n,t){return Object(o.a)({url:"api/register/getDept",method:"get",params:{name:e,pageNumber:n,pageSize:t}})};var o=t("vLgD")},NHnr:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var o=t("xd7I"),i=t("3cXf"),a=t.n(i),r=(t("+MaT"),t("vLgD"));var c={name:"App",mounted:function(){this.initSignature()},methods:{initSignature:function(){var e=this,n=location.href.split("#")[0];(function(e){return Object(r.a)({url:"/api/GetWXConfig",method:"get",params:{url:e}})})(n).then(function(n){console.log(n);var t={timestamp:n.data.timestamp,nonceStr:n.data.nonceStr,signature:n.data.signature,appId:n.data.appid};e.$store.commit("SET_SIGNATURE",t),sessionStorage.setItem("signatureInfo",a()(t))}).catch(function(t){e.$toast("网络异常，请重试！url是"+n)})}},watch:{$route:function(e,n){document.body.scrollTop=0,document.documentElement.scrollTop=0}}},l={render:function(){var e=this.$createElement,n=this._self._c||e;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},staticRenderFns:[]};var u=t("C7Lr")(c,l,!1,function(e){t("CE3C")},null,null).exports,s=t("3XdE"),d=[{path:"/",name:"layout",redirect:"/business",component:function(){return t.e(38).then(t.bind(null,"x2HD"))},children:[{path:"/business",name:"business",component:function(){return Promise.all([t.e(0),t.e(1)]).then(t.bind(null,"FvPH"))}},{path:"/finance",name:"finance",component:function(){return Promise.all([t.e(0),t.e(2)]).then(t.bind(null,"5QCq"))}},{path:"/ettlementRecords",name:"EttlementRecords",component:function(){return Promise.all([t.e(0),t.e(54)]).then(t.bind(null,"DXnP"))}},{path:"/personInfo",name:"personInfo",component:function(){return Promise.all([t.e(0),t.e(8)]).then(t.bind(null,"vffp"))}},{path:"/castSeedsApply",name:"castSeedsApply",component:function(){return Promise.all([t.e(0),t.e(34)]).then(t.bind(null,"84rN"))}},{path:"/feedRecord",name:"feedRecord",component:function(){return Promise.all([t.e(0),t.e(46)]).then(t.bind(null,"11H8"))}},{path:"/deadApplication",name:"deadApplication",component:function(){return Promise.all([t.e(0),t.e(5)]).then(t.bind(null,"vPsL"))}},{path:"/salesNotice",name:"salesNotice",component:function(){return Promise.all([t.e(0),t.e(6)]).then(t.bind(null,"0jxD"))}},{path:"/materialMenu",name:"materialMenu",component:function(){return Promise.all([t.e(0),t.e(3)]).then(t.bind(null,"dwSu"))}},{path:"/getFeed",name:"getFeed",component:function(){return Promise.all([t.e(0),t.e(29)]).then(t.bind(null,"4bfx"))}},{path:"/getFeedRecord",name:"getFeedRecord",component:function(){return Promise.all([t.e(0),t.e(51)]).then(t.bind(null,"+o4N"))}},{path:"/getFeedRecordDetail",name:"getFeedRecordDetail",component:function(){return Promise.all([t.e(0),t.e(24)]).then(t.bind(null,"uWTf"))}},{path:"/getMedicine",name:"getMedicine",component:function(){return Promise.all([t.e(0),t.e(4)]).then(t.bind(null,"JDeg"))}},{path:"/getMedicineRecord",name:"getMedicineRecord",component:function(){return Promise.all([t.e(0),t.e(36)]).then(t.bind(null,"2VPJ"))}},{path:"/getMedicineRecordDetail",name:"getMedicineRecordDetail",component:function(){return Promise.all([t.e(0),t.e(7)]).then(t.bind(null,"AcWF"))}},{path:"/needBalance",name:"needBalance",component:function(){return Promise.all([t.e(0),t.e(17)]).then(t.bind(null,"Q8fd"))}},{path:"/needBalanceRecord",name:"needBalanceRecord",component:function(){return Promise.all([t.e(0),t.e(15)]).then(t.bind(null,"L9Ac"))}},{path:"/needBalanceDetail",name:"needBalanceDetail",component:function(){return Promise.all([t.e(0),t.e(32)]).then(t.bind(null,"nNqO"))}},{path:"/financialApply",name:"financialApply",component:function(){return Promise.all([t.e(0),t.e(48)]).then(t.bind(null,"hVJM"))}},{path:"/financialApplyDetail",name:"financialApplyDetail",component:function(){return Promise.all([t.e(0),t.e(23)]).then(t.bind(null,"kjzt"))}},{path:"/financialApplyRecord",name:"financialApplyRecord",component:function(){return Promise.all([t.e(0),t.e(44)]).then(t.bind(null,"DgMu"))}},{path:"/deviceApply",name:"deviceApply",component:function(){return Promise.all([t.e(0),t.e(19)]).then(t.bind(null,"ahEN"))},meta:{keepAlive:!0}},{path:"/searchServiceName",name:"searchServiceName",component:function(){return Promise.all([t.e(0),t.e(26)]).then(t.bind(null,"n4yc"))}},{path:"/deviceApplyRecord",name:"deviceApplyRecord",component:function(){return Promise.all([t.e(0),t.e(42)]).then(t.bind(null,"qiU6"))}},{path:"/deviceApplyDetail",name:"deviceApplyDetail",component:function(){return Promise.all([t.e(0),t.e(40)]).then(t.bind(null,"gnf4"))}},{path:"/RecipientsRecords",name:"RecipientsRecords",component:function(){return Promise.all([t.e(0),t.e(11)]).then(t.bind(null,"DWL7"))}},{path:"/batchRecords",name:"BatchRecords",component:function(){return Promise.all([t.e(0),t.e(49)]).then(t.bind(null,"Vr/M"))}},{path:"/training",name:"Training",component:function(){return Promise.all([t.e(0),t.e(21)]).then(t.bind(null,"pBus"))}},{path:"/balanceQuery",name:"BalanceQuery",component:function(){return Promise.all([t.e(0),t.e(30)]).then(t.bind(null,"/i9G"))}},{path:"/billRecordDetails",name:"billRecordDetails",component:function(){return Promise.all([t.e(0),t.e(22)]).then(t.bind(null,"1GFw"))}},{path:"/openAccount",name:"OpenAccount",component:function(){return Promise.all([t.e(0),t.e(41)]).then(t.bind(null,"TkWP"))},meta:{keepAlive:!0}},{path:"/searchIntroducer",name:"searchIntroducer",component:function(){return t.e(37).then(t.bind(null,"cymx"))}},{path:"/searchAddress",name:"searchAddress",component:function(){return t.e(25).then(t.bind(null,"WCqw"))}},{path:"/openText",name:"OpenText",component:function(){return Promise.all([t.e(0),t.e(28)]).then(t.bind(null,"qOwj"))}},{path:"/castSeedsRecord",name:"CastSeedsRecord",component:function(){return Promise.all([t.e(0),t.e(27)]).then(t.bind(null,"Ddic"))}},{path:"/castSeedsRecordDetail",name:"CastSeedsRecordDetail",component:function(){return Promise.all([t.e(0),t.e(18)]).then(t.bind(null,"okqX"))}},{path:"/deadApplicationRecord",name:"DeadApplicationRecord",component:function(){return Promise.all([t.e(0),t.e(35)]).then(t.bind(null,"Kvki"))}},{path:"/deadApplicationRecordDetail",name:"DeadApplicationRecordDetail",component:function(){return Promise.all([t.e(0),t.e(10)]).then(t.bind(null,"xgD3"))}},{path:"/myInformation",name:"MyInformation",component:function(){return Promise.all([t.e(0),t.e(33)]).then(t.bind(null,"rfHN"))}},{path:"/problemFeedback",name:"problemFeedback",component:function(){return Promise.all([t.e(0),t.e(47)]).then(t.bind(null,"ifPb"))}},{path:"/askQuestions",name:"askQuestions",component:function(){return Promise.all([t.e(0),t.e(14)]).then(t.bind(null,"e5qF"))}},{path:"/feedbackDetail",name:"feedbackDetail",component:function(){return Promise.all([t.e(0),t.e(16)]).then(t.bind(null,"351U"))}},{path:"/feedRecordList",name:"FeedRecordList",component:function(){return Promise.all([t.e(0),t.e(43)]).then(t.bind(null,"1sNi"))}},{path:"/feedRecordListDetail",name:"FeedRecordListDetail",component:function(){return Promise.all([t.e(0),t.e(39)]).then(t.bind(null,"EsFi"))}},{path:"/bindInfo",name:"bindInfo",component:function(){return Promise.all([t.e(0),t.e(55)]).then(t.bind(null,"C+Mj"))}},{path:"/register",name:"Register",component:function(){return Promise.all([t.e(0),t.e(31)]).then(t.bind(null,"ad1A"))},meta:{keepAlive:!0}},{path:"/viedoDetail",name:"ViedoDetail",component:function(){return Promise.all([t.e(0),t.e(20)]).then(t.bind(null,"gswg"))}},{path:"/noticeList",name:"noticeList",component:function(){return Promise.all([t.e(0),t.e(50)]).then(t.bind(null,"qZK+"))}},{path:"/helpList",name:"HelpList",component:function(){return Promise.all([t.e(0),t.e(45)]).then(t.bind(null,"qsya"))}},{path:"/helpDetails",name:"HelpDetails",component:function(){return Promise.all([t.e(0),t.e(53)]).then(t.bind(null,"3B2Z"))}},{path:"/addGoOutRecord",name:"AddGoOutRecord",component:function(){return Promise.all([t.e(0),t.e(52)]).then(t.bind(null,"8YsE"))}},{path:"/clearRecord",name:"ClearRecord",component:function(){return Promise.all([t.e(0),t.e(13)]).then(t.bind(null,"LhR7"))}},{path:"/goOutDetail",name:"GoOutDetail",component:function(){return Promise.all([t.e(0),t.e(9)]).then(t.bind(null,"DvE8"))}},{path:"/takePhotoList",name:"TakePhotoList",component:function(){return Promise.all([t.e(0),t.e(12)]).then(t.bind(null,"fEyU"))}}]}],p=t("E4C3"),m=t.n(p),f=(t("ve9D"),t("TIfe")),h=t("GYqG"),b=t("wSez"),g=t.n(b),P=t("9rMa"),L={state:{code:Object(f.b)(),currentPigfarm:{},token:Object(f.d)(),signature:{},userInfo:Object(f.e)()},mutations:{SET_USERINFO:function(e,n){o.default.set(e,"userInfo",n),Object(f.k)(n)},SET_CODE:function(e,n){e.code=n,Object(f.h)(n)},SET_CURRENT_PIGFARM:function(e,n){o.default.set(e,"currentPigfarm",n)},SET_TOKEN:function(e,n){e.token=n,Object(f.j)(n)},SET_SIGNATURE:function(e,n){o.default.set(e,"signature",n)}},actions:{}},v=t("IHPB"),I=t.n(v),R={state:{photoList:Object(f.c)(),allPhotoLists:Object(f.a)(),userTaker:Object(f.f)()},mutations:{SET_PHOTOLIST:function(e,n){if(console.log(n,"obj"),e.photoList&&e.photoList.length>0){var t=[];e.photoList.forEach(function(e,n){t.push(e.itemId)}),-1==t.indexOf(n[0].itemId)?e.photoList=e.photoList.concat(n):(console.log(e.photoList),e.photoList=e.photoList.map(function(e,t){return e.itemId===n[0].itemId?{itemId:n[0].itemId,name:n[0].name,data:n[0].data,photoList:n[0].photoList,serverId:n[0].serverId}:(console.log(e),{itemId:e.itemId,name:e.name,data:e.data,photoList:e.photoList,serverId:e.serverId})}))}else e.photoList=[].concat(I()(n));console.log(e.photoList),o.default.set(e,"photoList",e.photoList),Object(f.i)(e.photoList),e.allPhotoLists.photoList=e.photoList,e.allPhotoLists.serviceSite.forEach(function(n,t){e.allPhotoLists.serviceSite[t].itemInfoList.forEach(function(n,o){for(var i=0;i<e.photoList.length;i++)e.photoList[i].itemId==e.allPhotoLists.serviceSite[t].itemInfoList[o].itemId&&(e.allPhotoLists.serviceSite[t].itemInfoList[o].photoList=e.photoList[i].photoList)})}),Object(f.g)(e.allPhotoLists),console.log(e.allPhotoLists,"allphotoListstate")},CLEAR_PHOTOLIST:function(e){o.default.set(e,"photoList",[]),Object(f.i)()},SET_ALLPHOTOLIST:function(e,n){console.log(n,"vuex-allPhotoList"),o.default.set(e,"allPhotoLists",n),Object(f.g)(n)},CLEAR_ALLPHOTOLIST:function(e){o.default.set(e,"allPhotoLists",[]),Object(f.g)()},SET_USERTAKER:function(e,n){console.log(n,"vuex-setUserTaker"),o.default.set(e,"userTaker",n),Object(f.l)(n)},CLEAR_USERTAKER:function(e){o.default.set(e,"userTaker",{}),Object(f.l)()}},actions:{}},O={code:function(e){return e.user.code},currentPigfarm:function(e){return e.user.currentPigfarm},token:function(e){return e.user.token},signature:function(e){return e.user.signature},userInfo:function(e){return e.user.userInfo},photoList:function(e){return e.photo.photoList},allPhotoLists:function(e){return e.photo.allPhotoLists},serviceSites:function(e){return e.photo.serviceSites}};o.default.use(P.a);var S=new P.a.Store({modules:{user:L,photo:R},getters:O});o.default.use(s.a);var A=new s.a({routes:d});A.beforeEach(function(e,n,t){m.a.start(),"bindInfo"==e.name||"Register"==e.name||"searchAddress"==e.name||"searchIntroducer"==e.name||"OpenText"==e.name||"HelpList"==e.name||"HelpDetails"==e.name?t():Object(f.e)()&&""!==Object(f.e)()?t():Object(h.f)().then(function(e){991===e.status?t("/bindInfo"):200==e.status&&Object(h.e)().then(function(e){var n=e.data,o={id:n.id,name:n.fname,size:n.cfwinternum};S.commit("SET_USERINFO",o),t()}).catch(function(e){t("/bindInfo")})}).catch(function(e){t("/bindInfo")})}),A.afterEach(function(e){m.a.done()});var T=A,D=(t("UAgs"),t("WWTk"),t("5OHe"),t("INv7")),E=t.n(D),j=t("t+b9"),_=t.n(j);t("/Hv2");o.default.use(E.a),o.default.use(_.a),o.default.use(g.a),o.default.config.productionTip=!1,new o.default({el:"#app",router:T,store:S,components:{App:u},template:"<App/>"})},TIfe:function(e,n,t){"use strict";n.k=function(e){sessionStorage.setItem("userInfo",i()(e))},n.e=function(){return JSON.parse(sessionStorage.getItem("userInfo"))},n.i=function(e){sessionStorage.setItem("photoList",i()(e))},n.c=function(){return JSON.parse(sessionStorage.getItem("photoList"))},n.g=function(e){sessionStorage.setItem("allPhotoLists",i()(e))},n.a=function(){return JSON.parse(sessionStorage.getItem("allPhotoLists"))},n.f=function(e){sessionStorage.setItem("userTaker",i()(e))},n.l=function(){return JSON.parse(sessionStorage.getItem("userTaker"))},n.h=function(e){r.a.set("code",e)},n.b=function(){return r.a.get("code")},n.d=function(){return r.a.get(c)},n.j=function(e){return r.a.set(c,e)};var o=t("3cXf"),i=t.n(o),a=t("uAC3"),r=t.n(a),c="Admin-Token"},UAgs:function(e,n){var t,o,i,a;t=window,o=document.documentElement||document.body,i="orientationchange"in window?"orientationchange":"resize",(a=function(){var e=o.clientWidth;o.style.fontSize=e>=375?"100px":e/375*100+"px"})(),t.addEventListener(i,a,!1)},WWTk:function(e,n){},vLgD:function(e,n,t){"use strict";var o=t("hRKE"),i=(t.n(o),t("rVsN")),a=t.n(i),r=t("aozt"),c=t.n(r),l=t("wSez"),u=(t.n(l),c.a.create({baseURL:"",timeout:35e3}));u.interceptors.request.use(function(e){return e},function(e){a.a.reject(e)}),u.interceptors.response.use(function(e){var n=e.data;return n},function(e){return a.a.reject(e)}),n.a=u},ve9D:function(e,n){}},["NHnr"]);
//# sourceMappingURL=app.a936c123e31d334b72b4.js.map