webpackJsonp([50],{"G39+":function(t,e){},"qZK+":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=i("teIl"),o=i("U6mY"),a=i("wSez"),n=i("W1T5"),r=i("UNpN"),c={name:"noticeList",data:function(){return{myCustomStyle:{background:"rgba(255,255,255,1)",color:"#FF6F1F",position:"fixed",bottom:"0",width:"100%",borderRadius:"0",margin:"0",borderTopWidth:"1px",borderTopColor:"#ddd",borderTopStyle:"solid"},show:"true",myBtnText:"提问",questionList:[]}},components:{RecordList:o.a,myHeader:s.a,NoRecord:n.a},created:function(){this._getQuessionList()},methods:{gotoFeedbackDetail:function(t){this.$router.push("/salesNotice?rcordId="+t)},_getQuessionList:function(){var t=this;a.Indicator.open(),setTimeout(function(){a.Indicator.close()},2e3),Object(r.i)().then(function(e){e.data.list.length>0?(t.questionList=e.data.list,a.Indicator.close()):t.show=!1}).catch(function(e){t.show=!1,a.Indicator.close(),Object(a.Toast)({message:"获取信息失败",duration:1e3})})}}},d={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"problemFeedback",staticStyle:{"max-width":"100%","box-sizing":"border-box"}},[i("myHeader",{attrs:{isBack:!0,backPath:"/business",title:"通知列表"}}),t._v(" "),i("div",{staticClass:"seat-head"}),t._v(" "),t.show?i("div",{staticClass:"list-container"},t._l(t.questionList,function(e){return i("div",[i("div",{staticClass:"list-item"},[i("div",{staticClass:"left",on:{click:function(i){return t.gotoFeedbackDetail(e.rcordId)}}},[i("div",{staticClass:"record-title"},[t._v(t._s(e.number))]),t._v(" "),i("div",{staticClass:"record-time"},[t._v(t._s(e.bizDate))])]),t._v(" "),t._m(0,!0)])])}),0):i("NoRecord")],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"right"},[e("span",{staticClass:"record-detail"})])}]};var l=i("C7Lr")(c,d,!1,function(t){i("G39+")},null,null);e.default=l.exports}});
//# sourceMappingURL=50.9925bdccd7788d9b4120.js.map