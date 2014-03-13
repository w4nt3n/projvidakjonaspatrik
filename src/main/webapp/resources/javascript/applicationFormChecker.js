/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//-------------------------------------------
// Form validation function
var borderOkStyle = "1px solid lime";                       // When a field is valid, this is it's style
var borderNotOkStyle = "1px solid red";                     // When a field is invalid, this is it's style
var submitButtonID = "submitButton";                        // The ID tag of the submit button

/**
 * This class checks that all given inputs are valid.
 * Input IDs can either be given in the constructor
 * as parameters or with the add-function.
 * @returns {Validator}
 */
function Validator() {
    var self = this;
    this.inputs = 0;
    this.mask = 0;

    /**
     * Use this function to add inputs for validation.
     * Optionally give a custom validator function as
     * the second parameter.
     * @param {type} inputID
     * @param {type} validatorFun
     * @returns {undefined}
     */
    this.add = function (inputID, validatorFun) {
        var element = document.getElementById(inputID);
        element.index = 1 << (self.inputs++);   // The index bit of the element
        self.mask |= element.index;             // Each set bit represents an invalid input field.
                                                // That means that all are valid when self.mask == 0.
        document.getElementById("inputText").innerHTML = self.mask.toString(2);

        // Choose validation function depending on type of input
        if(validatorFun === undefined) {
            switch (element.name) {
                case "firstname":
                case "lastname":
                    validatorFun = checkName;
                    break;
                case "email":
                    validatorFun = checkEmail;
                    break;
                case "phone":
                    validatorFun = checkTelephone;
                    break;
                case "dropdown":
                    validatorFun = checkDropdown;
                    break;
            }
        }

        element.onpropertychange = element.oninput = element.onchange = function () {
            // Check if the validity of the input has changed
            
            if (validatorFun(inputID)) {
                element.style.border = borderOkStyle;
                self.mask &= ~element.index;
            } else {
                element.style.border = borderNotOkStyle;
                self.mask |= element.index;
            }
            document.getElementById(submitButtonID).disabled = (self.mask !== 0);
            document.getElementById("inputText").innerHTML = self.mask.toString(2);
        };
    };

    // If inputs are given in constructor, add them
    for (var i = 0; i < arguments.length; i++) {
        this.add(arguments[i]);
    }
}

// This function checks that the value of the inputID ID'd input is text only
function checkName(inputID){
    // The regex defining the desired input
    var regexp = /(^[a-zA-Z]+[a-zA-Z\s]+$)/gi;
    var elem = document.getElementById(inputID);
    return regexp.exec(elem.value);
}

// This function checks that the value of the inputID ID'd input is a proper email
function checkEmail(inputID){
    var regexp = /(^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$)/gi;
    var elem = document.getElementById(inputID);
    return regexp.exec(elem.value);
}

// This function checks that the value of the inputID ID'd input is actually set
function checkDropdown(inputID){
    //// Validate input
    return document.getElementById(inputID).value.length > 0;
}

// This function checks that the value of the inputID ID'd input is a valid phone number of atleast 7 digits
function checkTelephone(inputID){
    var regexp = /(^\+?[0-9\-\s]{7,}$)/gi;
    var elem = document.getElementById(inputID);
    return regexp.exec(elem.value);
}

//-------------------------------------------
// Birthday picker logics

// Returns how many days are in a certain month on a certain year
function daysInMonth(year, month) {
    return new Date(year, month, 0).getDate();
}

// This function gets the input select tags of year, month and day of some date logic
// It makes sure that the day select field has the correct amount of days for that months selected
function checkDateOfBirth(inputID){
    // Gets the elements from the parameters
    var birthdayYear = document.getElementById("dropdownYear").value;
    var birthdayMonth = document.getElementById("dropdownMonth").value;
    var elementDay = document.getElementById("dropdownDay");
    
    if(birthdayYear === "" | birthdayMonth === "")
        return checkDropdown(inputID);

    // Gets the number of days in this month
    var numberOfDays = daysInMonth(birthdayYear, birthdayMonth);
    
    // If a user has already selected a day that doesn't exist within the new month
    // The selection must be reset and the dropdown list configured with new max date
    var diff = (numberOfDays+1) - elementDay.length;
    if(diff >= 0) {
        for(var i = elementDay.length; i < numberOfDays+1; i++){
            elementDay.add(new Option(i));
        }
    }else if(diff <= 0) {
        for(var i = 0; i < -diff; i++){
            elementDay.remove(elementDay.length-1);
        }
    }
    
    return checkDropdown(inputID);
}