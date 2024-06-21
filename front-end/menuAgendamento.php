<?php
session_start();
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pesquisar Agendamentos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        .form-group {
            display: flex;
            align-items: center;
        }
        .form-group label {
            width: 80px;
            margin-right: 10px;
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
                    <a class="nav-link text-white fw-bold" href="./">Início</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="?page=notificacao">Notificações</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container mt-4">
    <h2 class="text-center mt-4">Pesquisar Agendamentos</h2>
    <div class="d-flex justify-content-end mb-4 mt-4">
        <a href="?page=agendamento" type="button" class="btn btn-primary mt-4 mb-4">Solicitar Agendamento</a>
    </div>
    <div class="card shadow p-4 mb-4">
        <form class="mt-4" action="menuAgendamento.php" method="GET">
            <div class="form-group mb-3">
                <label for="cpf">CPF:</label>
                <input type="text" class="form-control" id="cpf" name="cpf" placeholder="Digite o CPF" required>
            </div>
            <div class="form-group mb-3">
                <label for="vacina">Vacina:</label>
                <select class="form-select" id="vacina" name="vacina">
                    <option value="">Selecione a Vacina</option>
                    <?php
                    $url = 'http://localhost:3000/vacinas';
                    $vacinas = @file_get_contents($url);
                    if ($vacinas !== false) {
                        $vacinas = json_decode($vacinas);
                        foreach ($vacinas as $vacina) {
                            echo "<option value='" . htmlspecialchars($vacina->nome, ENT_QUOTES, 'UTF-8') . "'>" . htmlspecialchars($vacina->nome, ENT_QUOTES, 'UTF-8') . "</option>";
                        }
                    } else {
                        echo "<option value=''>Erro ao carregar vacinas</option>";
                    }
                    ?>
                </select>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Pesquisar</button>
            </div>
        </form>
    </div>
</div>

<?php
if (isset($_SESSION['message'])) {
    $message = $_SESSION['message'];
    $message_type = $_SESSION['message_type'];
    echo "<script>
        Swal.fire({
            title: '".($message_type == 'success' ? 'Sucesso' : 'Erro')."',
            text: '".$message."',
            icon: '".$message_type."',
            confirmButtonText: 'OK'
        });
    </script>";
    unset($_SESSION['message']);
    unset($_SESSION['message_type']);
}

if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['cpf'])) {
    $cpf = $_GET['cpf'];
    $vacina = isset($_GET['vacina']) ? $_GET['vacina'] : null;

    $url = "http://localhost:3000/agendamentos/cpf/{$cpf}";
    if (!empty($vacina)) {
        $url .= "/vacina/" . urlencode($vacina);
    }

    $response = @file_get_contents($url);

    if ($response !== false) {
        $responseData = json_decode($response, true);

        if (isset($responseData['message'])) {
            echo '<div class="container mt-4">';
            echo '<div class="alert alert-info text-center" role="alert">';
            echo htmlspecialchars($responseData['message'], ENT_QUOTES, 'UTF-8');
            echo '</div>';
            echo '</div>';
        } else {
            $agendamentos = $responseData;
            if (!empty($agendamentos)) {
                echo '<div class="container mt-4">';
                echo '<h2 class="text-center mb-4">Resultados da Pesquisa</h2>';
                echo '<div class="table-responsive">';
                echo '<table class="table table-bordered">';
                echo '<thead class="table-dark">';
                echo '<tr>';
                echo '<th scope="col">Paciente</th>';
                echo '<th scope="col">Data</th>';
                echo '<th scope="col">Hora</th>';
                echo '<th scope="col">Nome da Vacina</th>';
                echo '<th scope="col">Status</th>';
                echo '<th scope="col">Ações</th>';
                echo '</tr>';
                echo '</thead>';
                echo '<tbody>';

                foreach ($agendamentos as $agendamento) {
                    $dataHora = new DateTime($agendamento['data_hora_visita'], new DateTimeZone('UTC'));
                    $dataHora->setTimezone(new DateTimeZone('America/Sao_Paulo'));
                    $data = $dataHora->format('d/m/Y');
                    $hora = $dataHora->format('H:i');
                    echo '<tr>';
                    echo '<td>' . htmlspecialchars($agendamento['nomeIdoso'], ENT_QUOTES, 'UTF-8') . '</td>';
                    echo '<td>' . $data . '</td>';
                    echo '<td>' . $hora . '</td>';
                    echo '<td>' . htmlspecialchars($agendamento['nomeVacina'], ENT_QUOTES, 'UTF-8') . '</td>';
                    echo '<td>' . htmlspecialchars($agendamento['status'], ENT_QUOTES, 'UTF-8') . '</td>';
                    echo '<td>';
                    echo '<a href="editarAgendamento.php?id=' . $agendamento['id'] . '" class="btn btn-warning btn-sm">Editar</a> ';
                    echo '<a href="#" onclick="confirmarExclusao(' . $agendamento['id'] . ')" class="btn btn-danger btn-sm">Excluir</a>';
                    echo '</td>';
                    echo '</tr>';
                }

                echo '</tbody>';
                echo '</table>';
                echo '</div>';
                echo '</div>';
            } else {
                echo '<div class="container mt-4">';
                echo '<div class="alert alert-danger text-center" role="alert">';
                echo 'Nenhum agendamento encontrado para o CPF ' . htmlspecialchars($cpf);
                echo '</div>';
                echo '</div>';
            }
        }
    } else {
        echo '<div class="container mt-4">';
        echo '<div class="alert alert-danger text-center" role="alert">';
        echo 'Erro ao buscar agendamentos. Verifique o CPF ou tente novamente mais tarde.';
        echo '</div>';
        echo '</div>';
    }
}
?>

<script>
    function confirmarExclusao(id) {
        Swal.fire({
            title: 'Tem certeza?',
            text: "Você não poderá reverter isso!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, excluir!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = 'excluirAgendamento.php?id=' + id;
            }
        });
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</body>
</html>
