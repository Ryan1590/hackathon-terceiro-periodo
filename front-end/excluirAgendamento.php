]<?php
session_start();

$id = isset($_GET['id']) ? $_GET['id'] : null;
if ($id === null) {
    die('ID do agendamento não fornecido.');
}

$url = "http://localhost:3000/agendamentos/excluir/{$id}";

// Inicializa uma sessão cURL
$ch = curl_init($url);

// Define as opções da sessão cURL
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Executa a solicitação
$response = curl_exec($ch);
$http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);

// Verifica erros na execução da solicitação
if (curl_errno($ch) || $http_code != 200) {
    die('Erro ao excluir o agendamento. Código HTTP: ' . $http_code);
}

// Fecha a sessão cURL
curl_close($ch);

// Redireciona de volta para a página de agendamentos
header('Location: menuAgendamento.php');
exit;
?>
