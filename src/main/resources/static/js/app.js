$(document).ready(function() {
	console.log("ready!");
	getContacts();

});

function getContacts() {
	$.ajax({
		url: "/api/user/contactList", success: function(result) {
			var data = "";
			for (var i = 0; i < result.length; i++) {
				var cd = result[i].contacts;
				var contactInfo = "</td><td></td><td></td></tr>";
				if (cd != undefined && cd.length != 0) {
					contactInfo = "</td><td>" + cd[0].number + "</td><td>" + cd[0].active + "</td></tr>";
				}

				data = data + "<tr><td><a onclick=editContact(" + result[i].id + ")><img alt=\"Edit\" src=\"/images/edit.png\" width=\"20\" height=\"20\"></a></td><td>" + result[i].firstName + "</td><td>" + result[i].lastName + "</td><td>" + result[i].email + contactInfo;
			}
			$("#contactData")[0].innerHTML = data;
		}
	});
}

function addContact() {
	var userData = {
		firstName: $('#firstname').val(),
		lastName: $('#lastname').val(),
		email: $('#email').val(),
		userName: $('#username').val(),
		password: $('#password').val(),
		number: $('#pnumber').val()
	};

	$.ajax({
		url: '/api/user/add',
		type: 'post',
		dataType: 'json',
		success: function(result) {
			$('#myModal').on('hidden', function() {
				$.clearInput();
			});
			getContacts();
			$('#firstname').val('');
			$('#lastname').val('');
			$('#email').val('');
			$('#username').val('');
			$('#password').val('');
			$('#pnumber').val('');
			$('#myModal').modal('hide');
		},
		data: { userData: JSON.stringify(userData) }
	});

}