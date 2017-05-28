<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <g:each in="${products.data}">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    ${it.name} - ${it.skus.data.first().price / 100} ${it.skus.data.first().currency}
                </div>
                <div class="panel-body">
                    <g:if test="${it.images.size() > 0}">
                        <img width="100%" src="${it.images.first()}">
                    </g:if>
                    <g:else>
                        ${it.description}
                    </g:else>
                </div>
                <div class="panel-footer">
                    <g:link controller="item" action="show" id="${it.id}" params="[name: it.name]"
                            class="btn btn-primary"><g:message code="products.seeDetails"/></g:link>
                </div>
            </div>
        </div>
    </g:each>
</div>
</body>
</html>