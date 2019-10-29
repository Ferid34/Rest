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
</head>
<body>
<h1>Bu sehife errlari handle etmek ucundur</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Age</th>
        <th>Action</th>
    </tr>

</table>
</body>

<script>
    $(document).ready(function () {
        console.log("ferid");
        $.ajax({
            url:'/rest/adams',
            succes:function (data) {

           console.log("datam "+data);

            }
        });
    })
</script>

</html>
