<%--
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: toshiba
  Date: 10/27/19
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <style>
        #adam-list, th, td {
            border: 1px solid red;


        }

        .rows {
            margin-left: 50px;
        }
    </style>
</head>
<body>
<h1>Bu sehife errlari handle etmek ucundur</h1>

<button onclick="addAdam()"> Insert new  Adam </button>


<table id="adam-list">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Age</th>
        <th>Action</th>
    </tr>

</table>

<div id="adam" style="display: none">
    <label>ID</label><span id="adam_id" class="rows"></span><br/>
    <label>NAME</label><span id="adam_name" class="rows"></span><br/>
    <label>AGE</label><span id="adam_age" class="rows"></span><br/>
</div>
<div id="upd_adam" style="display: none">
    <form>&lt;%&ndash; fromun action i yoxdur cunki her json ile gedir&ndash;%&gt;
        <label style="width: 100px">ID</label><input type="text" id="upd_adam_id" class="rows" readonly/><br/>
        <label>NAME</label><input type="text" id="upd_adam_name" class="rows"/><br/>
        <label>AGE</label><input type="text" id="upd_adam_age" class="rows"/><br/>
    </form>
</div>
<div id="add_adam" style="display: none">
    <form>
        <label>NAME</label><input type="text" id="add_adam_name" class="rows"/><br/>
        <label>AGE</label><input type="text" id="add_adam_age" class="rows"/><br/>
    </form>
</div>


</body>

<script>
    var baseUrl = 'http://localhost:8080/rest/';

    function clearTable() {
        $("#adam-list").empty();
        $("#adam-list").append(
            "<thead><tr>" +
            "<th>ID</th>" +
            "<th>NAME</th>" +
            "<th>AGE</th>" +
            "<th>Action</th>" +
            "</tr></thead>"
        )
    }

    function createDialog() {
        // view dialog ucun
        $("#adam").dialog({
            autoOpen: false,
            height: 400,
            width: 350,
            modal: true
        });
        //update dilaog ucun
        $("#upd_adam").dialog({
            autoOpen: false,
            height: 200,
            width: 450,
            modal: true,
            buttons: {
                "Save": function () {
                    //todo read data from input, validate data, if(valid) send ajax PUT request ,else show Validation error
                    console.log("Save kliklendi");
                    var update_olunacaq_adam = {
                        "id": $('#upd_adam_id').val(),
                        "name": $('#upd_adam_name').val(),
                        "age": $('#upd_adam_age').val()
                    };
                    var json = JSON.stringify(update_olunacaq_adam);
                    console.log("json =" + json);

                    $.ajax({
                        url: baseUrl + 'adam',
                        method: 'PUT',
                        contentType: 'application/json',
                        data: json,
                        success: function (data, textStatus, errorThrown) {
                            showAdamList();
                            $("#upd_adam").dialog("close");
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert(textStatus + " " + errorThrown);
                            $("#upd_adam").dialog("close");
                        }


                    })


                },
                "Cansel": function () {
                    console.log("Diloag baglandi");
                    $('#upd_adam').dialog("close");
                }
            }
        });

        //add adam dialog
    $("#add_adam").dialog({
        autoOpen: false,
        height: 200,
        width: 450,
        modal: true,
        buttons: {
            "Save": function () {
                console.log("add kliklendi");
                var add_olunacaq_adam = {
                    "name": $('#add_adam_name').val(),
                    "age": $('#add_adam_age').val()
                };
                var json = JSON.stringify(add_olunacaq_adam);
                console.log("json =" + json);

                $.ajax({
                    url: baseUrl + 'adam',
                    method: 'POST',
                    contentType: 'application/json',
                    data: json,
                    success: function (data, textStatus, errorThrown) {
                        showAdamList();
                        $("#add_adam").dialog("close");
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(textStatus + " " + errorThrown);
                        $("#add_adam").dialog("close");
                    }


                })


            },
            "Cansel": function () {
                console.log("Diloag baglandi");
                $('#add_adam').dialog("close");
            }
        }
    });




    }


    //id sine adami detalli gostermek function u
    function showAdam(adam_id) {
        $.ajax({
            url: baseUrl + adam_id,
            method: 'GET',
            success: function (data) {
                $('#adam_id').html(data.id);
                $('#adam_name').html(data.name);
                $('#adam_age').html(data.age);
                //$('#adam').show();
                $('#adam').dialog("open");
            }
        })
    }

    function editAdam(adam_id) {
        $.ajax({
            url: baseUrl + adam_id,
            method: 'GET',
            success: function (data) {
                $('#upd_adam_id').val(data.id);
                $('#upd_adam_name').val(data.name);
                $('#upd_adam_age').val(data.age);
                //$('#adam').show();
                $('#upd_adam').dialog("open");
            }
        })
    }

    function deleteAdam(adam_id) {
        if (confirm('Silmeye eminsinizmi')) {
            $.ajax({
                url: baseUrl + 'adam/' + adam_id,
                method: 'DELETE',
                success: function (data) {
                    showAdamList();
                }, error: function (jqXhr, textStatus, errorThrown) {
                    alert(textStatus + " " + errorThrown);
                }
            });
        }
    }


    //Butun adamlari getirir table da gosterir.Wiev e basanda id sibne gore dami detalli gosterir.
    function showAdamList() {

        $.ajax({
            url: baseUrl,
            success: function (data) {
                clearTable();
                for (var i = 0; i < data.length; i++) {
                    $('#adam-list').append('<tr>' +
                        '<td>' + data[i].id + '</td>' +
                        '<td>' + data[i].name + '</td>' +
                        '<td>' + data[i].age + '</td>' +
                        '<td>' +
                        '<a onclick=\'showAdam(' + data[i].id + ')\'>View</a> ' +
                        '<a onclick=\'editAdam(' + data[i].id + ')\'>Edit</a> ' +
                        '<a onclick=\'deleteAdam(' + data[i].id + ')\'>Delete</a> ' +
                        '</td>' +
                        '</tr>');
                }
            }
        });
    }

    function addAdam(){
        $("#add_adam").dialog("open");
    }

    $(document).ready(function () {
        showAdamList();
        createDialog();
    });



</script>

</html>
--%>
