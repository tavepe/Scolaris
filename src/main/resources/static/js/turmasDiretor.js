$(document).ready(function () {
    carregarTurmas();
    function carregarTurmas() {
        $.ajax({
            url: 'http://localhost:8080/data/listarTurmas',
            method: 'GET',
            success: function (data) {
                $('#tabelaTurmas tbody').empty();

                for (let i = 0; i < data.length; i++) {
                    let turma = data[i];

                    let id = $('<td>')
                        .text(turma.id);

                    let titulo = $('<td>')
                        .text(turma.titulo);
                    let descricao = $('<td>')
                        .text(turma.descricao);

                    let imgDeleta = $('<img>').attr('src', '../images/trash.png')
                    let imgEdita = $('<img>').attr('src', '../images/edit.png')

                    let botaoApagar = $('<a>');
                    botaoApagar.attr('class', 'botaoApagar')
                    botaoApagar.append(imgDeleta);
                    botaoApagar.click(function () {
                        modalApagar($(this).parent().parent().attr('data-id'))
                    })
                    let botaoEditar = $('<a>').attr('href', '/editarTurmas/' + (turma.id));
                    botaoEditar.attr('class', 'botaoEditar')
                    botaoEditar.append(imgEdita)
                    let colunaBotoes = $('<td>').append(botaoApagar).append(botaoEditar);



                    let tr = $('<tr>')
                        .attr('data-id', turma.id)
                        .append(id)
                        .append(titulo)
                        .append(descricao)
                        .append(colunaBotoes)


                    $('#tabelaTurmas tbody').append(tr);
                }
            },
            error: function () {
                alert('Não foi possível carregar as turmas!')
            }
        }

        )
    }
    function modalApagar(id) {
        $('#modalBody').text('Tem certeza que deseja deletar a turma de id ' + id + '?')
        $("#botaoDeletarModal").click(function () {
            apagarTurma(id);
        })
        $('#myModal').modal('show')
    }
    function apagarTurma(id) {
        $.ajax({
            url: 'http://localhost:8080/data/deletarTurma/' + id,
            method: 'DELETE',
            success: function () {
                carregarTurmas()
                $('#myModal').modal('hide')
            },
            error: function () {

            }

        })
    }
}
)


