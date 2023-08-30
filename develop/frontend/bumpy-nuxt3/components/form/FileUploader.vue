<template>
  <div @dragover.prevent @drop="handleDrop" class="photo-wrap">
    <div v-if="props.list.value.length === 0" class="image-file-list-wrap">
      <p class="cursor-pointer">Drag & Drop Your Image</p>
      <input type="file" multiple accept="image/*" @change="handleFileChange" style="display:none;" />
    </div>
    <div v-else class="image-file-list-wrap">
      <p class="bp-mb-sm" style="font-size:1.04rem; text-align: left;">업로드된 파일 수 : {{ props.list.value.length }} </p>
      <ul class="image-file-list">
        <template v-for="(item, idx) in props.list.value">
          <li>
            <div class="img-box">
              <img :src="`data:image/jpg;base64, ${item.data}`" :alt="item.name" style="width:200px; height:200px;"/>
              <p class="bp-mx-md">{{ item.name }} <span class="bp-mx-sm">{{ formatFileSize(item.size) }}</span> </p>
            </div>
            <CustomIcon iconName="mdiDelete" @click="removeFile(idx)"></CustomIcon>
          </li>
        </template>
      </ul>
    </div>
  </div>
</template>

<script setup>
import CustomIcon from "~~/components/icon/CustomIcon.vue";
import { setErrorMessage, setWarnMessage } from "~~/api/alert/message";

const props = defineProps({
  list: Object,
})

function formatFileSize(bytes) {
  if (bytes < 1024) return bytes + " Byte";
  else if (bytes < 1024 * 1024) return (bytes / 1024).toFixed(2) + " KB";
  else if (bytes < 1024 * 1024 * 1024) return (bytes / (1024 * 1024)).toFixed(2) + " MB";
  else return (bytes / (1024 * 1024 * 1024)).toFixed(2) + " GB";
}

const previewFiles = async (files) =>{
  const readerPromises = [];
  const result = [];
  for(let i=0;i<files.length;i++){
    const file = files[i];
    result.push({name: file.name, size: file.size});

    // readFile
    const reader = new FileReader();

    readerPromises.push(new Promise(async (resolve)=>{
      reader.onload = async (e) =>{
        resolve(e.target.result);
      }
    })
    );
  
    reader.readAsDataURL(file);

    await Promise.all(readerPromises).then((results) => {
      for(let i=0;i<results.length;i++){
        result[i].data = results[i].split(',')[1];
      }
    });
  }

  if(props.list.value.length!==0){
    const sumSize = [...props.list.value, ...result].map(el=>el.size).reduce((a,c)=>a+c, 0);
    if(sumSize>1024*1024){ 
      if((sumSize / (1024 * 1024)).toFixed(2) > 50) setWarnMessage('50MB이하만 등록 가능합니다.');
      else props.list.value = [...props.list.value, ...result]
    }else props.list.value = [...props.list.value, ...result]
  }else{
    const sumSize = [...result].map(el=>el.size).reduce((a,c)=>a+c, 0);
    if(sumSize>1024*1024){ 
      if((sumSize / (1024 * 1024)).toFixed(2) > 50) setWarnMessage('50MB이하만 등록 가능합니다.');
      else props.list.value = [...result]
    }else props.list.value = [...result]
  }
}

const handleFileChange = (event) => {
  const files = event.target.files;
  if(files.length>10) setErrorMessage('10개까지 등록할 수 있습니다.');
  if (files.length > 0 && files.length<=10) {
    if((props.list.value.length + files.length) >10){ 
      event.preventDefault();
      setErrorMessage('10개까지 등록할 수 있습니다.');
    }else{
      previewFiles([...files])
    }
  }
};


const handleDrop = (event) => {
  event.preventDefault();
  const files = event.dataTransfer.files;
  if(files.length>10) setErrorMessage('10개까지 등록할 수 있습니다.');
  if (files.length > 0 && files.length<=10) {
    if((props.list.value.length + files.length) >10){  
      event.preventDefault();
      setErrorMessage('10개까지 등록할 수 있습니다.');
    }else{
      previewFiles([...files])
    }
  }
};

const removeFile = (idx) => {
  props.list.value.splice(idx, 1);
};

</script>