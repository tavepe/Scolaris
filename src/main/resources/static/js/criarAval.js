$(document).ready(function () {


    $("#formAval").submit(function (event) {

        event.preventDefault();

        let turmaId = $('#turmaId').val();
        let titulo = $('#titulo').val();
        let valorTotal = $('#valor').val();
        let descricao = $('#descricaoAval').val();

        let nota = {
            turmaId: turmaId,
            titulo: titulo,
            valorTotal: valorTotal,
            descricao: descricao
        };
        adicionarNota(nota);

    })
    function adicionarNota(nota) {
        var formValues = JSON.stringify(nota);

        $.ajax({
            url: 'http://localhost:8080/data/adicionarAval',
            method: 'POST',
            contentType: 'application/json',
            data: formValues
        })
            .done(function () {
                alert("Avaliação registrada com sucesso!")
                $("#titulo").val("");
                $("#valor").val("");
                $("#descricaoAval").val("");
                
            })
            .fail(function () {

                alert("Falha ao enviar os dados!")
            })

    }
}
)