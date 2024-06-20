<?php
if (isset($_GET['page'])) {
    $page = $_GET['page'];

    $allowed_pages = ['login', 'cadastroNovoUsuario', 'redefinirSenha'];
    
    if (in_array($page, $allowed_pages)) {
        include $page . '.php';
    } else {
        echo 'Página não encontrada!';
    }
} else {
?>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Inicial</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f5f9 !important;
            font-family: Arial, sans-serif;
            height: 100%;
        }

        .header {
            background-color: #007bff;
            color: white;
            padding: 10px 0;
        }

        .header h1 {
            margin: 0;
        }

        .toggle-password {
            position: absolute;
            right: 10px;
            top: 70%;
            transform: translateY(-50%);
            cursor: pointer;
        }

        @media (max-width: 576px) {
            .header input[type="text"] {
                min-width: 150px;
            }
        }
        .footer{
            background-color: black;
            display: flex;
            text-align: center;
            align-items: center;
            height: 60px;
        }

    </style>
</head>

<body>
    
<div class="header text-center">
    <div class="container mt-3">
        <div class="row align-items-center">
            <div class="col-4 d-flex justify-content-center align-items-center">
                <img class="mb-2" src="./img/zegotinha.jpeg" alt="logo" style="width: 70px;">
            </div>
            <div class="col-4 d-none d-md-block"></div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user me-2"></i>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="./redefinirSenha.php">Redefinir senha</a></li>
                        <li><a class="dropdown-item" href="./login.php">Sair</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="carouselExampleCaptions" class="carousel slide" style="max-height: 500px; width: 100%;">
  <div class="carousel-inner">
  <div class="carousel-item active">
        <img src="./img/banner2-vacina-para-todos.png" class="d-block img-fluid" alt="Imagem" style="object-fit: cover; max-height: 500px; width: 100%; margin: 0; padding: 0;">
    </div>
    <div class="carousel-item ">
      <img src="./img/imagemcarrosselcovid.jpeg" class="d-block img-fluid" alt="Imagem" style="object-fit: cover; max-height: 500px;  width: 100%; margin: 0; padding: 0;">
    </div>
    <div class="carousel-item">
      <img src="./img/imagemcarrosselvacina.jpeg" class="d-block img-fluid" alt="Imagem" style="object-fit: cover; max-height: 500px;  width: 100%; margin: 0; padding: 0;">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<div class="wrapper">
    <div class="container mt-4">
        <h2 class="text-center mb-4">Sobre a Vacinação</h2>
        <p>
            <strong>A vacinação é reconhecida como uma das mais eficazes estratégias</strong> 
            para preservar a saúde da população e fortalecer uma sociedade saudável e resistente. Além de prevenir doenças graves,
            a imunização contribui para reduzir a disseminação desses agentes infecciosos na comunidade, protegendo aqueles que não podem ser vacinados por motivos de saúde.
        </p>

         <p> <strong>A política de vacinação é responsabilidade do </strong>Programa Nacional de Imunizações (PNI) do Ministério da Saúde. Estabelecido em 1973, o PNI desempenha um papel fundamental na promoção da saúde da população brasileira.
            Por meio do programa, o governo federal disponibiliza gratuitamente no Sistema Único de Saúde - SUS 48 imunobiológicos: 31 vacinas, 13 soros e 4 imunoglobulinas. Essas vacinas incluem tanto as presentes no Calendário Nacional
            de Vacinação quanto as indicadas para grupos em condições clínicas especiais, como pessoas com HIV ou indivíduos em tratamento de algumas doenças (câncer, insuficiência renal, entre outras), aplicadas nos Centros de Referência
            para Imunobiológicos Especiais (CRIE), e inclui também as vacinas COVID-19 e outras administradas em situações específicas.
        </p>

        <p>
          <strong> O calendário nacional de vacinação contempla, na rotina dos serviços, 20 vacinas que protegem o indivíduo em todos ciclos de vida, desde o nascimento.</strong> Entre as doenças imunopreveníveis por essas vacinas estão a poliomielite,
            sarampo, rubéola, tétano, coqueluche e outras doenças graves e muitas vezes fatais. O PNI é responsável por coordenar as campanhas anuais de vacinação. Essas campanhas têm como objetivo alcançar altas coberturas vacinais,
            garantindo a proteção individual e coletiva contra diversas doenças. Assim, o Ministério da Saúde atua em conjunto com estados, municípios e o Distrito Federal para garantir o acesso equitativo às vacinas em todo o país.
        </p>

    </div>

    <div class="text-center">
        <img src="./img/bannerMovimentoVacina.png" alt="Banner" class="img-fluid">
    </div>

</div>


<footer class="footer">
    <div class="container">
        <span class="text-white">© 2024 Meu Site. Todos os direitos reservados.</span>
    </div>
</footer>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
</body>

</html>
<?php } ?>
