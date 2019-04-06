<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<body>

<h1>Connectez-vous</h1>
<s:a action="index"><img src="../img/button_back.png" alt="<- Retour" width="80px"></s:a><br>

<s:form action="connecterUnJoueur">
    <s:textfield name="pseudo" key="saisie.pseudo"/>
    <s:textfield type="password" name="password" key="saisie.password"/>
    <s:submit/>
</s:form>
</body>
</html>
