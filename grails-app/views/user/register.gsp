<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"><g:message code="users.register"/></div>
            <g:form controller="user" action="doRegister">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="email"><g:message code="users.email"/></label>
                        <input type="email" id="email" name="email" required class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="password"><g:message code="users.email"/></label>
                        <input type="password" id="password" name="password" class="form-control"/>
                    </div>
                </div>
                <div class="panel-footer">
                    <input type="submit" value="<g:message code="users.register"/>" class="btn btn-default"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>