
$
var userIdentity = getCookie("ssocookie")
var username = getCookie("name")
var ul = document.getElementById("beginUL")
var li = document.createElement("li")
var li1 = document.createElement("li")
if(userIdentity=="user"){
    li.innerHTML = "尊敬的"+username+"，欢迎您"
    var a = document.createElement("a")
    a.setAttribute("href","index")
    a.setAttribute("onclick","logout()")
    a.innerHTML = "登出"
    a.setAttribute("style","display:inline;font-size:5pt")
    li1.appendChild(a)
    ul.appendChild(li)
    ul.appendChild(li1)
}
else{
    var LoginBtn = document.createElement("button")
    LoginBtn.setAttribute("class","btn btn-dark btn-icon")
    LoginBtn.setAttribute("id","btn_login")
    LoginBtn.setAttribute("data-toggle","modal")
    LoginBtn.setAttribute("data-target","#loginModal")
    LoginBtn.setAttribute("style","font-size: 10px;")
    LoginBtn.innerHTML = "<i class='icon-leaf' style='font-size: 10px'></i><span>登陆</span>"
    var RegisterBtn = document.createElement("button")
    RegisterBtn.innerHTML ="<i class='icon-leaf' style='font-size: 10px'></i><span>注册</span>"
    RegisterBtn.setAttribute("class","btn btn-dark btn-icon")
    RegisterBtn.setAttribute("id","btn_register")
    RegisterBtn.setAttribute("data-toggle","modal")
    RegisterBtn.setAttribute("data-target","#registerModal")
    RegisterBtn.setAttribute("style","font-size: 10px;")
    li.appendChild(LoginBtn)
    li1.appendChild(RegisterBtn)
    ul.appendChild(li)
    ul.appendChild(li1)
}
