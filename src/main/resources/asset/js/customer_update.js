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
// const user = userPrinciple;
// if (!user){
//     window.location.href = "../../templates/login.html";
// }

function getCustomerInfo() {
    if (user && user.username){
        $.ajax({
            url: `http://localhost:8080/api/customers/${user.id}`,
            method: "GET",
            headers:{
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            },
            success:function(data) {
                $("#name").val(data.name);
                $("#birthday").val(data.birthday);
                $("#email").val(data.email);
                $("#phoneNumber").val(data.phoneNumber);
                $("#avatar").val(data.avatar);
            },
            error: function(xhr, status, error) {
                console.error("Error fetching customer info:", error);
                alert("Failed to fetch customer information.");
            }
        })
    }
}
getCustomerInfo();

function updateCustomer() {
    const newCustomerInfo = {
        name: $("#name").val(),
        birthday: $("#birthday").val(),
        phoneNumber: $("#phoneNumber").val(),
        avatar: $("#avatar").val(),
    }

    $.ajax({
        url: `http://localhost:8080/api/customers/update-by-username`,
        method: "PUT",
        headers: {
            "Authorization": `Bearer ${user.token}`,
            "Content-Type": "application/json",
        },
        data: JSON.stringify(newCustomerInfo),
        success:function(data) {
            alert("Successfully updated customer information.");
            // window.location.href = "../../templates/list.html"
        },
        error: function (xhr, status, error) {
            console.error("Error updating customer information:", error);
            alert("Failed to update customer information.");
        }
    })
}