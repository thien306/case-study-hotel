$(document).ready(function () {
    $("#show-update").hide();


    $('#add-room').on("submit", function (event) {
        event.preventDefault();
        const id = $('#room-id').val();

        if (id) {
            updateRoom(id);
        } else {
            addNewRoom();
        }
    });

    $('#display').on('click', function () {
        displayRoomList();
    });

    $('#display-create').on('click', function () {
        displayFormCreate();
    });

    $('#search-room').on('submit', function (event) {
        event.preventDefault();
        searchRoom();
    });
    displayRoomList();
});

let rs = docLocalStorage();
if (rs == null) {
    // window.location.href = "listRoom.html";
}
let token = rs.token;

function docLocalStorage() {
    let roomString = localStorage.getItem("u");
    let room = JSON.parse(roomString);
    return room;
}

function getRoom(room) {
    return `<tr>
        <td>${room.code}</td>
        <td>${room.description}</td>
        <td><img src="${room.image}"></td>
        <td>${room.price}</td>
        <td>${room.status}</td>
        <td>${room.type.name}</td>
        <th class="btn"><button class="deleteRoom" onclick="deleteRoom(${room.id})">Delete</button></th>
        <th class="btn"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter" onclick='showUpdateForm(${room.id})'>Update</button></th>
    </tr>`;
}

function getRoomType(room) {
    return `<tr>
        <td class="btnbookcode">${room.code}</td>
        <td class="btnbookdescription">${room.description}</td>
        <td class="btnbookimg"><img src="${room.image}"></td>
        <td class="btnbookprice">${room.price}</td>
        <td class="btnbookstatus">${room.status}</td>
        <td class="btnbooktype">${room.type.name}</td>
        <th class="btnbook"><button type="button" >book room</button></th>
        <th class="btnsee"><button type="button" >See details</button></th>
    </tr>`;
}

function getRoomPrice(room) {
    return `<tr>
        <td class="btnbookcode">${room.code}</td>
        <td class="btnbookdescription">${room.description}</td>
        <td class="btnbookimg"><img src="${room.image}"></td>
        <td class="btnbookprice">${room.price}</td>
        <td class="btnbookstatus">${room.status}</td>
        <td class="btnbooktype">${room.type.name}</td>
        <th class="btnbook"><button type="button" >book room</button></th>
        <th class="btnsee"><button type="button" >See details</button></th>
    </tr>`;
}

