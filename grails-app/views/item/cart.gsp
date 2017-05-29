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
                <g:message code="cart"/>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <g:if test="${items.size() > 0}">
                            <ul class="list-group">
                                <g:each in="${items}">
                                    <li class="list-group-item">
                                        ${com.stripe.model.Product.retrieve(it.product).name} - ${it.price / 100} ${it.currency}
                                        <g:link class="btn btn-danger" controller="item" action="removeFromCart" id="${it.id}"><g:message code="cart.remove"/></g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </g:if>
                        <g:else>
                            <g:message code="cart.empty"/>
                        </g:else>
                    </div>
                </div>
            </div>
            <div class="panel-footer">
                <g:if test="${items.size() > 0}">
                    <g:link controller="item" action="checkout" class="btn btn-warning"><g:message
                            code="cart.checkout"/></g:link>
                </g:if>
                <g:else>
                    <g:link controller="item" action="checkout" class="btn btn-warning disabled"><g:message
                            code="cart.checkout"/></g:link>
                </g:else>
            </div>
        </div>
    </div>
</div>
</body>
</html>