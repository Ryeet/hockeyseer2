/*<![CDATA[*/

    var stats = [# th:utext="${stats.win}" /]
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: ["Win","Tie","Loss"],
        datasets: [{
        backgroundColor: [
        "#2ecc71",
        "#34495e",
        "#e74c3c"
        ],
        data: [12, 5, 9]
        }]
    }
    });

/*]]>*/