function showUpdateForm(id) {
    console.log("asd,", id)

    $.ajax({
        headers: {
            "Authorization": `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: `http://localhost:8080/api/rooms/${id}`,
        // data: JSON.stringify(formUpdateData)
        success: function (res) {
            $("#exampleModalCenter").click();
            handleClickUpdateForm();
            $('#room-id').val(res.id);
            $('#update-code').val(res.code);
            $('#update-description').val(res.description);
            $('#update-price').val(res.price);
            $('#update-image').val(res.image);
            $('#update-status').val(res.status.toString());
            $('#update-types').val(res.types.toString());

        },
        error: function (error) {
            console.error('Error searching rooms:', error);
        }
    });

}

function addNewRoom() {
    let tempImage = localStorage.getItem("tempImage");
    if (tempImage) {
        const code = $('#create-code').val();
        const description = $('#create-description').val();
        const image = JSON.parse(tempImage);
        const price = $('#create-price').val();
        const status = $('#create-status').val();
        const type = $('#create-types').val();
        const formData = {
            'code': code,
            'description': description,
            'image': image,
            'price': price,
            'status': status,
            'type': type,
        }
        console.log(formData)
        $.ajax({
            headers: {
                "Authorization": `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "http://localhost:8080/api/rooms",
            data: JSON.stringify(formData),
            success: function (res) {
                $("#exampleModal").click();
                alert(res?.message);
                displayRoomList();
                displayFormCreate();
                localStorage.removeItem("tempImage");
            },
            error: function (error) {
                console.error('Error searching rooms:', error);
            }
        });
    }

}

function displayFormCreate() {
    document.getElementById('roomList').style.display = "none";
    document.getElementById('add-room').style.display = "block";
    document.getElementById('update-room').style.display = "none";
    document.getElementById('display-create').style.display = "none";
    document.getElementById('title').style.display = "none";
}


function displayFormUpdate() {
    document.getElementById('roomList').style.display = "none";
    document.getElementById('add-room').style.display = "none";
    document.getElementById('update-room').style.display = "block";
    document.getElementById('display-create').style.display = "none";
    document.getElementById('title').style.display = "none";
}

let currentUpdateId = null;


function updateRoom(id) {
    let tempImage = localStorage.getItem("tempImage");
    let formData = null;
    if (tempImage) {
        const id = $('#room-id').val();
        const code = $('#update-code').val();
        const description = $('#update-description').val();
        const image = JSON.parse(tempImage);
        const price = $('#update-price').val();
        const status = $('#update-status').val();
        const type = $('#update-types').val();

        formData = {
            'id': id,
            'code': code,
            'description': description,
            'image': image,
            'price': price,
            'status': status,
            'type': type,
        }
    }
    console.log(formData)
    $.ajax({
        headers: {
            "Authorization": `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: `http://localhost:8080/api/rooms`,
        data: JSON.stringify(formData),
        processData: false,
        contentType: false,
        success: function (res) {
            $("#exampleModalCenter").click();
            alert(res?.message);
            displayRoomList();
            displayFormUpdate();
            localStorage.removeItem("tempImage");
        },
        error: function (error) {
            console.error('Error searching rooms:', error);
        }
    });
}

function deleteRoom(id) {
    $.ajax({
        headers: {
            "Authorization": "Bearer " + token
        },
        type: "DELETE",
        url: `http://localhost:8080/api/rooms/${id}`,
        success: function () {
            alert("Room deleted successfully");
            displayRoomList();
        }
    });
}

function hideForms() {
    // document.getElementById('add-room').style.display = "none";
    // document.getElementById('update-room').style.display = "none";
    document.getElementById('roomList').style.display = "block";
    // document.getElementById('display-create').style.display = "block";
    // document.getElementById('title').style.display = "block";
}

function searchRoom() {
    const searchQuery = $('#search-input').val();
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/api/rooms/search?search=${searchQuery}`,
        headers: {
            "Authorization": "Bearer " + token,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data) {
            roomList(data.content);
        },
        error: function (xhr, status, error) {
            console.error('Error searching rooms:', error);
            console.error('Response:', xhr.responseText);
        }
    });
}

function displayRoomList() {
    $.ajax({
        headers: {
            "Authorization": "Bearer " + token
        },
        method: "GET",
        url: "http://localhost:8080/api/rooms",
        success: function (data) {
            let content = '<table id="display-list" border="1"><tr>' +
                '<th>code</th>' +
                '<th>description</th>' +
                '<th class="img">image</th>' +
                '<th>price</th>' +
                '<th>status</th>' +
                '<th>type</th>' +
                '</tr>';
            for (let i = 0; i < data.content.length; i++) {
                content += getRoom(data.content[i]);
            }
            content += "</table>";
            $("#roomList").html(content);
            hideForms();
        }
    });

}

function handleClickUpdateForm() {
    $.ajax({
        url: 'http://localhost:8080/api/types',
        type: 'GET',
        crossDomain: true,
        contentType: 'application/json',
        dataType: "json",
        success: (response) => {
            console.log("response", response)
            let select = $("#update-types");
            select.empty();
            response.forEach(type => {
                select.append(new Option(type.name, type.id));
            });
        },
        error: (e) => {
            console.log(e);
        }
    })
}


function displayRooms(rooms) {
    let content = '<table id="display-list" border="1"><tr>' +
        '<th>code</th>' +
        '<th>description</th>' +
        '<th class="img">image</th>' +
        '<th>price</th>' +
        '<th>status</th>' +
        '<th>type</th>' +
        '</tr>';
    for (let i = 0; i < rooms.length; i++) {
        content += getRoomPrice(rooms[i]);
    }
    content += "</table>";
    $("#roomListType").html(content);
    hideForms();
}




