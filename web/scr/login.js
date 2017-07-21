function login() {
    let formData = document.getElementById('form').elements;
    params = "user=" + formData.user.value +
            "&password=" + formData.password.value;
    sendPost('Login', params, handleResponse);
}

function handleResponse(res) {
    res = JSON.parse(res);
    if (res.status === "wrong user") {
        $('.user').addClass('has-danger');
        $('.password').removeClass('has-danger');
        $('.user > .form-control-feedback')[0].innerHTML = 'Unknown username!';
        $('.password > .form-control-feedback')[0].innerHTML = '';
    }
    if (res.status === "wrong password") {
        $('.user').addClass('has-success').removeClass('has-danger');
        $('.password').addClass('has-danger');
        $('.user > .form-control-feedback')[0].innerHTML = '';
        $('.password > .form-control-feedback')[0].innerHTML = 'Wrong password!';
    }
    if (res.status === "success") {
        $('.user').addClass('has-success').removeClass('has-danger');
        $('.password').addClass('has-success').removeClass('has-danger');
        $('.user > .form-control-feedback')[0].innerHTML = '';
        $('.password > .form-control-feedback')[0].innerHTML = '';

        document.cookie = "token=" + res.token;
        document.cookie = "user=" + res.user;
        document.location = "queue_selector.html";
    }
}
;