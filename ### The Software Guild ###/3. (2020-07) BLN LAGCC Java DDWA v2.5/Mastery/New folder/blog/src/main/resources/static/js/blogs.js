$(document).ready(function() {

    if ($("#title").val() == "") {
        var displayTitle = $(".display-title").text();
        $("#title").val(displayTitle);
        var title = $("#title").val();
        $(".display-title").text(title);
    }

    // EDIT BLOG
    if ($("#blogID").val() == "") {
        var displayBlogID = $(".display-blogID").text();
        console.log("ID: " + displayBlogID);
        $("#blogID").val(displayBlogID);
    }

    window.addEventListener('scroll', function() {
        var x = window.scrollX;
        var y = window.scrollY;
        // console.log("x: " + x + " | " + "y: " + y);
    });

    window.addEventListener('input', function() {
        if ($("#title").val() != "") {
            var title = $("#title").val();
            $(".display-title").text(title);
        }

        if ($("#mytextarea").val() != "") {
            var content = $("#mytextarea").val();
            $(".display-content").text(content);
        }
    })

    if (document.querySelector(".blog-list").contains(document.querySelector(".blogs")) == false) {
        $("#status").text("Your blog is empty...");
        $("#writeBlog").text("Start Blog");
    } else {
        $("#status").text("Continue writing?");
        $("#writeBlog").text("Write More");
    }

})





$(".tags .name").click(function () {
    let selectedTag = $(this).text();
    console.log($(this).text());
    let i = 0;

    $(".blogs").each(function() {
        // console.log(i + ": ", $(this).find(".blogtag").text());
        // console.log("ID: ", $(this).attr("id"));
        var blogs = $(".blogs");
        // var tagList = selectedTag.split("#");
        // var string = '500x500-11*90~1+1';
        // string = string.split(/(?=[$-/:-?{-~!"^_`\[\]])/gi);
        // console.log(string);
        // console.log(tagList);
        if ($(this).find(".blogtag").text() == selectedTag) {
            console.log("match");
            $(this).insertBefore(blogs.first());
        }
        i++;
    })

})