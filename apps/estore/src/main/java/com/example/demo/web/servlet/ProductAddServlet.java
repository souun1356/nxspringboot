package com.example.demo.web.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.utils.UploadPic;

/**
 * Servlet implementation class Text
 */
@WebServlet("/ProductAddServlet")
public class ProductAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ProductAddServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Map<String, String[]> map = new HashMap<String, String[]>();// 用於封裝所有請求參數

    DiskFileItemFactory factory = new DiskFileItemFactory();
    // 創建一個文件上傳解析器
    ServletFileUpload upload = new ServletFileUpload(factory);
    // 解決上傳文件名的中文亂碼
    upload.setHeaderEncoding("UTF-8");
    // 判斷提交上來的數據是否是上傳表單的數據
    if (!ServletFileUpload.isMultipartContent(request)) {
      return;
    }
    try {
      List<FileItem> list = upload.parseRequest(request);

      for (FileItem item : list) {
        if (item.isFormField()) {
          map.put(item.getFieldName(),
              new String[] { item.getString("utf-8") }); // 封裝其它數據

        } else {
          String filename = item.getName();
          if (filename == null || filename.trim().equals("")) {
            continue;
          }
          filename = filename.substring(filename.lastIndexOf("\\") + 1);
          UploadPic.savePic(item, filename);

          map.put("imgurl", new String[] { filename });
          File directory = new File("");// 參數為空
          String proRootPath = directory.getCanonicalPath();
          System.out.println(proRootPath);
        }

      }
      // 將所得信息賦給Product對像
      Product product = new Product();
      // 調用service中添加方法
      ProductService service = new ProductService();

      try {
        BeanUtils.populate(product, map);
        System.out.println(product.getImgurl());

        service.add(product);

        request.getRequestDispatcher("/ProductFindAllServlet").forward(request, response);

        return;
      } catch (SQLException e) {
        e.printStackTrace();
        request.getSession().setAttribute("add.message", e.getMessage());
        request.getRequestDispatcher("/add_product.jsp").forward(request,
            response);
        return;

      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }

    } catch (FileUploadException e) {
      e.printStackTrace();
    }

  }

  // private boolean isPicture(String fileName) {
  // String[] exts = { "jpg", "bmp", "png", "jpeg", "gif" };
  // String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
  // fileName.length());
  // for (int i = 0; i < exts.length; i++) {
  // if (ext.toLowerCase().equals(exts[i])) {
  // return true;
  // }
  // }
  // return false;
  // }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
