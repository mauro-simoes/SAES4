
//ici on fera les getElementByID pour recup des inputs les champs 'nom','prenom' etc....

$.ajax({
    url: 'insert_user.php',
    type: 'POST',
    data:{nom:'mezoua',prenom:'anase',naissance:'05/05/2003',code_postal:'75013',ville:'Las Vegas',tel:'0600000000'},
    success: function(response){
        console.log(response)
    }
});