package cn.edu.dgut.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.dgut.service.ShowDiskDirService;
import net.sf.json.JSONArray;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowDiskDir {
	@Autowired
	ShowDiskDirService showDiskDirService;

	@RequestMapping("/ShowDiskDir")
	public void ShowDiskDir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showDiskDirService.showDiskDir(request,response);

	}
}
