const password = document.querySelector("#password");
const form = document.querySelector("form");

const eyeIcon = document.querySelector(".eye-icon");
const passwordField = document.getElementById("password");

function togglePasswordVisibility() {
    const type =
        passwordField.getAttribute("type") === "password" ? "text" : "password";
    passwordField.setAttribute("type", type);

    // Change the eye icon based on password visibility
    eyeIcon.textContent = type === "password" ? "visibility" : "visibility_off";
}

// form.addEventListener("submit", (e) => {
//   e.preventDefault();
//
//   if (checkPassword() < 5) {
//     return Toastify({
//       text: "Error: Insecure password. Please choose a stronger password.",
//       className: "warning",
//     }).showToast();
//   }
//
//   return Toastify({
//     text: "Thank you! The form has been sent.",
//     className: "success",
//   }).showToast();
//   // Lastly, let's make each toast have it's own color (red/green)
// });

function updateInnerBar(level) {
    const passMeter = document.querySelector("#pass-meter");
    const meterOuter = document.querySelector("#meter-outer");
    const meterInner = document.querySelector("#meter-inner");
    const pwdDesc = document.querySelector("#pwd-desc");

    let weakHint = `At least 6 characters long.
At least one uppercase letter.
One lowercase letter.
One number.
One special character.`;

    if (level === 0) {
        meterOuter.style.height = "0";
        meterOuter.style.border = "none";
        passMeter.style.marginTop = "-0.9rem";
        pwdDesc.style.fontSize = "0";

        return;
    }

    passMeter.style.marginTop = "0";
    pwdDesc.style.fontSize = "0.9rem";
    meterOuter.style.height = "0.5rem";
    meterOuter.style.border = "1px solid #E0E2E9";
    meterInner.style.width = level + "%";

    if (level <= 40) {
        meterInner.style.backgroundColor = "#ff4757";
        pwdDesc.innerText = "Your password is weak";
        pwdDesc.title = weakHint;
    } else if (level > 40 && level <= 80) {
        meterInner.style.backgroundColor = "#fbc531";
        pwdDesc.innerText = "Your password is weak";
        pwdDesc.title = weakHint;
    } else {
        meterInner.style.backgroundColor = "#2ed573";
        pwdDesc.innerText = "Your password is strong";
        pwdDesc.title = "Nice!";
    }
}

//our validation logic
function checkPassword() {
    let value = password.value.trim();

    let level =
        hasMoreThan12Chars(value) +
        hasNumber(value) +
        hasSpecialChar(value) +
        hasUpperCase(value) +
        hasLowerCase(value);

    // Now, let's update our password
    // meter with this number.
    updateInnerBar(level * 2);
    return level;
}

password.addEventListener("keyup", checkPassword);

function hasMoreThan12Chars(value) {
    return value.length > 6;
}

function hasNumber(value) {
    // Regex!
    return /\d/.test(value);
}

function hasSpecialChar(value) {
    // Regex again...
    return /[!@#$&*()[\]/.,;:]/.test(value);
}

function hasUpperCase(value) {
    // Once more!
    return /[A-Z]/.test(value);
}

function hasLowerCase(value) {
    return /[a-z]/.test(value);
}

let errorCount = 0;

// đã đưa sang login
function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let user = {
        "username": username,
        "password": password
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        method: "POST",
        data: JSON.stringify(user),
        url: "http://localhost:8080/api/auth/login",
        error: function (xhr, status, error) {
            if (xhr.status == 400) {
                errorCount++
                if (errorCount == 5) {
                    Swal.fire({
                        title: 'Are you sure?',
                        text: 'You will not be able to revert this!',
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonText: 'Yes, delete it!',
                        cancelButtonText: 'No, cancel!',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            // Xử lý khi người dùng xác nhận
                        } else if (result.dismiss === Swal.DismissReason.cancel) {
                            // Xử lý khi người dùng hủy bỏ
                        }
                    });
                    alert("sign in too fast")
                } else {
                    error = document.getElementById("error").innerText = "User name or password is not correct"
                }
            }
            console.log("errors")
        },
        success: function (data) {
            localStorage.setItem("user", JSON.stringify(data));
            console.log("hello")
            window.location.href = "../templates/list.html"
        }
    })
}

