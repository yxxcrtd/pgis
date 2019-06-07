<html>

<head>

</head>
<body>

<style>


    /*   @font-face {
           font-family: kaiti_GB2312;
           src: url('/kaiti_GB2312.ttf')
       }*/

    body {
        margin: 0px;
        border: 0px;
        padding: 0px;
    }

    .span_class {
        line-height: 1.2;
    }

    .content_class {
        margin: 0px;
        border: 0px;
        padding: 0px;
        /* font-family: "kaiti_GB2312";*/
        width: ${width}px;
        height: ${height}px;
        background-color: black;

    }

    p {
        margin: 0px;
        border: 0px;
        padding: 0px;
    }

    marquee {
        width: ${width}px;
        height: ${height}px;
        margin: 0px;
        border: 0px;
        padding: 0px;
        align: middle;
        line-height: ${height}px;
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

    /**
     */
    function changeContent() {
        if (program) {
            var showType = program.showType;
            var content = program.content;
            //替换dom html信息
            var div = document.getElementById("cell");
            if (!showType) {
                div.innerHTML = content[0];
            } else if (showType == 1) {
                var interval = program.interval ? program.interval : 100;
                var step = program.step ? program.step : 1;
                var marqueeDom = document.createElement("marquee");
                marqueeDom.scrollDelay = interval;
                marqueeDom.scrollAmount = step;
                marqueeDom.loop = -1;
                marqueeDom.behavior = 'scroll';
                marqueeDom.direction = 'left';
                marqueeDom.innerHTML = content[0];
                div.innerHTML = "";
                div.appendChild(marqueeDom);
            } else {
                var interval = program.interval ? program.interval : 2000;
                var index = 1;
                var len = content.length;
                div.innerHTML = content[0]
                setInterval(function () {
                    div.innerHTML = content[index];
                    index = (index + 1) % len;
                }, interval)
            }
        }

        //
        var divList = document.getElementsByTagName("div");
        var spanList = document.getElementsByTagName("span");

        if (divList) {
            for (var i = 0; i < divList.length; i++) {
                var div = divList[i];
                div.className = "content_class";
            }
        }
        if (spanList) {
            for (var i = 0; i < spanList.length; i++) {
                var span = spanList[i];
                span.className = "span_class";
            }
        }
    }

    var program = ${program};
    changeContent();
</script>

</body>
</html>
