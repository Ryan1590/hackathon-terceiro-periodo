<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Novo Usuário</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f5f9 !important;
            font-family: Arial, sans-serif;
        }

        .header {
            background-color: #007bff;
            color: white;
            padding: 10px 0;
        }

        .header h1 {
            margin: 0;
        }

        .toggle-password {
            position: absolute;
            right: 10px;
            top: 70%;
            transform: translateY(-50%);
            cursor: pointer;
        }

        @media (max-width: 576px) {
            .header input[type="text"] {
                min-width: 150px;
            }
        }

        .icon-container {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .icon-container i {
            font-size: 100px;
            color: #007bff;
        }
    </style>
</head>

<body>

    <div class="header text-center">
        <div class="container mt-3">
            <div class="row align-items-center">
                <div class="col-4 d-flex justify-content-center align-items-center">
                    <img class="mb-2" src="./img/zegotinha.jpeg" alt="logo" style="width: 70px;">
                </div>
                <div class="col-4 d-none d-md-block">
                    <!-- Espaço reservado para centralização -->
                </div>
                <div class="col-4 d-flex justify-content-end align-items-center">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user me-2"></i>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="./redefinirSenha.php">Redefinir senha</a></li>
                            <li><a class="dropdown-item" href="./login.php">Login</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <br><br>
    <div class="container">
        <div class="register-container p-4 bg-white rounded shadow">
            <h1 class="mb-4 text-center">Cadastro de Usuário</h1>
            <form id="register-form" action="./processa_NovoUsuario.php" method="post">
                <div class="form-group position-relative">
                    <label for="nomeCompleto">Nome Completo:</label>
                    <input type="text" id="nomeCompleto" name="nomeCompleto" class="form-control" required autofocus>
                </div>
                <div class="form-group position-relative">
                    <label for="email">E-mail:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="form-group position-relative">
                    <label for="nomeUsuario">Nome de Usuário:</label>
                    <input type="text" id="username" name="nomeUsuario" class="form-control" required>
                </div>
                <div class="form-group position-relative">
                    <label for="password">Senha:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                    <span class="toggle-password" onclick="togglePasswordVisibility('password', this)">
                        <i class="fa fa-eye"></i>
                    </span>
                </div>
                <div class="form-group position-relative">
                    <label for="confirm-senha">Confirmação de Senha:</label>
                    <input type="password" id="confirm-senha" name="confirm-senha" class="form-control" required>
                    <span class="toggle-password" onclick="togglePasswordVisibility('confirm-senha', this)">
                        <i class="fa fa-eye"></i>
                    </span>
                </div>
                <button type="submit" id="register-btn" class="btn btn-primary btn-block">Cadastrar</button>
            </form>
            <div id="success-message" class="alert alert-success mt-4 d-none" role="alert">
                Cadastro concluído!
            </div>
        </div>
    </div>

    <script>
        function togglePasswordVisibility(inputId, icon) {
            const passwordInput = document.getElementById(inputId);
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
            const iconClass = icon.querySelector('i');
            iconClass.classList.toggle('fa-eye');
            iconClass.classList.toggle('fa-eye-slash');
        }

        document.getElementById('register-form').addEventListener('submit', function(event) {
            var successMessage = document.getElementById('success-message');
            var password = document.getElementById('password').value;
            var confirmPassword = document.getElementById('confirm-senha').value;

            var formFields = document.querySelectorAll('#register-form input');
            var formValid = true;
            formFields.forEach(function(field) {
                if (!field.value.trim()) {
                    field.classList.add('is-invalid');
                    formValid = false;
                } else {
                    field.classList.remove('is-invalid');
                }
            });

            if (password !== confirmPassword) {
                alert('As senhas não correspondem.');
                formValid = false;
            }

            var passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
            if (!passwordRegex.test(password)) {
                alert('A senha deve conter pelo menos uma letra maiúscula, um número, um caractere especial e 8 caracteres.');
                formValid = false;
            }

            if (!formValid) {
                event.preventDefault();
                return;
            }

            successMessage.classList.remove('d-none');
            successMessage.textContent = 'Cadastro concluído!';
        });
    </script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">


</body>

</html>