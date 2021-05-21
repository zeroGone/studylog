package io.zerogone.user.service;

import io.zerogone.common.fileupload.AwsUploader;
import io.zerogone.common.fileupload.Image;
import io.zerogone.common.fileupload.ImageUrl;
import io.zerogone.user.UserDao;
import io.zerogone.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class UserImageUpdateService {
    private static final String USER_UPLOAD_PATH = "img/user";
    private final AwsUploader awsUploader;
    private final UserDao userDao;

    public UserImageUpdateService(AwsUploader awsUploader, UserDao userDao) {
        this.awsUploader = awsUploader;
        this.userDao = userDao;
    }

    @Transactional
    public ImageUrl updateUserImage(int id, MultipartFile image) {
        ImageUrl imageUrl = awsUploader.upload(new Image(USER_UPLOAD_PATH, image));

        User user = userDao.findById(id);
        user.setImageUrl(imageUrl.getValue());

        return imageUrl;
    }
}
