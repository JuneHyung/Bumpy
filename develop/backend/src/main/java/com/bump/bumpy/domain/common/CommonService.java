package com.bump.bumpy.domain.common;

import com.bump.bumpy.database.entity.cm.CmHFile;
import com.bump.bumpy.database.repository.cm.CmHFileRepository;
import com.bump.bumpy.util.dto.PictureDto;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final CmHFileRepository cmHFileRepository;

    @Value("${file.upload.path}")
    private String FILE_PATH;

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResultMap> uploadFile(MultipartFile file) {
        // file null check
        if (file == null) {
            return ResponseEntity.badRequest().body(new ResultMap("message", "file is null"));
        }

        // create new random UUID
        String uuid = null;

        // uuid duplication check
        do {
            uuid = UUID.randomUUID().toString() + "." + getExtensionByStringHandling(file.getOriginalFilename()).orElse("");
        } while (cmHFileRepository.existsById(uuid));

        // create CmHFile entity with uuid
        CmHFile cmHFile = CmHFile.builder()
                .fileId(uuid)
                .originFileName(file.getOriginalFilename())
                .userId(getUserId())
                .build();

        // store file in resources/img folder with uuid as filename
        // if folder doesn't exist, create one
        if (!new java.io.File(FILE_PATH).exists()) {
            new java.io.File(FILE_PATH).mkdirs();
        }
        try {
            file.transferTo(new java.io.File(FILE_PATH + uuid));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // save file to database
        cmHFileRepository.save(cmHFile);

        // return fileId
        return ResponseEntity.ok(new ResultMap("fileId", uuid));
    }

    @Transactional(rollbackFor = Exception.class)
    public String uploadBase64ImageInternal(PictureDto pictureDto, String userId) {
        String base64ImageFile = pictureDto.getData();
        String originalFileName = pictureDto.getName();
        Long size = pictureDto.getSize();

        // file null check
        if (base64ImageFile == null) {
            return null;
        }

        // create new random UUID
        String uuid = null;

        // uuid duplication check
        do {
            uuid = UUID.randomUUID().toString() + "." + getExtensionByStringHandling(originalFileName).orElse("");
        } while (cmHFileRepository.existsById(uuid));

        // create CmHFile entity with uuid
        CmHFile cmHFile = CmHFile.builder()
                .fileId(uuid)
                .originFileName(uuid)
                .userId(userId)
                .size(size)
                .build();

        // store file in resources/img folder with uuid as filename
        // if folder doesn't exist, create one
        if (!new java.io.File(FILE_PATH).exists()) {
            new java.io.File(FILE_PATH).mkdirs();
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64ImageFile);
            java.io.File file = new java.io.File(FILE_PATH + uuid);
            java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
            fos.write(decodedBytes);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // save file to database
        cmHFileRepository.save(cmHFile);

        // return fileId
        return uuid;
    }

    public List<Map<String, String>> getFileBase64Internal(List<String> uuidList) {
        List<CmHFile> cmHFileList = cmHFileRepository.findAllById(uuidList);
        List<Map<String, String>> returnList = new ArrayList<>();
        cmHFileList.forEach(cmHFile -> {
            // get file from FILE_PATH and encode to base64
            File file = new File(FILE_PATH + cmHFile.getFileId());
            byte[] fileContent = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(fileContent);
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String encoded = Base64.getEncoder().encodeToString(fileContent);
            int size = (int) file.length();

            returnList.add(Map.of("name", cmHFile.getOriginFileName(), "data", encoded, "size", String.valueOf(size)));
        });

        return returnList;
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    private boolean deleteFileInternal(String fileId) {
        try {
            // delete file from FILE_PATH
            File file = new File(FILE_PATH + fileId);
            if(file.exists()) {
                file.delete();
            }

            // delete file from database
            cmHFileRepository.deleteById(fileId);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


//    @Transactional(rollbackFor = Exception.class)
//    public String uploadFileInternal(MultipartFile file, String userId) {
//        // file null check
//        if (file == null) {
//            return null;
//        }
//
//        // create new random UUID
//        String uuid = null;
//
//        // uuid duplication check
//        do {
//            uuid = UUID.randomUUID().toString() + "." + getExtensionByStringHandling(file.getOriginalFilename()).orElse("");
//        } while (cmHFileRepository.existsById(uuid));
//
//        // create CmHFile entity with uuid
//        CmHFile cmHFile = CmHFile.builder()
//                .fileId(uuid)
//                .originFileName(file.getOriginalFilename())
//                .userId(userId)
//                .build();
//
//        // store file in resources/img folder with uuid as filename
//        // if folder doesn't exist, create one
//        if (!new java.io.File(FILE_PATH).exists()) {
//            new java.io.File(FILE_PATH).mkdirs();
//        }
//        try {
//            file.transferTo(new java.io.File(FILE_PATH + uuid));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // save file to database
//        cmHFileRepository.save(cmHFile);
//
//        // return fileId
//        return uuid;
//    }
}
