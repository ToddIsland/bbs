<template>
  <el-header class="me-area">
    <el-row class="me-header">

      <el-col :span="4" class="me-header-left">
        <router-link to="/" class="me-title">
          <img src="../assets/img/logo.png"/>
        </router-link>
      </el-col>
      <el-col v-if="!simple" :span="16">
        <el-menu :router=true menu-trigger="click" active-text-color="#5FB878" :default-active="activeIndex"
                 mode="horizontal">
          <!--<el-menu-item index="/">摸鱼讨论区</el-menu-item>-->
          <!--<el-menu-item index="/category/all">OJ刷题区</el-menu-item>-->
          <!--<el-menu-item index="/tag/all">校园周边展示</el-menu-item>-->
          <!--<el-menu-item index="/archives">生活区</el-menu-item>          -->
          <!--修改index-->
          <el-menu-item index="/">摸鱼讨论区</el-menu-item>
          <el-menu-item index="/oj">OJ刷题区</el-menu-item>
          <el-menu-item index="/campus">校园周边展示</el-menu-item>
          <el-menu-item index="/life">生活区</el-menu-item>


          <!--<el-menu-item index="/log">日志</el-menu-item>-->
          <!--<el-menu-item index="/messageBoard">留言板</el-menu-item>-->
          <!--  删除两个板块-->
          <el-col :span="4" :offset="4">
            <el-menu-item index="/write"><i class="el-icon-edit"></i>发帖子</el-menu-item>
          </el-col>

        </el-menu>
      </el-col>

      <template v-else>
        <slot></slot>
      </template>

      <el-col :span="4">
        <el-menu :router=true menu-trigger="click" mode="horizontal" active-text-color="#5FB878">

          <template v-if="!user.login">
            <el-menu-item index="/login">
              <el-button type="text">登录</el-button>
            </el-menu-item>
            <el-menu-item index="/register">
              <el-button type="text">注册</el-button>
            </el-menu-item>
          </template>

          <template v-else>
            <el-submenu mode="vertical">
              <template slot="title">
                <img class="me-header-picture" :src="user.avatar"/>&nbsp&nbsp&nbsp&nbsp你好{{this.$store.state.name}}
              </template>
              <el-menu-item index="/information" @click="goInformation">个人页面</el-menu-item>
              <el-menu-item index="1-2" @click="logout"><i class="el-icon-back"></i>退出</el-menu-item>
            </el-submenu>
          </template>
        </el-menu>
      </el-col>

    </el-row>
  </el-header>
</template>

<script>
  export default {
    name: 'BaseHeader',
    props: {
      activeIndex: String,
      simple: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {}
    },
    computed: {
      user() {
        let login = this.$store.state.account.length != 0
        let avatar = this.$store.state.avatar
        return {
          login, avatar
        }
      }
    },
    methods: {
      logout() {
        let that = this
        this.$store.dispatch('logout').then(() => {
          this.$router.push({path: '/'})
        }).catch((error) => {
          if (error !== 'error') {
            that.$message({message: error, type: 'error', showClose: true});
          }
        })
      },
      goInformation() {
        this.$router.push({path: '/information'})
      }
    }
  }
</script>

<style>

  .el-header {
    position: fixed;
    z-index: 1024;
    min-width: 100%;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
  }

  .me-title {
    margin-top: 10px;
    font-size: 24px;
  }

  .me-header-left {
    margin-top: 10px;
  }

  .me-title img {
    max-height: 2.4rem;
    max-width: 100%;
  }

  .me-header-picture {
    width: 36px;
    height: 36px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
    background-color: #5fb878;
    /*background-color: #0066cc;*/
  }
</style>
