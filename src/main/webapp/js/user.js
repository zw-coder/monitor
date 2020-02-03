var flag = true;
var flag1 = false;
$.extend($.fn.validatebox.defaults.rules, {
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '不少于5位字符'
    },
});

$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '两次密码不一致'
    }
});
function checkName() {
    var username = $('#username').val();
    $.ajax({
        type: 'post',
        url: '/user/checkName',
        data: {
            'username': username,
        },
        dataType: 'json',
        success: function (data) {
            if (data == "1") {
                //用户存在
                alert("该账号已存在");
                flag = false;
            } else {
                //不存在
                flag = true;
            }
        }
    });
}

function checkUser() {
        var username = $('#username').val();
        var password = $('#password').val();
        $.ajax({
            type: 'post',
            url: '/user/checkUser',
            data: {
                'username': username,
                'password': password
            },
            dataType: 'json',
            success: function (data) {
                if (data == "1") {
                    //账号错误
                    alert("账号错误");
                    flag1 = false;
                } else if (data == "2") {
                    //密码错误
                    flag1 = false;
                    alert("密码错误");
                } else if (data == "3") {
                    flag1 = true;
                    // window.location.href="pages/main.jsp";
                }
            }
        });
    }
function submit2() {
    checkName();
    console.log($('#ff').form('validate'));
    if ($('#ff').form('validate')==true && flag == true) {
        //校验通过
       $('#ff').submit();
    }
}

function  submit1() {
    checkUser();
    if (flag1 == true) {
        //校验通过
        $('#ff').submit();
    }
}



