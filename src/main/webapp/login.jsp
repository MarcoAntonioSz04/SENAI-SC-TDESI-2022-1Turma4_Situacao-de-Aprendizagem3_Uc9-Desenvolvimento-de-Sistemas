<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form class="row g-3 needs-validation was-validated"
			action="ServletOi" method="post">
			<div class="mb-3">
				<label for="validationServer01" class="form-label">Nome</label> <input
					type="text" name="nome" class="form-control"
					id="validationServer01" required="">
			</div>
			<div class="mb-3">
				<label for="validationServer03" class="form-label" for="documentos">Modalidade</label>
      <div class="col-md-6 position-relative">
        <div class="row">
          <div class="col-md-4">
      <select class="form-select" id="documentos" name="modalidade" required="">
        <option selected="" disabled="" value="">Selecione aqui um tipo de Documento</option>
        <option value="cpf">CPF</option>
        <option value="cnpj">CNPJ</option>
      </select>
      <input type="text" id="cpf" name="cpf" class="form-control cpf-cnpj" required="" placeholder="123.456.789-10">
<input type="text" id="cnpj" name="cnpj" class="form-control cpf-cnpj" required="" placeholder="12.345.678/0001-95">

    </div>
  </div>
  </div>

  <style>
    .cpf-cnpj {
      display: none;
    }
    </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
    $(document).ready(function(){
      $("#documentos").change(function(){
        var selectedOption = $(this).children("option:selected").val();
        if (selectedOption == "cpf") {
          $("#cpf").show();
          $("#cnpj").hide();
        } else if (selectedOption == "cnpj") {
          $("#cnpj").show();
          $("#cpf").hide();
        }
      });
    });
    </script>

			</div>
			<button type="submit" class="btn btn-outline-primary">Login</button>
		</form>
		<h4>${msg}</h4>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>