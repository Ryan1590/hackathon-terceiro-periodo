<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tela de Login - Vacinação</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            background-color: #f0f5f9;
            font-family: Arial, sans-serif;
        }

        .container-login {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .login-form {
            background-color: #ffffff;
            color: #333333;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 100%;
            max-width: 400px;
        }

        .login-form h2 {
            margin-bottom: 20px;
            text-align: center;
            color: #007bff;
        }

        .form-group {
            position: relative;
        }

        .form-group input {
            border-radius: 5px;
        }

        #togglePassword {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
        }

        .footer-text {
            text-align: center;
            margin-top: 20px;
            color: #777777;
        }

        .footer-text a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>

<body>

    <div class="container-login">
        <div class="login-form">
            <h2>Login</h2>
            <form action="" method="get">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required autofocus>
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">Senha:</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Digite sua senha" required>
                        <div class="input-group-append">
                            <span class="input-group-text" id="togglePassword"><i class="fa fa-eye"></i></span>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Entrar</button>
            </form>
            <div class="footer-text">
                <a href="./redefinirSenha.php">Esqueci minha senha</a>
                <span class="mx-2">|</span>
                <a href="./cadastroNovoUsuario.php">Primeiro Acesso</a>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        const togglePassword = document.querySelector('#togglePassword');
        const password = document.querySelector('#password');
        togglePassword.addEventListener('click', function () {
            const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            this.querySelector('i').classList.toggle('fa-eye-slash');
        });
    </script>
</body>

</html>
