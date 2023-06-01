
let modals = document.querySelectorAll(".modal");

let buttons = document.querySelectorAll(".post-edit-btn, .post-edit-btn-detailed");

let spans = document.querySelectorAll(".close");

let deleteRegret = document.querySelector(".deleteRegret");

let deleteBtn = document.querySelector(".delete-btn");

let deleteModal = document.querySelector(".delete-modal");

buttons.forEach((button, index) => {
    button.onclick = function() {
        modals[index].style.display = "block";
    };
});

spans.forEach((span, index) => {
    span.onclick = function() {
        modals[index].style.display = "none";
    };
});

window.onclick = function(event) {
    modals.forEach((modal) => {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });
};

if (deleteBtn != null) {
    deleteBtn.onclick = function () {
        deleteModal.style.display = "block";
    };

    deleteRegret.onclick = function() {
        deleteModal.style.display = "none";
    }
}
