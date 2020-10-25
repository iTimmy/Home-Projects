$(document).ready(function () {
    loadContacts();

    $("#add-button").click(addContacts());
});

function loadContacts() {
    clearContacts();
    var contentRows = $("#contentRows");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/contacts",
        success: function(contactArray) {
            $.each(contactArray, function (index, contact) {
                var name =  contact.firstName + ' ' + contact.lastName;
                var lastName = contact.lastName;

                var row = "<tr>";
                    row += "<td>" + name + "</td>";
                    row += "<td>" + lastName + "</td>";
                    row += "<td><a>Edit</a></td>";
                    row += "<td><a>Delete</a></td>";
                    row += "</tr>";

                contentRows.append(row);
            })
        },
        error: function() {
            $("#errorMessages")
                .append($('<li')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        }
    });
}

function addContacts() {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/contact',
        data: JSON.stringify({
            firstName: $('#add-first-name').val(),
            lastName: $('#add-last-name').val(),
            email: $('#add-email').val(),
            password: $('#add-password').val()
        }),
        headers: {
            'Accepts': 'application/json',
            'Content-Type': 'application/json'
        },
        'datatype': 'json',
        success: function () {
            $('#errorMessages').empty();
            $('#add-first-name').empty();
            $('#add-last-name').empty();
            $('#add-email').empty();
            $('#add-password').empty();
            loadContacts();
        },
        error: function () {
            $("#errorMessages")
                .append($('<li')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        }
    })
}

function clearContacts() {
    $("#contentRows").empty();
}