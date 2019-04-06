<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Arthur-PC
  Date: 22/12/2017
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<h1>Bienvenue dans le Labyrinthe (Observateur)</h1>
<div id="jeu">
    <div style="display:inline-block; padding-right:30px;border-right:2px dashed black;">
        <table cellspacing="0">
            <s:iterator value="monPlateau" status="x">
                <tr>
                    <s:iterator status="y">
                        <td align="center">
                            <s:if test="!(#y.index == 0 || #x.index == 0 || #y.index == 8 || #x.index == 8)">
                                <table cellspacing="0">
                                        <%--Haut--%>
                                    <tr>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <s:if test="getForme().isHaut()">
                                            <td width="10" height="10"></td>
                                            <td width="10" height="10"></td>
                                        </s:if>
                                        <s:else>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                        </s:else>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                    </tr>
                                    <tr>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <td width="10" height="10"></td>
                                        <td width="10" height="10"></td>
                                        <td width="10" height="10"></td>
                                        <td width="10" height="10"></td>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                    </tr>
                                        <%--Joueurs--%>
                                    <tr>
                                        <s:if test="getForme().isGauche()">
                                            <td width="10" height="10"></td>
                                        </s:if>
                                        <s:else>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                        </s:else>
                                        <s:iterator value="listeDesJoueurs">
                                            <s:if test="getX()==#x.index && getY()==#y.index">
                                                <td bgcolor="${couleur}" width="10" height="10" ></td>
                                            </s:if>
                                            <s:else>
                                                <td width="10" height="10"></td>
                                            </s:else>
                                        </s:iterator>
                                        <s:if test="listeDesJoueurs.length==2">
                                            <td width="10" height="10"></td>
                                            <td width="10" height="10"></td>
                                        </s:if>
                                        <s:else>
                                            <s:if test="listeDesJoueurs.length==3">
                                                <td width="10" height="10"></td>
                                            </s:if>
                                        </s:else>
                                        <s:if test="getForme().isDroite()">
                                            <td width="10" height="10"></td>
                                        </s:if>
                                        <s:else>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                        </s:else>
                                    </tr>
                                        <%--Tresor 1--%>
                                    <tr>
                                        <s:if test="getForme().isGauche()">
                                            <td width="10" height="10"></td>
                                        </s:if>
                                        <s:else>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                        </s:else>
                                        <td width="10" height="10"></td>
                                        <td align="center" rowspan="2" colspan="2" width="20" height="20">
                                            <s:if test="getTresor().getNom().length()!=1">
                                                <img src="../img/plateau/img_tresors/${tresor.getNom()}.png" width="20" height="20"/>
                                            </s:if>
                                        </td>
                                        <td width="10" height="10"></td>
                                        <s:if test="getForme().isDroite()">
                                            <td width="10" height="10"></td>
                                        </s:if>
                                        <s:else>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                        </s:else>
                                    </tr>
                                        <%--Trésor 2--%>
                                    <tr>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <td width="10" height="10"></td>
                                        <td width="10" height="10"></td>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                    </tr>
                                        <%--Bas--%>
                                    <tr>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <s:if test="getForme().isBas()">
                                            <td width="10" height="10"></td>
                                            <td width="10" height="10"></td>
                                        </s:if>
                                        <s:else>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                            <td bgcolor="${couleur}" width="10" height="10"></td>
                                        </s:else>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                        <td bgcolor="${couleur}" width="10" height="10"></td>
                                    </tr>
                                </table>
                            </s:if>
                        </td>
                    </s:iterator>
                </tr>
            </s:iterator>
        </table>
    </div>
    <!---------------------------------------------------------------------------------------------------------->
    <div style="display:inline-block; margin-left:30px; vertical-align: top;">
        <br>
        Voici la liste des joueurs dans la partie : <br>

        <s:iterator value="listeDesJoueurs">
            <tr>
                • <s:property value="pseudo"/> - <span style="color: ${couleur}">■</span><br/>
            </tr>
        </s:iterator>
        <br>

        C'est au tour de : <s:property value="leJoueurEnCours"/>
        <br><br>

        Voici les trésors à récupérer :<br>
        <table border="1">
            <s:iterator value="lesCartes">
                <tr><td><s:property value="key"/></td>
                    <s:iterator value="value">
                        <td><img src="../img/plateau/img_tresors/${tresor.getNom()}.png" width="20" height="20"/></td>
                    </s:iterator></tr>
            </s:iterator></table>

        <s:a action="annuleObservateur"><img src="../img/button_back.png" alt="<- Retour" width="80px"></s:a>

    </div>
</div>
</html>
