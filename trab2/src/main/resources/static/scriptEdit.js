// *******************************************************************************
// ***  SOMENTE A PARTIR DESTE PONTO VOCÊ PODERÁ ALTERAR O CÓDIGO
// *******************************************************************************


function enviaEdit(id) {
    let dto = {
        id: id,
        turma: document.getElementById('turma').value,
        nome: document.getElementById('nome').value,
        matricula: document.getElementById('matricula').value,
        nota: document.getElementById('nota').value
    };
    let json = JSON.stringify(dto);
    let request = new XMLHttpRequest();
    request.open('POST', '/updateAluno', true);
    request.setRequestHeader("Content-Type", "application/json");
    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                window.location.href = '/';
            } else {
                console.error('Erro ao atualizar aluno: ', request.responseText);
                alert('Erro ao atualizar aluno. Por favor, tente novamente.');
            }
        }
    };
    request.send(json);
    console.log(json);
}

function deleteAluno(id) {
    let payload = JSON.stringify({ id: id });
    let request = new XMLHttpRequest();
    request.open('POST', '/delete', true);
    request.setRequestHeader("Content-Type", "application/json");
    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status === 200) {
                window.location.href = '/';
            } else {
                console.error('Erro ao deletar aluno: ', request.responseText);
                alert('Erro ao deletar aluno. Por favor, tente novamente.');
            }
        }
    };
    request.send(payload);
}