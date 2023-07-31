<template>
  <div @dragover.prevent @drop="handleDrop" class="photo-wrap">
    <div v-if="fileList.length === 0" class="image-file-list-wrap">
      <p class="cursor-pointer">Drag & Drop Your Image</p>
      <input type="file" multiple accept="image/*" @change="handleFileChange" style="display:none;" />
    </div>
    <div v-else class="image-file-list-wrap">
      <p class="bp-mb-sm" style="font-size:1.04rem; text-align: left;">업로드된 파일 수 : {{ fileList.length }} </p>
      <ul class="image-file-list">
        <template v-for="(item, idx) in fileList">
          <li>
            <div class="img-box">
              <img :src="urlList[idx]" :alt="item.name" style="width:200px; height:200px;"/>
              <p class="bp-mx-md">{{ item.name }} <span class="bp-mx-sm">{{ sizeList[idx] }}</span> </p>
            </div>
            <CustomIcon iconName="mdiDelete" @click="removeFile(idx)"></CustomIcon>
          </li>
        </template>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import CustomIcon from "~~/components/icon/CustomIcon.vue";
import { setErrorMessage } from "~~/api/alert/message";
const fileList = ref([]);
const urlList = ref([]);
const props = defineProps({
  list: Object,
})
const sizeList = ref([]);

function formatFileSize(bytes) {
  if (bytes < 1024) return bytes + " Byte";
  else if (bytes < 1024 * 1024) return (bytes / 1024).toFixed(2) + " KB";
  else if (bytes < 1024 * 1024 * 1024) return (bytes / (1024 * 1024)).toFixed(2) + " MB";
  else return (bytes / (1024 * 1024 * 1024)).toFixed(2) + " GB";
}

const previewFiles = (files) =>{
  const readerPromises = [];
  for(const file of files){
    const reader = new FileReader();
    readerPromises.push(new Promise((resolve)=>{
      reader.onload = (e) =>{
        resolve(e.target.result);
      }
    })
    );

    reader.readAsDataURL(file);
    Promise.all(readerPromises).then((results) => {
      sizeList.value.push(formatFileSize(file.size))
      urlList.value = results;
      // props.list.value = results;
    });
    props.list.value = files;
  }
}

const handleFileChange = (event) => {
  const files = event.target.files;
  if(files.length>10) setErrorMessage('10개까지 등록할 수 있습니다.');
  if (files.length > 0 && files.length<=10) {
    if((fileList.value.length + files.length) >=10){ 
      event.preventDefault();
      setErrorMessage('10개까지 등록할 수 있습니다.');
    }
    fileList.value = [...fileList.value, ...files];
    previewFiles(fileList.value)
  }
};


const handleDrop = (event) => {
  event.preventDefault();
  const files = event.dataTransfer.files;

  if(files.length>10) setErrorMessage('10개까지 등록할 수 있습니다.');
  if (files.length > 0 && files.length<=10) {
    if((fileList.value.length + files.length) >=10){  
      event.preventDefault();
      setErrorMessage('10개까지 등록할 수 있습니다.');
    }
    
    fileList.value = [...fileList.value, ...files];

    previewFiles(fileList.value);
  }
};
const removeFile = (idx) => {
  fileList.value.splice(idx, 1);
  previewFiles(fileList.value)
};

</script>