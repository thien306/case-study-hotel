$(document).ready(function(){
    $('#checkin').val(localStorage.getItem("checkin"));
    $('#checkout').val(localStorage.getItem("checkout"));
})

function confirmBooking() {
    if (confirm("Are you sure you want to book this room?")) {
        bookRoom()
    }
}

function bookRoom() {
    let roomIds = JSON.parse(localStorage.getItem("roomIds"));
    let checkin = $('#checkin').val();
    let checkout = $('#checkout').val();
    let customerId = localStorage.getItem("customerId");

    if (!customerId){
        alert("Please login to book a room");
        window.location.href = 'customer_login.html';
        return;
    }

    let bookingData = {
        customerId: customerId,
        checkinDate: checkin,
        checkoutDate: checkout,
        roomIds: [roomIds]
    }

    $.ajax({
        method: "POST",
        url: "http://localhost:8080/api/bookings",
        contentType: "application/json",
        data: JSON.stringify(bookingData),
        success: function (data) {
            alert("Booking Successfully!");
            window.location.href = "customer_room_list.html";
        },
        error: function (error) {
            if (error.status === 401) {
                alert("Your session has expired. Please login again.");
                window.location.href = 'customer_login.html';
            } else {
                alert("Booking Error: " + error.responseText);
            }
        }
    })
}