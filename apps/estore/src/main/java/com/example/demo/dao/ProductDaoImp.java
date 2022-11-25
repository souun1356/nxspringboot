package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.example.demo.domain.Product;
import com.example.demo.utils.JdbcUtils;

@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class ProductDaoImp implements ProductDao {
  // 商品添加
  public void addProduct(Product product) throws SQLException {
    String sql = "insert into products values(null,?,?,?,?,?,?,?)";

    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());

    runner.update(sql, product.getName(), product.getPrice(), product.getPnum(),
        product.getC3code(), product.getImgurl(), product.getDescription(), product.getColor());
  }

  // 查詢所有商品信息
  public List<Product> findAll() throws SQLException {
    String sql = "select * from products ";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanListHandler<Product>(Product.class));
  }

  // 通過id查找唯一商品
  public Product findById(int id) throws SQLException {
    String sql = "select * from products where id=?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanHandler<Product>(Product.class), id);
  }

  // 編輯商品信息
  public void update(Product p) throws SQLException {
    String sql = "update products set name=?,price=?,pnum=?,c3code=?,"
        + "imgurl=?,description=?,color=? where id=?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    runner.update(sql, p.getName(), p.getPrice(), p.getPnum(), p.getC3code(),
        p.getImgurl(), p.getDescription(), p.getColor(), p.getId());
  }

  // 按條件查詢
  /* field為字段名稱、msg為為字段值 */
  public List<Product> simpleSelect(String field, String msg) throws SQLException {
    String sql = "select * from products where " + field + " like ?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanListHandler<Product>(Product.class), "%" + msg + "%");
  }

  // 分頁查詢
  /* pageNum為頁碼，currentPage為每頁條數 */
  public List<Product> findByPage(int pageNum, int currentPage) throws SQLException {
    String sql = "select * from products limit ?,?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanListHandler<Product>(Product.class),
        (pageNum - 1) * currentPage, currentPage);
  }

  // 得到所有頁碼
  public int findAllCount() throws SQLException {
    String sql = "select count(*) from products";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    long count = (long) runner.query(sql, new ScalarHandler());
    return (int) count;
  }

  // 得到所屬分類的頁碼
  public int findCountByCode(String code) throws SQLException {
    String sql = "select count(*) from products where substring(c3code,1," + code.length() + ")" + "=?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    long count = (long) runner.query(sql, new ScalarHandler(), code);
    return (int) count;
  }

  // 刪除單個商品信息
  public int delById(int id) throws SQLException {
    String sql = "delete from products where id=?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    int i = runner.update(sql, id);
    return i;
  }

  // 批量刪除商品信息
  public void delSelect(int[] id) throws SQLException {
    String sql = "delete from products where id=?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    Object[][] ids = new Object[id.length][1];
    for (int i = 0; i < id.length; i++) {
      ids[i][0] = id[i];// 一維數組變成 二維數組batch方法的執行
    }

    runner.batch(sql, ids);
  }

  // 根據所傳值得到在該分類的商品
  public List<Product> findByCode(String code) throws SQLException {
    String sql = "select * from products where substring(c3code,1," + code.length() + ")" + "=?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanListHandler<Product>(Product.class), code);
  }

  public List<Product> findByPageByCode(int pageNum, int currentPage, String code) throws SQLException {
    String sql = "select * from products where substring(c3code,1," + code.length() + ")" + "=? limit ?,?";
    QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
    return runner.query(sql, new BeanListHandler<Product>(Product.class), code,
        (pageNum - 1) * currentPage, currentPage);
  }

}
