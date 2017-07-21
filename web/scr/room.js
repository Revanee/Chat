/* global getCookies */

loadMessages("messages");
startPollingMessages();

//initialize page
$(document).ready(function () {
    //disable form submit for ajax
    $('#form').submit(function () {
        return false;
    });
    //set welcome text
    $('#userName')[0].innerHTML = getCookies().user;
});

function sendMessage() {
    console.log('sending');
    $.ajax({
        type: 'POST',
        url: 'Desk?type=send message',
        data: $('#form').serialize()
    });
    $(':input', '#form', ':text').val('');
}

let messages;
function loadMessages() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", "Desk", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            $('#messages')[0].innerHTML = "";
            messages = [];
            messages = JSON.parse(this.responseText);
            messages.forEach(function (message) {
                //message.time = new Date(message.time);
                addMessage(message);
            });
            scrollToBootom();
        }
    };
    xhttp.send("type=get messages");
    return false;
}

function addMessage(message) {
    if (getCookies().user === message.user) {
        $('#messages').append(
                $('#msgTemplate').clone()
                .find('.user').text(message.user).end()
                .find('.messageText').text(message.text).end()
                .find('.time').text(dateFormat(message.time, 'h:MM TT')).end()
                .find('.card').addClass('float-right')
                );
    } else {
        $('#messages').append(
                $('#msgTemplate').clone()
                .find('.user').text(message.user).end()
                .find('.messageText').text(message.text).end()
                .find('.time').text(dateFormat(message.time, 'h:MM TT')).end()
                );
    }
}

function startPollingMessages() {
    setTimeout(function () {
        sendPost("Desk", "type=message ammount", function (res) {
            if (Number(messages.length) !== Number(res)) {
                console.log("Need update:" + messages.length + " " + res);
                loadMessages();
            }
        });
        startPollingMessages();
    }, 1000);
}
