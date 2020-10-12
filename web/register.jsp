<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <script>
        window.onload = function(){
            //获取图片对象
            var img = document.getElementById("checkCode");
            //绑定单击事件
            img.onclick = function(){
                var date = new Date().getTime();
                img.src = "/day14/checkCodeServlet?"+date;
            }
        }
    </script>
</head>
<body>
    <img id="checkCode" src="/day14/checkCodeServlet"/>
    <a id="change" href="">看不清，换一张</a>
</body>
</html>