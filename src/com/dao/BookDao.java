package com.dao;

import com.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :Mr.Xu
 * Date    :2019-4-9
 */
public interface BookDao {

    //查询所有
    public List<Book>  queryAll(int index, int pageCount);

    //通过id查询书籍
    public  Book  queryOneById(int id );
    //添加
    public void add(Book book);

    public void update(Book book);

    public void delete(Book book);

    public int queryPage();


}
