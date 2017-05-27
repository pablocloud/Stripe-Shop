<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <g:message code="checkout.resume"/> - <g:message code="checkout.status"/> ${order.status}
            </div>
            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${order.items}">
                        <li class="list-group-item">${it.description} - ${it.amount / 100} ${it.currency}</li>
                    </g:each>
                </ul>
                <h3><g:message code="checkout.total"/> : ${order.amount / 100} ${order.currency}</h3>
            </div>
            <div class="panel-footer">
                <g:link controller="item" action="doSendPayedEmail" id="${order.id}"><g:message
                        code="checkout.sendMail"/></g:link>
            </div>
        </div>
    </div>
</div>
</body>
</html>