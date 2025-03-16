package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {

    private final ServletContext servletContext; // Để lấy đường dẫn thực tế của thư mục trong ứng dụng web.

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;// DI
    }

    public String handleSaveUploadFile(MultipartFile file, String targetFolder) {

        if (file.isEmpty())
            return "";

        String rootPath = this.servletContext.getRealPath("/resources/images");
        // this.servletContext.getRealPath(): Lấy ra đường link tới nơi lưu file,
        // getRealPath lấy ra folder webapp

        String fileName = "";

        // Save File
        try {
            byte[] bytes = file.getBytes(); // Lấy ra hình ảnh dưới dạng byte

            File dir = new File(rootPath + File.separator + targetFolder);// Trỏ tới targetFolder, File.separator: /
            if (!dir.exists())
                dir.mkdirs();// Tạo mới nếu folder avatar chưa tồn tại

            // Create the file on server
            fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator +
                    +System.currentTimeMillis() + "-" + file.getOriginalFilename());
            // dir.getAbsolutePath(): Lấy ra đường link dẫn đến folder
            // File.separator: /
            // System.currentTimeMillis(): Lấy ra millis second ở thời điểm hiện tại
            // avatarFile.getOriginalFilename(): Lấy ra tên file trên máy

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            // new FileOutputStream(serverFile): Tạo một luồng đầu ra để ghi dữ liệu vào
            // file trên server.
            // BufferedOutputStream: Bao bọc FileOutputStream để tăng hiệu suất ghi (bằng
            // cách giảm số lần gọi trực tiếp tới hệ thống tệp).

            stream.write(bytes); // Ghi mảng byte (nội dung file) vào file trên server.
            stream.close();// Đóng luồng để giải phóng tài nguyên.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public boolean handleDeleteFile(String fileName, String targetFolder) {
        // Lấy đường dẫn thư mục chứa ảnh
        String rootPath = this.servletContext.getRealPath("/resources/images");
        File file = new File(rootPath + File.separator + targetFolder + File.separator + fileName);

        // Kiểm tra nếu file tồn tại thì xóa
        if (file.exists()) {
            return file.delete(); // Trả về true nếu xóa thành công, false nếu thất bại
        }
        return false;
    }
}
