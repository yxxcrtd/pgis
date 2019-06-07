<body id="body">





<style>

  /*  @font-face {
        font-family: igoosd;
        src: url('/YaHei.Consolas.1.12.ttf')
    }*/

    body{
        margin: 0px;
        border: 0px;
        padding: 0px;
        background:black;
    }

    div {
        margin: 0px;
        border: 0px;
        padding: 0px;
        width: 32px;
        height: 16px;
        font-size: 16px;
/*
        font-family: "igoosd";
*/
        font-weight: bolder;
        text-align: center;
        line-height:16px;
    //letter-spacing:1px;
    }

</style>

<script>


    /**
     *	改变
     **/
    function changeText(screenId,car_num) {

        var divDom = document.getElementById('div_'+screenId);
        modifyDom(divDom,car_num);

    }

    /**自动补零**/
    function PrefixInteger(num, n) {
        return (Array(n).join(0) + num).slice(-n);
    }

    //获取请求参数
    function getParams() {
        var url = location.search; //获取url中"?"符后的字串
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = strs[i].split("=")[1];
            }
        }
        return theRequest;
    }

    var WARN_NUM = ${warnNum};
    //修改颜色和内容
    function modifyDom(dom,content){
        content = (!content || content < 0)? 0 : Number(content);
        dom.textContent = PrefixInteger(content,3);//补零处理
        if(content <= 0){
            dom.style.color = 'red';
        }else if(content <= WARN_NUM){
            dom.style.color = 'yellow';
        }else{
            dom.style.color = 'green';
        }
    }

</script>


<#list parkingList as parking>
 <div id="div_${parking.id}" style="color: ${parking.color};">
     ${parking.lotRemainCount}
 </div>
</#list>

</body>