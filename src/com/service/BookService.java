package com.service;

import com.dao.BookDao;
import com.entity.Book;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :Mr.Xu
 * Date    :2019-4-15
 */
@Service
public class BookService {

    @Resource
    private BookDao bookDao ;

    public List<Book>  queryAll(int index, int pageCount){
        List<Book> bookList = bookDao.queryAll(index, pageCount);
        return bookList;
    }
    //通过id查询书籍
    public  Book  queryOneById(int id ){
        Book book = bookDao.queryOneById(id);

        return book;
    }
    //添加
    public void add(Book book){
        bookDao.add(book);

    }
    public void update(Book book){

        bookDao.update(book);


    }

    public void delete(Book book){
        bookDao.delete(book);
    }
    public int queryPage() {
        int i = bookDao.queryPage();
        return i;
    }





}
