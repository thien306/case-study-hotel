function getUserDetail() {
    let userString = localStorage.getItem("user");
    if (userString) {
        return JSON.parse(userString);
    }
    return null;
}

let userPrinciple = getUserDetail();
let token = null;
if (userPrinciple) {
    token = userPrinciple.token;
}
const user = userPrinciple;

// function getCustomerInfo() {
//     $.ajax({
//         url: `http://localhost:8080/api/customers/${user.id}`,
//         method: "GET",
//         headers:{
//             "Authorization": `Bearer ${token}`
//         },
//         success:function(data) {
//             $("#name").val(data.name);
//             $("#birthday").val(data.birthday);
//             $("#email").val(data.email);
//             $("#phoneNumber").val(data.phoneNumber);
//             $("#avatar").val(data.avatar);
//         },
//         error: function(xhr, status, error) {
//             console.error("Error fetching customer info:", error);
//             alert("Failed to fetch customer information.");
//         }
//     })
// }
// getCustomerInfo();

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
            window.location.href = "../../templates/customer_update.html"
        },
        error: function (xhr, status, error) {
            console.error("Error updating customer information:", error);
            alert("Failed to update customer information.");
        }
    })
}


