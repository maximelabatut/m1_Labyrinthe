<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Arthur-PC
  Date: 19/12/2017
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Labyrinthe - TDL</title>
    </head>
    <body>

        <!-- Ne marche pas -->
        <s:if test="#session.login.equals(pseudoGagnant)">
            <h1>Bravo vous avez Gagné</h1>
        </s:if>
        <s:else>
            <h1>Dommage vous avez Perdu</h1>
        </s:else>


        <h2>Score de la partie de <s:property value="pseudoHost"/> :<br></h2>

        <s:iterator value="listeDesJoueurs">
            Pseudo : <s:property value="pseudo"/><br>
            Nombre de tresor restant : <s:property value="mesCartes.length"/><br><br>
        </s:iterator>

        <s:a action="menuPrincipal">Menu Principal</s:a>
    </body>
</html>
