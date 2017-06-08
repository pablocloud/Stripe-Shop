<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<ul>
    <g:each in="${charges}">
        <li>${it.id}</li>
    </g:each>
</ul>
</body>
</html>