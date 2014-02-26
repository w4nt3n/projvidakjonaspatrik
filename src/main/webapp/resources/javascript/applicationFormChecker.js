/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//-------------------------------------------
// FOrm validation function

var allIsCheckedValue = 0x1FFF; // When all fields have valid input, this will be set to 0.

var borderOkStyle = "1px solid lime";
var borderNotOkStyle = "1px solid red";
var submitButtonID = "submitButton";
var submitButtonEnableLable = "submitButtonEnableLable";

function enableSubmit()
{
    if(allIsCheckedValue === 0){
        document.getElementById(submitButtonID).disabled = false;
        document.getElementById(submitButtonEnableLable).style.display = "none";
    }
    else{
        document.getElementById(submitButtonID).disabled = true;
        document.getElementById(submitButtonEnableLable).style.display = "block";
    }
}

function checkName(inputBoxName, textBoxID){
    var patt = new RegExp("^[a-zA-Z]+$");
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

function daysInMonth(year, month) {
    return new Date(year, month, 0).getDate();
}

function setBirthdayDaySelect(yearSelect, monthSelect, daySelect){
    var birthdayYear = document.getElementById(yearSelect).value;
    var birthdayMonth = document.getElementById(monthSelect).value;
    var numberOfDays = daysInMonth(birthdayYear, birthdayMonth);
    
    daySelectInput = document.getElementById(daySelect);
    
    var temp = -1;
    if(daySelectInput.value <= numberOfDays){
        temp = daySelectInput.value;
    }
    
    daySelectInput.innerHTML = "";
    daySelectInput.innerHTML = "<option></option>";
    for(var i = 1; i < numberOfDays + 1; i++){
        var str = "<option>" + i + "</option>";
        daySelectInput.innerHTML = daySelectInput.innerHTML + str;
    }
    
    if(temp > -1)
        daySelectInput.value = temp;
}

//-------------------------------------------
// Expertise adder logics

var expertiseCounter = 0;

function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}

function addANewExpertise(){
    expertiseCounter++;
    item = document.getElementById("workExpertiseListDiv");
    
    var newRowCode = document.getElementById("jobExpertiseTemplate").innerHTML;
    
    var text = document.getElementById("workExpertiseExpertiseSelect").value;
    newRowCode = replaceAll("jobExpertiseRowPlaceholderText", text, newRowCode);
    
    var text2 = document.getElementById("workExpertiseYearsSelect").value;
    newRowCode = replaceAll("jobExpertiseRowPlaceholderYears", text2, newRowCode);
    
    var newId = "jobExpertiseRow" + expertiseCounter;
    newRowCode = replaceAll("jobExpertiseRow", newId, newRowCode);
    
    item.innerHTML = item.innerHTML + newRowCode;
    
    document.getElementById("jobExpertiseRow" + expertiseCounter + "ExperiteseInput").value = text;
    document.getElementById("jobExpertiseRow" + expertiseCounter + "YearsInput").value = text2;
}

function removeRow(rowID){
    (function(x){x.parentNode.removeChild(x);})(document.getElementById(rowID))
}