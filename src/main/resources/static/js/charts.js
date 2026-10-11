/* =========================================
   CHART VARIABLES
   ========================================= */

let casesChart = null;

let deathChart = null;

let comparisonChart = null;


/* =========================================
   CREATE MAIN CHARTS
   ========================================= */

function createCharts(statistic) {

    console.log("Chart function started.");

    if (typeof Chart === "undefined") {

        console.error(
            "ERROR: Chart.js is not loaded."
        );

        return;
    }

    console.log(
        "Chart.js version:",
        Chart.version
    );


    const casesCanvas =
        document.getElementById("casesChart");

    const deathCanvas =
        document.getElementById("deathChart");


    if (!casesCanvas || !deathCanvas) {

        console.error(
            "ERROR: Chart canvas elements not found."
        );

        return;
    }


    /* Destroy previous charts */

    if (casesChart) {

        casesChart.destroy();

        casesChart = null;
    }


    if (deathChart) {

        deathChart.destroy();

        deathChart = null;
    }


    /* Get values */

    const totalCases =
        Number(statistic.totalCases) || 0;

    const newCases =
        Number(statistic.newCases) || 0;

    const recovered =
        Number(statistic.totalRecovered) || 0;

    const activeCases =
        Number(statistic.activeCases) || 0;

    const criticalCases =
        Number(statistic.criticalCases) || 0;

    const totalDeaths =
        Number(statistic.totalDeaths) || 0;


    /* =========================================
       CASES BAR CHART
       ========================================= */

    casesChart = new Chart(
        casesCanvas,
        {

            type: "bar",

            data: {

                labels: [
                    "Total Cases",
                    "New Cases",
                    "Recovered",
                    "Active Cases",
                    "Critical Cases"
                ],

                datasets: [

                    {

                        label:
                            statistic.country ||
                            "COVID Statistics",

                        data: [

                            totalCases,

                            newCases,

                            recovered,

                            activeCases,

                            criticalCases

                        ],

                        borderWidth: 1

                    }

                ]

            },

            options: {

                responsive: true,

                maintainAspectRatio: false,

                scales: {

                    y: {

                        beginAtZero: true

                    }

                },

                plugins: {

                    legend: {

                        display: true

                    }

                }

            }

        }
    );


    /* =========================================
       DEATH / RECOVERY DOUGHNUT
       ========================================= */

    deathChart = new Chart(
        deathCanvas,
        {

            type: "doughnut",

            data: {

                labels: [

                    "Deaths",

                    "Recovered",

                    "Active Cases"

                ],

                datasets: [

                    {

                        data: [

                            totalDeaths,

                            recovered,

                            activeCases

                        ],

                        borderWidth: 1

                    }

                ]

            },

            options: {

                responsive: true,

                maintainAspectRatio: false,

                plugins: {

                    legend: {

                        display: true,

                        position: "bottom"

                    }

                }

            }

        }
    );


    console.log(
        "Main charts successfully created."
    );
}


/* =========================================
   CREATE COMPARISON CHART
   ========================================= */

function createComparisonChart(statistics) {

    console.log(
        "Creating comparison chart..."
    );

    console.log(
        "Selected countries:",
        statistics
    );


    if (typeof Chart === "undefined") {

        console.error(
            "ERROR: Chart.js is not loaded."
        );

        return;
    }


    const canvas =
        document.getElementById(
            "comparisonChart"
        );


    if (!canvas) {

        console.error(
            "ERROR: comparisonChart canvas not found."
        );

        return;
    }


    if (comparisonChart) {

        comparisonChart.destroy();

        comparisonChart = null;
    }


    if (
        !statistics ||
        statistics.length === 0
    ) {

        console.error(
            "ERROR: No country data available."
        );

        return;
    }


    const labels =
        statistics.map(
            statistic => statistic.country
        );


    const totalCases =
        statistics.map(
            statistic =>
                Number(
                    statistic.totalCases
                ) || 0
        );


    comparisonChart = new Chart(
        canvas,
        {

            type: "bar",

            data: {

                labels: labels,

                datasets: [

                    {

                        label:
                            "Total Cases",

                        data:
                            totalCases,

                        borderWidth: 1

                    }

                ]

            },

            options: {

                responsive: true,

                maintainAspectRatio: false,

                indexAxis: "y",

                scales: {

                    x: {

                        beginAtZero: true

                    }

                },

                plugins: {

                    legend: {

                        display: true

                    }

                }

            }

        }
    );


    console.log(
        "Comparison chart successfully created."
    );
}