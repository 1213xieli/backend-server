webpackJsonp([47],{ifPb:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=i("teIl"),o=i("U6mY"),a=i("pzWD"),n=i("GCmk"),r=i("wSez"),c=i("W1T5"),d={name:"problemFeedback",data:function(){return{myCustomStyle:{background:"rgba(255,255,255,1)",color:"#FF6F1F",position:"fixed",bottom:"0",width:"100%",borderRadius:"0",margin:"0",borderTopWidth:"1px",borderTopColor:"#ddd",borderTopStyle:"solid"},show:"true",myBtnText:"提问",questionList:[]}},components:{SubmitBtn:a.a,RecordList:o.a,myHeader:s.a,NoRecord:c.a},created:function(){this._getQuessionList()},methods:{gotoFeedbackDetail:function(t){this.$router.push({name:"feedbackDetail",params:{recordId:t}})},askQuestion:function(){this.$router.push("/askQuestions")},_getQuessionList:function(){var t=this;r.Indicator.open(),setTimeout(function(){r.Indicator.close()},2e3),Object(n.c)().then(function(e){e.data.list.length>0?(t.questionList=e.data.list,r.Indicator.close()):t.show=!1}).catch(function(t){r.Indicator.close(),Object(r.Toast)({message:"获取信息失败",duration:1e3})})}}},l={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"problemFeedback",staticStyle:{"max-width":"100%","box-sizing":"border-box"}},[i("myHeader",{attrs:{isBack:!0,backPath:"/personInfo",title:"问题反馈"}}),t._v(" "),i("div",{staticClass:"seat-head"}),t._v(" "),t.show?i("div",{staticClass:"list-container"},t._l(t.questionList,function(e){return i("div",[i("div",{staticClass:"list-item"},[i("div",{staticClass:"left",on:{click:function(i){return t.gotoFeedbackDetail(e.rcordId)}}},[i("div",{staticClass:"record-title"},[t._v(t._s(e.details))]),t._v(" "),i("div",{staticClass:"record-time"},[t._v(t._s(e.bizdate))])]),t._v(" "),t._m(0,!0)])])}),0):i("NoRecord"),t._v(" "),i("SubmitBtn",{attrs:{customStyle:t.myCustomStyle,btnText:t.myBtnText},nativeOn:{click:function(e){return t.askQuestion()}}})],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"right"},[e("span",{staticClass:"record-detail"})])}]};var u=i("C7Lr")(d,l,!1,function(t){i("wByA")},null,null);e.default=u.exports},wByA:function(t,e){}});
//# sourceMappingURL=47.7489394d461f6b097b5a.js.map