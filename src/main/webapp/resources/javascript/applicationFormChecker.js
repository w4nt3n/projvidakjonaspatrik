/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//-------------------------------------------
// Form validation function

var allIsCheckedValue = 0x1FFF;                             // When all fields have valid input, this will be set to 0.

var borderOkStyle = "1px solid lime";                       // When a field is valid, this is it's style
var borderNotOkStyle = "1px solid red";                     // When a field is invalid, this is it's style
var submitButtonID = "submitButton";                        // The ID tag of the submit button
var submitButtonEnableLable = "submitButtonEnableLable";    // The ID tag of the lable urging you to review your invalid form

// This function checks if all the fields are valid 
// and enables the submit button if they are
function enableSubmit(){
    if(allIsCheckedValue === 0){    // Enable
        document.getElementById(submitButtonID).disabled = false;
        document.getElementById(submitButtonEnableLable).style.display = "none";
    }
    else{   // Disable
        document.getElementById(submitButtonID).disabled = true;
        document.getElementById(submitButtonEnableLable).style.display = "block";
    }
}

// This function checks that the value of the inputBoxName ID'd input is text only
function checkName(inputBoxName, textBoxID){
    // The regex defining the desired input
    var patt = new RegExp("^[a-zA-Z]+$");
    item = document.getElementById(inputBoxName);
    
    // Validate input by running it through the regex
    // if the regex passes the input is valid
    if(patt.test(item.value)){
        item.style.border = borderOkStyle;                          // Colors the input as valid
        allIsCheckedValue = allIsCheckedValue & ~(1 << textBoxID);  // Set field's bit to 0 if input is valid
    }
    else{
        item.style.border = borderNotOkStyle;                       // Colors the input as invalid
        allIsCheckedValue = allIsCheckedValue | (1 << textBoxID);   // Set field's bit to 1 if input is invalid
    }
    enableSubmit();
}

// This function checks that the value of the inputBoxName ID'd input is a proper email
function checkEmail(inputBoxName, textBoxID){
    var patt = new RegExp("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
    item = document.getElementById(inputBoxName);
    // Valid input
    if(patt.test(item.value)){
        item.style.border = borderOkStyle;
        allIsCheckedValue = allIsCheckedValue & ~(1 << textBoxID); // Set field's bit to 0 if input is valid
    }
    else{
        item.style.border = borderNotOkStyle;
        allIsCheckedValue = allIsCheckedValue | (1 << textBoxID); // Set field's bit to 1 if input is invalid
    }
    enableSubmit();
}

// This function checks that the value of the inputBoxName ID'd input is actually set
function checkDate(inputBoxName, textBoxID){
    item = document.getElementById(inputBoxName);
    // Valid input
    if(item.value !== ""){
        item.style.border = borderOkStyle;
        allIsCheckedValue = allIsCheckedValue & ~(1 << textBoxID); // Set field's bit to 0 if input is valid
    }
    else{
        item.style.border = borderNotOkStyle;
        allIsCheckedValue = allIsCheckedValue | (1 << textBoxID); // Set field's bit to 1 if input is invalid
    }
    enableSubmit();
}

// This function checks that the value of the inputBoxName ID'd input is a valid phone number of atleast 7 digits
function checkTelephone(inputBoxName, textBoxID){
    var patt = new RegExp("^\\+?[0-9\\- ]{7,}$");
    item = document.getElementById(inputBoxName);
    // Valid input
    if(patt.test(item.value)){
        item.style.border = borderOkStyle;
        allIsCheckedValue = allIsCheckedValue & ~(1 << textBoxID); // Set field's bit to 0 if input is valid
    }
    else{
        item.style.border = borderNotOkStyle;
        allIsCheckedValue = allIsCheckedValue | (1 << textBoxID); // Set field's bit to 1 if input is invalid
    }
    enableSubmit();
}


//-------------------------------------------
// Birthday picker logics

// Returns how many days are in a certain month on a certain year
function daysInMonth(year, month) {
    return new Date(year, month, 0).getDate();
}

// This function gets the input select tags of year, month and day of some date logic
// It makes sure that the day select field has the correct amount of days for that months selected
function setBirthdayDaySelect(yearSelect, monthSelect, daySelect){
    // Gets the elements from the parameters
    var birthdayYear = document.getElementById(yearSelect).value;
    var birthdayMonth = document.getElementById(monthSelect).value;
    var daySelectInput = document.getElementById(daySelect);
    
    // Gets the number of days in this month
    var numberOfDays = daysInMonth(birthdayYear, birthdayMonth);
    
    // If a user has already selected a day that exists within the new month
    // the date should be re selected, so save the date in temp
    var temp = -1;
    if(daySelectInput.value <= numberOfDays)
        temp = daySelectInput.value;
    
    daySelectInput.innerHTML = "";                                  // Clears the select element
    daySelectInput.innerHTML = "<option></option>";                 // Adds an empty unselected state
    for(var i = 1; i < numberOfDays + 1; i++){                      // Goes through all the days to create
        var str = "<option>" + i + "</option>";                     // Creates the new selec with the new date
        daySelectInput.innerHTML = daySelectInput.innerHTML + str;  // Appends the new option the the current select
    }
    
    // If you saved a temp value, set it as the current value
    if(temp > -1)
        daySelectInput.value = temp;
}

//-------------------------------------------
// Expertise adder logics


var expertiseCounter = 0;       // This counts how many expertises are added
                                // it is used the give new expertises unique Id tags

// Uses regex to make a replaceAll function, the normal string.replace
// doesnt replace all occurrences
function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}

// This function adds a new expertise
function addANewExpertise(){
    expertiseCounter++;                                                             // Increases the count
    item = document.getElementById("workExpertiseListDiv");                         // Gets the expertise element, here the new expertises
                                                                                    // html will be added
    
    
    var newRowCode = document.getElementById("jobExpertiseTemplate").innerHTML;     // There is a hidden <div> that contains the HTML template for 
                                                                                    // a new expertise that will be added
    
    var text = document.getElementById("workExpertiseExpertiseSelect").value;       // Gets the selected expertise
    newRowCode = replaceAll("jobExpertiseRowPlaceholderText", text, newRowCode);    // Adds it to the template HTML code
    
    var text2 = document.getElementById("workExpertiseYearsSelect").value;          // Gets the selected amount of years
    newRowCode = replaceAll("jobExpertiseRowPlaceholderYears", text2, newRowCode);  // Adds them to the template HTML code
    
    var newId = "jobExpertiseRow" + expertiseCounter;                               // Gives this HTML block, hidden inputs 
    newRowCode = replaceAll("jobExpertiseRow", newId, newRowCode);                  // and event parameters unique Ids'
    
    item.innerHTML = item.innerHTML + newRowCode;                                   // Applies the new code
    
                                                                                    // Setts the values for the new hidden input fields
    document.getElementById("jobExpertiseRow" + expertiseCounter + "ExperiteseInput").value = text; 
    document.getElementById("jobExpertiseRow" + expertiseCounter + "YearsInput").value = text2;
}

// An element ID of a expertise row table is given, the expertise is then deleted
function removeRow(rowID){
    (function(x){x.parentNode.removeChild(x);})(document.getElementById(rowID))
}