<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Quentin
  Date: 15/12/2017
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labyrinthe - TDL</title>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script type="text/javascript">
    function update() {
        $.ajax({
            url: 'partie2.action', // action to be perform
            type: 'POST',       //type of posting the data
            success: function (html) {
                $('#jeu').load('partieReload.jsp'); //set result.jsp output to leftDiv
                console.log("ok");
            },
            error: function(xhr, ajaxOptions, thrownError){
                alert('An error occurred! ' + thrownError);
            },

        });
    }
    setInterval(function() {update();}, 5000);
</script>
<s:if test="!(placerUneCase)">
    <SCRIPT LANGUAGE="JavaScript">
        document.location.href="partie2.action"
    </SCRIPT>
</s:if>
<h1>Bienvenue dans le Labyrinthe</h1>
<div id="jeu">
<div style="display:inline-block; padding-right:30px;border-right:2px dashed black;">
    <table cellspacing="0">
        <s:iterator value="monPlateau" status="x">
            <tr>
                <s:iterator status="y">
                    <td align="center">
                        <s:if test="(auTourDe && !(placerUneCase))">
                            <!-- haut -->
                            <s:if test="(#x.index == 0 && #y.index % 2 == 0 && #y.index != 0 && #y.index != 8)">
                                <s:if test="(#y.index != dejaPousserY)">
                                    <s:a action="jouerPartie" >
                                        <s:param name="action" value="1"/>
                                        <s:param name="x" value="#x.index+1"/>
                                        <s:param name="y" value="#y.index"/>
                                        <s:param name="dejaPousserX" value="dejaPousserX"/>
                                        <s:param name="dejaPousserY" value="dejaPousserY"/>
                                        <img src="../img/plateau/fleche_haut.png" width="20" height="20"/>
                                    </s:a>
                                </s:if>
                                <s:else>
                                    <img src="../img/plateau/croix_rouge.png" width="20" height="20"/>
                                </s:else>
                            </s:if>
                            <s:else>
                                <!-- bas -->
                                <s:if test="(#x.index == 8 && #y.index % 2 == 0 && #y.index != 0 && #y.index != 8)">
                                    <s:if test="(#y.index != dejaPousserY)">
                                        <s:a action="jouerPartie">
                                            <s:param name="action" value="1"/>
                                            <s:param name="x" value="#x.index-1"/>
                                            <s:param name="y" value="#y.index"/>
                                            <s:param name="dejaPousserX" value="dejaPousserX"/>
                                            <s:param name="dejaPousserY" value="dejaPousserY"/>
                                            <img src="../img/plateau/fleche_bas.png" width="20" height="20"/>
                                        </s:a>
                                    </s:if>
                                    <s:else>
                                        <img src="../img/plateau/croix_rouge.png" width="20" height="20"/>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <!-- droite -->
                                    <s:if test="(#y.index == 8 && #x.index % 2 == 0 && #x.index != 0 && #x.index != 8)">
                                        <s:if test="(#x.index != dejaPousserX)">
                                            <s:a action="jouerPartie">
                                                <s:param name="action" value="1"/>
                                                <s:param name="x" value="#x.index"/>
                                                <s:param name="y" value="#y.index-1"/>
                                                <s:param name="dejaPousserX" value="dejaPousserX"/>
                                                <s:param name="dejaPousserY" value="dejaPousserY"/>
                                                <img src="../img/plateau/fleche_droite.png" width="20" height="20"/>
                                            </s:a>
                                        </s:if>
                                        <s:else>
                                            <img src="../img/plateau/croix_rouge.png" width="20" height="20"/>
                                        </s:else>
                                    </s:if>
                                    <s:else>
                                        <!-- gauche -->
                                        <s:if test="(#x.index % 2 == 0 && #y.index == 0 && #x.index != 0 && #x.index != 8)">
                                            <s:if test="(#x.index != dejaPousserX)">
                                                <s:a action="jouerPartie">
                                                    <s:param name="action" value="1"/>
                                                    <s:param name="x" value="#x.index"/>
                                                    <s:param name="y" value="#y.index+1"/>
                                                    <s:param name="dejaPousserX" value="dejaPousserX"/>
                                                    <s:param name="dejaPousserY" value="dejaPousserY"/>
                                                    <img src="../img/plateau/fleche_gauche.png" width="20" height="20"/>
                                                </s:a>
                                            </s:if>
                                            <s:else>
                                                <img src="../img/plateau/croix_rouge.png" width="20" height="20"/>
                                            </s:else>
                                        </s:if>
                                    </s:else>
                                </s:else>
                            </s:else>
                        </s:if>
                        <s:if test="!(#y.index == 0 || #x.index == 0 || #y.index == 8 || #x.index == 8)">
                            <s:if test="auTourDe && placerUneCase">
                                <s:a action="jouerPartie">
                                    <s:param name="action" value="5"/>
                                    <s:param name="x" value="#x.index"/>
                                    <s:param name="y" value="#y.index"/>
                                    <s:param name="placerUneCase" value="placerUneCase"/>
                                    <s:param name="dejaPousserX" value="dejaPousserX"/>
                                    <s:param name="dejaPousserY" value="dejaPousserY"/>
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
                                </s:a>
                        </s:if>
                        <s:else>
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
                        </s:else>
                        </s:if>
                    </td>
                </s:iterator>
            </tr>
        </s:iterator>
    </table>
