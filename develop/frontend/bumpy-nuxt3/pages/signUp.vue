<template>
  <div class="signup-wrap-box">
    <div class="signup-introduce-box">
      <h1 class="introdue-title">Welcome Bumpy!</h1>
      <div class="introduce-box">
        <template v-for="(message, idx) in introduceMessageList" :key="idx">
          <p class="introduce-message bp-my-md">{{ message }}</p>
        </template>
        <p class="contact-info"><span class="bp-mr-sm">email : cjh951114@naver.com</span><span>phone: 010-7917-2614</span></p>
      </div>
    </div>
    <div class="signup-form-box">
      <h2 class="signup-form-title">SIGN UP</h2>
      <form class="signup-form" :action="target">
        <div class="signup-id-wrap">
          <TextInput :data="userForm.id" class="id-input bp-my-sm bp-mr-sm" />
          <button class="short-filled-button duplicate-check-button" :class="{ 'check-ok': !duplicateId }" @click="checkDuplicateId">{{ duplicateBtnText }}</button>
        </div>
        <PasswordInput :data="userForm.password" class="bp-my-sm" />
        <PasswordInput :data="userForm.passwordChk" class="bp-my-sm" />
        <div class="signup-email-wrap">
          <TextInput :data="userForm.email" class="email-input bp-my-sm bp-mr-sm" />
          <button class="short-filled-button" :class="{ 'check-ok': verificateEmail }" @click="openModal">인증</button>
        </div>
        <TextInput :data="userForm.name" class="bp-my-sm" />
        <DateInput :data="userForm.birth" class="bp-my-sm" />
        <div class="signup-phone-wrap bp-my-sm">
          <NumberInput :data="userForm.phoneFirst" />
          <NumberInput :data="userForm.phoneSecond" class="bp-mx-sm" />
          <NumberInput :data="userForm.phoneThird" />
        </div>
        <div>
          <NumberInput :data="userForm.addressMail"></NumberInput><button @click="opening">우편번호 찾기</button>
          <TextInput :data="userForm.address" class="bp-my-sm" /> 
          <TextInput :data="userForm.addressDetail" class="bp-my-sm" /><TextInput :data="userForm.addressExtra" class="bp-my-sm" />
        </div>
        <div class="signup-button-wrap">
          <button class="short-ghost-button cancel-button bp-mr-sm" @click="moveLogin">취소</button>
          <button class="short-filled-button signup-button" @click="signUp">회원가입</button>
        </div>
      </form>
    </div>
  </div>

  <div v-if="emailModalFlag" class="email-verificate-modal">
    <h1>이메일 인증입니다.</h1>
    <label>
      <input type="text" v-model="emailVerficationCode" />
      <button @click="checkCertificateEmail">인증확인</button>
    </label>
    <span>Timer</span>
    <button @click="closeModal">닫기</button>
  </div>
</template>
<script setup lang="ts">
import _ from 'lodash';
import TextInput from "../components/form/TextInput.vue";
import PasswordInput from "../components/form/PasswordInput.vue";
import NumberInput from "../components/form/NumberInput.vue";
import DateInput from "../components/form/DateInput.vue";
import { ref, Ref } from "vue";
import { createCheckDuplicateId, createEmailVerificationCode, createCheckCertificateEmail, createSignUp } from "~/api/user/signup";
import { InputDate, InputNumber, InputPassword, InputText } from "~~/types/input";
const target = ref("/");
const router = useRouter();
interface userFormData {
  id: InputText;
  password: InputPassword;
  passwordChk: InputPassword;
  email: InputText;
  name: InputText;
  birth: InputDate;
  phoneFirst: InputNumber;
  phoneSecond: InputNumber;
  phoneThird: InputNumber;
  addressMail: InputNumber;
  address: InputText;
  addressDetail: InputText;
  addressExtra: InputText;
}

const moveLogin = () => {
  return true;
};

