<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit Your Info</h1>
    <form class="content-wrap-box userpage-form">
      <Avatar name="jh"></Avatar>
      <label class="userpage-input-wrap bp-my-sm">
        <span>아이디</span>
        <TextInput :data="userForm.userId" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>비밀번호</span>
        <PasswordInput :data="userForm.password" class="userpage-input" />
        <button @click="togglePasswordChange" type="button">변경할꺼야</button>
      </label>
      <label class="userpage-input-wrap bp-my-sm" v-if="!userForm.password.disabled">
        <span>비밀번호 확인</span>
        <PasswordInput :data="userForm.passwordChk" class="userpage-input" @change="verifyPassword"/>
      </label>
      <label class="userpage-input-wrap email-input-wrap bp-my-sm">
        <span>이메일</span>
        <TextInput :data="userForm.email" class="userpage-input bp-mx-sm" />
        <button class="short-filled-button" type="button" @click="openEmailVerifyModal">인증</button>
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>이름</span>
        <TextInput :data="userForm.username" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>성별</span>
        <SelectboxInput :data="userForm.gender" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>생년월일</span>
        <DateInput :data="userForm.birth" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>전화번호</span>
        <div class="userpage-input phone-input-wrap bp-my-sm">
          <TextInput :data="userForm.phoneFirst" class="userpage-phone-input bp-ml-sm" />
          <TextInput :data="userForm.phoneSecond" class="userpage-phone-input bp-mx-sm" />
          <TextInput :data="userForm.phoneThird" class="userpage-phone-input" />
        </div>
      </label>
      <div class="userpage-address-wrap">
          <label class="userpage-zipcode-wrap bp-mb-sm">
            <TextInput :data="userForm.zipCode" class="userpage-zipcode-input bp-mr-sm"></TextInput>
            <button type="button" @click="openAddressModal" class="short-filled-button find-zipcode-button">우편번호 찾기</button>
          </label>
          <TextInput :data="userForm.address" class="bp-mb-sm" /> 
          <TextInput :data="userForm.addressDetail" class="bp-mb-sm" />
        </div>
      <div class="userpage-button-wrap">
        <button class="short-ghost-button cancel-button bp-mr-sm" type="button" @click="removeUserInfo">회원 탈퇴</button>
        <button class="short-ghost-button cancel-button bp-mr-sm" type="button" @click="movePasswordCheck">취소</button>
        <button class="short-filled-button save-button" type="button" @click="saveUserInfo">저장</button>
      </div>
    </form>
  </main>
  <div v-if="emailModalFlag" class="email-verificate-modal">
    <div class="email-verificate-content">
      <h1 class="email-verificate-title bp-mb-xl">이메일 인증입니다.</h1>
      <label class="email-verificate-input-wrap">
        <input type="text" class="email-verificate-input bp-mr-sm" v-model="emailVerficationCode" />
        <button class="short-filled-button email-verificate-button" @click="checkCertificateEmail">인증확인</button>
      </label>
      <div class="email-verificate-timer-wrap bp-my-sm">
        <span>Timer : {{verifyTime}}</span>
      </div>
    </div>
    <button class="long-filled-button email-verificate-modal-close-button" @click="closeEmailVerifyModal">닫기</button>
  </div>
</template>
<script setup lang="ts">
import DateInput from '~~/components/form/DateInput.vue';
// import NumberInput from '~~/components/form/NumberInput.vue';
import SelectboxInput from '~~/components/form/SelectboxInput.vue';
import PasswordInput from '~~/components/form/PasswordInput.vue';
import TextInput from '~~/components/form/TextInput.vue';
import { ref } from 'vue';
import Avatar from '~~/components/user/Avatar.vue';
import { useUserStore } from '~~/store/user';
import { createCheckCertificateEmail, createEmailVerificationCode } from '~~/api/user/signup';
import { setErrorMessage, setWarnMessage } from '~~/api/alert/message';



const userForm = ref({
  userId: { value: '', disabled: true, placeholder: '아이디를 입력해주세요.' },
  password: { placeholder: '변경할 비밀번호를 영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요.', disabled: true },
  passwordChk: { value: '', placeholder: '영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요.' },
  email: { value: '', placeholder: 'email형식을 지켜 작성해주세요.' },
  username: { value: '', placeholder: '이름을 입력해주세요.' },
  gender: {value:'0', list:[{dtlCd: '0', dtlNm: '남'},{dtlCd: '1', dtlNm: '여'}], disabled: true},
  birth: { value: '', placeholder: '생일을 입력해주세요' },
  phoneFirst: { value: '', placeholder: '000' },
  phoneSecond: { value: '', placeholder: '0000' },
  phoneThird: { value: '', placeholder: '0000' },
  zipCode: { placeholder: "우편번호", disabled:true },
  address: {value: '', placeholder: "주소", disabled:true },
  addressDetail: {value: '', placeholder: "상세주소를 입력해주세요." },
});
definePageMeta({
  layout: 'main-layout',
});
const userStore = useUserStore();
const router= useRouter();

