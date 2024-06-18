
<!DOCTYPE html>
<html lang="pt-br">
<head> 
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Novo Usuário</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
    <style>
        body {
            background-color: #f8f9fa;
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
    </style>
</head>

<body>

    <div class="header text-center">
        <div class="container mt-3">
            <div class="row">
                <div class="col-4 d-flex justify-content-center align-items-center">
                    <img class="mb-4" src="logo-ge.svg" alt="logo" width="60px">
                </div>
                <div class="col-4 d-flex justify-content-center align-items-center ms-auto">
                    <div class="dropdown me-2">
                        <button class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-cog"></i>
                            <span class="visually-hidden">Configurações de acessibilidade</span>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="#">Aumentar fonte</a></li>
                            <li><a class="dropdown-item" href="#">Contraste de cores</a></li>
                        </ul>
                    </div>
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user me-2"></i>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="redefinirSenha.html">Atualização de senha</a></li>
                            <li><a class="dropdown-item" href="login.html">Login</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="register-container p-4 bg-white rounded shadow">
            <h1 class="mb-4">Cadastro de Novo Usuário</h1>
            <form id="register-form">
                <div class="form-group position-relative">
                    <label for="fullname">Nome Completo:</label>
                    <input type="text" id="fullname" name="fullname" class="form-control" required autofocus>
                </div>
                <div class="form-group position-relative">
                    <label for="email">E-mail:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="form-group position-relative">
                    <label for="username">Nome de Usuário:</label>
                    <input type="text" id="username" name="username" class="form-control" required>
                </div>
                <div class="form-group position-relative">
                    <label for="password">Senha:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                    <span class="toggle-password" onclick="togglePasswordVisibility('password', this)">
                        <i class="fa fa-eye"></i>
                    </span>
                </div>
                <div class="form-group position-relative">
                    <label for="confirm-password">Confirmação de Senha:</label>
                    <input type="password" id="confirm-password" name="confirm-password" class="form-control"
                        required>
                    <span class="toggle-password" onclick="togglePasswordVisibility('confirm-password', this)">
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

        document.getElementById('register-form').addEventListener('submit', function (event) {
            event.preventDefault();
            var successMessage = document.getElementById('success-message');
            var password = document.getElementById('password').value;
            var confirmPassword = document.getElementById('confirm-password').value;

            var formFields = document.querySelectorAll('#register-form input');
            var formValid = true;
            formFields.forEach(function (field) {
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
                return;
            }

            successMessage.classList.remove('d-none');
            successMessage.textContent = 'Cadastro concluído!';
            alert("Usuário cadastrado com sucesso!");
            window.location.href = "login.html";

            var form = event.target;
            form.reset();

            setTimeout(function () {
                successMessage.classList.add('d-none');
            }, 3000);
        });
    </script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">


</body>

</html>
