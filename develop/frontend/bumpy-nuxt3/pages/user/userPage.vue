<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit Your Info</h1>
    <form class="content-wrap-box userpage-form" :action="target" @submit="moveLogin(userForm)">
      <Avatar name="jh"></Avatar>
      <label class="userpage-input-wrap bp-my-sm">
        <span>아이디</span>
        <TextInput :data="userForm.id" class="userpage-input" />
        <!-- <button class="short-filled-button duplicate-check-button">중복검사</button> -->
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>비밀번호</span>
        <PasswordInput :data="userForm.password" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>비밀번호 확인</span>
        <PasswordInput :data="userForm.passwordChk" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap email-input-wrap bp-my-sm">
        <span>이메일</span>
        <TextInput :data="userForm.email" class="userpage-input bp-mx-sm" />
        <button class="short-filled-button">인증</button>
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>이름</span>
        <TextInput :data="userForm.name" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>생년월일</span>
        <DateInput :data="userForm.birth" class="userpage-input" />
      </label>
      <label class="userpage-input-wrap bp-my-sm">
        <span>전화번호</span>
        <div class="userpage-input phone-input-wrap bp-my-sm">
          <NumberInput :data="userForm.phoneFirst" class="userpage-phone-input bp-ml-sm" />
          <NumberInput :data="userForm.phoneSecond" class="userpage-phone-input bp-mx-sm" />
          <NumberInput :data="userForm.phoneThird" class="userpage-phone-input" />
        </div>
      </label>
      <div>
        <label class="userpage-input-wrap bp-my-sm">
          <span>주소</span>
          <TextInput :data="userForm.address" class="userpage-input" />
        </label>
        <label class="userpage-input-wrap bp-my-sm">
          <span>상세주소</span>
          <TextInput :data="userForm.detailAddress" class="userpage-input" />
        </label>
      </div>
      <div class="userpage-button-wrap">
        <button class="short-ghost-button cancel-button bp-mr-sm">취소</button>
        <input type="submit" class="short-filled-button save-button" value="저장" />
      </div>
    </form>
  </main>
</template>
<script setup>
import DateInput from '~~/components/form/DateInput.vue';
import NumberInput from '~~/components/form/NumberInput.vue';
import PasswordInput from '~~/components/form/PasswordInput.vue';
import TextInput from '~~/components/form/TextInput.vue';
import { ref } from 'vue';
import Avatar from '~~/components/user/Avatar.vue';
import { useUserStore } from '~~/store/user';
const target = 'login';
const userForm = ref({
  id: { value: 'cjh951114', disabled: true, placeholder: '아이디를 입력해주세요.' },
  password: { placeholder: '변경할 비밀번호를 영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요.' },
  passwordChk: { placeholder: '영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요.' },
  email: { value: 'cjh951114@naver.com', placeholder: 'email형식을 지켜 작성해주세요.' },
  name: { value: '준형갓', placeholder: '이름을 입력해주세요.' },
  birth: { value: '1996-01-04', placeholder: '생일을 입력해주세요' },
  phoneFirst: { value: '010', placeholder: '000' },
  phoneSecond: { value: '7917', placeholder: '0000' },
  phoneThird: { value: '2614', placeholder: '0000' },
  address: { placeholder: '주소를 입력해주세요.' },
  detailAddress: { placeholder: '상세주소를 입력해주세요.', disabled: true },
});
definePageMeta({
  layout: 'main-layout',
});
const userStore = useUserStore();
const router= useRouter();
onMounted(()=>{
  // if(!userStore.getIsPass()) router.push({name: 'user-passwordCheck'})
})
</script>
