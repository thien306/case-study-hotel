function getUserDetail() {
    let userString = localStorage.getItem("user");
    if (userString) {
        return JSON.parse(userString);
    }
    return null;
}

// let userPrinciple = getUserDetail();
let token = null;
let user = getUserDetail();
if (user) {
    token = user.token;
}

$(document).ready(function () {
    let user = getUserDetail();
    if (user) {
        $('#username').val(user.username);
    }
})

function updatePassword() {
    let currentPassword = $('#currentPassword').val();
    let newPassword = $('#newPassword').val();
    let confirmPassword = $('#confirmPassword').val();

    if (newPassword !== confirmPassword) {
        $('#alertError').removeClass('d-none').text('New password and confirm password do not match');
        return;
    }

    let request = {
        currentPassword: currentPassword,
        newPassword: newPassword,
        confirmPassword: confirmPassword
    };

    $.ajax({
        url: "http://localhost:8080/api/users/update-password",
        method: "PUT",
        headers: {
            "Authorization": `Bearer ` + token,
            "Content-Type": "application/json",
        },
        data: JSON.stringify(request),
        success: function(response) {
            $('#alertSuccess').removeClass("d-none").text(response);
            $('#currentPassword').val('');
            $('#newPassword').val('');
            $('#confirmPassword').val('');
        },
        error: function(xhr, status, error) {
            $('#alertError').removeClass("d-none").text(xhr.responseText);
        }
    });
}

// $("button").click(function () {
//     updatePassword();
// })