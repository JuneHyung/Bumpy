package com.bump.bumpy.domain.common;

import com.bump.bumpy.database.entity.cm.CmHFile;
import com.bump.bumpy.database.repository.cm.CmHFileRepository;
import com.bump.bumpy.util.dto.ResultMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public String uploadFileInternal(MultipartFile file, String userId) {
        // file null check
        if (file == null) {
            return null;
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
                .userId(userId)
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
        return uuid;
    }

    // downloadFile
//    public ResponseEntity<Resource> downloadFile(String uuid) {
//        // file null check
//        if (uuid == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // create CmHFile entity with uuid
//        CmHFile cmHFile = cmHFileRepository.findById(uuid).orElseThrow(() -> new RuntimeException("file not found"));
//
//        try {
//            Resource resource = new UrlResource(Paths.get(FILE_PATH + uuid).toUri());
//            if (!resource.exists()) {
//                throw new RuntimeException("file not found");
//            }
//
//            InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .header("Content-Disposition", "attachment; filename=\"" + cmHFile.getOriginFileName() + "\"")
//                    .body(inputStreamResource);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
