<?php
    require_once 'connect-PDO.php';

    $nom = $_POST['nom'];
    $prenom = $_POST['prenom'];
    $naissance = $_POST['naissance'];
    $code_postal = $_POST['code_postal'];
    $ville = $_POST['ville'];
    $tel = $_POST['tel'];

    $sql = 'INSERT INTO SONDE(nom,prenom,naissance,code_postal,ville,tel) VALUES(:nom,:prenom,:naissance,:code_postal,:ville,:tel)';

    
    // $pdo = connexionBDD();

    // $statement = $pdo->prepare($sql);

    // $statement->execute([
    //     ':nom' => $nom,
    //     ':prenom' => $prenom,
    //     ':naissance' => $naissance,
    //     ':code_postal' => $code_postal,
    //     ':ville' => $ville,
    //     ':tel' => $tel
    // ]);

    // $id_user = $pdo->lastInsertId();

    // deconnexionBDD($PDO);

    // echo $id_user;

    
?>