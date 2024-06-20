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
        <link rel="stylesheet" href="./css/index.css">
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
                            <li><a class="dropdown-item" href="">Agendamento</a></li>
                            <li><a class="dropdown-item" href="">Notificação</a></li>
                            <li><a class="dropdown-item" href="">Informações</a></li>
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
            <h2 class="text-center mb-4 text-primary">Sobre a Vacinação</h2>
            <p>
                <strong>A vacinação é reconhecida como uma das mais eficazes estratégias</strong>
                para preservar a saúde da população e fortalecer uma sociedade saudável e resistente. Além de prevenir doenças graves,
                a imunização contribui para reduzir a disseminação desses agentes infecciosos na comunidade, protegendo aqueles que não podem ser vacinados por motivos de saúde.
            </p>
            <p>
                <strong> O calendário nacional de vacinação contempla, na rotina dos serviços, 20 vacinas que protegem o indivíduo em todos ciclos de vida, desde o nascimento.</strong> Entre as doenças imunopreveníveis por essas vacinas estão a poliomielite,
                sarampo, rubéola, tétano, coqueluche e outras doenças graves e muitas vezes fatais. O PNI é responsável por coordenar as campanhas anuais de vacinação. Essas campanhas têm como objetivo alcançar altas coberturas vacinais,
                garantindo a proteção individual e coletiva contra diversas doenças. Assim, o Ministério da Saúde atua em conjunto com estados, municípios e o Distrito Federal para garantir o acesso equitativo às vacinas em todo o país.
            </p>
        </div>
        <div class="container">
            <img src="./img/bannerMovimentoVacina.png" alt="Banner" class="img-fluid">
        </div>
    </div>


    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="p-3 border bg-light text-center">
                    <a href="#" class="text-decoration-none text-dark cursor-pointer">Solicitar Agendamento</a>
                </div>
            </div>
            <div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="p-3 border bg-light text-center">
                    <a href="#" class="text-decoration-none text-dark cursor-pointer">Notificações</a>
                </div>
            </div>
            <div class="col-12 col-md-6 col-lg-4 mb-4">
                <div class="p-3 border bg-light text-center">
                    <a href="#" class="text-decoration-none text-dark cursor-pointer">Informações</a>
                </div>
            </div>
        </div>
    </div>


<div class="wrapper">
    <div class="container mt-4">
        <h2 class="text-center mb-4 text-primary">Campanhas da Saúde</h2>
        <p>
            <strong>O Ministério da Saúde promove atualmente três campanhas de vacinação ao ano</strong>, juntamente com Secretarias de Saúde de estados, Distrito Federal e municípios. As estratégias são: vacinação contra Influenza (gripe); Multivacinação para atualização
            da Caderneta de Vacinação de crianças e adolescentes menores de 15 anos de idade; e a vacinação contra a covid-19 que ocorre ao longo de todo o ano.
        </p>
    </div>
    <div class="container">
        <div class="accordion accordion-flush" id="accordionFlushExample">
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                        Hepatite B
                    </button>
                </h2>
                <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">A Hepatite B é um dos cinco tipos de hepatite existentes no Brasil e é causada por vírus. Em 2018, foi responsável por 13.922 (32,8%) dos casos de hepatites notificados no Brasil. O vírus da Hepatite B está relacionado a 21,3% das mortes relacionadas às hepatites entre 2000 e 2017.
                        Na maioria dos casos não apresenta sintomas e muitas vezes é diagnosticada décadas após a infecção, com sinais relacionados a outras doenças do fígado, como cansaço, tontura, enjoo/vômitos, febre, dor abdominal, pele e olhos amarelados. A principal forma de prevenção é por meio da vacinação.</div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                        Pentavalente
                    </button>
                </h2>
                <div id="flush-collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">A vacina penta (DTP/HB/Hib) - vacina adsorvida difteria, tétano, pertussis, hepatite B (recombinante) e Haemophilus influenzae B (conjugada) -, é uma composição combinada que previne contra cinco doenças: difteria, tétano, coqueluche, hepatite B e infecções causadas pela bactéria H. influenzae tipo B, doenças graves e que muitas vezes podem ser fatais
                        Desde 2012, o Programa Nacional de Imunizações-PNI, do Ministério da Saúde, oferta a vacina penta na rotina do Calendário Nacional de Vacinação, em substituição a vacina tetravalente. A vacina penta disponibilizada no Sistema Único de Saúde – SUS é importada, adquirida pelo Brasil via Fundo Estratégico da Organização Pan-Americana da Saúde - OPAS, uma vez que não existe laboratório produtor no país para este imunobiológico.</div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                        Febre amarela
                    </button>
                </h2>
                <div id="flush-collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">A febre amarela é uma doença infecciosa febril aguda, imunoprevenível, de evolução abrupta e gravidade variável, com elevada letalidade nas suas formas graves. A doença é causada por um vírus transmitido por mosquitos, e possui dois ciclos de transmissão (urbano e silvestre). No ciclo urbano, a transmissão ocorre a partir de vetores urbanos (Ae. aegypti)
                        infectados. No ciclo silvestre, os transmissores são mosquitos com hábitos predominantemente silvestres, sendo os gêneros Haemagogus e Sabethes os mais importantes.
                        No ciclo silvestre, os primatas não humanos (PNHs) são considerados os principais hospedeiros, amplificadores do vírus, e são vítimas da doença assim como o ser humano,
                        que, nesse ciclo, apresenta-se como hospedeiro acidental.</div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <img src="./img/bannerMovimentoVacina2.png" alt="Banner" class="img-fluid">
    </div>
</div>

<div class="wrapper">
    <div class="container mt-4">
        <p>
            <strong>A política de vacinação é responsabilidade do </strong>Programa Nacional de Imunizações (PNI) do Ministério da Saúde. Estabelecido em 1973, o PNI desempenha um papel fundamental na promoção da saúde da população brasileira.
            Por meio do programa, o governo federal disponibiliza gratuitamente no Sistema Único de Saúde - SUS 48 imunobiológicos: 31 vacinas, 13 soros e 4 imunoglobulinas. Essas vacinas incluem tanto as presentes no Calendário Nacional
            de Vacinação quanto as indicadas para grupos em condições clínicas especiais, como pessoas com HIV ou indivíduos em tratamento de algumas doenças (câncer, insuficiência renal, entre outras), aplicadas nos Centros de Referência
            para Imunobiológicos Especiais (CRIE), e inclui também as vacinas COVID-19 e outras administradas em situações específicas.
        </p>
    </div>
</div>

<div class="wrapper">
    <div class="container mt-4">
    <img src="./img/bannerimagemfalsa.png" alt="Banner" class="img-fluid">
    </div>
</div>

<div class="container d-container">
    <div class="link-container">
        <div class="d-box-1">
            <div class="d-logo-1">
                <img class="d-logo-desktop" src="./img/logo-ouvsus.png" alt="OuvSUS - Disque 136" />
            </div>
        </div>
        <div class="d-linha"></div>
        <div class="d-box-2">
            <div class="d-titulo">
                <span>Ouvidoria Geral do <b>SUS</b></span>
            </div>
            <div class="d-texto">
                <span>Teleatendente: de segunda-feira <br />
                    a sexta-feira, das 8h às 20h,<br />
                    e aos sábados, das 8h às 18h.</span>
            </div>
        </div>
    </div>
</div>

<div class="container mt-4"></div>
<footer class="footer">
    <div class="container">
        <span class="text-white">© 2024 Todos os direitos reservados.</span>
    </div>
</footer>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

</body>
</html>
<?php } ?>