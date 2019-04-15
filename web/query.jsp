<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/14
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的书店</title>
</head>
<body>
    <table border="1px" >
        <a href="add.jsp">嫌书太少_添加一本!!</a>

        <tr>
            <td>序号</td>
            <td>书名</td>
            <td>操作</td>
        </tr>

       <c:forEach var="all" items="${all}">
           <tr>
               <td>${all.id}</td>
               <td>${all.name}</td>


               <td>
                   <a href="BookServlet?action=delete&id=${all.id}">删除</a>
                   <a href="BookServlet?action=queryOne&id=${all.id}">修改</a>
               </td>
           </tr>
       </c:forEach>
    </table>
    <c:forEach var="i" begin="1" step="1" end="${end}">
        <a href="/BookServlet?action=query&index=${i}">【${i}】</a>
    </c:forEach><br/>

</body>
</html>
