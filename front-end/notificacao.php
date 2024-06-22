<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/notificacao.css">
    <title>Alertas e Lembretes de Vacinação</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container">
        <a class="navbar-brand ms-2"  href="./"><img src="./img/zegotinha.jpeg" width="80px" alt="Logo"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link text-white fw-bold" href="./">Home</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link text-white fw-bold" href="menuAgendamento">Consulta Agendamento</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="agendamento">Agendamento</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h2 class="text-center mt-4">Notificaçãoes</h2>

<main>
    <div class="alertas-container">
        <h2>Próximas Vacinas</h2>
        <div id="alertas-lista">
        <?php
            $url = 'http://localhost:3000/vacinas';
            $vacinas = file_get_contents($url);
            $vacinas = json_decode($vacinas);
            foreach ($vacinas as $vacina) {
                echo "<div class='alerta-item'><i class='fas fa-syringe'></i> Vacina $vacina->nome</div>";
            }
        ?>
        </div>
    </div>
</main>

<footer class="footer">
    <div class="container">
        <span>© 2024 Todos os direitos reservados.</span>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
