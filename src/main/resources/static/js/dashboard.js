/* =========================================
   API CONFIGURATION
   ========================================= */

const API_URL = "/api/covid";


/* =========================================
   FORMAT NUMBER
   ========================================= */

function formatNumber(value) {

    if (
        value === null ||
        value === undefined ||
        value === ""
    ) {

        return "0";
    }

    return Number(value).toLocaleString(
        "en-IN"
    );
}


/* =========================================
   DISPLAY STATISTIC
   ========================================= */

function displayStatistic(statistic) {

    if (!statistic) {

        console.error(
            "Statistic data is empty."
        );

        return;
    }


    /* Statistics */

    document.getElementById(
        "totalCases"
    ).textContent =
        formatNumber(
            statistic.totalCases
        );


    document.getElementById(
        "newCases"
    ).textContent =
        formatNumber(
            statistic.newCases
        );


    document.getElementById(
        "totalDeaths"
    ).textContent =
        formatNumber(
            statistic.totalDeaths
        );


    document.getElementById(
        "newDeaths"
    ).textContent =
        formatNumber(
            statistic.newDeaths
        );


    document.getElementById(
        "totalRecovered"
    ).textContent =
        formatNumber(
            statistic.totalRecovered
        );


    document.getElementById(
        "activeCases"
    ).textContent =
        formatNumber(
            statistic.activeCases
        );


    document.getElementById(
        "criticalCases"
    ).textContent =
        formatNumber(
            statistic.criticalCases
        );


    document.getElementById(
        "population"
    ).textContent =
        formatNumber(
            statistic.population
        );


    /* Country information */

    document.getElementById(
        "countryName"
    ).textContent =
        statistic.country || "-";


    document.getElementById(
        "countryCode"
    ).textContent =
        statistic.countryCode || "-";


    document.getElementById(
        "continent"
    ).textContent =
        statistic.continent || "-";


    document.getElementById(
        "recordDate"
    ).textContent =
        statistic.recordDate || "-";


    document.getElementById(
        "casesPerMillion"
    ).textContent =
        formatNumber(
            statistic.casesPerMillion
        );


    document.getElementById(
        "deathsPerMillion"
    ).textContent =
        formatNumber(
            statistic.deathsPerMillion
        );


    /* Create charts */

    createCharts(statistic);
}


/* =========================================
   SEARCH COUNTRY
   ========================================= */

async function searchCountry() {

    const input =
        document.getElementById(
            "countryInput"
        );


    const country =
        input.value.trim();


    if (!country) {

        showMessage(
            "Please enter a country name.",
            "error"
        );

        return;
    }


    try {

        showLoading();


        showMessage(
            "Loading country data...",
            "info"
        );


        const response =
            await fetch(
                `${API_URL}/country/${encodeURIComponent(country)}`
            );


        if (!response.ok) {

            if (response.status === 404) {

                throw new Error(
                    "Country data not found."
                );
            }


            throw new Error(
                "Unable to fetch country data."
            );
        }


        const statistic =
            await response.json();


        displayStatistic(statistic);


        showMessage(
            `${statistic.country} data loaded successfully.`,
            "success"
        );


    } catch (error) {

        console.error(
            "Search error:",
            error
        );


        showMessage(
            error.message ||
            "Something went wrong.",
            "error"
        );


    } finally {

        hideLoading();
    }
}


/* =========================================
   REFRESH COUNTRY
   ========================================= */

async function refreshCountry() {

    const input =
        document.getElementById(
            "countryInput"
        );


    const country =
        input.value.trim();


    if (!country) {

        showMessage(
            "Please enter a country name.",
            "error"
        );

        return;
    }


    try {

        showLoading();


        showMessage(
            "Refreshing country data...",
            "info"
        );


        const response =
            await fetch(
                `${API_URL}/refresh/${encodeURIComponent(country)}`,
                {
                    method: "PUT"
                }
            );


        if (!response.ok) {

            const errorText =
                await response.text();

            console.error(
                "Refresh error:",
                errorText
            );

            throw new Error(
                "Unable to refresh country data."
            );
        }


        const statistic =
            await response.json();


        displayStatistic(statistic);


        await loadAllCountries();


        showMessage(
            `${statistic.country} data refreshed successfully.`,
            "success"
        );


    } catch (error) {

        console.error(
            "Refresh error:",
            error
        );


        showMessage(
            error.message ||
            "Unable to refresh country data.",
            "error"
        );


    } finally {

        hideLoading();
    }
}


/* =========================================
   LOAD ALL COUNTRIES
   ========================================= */

   async function loadAllCountries() {

       try {

           const response =
               await fetch("/api/covid");

           if (!response.ok) {
               throw new Error(
                   "Unable to load saved statistics."
               );
           }

           const statistics =
               await response.json();

           displayCountryTable(statistics);

           populateCountryDropdown(statistics);

           console.log(
               "Countries loaded:",
               statistics.length
           );

       } catch (error) {

           console.error(error);

       }
   }

/* =========================================
   DISPLAY COUNTRY TABLE
   ========================================= */

