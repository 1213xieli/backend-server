webpackJsonp([40],{fX1z:function(t,e){},gnf4:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("teIl"),i=a("3gsG"),n=a("pzWD"),r=a("GE2/"),c=a("+MaT"),l=a("wSez"),v={name:"deviceApplyDetail",data:function(){return{deviceDetailsData:{custName:"",bizDate:"",equipAmt:"",entrustorName:"",entrustorIdCard:"",isEntrust:!1,entrys:[{}],actEntrys:[]},rcordId:"",recordState:"",billStatusIndex:"",signerImgs:"",isShowSignature:!0}},components:{myHeader:s.a,Signature:i.a,SubmitBtn:n.a},created:function(){this._queryEquipmentApplyInfoById(),this.rcordId=Object(c.b)("rcordId").replace(/%3D/g,"=").trim(),l.Indicator.open({text:"加载中...",spinnerType:"fading-circle"})},watch:{recordState:function(t,e){var a=this;2==this.recordState&&30==this.billStatusIndex&&this.$nextTick(function(){a.isShowSignature=!0,a.$refs.signature.fromDataURL(a.signerImgs),a.$refs.signature.disabled=!0}),30==this.billStatusIndex&&2!=this.recordState&&this.$nextTick(function(){a.isShowSignature=!0,a.$refs.signature.disabled=!1}),30!=this.billStatusIndex&&(this.isShowSignature=!1)}},mounted:function(){},methods:{_queryEquipmentApplyInfoById:function(){var t=this,e=Object(c.b)("rcordId").replace(/%3D/g,"=").trim();Object(r.q)(e).then(function(e){if(l.Indicator.close(),e.data){var a=e.data;a.billStatusIndex&&(t.billStatusIndex=a.billStatusIndex),a.state&&(t.recordState=a.state),a.signerUrl.length&&(t.signerImgs=a.signerUrl[0].img);var s=[],i=[];a.entrys&&a.entrys.length&&(s=a.entrys.map(function(t){return{materialName:t.materialName,qty:t.qty,unit:t.unit,model:t.model,price:t.price,amount:t.amount}})),a.actEntrys&&a.actEntrys.length&&(i=a.actEntrys.map(function(t){return{materialName:t.materialName,qty:t.qty,unit:t.unit,model:t.model,price:t.price,amount:t.amount}})),t.deviceDetailsData={custName:a.custName,bizDate:a.bizDate,equipAmt:a.equipAmt,entrustorName:a.entrustorName,entrustorIdCard:a.entrustorIdCard,isEntrust:a.isEntrust,entrys:s,actEntrys:i}}}).catch(function(t){l.Indicator.close()})},submitData:function(){var t=this,e=this.$refs.signature.save(),a={rcordId:this.rcordId,fileEntry:{imgContent:e,imgType:"png"}};Object(r.z)(a).then(function(e){t.$toast({message:"签字确认成功！",duration:1e3}),t.$router.push("/deviceApplyRecord")})}}},d={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"deviceApplyDetail",staticStyle:{"max-width":"100%","box-sizing":"border-box"}},[s("myHeader",{attrs:{isBack:!0,backPath:"/deviceApplyRecord",title:"申请详情"}}),t._v(" "),s("div",{staticClass:"seat-head"}),t._v(" "),s("div",{staticClass:"list-block"},[s("mt-cell",{attrs:{title:"养户："}},[t._v(t._s(t.deviceDetailsData.custName))]),t._v(" "),s("mt-cell",{attrs:{title:"申请时间："}},[t._v(t._s(t.deviceDetailsData.bizDate))]),t._v(" "),s("mt-cell",{attrs:{title:"累计已领设备金额："}},[t._v(t._s(t.deviceDetailsData.equipAmt+"元"))])],1),t._v(" "),s("div",{staticClass:"service-apply-detail"},[t._m(0),t._v(" "),t._l(t.deviceDetailsData.entrys,function(e,a){return s("div",{key:a,staticClass:"details-container"},[s("div",{staticClass:"detail-item"},[s("div",{staticClass:"left"},[s("span",[t._v("设备名称：")]),t._v(" "),s("span",[t._v(t._s(e.materialName))])]),t._v(" "),s("div",{staticClass:"right"},[s("span",[t._v("数量：")]),t._v(" "),s("span",[t._v(t._s(e.qty))])])]),t._v(" "),s("div",{staticClass:"detail-item"},[s("div",{staticClass:"left"},[s("span",[t._v("计量单位：")]),t._v(" "),s("span",[t._v(t._s(e.unit))])]),t._v(" "),s("div",{staticClass:"right"},[s("span",[t._v("规格：")]),t._v(" "),s("span",[t._v(t._s(e.model))])])]),t._v(" "),s("div",{staticClass:"detail-item"},[s("div",{staticClass:"left"},[s("span",[t._v("设备单价：")]),t._v(" "),s("span",[t._v(t._s(e.price))])]),t._v(" "),s("div",{staticClass:"right"},[s("span",[t._v("金额：")]),t._v(" "),s("span",[t._v(t._s(e.amount))])])])])})],2),t._v(" "),s("div",{staticClass:"select-type"},[s("div",{staticClass:"title"},[t._v("\n        是否委托他人领取\n      ")]),t._v(" "),s("div",{staticClass:"radio-select-box"},[s("div",{staticClass:"radio-item"},[s("span",[t._v("否")]),t._v(" "),t.deviceDetailsData.isEntrust?s("img",{attrs:{src:a("Xjmz")}}):s("img",{attrs:{src:a("yS0J")}})]),t._v(" "),s("div",{staticClass:"radio-item"},[s("span",[t._v("是")]),t._v(" "),t.deviceDetailsData.isEntrust?s("img",{attrs:{src:a("yS0J")}}):s("img",{attrs:{src:a("Xjmz")}})])]),t._v(" "),t.deviceDetailsData.isEntrust?s("div",[s("mt-cell",{attrs:{title:"委托领取人姓名："}},[t._v(t._s(t.deviceDetailsData.entrustorName))]),t._v(" "),s("mt-cell",{attrs:{title:"委托领取人身份证号："}},[t._v(t._s(t.deviceDetailsData.entrustorIdCard))])],1):t._e()]),t._v(" "),30==t.billStatusIndex?s("div",{staticClass:"service-apply-detail"},[t._m(1),t._v(" "),t._l(t.deviceDetailsData.actEntrys,function(e,a){return s("div",{key:a,staticClass:"details-container"},[s("div",{staticClass:"detail-item"},[s("div",{staticClass:"left"},[s("span",[t._v("设备名称：")]),t._v(" "),s("span",[t._v(t._s(e.materialName))])]),t._v(" "),s("div",{staticClass:"right"},[s("span",[t._v("数量：")]),t._v(" "),s("span",[t._v(t._s(e.qty))])])]),t._v(" "),s("div",{staticClass:"detail-item"},[s("div",{staticClass:"left"},[s("span",[t._v("设备单价：")]),t._v(" "),s("span",[t._v(t._s(e.price))])]),t._v(" "),s("div",{staticClass:"right"},[s("span",[t._v("金额：")]),t._v(" "),s("span",[t._v(t._s(e.amount))])])])])})],2):t._e(),t._v(" "),s("Signature",{directives:[{name:"show",rawName:"v-show",value:t.isShowSignature,expression:"isShowSignature"}],ref:"signature"}),t._v(" "),2!=t.recordState&&30==t.billStatusIndex?s("SubmitBtn",{nativeOn:{click:function(e){return t.submitData()}}}):t._e()],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"top"},[e("span",[this._v("申请领用明细")])])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"top"},[e("span",[this._v("实际领用明细")])])}]};var u=a("C7Lr")(v,d,!1,function(t){a("fX1z")},null,null);e.default=u.exports}});
//# sourceMappingURL=40.47e9b9757ac777f69547.js.map