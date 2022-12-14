package com.Nixagh.javaspringmongodb.Helpers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class UploadImage {
  public static String upload(MultipartFile file) throws IOException {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dmvd8i5u8",
            "api_key", "724185188444158",
            "api_secret", "SRuq9ojEhtIGD6c4dZaR1lU0EGU"));

    String pathName = "D:\\Cai dat\\!intel\\Save\\java-spring-apps-main\\src\\main\\resources\\static\\assets\\";
    String randomGuid = UUID.randomUUID().toString().replace("-", "");

    String fileName = randomGuid + file.getOriginalFilename();
    File f = new File(pathName + fileName);
    file.transferTo(f);

    var result = cloudinary.uploader().upload(f ,
            ObjectUtils.asMap("public_id", fileName));
    boolean r = f.delete();
    return (String) result.get("url");
  }

  public static void deleteImage(String publicId) {
//    try {
//      Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//              "cloud_name", "dmvd8i5u8",
//              "api_key", "724185188444158",
//              "api_secret", "SRuq9ojEhtIGD6c4dZaR1lU0EGU"));
//
//      cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    this is delete image form cloudinary by publicId
  }
}
