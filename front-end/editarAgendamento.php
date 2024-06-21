<?php
$id = isset($_GET['id']) ? $_GET['id'] : null;
if ($id === null) {
    die('ID do agendamento não fornecido.');
}

$url = "http://localhost:3000/agendamentos/id/{$id}";
$response = @file_get_contents($url);
if ($response === false) {
    die('Erro ao buscar informações do agendamento.');
}

$agendamento = json_decode($response);
if ($agendamento === null) {
    die('Agendamento não encontrado.');
}

?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Agendamento</title>
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
                    <a class="nav-link text-white fw-bold" href="./">Início</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="./notificacao">Notificações</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<br><br>
<div class="container">
    <div class="register-container p-4 bg-white rounded shadow">
        <h1 class="mb-4 text-center">Editar Agendamento</h1>
        <form id="edit-form" action="./processa_edicao_agendamento.php" method="post">
            <input type="hidden" name="id" value="<?php echo htmlspecialchars($agendamento->id, ENT_QUOTES, 'UTF-8'); ?>">
            <div class="form-group input-group">
                <label for="vacinas" class="visually-hidden">Vacinas</label>
                <select id="vacinas" name="vacinas" class="form-control" required>
                    <option value="">Selecione a vacina</option>
                    <?php
                    $url = 'http://localhost:3000/vacinas';
                    $vacinas = @file_get_contents($url);
                    if ($vacinas !== false) {
                        $vacinas = json_decode($vacinas);
                        foreach ($vacinas as $vacina) {
                            $selected = ($vacina->nome == $agendamento->nomeVacina) ? 'selected' : '';
                            echo "<option value='" . htmlspecialchars($vacina->nome, ENT_QUOTES, 'UTF-8') . "' $selected>" . htmlspecialchars($vacina->nome, ENT_QUOTES, 'UTF-8') . "</option>";
                        }
                    } else {
                        echo "<option value=''>Erro ao carregar vacinas</option>";
                    }
                    ?>
                </select>
            </div>
            <div class="form-group input-group">
                <label for="dataHora" class="visually-hidden">Data e Hora</label>
                <?php

                $dataHora = new DateTime($agendamento->data_hora_visita, new DateTimeZone('UTC'));
                $dataHora->setTimezone(new DateTimeZone('America/Sao_Paulo'));
                $dataHoraFormatada = $dataHora->format('Y-m-d\TH:i');
                ?>
                <input type="datetime-local" id="dataHora" name="dataHora" class="form-control" value="<?php echo htmlspecialchars($dataHoraFormatada, ENT_QUOTES, 'UTF-8'); ?>" required>
            </div>
            <button type="submit" id="edit-btn" class="btn btn-primary btn-block">Salvar</button>
        </form>
        <div id="success-message" class="alert alert-success mt-4 d-none" role="alert">
            Edição concluída!
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
