$(document).ready(function () {
    displayItems();
    selectItem();

    var dollar = 0;
    var quarter = 0;
    var dime = 0;
    var nickel = 0;

    var money = 0;

    $("#add-dollar").click(function () {
        validationCheck();
        console.log("run");
        if ($("#total").val() == "" || ($("#total").val() > 0 && $("#total").val() < 9999) == false) {
            $("#total").css({ 'box-shadow': '1px 1px 15px red' });
            console.log("run1");
        } else {
            console.log("run2");
            dollar += parseInt($("#total").val());
            console.log(dollar);
            $("#total").css({ 'box-shadow': '1px 1px 15px green' });
            money = (dollar * 1.00) + (quarter * 0.25) + (dime * 0.10) + (nickel * 0.05);
            $("#total").attr("placeholder", "$" + money.toFixed(2));
            $("#total").val("");
        }
    })

    $("#add-quarter").click(function () {
        validationCheck();
        if ($("#total").val() == "" || ($("#total").val() > 0 && $("#total").val() < 9999) == false) {
            $("#total").css({ 'box-shadow': '1px 1px 15px red' });
        } else {
            quarter += parseInt($("#total").val());
            console.log(quarter);
            $("#total").css({ 'box-shadow': '1px 1px 15px green' });
            money = (dollar * 1.00) + (quarter * 0.25) + (dime * 0.10) + (nickel * 0.05);
            $("#total").attr("placeholder", "$" + money.toFixed(2));
            $("#total").val("");
        }
    })

    $("#add-dime").click(function () {
        validationCheck();
        if ($("#total").val() == "" || ($("#total").val() > 0 && $("#total").val() < 9999) == false) {
            $("#total").css({ 'box-shadow': '1px 1px 15px red' });
        } else {
            dime += parseInt($("#total").val());
            console.log(dime);
            $("#total").css({ 'box-shadow': '1px 1px 15px green' });
            money = (dollar * 1.00) + (quarter * 0.25) + (dime * 0.10) + (nickel * 0.05);
            $("#total").attr("placeholder", "$" + money.toFixed(2));
            $("#total").val("");
        }
    })

    $("#add-nickel").click(function () {
        validationCheck();
        if ($("#total").val() == "" || ($("#total").val() > 0 && $("#total").val() < 9999) == false) {
            $("#total").css({ 'box-shadow': '1px 1px 15px red' });
        } else {
            nickel += parseInt($("#total").val());
            console.log(nickel);
            $("#total").css({ 'box-shadow': '1px 1px 15px green' });
            money = (dollar * 1.00) + (quarter * 0.25) + (dime * 0.10) + (nickel * 0.05);
            $("#total").attr("placeholder", "$" + money.toFixed(2));
            $("#total").val("");
        }
    })

    $("#make-purchase").click(function () {
        validationCheck();

        var item = $("#enter-item").val();

        console.log(money);
        $.ajax({
            type: 'GET',
            url: 'http://tsg-vending.herokuapp.com/items',
            success: function (dataArray) {
                $.each(dataArray, function (i, data) {
                    console.log("running arary");
                    if (item == data.name) {
                        console.log("green");
                        $("#enter-item").css({ 'box-shadow': '1px 1px 15px green' });
                        $(".error-money").css({ 'display': 'none' });
                        $(".error-item").css({ 'display': 'none' });
                        console.log(data.name);
                        if (money >= data.price) {
                            money = money - data.price;
                            console.log(money);
                            convertToChange(item, money);
                            return false;
                        } else {
                            console.log("not enough money");
                            $(".error-item").css({ 'display': 'none' });
                            $("#enter-item").css({ 'box-shadow': '1px 1px 15px red' });
                            $(".error-money-blank").css({ 'display': 'none' });
                            $(".error-money").css({ 'display': 'block' });
                            return false;
                        }
                        console.log("going to return");
                        // return false;
                    } else if (item != data.name && $("#enter-item").val() != "") {
                        console.log("red");
                        validationCheck();
                        $(".error-item").css({ 'display': 'block' });
                        // return false;
                    }
                })
            },
            error: function (dataArray) {
                console.log("fdsfs");
            }
        })
    })
})

