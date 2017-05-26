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
                ${product.name} - ${product.skus.data.first().price / 100} ${product.skus.data.first().currency}
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-3">
                        <g:if test="${product.images.size() > 0}">
                            <img width="100%" src="${product.images.first()}">
                        </g:if>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-9">
                        <g:message code="products.choices"/>
                        <ul class="list-group">
                            <g:each in="${product.skus.data}">
                                <li class="list-group-item">${it.price / 100} ${it.currency}</li>
                            </g:each>
                        </ul>
                        ${product.description}
                    </div>
                </div>
            </div>
            <div class="panel-footer">

            </div>
        </div>
    </div>
</div>
</body>
</html>