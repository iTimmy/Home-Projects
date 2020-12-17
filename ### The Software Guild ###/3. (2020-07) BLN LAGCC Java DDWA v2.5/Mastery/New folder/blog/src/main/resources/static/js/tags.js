$(document).ready(function() {
    var count = 0;

    $(".tag-list").children(".tags").each(function () {
        console.log($(this));
        count++;
    })

    if (count == 18) {
        $("middle").append("<div class='column col-2 tag-list'>");
    }
});

[].forEach.call(document.getElementsByClassName('name'), function (el) {
    let hiddenInput = document.createElement('input'),
        mainInput = document.createElement('input'),
        tags = [];

    hiddenInput.setAttribute('type', 'hidden');
    hiddenInput.setAttribute('name', el.getAttribute('data-name'));

    mainInput.setAttribute('type', 'text');
    mainInput.classList.add('main-input');
    mainInput.addEventListener('input', function () {
        let enteredTags = mainInput.value.split(',');
        if (enteredTags.length > 1) {
            enteredTags.forEach(function (t) {
                let filteredTag = filterTag(t);
                if (filteredTag.length > 0)
                    addTag(filteredTag);
            });
            mainInput.value = '';
        }
    });

    mainInput.addEventListener('keydown', function (e) {
        let keyCode = e.which || e.keyCode;
        if (keyCode === 8 && mainInput.value.length === 0 && tags.length > 0) {
            removeTag(tags.length - 1);
        }
    });

    el.appendChild(mainInput);
    el.appendChild(hiddenInput);

    document.getElementById("tag").addEventListener("change", function() {
        if ($("#tag option:selected").length != 0) {
            $(".main-input").val($("#tag option:selected").text());
        }
    });



    // ADD TAG
    $("#add-tag").click(function() {
        if ($(".main-input").val() == "") {
            $(".main-input").css({ 'box-shadow': '0px 1px 20px red' }).attr("placeholder", "Cannot leave empty!");
        } else {
            let tags = ($("input[name='name']").val()).split(",");
            var tagExists = false;
            for (var i in tags) {
                console.log(i);
                if ($(".main-input").val() == tags[i]) {
                    $(".tag").css({ 'background-color': '#92003d' });
                    $(".main-input").css({ 'box-shadow': '0px 1px 20px orange' }).attr("placeholder", "Already exists!")
                    tagExists = true;
                }
            }
            if (tagExists == false) {
                addTag($(".main-input").val());
            }
            $(".main-input").val("").attr("placeholder", "").css({ 'box-shadow': '0px 1px 20px green' });
        }
    })

    // ADD TAG
    $(".tag").click(function () {
        console.log("yeag");
        addTag($(this).text());
    })



    function addTag(text) {
        let tag = {
            text: text,
            element: document.createElement('span'),
        };

        tag.element.classList.add('tag');
        console.log("Creating new name");
        let input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("value", tag.text);
        input.setAttribute("id", "name");
        tag.element.appendChild(input);
        tag.element.setAttribute("id", "name");
        tag.element.textContent = tag.text;

        let closeBtn = document.createElement('span');
        closeBtn.classList.add('close');
        closeBtn.addEventListener('click', function () {
            removeTag(tags.indexOf(tag));
        });
        tag.element.appendChild(closeBtn);

        tags.push(tag);

        el.insertBefore(tag.element, mainInput);

        refreshTags();
    }

    function removeTag(index) {
        let tag = tags[index];
        tags.splice(index, 1);
        el.removeChild(tag.element);
        refreshTags();
    }

    function refreshTags() {
        let tagsList = [];
        tags.forEach(function (t) {
            tagsList.push(t.text);
        });
        hiddenInput.value = tagsList.join(',');
    }

    function filterTag(tag) {
        return tag.replace(/[^\w -]/g, '').trim().replace(/\W+/g, '-');
    }

    // IMPORT EXISTING TAGS FROM BLOG
    $("#blogstag option").each(function() {
        addTag($(this).text());
    })

});










