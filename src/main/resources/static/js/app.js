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
					var status = cd[0].active ? "Active" : "Inactive";
					var moreCd = (cd.length > 1) ? "<a style=\"float: right;\" onclick='showContacts(" + JSON.stringify(result[i]) +
						")'><img alt=\"Show\" src=\"/images/show.png\" width=\"30\" height=\"20\" style=\"cursor: pointer;margin-right: 8px;\"></a>" : "";
					contactInfo = "</td><td>" + cd[0].number + "   " + moreCd + "</td><td>" + status + "</td></tr>";
				}

				data = data + "<tr id=\"row-" + result[i].id + "\"><td><a onclick=editContact(" + result[i].id +
					")><img alt=\"Edit\" src=\"/images/edit.png\" width=\"20\" height=\"20\" style=\"cursor: pointer;\"></a></td><td>" +
					result[i].firstName + "</td><td>" + result[i].lastName + "</td><td>" + result[i].email + contactInfo;
			}
			$("#contactData")[0].innerHTML = data;
		}
	});
}

function showContacts(result) {
	$('#contactModal').modal('show');
	var data = "";
	var cd = result.contacts;
	for (var i = 0; i < cd.length; i++) {
		var check = cd[i].active ? 'checked' : '';
		data = data + "<tr id=\"con-" + cd[i].id + "\"> <td style=\"padding-left: 20px;\"> <input type=\"text\" id=\"pnumber-"+ cd[i].id 
		+ "\" class=\"form-control\" style=\"display: inline-block; width: 60%; float: right; margin-right: 30px;\" value=\""+ cd[i].number 
		+ "\"/> </td> <td> <input type=\"checkbox\" id=\"active-" + cd[i].id + "\" class=\"form-control\" "+ check +" /></td></tr>";
	}

	$("#contactListData")[0].innerHTML = data;
}


function editContact(id) {
	var r = $('#row-' + id)[0];
	$('#editModal').modal('show');
	$('#id').val(id);
	$('#firstname1').val(r.childNodes[1].innerText);
	$('#lastname1').val(r.childNodes[2].innerText);
	$('#email1').val(r.childNodes[3].innerText);
	$('#pnumber1').val(r.childNodes[4].innerText);
	if (r.childNodes[5].innerText == "true") {
		$('#active').prop('checked', true);
	} else {
		$('#active').prop('checked', false);
	}
}

function updateContact() {
	var userData = {
		id: $('#id').val(),
		number: $('#pnumber1').val(),
		active: $('#active').val() == 'on' ? true : false
	};

	$.ajax({
		url: '/api/user/update',
		type: 'post',
		dataType: 'json',
		success: function(result) {
			$('#editModal').on('hidden', function() {
				$.clearInput();
			});
			getContacts();
			$('#editModal').modal('hide');
		},
		data: { userData: JSON.stringify(userData) }
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