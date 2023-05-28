import React, { useState, useEffect } from 'react';
import ReactLoading from 'react-loading';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'bootstrap/dist/css/bootstrap.css';

import { Chart as ChartJS, ArcElement, Tooltip, Legend, Colors } from "chart.js";
import { Doughnut } from "react-chartjs-2";

ChartJS.register(ArcElement, Tooltip, Legend, Colors);

const Resultats = ({ questions, cookies }) => {
    const token = cookies.token;
    const [repData, setRepData] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        let successfulRequests = 0;
    
        questions.forEach((question) => {
          fetch('http://localhost:8080/api/data/top-ten-reponses/' + question.id, {
            method: 'GET',
            headers: {
              'Authorization': `Bearer ${token}`,
              'Content-Type': 'application/json'
            }
          })
          .then(response => response.json())
          .then(data => {
            setRepData(prevState => ({ ...prevState, [question.id]: data }));
            successfulRequests++;

            const totalRequests = questions.length;
            if (successfulRequests === totalRequests) {
                setLoading(false);
              }
          })
          .catch(error => console.error(error));
        });
    
      }, [questions, token]);
      
    function renderCharts(){
        console.log('renderCharts',repData);
        return(
            Object.keys(repData).map((key, index) => (
                <div className='resultats-question' key={index}>
                <h2>{questions[index].contenu}</h2>
                <div className='chart-container'>
                    <Doughnut
                    key={index}
                    data={{
                        labels: Object.keys(repData[key]),
                        datasets: [
                        {
                            label: 'Top 10 des réponses',
                            data: Object.values(repData[key]),
                        },
                        ],
                    }}
                    options={{
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                        legend: {
                            position: 'bottom',
                            labels: {
                                font: {
                                    size: 14
                                },
                                color: '#000000',
                                wrap : true
                            },
                        },
                        title: {
                            display: true,
                            text: 'Top 10 des réponses',
                        },
                        },
                    }}
                    />
                </div>
                </div>
            ))
        );
    }


    return (
        <div className='resultats-container'>
        <div className='resultats-header'>
            <h1>Résultats</h1>
        </div>
        <div className='resultats-body'>
            {loading ? (
            <ReactLoading type={'spin'} color={'#000000'} />
            ) : (
                renderCharts()
            )}
        </div>
        </div>
    );

};
export default Resultats;