<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $nomeCompleto = $_POST['nomeCompleto'];
    $email = $_POST['email'];
    $nomeUsuario = $_POST['nomeUsuario'];
    $password = $_POST['password'];

    $data = array('nomeCompleto' => $nomeCompleto, 'email' => $email, 'nomeUsuario' => $nomeUsuario, 'password' => $password);
    $data_json = json_encode($data);

   
    echo "<pre>";
    print_r($data_json);
    echo "</pre>";

    $url = 'http://localhost:3000/submit'; 

    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data_json);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));

    $response = curl_exec($ch);
    curl_close($ch);

    echo "Resposta do servidor: " . $response;
}
?>

