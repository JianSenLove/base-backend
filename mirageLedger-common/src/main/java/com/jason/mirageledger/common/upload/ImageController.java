package com.jason.mirageledger.common.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/mirageLedger/image")
public class ImageController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("id") String id) throws IOException {
        if (image.isEmpty()) {
            return "The file is empty.";
        }

        // 确保目录存在
        Path dirPath = Paths.get(uploadDir);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        // 使用ID作为文件名，并强制保存为.jpg格式
        String fileName = id + ".jpg";
        Path path = Paths.get(uploadDir + fileName);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return "图片上传成功:" + id;
    }

    @GetMapping("/view/{imageName:.+}")
    public byte[] viewImage(@PathVariable String imageName) throws IOException {
        Path path = Paths.get(uploadDir + imageName);
        return Files.readAllBytes(path);
    }
}
