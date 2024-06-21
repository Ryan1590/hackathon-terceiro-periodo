<?php
session_start();

$id = isset($_GET['id']) ? $_GET['id'] : null;
if ($id === null) {
    die('ID do agendamento não fornecido.');
}

$url = "http://localhost:3000/agendamentos/excluir/{$id}";

$ch = curl_init($url);

curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);
$http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);

if (curl_errno($ch) || $http_code != 200) {
    $_SESSION['message'] = 'Erro ao excluir o agendamento. Código HTTP: ' . $http_code;
    $_SESSION['message_type'] = 'error';
} else {
    $_SESSION['message'] = 'Agendamento excluído com sucesso.';
    $_SESSION['message_type'] = 'success';
}

curl_close($ch);

header('Location: menuAgendamento');
exit;
?>
