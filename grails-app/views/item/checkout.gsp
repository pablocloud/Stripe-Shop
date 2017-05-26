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
                <g:message code="checkout.resume"/>
            </div>
            <div class="panel-body">
                <ul class="list-group">
                    <g:each in="${order.items}">
                        <li class="list-group-item">${it.description} - ${it.amount / 100} ${it.currency}</li>
                    </g:each>
                </ul>
                <h3><g:message code="checkout.total"/> : ${order.amount/100} ${order.currency}</h3>
            </div>
            <div class="panel-footer">
                <g:form controller="item" action="doPay" id="${order.id}" method="POST">
                    <script
                            src="https://checkout.stripe.com/checkout.js" class="stripe-button"
                            data-key="${publicKey}"
                            data-amount="${order.amount}"
                            data-name="${com.stripe.model.Account.retrieve().displayName}"
                            data-image="https://stripe.com/img/documentation/checkout/marketplace.png"
                            data-locale="auto"
                            data-zip-code="true"
                            data-currency="${order.currency}">
                    </script>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>