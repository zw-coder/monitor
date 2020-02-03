package cn.edu.dgut.controller;

import cn.edu.dgut.service.RemoteDesktopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class RemoteDesktop  {
    @Autowired
    RemoteDesktopService remoteDesktopService;
    @RequestMapping("/RemoteDestop")
    public void RemoteDestop(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            remoteDesktopService.RemoteDesktop(request,response);
    }
}
