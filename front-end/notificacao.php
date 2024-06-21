<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <title>Alertas e Lembretes de Vacinação</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }
        main {
            padding: 20px;
        }
        .alertas-container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        .alerta-item {
            margin-bottom: 10px;
        }
        .lembretes-container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .lembrete-item {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container">
        <a class="navbar-brand ms-2" href="#"><img src="./img/zegotinha.jpeg" width="80px" alt="Logo"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link text-white fw-bold" href="./">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="?page=agendamento">Agendamento</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


    <header>
        <h1>Alertas e Lembretes de Vacinação</h1>
    </header>
    <main>
        <div class="alertas-container">
            <h2>Próximas Vacinas</h2>
            <div id="alertas-lista">
                <!-- Aqui serão inseridos os alertas de vacinação -->
            </div>
        </div>
        <div class="lembretes-container">
            <h2>Lembretes de Vacinação</h2>
            <div id="lembretes-lista">
                <!-- Aqui serão inseridos os lembretes de vacinação -->
            </div>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Sua Empresa. Todos os direitos reservados.</p>
    </footer>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const alertasLista = document.getElementById('alertas-lista');
            const lembretesLista = document.getElementById('lembretes-lista');

            // Simulando dados de alertas de vacinação
            const alertasData = [
                { id: 1, data: '01/08/2024', vacina: 'Vacina A' },
                { id: 2, data: '10/08/2024', vacina: 'Vacina B' },
                { id: 3, data: '15/08/2024', vacina: 'Vacina C' },
            ];

            // Simulando dados de lembretes de vacinação
            const lembretesData = [
                { id: 1, data: '30/07/2024', vacina: 'Vacina D' },
                { id: 2, data: '05/08/2024', vacina: 'Vacina E' },
                { id: 3, data: '12/08/2024', vacina: 'Vacina F' },
            ];

            // Função para exibir os alertas de vacinação na tela
            function exibirAlertas() {
                alertasLista.innerHTML = '';
                alertasData.forEach(alerta => {
                    const item = document.createElement('div');
                    item.classList.add('alerta-item');
                    item.innerHTML = `<strong>Próxima vacina:</strong> ${alerta.vacina} - <strong>Data:</strong> ${alerta.data}`;
                    alertasLista.appendChild(item);
                });
            }

           
            function exibirLembretes() {
                lembretesLista.innerHTML = '';
                lembretesData.forEach(lembrete => {
                    const item = document.createElement('div');
                    item.classList.add('lembrete-item');
                    item.innerHTML = `<strong>Lembrete:</strong> ${lembrete.vacina} - <strong>Data:</strong> ${lembrete.data}`;
                    lembretesLista.appendChild(item);
                });
            }

            exibirAlertas();
            exibirLembretes();
        });
    </script>
</body>
</html>
