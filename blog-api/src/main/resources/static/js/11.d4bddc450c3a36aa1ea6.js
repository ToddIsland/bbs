webpackJsonp([11],{O1mf:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("ada0"),i=a("QbzY"),r=a("viA7"),c={name:"Index",props:{offset:{type:Number,default:100},page:{type:Object,default:function(){return{}}},query:{type:Object,default:function(){return{}}}},watch:{query:{handler:function(){this.noData=!1,this.articles=[],this.innerPage.pageNumber=1,this.getArticles()},deep:!0},page:{handler:function(){this.noData=!1,this.articles=[],this.innerPage=this.page,this.getArticles()},deep:!0}},created:function(){this.getArticles()},data:function(){return{loading:!1,noData:!1,innerPage:{pageSize:5,pageNumber:1,name:"a.createDate",sort:"desc"},articles:[]}},methods:{load:function(){this.getArticles()},view:function(e){this.$router.push({path:"/view/"+e})},getArticles:function(){var e=this;e.loading=!0,Object(r.d)(4).then(function(t){var a=t.data;a&&a.length>0?(e.innerPage.pageNumber+=1,e.articles=e.articles.concat(a)):e.noData=!0}).catch(function(t){"error"!==t&&e.$message({type:"error",message:"文章加载失败!",showClose:!0})}).finally(function(){e.loading=!1})}},components:{"article-item":n.a,"scroll-page":i.a}},s={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{directives:[{name:"title",rawName:"v-title"}]},[a("el-container",[a("el-main",{staticClass:"me-OJ"},[a("scroll-page",{attrs:{loading:e.loading,offset:e.offset,"no-data":e.noData},on:{load:e.load}},e._l(e.articles,function(t){return a("article-item",e._b({key:t.id},"article-item",t,!1))}),1)],1)],1)],1)},staticRenderFns:[]};var o=a("VU/8")(c,s,!1,function(e){a("U5tB")},"data-v-10b10ce8",null);t.default=o.exports},U5tB:function(e,t){}});
//# sourceMappingURL=11.d4bddc450c3a36aa1ea6.js.map