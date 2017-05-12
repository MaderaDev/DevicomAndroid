package madera.devicom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeDevis {

    List<Map<String, String>> mydevislist;

    public FakeDevis(){
        mydevislist = new ArrayList<Map<String, String>>();

        Map<String, String> map01 = new HashMap<>();
        map01.put("id", "1");
        map01.put("nom", "Allevard 38580, 8 rue des Lilas");
        map01.put("montant", "50000");
        map01.put("id_utilisateur", "0");
        map01.put("id_client", "0");
        map01.put("status", "Brouillon");
        map01.put("etape", "Devis ouvert");
        map01.put("modules", "");
        mydevislist.add(0, map01);

        Map <String, String> map02 = new HashMap<>();
        map02.put("id", "2");
        map02.put("nom", "Beaurepaire 38270, 69 boulevard Joseph");
        map02.put("montant", "50000");
        map02.put("id_utilisateur", "0");
        map02.put("id_client", "0");
        map02.put("status", "Brouillon");
        map02.put("etape", "Devis ouvert");
        map02.put("modules", "");
        mydevislist.add(1, map02);

        Map <String, String> map03 = new HashMap<>();
        map03.put("id", "3");
        map03.put("nom", "Biviers 38330, 35 avenue du Pont");
        map03.put("montant", "50000");
        map03.put("id_utilisateur", "0");
        map03.put("id_client", "0");
        map03.put("status", "Brouillon");
        map03.put("etape", "Devis ouvert");
        map03.put("modules", "");
        mydevislist.add(2, map03);

        Map <String, String> map04 = new HashMap<>();
        map04.put("id", "4");
        map04.put("nom", "Bourgoin Jallieu 38300, 987 rue Grumeaux");
        map04.put("montant", "50000");
        map04.put("id_utilisateur", "0");
        map04.put("id_client", "0");
        map04.put("status", "Brouillon");
        map04.put("etape", "Devis ouvert");
        map04.put("modules", "");
        mydevislist.add(3, map04);

        Map <String, String> map05 = new HashMap<>();
        map05.put("id", "5");
        map05.put("nom", "Claix 38640, 65 chemin de la Planche");
        map05.put("montant", "50000");
        map05.put("id_utilisateur", "0");
        map05.put("id_client", "0");
        map05.put("status", "En attente");
        map05.put("etape", "Devis ouvert");
        map05.put("modules", "");
        mydevislist.add(4, map05);

        Map <String, String> map06 = new HashMap<>();
        map06.put("id", "6");
        map06.put("nom", "Corenc 38700, 32 boulevard Montparnasse");
        map06.put("montant", "50000");
        map06.put("id_utilisateur", "0");
        map06.put("id_client", "0");
        map06.put("status", "En attente");
        map06.put("etape", "Devis ouvert");
        map06.put("modules", "");
        mydevislist.add(5, map06);

        Map <String, String> map07 = new HashMap<>();
        map07.put("id", "7");
        map07.put("nom", "Crolles 38920, 12 avenue Dell");
        map07.put("montant", "50000");
        map07.put("id_utilisateur", "0");
        map07.put("id_client", "0");
        map07.put("status", "En attente");
        map07.put("etape", "Devis ouvert");
        map07.put("modules", "");
        mydevislist.add(6, map07);

        Map <String, String> map08 = new HashMap<>();
        map08.put("id", "8");
        map08.put("nom", "Echirolles 38130, 36 rue Poubelle");
        map08.put("montant", "50000");
        map08.put("id_utilisateur", "0");
        map08.put("id_client", "0");
        map08.put("status", "Refusé");
        map08.put("etape", "Devis ouvert");
        map08.put("modules", "");
        mydevislist.add(7, map08);

        Map <String, String> map09 = new HashMap<>();
        map09.put("id", "9");
        map09.put("nom", "Eybens 38320, 54 boulevard Tableau");
        map09.put("montant", "50000");
        map09.put("id_utilisateur", "0");
        map09.put("id_client", "0");
        map09.put("status", "Refusé");
        map09.put("etape", "Devis ouvert");
        map09.put("modules", "");
        mydevislist.add(8, map09);

        Map <String, String> map10 = new HashMap<>();
        map10.put("id", "10");
        map10.put("nom", "Fontaine 38600, 867 chemin Table");
        map10.put("montant", "50000");
        map10.put("id_utilisateur", "0");
        map10.put("id_client", "0");
        map10.put("status", "Refusé");
        map10.put("etape", "Devis ouvert");
        map10.put("modules", "");
        mydevislist.add(9, map10);

        Map <String, String> map11 = new HashMap<>();
        map11.put("id", "11");
        map11.put("nom", "Grenoble 38100, 5874 rue du Python");
        map11.put("montant", "50000");
        map11.put("id_utilisateur", "0");
        map11.put("id_client", "0");
        map11.put("status", "Accepté");
        map11.put("etape", "Signature du devis");
        map11.put("modules", "");
        mydevislist.add(10, map11);

        Map <String, String> map12 = new HashMap<>();
        map01.put("id", "1");
        map12.put("nom", "La Mure 38350, 2 avenue Delachaise");
        map12.put("montant", "50000");
        map12.put("id_utilisateur", "0");
        map12.put("id_client", "0");
        map12.put("status", "Accepté");
        map12.put("etape", "Signature du devis");
        map12.put("modules", "");
        mydevislist.add(11, map12);

        Map <String, String> map13 = new HashMap<>();
        map13.put("id", "13");
        map13.put("nom", "La Garde 38520, 654 chemin Longchamps");
        map13.put("montant", "50000");
        map13.put("id_utilisateur", "0");
        map13.put("id_client", "0");
        map13.put("status", "Accepté");
        map13.put("etape", "Signature du devis");
        map13.put("modules", "");
        mydevislist.add(12, map13);

        Map <String, String> map14 = new HashMap<>();
        map14.put("id", "14");
        map14.put("nom", "Lans en Vercors 38250, 687 rue Roquette");
        map14.put("montant", "50000");
        map14.put("id_utilisateur", "0");
        map14.put("id_client", "0");
        map14.put("status", "En commande");
        map14.put("etape", "Permis de construire");
        map14.put("modules", "");
        mydevislist.add(13, map14);

        Map <String, String> map15 = new HashMap<>();
        map15.put("id", "15");
        map15.put("nom", "Marcollin 38270,32 avenue de la Prise");
        map15.put("montant", "50000");
        map15.put("id_utilisateur", "0");
        map15.put("id_client", "0");
        map15.put("status", "En commande");
        map15.put("etape", "Permis de construire");
        map15.put("modules", "");
        mydevislist.add(14, map15);

        Map <String, String> map16 = new HashMap<>();
        map16.put("id", "16");
        map16.put("nom", "Meylan 38240, 35 boulevard Mitaine");
        map16.put("montant", "50000");
        map16.put("id_utilisateur", "0");
        map16.put("id_client", "0");
        map16.put("status", "En commande");
        map16.put("etape", "Permis de construire");
        map16.put("modules", "");
        mydevislist.add(15, map16);

        Map <String, String> map17 = new HashMap<>();
        map17.put("id", "17");
        map17.put("nom", "Moirans 38430, 68 rue Cablaie");
        map17.put("montant", "50000");
        map17.put("id_utilisateur", "0");
        map17.put("id_client", "0");
        map17.put("status", "En facturation");
        map17.put("etape", "Ouverture du chantier");
        map17.put("modules", "");
        mydevislist.add(16, map17);

        Map <String, String> map18 = new HashMap<>();
        map18.put("id", "18");
        map18.put("nom", "Roussillon 38150, 4 chemin Teille");
        map18.put("montant", "50000");
        map18.put("id_utilisateur", "0");
        map18.put("id_client", "0");
        map18.put("status", "En facturation");
        map18.put("etape", "Ouverture du chantier");
        map18.put("modules", "");
        mydevislist.add(17, map18);

        Map <String, String> map19 = new HashMap<>();
        map19.put("id", "19");
        map19.put("nom", "Rencurel 38680, 56 avenue de la Pomme");
        map19.put("montant", "50000");
        map19.put("id_utilisateur", "0");
        map19.put("id_client", "0");
        map19.put("status", "En facturation");
        map19.put("etape", "Achèvement des fondations");
        map19.put("modules", "");
        mydevislist.add(18, map19);

        Map <String, String> map20 = new HashMap<>();
        map20.put("id", "20");
        map20.put("nom", "Ruy 38300, 354 boulevard Manteau");
        map20.put("montant", "50000");
        map20.put("id_utilisateur", "0");
        map20.put("id_client", "0");
        map20.put("status", "En facturation");
        map20.put("etape", "Achèvement des fondations");
        map20.put("modules", "");
        mydevislist.add(19, map20);

        Map <String, String> map21 = new HashMap<>();
        map21.put("id", "21");
        map21.put("nom", "Morestel 38510, 563 rue Lunettes");
        map21.put("montant", "50000");
        map21.put("id_utilisateur", "0");
        map21.put("id_client", "0");
        map21.put("status", "En facturation");
        map21.put("etape", "Achèvement des murs");
        map21.put("modules", "");
        mydevislist.add(20, map21);

        Map <String, String> map22 = new HashMap<>();
        map22.put("id", "22");
        map22.put("nom", "Nivolas Vermelle 38300, 65 rue du Gobelet");
        map22.put("montant", "50000");
        map22.put("id_utilisateur", "0");
        map22.put("id_client", "0");
        map22.put("status", "En facturation");
        map22.put("etape", "Achèvement des murs");
        map22.put("modules", "");
        mydevislist.add(21, map22);

        Map <String, String> map23 = new HashMap<>();
        map23.put("id", "23");
        map23.put("nom", "Sassenage 38360, 68 boulevard Sacoche");
        map23.put("montant", "50000");
        map23.put("id_utilisateur", "0");
        map23.put("id_client", "0");
        map23.put("status", "En facturation");
        map23.put("etape", "Achevement de l'isolation");
        map23.put("modules", "");
        mydevislist.add(22, map23);

        Map <String, String> map24 = new HashMap<>();
        map24.put("id", "24");
        map24.put("nom", "Seyssins 38180, 68 avenue Tuyau");
        map24.put("montant", "50000");
        map24.put("id_utilisateur", "0");
        map24.put("id_client", "0");
        map24.put("status", "En facturation");
        map24.put("etape", "Achevement de l'isolation");
        map24.put("modules", "");
        mydevislist.add(23, map24);

        Map <String, String> map25 = new HashMap<>();
        map25.put("id", "25");
        map25.put("nom", "Sillans 38590, 65 chemin du Sac");
        map25.put("montant", "50000");
        map25.put("id_utilisateur", "0");
        map25.put("id_client", "0");
        map25.put("status", "En facturation");
        map25.put("etape", "Travaux d'équipement");
        map25.put("modules", "");
        mydevislist.add(24, map25);

        Map <String, String> map26 = new HashMap<>();
        map26.put("id", "26");
        map26.put("nom", "Thodure 38260, 32 rue des Fleurs");
        map26.put("montant", "50000");
        map26.put("id_utilisateur", "0");
        map26.put("id_client", "0");
        map26.put("status", "En facturation");
        map26.put("etape", "Travaux d'équipement");
        map26.put("modules", "");
        mydevislist.add(25, map26);

        Map <String, String> map27 = new HashMap<>();
        map27.put("id", "27");
        map27.put("nom", "Tullins 38210, 56 avenue du Pressant");
        map27.put("montant", "50000");
        map27.put("id_utilisateur", "0");
        map27.put("id_client", "0");
        map27.put("status", "En facturation");
        map27.put("etape", "Remise des clés");
        map27.put("modules", "");
        mydevislist.add(26, map27);

        Map <String, String> map28 = new HashMap<>();
        map28.put("id", "28");
        map28.put("nom", "Villard de Lans 38250, 365 boulevard Rossignol");
        map28.put("montant", "50000");
        map28.put("id_utilisateur", "0");
        map28.put("id_client", "0");
        map28.put("status", "Clôturé");
        map28.put("etape", "Clôturé");
        map28.put("modules", "");
        mydevislist.add(27, map28);

        Map <String, String> map29 = new HashMap<>();
        map29.put("id", "29");
        map29.put("nom", "Villefontaine 38090, 54 chemin de l'Arbre");
        map29.put("montant", "50000");
        map29.put("id_utilisateur", "0");
        map29.put("id_client", "0");
        map29.put("status", "Clôturé");
        map29.put("etape", "Clôturé");
        map29.put("modules", "");
        mydevislist.add(28, map29);

        Map <String, String> map30 = new HashMap<>();
        map30.put("id", "30");
        map30.put("nom", "Voiron 38500, 564 rue Finale");
        map30.put("montant", "50000");
        map30.put("id_utilisateur", "0");
        map30.put("id_client", "0");
        map30.put("status", "Clôturé");
        map30.put("etape", "Clôturé");
        map30.put("modules", "");
        mydevislist.add(29, map30);

        /*for(int i=0; i<mydevislist.size(); i++){
            System.out.println(mydevislist.get(i));
        }*/

    }
}
