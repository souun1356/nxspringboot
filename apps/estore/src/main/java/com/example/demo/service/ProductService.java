package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.dao.ProductDaoImp;
import com.example.demo.domain.PageBean;
import com.example.demo.domain.Product;

public class ProductService {
  ProductDaoImp dao = new ProductDaoImp();

  // 添加商品
  public void add(Product product) throws SQLException {
    dao.addProduct(product);
  }

  // 查詢所有商品信息
  public List<Product> findAll() throws SQLException {
    return dao.findAll();
  }

  // 通過id查找唯一商品
  public Product findById(int id) throws SQLException {
    return dao.findById(id);
  }

  // 修改產品信息
  public void update(Product product) throws SQLException {
    dao.update(product);
  }

  // 按條件查詢
  public List<Product> simpleSelect(String field, String msg) throws SQLException {
    return dao.simpleSelect(field, msg);
  }

  // 分頁查詢
  public PageBean findByPage(int pageNum, int currentPage) {
    PageBean pb = new PageBean();
    try {
      List<Product> pro = dao.findByPage(pageNum, currentPage);

      // 查詢總條數:
      int totalCount = dao.findAllCount();

      // 得到總頁數
      int totalPage = (int) Math.ceil(totalCount * 1.0 / currentPage);

      pb.setTotalCount(totalCount); // 封裝總條數
      pb.setTotalPage(totalPage);// 封裝總頁數
      pb.setPro(pro);// 封裝當前頁數據.
      pb.setCurrentPage(currentPage); // 封裝每頁條數
      pb.setPageNum(pageNum);// 封裝當前頁碼

    } catch (SQLException e) {
      e.getStackTrace();
    }
    return pb;
  }

  // 得到所有頁碼
  public int findAllCount() throws SQLException {
    return dao.findAllCount();
  }

  // 刪除單個數據
  public void delete(int id) throws SQLException {
    dao.delById(id);
  }

  // 刪除選中數據
  public void delSelect(int[] id) throws SQLException {
    dao.delSelect(id);
  }

  // 根據所傳值得到在該分類的商品
  public List<Product> findByCode(String code) throws SQLException {
    return dao.findByCode(code);
  }

  public PageBean findByPageByCode(int pageNum, int currentPage, String code) {
    PageBean pb = new PageBean();
    try {
      List<Product> pro = dao.findByPageByCode(pageNum, currentPage, code);

      // 查詢總條數:
      int totalCount = dao.findCountByCode(code);

      // 得到總頁數
      int totalPage = (int) Math.ceil(totalCount * 1.0 / currentPage);

      pb.setTotalCount(totalCount); // 封裝總條數
      pb.setTotalPage(totalPage);// 封裝總頁數
      pb.setPro(pro);// 封裝當前頁數據.
      pb.setCurrentPage(currentPage); // 封裝每頁條數
      pb.setPageNum(pageNum);// 封裝當前頁碼

    } catch (SQLException e) {
      e.getStackTrace();
    }
    return pb;
  }

}
