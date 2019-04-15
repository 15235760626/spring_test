package com.dao.Servlet;

import com.entity.Book;
import com.service.BookService;
import com.util.StringUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/BookServlet" )
public class BookServlet extends HttpServlet {
    ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("annotation.xml");
    BookService service = ac.getBean("bookService", BookService.class);

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");

//        System.out.println(action);

        if ("query".equals(action)){
            query(request,response);
        } else if ("add".equals(action)){
            add(request,response);
        }else if ("delete".equals(action)){
            delete(request,response);
        }else if ("update".equals(action)){
            update(request,response);
        }else if ("queryOne".equals(action))
            queryOne(request,response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String index = request.getParameter("index");
        if(index==null){
            index="1";
        }
        int pageCount=3;
        List<Book> all = service.queryAll(Integer.parseInt(index),pageCount);
        int count = service.queryPage();
        int pages=(count+pageCount-1)/pageCount;
        request.setAttribute("end",pages);
        request.setAttribute("all",all);
        request.getRequestDispatcher("/query.jsp").forward(request,response);
    }





    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("name");
        Book book = new Book(bookName);
        service.add(book);
        request.getRequestDispatcher("/success.jsp").forward(request,response);


    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int bookId=-1;
        if(!StringUtil.isEmpty(id)){
            bookId=Integer.parseInt(id);
        }
        Book book = service.queryOneById(Integer.parseInt(id));
        service.delete(book);
        request.getRequestDispatcher("/success.jsp").forward(request,response);

    }

    //修改
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("id");

        String bookName = request.getParameter("name");

        Book book = new Book(Integer.parseInt(bookId),bookName);
        service.update(book);
        request.getRequestDispatcher("/success.jsp").forward(request,response);

    }
    protected void queryOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Book book = service.queryOneById(Integer.parseInt(id));

        request.setAttribute("book",book);
        request.getRequestDispatcher("/queryOne.jsp").forward(request,response);


    }
}
