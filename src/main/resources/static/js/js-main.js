// Get all the modal elements
let modals = document.querySelectorAll(".modal");

// Get all the buttons that open the modals
let buttons = document.querySelectorAll(".post-edit-btn, .post-edit-btn-detailed");

// Get all the <span> elements that close the modals
let spans = document.querySelectorAll(".close");

// Attach event listeners to each button to open the respective modal
buttons.forEach((button, index) => {
    button.onclick = function() {
        modals[index].style.display = "block";
    };
});

// Attach event listeners to each span to close the respective modal
spans.forEach((span, index) => {
    span.onclick = function() {
        modals[index].style.display = "none";
    };
});

// When the user clicks anywhere outside a modal, close it
window.onclick = function(event) {
    modals.forEach((modal) => {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });
};