</div>
<div style="display:inline-block; margin-left:30px; vertical-align: top;">

<br>

<s:if test="placerUneCaseObligatoire">
    <img src="../img/plateau/croix_rouge.png" width="20" height="20"/> WARNING ! --> Vous devez placer la CASE LIBRE avant de JOUER !!
    <br><br>
</s:if>

<s:if test="deplacementImpossible">
    <img src="../img/plateau/croix_rouge.png" width="20" height="20"/> WARNING ! --> Le déplacement de votre JOUEUR est IMPOSSIBLE sur cette CASE !!
    <br><br>
</s:if>

<s:if test="tresorTrouve">
    <img src="../img/plateau/fire_cracker.png" width="20" height="20"/> BRAVO ! --> Vous venez de trouver un TRESOR !!
    <br><br>
</s:if>

Voici la liste des joueurs dans la partie : <br>

<s:iterator value="listeDesJoueurs">
    <tr>
        • <s:if test="#session.login.equals(pseudo)">[VOUS] </s:if><s:property value="pseudo"/> - <span style="color: ${couleur}">■</span><br/>
    </tr>
</s:iterator>
<br>
<s:if test="auTourDe && !placerUneCase">
    Voici la case libre :
    <table cellspacing="0">
        <%--Haut--%>
        <tr>
            <td bgcolor="black" width="10" height="10"></td>
            <td bgcolor="black" width="10" height="10"></td>
            <s:if test="caseLibre.getForme().isHaut()">
                <td width="10" height="10"></td>
                <td width="10" height="10"></td>
            </s:if>
            <s:else>
                <td bgcolor="black" width="10" height="10"></td>
                <td bgcolor="black" width="10" height="10"></td>
            </s:else>
            <td bgcolor="black" width="10" height="10"></td>
            <td bgcolor="black" width="10" height="10"></td>
        </tr>
        <tr>
            <td bgcolor="black" width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td bgcolor="black" width="10" height="10"></td>
        </tr>
        <%--Joueurs--%>
        <tr>
            <s:if test="caseLibre.getForme().isGauche()">
                <td width="10" height="10"></td>
            </s:if>
            <s:else>
                <td bgcolor="black" width="10" height="10"></td>
            </s:else>
            <td width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td width="10" height="10"></td>
            <s:if test="caseLibre.getForme().isDroite()">
                <td width="10" height="10"></td>
            </s:if>
            <s:else>
                <td bgcolor="black" width="10" height="10"></td>
            </s:else>
        </tr>
        <%--Tresor 1--%>
        <tr>
            <s:if test="caseLibre.getForme().isGauche()">
                <td width="10" height="10"></td>
            </s:if>
            <s:else>
                <td bgcolor="black" width="10" height="10"></td>
            </s:else>
            <td width="10" height="10"></td>
            <td align="center" rowspan="2" colspan="2" width="10" height="10">
                <s:if test="caseLibre.getTresor().getNom().length()!=1">
                    <img src="../img/plateau/img_tresors/${caseLibre.getTresor().getNom()}.png" width="20" height="20"/>
                </s:if>
                <s:a action="jouerPartie" >
                    <s:param name="action" value="5"/>
                    <s:param name="x" value="#x.index"/>
                    <s:param name="y" value="#y.index"/>
                </s:a></td>
            <td width="10" height="10"></td>
            <s:if test="caseLibre.getForme().isDroite()">
                <td width="10" height="10"></td>
            </s:if>
            <s:else>
                <td bgcolor="black" width="10" height="10"></td>
            </s:else>
        </tr>
        <%--Trésor 2--%>
        <tr>
            <td bgcolor="black" width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td width="10" height="10"></td>
            <td bgcolor="black" width="10" height="10"></td>
        </tr>
        <%--Bas--%>
        <tr>
            <td bgcolor="black" width="10" height="10"></td>
            <td bgcolor="black" width="10" height="10"></td>
            <s:if test="caseLibre.getForme().isBas()">
                <td width="10" height="10"></td>
                <td width="10" height="10"></td>
            </s:if>
            <s:else>
                <td bgcolor="black" width="10" height="10"></td>
                <td bgcolor="black" width="10" height="10"></td>
            </s:else>
            <td bgcolor="black" width="10" height="10"></td>
            <td bgcolor="black" width="10" height="10"></td>
        </tr>
    </table>
