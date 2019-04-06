<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Bendoux
  Date: 27/11/2017
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>

<h1>Menu principal</h1>
    Vous êtes connecté en tant que : <s:property value="#session.login" />.<br/>
    La population est actuellement de <s:property value="population"/> personne(s). (<s:a action="menuPrincipal">Rafraichir</s:a>)</div>

    <ul>
        <li><s:a action="menuPartieCreation">Créer</s:a></li>
        <li> <s:a action="menuPartieRejoindre">Rejoindre</s:a></li>
        <li> <s:a action="menuPartieObserver">Observer</s:a></li>
        <li> <s:a action="menuInvitations">Invitations reçues</s:a></li>
    </ul>

    Voici la liste des joueurs connectés : <br>

    <s:iterator value="lesJoueursPopulation">
        <tr>
            • <s:property value="pseudo"/><br/>
        </tr>
    </s:iterator>
    <br><br>
    <s:a action="deconnexion">Déconnexion</s:a>
    <br><br>
    <s:a action="seDesinscrire">Se Désinscrire</s:a>
</body>

</html>
