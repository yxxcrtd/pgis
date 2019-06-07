<html>

<head>

</head>
<body>

<style>

    body {
        margin: 0px;
        border: 0px;
        padding: 0px;
    }

    div {
        margin: 0px;
        border: 0px;
        padding: 0px;
        width: ${width}px;
        height: ${height}px;
        background-color: black;
    }

    p {
        margin: 0px;
        border: 0px;
        padding: 0px;
    }

    #root {
        display: table;
    }

    #cell {
        display: table-cell;
        vertical-align: middle;
    }
</style>

<div id="root">
    <div id="cell">
    </div>
</div>


<script>
    var content = ${content};
    function init(){
        var data = content.data;
        data = decodeURI(data);
        var domCell = document.getElementById("cell");
        domCell.innerHTML = data;
    }
    init();
</script>
</body>

</html>
