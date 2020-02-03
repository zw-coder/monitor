package cn.edu.dgut.controller;

import cn.edu.dgut.domain.User;
import cn.edu.dgut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService  userService;
    /**
     *跳转到登录界面
     */
    @RequestMapping(value = "/tologin",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpSession session){
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = userService.findUserByName(username);
        if(user1!=null) {
            if (user1.getPassword().equals(password)) {
                session.setAttribute("User", user1);
                return "main";
            }
        }
            session.setAttribute("msg", "用户错误，重新登录");
            return "login";
    }

    /**
     * 跳转用户主界面
     */
    @RequestMapping("/tomain")
    public String tomain() {
        return "main";
    }

    /**
     * 跳转注册界面
     */
    @RequestMapping("/toregister")
    public String toregsiter()
    {
        return "register";
    }

    /**
     * 注册
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/register")
    public String register(User user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username);
        System.out.println(password);
        User user1 = userService.findUserByName(username);
        if(user1==null) {
            int row = userService.addUser(user);
            if (row > 0) {
                session.setAttribute("User", user);
                return "main";
            }
        }
            return "register";
    }

    /**
     * 退出
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpSession session)
    {
        //清除session
        //request.getSession().removeAttribute("User");
        session.removeAttribute("User");
        //重新登陆
        return "login";
    }

    /**
     * 查找账号
     * @param user
     * @param response
     * @throws IOException
     */
    @RequestMapping("/checkName")
    public void checkName(User user, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String flag="0";
        if(userService.findUserByName(username)!=null){
            flag="1";
        }
        System.out.println(flag);
       response.getWriter().write(flag);
    }

    /**
     * 查找用户
     * @param user
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/checkUser")
    public void checkUser(User user, HttpServletResponse response,HttpSession session) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println(username);
        System.out.println(password);
        User user1 = userService.findUserByName(username);
        String flag="0";
        //用户不存在
        if(user1==null){
            flag="1";
        }
        //密码不正确
        else if(user1.getUsername().equals(username)&&!user1.getPassword().equals(password)){
            flag="2";
        }
        //全部正确
        else{
            flag="3";
            session.setAttribute("User", user1);
        }
        response.getWriter().write(flag);
    }
}
