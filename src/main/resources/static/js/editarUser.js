$(document).ready(function () {


    $("#formUser").submit(function (event) {

        event.preventDefault();

        let id = $('#userId').val();
        let nome = $('#nome').val();
        let usuario = $('#usuario').val();
        let senha = $('#senha').val();
        let tipo = $('#userTipo').val();

        let user = {
            id:id,
            nome: nome,
            usuario: usuario,
            senha: senha,
            tipo: tipo
        };
        adicionarUser(user);

    })
    function adicionarUser(user) {
        var formValues = JSON.stringify(user);

        $.ajax({
            url: 'http://localhost:8080/data/atualizarUser/' + user.id,
            method: 'PUT',
            contentType: 'application/json',
            data: formValues
        })
            .done(function () {
                alert("User salvo com sucesso!")
                window.location = "/verUsers"
            })
            .fail(function () {

                alert("Falha ao enviar os dados, tente novamente!")
            });
    }
})