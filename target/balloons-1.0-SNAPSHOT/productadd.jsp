<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.acme.balloons.Beans.Product"%>
<%@page import="com.acme.balloons.Dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String errmsg = "";
    String productname = "";
    String price = "";
    String productDescription = "";
    String categoryId = "";

    if ("GET".equalsIgnoreCase(request.getMethod())) {
    } else {
        productname = request.getParameter("productname");
        price = request.getParameter("price");
        productDescription = request.getParameter("productDescription");
        categoryId = request.getParameter("categoryId");

        if(productname == null || productname.isEmpty()){
            errmsg = "Product name can't be empty!";
        }else if(price == null || price.isEmpty()){
            errmsg = "Price can't be empty!";
        }else if( productDescription == null || productDescription.isEmpty()){
            errmsg = "Product Description can't be empty!";
        }else if(categoryId == null || categoryId.isEmpty()){
            errmsg = "Category Id can't be empty!";
        }   

        double dprice = 0.0;
        int iCategoryId = 0;
        
        if (errmsg.isEmpty()) {
            try {
                dprice = Double.parseDouble(price);
                iCategoryId = Integer.parseInt(categoryId);
                Product product = new Product();
                product.setProductname(productname);
                product.setPrice(dprice);
                product.setProductDescription(productDescription);
                product.setCategoryId(iCategoryId);
                // create
                ProductDao.insert(product);
                response.sendRedirect("productlist.jsp");
            } catch (NumberFormatException nfe) {
                errmsg = "Price must be number!";
            }
        }
    }
    pageContext.setAttribute("errmsg", errmsg);
    pageContext.setAttribute("productname", productname);
    pageContext.setAttribute("price", price);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Product</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
  <h2>Add New Product</h2>
  <h3 style='color:red'>${errmsg}</h3>
  <form class="form-horizontal" action="/balloons/productadd.jsp" method="Post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Product Name:</label>
      <div class="col-sm-10">
        <input class="form-control" id="productname" placeholder="Enter product name" name="productname" value="${productname}">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Price:</label>
      <div class="col-sm-10">
        <input class="form-control" id="price" placeholder="Enter price" name="price" value="${price}">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Product Description:</label>
      <div class="col-sm-10">
        <input class="form-control" id="productDescription" placeholder="Enter Product Description" name="productDescription" value="${productDescription}">
      </div>
    </div>    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Category Id:</label>
      <div class="col-sm-10">
        <input class="form-control" id="categoryId" placeholder="Enter Category Id" name="categoryId" value="${categoryId}">
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
    </div>
  </form>
</div>
</body>
</html>