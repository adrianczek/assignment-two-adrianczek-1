<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">    
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        
        <script type="text/javascript">
            $(document).ready( function () {
                $('#beerTable').DataTable();
            } );
        </script>

        <title>View Beers</title>
    </head>
    <body>
        
        <nav class="navbar navbar-inverse" style="background-color: RGBA(70, 66, 60, 1)">
            <div class="container-fluid">
              <div class="navbar-header">
                <a class="navbar-brand" href="viewBeers">Global Beers</a>
              </div>
              <ul class="nav navbar-nav">
                <li class="active"><a href="viewBeers">Our Beers</a></li>
              </ul>
            </div>
        </nav>
        
        <br><br>
        
        <div class="container">
            
        <h1>Our Beers</h1>
        
        <table class="display" id="beerTable">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>ABV</td>
                    <td>IBU</td>
                    <td>SRM</td>
                    <td>Buy Price</td>
                    <td>Sell Price</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${beerslist}" var="aBeer">
                    <tr>
                        <td><a href="">${aBeer.id}</a></td>
                        <td>${aBeer.name}</td>    
                        <td>${aBeer.abv}</td>  
                        <td>${aBeer.ibu}</td>  
                        <td>${aBeer.srm}</td>  
                        <td>${aBeer.buy_price}</td> 
                        <td>${aBeer.sell_price}</td> 
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
        <div>             
        <br><br>
    </body>
</html>
