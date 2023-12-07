$(document).ready(function () {
    carregarTurmas();
    function carregarTurmas() {
        let idTurma = $('#turmaId').val()
        let idProf=$('#profId').val()
        $.ajax({
            url: 'http://localhost:8080/data/listarAvalsTurma/' + idTurma,
            method: 'GET',
            success: function (data) {
                $('#tabelaAvals tbody').empty();
                for (let i = 0; i < data.length; i++) {
                    let aval = data[i];

                    let id = $('<td>')
                        .text(aval.id);

                    let titulo = $('<td>')
                        .text(aval.titulo);
                    let descricao = $('<td>')
                        .text(aval.descricao);
                    let valor = $('<td>')
                        .text(aval.valorTotal);

                    let imgEdita = $('<img>').attr('src', '../../images/edit.png')

                    let botaoEditar = $('<a>').attr('href', '/darNotas/' + idProf + '/'+ idTurma +'/'+ (aval.id));
                    botaoEditar.attr('class', 'botaoEditar')
                    botaoEditar.append(imgEdita)
                    let colunaBotoes = $('<td>').append(botaoEditar);



                    let tr = $('<tr>')
                        .attr('data-id', aval.id)
                        .append(id)
                        .append(titulo)
                        .append(descricao)
                        .append(valor)
                        .append(colunaBotoes)


                    $('#tabelaAvals tbody').append(tr);
                }
            },
            error: function () {
                alert('Não foi possível carregar as turmas!')
            }
        }

        )
    }

}
)
