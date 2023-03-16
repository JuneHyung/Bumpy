<template>
  <div class="user-info-box">
    <ul class="user-info-button-list">
      <li class="logout-button">Logout</li>
      <li class="setting-button">Setting</li>
    </ul>
    <div class="user-profile-wrap-box">
      <div class="user-profile-box"></div>
    </div>
    <p>준형갓</p>
    <ul class="user-body-info-box">
      <li>
        <p>170cm</p>
        <p>Height</p>
      </li>
      <li>
        <p>69.5kg</p>
        <p>Weight</p>
      </li>
      <li>
        <p>27</p>
        <p>age</p>
      </li>
    </ul>
    <ul class="user-activity-info-box">
      <li>
        <p>170cm</p>
        <p>Height</p>
      </li>
      <li>
        <p>69.5kg</p>
        <p>Weight</p>
      </li>
      <li>
        <p>27</p>
        <p>age</p>
      </li>
    </ul>
    <ul class="user-inbody-info-box">
      <li>
        <p>체중</p>
        <p>ㅇㅇㅇ</p>
      </li>
      <li>
        <p>체중</p>
        <p>ㅇㅇㅇ</p>
      </li>
      <li>
        <p>체중</p>
        <p>ㅇㅇㅇ</p>
      </li>

      <li>
        <p>체중</p>
        <p>ㅇㅇㅇ</p>
      </li>
      <li>
        <p>체중</p>
        <p>ㅇㅇㅇ</p>
      </li>
    </ul>
  </div>
</template>
<style scoped lang="scss">
.user-info-box {
  display: flex;
  flex-direction: column;

  /* align-items: center; */
  height: 100%;
  width: 300px;
  box-sizing: border-box;
  border: 1px solid black;
  padding: 20px 10px;

  .user-info-button-list {
    display: flex;
    height: auto;
    /* flex-direction: row; */
    /* flex: 1; */
    /* justify-content: flex-start; */
    .logout-button {
      font-weight: 500;
      /* justify-content: start; */
      /* margin-left: auto; */
    }
    .setting-button {
      margin-left: auto;
    }
  }

  .user-profile-wrap-box {
    display: flex;
    justify-content: center;
    margin: 60px 0 24px 0;
    .user-profile-box {
      width: 200px;
      height: 200px;
      border-radius: 50%;
      background: grey;
    }
  }

  .user-body-info-box {
    margin: 20px 0 60px 0;
  }
  .user-activity-info-box {
    margin: 20px 0 60px 0;
  }
  .user-inbody-info-box {
    margin: 0;
  }
}
</style>
