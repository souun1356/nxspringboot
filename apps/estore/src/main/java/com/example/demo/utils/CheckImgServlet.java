package com.example.demo.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckImgServlet
 */
@WebServlet("/checkImg")
/**
 * 驗證碼生成程序
 *
 *
 *
 */
public class CheckImgServlet extends HttpServlet {

  // 集合中保存所有成語
  private List<String> words = new ArrayList<String>();

  @Override
  public void init() throws ServletException {
    // 初始化階段，讀取new_words.txt
    // web工程中讀取 文件，必須使用絕對磁盤路徑
    String path = getServletContext().getRealPath("/WEB-INF/new_words.txt");
    try {
      BufferedReader reader = new BufferedReader(new FileReader(path));
      String line;
      while ((line = reader.readLine()) != null) {
        words.add(line);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 禁止緩存
    // response.setHeader("Cache-Control", "no-cache");
    // response.setHeader("Pragma", "no-cache");
    // response.setDateHeader("Expires", -1);

    int width = 120;
    int height = 30;

    // 步驟一 繪製一張內存中圖片
    BufferedImage bufferedImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);

    // 步驟二 圖片繪製背景顏色 ---通過繪圖對像
    Graphics graphics = bufferedImage.getGraphics();// 得到畫圖對像 --- 畫筆
    // 繪製任何圖形之前 都必須指定一個顏色
    graphics.setColor(getRandColor(200, 250));
    graphics.fillRect(0, 0, width, height);

    // 步驟三 繪製邊框
    graphics.setColor(Color.WHITE);
    graphics.drawRect(0, 0, width - 1, height - 1);

    // 步驟四 四個隨機數字
    Graphics2D graphics2d = (Graphics2D) graphics;
    // 設置輸出字體
    graphics2d.setFont(new Font("細明體", Font.BOLD, 18));

    Random random = new Random();// 生成隨機數
    int index = random.nextInt(words.size());
    String word = words.get(index);// 獲得成語

    // 定義x坐標
    int x = 10;
    for (int i = 0; i < word.length(); i++) {
      // 隨機顏色
      graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
          .nextInt(110), 20 + random.nextInt(110)));
      // 旋轉 -30 --- 30度
      int jiaodu = random.nextInt(60) - 30;
      // 換算弧度
      double theta = jiaodu * Math.PI / 180;

      // 獲得字母數字
      char c = word.charAt(i);

      // 將c 輸出到圖片
      graphics2d.rotate(theta, x, 20);
      graphics2d.drawString(String.valueOf(c), x, 20);
      graphics2d.rotate(-theta, x, 20);
      x += 30;
    }

    // 將驗證碼內容保存session
    request.getSession().setAttribute("checkcode_session", word);

    // 步驟五 繪製干擾線
    graphics.setColor(getRandColor(160, 200));
    int x1;
    int x2;
    int y1;
    int y2;
    for (int i = 0; i < 30; i++) {
      x1 = random.nextInt(width);
      x2 = random.nextInt(12);
      y1 = random.nextInt(height);
      y2 = random.nextInt(12);
      graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
    }

    // 將上面圖片輸出到瀏覽器 ImageIO
    graphics.dispose();// 釋放資源
    ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  /**
   * 取其某一範圍的color
   *
   * @param fc
   *           int 範圍參數1
   * @param bc
   *           int 範圍參數2
   * @return Color
   */
  private Color getRandColor(int fc, int bc) {
    // 取其隨機顏色
    Random random = new Random();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }

}
