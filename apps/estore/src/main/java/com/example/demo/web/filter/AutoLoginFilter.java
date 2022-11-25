package com.example.demo.web.filter;

/**
 * Servlet implementation class AutoLoginFilter
 */
/* @WebServlet("") */
/*
 * public class AutoLoginFilter extends HttpServlet {
 * private static final long serialVersionUID = 1L;
 *
 * public void init(FilterConfig filterConfig) throws ServletException {
 *
 * }
 *
 * public void doFilter(ServletRequest request, ServletResponse response,
 * FilterChain chain) throws IOException, ServletException {
 * // 處理請求亂碼
 * HttpServletRequest httpServletRequest = (HttpServletRequest) request;
 * HttpServletResponse httpServletResponse = (HttpServletResponse) response;
 *
 * // 處理自動登錄
 * // 1.如果用戶已經登錄不需要自動登錄
 * User user = (User) httpServletRequest.getSession().getAttribute("user");
 * if (user == null) {
 * // 沒有登錄，進行自動登錄
 * // 2.判斷訪問的資源路徑，例如登錄，註冊不需要進行自動登錄
 * String uri = httpServletRequest.getRequestURI();
 * String contextPath = httpServletRequest.getContextPath();
 * String path = uri.substring(contextPath.length());
 * if (!("/regist".equals(path) || "/regist.jsp".equals(path)
 * || "/login.jsp".equals(path) || "/logout".equals(path))) {
 *
 * // 3.得到Cookie
 * Cookie cookie = CookieUtils.findCookieByName(
 * httpServletRequest.getCookies(), "autologin");
 * if (cookie != null) {
 *
 * // 有cookie，進行登錄操作.
 * String username = URLDecoder.decode(cookie.getValue()
 * .split("::")[0], "utf-8");
 * String password = cookie.getValue().split("::")[1];
 *
 * UserService service = new UserService();
 * try {
 * User u = service.login(username, password);
 * if (u != null) {
 * httpServletRequest.getSession().setAttribute(
 * "user", u);// 進行自動登錄.
 * }
 * } catch (javax.security.auth.login.LoginException e) {
 * e.printStackTrace();
 * } catch (ActiveCodeException e) {
 * e.printStackTrace();
 * }
 *
 * }
 *
 * }
 * }
 *
 * // 放行
 * chain.doFilter(httpServletRequest, httpServletResponse);
 * }
 *
 * public void destroy() {
 *
 * }
 */
/* } */