</s:if>

<s:if test="auTourDe && !(placerUneCase)">
    <s:a action="jouerPartie">
        <s:param name="action" value="2"/>
        <s:param name="placerUneCase" value="placerUneCase"/>
        <s:param name="dejaPousserX" value="dejaPousserX"/>
        <s:param name="dejaPousserY" value="dejaPousserY"/>
        <img src="../img/plateau/pivoter_plus.png" width="34" height="34"/>
    </s:a>

    <s:a action="jouerPartie">
        <s:param name="action" value="3"/>
        <s:param name="placerUneCase" value="placerUneCase"/>
        <s:param name="dejaPousserX" value="dejaPousserX"/>
        <s:param name="dejaPousserY" value="dejaPousserY"/>
        <img src="../img/plateau/pivoter_minus.png" width="34" height="34"/>
    </s:a>
    <br><br>
</s:if>

<s:if test="#session.login.equals(leJoueurEnCours)">
    A votre tour de jouer !
</s:if>
<s:else>
    C'est au tour de : <s:property value="leJoueurEnCours"/>
</s:else>
<br><br>

Voici le trésor à récupérer :<br>
<img src="../img/plateau/img_tresors/${maCarte.getTresor().getNom()}.png" width="40" height="40"/><br><br>

Il vous reste encore <s:property value="mesCartes.length-1"/> trésors à récupérer.<br><br>

<s:if test="auTourDe && placerUneCase">
    <s:a action="jouerPartie">
        <s:param name="action" value="4"/>Passer son tour
        <s:param name="placerUneCase" value="placerUneCase"/>
        <s:param name="dejaPousserX" value="dejaPousserX"/>
        <s:param name="dejaPousserY" value="dejaPousserY"/>
    </s:a>
    <br>
    <br>
</s:if>

<s:a action="jouerPartie">
    <s:param name="action" value="6"/>Quitter la Partie
    <s:param name="placerUneCase" value="placerUneCase"/>
    <s:param name="dejaPousserX" value="dejaPousserX"/>
    <s:param name="dejaPousserY" value="dejaPousserY"/>
</s:a>


</div>
</div>
<s:if test="ActionContext.getContext().getName().equals(partie)">
    <SCRIPT LANGUAGE="JavaScript">
        document.location.href="partie2.action"
    </SCRIPT>
</s:if>
</html>
