function getUserDetail() {
    let userString = localStorage.getItem("user");
    if (userString) {
        let user = JSON.parse(userString);
        if (user && user.token) {
            return user;
        }
    }
    return null;
}

let userPrinciple = getUserDetail();
let token = null;
if (userPrinciple) {
    token = userPrinciple.token;
}
const user = userPrinciple;
if (!user){
    window.location.href = "../../templates/login.html";
}

function updatePassword() {
    const newPassword = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (newPassword !== confirmPassword) {
        $('#alertError').removeClass("d-none").text("New password and confirm password do not match");
        return;
    }

    const request = {
        newPassword: newPassword,
    }

    $.ajax({
        url: "http://localhost:8080/api/users/update-password",
        method: "PUT",
        headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json",
        },
        data: JSON.stringify(request),
        success: function (response) {
            $('#alertSuccess').removeClass("d-none").text(response);
            window.location.href = "../../templates/list.html";
        },
        error: function (xhr, status, error) {
            $('#alertError').removeClass("d-none").text(xhr.responseText);
        }
    })
}

// $("button").click(function () {
//     updatePassword();
// })