<%--
  Created by IntelliJ IDEA.
  User: toshiba
  Date: 10/27/19
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <style>
        #adam-list,th,td{
           border: 1px solid red;


        }
    </style>
</head>
<body>
<h1>Bu sehife errlari handle etmek ucundur</h1>
<table id="adam-list">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Age</th>
        <th>Action</th>
    </tr>

</table>

<div id ="adam" style="display: none">
    ID:<span id="adam_id"></span><br/>
    AD:<span id="adam_name"></span><br/>
    Yas:<span id="adam_age"></span><br/>
</div>




</body>

<script>
    function showAdam(adam_id){
        $.ajax({
            url: '/rest/adams/'+adam_id,
            success:function (data) {
                $('#adam_id').html(data.id);
                $('#adam_name').html(data.name);
                $('#adam_age').html(data.age);
                $('#adam').show();
            }
        })
    }
    $(document).ready(function () {






        $.ajax({
            url: '/rest/adams',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $('#adam-list').append('<tr>' +
                        '<td>' + data[i].id+'</td>'+
                        '<td>' + data[i].name+'</td>'+
                        '<td>' + data[i].age+'</td>'+
                        '<td><a onclick=\'showAdam('+data[i].id+')\'>View</a> </td>'+
                    '</tr>');
                   // console.log(data[i].id + " " + data[i].name + " " + data[i].age);
                }


            }
        });

    })
</script>

</html>