function displayCountryTable(
    statistics
) {

    const tableBody =
        document.getElementById(
            "countryTableBody"
        );


    if (!tableBody) {

        console.error(
            "countryTableBody not found."
        );

        return;
    }


    tableBody.innerHTML = "";


    if (
        !statistics ||
        statistics.length === 0
    ) {

        tableBody.innerHTML = `

            <tr>

                <td
                    colspan="8"
                    style="text-align:center;">

                    No COVID statistics available.

                </td>

            </tr>

        `;

        return;
    }


    statistics.forEach(
        statistic => {

            const row =
                document.createElement(
                    "tr"
                );


            row.innerHTML = `

                <td>
                    ${statistic.id ?? "-"}
                </td>

                <td>
                    ${statistic.country ?? "-"}
                </td>

                <td>
                    ${statistic.continent ?? "-"}
                </td>

                <td>
                    ${formatNumber(
                        statistic.totalCases
                    )}
                </td>

                <td>
                    ${formatNumber(
                        statistic.totalDeaths
                    )}
                </td>

                <td>
                    ${formatNumber(
                        statistic.totalRecovered
                    )}
                </td>

                <td>
                    ${formatNumber(
                        statistic.activeCases
                    )}
                </td>

                <td>
                    ${statistic.recordDate ?? "-"}
                </td>

            `;


            tableBody.appendChild(
                row
            );
        }
    );
}


/* =========================================
   POPULATE COUNTRY DROPDOWN
   ========================================= */

   function populateCountryDropdown(statistics) {

       const countrySelect =
           document.getElementById("countrySelect");

       if (!countrySelect) {
           console.error("countrySelect not found.");
           return;
       }

       countrySelect.innerHTML = "";

       statistics.forEach(statistic => {

           const option =
               document.createElement("option");

           option.value = statistic.country;

           option.textContent = statistic.country;

           countrySelect.appendChild(option);
       });

       console.log(
           "Countries loaded:",
           countrySelect.options.length
       );
   }

/* =========================================
   COMPARE COUNTRIES
   ========================================= */

async function compareCountries() {

    const countrySelect =
        document.getElementById(
            "countrySelect"
        );


    if (!countrySelect) {

        console.error(
            "countrySelect element not found."
        );

        return;
    }


    const selectedCountries =
        Array.from(
            countrySelect.selectedOptions
        )
        .map(
            option => option.value
        );


    console.log(
        "Selected countries:",
        selectedCountries
    );


    if (
        selectedCountries.length < 2
    ) {

        showMessage(
            "Please select at least 2 countries.",
            "error"
        );

        return;
    }


    if (
        selectedCountries.length > 5
    ) {

        showMessage(
            "Please select maximum 5 countries.",
            "error"
        );

        return;
    }


    try {

        showLoading();


        showMessage(
            "Preparing country comparison...",
            "info"
        );


        const response =
            await fetch(API_URL);


        if (!response.ok) {

            throw new Error(
                "Unable to load country data."
            );
        }


        const statistics =
            await response.json();


        const selectedStatistics =
            statistics.filter(
                statistic =>
                    selectedCountries.includes(
                        statistic.country
                    )
            );


        console.log(
            "Comparison data:",
            selectedStatistics
        );


        if (
            selectedStatistics.length < 2
        ) {

            throw new Error(
                "Not enough country data available for comparison."
            );
        }


        createComparisonChart(
            selectedStatistics
        );


        showMessage(
            "Country comparison created successfully.",
            "success"
        );


    } catch (error) {

        console.error(
            "Comparison error:",
            error
        );


        showMessage(
            error.message ||
            "Unable to compare countries.",
            "error"
        );


    } finally {

        hideLoading();
    }
}


/* =========================================
   MESSAGE FUNCTION
   ========================================= */

function showMessage(
    message,
    type
) {

    const messageElement =
        document.getElementById(
            "message"
        );


    if (!messageElement) {
        return;
    }


    messageElement.textContent =
        message;


    if (type === "error") {

        messageElement.style.color =
            "#dc3545";

    } else if (type === "success") {

        messageElement.style.color =
            "#198754";

    } else {

        messageElement.style.color =
            "#1f4e79";
    }
}


/* =========================================
   LOADING
   ========================================= */

function showLoading() {

    const buttons =
        document.querySelectorAll(
            "button"
        );


    buttons.forEach(
        button => {

            button.disabled = true;

        }
    );
}


function hideLoading() {

    const buttons =
        document.querySelectorAll(
            "button"
        );


    buttons.forEach(
        button => {

            button.disabled = false;

        }
    );
}


/* =========================================
   DOM LOADED
   ========================================= */

document.addEventListener(
    "DOMContentLoaded",
    () => {

        console.log(
            "COVID Tracker frontend loaded."
        );


        const countryInput =
            document.getElementById(
                "countryInput"
            );


        const searchButton =
            document.getElementById(
                "searchButton"
            );


        const refreshButton =
            document.getElementById(
                "refreshButton"
            );


        const loadAllButton =
            document.getElementById(
                "loadAllButton"
            );


        const compareButton =
            document.getElementById(
                "compareButton"
            );


        /* Default country */

        countryInput.value =
            "India";


        /* Search */

        searchButton.addEventListener(
            "click",
            searchCountry
        );


        /* Refresh */

        refreshButton.addEventListener(
            "click",
            refreshCountry
        );


        /* Load all */

        loadAllButton.addEventListener(
            "click",
            loadAllCountries
        );


        /* Compare */

        if (compareButton) {

            compareButton.addEventListener(
                "click",
                compareCountries
            );

        }


        /* Enter key */

        countryInput.addEventListener(
            "keypress",
            event => {

                if (
                    event.key === "Enter"
                ) {

                    searchCountry();

                }

            }
        );


        /* Initial loading */

        searchCountry();

        loadAllCountries();

    }
);