<!--<template>-->
<!--  <el-container>-->
<!--    <el-main>-->
<!--      <article-scroll-page></article-scroll-page>-->
<!--    </el-main>-->
<!--  </el-container>-->
<!--</template>-->

<!--<script>-->
<!--  // import defaultAvatar from '@/assets/img/logo.png'-->
<!--  // import {getAllCategorysDetail} from '@/api/category'-->
<!--  // import {getAllTagsDetail} from '@/api/tag'-->
<!--  import ArticleScrollPage from '@/views/common/ArticleScrollPage'-->

<!--  import {getArticles, getHotArtices, getNewArtices} from '@/api/article'-->

<!--  export default {-->
<!--    // name: 'BlogAllCategoryTag',-->
<!--    // created() {-->
<!--    //   // this.getCategorys()-->
<!--    //   this.getTags()-->
<!--    // },-->
<!--    data() {-->
<!--      return {-->
<!--        // defaultAvatar: defaultAvatar,-->
<!--        // categorys: [],-->
<!--        // tags: [],-->
<!--        // currentActiveName: 'category'-->
<!--      }-->
<!--    },-->
<!--    computed: {-->
<!--      activeName: {-->
<!--        get() {-->
<!--          return (this.currentActiveName = this.$route.params.type)-->
<!--        },-->
<!--        set(newValue) {-->
<!--          this.currentActiveName = newValue-->
<!--        }-->
<!--      },-->
<!--      // categoryTagTitle() {-->
<!--      //   if (this.currentActiveName == 'category') {-->
<!--      //     return '文章分类 - For Fun'-->
<!--      //   }-->
<!--      //   console.info('dddd')-->
<!--      //   return '标签 - For Fun'-->
<!--      // }-->
<!--    },-->
<!--    // methods: {-->
<!--    //   view(id) {-->
<!--    //     this.$router.push({path: `/${this.currentActiveName}/${id}`})-->
<!--    //   },-->
<!--    //   getCategorys() {-->
<!--    //     let that = this-->
<!--    //     getAllCategorysDetail().then(data => {-->
<!--    //       that.categorys = data.data-->
<!--    //     }).catch(error => {-->
<!--    //       if (error !== 'error') {-->
<!--    //         that.$message({type: 'error', message: '文章分类加载失败', showClose: true})-->
<!--    //       }-->
<!--    //     })-->
<!--    //   },-->
<!--    //   getTags() {-->
<!--    //     let that = this-->
<!--    //     getAllTagsDetail().then(data => {-->
<!--    //       that.tags = data.data-->
<!--    //     }).catch(error => {-->
<!--    //       if (error !== 'error') {-->
<!--    //         that.$message({type: 'error', message: '标签加载失败', showClose: true})-->
<!--    //       }-->
<!--    //     })-->
<!--    //   }-->
<!--    // },-->
<!--    //组件内的守卫 调整body的背景色-->
<!--    // beforeRouteEnter(to, from, next) {-->
<!--    //   window.document.body.style.backgroundColor = '#fff';-->
<!--    //   next();-->
<!--    // },-->
<!--    // beforeRouteLeave(to, from, next) {-->
<!--    //   window.document.body.style.backgroundColor = '#f5f5f5';-->
<!--    //   next();-->
<!--    // },-->
<!--    components: {-->
<!--      ArticleScrollPage,-->
<!--    }-->
<!--  }-->
<!--</script>-->

<!--<style>-->
<!--  /*.me-allct-body {*/-->
<!--  /*  margin: 60px auto 140px;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-container {*/-->
<!--  /*  width: 1000px;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-items {*/-->
<!--  /*  padding-top: 2rem;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-item {*/-->
<!--  /*  width: 25%;*/-->
<!--  /*  display: inline-block;*/-->
<!--  /*  margin-bottom: 2.4rem;*/-->
<!--  /*  padding: 0 .7rem;*/-->
<!--  /*  box-sizing: border-box;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-content {*/-->
<!--  /*  display: inline-block;*/-->
<!--  /*  width: 100%;*/-->
<!--  /*  background-color: #fff;*/-->
<!--  /*  border: 1px solid #f1f1f1;*/-->
<!--  /*  transition: border-color .3s;*/-->
<!--  /*  text-align: center;*/-->
<!--  /*  padding: 1.5rem 0;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-info {*/-->
<!--  /*  cursor: pointer;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-img {*/-->
<!--  /*  margin: -40px 0 10px;*/-->
<!--  /*  width: 60px;*/-->
<!--  /*  height: 60px;*/-->
<!--  /*  vertical-align: middle;*/-->

<!--  /*}*/-->

<!--  /*.me-allct-name {*/-->
<!--  /*  font-size: 21px;*/-->
<!--  /*  font-weight: 150;*/-->
<!--  /*  overflow: hidden;*/-->
<!--  /*  text-overflow: ellipsis;*/-->
<!--  /*  white-space: nowrap;*/-->
<!--  /*  margin-top: 4px;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-description {*/-->
<!--  /*  min-height: 50px;*/-->
<!--  /*  font-size: 13px;*/-->
<!--  /*  line-height: 25px;*/-->
<!--  /*}*/-->

<!--  /*.me-allct-meta {*/-->
<!--  /*  font-size: 12px;*/-->
<!--  /*  color: #969696;*/-->
<!--  /*}*/-->

<!--  /*.el-main {*/-->
<!--  /*  padding: 0px;*/-->
<!--  /*  line-height: 16px;*/-->
<!--  /*}*/-->
<!--</style>-->
