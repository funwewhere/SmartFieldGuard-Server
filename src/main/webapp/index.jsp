<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<script type="text/javascript" src="jquery-1.11.1.min.js"></script>
<body>
${data1},${data2},${data3}<br>
<form action="/SZTY/user/login" method="post">
<input type="text" name="username">
<input type="password" name="password">
<input type="submit" value="submit">
</form>
<a href="http://localhost:8080/Web_Test/test1">test1</a><br>
<a href="http://localhost:8080/Web_Test/test2">test2</a><br>
<a href="http://localhost:8080/Web_Test/test3">test3</a><br>

<input type="button" onclick="login()" value="login"/>
<input type="button" onclick="logout()" value="logout"/>
<input type="button" onclick="judgeLogin()" value="judgeLogin"/><br/><br/>
<input id="test" type="text"/>
<input type="button" onclick="enum1()" value="enum1"/>
</body>
<script src="//cdn.bootcss.com/sockjs-client/1.0.3/sockjs.js"></script>
<script type="text/javascript">

		function enum1(){
			$.ajax({
				type:'post',
				url:'post/list',
				data:{"dataStart":0,"pageCount":65535,"pageIndex":1,"parentArea":"","status":"","type":"Expert","userId":"US001605290001"},
				success:function(data){
					console.log(data);
				}
			});
		}

		function test(){
			var a = $(".test").val();
			$.ajax({
				type:'post',
				url:'crop/crops',
				data:'cropTypeNo=000001',
				success:function(data){
					console.log(data);
				}
			});
		}


		function login(){
			$.ajax({
				type:'post',
				url:'user/login',
				//contentType:'application/json;charset=utf-8',
				data:'username=漆原&password=777uuu',
				success:function(data){
					console.log(data);
				}
			});
		}
		
		function logout(){
			$.ajax({
				type:'post',
				url:'user/logout',
				data:'pageIndex=1&pageCount=3',
				success:function(data){
					alert(data);
				}
			});
		}
		
		function judgeLogin(){
			$.ajax({
				type:'post',
				url:'post/list',
				data:'pageIndex=1&pageCount=6&status=Unsolved',
				success:function(data){
					alert(data);
				}
			});
		}
	</script>
 <script>
     var websocket;
     if ('WebSocket' in window) {
         websocket = new WebSocket("ws://localhost:8080/Web_Test/webSocketServer");
     } else if ('MozWebSocket' in window) {
         websocket = new MozWebSocket("ws://localhost:8080/Web_Test/webSocketServer");
     } else {
         websocket = new SockJS("http://localhost:8080/Web_Test/sockjs/webSocketServer");
     }
     websocket.onopen = function (evnt) {
     };
     websocket.onmessage = function (evnt) {
         //$("#msgcount").html("(<font color='red'>"+evnt.data+"</font>)")
         alert(evnt.data);
     };
     websocket.onerror = function (evnt) {
     };
     websocket.onclose = function (evnt) {
     }

 </script>
</html>
