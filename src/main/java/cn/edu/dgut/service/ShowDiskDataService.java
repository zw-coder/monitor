package cn.edu.dgut.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ShowDiskDataService {
    void ShowDiskData(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
