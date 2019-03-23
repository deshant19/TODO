  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

<style type="text/css">
.loader {
  visibility: hidden;
  background-color: rgba(255,255,255,0.7);
  position: absolute;
  z-index: +100 !important;
  width: 100%;
  height:100%;
}

.loader img {
  position: relative;
  top:50%;
  left:50%;
}
</style>
</head>
<body>
<div class="loader" > 
<img src='/ajax-loader.gif' /> 
</div> 
<h1>TODO List</h1>  
<table border="1" width="70%" cellpadding="1">  
<tr><th>Id</th><th>Name</th><th>Amount</th></tr>  
   <c:forEach var="todo" items="${list}"> 
  
   <tr >  
   
   <td class = "id">${todo.id}</td>  
   <td contenteditable="false" class = "name">${todo.name}</td>  
   <td contenteditable="false" class = "amount">${todo.amount}</td>
   <td><button class="editbtn">Edit</button></td>  
   <td><a href="/deletetodo/${todo.id}"><button>Delete</button></a></td>    
   </tr>  
   </c:forEach>  
   </table>  
   <br/>
	
	
	
	<!-- <a href="/viewemp/1">1</a>    -->
   <!-- <a href="/viewemp/2">2</a>   
   <a href="/viewemp/3">3</a>  
   <a href="/viewemp/4">4</a> -->
   
   <br/><a href="/todoform">Add New TODO</a>  
   
   
 <script type="text/javascript">


 
  $(document).ready(function() {
	 
$('.editbtn').click(function() {
	var id = $(this).parent().parent().find('.id').text();
	var name = $(this).parent().parent().find('.name').text();
	var amount = $(this).parent().parent().find('.amount').text();
	if ($(this).html() === 'Edit') {
		$(this).html('Save');
		$(this).parent().parent().find('.name').prop('contenteditable',true);
		$(this).parent().parent().find('.amount').prop('contenteditable',true);
		
	} else {
		$(this).html('Edit');
		$(this).parent().parent().find('.name').prop('contenteditable',false);
		$(this).parent().parent().find('.amount').prop('contenteditable',false);
		
        
		var dataString = 'id=' + id + '&name=' + name + '&amount=' + amount;
		$.ajax({
		     url: "/editsave",
		     type: "post",
		     data: dataString,
		     beforeSend: function() {
		         $('.loader').css("visibility","visible");
		      },
		     success:function(d){setTimeout(function() { alert("Update Completed"); }, 300);},
			 error:function(){alert('Error');},
		     complete: function(){
		         $('.loader').css("visibility","hidden");
		      }
		     });
	}
});
}); 

</script> 


</body>
</html>