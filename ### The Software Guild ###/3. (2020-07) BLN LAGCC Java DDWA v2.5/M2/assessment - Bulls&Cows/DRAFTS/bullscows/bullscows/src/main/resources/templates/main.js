var lightTheme = false;


$("#toggleTheme").on('click', function (event) {
    if (lightTheme == false) {
        lightMode();
    } else if (lightTheme == true) {
        darkMode();
    }
})


function darkMode(event) {
    $("body, .container-fluid, .bottom").css({ 'background-color': 'black' });
    $(".overlay").css({ 'background-color': 'rgba(0, 0, 0, 0.5)' });
    $(".choose ul").css({ 'background-color': 'rgba(0, 0, 0, 0.75)', 'color': 'white' });
    $(".alert").addClass("bg-danger");

    $(".hero-form h3").addClass("white-text").removeClass("black-text");
    document.querySelector(".themeStatus").innerHTML = 'Dark';
    var bgimgs = document.querySelectorAll(".bg-img");
    bgimgs.forEach((e) => {
        e.style.backgroundImage = "url('assets/cover-4.jpg')";
    })
    var bgcolors = document.querySelectorAll(".bg-color");
    bgcolors.forEach((e) => {
        e.style.backgroundColor = "rgba(0, 0, 0, 0.75)";
    })

    document.querySelector(".bottom p").style.color = 'white';
    var paragraphs = document.querySelectorAll("p, label, .bottom a, .h3");
    paragraphs.forEach((e) => {
        e.style.color = 'white';
    })

    var texts = document.querySelectorAll(".white-text");
    texts.forEach((e) => {
        e.style.color = "white";
    })

    document.getElementById("send").className = 'btn btn-outline-warning btn-lg';

    document.getElementById("toggleTheme").className = 'btn btn-outline-info btn-lg';

    $(".btn, .news, .other").addClass("btn-outline-primary").removeClass("btn-primary");
    $(".registerLogin .register").addClass("btn-outline-success").removeClass("btn-success");
    $(".registerLogin .login").addClass("btn-outline-danger").removeClass("btn-danger");
    $(".subdomain .btn").css({ 'background-color': 'rgba(0,0,0,0.75)' });

    $(".swiper-container img").css({ 'background-color': 'rgba(0, 0, 0, 0.5)' });
    $(".swiper-pagination-bullets").css({ 'background-color': 'rgba(0, 0, 0, 0.15)' });
    $(".swiper-container").css({ 'background-color': 'rgba(0, 0, 0, 0.5)' });
    $(".table").addClass("table-dark").removeClass("table-light");
    $(".results .border").addClass("border-dark").removeClass("border-light");
    $(".revealMap h3").css({ 'background-color': 'rgb(0, 0, 0)', 'color': 'rgb(255, 255, 255)' });
    lightTheme = false;
}

function lightMode(event) {
    $("body, .container-fluid, .bottom, .choose ul").css({ 'background-color': 'white' });
    $(".overlay").css({ 'background-color': 'rgba(255, 255, 255, 0.25)' });
    $(".choose ul").css({ 'background-color': 'rgba(255, 255, 255, 0.75)', 'color': 'black' });
    $(".alert").removeClass("bg-danger");

    $(".hero-form h3").addClass("black-text").removeClass("white-text");

    document.querySelector(".themeStatus").innerHTML = 'Light';
    var bgimgs = document.querySelectorAll(".bg-img");
    bgimgs.forEach((e) => {
        e.style.backgroundImage = "url('https://cdn.wallpapersafari.com/77/60/Qmy5KZ.jpg')";
    })
    var bgcolors = document.querySelectorAll(".bg-color");
    bgcolors.forEach((e) => {
        e.style.backgroundColor = "rgba(255, 255, 255, 0.75)";
    })
    var texts = document.querySelectorAll(".white-text");
    texts.forEach((e) => {
        e.style.color = "black";
    })

    var paragraphs = document.querySelectorAll("p, label, .bottom a, .h3");
    paragraphs.forEach((e) => {
        e.style.color = 'black';
    })
    document.getElementById("send").className = 'btn btn-warning btn-lg';

    // REDUCE REDUNDANCY
    document.getElementById("toggleTheme").className = 'btn btn-info btn-lg';

    $(".btn, .news, .other").addClass("btn-primary").removeClass("btn-outline-primary");
    $(".registerLogin .register").addClass("btn-success").removeClass("btn-outline-success");
    $(".registerLogin .login").addClass("btn-danger").removeClass("btn-outline-danger");

    $(".subdomain .btn").css({ 'background-color': 'rgba(0, 0, 255, 0.5)' });
    $(".swiper-container img").css({ 'background-color': 'rgba(255, 255, 255, 0.25)' });
    $(".swiper-pagination-bullets").css({ 'background-color': 'rgba(255, 255, 255, 0.05)' });
    $(".swiper-container").css({ 'background-color': 'rgba(255, 255, 255, 0.15)' });
    $(".table").addClass("table-light").removeClass("table-dark");
    $(".results .border").addClass("border-light").removeClass("border-dark");
    $(".revealMap h3").css({ 'background-color': 'rgb(255, 255, 255)', 'color': 'rgb(0, 0, 0)' });

    lightTheme = true;
}