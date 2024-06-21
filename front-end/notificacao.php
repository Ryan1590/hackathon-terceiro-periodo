<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <title>Alertas e Lembretes de Vacinação</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        header {
            background-color: blue;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
        main {
            padding: 20px;
            flex-grow: 1;
        }
        .alertas-container, .lembretes-container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        .alerta-item, .lembrete-item {
            margin-bottom: 10px;
            padding: 15px;
            border: 1px solid #e0e0e0;
            border-radius: 5px;
            display: flex;
            align-items: center;
        }
        .alerta-item i, .lembrete-item i {
            margin-right: 10px;
            color: #007bff;
        }
        .footer {
            background-color: black;
            display: flex;
            text-align: center;
            align-items: center;
            height: 60px;
            justify-content: center;
            color: white;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container">
        <a class="navbar-brand ms-2" href="#"><img src="./img/zegotinha.jpeg" width="80px" alt="Logo"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link text-white fw-bold" href="./">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="?page=agendamento">Agendamento</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="wrapper">
    <div class="container mt-4 d-flex justify-content-center">
        <p class="fw-bold">Atenção aos Lembretes!</p>
    </div>
</div>

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
