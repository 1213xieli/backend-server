webpackJsonp([42],{llqj:function(t,e){},qiU6:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=i("IHPB"),s=i.n(a),r=i("teIl"),n=i("Lpv+"),o=i("U6mY"),c=i("W1T5"),l=i("GE2/"),d=i("wesB"),p=i("wSez"),u={name:"deviceApplyRecord",data:function(){return{allLoaded:!1,bottomStatus:"",pageNumber:1,pageSize:10,recordList:[{recordTitle:"",recordTime:"",recordState:"",rcordId:"",billStatusIndex:""}],endtime:"",starttime:"",state:"",wrapperHeight:0,switchSelectStatusPopup:!1,statusTypeSlots:[{values:[{name:"进行中",id:1},{name:"已完成",id:2},{name:"已取消",id:3}],textAlign:"center"}]}},components:{RecordList:o.a,myHeader:r.a,NoRecord:c.a,filterTime:n.a},created:function(){this._queryEquipmentApplyRecordList({endtime:this.endtime,starttime:this.starttime,state:this.state,pageNumber:1,pageSize:this.pageSize})},mounted:function(){var t=this;this.$nextTick(function(){t.wrapperHeight=document.documentElement.clientHeight-t.$refs.wrapper.getBoundingClientRect().top})},methods:{handlerLeft:function(t){this.gotoDetail(t)},handlerIcon:function(t){this.gotoDetail(t)},handlerBtn:function(t){this.cancelApply(t)},gotoDetail:function(t){this.$router.push("/deviceApplyDetail?rcordId="+t)},_queryEquipmentApplyRecordList:function(t){var e=this;p.Indicator.open({text:"加载中...",spinnerType:"fading-circle"}),Object(l.s)(t).then(function(t){if(t.data){var i=t.data.list.map(function(t,e){return{recordTitle:t.number,recordTime:t.bizDate,recordState:d.a.recordState[t.state],rcordId:t.rcordId,billStatusIndex:t.billStatusIndex}});e.recordList=i,e.recordList.length<e.pageSize&&(e.allLoaded=!0)}else e.recordList=[];p.Indicator.close()},function(t){p.Indicator.close()})},selectFilterDate:function(){this.$refs.filterTime.selectFilterDate()},onTimeChangeFilter:function(t){var e=t.starttime,i=t.endtime;this._queryEquipmentApplyRecordList({endtime:i,starttime:e,pageNumber:1,pageSize:this.pageSize,state:this.state})},handleConfirm:function(){this.state=this.$refs.picker.getValues()[0].id,this._queryEquipmentApplyRecordList({endtime:this.endtime,starttime:this.starttime,pageNumber:1,pageSize:this.pageSize,state:this.state}),this.switchSelectStatusPopup=!1},handleReset:function(){this.state="",this.starttime="",this.endtime="",this._getfeedRecordList({endtime:this.endtime,starttime:this.starttime,pageNumber:1,pageSize:this.pageSize,state:this.state}),this.switchSelectStatusPopup=!1},selectFilterStatus:function(){this.switchSelectStatusPopup=!this.switchSelectStatusPopup},handleBottomChange:function(t){this.bottomStatus=t},loadBottom:function(){var t=this;console.log("下来"),this.pageNumber++,Object(l.s)({endtime:this.endtime,starttime:this.starttime,pageNumber:this.pageNumber,pageSize:this.pageSize,state:this.state}).then(function(e){var i=e.data.list.map(function(t,e){return{recordTitle:t.number,recordTime:t.feedDate,recordState:d.a.recordState[t.state],rcordId:t.rcordId,billStatusIndex:t.billStatusIndex}});i.length<t.pageSize?t.allLoaded=!0:t.allLoaded=!1,t.recordList=[].concat(s()(t.recordList),s()(i)),t.$refs.loadmore.onBottomLoaded()},function(e){t.allLoaded=!0})},cancelApply:function(t){var e=this;this.$messagebox.confirm("确定执行此操作?").then(function(i){Object(l.f)(t).then(function(){e.$toast({message:"取消成功！",duration:1e3}),e._queryEquipmentApplyRecordList({endtime:e.endtime,starttime:e.starttime,state:e.state,pageNumber:1,pageSize:e.pageSize})},function(){})})}}},m={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"deviceApplyRecord",staticStyle:{"max-width":"100%","box-sizing":"border-box"}},[i("myHeader",{attrs:{isBack:!0,backPath:"/deviceApply",title:"记录列表"}}),t._v(" "),i("div",{staticClass:"seat-head"}),t._v(" "),i("div",{staticClass:"filter-block"},[i("div",{staticClass:"left",on:{click:t.selectFilterDate}},[i("span",{staticClass:"filter-item"},[t._v("日期筛选")]),t._v(" "),i("span",[t._v("▼")])]),t._v(" "),i("div",{staticClass:"right"},[i("span",{staticClass:"filter-item",on:{click:t.selectFilterStatus}},[t._v("状态筛选")]),t._v(" "),i("span",[t._v("▼")])])]),t._v(" "),i("div",{staticClass:"list-container"},[i("div",{directives:[{name:"show",rawName:"v-show",value:t.recordList.length,expression:"recordList.length"}],ref:"wrapper",staticClass:"page-loadmore-wrapper",style:{height:t.wrapperHeight+"px"}},[i("mt-loadmore",{ref:"loadmore",attrs:{"bottom-method":t.loadBottom,"auto-fill":!1,"bottom-all-loaded":t.allLoaded},on:{"bottom-status-change":t.handleBottomChange}},[i("RecordList",{attrs:{listData:t.recordList},on:{"on-handlerIcon":t.handlerIcon,"on-handlerBtn":t.handlerBtn,"on-handlerLeft":t.handlerLeft}})],1)],1)]),t._v(" "),i("filter-time",{ref:"filterTime",on:{"on-filter-search":t.onTimeChangeFilter}}),t._v(" "),i("div",{staticClass:"my-prop"},[i("mt-popup",{attrs:{position:"bottom"},model:{value:t.switchSelectStatusPopup,callback:function(e){t.switchSelectStatusPopup=e},expression:"switchSelectStatusPopup"}},[i("mt-picker",{ref:"picker",attrs:{slots:t.statusTypeSlots,"value-key":"name","show-toolbar":!0}},[i("span",{staticClass:"sure-btn",on:{click:t.handleConfirm}},[t._v("确认")]),t._v(" "),i("span",{staticClass:"reset-btn",on:{click:t.handleReset}},[t._v("重置筛选")])])],1)],1),t._v(" "),t.recordList.length?t._e():i("NoRecord")],1)},staticRenderFns:[]};var h=i("C7Lr")(u,m,!1,function(t){i("llqj")},"data-v-41e2bb4f",null);e.default=h.exports}});
//# sourceMappingURL=42.73e5744894329fd2b944.js.map