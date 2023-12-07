$(document).ready(function () {
    carregarTurmas();
    function carregarTurmas() {
        let alunoId = $('#alunoId').val()
        $.ajax({
            url: 'http://localhost:8080/data/getTurmasAluno/' + alunoId,
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

                    let imgView = $('<img>').attr('src', '../images/view.png')
                    let imgEdita = $('<img>').attr('src', '../images/edit.png')

                    let botaoView = $('<a>');
                    botaoView.attr('class', 'botaoVizualizar')
                    botaoView.append(imgView);
                    botaoView.click(function () {
                        modalVisualizar($(this).parent().parent().attr('data-id'))
                    })
                    let botaoEditar = $('<a>').attr('href', '/avalAlunos/' + alunoId + '/' + (turma.id));
                    botaoEditar.attr('class', 'botaoEditar')
                    botaoEditar.append(imgEdita)
                    let colunaBotoes = $('<td>').append(botaoView).append(botaoEditar);



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
    function modalVisualizar(id) {
        $.ajax({
            url: 'http://localhost:8080/data/getTurma/' + id,
            method: 'GET',
            success: function (data) {
                $('#tituloTurma').text(data.titulo)
                $('#descricaoTurma').text(data.descricao)
                $.ajax({
                    url:'http://localhost:8080/data/getUser/'+data.professorId,
                    method:'GET',
                    success:function(data){
                        $('#professorTurma').text(data.nome)
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