const userForm: Ref<userFormData> = ref({
  id: { value: "", placeholder: "아이디를 입력해주세요." },
  password: { placeholder: "영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요." },
  passwordChk: { placeholder: "영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요." },
  email: { placeholder: "email형식을 지켜 작성해주세요." },
  name: { placeholder: "이름을 입력해주세요." },
  birth: { placeholder: "생일을 입력해주세요" },
  phoneFirst: { placeholder: "000" },
  phoneSecond: { placeholder: "0000" },
  phoneThird: { placeholder: "0000" },
  addressMail: { placeholder: "우편번호", disabled:true },
  address: { placeholder: "주소", disabled:true },
  addressDetail: { placeholder: "상세주소를 입력해주세요." },
  addressExtra: { placeholder: "참고항목", disabled: true },
});

const introduceMessageList: string[] = ["This is Signup Page", "Please enter your information.", "You can sign up for membership", "If you have any questions", "Please call or email us"];

const duplicateBtnText = computed(() => (duplicateId.value ? "중복검사" : "✔"));
const duplicateId = ref(true);
const checkDuplicateId = (e: Event) => {
  e.preventDefault();
  if (duplicateId.value) {
    const { data } = createCheckDuplicateId({ userId: userForm.value.id.value });
    const duplicateMessage = data.value.message;
    if (duplicateMessage === "사용 가능한 아이디입니다.") duplicateId.value = false;
  }
};

const emailVerficationCode = ref("");
const verificateEmail = ref(false);
const emailModalFlag = ref(false);
const closeModal = () => (emailModalFlag.value = false);
const openModal = (e: Event) => {
  e.preventDefault();
  if (duplicateId.value) {
    alert("아이디 중복검사 먼저해주세요");
  } else {
    emailModalFlag.value = true;
    sendEmailVerificationCode();
  }
};
const sendEmailVerificationCode = () => {
  const { email, id } = userForm.value;
  const { data } = createEmailVerificationCode({ email: email.value, userId: id.value });
  const { code } = data.value.verificationCode;
  emailVerficationCode.value = code;
};
const checkCertificateEmail = () => {
  const { email, id } = userForm.value;
  const body = {
    email: email.value,
    userId: id.value,
    verifyCode: emailVerficationCode.value,
  };
  const { data } = createCheckCertificateEmail(body);
  if (data.value.message === "OK") {
    verificateEmail.value = true;
    closeModal();
  }
};

const addScript = () => {
  const script = document.createElement("script");
  script.src = "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
  script.async = true;
  script.onload = () => {
    // API 로드가 완료되었을 때 실행되는 로직
    // 주소 검색 등을 처리할 수 있습니다.
  };
  document.body.appendChild(script);
};
const opening = (e: Event) => {
  e.preventDefault();
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수
      var extraAddr = ""; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if (data.userSelectedType === "R") {
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr += extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        userForm.value.addressExtra.value = extraAddr;
      } else {
        userForm.value.addressExtra.value = "";
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      userForm.value.addressMail.value = data.zonecode;
      userForm.value.address.value = addr;
      // 커서를 상세주소 필드로 이동한다.
    },
  }).open();
};

const makeSignUpBody = (body: userFormData) =>{
  
  const result = {
    userId : body.id.value,
    password : body.password.value,
    email : body.email.value,
    // gender :body.gender.value,
    gender :1,
    birth : body.birth.value,
    phoneNumber : `${body.phoneFirst.value}-${body.phoneSecond.value}-${body.phoneThird.value}`,
    addressMail :body.addressMail.value,
    address :`${body.address.value} ${body.addressDetail.value} ${body.addressExtra.value}`,
  };
  return result;
}

const signUp = (e:Event) =>{
  e.preventDefault();
  console.log(duplicateId.value)
  console.log(verificateEmail.value)
  if(duplicateId.value) alert('아이디 중복 확인 바랍니다.');
  else if(!verificateEmail.value) alert('이메일 인증 확인 바랍니다.');
  else {
    const body = makeSignUpBody(userForm.value)
    const {data} = createSignUp(body)
    const message = data.value.message;
    // console.log(message)
    if(message === 'OK') router.push({ path: '/' });
  }
}



onMounted(() => {
  addScript();
});
</script>
<style scoped lang="scss">
.login-form {
  display: flex;
  flex-direction: column;
}
.check-ok {
  background-color: lightgreen;
  color: green;
}
</style>
