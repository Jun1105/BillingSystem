<script lang="ts">
  import { defineComponent, reactive, ref } from 'vue'
  import type { FormInstance, FormRules } from 'element-plus'
  import { login } from '@/api/login'
  import { userStore } from '@/stores/user'
  import { useRouter } from 'vue-router'
  import loading from '@/utils/loading'
  export default defineComponent({
    setup() {
      const user = userStore()
      const router = useRouter()
      const formRef = ref<FormInstance>()
      const ruleForm = reactive({
        username: '',
        password: ''
      })

      const rules = reactive<FormRules>({
        username: [
          { required: true, message: 'Please input username', trigger: 'blur' },
          { min: 3, message: 'Min Length min 3', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' },
          { min: 3, message: 'Min Length is 3', trigger: 'blur' }
        ]
      })

      const submitForm = async (formEl: FormInstance | undefined) => {
        if (!formEl) return
        await formEl.validate((valid, fields) => {
          if (valid) {
            loading(true)
            login(ruleForm)
              .then(value => {
                const { data } = value
                if (data) {
                  user.settingUser(data.id, data.username)
                  router.push('/')
                } else {
                  formEl.resetFields()
                }
              })
              .finally(() => {
                loading(false)
              })
          } else {
            console.log('error submit!', fields)
          }
        })
      }

      const resetForm = (formEl: FormInstance | undefined) => {
        if (!formEl) return
        formEl.resetFields()
      }

      return {
        formRef,
        ruleForm,
        rules,
        submitForm,
        resetForm
      }
    }
  })
</script>
<template>
  <div class="box-card">
    <el-card class="card" shadow="always">
      <template #header>
        <h3>Welecom to Billing System!</h3>
      </template>
      <el-form
        ref="formRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        status-icon
      >
        <el-row :gutter="20">
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="用户名" prop="username">
              <el-input
                v-model="ruleForm.username"
                placeholder="please input username"
                @keyup="
                  ruleForm.username = ruleForm.username.replace(
                    /[^a-zA-Z]/g,
                    ''
                  )
                "
                clearable
                maxlength="18"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item label="密码" prop="password">
              <el-input
                type="password"
                show-password
                placeholder="please input password"
                clearable
                maxlength="18"
                v-model="ruleForm.password"
                @keyup.enter="submitForm(formRef)"
              />
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <el-form-item>
              <el-button type="primary" @click="submitForm(formRef)">
                Submit
              </el-button>
              <el-button @click="resetForm(formRef)">Reset</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <h6>
        <el-tooltip
          effect="dark"
          content="jun1806068039@163.com"
          placement="bottom"
        >
          <el-button class="box-item" size="small">
            若无账号，请联系管理员！
          </el-button>
        </el-tooltip>
      </h6>
    </el-card>
  </div>
  <div class="foot">
    <a href="https://beian.miit.gov.cn/">鄂ICP备 2022015325号</a>
  </div>
</template>
<style lang="scss">
  @media screen and (max-width: 768px) {
    .box-card .card {
      width: 100vw !important;
    }
  }
  .box-card {
    min-height: calc(100vh - 40px);
    // background-image: linear-gradient(
    //   to left,
    //   rgb(149, 154, 245),
    //   rgb(71, 71, 245)
    // );
    .card {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 30%;
      h3 {
        text-align: center;
      }
      h6 {
        text-align: center;
        .box-item {
          color: red;
        }
      }
    }
  }
  .foot {
    height: 40px;
    line-height: 40px;
    background: #666666;
    text-align: center;
    a {
      color: #fff;
      text-decoration: none;
    }
  }
</style>
