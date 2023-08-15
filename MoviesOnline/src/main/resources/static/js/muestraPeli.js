let puntuaciones=[];
var chart;
$(document).ready(function(){
    
    $("#btn").click(function(){
        $("#grafica").toggle("blind", 1000, callback);
    })
    // BOTÓN JQUERY UI
    $( ".widget input[type=submit], .widget a, .widget button" ).button();
})

function generateChart(ratings) {
    // Añadimos en un array todas las puntuaciones de esa película/serie
    let valor;
    ratings.forEach(rating => {
        if (rating.Value.endsWith("%")) {
            valor=parseFloat(rating.Value.split("%")[0])/10;
        }
        else{
            valor=parseFloat(rating.Value.split("/")[0]);
            if(valor>10){ //Si está puntuado sobre 100
                valor=valor/10;
            }
        }
        puntuaciones.push([rating.Source, valor]);
    });

    // INTRODUCE LAS PUNTUACIONES EN LA GRÁFICA
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
}

function drawChart() {
    // Crea la tabla
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Fuente');
    data.addColumn('number', 'Valoración');
    data.addRows(puntuaciones);
    
    var options = {
        'title':'Valoraciones de distintas fuentes', 
        "titleTextStyle": {color: "white"}, 
        'width':700, 'height':400, 
        chartArea: {
            'width': '70%'
        },
        bar: {
            groupWidth: "100%"
        },
        "backgroundColor":{fill: "transparent"}, 
        "legend": {textStyle: {color: "white"}}, 
        "tooltip": {textStyle: {color: "black"}}, 
        "hAxis":{textStyle:{color: "white"}}, 
        "vAxis":{textStyle:{color: "white"}}};
    
    chart = new google.visualization.ColumnChart(document.getElementById('grafica'));
    chart.draw(data, options);
}
function callback() {
    setTimeout(function() {
        $( "#effect" ).removeAttr( "style" ).show().fadeIn();
    }, 1000 );
}

  // Redibuja la gráfica cuando el tamaño de la ventana cambie
  window.addEventListener('resize', function() {
    google.charts.setOnLoadCallback(drawChart);
  });