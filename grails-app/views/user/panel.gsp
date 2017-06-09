<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading"><g:message code="users.lastOrders"/></div>
            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${charges}">
                        <g:link controller="item" action="payed" id="${it.order}" target="_blank"
                                class="list-group-item">${it.amount / 100} ${it.currency} - ${it.description}</g:link>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">

    </div>
</div>
</body>
</html>