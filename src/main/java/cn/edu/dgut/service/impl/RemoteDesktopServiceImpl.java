package cn.edu.dgut.service.impl;

import cn.edu.dgut.service.RemoteDesktopService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class RemoteDesktopServiceImpl implements RemoteDesktopService {
    @Override
    public void RemoteDesktop(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        response.setHeader("refresh", "1");

        BufferedImage bi = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(bi, "jpeg", response.getOutputStream());
    }


}
