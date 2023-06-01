<template>
  <div class="login-wrap-box">
    <div class="login-introduce-box">
      <h1 class="introduce-title">Welcome Bumpy!</h1>
      <div class="introduce-box">
        <template v-for="(message, idx) in introduceMessageList" :key="idx">
          <p class="introduce-message bp-my-md">{{ message }}</p>
        </template>

        <p class="contact-info"><span class="bp-mr-sm">email : cjh951114@naver.com</span><span>phone: 010-7917-2614</span>
        </p>
      </div>
    </div>
    <div class="login-form-box">
      <h2 class="login-form-title">USER LOGIN</h2>
      <form class="login-form bp-pa-md" :action="target" @submit="moveMain()">
        <div>
          <TextInput :data="formData.id" class="login-input bp-mb-xl"></TextInput>
          <PasswordInput :data="formData.password" class="login-input bp-mb-xl"></PasswordInput>
        </div>
        <div>
          <input type="submit" class="long-filled-button login-button bp-mb-xl" value="Login" />
          <p class="move-sign-up-wrap-box">not a member? <NuxtLink to="/signup" class="text-button bp-ml-sm">Sign up
            </NuxtLink>
          </p>
        </div>
      </form>
      <div @click="moveMain()">bbb</div>
      <!-- <div @click="opening()">bbb</div> -->
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import TextInput from '../components/form/TextInput.vue';
import PasswordInput from '../components/form/PasswordInput.vue';
import { InputPassword, InputText } from '~~/types/input';
import { fetchLogin } from '~~/api/user/user';
// const script = [{src: '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'}]
const router = useRouter();
const addScript = () =>{
  const script = document.createElement('script')
    script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'
    script.async = true
    script.onload = () => {
      // API 로드가 완료되었을 때 실행되는 로직
      // 주소 검색 등을 처리할 수 있습니다.
    }
    document.body.appendChild(script)
}
const opening = () =>{
  new daum.Postcode({
        oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 각 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          var addr = ''; // 주소 변수
          var extraAddr = ''; // 참고항목 변수

          //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
          if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
          } else { // 사용자가 지번 주소를 선택했을 경우(J)
            addr = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if(data.userSelectedType === 'R'){
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
              extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraAddr !== ''){
              extraAddr = ' (' + extraAddr + ')';
            }
            // 조합된 참고항목을 해당 필드에 넣는다.
            document.getElementById("sample6_extraAddress").value = extraAddr;

          } else {
            document.getElementById("sample6_extraAddress").value = '';
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('sample6_postcode').value = data.zonecode;
          document.getElementById("sample6_address").value = addr;
          // 커서를 상세주소 필드로 이동한다.
          document.getElementById("sample6_detailAddress").focus();
        }
      }).open();
}
interface userFormData {
  id: InputText;
  password: InputPassword;
}

const formData: Ref<userFormData> = ref({
  id: { value: '', placeholder: '아이디를 입력해주세요.', maxlength: 20, minlength: 3, pattern: '^[a-z]+[a-z0-9]{3,19}$' },
  password: { value: '', placeholder: '비밀번호', maxlength: 20, minlength: 3 },
});
const target = ref('#');
const moveMain = async () => {
  const formD = new FormData();
  formD.append('userId', formData.value.id.value);
  formD.append('password', formData.value.password.value);

  try {
    const { data, refresh } = fetchLogin('POST', formD);
    target.value = data.value.code ? 'main' : '#';
    return data.value.code;
  } catch (e) {
    console.error(e);
  }
};

const introduceMessageList: string[] = [
  'This is Login Page',
  'Please enter your information.',
  'If you have not signed up,',
  'plese click the sign up button',
  'to proceed with sign up.',
  'If you have any questions, please call or email us',
];

onMounted(()=>{
  addScript();
})
</script>
<style lang="scss" scoped></style>
