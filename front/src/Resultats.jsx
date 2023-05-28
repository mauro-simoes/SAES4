import React, { useState, useEffect } from 'react';
import ReactLoading from 'react-loading';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'bootstrap/dist/css/bootstrap.css';

import { Chart as ChartJS, ArcElement, Tooltip, Legend, Colors } from "chart.js";
import { Doughnut } from "react-chartjs-2";

ChartJS.register(ArcElement, Tooltip, Legend, Colors);

const Resultats = ({ questions, nbQuestion, cookies, idSondage }) => {
    const token = cookies.token;
    const [repData, setRepData] = useState({});
    const [loading, setLoading] = useState(false);


    // Example data for chart.js
    const dataTop10ResponseExempleForOneQuestion = {
        "Eau minérale, embouteillée, faiblement minéralisée (aliment moyen)": 2,
        "Eau minérale, gazeuse (aliment moyen)": 2,
        "Jus de tomate, pur jus (aliment moyen)": 1,
        "Sandwich (aliment moyen)": 1,
        "Veau, viande, cuite (aliment moyen)": 1,
        "Boisson lactée aromatisée (arôme inconnu), sucrée, au lait partiellement écrémé, enrichie et/ou restaurée en vitamines et/ou minéraux (aliment moyen)": 1,
        "Abricot au sirop (sans précision sur léger ou classique), appertisé, égoutté (aliment moyen)": 1,
        "Macédoine ou cocktail ou salade de fruits, au sirop (sans précision sur léger ou classique), appertisé, égouttée (aliment moyen)": 1,
        "Jus de pomme BIO, pur jus": 1
    };

    useEffect(() => {
        setLoading(true); // Définit initialement le chargement à true
        let successfulRequests = 0; // Variable de comptage des requêtes réussies
      
        questions.map((question) => {
          fetch('http://localhost:8080/api/data/top-ten-reponses/' + question.id, {
            method: 'GET',
            headers: {
              'Authorization': `Bearer ${token}`,
              'Content-Type': 'application/json'
            }
          })
          .then(response => response.json())
          .then(data => {
            console.log(data);
            setRepData({ ...repData, [question.id]: data });
            successfulRequests++; // Incrémente le nombre de requêtes réussies
      
            // Vérifie si toutes les requêtes ont réussi
            if (successfulRequests === questions.length) {    
                setLoading(false); // Définit le chargement à false lorsque toutes les requêtes ont réussi
            }
          })
          .catch(error => console.error(error));
        }, []);
    }, []);
      

    return (
        <div className='resultats-container'>
            <div className='resultats-header'>
                <h1>Résultats</h1>
            </div>
            <div className='resultats-body'>
            {
                loading ? (
                <ReactLoading type={'spin'} color={'#000000'}/>
                ) : (
                    // for each repData
                    Object.keys(repData).map((key, index) => {
                        return (
                            <div className='resultats-question' key={index}>
                                <h2>{questions[index].question}</h2>
                                <Doughnut 
                                    data={{
                                        labels: Object.keys(repData[key]),
                                        datasets: [
                                            {
                                                label: 'Top 10 des réponses',
                                                data: Object.values(repData[key])
                                            }
                                        ]
                                    }}
                                    options={{
                                        responsive: true,
                                        maintainAspectRatio: false,
                                        plugins: {
                                            legend: {
                                                position: 'bottom',
                                            },
                                            title: {
                                                display: true,
                                                text: 'Top 10 des réponses'
                                            }
                                        }
                                    }}
                                />
                            </div>
                        );
                    })
                )
            }
            </div>
        </div>
      );
      
};
export default Resultats;