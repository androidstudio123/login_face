$(function () {
    $('#register').click(function (event) {
        $('#errorInfo').html("");
        var username_ = $('#username').val();
        var password_ = $('#password').val();
        if (username_.length == 0 || password_.length == 0) {
            $('#errorInfo').html("账号或密码不能为空！");
            return false;
        }

        var user_ = {"username": username_, "password": password_};
        var jsonData = JSON.stringify(user_);
        $.ajax({
            type: "POST",
            url: "/register",
            async: false,
            //dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data: jsonData,
            success: function (list) {
                    location.href= "/main";
            }
        });
    });
})