<template>
  <el-container>
    <el-aside width="60px">
    </el-aside>
    <el-main>
      <el-tabs v-model="activeName" @tab-click="handleClick" tab-position="left">
        <!--<el-tab-pane label="个人信息" name="info">-->
        <!--  <h4>blank</h4>-->
        <!--</el-tab-pane>-->
        <!--<el-tab-pane label="已发帖" name="post">-->
        <!--  <h2>后端加班中...</h2>-->
        <!--</el-tab-pane>-->
        <el-tab-pane label="编辑个人资料" name="info">
          <el-form ref="form" :model="form" label-width="80px" label-position="right" width="100px">
            <el-form-item label="昵称">
              <el-input v-model="form.name" placeholder="请输入昵称" style="width: 200px;"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email" placeholder="请输入邮箱" style="width: 200px;"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio-group v-model="form.sex">
                <el-radio label="1">男生</el-radio>
                <el-radio label="2">女生</el-radio>
                <el-radio label="3">神秘</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="院系">
              <el-select v-model="form.value" placeholder="请选择院系" style="width:200px;">
                <!--这个form.value太坑了-->
                <el-option
                  v-for="item in form.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="出生年月">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" v-model="form.date1"
                                style="width:200px"></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label="爱好">
              <el-checkbox-group v-model="form.type">
                <el-checkbox label="Moyu灌水" name="type"></el-checkbox>
                <el-checkbox label="前端爱好者" name="type"></el-checkbox>
                <el-checkbox label="后端爱好者" name="type"></el-checkbox>
                <el-checkbox label="人像/摄影" name="type"></el-checkbox>
                <br>
                <el-checkbox label="电影剧集" name="type"></el-checkbox>
                <el-checkbox label="追星/八卦" name="type"></el-checkbox>
                <el-checkbox label="运动" name="type"></el-checkbox>
                <el-checkbox label="编程" name="type"></el-checkbox>
                <el-checkbox label="发呆" name="type"></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="学历">
              <el-radio-group v-model="form.resource">
                <el-radio label="本科生"></el-radio>
                <el-radio label="研究生"></el-radio>
                <el-radio label="博士生"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="个人简介">
              <el-input type="textarea" v-model="form.desc" placeholder="简单介绍一下自己吧！" style="width: 500px"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="alterInformation()">修改</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="修改密码" name="password">
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="新密码" prop="pass">
              <el-input type="password" v-model="ruleForm.pass" placeholder="请输入新密码" autocomplete="off" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="ruleForm.checkPass" placeholder="重复输入以确认" autocomplete="off" style="width: 200px"></el-input>
            </el-form-item>
            <!--<el-form-item label="年龄" prop="age">-->
            <!--  <el-input v-model.number="ruleForm.age"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item>
              <el-button type="primary" @click="alterUserPassword('ruleForm')">提交</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="用户管理" name="userManage">
          <el-table
            :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%">
            <el-table-column
              label="注册日期"
              prop="date">
            </el-table-column>
            <el-table-column
              label="账号"
              prop="account">
            </el-table-column>
            <el-table-column
              label="昵称"
              prop="name">
            </el-table-column>
            <el-table-column
              align="right">
              <template slot="header" slot-scope="scope">
                <el-input
                  v-model="search"
                  size="mini"
                  placeholder="输入关键字搜索"/>
              </template>
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="deleteUser()">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <!--<el-tab-pane label="举报处理" name="Manage">-->
        <!--  <h2>后端加班中...</h2>-->
        <!--</el-tab-pane>-->
      </el-tabs>

    </el-main>
  </el-container>
</template>


<script>
  export default {
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        ruleForm: {
          pass: '',
          checkPass: '',
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
        },
        form: {
          name: '',
          sex: '',
          value:[],
          options:[
            {value:1,label:'1系-材料科学与工程学院'},
            {value:2,label:'2系-电子信息工程学院'},
            {value:3,label:'3系-自动化科学与电气工程学院'},
            {value:4,label:'4系-能源与动力工程学院'},
            {value:5,label:'5系-航空科学与工程学院'},
            {value:6,label:'6系-计算机学院'},
            {value:7,label:'7系-机械工程及自动化学院'},
            {value:8,label:'8系-经济管理学院'},
            {value:9,label:'9系-数学与系统科学学院'},
            {value:10,label:'10系-生物与医学工程学院'},
            {value:11,label:'11系-人文社会科学学院（公共管理学院）'},
            {value:12,label:'12系-外国语学院'},
            {value:13,label:'13系-交通科学与工程学院'},
            {value:14,label:'14系-可靠性与系统工程学院'},
            {value:15,label:'15系-宇航学院'},
            {value:16,label:'16系-飞行学院'},
            {value:17,label:'17系-仪器科学与光电工程学院'},
            {value:18,label:'18系-北京学院'},
            {value:19,label:'19系-物理科学与核能工程学院'},
            {value:20,label:'20系-法学院'},
            {value:21,label:'21系-软件学院'},
            {value:22,label:'22系-现代远程教育学院'},
            {value:23,label:'23系-高等理工学院'},
            {value:24,label:'24系-中法工程师学院'},
            {value:25,label:'25系-国际学院'},
            {value:26,label:'26系-新媒体艺术与设计学院'},
            {value:27,label:'27系-化学学院'},
            {value:28,label:'28系-马克思主义学院'},
            {value:29,label:'29系-人文与社会科学高等研究院'},
            {value:30,label:'30系-空间与环境学院'},
            {value:35,label:'35系-国际通用工程学院'},
            {value:37,label:'37系-北航学院（本科生院'},
            {value:39,label:'39系-网络空间安全学院'},
            {value:41,label:'41系-微电子学院'},
          ],
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '',
        },
        activeName: 'info',
        tableData: [{
          date: '2019-08-25',
          account:'jack1',
          name: 'jack',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2019-08-26',
          account:'whh',
          name: 'todd',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2019-08-23',
          account:'hky',
          name: 'tom',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-07-03',
          account:'wmh',
          name: 'jerry',
          address: '上海市普陀区金沙江路 1516 弄'
        }],
        search: ''
      }
    },
    methods: {
      onSubmit() {
        alert('修改成功');//需要判断信息的合法性
        console.log('修改成功!');
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClick(tab, event) {
        console.log(tab, event);
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('修改成功!');
          } else {
            console.log('修改失败!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      alterUserPassword(formName){
        let self = this
        let data={account:self.$store.state.account,password:this.ruleForm.pass}
        this.$refs[formName].validate((valid) => {
          if(valid){
            this.$ajax.post('/change',data).then(() =>{
                self.$router.push({path:'/'});//返回主页
                self.$message({message:'修改密码成功 请继续浏览哦',type:'success',showClose:true})//消息提示
              }).catch((error) => {
              if(error !== 'error'){
                self.$message({message:error,type:'error',showClose:true});
              }
            })
          }
        })
      },
      deleteUser(){
        this.$router.push({path:'/'});
        this.$message({message:'删除用户成功 请继续浏览哦',type:'success',showClose:true})
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      alterInformation(){
        this.$router.push({path:'/'});
        this.$message({message:'修改用户信息成功 请继续浏览哦',type:'success',showClose:true})
      }
    }
  }
</script>
