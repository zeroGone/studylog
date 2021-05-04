//package io.zerogone.controller.api;
//
//import io.zerogone.model.ErrorResponse;
//import io.zerogone.service.UserImageUpdateService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//public class UserImageUpdateController {
//    private final UserImageUpdateService userImageUpdateService;
//
//    public UserImageUpdateController(UserImageUpdateService userImageUpdateService) {
//        this.userImageUpdateService = userImageUpdateService;
//    }
//
//    @PostMapping(value = "api/user/{id}")
//    public ResponseEntity<Object> handleUpdatingUserImage(
//            @PathVariable int id,
//            @SessionAttribute UserVo userInfo,
//            @RequestPart MultipartFile image) {
//
//        if (id != userInfo.getId()) {
//            return ResponseEntity.badRequest().body(new ErrorResponse
//                    .Builder()
//                    .exception(IllegalAccessException.class)
//                    .cause("다른 유저 정보에 접근할 수 없습니다")
//                    .detail("세션에 저장되있는 현재 유저와 이미지 업데이트할 유저의 아이디가 다릅니다")
//                    .statusCode(HttpStatus.BAD_REQUEST)
//                    .build());
//        }
//
//        return ResponseEntity.ok(userImageUpdateService.updateUserImage(userInfo, image));
//    }
//}