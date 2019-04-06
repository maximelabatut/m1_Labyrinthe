<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>

<s:a action="menuPrincipal"><img src="../img/button_back.png" alt="<- Retour" width="80px"></s:a>

<h1>Invitations reçues</h1>
<s:a action="menuInvitations"><img src="../img/button_refresh.png" alt="Rafraîchir la page" width="80px"></s:a><br/><br/>
<s:if test="%{lesInvitations.size()!=0}">
    <table border="1">
        <thead align="center">
            <td style="padding:5px;"></td>
            <td style="padding:5px;">Type de partie</td>
            <td style="padding:5px;">Joueurs</td>
            <td style="padding:5px;">Accepter</td>
            <td style="padding:5px;">Refuser</td>
        </thead>

        <s:iterator value="lesInvitations">
        <tr align="center">
            <td style="padding:5px;">Invitation de <s:property value="key.joueurHost"/></td>
            <td style="padding:5px;">Partie <s:if test="value.prive">privée</s:if><s:else>publique</s:else></td>
            <td style="padding:5px;"><s:property value="value.mesJoueurs.length"/>/<s:property value="value.nbJoueursMax"/></td>
            <td style="padding:5px;"><s:a action="accepterInvitation"><s:param value="key.getJoueurHost()" name="pseudoHost"/><img src="../img/accept.png" alt="Accepter" width="30px"></s:a></td>
            <td style="padding:5px;"><s:a action="refuserInvitation"><s:param value="key.getJoueurHost()" name="pseudoHost"/><img src="../img/decline.png" alt="Refuser" width="30px"></s:a></td>
        </tr>
        </s:iterator>
    </table>
</s:if>
<s:else>
    Aucune invitation n'a été trouvée.
</s:else>
</body>
</html>
