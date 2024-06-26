<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/notificacao.css">
    <title>Alertas e Lembretes de Vacinação</title>
    <style>
        .alerta-item.alerta-urgente {
            border: 2px solid red;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container">
        <a class="navbar-brand ms-2" href="./"><img src="./img/zegotinha.jpeg" width="80px" alt="Logo"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link text-white fw-bold" href="./">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="menuAgendamento">Consulta Agendamento</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="agendamento">Agendamento</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white fw-bold" href="lembrete">Lembrete</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<h2 class="text-center mt-4">Lembrete</h2>

<main>
    <div class="alertas-container">
        <h2>Próximas Vacinas</h2>
        <div id="alertas-lista"></div>
    </div>
</main>

<!-- Modal para captura do CPF Dos idosos aqui-->
<div class="modal fade" id="cpfModal" tabindex="-1" aria-labelledby="cpfModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cpfModalLabel">Digite seu CPF</h5>
            </div>
            <div class="modal-body">
                <form id="cpfForm">
                    <div class="mb-3">
                        <label for="cpfInput" class="form-label">CPF:</label>
                        <input type="text" class="form-control" id="cpfInput" name="cpfInput">
                    </div>
                    <button type="submit" class="btn btn-primary">Buscar</button>
                    <a href="./" class="btn btn-danger">Cancelar</a>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
document.addEventListener('DOMContentLoaded', function() {
    const cpfModal = new bootstrap.Modal(document.getElementById('cpfModal'), {
        backdrop: 'static',
        keyboard: false
    });
    cpfModal.show();

    const cpfForm = document.getElementById('cpfForm');
    const cpfInput = document.getElementById('cpfInput');

    cpfForm.addEventListener('submit', function(event) {
        event.preventDefault();
        let cpf = cpfInput.value.replace(/\D/g, '');
        console.log('CPF:', cpf);

        fetch('http://localhost:3000/vacinas?cpf=' + cpf)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(error => { throw new Error(error.message); });
                }
                return response.json();
            })
            .then(data => {
                const alertasLista = document.getElementById('alertas-lista');
                alertasLista.innerHTML = '';

                if (Array.isArray(data)) {
                    data.forEach(vacina => {
                        const alertaItem = document.createElement('div');
                        alertaItem.className = 'alerta-item';
                        
                        const dataHoraVisita = new Date(vacina.data_hora_visita);
                        const dataFormatada = dataHoraVisita.toLocaleDateString();
                        const horaFormatada = dataHoraVisita.toLocaleTimeString();
                        
                        // Verifica se falta exatamente 1 dia para a visita
                        const umDiaAntes = new Date();
                        umDiaAntes.setDate(umDiaAntes.getDate() + 1);
                        
                        if (dataHoraVisita <= umDiaAntes) {
                            alertaItem.classList.add('alerta-urgente');
                        }
                        
                        alertaItem.innerHTML = `<i class="fas fa-syringe"></i> Vacina ${vacina.nome}, Data: ${dataFormatada}, Horário: ${horaFormatada}`;
                        alertasLista.appendChild(alertaItem);
                    });
                } else if (data.message) {
                    console.error('Mensagem do backend:', data.message);
                    alert(data.message);
                    window.location.href='./';
                } else {
                    alert('Ocorreu um erro ao processar os dados.');
                }
                
                cpfModal.hide();
            })
            .catch(error => {
                console.error('Erro:', error);
                alert(error.message);
                cpfInput.value = ''; 
            });
    });

    cpfInput.addEventListener('input', function() {
        let cpf = cpfInput.value.replace(/\D/g, ''); 
        if (cpf.length > 11) {
            cpf = cpf.slice(0, 11); 
        }
        cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); 
        cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); 
        cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2'); 
        cpfInput.value = cpf;
    });
});
</script>


<footer class="footer">
    <div class="container">
        <span>© 2024 Todos os direitos reservados.</span>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
