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
    window.location.href = "listRoom.html";
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
        <th class="btn"><button class="updateRoom" onclick='showUpdateForm(${room.id})'>Update</button></th>
    </tr>`;
}
function showUpdateForm(id) {
    console.log("asd,",id)
    $("#show-update").show();
    $.ajax({
        headers: {
            "Authorization": "Bearer " + token
        },
        method: "GET",
        url: `http://localhost:8080/api/rooms?id=${id}`,
        success: function (data) {
            $('#room-id').val(data.id);
            $('#update-code').val(data.code);
            $('#update-description').val(data.description);
            $('#update-image').val(data.image);
            $('#update-price').val(data.price);
            $('#update-status').val(data.status);

            $('#update-type').val(data.typeList);
        }
    });

    // $('#room-id').val(room.id);
    // $('#update-code').val(room.code);
    // $('#update-description').val(room.description);
    // $('#update-image').val(room.image);
    // $('#update-price').val(room.price);
    // $('#update-status').val(room.status);
    // $('#update-type').val(room.type);
    //
    // currentUpdateId = room.id;

    // hideForms()
}

function addNewRoom() {
    let tempImage = localStorage.getItem("tempImage");
    if(tempImage){
        const code = $('#create-code').val();
        const description = $('#create-description').val();
        const image = JSON.parse(tempImage);
        const price =$('#create-price').val();
        const status = $('#create-status').val();
        const type = $('#create-types').val();
        const formData = {
            'code':code,
            'description':description,
            'image':image,
            'price':price,
            'status':status,
            'type':type,
        }
        $.ajax({
            headers: {
                "Authorization": `Bearer ${token}` ,
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
            error: function ( error) {
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

let currentUpdateId = null;



function updateRoom(id) {
    const code = $('#update-code').val();
    const description = $('#update-description').val();
    const image = $('#update-image')[0].files[0];
    console.log(image);

    const price = $('#update-price').val();
    const status = $('#update-status').val();
    const type = $('#update-type').val();

    const formData = new FormData();
    formData.append('code', code);
    formData.append('description', description);
    formData.append('image', image);
    formData.append('price', price);
    formData.append('status', status);
    formData.append('type', type);

    $.ajax({
        headers: {
            "Authorization": "Bearer " + token
        },
        type: "PUT",
        url: `http://localhost:8080/api/rooms/${id}`,
        data: formData,
        processData: false,
        contentType: false,
        success: function () {
            alert("Room updated successfully!");
            displayRoomList();
            hideForms();
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
                '<th>image</th>' +
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
