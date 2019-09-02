<!--//摸鱼讨论区-->
<!--<template>-->
<!--  <div v-title data-title="Let's Moyu Together!">-->
<!--    <el-container>-->
<!--      <el-aside width="100px"></el-aside>-->
<!--      <el-main class="me-articles">-->

<!--        <article-scroll-page></article-scroll-page>-->

<!--      </el-main>-->

<!--    </el-container>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--  import CardMe from '@/components/card/CardMe'-->
<!--  import CardArticle from '@/components/card/CardArticle'-->
<!--  import CardArchive from '@/components/card/CardArchive'-->
<!--  import CardTag from '@/components/card/CardTag'-->
<!--  import ArticleScrollPage from '@/views/common/ArticleScrollPage'-->

<!--  import {getArticles, getHotArtices, getNewArtices} from '@/api/article'-->
<!--  import {getHotTags} from '@/api/tag'-->
<!--  import {listArchives} from '@/api/article'-->

<!--  export default {-->
<!--    name: 'Index',-->
<!--    created() {-->
<!--      this.getHotArtices()-->
<!--      this.getNewArtices()-->
<!--      this.getHotTags()-->
<!--      this.listArchives()-->
<!--    },-->
<!--    data() {-->
<!--      return {-->
<!--        hotTags: [],-->
<!--        hotArticles: [],-->
<!--        newArticles: [],-->
<!--        archives: []-->
<!--      }-->
<!--    },-->
<!--    methods: {-->
<!--      getHotArtices() {-->
<!--        let that = this-->
<!--        getHotArtices().then(data => {-->
<!--          that.hotArticles = data.data-->
<!--        }).catch(error => {-->
<!--          if (error !== 'error') {-->
<!--            that.$message({type: 'error', message: '最热文章加载失败!', showClose: true})-->
<!--          }-->

<!--        })-->

<!--      },-->
<!--      getNewArtices() {-->
<!--        let that = this-->
<!--        getNewArtices().then(data => {-->
<!--          that.newArticles = data.data-->
<!--        }).catch(error => {-->
<!--          if (error !== 'error') {-->
<!--            that.$message({type: 'error', message: '最新文章加载失败!', showClose: true})-->
<!--          }-->

<!--        })-->

<!--      },-->
<!--      getHotTags() {-->
<!--        let that = this-->
<!--        getHotTags().then(data => {-->
<!--          that.hotTags = data.data-->
<!--        }).catch(error => {-->
<!--          if (error !== 'error') {-->
<!--            that.$message({type: 'error', message: '最热标签加载失败!', showClose: true})-->
<!--          }-->

<!--        })-->
<!--      },-->
<!--      listArchives() {-->
<!--        listArchives().then((data => {-->
<!--          this.archives = data.data-->
<!--        })).catch(error => {-->
<!--          if (error !== 'error') {-->
<!--            that.$message({type: 'error', message: '文章归档加载失败!', showClose: true})-->
<!--          }-->
<!--        })-->
<!--      }-->

<!--    },-->
<!--    components: {-->
<!--      'card-me': CardMe,-->
<!--      'card-article': CardArticle,-->
<!--      'card-tag': CardTag,-->
<!--      ArticleScrollPage,-->
<!--      CardArchive-->
<!--    }-->
<!--  }-->
<!--</script>-->

<!--<style scoped>-->

<!--  .el-container {-->
<!--    width: 960px;-->
<!--  }-->

<!--  .el-aside {-->
<!--    margin-left: 20px;-->
<!--    width: 260px;-->
<!--  }-->

<!--  .el-main {-->
<!--    padding: 0px;-->
<!--    line-height: 16px;-->
<!--  }-->

<!--  .el-card {-->
<!--    border-radius: 0;-->
<!--  }-->

<!--  .el-card:not(:first-child) {-->
<!--    margin-top: 20px;-->
<!--  }-->
<!--</style>-->

//后面三个页面并没有直接用article-scroll-page
<template>
  <div v-title>
    <el-container>
      <el-main class="me-OJ">
        <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
          <article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
        </scroll-page>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import ArticleItem from '@/components/article/ArticleItem'
  import ScrollPage from '@/components/scrollpage'
  import {getArticlesByCategory} from "../api/article";

  export default {
    name: "Index",
    props: {
      offset: {
        type: Number,
        default: 100
      },
      page: {
        type: Object,
        default() {
          return {}
        }
      },
      query: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    watch: {
      'query': {
        handler() {
          this.noData = false
          this.articles = []
          this.innerPage.pageNumber = 1
          this.getArticles()
        },
        deep: true
      },
      'page': {
        handler() {
          this.noData = false
          this.articles = []
          this.innerPage = this.page
          this.getArticles()
        },
        deep: true
      }
    },
    created() {
      this.getArticles()
    },
    data() {
      return {
        loading: false,
        noData: false,
        innerPage: {
          pageSize: 5,
          pageNumber: 1,
          name: 'a.createDate',
          sort: 'desc'
        },
        articles: []
      }
    },
    methods: {
      load() {
        this.getArticles()
      },
      view(id) {
        this.$router.push({path: `/view/${id}`}) //该方法用于浏览不同的文章
      },
      getArticles() {
        let that = this
        that.loading = true

        getArticlesByCategory(1).then(data => {

          let newArticles = data.data
          if (newArticles && newArticles.length > 0) {
            that.innerPage.pageNumber += 1
            that.articles = that.articles.concat(newArticles)
          } else {
            that.noData = true
          }

        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章加载失败!', showClose: true})
          }
        }).finally(() => {
          that.loading = false
        })

      }
    },
    components: {
      'article-item': ArticleItem,
      'scroll-page': ScrollPage
    }

  }
</script>

<style scoped>

  /*.el-container {*/
  /*  width: 960px;*/
  /*}*/

  /*.el-aside {*/
  /*  margin-left: 20px;*/
  /*  width: 260px;*/
  /*}*/

  /*.el-main {*/
  /*  padding: 0px;*/
  /*  line-height: 16px;*/
  /*}*/

  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 20px;
  }
</style>


