webpackJsonp([29],{"4bfx":function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=i("IHPB"),a=i.n(s),r=i("teIl"),n=i("y6zN"),c=i("pzWD"),d=i("w3CS"),l=i("GE2/"),u=i("wSez"),o={name:"getFeed",mixins:[i("PnOK").a],data:function(){return{batchNumber:"",batchId:"",switchBatchNumPopup:!1,batchNumSlots:[{values:[{id:"",name:""}],textAlign:"center"}],drawingMaterialDateText:Object(n.c)(3,"yyyy-MM-dd"),applyTitle:"申请领料明细",drawingMaterialDate:new Date(Object(n.c)(3,"yyyy-MM-dd")),switchDriverPopup:!1,driverSlots:[{values:[{id:"",name:"",number:"",phone:"",vehicleno:""}],textAlign:"center"}],driverName:"",driverNameId:"",driverID:"",driverCarID:"",selectedType:0,isGetFeed:!0,isNoFeed:!1,switchSelectFeedPopup:!1,getFeedList:[{feedName:"",bclybs:"",quotarecevenum:"",sumReceve:"",feedId:""}],feedTypeSlots:[{values:[{feedName:"",feedId:"",quotarecevenum:"",sumReceve:""}],textAlign:"center"}],currentFeedTypeIndex:0,receiveDetails:{feedName:"饲料名001",receiveNum:2,receiveSinglePrice:"350元",receivePrice:"100元",alreadyGotNum:"100",limitNum:"300"},isShowFeedList:!0}},components:{SubmitBtn:c.a,myHeader:r.a},created:function(){u.Indicator.open({text:"加载中...",spinnerType:"fading-circle"}),this._getBatchNumberList(),this._getDriverList()},mounted:function(){},methods:{_getBatchNumberList:function(){var e=this;Object(l.g)().then(function(t){t.data&&t.data.length?(e.batchNumSlots[0].values=t.data.map(function(e,t){return{name:e.name,id:e.id}}),e.batchNumber=e.batchNumSlots[0].values[0].name,e.batchId=e.batchNumSlots[0].values[0].id,e._getFeedList(e.batchNumSlots[0].values[0].id)):(u.Indicator.close(),e.isShowFeedList=!1)},function(e){u.Indicator.close()})},_getDriverList:function(){var e=this;Object(l.j)().then(function(t){t.data.length&&(e.driverSlots[0].values=t.data.map(function(e,t){return{name:e.name,id:e.id,number:e.number,phone:e.phone,vehicleno:e.vehicleno}}),e.driverName=e.driverSlots[0].values[0].name,e.driverID=e.driverSlots[0].values[0].number,e.driverNameId=e.driverSlots[0].values[0].id,e.driverCarID=e.driverSlots[0].values[0].vehicleno)})},_getFeedList:function(e){var t=this;Object(l.l)(e).then(function(e){if(u.Indicator.close(),e.data&&e.data.length){t.feedTypeSlots[0].values=e.data.map(function(e,t){return{feedName:e.feedName,feedId:e.feedId,quotarecevenum:e.quotarecevenum,sumReceve:e.sumReceve}});var i=t.feedTypeSlots[0].values[0],s=i.feedName,a=i.quotarecevenum,r=i.sumReceve,n=i.feedId;t.getFeedList[t.currentFeedTypeIndex].feedName=s,t.getFeedList[t.currentFeedTypeIndex].quotarecevenum=a,t.getFeedList[t.currentFeedTypeIndex].sumReceve=r,t.getFeedList[t.currentFeedTypeIndex].feedId=n}else t.isShowFeedList=!1,u.Indicator.close()}).catch(function(e){u.Indicator.close()})},selectBatchNum:function(){this.batchNumSlots[0].values[0].name?this.switchBatchNumPopup=!this.switchBatchNumPopup:this.$toast({message:"无可选批次，请核实业务场景！",duration:1e3})},handleBatchConfirm:function(){this.batchNumber=this.$refs.batchPicker.getValues()[0].name,this.batchId=this.$refs.batchPicker.getValues()[0].id,this.switchBatchNumPopup=!1},selectDriver:function(){console.log(this.driverSlots[0].values[0].name),this.driverSlots[0].values[0]&&this.driverSlots[0].values[0].name?this.switchDriverPopup=!this.switchDriverPopup:this.$toast({message:"无司机信息，请维护养户档案！",duration:1e3})},handleDriverConfirm:function(){this.driverName=this.$refs.diverPicker.getValues()[0].name,this.driverID=this.$refs.diverPicker.getValues()[0].number,this.driverNameId=this.$refs.diverPicker.getValues()[0].id,this.driverCarID=this.$refs.diverPicker.getValues()[0].vehicleno,this.switchDriverPopup=!1},selectdrawingMaterialDate:function(){this.$refs.drawingMaterialPicker.open()},drawingMaterialConfirm:function(e){this.drawingMaterialDateText=Object(n.a)(e,"yyyy-MM-dd")},switchApplyHandler:function(e){"isGetFeed"===e?this.isGetFeed||(this.isGetFeed=!0,this.isNoFeed=!1,this.applyTitle="申请领料明细",this.selectedType=0):"isNoFeed"===e&&(this.isNoFeed||(this.isNoFeed=!0,this.isGetFeed=!1,this.applyTitle="申请退料明细",this.selectedType=1))},handlerFeedNum:function(e,t){this.getFeedList[e].bclybs=Object(d.a)(t)},addFeedType:function(){this.getFeedList.push({feedName:this.feedTypeSlots[0].values[0].feedName,bclybs:"",quotarecevenum:this.feedTypeSlots[0].values[0].quotarecevenum,sumReceve:this.feedTypeSlots[0].values[0].sumReceve,feedId:this.feedTypeSlots[0].values[0].feedId})},delGetFeedItem:function(e){this.getFeedList.length>1&&(this.getFeedList=this.getFeedList.filter(function(t,i){return e!==i}))},selectFeedType:function(e){this.isShowFeedList?(this.currentFeedTypeIndex=e,this.switchSelectFeedPopup=!this.switchSelectFeedPopup):this.$toast("暂无信息！")},handleFeedTypeConfirm:function(){this.getFeedList[this.currentFeedTypeIndex].feedName=this.$refs.feedTypePicker.getValues()[0].feedName,this.getFeedList[this.currentFeedTypeIndex].quotarecevenum=this.$refs.feedTypePicker.getValues()[0].quotarecevenum,this.getFeedList[this.currentFeedTypeIndex].sumReceve=this.$refs.feedTypePicker.getValues()[0].sumReceve,this.getFeedList[this.currentFeedTypeIndex].feedId=this.$refs.feedTypePicker.getValues()[0].feedId,this.switchSelectFeedPopup=!1},checkedSubmitData:function(){var e=[{val:this.batchNumber,rule:["required"],getTipText:"批次号"}],t=this.getFeedList.map(function(e,t){return{val:e.bclybs,rule:["required"],getTipText:"饲料包数"}});return Object(d.b)([].concat(e,a()(t)))},_saveFeedApply:function(){var e=this,t={batchId:this.batchId,batchName:this.batchNumber,plandate:this.drawingMaterialDateText,driverId:this.driverNameId,driverName:this.driverName,driverVehicleno:this.driverCarID,driverIdcard:this.driverID,type:this.selectedType,pickDetail:this.getFeedList};Object(l.x)(t).then(function(t){e.$toast({message:"提交成功！",duration:1e3}),e.$router.push("/getFeedRecord"),e.isDisabledSubmit=!1,e.submitBtnText="提交"},function(t){e.isDisabledSubmit=!1,e.submitBtnText="提交"})},submitPageData:function(){if(!this.checkedSubmitData())return!1;this.isDisabledSubmit||(this.isDisabledSubmit=!0,this.submitBtnText="提交中...",this._saveFeedApply())}}},v={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"getFeed",staticStyle:{"max-width":"100%","box-sizing":"border-box"}},[s("myHeader",{attrs:{isBack:!0,backPath:"/business",title:"我要领料",subtitle:"记录",subtitlePath:"/getFeedRecord"}}),e._v(" "),s("div",{staticClass:"seat-head"}),e._v(" "),s("div",{staticClass:"list-block"},[s("mt-cell",{attrs:{title:"批次号："},nativeOn:{click:function(t){return e.selectBatchNum(t)}}},[e._v("\n      "+e._s(e.batchNumber)+"\n      "),s("span",{staticClass:"img"})]),e._v(" "),s("mt-cell",{attrs:{title:"计划拉料日期："},nativeOn:{click:function(t){return e.selectdrawingMaterialDate(t)}}},[s("span",{staticClass:"color-text"},[e._v("\n        "+e._s(e.drawingMaterialDateText)+"\n      ")]),e._v(" "),s("span",{staticClass:"img"})]),e._v(" "),s("mt-cell",{attrs:{title:"拉料司机姓名："},nativeOn:{click:function(t){return e.selectDriver(t)}}},[e._v("\n      "+e._s(e.driverName)+"\n      "),s("span",{staticClass:"img"})]),e._v(" "),s("mt-field",{staticClass:"zb-field",attrs:{label:"司机车牌号码：",placeholder:"请输入司机车牌号码",disabled:""==e.driverSlots[0].values[0].name},model:{value:e.driverCarID,callback:function(t){e.driverCarID=t},expression:"driverCarID"}}),e._v(" "),s("mt-cell",{attrs:{title:"拉料司机身份证号：",value:e.driverID}})],1),e._v(" "),s("div",{staticClass:"select-type"},[s("div",{staticClass:"title"},[e._v("\n      请选择申请类别\n    ")]),e._v(" "),s("div",{staticClass:"radio-select-box"},[s("div",{staticClass:"radio-item"},[s("span",[e._v("领料")]),e._v(" "),e.isGetFeed?s("img",{attrs:{src:i("yS0J")},on:{click:function(t){return e.switchApplyHandler("isGetFeed")}}}):s("img",{attrs:{src:i("Xjmz")},on:{click:function(t){return e.switchApplyHandler("isGetFeed")}}})]),e._v(" "),s("div",{staticClass:"radio-item"},[s("span",[e._v("退料")]),e._v(" "),e.isNoFeed?s("img",{attrs:{src:i("yS0J")},on:{click:function(t){return e.switchApplyHandler("isNoFeed")}}}):s("img",{attrs:{src:i("Xjmz")},on:{click:function(t){return e.switchApplyHandler("isNoFeed")}}})])])]),e._v(" "),s("div",{staticClass:"apply-detail"},[s("div",{staticClass:"top"},[s("span",[e._v(e._s(e.applyTitle))])]),e._v(" "),e._l(e.getFeedList,function(t,a){return s("div",{key:a,staticClass:"detail-item"},[s("div",{staticClass:"select-detail"},[s("div",{staticClass:"del-icon",on:{click:function(t){return e.delGetFeedItem(a)}}},[s("img",{attrs:{src:i("Xmmv")}})]),e._v(" "),s("div",{staticClass:"left"},[s("span",{staticClass:"left-name"},[e._v("饲料品种：")]),e._v(" "),s("span",{on:{click:function(t){return e.selectFeedType(a)}}},[e._v(e._s(t.feedName))]),e._v(" "),s("span",{staticClass:"img",on:{click:function(t){return e.selectFeedType(a)}}})]),e._v(" "),s("div",{staticClass:"right"},[s("span",[e._v("包数：")]),e._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.bclybs,expression:"item.bclybs"}],attrs:{type:"text",placeholder:"请输入数量"},domProps:{value:t.bclybs},on:{input:[function(i){i.target.composing||e.$set(t,"bclybs",i.target.value)},function(t){return e.handlerFeedNum(a,t)}]}})])]),e._v(" "),s("div",{staticClass:"select-result"},[s("div",[s("span",[e._v("已领："+e._s(t.sumReceve))]),e._v(" "),s("span",[e._v("定额："+e._s(t.quotarecevenum))])])])])}),e._v(" "),s("div",{staticClass:"add-select",on:{click:e.addFeedType}},[s("span",{staticClass:"add-icon"},[e._v("+")]),e._v("新增\n    ")])],2),e._v(" "),s("SubmitBtn",{attrs:{btnText:e.submitBtnText,customStyle:e.myCustomStyle},nativeOn:{click:function(t){return e.submitPageData(t)}}}),e._v(" "),s("mt-datetime-picker",{ref:"drawingMaterialPicker",attrs:{type:"date"},on:{confirm:e.drawingMaterialConfirm},model:{value:e.drawingMaterialDate,callback:function(t){e.drawingMaterialDate=t},expression:"drawingMaterialDate"}}),e._v(" "),s("div",{staticClass:"my-prop"},[s("mt-popup",{attrs:{position:"bottom"},model:{value:e.switchSelectFeedPopup,callback:function(t){e.switchSelectFeedPopup=t},expression:"switchSelectFeedPopup"}},[s("mt-picker",{ref:"feedTypePicker",attrs:{slots:e.feedTypeSlots,"value-key":"feedName","show-toolbar":!0}},[s("span",{staticClass:"sure-btn",on:{click:e.handleFeedTypeConfirm}},[e._v("确认")])])],1),e._v(" "),s("mt-popup",{attrs:{position:"bottom"},model:{value:e.switchBatchNumPopup,callback:function(t){e.switchBatchNumPopup=t},expression:"switchBatchNumPopup"}},[s("mt-picker",{ref:"batchPicker",attrs:{slots:e.batchNumSlots,"value-key":"name","show-toolbar":!0}},[s("span",{staticClass:"sure-btn",on:{click:e.handleBatchConfirm}},[e._v("确认")])])],1),e._v(" "),s("mt-popup",{attrs:{position:"bottom"},model:{value:e.switchDriverPopup,callback:function(t){e.switchDriverPopup=t},expression:"switchDriverPopup"}},[s("mt-picker",{ref:"diverPicker",attrs:{slots:e.driverSlots,"value-key":"name","show-toolbar":!0}},[s("span",{staticClass:"sure-btn",on:{click:e.handleDriverConfirm}},[e._v("确认")])])],1)],1)],1)},staticRenderFns:[]};var h=i("C7Lr")(o,v,!1,function(e){i("W0Du")},null,null);t.default=h.exports},W0Du:function(e,t){}});
//# sourceMappingURL=29.31e5e940f8cf80cf4796.js.map