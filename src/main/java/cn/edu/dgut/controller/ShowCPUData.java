package cn.edu.dgut.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.dgut.service.ShowCPUDataService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowCPUData{
	@Autowired
	ShowCPUDataService showCPUDataService;
	@RequestMapping("/ShowCPUData")
	public void ShowCPUData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showCPUDataService.showCPUData(request,response);

	}

	

}
