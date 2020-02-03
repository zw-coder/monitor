package cn.edu.dgut.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileDownLoadService {
    void fileDownLoad(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException;
}
