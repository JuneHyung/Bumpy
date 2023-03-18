<template>
  <main class="main-page">
    <h1 class="content-title">Introduce</h1>
    <div class="content-wrap-box">
      <p>Welcome 준형갓!</p>
      <p>운동하다 죽기 딱 좋은 날씨에요!</p>
    </div>
    <div class="activity-meal-wrap-box">
      <div class="content-wrap-box activity-wrap-box">
        <div class="activity-title-box">
          <p class="activity-title">Your activity</p>
          <p class="activity-date">2023.02.07</p>
        </div>
        <div class="calendar"></div>
      </div>
      <div class="content-wrap-box meal-wrap-box">
        <div class="meal-title-box">
          <p class="meal-title">Your activity</p>
          <p class="meal-date">2023.02.07</p>
        </div>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <p class="activity-title">Last activity</p>
          <p class="activity-date">2023.02.07</p>
        </div>
        <li class="list-item">
          <p>벤치 프레스</p>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
        </li>
        <li class="list-item">
          <p>벤치 프레스</p>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
        </li>
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">My Best</p>
            <p class="chart-info-item-value">40kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Month Average</p>
            <p class="chart-info-item-value">26.6kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">12reps / 5sets</p>
          </li>
        </ul>
        <div class="last-activity-chart"></div>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <p class="activity-title">Last activity</p>
          <p class="activity-date">2023.02.07</p>
        </div>
        <li class="list-item">
          <p>벤치 프레스</p>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
        </li>
        <li class="list-item">
          <p>벤치 프레스</p>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
          <div>
            <span>10kg ~ 30kg</span>
            <span> / </span>
            <span>20kg</span>
          </div>
        </li>
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">My Best</p>
            <p class="chart-info-item-value">40kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Month Average</p>
            <p class="chart-info-item-value">26.6kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">12reps / 5sets</p>
          </li>
        </ul>
        <div class="last-activity-chart"></div>
      </div>
    </div>
  </main>
</template>
<script setup lang="ts">
definePageMeta({
  layout: 'main-layout',
});
</script>
<style lang="scss">
.main-page {
  width: 940px;
  height: 100%;
  display: flex;
  flex-direction: column;
  padding: 30px;
  background-color: green;
}
.content-title {
  color: #fb8c00;
}
.content-wrap-box {
  background-color: #fff;
  border-radius: 20px;
  border: none;
  padding: 16px;
  margin: 8px 0;
}

.activity-meal-wrap-box {
  display: flex;
  flex-direction: row;

  .activity-wrap-box,
  .meal-wrap-box {
    flex-direction: column;

    .activity-title-box,
    .meal-title-box {
      display: flex;
      /* flex-wrap: wrap; */
      /* flex-direction: row; */
      .activity-title,
      .meal-title {
        /* justify-self: flex-start; */
        align-content: flex-start;
      }
      .activity-date,
      .meal-date {
        justify-content: center;
        align-items: center;
        justify-self: flex-end;
        align-self: end;
      }
    }
  }
  .activity-wrap-box {
    flex: 3;
    margin-right: 8px;
    .calendar {
      flex: 1 1 200px;
      height: 200px;
      margin-top: 8px;
      border: 1px solid black;
    }
  }
  .meal-wrap-box {
    flex: 9;
    margin-left: 8px;
  }
}

.last-activity-wrap-box {
  display: flex;
  flex-direction: row;
  .last-activity-list {
    flex: 3;
    .activity-title-box {
      display: flex;
      align-items: center;
    }
  }
  .last-activity-chart-box {
    display: flex;
    flex-direction: column;
    flex: 9;
    padding: 8px;
    border: 1px solid black;
    .last-activity-chart-info {
      display: flex;
      flex: 1;
      flex-direction: row;
      justify-content: center;

      .chart-info-item {
        flex: 3;
        justify-content: center;
        align-items: center;
        height: 40px;
        &:nth-child(2) {
          border-right: 2px solid black;
          border-left: 2px solid black;
        }
        /* .chart-info-item-title {
        } */
      }
    }
    .last-activity-chart {
      border: 1px solid black;
      height: 100px;
    }
  }
}
</style>
