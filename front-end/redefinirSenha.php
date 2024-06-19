<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperação de Senha</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
           body {
            background-color: #f0f5f9;
            font-family: Arial, sans-serif;
        }

        .header {
            background-color: #007bff;
            color: white;
            padding: 10px 0;
        }

        .header img {
            width: 70px;
        }

        .header h1 {
            margin: 0;
        }

        .recovery-container {
            max-width: 400px;
            width: 100%;
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
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-4 col-6 d-flex justify-content-center">
                    <img class="mb-2" src="./img/zegotinha.jpeg" alt="logo">
                </div>
                <div class="col-md-4 col-12 d-flex justify-content-center"></div>
                <div class="col-md-4 col-6 d-flex justify-content-end">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button" id="userMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user me-2"></i>
                        </button>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userMenuButton">
                            <li><a class="dropdown-item" href="./login.php">Login</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container d-flex justify-content-center align-items-center" style="min-height: 65vh;">
        <div class="recovery-container p-4 bg-white rounded shadow">
            <h1 class="mb-4 text-center">Redefinir Senha</h1>
            <form onsubmit="enviar(event)">
                <div class="form-group">
                    <label for="email">E-mail cadastrado:</label>
                    <input type="email" id="email" name="email" class="form-control" required autofocus>
                </div>
                <br>
                <button type="submit" class="btn btn-primary btn-block">Enviar</button>
            </form>
            <div id="mensagem-recuperacao" class="mt-4"></div>
        </div>
    </div>

    <script>
        function enviar(event) {
            event.preventDefault();
            var mensagemDiv = document.getElementById("mensagem-recuperacao");
            mensagemDiv.innerHTML = '<div class="alert alert-success" role="alert">Recuperação de senha enviada com sucesso!</div>';
            var emailInput = document.getElementById("email");
            emailInput.value = "";
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>

</body>

</html>