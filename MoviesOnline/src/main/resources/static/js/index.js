let coloresSecundarios={"good":"#0f6533", "medium": "#655104", "bad": "#77170cd1"};
let sugerencias=["The Last of Us", "Peaky Blinders", "The Simpsons", "Miércoles", "The Walinkg Dead", "Los Vengadores", "Spider-Man", "Hulk", "Iron-Man"];
$(document).ready(function(){
	// Autocomplete
	$("#titulo").autocomplete({
		source: sugerencias
	});
	// $("#btnSearch").click(filterCall);
	$("#search_form").submit(e => filterCall(e));
})
function filterCall(event){
	event.preventDefault();
	let titulo=$("#titulo").val();
    let tipo=$("#tipo").val();
	$.ajax({
		url: "/filterMovie",
		type: "GET",
		data: {"titulo": titulo, "tipo": tipo},
		success: function(response){
			console.log(response)
			showFilterMovies(response)
		},
		error: function(xhr, status, error){
			alert("Se ha producido un error inesperado");
		}
	})
}

function showFilterMovies(movies){
	let container = $("#movies__container");
	container[0].innerHTML = "";
	movies.forEach(peli => {
		container.append("<div id='" + peli.imdbID + "' class='card text-center'>" +
			"<figure>" +
			"<a href='movie/" + peli.imdbID + "'><img src='" + peli.Poster + "' alt='' class='img-fluid w-100 d-block'></a>" +
			"</figure>" +
			"<p><strong>" + peli.Title + "</strong></p>" +
			"</div>");
	});
}

function addRatingClass(ratingDiv) {
    //Rating de la película
    ratingDiv=$(ratingDiv);
    let rating="";
    let puntuacion=parseFloat(ratingDiv.text());

    if (puntuacion<4) {
        ratingDiv.addClass("bad");
        rating="bad";
    }
    else if(puntuacion>=4 && puntuacion<6){
        ratingDiv.addClass("medium");
        rating="medium";
    }
    else{
        ratingDiv.addClass("good");
        rating="good";
    }
    
    let colorRating=ratingDiv.css("background-color");
    let gradient=`conic-gradient(${colorRating} ${36*puntuacion}deg, ${coloresSecundarios[rating]} ${36*puntuacion}deg)`;
    ratingDiv.css("background", gradient);
}

