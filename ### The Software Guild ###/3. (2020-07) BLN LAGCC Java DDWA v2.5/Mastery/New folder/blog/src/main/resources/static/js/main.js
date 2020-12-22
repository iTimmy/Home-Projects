$(document).ready(function() {
    $("#nav-button").click(function() {
        console.log("nav-button");

        $("#nav-options").css({ 'display': 'block' , 'transition-property': 'display' , 'transition-duration': '300ms' });
        // $(".top").css({ "background-color": "white" , "transition-property": "width", "transition-duration": "2000ms" });
    })
})



var lightTheme = false;

$("#toggleTheme").on('click', function (event) {
    if (lightTheme == false) {
        lightMode();
    } else if (lightTheme == true) {
        darkMode();
    }
})

function darkMode(event) {
    $(".bg-color").css({ 'background-color': 'rgba(10,10,10)' });
    $(".black-white").css({ 'background-color': 'black' , 'color': 'white' });
    $("body").css({ "background-color": "rgb(5, 5, 5)" , 'color': 'white' , "background-image": "url('assets/1.jpg')" });
    $("hr").css({ "border-top": "2.5px solid rgb(255, 0, 34)" });
    $(".tag").css({ 'background-color': '#ff006a' });
    $(".tags, .blogs").css({ 'box-shadow': '20px 50px 30px rgba(255, 0, 85, 0.089)' , 'border': '1px solid rgba(247, 247, 247, 0.116)' });
    $(".cover-overlay").css({ "background-color": "rgba(0, 0, 0, 0.5)" });
    lightTheme = false;
}

function lightMode(event) {
    $(".bg-color").css({ 'background-color': 'rgba(240,240,240)' });
    $(".black-white").css({ 'background-color': 'white' , 'color': 'black' });
    $("body").css({ "background-color": "rgb(255, 255, 255)", 'color': 'black' , "background-image": "url('assets/60113.jpg')" });
    $("hr").css({ "border-top": "2.5px solid rgb(230, 230, 230)" });
    $(".tag").css({ 'background-color': '#ddd' });
    $(".tags, .blogs").css({ 'box-shadow': '0px 15px 30px rgba(0, 0, 0, 0.507)' , 'border': '' });
    $(".cover-overlay").css({ "background-color": "rgba(255, 255, 255, 0.5)" });
    lightTheme = true;
}





