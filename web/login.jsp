<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <script>
        window.onload = function(){
        	document.getElementById("img").onclick = function(){
        		this.src="/day14.checkCodeServlet?time="+new Date().getTime();
        	}
        }
    </script>
    <style>
        div{
        	color:blue;
        }
    </style>
    
</head>
<body>
<form action="/day14/loginServlet" method="post">
  <table>
      <tr>
         	<td>用户名</td>
		   <td><input type="text" name="username"></td>      
      </tr>
      <tr>
          <td>密码 </td>
          <td><input type="password" name="password"></td>
      </tr>
      <tr>
          	<td>验证码</td>
            <td><input type="text" name="checkCode"></td>
      </tr>
      <tr>
          <td colspan="2"><img id="img" src="/day14/checkCodeServlet"></td>
      </tr>
      <tr>
          <td clospan="2"><imput type="submit" value="登录"></td>
      </tr>
  </table>
</form>

      <div><%=request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error")%></div>
      <div><%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_eroor")%></div>

</body>
</html>