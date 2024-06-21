<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];
    $vacina = $_POST['vacinas'];
    $dataHora = $_POST['dataHora'];

    $data = [
        'nomeVacina' => $vacina,
        'data_hora_visita' => $dataHora
    ];

    $options = [
        'http' => [
            'header'  => "Content-type: application/json\r\n",
            'method'  => 'PUT',
            'content' => json_encode($data)
        ]
    ];

    $context  = stream_context_create($options);
    $url = "http://localhost:3000/agendamentos/{$id}";
    $result = @file_get_contents($url, false, $context);
    $http_code = $http_response_header[0] ?? null;

    if ($result === false || strpos($http_code, '200') === false) {
        $_SESSION['message'] = 'Erro ao editar o agendamento. Código HTTP: ' . $http_code;
        $_SESSION['message_type'] = 'error';
    } else {
        $_SESSION['message'] = 'Agendamento editado com sucesso.';
        $_SESSION['message_type'] = 'success';
    }

    header('Location: menuAgendamento.php');
    exit;
}
?>
