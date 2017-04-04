console.log("JavaScript is online!");

$(document).ready(function () {
    console.log("jQuery is online!");

    $.validator.messages.required = "";

    $("#changePasswordForm input[type='submit']").click(function () {
        $("#changePasswordForm").validate();
    });
});