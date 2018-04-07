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
	
	<div id="big-borda-yo" style="border: 1px solid black;width:75%;margin:auto">
		<br>
		
		<div align="center" style="border: 1px solid black;width:50%;margin:auto" class="cryptoDiv">		
			Select first currency: <select name="crypto1">
				<option value="<c:out value="${crypto1}" />"><c:out value="${crypto1}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>
			</select>			
			<br><br>Total holdings in USD: <input type="text" name="currency1Value" value=<c:out value="${currency1Value}" />>
			<input type="text" name="currency1total" readonly="readonly" disabled
				value=$<c:out value="${currency1total}"/> /> <label style="text-decoration:bold"> @ $${coin1Value}</label>
		</div>
		
		<br><br>
		
		<div align="center" style="border: 1px solid black;width:50%;margin:auto" class="cryptoDiv">
		Select second currency:	<select name="crypto2">
			<option value="<c:out value="${crypto2}" />"><c:out value="${crypto2}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings in USD:<input type="text" name="currency2Value" value=<c:out value="${currency2Value}" /> > <input
				type="text" name="currency2total" readonly="readonly" disabled
				value=$<c:out value="${currency2total}"/> /> <label style="text-decoration:bold"> @ $${coin2Value}</label>
		</div>
		
		<br><br>
		
				<div align="center" style="border: 1px solid black;width:50%;margin:auto" class="cryptoDiv">
		Select Third currency:	<select name="crypto3">
			<option value="<c:out value="${crypto3}" />"><c:out value="${crypto3}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings in USD:<input type="text" name="currency3Value" value=<c:out value="${currency3Value}" /> > <input
				type="text" name="currency3total" readonly="readonly" disabled
				value=$<c:out value="${currency3total}"/> /> <label style="text-decoration:bold"> @ $${coin3Value}</label>
		</div>
		<br><br>
		
						<div align="center" style="border: 1px solid black;width:50%;margin:auto" class="cryptoDiv">
		Select Fourth currency:	<select name="crypto4">
			<option value="<c:out value="${crypto4}" />"><c:out value="${crypto4}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings in USD:<input type="text" name="currency4Value" value=<c:out value="${currency4Value}" /> > <input
				type="text" name="currency4total" readonly="readonly" disabled
				value=$<c:out value="${currency4total}"/> /> <label style="text-decoration:bold"> @ $${coin4Value}</label>
		</div>
		
		<br><br>
		
								<div align="center" style="border: 1px solid black;width:50%;margin:auto" class="cryptoDiv">
		Select Fifth currency:	<select name="crypto5">
			<option value="<c:out value="${crypto5}" />"><c:out value="${crypto4}" /></option>
				<c:forEach items="${fullCryptoList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /> </option>
				</c:forEach>		
		</select>
			<br><br>Total Holdings in USD:<input type="text" name="currency5Value" value=<c:out value="${currency5Value}" /> > <input
				type="text" name="currency5total" readonly="readonly" disabled
				value=$<c:out value="${currency5total}"/> /> <label style="text-decoration:bold"> @ $${coin5Value}</label>
		</div>
		
	
		
		<div align="center">
			</br> </br> <input type="submit" value="Calculate Total Portfolio Value" />
		</div>

		</br>		
		
		
		<div align="center" style="border: 1px solid black;width:25%;margin:auto
		;"><span style="text-decoration: underline">
			Your total portfolio value:</span> <input type="text" name="totalValue"
				readonly="readonly" disabled value=$<c:out value="${totalValue}"/> />
		</div>
		
		</div>
		<br>
	</form:form>

	<br><br><br>


	<%@include file="footer.jsp"%>


</body>
</html>