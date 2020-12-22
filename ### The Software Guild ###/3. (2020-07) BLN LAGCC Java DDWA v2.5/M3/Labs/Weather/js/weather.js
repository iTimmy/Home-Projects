$(document).ready(function () {
    $("#get-weather").click(function (event) {
        console.log("fdsf");
        // var units = $("#units").val();
        $.ajax({
            type: 'GET',
            url: 'http://api.openweathermap.org/data/2.5/weather?zip=' + $("#zip-code").val() + ',us&APPID=d0faac984038543beccb8b3b68a3f250',
            success: function (data) {
                showTables(data);
            },
            error: function () {
                console.log("error");
            }
        })
    })
})

function showTables(data) {
    // $("#current-conditions").style({ 'display': 'block' });
    $("#current-conditions h1").append("12343");
}