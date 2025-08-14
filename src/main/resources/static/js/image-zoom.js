document.addEventListener("DOMContentLoaded", () => {
    // Создаём оверлей один раз
    const overlay = document.createElement("div");
    overlay.id = "fullscreenOverlay";
    overlay.className = "fullscreen-overlay";
    overlay.innerHTML = `
        <span class="close-btn">&times;</span>
        <img id="fullscreenImage" class="fullscreen-image">
    `;
    document.body.appendChild(overlay);

    const fullscreenImg = document.getElementById("fullscreenImage");
    const closeBtn = overlay.querySelector(".close-btn");

    // Функция открытия
    function openOverlay(img) {
        const fullImageUrl = img.dataset.full || img.src;
        fullscreenImg.src = fullImageUrl;
        overlay.style.display = "flex";
        requestAnimationFrame(() => overlay.classList.add("show"));
        document.body.style.overflow = "hidden";
    }

    // Функция закрытия
    function closeOverlay() {
        overlay.classList.remove("show");
        setTimeout(() => {
            overlay.style.display = "none";
            document.body.style.overflow = "";
        }, 250);
    }

    // Навешиваем обработчики на все zoomable-image
    document.querySelectorAll(".zoomable-image").forEach(img => {
        img.addEventListener("click", () => openOverlay(img));
    });

    // Закрытие по крестику, клику вне картинки и Esc
    closeBtn.addEventListener("click", closeOverlay);
    overlay.addEventListener("click", (e) => {
        if (e.target === overlay) closeOverlay();
    });
    document.addEventListener("keydown", (e) => {
        if (e.key === "Escape") closeOverlay();
    });
});