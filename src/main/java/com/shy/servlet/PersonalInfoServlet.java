package com.shy.servlet;

import com.shy.pojo.User;
import com.shy.service.UserService;
import com.shy.utils.ConString;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "PersonalInfoServlet", urlPatterns = "/personal" + "/upload")
@MultipartConfig()
public class PersonalInfoServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        User user = new User(
                req.getParameter("username"),
                req.getParameter("nickname"),
                req.getParameter("sex"),
                req.getParameter("cellphone"),
                req.getParameter("email"),
                req.getParameter("remarks"));
        Part part = req.getPart("avatar");
        if (part.getSize() > 0) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            //获取文件名
            String fileName = part.getSubmittedFileName();
            System.out.println("fileName"+fileName);

            String[] fileNames = fileName.split("\\.");
            System.out.println("fileNames"+fileNames.toString());

            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid"+uuid);

            String file = uuid + "." + fileNames[fileNames.length - 1];
            System.out.println("file"+file);

            //把文件写到指定路径
            part.write(ConString.HEADER_FILE_DIR + file);
            System.out.println(ConString.HEADER_FILE_DIR + file+"");

            user.setHeader("/header/" + file);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        String message = userService.uploadUserInfo(user, req.getSession());

//        resp.getWriter().print("<script>location.reload()</script>");
        
        //转向会原页面，刷新页面
        req.setAttribute("fresh", true);
        req.getRequestDispatcher("/personalInfo.jsp?message=" + message).forward(req, resp);
    }
}
