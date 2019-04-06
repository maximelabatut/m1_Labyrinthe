<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>
<s:a action="menuPrincipal"><img src="../img/button_back.png" alt="<- Retour" width="80px"></s:a>
<h1>Rejoindre une partie</h1>

<s:a action="menuPartieRejoindre"><img src="../img/button_refresh.png" alt="Rafraîchir la page" width="80px"></s:a><br/><br/>

<s:if test="%{lesPartiesRejoignables.size()!=0}">
<s:form action="rejoindrePartie" method="GET">
    <s:select list="lesPartiesRejoignables"
              name="pseudoHost"
              label="Choisir une partie à rejoindre"
              listKey="getJoueurHost().getPseudo()"
              listValue="getJoueurHost().getPseudo() + ' - ' + mesJoueurs.length + '/' + nbJoueursMax"
    />
    <s:submit/>
</s:form>
</s:if>
<s:else>
    Aucune partie n'a été trouvée.
</s:else>
</body>
</html>
