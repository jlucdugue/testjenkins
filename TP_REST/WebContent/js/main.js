$(function() {
	$.ajax({
		url : "api/personnes/",
		type : 'GET'
	}).done(
			function(data) {
				console.log(data);
				for (personneKey in data) {
					var personne = data[personneKey];
					$('#personneTable tbody').append(
							$('<tr><td>' + personne.nom + '</td><td>'
									+ personne.prenom + '</td></tr>'))
				}
			});
});