function validationCheck() {
    console.log("running validation");

    // MONEY VALIDATION
    if (($("#total").val() > 0 && $("#total").val() < 9999) == false && $("#total").val() != "") {
        console.log("yup");
        $(".error-type").css({ 'display': 'block' });
    } else if ($("#total").val() == "" && $("#total").attr("placeholder") == "$") {
        console.log("rafds");
        $("#total").css({ 'box-shadow': '1px 1px 15px red' });
        console.log("rafds");
        $(".error-money-blank").css({ 'display': 'block' });
        $(".error-type").css({ 'display': 'none' });
    } else if ($("#total").val() != "") {
        console.log("works");
        $("#total").css({ 'box-shadow': 'green' });
        $(".error-money-blank").css({ 'display': 'none' });
        $(".error-type").css({ 'display': 'none' });
    }

    // ITEM VALIDATION
    if ($("#enter-item").val() == "") {
        $(".error-item-blank").css({ 'display': 'block' });
    } else if ($("#enter-item").val() != "") {
        $(".error-item-blank").css({ 'display': 'none' });
    }
}

function displayItems() {
    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (dataArray) {
            $.each(dataArray, function (i, data) {
                var row = "<tr id='row-" + (i + 1) + "'>";
                    row += "<td id='item-name'><a href='#' class='select-item'>" + data.name + "</a></td>";
                    row += "<td id='item-cost'>" + "$" + data.price + "</td>";
                    row += "<td id='item-quantity'>" + data.quantity + "</td></tr>";
                $(".itemRows").append(row);
                $("#row-" + (i + 1) + " .select-item").click(() => $("#enter-item").val(data.name));
            }) 
        },
        error: function () {
            console.log("hetgfdgd");
        }
    })
}

function displayOrder(userChange) {
    $(".body").css({ 'display': 'block' })
    var description = "You ordered <strong style='color:green'>" + userChange.item + "</strong> at the price of <strong style='color:yellow'>$" + userChange.money.toFixed(2) + "</strong>.<br><br>";
        description += "<h2 style='color:red;background-color:rgba(0,0,0,0.75)'>Returning...</h2><br>" + 
            "<strong style='color:red;font-size:30px'>" + userChange.GC + "</strong><i style='color:orange'> dollars</i>,<br>" +
            "<strong style='color:red;font-size:30px'>" + userChange.Q + "</strong><i style='color:orange'> quarters</i>,<br>" +
            "<strong style='color:red;font-size:30px'>" + userChange.D +"</strong><i style='color:orange'> dimes</i>,<br>" +
            "<strong style='color:red;font-size:30px'>" + userChange.N +"</strong><i style='color:orange'> nickels</i>,<br>" +
            "<strong style='color:red;font-size:30px'>" + userChange.P + "</strong><i style='color:orange'> pennies</i>,<br></p>";
    description += "<br><hr><p style='color:grey'>You received a total of <strong style='color:yellow'>$" + userChange.money.toFixed(2) + "</strong> back.</p>";
    
    $("#change").html(description);
    $("#total").attr("placeholder", "$" + userChange.money.toFixed(2));
}

function convertToChange(item, money) {
    var dollar = 1;
    var quarter = 0.25;
    var dime = 0.10;
    var nickel = 0.05;
    var penny = 0.01;

    var dollars = 0;
    var quarters = 0;
    var dimes = 0;
    var nickels = 0;
    var pennies = 0;

    var resultMoney = money;

    while (resultMoney > 0 ) {
        if (resultMoney >= dollar) {
            resultMoney -= dollar;
            dollars++;
        } else if (resultMoney >= quarter) {
            resultMoney -= quarter;
            quarters++;
        } else if (resultMoney >= dime) {
            resultMoney -= dime;
            dimes++;
        } else if (resultMoney >= nickel) {
            resultMoney -= nickel;
            nickels++;
        } else if (resultMoney >= penny) {
            resultMoney -= penny;
            pennies++;
        } else {
            break;
        }
        console.log(money);
    }

    console.log(dollars);
    console.log(quarters);
    console.log(dimes);
    console.log(nickels);
    console.log(pennies);

    var userChange = {
        money: money,
        item: item,
        GC: dollars,
        Q: quarters,
        D: dimes,
        N: nickels,
        P: pennies
    }

    displayOrder(userChange);
}

function selectItem() {
    console.log("im running nigga");

    $.each($(".itemRows"), function (i, row) {
        console.log("im clicking nigga");
        console.log(row);
    })
}