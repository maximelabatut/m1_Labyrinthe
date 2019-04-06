<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 22/12/2017
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>

<h1>Score de la partie de <s:property value="pseudoHost"/> :<br></h1>

<s:iterator value="listeDesJoueurs">
    Pseudo : <s:property value="pseudo"/><br>
    Nombre de tresor restant : <s:property value="mesCartes.length"/><br><br>
</s:iterator>

<s:a action="menuPrincipal">Menu Principal</s:a>
</body>
</html>
