<?php
if (isset($_POST)) {
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
    if ($result === false) {
        die('Erro ao editar o agendamento.');
    }

    header('Location: menuAgendamento.php');
    exit;
}
?>
