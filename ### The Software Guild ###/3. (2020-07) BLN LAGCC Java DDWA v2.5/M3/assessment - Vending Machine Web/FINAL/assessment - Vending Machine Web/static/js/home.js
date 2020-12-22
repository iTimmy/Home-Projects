var dollar = 0;
var quarter = 0;
var dime = 0;
var nickel = 0;
var money = 0;

var selectedData;

$(document).ready(function () {
    displayItems();

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

    function idk(amount, data, id) {
        console.log("__________________________________________________________________________");

        console.log("The vending machine eats your: $" + amount);
        console.log(amount, data);
        console.log("It returns: $" + money);

        $.ajax({
            type: 'POST',
            url: "http://tsg-vending.herokuapp.com/money/" + amount + "/item/" + id,
            success: function (dataArray) {
                console.log("Transaction completed!");
                convertToChange(data.id, amount);

                // console.log("dataArray: " + dataArray.quarters);
                var statement = dataArray.quarters + " quarter(s), " + dataArray.dimes + " dime(s), " + dataArray.nickels + " nickel(s), " + dataArray.pennies + " pennies."; 
                displayOrder(statement);

                $("#enter-messages").attr("placeholder", "Thank You!!!");
                $("#enter-item, #total").css({ 'box-shadow': '1px 1px 15px green' });  

                $("#change-return").css({ 'display': 'block' });
                displayItems();

                money = money - data.price;
                if (data.quantity != 0 && money >= 0) {
                    $("#total").attr("placeholder", "$" + money.toFixed(2));
                    // console.log("Q: " + typeof 0);
                } else if (data.quantity == 0) {
                    // console.log("Qhgfhf: " + data.quantity);
                }
            },
            error: function (dataArray) {
                console.log("error: ", dataArray.responseJSON.message);
                $("#enter-messages").attr("placeholder", dataArray.responseJSON.message);
                $("#enter-item, #total").css({ 'box-shadow': '1px 1px 15px red' });
            }
        })
    }

    $("#make-purchase").click(function () {
        var item = $("#enter-item").val();

        console.log("__________________________________________________________________________");
        console.log("Making purchase with item: " + "--- " + item + ") " + getData().name + " : " + getData().id + " ---");
        console.log("--------------------------------------------------------------------------");
        console.log("You insert: $" + money);
        $.ajax({
            type: 'GET',
            url: 'http://tsg-vending.herokuapp.com/items',
            success: function (dataArray) {
                $.each(dataArray, function (i, data) {
                    console.log("Scanning... " + data.name);
                    // console.log(data.id);
                    if (getData().id == data.id && money >= 0) {
                        idk(money.toFixed(2), data, data.id);
                        return false;
                    } else if (item == "") {
                        idk(money.toFixed(2), null, 0);
                        return false;
                    }
                })
            }
        })
    })
})

function resetMoney() {
    dollar = 0;
    quarter = 0;
    dime = 0;
    nickel = 0;
}

function setData(selectedData) {
    this.selectedData = selectedData;
}

function getData() {
    return selectedData;
}

function displayItems() {
    $(".itemRows").empty();
    var row = "";
    var num = 0;
    var col = "";
    var c = 0;
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
                col = "<div class='column column-" + c + " col-lg-4 col-md-12 col-sm-12 col-xs-12'>" +
                    "<p id='item-id'>" + (i + 1) + "</p>" +
                    "<a class='select-item'><h3>" + data.name + "</h3></a>" +
                    "<p id='item-cost'>$" + data.price.toFixed(2) + "</p>" +
                    "<br><br>" +
                    "<p id='item-quantity'>Quantity Left: " + data.quantity + "</p>" +
                    "<br>" + 
                    "</div>";
                $(".r-" + num).append(col);
                $(".column-" + c + " .select-item").click(function () {
                    $("#enter-item").val(i + 1);
                    console.log("__________________________________________________________________________");
                    console.log((i + 1) + ") " + data.name + " : " + data.id);
                    setData(data);
                    $(".column-" + c + " h3").css({ 'border': '5px solid red' });
                });
                c++;
            })
        },
        statusCode: {
            422: function () {
                alert("hey");
            }
        }
    })
}

function displayOrder(statement) {
    $("#display-change").attr("placeholder", statement); 

    $("#change-return").click(function () {
        $("#total").attr("placeholder", "$0.00");
        $("#enter-item").val("");
        $("#enter-messages").attr("placeholder", "");  
        $("#enter-item, #total").css({ 'box-shadow': 'none' }); 
        $("#display-change").attr("placeholder", "");  
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
    money += dollar + quarter + dime + nickel;

    $("#total")
        .css({ 'box-shadow': '1px 1px 15px green' })
        .val("")
        .attr("placeholder", "$" + money.toFixed(2));
    $(".error-money-blank").css({ 'display': 'none' });
    resetMoney();
    return money;
}