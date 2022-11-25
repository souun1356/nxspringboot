package com.example.demo.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.example.demo.domain.User;
import com.example.demo.exception.RegistException;
import com.example.demo.service.UserService;
import com.example.demo.utils.ActiveCodeUtils;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* 1.進行驗證碼判斷 START */
        // 驗證服務器端生成的驗證碼與客戶端驗證碼進行校驗，驗證用戶請求是否合法。
        /*
         * String checkCode = request.getParameter("checkcode");
         *
         * String _checkCode = (String) request.getSession().getAttribute(
         * "checkcode_session");
         * request.getSession().removeAttribute("checkcode_session");//從session中刪除。
         *
         * if (!checkCode.equals(_checkCode)) {
         * request.setAttribute("regist.message", "驗證碼不正確");
         * request.getRequestDispatcher("/regist.jsp").forward(request,
         * response);
         * return;
         * }
         */ // ***********驗證碼確認BUG******************
        /* 1.進行驗證碼判斷 END */
        // request.setCharacterEncoding("utf-8");

        /* 2.得到所有請求參數，封裝到User對像中. START */
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        /* 2.得到所有請求參數，封裝到User對像中. END */

        // 手動封裝激活碼
        user.setActivecode(ActiveCodeUtils.getActiveCode());

        /* 3.調用service完成註冊操作. START */
        UserService service = new UserService();
        try {
            service.regist(user);

            // 3.1註冊成功
            response.sendRedirect(request.getContextPath()
                    + "/regist_success.jsp");
            return;
        } catch (RegistException e) {
            // 3.2註冊失敗
            request.setAttribute("regist.message", e.getMessage());
            request.getRequestDispatcher("/error/registuser_error.jsp").forward(request,
                    response);
            return;
        }
        /* 3.調用service完成註冊操作. END */
    }
}
