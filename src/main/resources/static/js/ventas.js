/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
$('document').ready(function (){
    $('#editarCantidad').on('click',function (event){
        event.preventDefault();
        var href=$(this).attr('href');
        $get(href, function (producto,atributo){
            $('#codigoEditar').val(producto.codigo);
        });
    $('#editModal').modal();    
    });
});


