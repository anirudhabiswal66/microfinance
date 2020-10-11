<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Upload Excel File</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>
	<h4>Create Loan</h4>
	${getAllLoanTitle}
	<h4>Create Loan</h4>
	<!-- <div th:each="author : ${getAllLoanTitle}">
		<a th:text="${author}"></a>
	</div> -->

	<div>
		<c:url var='create' value='/loan/create-loan' />
		<form:form action="${create }" modelAttribute="loan" method="POST"
			id="create">


			<div>
				<label>Principal</label>
				<form:input path="principal" />
			</div>
			<div>
				<label>No of EMI’s</label>
				<form:input path="tenure" />
			</div>
			<div>
				<button type="submit">Create</button>
			</div>
		</form:form>
	</div>
</body>
</html>
