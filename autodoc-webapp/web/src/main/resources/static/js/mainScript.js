function toggle_displays(t, d) {
    var x = document.getElementById(t);
    var y = document.getElementById(d);

    if (x.style.display === "none" && y.style.display === "block") {
        y.style.display = "none";
        x.style.display = "block";

    } else if (x.style.display === "block") {
        x.style.display = "none";

    } else {
        x.style.display = "block";

    }
}

/*function toggling() {
    var checkBox = document.getElementById("myCheck");
    var text = document.getElementById("text");
    if (checkBox.checked == true){
        text.style.display = "block";
    } else {
        text.style.display = "none";
    }
}*/
function rowClicked(url) {
    location.replace(url);
}

function showLoans() {
    var x = document.getElementById('myLoans');
    x.style.display = "block";
}

function startTime() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('clock').innerHTML =
        mm + '/' + dd + '/' + yyyy + " " + h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i
    }  // add zero in front of numbers < 10
    return i;
}


function alertOverdue() {
    Swal.fire({
        title: 'You can\'t book while having overdue items!',
        type: 'error',
        confirmButtonText: 'Ok, I ll take care of it!'
    });
    showLoans();
}

function alertMaxRented() {
    Swal.fire({
        title: 'You have reach the max reserving limit!',
        type: 'error',
        confirmButtonText: 'Oh, Ok!'
    });
    showLoans();
}


function alertAlreadyRented() {
    Swal.fire({
        title: 'You are already renting that book!',
        type: 'error',
        confirmButtonText: 'Ups, my bad!'
    });
    showLoans();
}

function overdueInitPopup(overdue) {
    $(document).ready(function () {
        if (overdue) {
            if (sessionStorage.getItem('popState') != 'shown') {
                Swal.fire({
                    title: 'You have some overdue items!',
                    type: 'error',
                    confirmButtonText: 'Ok, I ll take care of it!'
                }, document.getElementById("myLoans").style.display = "block");
                sessionStorage.setItem('popState', 'shown')
            }
        }
    });
}


//////////////// table edit


var $TABLE = $('#table');
var $BTN = $('#export-btn');
var $EXPORT = $('#export');

$('.table-add').click(function () {
    var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
    $TABLE.find('table').append($clone);
});

$('.table-remove').click(function () {
    $(this).parents('tr').detach();
});

$('.table-up').click(function () {
    var $row = $(this).parents('tr');
    if ($row.index() === 1) return; // Don't go above the header
    $row.prev().before($row.get(0));
});

$('.table-down').click(function () {
    var $row = $(this).parents('tr');
    $row.next().after($row.get(0));
});

// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;

$BTN.click(function () {
    var $rows = $TABLE.find('tr:not(:hidden)');
    var headers = [];
    var data = [];

    // Get the headers (add special header logic here)
    $($rows.shift()).find('th:not(:empty)').each(function () {
        headers.push($(this).text().toLowerCase());
    });

    // Turn all existing rows into a loopable array
    $rows.each(function () {
        var $td = $(this).find('td');
        var h = {};

        // Use the headers from earlier to name our hash keys
        headers.forEach(function (header, i) {
            h[header] = $td.eq(i).text();
        });

        data.push(h);
    });

    // Output the result
    $EXPORT.text(JSON.stringify(data));
});


