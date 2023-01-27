const btnSondage = document.querySelector('.switch-to-sondage');
const btnResultats = document.querySelector('.switch-to-resultats');
const divSondage = document.querySelector('.sondage');
const divResultats = document.querySelector('.resultats');

btnSondage.addEventListener('click', ()=>{
    divSondage.style.display = 'block';
    divResultats.style.display = 'none';
});

btnResultats.addEventListener('click', ()=>{
    divResultats.style.display = 'block';
    divSondage.style.display = 'none';
});