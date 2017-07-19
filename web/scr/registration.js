function register() {
    let formData = document.getElementById('form').elements;
    params = "user=" + formData.user.value +
             "&password=" + formData.password.value;
    sendPost('Register', params, handleResponse);
}

function handleResponse(res) {
    res = JSON.parse(res);
    if (res.status === "existing user") {
        $('.user').addClass('has-danger');
        $('.password').removeClass('has-danger');
        $('.user > .form-control-feedback')[0].innerHTML = 'User already exists!';
        $('.password > .form-control-feedback')[0].innerHTML = '';
    }
    if (res.status === "short password") {
        $('.user').addClass('has-success').removeClass('has-danger');
        $('.password').addClass('has-danger');
        $('.user > .form-control-feedback')[0].innerHTML = '';
        $('.password > .form-control-feedback')[0].innerHTML = 'Password too short!';
    }
    if (res.status === "success") {
        $('.user').addClass('has-success').removeClass('has-danger');
        $('.password').addClass('has-success').removeClass('has-danger');
        $('.user > .form-control-feedback')[0].innerHTML = '';
        $('.password > .form-control-feedback')[0].innerHTML = '';
        
        document.location = "login.html";
    }
};