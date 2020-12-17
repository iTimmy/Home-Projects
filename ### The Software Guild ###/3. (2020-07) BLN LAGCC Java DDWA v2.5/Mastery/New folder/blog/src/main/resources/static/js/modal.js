// Get the modal
var underlay = document.querySelector(".underlay");
var modal = document.querySelector(".myModal");
var login = document.querySelector(".loginModal");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
function openModal(blogID) {

}

$(".deleteBlog").click(function () {
    $("#blogID").val($(this).attr("name").trim());
    underlay.style.display = "block";
    modal.style.display = "block";
})

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    underlay.style.display = "none";
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        underlay.style.display = "none";
        modal.style.display = "none";
    }
}










$("#login").click(function() {
    console.log("click");
})