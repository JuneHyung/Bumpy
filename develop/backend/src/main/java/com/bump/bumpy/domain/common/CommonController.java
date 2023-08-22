package com.bump.bumpy.domain.common;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

//    @Operation(summary = "파일 업로드", description = "")
//    @PostMapping("/file/upload")
//    public ResponseEntity<ResultMap> uploadFile(MultipartFile file) {
//        return commonService.uploadFile(file);
//    }

}
