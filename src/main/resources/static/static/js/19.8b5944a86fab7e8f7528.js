webpackJsonp([19],{"Ty+x":function(e,t){},ahEN:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=s("4YfN"),r=s.n(i),a=s("9rMa"),c=s("teIl"),n=s("pzWD"),l=s("GE2/"),u=s("y6zN"),v=s("w3CS"),o={name:"deviceApply",mixins:[s("PnOK").a],data:function(){return{fosterHouseholds:"",applyDateText:Object(u.a)(new Date,"yyyy-MM-dd"),applyDate:new Date,serviceMoney:"",serviceApplyDetails:[{serviceName:"",serviceId:"",serviceNum:"",serviceCalculate:"",serviceWeight:"",serviceSinglePrice:"",serviceMoney:"",stock:""}],isNeedOther:!0,isNotNeedOther:!1,isShowEntrust:!1,isEntrust:!1,clientName:"",clientID:"",entrustorId:"",curSelectIndex:0,switchEntrustPopup:!1,needEntrustSlots:[{values:[{id:"",name:"",idcard:""}],textAlign:"center"}]}},components:{SubmitBtn:n.a,myHeader:c.a},created:function(){this._queryEquipmentApplyInitData(),this.fosterHouseholds=this.userName},computed:r()({},Object(a.c)({userName:function(e){return e.user.userInfo.name}})),beforeRouteEnter:function(e,t,s){s(function(e){if("searchServiceName"==t.name&&sessionStorage.getItem("serviceInfo")){var s=JSON.parse(sessionStorage.getItem("serviceInfo")),i=s.materialName,r=s.model,a=s.price,c=s.unit,n=s.materialId,l=s.stock,u=a*l;e.$set(e.serviceApplyDetails,e.curSelectIndex,{serviceName:i,serviceNum:l,serviceCalculate:c,serviceWeight:r,serviceSinglePrice:a,serviceId:n,serviceMoney:u,stock:l})}})},beforeRouteLeave:function(e,t,s){sessionStorage.removeItem("serviceInfo"),s()},methods:{gotoSearchServiceName:function(e){this.curSelectIndex=e,sessionStorage.removeItem("serviceInfo"),this.$router.push("/searchServiceName")},_queryEquipmentApplyInitData:function(){var e=this;Object(l.r)().then(function(t){var s=t.data;e.serviceMoney=s.equipAmt})},_queryEntrustList:function(){var e=this;Object(l.p)().then(function(t){var s=t.data;s.list.length?(e.isShowEntrust=!0,e.needEntrustSlots[0].values=s.list.map(function(e){return{name:e.name,id:e.id,idcard:e.idcard}}),e.clientName=e.needEntrustSlots[0].values[0].name,e.clientID=e.needEntrustSlots[0].values[0].idcard,e.entrustorId=e.needEntrustSlots[0].values[0].id):(e.$toast({message:"无委托人信息，请维护养户档案！",duration:1e3}),e.isNeedOther=!0,e.isNotNeedOther=!1,e.isEntrust=!1,e.isShowEntrust=!1)}).catch(function(t){e.isNeedOther=!0,e.isNotNeedOther=!1,e.isEntrust=!1,e.isShowEntrust=!1,e.$toast({message:"无委托人信息，请维护养户档案！",duration:1e3})})},selectApplyDate:function(){this.$refs.applyDatePicker.open()},applyDateConfirm:function(e){this.applyDateText=Object(u.a)(this.applyDate,"yyyy-MM-dd")},handlerServiceNum:function(e,t){if(""==this.serviceApplyDetails[e].serviceName)return this.serviceApplyDetails[e].serviceNum="",void this.$toast({message:"请先选择设备名称",duration:1e3});if(t.target.value>this.serviceApplyDetails[e].stock)return this.serviceApplyDetails[e].serviceNum=this.serviceApplyDetails[e].stock,void this.$toast({message:"库存为"+this.serviceApplyDetails[e].stock+"不能大于",duration:1e3});if(this.serviceApplyDetails[e].serviceNum=Object(v.a)(t),this.serviceApplyDetails[e].serviceSinglePrice){var s=this.serviceApplyDetails[e].serviceNum*this.serviceApplyDetails[e].serviceSinglePrice;this.$set(this.serviceApplyDetails,e,{serviceName:this.serviceApplyDetails[e].serviceName,serviceNum:this.serviceApplyDetails[e].serviceNum,serviceCalculate:this.serviceApplyDetails[e].serviceCalculate,serviceWeight:this.serviceApplyDetails[e].serviceWeight,serviceSinglePrice:this.serviceApplyDetails[e].serviceSinglePrice,serviceId:this.serviceApplyDetails[e].serviceId,serviceMoney:s||"",stock:this.serviceApplyDetails[e].stock})}},delApplyItem:function(e){this.serviceApplyDetails.length>1&&(this.serviceApplyDetails=this.serviceApplyDetails.filter(function(t,s){return e!==s}))},addApply:function(){this.serviceApplyDetails.push({serviceName:"",serviceNum:"",serviceId:"",serviceCalculate:"",serviceWeight:"",serviceSinglePrice:"",serviceMoney:"",stock:""})},switchNeedOtherHandler:function(e){"isNeedOther"===e?this.isNeedOther||(this.isNeedOther=!0,this.isNotNeedOther=!1,this.isEntrust=!1):"isNotNeedOther"===e&&(this.isNotNeedOther||(this.isNotNeedOther=!0,this.isNeedOther=!1,this.isEntrust=!0,this._queryEntrustList()))},selectEntrust:function(){this.needEntrustSlots[0].values[0].name?this.switchEntrustPopup=!this.switchEntrustPopup:this.$toast({message:"无委托人信息，请维护养户档案！",duration:1e3})},handleEntrustConfirm:function(){this.clientName=this.$refs.entrustPicker.getValues()[0].name,this.clientID=this.$refs.entrustPicker.getValues()[0].idcard,this.entrustorId=this.$refs.entrustPicker.getValues()[0].id,this.switchEntrustPopup=!1},checkSubmitData:function(){for(var e=[],t=0;t<this.serviceApplyDetails.length;t++)e.push({val:this.serviceApplyDetails[t].serviceName,rule:["required"],getTipText:"设备名称"}),e.push({val:this.serviceApplyDetails[t].serviceNum,rule:["required"],getTipText:"设备数量"});return Object(v.b)(e)},_saveEquipmentApply:function(){var e=this,t=this.serviceApplyDetails.map(function(e){return{materialId:e.serviceId,materialName:e.serviceName,qty:e.serviceNum,model:e.serviceWeight,price:e.serviceSinglePrice,unit:e.serviceCalculate,amount:e.serviceMoney}}),s={custName:this.fosterHouseholds,bizDate:this.applyDateText,totalPayment:this.serviceMoney,entrys:t,isEntrust:this.isEntrust,entrustorName:this.clientName,entrustorId:this.entrustorId,entrustorIdCard:this.clientID};Object(l.w)(s).then(function(t){e.$toast({message:"提交成功！",duration:1e3}),e.$router.push("/deviceApplyRecord"),e.isDisabledSubmit=!1,e.submitBtnText="提交"}).catch(function(t){e.isDisabledSubmit=!1,e.submitBtnText="提交"})},submitPageData:function(){if(!this.checkSubmitData())return!1;this.isDisabledSubmit||(this.isDisabledSubmit=!0,this.submitBtnText="提交中...",this._saveEquipmentApply())}}},p={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"deviceApply",staticStyle:{"max-width":"100%","box-sizing":"border-box"}},[i("myHeader",{attrs:{isBack:!0,backPath:"/business",title:"设备申请",subtitle:"记录",subtitlePath:"/deviceApplyRecord"}}),e._v(" "),i("div",{staticClass:"seat-head"}),e._v(" "),i("div",{staticClass:"list-block"},[i("mt-cell",{attrs:{title:"养户：",value:e.fosterHouseholds}}),e._v(" "),i("mt-cell",{attrs:{title:"申请日期："},nativeOn:{click:function(t){return e.selectApplyDate(t)}}},[i("span",{staticClass:"color-text"},[e._v("\n        "+e._s(e.applyDateText)+"\n      ")]),e._v(" "),i("span",{staticClass:"img"})]),e._v(" "),i("mt-cell",{attrs:{title:"累计已领设备金额：",value:e.serviceMoney}})],1),e._v(" "),i("div",{staticClass:"service-apply-detail"},[e._m(0),e._v(" "),e._l(e.serviceApplyDetails,function(t,r){return i("div",{key:r,staticClass:"details-container"},[i("div",{staticClass:"detail-item"},[i("div",{staticClass:"del-btn",on:{click:function(t){return e.delApplyItem(r)}}},[i("img",{attrs:{src:s("Xmmv")}})]),e._v(" "),i("div",{staticClass:"left"},[i("span",[e._v("设备名称：")]),e._v(" "),t.serviceName?i("span",{on:{click:function(t){return e.gotoSearchServiceName(r)}}},[e._v(e._s(t.serviceName))]):i("span",{staticClass:"tip-text",on:{click:function(t){return e.gotoSearchServiceName(r)}}},[e._v("请选择名称")])]),e._v(" "),i("div",{staticClass:"right"},[i("span",[e._v("数量：")]),e._v(" "),i("input",{directives:[{name:"model",rawName:"v-model",value:t.serviceNum,expression:"item.serviceNum"}],attrs:{type:"text",placeholder:"请输入数量"},domProps:{value:t.serviceNum},on:{input:[function(s){s.target.composing||e.$set(t,"serviceNum",s.target.value)},function(t){return e.handlerServiceNum(r,t)}]}})])]),e._v(" "),i("div",{staticClass:"detail-item"},[i("div",{staticClass:"del-btn"}),e._v(" "),i("div",{staticClass:"left"},[i("span",[e._v("计量单位：")]),e._v(" "),i("span",[e._v(e._s(t.serviceCalculate))])]),e._v(" "),i("div",{staticClass:"right"},[i("span",[e._v("规格：")]),e._v(" "),i("span",[e._v(e._s(t.serviceWeight))])])]),e._v(" "),i("div",{staticClass:"detail-item"},[i("div",{staticClass:"del-btn"}),e._v(" "),i("div",{staticClass:"left"},[i("span",[e._v("设备单价：")]),e._v(" "),i("span",[e._v(e._s(t.serviceSinglePrice))])]),e._v(" "),i("div",{staticClass:"right"},[i("span",[e._v("金额：")]),e._v(" "),i("span",[e._v(e._s(t.serviceMoney))])])])])}),e._v(" "),i("div",{staticClass:"add-btn",on:{click:e.addApply}},[i("span",{staticClass:"add-icon"},[e._v("+")]),e._v("新增\n    ")])],2),e._v(" "),i("div",{staticClass:"select-type"},[i("div",{staticClass:"title"},[e._v("\n      是否委托他人领取\n    ")]),e._v(" "),i("div",{staticClass:"radio-select-box"},[i("div",{staticClass:"radio-item"},[i("span",[e._v("否")]),e._v(" "),e.isNeedOther?i("img",{attrs:{src:s("yS0J")},on:{click:function(t){return e.switchNeedOtherHandler("isNeedOther")}}}):i("img",{attrs:{src:s("Xjmz")},on:{click:function(t){return e.switchNeedOtherHandler("isNeedOther")}}})]),e._v(" "),i("div",{staticClass:"radio-item"},[i("span",[e._v("是")]),e._v(" "),e.isNotNeedOther?i("img",{attrs:{src:s("yS0J")},on:{click:function(t){return e.switchNeedOtherHandler("isNotNeedOther")}}}):i("img",{attrs:{src:s("Xjmz")},on:{click:function(t){return e.switchNeedOtherHandler("isNotNeedOther")}}})])]),e._v(" "),e.isNotNeedOther&&e.isShowEntrust?i("div",[i("mt-cell",{attrs:{title:"委托领取人姓名："},nativeOn:{click:function(t){return e.selectEntrust(t)}}},[e._v("\n        "+e._s(e.clientName)+"\n        "),i("span",{staticClass:"img"})]),e._v(" "),i("mt-cell",{attrs:{title:"委托领取人身份证号：",value:e.clientID}})],1):e._e()]),e._v(" "),i("SubmitBtn",{attrs:{btnText:e.submitBtnText,customStyle:e.myCustomStyle},nativeOn:{click:function(t){return t.preventDefault(),e.submitPageData()}}}),e._v(" "),i("mt-datetime-picker",{ref:"applyDatePicker",attrs:{type:"date"},on:{confirm:e.applyDateConfirm},model:{value:e.applyDate,callback:function(t){e.applyDate=t},expression:"applyDate"}}),e._v(" "),i("div",{staticClass:"my-prop"},[i("mt-popup",{attrs:{position:"bottom"},model:{value:e.switchEntrustPopup,callback:function(t){e.switchEntrustPopup=t},expression:"switchEntrustPopup"}},[i("mt-picker",{ref:"entrustPicker",attrs:{slots:e.needEntrustSlots,"value-key":"name","show-toolbar":!0}},[i("span",{staticClass:"sure-btn",on:{click:e.handleEntrustConfirm}},[e._v("确认")])])],1)],1)],1)},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"top"},[t("span",[this._v("设备申请明细")])])}]};var d=s("C7Lr")(o,p,!1,function(e){s("Ty+x")},null,null);t.default=d.exports}});
//# sourceMappingURL=19.8b5944a86fab7e8f7528.js.map