$(document).ready(function () {


    $("#formUser").submit(function (event) {

        event.preventDefault();

        let nome = $('#nome').val();
        let usuario = $('#usuario').val();
        let senha = $('#senha').val();
        let tipo = $('input[name="tipoUser"]:checked').val();

        let user = {
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
            url: 'http://localhost:8080/data/adicionarUser',
            method: 'POST',
            contentType: 'application/json',
            data: formValues
        })
            .done(function () {
                alert("User registrado com sucesso!")
                $("#nome").val("");
                $("#usuario").val("");
                $("#senha").val("");
                $("#professor").prop('checked', false);
                $("#aluno").prop('checked', false);
            })
            .fail(function () {

                alert("Falha ao enviar os dados, tente novamente!")
            });
    }
})