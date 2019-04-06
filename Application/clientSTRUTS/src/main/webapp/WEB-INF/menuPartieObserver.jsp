<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>
<s:a action="menuPrincipal"><img src="../img/button_back.png" alt="<- Retour" width="80px"></s:a>
<h1>Observer une partie</h1>
<s:a action="menuPartieObserver"><img src="../img/button_refresh.png" alt="Rafraîchir la page" width="80px"></s:a><br/><br/>
<s:if test="%{lesPartiesObservables.size()!=0}">
    <s:form action="regarderPartie">
        <s:select list="lesPartiesObservables"
                  name="pseudoHost"
                  label="Choisir une partie à observer"
                  listKey="getJoueurHost().getPseudo()"
                  listValue="getJoueurHost().getPseudo()"
        />
        <s:submit/>
    </s:form>
</s:if>
<s:else>
    Aucune partie n'a été trouvée.
</s:else>
</body>
</html>
