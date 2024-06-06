function showAllRoom() {
    $.ajax({
        //doi co security config thi moi su dung toi token
        // headers: {"Authorization": "Bearer " + localStorage.getItem('token')},
        method: "GET",
        url: "http://localhost:8080/api/rooms",
        success: function (data) {
            let roomList = $('#roomList');
            roomList.empty(); // xoa noi dung cu

            data.forEach(function(room){
                let roomItem = `
                    <div class="room-item">
                        <td>${room.id}</td>
                        <td>${room.code}</td>
                        <td>${room.description}</td>
                        <td>${room.type}</td>
                        <td>${room.price}</td>
                        <td>${room.status ? 'Available' : 'Booked'}</td>
                        <img src="${room.image}" alt="${room.code}" width="200" height="200"/>
                    </div>`
                ;
                roomList.append(roomItem)
            })
        },
        error: function (error) {
            console.log("Error Show Room List: " ,error);
        }
    })
}
showAllRoom();