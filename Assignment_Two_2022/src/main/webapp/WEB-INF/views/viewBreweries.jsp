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
                $('#breweryTable').DataTable();
            } );
        </script>

        <title>View Breweries</title>
    </head>
    <body>
        
        <nav class="navbar navbar-inverse" style="background-color: RGBA(70, 66, 60, 1)">
            <div class="container-fluid">
              <div class="navbar-header">
                <a class="navbar-brand" href="beers">Global Beers</a>
              </div>
              <ul class="nav navbar-nav">
                <li><a href="beers">Our Beers</a></li>
                <li class="active"><a href="breweries">Our Breweries</a></li>
              </ul>
            </div>
        </nav>
        
        <br><br>
        
        <div class="container">
            
        <h1>Our Breweries</h1>
        
        <table class="display" id="breweryTable">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Address 1</td>
                    <td>City</td>
                    <td>State</td>
                    <td>Country</td>
                    <td>Phone</td>
                    <td>Website</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${brewerieslist}" var="aBrewery">
                    <tr>
                        <td>${aBrewery.id}</td>
                        <td>${aBrewery.name}</td>    
                        <td>${aBrewery.address1}</td>   
                        <td>${aBrewery.city}</td>  
                        <td>${aBrewery.state}</td> 
                        <td>${aBrewery.country}</td> 
                        <td>${aBrewery.phone}</td>  
                        <td>${aBrewery.website}</td> 
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
        <div>             
        <br><br>
    </body>
</html>
