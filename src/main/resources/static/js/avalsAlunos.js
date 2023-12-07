$(document).ready(function () {
    carregarTurmas();
    function carregarTurmas() {
        let idTurma = $('#turmaId').val()
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

                    let imgView = $('<img>').attr('src', '../../images/view.png')

                    let botaoView = $('<button>');
                    botaoView.attr('class', 'botaoVizualizar')
                    botaoView.append(imgView)
                    botaoView.click(function () {
                        modalVisualizar($(this).parent().parent().attr('data-id'))
                    })
                    let colunaBotoes = $('<td>').append(botaoView);



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
                alert('Não foi possível carregar as avaliações!')
            }
        }

        )
    }

    function modalVisualizar(id) {
        let alunoId = $('#alunoId').val()
        $.ajax({
            url: 'http://localhost:8080/data/getAvaliacao/'+id ,
            method: 'GET',
            success: function (data) {
                $('#tituloAval').text(data.titulo)
                $('#descricaoAval').text(data.descricao)
                $('#valorTotal').text(data.valorTotal)
                $.ajax({
                    url: 'http://localhost:8080/data/getNotaAlunoAval/' + alunoId + '/' + id,
                    method: 'GET',
                    success: function (data) {
                        $('#notaAval').text(data.nota)
                        $('#recadoAval').text(data.recado)
                        $('#myModal').modal('show')
                    }
                })



            }
        })
        $("#botaoDeletarModal").click(function () {
            $('#myModal').modal('hide')
        })
    }

}
)