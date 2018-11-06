<%-- 
    Document   : index
    Created on : Nov 4, 2018, 4:05:02 PM
    Author     : xinranchen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="index.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
		function startTime(){
			var today=new Date();
			var h=today.getHours();
			var m=today.getMinutes();
			var s=today.getSeconds();
			m=checkTime(m);
			s=checkTime(s);
			document.getElementById('txt').innerHTML=h+":"+m+":"+s;
			t=setTimeout('startTime()',500);
		}

		function checkTime(i){
			if (i<10){
  			    i="0" + i;
                        }
  			return i;
		}
	</script>
    </head>
    <body onload="startTime()">

        <h1>Please submit your personal information.</h1>
        <form method ="post" action = "submit">
            <table style="margin: auto">
                <tbody>
                    
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="sender" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="title" value="" size="50" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="message" value="" size="50"></td>
                    </tr>
                    <tr>
                        <td>Content:</td>
                        <td><input type="text" name="cont" value="" size="50"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Submit" name="Submit" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><a href ="login">Already registered? Please log in.</a></td>
                    </tr>
                </tbody>
            </table>
        </form><br/>
        <div id="txt"></div>
    </body>
</html>
