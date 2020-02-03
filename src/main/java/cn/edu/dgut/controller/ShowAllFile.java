package cn.edu.dgut.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.dgut.domain.FileBean;
import cn.edu.dgut.service.ShowAllFileService;
import cn.edu.dgut.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowAllFile {
	@Autowired
	ShowAllFileService showAllFileService;
	@RequestMapping("/ShowAllFile")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showAllFileService.showAllFile(request,response);

	}
}
