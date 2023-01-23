<?php
 
function connexionBDD () // déclaration de la fonction connexionBDD
    {
        include('paramCon.php'); // on "inclut" un fichier source contenant le code
        $dsn='mysql:host='.$lehost.';dbname='.$dbname.';port='.$leport;
      //  echo $dsn."<br/>";  // pour vérif. Permet l'affichage du dsn à l'écran (avec un retour à la ligne).
     
     
        // connexion à la bdd (connexion non persistante) avec le connecteur nommé $conn1
        try { // essai de connexion
            $conn1 = new PDO($dsn, $user, $pass); // tentative de connexion
           print "Connecté :)<br />"; // message de debug
             
        } catch (PDOException $e) { // si erreur
            print "Erreur de connexion à la base de données ! : " . $e->getMessage(); // pour exception
            die(); // Arrêt du script - sortie.
        }
        return $conn1;
        //si pas erreur, on continue !
        // $conn1 est le connecteur de notre base de données.
    }
 
function deconnexionBDD ($conn1) // déclaration de la fonction deconnexionBDD
    {
        $conn1 = null; //fermeture de la  connexion
    }
?>