function showAllRoom() {
    $.ajax({
        // headers: {"Authorization": "Bearer " + localStorage.getItem('token')},
        method: "GET",
        url: "http://localhost:8080/api/homepage",
        success: function (data) {
            let tbody = $('#roomList tbody');
            tbody.empty(); // xoa noi dung cu cua tbody

            data.forEach(function(room){
                let roomItem = `
                    <tr>
                        <td class="hidden">${room.id}</td>
                        <td>${room.description}</td>
                        <td>${room.type}</td>
                        <td>${room.price}</td>
                        <td>${room.status ? 'Available' : 'Booked'}</td>
                        <td>${room.image ? `<img src="${room.image}" alt="" width="50" height="50"/>` : 'No Image'}</td>
                        <td>${room.status ? `<button onclick="roomBook(${room.id})">Book</button>` : ''}</td>
                    </tr>`;
                tbody.append(roomItem)
            })
        },
        error: function (error) {
            console.log("Error Show Room List: " ,error);
        }
    })
}
showAllRoom();

function getAvailableRooms(checkin, checkout) {
    $.ajax({
        method: "GET",
        url: `http://localhost:8080/api/homepage/available-room?checkin=${checkin}&checkout=${checkout}`,
        success: function (data) {
            let tbody = $('#roomList tbody');
            tbody.empty();
            data.forEach(function(room){
                let roomItem = `
                    <tr>
                        <td class="hidden">${room.id}</td>
                        <td>${room.description}</td>
                        <td>${room.type}</td>
                        <td>${room.price}</td>
                        <td>${room.status ? 'Available' : 'Booked'}</td>
                        <td><img src="${room.image}" alt="can't find the image" width="50" height="50"></td>
                        <td><button onclick="roomBook(${room.id})">Book</button></td>
                    </tr>`
                tbody.append(roomItem)
            })
        },
        error: function (error) {
            console.log("Error Getting Available Rooms: " + error.responseText);
        }
    })
}

function roomBook(roomId) {
    let checkin = $(`#checkinDate`).val()
    let checkout = $(`#checkoutDate`).val()
    let customerId = localStorage.getItem('customerId')

    if (!customerId){
        alert("Please login to book a room")
        window.location.href = 'customer_login.html';
        return;
    }

    localStorage.setItem('roomIds', JSON.stringify(roomId));
    localStorage.setItem('checkinDate', checkin);
    localStorage.setItem('checkoutDate', checkout);

    window.location.href = 'booking_check.html'

    // let bookingData = {
    //     customerId: 4,
    //     checkinDate: checkin,
    //     checkoutDate: checkout,
    //     roomIds: [roomId]
    // }

}

function searchRooms() {
    let checkin = $('#checkinDate').val()
    let checkout = $('#checkoutDate').val()

    getAvailableRooms(checkin, checkout);
}