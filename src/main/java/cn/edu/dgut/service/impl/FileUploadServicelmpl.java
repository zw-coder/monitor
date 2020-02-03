package cn.edu.dgut.service.impl;

import cn.edu.dgut.service.FileUploadService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FileUploadServicelmpl implements FileUploadService {
    @Override
    public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload fileUpload = new ServletFileUpload(factory);
                List<FileItem> allFileItem = fileUpload.parseRequest(request);
                // 隐藏字段，父目录
                String id = allFileItem.get(0).getString("UTF-8");
                System.out.println(id);
                // 上传文件
                FileItem fi = allFileItem.get(1);

                String fileName = fi.getName();
                System.out.println(fileName);
                fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                FileUtils.copyInputStreamToFile(fi.getInputStream(), new File(id,fileName));
                id =  URLEncoder.encode(id, "utf-8");
                //重定向到
                String url = request.getContextPath() + "ShowAllFile?id=" + id;
                response.sendRedirect(url);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
}