const verifyPassword = async () =>{
  if(userForm.value.password.value ===userForm.value.passwordChk.value ){
    const flag = await userStore.checkPasswordVerify({password: userForm.value.passwordChk.value})
    userForm.value.password.disabled = flag
  }
}

const togglePasswordChange = () => {
    userForm.value.password.disabled = !userForm.value.password.disabled;
    userForm.value.passwordChk.value = '';
}
const emailVerficationCode = ref("");

// 이메일 인증 여부 변수
const verificateEmail = ref(false);
// 이메일 인증 Modal
const emailModalFlag = ref(false);
// 이메일 인증 타이머
const verifyTime = ref(0);

let countTimer: any = null; // interval함수 저장할 변수.


// 1씩 감소하는 함수
const countVerifyTime = () =>{
  verifyTime.value-= 1;
  if(verifyTime.value===0) verifyTime.value = 300;
  if(verifyTime.value===300) {
    sendEmailVerificationCode();
  }
}


const sendEmailVerificationCode = async () => {
  try{
    const { email, userId } = userForm.value;
    const { data, error } = await createEmailVerificationCode({ email: email.value, userId: userId.value });
    const verificationCode = data.value.verificationCode;
    emailVerficationCode.value = verificationCode;
  }catch(e){
    setErrorMessage(e);
  }
};


// // Timer 세팅하는 함수
const setVerifyTimer = () =>{
  verifyTime.value = 300;
  emailModalFlag.value = true;
  countTimer = setInterval(countVerifyTime, 1000);
  sendEmailVerificationCode();
}

// // 이메일 인증 Modal Open함수
const openEmailVerifyModal = () => {
  // e.preventDefault();
  const emailValue = userForm.value.email.value as string;
  if(emailValue === null || emailValue.length===0){
    setWarnMessage("이메일 값을 입력해주세요.");
  } else if(!verificateEmail.value){
    setVerifyTimer();
  }
};

// // 이메일 인증 Modal Close 함수
const closeEmailVerifyModal = () => {
  emailModalFlag.value = false;
  clearInterval(countTimer)
};


// // 이메일 인증 확인
const checkCertificateEmail = async() => {
  try{
    const { email, userId } = userForm.value;
    const body = {
      email: email.value,
      userId: userId.value,
      verifyCode: emailVerficationCode.value,
    };
    const {data, error} = await createCheckCertificateEmail(body);
    if(error.value !== null){
        const statusCode = error.value.statusCode;
        const errorMessage = error.value?.data.message;
        setErrorMessage(errorMessage);
    }else if(data.value !== null){
      // 성공 후 
      verificateEmail.value = true; // 변경 필요
      userForm.value.email.disabled = true;
      closeEmailVerifyModal();
    }
  }catch(e){
    setErrorMessage(e)
  }
};

// 우편번호API script 추가
const addScript = () => {
  const script = document.createElement("script");
  // script.src = process.env.ZIPCODE_SCRIPT_URL as string;
  script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'
  script.async = true;
  script.onload = () => {
    // API 로드가 완료되었을 때 실행되는 로직
    // 주소 검색 등을 처리할 수 있습니다.
  };
  document.body.appendChild(script);
};
// 우편번호 modal open
const openAddressModal = () => {
  new daum.Postcode({
    oncomplete: function (data: any) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      let addr = ""; // 주소 변수
      let extraAddr = ""; // 참고항목 변수

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
        addr += ` ${extraAddr}`;
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      userForm.value.zipCode.value = data.zonecode;
      userForm.value.address.value = addr;
      // 커서를 상세주소 필드로 이동한다.
    },
  }).open();
};

// 회원가입 버튼 클릭 시 body만들기
const makeBody = (body: any) =>{
  
  const result = {
    // userId : body.id.value,
    password : body.password.value,
    // email : body.email.value,
    gender :body.gender.value,
    birth : body.birth.value,
    phoneNumber : `${body.phoneFirst.value}-${body.phoneSecond.value}-${body.phoneThird.value}`,
    zipCode :body.zipCode.value,
    address :body.address.value,
    addressDetail: body.addressDetail.value 
  };
  return result;
}

const saveUserInfo = async () =>{
  const body = makeBody(userForm.value);
  await userStore.updateUserPageData(body)
  router.push({name: 'user-passwordCheck'});
  
}

const removeUserInfo = async () =>{
  await userStore.deleteUserPageData();
  router.push({name: 'login'})
}

const movePasswordCheck = () =>{
  router.back();
}

const initUserForm = async () =>{
  const userData = userStore.getUserPageData();
  const keyList = Object.keys(userData);
  for(const key of keyList){
    userForm.value[key].value = userData[key];
  }
}

onMounted(async () => {
  addScript();
  await userStore.fetchUserPageInfo();
  await initUserForm();
  console.log(userStore.getUserPageData())
});
</script>
