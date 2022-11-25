package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.domain.Product;

public interface ProductDao {
  // 商品添加
  public void addProduct(Product product) throws SQLException;

  // 刪除單個商品信息
  public int delById(int id) throws SQLException;

  // 批量刪除勾選的客戶信息
  public void delSelect(int[] id) throws SQLException;

  // 查詢所有商品信息
  public List<Product> findAll() throws SQLException;

  // 通過id查找唯一商品 編輯商品信息

  public Product findById(int id) throws SQLException;

  public void update(Product p) throws SQLException;

  // 按條件查詢
  public List<Product> simpleSelect(String field, String msg) throws SQLException;

  // 分頁查詢
  public List<Product> findByPage(int pageNum, int currentPage) throws SQLException;

  // 得到所有頁碼
  public int findAllCount() throws SQLException;

  // 得到所屬分類的頁碼
  public int findCountByCode(String code) throws SQLException;

  // 根據所傳值得到在該分類的商品
  public List<Product> findByCode(String code) throws SQLException;

}
