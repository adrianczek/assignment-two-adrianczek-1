<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">    
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <c:forEach items="${beerslist}" var="aBeer">
        <title>Drill Down ${aBeer.name}</title>
    </head>
    <body>   
    <nav class="navbar navbar-inverse" style="background-color: RGBA(70, 66, 60, 1)">
        <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="viewBeers">Global Beers</a>
        /div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="viewBeers">Our Beers</a></li>
        </ul>
        </div>
    </nav>
        
    <div class="container">
        <br>
          <a href="viewBeers" class="btn btn-info btn-lg">
          <span class="glyphicon glyphicon-menu-left"></span> Back
          </a>
        <br>
        
        <h1>More Details</h1>
        
        <table  width='1200' >
            <tr><td width='150'><strong>Name: </strong></td><td>${aBeer.id}</td></TR>
            <tr><td width='150'><strong>Description: </strong></td><td>${aBeer.id}</td></TR>
            <tr><td width='150'><strong>Brewery Name: </strong></td><td>${aBeer.name}</td></TR>     
        </table>
        </c:forEach> 
    <div>
    </body>
</html>
