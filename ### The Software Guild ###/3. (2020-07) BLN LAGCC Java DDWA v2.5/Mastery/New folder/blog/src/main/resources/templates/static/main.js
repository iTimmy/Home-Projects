$(document).ready(function() {
    $("#nav-button").click(function() {
        console.log("nav-button");

        $("#nav-options").css({ 'display': 'block' , 'transition-property': 'display' , 'transition-duration': '300ms' });
    })
})



var lightTheme = false;

$("#toggleTheme").on('click', function (event) {
    console.log("Gfdg");
    if (lightTheme == false) {
        console.log(lightTheme);
        console.log("light");
        lightMode();
    } else if (lightTheme == true) {
        console.log(lightTheme);
        console.log("dark");
        darkMode();
    }
})

function darkMode(event) {
    $(".bg-color").css({ 'background-color': 'rgba(0,0,0,0.5)' });
    $(".black-white").css({ 'background-color': 'black' });
    $("body").css({ "background-image": "url('103237.jpg')" });
    lightTheme = false;
}

function lightMode(event) {
    $(".bg-color").css({ 'background-color': 'rgba(255,255,255,0.5)' });
    $(".black-white").css({ 'background-color': 'white' });
    $("body").css({ "background-image": "url('27022.jpg')" });
    lightTheme = true;
}