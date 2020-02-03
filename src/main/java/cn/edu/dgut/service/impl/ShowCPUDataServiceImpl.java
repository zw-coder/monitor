package cn.edu.dgut.service.impl;

import cn.edu.dgut.service.ShowAllFileService;
import cn.edu.dgut.service.ShowCPUDataService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ShowCPUDataServiceImpl implements ShowCPUDataService {
    @Override
    public void showCPUData(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        List<Map<String, Object>> cpuData = (List<Map<String, Object>>) request.getSession().getServletContext().getAttribute("cpuData");

        String jsonData = JSONArray.fromObject(cpuData).toString();

        response.getWriter().print(jsonData);

    }
}
