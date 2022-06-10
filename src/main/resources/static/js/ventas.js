/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
$('document').ready(function (){
    $('.edicion').on('click',function (event){
        event.preventDefault();
        var href=$(this).attr('href');
        $.get(href, function (producto,status){
            console.log("codigo producto: "+producto.codigo);
            $('#codigoEditar').val(producto.codigo);
        });
    $('#editModal').modal("show");    
    });
});


