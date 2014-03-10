/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//-------------------------------------------
// Expertise adder logics
// Adds an experience pair from the inputs to the buttonList
function addANewExpertise() {
    var expForm   = document.getElementById("workExpertiseExpertiseSelect");
    var expertise = expForm.options[expForm.selectedIndex].innerHTML;
    var years     = document.getElementById("workExpertiseYearsSelect").value;
    bListExp["add"](new Exp(expertise, years));
}

// Adds a period of availability from the inputs to the buttonList
function addPeriod() {
    var from = document.getElementById("datepickerFrom").value;
    var to   = document.getElementById("datepickerTo").value;
    bListPeriods["add"](new Exp(from, to));
}

// Container for a period of availability (from date, to date)
function Period(_from, _to) {
    this.from = _from;
    this.years= _to;
}

// Container for an experience pair of Expertise and Years of experience
function Exp(_expertise, _years) {
    this.expertise = _expertise;
    this.years     = _years;
}

/**
 * A list object where each row have a delete button.
 * Creator takes a formID to append the list to, and
 * custom headlines for each column.
 * @param {type} formID
 * @param {type} headlines
 * @returns {ButtonList}
 */
function ButtonList(formID, headlines) {
    // All rows are inserted into this table, which in turn
    // is appended to the form with give ID 'formID'.
    var table = document.createElement('table');
    document.getElementById(formID).appendChild(table);
    //table.border = "1px solid #999999";
    
    // Add the headlines if given
    if(headlines !== undefined) {
        var row = table.insertRow(-1);
        //var th = document.createElement('th');
        //row.appendChild(th);
        row.insertCell(-1);
        headlines.forEach(function(entry) {
            row.insertCell(-1).innerHTML=entry;
            //th = document.createElement('th');
            //th.innerHTML = entry;
            //row.appendChild(th);
        });
    }
    
    /**
     * Adds a new row containing a button for deleting the row
     * followed by each member value of 'item' in a separate column.
     * @param {type} item Object, container of column values.
     * @returns {undefined}
     */
    this.add = function (item) {
        var row = table.insertRow(-1);

        // Add a button to the new row
        var button = document.createElement('button');
        button.innerHTML = 'X';
        // Onclick deletes the row containing the pressed button
        button.onclick = function(){
            var i = button.parentNode.parentNode.rowIndex;
            table.deleteRow(i);
        };
        row.insertCell(-1).appendChild(button);
        // Add each value of 'item' to a new column
        for(var value in item) {
            row.insertCell(-1).innerHTML = item[value];
        }
    };
	
    /**
     * Returns a string of all values in table joined by separator.
     * The inner loop starts at 1 (one) to skip the button column.
     * Loops stop when no additional row or column is found.
     * @param {String} separator
     * @returns {String}
     */
    this.join = function(separator) {
        alert("joining");
        var arr = new Array();
        for (var i = 0, row; (bool)(row = table.rows[i]); i++) {
            for (var j = 1, col; (bool)(col = row.cells[j]); j++) {
                arr.push(col.innerHTML);
            }  
        }
        return arr.join(separator);
    };
    
    return this;
}