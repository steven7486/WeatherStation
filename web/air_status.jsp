<%-- 
    Document   : air_status
    Created on : 2016/06/09, 18:27:08
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" type="text/css" rel="StyleSheet" />
        <link href="css/custom.css" type="text/css" rel="StyleSheet" />
        <link href="css/blade_anim.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Air status panel -->
        <br/>
   
            <div class="panel">
            <div class="ws-panel-title" >
                <h4>
                    Atmosphere
                </h4>
                <ul class="nav navbar-right panel-tool">

                    <li>
                        <a href="#" role="button" class="dropdown-toggle a-button" data-toggle="dropdown">
                            <i class="fa fa-wrench"></i>
                        </a>
                    </li>


                </ul>
            </div>
            <div class="panel-body" >

                <div style="height: 150px; width: 125px;position: relative; float: left">
                    <div style="height: 136px; width: 70px; position: relative;float: left">
                    <div class="blade-fast-rotate"></div>
                    <div class="bar-big"></div>
                    </div>
                    <div style="height: 136px; width: 50px; position: relative; float: left; left: -18px;">
                    <div class="blade-slow-rotate"></div>
                    <div class="bar-sm"></div>
                    </div>
                </div>
                <div >
                    Data test str.
                </div>
            </div>
        </div>
   
    </body>
</html>
