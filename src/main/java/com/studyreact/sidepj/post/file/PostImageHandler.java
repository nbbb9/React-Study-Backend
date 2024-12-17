package com.studyreact.sidepj.post.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

//실제로 서버안에 이미지를 저장하는 부분을 담당
public class PostImageHandler {

    @Value("/media/post-images/")
    private String ROOT_PATH;

    public String save(MultipartFile image) throws IOException {
        String fileName = getOriginName(image);
        String fullPathName = ROOT_PATH + fileName;

        // 디렉터리가 없으면 생성
        File directory = new File(ROOT_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 저장
        image.transferTo(new File(fullPathName));
        return fullPathName;
    }

    private String getOriginName(MultipartFile image) {
        return image.getOriginalFilename(); // 전달받은 이미지 파일의 이름을 반환
    }

}

//package com.neulgo.app.mes.equips.file;
//
//import com.neulgo.app.base.dto.BaseFileUploadResponse;
//
//import com.neulgo.app.exceptions.CustomException;
//import com.neulgo.app.utils.s3.S3UploadInfo;
//import com.neulgo.app.utils.s3.S3Utils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class EquipFileService {
//
//    public static final String FILE_DIR = "equip/";
//    private final EquipFileRepository equipFileRepository;
//    private final S3Utils s3Utils;
//
//
//    @Value("${naver.fullUrl}")
//    private String fullUrl;
//
//    /**
//     * 파일 업로드
//     * 1. 파일 s3 업로드
//     * 2. 파일 엔티티 저장
//     * 3. 파일 업로드 응답 반환
//     *
//     * @param file 업로드할 파일
//     * @return 업로드 응답
//     */
//    public BaseFileUploadResponse upload(MultipartFile file, Long menuId) {
//        S3UploadInfo s3UploadInfo = s3Utils.uploadToS3(file, FILE_DIR);
//        EquipFile save = equipFileRepository.save(new EquipFile(s3UploadInfo, menuId));
//        return BaseFileUploadResponse.from(save, true);
//    }
//
//    public void deleteFiles(List<Long> deletedFileId) {
//        for (Long fileId : deletedFileId) {
//            EquipFile equipFile = getEquipFileById(fileId);
//            String key = equipFile.getUrl().substring(fullUrl.length());
//            equipFileRepository.delete(equipFile);
//            s3Utils.deleteAsync(key)
//                    .exceptionally(ex -> {
//                        log.error("s3 파일 삭제 실패", ex);
//                        return null;
//                    });
//        }
//    }
//
//    public EquipFile getEquipFileById(Long fileId) {
//        return equipFileRepository.findById(fileId).orElseThrow(() -> new CustomException("존재하지 않는 파일입니다."));
//    }
//
//    public EquipFile getEquipFileByEquipId(Long customerId) {
//        Optional<EquipFile> equipFile = equipFileRepository.findOneByEquipId(customerId);
//        return equipFile.orElse(null);
//    }
//
//    public List<EquipFile> getEquipFilesByEquipId(Long customerId) {
//        return equipFileRepository.findAllByEquipId(customerId);
//    }
//}