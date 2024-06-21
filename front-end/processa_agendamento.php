<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $cpf = $_POST['cpf'];
    $nomeVacina = $_POST['vacinas'];
    $dataHora = $_POST['dataHora'];

    $data = array(
        'cpf' => $cpf,
        'nomeVacina' => $nomeVacina,
        'data_hora_visita' => $dataHora,
    );

    $url = 'http://localhost:3000/agendamentos';

    $ch = curl_init($url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(
        'Content-Type: application/json',
        'Content-Length: ' . strlen(json_encode($data))
    ));

    $response = curl_exec($ch);
    $http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);

    if (curl_errno($ch) || $http_code != 200) {
        $_SESSION['message'] = 'Erro ao incluir o agendamento. Código HTTP: ' . $http_code;
        $_SESSION['message_type'] = 'error';
    } else {
        $_SESSION['message'] = 'Agendamento incluído com sucesso.';
        $_SESSION['message_type'] = 'success';
    }

    curl_close($ch);

    header('Location: menuAgendamento.php');
    exit;
}
?>
