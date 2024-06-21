<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Novo Usuário</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
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
                <li class="nav-item ">
                    <a class="nav-link text-white fw-bold" href="?page=notificacao">Notificaçãoes</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
  
    <br><br>
    <div class="container">
    <div class="register-container p-4 bg-white rounded shadow">
        <h1 class="mb-4 text-center">Agendamento</h1>
        <form id="register-form" action="./processa_agendamento.php" method="post">
            <div class="form-group input-group">
                <label for="cpf" class="visually-hidden">CPF</label>
                <input type="text" id="cpf" name="cpf" class="form-control" placeholder="CPF" required autofocus>
            </div>
            <div class="form-group input-group">
                <label for="vacinas" class="visually-hidden">Vacinas</label>
                <select id="vacinas" name="vacinas" class="form-control" required>
                    <option value="">Selecione a vacina</option>
                    <?php
                        $url = 'http://localhost:3000/vacinas';
                        $vacinas = file_get_contents($url);
                        $vacinas = json_decode($vacinas);
    
                        foreach ($vacinas as $vacina) {
                            echo "<option value='" . $vacina->id . "'>" . $vacina->nome . "</option>";
                        }
                    ?>
                </select>
            </div>

            <div class="form-group input-group">
                <label for="dataHora" class="visually-hidden">Data e Hora</label>
                <input type="datetime-local" id="dataHora" name="dataHora" class="form-control" required>
            </div>
            <button type="submit" id="register-btn" class="btn btn-primary btn-block">Cadastrar</button>
        </form>
        <div id="success-message" class="alert alert-success mt-4 d-none" role="alert">
            Cadastro concluído!
        </div>
    </div>
</div>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">


</body>

</html>