<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>
<s:a action="menuPrincipal" onclick="return window.confirm('Retourner au menu annulera la création de la partie.\nÊtes-vous sûr de vouloir continuer??')"><img src="../img/button_cancel.png" alt="<- Annuler" width="80px"></s:a>
<h1>Créer une partie</h1>
<s:form action="menuLobby">
    <s:radio list="lesNbJoueurs" label="Nombre maximum de joueurs" value="2" name="nbJoueursMax"/>
    <s:label for="prive" label="Type de partie"/>
    <s:checkbox label="Partie privée" name="prive" value="true"/>
    <s:checkboxlist list="lesJoueursInvitables"
                    listKey="pseudo"
                    listValue="pseudo"
                    label="Joueurs à inviter"
                    name="lesUtilisateursInvites"
    />
    <s:submit/>
</s:form>
</body>
</html>
