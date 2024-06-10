$(document).ready(function(){
    showAllCustomer()
})

function showAllCustomer(){
    $.ajax({
        url:"http://localhost:8080/api/customers",
        type:"GET",
        dataType:"json",
        success:function(customers){
            displayCustomers(customers)
        },
        error:function(error){
            console.error("Error Showing Customers: " + error.message);
        }
    })
}

function createCustomer(){
    let customerData = {
        name: $('#name').val(),
        birthday: $('#birthday').val(),
        email: $('#email').val(),
        password: $('#password').val(),
        phoneNumber: $('#phoneNumber').val(),
        avatar: $('#avatar').val(),
    }

    $.ajax({
        url:"http://localhost:8080/api/customers",
        type:"POST",
        contentType: "application/json",
        data: JSON.stringify(customerData),
        success: function (data) {
            window.location.href = "login.html";
        },
        error: function (error) {
            console.error("Error Created Customer: " + error.message);
        }
    })
}

function displayCustomers(customers) {
    let tableBody = $('#customerList tbody');
    tableBody.empty();

    customers.forEach(function (customer) {
        let rowElement = $("<tr>");
        rowElement.append($("<td>").text(customer.id));
        rowElement.append($("<td>").text(customer.name));
        rowElement.append($("<td>").text(formatDate(customer.birthday)));
        rowElement.append($("<td>").text(customer.email));
        rowElement.append($("<td>").text(customer.password));
        rowElement.append($("<td>").text(customer.phoneNumber));
        rowElement.append($("<td>").html(customer.avatar ? `<img src="${customer.avatar}" alt="Avatar" style="width:50px; height:50px;">` : "No Avatar"));

        tableBody.append(rowElement);
    })
}

function loginCustomer() {
    let loginData = {
        email: $('#email').val(),
        password: $('#password').val(),
    }

    $.ajax({
        url:"http://localhost:8080/api/auth/login",
        type:"POST",
        contentType: "application/json",
        data: JSON.stringify(loginData),
        success: function (data) {
            window.location.href = "../roomcv/list.html";
        },
        error: function (error) {
            alert("Invalid Email or Password");
        }
    })
}

function formatDate(dateString) {
    let date = new Date(dateString);
    return date.toLocaleDateString();
}