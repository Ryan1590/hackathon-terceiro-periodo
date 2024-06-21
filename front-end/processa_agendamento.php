<?php

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
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));

curl_setopt($ch, CURLOPT_HTTPHEADER, array(
    'Content-Type: application/json',
    'Content-Length: ' . strlen(json_encode($data))
));

$response = curl_exec($ch);

if (curl_errno($ch)) {
    echo 'Erro na requisição: ' . curl_error($ch);
    http_response_code(500);
} else {
    $statusCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

    if ($statusCode == 201) { 
        echo 'Agendamento criado com sucesso!';
    } else {
        echo 'Erro ao criar agendamento. Status: ' . $statusCode;
    }
}

curl_close($ch);

?>
