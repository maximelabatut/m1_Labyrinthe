<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script type="text/javascript">
    function update() {
        window.location.reload();
    }
    setInterval(function() {update();}, 5000);
</script>
<s:if test="createur.equals(#session.login)">
    <s:a action="partieAnnule"><s:param name="pseudoHost" value="%{createur}"/><img src="../img/button_back.png" alt="<- Retour" width="80px"></s:a>
</s:if>
<s:else>
    <s:a action="annuleRejoindre"><img src="../img/button_back.png" alt="<- Retour" width="80px"></s:a>
</s:else>
<h1>En attente de lancement</h1>

<a href value="" onclick="location.reload(true)" >Rafraîchir la page</a>
<div id="toRefresh">
<table border="1">
    <tr>
        <td>Créateur de la partie</td>
        <td><s:property value="createur"/></td>
    </tr>
    <tr>
        <td>Nombre de joueurs maximum</td>
        <td><s:property value="nbJoueursMax"/></td>
    </tr>
    <tr>
        <td>Type de partie</td>
        <td><s:if test="%{prive}">Privée</s:if><s:else>Publique</s:else></td>
    </tr>
    <s:if test="lesUtilisateursInvites.size()!=0">
        <tr>
            <td>Joueurs invités</td>
            <td>
                <s:iterator value="lesUtilisateursInvites" var="x">
                    ${x}<br/>
                </s:iterator>
            </td>
        </tr>
    </s:if>
    <tr>
        <td>Joueurs prêts</td>
        <td>
            <s:iterator value="lesJoueurs" var="y">
                ${y}<br/>
            </s:iterator>
        </td>
    </tr>
</table>
<s:if test="%{session.login==createur}">
    <s:if test="laPartie.mesJoueurs.length > 1">
        <s:a action="partie">Lancer la partie</s:a>
    </s:if>
</s:if>
<s:else>
    <s:if test="laPartie.monPlateau!=null">
        <SCRIPT LANGUAGE="JavaScript">
            document.location.href="partie2.action"
        </SCRIPT>
    </s:if>
</s:else>
</div>
</body>
</html>
