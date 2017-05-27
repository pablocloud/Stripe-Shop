<h1>${order.id}</h1>
<ul>
    <g:each in="${order.items}">
        <li class="list-group-item">${it.description} - ${it.amount / 100} ${it.currency}</li>
    </g:each>
</ul>
<h3><g:message code="checkout.total"/> : ${order.amount / 100} ${order.currency}</h3>