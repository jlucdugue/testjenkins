var refresh = function() {
	$
			.ajax({
				url : 'api/personnes/',
				type : 'GET'
			})
			.done(
					function(data) {
						$('#personneTable tbody').empty();
						for (personneKey in data) {
							var personne = data[personneKey];
							$('#personneTable tbody')
									.append(
											$('<tr><td>'
													+ personne.nom
													+ '</td><td>'
													+ personne.prenom
													+ '</td><td><button class="supprimer" data-id="'
													+ personne.id
													+ '">supprimer</button></td><td><button class="modifierNavigation" data-id="'
													+ personne.id
													+ '" data-nom="'
													+ personne.nom
													+ '" data-prenom="'
													+ personne.prenom
													+ '">modifier</button></td></tr>'))
						}

						$('.modifierNavigation').on('click', function(e) {
							var id = $(this).attr('data-id');
							$('#nomInput').val($(this).attr('data-nom'));
							$('#prenomInput').val($(this).attr('data-prenom'));
							$('#modifier').attr('data-id', id);
							$('#creer').hide();
							$('#modifier').show();
							$('#list').hide();
							$('#form').show();
						});

						$('.supprimer').on('click', function(e) {
							var id = $(this).attr('data-id');
							$.ajax({
								url : 'api/personnes/' + id,
								type : 'DELETE'
							}).done(function(data) {
								refresh();
							});

						});
					});
};

$(function() {
	$('#creerNavigation').on('click', function(e) {
		$('#list').hide();
		$('#form').show();
		$('#creer').show();
		$('#modifier').hide();
	});

	$('#creer').on('click', function(e) {
		var data = {
			nom : $('#nomInput').val(),
			prenom : $('#prenomInput').val()
		}
		$.ajax({
			url : 'api/personnes/',
			type : 'POST',
			data : JSON.stringify(data),
			contentType : 'application/json'
		}).done(function(data) {
			refresh();
			$('#list').show();
			$('#form').hide();
		});

	});

	$('#modifier').on('click', function(e) {
		var data = {
			nom : $('#nomInput').val(),
			prenom : $('#prenomInput').val()
		}
		var id = $(this).attr('data-id');
		$.ajax({
			url : 'api/personnes/' + id,
			type : 'PUT',
			data : JSON.stringify(data),
			contentType : 'application/json'
		}).done(function(data) {
			refresh();
			$('#list').show();
			$('#form').hide();
		});

	});

	$('#form').hide();
	refresh();
});