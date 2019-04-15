package com.dao.DaoImpl;

import com.dao.BookDao;
import com.entity.Book;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :Mr.Xu
 * Date    :2019-4-15
 */
@Repository
public class BookDaoImpl implements BookDao {

    private Session session=null;

    private void doBefore(){

        session = HibernateUtil.getSession();
        session.beginTransaction();

    }

    private   void doAfter(){

        session.getTransaction().commit();
        session.close();
    }
    //查询所有
    public List<Book>  queryAll(int index, int pageCount){
        doBefore();
        String hql ="from Book  ";
        Query<Book> query = session.createQuery(hql, Book.class);
        int start=(index-1)*pageCount;
        int end=pageCount;
        query.setFirstResult(start);
        query.setMaxResults(end);
        List<Book> list = query.list();
        doAfter();
        return list;
    }
    //通过id查询书籍
    public  Book  queryOneById(int id ){
        doBefore();
        String hql ="from Book where id= :id ";
        Query<Book> query = session.createQuery(hql, Book.class);
        query.setParameter("id",id);
        Book book = query.list().get(0);
        doAfter();

        return book;
    }
    //添加
    public void add(Book book){
        doBefore();
        session.save(book);
        doAfter();

    }
    public void update(Book book){
        doBefore();
        session.update(book);
        doAfter();

    }

    public void delete(Book book){
        doBefore();
        session.delete(book);
        doAfter();

    }
    public int queryPage() {
        doBefore();
        String hql ="select count(*) from Book  ";
        Query<Object> query = session.createQuery(hql, Object.class);
        Object object = query.uniqueResult();
        Number number = (Number)object;
        int i = number.intValue();
        doAfter();
        return i;
    }


}
