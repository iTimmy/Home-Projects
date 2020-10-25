var dollar = 0;
var quarter = 0;
var dime = 0;
var nickel = 0;
var money = 0;

$(document).ready(function () {
    displayItems();
    selectItem();

    $("#add-dollar").click(function () {
        dollar += 1.00;
        console.log(dollar);
        money = addMoney(money, dollar, quarter, dime, nickel);
    })

    $("#add-quarter").click(function () {
        quarter += 0.25;
        console.log(quarter);
        money = addMoney(money, dollar, quarter, dime, nickel);
    })

    $("#add-dime").click(function () {
        dime += 0.10;
        console.log(dime);
        money = addMoney(money, dollar, quarter, dime, nickel);
    })

    $("#add-nickel").click(function () {
        nickel += 0.05;
        console.log(nickel);
        money = addMoney(money, dollar, quarter, dime, nickel);
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
                    if (item == data.id && data.quantity > 0) {
                        validationCheck(2);
                        if (money >= data.price) {
                            money = money - data.price;
                            convertToChange(item, money);
                            $("#enter-messages").attr("placeholder", "Thank You!!!");
                            //data.quantity--;
                            $("#item-quantity").html("Quantity Left: " + data.quantity);
                            $("#change-return").css({ 'display': 'block'});
                            return false;
                        } else {
                            validationCheck(0);
                            return false;
                        }
                    } else if (($("#enter-item").val() == "") || ($("#total").attr("placeholder") == "$")) {
                        validationCheck(1);
                    }
                })
            },
            error: function (dataArray) {
            }
        })
    })
})

function validationCheck(errorCode) {
    // NOT ENOUGH MONEY \\
    if (errorCode == 0) {
        $(".error-item, .error-money-blank").css({ 'display': 'none' });
        $(".error-money").css({ 'display': 'block' });
        $("#enter-item, #total").css({ 'box-shadow': '1px 1px 15px red' });
    }

    // TOTAL INPUT VALIDATION \\
    if (errorCode == 1) {
        if ($("#enter-item").val() == "") {
            $(".error-item-blank").css({ 'display': 'block' });
            $("#enter-item").css({ 'box-shadow': '1px 1px 15px red' });
        }
        if ($("#total").attr("placeholder") == "$") {
            $(".error-money-blank").css({ 'display': 'block' });
            $("#total").css({ 'box-shadow': '1px 1px 15px red' });
        }
    }

    // if (data.quantity <= 0) {
    //     $("#enter-messages").attr("placeholder", "Out Of Stock!!!");
    // }

    if (errorCode == 2) {
        $("#enter-item").css({ 'box-shadow': '1px 1px 15px green' });
        $(".error-money, .error-item, .error-item-blank").css({ 'display': 'none' });
    }
}

function displayItems() {
    var row = "";
    var num = 0;
    var col = "";
    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (dataArray) {
            $.each(dataArray, function (i, data) {
                if (i % 3 == 0) {
                    num++;
                    row = "<div class='row r-" + (num) + "'></div>";
                    $(".itemRows").append(row);
                }
                col = "<div class='column col-lg-4 col-md-12 col-sm-12 col-xs-12'>" +
                    "<p id='item-id'>" + data.id + "</p>" +
                    "<a class='select-item'><h3>" + data.name + "</h3></a>" +
                    "<p id='item-cost'>$" + data.price.toFixed(2) + "</p>" +
                    "<br><br>" +
                    "<p id='item-quantity'>Quantity Left: " + data.quantity + "</p>" +
                    "<br>" +
                    "</div>";
                $(".r-" + num).append(col);
            })
        },
        statusCode: {
            422: function () {
                alert("hey");
            }
        }
    })
}

function selectItem() {
    var value = "";
    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (dataArray) {
            $.each(dataArray, function (i, data) {
                $(".select-item").click(function () {
                    value = $("h3").val();
                    console.log(value);
                    validationCheck(2);
                })
            })
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
    $("#display-change").attr("placeholder", userChange.statement);

    $("#change-return").click(function () {
        reset();
    })
}

function reset() {
    dollar = 0;
    quarter = 0;
    dime = 0;
    nickel = 0;
    money = 0;

    $("#display-change, #enter-messages").attr("placeholder", "");
    $("#total").attr("placeholder", "$")
        .css({ 'box-shadow': 'none' });
    $("#enter-item").val("")
        .css({ 'box-shadow': 'none' });
    $("#change-return").css({ 'display': 'none' });
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

    var statement = "";

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

    if (dollars != 0) {
        statement += dollars + " dollars. ";
    }
    if (quarters != 0) {
        statement += quarters + " quarters. ";
    }
    if (nickels != 0) {
        statement += nickels + " nickels. ";
    }
    if (pennies != 0) {
        statement += pennies + " pennies. ";
    }

    var userChange = {
        money: money,
        item: item,
        GC: dollars,
        Q: quarters,
        D: dimes,
        N: nickels,
        P: pennies,
        statement: statement
    }

    displayOrder(userChange);
}

function addMoney(money, dollar, quarter, dime, nickel) {
    money = dollar + quarter + dime + nickel;
    $("#total")
        .css({ 'box-shadow': '1px 1px 15px green' })
        .val("")
        .attr("placeholder", "$" + money.toFixed(2));
    $(".error-money-blank").css({ 'display': 'none' });
    return money;
}