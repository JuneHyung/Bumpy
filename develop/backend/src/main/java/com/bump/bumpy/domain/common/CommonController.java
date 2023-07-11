package com.bump.bumpy.domain.common;

import com.bump.bumpy.domain.main.MainService;
import com.bump.bumpy.domain.main.dto.ChartRequestDto;
import com.bump.bumpy.domain.screen.dto.SearchMonthRequestDto;
import com.bump.bumpy.util.dto.ResultMap;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.bump.bumpy.util.funtion.FieldValueUtil.getUserId;

@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

    @Operation(summary = "파일 업로드", description = "")
    @PostMapping("/file/upload")
    public ResponseEntity<ResultMap> uploadFile(MultipartFile file) {
        return commonService.uploadFile(file);
    }

    @Operation(summary = "파일 업로드", description = "")
    @GetMapping("/file/download")
    public ResponseEntity<ResultMap> downloadFile(String fileId) {
        return commonService.downloadFile(fileId);
    }


}
