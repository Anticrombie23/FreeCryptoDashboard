<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Free Crypto Dashboard</title>
</head>
<body>


	<%@include file="Header.jsp"%>
<hr>

	<script type="text/javascript">
    localStorage.setItem('crypto1','${crypto1}'); //Where param user is your request parameter from previous jsp.
</script>

<script type="text/javascript">
    localStorage.getItem('crypto1');
</script>
	

	<h3 align="center">
		<span style ="color:gold"> BTC </span> is currently trading @:
		<c:out value="${cryptomap['BTC']}" />
		USD
	</h3>
	<h3 align="center">
		<span style = "color:gray">ETH </span> is currently trading @:
		<c:out value="${cryptomap['ETH']}" />
		USD
	</h3>
	<h3 align="center">
		<span style = "color:green"> LTC </span> is currently trading @:
		<c:out value="${cryptomap['LTC']}" />
		USD
	</h3>
	 <br>

<!--  	<form:form method="post" action = "cryptoselection">
		<div align = "center" >
			 <h3> How many different coins are in your portfolio?:</h3>
			Number coins: <select name="numberCoins">
				<option value ="1">1</option>
				<option value ="2">2</option>
				<option value ="3">3</option>
				<option value ="4">4</option>
				<option value ="5">5</option>
				<option value ="6">6</option>
				<option value ="7">7</option>
				<option value ="8">8</option>
				<option value ="9">9</option>
				<option value ="10">10</option>
			</select>
		
		</div>
		
	</form:form> -->
	
	<br>
	<br>
	

	<form:form method="post" action="cryptodashboard">
	
	<div class = "container" style="border:1px solid black" align="center">
	
		<div class="panel-group"> 
	
		<br>
		
				
		<div align="center" style="border: 1px solid black;margin:auto" class="well">		
			Select first currency: <select name="crypto1">
				<option value="<c:out value="${crypto1}" />"><c:out value="${crypto1}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>
			</select>			
			<br><br>Total Holdings: <input type="text" name="currency1Value" value=<c:out value="${currency1Value}" />>
			<input type="text" name="currency1total" readonly="readonly" disabled
				value=$<c:out value="${currency1total}"/> /> <label style="text-decoration:bold"> <c:choose>
	<c:when test="${empty coin1Value}"> </c:when>
	<c:otherwise>@ $${coin1Value} </c:otherwise>
</c:choose></label>
		</div>
		
		<br><br>
		

		
		<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#crypto2collapse" >Add Second Crypto</button>
		

		<div id="crypto2collapse" class="${empty coin2Value ? 'collapse' : 'collapse in' }" > 
		
		<div align="center" style="border: 1px solid black;margin:auto" class="well">
		Select second currency:	<select name="crypto2">
			<option value="<c:out value="${crypto2}" />"><c:out value="${crypto2}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings: <input type="text" name="currency2Value" value=<c:out value="${currency2Value}" /> > <input
				type="text" name="currency2total" readonly="readonly" disabled
				value=$<c:out value="${currency2total}"/> /> <label style="text-decoration:bold"> <c:choose>
	<c:when test="${empty coin2Value}"> </c:when>
	<c:otherwise>@ $${coin2Value} </c:otherwise>
</c:choose></label>
		</div>
		
		</div>
		
		<br><br>
		
		<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#crypto3collapse" >Add Third Crypto</button>
		
		<div id="crypto3collapse" class="${empty coin3Value ? 'collapse' : 'collapse in' }"> 
				<div align="center" style="border: 1px solid black;margin:auto" class="well">
		Select Third currency:	<select name="crypto3">
			<option value="<c:out value="${crypto3}" />"><c:out value="${crypto3}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings: <input type="text" name="currency3Value" value=<c:out value="${currency3Value}" /> > <input
				type="text" name="currency3total" readonly="readonly" disabled
				value=$<c:out value="${currency3total}"/> /> <label style="text-decoration:bold"><c:choose>
	<c:when test="${empty coin3Value}"> </c:when>
	<c:otherwise>@ $${coin3Value} </c:otherwise>
</c:choose></label>
		</div>
		
		</div>
		<br><br>
		
		<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#crypto4collapse" >Add Fourth Crypto</button>
		
		<div id="crypto4collapse" class="${empty coin4Value ? 'collapse' : 'collapse in' }"> 
						<div align="center" style="border: 1px solid black;margin:auto" class="well">
		Select Fourth currency:	<select name="crypto4">
			<option value="<c:out value="${crypto4}" />"><c:out value="${crypto4}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings: <input type="text" name="currency4Value" value=<c:out value="${currency4Value}" /> > <input
				type="text" name="currency4total" readonly="readonly" disabled
				value=$<c:out value="${currency4total}"/> /> <label style="text-decoration:bold"> <c:choose>
	<c:when test="${empty coin4Value}"> </c:when>
	<c:otherwise>@ $${coin4Value} </c:otherwise>
</c:choose></label>
		</div>
		
		</div>
		<br><br>
		
		<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#crypto5collapse" >Add Fifth Crypto</button>
		
		<div id="crypto5collapse" class="${empty coin2Value ? 'collapse' : 'collapse in' }"> 
								<div align="center" style="border: 1px solid black;margin:auto" class="well">
		Select Fifth currency:	<select name="crypto5">
			<option value="<c:out value="${crypto5}" />"><c:out value="${crypto5}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings: <input type="text" name="currency5Value" value=<c:out value="${currency5Value}" /> > <input
				type="text" name="currency5total" readonly="readonly" disabled
				value=$<c:out value="${currency5total}"/> /> <label style="text-decoration:bold"> <c:choose>
	<c:when test="${empty coin5Value}"> </c:when>
	<c:otherwise>@ $${coin5Value} </c:otherwise>
</c:choose></label>
		</div>
		 </div>
		 
		 <br><br>
		 
		 		<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#crypto6collapse" >Add Sixth Crypto</button>
		
		<div id="crypto6collapse" class="${empty coin6Value ? 'collapse' : 'collapse in' }"> 
								<div align="center" style="border: 1px solid black;margin:auto" class="well">
		Select Sixth currency:	<select name="crypto6">
			<option value="<c:out value="${crypto6}" />"><c:out value="${crypto6}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings: <input type="text" name="currency6Value" value=<c:out value="${currency6Value}" /> > <input
				type="text" name="currency6total" readonly="readonly" disabled
				value=$<c:out value="${currency6total}"/> /> <label style="text-decoration:bold"> <c:choose>
	<c:when test="${empty coin6Value}"> </c:when>
	<c:otherwise>@ $${coin6Value} </c:otherwise>
</c:choose></label>
		</div>
		 </div>
		 
		 
	
		
		<div align="center">
			</br> </br> <input type="submit" value="Calculate Total Portfolio Value" class="btn btn-primary"/>
		</div>

		<br>		
		
		
		<div align="center" style="border: 1px solid black;margin:auto" class="well">
			Your total portfolio value:</span> <input type="text" name="totalValue"
				readonly="readonly" disabled value=$<c:out value="${totalValue}"/> />
		</div>
		
		<br>
		
		</div>
		
		
		</div>
		
	</form:form>

	<br><br><br>


	<%@include file="footer.jsp"%>


</body>
</html>