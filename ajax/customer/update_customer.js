$(document).ready(function () {
    let customerId = localStorage.getItem("customerId");
    if (!customerId) {
        alert("Please login first!")
        window.location.href = "customer_login.html";
        // return;
    }

    $.ajax({
        url: `http://localhost:8080/api/customers/${customerId}`,
        method: 'GET',
        success: function (customer) {
            $('#name').val(customer.name);
            $('#birthday').val(customer.birthday);
            $('#email').val(customer.email);
            $('#phoneNumber').val(customer.phoneNumber);
            $('#avatar').val(customer.avatar);
        },
        error: function (error) {
            console.error("Error fetching customer" ,error);
            alert("Error fetching customer details");
        }
    })
})

function updateCustomer() {
    let customerId = localStorage.getItem("customerId");
    let customerData = {
        name: $('#name').val(),
        birthday: $('#birthday').val(),
        password: $('#password').val(),
        phoneNumber: $('#phoneNumber').val(),
        avatar: $('#avatar').val(),
    }

    $.ajax({
        url: `http://localhost:8080/api/customers/${customerId}`,
        type:"PUT",
        contentType: "application/json",
        data: JSON.stringify(customerData),
        success: function (data) {
            alert("Customer updated successfully!");
            window.location.href = "customer_room_list.html";
        },
        error: function (error) {
            console.error("Error updating customer" ,error);
            alert("Error updating customer details");
        }
    })
}