<?php
if (isset($_GET['page'])) {
    $page = $_GET['page'];

    $allowed_pages = ['login', 'cadastroNovoUsuario', 'redefinirSenha'];
    
    if (in_array($page, $allowed_pages)) {
        include $page . '.php';
    } else {
        echo 'Página não encontrada!';
    }
} else {
    echo 'Bem-vindo à página inicial!';
}
?>